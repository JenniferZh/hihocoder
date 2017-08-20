import java.util.Scanner;


public class Main
{

	/**
	*方法1：计算01字符串的最长递增子序列，答案是len-maxIncreaseSubSequence
	*/
	
	public int fun(String str) {
		int len = str.length();
		int[] dp = new int[len+1];
		dp[0] = 1;
		
		int ans = 1;

		for(int i = 1; i < len; i++) {
			int max = 1;
			for(int j = 0; j < i; j++) {
				if(str.charAt(i) >= str.charAt(j)) {
					max = Math.max(max, dp[j]+1);
				}
				dp[i] = max;
				ans = Math.max(max, ans);
			}
		}
		return len-ans;
	}
	
	/**
	*方法2：枚举所有正确答案（共len+1个），然后和原串比较，找差别最小的
	*/
	public int fun2(String str) {
		int len = str.length();
		int min = Integer.MAX_VALUE;
		for(int zero = 0; zero <= len; zero++) {
			int cnt = 0;
			for(int i = 0; i < zero; i++)
				if(str.charAt(i) != '0') cnt++;
			for(int i = zero; i < len; i++)
				if(str.charAt(i) != '1') cnt++;
			min = Math.min(min, cnt);
		}
		return min;
	}
	
    public static void main(String[] args)
    {
    	
            Scanner scanner = new Scanner(System.in);
            int cnt = scanner.nextInt();
            Main main = new Main();
            for(int i = 0; i < cnt; i++) {
            	String pString = scanner.next();
            	System.out.println(main.fun(pString));
            	
            }

    }

    
}