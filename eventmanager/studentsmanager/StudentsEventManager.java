package eventmanager.studentsmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eventmanager.studentsmanager.interfaces.EventListener;
import student.model.StudentsModel;

public class StudentsEventManager {

    Map<String, List<EventListener>> listeners = new HashMap<>();

    public StudentsEventManager(String... operations) {
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

    public void notify(String eventType, StudentsModel students) {
        List<EventListener> event = listeners.get(eventType);
        for (EventListener listener : event) {
            listener.handleEvent(students);
        }
    }

}


