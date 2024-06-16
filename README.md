# Expense-Tracker
The "Java Expense-Income Tracker" is a console-based financial management application. It allows users to track their monthly income and expenses efficiently. This program utilizes object-oriented principles and file I/O to provide a user-friendly interface for managing financial transactions.

Version 11.1 Release 6/15/2024

New Features and Enhancements:

Data Persistence with File I/O:

Introduced budget_data.txt to store income and expense transactions.
Implemented saveToFile and loadFromFile methods for saving and loading budget data.
Date Handling:

Utilized java.time.LocalDate to manage monthly budget dates.
Displayed the current date in the summary and used it for file operations.
Menu Display and Input Handling:

Created a user-friendly menu (1. Add Income, 2. Add Expense, 3. View Summary, 4. Exit).
Used Scanner for input fields like category, description, and amount.
Transaction Management:

Implemented Transaction class for transaction details (category, description, amount).
Managed transactions with MonthlyBudget class, calculating totals and balances.
Exception Handling:

Enhanced reliability with try-catch blocks for file operations, ensuring error messages are displayed when necessary.
This setup allows for efficient tracking of income and expenses, ensuring clarity and ease of use.
