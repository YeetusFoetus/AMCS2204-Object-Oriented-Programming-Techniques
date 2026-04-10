public class UserFactory {
    private UserFactory() {};
    
    public static User createUser(String[] data) {

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