import java.util.Scanner;

public class Catalog {
    Scanner scanner;
    LoanManager lm;
    MemberMenu memberMenu; // Removed the instantiation here to avoid a potential infinite loop/stack overflow

    public Catalog(Scanner scanner) {
        this.scanner = scanner;
    }

    public void catalogScreen(BookManager bm) {
        ConsoleUtils.clearScreen();
        System.out.println();
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║               BOOK CATALOG               ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        System.out.println();

        bm.printAllBooks();
        System.out.println();

        boolean condition = true;

        while (condition) {
            ConsoleUtils.printCenteredInput("Reserve a book (Y/N): ");

            String input = scanner.nextLine().trim().toUpperCase();

            // Prevent crash if user just presses Enter
            if (input.isEmpty()) {
                continue;
            }

            char choice = input.charAt(0);

            if (choice == 'Y') {
                ConsoleUtils.printCenteredInput("Enter a book to reserve (ID): ");
                String id = scanner.nextLine().trim();

                if (!id.isEmpty()) {
                    Book book = bm.getBookById(id);
                    if (book != null) {
                        bm.updateBookReserved(book);
                    } else {
                        System.out.println();
                        ConsoleUtils.printCentered("Book not found.");
                    }
                }

                System.out.println();
                ConsoleUtils.printCenteredInput("Press Enter to return...");
                scanner.nextLine();
                condition = false;

            } else if (choice == 'N') {
                return;
            } else {
                ConsoleUtils.printCentered("Please enter 'Y' or 'N' only");
            }
        }
    }

    public void searchBook(BookManager bm) {
        bm.loadBooksFromFile();

        ConsoleUtils.clearScreen();
        System.out.println(); // Kept this here to prevent that form-feed glitch!

        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("║             1. Book name                 ║");
        ConsoleUtils.printCentered("║             2. Book ID                   ║");
        ConsoleUtils.printCentered("║             3. Genre                     ║");
        ConsoleUtils.printCentered("║             4. Section                   ║");
        ConsoleUtils.printCentered("║             5. Return                    ║");
        ConsoleUtils.printCentered("║                                          ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        ConsoleUtils.printCenteredInput("Search by: ");

        int choice;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean try1 = true;

                    while (try1) {
                        System.out.println();
                        ConsoleUtils.printCenteredInput("Enter book name: ");
                        String name1 = scanner.nextLine().trim();

                        Book name2 = bm.getBookByName(name1);

                        System.out.println();
                        if (name2 != null) {
                            // Centered headers and data
                            String header = String.format("%-5s %-15s %-10s %-10s %-10s %-13s", "ID", "Name", "Genre", "ISBN", "Section", "Status");
                            String bookData = String.format("%-5s %-15s %-10s %-10s %-10s %-13s",
                                    name2.getBookId(), name2.getBookName(), name2.getBookGenre(),
                                    name2.getBookISBN(), name2.getBookSection(), name2.getBookStatus()
                            );
                            ConsoleUtils.printCentered(header);
                            ConsoleUtils.printCentered("------------------------------------------------------------------");
                            ConsoleUtils.printCentered(bookData);
                        } else {
                            ConsoleUtils.printCentered("This book doesn't exist.");
                        }

                        System.out.println();
                        ConsoleUtils.printCenteredInput("Would you like to try again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();

                        if (!response.equals("yes") && !response.equals("y")) {
                            ConsoleUtils.clearScreen();
                            try1 = false;
                        }
                    }
                    break;
                case 2:
                    boolean try2 = true;
                    while (try2) {
                        System.out.println();
                        ConsoleUtils.printCenteredInput("Enter book ID: ");
                        String id1 = scanner.nextLine().trim();

                        Book id2 = bm.getBookById(id1);

                        System.out.println();
                        if (id2 != null) {
                            String header = String.format("%-5s %-15s %-10s %-10s %-10s %-13s", "ID", "Name", "Genre", "ISBN", "Section", "Status");
                            String bookData = String.format("%-5s %-15s %-10s %-10s %-10s %-13s",
                                    id2.getBookId(), id2.getBookName(), id2.getBookGenre(),
                                    id2.getBookISBN(), id2.getBookSection(), id2.getBookStatus()
                            );
                            ConsoleUtils.printCentered(header);
                            ConsoleUtils.printCentered("------------------------------------------------------------------");
                            ConsoleUtils.printCentered(bookData);
                        } else {
                            ConsoleUtils.printCentered("This book doesn't exist.");
                        }

                        System.out.println();
                        ConsoleUtils.printCenteredInput("Would you like to try again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();

                        if (!response.equals("yes") && !response.equals("y")) {
                            ConsoleUtils.clearScreen();
                            try2 = false;
                        }
                    }
                    break;
                case 3:
                    boolean try3 = true;

                    while (try3) {
                        System.out.println();
                        ConsoleUtils.printCenteredInput("Enter a genre: ");
                        String genre = scanner.nextLine();
                        System.out.println();
                        bm.printAllBooksByGenre(genre);

                        System.out.println();
                        ConsoleUtils.printCenteredInput("Would you like to search again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();

                        if (!response.equals("yes") && !response.equals("y")) {
                            ConsoleUtils.clearScreen();
                            try3 = false;
                        }
                    }
                    break;
                case 4:
                    boolean try4 = true;
                    while (try4) {
                        System.out.println();
                        ConsoleUtils.printCenteredInput("Enter book section: ");
                        String section = scanner.nextLine();
                        System.out.println();
                        bm.printAllBooksBySection(section);

                        System.out.println();
                        ConsoleUtils.printCenteredInput("Would you like to search again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();

                        if (!response.equals("yes") && !response.equals("y")) {
                            ConsoleUtils.clearScreen();
                            try4 = false;
                        }
                    }
                    break;
                default:
                    System.out.println();
                    ConsoleUtils.printCentered("This option isn't available");
                    ConsoleUtils.printCenteredInput("Press Enter to return...");
                    scanner.nextLine();
                    ConsoleUtils.clearScreen();
                    break;
            }

        } else {
            System.out.println();
            ConsoleUtils.printCentered("You did not enter a number.");
            ConsoleUtils.printCenteredInput("Press Enter to return...");
            scanner.nextLine(); // clear the invalid input
            scanner.nextLine(); // wait for enter
            ConsoleUtils.clearScreen();
        }
    }

    public void reserveBook() {
        //update book status to 'Reserved'
    }
}