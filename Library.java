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
                
                String id = getNonEmptyInput(scanner, "Enter your ID: ");
                String name = getNonEmptyInput(scanner, "Enter your Name: ");
                String email = getNonEmptyInput(scanner, "Enter your Email: ");
                String password = getNonEmptyInput(scanner, "Enter your Password: ");
                
                globalDb.addUser(new Member(id, name, email, password, 0));
                globalDb.saveUsersToFile();
                globalDb.getLastUser();
                scanner.nextLine();

            } else if (role == 4) {
                LoginScreen.exitProgram();
            }
        }
    }

    private static String getNonEmptyInput(Scanner scanner, String prompt) {
        String input = "";
        while (input.isEmpty()) {
            ConsoleUtils.printCenteredInput(prompt);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                ConsoleUtils.printCentered("Error: Please write something!");
            }
        }
        return input;
    }

}