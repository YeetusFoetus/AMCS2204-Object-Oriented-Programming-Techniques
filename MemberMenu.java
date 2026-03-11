import java.util.Scanner;

public class MemberMenu {
    Scanner scanner;
    LibraryDatabase db = new LibraryDatabase();

    public MemberMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void login1() {
        db.addUser(new Member("A123", "Alice", "alice123"));

        db.addBook(
            new Book
            (
                "B123", 
                "Moby Dick", 
                "Fantasy", 
                "98579275",
                "Fiction",
                "Available"
            )
        );

        System.out.print("Enter password(ID): ");
        Catalog catalog = new Catalog();
        
        String password = scanner.next();

        User member = db.getUserById(password);

        if (member != null) {
            System.out.println("\n==================");
            System.out.println("Welcome " + member.getUserName());
            System.out.println("==================");
            System.out.println("1. Catalog");
            System.out.println("2. Borrowed books");
            System.out.println("3. Study room booking");
            System.out.println("4. Pay fine");
            System.out.println("5. Return");

            int choice = scanner.nextInt();

            switch (choice) { 
                case 1:
                    catalog.catalogScreen(db);
                    break;
                case 2:
                    System.out.println("Sup");
                case 3:
                    System.out.println("Sup");
                case 4:
                    System.out.println("Sup");
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
        } 
        
        else if (member == null) {
            System.out.println("Invalid login");
        }
    }
}