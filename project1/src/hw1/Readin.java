package hw1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Readin {
	HashMap<String,Course> map;
	HashMap<String,HashSet<String>> adjList;
	int k;
	int totalNumofCourse;
	
	public Readin(){
		map = new HashMap<String, Course>();
		adjList = new HashMap<String, HashSet<String>>();
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String[] in = input.split(" ");
		totalNumofCourse = Integer.valueOf(in[0]);
		k = Integer.valueOf(in[1]);
		//read in all courses name
		input = scan.nextLine();
		in = input.split(" ");
		for (String s: in) {
			Course newc = new Course(s);
			map.put(s, newc);
		}
		//read in all prerequisit and build map/adjList
		while(!(input = scan.nextLine()).equals("-1 -1")){
			in = input.split(" ");
			String name = in[0];
			String semester = in[1];
			char s1 = semester.charAt(0);
			int sem = 1;
			if (s1 == 'S' || s1 == 's' ){
				sem = -1;
			} else if (s1 == 'B' || s1 == 'b'){
				sem = 0;
			}
			
			int NumofPre = Integer.valueOf(in[2]);
			HashSet<String> pres = new HashSet<>();
			for (int i = 3; i < in.length; i++){
				pres.add(in[i]);
			}
			map.get(name).setType(sem);
			map.get(name).SetNumPre(NumofPre);
			for (String s: pres) {
				map.get(name).addPre(map.get(s));
			}
			
			adjList.put(name, pres);
		}
		scan.close();
		
	}
	
	public HashMap<String, Course> getMap(){
		return map;
	}
	
	public HashMap<String, HashSet<String>> getadjList(){
		return adjList;
	}
	
	public void printAdj(){
		for (Map.Entry<String, HashSet<String>> e: adjList.entrySet()) {
			System.out.println("");
			System.out.print(e.getKey() + " ");
			for (String s : e.getValue()){
				System.out.print(s + " ");
			}
		}
	}
	
	public int getMaxCs()
	{
		return k;
	}
		
}
