import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class ReportGen {
    Random r = new Random();
    LibraryDatabase ld = new LibraryDatabase();
    RoomManager rm = new RoomManager();
    BookManager bm = new BookManager();

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
            try{
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
                        displayStudyRoom();
                        break; // for the study room stuff
                    case 5:
                        break; // exit 
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
        System.out.println("2. Fines summary");
        System.out.println("3. Lost books");
        System.out.println("4. Discussion Room Statistics");
        System.out.println("5. Exit");

        scanner.nextLine();
        return scanner.nextInt();
    }



    // book name : number
    public void displayTotalBorrowed(){
        bm.loadBooksFromFile();

        System.out.printf("%-25s %-16s %-16s \n",
                        "Book name", "Total borrowed", "Total returned");

        bm.printBorrowCountBooks();
        System.out.println("\n");
    }

    public void displayStudyRoom(){
        List<DiscussionRoom> room2 = RoomManager.rooms;
        System.out.printf("%-9s %-20s\n", "Room ID", "Total Booking Count");
        for (DiscussionRoom r : room2){
            System.out.printf("%-9s %-20d\n", r.getRoomID(), r.getBookingCount());
        }
        System.out.println("\n");
    } // genuinely dont know how the FUCK i managed to get this to even work but it works

}