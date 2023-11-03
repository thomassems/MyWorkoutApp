package entity;

public class Exercise {
    private final String title;
    private final String muscle;
    private final String description;
    private final String difficulty;
    public Exercise (String title, String muscle, String description, String difficutly){
        this.title = title;
        this.muscle = muscle;
        this.description = description;
        this.difficulty = difficutly;
    }

    public String getTitle() {
        return title;
    }
    public String getMuscle(){
        return muscle;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
