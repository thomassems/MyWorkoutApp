package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsState;
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


public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "results";
    private final ResultsViewModel resultsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final SearchViewModel searchViewModel;

    final JButton home;
    final JButton search;
    final JButton workouts;
    private final ResultsController resultsController;
    private JPanel searchResultPanel;  // Create a panel to contain the search results

    public ResultsView(ResultsViewModel resultsViewModel,
                       ResultsController controller,
                       ViewManagerModel viewManagerModel,
                       LoggedInViewModel loggedInViewModel,
                       SearchViewModel searchViewModel) {
        this.resultsController = controller;
        this.resultsViewModel = resultsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchViewModel = searchViewModel;
        this.resultsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ResultsViewModel.TITLE_LABEL);
        Font f = title.getFont();
        Font font = new Font(f.getFontName(), Font.BOLD,16);
        title.setFont(font);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel headings = new JPanel();
        headings.add(new JLabel(ResultsViewModel.NAME_LABEL));
        headings.add(Box.createHorizontalStrut(20));
        headings.add(new JLabel(ResultsViewModel.MUSCLE_GROUP_LABEL));
        headings.add(Box.createHorizontalStrut(20));
        headings.add(new JLabel(ResultsViewModel.DIFFICULTY_LABEL));
        headings.add(Box.createHorizontalStrut(20));
        headings.add(new JLabel(ResultsViewModel.DESCRIPTION_LABEL));

        JPanel buttons = new JPanel();
        home = new JButton(ResultsViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);
        search = new JButton(ResultsViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        workouts = new JButton(ResultsViewModel.WORKOUTS_BUTTON_LABEL);
        buttons.add(workouts);

        searchResultPanel = new JPanel(); // Initialize the panel for search results
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS)); // Set layout for search results panel

        search.addActionListener(          // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            ResultsState currentState = resultsViewModel.getState();

                            resultsViewModel.setState(currentState);

                            // Switch back to the SearchView
                            viewManagerModel.setActiveView(searchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
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

//        private void displaySearchResults(ArrayList<String> query) {
//            for (String result : query) {
//                JButton resultButton = new JButton("Result " + result);
//                resultButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        // Handle the result button click here if needed ?? check
//                    }
//                });
//                searchResultPanel.add(resultButton); // Add result button to the search results panel
//            }
//            searchResultPanel.revalidate(); // Update the layout
//            searchResultPanel.repaint(); // Repaint the panel
//        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(headings);
        this.add(buttons);
        this.add(searchResultPanel); // Add the search results panel to the main view

        JLabel subTitle = new JLabel("Exercises");
        subTitle.setAlignmentX(CENTER_ALIGNMENT);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ResultsState state = (ResultsState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(ResultsState state) {
    }
}
