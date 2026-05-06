import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee (Digitize Record)");
            System.out.println("2. Search Employee (Quick Retrieval <10s)");
            System.out.println("3. Update Department (Allocation)");
            System.out.println("4. Generate Attendance Report (Automated)");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("First Name: "); String fn = sc.nextLine();
                    System.out.print("Last Name: "); String ln = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Dept ID (1-HR, 2-Eng, 3-Sales): "); int dId = sc.nextInt();
                    dao.addEmployee(fn, ln, email, dId);
                    break;
                case 2:
                    System.out.print("Enter name to search: "); String name = sc.nextLine();
                    dao.searchEmployee(name);
                    break;
                case 3:
                    System.out.print("Employee ID: "); int eId = sc.nextInt();
                    System.out.print("New Dept ID: "); int nDId = sc.nextInt();
                    dao.updateEmployeeDepartment(eId, nDId);
                    break;
                case 4:
                    System.out.print("Enter Employee ID for report: "); int repId = sc.nextInt();
                    dao.generateAttendanceReport(repId);
                    break;
                case 5:
                    System.out.println("Exiting System...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}