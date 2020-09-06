package pers.xiaomo.demo.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;

import org.apache.dubbo.config.ReferenceConfig;
import pers.xiaomo.demo.dubbo.common.Result;
import pers.xiaomo.demo.dubbo.service.DemoService;
import pers.xiaomo.demo.dubbo.service.MessageService;


import java.util.concurrent.TimeUnit;

public class ConsumerMain {

	public static void main(String[] args) {

		ApplicationConfig application = new ApplicationConfig();
		application.setQosEnable(false);
		application.setName("ConsumerMain");

		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");

		{
			final ReferenceConfig<MessageService> reference = new ReferenceConfig<MessageService>();
			reference.setApplication(application);
			reference.setRegistry(registry);
			reference.setInterface(MessageService.class);
			reference.setVersion("1.0.0");

			new Thread(new Runnable() {
				@Override
				public void run() {
					testMessageService(reference);
				}
			}).start();
		}

		{
			final ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
			reference.setApplication(application);
			reference.setRegistry(registry);
			reference.setInterface(DemoService.class);
			reference.setVersion("1.0.0");
			new Thread(new Runnable() {
				@Override
				public void run() {
					testDemoService(reference);
				}
			}).start();
		}
	}

	private static void testDemoService(ReferenceConfig<DemoService> reference) {
		DemoService demoService = reference.get();
		int callCount = 100;
		while(callCount > 0){
			{
				String result = demoService.call(1);
				System.out.println(result);
			}

			{
				String result = demoService.call("a","b");
				System.out.println(result);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			callCount --;
		}
	}

	private static void testMessageService(ReferenceConfig<MessageService> reference) {
		MessageService messageService = reference.get();

		int sleepCount = 100;
		for(int i =0;i<sleepCount;i++){
			Result<String> result = messageService.send("Hello,world"+i);
			System.out.println(result);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
