package interface_adapter.retrieve;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class RetrieveViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Saved Exercises";
    public static final String RETURN_BUTTON_LABEL = "Return";
    public static final String DELETE_BUTTON_LABEL = "Delete";
    private ArrayList<ArrayList<String>> savedExercisesLabels;

    private RetrieveState state = new RetrieveState();

    public RetrieveViewModel() {
        super("saved exercises");
    }

    public void setState(RetrieveState state) {
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

    public RetrieveState getState() {
        return state;
    }

    public ArrayList<ArrayList<String>> getSavedExercises() {
        return savedExercisesLabels;
    }

    public void setSavedExercises(ArrayList<ArrayList<String>> savedExercises) {
        this.savedExercisesLabels = savedExercises;
    }
}

