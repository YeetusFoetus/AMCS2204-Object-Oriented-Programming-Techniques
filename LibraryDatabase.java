import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class LibraryDatabase {
    // Fields
    //private List<Member> members;
    //private List<Book> books;
    private List<User> users; 
    
    LibraryDatabase() {
        //sbooks = new ArrayList<>();
        users = new ArrayList<>();
    }


    // -------USERS--------
    // File read write
    public void saveUsersToFile() {
        try (FileWriter writer = new FileWriter("users.csv")){

            for (User user : users) {
                writer.write(user.toCSV());
                writer.write("\n");
            }

            writer.close();
            ConsoleUtils.printCentered("User saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsersFromFile() {
        users.clear(); // prevent duplicates

        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))){
            String line;

            while ((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim(); // Remove leading/trailing whitespace
                }

                User user = UserFactory.createUser(data);
                if(user != null) {
                    users.add(user);
                } else {
                    System.out.println("Invalid user data: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Other methods
    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(String id) {
        for (User u : users) {
            if (u.getUserId().equals(id)) {
                return u;
            }
        }
        return null;  // not found
    }

    public User getUserByName(String name) {
        for (User u : users) {
            if (u.getUserName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public void getLastUser() {
        if (!users.isEmpty()) {

            User lastUser = users.get(users.size() - 1);

            System.out.println();
            ConsoleUtils.printCentered("╔══════════════════════════════════════════╗");
            ConsoleUtils.printCentered("║           NEW MEMBER REGISTERED          ║");
            ConsoleUtils.printCentered("╠══════════════════════════════════════════╣");
            ConsoleUtils.printCentered("║                                          ║");

            // %-40s pads the string with spaces on the right up to 40 characters
            String idLine = String.format("║  %-40s║", "ID: " + lastUser.getUserId());
            String nameLine = String.format("║  %-40s║", "Name: " + lastUser.getUserName());
            String passLine = String.format("║  %-40s║", "Password: " + lastUser.getUserPassword());

            ConsoleUtils.printCentered(idLine);
            ConsoleUtils.printCentered(nameLine);
            ConsoleUtils.printCentered(passLine);

            ConsoleUtils.printCentered("║                                          ║");
            ConsoleUtils.printCentered("╚══════════════════════════════════════════╝");
            System.out.println();
        } else {
            System.out.println();
            ConsoleUtils.printCentered("No users currently in the database.");
            System.out.println();
        }
    }

    public List<User> getAllUsers() {
        return users;
    }
}