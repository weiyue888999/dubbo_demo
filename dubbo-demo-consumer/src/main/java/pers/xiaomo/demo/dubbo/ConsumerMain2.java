package pers.xiaomo.demo.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import pers.xiaomo.demo.dubbo.service.DemoService;
import pers.xiaomo.demo.dubbo.service.MessageService;

import java.util.concurrent.TimeUnit;

public class ConsumerMain2 {

    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setQosEnable(false);
        application.setName("ConsumerMain");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        final ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(DemoService.class);
        reference.setVersion("1.0.0");

        DemoService demoService = reference.get();
        String result = demoService.call(1);
        System.out.println(result);
    }
}
