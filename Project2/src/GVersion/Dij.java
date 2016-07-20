package GVersion;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Dij {
    private final Graph g;

    public Dij(Graph g){
        this.g = g;
    }

    public int[] run() {
        int result = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 1; i <= g.getNumofPerson(); i ++) {
            HashMap<Integer,Person> list = new HashMap<>(g.getall());
            int temp = runfromOneSource(list.get(i));
            if (temp < result) {
                index = i;
                result = temp;
            }
        }
        return new int[] {index, result};
    }

    public int runfromOneSource(Person source) {
        HashMap<Integer,Person> set = new HashMap<>();
        int[] dist = new int[g.getNumofPerson()];
        set = new HashMap<>(g.getall());
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[source.getIndex()-1] = 0;
        while (!set.isEmpty()) {
            Person u = set.remove(findMin(set,dist));
            if (dist[u.getIndex() - 1] == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            HashMap<Integer,Integer> neighbor = u.getContacts();
            if (neighbor != null) {
                for (Map.Entry<Integer, Integer> e : neighbor.entrySet()) {
                    int temp = dist[u.getIndex() - 1] + e.getValue();
                    if (temp < dist[e.getKey() - 1]) {
                        dist[e.getKey() - 1] = temp;
                    }
                }
            }
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i ++) {
            if (dist[i] > result) {
                result = dist[i];
            }
        }
        return result;
    }

    public int findMin(HashMap<Integer, Person> set, int[] dist) {
        int result = -1;
        int d = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Person> e : set.entrySet()) {
            int index = e.getKey();
            if (dist[index-1] <= d) {
                d = dist[index-1];
                result = index;
            }
        }
        return result;

    }

}
