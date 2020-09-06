package pers.xiaomo.demo.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import pers.xiaomo.demo.dubbo.service.DemoService;
import pers.xiaomo.demo.dubbo.service.MessageService;
import pers.xiaomo.demo.dubbo.service.impl.DemoServiceImpl;
import pers.xiaomo.demo.dubbo.service.impl.MessageServiceImpl;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DubboApplicationProducerMain {

    public static void main(String[] args) {


        ApplicationConfig application = new ApplicationConfig();
        application.setQosEnable(false);
        application.setName("ConsumerMain");

        List<RegistryConfig> registryConfigList = new ArrayList<>();
        {
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress("zookeeper://127.0.0.1:2181");
            registryConfigList.add(registry);
        }

        List<ProtocolConfig> protocolConfigList = new ArrayList<>();

        /**
        {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("injvm");
            protocol.setThreads(4);
            protocolConfigList.add(protocol);
        }
        {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("rmi");
            protocol.setThreads(4);
            protocolConfigList.add(protocol);
        }

        {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("grpc");
            protocol.setThreads(4);
            protocolConfigList.add(protocol);
        }
         **/
        {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("dubbo");
            protocol.setThreads(4);
            protocolConfigList.add(protocol);

        }

        List<ServiceConfig> serviceConfigList = new ArrayList<>();
        {
            DemoService demoServiceImpl = new DemoServiceImpl();
            ServiceConfig<DemoService> service = new ServiceConfig<>();
            service.setApplication(application);
            service.setTimeout(100000);
            service.setRegistries(registryConfigList);
            service.setProtocols(protocolConfigList);
            service.setInterface(DemoService.class);
            service.setRef(demoServiceImpl);
            service.setExport(true);
            service.setVersion("1.0.0");
            service.setFilter("webContextFilter");
            //service.setToken(token);
            //service.setToken(true);

            serviceConfigList.add(service);
        }

        {
            MessageService messageService = new MessageServiceImpl();
            ServiceConfig<MessageService> service = new ServiceConfig<>();
            service.setApplication(application);
            service.setTimeout(100000);
            service.setRegistries(registryConfigList);
            service.setProtocols(protocolConfigList);
            service.setInterface(MessageService.class);
            service.setRef(messageService);
            service.setExport(true);
            service.setVersion("1.0.0");
            service.setFilter("webContextFilter");

            serviceConfigList.add(service);
        }

        for(ServiceConfig sc : serviceConfigList){
            sc.export();
            //sc.unexport();
        }

        try {
            TimeUnit.SECONDS.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
