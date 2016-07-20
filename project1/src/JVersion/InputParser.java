package JVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputParser {
    public static List<Problem> parse() {
        List<Problem> problems = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n != -1) {
            int maxNumberPerSem = in.nextInt();
            String[] requiredCourses = new String[n];
            for (int i = 0; i < n; i++) {
                requiredCourses[i] = in.next();
            }
            Map<String, Course> courses = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String id = in.next();
                String offering = in.next();
                int numberOfPrerequisites = in.nextInt();
                String[] prerequisites = new String[numberOfPrerequisites];
                for (int j = 0; j < numberOfPrerequisites; j++) {
                    prerequisites[j] = in.next();
                }
                Course course = new Course(id, offering, prerequisites);
                courses.put(id, course);
            }
            Problem problem = new Problem(n, maxNumberPerSem, requiredCourses, courses);
            n = in.nextInt();
            // System.out.println(problem + "\n\n===========================\n\n");
            problems.add(problem);
        }
        return problems;
    }
}
