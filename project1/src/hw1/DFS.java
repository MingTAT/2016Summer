package hw1;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

public class DFS {
    int maxPerSem;
    HashMap<String,Course> map;
    HashMap<String,HashSet<String>> adjList;

    public static void main(String[] args) {
        HashSet<String> visited = new HashSet<>();
        boolean isFall = true;
        Readin ri = new Readin();
        HashMap<String,Course> buildmap = ri.getMap();
        HashMap<String,HashSet<String>> buildadjList = ri.getadjList();
        int getmaxPerSem = ri.getMaxCs();
        DFS dfs = new DFS(buildmap, buildadjList, getmaxPerSem);
        int result = dfs.getNumofSemester(visited, true, true);
        System.out.println("");
        if (result < 0) {
            System.out.println("There is a loop in the graph, impossible to finish all courses");
        } else {
            System.out.println("Minimum number of semesters : " + result);
        }


    }

    public DFS(HashMap<String,Course> map, HashMap<String,HashSet<String>> adjList, int maxPerSem) {
        this.map = map;
        this.adjList = adjList;
        this.maxPerSem = maxPerSem;
    }


    public int getNumofSemester(HashSet<String> visited, boolean isFall,boolean classAvaliable) {
        int k = this.maxPerSem;
        //if all courses are visited, then no more semester needed.
        if (visited.size() == map.size()) {
            return 0;
        }

        List<Course> list =  findAllValidCourse(isFall, visited);
        //if no valid course,forward to next stage
        if(list.size() == 0) {
            //if last stage also has no available class, then there must be a loop in the graph.
            if(!classAvaliable) {
                return Integer.MIN_VALUE;
            }
        	int depth = getNumofSemester(visited, !isFall, false) + 1;
        	return depth;
        }

        List<List<Course>> subsets = getSubset(list);
        int minDepth = Integer.MAX_VALUE;
        for (int i = 0; i < subsets.size(); i ++) {
            removeSubset(subsets.get(i), visited);
            int depth = getNumofSemester(visited, !isFall,true);
            if (depth < minDepth) {
                minDepth = depth;
                
            }
            addSubset(subsets.get(i), visited);
        }
        return minDepth + 1;
        
    }

    //add one course subset to adjlist
    public void addSubset(List<Course> list, HashSet<String> visited) {
        for (Course c : list) {
            addCourse(c.getName(),visited);
        }
    }

    //remove one course subset from adjlist
    public void removeSubset(List<Course> list, HashSet<String> visited) {
        for (Course c : list) {
            removeCourse(c.getName(), visited);
        }
    }

    //put current course back
    public void addCourse(String c, HashSet<String> visited) {
        for(Map.Entry<String,Course> e : map.entrySet()) {
            if (!visited.contains(e.getKey())) {
                if(e.getValue().getPre().contains(map.get(c))) {
                    adjList.get(e.getKey()).add(c);
                }
            }
        }
        visited.remove(c);

    }

    //visit and remove current course
    public void removeCourse(String c, HashSet<String> visited) {
        for(Map.Entry<String,Course> e : map.entrySet()) {
            if (!visited.contains(e.getKey())) {
                //if current course take c as prerequisit, then remove c from prerequisit list
                if(e.getValue().getPre().contains(map.get(c))) {
                    adjList.get(e.getKey()).remove(c);
                }
            }
        }
        visited.add(c);
    }

    //find all valid course according to adjacent list
    public List<Course> findAllValidCourse(boolean isFall, HashSet<String> visited) {
        List<Course> result = new ArrayList<Course>();
        //System.out.println("");
        for(Map.Entry<String, HashSet<String>> e : adjList.entrySet()) {
            //if course is not visited, is offered this semester, all prerequisite is finished, then it is valid
            if (!visited.contains(e.getKey()) && e.getValue().size() == 0) {
            	int type = map.get(e.getKey()).getType();
            	if ((isFall && (type == 0 || type == 1)) || (!isFall && (type == 0 || type == -1)))
            		result.add(map.get(e.getKey()));
            	   // System.out.print(map.get(e.getKey()).getName());
            }
        }
        return result;
    }

    //get k or smaller than k subsets of input list
    public List<List<Course>> getSubset(List<Course> input) {
        int k = maxPerSem;
        List<List<Course>> result = new ArrayList<List<Course>>();
        List<Course> temp = new ArrayList<Course>();
        getSubset(input, k, 0, result, temp);
        return result;
    }

    //helper function of getsubset 
    //add result.size() > 0 here
    public void getSubset(List<Course> input, int k, int index, List<List<Course>> result, List<Course> temp) {
    	
        if(index == input.size()) {
        	if (temp.size() <= k && temp.size() > 0) {
            List<Course> valid = new ArrayList<Course>();
            valid.addAll(temp);
            result.add(valid);
            return;
        	} else {
        		return;
        	}
        }
        //System.out.println(index + " "+ input.size());
        temp.add(input.get(index));
        getSubset(input, k, index + 1, result, temp);
        temp.remove(temp.size() - 1);
        getSubset(input, k, index + 1, result, temp);
    }
    
    public void printsubSet(List<List<Course>> input) {
    	System.out.println("print subset");
    	for (List<Course> l : input) {
    		System.out.println(l.size());
    		System.out.println(" ");
    		for (Course c : l) {
    			System.out.print(c.getName() + " ");
    		}
    	}
    }
    
   
    public void printlist(List<Course> input) {
    	System.out.println("print list");
    	for (Course c : input) {
    		System.out.println(c.getName() + " "+c.getType());
    	}
    }
}
