package entity;

public class ExerciseFactory {
    public static Exercise create(String name, String muscle, String description, String intensity) {
        return new Exercise(name, muscle, description, intensity);
    }
}

