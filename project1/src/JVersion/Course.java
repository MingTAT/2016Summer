package JVersion;
public class Course {
    final String id;
    final String offering; // ideally use enum
    final String[] prerequisites;

    public Course(String id, String offering, String[] prerequisites) {
        this.id = id;
        this.offering = offering;
        this.prerequisites = prerequisites;
    }

    public boolean offeredInFall() {
        return offering.equalsIgnoreCase("F") || offering.equalsIgnoreCase("B");
    }

    public boolean offeredInSpring() {
        return offering.equalsIgnoreCase("S") || offering.equalsIgnoreCase("B");
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(id);
        s.append(" ");
        s.append(offering);
        s.append(" {");
        if (prerequisites.length > 0) {
            s.append(prerequisites[0]);
        }
        for (int i = 1; i < prerequisites.length; i++) {
            s.append(", ");
            s.append(prerequisites[i]);
        }
        s.append("}");
        return s.toString();
    }
}
