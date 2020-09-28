package pers.xiaomo.demo.th;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class CFTest {

    private Executor exe = Executors.newWorkStealingPool(4);

    private Executor executor = Executors.newFixedThreadPool(4, new ThreadFactory() {

        private final AtomicInteger i = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"executor-thread-"+this.i.getAndIncrement());
        }
    });

    @Test
    public void test0(){
        CompletableFuture<?> f1 = CompletableFuture.runAsync(()->{
            System.out.println("hello.world1");
        },this.executor);

        CompletableFuture<?> f2 = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                System.out.println("get");
                return "hello,world2";
            }
        },this.executor);

        CompletableFuture<?> all = CompletableFuture.allOf(f1,f2);

        try {
            all.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试线性的执行
     */
    @Test
    public void test1(){

        //启动
        CompletableFuture<?> f1 = CompletableFuture.runAsync(()->{
            System.out.println("runAsync");
            System.out.println(Thread.currentThread());
            System.out.println("........");
        });
        //继续执行
        CompletableFuture<?> f2 =  f1.thenRunAsync(()-> {
            System.out.println("then run async");
            System.out.println(Thread.currentThread());
            System.out.println(".....................");
        });

        try {
            f2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //两个future对象不一样
        System.out.println(f1 == f2);

    }
    @Test
    public void test2(){

        //启动1
        CompletableFuture<?> f1 = CompletableFuture.runAsync(()->{
            System.out.println("task1");
            System.out.println(Thread.currentThread());
            System.out.println("........");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<?> f2 = CompletableFuture.runAsync(()->{
            System.out.println("task2");
            System.out.println(Thread.currentThread());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("........");
        });

        //继续执行
        CompletableFuture<?> f3 = CompletableFuture.allOf(f1,f2);
        f3.isCompletedExceptionally();
        try {
            f3.get();
            if(f3.isCompletedExceptionally()){
                //因为异常
            }else{
                //正常
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test3(){

        //启动1
        CompletableFuture<?> f1 = CompletableFuture.runAsync(()->{
            System.out.println("task1");
            System.out.println(Thread.currentThread());
            System.out.println("........");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<?> f2 = CompletableFuture.runAsync(()->{
            System.out.println("task2");
            System.out.println(Thread.currentThread());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("........");
        });

        //继续执行
        CompletableFuture<?> f3 = CompletableFuture.allOf(f1,f2);
        CompletableFuture<?> f4 =  f3.thenRunAsync(()->{
            System.out.println("all completable !!!");
        });

        try {
            f4.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
