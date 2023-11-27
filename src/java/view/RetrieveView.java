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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
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
    public JPanel exercisesPanel;  // Create a panel to contain the search results

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

        returnButton.addActionListener(
                new ActionListener() {
                    @Override
                    /** If the return button is clicked the user is taken back to the login view, and the exercises
                     * are removed from the panel */
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(returnButton)) {

                            // Switch to LoggedInView
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                            System.out.println("Active view set to: " + loggedInViewModel.getViewName());

                            // Reset the exercises panel every time the user goes back to the home screen
                            exercisesPanel.removeAll();
                            results = new ArrayList<ArrayList<String>>();
                        }

                    }
                }

        );

        // Set layout for JPanel
        GridBagLayout panelGridBagLayout = new GridBagLayout();
        GridBagConstraints panelGridBagConstraints = new GridBagConstraints();

        exercisesPanel = new JPanel();

        retrieveViewModel.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    /** Updates the retrieve view model if the saved exercises changes*/
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("saved exercises".equals(evt.getPropertyName())) {

                            LoggedInState currentState = loggedInViewModel.getState();
                            retrieveController.execute(currentState.getUsername());
                            ArrayList<ArrayList<String>> exercises = retrieveViewModel.getSavedExercises();
                            System.out.println(exercises);

                            if (exercises != null && !exercises.isEmpty()) {
                                displayNewExercise(exercisesPanel, exercises);
                            }
                        }
                    }
                }
        );
        // Add a scroll panel to the screen
        JScrollPane scrollPane = new JScrollPane(exercisesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(returnButton);
        this.add(scrollPane);
    }

    /**
     * React to a button click that results in evt.
     */

    /** Responds to an action being performed. i.e; button click*/
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    /** Updates the state and sets the fields in the retrieve view if a property change has occured*/
    public void propertyChange(PropertyChangeEvent evt) {
        if ("saved exercises".equals(evt.getPropertyName())) {
            System.out.println("Property changed: " + evt.getPropertyName());
            RetrieveState state = (RetrieveState) evt.getNewValue();
            setFields(state);
        }
    }

    /** Sets the saved exercises*/
    private void setFields(RetrieveState state) {
        results = state.getSavedExercises();
    }

    /** Displays the most recently added exercise in the jpanel*/
    private JPanel displayNewExercise(JPanel jPanel, ArrayList<ArrayList<String>> exercises) {
//        ArrayList<String> newExercise = exercises.get(exercises.size() - 1);
        jPanel.removeAll();

        System.out.println(exercises);

        String[] columnNames = {"Exercise Name", "Difficulty", "Muscle Group", "Description"};

        // Initializing the JTable using SwingUtilities.invokeLater()
        SwingUtilities.invokeLater(() -> {
            JTable j = new JTable(convertTo2DArray(exercises), columnNames);

            j.setDefaultEditor(Object.class, null); // Make all cells non-editable

            j.getColumnModel().getColumn(3).setCellRenderer(new MultilineTableCellRenderer()); // Set custom cell renderer for the last column

            JScrollPane scrollPane = new JScrollPane(j);
            j.setRowHeight(200);

            // Set selection mode and disable row selection
            j.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            j.setRowSelectionAllowed(false);

            // Adjust column widths (example weights, adjust as needed)
            j.getColumnModel().getColumn(0).setPreferredWidth(200);
            j.getColumnModel().getColumn(1).setPreferredWidth(80);
            j.getColumnModel().getColumn(2).setPreferredWidth(80);
            j.getColumnModel().getColumn(3).setPreferredWidth(300);

            j.setRowHeight(300); // Set the preferred row height for wrapped text

            jPanel.setLayout(new BorderLayout());
            jPanel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane directly to the panel

            // Repaint the panel after adding components
            jPanel.revalidate();
            jPanel.repaint();
        });

        return jPanel;
    }

    /** converts the exercises to a 2D array so that they can be properly aligned with the columns in the table*/
    private String[][] convertTo2DArray(ArrayList<ArrayList<String>> exercises) {
        String[][] results = new String[exercises.size()][];
        for (int i = 0; i < exercises.size(); i++) {
            ArrayList<String> row = exercises.get(i);
            results[i] = row.toArray(new String[row.size()]);
        }
        return results;
    }

    // TODO: Move this to a separate class
    public class MultilineTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        /** Sets the property's of the table */
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea textArea = new JTextArea(value.toString());
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setOpaque(true);

            if (isSelected) {
                textArea.setBackground(table.getSelectionBackground());
                textArea.setForeground(table.getSelectionForeground());
            } else {
                textArea.setBackground(table.getBackground());
                textArea.setForeground(table.getForeground());
            }

            return textArea;
        }
    }

}
