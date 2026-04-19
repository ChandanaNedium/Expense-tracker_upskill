import java.util.*;

// Expense Class
class Expense {
    int id;
    String date, category, description;
    double amount;

    Expense(int id, String date, double amount, String category, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String toString() {
        return id + " | " + date + " | " + category + " | Rs." + amount + " | " + description;
    }
}

public class expensetracker {

    static Scanner sc = new Scanner(System.in);
    static List<Expense> expenses = new ArrayList<>();
    static Set<String> categories = new HashSet<>();
    static int idCounter = 1;

    public static void main(String[] args) {

        categories.add("Food");
        categories.add("Travel");
        categories.add("Shopping");

        while (true) {
            System.out.println("\n--- EXPENSE TRACKER ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Filter by Category");
            System.out.println("4. Total Expenses");
            System.out.println("5. Add Category");
            System.out.println("6. Edit Expense");
            System.out.println("7. Delete Expense");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: addExpense(); break;
                case 2: viewExpenses(); break;
                case 3: filterByCategory(); break;
                case 4: totalExpenses(); break;
                case 5: addCategory(); break;
                case 6: editExpense(); break;
                case 7: deleteExpense(); break;
                case 8: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add Expense
    static void addExpense() {
        sc.nextLine();

        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        if (!categories.contains(category)) {
            System.out.println("Category not found! Add it first.");
            return;
        }

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        expenses.add(new Expense(idCounter++, date, amount, category, desc));
        System.out.println("Expense Added Successfully!");
    }

    // View Expenses
    static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded!");
            return;
        }

        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    // Filter by Category
    static void filterByCategory() {
        sc.nextLine();
        System.out.print("Enter Category: ");
        String cat = sc.nextLine();

        boolean found = false;

        for (Expense e : expenses) {
            if (e.category.equalsIgnoreCase(cat)) {
                System.out.println(e);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found for this category.");
        }
    }

    // Total Expenses
    static void totalExpenses() {
        double total = 0;

        for (Expense e : expenses) {
            total += e.amount;
        }

        System.out.println("Total Expenses: ₹" + total);
    }

    // Add Category
    static void addCategory() {
        sc.nextLine();
        System.out.print("Enter New Category: ");
        String cat = sc.nextLine();

        categories.add(cat);
        System.out.println("Category Added!");
    }

    // Edit Expense
    static void editExpense() {
        System.out.print("Enter Expense ID: ");
        int id = sc.nextInt();

        boolean found = false;

        for (Expense e : expenses) {
            if (e.id == id) {
                sc.nextLine();
                System.out.print("New Amount: ");
                e.amount = sc.nextDouble();
                sc.nextLine();

                System.out.print("New Description: ");
                e.description = sc.nextLine();

                System.out.println("Expense Updated!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Expense not found!");
        }
    }

    // Delete Expense
    static void deleteExpense() {
        System.out.print("Enter Expense ID: ");
        int id = sc.nextInt();

        boolean removed = expenses.removeIf(e -> e.id == id);

        if (removed) {
            System.out.println("Expense Deleted!");
        } else {
            System.out.println("Expense not found!");
        }
    }
}
