import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    MemberLogin login = new MemberLogin();
    RegisterMember register = new RegisterMember();

    void start() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    option1();;
                    return;
                case 2:
                    return;
                case 3:
                    option3();
                    return;
                case 4:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice!");
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
    }

    void option1() {
        login.login1();
    }

    void option3() {
        register.register1();
    }

    void exitProgram() {
        System.out.println("Exiting...");
        scanner.close();
    }
}