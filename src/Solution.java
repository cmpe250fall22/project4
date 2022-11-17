import java.util.*;

public class Solution {

    //Dijkstra's algorithm
    public static int[] runRambo(
            int V,
            ArrayList<ArrayList<Node>> graph,
            int src)
    {
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
        pq.add(new Node(src, 0));

        while (pq.size() > 0) {
            Node current = pq.poll();

            for (Node n :
                    graph.get(current.getVertex())) {
                if (distance[current.getVertex()]
                        + n.getDistance()
                        < distance[n.getVertex()]) {
                    distance[n.getVertex()]
                            = n.getDistance()
                            + distance[current.getVertex()];
                    pq.add(new Node(
                            n.getVertex(),
                            distance[n.getVertex()]));
                }
            }
        }
        return distance;
    }

    public static int cutRambo(int V,
                               ArrayList<ArrayList<Node>> graph,
                               int[] flags){

        ArrayList<ArrayList<Node> > newGraph = new ArrayList<>();

        for (int i = 0; i < flags.length; i++) {
            newGraph.add(new ArrayList<>());
        }

        HashMap<Integer, Integer> conversion = new HashMap<>();
        HashMap<Integer, Integer> conversionBack = new HashMap<>();
        int c = 0;

        for (int flag : flags) {
            conversion.put(flag, c);
            conversionBack.put(c, flag);
            c += 1;
        }

        long allStart = System.nanoTime();

        for(int n: flags){
            int[] distance = runRambo(V, graph, n);
            for(int m: flags) {
                newGraph.get(conversion.get(n)).add(new Node(conversion.get(m), distance[m]));
            }
        }

        long allEnd = System.nanoTime();
        long allTime = allEnd - allStart;
        System.out.println("All Time: " + allTime * 0.000000001);

        return primMst(newGraph, flags);
    }

    static int primMst(ArrayList<ArrayList<Node>> graph, int[] flags){

        long primStart = System.nanoTime();

        int noOfStreets = flags.length;
        boolean[] visited = new boolean[noOfStreets];
        Node[] nod = new Node[noOfStreets];
        int[] cost = new int[noOfStreets];

        Arrays.fill(cost, -1);


        TreeSet<Node> queue = new TreeSet<>(Comparator.comparingInt(Node::getDistance));

        cost[0] = 0;
        nod[0] = new Node(0, 0);
        visited[0] = true;
        queue.add(nod[0]);

        for (int i = 1; i < noOfStreets; i++) {
            nod[i] = new Node(i, Integer.MAX_VALUE);
            visited[i] = false;
            queue.add(nod[i]);
        }

        while (!queue.isEmpty()) {

            Node currentEdge = queue.pollFirst();
            visited[currentEdge.vertex] = true;

            for (Node chosen : graph.get(currentEdge.vertex)) {
                if (!visited[chosen.vertex]) {
                    if (nod[chosen.vertex].distance > chosen.distance) {
                        queue.remove(nod[chosen.vertex]);
                        nod[chosen.vertex].distance = chosen.distance;
                        queue.add(nod[chosen.vertex]);
                        cost[chosen.vertex] = chosen.distance;
                    }
                }
            }
        }


        int minCost = 0;

        for (int i : cost) {
            if (i == -1) {
                return -1;
            }
            minCost += i;
        }

        long primEnd = System.nanoTime();
        long primTime = primEnd - primStart;
        System.out.println("Prim Time: " + primTime * 0.000000001);
        return minCost;
    }

}
