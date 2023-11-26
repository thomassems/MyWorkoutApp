package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    /** Takes in the name of the view we want the user to see and displays this view to the user */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
        firePropertyChanged();
    }

    /** Notifies the view of a change in the view that is being displayed */
    public void firePropertyChanged() {
        support.firePropertyChange("view", (null), this.activeViewName
        );
    }
    /** Listens for a change in property/state */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

