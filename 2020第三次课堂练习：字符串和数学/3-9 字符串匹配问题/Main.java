import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private char[] pat;
    private char[] txt;
    private int m;
    private int n;
    private int[] next;
    private List<Integer> matchIndex;

    private void getNext() {
        next = new int[m + 1];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < m) {
            if (j == -1 || pat[i] == pat[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }

    private void kmp() {
        matchIndex = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n) {
            if (j == - 1 || j < m && txt[i] == pat[j]) {
                i++;
                j++;
                if (j == m) {
                    matchIndex.add(i - m);
                }
            } else {
                j = next[j];
            }
        }
    }

    public Main(String txt, String pat) {
        this.txt = txt.toCharArray();
        this.pat = pat.toCharArray();
        n = this.txt.length;
        m = this.pat.length;
        getNext();
        kmp();
        System.out.println(matchIndex.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();
        while (t-- > 0) {
            String[] strings = scan.nextLine().split(",");
            new Main(strings[0], strings[1]);
        }
    }
}
