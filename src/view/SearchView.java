package view;

import interface_adapter.login.LoginState;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";
    private final SearchViewModel searchViewModel;

    final JComboBox<String> exerciseTypeInputField = new JComboBox<String>();
    private final JLabel exerciseTypeErrorField = new JLabel();

    final JComboBox<String> muscleGroupInputField = new JComboBox<String>();
    private final JLabel muscleGroupErrorField = new JLabel();

    final JComboBox<String> difficultyInputField = new JComboBox<String>();
    private final JLabel difficultyErrorField = new JLabel();

    final JButton search;
    final JButton cancel;
    private final SearchController searchController;

    public SearchView(SearchViewModel searchViewModel, SearchController controller) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel exerciseTypeInfo = new LabelTextPanel(
                new JLabel("Exercise Type"), exerciseTypeInputField);
        LabelTextPanel muscleGroupInfo = new LabelTextPanel(
                new JLabel("Muscle Group"), muscleGroupInputField);
        LabelTextPanel difficultyInfo = new LabelTextPanel(
                new JLabel("Difficulty"), difficultyInputField);
        JPanel buttons = new JPanel();
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        cancel = new JButton(searchViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        search.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();

                            ArrayList<String> searchQuery = searchController.execute(
                                    currentState.getExerciseType(),
                                    currentState.getMuscleGroup(),
                                    currentState.getDifficulty()
                            );
                            displaySearchResults(searchQuery);
                        }
                    }
                }
        );
        cancel.addActionListener(this);
        exerciseTypeInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchState currentState = searchViewModel.getState();
                JComboBox comboBox = (JComboBox) e.getSource();
                currentState.setExerciseType(exerciseTypeInputField.getText() + comboBox.getKeyChar());
                searchViewModel.setState(currentState);
            }
        });

        muscleGroupInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchState currentState = searchViewModel.getState();
                JComboBox comboBox = (JComboBox) e.getSource();
                currentState.setMuscleGroup(muscleGroupInputField.getText() + comboBox.getKeyChar());
                searchViewModel.setState(currentState);
            }
        });

        difficultyInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchState currentState = searchViewModel.getState();
                JComboBox comboBox = (JComboBox) e.getSource();
                currentState.setDifficulty(difficultyInputField.getText() + comboBox.getKeyChar());
                searchViewModel.setState(currentState);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(exerciseTypeInfo);
        this.add(exerciseTypeErrorField);
        this.add(muscleGroupInfo);
        this.add(muscleGroupErrorField);
        this.add(difficultyInfo);
        this.add(difficultyErrorField);
        this.add(buttons);

        JLabel subTitle = new JLabel("Search Result Screen");
        subTitle.setAlignmentX(CENTER_ALIGNMENT);
        JPanel buttonsResult = new JPanel();
    }

    private void displaySearchResults(ArrayList<String> query){
        buttonsResult.removeAll();
        for (String result : query) {
            JButton resultButton = new JButton("Result " + result);
            resultButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SearchState currentState = searchViewModel.getState();
                    currentState.setExercise(exerciseInputField.getText() + e.getKeyChar());
                    searchViewModel.setState(currentState);
                }
            });
            this.add(resultButton);
        }
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(SearchState state) {
        exerciseTypeInputField.setText(state.getExerciseType());
        muscleGroupInputField.setText(state.getMuscleGroup());
        difficultyInputField.setText(state.getDifficultyType());
    }
}