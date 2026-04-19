import java.util.Arrays;

public class UserFactory {
    public static User createUser(String[] data) {
        if (data.length < 5) { 
            // Log the error or skip the line so the whole program doesn't crash
            System.err.println("Skipping malformed line: " + Arrays.toString(data));
            return null; 
        }
        
        String type = data[0];

        switch (type) {
            case "Member":
                return new Member(data[1], data[2], data[3], data[4], Integer.parseInt(data[5])); 
                
            case "Admin":
                return new Admin(data[1], data[2], data[3], data[4], Integer.parseInt(data[5])); 

            default:
                return null;
        }
    }
}