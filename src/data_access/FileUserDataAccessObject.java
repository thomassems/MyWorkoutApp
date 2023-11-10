package data_access;

import entity.*;
import use_case.login.LoginUserDataAccessInterface;
import use_case.search.SearchUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.retrieve.RetrieveUserDataAccessInterface;

import java.io.*;
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

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, SearchUserDataAccessInterface, RetrieveUserDataAccessInterface {
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
                        String[] element = i.split(",");
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

    public boolean existsByUsername(String username) {
        return accounts.containsKey(username);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }
    @Override
    public User get(String username) {
        return accounts.get(username);
    }

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
    private void saveExercise(String username,String title, String muscle, String description, String difficulty) {
        if (!exercises.containsKey(username)){
            exercises.put(username, new ArrayList<Exercise>());
        }
        exercises.get(username).add(exerciseFactory.create(title, muscle, description, difficulty));
        this.saveExercise();
    }
    public void saveExercise(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(exerciseFile));
            writer.write("username; exercises(title, muscle, description, difficulty)");
            writer.newLine();

            for (String username : exercises.keySet()) {
                String line = username + ";";
                ArrayList<Exercise> exerList = exercises.get(username);
                for (Exercise exer: exerList){
                    line = line + String.format("%s,%s,%s, %s;", exer.getTitle(), exer.getMuscle(), exer.getDescription(), exer.getDifficulty());
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Exercise> getExercises(String username) {
            return exercises.get(username);
    }
}