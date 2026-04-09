public class DiscussionRoom {
    private String roomID;
    private int floor;
    private int capacity;
    private boolean status; 

    public DiscussionRoom(String roomID, int floor, int capacity, boolean status) {
        this.roomID = roomID;
        this.floor = floor;
        this.capacity = capacity;
        this.status = status;
    }

    public String getRoomID() { return roomID; }
    public int getFloor() { return floor; }
    public int getCapacity() { return capacity; }
    public Boolean getStatus() { return status; }

    public void setRoomID(String roomID) { this.roomID = roomID; }
    public void setFloor(int floor) { this.floor = floor; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setStatus(boolean status) { this.status = status; }
}