import java.util.Scanner;
import java.util.TreeSet;

public class Main {


    public Main() {
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        System.out.println(greedy(n, str));
    }
    
    //my bad O(n*k) solution with O(n*k) space
    public String SmallSub(int K, String string) {
        String[] mem = new String[string.length()];
        //init
        mem[0] = string.substring(0, 1);
        for (int i = 1; i < string.length(); i++) {
            if (mem[i-1].compareTo(string.substring(i, i+1)) < 0)
                mem[i] = mem[i-1];
            else
                mem[i] = string.substring(i, i+1);
        }
        for (int k = 1; k < K; k++) {
            String backup = mem[k];
            mem[k] = mem[k-1] + string.substring(k, k+1);
            for (int i = k+1; i < string.length(); i++) {
                String prev = mem[i-1];
                String cur = backup+string.substring(i, i+1);
                backup = mem[i];
                if (prev.compareTo(cur) < 0)
                    mem[i] = prev;
                else
                    mem[i] = cur;
            }
        }
        return mem[string.length()-1];
    }

    private class CharIndex implements Comparable<CharIndex>{
        private char c;
        private int index;

        @Override
        public int compareTo(CharIndex o) {
            if (c < o.c) return -1;
            else if(c > o.c) return 1;
            else return index-o.index;
        }

        CharIndex(char c, int index) {
            this.c = c;
            this.index = index;
        }

    }

    // 贪心算法：第i个字符一定是S[P(i-1) + 1] .. S[N-K+i-1] 中字典序最小的字符
    // 答案的第一个字符一定是S[0] .. S[N-K]中字典序最小的字符；如果有多个，我们一定找其中最靠前的，不妨设为S[P1]。
    //
    //例如对于样例S=cacbbac，第一个字符是cacb中字典序最小的，即S[1] = 'a'。
    //
    //答案的第二个字符一定是S[P1+1] .. S[N-K+1]中字典序最小的字符；如果有多个，我们一定找其中最靠前的，不妨设为S[P2]。
    //
    //例如对于样例S=cacbbac，第二个字符是cbb中字典序最小的，即S[3] = 'b'。
    //
    //……
    //
    //答案的第i个字符一定是S[P(i-1) + 1] .. S[N-K+i-1] 中字典序最小的字符；如果有多个，我们一定找其中最靠前的，不妨设为S[Pi]。
    public String greedy(int k, String string) {
        int p = -1;
        String result = "";
        TreeSet<CharIndex> set = new TreeSet<>();
        for (int i = 1; i <= k; i++) {
            //remove elements before p
            for (int t = p; t >=0; t--) {
                CharIndex tmpRemove = new CharIndex(string.charAt(t), t);
                if (!set.remove(tmpRemove)) break;
            }

            //add elements from s[p+1] to s[n-k+i-1]
            for (int t = string.length()-k+i-1; t >= p+1; t--) {
                CharIndex tmpAdd = new CharIndex(string.charAt(t), t);
                if(set.contains(tmpAdd)) break;
                else set.add(tmpAdd);
            }

            //get target char and update p
            CharIndex target = set.first();
            result = result + target.c;
            p = target.index;

        }
        return result;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solution();
    }
}
