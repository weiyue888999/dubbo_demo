package pers.xiaomo.demo.dubbo.service.impl;

import org.apache.dubbo.rpc.RpcContext;
import pers.xiaomo.demo.dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {

    @Override
    public String call(String a, String b) {
        String uid = RpcContext.getContext().getAttachment("uid");
        System.out.println(uid);
        return a+":"+b;
    }

    @Override
    public String call(int num) {
        return ""+num;
    }

}
