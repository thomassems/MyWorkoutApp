package view;

import com.fasterxml.jackson.core.JsonToken;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;



public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    private Boolean testing = false;
    public final String viewName = "results";
    private final ResultsViewModel resultsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final SearchViewModel searchViewModel;

    private final RetrieveViewModel retrieveViewModel;

    ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    private final ArrayList<ArrayList<String>> resultsError = null;


    final JButton home;
    final JButton search;
    final JButton workouts;
    private final ResultsController resultsController;
    private JPanel searchResultPanel;  // Create a panel to contain the search results

    public ResultsView(ResultsViewModel resultsViewModel,
                       ResultsController controller,
                       ViewManagerModel viewManagerModel,
                       LoggedInViewModel loggedInViewModel, RetrieveViewModel retrieveViewModel,
                       SearchViewModel searchViewModel) {
        this.resultsController = controller;
        this.resultsViewModel = resultsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchViewModel = searchViewModel;
        this.retrieveViewModel = retrieveViewModel;
//        this.results = resultsViewModel.getExercise();
        this.resultsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ResultsViewModel.TITLE_LABEL);
        Font f = title.getFont();
        Font font = new Font(f.getFontName(), Font.BOLD, 16);
        title.setFont(font);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        home = new JButton(ResultsViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);
        search = new JButton(ResultsViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        workouts = new JButton(ResultsViewModel.WORKOUTS_BUTTON_LABEL);
        buttons.add(workouts);

        search.addActionListener(          // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            ResultsState currentState = resultsViewModel.getState();
                            resultsViewModel.setState(currentState);

                            // Switch back to the SearchView
                            viewManagerModel.setActiveView(searchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                            System.out.println("Active view set to: " + searchViewModel.getViewName());
                        }
                    }
                }
        );
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(home)) {

                    // Switch to the LoggedInView
                    viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                    System.out.println("Active view set to: " + loggedInViewModel.getViewName());
                }
            }
        });

        workouts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(workouts)) {

                    // Switch to retrieve view
                    viewManagerModel.setActiveView(retrieveViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                    retrieveViewModel.firePropertyChanged();
                    System.out.println("Active view set to :" + retrieveViewModel.getViewName());
                }
            }
        });

        // Create a panel to search and all specifications for the grid.
        searchResultPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10); // Set the bottom margin (space) between rows

        // Add a property change listener to the ResultsViewModel
        resultsViewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Handle the property change event, e.g., update the UI with new exercises
                if ("exercise".equals(evt.getPropertyName())) {

                    // TODO: Line below, is it according to CA to get the Usename?

                    String username = loggedInViewModel.getState().getUsername();
                    searchResultPanel = updateResultsPanel(searchResultPanel, resultsViewModel.getExercise(), gridBagConstraints, username);
                }
                ResultsState currentState = resultsViewModel.getState();
                resultsViewModel.setState(currentState);
            }
        });
        // Make pane scrollable
        JScrollPane scrollPane = new JScrollPane(searchResultPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
//        this.add(headings);
        this.add(scrollPane); // Add the search results panel to the main view
        this.add(buttons);
        this.setVisible(true);
    }

    private JPanel updateResultsPanel(JPanel panel, ArrayList<ArrayList<String>> newResults, GridBagConstraints gbc, String username) {
        // Clear existing components
        panel.removeAll();

        JLabel nameLabel = new JLabel(ResultsViewModel.NAME_LABEL);
        Font f = nameLabel.getFont();
        Font font = new Font(f.getFontName(), Font.BOLD, 14);

        nameLabel.setFont(font);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nameLabel, gbc);
        gbc.gridx++;

        JLabel muscleGroupLabel = new JLabel(ResultsViewModel.MUSCLE_GROUP_LABEL);
        muscleGroupLabel.setFont(font);
        panel.add(muscleGroupLabel, gbc);
        gbc.gridx++;

        JLabel difficultyLabel = new JLabel(ResultsViewModel.DIFFICULTY_LABEL);
        difficultyLabel.setFont(font);
        panel.add(difficultyLabel, gbc);
        gbc.gridx++;

        gbc.anchor = GridBagConstraints.WEST; // Set anchor to left
        JLabel descriptionLabel = new JLabel(ResultsViewModel.DESCRIPTION_LABEL);
        descriptionLabel.setFont(font);
        panel.add(descriptionLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER; // Set anchor to center
        gbc.gridx = 0; // Move to the next row
        gbc.gridy++;

        if (newResults != null && !newResults.isEmpty() && !newResults.get(0).isEmpty()) {
            for (ArrayList<String> result : newResults) {

                JButton resultButton = getjButton(result, username);
                panel.add(resultButton, gbc);
                gbc.gridx++;
                panel.add(new JLabel(result.get(2)), gbc);
                gbc.gridx++;
                panel.add(new JLabel(result.get(3)), gbc);
                gbc.gridx++;

                gbc.anchor = GridBagConstraints.WEST; // Set anchor to left
                panel.add(new JLabel(result.get(4)), gbc);
                gbc.anchor = GridBagConstraints.CENTER; // Set anchor to left

                gbc.gridx = 0; // Move to the next row
                gbc.gridy++;
            }
        }

        panel.revalidate();
        panel.repaint();
        return panel;
    }

    private JButton getjButton(ArrayList<String> exerciseName, String username) {
        JButton resultButton = new JButton(exerciseName.get(0));
        resultButton.addActionListener(          // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(resultButton)) {
                            ResultsState currentState = resultsViewModel.getState();
                            System.out.println(username);

                            ArrayList exerciseToSave = new ArrayList<>();
                            exerciseToSave.add(exerciseName.get(0));
                            exerciseToSave.add(exerciseName.get(3));
                            exerciseToSave.add(exerciseName.get(2));
                            exerciseToSave.add(exerciseName.get(4));

                            // Only save the newly added exercise if it does not exist already in the user's saved list.
                            if (testing == false && !retrieveViewModel.getSavedExercises().contains(exerciseToSave)) {

                                try {
                                    resultsController.execute(username,
                                            exerciseName.get(0),
                                            exerciseName.get(2),
                                            exerciseName.get(4),
                                            exerciseName.get(3));
                                } catch (IOException e) {
                                throw new RuntimeException(e);
                                }

                                JOptionPane.showMessageDialog(null, exerciseName.get(0) + " added!");
                            }
                            else if (testing == false && retrieveViewModel.getSavedExercises().contains(exerciseToSave)) {
                                // Show popup that the exercise has already been added if it exists in the saved list.
                                JOptionPane.showMessageDialog(null, "This exercise has already been previously saved.");
                            }
                        }
                    }
                }
        );
        return resultButton;
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("exercise".equals(evt.getPropertyName())) {
            System.out.println("Property changed: " + evt.getPropertyName());
            ResultsState state = (ResultsState) evt.getNewValue();
            setFields(state);
        }
    }
    public void setTesting(){
        this.testing=true;
    }

    private void setFields(ResultsState state) {
        results = state.getExercise();
    }
}
