import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Admin extends User {

    LoginScreen loginScreen;
    
    //Scanner scanner;
    //LibraryDatabase db; zheng delete 9/4/2026
    ReportGen rg = new ReportGen();


    private static int nextIdCounter = 1001;


    // A list to hold all valid Admin IDs in memory.
    // add "admin995" so the default admin can still log in.
    private List<String> validAdminIds = new ArrayList<>(List.of("admin995"));
    
    public Admin(Scanner scanner, LibraryDatabase db, BookManager bm, LoanManager lm) {
        super("admin995", "Default Admin", "admin@gmail.com", "admin123", 0);
        this.scanner = scanner;
        this.db = db;
        this.bm = bm;
        this.lm = lm;

        loadValidAdminIds();
    }

    public Admin(String id, String name, String email, String password, int borrowCounter) {
        super(id, name, email, password, borrowCounter);
        loadValidAdminIds();
    } //zheng add 10/4/2026

    @Override
    public String toCSV() {
        return "Admin, " + getUserId() + ", " + getUserName() + ", " + getUserEmail() + ", " + getUserPassword() + ", " + getBorrowCounter();
    }

    public void mainMenu() {
        adminLogin();
    }

    public void adminLogin() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║              Admin Login                 ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        ConsoleUtils.printCenteredInput("Enter your Admin ID: ");

        String id = scanner.nextLine().trim();

        if(validAdminIds.contains(id)){
            ConsoleUtils.printCentered("Login Success");
            adminMenu();
        }
        else{
            ConsoleUtils.printCentered("Invalid Login. Admin ID not recognized.");
        }
    }

    public void adminMenu() {
        String choice = "0";

        while(!choice.equals("10")){
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║                Admin Menu                ║");
        ConsoleUtils.printCentered("╠══════════════════════════════════════════╣");
        ConsoleUtils.printCentered("║             1. Lend a book               ║");
        ConsoleUtils.printCentered("║             2. Register New Admin        ║");
        ConsoleUtils.printCentered("║             3. Remove catalog entry      ║");
        ConsoleUtils.printCentered("║             4. Update book details       ║");
        ConsoleUtils.printCentered("║             5. Register new admin        ║");
        ConsoleUtils.printCentered("║             6. Pending fines             ║");
        ConsoleUtils.printCentered("║             7. Remove Book Reservation   ║");
        ConsoleUtils.printCentered("║             8. Lost books                ║");
        ConsoleUtils.printCentered("║             9. Generate Report           ║");
        ConsoleUtils.printCentered("║             10. Back to Main Menu        ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");

        ConsoleUtils.printCenteredInput("Enter choice: ");
        choice = scanner.nextLine();

        switch (choice) {
            case "1":
                ConsoleUtils.clearScreen();
                ConsoleUtils.printCenteredInput("Enter user ID:");
                String UID = scanner.nextLine();
                ConsoleUtils.printCenteredInput("Enter book ID: ");
                String BID = scanner.nextLine();
                lendBook(UID, BID);
                break;

            case "2":
                addCatalog();
                break;
            
            case "3":
                removeCatalog();
                break;
        
            case "4":
                updateCatalog();
                break;
            
            case "5":
                registerNewAdmin();
                break;

            case "6":
                viewPendingFines();
                break;

            case "7":
                removeBookReservation();
                break; 

            case "8":
                viewLostBooks();
                break;
            
            case "9":
                rg.reportGen();
                break;

            case "10":
                break;

            default:
                ConsoleUtils.printCentered("Invalid choice! Please select 1-10.");
                scanner.nextLine();
                break;
         }
        
        }

        ConsoleUtils.printCentered("Returning to main menu...");
        scanner.nextLine();
        return;
        
    }

    public void lendBook(String userId, String bookId) {
        // 1. Fetch the User and Book from your data source (e.g., a List or DB)
        User user = db.getUserById(userId);
        Book book = bm.getBookById(bookId);

        // 2. Simple Validation
        if (user == null || book == null) {
            ConsoleUtils.printCentered("Error: User or Book does not exist.");
            return;
        }

        if (book.getBookStatus().equalsIgnoreCase("On Loan")) {
            ConsoleUtils.printCentered("Error: Book is already borrowed.");
            return;
        }

        // 3. Generate Dates using Java's modern Time API
//      LocalDate borrowedDate = LocalDate.now();
//      LocalDate dueDate = borrowedDate.plusDays(14); // Standard 14-day loan

        //to test overdue
       LocalDate borrowedDate = LocalDate.now().minusDays(30);
       LocalDate dueDate = borrowedDate.plusDays(1);

        // 4. Update User info
        // Increment the counter (current count + 1)
        user.incrementBorrowCounter();
        db.saveUsersToFile();

        // 5. Update Book info
        book.setBookStatus("On Loan");
        book.setBorrowedDate(borrowedDate);
        book.setDueDate(dueDate);
        book.setBorrowerId(user.getUserId());

        // 6. Create and save Loan record
        Loan newLoan = new Loan(userId, bookId, borrowedDate, dueDate);
        lm.addLoan(newLoan);

        lm.saveLoansToFile();
        bm.saveBooksToFile();

        ConsoleUtils.printCentered("Success! Book '" + book.getBookName() + "' lent to " + user.getUserName());
        ConsoleUtils.printCentered("Due date is: " + dueDate);
    }

    public void addCatalog() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║             Adding a New Book            ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        System.out.println();
        String id = bm.generateNextBookId();
        ConsoleUtils.printCentered("Generated Book ID: " + id);

        String name = getNonEmptyInput("Enter the Book Name: ");
        String genre = getNonEmptyInput("Enter the Genre: ");
        String isbn = getNonEmptyInput("Enter ISBN: ");
        String section = getNonEmptyInput("Enter the Section: ");
        String status = "Available";

        Book newBook = new Book(id, name, genre, isbn, section, status);
    
        bm.addBook(newBook);
        bm.saveBooksToFile();
    
        ConsoleUtils.printCentered("Success! [" + name + "] has been added to the catalog.");
        scanner.nextLine();

    }

    private String getNonEmptyInput(String prompt) {
    String input = "";
    while (input.isEmpty()) {
        ConsoleUtils.printCenteredInput(prompt);
        input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            ConsoleUtils.printCentered("Error: Please write something!");
        }
        }
        return input;
    }


    public void removeCatalog() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║             Removing a Book              ║");        
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        ConsoleUtils.printCenteredInput("Enter the Book ID to remove: ");
        String id = scanner.nextLine();
        Book bookToRemove = bm.getBookById(id);

        if (bookToRemove != null) {
            ConsoleUtils.printCentered("Book found: " + bookToRemove.getBookName());
            ConsoleUtils.printCentered("\n---Current Details---");
            ConsoleUtils.printCentered("ID: " + bookToRemove.getBookId() + "\n");
            ConsoleUtils.printCentered("Name: " + bookToRemove.getBookName());
            ConsoleUtils.printCentered("Genre: " + bookToRemove.getBookGenre());
            ConsoleUtils.printCentered("ISBN: " + bookToRemove.getBookISBN());
            ConsoleUtils.printCentered("Section: " + bookToRemove.getBookSection());
            ConsoleUtils.printCentered("Status: " + bookToRemove.getBookStatus());
            ConsoleUtils.printCenteredInput("Are you sure you want to remove this book? (Y/N): ");
            String confirmation = scanner.nextLine();
            switch (confirmation.toUpperCase()) {
                case "Y":
                    bm.removeBook(bookToRemove);
                    bm.saveBooksToFile();
                    ConsoleUtils.printCentered("Book [" + bookToRemove.getBookName() + "] has been removed from the catalog.");
                    scanner.nextLine();
                    break;
                case "N":
                    ConsoleUtils.printCentered("Removal cancelled.");
                    scanner.nextLine();
                    break;
                default:
                    ConsoleUtils.printCentered("Invalid input. Removal cancelled.");
                    scanner.nextLine();
                    break;
            }
            }
            else {
                ConsoleUtils.printCentered("Book not found.");
                scanner.nextLine();
        }
    }

    public void updateCatalog() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║             Updating Book Details        ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        ConsoleUtils.printCenteredInput("Enter the Book ID to update: ");
        String id = scanner.nextLine();

        Book bookToUpdate = bm.getBookById(id);
        if(bookToUpdate != null) {
            ConsoleUtils.printCentered("\n---Current Details---");
            ConsoleUtils.printCentered("ID: " + bookToUpdate.getBookId() + "\n");
            ConsoleUtils.printCentered("1. Name: " + bookToUpdate.getBookName());
            ConsoleUtils.printCentered("2. Genre: " + bookToUpdate.getBookGenre());
            ConsoleUtils.printCentered("3. ISBN: " + bookToUpdate.getBookISBN());
            ConsoleUtils.printCentered("4. Section: " + bookToUpdate.getBookSection());
            ConsoleUtils.printCentered("5. Status: " + bookToUpdate.getBookStatus());
            ConsoleUtils.printCentered("6. Cancel Update");
            ConsoleUtils.printCenteredInput("\nSelect the detail to update (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" :
                    bookToUpdate.setBookName(getNonEmptyInput("Enter new Name: "));
                    break;

                case "2" :
                    bookToUpdate.setBookGenre(getNonEmptyInput("Enter new Genre: "));
                    break;

                case "3" :
                    bookToUpdate.setBookISBN(getNonEmptyInput("Enter new ISBN: "));
                    break;
                
                case "4" :
                    bookToUpdate.setBookSection(getNonEmptyInput("Enter new Section: "));
                    break;

                case "5" :
                    bookToUpdate.setBookStatus(getNonEmptyInput("Enter new Status: "));
                    break;

                case "6" :
                    ConsoleUtils.printCentered("Update cancelled");
                    scanner.nextLine();
                    return;
            
                default:
                    ConsoleUtils.printCentered("Invalid choice");
                    scanner.nextLine();
                    return;
            }

            bm.saveBooksToFile();
            ConsoleUtils.printCentered("\nBook details updated successfully.");
            scanner.nextLine();
        
        }else{
            ConsoleUtils.printCentered("Book ID [" + id + "] not found.");
            scanner.nextLine();
        } 

    }

    public void registerNewAdmin() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║             Register New Admin           ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");

        // 1. Load the current number from the file
        int currentIdNumber = loadCounter();

        // 2. Generate the ID
        String newAdminId = "ADM-" + currentIdNumber;
        ConsoleUtils.printCentered("Auto-generated Admin ID : " + newAdminId);

        ConsoleUtils.printCenteredInput("Enter Admin Name        : ");
        String name = scanner.nextLine();

        ConsoleUtils.printCenteredInput("Enter Admin Email       : ");
        String email = scanner.nextLine();

        ConsoleUtils.printCenteredInput("Enter Admin Phone No    : ");
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
        Admin newAdmin = new Admin(newAdminId, name, "test@gmail.com", "admin123", 0);
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
            ConsoleUtils.printCentered("Could not load counter, starting fresh.");
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
            ConsoleUtils.printCentered("Error saving the counter!");
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
            ConsoleUtils.printCentered("Error loading admin IDs.");
        }
    }

    public void viewPendingFines() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║             Viewing Pending Fines        ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");

        List<User> allUsers = db.getAllUsers();
        boolean foundFines = false;

        System.out.printf("%-10s | %-20s | %-10s\n", "User ID", "Name", "Amount Due");
        ConsoleUtils.printCentered("--------------------------------------------------");

        for (User user : allUsers) {

            // 1. Skip if user is null (BRACKET CLOSES IMMEDIATELY)
            if (user == null) {
                continue;
            }

            // 2. Create a fresh list just for THIS user
            List<Book> thisUsersBooks = new ArrayList<>();

            // 3. Loop through all books to find the ones this user borrowed
            for (Book book : bm.getAllBooks()) {
                if ("On Loan".equals(book.getBookStatus()) && user.getUserId().equals(book.getBorrowerId())) {
                    thisUsersBooks.add(book);
                }
            }

            // 4. Calculate fines using ONLY their specific books
            user.calculateOverdueFines(thisUsersBooks);

            // 5. Print out the details if they owe money
            if (user.getFineAmount() > 0) {
                System.out.println("\n-----------------------Pending Fines---------------------------");
                System.out.println("User: " + user.getUserName() + " (ID: " + user.getUserId() + ")");
                System.out.println("Total Amount Due: $" + String.format("%.2f", user.getFineAmount()));
                System.out.println("Breakdown:");

                // Loops through the Fine objects
                for (Fine fineRecord : user.getFineDetails()) {
                    System.out.println("  -> " + fineRecord.toString());
                }
                foundFines = true;
            }

        } // <-- Closes the main (User user : allUsers) loop

        if (!foundFines) {
            ConsoleUtils.printCentered("No pending fines found in the system.");
        }

        ConsoleUtils.printCentered("Press Enter to return to menu...");
        scanner.nextLine();
    }

    public void removeBookReservation() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("--- Book Reservation Removal ---");
        ConsoleUtils.printCenteredInput("Enter Book ID to remove reservation: ");
        String id = scanner.nextLine();
        Book bookToCancel = bm.getBookById(id);

        if(bookToCancel !=null){
            if ("Reserved".equals(bookToCancel.getBookStatus())) {            
            bm.updateBookAvailable(bookToCancel); 
        } else {
            ConsoleUtils.printCentered("This book is not currently reserved.");
        }
        } else {
        ConsoleUtils.printCentered("Book ID [" + id + "] not found.");
        }
        scanner.nextLine();
    }

    public void viewLostBooks() {
        String choice = "0";
        while (!choice.equals("4")) {
            ConsoleUtils.printCentered("\n--- Lost Books Management ---");
            ConsoleUtils.printCentered("1. Mark Book as Lost");
            ConsoleUtils.printCentered("2. Display List of Lost Books");
            ConsoleUtils.printCentered("3. Mark Book as Restored");
            ConsoleUtils.printCentered("4. Back to Admin Menu");
            ConsoleUtils.printCenteredInput("Enter choice: ");
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
        ConsoleUtils.printCentered("Enter Book ID to mark as LOST: ");
        String id = scanner.nextLine();
        Book book = bm.getBookById(id);

        if (book != null) {
            // Auto-update status to Unavailable
            book.setBookStatus("Lost (Unavailable)");
            bm.saveBooksToFile();
            ConsoleUtils.printCentered("Success: [" + book.getBookName() + "] is now marked as Lost.");
        } else {
            ConsoleUtils.printCentered("Error: Book ID not found.");
        }
        scanner.nextLine();
    }


    private void displayLostBooks() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printCentered("\n--- List of Lost Books ---");
        List<Book> allBooks = bm.getAllBooks(); // Assuming db has a method to get the list
        boolean found = false;

        for (Book book : allBooks) {
            if (book.getBookStatus().equalsIgnoreCase("Lost (Unavailable)")) {
                ConsoleUtils.printCentered("ID: " + book.getBookId() + " | Title: " + book.getBookName());
                found = true;
            }
        }

        if (!found) {
            ConsoleUtils.printCentered("No books are currently marked as lost.");
        }
        scanner.nextLine();
    }

    private void restoreLostBook() {
        ConsoleUtils.printCenteredInput("Enter Book ID to RESTORE: ");
        String id = scanner.nextLine();
        Book book = bm.getBookById(id);

        if (book != null && book.getBookStatus().equalsIgnoreCase("Lost (Unavailable)")) {
            book.setBookStatus("Available");
            bm.saveBooksToFile();
            ConsoleUtils.printCentered("Success: [" + book.getBookName() + "] has been restored to 'Available'.");
        } else {
            ConsoleUtils.printCentered("Error: Book ID not found or book is not currently marked as lost.");
        }
        scanner.nextLine();
    }

}

