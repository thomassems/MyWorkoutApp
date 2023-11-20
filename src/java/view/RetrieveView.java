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
    private final ArrayList<JTextArea> savedExercisesList = new ArrayList<JTextArea>();
    final ArrayList<JButton> deleteButtons = new ArrayList<JButton>();
    final JButton returnButton;
    private final RetrieveController retrieveController;
    public RetrieveView(RetrieveViewModel retrieveViewModel, RetrieveController controller, ViewManagerModel viewManagerModel,
                        LoggedInViewModel loggedInViewModel) {

        this.retrieveController = controller;
        this.retrieveViewModel = retrieveViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.retrieveViewModel.addPropertyChangeListener(this);

        // Set layout for JPanel
        GridBagLayout panelGridBagLayout = new GridBagLayout();
        GridBagConstraints panelGridBagConstraints = new GridBagConstraints();

        JLabel title = new JLabel("saved exercises");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        RetrieveState currentState = retrieveViewModel.getState();

        // Add grid layout to buttons panel
        JPanel exercisesPanel = new JPanel(new GridBagLayout());

        // Create grid bag constraints for the exercises panel
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        // Observe when the user logs in succesfully
        loggedInViewModel.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("logged in state".equals(evt.getPropertyName())) {

                            // Get the saved exercises when the user logs in
                            LoggedInState currentState = loggedInViewModel.getState();
                            retrieveController.execute(currentState.getUsername());

                            // Create a text area for each exercises in the user's saved list
                            if (!retrieveViewModel.getSavedExercises().isEmpty()) {
                                for (ArrayList<String> exercise : retrieveViewModel.getSavedExercises()) {

                                    JPanel singleExercise = new JPanel();

                                    // Create text area
                                    JTextArea newExerciseLabel = new JTextArea(1, 110);
                                    newExerciseLabel.setEditable(false);
                                    newExerciseLabel.setLineWrap(true);
                                    newExerciseLabel.setWrapStyleWord(true);
                                    newExerciseLabel.setName(exercise.get(0));
                                    newExerciseLabel.setText("Name: " + exercise.get(0) + " " + exercise.get(1) + " | " + exercise.get(2) + " " + exercise.get(3));

                                    // Add the text area to the list of saved exercises arraylist
                                    savedExercisesList.add(newExerciseLabel);

                                    //                                JButton newDeleteButton = new JButton(retrieveViewModel.DELETE_BUTTON_LABEL);
                                    //                                newDeleteButton.setName(exercise.get(0));
                                    //
                                    //                                newDeleteButton.addActionListener(
                                    //                                        new ActionListener() {
                                    //                                            public void actionPerformed(ActionEvent evt) {
                                    //                                                if (evt.getSource().equals(deleteButtons)) {
                                    //                                                    RetrieveState currentState = retrieveViewModel.getState();
                                    //
                                    //                                                    retrieveController.execute(
                                    //                                                            currentState.getUsername()
                                    //                                                    );
                                    //                                                }
                                    //                                            }
                                    //                                        }
                                    //                                );
                                    //                                deleteButtons.add(newDeleteButton);
                                    //                                singleExercise.add(newDeleteButton);

                                    // Add the exercise to the exercises panel
                                    singleExercise.add(newExerciseLabel);
                                    exercisesPanel.add(singleExercise, gridBagConstraints);

                                    // Increment to the next row to display the next exercise
                                    gridBagConstraints.gridy++;
                                }
                            }
                            retrieveViewModel.addPropertyChangeListener(
                                    new PropertyChangeListener() {
                                        @Override
                                        public void propertyChange(PropertyChangeEvent evt) {

                                            if ("saved exercises".equals(evt.getPropertyName())) {
                                                ArrayList<ArrayList<String>> exercises = retrieveViewModel.getSavedExercises();
                                                if (!exercises.isEmpty()) {
                                                    ArrayList<String> newExercise = exercises.get(exercises.size() - 1);

                                                    JTextArea newExerciseLabel = new JTextArea(1, 110);
                                                    newExerciseLabel.setEditable(false);
                                                    newExerciseLabel.setLineWrap(true);
                                                    newExerciseLabel.setWrapStyleWord(true);

                                                    newExerciseLabel.setName(newExercise.get(0));
                                                    newExerciseLabel.setText("Name: " + newExercise.get(0) + " " + newExercise.get(1) + " | " + newExercise.get(2) + " " + newExercise.get(3));
                                                    savedExercisesList.add(newExerciseLabel);

                                                    JPanel singleExercise = new JPanel();
                                                    singleExercise.add(newExerciseLabel);

                                                    exercisesPanel.add(singleExercise, gridBagConstraints);
                                                    gridBagConstraints.gridy++;

                                                }
                                                else {
                                                    
                                                }
                                            }

                                        }
                                    }
                            );
                        }
                    }
                }
            );

//        retrieveViewModel.addPropertyChangeListener(
//                new PropertyChangeListener() {
//                    @Override
//                    public void propertyChange(PropertyChangeEvent evt) {
//
//                        if ("saved exercises".equals(evt.getPropertyName())) {
//                            ArrayList<ArrayList<String>> exercises = retrieveViewModel.getSavedExercises();
//                            if (!exercises.isEmpty()) {
//                                ArrayList<String> newExercise = exercises.get(exercises.size() - 1);
//
//                                JTextArea newExerciseLabel = new JTextArea(1, 110);
//                                newExerciseLabel.setEditable(false);
//                                newExerciseLabel.setLineWrap(true);
//                                newExerciseLabel.setWrapStyleWord(true);
//
//                                newExerciseLabel.setName(newExercise.get(0));
//                                newExerciseLabel.setText("Name: " + newExercise.get(0) + " " + newExercise.get(1) + " | " + newExercise.get(2) + " " + newExercise.get(3));
//                                savedExercisesList.add(newExerciseLabel);
//
//                                JPanel singleExercise = new JPanel();
//                                singleExercise.add(newExerciseLabel);
//
//                                exercisesPanel.add(singleExercise, gridBagConstraints);
//                                gridBagConstraints.gridy++;
//
//                            }
//                        }
//
//                    }
//                }
//        );

        returnButton = new JButton(retrieveViewModel.RETURN_BUTTON_LABEL);

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

        // Add a scroll panel to the screen
        //JScrollPane scrollPane = new JScrollPane(exercisesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //this.add(scrollPane);

        panelGridBagConstraints.gridx = 0;
        panelGridBagConstraints.gridy = 0;
        this.add(title, panelGridBagConstraints);

        panelGridBagConstraints.gridx = 2;
        panelGridBagConstraints.gridy = 0;
        this.add(returnButton, panelGridBagConstraints);

        panelGridBagConstraints.gridx = 1;
        panelGridBagConstraints.gridy++;
        this.add(exercisesPanel, panelGridBagConstraints);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RetrieveState state = (RetrieveState) evt.getNewValue();
    }
}
