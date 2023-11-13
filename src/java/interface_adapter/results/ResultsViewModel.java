package interface_adapter.results;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;

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
    private ArrayList<ArrayList<String>> workouts;

    private ResultsState state = new ResultsState();

    public ResultsViewModel() {
        super("results");
    }

    public void setState(ResultsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void setExercise(ArrayList<ArrayList<String>> workouts){
        this.workouts = workouts;
    }
    public ArrayList<ArrayList<String>> getExercise(){
        return workouts;
    }

    public ResultsState getState() {
        return state;
    }
}
