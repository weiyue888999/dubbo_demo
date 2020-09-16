package pers.xiaomo.demo.dubbo.jmx;

public interface ConsumerControllerMBean {

    void startReferService();

    void stopReferService();

    void sendMessage(String msg);
}
