import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import static java.math.BigInteger.ZERO;

public class Main {
    private static ListNode head;
    private static ListNode tail;
    private static int n;
    private static StringBuilder sb;
    private static final BigInteger DIV = new BigInteger("17");
    private static BigInteger res;
    private static final String NOT_POSSIBLE = "Not Possible";

    private static class ListNode {
        private ListNode last;
        private ListNode next;
        private char value;

        private ListNode(ListNode last, ListNode next, char value) {
            this.last = last;
            this.next = next;
            this.value = value;
        }

        private void remove() {
            if (last == null) {
                head = next;
            } else {
                last.next = next;
            }
            if (next == null) {
                tail = last;
            } else {
                next.last = last;
            }
        }

        private void add() {
            if (last == null) {
                head = this;
            } else {
                last.next = this;
            }
            if (next == null) {
                tail = this;
            } else {
                next.last = this;
            }
        }
    }

    private static boolean run(int size) {
        if (size == n) {
            res = new BigInteger(sb.toString());
            return ZERO.equals(res.divideAndRemainder(DIV)[1]);
        }
        for (ListNode node = head; node != null; node = node.next) {
            char ch = node.value;
            node.remove();
            sb.append(ch);
            if (run(size + 1)) {
                return true;
            }
            node.add();
            sb.deleteCharAt(size);
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();
        while (t-- > 0) {
            char[] numChArr = scan.nextLine().toCharArray();
            n = numChArr.length;
            if (n == 1 && numChArr[0] == '0') {
                System.out.println(NOT_POSSIBLE);
                continue;
            }
            Arrays.sort(numChArr);
            sb = new StringBuilder(n);
            tail = head = new ListNode(null, null, numChArr[n - 1]);
            for(int i = n - 2; i >= 0; i--) {
                tail.next = new ListNode(tail, null, numChArr[i]);
                tail = tail.next;
            }
            System.out.println(run(0) ? res : NOT_POSSIBLE);
        }
    }
}
