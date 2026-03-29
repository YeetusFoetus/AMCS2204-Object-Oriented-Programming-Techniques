public class UserFactory {
    private UserFactory() {};
    
    public static User createUser(String[] data) {

        String type = data[0];
        String id = data[1];
        String name = data[2];
        String password = data[3];

        switch (type) {
            case "Member":
                return new Member(id, name, password);
            /*case"Admin":
                return new Admin(id, name, password);*/
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}