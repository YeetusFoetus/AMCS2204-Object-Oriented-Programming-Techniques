import java.util.Scanner;

public class LoginScreen {
    Scanner scanner;
    //MemberMenu login = new MemberMenu();
    RegisterMember register = new RegisterMember();

    public LoginScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public int start() {
        int choice = -1;

        while (true) {
            showMenu();

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Error: Please choose a number between 1 and 4.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a NUMBER.");
                scanner.nextLine(); 
            }
        }
    }

    void showMenu() {
        System.out.println("\n====================");
        System.out.println("   ABC Library");
        System.out.println("====================");
        System.out.println("1. Member Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Register Member");
        System.out.println("4. Exit");

        System.out.print("Enter choice: ");
    }
}