package com.zfsoft;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;

import com.zfsoft.dto.MessageParamDto;
import com.zfsoft.dto.MessageResultDto;
import com.zfsoft.service.MessageService;
import org.apache.dubbo.config.ReferenceConfig;

public class ConsumerMain {

	public static void main(String[] args) {

		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setQosEnable(false);
		application.setName("ConsumerMain");

		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");

		// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

		// 引用远程服务
		ReferenceConfig<MessageService> reference = new ReferenceConfig<MessageService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(MessageService.class);
		reference.setVersion("1.0.0");

		// 和本地bean一样使用messageService
		MessageService messageService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

		MessageParamDto dto = new MessageParamDto();
		dto.setId(1);
		dto.setMsg("hello,world!!!");
		MessageResultDto result = messageService.send(dto);
		System.out.println(result);
	}
}
