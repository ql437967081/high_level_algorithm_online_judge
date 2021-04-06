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
            for (String s : scan.nextLine().split(" ")) {
                list.add(Integer.valueOf(s));
            }
            int num = scan.nextInt();
            LinkedList<Integer> queue1 = new LinkedList<>();
            LinkedList<Integer> queue2 = new LinkedList<>();
            long cnt = 0;
            for (int i = 0, start = 0, n = list.size(); i < n; i++) {
                while (!queue1.isEmpty() && list.get(queue1.getLast()) >= list.get(i)) {
                    queue1.removeLast();
                }
                while (!queue2.isEmpty() && list.get(queue2.getLast()) <= list.get(i)) {
                    queue2.removeLast();
                }
                queue1.addLast(i);
                queue2.addLast(i);
                int end = start - 1;
                while (list.get(queue2.getFirst()) - list.get(queue1.getFirst()) > num) {
                    switch (Integer.compare(queue1.getFirst(), queue2.getFirst())) {
                        case -1: end = queue1.removeFirst(); break;
                        case 1: end = queue2.removeFirst(); break;
                        case 0: end = queue1.removeFirst(); queue2.removeFirst(); break;
                    }
                }
                end++;
                cnt += (long) (end - start) * (n - i);
                start = end;
            }
            System.out.println(cnt);
        }
    }
}
