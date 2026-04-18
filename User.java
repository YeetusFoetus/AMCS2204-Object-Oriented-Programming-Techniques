import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

abstract class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private int borrowCounter;

    LibraryDatabase db; //= new LibraryDatabase();
    Scanner scanner; //= new Scanner(System.in); zheng delete 9/4/2026
    BookManager bm;
    LoanManager lm;

    public abstract String toCSV();

    private double fineAmount = 0.0;
    private List<Fine> fineDetails = new ArrayList<>();

    //Constructor
    public User(String userId, String userName, String userEmail, String userPassword, int borrowCounter) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.borrowCounter = borrowCounter;
    }

    //Getter
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getBorrowCounter() {
        return borrowCounter;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double amount) {
        this.fineAmount = amount;
    }

    // Inside your User class
    public void incrementBorrowCounter() {
        this.borrowCounter++;
    }

    public void setBorrowCounter(int borrowCounter) {
        this.borrowCounter = borrowCounter;
    }

    public void decrementBorrowCounter() {
        if (this.borrowCounter > 0) {
            this.borrowCounter--;
        }
    }

    //Main Menu
    public void mainMenu() {
        LibraryDatabase db = new LibraryDatabase();

        System.out.print("Enter password(ID): ");
        Catalog catalog = new Catalog(scanner);

        String password = scanner.next();

        User member = db.getUserById(password);

        if (member != null) {
            System.out.println("\n==================");
            System.out.println("Welcome " + member.getUserName());
            System.out.println("==================");
            System.out.println("1. Catalog");
            System.out.println("2. Borrowed books");
            System.out.println("3. Study room booking");
            System.out.println("4. Pay fine");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    catalog.catalogScreen(bm);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
        } else if (member == null) {
            System.out.println("Invalid login");
        }
    }

    public List<Fine> getFineDetails() {
        return fineDetails;
    }

    public void calculateOverdueFines(List<Book> borrowedBooks) {
        this.fineAmount = 0.0; // Reset fine to recalculate
        this.fineDetails.clear(); // Clear old fine details

        LocalDate today = LocalDate.now();
        double fineRatePerDay = 0.50; // $0.50 per day overdue

        for (Book book : borrowedBooks) {
            // Check if the book has a due date AND today is after the due date
            if (book.getDueDate() != null && today.isAfter(book.getDueDate())) {

                // Calculate how many days late it is
                long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), today);
                double bookFine = daysOverdue * fineRatePerDay;

                // Add to total amount
                this.fineAmount += bookFine;

                // Create a Fine object (using your Fine.java) and add it to the list
                String reason = "Overdue: " + book.getBookName() + " (" + daysOverdue + " days)";
                Fine fineRecord = new Fine(reason, bookFine);
                fineDetails.add(fineRecord);
            }
        }
    }
}


