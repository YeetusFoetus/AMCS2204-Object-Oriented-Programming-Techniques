import java.util.Scanner;

public class MemberLogin {
    Scanner scanner = new Scanner(System.in);

    public void login1() {
        System.out.print("Enter password: ");
        
        int password1 = scanner.nextInt();

        if (password1 == 1234) {
            System.out.println("\n==================");
            System.out.println("Welcome, ____");
            System.out.println("==================");
            System.out.println("1. Catalog");
            System.out.println("2. Borrowed books");
            System.out.println("3. Study room booking");
            System.out.println("4. Pay fine");
        }
    }
}