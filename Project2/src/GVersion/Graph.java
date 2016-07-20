package GVersion;
import java.util.HashMap;

public class Graph {
    private HashMap<Integer,Person> list;
    private final int numofPerson;

    public Graph(int numofPerson) {
        this.numofPerson = numofPerson;
        this.list = new HashMap<Integer, Person>();
    }

    public int getNumofPerson() {
        return this.numofPerson;
    }

    public void addPerson(int index, Person p) {
        list.put(index,p);
    }

    public Person getPerson(int index) {
        return list.get(index);
    }

    public HashMap<Integer,Person> getall() {
        return list;
    }

}
