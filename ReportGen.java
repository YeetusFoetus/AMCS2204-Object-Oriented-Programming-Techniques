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
                        displayFines();
                        break; // for fine
                    case 3:
                        break; // for lost books
                    case 4:
                        displayStudyRoom();
                        break; // for the study room stuff
                    case 5:
                        break; // exit 
                    default:
                        ConsoleUtils.printCentered("Invalid input");
                        return;
                    }
                } catch (Exception e){
                    ConsoleUtils.printCentered("Invalid input! Try again.");
             }
        } while (choice != 5);
        scanner.nextLine();

    }

    public static int printMenu(){
        ConsoleUtils.clearScreen();
        System.out.println("\n\n\n\n");
        ConsoleUtils.printCentered("╔════════════════════════════════════════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║  This is the report generator part. Which one do you want to access here?  ║");
        ConsoleUtils.printCentered("╠════════════════════════════════════════════════════════════════════════════╣");
        ConsoleUtils.printCentered("║          1. Borrowing Statistics for Every User                            ║");
        ConsoleUtils.printCentered("║          2. Fines summary                                                  ║");
        ConsoleUtils.printCentered("║          3. Lost books                                                     ║");
        ConsoleUtils.printCentered("║          4. Discussion Room Statistics                                     ║");
        ConsoleUtils.printCentered("║          5. Exit                                                           ║");
        ConsoleUtils.printCentered("╚════════════════════════════════════════════════════════════════════════════╝");
        ConsoleUtils.printCenteredInput("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }



    // book name : number
    public void displayTotalBorrowed(){
        bm.loadBooksFromFile();
        int tableWidth = 46; 
        String offset = ConsoleUtils.getPadding(tableWidth);

        System.out.print(offset);
        System.out.printf("%-20s | %-16s\n", "User's name", "Total borrow count");
        
        System.out.print(offset);
        System.out.println("-".repeat(tableWidth));

        bm.printBorrowCountBooks(offset); 
        
        System.out.println();
        ConsoleUtils.printCenteredInput("Press Enter to return...");
        scanner.nextLine();
    }

    public void displayFines(){
        
        
    }

    public void displayStudyRoom(){
        List<DiscussionRoom> room2 = RoomManager.rooms;
        int tableWidth = 46; 
        String offset = ConsoleUtils.getPadding(tableWidth);
        System.out.print(offset);
        ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
        ConsoleUtils.printCentered("║    Discussion Room Booking Statistics    ║");
        ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
        System.out.print(offset);
        System.out.printf("%-9s | %-20s\n", "Room ID", "Total Booking Count");
        for (DiscussionRoom r : room2){
            System.out.printf("%-9s | %-20d\n", r.getRoomID(), r.getBookingCount());
        }
        scanner.nextLine();
    } // it works

}