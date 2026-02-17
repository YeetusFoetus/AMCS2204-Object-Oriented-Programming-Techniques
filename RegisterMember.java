import java.util.Scanner;

public class RegisterMember {
    Scanner scanner = new Scanner(System.in);
    LibraryDatabase db = new LibraryDatabase();
    MemberLogin login = new MemberLogin();

    public void register1() {
        System.out.print("Enter your ID: ");
        String userId = scanner.nextLine();

        System.out.print("\nEnter your name: ");
        String userName = scanner.nextLine();

        Member newMember = new Member(userId, userName);

        db.addMember(newMember);

        while (true) {
            System.out.println("Press 1 to return");

            int returnMemberLogin = scanner.nextInt();

            if (returnMemberLogin == 1) {
                login.login1();
            }

            else {
                System.out.println("Invalid. Please try again");
            }
        }
    }
}