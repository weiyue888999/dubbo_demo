package pers.xiaomo.demo.dubbo;

import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import pers.xiaomo.demo.dubbo.service.DemoService;
import pers.xiaomo.demo.dubbo.service.MessageService;
import pers.xiaomo.demo.dubbo.service.impl.DemoServiceImpl;
import pers.xiaomo.demo.dubbo.service.impl.MessageServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DubboApplicationProduderSingleMain {

    public static void main(String[] args) {
        List<RegistryConfig> registryConfigList = new ArrayList<>();
        {
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress("zookeeper://127.0.0.1:2181");
        }

        List<ProtocolConfig> protocolConfigList = new ArrayList<>();

        {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("dubbo");
            protocol.setThreads(4);
            protocolConfigList.add(protocol);

        }

        List<ServiceConfig> serviceConfigList = new ArrayList<>();

        {
            MessageService messageService = new MessageServiceImpl();
            ServiceConfig<MessageService> service = new ServiceConfig<>();
            service.setRegistries(registryConfigList);
            service.setProtocols(protocolConfigList);
            service.setInterface(MessageService.class);
            service.setRef(messageService);
            service.setExport(true);
            service.setVersion("1.0.0");

            serviceConfigList.add(service);
        }

        for(ServiceConfig sc : serviceConfigList){
            sc.export();
        }

        try {
            TimeUnit.SECONDS.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
