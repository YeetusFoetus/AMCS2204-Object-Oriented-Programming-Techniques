import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanManager {
    private List<Loan> loans = new ArrayList<>();

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void saveLoansToFile() {
        try {
            FileWriter writer = new FileWriter("loans.csv");

            for (Loan loan : loans) {
                writer.write(loan.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    public void loadLoansFromFile() {
        loans.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("loans.csv"));

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip blank lines
                
                String[] data = line.split(",");
                if (data.length < 4) continue; // Safety check for malformed rows
                
                Loan loan = createLoan(data);
                loans.add(loan);
            }

            reader.close();

        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public static Loan createLoan(String[] data) {
        String uid = data[0];
        String bid = data[1];
        
        LocalDate borrow = LocalDate.parse(data[2]); 
        LocalDate due = LocalDate.parse(data[3]);

        return new Loan(uid, bid, borrow, due);
    }

    public String printAllLoans() {
        loadLoansFromFile();
        
        // Use StringBuilder for efficiency when concatenating in a loop
        StringBuilder report = new StringBuilder();

        for (Loan loan : loans) {
            // 1. Call toString() ON the loan object
            // 2. Add a newline (\n) so they don't all appear on one line
            report.append(loan.toString()).append("\n");
        }

        // Return the full string instead of null
        return report.toString();
    }
}