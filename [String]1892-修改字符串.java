import java.util.*;

public class Main {


    public Main() {
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();

        if (str1.length() != str2.length()) {
            System.out.println("-1");
            return;
        }

        int[] cnt = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            cnt[str1.charAt(i)-'A']++;
        }
        for (int i = 0; i < str2.length(); i++) {
            cnt[str2.charAt(i)-'A']--;
            if (cnt[str2.charAt(i)-'A'] < 0) {
                System.out.println("-1");
                return;
            }
        }

        int i = str1.length()-1, j = i;

        //找到string2的子串（从后往前的）最多有几个是string1的子序列，
        while(i >= 0 && j >= 0){
            while(i >= 0 && str1.charAt(i) != str2.charAt(j)) i--;
            if(i >= 0) {
                j--; i--;
            }
        }
        System.out.println(j+1);

    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solution();
    }
}
