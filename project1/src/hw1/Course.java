package hw1;
import java.util.HashSet;

public class Course {
    private String name;
    private int NumPre;
    private HashSet<Course> pre;
    private int isFall;

    public Course(String name) {
    	pre = new HashSet<Course>();
        this.name = name;
    }

    public void SetNumPre(int n) {
        this.NumPre = n;
    }

    public void addPre(Course c) {
        pre.add(c);
    }

    public int getNumPre() {
        return NumPre;
    }

    public HashSet<Course> getPre() {
        return pre;
    }

    public String getName() {
        return name;
    }

    public void setType(int isFall) {
        this.isFall = isFall;
    }

    // 1 - fall 0 - both   -1 - spring
    public int getType(){
        return isFall;
    }
}
