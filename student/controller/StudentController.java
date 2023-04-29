package student.controller;

import java.awt.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import eventmanager.studentmanager.listeners.AddStudentEventListener;
import eventmanager.studentmanager.listeners.RemoveStudentEventListener;
import eventmanager.studentmanager.listeners.UpdateStudentEventListener;
import eventmanager.studentsmanager.listeners.SaveStudentsEventListener;
import student.model.StudentModel;
import student.model.StudentsModel;
import student.view.StudentView;

public class StudentController {

    private StudentsModel model;
    private StudentView view;

    public StudentController(StudentsModel model, StudentView view) {
        this.model = model;
        this.view = view;

        this.subscribeToAllEvents();
    }

    public void addStudent(StudentModel student) {
        model.addStudent(student);
        this.updateView();
    }

    public StudentModel getStudent(String id) {
        return model.getStudent(id);
    }

    public void updateStudent(String id, String name, int age) {
        StudentModel student = this.getStudent(id);
        student.setName(name);
        student.setAge(age);
        this.updateView();
    }

    public void removeStudent(StudentModel student) {
        model.removeStudent(student);
        this.updateView();
    }

    public void saveStudents(StudentsModel students) {
        try (FileOutputStream fos = new FileOutputStream("students.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(model.getStudentList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateView() {
        view.updateView(model.getStudentList());
    }

    public void subscribeToAllEvents() {
        view.studentEvents.subscribe("addStudent", new AddStudentEventListener(this));
        view.studentEvents.subscribe("updateStudent", new UpdateStudentEventListener(this));
        view.studentEvents.subscribe("removeStudent", new RemoveStudentEventListener(this));
        view.studentsEvents.subscribe("saveStudents", new SaveStudentsEventListener(this));
    }
}