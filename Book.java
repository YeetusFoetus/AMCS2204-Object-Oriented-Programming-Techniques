public class Book {
    private String bookId;
    private String bookName;
    private String bookGenre;
    private String bookISBN;
    private String bookSection;
    private String bookStatus; 

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

    public String changeStatusToLoan() {
        return this.bookStatus = "On Loan";
    }
}