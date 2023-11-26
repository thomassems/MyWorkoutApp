package data_access;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.*;
import use_case.delete.DeleteUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.results.ResultsDataAccessInterface;
import use_case.retrieve.RetrieveUserDataAccessInterface;
import use_case.search.SearchUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

//package data_access;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, SearchUserDataAccessInterface, DeleteUserDataAccessInterface, ResultsDataAccessInterface, RetrieveUserDataAccessInterface {
    private final File accountsFile;
    private final File exerciseFile;
    private UserFactory userFactory;
    private ExerciseFactory exerciseFactory;
    private final Map<String, User> accounts = new HashMap<>();
    private final Map<String, ArrayList<Exercise>> exercises = new HashMap<>();
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    public FileUserDataAccessObject(String accountPath, String exercisePath, UserFactory userFactory, ExerciseFactory exerciseFactory) throws IOException{
        this.userFactory = userFactory;
        this.exerciseFactory = exerciseFactory;
        exerciseFile = new File(exercisePath);
        accountsFile = new File(accountPath);
        headers.put("username", 0);
        headers.put("name", 1);
        headers.put("password", 2);

        if (accountsFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(accountsFile))) {
                String header = reader.readLine();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String name = String.valueOf(col[headers.get("name")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    User user = userFactory.create(name, username, password);
                    accounts.put(username, user);
                }
            }
        }if (exerciseFile.length() == 0) {
            saveExercise();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(exerciseFile))) {
                String header2 = reader.readLine();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(";");
                    String name = "";
                    ArrayList<Exercise> exerciselist = new ArrayList<>();
                    for (String i: col){
                        String[] element = i.split("\\|");
                        if (element.length == 1){
                            name = element[0];
                        }
                        else{
                            Exercise a = exerciseFactory.create(element[0], element[1], element[2], element[3]);
                            exerciselist.add(a);
                        }
                    }
                    exercises.put(name, exerciselist);
                }
            }
        }
    }

    /** Returns whether or not a user exists in the database/file. If a user exists meaning their username is in the
     * file of users then return true, otherwise return false. */
    public boolean existsByUsername(String username) {
        return accounts.containsKey(username);
    }

    /** Saves a users information */
    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }
    @Override
    /** Gets the users account information from their username */
    public User get(String username) {
        return accounts.get(username);
    }

    /** Writes a users information to a file*/
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(accountsFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s",
                        user.getUsername(), user.getName(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Saves an exercise to a file, along with the username of the user who is saving the exercise so that they are
     * linked to one another */
    public void saveExercise(String username, String title, String muscle, String description, String difficulty) {
        if (!exercises.containsKey(username)) {
            exercises.put(username, new ArrayList<Exercise>());
        }
        exercises.get(username).add(exerciseFactory.create(title, muscle, description, difficulty));
        this.saveExercise();
    }
    /** Writes the exercise, and username to a file */
    private void saveExercise(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(exerciseFile));
            writer.write("username; exercises(title| muscle| description| difficulty)");
            writer.newLine();

            for (String username : exercises.keySet()) {
                String line = username + ";";
                ArrayList<Exercise> exerList = exercises.get(username);
                for (Exercise exer: exerList){
                    line = line + String.format("%s|%s|%s|%s;", exer.getTitle(), exer.getMuscle(), exer.getDescription(), exer.getDifficulty());
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /** Removes the username from the accounts file, and removes all of their saved exercises from the exercise
     * file */
    @Override
    public void delete(String username) {
        accounts.remove(username);
        save();
        if (exercises.containsKey(username)){
            exercises.remove(username);
            saveExercise();
        }
    }
    /** Calls the api with the user inputted criteria, and returns a JsonNode object that contains the exercises
     * that match the criteria that were returned from the api*/
    @Override
    public JsonNode getApi(String type, String muscle, String difficulty) {
        String apiKey = "KE8a7QjrGwSZ2jx3+4URNg==aPNxCl8ULpu4Trvb";

        StringBuilder apiUrl = new StringBuilder("https://api.api-ninjas.com/v1/exercises?");

        /** Have these conditions because if a user does not input a type, muscle or difficulty, we don't need to
         * call the api with these parameters */
        if (type != null){
            apiUrl.append(String.format("type=%s&", type));
        }
        if (muscle != null){
            apiUrl.append(String.format("muscle=%s&", muscle));
        }
        if (difficulty != null) {
            apiUrl.append(String.format("difficulty=%s&", difficulty));
        }
        URL url = null;
        try {
            url = new URL(apiUrl.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.setRequestProperty("Accept", "application/json");
        connection.addRequestProperty("X-Api-Key", apiKey);
        InputStream responseStream = null;
        try {
            responseStream = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(responseStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<Exercise> getSavedExercises(String username){
        if (exercises.containsKey(username)){
            return exercises.get(username);
        }
        return new ArrayList<>();
    }
}
