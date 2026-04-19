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

        // Clear screen and center the login prompt
        ConsoleUtils.clearScreen();
        System.out.println();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║                MEMBER LOGIN              ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        ConsoleUtils.printCenteredInput("Enter password(ID): ");

        String password = scanner.next();
        scanner.nextLine();

        User member = db.getUserById(password);

        if (member != null) {
            Catalog catalog = new Catalog(scanner);
            boolean loggedIn = true;

            while (loggedIn) {
                ConsoleUtils.clearScreen();
                System.out.println();
                ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
                ConsoleUtils.printCentered("║                                          ║");

                // Dynamically pad the welcome message to maintain the 42-character inner width
                String welcomeText = "Welcome " + member.getUserName();
                int totalSpaces = 42 - welcomeText.length();
                int leftSpaces = totalSpaces / 2;
                int rightSpaces = totalSpaces - leftSpaces;
                ConsoleUtils.printCentered("║" + " ".repeat(Math.max(0, leftSpaces)) + welcomeText + " ".repeat(Math.max(0, rightSpaces)) + "║");

                ConsoleUtils.printCentered("║                                          ║");
                ConsoleUtils.printCentered("╠══════════════════════════════════════════╣");
                ConsoleUtils.printCentered("║                                          ║");
                ConsoleUtils.printCentered("║        1. Catalog                        ║");
                ConsoleUtils.printCentered("║        2. Search books                   ║");
                ConsoleUtils.printCentered("║        3. Your borrowed books            ║");
                ConsoleUtils.printCentered("║        4. Discussion room booking        ║");
                ConsoleUtils.printCentered("║        5. Pay fine                       ║");
                ConsoleUtils.printCentered("║        6. Logout                         ║");
                ConsoleUtils.printCentered("║                                          ║");
                ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
                ConsoleUtils.printCenteredInput("Choose an option: ");

                if (!scanner.hasNextInt()) {
                    ConsoleUtils.printCentered("\nPlease enter a valid number.\n");
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
                        ConsoleUtils.clearScreen();
                        System.out.println();
                        ConsoleUtils.printCentered("Fetching your books...");
                        scanner.nextLine();
                        System.out.println(lm.printAllLoans());
                        scanner.nextLine();
                        break;
                    case 4:
                        ConsoleUtils.clearScreen();
                        System.out.println();
                        RoomManager.displayAllRooms();
                        System.out.println();
                        ConsoleUtils.printCenteredInput("Enter Room ID to reserve (e.g., 1-A): ");
                        String userChoice = scanner.nextLine();
                        RoomManager.reserveRoom(userChoice);
                        scanner.nextLine();
                        break;
                    case 5:
                        payFine();
                        break;
                    case 6:
                        ConsoleUtils.clearScreen();
                        System.out.println();
                        ConsoleUtils.printCentered("Logging out...");
                        loggedIn = false;
                        break;
                    default:
                        ConsoleUtils.printCentered("Invalid choice! Press Enter to continue.");
                        scanner.nextLine(); // Pause to let them see the error
                }
            }
        } else {
            ConsoleUtils.clearScreen();
            System.out.println();
            ConsoleUtils.printCentered("Invalid login credentials.");
            System.out.println();
            ConsoleUtils.printCenteredInput("Press Enter to return...");
            scanner.nextLine();
        }
    }

    public void payFine() {
        ConsoleUtils.clearScreen();
        System.out.println();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("║      Current list of pending fines:      ║");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("╠══════════════════════════════════════════╣");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("║            (No fines pending)            ║");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        System.out.println();
        ConsoleUtils.printCenteredInput("Press x to return: ");

        char choice = scanner.next().charAt(0);

        if (choice == 'x' || choice == 'X') {
            return;
        }
    }
}