package Task1;

import java.io.*;

public class Task1 {

    static String[] pid;
    static int[] arrival_time;
    static int[] burst_time;
    static int[] complete_time;
    static int[] turnaround_time;
    static int[] waiting_time;
    static int[] remaining_time;
    static int flag[];
    static int i, st = 0, total = 0;
    static float avg_wait = 0, avg_ta = 0;

    public static void main(String args[]) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("Q:\\Scheduli\\src\\Task1\\input.txt");
        BufferedReader br = new BufferedReader(fr);

        String str = br.readLine();
        int count = 0;
        int n = Integer.parseInt(str);

        pid = new String[n];
        arrival_time = new int[n];
        burst_time = new int[n];
        complete_time = new int[n];
        turnaround_time = new int[n];
        waiting_time = new int[n];
        remaining_time = new int[n];
        flag = new int[n];

        while ((str = br.readLine()) != null) {
            String[] arr = str.split(" ");
            i = 0;
            pid[count] = arr[i++];
            arrival_time[count] = Integer.parseInt(arr[i++]);
            remaining_time[count] = Integer.parseInt(arr[i]);
            burst_time[count] = Integer.parseInt(arr[i++]);
            count++;
        }
        while (true) {
            int min = 99, c = n;
            if (total == n) {
                break;
            }
            for (i = 0; i < n; i++) {
                if ((arrival_time[i] <= st) && (flag[i] == 0) && (burst_time[i] < min)) {
                    min = burst_time[i];
                    c = i;
                }
            }
            if (c == n) {
                st++;
            } else {
                burst_time[c]--;
                st++;
                if (burst_time[c] == 0) {
                    complete_time[c] = st;

                    turnaround_time[c] = complete_time[c] - arrival_time[c];
                    waiting_time[c] = turnaround_time[c] - remaining_time[c];
                    avg_wait += waiting_time[c];
                    avg_ta += turnaround_time[c];

                    flag[c] = 1;
                    total++;
                }
            }
        }
        printResult();
    }

    public static void printResult() {
        System.out.println("pid arrival burst complete turnaround waiting");
        for (i = 0; i < pid.length; i++) {
            System.out.println(pid[i] + "     " + arrival_time[i] + "      " + remaining_time[i] + "      " + complete_time[i] + "       " + turnaround_time[i] + "       " + waiting_time[i]);
        }
        System.out.println("Average Wait =" + avg_wait / pid.length);
        System.out.println("Average Turnround =" + avg_ta / pid.length);
    }
}
