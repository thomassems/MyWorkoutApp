package use_case.results;

public class ResultsInputData {
    final private String username;
    final private String title;
    final private String muscle;
    final private String description;
    final private String difficulty;

    public ResultsInputData(String username, String title, String muscle, String description, String difficulty){
        this.username = username;
        this.title = title;
        this.muscle = muscle;
        this.description = description;
        this.difficulty = difficulty;
    }
    public String getUsername(){
        return username;
    }
    public String getTitle(){
        return title;
    }
    public String getMuscle(){
        return muscle;
    }
    public String getDescription(){
        return description;
    }
    public String getDifficulty(){
        return difficulty;
    }
}
