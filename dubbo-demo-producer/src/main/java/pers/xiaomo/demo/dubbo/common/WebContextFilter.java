package pers.xiaomo.demo.dubbo.common;

import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebContextFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(WebContextFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        log.debug("trace invoke ....");

        return invoker.invoke(invocation);
    }
}
