import java.util.Scanner;

public class Main {

    private int[] total = new int[26];
    private int[] cnt = new int[26];
    private int K;

    public Main() {
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        String str = sc.next();
        int result = maxLength(str.toCharArray(), K);
        System.out.println(result);
    }

    //把[i,j]全部换成ch可以在K步里换完吗
    private boolean canChangeSameCh(int i, int j, int ch) {
        int inch = cnt[ch];
        int outch = total[ch]-inch;
        //K步不够换
        if (K < j-i+1 - inch) return false;
        //外面没这么多
        if (outch < j-i+1 - inch) return false;
        return true;
    }

    //把[i,j]全部换成某种字符可以在K步换完吗
    private boolean canChangeSame(int i, int j) {
        for (int c = 0; c < 26; c++) {
            if (canChangeSameCh(i, j, c)) return true;
        }
        return false;
    }

    private int maxLength(char[] str, int k) {
        //compute total
        for (char c: str) total[c-'a']++;
        int max = -1;
        int j = 0;

        for (int i = 0; i < str.length; i++) {
            // i右移动时弹出一个
            if (i - 1 >= 0) {
                cnt[str[i-1]-'a']--;
            }
            // 放入当前i
            cnt[str[i]-'a']++;
            while(j < str.length && canChangeSame(i, j)) {
                max = Math.max(max, j-i+1);
                j++;
                if (j < str.length) cnt[str[j]-'a']++;
            }
        }
        return max;
    }



    public static void main(String[] args) {
        Main main = new Main();
        main.solution();
    }
}
