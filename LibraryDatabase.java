import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class LibraryDatabase {
    // Fields
    //private List<Member> members;
    private List<Book> books;
    private List<User> users; 

    LibraryDatabase() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // File read write
    public void saveUsersToFile() {
        try {
            FileWriter writer = new FileWriter("users.csv");

            for (User user : users) {
                writer.write(user.toCSV());
                writer.write("\n");
            }

            writer.close();
            System.out.println("Users saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsersFromFile() {
        users.clear(); // prevent duplicates

        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.csv"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                User user = UserFactory.createUser(data);
                users.add(user);
            }

            reader.close();
            System.out.println("Users loaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Other methods
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

    public void getLastUser() {
        int size = users.size() - 1;

        if (size >= 0) {
            User lastUser = users.get(size);
            System.out.println("ID: " + lastUser.getUserId() +
                "\nName: " + lastUser.getUserName() +
                "\nPassword: " + lastUser.getUserPassword());
        } else {
            System.out.println("No objects in ArrayList");
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBookById(String id) {
        for (Book b : books) {
            if (b.getBookId().equals(id)) {
                return b;
            } else {
                System.out.println("We couldn't find the book you're searching for");
            }
        }
        return null;
    }

    public Book getBookByName(String name) {
        for (Book b : books) {
            if (b.getBookName().equals(name)) {
                return b;
            } else {
                System.out.println("We couldn't find the book you're searching for");
            }
        }
        return null;
    }

    public void removeBook(Book book){
        if (book != null) {
            books.remove(book);
        }
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

    public String generateNextBookId(){
        int nextBook = books.size() + 1;
        return String.format("B%03d", nextBook);
    }


}