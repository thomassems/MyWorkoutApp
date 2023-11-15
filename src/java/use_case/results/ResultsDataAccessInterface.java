package use_case.results;

public interface ResultsDataAccessInterface {
    void saveExercise(String username, String title, String muscle, String description, String difficulty);
}
