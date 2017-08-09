import java.util.Scanner;


public class Main {
	
	int[][] dp;
	String string;
	int len = 0;
	
	public Main(String str) {
		// TODO Auto-generated constructor stub
		this.string = str;
		this.len = str.length();
		dp = new int[len+1][len+1];
		for(int i = 0; i < len; i++)
			for(int j = 0; j <len; j++)
				dp[i][j] = -1;
		
	}
	
	public int best(int i, int j) {
		if(i > j) return 0;
		
		if(i == j) {
			dp[i][j] = 0;
			return 0;
		}
		
		if(dp[i][j] != -1) return dp[i][j];
		
		if(string.charAt(i) == string.charAt(j)) return best(i+1, j-1);
		
		else {
			int result =  Math.min(best(i+1, j), Math.min(best(i, j-1), best(i+1, j-1)))+1;
			dp[i][j] = result;
			return result;
		}
	}
		
    public static void main(String[] args) {
    	Scanner in =  new Scanner(System.in);
    	String string = in.next();
    	Main aMain = new Main(string);
    	System.out.println(aMain.best(0, string.length()-1));
        
    }
}