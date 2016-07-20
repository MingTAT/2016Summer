package JVersion;
import java.util.Map;

public class Problem {
    final int numberOfCourses;
    final int maxNumberPerSem;
    final String[] requiredCourses;
    final Map<String, Course> courses;

    public Problem(
        int numberOfCourses,
        int maxNumberPerSem,
        String[] requiredCourses,
        Map<String, Course> courses) {
        this.numberOfCourses = numberOfCourses;
        this.maxNumberPerSem = maxNumberPerSem;
        this.requiredCourses = requiredCourses;
        this.courses = courses;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("numberOfCourses = " + numberOfCourses);
        s.append("\n");
        s.append("maxNumberPerSem = " + maxNumberPerSem);
        s.append("\n");
        s.append("requiredCourses = [");
        if (requiredCourses.length > 0) {
            s.append(requiredCourses[0]);
        }
        for (int i = 1; i < requiredCourses.length; i++) {
            s.append(", ");
            s.append(requiredCourses[i]);
        }
        s.append("]\n");
        s.append("courses:\n");
        for (String cid : requiredCourses) {
            s.append(courses.get(cid));
            s.append("\n");
        }
        return s.toString();
    }
}
