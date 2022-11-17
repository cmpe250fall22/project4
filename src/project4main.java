import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class project4main {

    public static void main(String[] args) throws FileNotFoundException {

        long  inputStartTime = System.nanoTime();

        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File(args[0]));
        PrintStream out = new PrintStream(args[1]);
        PrintStream out2 = new PrintStream(args[2]);
        
        int noOfStreet = in.nextInt();
        int noOfFlags = in.nextInt();

        int startingPoint = Integer.parseInt(in.next().substring(1));
        int endingPoint = Integer.parseInt(in.next().substring(1));

        in.nextLine();

        int[] flags = new int[noOfFlags];
        String[] temp = in.nextLine().split(" ");
        for(int i = 0; i < temp.length; i++){
            flags[i] = Integer.parseInt(temp[i].substring(1));
        }

        //System.out.println(Arrays.toString(flags));

        ArrayList<ArrayList<Node> > graph = new ArrayList<>();
        for (int i = 0; i < noOfStreet; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < noOfStreet; i++){
            temp = in.nextLine().split(" ");

            for(int j = 1; j < temp.length; j+=2) {
                graph.get(i).add(new Node(Integer.parseInt(temp[j].substring(1)), Integer.parseInt(temp[j+1])));
                graph.get(Integer.parseInt(temp[j].substring(1))).add(new Node(i, Integer.parseInt(temp[j+1])));
            }
        }

//        for(int i = 0; i < noOfStreet; i++){
//            for(int j = 0; j < graph.get(i).size(); j++){
//                System.out.println(i + " " + graph.get(i).get(j).getVertex() + " " + graph.get(i).get(j).getWeight());
//            }
//        }

        long inputEndTime = System.nanoTime();
        long inputTime = inputEndTime - inputStartTime;
        System.out.println("Input Time: " + inputTime * 0.000000001);

        long  dijkstraStartTime = System.nanoTime();

        int[] distance = Solution.runRambo(noOfStreet, graph, startingPoint);
        if(distance[endingPoint] == Integer.MAX_VALUE){
            System.out.println(-1);
            out.println(-1);
        } else{
            System.out.println(distance[endingPoint]);
            out.println(distance[endingPoint]);
        }

        long dijkstraEndTime = System.nanoTime();
        long dijkstraTime = dijkstraEndTime - dijkstraStartTime;
        System.out.println("Dijkstra Time: " + dijkstraTime * 0.000000001);

        long  outputStartTime = System.nanoTime();

        int result = Solution.cutRambo(noOfStreet, graph, flags);
        System.out.println(result);
        out.println(result);

        long outputEndTime = System.nanoTime();
        long outputTime = outputEndTime - outputStartTime;
        System.out.println("Output Time: " + outputTime  * 0.000000001);

        long globalTime = outputEndTime - inputStartTime;

        System.out.println("Global Time: " + globalTime * 0.000000001);

        out2.println(globalTime * 0.000000001);

        out.close();
        out2.close();

        in.close();

    }

}
