package interface_adapter.results;
import java.util.ArrayList;

/** Store the state of the information in the results view*/
public class ResultsState {

    /** Initialize private variable for the exercises, and exercise erorr which may occur  */
    private ArrayList<ArrayList<String>> exercise = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> exerciseError = null;

    /** Copy constructor for creating a new ResultsState instance as a copy of another ResultsState*/
    /** Default constructor to create an empty ResultsState (required due to the presence of a copy constructor)*/
    public ResultsState() {}

    /** Getter methods to retrieve the values of exerciseSearchResults*/

    public ArrayList<ArrayList<String>> getExercise() {
        return exercise;
    }

    public ArrayList<ArrayList<String>> getExerciseError() {
        return exerciseError;
    }

    /** Setter methods to set the values of exerciseSearchResults*/
    public void setExercise(ArrayList<ArrayList<String>> exercise) {
        this.exercise = exercise;
    }
    public void setExerciseError(ArrayList<ArrayList<String>> exerciseError) {
        this.exerciseError = exerciseError;
    }
}
