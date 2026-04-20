public class Fine {
    private String reason;
    private double amount;

    // Default Constructor
    public Fine() {}

    // Parameterized Constructor
    public Fine(String reason, double amount) {
        this.reason = reason;
        this.amount = amount;
    }

    // Getters
    public String getReason() {
        return reason;
    }

    public double getAmount() {
        return amount;
    }

    // Setters
    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Reason = '" + reason + '\'' +
                ", amount = $" + String.format("%.2f", amount);
    }
}