import java.util.Scanner;

public class Library {
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LoginScreen loginScreen = new LoginScreen(scanner);
            int role = loginScreen.start();

            if (role == 1) {
                MemberMenu memberMenu = new MemberMenu(scanner);
                memberMenu.login1();
            } else if (role == 2) {
                Admin admin = new Admin(scanner);
                admin.adminlogin();
            } else if (role == 3) {
                System.out.println("WIP");
            } else if (role == 4) {
                exitProgram();
            } else if (role == 0) {
                System.out.println("Please try again.");
            }
        }
    }

    static void exitProgram() {
        System.out.println("Exiting...");
        System.exit(0);;
    }
}

