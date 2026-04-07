import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class LibraryDatabase {
    // Fields
    //private List<Member> members;

    private List<Book> books;
    private List<User> users; 

    LibraryDatabase() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }


    // -------USERS--------
    // File read write
    public void saveUsersToFile() {
        try {
            FileWriter writer = new FileWriter("users.csv");

            for (User user : users) {
                writer.write(user.toCSV());
                writer.write("\n");
            }

            writer.close();
            System.out.println("User saved successfully.");

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
            //System.out.println("Users loaded successfully.");

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


    // -------BOOKS-------
    public void addBook(Book book) {
        books.add(book);
    }

    public void saveBooksToFile() {
        try {
            FileWriter writer = new FileWriter("books.csv");

            for (Book book : books) {
                writer.write(book.toCSV() +"\n");
            }

            writer.close();
            System.out.println("Book saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBooksFromFile() {
        books.clear(); // prevent duplicates

        try {
            BufferedReader reader = new BufferedReader(new FileReader("books.csv"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                Book book = BookFactory.createBook(data);
                books.add(book);
            }

            reader.close();
            //System.out.println("Users loaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    public Book getBookById(String id) {
        for (Book b : books) {
            if (b.getBookId().equals(id)) {
                return b;
            } //else {
                //System.out.println("We couldn't find the book you're searching for");} zheng add// 2/4/2026
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


    public Book getBookByGenre(String genre) {
        for (Book b : books) {
            if (b.getBookGenre().equals(genre)) {
                return b;
            } else {
                System.out.println("We couldn't find the book you're searching for");
            }
        }
        return null;
    }

    public Book getBookByISBN(String isbn) {
        for (Book b : books) {
            if (b.getBookISBN().equals(isbn)) {
                return b;
            } else {
                System.out.println("We couldn't find the book you're searching for");
            }
        }
        return null;
    }

    public Book getBookBySection(String section) {
        for (Book b : books) {
            if (b.getBookSection().equals(section)) {
                return b;
            } else {
                System.out.println("We couldn't find the book you're searching for");
            }
        }
        return null;
    }

    public Book getBookByStatus(String status) {
        for (Book b : books) {
            if (b.getBookStatus().equals(status)) {
                return b;
            } else {
                System.out.println("We couldn't find the book you're searching for");
            }
        }
        return null;
    }

    public void updateBookReserved(Book b1) {
        for (Book b2 : books) {
            if (b1 == b2) {
                b1.changeStatustoReserved();
                saveBooksToFile();
                System.out.println("Book reserved successfully");
                return; // Exit the method
            }
        }
        System.out.println("This book doesn't exist");
    }

    public void printAllBooks() {
        //loadBooksFromFile(); zheng delete 2/4/2026
        if (books.isEmpty()) {
            System.out.println("No books in the catalog.");
            return;
        }  //zheng add 2/4/2026
        
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
        if(books.isEmpty()) {
            return "B001";
        }
        int maxId = 0;
        for (Book book : books) {
            String id = book.getBookId();
            int num = Integer.parseInt(id.substring(1)); // Extract numeric part
            if (num > maxId) {
                maxId = num;
            }
        }
        return String.format("B%03d", maxId + 1);
    }


    public void printBorrowCountBooks() {
        loadBooksFromFile();
        Random r = new Random();
        for (Book book : books) {
            int totalBorrowed = r.nextInt(20);

            System.out.printf("%-12s %-3d\n", book.getBookName(), totalBorrowed);
        }
    }
    public List<User> getAllUsers() {
        return users;
    }


}