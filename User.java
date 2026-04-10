import java.util.Scanner;

abstract class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;

    LibraryDatabase db; //= new LibraryDatabase();
    Scanner scanner; //= new Scanner(System.in); zheng delete 9/4/2026

    public abstract String toCSV();

    //Constructor
    public User(String userId, String userName, String userEmail,String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    //Getter
    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getUserEmail() { return userEmail; }
    public String getUserPassword() { return userPassword; }

    //Main Menu
    public void mainMenu() {
        LibraryDatabase db = new LibraryDatabase();

        System.out.print("Enter password(ID): ");
        Catalog catalog = new Catalog(scanner);
        
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

            int choice = scanner.nextInt();

            switch (choice) { 
                case 1:
                    catalog.catalogScreen(db);
                    break;
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