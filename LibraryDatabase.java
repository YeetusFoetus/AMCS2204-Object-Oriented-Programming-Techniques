import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    //private List<Member> members;
    private List<Book> books;
    private List<User> users; 

    public LibraryDatabase() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(String id) {
        for (User u : users) {
            if (u.getUserId().equals(id)) {
                return u;
            }
        }
        return null;  // not found
    }

    public User getUserByName(String name) {
        for (User u : users) {
            if (u.getUserName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printAllBooks() {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            System.out.printf("%-3d %-5s %-15s %-10s %-10s %-10s %-13s\n", 
                i + 1,
                book.getBookId(),
                book.getBookName(),
                book.getBookGenre(),
                book.getBookISBN(),
                book.getBookSection(),
                book.getBookStatus()
            );
        }
    }
}