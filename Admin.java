import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    LoginScreen loginScreen;
    //Scanner scanner;
    //LibraryDatabase db; zheng delete 9/4/2026
    ReportGen rg = new ReportGen();

    private static int nextIdCounter = 1001;

    // A list to hold all valid Admin IDs in memory.
    // add "admin995" so the default admin can still log in.
    private List<String> validAdminIds = new ArrayList<>(List.of("admin995"));
    
    public Admin(Scanner scanner, LibraryDatabase db) {
        super("admin995", "Default Admin", "admin@gmail.com", "admin123");
        this.scanner = scanner;
        this.db = db;

        loadValidAdminIds();
    }

    public Admin(String id, String name, String email, String password) {
    super(id, name, email, password);
    loadValidAdminIds();
    } //zheng add 10/4/2026

    @Override
    public String toCSV() {
        return "Admin, " + getUserId() + ", " + getUserName() + ", " + getUserPassword();
    } //zheng add 10/4/2026

    public void mainMenu() {
        adminLogin();
    }

    public void adminLogin() {
        System.out.println("----Admin Login----");
        System.out.println("Enter your Admin ID: ");

        String id = scanner.nextLine().trim();

        if(validAdminIds.contains(id)){
            System.out.println("Login Success");
            adminMenu();
        }
        else{
            System.out.println("Invalid Login. Admin ID not recognized.");
        }
    }

    public void adminMenu() {
        String choice = "0";

        while(!choice.equals("8")){
        System.out.println("\n====================");
        System.out.println("     Admin Menu");
        System.out.println("====================");
        System.out.println("1. Add catalog entry");
        System.out.println("2. Remove catalog entry");
        System.out.println("3. Update book details");
        System.out.println("4. Register new admin");
        System.out.println("5. Pending fines");
        System.out.println("6. Lost books");
        System.out.println("7. Generate Report");
        System.out.println("8. Back to Main Menu");

        System.out.print("\nEnter choice: ");
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
                viewPendingFines();
                break;

            case "6":
                viewLostBooks();
                break;
            
            case "7":
                rg.reportGen();
                break;

            case "8":
                break;

            default:
                System.out.println("Invalid choice! Please select 1-7.");
                scanner.nextLine();
                break;
         }
        
        }

        System.out.println("Returning to main menu...");
        scanner.nextLine();
        return;
        
    }

    public void addCatalog() {
        System.out.println("--- Adding a New Book ---");
        String id = db.generateNextBookId();
        System.out.println("Generated Book ID: " + id);

        String name = getNonEmptyInput("Enter the Book Name: ");
        String genre = getNonEmptyInput("Enter the Genre: ");
        String isbn = getNonEmptyInput("Enter ISBN: ");
        String section = getNonEmptyInput("Enter the Section: ");
        String status = "Available";

        Book newBook = new Book(id, name, genre, isbn, section, status);
    
        db.addBook(newBook);
        db.saveBooksToFile();
    
        System.out.println("\nSuccess! [" + name + "] has been added to the catalog.");
        scanner.nextLine();

    }

    private String getNonEmptyInput(String prompt) {
    String input = "";
    while (input.isEmpty()) {
        System.out.print(prompt);
        input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            System.out.println("Error: Please write something!");
        }
    }
    return input;
}

    public void removeCatalog() {
        System.out.println("--- Removing a Book ---");
        System.out.println("Enter the Book ID to remove: ");
        String id = scanner.nextLine();
        Book bookToRemove = db.getBookById(id);

        if (bookToRemove != null) {
            System.out.println("Book found: " + bookToRemove.getBookName());
            System.out.println("\n---Current Details---");
            System.out.println("ID: " + bookToRemove.getBookId() + "\n");
            System.out.println("Name: " + bookToRemove.getBookName());
            System.out.println("Genre: " + bookToRemove.getBookGenre());
            System.out.println("ISBN: " + bookToRemove.getBookISBN());
            System.out.println("Section: " + bookToRemove.getBookSection());
            System.out.println("Status: " + bookToRemove.getBookStatus());
            System.out.println("Are you sure you want to remove this book? (Y/N)");
            String confirmation = scanner.nextLine();
            switch (confirmation.toUpperCase()) {
                case "Y":
                    db.removeBook(bookToRemove);
                    db.saveBooksToFile();
                    System.out.println("Book [" + bookToRemove.getBookName() + "] has been removed from the catalog.");
                    scanner.nextLine();
                    break;
                case "N":
                    System.out.println("Removal cancelled.");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid input. Removal cancelled.");
                    scanner.nextLine();
                    break;
            }
            }
            else {
                System.out.println("Book not found.");
                scanner.nextLine();
        }
    }

    public void updateCatalog() {
        System.out.println("--- Updating Book Details ---");
        System.out.println("Enter the Book ID to update: ");
        String id = scanner.nextLine();

        Book bookToUpdate = db.getBookById(id);
        if(bookToUpdate != null) {
            System.out.println("\n---Current Details---");
            System.out.println("ID: " + bookToUpdate.getBookId() + "\n");
            System.out.println("1. Name: " + bookToUpdate.getBookName());
            System.out.println("2. Genre: " + bookToUpdate.getBookGenre());
            System.out.println("3. ISBN: " + bookToUpdate.getBookISBN());
            System.out.println("4. Section: " + bookToUpdate.getBookSection());
            System.out.println("5. Status: " + bookToUpdate.getBookStatus());
            System.out.println("6. Cancel Update");
            System.out.print("\nSelect the detail to update (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" :
                    System.out.print("Enter new Name: ");
                    bookToUpdate.setBookName(scanner.nextLine());
                    break;

                case "2" :
                    System.out.print("Enter new Genre: ");
                    bookToUpdate.setBookGenre(scanner.nextLine());
                    break;

                case "3" :
                    System.out.print("Enter new ISBN: ");
                    bookToUpdate.setBookISBN(scanner.nextLine());
                    break;
                
                case "4" :
                    System.out.print("Enter new Section: ");
                    bookToUpdate.setBookSection(scanner.nextLine());
                    break;

                case "5" :
                    System.out.print("Enter new Status: ");
                    bookToUpdate.setBookStatus(scanner.nextLine());
                    break;

                case "6" :
                    System.out.println("Update cancelled");
                    scanner.nextLine();
                    return;
            
                default:
                    System.out.println("Invalid choice");
                    scanner.nextLine();
                    return;
            }

            db.saveBooksToFile();
            System.out.println("\nBook details updated successfully.");
            scanner.nextLine();
        
        }else{
            System.out.println("Book ID [" + id + "] not found.");
            scanner.nextLine();
        } 

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

        //try {
            // The 'true' means "append to the end of the file", don't overwrite it
        //    java.io.FileWriter writer = new java.io.FileWriter("admin_ids.txt", true);
        //    writer.write(newAdminId + "\n");
        //    writer.close();
        //} catch (java.io.IOException e) {
        //    System.out.println("Error saving ID to file.");
        //}   zheng delete 10/4/2026
        Admin newAdmin = new Admin(newAdminId, name, "test@gmail.com", "admin123");
        db.addUser(newAdmin);
        db.saveUsersToFile();
        //zheng add 10/4/2026

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

    // Replace the empty method in Admin.java
    public void viewPendingFines() {
        System.out.println("\n--- Viewing Pending Fines ---");

        // Get all users from the database
        List<User> allUsers = db.getAllUsers();
        boolean foundFines = false;

        System.out.printf("%-10s | %-20s | %-10s\n", "User ID", "Name", "Amount Due");
        System.out.println("--------------------------------------------------");

        for (User user : allUsers) {
            // Check if the user has a pending fine
            if (user.getFineAmount() > 0) {
                System.out.printf("%-10s | %-20s | $%-10.2f\n",
                        user.getUserId(),
                        user.getUserName(),
                        user.getFineAmount());
                foundFines = true;
            }
        }

        if (!foundFines) {
            System.out.println("No pending fines found in the system.");
        }

        System.out.println("\nPress Enter to return to menu...");
        // Use the existing scanner to pause
        scanner.nextLine();
    }

    public void viewLostBooks() {
        String choice = "0";
        while (!choice.equals("4")) {
            System.out.println("\n--- Lost Books Management ---");
            System.out.println("1. Mark Book as Lost");
            System.out.println("2. Display List of Lost Books");
            System.out.println("3. Mark Book as Restored");
            System.out.println("4. Back to Admin Menu");
            System.out.print("\nEnter choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    markBookAsLost();
                    break;
                case "2":
                    displayLostBooks();
                    break;
                case "3":
                    restoreLostBook();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private void markBookAsLost() {
        System.out.print("Enter Book ID to mark as LOST: ");
        String id = scanner.nextLine();
        Book book = db.getBookById(id);

        if (book != null) {
            // Auto-update status to Unavailable
            book.setBookStatus("Lost (Unavailable)");
            db.saveBooksToFile();
            System.out.println("Success: [" + book.getBookName() + "] is now marked as Lost.");
        } else {
            System.out.println("Error: Book ID not found.");
        }
        scanner.nextLine();
    }

    private void displayLostBooks() {
        System.out.println("\n--- List of Lost Books ---");
        List<Book> allBooks = db.getAllBooks(); // Assuming db has a method to get the list
        boolean found = false;

        for (Book book : allBooks) {
            if (book.getBookStatus().equalsIgnoreCase("Lost (Unavailable)")) {
                System.out.println("ID: " + book.getBookId() + " | Title: " + book.getBookName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently marked as lost.");
        }
        scanner.nextLine();
    }

    private void restoreLostBook() {
        System.out.print("Enter Book ID to RESTORE: ");
        String id = scanner.nextLine();
        Book book = db.getBookById(id);

        if (book != null && book.getBookStatus().equalsIgnoreCase("Lost (Unavailable)")) {
            book.setBookStatus("Available");
            db.saveBooksToFile();
            System.out.println("Success: [" + book.getBookName() + "] has been restored to 'Available'.");
        } else {
            System.out.println("Error: Book ID not found or book is not currently marked as lost.");
        }
        scanner.nextLine();
    }

}

