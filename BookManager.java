import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookManager {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void saveBooksToFile() {
        try {
            FileWriter writer = new FileWriter("books.csv");

            for (Book book : books) {
                writer.write(book.toCSV() +"\n");
            }

            writer.close();
            //System.out.println("Book saved successfully."); zheng delete 16/4/2026

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
            } 
        }
        return null;
    }

    public void removeBook(Book book){
        if (book != null) {
            books.remove(book);
        }
    }

    /*public Book updateBookOnLoan(Book b1) {
        for (Book b2 : books) {
            if (b1 == b2) {
                b1.changeStatusToLoan();
            }
            else {
                System.out.println("This book doesn't exist");
            }
        }
    }*/

    public Book getBookByGenre(String genre) {
        for (Book b : books) {
            if (b.getBookGenre().equals(genre)) {
                return b;
            }
        }
        return null;
    }

    public Book getBookByISBN(String isbn) {
        for (Book b : books) {
            if (b.getBookISBN().equals(isbn)) {
                return b;
            } 
        }
        return null;
    }

    public Book getBookBySection(String section) {
        for (Book b : books) {
            if (b.getBookSection().equals(section)) {
                return b;
            } 
        }
        return null;
    }

    public Book getBookByStatus(String status) {
        for (Book b : books) {
            if (b.getBookStatus().equals(status)) {
                return b;
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

    public void updateBookAvailable(Book b1) {
        for (Book b2 : books) {
            if (b1 == b2) {
                b1.changeStatustoAvailable();
                saveBooksToFile();
                System.out.println("Reservation unset successfully");
                return; // Exit the method
            }
        }
        System.out.println("This book doesn't exist");
    }

    public void printAllBooks() {
        loadBooksFromFile(); 
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
        LibraryDatabase ld = new LibraryDatabase();
        ld.loadUsersFromFile();
        List<User> BorrowUser = ld.getAllUsers();

        for (User user : BorrowUser) {
            if(user.getBorrowCounter() > 0){
                System.out.printf("%-18s %-8d\n", user.getUserName(), user.getBorrowCounter());
            }
        }
    }
    

    public void printBookDetails(Book book) {
        System.out.printf("%-5s %-15s %-10s %-10s %-10s %-13s\n", 
                book.getBookId(),
                book.getBookName(),
                book.getBookGenre(),
                book.getBookISBN(),
                book.getBookSection(),
                book.getBookStatus()
        );
    }

    //(WIP)
    public List<Book> printAllBooksByGenre(String genre) {
        List<Book> foundGenre = new ArrayList<>();

        for (Book b : books) {
            if (b.getBookGenre().equals(genre)) {
                foundGenre.add(b);

                printBookDetails(b);
            }
        }

        if (foundGenre.isEmpty()) {
            System.out.println("Genre not found.");
        }

        return foundGenre;
    }   
    
    public List<Book> printAllBooksBySection(String section) {
        List<Book> foundSection = new ArrayList<>();

        for (Book b : books) {
            if (b.getBookSection().equals(section)) {
                foundSection.add(b);

                printBookDetails(b);
            }
        }

        if (foundSection.isEmpty()) {
            System.out.println("Section not found.");
        }

        return foundSection;
    }   


}