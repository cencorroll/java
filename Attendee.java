public class Attendee {
  private int attendeeId;
  private String attendeeName;

  public Attendee(int attendeeId, String attendeeName) {
    this.attendeeId = attendeeId;
    this.attendeeName = attendeeName;
  }

  public String toString() {
    return "\nAttendee ID: " + attendeeId + ", Name: " + attendeeName;
  }

  public String getName() {
    return this.attendeeName;
  }

  public int getId() {
    return this.attendeeId;
  }

}
