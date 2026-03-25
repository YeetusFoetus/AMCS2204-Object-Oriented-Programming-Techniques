import java.util.Scanner;

public class Catalog {
    Scanner scanner;
    MemberMenu memberMenu = new MemberMenu(scanner);

    public Catalog(Scanner scanner) {
        this.scanner = scanner;
    }

    public void catalogScreen(LibraryDatabase db) {
        db.printAllBooks();

        System.out.println("Reserve a book (Y/N): ");

        scanner.nextLine();

        String input = scanner.nextLine();
        char choice = input.charAt(0);

        if (choice == 'Y') {
            System.out.println("Enter a book to reserve (ID): ");
            
            String id = scanner.nextLine();

            if (id != null) {                
                Book book = db.getBookById(id);
                db.updateBookOnLoan(book);
                memberMenu.login1();
            }
        }        
    }

    public void searchBook(LibraryDatabase db) {
        System.out.println("1. Book name\n" +
            "2. Book ID\n" +
            "Search by: "
        );

        //check for non-integer, not 1 or 2
        int choice;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                System.out.println("Number entered successfully.");
            } else {
                System.out.println("This option isn't available");
            }

        } else {
            System.out.println("You did not enter a number.");
        }
        
        scanner.nextLine(); 
    } 

    public void reserveBook() {
        //update book status to 'Reserved'
    }
}