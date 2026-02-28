import java.util.Scanner;

public class ReportGen {
    Scanner scanner = new Scanner(System.in);
    public void generateReport() {
        System.out.println("This is the report generator part. Which one do you want to access here?\n");
        System.out.println("1. Borrowing Statistics for Every User\n");
        System.out.println("2. Fines summary\n");
        System.out.println("3. Lost books\n");
        System.out.println("4. Study Room Statistics");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                break; //ts for BorrowStat
            case 2:
                break; // for fine
            case 3:
                break; // for lost books
            case 4:
                break; // for the study room stuff
            case 5:
                break; // exit (exit) (exit(((()))))
            default:
                System.out.println("dont do that");
                return;
        }
    }
}
