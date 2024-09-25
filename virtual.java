import java.util.ArrayList;
import java.util.List;

class VirtualClassroom {
    private String name;
    private List<Student> students;
    private List<Assignment> assignments;

    public VirtualClassroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getId() + " has been enrolled in " + name + ".");
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
        System.out.println("Assignment for " + name + " has been scheduled.");
    }

    public void submitAssignment(String studentId, String assignmentDetails) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                student.submitAssignment(assignmentDetails);
                System.out.println("Assignment submitted by Student " + studentId + " in " + name + ".");
                return;
            }
        }
        System.out.println("Student " + studentId + " is not enrolled in " + name + ".");
    }
}

class Student {
    private String id;
    private List<String> submittedAssignments;

    public Student(String id) {
        this.id = id;
        this.submittedAssignments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void submitAssignment(String assignmentDetails) {
        submittedAssignments.add(assignmentDetails);
    }

    public List<String> getSubmittedAssignments() {
        return submittedAssignments;
    }
}
