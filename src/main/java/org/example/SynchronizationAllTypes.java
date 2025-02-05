package org.example;

import java.util.ArrayList;
import java.util.List;

public class SynchronizationAllTypes {
    private int sum = 0;
    private static int cnt = 0;
    private int i = 0;
    private List<Integer> list = new ArrayList<>();

    public synchronized void calculate() {
        setSum(getSum() + 1);
    }

    public static synchronized void counter() {
        setCnt(getCnt() + 1);
    }

    public void listAdder() {
        synchronized (this) {
            list.add(i++);
        }
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public static int getCnt() {
        return cnt;
    }

    public static void setCnt(int cnt) {
        SynchronizationAllTypes.cnt = cnt;
    }

    public List<Integer> getList() {
        return list;
    }
}
