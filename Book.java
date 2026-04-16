import java.time.LocalDate;

public class Book {
    private String bookId;
    private String bookName;
    private String bookGenre;
    private String bookISBN;
    private String bookSection;
    private String bookStatus;
    private LocalDate dueDate;
    private LocalDate  borrowedDate;


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Getter for borrowedDate
    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    // Setter for borrowedDate
    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Book (String bookId, 
            String bookName, 
            String bookGenre, 
            String bookISBN,
            String bookSection,
            String bookStatus) {
                this.bookId = bookId;
                this.bookName = bookName;
                this.bookGenre = bookGenre;
                this.bookISBN = bookISBN;
                this.bookSection = bookSection;
                this.bookStatus = bookStatus;
    }

    public String getBookId() { return bookId; }
    public String getBookName() { return bookName; }
    public String getBookGenre() { return bookGenre; }
    public String getBookISBN() { return bookISBN; }
    public String getBookSection() { return bookSection; }
    public String getBookStatus() { return bookStatus; }

    public void setBookName(String bookName) { this.bookName = bookName; }
    public void setBookGenre(String bookGenre) { this.bookGenre = bookGenre; }
    public void setBookISBN(String bookISBN) { this.bookISBN = bookISBN; }
    public void setBookSection(String bookSection) { this.bookSection = bookSection; }
    public void setBookStatus(String bookStatus) { this.bookStatus = bookStatus; }
    
    public String toCSV() {
        return getBookId() + "," + getBookName() + "," + getBookGenre() +
            "," + getBookISBN() + "," + getBookSection() + "," + getBookStatus();
    }

    public void changeStatusToLoan() {
        this.bookStatus = "On Loan";
    }

    public void changeStatustoReserved() {
        this.bookStatus = "Reserved";
    }

    public void changeStatustoAvailable() {
        this.bookStatus = "Available";
    }


}