package pers.xiaomo.demo.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboApplicationSpringProducerMain {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}
