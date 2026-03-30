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
            System.out.println("Login Success");
            adminMenu();
        }
        else{
            System.out.println("Invalid Login");
        }
    }

    public void adminMenu(){
        String choice = "0";

        while(!choice.equals("4")){
        System.out.println("\n1. Add catalog entry");
        System.out.println("2. Remove catalog entry");
        System.out.println("3. Update book details");
        System.out.println("4. Back to Main Menu");

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
        String id = db.generateNextBookId();
        System.out.println("Generated Book ID: " + id);

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
        System.out.println("Enter the Book ID to remove: ");
        String id = scanner.nextLine();
        Book bookToRemove = db.getBookById(id);

        if (bookToRemove != null) {
            System.out.println("Book found: " + bookToRemove.getBookName());
            System.out.println("Are you sure you want to remove this book? (Y/N)");
            String confirmation = scanner.nextLine();
            switch (confirmation.toUpperCase()) {
                case "Y":
                    db.removeBook(bookToRemove);
                    System.out.println("Book [" + bookToRemove.getBookName() + "] has been removed from the catalog.");
                    break;
                case "N":
                    System.out.println("Removal cancelled.");
                    break;
                default:
                    System.out.println("Invalid input. Removal cancelled.");
                    break;
            }
            }
            else {
                System.out.println("Book not found.");
        }
    }

    public void updateCatalog() {
        System.out.println("--- Updating Book Details ---");
    }
    
}