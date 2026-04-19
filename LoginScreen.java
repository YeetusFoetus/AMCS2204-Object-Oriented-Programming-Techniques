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


    void showMenu() {
        ConsoleUtils.clearScreen();
        System.out.println("\n\n\n\n");
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("║               ABC LIBRARY                ║");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("╠══════════════════════════════════════════╣");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("║             1. Member Login              ║");
        ConsoleUtils.printCentered("║             2. Admin Login               ║");
        ConsoleUtils.printCentered("║             3. Register Member           ║");
        ConsoleUtils.printCentered("║             4. Exit                      ║");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        System.out.println();
        
        ConsoleUtils.printCenteredInput("Enter choice: ");
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