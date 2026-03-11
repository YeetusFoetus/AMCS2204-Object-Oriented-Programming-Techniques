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
        System.out.println("\n====================");
        System.out.println("   ABC Library");
        System.out.println("====================");
        System.out.println("1. Member Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Register Member");
        System.out.println("4. Exit");
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