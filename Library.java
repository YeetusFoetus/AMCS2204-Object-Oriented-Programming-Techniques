import java.util.Scanner;

public class Library {
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);

        LibraryDatabase globalDb = new LibraryDatabase(); //Zheng add 27/3/2026
        BookManager globalBm = new BookManager();
        globalBm.loadBooksFromFile(); //Zheng add 2/4/2026
        globalDb.loadUsersFromFile(); //Zheng add 10/4/2026

        while (true) {
            LoginScreen loginScreen = new LoginScreen(scanner);
            int role = loginScreen.start();

            if (role == 1) {
                MemberMenu memberMenu = new MemberMenu(scanner);
                
                memberMenu.db = globalDb; //Zheng add 27/3/2026

                memberMenu.login1();
            } else if (role == 2) {
                Admin admin = new Admin(scanner, globalDb, globalBm);
                admin.adminLogin();
            } else if (role == 3) {
                System.out.println("Enter your  id: ");

                String id = scanner.nextLine();

                System.out.println("Enter your name: ");

                String name = scanner.nextLine();

                System.out.println("Enter your email: ");

                String email = scanner.nextLine();

                System.out.println("Enter your password: ");

                String password = scanner.nextLine();
                
                globalDb.loadUsersFromFile();
                globalDb.addUser(new Member(id, name, email, password, 0));
                globalDb.saveUsersToFile();

                // Testing
                globalDb.getLastUser();

            } else if (role == 4) {
                exitProgram();
            } else if (role == 0) {
                System.out.println("Please try again.");
            }
        }
    }

    static void exitProgram() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}

