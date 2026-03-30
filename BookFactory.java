public class BookFactory {
    private BookFactory() {};

    public static Book createBook(String[] data) {
        //String bookId, String bookName, string bookgenre, string bookisbn, string booksection, string bookstatus
        String id = data[0];
        String name = data[1];
        String genre = data[2];
        String isbn = data[3];
        String section = data[4];
        String status = data[5];
        
        return new Book(id, name, genre, isbn, section, status);
    }
}