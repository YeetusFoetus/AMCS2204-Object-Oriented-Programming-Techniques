import java.util.Scanner;
import java.util.Random;

public class ReportGen {
    Random r = new Random();
    LibraryDatabase ld = new LibraryDatabase();

    Book bookName = new Book("B011",
                            "Tung", 
                            "Classic", 
                            "978-47387242", 
                            "Literature", 
                            "Available");

    private static Scanner scanner = new Scanner(System.in);

    public void reportGen() {
        int choice = 0;
        do {
            choice = printMenu();
            switch(choice){
            case 1:
                displayTotalBorrowed();
                break; //ts for BorrowStat
            case 2:
                break; // for fine
            case 3:
                break; // for lost books
            case 4:
                break; // for the study room stuff
            case 5:
                System.exit(0);
                break; // exit 
            default:
                System.out.println("invalid input");
                return;
            }
        } while (choice != 5);

    }

    public static int printMenu(){
        System.out.println("This is the report generator part. Which one do you want to access here?");
        System.out.println("========================================================================");        
        System.out.println("1. Borrowing Statistics for Every User");
        System.out.println("2. Fines summary");
        System.out.println("3. Lost books");
        System.out.println("4. Study Room Statistics");
        System.out.println("5. Exit");

        return scanner.nextInt();
    }


    
    // book name : number
    public void displayTotalBorrowed(){
        ld.loadBooksFromFile();

        System.out.printf("%-12s %-16s %-12s \n", 
                        "Book name", "Total borrowed",
                    "Total returned");

        ld.printBorrowCountBooks();
    }

}