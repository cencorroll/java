import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class JavaAssessment1 {
  public static void main(String[] args) {

    // File Handling for Menu
    try {
      FileWriter os = new FileWriter("JavaAssessmentMenu.txt");

      os.write("\n" +
          "*************************************** MENU ***********************************************\n" +
          "1. List All Events\n" +
          "2. List an individual event\n" +
          "3. Edit an event\n" +
          "4. Delete an event\n" +
          "5. List the attendees attending an event\n" +
          "6. Add an attendee to the event\n" +
          "7. Delete an attendee from an event\n" +
          "Enter your choice");

      os.close();
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e) {
      System.out.println(e);
    }

    // Creating attendee objects
    Attendee attendee1 = new Attendee(1, "Marcus Thuram");
    Attendee attendee2 = new Attendee(2, "Harry Hill");
    Attendee attendee3 = new Attendee(3, "Abdallah Harun");
    Attendee attendee4 = new Attendee(4, "Erykah Badu");
    Attendee attendee5 = new Attendee(5, "Maxwell Rivera");
    Attendee attendee6 = new Attendee(6, "Michael Eugene Archer");
    Attendee attendee7 = new Attendee(7, "Jamilah Barry");
    Attendee attendee8 = new Attendee(8, "Latanya Alberto");
    Attendee attendee9 = new Attendee(9, "Lauryn Hill");
    Attendee attendee10 = new Attendee(10, "Jazmine Sullivan");
    Attendee attendee11 = new Attendee(11, "Daniel Ceasar");
    Attendee attendee12 = new Attendee(12, "Kelela Mizanekristos");
    Attendee attendee13 = new Attendee(13, "Brent Faiyaz");
    Attendee attendee14 = new Attendee(14, "Alicia Keys");
    Attendee attendee15 = new Attendee(15, "Aubrey Graham");
    Attendee attendee16 = new Attendee(16, "Whitney Houston");
    Attendee attendee17 = new Attendee(17, "Aretha Franklin");
    Attendee attendee18 = new Attendee(18, "Leon Ware");
    Attendee attendee19 = new Attendee(19, "Donell Jones");
    Attendee attendee20 = new Attendee(20, "Luther Vandross");

    Event carpentryEvent = new Event(1, "Carpentry", new ArrayList<>());
    carpentryEvent.getAttendeeList().add(attendee1);
    carpentryEvent.getAttendeeList().add(attendee3);
    carpentryEvent.getAttendeeList().add(attendee6);
    carpentryEvent.getAttendeeList().add(attendee5);
    carpentryEvent.getAttendeeList().add(attendee13);
    carpentryEvent.getAttendeeList().add(attendee14);

    Event cookingEvent = new Event(2, "Cooking Class", new ArrayList<>());
    cookingEvent.getAttendeeList().add(attendee4);
    cookingEvent.getAttendeeList().add(attendee5);
    cookingEvent.getAttendeeList().add(attendee2);
    cookingEvent.getAttendeeList().add(attendee11);
    cookingEvent.getAttendeeList().add(attendee16);
    cookingEvent.getAttendeeList().add(attendee12);
    cookingEvent.getAttendeeList().add(attendee18);
    cookingEvent.getAttendeeList().add(attendee19);

    Event birdEvent = new Event(3, "Bird Watching", new ArrayList<>());
    birdEvent.getAttendeeList().add(attendee7);
    birdEvent.getAttendeeList().add(attendee8);
    birdEvent.getAttendeeList().add(attendee9);
    birdEvent.getAttendeeList().add(attendee2);
    birdEvent.getAttendeeList().add(attendee1);
    birdEvent.getAttendeeList().add(attendee17);
    birdEvent.getAttendeeList().add(attendee20);

    Event potteryEvent = new Event(4, "Pottery Painting", new ArrayList<>());
    potteryEvent.getAttendeeList().add(attendee10);
    potteryEvent.getAttendeeList().add(attendee11);
    potteryEvent.getAttendeeList().add(attendee9);
    potteryEvent.getAttendeeList().add(attendee2);
    potteryEvent.getAttendeeList().add(attendee5);
    potteryEvent.getAttendeeList().add(attendee20);
    potteryEvent.getAttendeeList().add(attendee15);

    // Creating an ArrayList of just the events
    List<Event> justEventsList = new ArrayList<>();
    justEventsList.add(carpentryEvent);
    justEventsList.add(cookingEvent);
    justEventsList.add(birdEvent);
    justEventsList.add(potteryEvent);

    // Creating a hashmap of the events(key) and their attendees(value)
    Map<Object, List<Attendee>> EventMap = new HashMap<>();
    EventMap.put(carpentryEvent.getId(), carpentryEvent.getAttendeeList());
    EventMap.put(cookingEvent.getId(), cookingEvent.getAttendeeList());
    EventMap.put(birdEvent.getId(), birdEvent.getAttendeeList());
    EventMap.put(potteryEvent.getId(), potteryEvent.getAttendeeList());

    // Creating user input for program
    try (Scanner scannerObj = new Scanner(System.in)){
      while (true) {

        System.out.println("\n" +
            "*************************************** MENU ***********************************************\n" +
            "1. List All Events\n" +
            "2. List an individual event\n" +
            "3. Edit an event\n" +
            "4. Delete an event\n" +
            "5. List the attendees attending an event\n" +
            "6. Add an attendee to the event\n" +
            "7. Delete an attendee from an event\n" +
            "Enter your choice or type 'e' to exit the application");
        String userInput = scannerObj.nextLine();
        if (userInput.equals("e")) {
          System.exit(0);
        }

          // Switch Case Statement for each Menu item
          switch (userInput) {

            case "1":
              Methods.printEvents(justEventsList);
              break;
              
            case "2":
              Methods.printOneEvent(justEventsList);
              break;

            case "3":
              Methods.editEvent(justEventsList);
              break;

            case "4":
              Methods.deleteEvent(justEventsList);
              break;

            case "5":
              Methods.listEventAttendees(justEventsList, EventMap);
              break;

            case "6":
              Methods.addEventAttendee(justEventsList, EventMap);
              break;

            case "7":
              Methods.deleteEventAttendee(justEventsList, EventMap);
              break;
          }
        }
      }
    }
}
