import java.util.Scanner;

public class Main {

	public long fun(int hn, long x, long y) {
		if (hn == 0) return 1;
		long base = 1 << (hn-1);
		long basecnt = 1l << 2*(hn-1);
		
		if(x <= base && y <= base) {			
			return fun(hn-1, y, x);
		}else if(x <= base && y > base) {
			return fun(hn-1, x, y-base)+basecnt;
		}else if(x > base && y > base) {
			return fun(hn-1, x-base, y-base)+2*basecnt;
		}else {
			return fun(hn-1, base+1-y, 2*base+1-x)+3*basecnt;
		}
	}
	
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		int hn = in.nextInt();
		long x = in.nextLong();
		long y = in.nextLong();
		Main main2 = new Main();
		System.out.println(main2.fun(hn,x,y));
	}
}