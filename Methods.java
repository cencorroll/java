import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class Methods {

  public static Scanner scannerObj = new Scanner(System.in);

  // Case 1
  public static void printEvents(List<Event> justEventsList) {
    System.out.println("All events ->\n");
    justEventsList.stream()
        .forEach(event -> System.out.println(event));
  }

  // Case 2
  public static void printOneEvent(List<Event> justEventsList) {
    System.out.println("Choose an event for more information ->\n");
    int eventChoice = scannerObj.nextInt();
    Predicate<Event> sameAsInput = event -> event.getId() == eventChoice;
    printEvents(justEventsList);
    try {
      justEventsList.stream()
          .filter(sameAsInput)
          .forEach(event -> System.out.println(event));
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // Case 3
  public static void editEvent(List<Event> justEventsList) {
    System.out.println("Edit an event ->\n");
    printEvents(justEventsList);

    try {
      int eventChoice = scannerObj.nextInt();
      Predicate<Event> sameAsInput = event -> event.getId() == eventChoice;
      scannerObj.nextLine();

      Optional<Event> optionalEvent = justEventsList.stream()
          .filter(sameAsInput)
          .findFirst();

      optionalEvent.ifPresent(event -> {
        System.out.println("Enter a new ID for this event: ");
        int newId = scannerObj.nextInt();
        scannerObj.nextLine();
        event.setId(newId);

        System.out.println("Enter a new name for this event: ");
        String newName = scannerObj.nextLine();
        event.setName(newName);

        System.out.println("Updated event -> " + event);
        System.out.println("Updated events list ->");
        justEventsList.stream().forEach(System.out::println);
      });

    } catch (InputMismatchException i) {
      System.out.println("Error. This input is invalid. Only integers can be accepted " + i);
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

  }

  // Case 4
  public static void deleteEvent(List<Event> justEventsList) {
    System.out.println("Delete an event ->\n");
    printEvents(justEventsList);

    try {
      int eventChoice = scannerObj.nextInt();
      Predicate<Event> sameAsInput = event -> event.getId() == eventChoice;

      int indexToRemove = eventChoice - 1;

      Optional<Event> optionalEvent = justEventsList.stream()// optional is a container object that may or may not contain a value - we can use this to store the first value that satisfies the filter. It is good practise to be able to avoid any exceptions just in case there is no values within justEventsList
          .filter(sameAsInput) // filters using the predicate sameAsInput which returns a boolean value
          .findFirst(); // Finds the first that satisfies the predicate sameAsInput

      optionalEvent.ifPresent(event -> {// .ifPresent() is used when the Optional Event exists and we can call on methods like .remove
        justEventsList.remove(indexToRemove);
        System.out.println("Updated Events List -> ");
        printEvents(justEventsList);
      });

    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  // Case 5
  public static void listEventAttendees(List<Event> justEventsList, Map<Object, List<Attendee>> EventMap) {

    System.out.println("List the attendees attending an event ->\n");
    printEvents(justEventsList);

    try {
      int eventChoice = scannerObj.nextInt();
      Predicate<Event> sameAsInput = event -> event.getId() == eventChoice;

      justEventsList.stream()
          .filter(sameAsInput)
          .forEach(event -> System.out.println(event.getAttendeeList()));

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // Case 6
  public static void addEventAttendee(List<Event> justEventsList, Map<Object, List<Attendee>> EventMap) {
    System.out.println("Add an Attendee to an event ->\n");
    printEvents(justEventsList);

    AtomicBoolean foundEvent = new AtomicBoolean(false); // If i just used a normal boolean variable I wouldn't be able to update it within lambda expressions because it would be final. AtomicBoolean allows for this

    try {
      int eventChoice = scannerObj.nextInt();
      // Predicate<Map.Entry<Object, List<Attendee>>> sameAsInput2 = entry -> ((Event) entry.getKey()).getId() == eventChoice; - Exception here because I am trying to cast an Integer to an event. Below is the solution
      Predicate<Map.Entry<Object, List<Attendee>>> sameAsInput2 = entry -> ((Integer) entry.getKey()) == eventChoice;

      Set<Entry<Object, List<Attendee>>> updatedEventMap = EventMap.entrySet();

      updatedEventMap.stream()
        .filter(sameAsInput2)
        .forEach(entry -> {
          List<Attendee> value = entry.getValue();
          System.out.println("Current attendee list ->" + value);
          System.out.println("Enter an ID for new Attendee: ");
          int attendeeId = scannerObj.nextInt();
          scannerObj.nextLine();
          System.out.println("Enter a Name for new Attendee: ");
          String attendeeName = scannerObj.nextLine();
          Attendee newAttendee = new Attendee(attendeeId, attendeeName);
          // Checks to see if an attendee with the id already exists
          boolean attendeeExists = false;
          for (Attendee attendee : value) {
            if (attendee.getId() == attendeeId) {
              System.out.println("Attendee with the ID " + attendeeId + " already exists");
              attendeeExists = true;
              break;
            }
          }
          if (!attendeeExists) {
            value.add(newAttendee);
            System.out.println("Updated attendee list ->" + value);
            foundEvent.set(true); // AtomicBoolean has a set method to update the boolean value
          }
        });

      if (!foundEvent.get()) {// AtomicBoolean has a get method to recieve the value of the boolean
        System.out.println("Invalid Choice. Please choose again from Main Menu");
      }
    } catch (InputMismatchException i) {
      System.out.println("Please input a valid Number for ID. Error: " + i);
    } catch (ConcurrentModificationException c) {
      System.out.println("Error caught -> " + c);
    } catch (ClassCastException cc) {
      System.out.println(cc);
    }
  }

  // Case 7
  public static void deleteEventAttendee(List<Event> justEventsList, Map<Object, List<Attendee>> EventMap) {
    System.out.println("Delete an attendee from an event ->\n");
    printEvents(justEventsList);

    int eventChoice7 = scannerObj.nextInt();
    try {

      boolean foundEvent = false;
      for (Entry<Object, List<Attendee>> entry : EventMap.entrySet()) {

        int id = Integer.parseInt(entry.getKey().toString());

        if (id == eventChoice7) {
          List<Attendee> value = entry.getValue();
          System.out.println(value);
          int eventChoice7cont = scannerObj.nextInt();
          Iterator<Attendee> iterator = value.iterator();
          while (iterator.hasNext()) {
            Attendee attendee = iterator.next();
            if (attendee.getId() == eventChoice7cont) {
              iterator.remove();
              System.out.println("Updated attendee list ->" + value);
              break;
            }
          }
          foundEvent = true;
        }
        if (!foundEvent) {
          System.out.println("Invalid Choice. Please choose again from Main Menu");
        }
      }
    } catch (InputMismatchException i) {
      System.out.println("Please input a valid Number for ID. Error: " + i);
    } catch (ConcurrentModificationException c) {
      System.out.println("Error caught -> " + c);
    }
  }

}
