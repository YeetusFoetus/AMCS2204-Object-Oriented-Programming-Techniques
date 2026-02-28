//import java.util.ArrayList;

public class Member extends User {

    public Member(String userId, String userName, String userPassword) {
        super(userId, userName, userPassword);
    }

    @Override
    // Inheritance(?)
    public String toCSV() {
        return "Member," + getUserId() + "," + getUserName() + "," + getUserPassword();
    }
}

    //Ignore this
    /*@Override
    public void mainMenu() {
        System.out.println("This is where I'd put the main menu for Admin, if I had one.");
    }*/

    /*private ArrayList<BorrowReturn> borrowedBooks;
    private ArrayList<StudyRoomBooking> roomBookings;
    private ArrayList<Fine> pendingFines;*/


