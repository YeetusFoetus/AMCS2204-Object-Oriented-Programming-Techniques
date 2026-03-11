import java.util.Scanner;

public class Admin {

    LoginScreen loginScreen;
    Scanner scanner;

    public Admin(Scanner scanner) {
        this.scanner = scanner;
    }

    public void adminlogin() {
        System.out.println("----Admin Login----");
        System.out.println("Enter your Admin ID: ");

        scanner.nextLine();
        String id = scanner.nextLine().trim();

        if(id.equals("admin995")){
            System.out.println("Login Success");
            adminmenu();
        }
        else{
            System.out.println("Invalid Login");
        }
    }

    public void adminmenu(){
        int choice = 0;

        if(choice != 4){
        System.out.println("\n1. Add catalog entry");
        System.out.println("2. Remove catalog entry");
        System.out.println("3. Update book details");
        System.out.println("4. Back to Main Menu");
        
        choice = scanner.nextInt();



        
        }

        System.out.println("Returning...");
        return;
        
    }
}