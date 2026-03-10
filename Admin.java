import java.util.Scanner;

public class Admin {

    Scanner input = new Scanner(System.in);

    public void adminlogin() {
        System.out.println("----Adimin Login----");
        System.out.println("Enter your Admin ID: ");

        String id = input.nextLine();

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
        
        choice = input.nextInt();



        
        }

        System.out.println("Returning...");
        return;
        
    }
}