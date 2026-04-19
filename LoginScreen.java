import java.util.Scanner;

public class LoginScreen {
    Scanner scanner;
    //MemberMenu login = new MemberMenu();
    RegisterMember register = new RegisterMember();

    public LoginScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public int start() { 
        showMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                return 1;
            case 2:
                return 2; 
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                System.out.println("Invalid choice!");
                return 1;
        }
    }

    
    private void printCentered(String text) {
        int width = 120;
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            System.out.println(" ".repeat(padding) + text);
        } else {
            System.out.println(text);
        }
    }

    private void printCenteredInput(String text) {
        int width = 120;
        int padding = (width - text.length()) / 2; 
        if (padding > 0) {
            System.out.print(" ".repeat(padding) + text);
        } else {
            System.out.print(text);
        }
    }

    void showMenu() {
        ConsoleUtils.clearScreen();
        System.out.println("\n\n\n\n");
        printCentered("╔══════════════════════════════════════════╗");
        printCentered("║                                          ║");
        printCentered("║               ABC LIBRARY                ║");
        printCentered("║                                          ║");
        printCentered("╠══════════════════════════════════════════╣");
        printCentered("║                                          ║");
        printCentered("║             1. Member Login              ║");
        printCentered("║             2. Admin Login               ║");
        printCentered("║             3. Register Member           ║");
        printCentered("║             4. Exit                      ║");
        printCentered("║                                          ║");
        printCentered("╚══════════════════════════════════════════╝");
        System.out.println();
        
        printCenteredInput("Enter choice: ");
    }

    /*void option1() {
        login.login1();
    }

    void option2() {
        return 2;
        //admin.adminlogin();
    }

    void option3() {
        register.register1();
    }

    void option4() {

    }*/
}