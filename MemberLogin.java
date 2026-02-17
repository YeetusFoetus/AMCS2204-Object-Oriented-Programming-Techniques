import java.util.Scanner;

public class MemberLogin {
    Scanner scanner = new Scanner(System.in);
    LibraryDatabase db = new LibraryDatabase();

    public void login1() {
        db.addMember(new Member("A123", "Alice"));
        System.out.print("Enter password(ID): ");
        
        String password = scanner.next();

        Member member = db.getMemberById(password);

        if (member != null) {
            System.out.println("\n==================");
            System.out.println("Welcome " + member.getName());
            System.out.println("==================");
            System.out.println("1. Catalog");
            System.out.println("2. Borrowed books");
            System.out.println("3. Study room booking");
            System.out.println("4. Pay fine");
        } 
        
        else if (member == null) {
            System.out.println("Invalid login");
        }
    }
}