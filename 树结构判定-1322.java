import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	
	private int node;
	private int[][] graph;
	private int[]  visited;
	
	
	Main(int node) {
		this.node = node;
		visited = new int[node+1];
		graph = new int[node+1][node+1];
	}
	
	public void addedge(int a, int b) {
		graph[a][b] = 1;
		graph[b][a] = 1;
		
	}
	
	public boolean bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);
		
		visited[node] = -1;
		
		while(!queue.isEmpty()) {
			
			int curnode = queue.poll();
			
			
			for(int i = 1; i <= this.node; i++) if(i != curnode) {
				if(graph[curnode][i] == 1) {
					if(visited[i] == 0) {
						visited[i] = -1;
						queue.add(i);
					}
					else if(visited[i] == -1)
						return false;
				}
			}
			
			visited[curnode] = 1;
		}
		
		return true;
	}
	
	public boolean judge() {
		//空树是树
		if(node == 0) return true;
		//不为空
		
		if(!bfs(1)) return false;
		
		//如果有不止一棵树
		for(int i = 1; i <= node; i++) {
			if(visited[i] == 0) return false;
		}
		
		return true;
	}
		
    public static void main(String[] args) {
    	Scanner in =  new Scanner(System.in);
    	int cnt = in.nextInt();
    	for(int i = 0; i < cnt; i++) {
    		int node = in.nextInt();
    		int edge = in.nextInt();
    		
    		Main main = new Main(node);
    		for(int j = 0; j < edge; j++) {
    			int nodea = in.nextInt();
    			int nodeb = in.nextInt();
    			
    			main.addedge(nodea, nodeb);
    			
    		}
   		
    		if(main.judge()) System.out.println("YES");
    		else System.out.println("NO");

    		
    	}
        
    }
}
