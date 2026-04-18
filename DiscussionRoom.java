public class DiscussionRoom {
    private String roomID;
    private int floor;
    private int capacity;
    private boolean status; 
    private int bookingCount;

    public DiscussionRoom(String roomID, int floor, int capacity, boolean status, int bookingCount) {
        this.roomID = roomID;
        this.floor = floor;
        this.capacity = capacity;
        this.status = status;
        this.bookingCount = bookingCount;
    }

    public String getRoomID() { return roomID; }
    public int getFloor() { return floor; }
    public int getCapacity() { return capacity; }
    public Boolean getStatus() { return status; }
    public int getBookingCount() { return bookingCount; }
    public void incrementBookingCount() { bookingCount++;}

    public void setRoomID(String roomID) { this.roomID = roomID; }
    public void setFloor(int floor) { this.floor = floor; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setStatus(boolean status) { this.status = status; }
    public void setBookingCount(int bookingCount) { this.bookingCount = bookingCount; }
}