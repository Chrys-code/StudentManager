package eventmanager.studentmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eventmanager.studentmanager.interfaces.EventListener;
import student.model.StudentModel;

public class StudentEventManager {
    
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public StudentEventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> event = listeners.get(eventType);
        event.add(listener);
    }

    
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> event = listeners.get(eventType);
        event.remove(listener);
    }

    public void notify(String eventType, StudentModel student) {
        List<EventListener> event = listeners.get(eventType);
        for (EventListener listener : event) {
            listener.handleEvent(student);
        }
    }
}
