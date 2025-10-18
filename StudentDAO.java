import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Add a new student
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, course, marks) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getCourse());
            pstmt.setDouble(3, student.getMarks());
            
            pstmt.executeUpdate();
        }
    }

    // Update student information
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, course = ?, marks = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getCourse());
            pstmt.setDouble(3, student.getMarks());
            pstmt.setInt(4, student.getId());
            
            pstmt.executeUpdate();
        }
    }

    // Delete a student
    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Get all students
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));
                students.add(student);
            }
        }
        return students;
    }

    // Get student by ID
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));
                return student;
            }
        }
        return null;
    }
}