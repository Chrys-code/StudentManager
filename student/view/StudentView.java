package student.view;

import javax.swing.*;

import eventmanager.studentmanager.StudentEventManager;
import eventmanager.studentsmanager.StudentsEventManager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import student.model.StudentModel;
import student.view.components.ListView.StudentListView;

public class StudentView extends JFrame {

    public  StudentEventManager studentEvents = new StudentEventManager("addStudent", "updateStudent", "removeStudent");
    public  StudentsEventManager studentsEvents = new StudentsEventManager("saveStudents");
    private StudentListView listView;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel ageLabel;
    private JTextField ageTextField;
    private JTextArea nameOutput;
    private JTextArea ageOutput;
    private JButton submitButton;

    public StudentView(List<StudentModel> students) {

        super("Student Management");

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
        Insets insets = this.getInsets();
        setSize(new Dimension(insets.left + insets.right + 600, insets.top + insets.bottom + 450));
        setMinimumSize(new Dimension(600, 450));
        setLocationRelativeTo(null);
    
        // Create components
        nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(20);
        ageLabel = new JLabel("Age:");
        ageTextField = new JTextField(20);
        submitButton = new JButton("Submit");
        submitButton.setBounds(50,100,95,30);
        nameOutput = new JTextArea("Name:");
        nameOutput.setEditable(false);
        ageOutput = new JTextArea("Age:");
        ageOutput.setEditable(false);

        // Create component action listeners
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                StudentModel newStudentDetails = new StudentModel(name, age);
                // do smth with the data
                studentEvents.notify("addStudent", newStudentDetails);
            }
        });

        // Create panel
        JPanel panel = new JPanel();
        // Set panel properties
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        // Append components to panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameTextField, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(ageLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(ageTextField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // OUTPUT LIST
        listView = new StudentListView(students, studentEvents);
        listView.setPreferredSize(new Dimension(400, 300));
        panel.add(listView, gbc);

        // append panel to frame
        this.add(panel);
        setVisible(true);   
    }

    public String getStudentName() {
        return this.nameTextField.getText();
    }

    public int getStudentAge() {
        return Integer.parseInt(this.ageTextField.getText());
    }

    public void updateView(List<StudentModel> students) {
        nameTextField.setText(null);
        ageTextField.setText(null);
        this.listView.updateView(students);
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            int opt = JOptionPane.showConfirmDialog(null,
                    "Are you sure to close this window?", "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (opt == JOptionPane.NO_OPTION) {
                return;
            }
            studentsEvents.notify("saveStudents", null);
        }
        super.processWindowEvent(e);
    }
}
