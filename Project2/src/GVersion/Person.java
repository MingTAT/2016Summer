package GVersion;
import java.util.HashMap;

public class Person {
    final private int index;
    private int numContact;
    private HashMap<Integer,Integer> conInfo;

    public Person(int index) {
        this.index = index;
        conInfo = new HashMap<Integer,Integer>();
    }

    public int getIndex() {
        return index;
    }

    public void setNumCont(int num) {
        this.numContact = num;
    }

    public int getNumCont() {
        return numContact;
    }

    public void addContact(int conIndex, int cost) {
        conInfo.put(conIndex,cost);
    }

    public HashMap<Integer, Integer> getContacts() {
        return conInfo;
    }
}
