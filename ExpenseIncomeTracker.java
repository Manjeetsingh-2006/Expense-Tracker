//Programmer: Manjeet Singh

// This program calculate the income and expenses per month.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String description;
    private double amount;

    public Transaction(String description, double amount) 
    {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    public double getAmount() 
    {
        return amount;
    }
}

class MonthlyBudget
 {
    private List<Transaction> incomeList;
    private List<Transaction> expenseList;

    public MonthlyBudget() 
    {
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
    }

    public void addIncome(String description, double amount) 
    {
        incomeList.add(new Transaction(description, amount));
    }

    public void addExpense(String description, double amount) 
    {
        expenseList.add(new Transaction(description, amount));
    }

    public double calculateTotalIncome() 
    {
        double totalIncome = 0;
        for (Transaction transaction : incomeList)
        {
            totalIncome += transaction.getAmount();
        }
        return totalIncome;
    }

    public double calculateTotalExpense() 
    {
        double totalExpense = 0;
        for (Transaction transaction : expenseList)
        {
            totalExpense += transaction.getAmount();
        }
        return totalExpense;
    }

    public double calculateBalance()
    {
        return calculateTotalIncome() - calculateTotalExpense();
    }

    public void displaySummary() 
    {
        System.out.println("----- Monthly Budget Summary -----");
        System.out.println("Total Income: $" + calculateTotalIncome());
        System.out.println("Total Expenses: $" + calculateTotalExpense());
        System.out.println("Balance: $" + calculateBalance());
    }

    // Save income and expense data to a text file
    public void saveData(String fileName) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) 
        {
            for (Transaction incomeTransaction : incomeList) {
                writer.println("Income," + incomeTransaction.getDescription() + "," + incomeTransaction.getAmount());
            }
            for (Transaction expenseTransaction : expenseList) {
                writer.println("Expense," + expenseTransaction.getDescription() + "," + expenseTransaction.getAmount());
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to " + fileName + ": " + e.getMessage());
        }
    }

    // Load income and expense data from a text file
    public void loadData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0];
                    String description = parts[1];
                    double amount = Double.parseDouble(parts[2]);

                    if ("Income".equals(type)) {
                        addIncome(description, amount);
                    } else if ("Expense".equals(type)) {
                        addExpense(description, amount);
                    }
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading data from " + fileName + ": " + e.getMessage());
        }
    }
}

public class ExpenseIncomeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyBudget budget = new MonthlyBudget();

        // Load data from a file (if it exists)
        String fileName = "budget_data.txt";
        budget.loadData(fileName);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Summary");
            System.out.println("4. Save and Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Income Description: ");
                    String incomeDescription = scanner.nextLine();
                    System.out.print("Enter Income Amount: $");
                    double incomeAmount = scanner.nextDouble();
                    budget.addIncome(incomeDescription, incomeAmount);
                    break;
                case 2:
                    System.out.print("Enter Expense Description: ");
                    String expenseDescription = scanner.nextLine();
                    System.out.print("Enter Expense Amount: $");
                    double expenseAmount = scanner.nextDouble();
                    budget.addExpense(expenseDescription, expenseAmount);
                    break;
                case 3:
                    budget.displaySummary();
                    break;
                case 4:
                    budget.saveData(fileName);
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}
