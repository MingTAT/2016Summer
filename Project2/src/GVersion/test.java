package GVersion;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
public class test {
    public static void main(String[] args) {
        File in = new File("a.in");
        File out = new File("Zhonghao.out");

        try {
            Scanner scan = new Scanner(in);
            PrintWriter pw = new PrintWriter(out);
            String input = null;
            while (!(input = scan.nextLine()).equals("0")) {
                int num = Integer.valueOf(input);
                Graph g = new Graph(num);
                for (int i = 1; i < num + 1; i ++) {
                    g.addPerson(i, new Person(i));
                }
                for (int i = 0; i < num; i ++) {
                    String temp = scan.nextLine();
                    String[] list = temp.split(" ");
                    int numofContact = Integer.valueOf(list[0]);
                    g.getPerson(i + 1).setNumCont(numofContact);

                    for (int j = 1; j < list.length; j += 2) {
                        int cindex = Integer.valueOf(list[j]);
                        int cost = Integer.valueOf(list[j+1]);
                        g.getPerson(i + 1).addContact(cindex, cost);
                    }
                }
                Dij dj = new Dij(g);
                int[] result = dj.run();
                if (result[1] == Integer.MAX_VALUE){
                    System.out.println("disjoint");
                    pw.println("disjoint");
                } else {
                    System.out.println(result[0] + " " + result[1]);
                    pw.println(result[0] + " " + result[1]);
                }
            }
            scan.close();
            pw.close();
        }catch (FileNotFoundException e) {
            System.out.println("No such File found");
        }

    }
}
