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
                    ConsoleUtils.printCentered("Error: Please choose a number between 1 and 4.");
                    ConsoleUtils.printCenteredInput("Press ENTER to try again...");
                    scanner.nextLine();
                }
            }else {
                ConsoleUtils.printCentered("Error: Invalid input. Please enter a NUMBER.");
                scanner.nextLine(); 
                ConsoleUtils.printCenteredInput("Press ENTER to try again...");
                scanner.nextLine();
            }
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
