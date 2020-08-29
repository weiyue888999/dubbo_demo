package pers.xiaomo.demo.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;

import org.apache.dubbo.config.ReferenceConfig;
import pers.xiaomo.demo.dubbo.dto.MessageParamDto;
import pers.xiaomo.demo.dubbo.dto.MessageResultDto;
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
			ReferenceConfig<MessageService> reference = new ReferenceConfig<MessageService>();
			reference.setApplication(application);
			reference.setRegistry(registry);
			reference.setInterface(MessageService.class);
			reference.setVersion("1.0.0");
			//testMessageService(reference);
		}

		{
			ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
			reference.setApplication(application);
			reference.setRegistry(registry);
			reference.setInterface(DemoService.class);
			reference.setVersion("1.0.0");
			testDemoService(reference);
		}

	}

	private static void testDemoService(ReferenceConfig<DemoService> reference) {
		DemoService demoService = reference.get();

		{
			String result = demoService.call(1);
			System.out.println(result);
		}

		{
			String result = demoService.call("a","b");
			System.out.println(result);
		}
	}

	private static void testMessageService(ReferenceConfig<MessageService> reference) {
		MessageService messageService = reference.get();

		int sleepCount = 100;
		for(int i =0;i<sleepCount;i++){
			MessageParamDto dto = new MessageParamDto();
			dto.setId(1);
			dto.setMsg("hello,world!!!");
			MessageResultDto result = messageService.send(dto);
			System.out.println(result);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
