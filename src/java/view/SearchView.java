package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;


public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ResultsViewModel resultsViewModel;

    final JComboBox<String> exerciseTypeInputField = new JComboBox<String>();
    private final JLabel exerciseTypeErrorField = new JLabel();

    final JComboBox<String> muscleGroupInputField = new JComboBox<String>();
    private final JLabel muscleGroupErrorField = new JLabel();

    final JComboBox<String> difficultyInputField = new JComboBox<String>();
    private final JLabel difficultyErrorField = new JLabel();

    final JButton search;
    final JButton cancel;
    private final SearchController searchController;

    private JPanel searchResultPanel;  // Create a panel to contain the search results

    public SearchView(SearchViewModel searchViewModel,
                      SearchController controller,
                      ViewManagerModel viewManagerModel,
                      LoggedInViewModel loggedInViewModel,
                      ResultsViewModel resultsViewModel) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.resultsViewModel = resultsViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel exerciseTypeLabel = new JLabel(SearchViewModel.EXERCISE_LABEL);
        JLabel muscleGroupLabel = new JLabel(SearchViewModel.MUSCLE_LABEL);
        JLabel difficultyLabel = new JLabel(SearchViewModel.DIFFICULTY_LABEL);

        // Create an array of items for the JComboBox
        String[] exerciseTypes = {"strength", "olympic_weightlifting", "plyometrics", "powerlifting", "cardio", "stretching", "strongman"};
        JComboBox<String> exerciseTypeInputField = new JComboBox<>(exerciseTypes);
        String[] muscleGroups = {"biceps", "abdominals", "abductors", "adductors", "calves", "chest", "forearms", "glutes", "hamstrings", "lats", "lower_back", "middle_back", "neck", "quadriceps", "traps", "triceps"};
        JComboBox<String> muscleGroupInputField = new JComboBox<>(muscleGroups);
        String[] difficulties = {"beginner", "intermediate", "expert"};
        JComboBox<String> difficultyInputField = new JComboBox<>(difficulties);

        JPanel buttons = new JPanel();
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        cancel = new JButton(searchViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        searchResultPanel = new JPanel(); // Initialize the panel for search results
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS)); // Set layout for search results panel

        search.addActionListener(          // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /** Passes the inputted exercise, muscle group, and difficulty to the search controller when
                     * the search button has been pressed. It also checks for errors, and if there are results
                     * that match the user's inputted criteria, the user is taken to the results view. */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();

                            currentState.setExerciseType((String) exerciseTypeInputField.getSelectedItem());
                            currentState.setMuscleGroup((String) muscleGroupInputField.getSelectedItem());
                            currentState.setDifficulty((String) difficultyInputField.getSelectedItem());

                            searchViewModel.setState(currentState);
                            try {
                                searchController.execute(
                                        currentState.getExerciseType(),
                                        currentState.getMuscleGroup(),
                                        currentState.getDifficulty()
                                );
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            // Only switch to results screen if there are results matching the user's criteria
                            if (currentState.getExerciseSearchResultsError() == null) {
                                viewManagerModel.setActiveView(resultsViewModel.getViewName());
                                viewManagerModel.firePropertyChanged();
                            }
                            // Reset search results error in state to null
                            currentState.setExerciseSearchResultsError(null);
                        }
                    }
                }
        );
        cancel.addActionListener(new ActionListener() {
            @Override
            /** If the cancel button is clicked, then the user is taken back to the login view*/
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {

                    // Switch to the SearchView
                    viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                    System.out.println("Active view set to: " + loggedInViewModel.getViewName());
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(exerciseTypeLabel);
        this.add(exerciseTypeInputField);
        this.add(exerciseTypeErrorField);
        this.add(muscleGroupLabel);
        this.add(muscleGroupInputField);
        this.add(muscleGroupErrorField);
        this.add(difficultyLabel);
        this.add(difficultyInputField);
        this.add(difficultyErrorField);
        this.add(buttons);
        this.add(searchResultPanel); // Add the search results panel to the main view

        JLabel subTitle = new JLabel("Search Result Screen");
        subTitle.setAlignmentX(CENTER_ALIGNMENT);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {}

    @Override
    /** In the event of a property change, the search state gets updated, and a popup is displayed if there are
     * no exercises that match the criteria inputted by the user */
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property changed: " + evt.getPropertyName());
        SearchState state = (SearchState) evt.getNewValue();

        if (state.getExerciseSearchResultsError() != null) {
            JOptionPane.showMessageDialog(this, state.getExerciseSearchResultsError());
        }

        setFields(state);
    }
    /** Sets the user inputted values */
    private void setFields(SearchState state) {
        exerciseTypeInputField.setSelectedItem(state.getExerciseType());
        muscleGroupInputField.setSelectedItem(state.getMuscleGroup());
        difficultyInputField.setSelectedItem(state.getDifficulty());
    }
}