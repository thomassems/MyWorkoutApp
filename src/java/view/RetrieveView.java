package view;

import entity.Exercise;
import interface_adapter.ViewManagerModel;
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

    private final ResultsViewModel resultsViewModel;

    private final ArrayList<JLabel> savedExercisesList = new ArrayList<JLabel>();
    final ArrayList<JButton> deleteButtons = new ArrayList<JButton>();
    final JButton returnButton;
    private final RetrieveController retrieveController;
    public RetrieveView(RetrieveViewModel retrieveViewModel, RetrieveController controller, ViewManagerModel viewManagerModel,
                        LoggedInViewModel loggedInViewModel, ResultsViewModel resultsViewModel) {
        this.retrieveController = controller;
        this.retrieveViewModel = retrieveViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.resultsViewModel = resultsViewModel;
        this.retrieveViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("saved exercises");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        RetrieveState currentState = retrieveViewModel.getState();
        retrieveController.execute(loggedInViewModel.getState().getUsername());

        JPanel buttons = new JPanel();

        retrieveViewModel.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {

                    }
                }
        );

        if (retrieveViewModel.getSavedExercises()!=null) {
            for (ArrayList<String> exercise : retrieveViewModel.getSavedExercises()) {
                JLabel newExerciseLabel = new JLabel();
                newExerciseLabel.setName(exercise.get(0));
                newExerciseLabel.setText("Name: " + exercise.get(0) + " " + exercise.get(1) + " | " + exercise.get(2) + "\n" + exercise.get(3));
                savedExercisesList.add(newExerciseLabel);
                JButton newDeleteButton = new JButton(retrieveViewModel.DELETE_BUTTON_LABEL);
                newDeleteButton.setName(exercise.get(0));

                newDeleteButton.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (evt.getSource().equals(deleteButtons)) {
                                    RetrieveState currentState = retrieveViewModel.getState();

                                    retrieveController.execute(
                                            currentState.getUsername()
                                    );
                                }
                            }
                        }
                );
                deleteButtons.add(newDeleteButton);
                newExerciseLabel.add(newDeleteButton);
                buttons.add(newExerciseLabel);
            }
        }

        returnButton = new JButton(retrieveViewModel.RETURN_BUTTON_LABEL);

        returnButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }

        );

        buttons.add(returnButton);


        //this.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS))
        this.add(title);
        this.add(buttons);
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
