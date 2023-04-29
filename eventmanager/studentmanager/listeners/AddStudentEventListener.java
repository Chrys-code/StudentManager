package eventmanager.studentmanager.listeners;

import student.model.StudentModel;
import eventmanager.studentmanager.interfaces.EventListener;
import student.controller.StudentController;

public class AddStudentEventListener implements EventListener {
    
    private StudentController controller;

    public AddStudentEventListener(StudentController controller) {
        this.controller = controller;
    }

    @Override
    public void handleEvent(StudentModel student) {
        controller.addStudent(student);
     }
}