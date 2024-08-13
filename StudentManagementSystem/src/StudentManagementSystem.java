
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    private final ArrayList<Student> students;
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        if (student.getName().isEmpty() || student.getGrade().isEmpty() || student.getRollNumber() <= 0) {
            System.out.println("Invalid input. Please ensure all fields are filled correctly.");
            return;
        }
        students.add(student);
        saveStudentsToFile();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
        saveStudentsToFile();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void editStudent(int rollNumber, String newName, String newGrade) {
        if (newName.isEmpty() || newGrade.isEmpty()) {
            System.out.println("Invalid input. Please ensure all fields are filled correctly.");
            return;
        }
        Student student = searchStudent(rollNumber);
        if (student != null) {
            student.setName(newName);
            student.setGrade(newGrade);
            saveStudentsToFile();
        }
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int rollNumber = Integer.parseInt(parts[1]);
                    String grade = parts[2];
                    students.add(new Student(name, rollNumber, grade));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentManagementSystem sms = new StudentManagementSystem();
            while (true) {
                System.out.println("\nStudent Management System");
                System.out.println("1. Add Student");
                System.out.println("2. Remove Student");
                System.out.println("3. Search Student");
                System.out.println("4. Edit Student");
                System.out.println("5. Display All Students");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter name: ");
                        String name = scanner.next();
                        System.out.print("Enter roll number: ");
                        int rollNumber = scanner.nextInt();
                        System.out.print("Enter grade: ");
                        String grade = scanner.next();
                        sms.addStudent(new Student(name, rollNumber, grade));
                    }
                    case 2 -> {
                        System.out.print("Enter roll number to remove: ");
                        int rollNumber = scanner.nextInt();
                        sms.removeStudent(rollNumber);
                    }
                    case 3 -> {
                        System.out.print("Enter roll number to search: ");
                        int rollNumber = scanner.nextInt();
                        Student student = sms.searchStudent(rollNumber);
                        if (student != null) {
                            System.out.println("Student Found: " + student);
                        } else {
                            System.out.println("Student not found");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter roll number to edit: ");
                        int rollNumber = scanner.nextInt();
                        System.out.print("Enter new name: ");
                        String newName = scanner.next();
                        System.out.print("Enter new grade: ");
                        String newGrade = scanner.next();
                        sms.editStudent(rollNumber, newName, newGrade);
                    }
                    case 5 -> sms.displayAllStudents();
                    case 6 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        }
    }
}
