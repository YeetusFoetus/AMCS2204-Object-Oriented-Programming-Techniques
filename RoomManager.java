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
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║             DISCUSSION ROOMS             ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        System.out.println();

        // Setup column headers
        String header = String.format("%-8s %-8s %-10s %-10s", "Room ID", "Floor", "Capacity", "Status");
        ConsoleUtils.printCentered(header);
        ConsoleUtils.printCentered("-----------------------------------------");

        for (DiscussionRoom r : rooms) {
            String statusText = r.getStatus() ? "Available" : "Occupied";
            String roomData = String.format("%-8s %-8d %-10d %-10s",
                    r.getRoomID(), r.getFloor(), r.getCapacity(), statusText);
            ConsoleUtils.printCentered(roomData);
        }
    }

    public static boolean reserveRoom(String id) {
        System.out.println();

        for (DiscussionRoom room : rooms) {
            if (room.getRoomID().equalsIgnoreCase(id)) {
                if (room.getStatus()) { // If status is true (available)
                    room.setStatus(false); // Change to occupied
                    ConsoleUtils.printCentered("Success: Room " + id + " has been reserved.");
                    room.incrementBookingCount();
                    return true;
                } else {
                    ConsoleUtils.printCentered("Error: Room " + id + " is already occupied.");
                    return false;
                }
            }
        }

        ConsoleUtils.printCentered("Error: Room ID " + id + " not found.");
        return false;
    }
}
