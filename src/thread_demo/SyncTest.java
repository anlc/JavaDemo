package thread_demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SyncTest {

    private List<String> data;

    public SyncTest() {
        data = Collections.synchronizedList(new ArrayList<>());
    }

    public void startAdd() {
        new Thread(() -> {
            add();
        }).start();
    }

    public void print() {
        System.out.println(data.toString());
    }

    public synchronized void printList() {
        System.out.println(data.toString());
    }

    public synchronized void add() {
        try {
            System.out.println("start  add");
            for (int i = 0; i < 50; i++) {
                TimeUnit.MILLISECONDS.sleep(150);
                readAdd(i);
            }
            System.out.println("end  add : " + data.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void readAdd(int i) {
        data.add("item " + i);
    }

    public static void main(String[] args) {
        SyncTest test = new SyncTest();
        test.startAdd();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.print();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.printList();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.printList();
        }).start();
    }
}
