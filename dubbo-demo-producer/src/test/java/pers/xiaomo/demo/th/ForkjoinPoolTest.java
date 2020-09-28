package pers.xiaomo.demo.th;


import javax.sound.midi.SysexMessage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算1-10000个随机数的和
 * 每个子任务只计算100个
 */
public class ForkjoinPoolTest {

    private static class MyForkJoinTask extends RecursiveTask<Integer>{

        private Integer start;
        private Integer end;

        public MyForkJoinTask(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            if(end - start > 100000){
                MyForkJoinTask left = new MyForkJoinTask(start,start+100000);
                MyForkJoinTask right = new MyForkJoinTask(start+100001,end);
                MyForkJoinTask.invokeAll(left,right);
                return left.join() + right.join();
            }else{
                int sum = 0;
                for(int i = start;i<=end;i++){
                    sum += i;
                }
                return sum;
            }
        }
    }

    public static void main(String[] args) {

        int max = 100000000;

        long now = System.currentTimeMillis();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> task = pool.submit(new MyForkJoinTask(0,max));
        int x = task.join();
        long t = System.currentTimeMillis() - now;
        System.out.println(x);
        System.out.println(t);

        now = System.currentTimeMillis();
        int sum = 0;
        for(int i = 0;i<max;i++){
            sum += i;
        }
        t = System.currentTimeMillis() - now;
        System.out.println(sum);
        System.out.println(t);
    }
}

