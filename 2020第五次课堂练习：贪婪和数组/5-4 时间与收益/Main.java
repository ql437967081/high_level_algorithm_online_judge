import java.util.*;

public class Main {
    private static class Job {
        private int deadline;
        private int profit;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            boolean[] vis = new boolean[101];
            Arrays.fill(vis, false);
            PriorityQueue<Job> queue = new PriorityQueue<>(n, (job1, job2) -> Integer.compare(job2.profit, job1.profit));
            int maxCnt = 0;
            for (int i = 0; i < n; i++) {
                Job job = new Job();
                scan.nextInt();
                job.deadline = scan.nextInt();
                job.profit = scan.nextInt();
                queue.offer(job);
                maxCnt = Math.max(maxCnt, job.deadline);
            }
            int cnt = 0;
            int sum = 0;
            while (!queue.isEmpty() && cnt < maxCnt) {
                Job job = queue.poll();
                int time = job.deadline;
                while (time > 0 && vis[time]) {
                    time--;
                }
                if (time > 0) {
                    cnt++;
                    sum += job.profit;
                    vis[time] = true;
                }
            }
            System.out.println(cnt + " " + sum);
        }
    }
}
