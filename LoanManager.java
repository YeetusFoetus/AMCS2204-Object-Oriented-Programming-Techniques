import java.io.BufferedReader;
import java.io.File;
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
                writer.write(loan.getUserId() + "," + loan.getBookId() + "," + 
                       loan.getBorrowedDate() + "," + loan.getDueDate() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }  
    
    public void loadLoansFromFile() {
        loans.clear();
        File file = new File("loans.csv");
        
        // DEBUG LINE 1: Check if the file even exists
        //System.out.println("Looking for file at: " + file.getAbsolutePath());
        //System.out.println("Does file exist? " + file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip blank lines

                String[] data = line.split(",");

                if (data.length < 4) continue; // Safety check for malformed rows

                Loan loan = createLoan(data);

                loans.add(loan);
                count++;
            }
            // DEBUG LINE 2: See how many lines were actually read
            //System.out.println("Lines read from file: " + count);
            } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
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

        if (loans.isEmpty()) {
            return "No loan records found in the system.";
        }
        
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