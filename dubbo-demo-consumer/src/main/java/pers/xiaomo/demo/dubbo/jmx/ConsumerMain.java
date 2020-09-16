package pers.xiaomo.demo.dubbo.jmx;


import java.util.concurrent.TimeUnit;

public class ConsumerMain {

	public static void main(String[] args) {

		ConsumerController cc = new ConsumerController();
		cc.start();
		try {
			TimeUnit.SECONDS.sleep(999999999);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cc.stop();
	}
}
