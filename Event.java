import java.util.ArrayList;
import java.util.List;

public class Event {
    private int eventId;
    private String eventName;
    private List<Attendee> attendeeList = new ArrayList<>();
  
    public Event(int eventId, String eventName, List<Attendee> attendeeList) {
      this.eventId = eventId;
      this.eventName = eventName;
      this.attendeeList = attendeeList;
    }
  
    public String toString() {
      return "Event ID: " + eventId + ", Event Name: " + eventName;
    }
  
    public String getName() {
      return this.eventName;
    }
  
    public int getId() {
      return this.eventId;
    }
  
    public void setId(int newId) {
      eventId = newId;
    }
  
    public void setName(String newName) {
      eventName = newName;
    }
  
    public void setAttendeeList(List<Attendee> attendeeList) {
      this.attendeeList = attendeeList;
    }
  
    public List<Attendee> getAttendeeList() {
      return this.attendeeList;
    }
  }
