import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int charToInt(char ch) {
        return ch - 'A';
    }

    private static char intToChar(int i) {
        return (char)(i + 'A');
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();
        while (t-- > 0) {
            String s = scan.nextLine();
            boolean[] exist = new boolean[26];
            Arrays.fill(exist, false);
            for (char ch: s.toCharArray()) {
                exist[charToInt(ch)] = true;
            }
            int maxLen = 0;
            String res = null;
            for (int last = charToInt('Z'); last + 1 > maxLen; last--) {
                if (!exist[last]) {
                    continue;
                }

                for (int commonDif = 1; last >= maxLen * commonDif; commonDif++) {
                    StringBuilder sb = new StringBuilder(26);
                    sb.append(intToChar(last));
                    for (int pos = last - commonDif; pos >= 0 && exist[pos]; pos -= commonDif) {
                        sb.append(intToChar(pos));
                    }
                    if (sb.length() > maxLen) {
                        maxLen = sb.length();
                        res = sb.toString();
                    }
                }
            }
            System.out.println(res);
        }
    }
}
