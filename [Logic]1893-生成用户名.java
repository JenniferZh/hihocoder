import java.util.*;


public class Main {


    public Main() {
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        HashSet<String> trie = new HashSet<>();
        HashMap<String, Integer> index = new HashMap<>();
        for (int i = 0; i < cnt; i++) {
            String current = sc.next();
            if (!trie.contains(current)) {
                System.out.println(current);
                trie.add(current);
            } else {

                while (true) {
                    Integer curIndex = index.get(current);
                    if (curIndex == null) index.put(current, 1);
                    else index.put(current, curIndex+1);


                    String newName = current + index.get(current);
                    if (!trie.contains(newName)) {
                        System.out.println(newName);
                        trie.add(newName);
                        break;
                    }
                }
            }
        }
    }






    public static void main(String[] args) {
        Main main = new Main();
        main.solution();
    }
}
