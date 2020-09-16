package pers.xiaomo.demo.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import pers.xiaomo.demo.dubbo.common.Result;
import pers.xiaomo.demo.dubbo.service.MessageService;

public class ConsumerMain3 {

    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setQosEnable(false);
        application.setName("ConsumerMain");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        final ReferenceConfig<MessageService> reference = new ReferenceConfig<MessageService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(MessageService.class);
        reference.setVersion("1.0.0");

        MessageService messageService = reference.get();
        Result<String> result = messageService.send("hello,world!!!");
        System.out.println(result);
    }
}
