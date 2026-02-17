import java.util.ArrayList;

public class Member {
    private String memberId;    
    private String name;
    private ArrayList<BorrowReturn> borrowedBooks;
    //private ArrayList<StudyRoomBooking> roomBookings;
    private ArrayList<Fine> pendingFines;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
        //this.roomBookings = new ArrayList<>();
        this.pendingFines = new ArrayList<>();
    }

    // Getters and Adders
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public ArrayList<BorrowReturn> getBorrowedBooks() { return borrowedBooks; }
    public void borrowBook(BorrowReturn borrow) { borrowedBooks.add(borrow); }
    public void addFine(Fine fine) { pendingFines.add(fine); }
}
