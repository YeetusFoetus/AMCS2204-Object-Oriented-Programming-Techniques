public class UserFactory {
    public static User createUser(String[] data) {
        String type = data[0];

        switch (type) {
            case "Member":
                return new Member(data[1], data[2], data[3], data[4]); 
                
            case "Admin":
                return new Admin(data[1], data[2], "admin@gmail.com", data[3]); 

            default:
                return null;
        }
    }
}