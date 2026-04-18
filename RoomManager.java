import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    // Making the list static so it exists as long as the program is running
    public static final List<DiscussionRoom> rooms = new ArrayList<>();

    static {
        // Floor 1
        rooms.add(new DiscussionRoom("1-A", 1, 2, true,0));
        rooms.add(new DiscussionRoom("1-B", 1, 4, false,0));
        rooms.add(new DiscussionRoom("1-C", 1, 6, true,0));
        rooms.add(new DiscussionRoom("1-D", 1, 8, true,0));
        rooms.add(new DiscussionRoom("1-E", 1, 4, false,0));

        // Floor 2
        rooms.add(new DiscussionRoom("2-A", 2, 2, false,0));
        rooms.add(new DiscussionRoom("2-B", 2, 4, true,0));
        rooms.add(new DiscussionRoom("2-C", 2, 6, true,0));
        rooms.add(new DiscussionRoom("2-D", 2, 8, false,0));
        rooms.add(new DiscussionRoom("2-E", 2, 8, true,0));
    }

    public static void displayAllRooms() {
        System.out.println("--- Discussion Room Status ---");
        for (DiscussionRoom r : rooms) {
            // Using a ternary operator for a cleaner 'Available' vs 'Occupied' print
            String statusText = r.getStatus() ? "Available" : "Occupied";
            System.out.println("ID: " + r.getRoomID() + " | Floor: " + r.getFloor() + 
                               " | Cap: " + r.getCapacity() + " | Status: " + statusText);
        }
    }

    public static boolean reserveRoom(String id) {
        for (DiscussionRoom room : rooms) {
            // Find the room that matches the ID (ignoring case is usually safer)
            if (room.getRoomID().equalsIgnoreCase(id)) {
                if (room.getStatus()) { // If status is true (available)
                    room.setStatus(false); // Change to occupied
                    System.out.println("Success: " + id + " has been reserved.");
                    room.incrementBookingCount();
                    return true; 
                } else {
                    System.out.println("Error: " + id + " is already occupied.");
                    return false;
                }
            }
        }

        System.out.println("Error: Room ID " + id + " not found.");
        
        return false;
    }
}
