import java.util.Scanner;

public class Catalog {
    Scanner scanner;
    MemberMenu memberMenu = new MemberMenu(scanner);

    public Catalog(Scanner scanner) {
        this.scanner = scanner;
    }

    public void catalogScreen(BookManager bm) {
        bm.printAllBooks();

        Boolean condition = true; 

        while (condition == true) {
            System.out.println("Reserve a book (Y/N): ");
            
            String input = scanner.nextLine();
            char choice = input.charAt(0);

            if (choice == 'Y') {
                System.out.println("Enter a book to reserve (ID): ");
                
                String id = scanner.nextLine();

                if (id != null) {                
                    Book book = bm.getBookById(id);
                    bm.updateBookReserved(book);
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

    public void searchBook(BookManager bm) {
        bm.loadBooksFromFile();

        System.out.println(
            "1. Book name\n" +
            "2. Book ID\n" +
            "3. Genre\n" +
            "4. Section\n" +
            "Search by: "
        );

        int choice;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean try1 = true;

                    while (try1) {
                        System.out.println("Enter book name: ");
                        String name1 = scanner.nextLine().trim();

                        Book name2 = bm.getBookByName(name1);

                        if (name2 != null) {
                            System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                                name2.getBookId(),
                                name2.getBookName(),
                                name2.getBookGenre(),
                                name2.getBookISBN(),
                                name2.getBookSection(),
                                name2.getBookStatus()
                            );
                        } else {
                            System.out.println("This book doesn't exist.");
                        }

                        System.out.print("\nWould you like to try again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();
                        
                        if (!response.equals("yes") && !response.equals("y")) {
                            try1 = false;
                            System.out.println("Returning to main menu...");
                        }
                    }                        
                    break;
                case 2:
                    boolean try2 = true;

                    while (try2) {
                        System.out.println("Enter book ID: ");
                        String id1 = scanner.nextLine().trim();

                        Book id2 = bm.getBookById(id1);

                        if (id2 != null) {
                            System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                                id2.getBookId(),
                                id2.getBookName(),
                                id2.getBookGenre(),
                                id2.getBookISBN(),
                                id2.getBookSection(),
                                id2.getBookStatus()
                            );
                        } else {
                            System.out.println("This book doesn't exist.");
                        }

                        System.out.print("\nWould you like to try again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();
                        
                        if (!response.equals("yes") && !response.equals("y")) {
                            try2 = false;
                            System.out.println("Returning to main menu...");
                        }
                    }
                    break;
                case 3:
                    boolean try3 = true;

                    while (try3) {
                        System.out.println("Enter a genre: ");
                        String genre = scanner.nextLine();
                        bm.printAllBooksByGenre(genre);

                        System.out.print("\nWould you like to search again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();
                        
                        if (!response.equals("yes") && !response.equals("y")) {
                            try3 = false;
                            System.out.println("Returning ...");
                        }
                    }
                    break;
                case 4:
                    boolean try4 = true;

                    while (try4) {
                        System.out.println("Enter book section: ");
                        String section = scanner.nextLine();
                        bm.printAllBooksBySection(section);

                        System.out.print("\nWould you like to search again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();
                        
                        if (!response.equals("yes") && !response.equals("y")) {
                            try4 = false;
                            System.out.println("Returning ...");
                        }
                    }
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