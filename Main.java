
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
static void saveStudents(ArrayList<Student> students) {
    try (PrintWriter writer = new PrintWriter("students.txt")) {
        for (Student s : students) {
            writer.println(s.id + "," + s.name + "," + s.marks);
        }
        System.out.println("Student data saved successfully!");
    } catch (IOException e) {
        System.out.println("Error saving student data.");
    }
}

static void loadStudents(ArrayList<Student> students) {
    File file = new File("students.txt");

    if (!file.exists()) {
        return;
    }

    try (Scanner reader = new Scanner(file)) {
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] data = line.split(",", 3);

            if (data.length == 3) {
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double marks = Double.parseDouble(data[2]);

                students.add(new Student(id, name, marks));
            }
        }
        System.out.println("Student data loaded successfully!");
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error reading student data.");
    }
}


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
ArrayList<Student> students = new ArrayList<>();

loadStudents(students);

int choice = 0;
        while (choice != 5) {

            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    boolean exists = false;

                    for (Student s : students) {
                        if (s.id == id) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Student ID already exists!");
                        break;
                    }

                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter student marks: ");
                    double marks = sc.nextDouble();
                    sc.nextLine();

                    Student student = new Student(id, name, marks);
                    students.add(student);
                    saveStudents(students);

                    System.out.println("Student added successfully!");
                    break;

            case 2:
    System.out.println("VIEW OPTION WORKS!");

    System.out.println("Number of students: " + students.size());

    for (Student s : students) {
        s.displayStudent();
    }

    System.out.println("View operation finished.");
    break;
               

                case 3:
                    System.out.print("Enter student ID to search: ");
                    int searchId = sc.nextInt();
                    sc.nextLine();

                    boolean found = false;

                    for (Student s : students) {
                        if (s.id == searchId) {
                            System.out.println("\nStudent Found:");
                            s.displayStudent();
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    boolean deleted = false;

                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).id == deleteId) {
                            students.remove(i);
                            saveStudents(students);
                            deleted = true;
                            System.out.println(
                                "Student deleted successfully!"
                            );
                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
