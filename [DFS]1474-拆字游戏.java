import java.util.*;

public class Main {

    int[][] board;

    int row;
    int col;

    int upmost = row;
    int downmost = -1;
    int leftmost = col;
    int rightmost = -1;

    int flag = 2;

    public Main() {
    }

    public void init() {
        upmost = row;
        downmost = -1;
        leftmost = col;
        rightmost = -1;
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        col = sc.nextInt();
        board = new int[row][col];

        for (int i = 0; i < row; i++) {
            String str = sc.next();
            for (int j = 0; j < col; j++) {
                board[i][j] = str.charAt(j)-'0';
            }
        }

        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (board[i][j] == 1) {
                    init();
                    dfs(i, j);
                    print();
                    flag++;
                }
            }
        }
    }

    public boolean isValid(int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col ) return false;
        return true;
    }

    public void dfs(int i, int j) {

        if (i < upmost) upmost = i;
        if (i > downmost) downmost = i;
        if (j < leftmost) leftmost = j;
        if (j > rightmost) rightmost = j;

        board[i][j] = flag;

        if (isValid(i-1,j) && board[i-1][j] == 1) dfs(i-1, j);
        if (isValid(i+1,j) && board[i+1][j] == 1) dfs(i+1, j);
        if (isValid(i,j-1) && board[i][j-1] == 1) dfs(i, j-1);
        if (isValid(i,j+1) && board[i][j+1] == 1) dfs(i, j+1);

    }

    public void print() {
        System.out.println((downmost-upmost+1)+" "+(rightmost-leftmost+1));
        for (int i = upmost; i <= downmost; i++) {
            for (int j = leftmost; j <= rightmost; j++) {
                if (board[i][j] == flag)
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solution();
    }
}
