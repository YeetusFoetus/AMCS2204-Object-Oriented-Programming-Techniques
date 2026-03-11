import java.util.Scanner;

public class Catalog {
    Scanner scanner;
    MemberMenu memberMenu = new MemberMenu(scanner);

    public void catalogScreen(LibraryDatabase db) {
        db.printAllBooks();

        System.out.println("Reserve a book (Y/N): ");

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

    public void reserveBook() {
        
    }
}