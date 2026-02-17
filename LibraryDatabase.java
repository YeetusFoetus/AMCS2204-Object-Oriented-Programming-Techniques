import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    private List<Member> members;
    private List<Book> books;

    public LibraryDatabase() {
        members = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Member getMemberById(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public Member getMemberByName(String name) {
        for (Member m : members) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    public void printAllMembers() {
        for (Member m : members) {
            System.out.println(m);
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printAllBooks() {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            System.out.printf("%-3d %-5s %-50s\n", 
                i + 1,
                book.getBookId(),
                book.getBookName()
            );
        }
    }
}