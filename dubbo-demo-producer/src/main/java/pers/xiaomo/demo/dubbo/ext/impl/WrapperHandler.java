package pers.xiaomo.demo.dubbo.ext.impl;

import pers.xiaomo.demo.dubbo.ext.Handler;

public class WrapperHandler implements Handler {

    @Override
    public void handle() {
        System.out.println("wrapperHandler");
    }
}
