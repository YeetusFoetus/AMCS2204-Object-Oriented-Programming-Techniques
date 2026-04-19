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

        while(!choice.equals("10")){
        System.out.println("\n====================");
        System.out.println("     Admin Menu");
        System.out.println("====================");
        System.out.println("1. Lend a book");
        System.out.println("2. Add catalog entry");
        System.out.println("3. Remove catalog entry");
        System.out.println("4. Update book details");
        System.out.println("5. Register new admin");
        System.out.println("6. Pending fines");
        System.out.println("7. Remove Book Reservation");
        System.out.println("8. Lost books");
        System.out.println("9. Generate Report");
        System.out.println("10. Back to Main Menu");

        System.out.print("\nEnter choice: ");
        choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Enter user ID:");
                String UID = scanner.nextLine();
                System.out.println("Enter book ID: ");
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
                System.out.println("Invalid choice! Please select 1-10.");
                scanner.nextLine();
                break;
         }
        
        }

        System.out.println("Returning to main menu...");
        scanner.nextLine();
        return;
        
    }

    public void lendBook(String userId, String bookId) {
        // 1. Fetch the User and Book from your data source (e.g., a List or DB)
        User user = db.getUserById(userId);
        Book book = bm.getBookById(bookId);

        // 2. Simple Validation
        if (user == null || book == null) {
            System.out.println("Error: User or Book does not exist.");
            return;
        }

        if (book.getBookStatus().equalsIgnoreCase("On Loan")) {
            System.out.println("Error: Book is already borrowed.");
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

        // 5. Update Book info
        book.setBookStatus("On Loan");
        book.setBorrowedDate(borrowedDate);
        book.setDueDate(dueDate);

        book.setBorrowerId(user.getUserId());

        // 6. Create and save Loan record
        Loan newLoan = new Loan(userId, bookId, borrowedDate, dueDate);
        lm.addLoan(newLoan);

        bm.saveBooksToFile();

        System.out.println("Success! Book '" + book.getBookName() + "' lent to " + user.getUserName());
        System.out.println("Due date is: " + dueDate);
    }

    public void addCatalog() {
        System.out.println("--- Adding a New Book ---");
        String id = bm.generateNextBookId();
        System.out.println("Generated Book ID: " + id);

        String name = getNonEmptyInput("Enter the Book Name: ");
        String genre = getNonEmptyInput("Enter the Genre: ");
        String isbn = getNonEmptyInput("Enter ISBN: ");
        String section = getNonEmptyInput("Enter the Section: ");
        String status = "Available";

        Book newBook = new Book(id, name, genre, isbn, section, status);
    
        bm.addBook(newBook);
        bm.saveBooksToFile();
    
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
        Book bookToRemove = bm.getBookById(id);

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
                    bm.removeBook(bookToRemove);
                    bm.saveBooksToFile();
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

        Book bookToUpdate = bm.getBookById(id);
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
                    System.out.println("Update cancelled");
                    scanner.nextLine();
                    return;
            
                default:
                    System.out.println("Invalid choice");
                    scanner.nextLine();
                    return;
            }

            bm.saveBooksToFile();
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

    public void viewPendingFines() {
        System.out.println("\n--- Viewing Pending Fines ---");

        List<User> allUsers = db.getAllUsers();
        boolean foundFines = false;

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
            System.out.println("No pending fines found in the system.");
        }

        System.out.println("\nPress Enter to return to menu...");
        scanner.nextLine();
    }

    public void removeBookReservation() {
        System.out.println("--- Book Reservation Removal ---");
        System.out.println("Enter Book ID to remove reservation: ");
        String id = scanner.nextLine();
        Book bookToCancel = bm.getBookById(id);

        if(bookToCancel !=null){
            if ("Reserved".equals(bookToCancel.getBookStatus())) {            
            bm.updateBookAvailable(bookToCancel); 
        } else {
            System.out.println("This book is not currently reserved.");
        }
        } else {
        System.out.println("Book ID [" + id + "] not found.");
        }
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
        Book book = bm.getBookById(id);

        if (book != null) {
            // Auto-update status to Unavailable
            book.setBookStatus("Lost (Unavailable)");
            bm.saveBooksToFile();
            System.out.println("Success: [" + book.getBookName() + "] is now marked as Lost.");
        } else {
            System.out.println("Error: Book ID not found.");
        }
        scanner.nextLine();
    }


    private void displayLostBooks() {
        System.out.println("\n--- List of Lost Books ---");
        List<Book> allBooks = bm.getAllBooks(); // Assuming db has a method to get the list
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
        Book book = bm.getBookById(id);

        if (book != null && book.getBookStatus().equalsIgnoreCase("Lost (Unavailable)")) {
            book.setBookStatus("Available");
            bm.saveBooksToFile();
            System.out.println("Success: [" + book.getBookName() + "] has been restored to 'Available'.");
        } else {
            System.out.println("Error: Book ID not found or book is not currently marked as lost.");
        }
        scanner.nextLine();
    }

}

