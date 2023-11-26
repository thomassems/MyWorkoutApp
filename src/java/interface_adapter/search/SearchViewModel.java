package interface_adapter.search;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Search Exercise";
    public static final String EXERCISE_LABEL = "Exercise Type";
    public static final String MUSCLE_LABEL = "Muscle Group";
    public static final String DIFFICULTY_LABEL = "Difficulty";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private SearchState state = new SearchState();

    public SearchViewModel() {
        super("search");
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** Adds support for being able to react to changes in property/state */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /** Listens and reacts to change in property*/
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchState getState() {
        return state;
    }
}
