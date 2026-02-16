import java.util.Scanner;

public class RegisterMember {
    Scanner scanner = new Scanner(System.in);

    public Member register1() {
        System.out.print("Enter your ID: ");
        String userId = scanner.nextLine();

        System.out.print("\nEnter your name: ");
        String userName = scanner.nextLine();

        Member newMember = new Member(userId, userName);

        return newMember;
    }
}