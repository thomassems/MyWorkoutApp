package view;

import interface_adapter.delete.DeleteController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SearchViewModel searchViewModel;


    JLabel username;
    final JButton logOut;
    final JButton searchButton;
    private final DeleteController deleteController;
    final JButton delete;
    JLabel user;

    public LoggedInView(LoggedInViewModel loggedInViewModel,
                        ViewManagerModel viewManagerModel,
                        SearchViewModel searchViewModel,
                        DeleteController deleteController) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.deleteController = deleteController;

        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        user = new JLabel();

        JLabel userInfo = new JLabel("Account: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        // Create a button for searching
        searchButton = new JButton("Search");
        buttons.add(searchButton);

        delete = new JButton("Delete User");
        buttons.add(delete);

        logOut.addActionListener(this);
        delete.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete)) {
                            deleteController.execute(username.getText());
                        }
                    }
                }
        );

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {

                    // Switch to the SearchView
                    viewManagerModel.setActiveView(searchViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                    System.out.println("Active view set to: " + searchViewModel.getViewName());
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(user);
        this.add(userInfo);
        this.add(username);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property changed: " + evt.getPropertyName());
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        user.setText(state.getUser());
    }
}