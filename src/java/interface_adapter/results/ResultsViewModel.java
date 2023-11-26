package interface_adapter.results;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ResultsViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Search Results";
    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String WORKOUTS_BUTTON_LABEL = "Workouts";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String DIFFICULTY_LABEL = "Difficulty";
    public static final String MUSCLE_GROUP_LABEL = "Muscle Group";
    public static final String DESCRIPTION_LABEL = "Description";
    public static final String NAME_LABEL = "Name";
    private ArrayList<ArrayList<String>> exercise;

    private ResultsState state = new ResultsState();

    /** Instantiates ResultsViewModel with "results" parameter as the view name */
    public ResultsViewModel() {
        super("results");
    }
    /** Initializes the state */
    public void setState(ResultsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Alerts the view of a property change. This will be called by the presenter */
    public void firePropertyChanged() {
        // Fire property change for "exercise"
        support.firePropertyChange("exercise", null, this.state);
    }

    /** Adds a property change listener to the view model */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void setExercise(ArrayList<ArrayList<String>> exercise){
        this.exercise = exercise;
        // Add logging to verify that exercise is being updated
        System.out.println("Exercise updated: " + exercise.get(0));
        firePropertyChanged();
    }
    public ArrayList<ArrayList<String>> getExercise(){
        return exercise;
    }

    public ResultsState getState() {
        return state;
    }
}
