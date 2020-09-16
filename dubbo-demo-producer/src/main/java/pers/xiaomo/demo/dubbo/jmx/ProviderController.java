package pers.xiaomo.demo.dubbo.jmx;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import pers.xiaomo.common.BaseLifecycleMBean;
import pers.xiaomo.demo.dubbo.service.MessageService;
import pers.xiaomo.demo.dubbo.service.impl.MessageServiceImpl;

public class ProviderController extends BaseLifecycleMBean implements ProviderControllerMBean{

    @Override
    public void startRegisteService() {

        System.out.println(Thread.currentThread());

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setQosEnable(false);
        application.setName("ProducerMain");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setThreads(16);
        protocol.setIothreads(2);
        protocol.setKeepAlive(true);
        //protocol.setThreadpool("dubbo-thread-pool");
        protocol.setAccepts(4);

        ServiceConfig<MessageService> service = new ServiceConfig<MessageService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol);
        service.setInterface(MessageService.class);
        service.setRef(new MessageServiceImpl());
        service.setExport(true);
        service.setDelay(1000);
        service.setVersion("1.0.0");

        service.export();
    }

    @Override
    public void stopRegisterService() {
        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();
        dubboBootstrap.stop();
        dubboBootstrap.destroy();
    }
}
