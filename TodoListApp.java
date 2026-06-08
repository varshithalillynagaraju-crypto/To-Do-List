import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoListApp extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, removeButton;

    public TodoListApp() {
        setTitle("To-Do List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        taskField = new JTextField();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Selected");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(removeButton, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText().trim();

                if (!task.isEmpty()) {
                    listModel.addElement(task);
                    taskField.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                            TodoListApp.this,
                            "Please enter a task!"
                    );
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();

                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(
                            TodoListApp.this,
                            "Select a task to remove!"
                    );
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TodoListApp().setVisible(true);
        });
    }
}