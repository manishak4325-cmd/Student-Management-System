import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private static StudentDAO studentDAO = new StudentDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Find Student by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        viewAllStudents();
                        break;
                    case 5:
                        findStudentById();
                        break;
                    case 6:
                        System.out.println("Thank you for using Student Management System!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void addStudent() throws SQLException {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter course: ");
        String course = scanner.nextLine();
        
        System.out.print("Enter marks: ");
        double marks = scanner.nextDouble();

        Student student = new Student(0, name, course, marks);
        studentDAO.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter new name (press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter new course (press Enter to keep current): ");
        String course = scanner.nextLine();
        if (!course.trim().isEmpty()) {
            student.setCourse(course);
        }

        System.out.print("Enter new marks (enter -1 to keep current): ");
        double marks = scanner.nextDouble();
        if (marks != -1) {
            student.setMarks(marks);
        }

        studentDAO.updateStudent(student);
        System.out.println("Student updated successfully!");
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        studentDAO.deleteStudent(id);
        System.out.println("Student deleted successfully!");
    }

    private static void viewAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\nAll Students:");
        System.out.println("ID\tName\tCourse\tMarks");
        for (Student student : students) {
            System.out.printf("%d\t%s\t%s\t%.2f%n", 
                student.getId(), 
                student.getName(), 
                student.getCourse(), 
                student.getMarks());
        }
    }

    private static void findStudentById() throws SQLException {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\nStudent Details:");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Course: " + student.getCourse());
        System.out.println("Marks: " + student.getMarks());
    }
}