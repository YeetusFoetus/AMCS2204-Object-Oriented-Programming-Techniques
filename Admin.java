import java.util.Scanner;

public class Admin {

    LoginScreen loginScreen;
    Scanner scanner;
    LibraryDatabase db;
    
    public Admin(Scanner scanner, LibraryDatabase db) {
        this.scanner = scanner;
        this.db = db;
    }

    public void adminLogin() {
        System.out.println("----Admin Login----");
        System.out.println("Enter your Admin ID: ");

        scanner.nextLine();
        String id = scanner.nextLine().trim();

        if(id.equals("admin995")){
            System.out.println("Login Success, Please press Enter to continue...");
            scanner.nextLine();
            adminMenu();
        }
        else{
            System.out.println("Invalid Login ID, please try again.");
            scanner.nextLine();
        }
    }

    public void adminMenu(){
        String choice = "0";

        while(!choice.equals("7")){
        System.out.println("\n====================");
        System.out.println("     Admin Menu");
        System.out.println("====================");
        System.out.println("1. Add catalog entry");
        System.out.println("2. Remove catalog entry");
        System.out.println("3. Update book details");
        System.out.println("4. Register new admin");
        System.out.println("5. Pending fines");
        System.out.println("6. Lost books");
        System.out.println("7. Back to Main Menu");

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
                registerAdmin();
                break;

            case "5":
                viewPendingFines();
                break;

            case "6":
                viewLostBooks();
                break;
            
            case "7":
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

    public void registerAdmin() {
        System.out.println("--- Registering a New Admin ---");
    }

    public void viewPendingFines() {
        System.out.println("--- Viewing Pending Fines ---");
    }

    public void viewLostBooks() {
        System.out.println("--- Viewing Lost Books ---");
    }

    
}