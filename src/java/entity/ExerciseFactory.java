package entity;

public class ExerciseFactory {
    /** Creates an Exercise object with the exercise name, muscle group, a description of how to perform
     * the exercise, and the intensity of the exercise */
    public static Exercise create(String name, String muscle, String description, String intensity) {
        return new Exercise(name, muscle, description, intensity);
    }
}

