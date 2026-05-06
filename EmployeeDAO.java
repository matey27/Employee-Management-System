import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    // Create: Add new Employee
    public void addEmployee(String fname, String lname, String email, int deptId) {
        String query = "INSERT INTO employees (first_name, last_name, email, dept_id, hire_date) VALUES (?, ?, ?, ?, CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);
            pstmt.setInt(4, deptId);
            pstmt.executeUpdate();
            System.out.println("Employee record digitized successfully.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Read: Quick Search functionality
    public void searchEmployee(String name) {
        String query = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("emp_id") + " | " + rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void generateAttendanceReport(int empId) {
    String query = "SELECT COUNT(*) as total_present FROM attendance WHERE emp_id = ? AND status = 'Present'";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, empId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("Employee ID " + empId + " was present for " + rs.getInt("total_present") + " days this month.");
        }
    } catch (Exception e) { e.printStackTrace(); }
}
    // Update: Department Allocation logic
public void updateEmployeeDepartment(int empId, int newDeptId) {
    String query = "UPDATE employees SET dept_id = ? WHERE emp_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, newDeptId);
        pstmt.setInt(2, empId);
        int rows = pstmt.executeUpdate();
        if (rows > 0) System.out.println("Department allocation updated successfully.");
    } catch (Exception e) { e.printStackTrace(); }
}

// Delete: Remove an employee record
public void deleteEmployee(int empId) {
    String query = "DELETE FROM employees WHERE emp_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, empId);
        pstmt.executeUpdate();
        System.out.println("Record removed from the centralized system.");
    } catch (Exception e) { e.printStackTrace(); }
}
}