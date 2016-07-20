package JVersion;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Solution {
    public static void solve(Problem problem) {
        int minNumOfSemesters = computeNumberOfSemesters(problem);
        System.out.println("The minimum number of semsters required to graduate is " + minNumOfSemesters);
    }

    //=================================================================================

    public static int computeNumberOfSemesters(Problem problem) {
        Set<String> coursesTaken = new HashSet<>();
        int semesterCounter = 0; // odd = fall;
        while (coursesTaken.size() < problem.numberOfCourses) {
            semesterCounter++;
            boolean isFallSemester = (semesterCounter % 2 == 1);
            Queue<String> availableCourses = computeAvailableCourses(isFallSemester, coursesTaken, problem);
            fillCourseInOneSemester(problem.maxNumberPerSem, availableCourses, coursesTaken);
        }
        return semesterCounter;
    }

    //=================================================================================

    public static Queue<String> computeAvailableCourses(
        boolean isFallSemester, Set<String> coursesTaken, Problem problem) {
        Queue<String> queue = new LinkedList<>();
        for (String courseId : problem.requiredCourses) {
            if (!coursesTaken.contains(courseId)) {
                Course course = problem.courses.get(courseId);
                if ((isFallSemester && course.offeredInFall())
                    || (!isFallSemester && course.offeredInSpring())) {
                    if (prerequisitesFullfilled(courseId, coursesTaken, problem)) {
                        queue.add(courseId);
                    }
                }
            }
        }
        return queue;
    }

    //=================================================================================

    public static boolean prerequisitesFullfilled(String courseId, Set<String> coursesTaken, Problem problem) {
        Course course = problem.courses.get(courseId);
        for (String prerequisiteCourseId : course.prerequisites) {
            if (!coursesTaken.contains(prerequisiteCourseId)) {
                return false;
            }
        }
        return true;
    }

    //=================================================================================

    public static void fillCourseInOneSemester(
        int courseBudgets, Queue<String> availableCourses, Set<String> coursesTaken) {
        while (!availableCourses.isEmpty() && courseBudgets > 0) {
            String courseId = availableCourses.remove();
            coursesTaken.add(courseId);
            courseBudgets--;
        }
    }

    //=================================================================================

    public static void main(String[] args) {
        List<Problem> problems = InputParser.parse();
        for (Problem problem : problems) {
            solve(problem);
        }
    }
}
