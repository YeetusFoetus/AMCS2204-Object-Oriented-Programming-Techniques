import java.util.Scanner;

public class LoginScreen {
    Scanner scanner = new Scanner(System.in);
    MemberMenu login = new MemberMenu();
    RegisterMember register = new RegisterMember();
    Admin admin = new Admin();

    void start() 
    { int n = 0;
        while (n != 4) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    option1();
                    return;
                case 2:
                    option2();
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

    void option2() {
        admin.adminlogin();
    }

    void option3() {
        register.register1();
    }

    void option4() {

    }

    void exitProgram() {
        System.out.println("Exiting...");
        scanner.close();
    }
}