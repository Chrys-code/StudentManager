package eventmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eventmanager.interfaces.EventListener;
import student.model.StudentModel;

public class EventManager {
    
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> students = listeners.get(eventType);
        students.add(listener);
    }

    
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> students = listeners.get(eventType);
        students.remove(listener);
    }

    public void notify(String eventType, StudentModel student) {
        List<EventListener> students = listeners.get(eventType);
        for (EventListener listener : students) {
            listener.handleEvent(student);
        }
    }

}
