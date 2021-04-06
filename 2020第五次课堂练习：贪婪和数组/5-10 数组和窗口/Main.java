import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            scan.nextLine();
            List<Integer> list = new ArrayList<>();
            for (String s: scan.nextLine().split(" ")) {
                list.add(Integer.valueOf(s));
            }
            int w = scan.nextInt();
            LinkedList<Integer> queue = new LinkedList<>();
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                while (!queue.isEmpty() && list.get(queue.getLast()) <= list.get(i)) {
                    queue.removeLast();
                }
                queue.addLast(i);
                if (i - queue.getFirst() == w) {
                    queue.removeFirst();
                }
                if (i >= w - 1) {
                    sum += list.get(queue.getFirst());
                }
            }
            System.out.println(sum);
        }
    }
}
