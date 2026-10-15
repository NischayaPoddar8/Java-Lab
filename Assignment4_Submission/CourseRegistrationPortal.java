import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseRegistrationPortal {
    private final Map<String, Course> courses = new HashMap<>();
    private final Map<String, Set<String>> completedCourses = new HashMap<>();

    public void addCourse(String courseCode, int capacity, List<String> prerequisites) {
        if (courseCode == null || courseCode.isBlank() || capacity < 1) {
            throw new IllegalArgumentException("Course code and positive capacity are required.");
        }
        if (courses.containsKey(courseCode)) {
            throw new IllegalArgumentException("Course already exists: " + courseCode);
        }
        courses.put(courseCode, new Course(courseCode, capacity, prerequisites));
    }

    public void markCourseCompleted(String studentId, String courseCode) {
        if (studentId == null || studentId.isBlank() || !courses.containsKey(courseCode)) {
            throw new IllegalArgumentException("Valid student ID and course code are required.");
        }
        completedCourses.computeIfAbsent(studentId, key -> new HashSet<>()).add(courseCode);
    }

    public String registerStudent(String studentId, String courseCode) {
        if (studentId == null || studentId.isBlank()) {
            return "Invalid student ID.";
        }

        Course course = courses.get(courseCode);
        if (course == null) {
            return "Course not found.";
        }

        if (course.enrolledStudentIds.contains(studentId)) {
            return "Student is already enrolled.";
        }

        if (course.waitlist.contains(studentId)) {
            return "Student is already on the waitlist.";
        }

        Set<String> completed = completedCourses.getOrDefault(studentId, new HashSet<>());
        if (!completed.containsAll(course.prerequisiteCodes)) {
            return "Prerequisites not completed.";
        }

        if (course.enrolledStudentIds.size() < course.capacity) {
            course.enrolledStudentIds.add(studentId);
            return "Registration successful.";
        }

        course.waitlist.addLast(studentId);
        return "Course full. Student added to waitlist.";
    }

    public String dropCourse(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            return "Course not found.";
        }

        if (!course.enrolledStudentIds.remove(studentId)) {
            if (course.waitlist.remove(studentId)) {
                return "Student removed from waitlist.";
            }
            return "Student was not enrolled.";
        }

        promoteNextStudent(course);
        return "Student dropped from course.";
    }

    private void promoteNextStudent(Course course) {
        while (!course.waitlist.isEmpty()) {
            String nextStudent = course.waitlist.removeFirst();
            Set<String> completed = completedCourses.getOrDefault(nextStudent, new HashSet<>());
            if (completed.containsAll(course.prerequisiteCodes)) {
                course.enrolledStudentIds.add(nextStudent);
                break;
            }
        }
    }

    public void displayCourse(String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        System.out.println("Course: " + course.courseCode);
        System.out.println("Capacity: " + course.capacity);
        System.out.println("Enrolled students: " + course.enrolledStudentIds);
        System.out.println("Prerequisites: " + course.prerequisiteCodes);
        System.out.println("Waitlist: " + course.waitlist);
    }

    private static class Course {
        private final String courseCode;
        private final int capacity;
        private final HashSet<String> enrolledStudentIds = new HashSet<>();
        private final ArrayList<String> prerequisiteCodes;
        private final LinkedList<String> waitlist = new LinkedList<>();

        private Course(String courseCode, int capacity, List<String> prerequisites) {
            this.courseCode = courseCode;
            this.capacity = capacity;
            this.prerequisiteCodes = new ArrayList<>(prerequisites);
        }
    }

    public static void main(String[] args) {
        CourseRegistrationPortal portal = new CourseRegistrationPortal();
        portal.addCourse("CS101", 2, List.of());
        portal.addCourse("CS201", 1, List.of("CS101"));

        portal.markCourseCompleted("S100", "CS101");
        portal.markCourseCompleted("S200", "CS101");
        portal.markCourseCompleted("S300", "CS101");

        System.out.println(portal.registerStudent("S100", "CS201"));
        System.out.println(portal.registerStudent("S200", "CS201"));
        portal.displayCourse("CS201");
        System.out.println(portal.dropCourse("S100", "CS201"));
        portal.displayCourse("CS201");
    }
}
