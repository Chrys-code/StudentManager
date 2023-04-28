package eventmanager.listeners;

import student.model.StudentModel;
import eventmanager.interfaces.EventListener;
import student.controller.StudentController;

public class UpdateStudentEventListener implements EventListener {

   private StudentController controller;

   public UpdateStudentEventListener(StudentController controller) {
      this.controller = controller;
   }

   @Override
   public void handleEvent(StudentModel student) {
      controller.updateStudent(student.getId(), student.getName(), student.getAge());
   }

}
