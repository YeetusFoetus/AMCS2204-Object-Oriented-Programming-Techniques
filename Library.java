import java.util.Scanner;

public class Library {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LibraryDatabase globalDb = new LibraryDatabase(); //Zheng add 27/3/2026
        BookManager globalBm = new BookManager();
        LoanManager globalLm = new LoanManager();
        globalBm.loadBooksFromFile(); //Zheng add 2/4/2026
        globalDb.loadUsersFromFile(); //Zheng add 10/4/2026

        while (true) {
            LoginScreen loginScreen = new LoginScreen(scanner);
            int role = loginScreen.start();

            if (role == 1) {
                MemberMenu memberMenu = new MemberMenu(scanner, globalLm);

                memberMenu.db = globalDb; //Zheng add 27/3/2026


                memberMenu.login1();
            } else if (role == 2) {
                Admin admin = new Admin(scanner, globalDb, globalBm, globalLm);
                admin.adminLogin();
            } else if (role == 3) {
                ConsoleUtils.clearScreen();
                System.out.println();
                ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
                ConsoleUtils.printCentered("║              REGISTER MEMBER             ║");
                ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
                System.out.println();

                ConsoleUtils.printCenteredInput("Enter your ID: ");
                String id = scanner.nextLine().trim();

                System.out.println();
                ConsoleUtils.printCenteredInput("Enter your name: ");
                String name = scanner.nextLine().trim();

                System.out.println();
                ConsoleUtils.printCenteredInput("Enter your email: ");
                String email = scanner.nextLine().trim();

                System.out.println();
                ConsoleUtils.printCenteredInput("Enter your password: ");
                String password = scanner.nextLine().trim();

                globalDb.loadUsersFromFile();
                globalDb.addUser(new Member(id, name, email, password, 0));
                globalDb.saveUsersToFile();

                System.out.println();
                ConsoleUtils.printCentered("Registration successful!");
                System.out.println();
                scanner.nextLine();

                // Testing
                globalDb.getLastUser();

            } else if (role == 4) {
                exitProgram();
            } else if (role == 0) {
                // The LoginScreen handles invalid inputs, but just in case it slips through:
                System.out.println();
                ConsoleUtils.printCentered("Please try again.");

            }
        }
    }


    static void exitProgram() {
        ConsoleUtils.clearScreen();
        System.out.println();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║            EXITING SYSTEM...             ║");
        ConsoleUtils.printCentered("║                 GOODBYE!                 ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        System.out.println();
        System.exit(0);
    }
}

