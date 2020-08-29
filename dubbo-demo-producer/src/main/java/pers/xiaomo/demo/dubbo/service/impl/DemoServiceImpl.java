package pers.xiaomo.demo.dubbo.service.impl;

import pers.xiaomo.demo.dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String call(String a, String b) {
        return a+":"+b;
    }

    @Override
    public String call(int num) {
        return ""+num;
    }
}
