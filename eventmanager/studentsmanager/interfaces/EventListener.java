package eventmanager.studentsmanager.interfaces;

import student.model.StudentsModel;

public interface EventListener {
    public void handleEvent(StudentsModel students);
}