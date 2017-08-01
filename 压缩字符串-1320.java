import java.util.Scanner;


public class Main {
	
	int[][] dp;
	
	public Main(String str) {
		int strlen = str.length();
		dp = new int[strlen+1][strlen+1];
		
		int result = best(str, 0, strlen);
		System.out.println(result);
	}
	
	public int best(String str, int start, int end) {
		
		if(dp[start][end] != 0) return dp[start][end];
		
		//case 1: its original size. i.e.  abc is abc
		int origin = end - start;
		
		//case 2: repeated pattern. the length is len(repeatcnt)+2+repeatsize i.e. 2(ab) is abab
		int repeat = Integer.MAX_VALUE;
		int repeatlen = repeatedSubstringPattern(str, start, end);
		if(repeatlen != -1)
			repeat = Integer.toString((end-start)/repeatlen).length() + 2 + best(str, start, start+repeatlen);
		
		//case 3: split
		int split = Integer.MAX_VALUE;
		for(int i = start+1; i < end; i++) {
			int tmp = best(str, start, i) + best(str, i, end);
			if(tmp < split) split = tmp;
		}
		
		dp[start][end] = Math.min(origin, Math.min(repeat, split));
		
		return dp[start][end];
	}
	
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public int repeatedSubstringPattern(String str, int start, int end) {
		
		String s = str.substring(start, end);
		
        int i = 1, j = 0;
        int[] prefixArray = new int[s.length()];
        while (i < s.length()) {

            while (s.charAt(i) != s.charAt(j) && j > 0) {
                j = prefixArray[j - 1];

            }
            if (s.charAt(i) == s.charAt(j)) {
                prefixArray[i] = j + 1;
                i++;
                j++;

            } else {
                prefixArray[i] = j;
                i++;
            }
        }
        
        if(prefixArray[s.length()-1] > 0 && s.length()%(s.length()-prefixArray[s.length()-1]) == 0)
        	return s.length()-prefixArray[s.length()-1];
        else
        	return -1;
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner inScanner = new Scanner(System.in);
		int cnt = inScanner.nextInt();
		for(int i = 0; i < cnt; i++) {
			String str = inScanner.next();
			Main main2 = new Main(str);
			//main2.solution();
			//System.out.println(main2.repeatedSubstringPattern(str, 0, str.length()));
			
		}

	}

}
