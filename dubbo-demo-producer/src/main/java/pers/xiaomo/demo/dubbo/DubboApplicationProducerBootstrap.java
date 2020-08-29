package pers.xiaomo.demo.dubbo;

import pers.xiaomo.demo.dubbo.service.MessageService;
import pers.xiaomo.demo.dubbo.service.impl.MessageServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DubboApplicationProducerBootstrap {

    public static void main(String[] args) {

        MessageService messageService = new MessageServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setQosEnable(false);
        application.setName("ProducerMain");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        //registry.setProtocol("zookeeper://127.0.0.1:2181");

        // 服务提供者协议配置
        List<ProtocolConfig> protocolConfigList = new ArrayList<>();

        {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("grpc");
            protocol.setThreads(4);
            protocolConfigList.add(protocol);

        }

        {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("dubbo");
            protocol.setPort(12345);
            protocol.setThreads(16);
            protocol.setIothreads(2);
            protocol.setKeepAlive(true);
            protocol.setThreadpool("dubbo-thread-pool");
            protocol.setAccepts(4);
            protocolConfigList.add(protocol);

        }

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        ServiceConfig<MessageService> service = new ServiceConfig<MessageService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocols(protocolConfigList);
        service.setInterface(MessageService.class);
        service.setRef(messageService);
        service.setExport(true);
        service.setDelay(1000);
        service.setVersion("1.0.0");

        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();
        dubboBootstrap.service(service);
        dubboBootstrap.exportAsync();
        dubboBootstrap.start();

        try {
            TimeUnit.SECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dubboBootstrap.stop();
    }
}
