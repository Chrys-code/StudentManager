package eventmanager.studentmanager.listeners;

import student.controller.StudentController;
import eventmanager.studentmanager.interfaces.EventListener;
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
