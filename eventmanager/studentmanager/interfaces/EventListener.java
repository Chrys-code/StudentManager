package eventmanager.studentmanager.interfaces;

import student.model.StudentModel;

public interface EventListener {
    public void handleEvent(StudentModel student);
}