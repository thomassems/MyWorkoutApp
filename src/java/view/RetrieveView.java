package view;

import entity.Exercise;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveController;
import interface_adapter.retrieve.RetrieveState;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class RetrieveView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "saved exercises";
    private final RetrieveViewModel retrieveViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

    final JButton returnButton;
    private final RetrieveController retrieveController;
    private JPanel exercisesPanel;  // Create a panel to contain the search results

    public RetrieveView(RetrieveViewModel retrieveViewModel, RetrieveController controller, ViewManagerModel viewManagerModel,
                        LoggedInViewModel loggedInViewModel) {

        this.retrieveController = controller;
        this.retrieveViewModel = retrieveViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.retrieveViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RetrieveViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        returnButton = new JButton(retrieveViewModel.RETURN_BUTTON_LABEL);

//        RetrieveState currentState = retrieveViewModel.getState();

        // Observe when the user logs in succesfully
//        loggedInViewModel.addPropertyChangeListener(
//                new PropertyChangeListener() {
//                    @Override
//                    public void propertyChange(PropertyChangeEvent evt) {
//                        if ("logged in state".equals(evt.getPropertyName())) {
//                            System.out.println("Logged in state updated");
//
//                            // Get the saved exercises when the user logs in
//                            LoggedInState currentState = loggedInViewModel.getState();
//                            loggedInViewModel.setState(currentState);
//                        }
//                    }
//                }
//            );

        returnButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(returnButton)) {

                            // Switch to LoggedInView
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                            System.out.println("Active view set to: " + loggedInViewModel.getViewName());
                        }

                    }
                }

        );

        // Set layout for JPanel
        GridBagLayout panelGridBagLayout = new GridBagLayout();
        GridBagConstraints panelGridBagConstraints = new GridBagConstraints();

        // Add grid layout to buttons panel
//        exercisesPanel = new JPanel(new GridBagLayout());
        exercisesPanel = new JPanel();
        // Create grid bag constraints for the exercises panel
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0, 0, 0, 10); // Set the bottom margin (space) between rows

        retrieveViewModel.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("saved exercises".equals(evt.getPropertyName())) {

                            LoggedInState currentState = loggedInViewModel.getState();
                            retrieveController.execute(currentState.getUsername());
//                            RetrieveState currentRetrieveState = retrieveViewModel.getState();
//                            retrieveViewModel.setState(currentRetrieveState);
                            ArrayList<ArrayList<String>> exercises = retrieveViewModel.getSavedExercises();
                            System.out.println(exercises);

                            System.out.println(exercises);
                            if (exercises != null && !exercises.isEmpty()) {
//                                displayNewExercise(exercisesPanel, exercises, gbc);
                                displayNewExercise(exercisesPanel, exercises);
                            }

//                            RetrieveState currentRetrieveState = retrieveViewModel.getState();
//                            retrieveViewModel.setState(currentRetrieveState);
                        }
                    }
                }
        );

        // Add a scroll panel to the screen
//        JScrollPane scrollPane = new JScrollPane(exercisesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(returnButton);
        this.add(exercisesPanel);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("saved exercises".equals(evt.getPropertyName())) {
            System.out.println("Property changed: " + evt.getPropertyName());
            RetrieveState state = (RetrieveState) evt.getNewValue();
            setFields(state);
        }
    }

    private void setFields(RetrieveState state) {
        results = state.getSavedExercises();
    }

//    private JPanel updateRetrieveView(JPanel jPanel, GridBagConstraints gridBagConstraints) {
//        LoggedInState currentState = loggedInViewModel.getState();
//        retrieveController.execute(currentState.getUsername());
////        RetrieveState currentRetrieveState = retrieveViewModel.getState();
////        retrieveViewModel.setState(currentRetrieveState);
//
//
//        // Create a text area for each exercises in the user's saved list
//        if (retrieveViewModel.getSavedExercises() != null) {
//            for (ArrayList<String> exercise : retrieveViewModel.getSavedExercises()) {
//
//                JPanel singleExercise = new JPanel();
//
//                // Create text area
//                JTextArea newExerciseLabel = new JTextArea(1, 110);
//                newExerciseLabel.setEditable(false);
//                newExerciseLabel.setLineWrap(true);
//                newExerciseLabel.setWrapStyleWord(true);
//                newExerciseLabel.setName(exercise.get(0));
//                newExerciseLabel.setText("Name: " + exercise.get(0) + " " + exercise.get(1) + " | " + exercise.get(2) + " " + exercise.get(3));
//
//                // Add the text area to the list of saved exercises arraylist
//                savedExercisesList.add(newExerciseLabel);
//
//                //                                JButton newDeleteButton = new JButton(retrieveViewModel.DELETE_BUTTON_LABEL);
//                //                                newDeleteButton.setName(exercise.get(0));
//                //
//                //                                newDeleteButton.addActionListener(
//                //                                        new ActionListener() {
//                //                                            public void actionPerformed(ActionEvent evt) {
//                //                                                if (evt.getSource().equals(deleteButtons)) {
//                //                                                    RetrieveState currentState = retrieveViewModel.getState();
//                //
//                //                                                    retrieveController.execute(
//                //                                                            currentState.getUsername()
//                //                                                    );
//                //                                                }
//                //                                            }
//                //                                        }
//                //                                );
//                //                                deleteButtons.add(newDeleteButton);
//                //                                singleExercise.add(newDeleteButton);
//
//                // Add the exercise to the exercises panel
//                singleExercise.add(newExerciseLabel);
//                jPanel.add(singleExercise, gridBagConstraints);
//
//                // Increment to the next row to display the next exercise
//                gridBagConstraints.gridy++;
//            }
//        }
//        return jPanel;
//    }

    //    private JPanel displayNewExercise(JPanel jPanel, ArrayList<ArrayList<String>> exercises, GridBagConstraints gridBagConstraints) {
//        // Get only the most recently added exercise.
////        ArrayList<String> newExercise = exercises.get(exercises.size() - 1);
//        jPanel.removeAll();
//
//        for (ArrayList<String> exercise : exercises) {
//            JTextArea exerciseLabel = new JTextArea();
//            exerciseLabel.setEditable(false);
//            exerciseLabel.setLineWrap(true);
//            exerciseLabel.setWrapStyleWord(true);
//
//            exerciseLabel.setName(exercise.get(0));
//            exerciseLabel.setText("Name: " + exercise.get(0) + " " + exercise.get(1) + " | " + exercise.get(2) + " " + exercise.get(3));
//
//            jPanel.add(exerciseLabel);
//
//            gridBagConstraints.gridy++;
//        }
//
//        return jPanel;
//    }
//}
    private JPanel displayNewExercise(JPanel jPanel, ArrayList<ArrayList<String>> exercises) {
        // Get only the most recently added exercise.
//        ArrayList<String> newExercise = exercises.get(exercises.size() - 1);
        jPanel.removeAll();

        System.out.println(exercises);

        String[] columnNames = {"Name", "Item2", "Item3", "Item4"};

        // Initializing the JTable using SwingUtilities.invokeLater()
        SwingUtilities.invokeLater(() -> {
            JTable j = new JTable(convertTo2DArray(exercises), columnNames);
//            j.setBounds(30, 40, 400, 300);

            JScrollPane sp = new JScrollPane(j);
            jPanel.add(sp);

            // Repaint the panel after adding components
            jPanel.revalidate();
            jPanel.repaint();
        });

        return jPanel;
    }

    private String[][] convertTo2DArray(ArrayList<ArrayList<String>> exercises) {
        String[][] results = new String[exercises.size()][];
        for (int i = 0; i < exercises.size(); i++) {
            ArrayList<String> row = exercises.get(i);
            results[i] = row.toArray(new String[row.size()]);
        }
        return results;
    }
}
