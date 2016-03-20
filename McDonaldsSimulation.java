/*
 * Created by Richard Lun on 2/3/16.
 */

import java.util.*;
public class McDonaldsSimulation {
    static Queue<CustomerNonVIP> q;
    static Queue<CustomerVIP> vq;
    static int longWait = 0;
    static int totalCustomers = 0;
    static int longQueue = 0;
    static double averageWait = 0;
    static double averageVIP = 0;
    static int servedVIP = 0;

    public static void main(String[] args) {
        runSimulation(1440);
    }

    public static void runSimulation(int t) {
        q = new LinkedList<CustomerNonVIP>();
        vq = new LinkedList<CustomerVIP>();
        for (int x = 0; x < t; x++) {
            if (!vq.isEmpty() && vq.peek().getOrder() == 0) {
                int wait = x - vq.peek().getEnter();
                averageVIP = averageVIP + wait;
                servedVIP++;
                totalCustomers++;
                if (wait > longWait)
                    longWait = wait;
                vq.remove();
                if (!q.isEmpty() && q.size() > longQueue)
                    longQueue = q.size() + vq.size();
                if (!q.isEmpty()) {
                    q.peek().orderDown();
                }
            }
            if(!vq.isEmpty())
            {
                vq.peek().orderDown();
            }
            if (Math.random() < .2) {
                CustomerNonVIP c = new CustomerNonVIP();
                c.setEnter(x);
                q.add(c);
            }
            if (Math.random() < .01) {
                CustomerVIP c = new CustomerVIP();
                c.setEnter(x);
                vq.add(c);
            }
            if (!q.isEmpty() && q.peek().getOrder() == 0) {
                int wait = x - q.peek().getEnter();
                averageWait = averageWait + wait;
                totalCustomers++;
                if (wait > longWait)
                    longWait = wait;
                q.remove();
            }
            if (!q.isEmpty() && q.size() > longQueue)
                longQueue = q.size();
            if (vq.isEmpty() && !q.isEmpty()) {
                q.peek().orderDown();
            }
            System.out.println(q);
        }
        int time = t;
        while (!q.isEmpty() || !vq.isEmpty()) {
            if (!q.isEmpty() && q.peek().getOrder() == 0) {
                int wait = time - q.peek().getEnter();
                averageWait = averageWait + wait;
                totalCustomers++;
                if (wait > longWait)
                    longWait = wait;
                q.remove();
            }
            if (!q.isEmpty() && q.size() > longQueue)
                longQueue = q.size();
            if (vq.isEmpty() && !q.isEmpty()) {
                q.peek().orderDown();
            }
            time++;
            System.out.println(q);
        }
        averageWait = averageWait / totalCustomers;
        averageVIP = averageVIP / servedVIP;
        System.out.println("Total customers served = " + totalCustomers);
        System.out.println("Average wait time = " + averageWait);
        System.out.println("Longest wait time = " + longWait);
        System.out.println("Longest queue = " + longQueue);
        System.out.println("Total VIPs served = " + servedVIP);
        System.out.println("Average VIP wait time = " + averageVIP);
    }
}
