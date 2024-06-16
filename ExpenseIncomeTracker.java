//Programmer: Manjeet Singh

// This program calculate the income and expenses per month.

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseIncomeTracker {

    private static final String DATA_FILE = "budget_data.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.println("Welcome, " + userName + "!");

        MonthlyBudget budget = new MonthlyBudget(LocalDate.now()); // Initialize with current date

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline characters

            switch (choice) {
                case 1:
                    addIncome(budget);
                    break;
                case 2:
                    addExpense(budget);
                    break;
                case 3:
                    viewSummary(budget);
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    saveToFile(budget, DATA_FILE);
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n----- Expense Income Tracker -----");
        System.out.println("1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View Summary");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addIncome(MonthlyBudget budget) {
        System.out.print("Enter Income Category: ");
        String incomeCategory = scanner.nextLine();
        System.out.print("Enter Income Description: ");
        String incomeDescription = scanner.nextLine();
        System.out.print("Enter Income Amount: $");
        double incomeAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        budget.addIncome(incomeCategory, incomeDescription, incomeAmount);
    }

    private static void addExpense(MonthlyBudget budget) {
        System.out.print("Enter Expense Category: ");
        String expenseCategory = scanner.nextLine();
        System.out.print("Enter Expense Description: ");
        String expenseDescription = scanner.nextLine();
        System.out.print("Enter Expense Amount: $");
        double expenseAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        budget.addExpense(expenseCategory, expenseDescription, expenseAmount);
    }

    private static void viewSummary(MonthlyBudget budget) {
        System.out.println("----- Monthly Budget Summary (" + budget.getDate() + ")-----");
        System.out.println("Total Income: $" + budget.calculateTotalIncome());
        System.out.println("Total Expenses: $" + budget.calculateTotalExpense());
        System.out.println("Balance: $" + budget.calculateBalance());
    }

    private static void saveToFile(MonthlyBudget budget, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Transaction income : budget.getIncomeList()) {
                writer.write("INCOME," + income.getCategory() + "," + income.getDescription() + "," + income.getAmount());
                writer.newLine();
            }
            for (Transaction expense : budget.getExpenseList()) {
                writer.write("EXPENSE," + expense.getCategory() + "," + expense.getDescription() + "," + expense.getAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving data to the file: " + e.getMessage());
        }
    }
}

class MonthlyBudget {

    private final LocalDate date;
    private final List<Transaction> incomeList = new ArrayList<>();
    private final List<Transaction> expenseList = new ArrayList<>();

    public MonthlyBudget(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addIncome(String category, String description, double amount) {
        this.incomeList.add(new Transaction(category, description, amount));
    }

    public void addExpense(String category, String description, double amount) {
        this.expenseList.add(new Transaction(category, description, amount));
    }

    public List<Transaction> getIncomeList() {
        return incomeList;
    }

    public List<Transaction> getExpenseList() {
        return expenseList;
    }

    public double calculateTotalIncome() {
        return incomeList.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double calculateTotalExpense() {
        return expenseList.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double calculateBalance() {
        return calculateTotalIncome() - calculateTotalExpense();
    }
}

class Transaction {

    private final String category;
    private final String description;
    private final double amount;

    public Transaction(String category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
