package student.controller;

import eventmanager.listeners.AddStudentEventListener;
import eventmanager.listeners.RemoveStudentEventListener;
import eventmanager.listeners.UpdateStudentEventListener;
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

    public void updateView() {
        view.updateView(model.getStudentList());
    }

    public void subscribeToAllEvents() {
        view.events.subscribe("addStudent", new AddStudentEventListener(this));
        view.events.subscribe("updateStudent", new UpdateStudentEventListener(this));
        view.events.subscribe("removeStudent", new RemoveStudentEventListener(this));
    }

    public void subscribeToAddStudentEvent() {
        view.events.subscribe("addStudent", new AddStudentEventListener(this));
    }

    public void subscribeToUpdateStudentEvent() {
        view.events.subscribe("updateStudent", new UpdateStudentEventListener(this));
    }

    public void subscribeToRemoveStudentEvent() {
        view.events.subscribe("removeStudent", new RemoveStudentEventListener(this));
    }

}