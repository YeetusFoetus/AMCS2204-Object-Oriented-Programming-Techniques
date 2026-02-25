import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    //private List<Member> members;
    private List<Book> books;
    private List<User> users; 

    LibraryDatabase() {
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

    public Book getBookById(String id) {
        for (Book b : books) {
            if (b.getBookId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    public Book updateBookOnLoan(Book b1) {
        for (Book b2 : books) {
            if (b1 == b2) {
                b1.changeStatusToLoan();
            }
            else {
                System.out.println("This book doesn't exist");
            }
        }
        return null;
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