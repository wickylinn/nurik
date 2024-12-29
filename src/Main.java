import java.util.*;

class Person {
    String name;
    String surname;
    int age;
    boolean gender;

    public Person(String name, String surname, int age, boolean gender) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }

    public String toString() {
        String genderStr = gender ? "Male" : "Female";
        return "Hi, I am " + name + " " + surname + ", a " + age + "-year-old " + genderStr + ".";
    }
}

class Student extends Person {
    int studentID;
    ArrayList<Integer> grades = new ArrayList<>();

    public Student(String name, String surname, int age, boolean gender, int studentID) {
        super(name, surname, age, gender);
        this.studentID = studentID;
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double calculateGPA() {
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return grades.size() > 0 ? (double) total / grades.size() : 0.0;
    }

    public String toString() {
        return super.toString() + " I am a student with ID " + studentID + ".";
    }
}

class Teacher extends Person {
    String subject;
    int yearsOfExperience;
    int salary;

    public Teacher(String name, String surname, int age, boolean gender, String subject, int yearsOfExperience, int salary) {
        super(name, surname, age, gender);
        this.subject = subject;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
    }

    public void giveRaise(double percent) {
        salary += salary * percent / 100;
    }

    public String toString() {
        return super.toString() + " I teach " + subject + ".";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> members = new ArrayList<>();

        System.out.println("Enter student data (name surname age gender ID grades...) or 'done':");
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("done")) break;
            String[] parts = line.split(" ");
            String name = parts[0];
            String surname = parts[1];
            int age = Integer.parseInt(parts[2]);
            boolean gender = parts[3].equalsIgnoreCase("Male");
            int studentID = Integer.parseInt(parts[4]);
            Student student = new Student(name, surname, age, gender, studentID);
            for (int i = 5; i < parts.length; i++) {
                student.addGrade(Integer.parseInt(parts[i]));
            }
            members.add(student);
        }

        System.out.println("Enter teacher data (name surname age gender subject experience salary) or 'done':");
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("done")) break;
            String[] parts = line.split(" ");
            String name = parts[0];
            String surname = parts[1];
            int age = Integer.parseInt(parts[2]);
            boolean gender = parts[3].equalsIgnoreCase("Male");
            String subject = parts[4];
            int experience = Integer.parseInt(parts[5]);
            int salary = Integer.parseInt(parts[6]);
            Teacher teacher = new Teacher(name, surname, age, gender, subject, experience, salary);
            if (experience > 10) teacher.giveRaise(10);
            members.add(teacher);
        }

        for (Person member : members) {
            System.out.println(member);
            if (member instanceof Student) {
                System.out.println("GPA: " + ((Student) member).calculateGPA());
            }
        }

        scanner.close();
    }
}
