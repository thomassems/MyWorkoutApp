package view;

import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeleteViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;

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
    private final SignupViewModel signupViewModel;
    private final SearchViewModel searchViewModel;

    private final RetrieveViewModel retrieveViewModel;
//    private final DeleteViewModel deleteViewModel;

    JLabel username;
    final JButton logOut;
    final JButton search;
    private final DeleteController deleteController;
    final JButton workouts;
    final JButton delete;
    JLabel user;

    public LoggedInView(LoggedInViewModel loggedInViewModel,
                        ViewManagerModel viewManagerModel,
                        SignupViewModel signupViewModel,
                        SearchViewModel searchViewModel,
                        RetrieveViewModel retrieveViewModel,
//                        DeleteViewModel deleteViewModel,
                        DeleteController deleteController) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.searchViewModel = searchViewModel;
        this.retrieveViewModel = retrieveViewModel;
//        this.deleteViewModel = deleteViewModel;
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
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);

        workouts = new JButton(LoggedInViewModel.WORKOUTS_BUTTON_LABEL);
        buttons.add(workouts);

        delete = new JButton("Delete Account");
        buttons.add(delete);

        logOut.addActionListener(new ActionListener() {
            /** If the logout button is clicked, the user is redirected back to the signup screen*/
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(logOut)) {

                    // Switch to the SignupView
                    viewManagerModel.setActiveView(signupViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();

                    System.out.println("Active view set to: " + signupViewModel.getViewName());
                }
            }
        }
        );;

        search.addActionListener(new ActionListener() {
            /** If the search button is clicked then the user is taken to the search view*/
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {

                    // Switch to the SearchView
                    viewManagerModel.setActiveView(searchViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                    System.out.println("Active view set to: " + searchViewModel.getViewName());
                }
            }
        });

        workouts.addActionListener(new ActionListener() {
            @Override
            /** If the retrieve button is clicked. Then the user is taken to the retrieve view where they can see all
             * of their saved workouts*/
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(workouts)) {

                    // Switch to retrieve view
                    viewManagerModel.setActiveView(retrieveViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();

                    // Make the retrieve view screen match the retrieve view model again (set saved exercises on exercises panel)
                    retrieveViewModel.firePropertyChanged();

                    System.out.println("Active view set to :" + retrieveViewModel.getViewName());
                }
            }
        });

        delete.addActionListener(
                /** If the delete button is clicked, the users account gets deleted, and they get taken back to the
                 * signup view*/
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete)) {
                            deleteController.execute(username.getText());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(user);
        this.add(userInfo);
        this.add(username);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {}

    @Override
    /** Updates the state, and fields when a property change event occurs*/
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property changed: " + evt.getPropertyName());
        LoggedInState state = (LoggedInState) evt.getNewValue();
        setFields(state);
    }

    /** Sets the username and user field*/
    public void setFields(LoggedInState state) {
        username.setText(state.getUsername());
        user.setText(state.getUser());
    }
}