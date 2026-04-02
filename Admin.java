import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    LoginScreen loginScreen;
    Scanner scanner;
    LibraryDatabase db;

    private static int nextIdCounter = 1001;

    // A list to hold all valid Admin IDs in memory.
    // add "admin995" so the default admin can still log in.
    private List<String> validAdminIds = new ArrayList<>(List.of("admin995"));
    
    public Admin(Scanner scanner, LibraryDatabase db) {
        this.scanner = scanner;
        this.db = new LibraryDatabase();

        loadValidAdminIds();
    }

    public void adminLogin() {
        System.out.println("----Admin Login----");
        System.out.println("Enter your Admin ID: ");

        scanner.nextLine();
        String id = scanner.nextLine().trim();

        if(validAdminIds.contains(id)){
            System.out.println("Login Success");
            adminMenu();
        }
        else{
            System.out.println("Invalid Login. Admin ID not recognized.");
        }
    }

    public void adminMenu(){
        String choice = "0";

        while(!choice.equals("4")){
        System.out.println("\n1. Add catalog entry");
        System.out.println("2. Remove catalog entry");
        System.out.println("3. Update book details");
        System.out.println("4. Register new admin");
        System.out.println("5. Back to Main Menu");

        System.out.print("Enter choice: ");
        choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addCatalog();
                break;
            
            case "2":
                removeCatalog();
                break;
        
            case "3":
                updateCatalog();
                break;
            
            case "4":
                registerNewAdmin();
                break;

            case "5":
                
                break;

            default:
                System.out.println("Invalid choice! Please select 1-4.");
                break;
        }
        
        }

        System.out.println("Returning...");
        return;
        
    }

    public void addCatalog() {
        System.out.println("--- Adding a New Book ---");
        System.out.println("Enter the Book ID: ");
        String id = scanner.nextLine();

        System.out.println("Enter the Book Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Enter the Section: ");
        String section = scanner.nextLine();

        String status = "Available";

        Book newBook = new Book(id, name, genre, isbn, section, status);
    
        db.addBook(newBook);
    
        System.out.println("\nSuccess! [" + name + "] has been added to the catalog.");

    }

    public void removeCatalog() {
        System.out.println("--- Removing a Book ---");
    }
    
    public void updateCatalog() {
        System.out.println("--- Updating Book Details ---");
    }

    public void registerNewAdmin() {
        System.out.println("--- Register New Admin ---");

        // 1. Load the current number from the file
        int currentIdNumber = loadCounter();

        // 2. Generate the ID
        String newAdminId = "ADM-" + currentIdNumber;
        System.out.println("Auto-generated Admin ID : " + newAdminId);

        System.out.print("Enter Admin Name        : ");
        String name = scanner.nextLine();

        System.out.print("Enter Admin Email       : ");
        String email = scanner.nextLine();

        System.out.print("Enter Admin Phone No    : ");
        String phone = scanner.nextLine();

        // 3. Add +1 to the number and save it to the file for the NEXT admin
        saveCounter(currentIdNumber + 1);
        validAdminIds.add(newAdminId);

        try {
            // The 'true' means "append to the end of the file", don't overwrite it
            java.io.FileWriter writer = new java.io.FileWriter("admin_ids.txt", true);
            writer.write(newAdminId + "\n");
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("Error saving ID to file.");
        }

        System.out.println("\nSuccess! New admin [" + name + "] registered with ID [" + newAdminId + "].");
    }

    // Reads the last saved ID from a file. If the file doesn't exist, it starts at 1001.
    private int loadCounter() {
        try {
            File file = new File("admin_counter.txt");
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                int count = fileScanner.nextInt();
                fileScanner.close();
                return count;
            }
        } catch (Exception e) {
            System.out.println("Could not load counter, starting fresh.");
        }
        return 1001; // Default starting number
    }

    // Saves the new ID to the file so it remembers it for next time
    private void saveCounter(int currentCount) {
        try {
            FileWriter writer = new FileWriter("admin_counter.txt");
            writer.write(String.valueOf(currentCount));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving the counter!");
        }
    }

    // Add this method inside your Admin class
    private void loadValidAdminIds() {
        try {
            java.io.File file = new java.io.File("admin_ids.txt");
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String savedId = fileScanner.nextLine().trim();
                    if (!validAdminIds.contains(savedId)) {
                        validAdminIds.add(savedId);
                    }
                }
                fileScanner.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading admin IDs.");
        }
    }
}
