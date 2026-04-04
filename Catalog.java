import java.util.Scanner;

public class Catalog {
    Scanner scanner;
    MemberMenu memberMenu = new MemberMenu(scanner);

    public Catalog(Scanner scanner) {
        this.scanner = scanner;
    }

    public void catalogScreen(LibraryDatabase db) {
        db.printAllBooks();

        Boolean condition = true; 

        while (condition == true) {
            System.out.println("Reserve a book (Y/N): ");
            
            String input = scanner.nextLine();
            char choice = input.charAt(0);

            if (choice == 'Y') {
                System.out.println("Enter a book to reserve (ID): ");
                
                String id = scanner.nextLine();

                if (id != null) {                
                    Book book = db.getBookById(id);
                    db.updateBookReserved(book);
                }
                condition = false;
            } else if (choice == 'N') {
                System.out.println("Test");
                return; 
            } else {
                System.out.println("Please enter 'Y' or 'N' only");    
            }
        }
    }

    public void searchBook(LibraryDatabase db) {
        db.loadBooksFromFile();

        System.out.println("1. Book name\n" +
            "2. Book ID\n" +
            "3. Genre\n" +
            "9. Test\n" +
            "Search by: "
        );

        //check for non-integer, not 1 or 2
        int choice;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter book name: ");
                    String name1 = scanner.nextLine();
                    name1 = name1.trim();

                    Book name2 = db.getBookByName(name1);

                    System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                        name2.getBookId(),
                        name2.getBookName(),
                        name2.getBookGenre(),
                        name2.getBookISBN(),
                        name2.getBookSection(),
                        name2.getBookStatus()
                    );
                    break;
                case 2:
                    System.out.println("Enter book ID: ");
                    String id1 = scanner.nextLine();

                    Book id2 = db.getBookById(id1);

                    System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                        id2.getBookId(),
                        id2.getBookName(),
                        id2.getBookGenre(),
                        id2.getBookISBN(),
                        id2.getBookSection(),
                        id2.getBookStatus()
                    );
                    break;
                case 3:
                    System.out.println("Enter book genre: ");
                    String genre1 = scanner.nextLine();
                    System.out.println(genre1);

                    Book genre2 = db.getBookByGenre(genre1);

                    System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                        genre2.getBookId(),
                        genre2.getBookName(),
                        genre2.getBookGenre(),
                        genre2.getBookISBN(),
                        genre2.getBookSection(),
                        genre2.getBookStatus()
                    );
                    break;
                case 4:
                    System.out.println("Enter book ISBN: ");
                    String isbn1 = scanner.nextLine();

                    Book isbn2 = db.getBookByISBN(isbn1);

                    System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                        isbn2.getBookId(),
                        isbn2.getBookName(),
                        isbn2.getBookGenre(),
                        isbn2.getBookISBN(),
                        isbn2.getBookSection(),
                        isbn2.getBookStatus()
                    );
                    break;
                case 5:
                    System.out.println("Enter book section: ");
                    String sec1 = scanner.nextLine();

                    Book sec2 = db.getBookBySection(sec1);

                    System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                        sec2.getBookId(),
                        sec2.getBookName(),
                        sec2.getBookGenre(),
                        sec2.getBookISBN(),
                        sec2.getBookSection(),
                        sec2.getBookStatus()
                    );
                    break;
                case 6:
                    System.out.println("Enter book status: ");
                    String status1 = scanner.nextLine();

                    Book status2 = db.getBookByStatus(status1);

                    System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                        status2.getBookId(),
                        status2.getBookName(),
                        status2.getBookGenre(),
                        status2.getBookISBN(),
                        status2.getBookSection(),
                        status2.getBookStatus()
                    );
                    break;
                case 9:
                    Book testBook = new Book("apple", "apple", "apple", "apple", "apple", "apple");
                    String test = testBook.getBookId();
                    System.out.print(test);
                    break;
                default:
                    System.out.println("This option isn't available");
                    break;
            }

        } else {
            System.out.println("You did not enter a number. Press enter to return");
            scanner.nextLine();
        }
        
        scanner.nextLine(); 
    } 

    public void reserveBook() {
        //update book status to 'Reserved'
    }
}