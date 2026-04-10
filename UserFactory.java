import java.util.Arrays;

public class UserFactory {
    private UserFactory() {};
    
    public static User createUser(String[] data) {
        if (data.length < 5) { 
            // Log the error or skip the line so the whole program doesn't crash
            System.err.println("Skipping malformed line: " + Arrays.toString(data));
            return null; 
        }

        String type = data[0];
        String id = data[1];
        String name = data[2];
        String email = data[3];
        String password = data[4];

        switch (type) {
            case "Member":
                return new Member(id, name, email, password);
            case"Admin":
                return new Admin(id, name, email, password);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}