import java.util.Scanner;
import java.util.List;

public class ReportGen {
    LibraryDatabase ld = new LibraryDatabase();
    RoomManager rm = new RoomManager();
    BookManager bm = new BookManager();
    LoanManager lm = new LoanManager();

    private static Scanner scanner = new Scanner(System.in);

    public void reportGen() {
        int choice = 0;

        do {
            try{
                choice = printMenu();
                switch(choice){
                    case 1:
                        displayTotalBorrowed();
                        break; //ts for BorrowStat
                    case 2:
                        displayStudyRoom();
                        break; // for fine
                    case 3:
                        break; // for lost books
                    default:
                        System.out.println("Invalid input");
                        return;
                    }
                } catch (Exception e){
                    System.out.println("Invalid input! Try again.\n");
             }
        } while (choice != 5);

    }

    public static int printMenu(){
        System.out.println("========================================================================");
        System.out.println("This is the report generator part. Which one do you want to access here?");
        System.out.println("========================================================================");
        System.out.println("1. Borrowing Statistics for Every User");
        System.out.println("2. Discussion Room Statistics");
        System.out.println("3. Exit");

        scanner.nextLine();
        return scanner.nextInt();
    }


    // book name : number
    public void displayTotalBorrowed(){
        bm.loadBooksFromFile();

        System.out.printf("%-20s | %-16s\n", 
                        "User's name", "Total borrow count");

        bm.printBorrowCountBooks();
        System.out.println("\n");
    }


    public void displayStudyRoom(){
        List<DiscussionRoom> room2 = RoomManager.rooms;
        System.out.printf("%-9s | %-20s\n", "Room ID", "Total Booking Count");
        for (DiscussionRoom r : room2){
            System.out.printf("%-9s | %-20d\n", r.getRoomID(), r.getBookingCount());
        }
        System.out.println("\n");
    } // it works

}