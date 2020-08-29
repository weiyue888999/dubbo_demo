package pers.xiaomo.demo.dubbo.ext;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Handler {

    void handle();
}
