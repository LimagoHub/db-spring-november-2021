package de.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
       new Main().go();

    }

    private void go() {
        try {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            List<Future<Long>> futures = new ArrayList<>();
            ExecutorService service = Executors.newFixedThreadPool(availableProcessors);
//            for(int i = 0 ; i < availableProcessors * 2 ;i++){
//                service.execute(this::demo);
//            }
            for(int i = 0 ; i < availableProcessors;i++) {
                futures.add(service.submit(new MyCallable()));
            }
            service.shutdown();

            futures.stream().map(this::convert).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public long convert(Future<Long> f) {
        try {
            return f.get();
        } catch (Exception e) {
            return 0;
        }
    }

    public void demo() {
        try {
            Thread.sleep((long)(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId());
    }

    class MyCallable implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long value = (long)(Math.random()*1000);
            try {
                Thread.sleep(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value;
        }
    }
}
