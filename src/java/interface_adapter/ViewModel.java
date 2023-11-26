package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {

    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }
    /** Called to notify the view of a change in property/state */
    public abstract void firePropertyChanged();
    /** Listens for a change in property/state*/
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
