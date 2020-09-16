package pers.xiaomo.demo.dubbo.jmx;

import java.util.concurrent.TimeUnit;

public class ProviderMain {

    public static void main(String[] args) {

        ProviderController pc = new ProviderController();
        pc.start();

        try {
            TimeUnit.SECONDS.sleep(999999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pc.stop();
    }
}
