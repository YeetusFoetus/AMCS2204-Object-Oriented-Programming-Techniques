import java.util.Scanner;

public class MemberMenu {
    Scanner scanner;
    LibraryDatabase db = new LibraryDatabase();

    public MemberMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void login1() {
        db.loadUsersFromFile();

        System.out.print("Enter password(ID): ");
        Catalog catalog = new Catalog(scanner);
        
        String password = scanner.next();

        User member = db.getUserById(password);

        if (member != null) {
            System.out.println("\n==================");
            System.out.println("Welcome " + member.getUserName());
            System.out.println("==================");
            System.out.println("1. Catalog");
            System.out.println("2. Search books");
            System.out.println("3. Your borrowed books");
            System.out.println("4. Discussion room booking");
            System.out.println("5. Pay fine");
            System.out.println("6. Return");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) { 
                case 1:
                    catalog.catalogScreen(db);
                    break;
                case 2:
                    catalog.searchBook(db);
                    break;
                case 3:
                    System.out.println("Sup");
                    break;
                case 4:
                    RoomManager.displayAllRooms();
                    System.out.print("Enter Room ID to reserve (e.g., 1-A): ");
                    String userChoice = scanner.nextLine();
                    RoomManager.reserveRoom(userChoice);
                    break;
                case 5:
                    payFine();
                    break;
                case 6:
                    System.out.println("Sup");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
        } 
        
        else if (member == null) {
            System.out.println("Invalid login");
        }
    }

    //iteration 1: display room status after entering number
    //iteration 2: display room information as a table 
    public void discussionRoomBooking() {
        //db.printAllRooms();
        /*System.out.println("Choose a room to book: \n" +
            "1.   Room A (Floor 1, 4 people)\n" +
            "2.   Room B (Floor 1, 4 people)\n" +
            "3.   Room C (Floor 1, 4 people)\n" +
            "4.   Room D (Floor 1, 4 people)\n" +
            "5.   Room E (Floor 1, 6 people)\n" +
            "6.   Room F (Floor 1, 6 people)\n" +
            "7.   Room G (Floor 2, 6 people)\n" +
            "8.   Room H (Floor 2, 6 people)\n" +
            "9.   Room I (Floor 2, 8 people)\n" +
            "10.  Room J (Floor 2, 8 people)\n" +
            "Enter number: "
        );

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Discussion room " + choice + " booked successfully. (Press ENTER to return)");

        } else {

            System.out.println("Failed to book");

        }

        scanner.nextLine();*/
    }

    public void payFine() {
        System.out.println("Current list of pending fines: \n\n" +
            "Press x to return"
        );

        char choice = scanner.next().charAt(0);

        if (choice == 'x') {
            return;
        }
    }
}