package eventmanager.studentsmanager.listeners;

import eventmanager.studentsmanager.interfaces.EventListener;
import student.controller.StudentController;
import student.model.StudentsModel;

public class SaveStudentsEventListener implements EventListener {
    
    StudentController controller;

    public SaveStudentsEventListener(StudentController controller) {
        this.controller = controller;
    }

    @Override
    public void handleEvent(StudentsModel students) {
        controller.saveStudents(students);
    }
}
