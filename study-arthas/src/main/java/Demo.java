import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {
    public static Map<String,String> map = new HashMap<String, String>();
    static {
        map.put("zhang","sdfsdf");
        map.put("rao","ss");
    }
    static class Counter {
        private static AtomicInteger count = new AtomicInteger(0);
        public static void increment() {
            count.incrementAndGet();
            System.out.println("fffff");
        }
        public static int value() {
            return count.get();
        }
    }

    public static void execute() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");

        List<String> list2 = new ArrayList<String>();
        list2.add("c");
        list2.add("d");

        int len = add(list, list2);
    }

    //watch Demo add params[1].size
    private static int add(List<String> list, List<String> list2) {
        int i = 10;
        while (i >= 0) {
            try {
                hehe(i);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            i--;
        }
        list.addAll(list2);
        addStr("rao",10);
        return list.size();
    }

    private static void hehe(int i) {
        if (i == 0) {
            throw new RuntimeException("ZERO");
        }
    }

    //watch Demo addStr "{params,returnObj}" "params[0].equals('rao')&&params[1]==10" -x 3
    //其实这实际上就是java代码调用
    private static String addStr(String name,int age){
        return name + "" + age;
    }

    public static void main(String[] args) throws InterruptedException {
        final List<String> strings = new ArrayList<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String string = ""+new Random().nextInt();
                    System.out.println(string);
                    strings.add(string);
                }
            }
        },"dead task").start();
        while (true) {
            execute();
            Counter.increment();
            System.out.println("counter: " + Counter.value());
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
