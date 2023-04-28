package eventmanager.listeners;

import eventmanager.interfaces.EventListener;
import student.controller.StudentController;
import student.model.StudentModel;

public class RemoveStudentEventListener  implements EventListener {

    private StudentController controller;

    public RemoveStudentEventListener(StudentController controller) {
        this.controller = controller;
    }

    @Override
    public void handleEvent(StudentModel student) {
        controller.removeStudent(student);
     }

}
