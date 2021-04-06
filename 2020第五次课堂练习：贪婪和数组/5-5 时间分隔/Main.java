import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static class Train implements Comparable<Train> {
        private int arrival;
        private int departure;

        @Override
        public int compareTo(Train o) {
            if (arrival == o.arrival)
                return Integer.compare(o.departure, departure);
            return Integer.compare(arrival, o.arrival);
        }
    }

    private static class Node {
        private Node pre;
        private Node next;
        private Train train;

        private Node(Node pre, Train train) {
            this.pre = pre;
            this.train = train;
        }
    }

    private static int nextInt(Scanner scan) {
        return Integer.parseInt(scan.next().trim().replaceAll(String.valueOf((char) 160), ""));
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = nextInt(scan);
            Train[] trains = new Train[n];
            for (int i = 0; i < n; i++) {
                trains[i] = new Train();
                trains[i].arrival = nextInt(scan);
            }
            for (int i = 0; i < n; i++) {
                trains[i].departure = nextInt(scan);
            }
            Arrays.sort(trains);
            Node head = new Node(null, null);
            Node node = head;
            for (int i = 0; i < n; i++) {
                Node newNode = new Node(node, trains[i]);
                node.next = newNode;
                node = newNode;
            }
            int numOfPlatforms = 0;
            while (head.next != null) {
                numOfPlatforms++;
                node = head.next;
                int time = -1;
                while (node != null) {
                    if (node.train.arrival > time) {
                        time = node.train.departure;
                        node.pre.next = node.next;
                        if (node.next != null) {
                            node.next.pre = node.pre;
                        }
                    }
                    node = node.next;
                }
            }
            System.out.println(numOfPlatforms);
        }
    }
}
