import java.util.Scanner;

public class RegisterMember {
    Scanner scanner;
    LibraryDatabase db = new LibraryDatabase();
    MemberMenu login = new MemberMenu(scanner);

    public void register1() {
        System.out.print("Enter your ID: ");
        String userId = scanner.nextLine();

        System.out.print("\nEnter your name: ");
        String userName = scanner.nextLine();

        System.out.print("\nEnter your email: ");
        String userEmail = scanner.nextLine();

        System.out.print("\nEnter your password: ");
        String userPassword = scanner.nextLine();

        User newMember = new Member(userId, userName, userEmail, userPassword, 0);

        db.addUser(newMember);

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