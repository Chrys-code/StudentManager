package student.view.components.ListView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eventmanager.EventManager;
import student.model.StudentModel;

public class StudentListView extends JScrollPane {

    private  JPanel myList;
    private  EventManager events;
    private  List<StudentModel> students;
    private  StudentRow listRow;
    private  JPanel BorderLayoutPanel;

    public StudentListView(List<StudentModel> students, EventManager events) {
        this.students = students;
        this.events = events;

        myList = new JPanel();
        myList.setLayout(new GridLayout(0,1));

        BorderLayoutPanel = new JPanel(new BorderLayout());
        BorderLayoutPanel.add(myList, BorderLayout.PAGE_START);

        for (int i = 0; i < this.students.size(); i++) {  
            listRow = new StudentRow(students.get(i), events, i);
            myList.add(listRow);
        }

        setViewportView(BorderLayoutPanel);
    }

    public void updateView(List<StudentModel> students) {
        myList.removeAll();

        this.students = students;
        for (int i = 0; i < this.students.size(); i++) { 
            listRow = new StudentRow(students.get(i), events, i);
            myList.add(listRow);
        }

        setViewportView(BorderLayoutPanel);
        myList.revalidate();
    }
}
