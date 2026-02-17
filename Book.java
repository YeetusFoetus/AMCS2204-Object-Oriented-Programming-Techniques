public class Book {
    private String bookId;
    private String bookName;

    public Book (String bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public String getBookId() { return bookId; }
    public String getBookName() { return bookName; }
}