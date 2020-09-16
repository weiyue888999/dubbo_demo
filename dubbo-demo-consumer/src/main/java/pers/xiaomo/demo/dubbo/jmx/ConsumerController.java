package pers.xiaomo.demo.dubbo.jmx;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import pers.xiaomo.common.BaseLifecycleMBean;
import pers.xiaomo.demo.dubbo.service.MessageService;

public class ConsumerController extends BaseLifecycleMBean implements ConsumerControllerMBean{

    private ReferenceConfig<MessageService> reference;

    private MessageService messageService;

    public ConsumerController() {

        ApplicationConfig application = new ApplicationConfig();
        application.setQosEnable(false);
        application.setName("ConsumerMain");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        this.reference = new ReferenceConfig<MessageService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(MessageService.class);
        reference.setVersion("1.0.0");
    }

    @Override
    public void startReferService() {
        this.messageService = reference.get();
    }

    @Override
    public void stopReferService() {
        this.reference.destroy();
        this.messageService = null;
    }

    @Override
    public void sendMessage(String msg) {

        if(this.messageService != null){
            Object o = this.messageService.send(msg);
            System.out.println(o);
        }else{
            System.out.println("messageService is null!!!");
        }
    }
}
