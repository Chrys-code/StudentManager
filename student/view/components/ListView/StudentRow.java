package student.view.components.ListView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventmanager.studentmanager.StudentEventManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import student.model.StudentModel;

public class StudentRow extends JPanel {
    
    public int index;
    private StudentModel student;
    private StudentEventManager events;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JButton editButton;
    private JButton deleteButton;
    private GridBagConstraints gbc;
    private JTextField nameInput;
    private JTextField ageInput;

    public StudentRow(StudentModel student, StudentEventManager events, int index) {
        
        this.index = index;
        this.events = events;
        this.student = student;

        setPreferredSize(new Dimension(400, 30));
        setLayout(new GridBagLayout());

        nameLabel = new JLabel();
        nameLabel.setText(student.getName());
        ageLabel = new JLabel();
        ageLabel.setText(Integer.toString(student.getAge()));
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(ageLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(editButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        gbc.weighty = 0;
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(deleteButton, gbc);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                events.notify("removeStudent", student);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEditorMode();
            }
        });

        int gap = 3;
        setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
    }

    public void setEditorMode() {
        replaceEditButton();
        replaceLablesWithInput();
        revalidate();
    }

    public void replaceEditButton() {
        remove(editButton);

        JButton saveButton = new JButton("Save");

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                student.setName(nameInput.getText());
                student.setAge(Integer.parseInt(ageInput.getText()));
                events.notify("updateStudent", student);
            }
        });
    }

    public void replaceLablesWithInput() {
        remove(nameLabel);
        remove(ageLabel);

        nameInput = new JTextField(null, student.getName(), 5);
        ageInput = new JTextField(null, Integer.toString(student.getAge()), 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameInput, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(ageInput, gbc);
    }
}
