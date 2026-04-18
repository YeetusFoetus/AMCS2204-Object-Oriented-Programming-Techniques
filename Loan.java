import java.time.LocalDate;

public class Loan {
    private String userId;
    private String bookId;
    private LocalDate borrowedDate;
    private LocalDate dueDate;

    public Loan() {};

    public Loan(String userId, String bookId, LocalDate borrowedDate, LocalDate dueDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
    }

    public String getUserId() { return userId; }
    public String getBookId() { return bookId; }
    public LocalDate getBorrowedDate() { return borrowedDate; }
    public LocalDate getDueDate() { return dueDate; }

    @Override
    public String toString() {
        return String.format("User ID: %-10s | Book ID: %-10s | Borrowed: %-12s | Due: %-12s", 
                              userId, bookId, borrowedDate, dueDate);
    }
}