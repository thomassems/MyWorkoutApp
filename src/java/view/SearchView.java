package view;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
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

        JLabel exerciseTypeLabel = new JLabel("Exercise Type");
        JLabel muscleGroupLabel = new JLabel("Muscle Group");
        JLabel difficultyLabel = new JLabel("Difficulty");

        JPanel buttons = new JPanel();
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        cancel = new JButton(searchViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);


        search.addActionListener(          // This creates an anonymous subclass of ActionListener and instantiates it.
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
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                currentState.setExerciseType((String) comboBox.getSelectedItem());
                searchViewModel.setState(currentState);
            }
        });

        muscleGroupInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchState currentState = searchViewModel.getState();
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                currentState.setMuscleGroup((String) comboBox.getSelectedItem());
                searchViewModel.setState(currentState);
            }
        });

        difficultyInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchState currentState = searchViewModel.getState();
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                currentState.setDifficulty((String) comboBox.getSelectedItem());
                searchViewModel.setState(currentState);
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

        JLabel subTitle = new JLabel("Search Result Screen");
        subTitle.setAlignmentX(CENTER_ALIGNMENT);
        JPanel buttonsResult = new JPanel();
    }

//    private void displaySearchResults(ArrayList<String> query){
//        buttonsResult.removeAll();
//        for (String result : query) {
//            JButton resultButton = new JButton("Result " + result);
//            resultButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    SearchState currentState = searchViewModel.getState();
//                    currentState.setExercise(exerciseInputField.getText() + e.getKeyChar());
//                    searchViewModel.setState(currentState);
//                }
//            });
//            this.add(resultButton);
//        }
//    }

    private void displaySearchResults(ArrayList<String> query) {
        for (String result : query) {
            JButton resultButton = new JButton("Result " + result);
            resultButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
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
        exerciseTypeInputField.setSelectedItem(state.getExerciseType());
        muscleGroupInputField.setSelectedItem(state.getMuscleGroup());
        difficultyInputField.setSelectedItem(state.getDifficulty());
    }
}