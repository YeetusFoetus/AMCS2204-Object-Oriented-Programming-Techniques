import java.util.Scanner;

public class MemberMenu {
    Scanner scanner;
    LibraryDatabase db = new LibraryDatabase();
    BookManager bm = new BookManager();
    LoanManager lm;

    public MemberMenu(Scanner scanner, LoanManager lm) {
        this.scanner = scanner;
        this.lm = lm;
    }

    public void login1() {
        db.loadUsersFromFile();

        System.out.print("Enter password(ID): ");
        String password = scanner.next();
        scanner.nextLine();

        User member = db.getUserById(password);

        if (member != null) {
            Catalog catalog = new Catalog(scanner);
            boolean loggedIn = true; // Loop control variable

            while (loggedIn) {
                ConsoleUtils.clearScreen();
                System.out.println("\n==================");
                System.out.println("Welcome " + member.getUserName());
                System.out.println("==================");
                System.out.println("1. Catalog");
                System.out.println("2. Search books");
                System.out.println("3. Your borrowed books");
                System.out.println("4. Discussion room booking");
                System.out.println("5. Pay fine");
                System.out.println("6. Logout"); 
                System.out.print("Choose an option: ");

                // Use a string or check hasNextInt to avoid crashes if user types a letter
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine(); // clear invalid input
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        catalog.catalogScreen(bm);
                        break;
                    case 2:
                        catalog.searchBook(bm);
                        break;
                    case 3:
                        System.out.println("Fetching your books...\n");
                        System.out.println(lm.printAllLoans());
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
                        System.out.println("Logging out...");
                        loggedIn = false; 
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid login credentials.");
        }
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