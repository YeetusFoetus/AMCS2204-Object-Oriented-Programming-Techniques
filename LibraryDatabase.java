import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    private List<Member> members;

    public LibraryDatabase() {
        members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Member getMemberById(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public Member getMemberByName(String name) {
        for (Member m : members) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    public void printAllMembers() {
        for (Member m : members) {
            System.out.println(m);
        }
    }
}