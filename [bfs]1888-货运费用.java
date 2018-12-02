import java.util.*;

public class Main {

    int weight;
    int n;
    int[][] graph;
    int[] dist;

    public Main() {
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        graph = new int[n+1][n+1];
        dist = new int[n+1];

        int e = sc.nextInt();
        weight = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int quant = sc.nextInt();
            if (quant >= weight) {
                graph[v1][v2] = 1;
                graph[v2][v1] = 1;
            }
        }
        System.out.println(bfs2());
    }

    public List<Integer> adj(int v) {
        List<Integer> adjs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (graph[v][i] == 1) adjs.add(i);
        }
        return adjs;
    }

    public int bfs2() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        while(!queue.isEmpty()) {
            int v = queue.poll();
            if (v == n) return dist[v];

            List<Integer> adjs = adj(v);
            for (int adjv: adjs) {
                if(dist[adjv] == Integer.MAX_VALUE) {
                    queue.offer(adjv);
                    dist[adjv] = dist[v] + 1;
                }
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solution();
    }
}
