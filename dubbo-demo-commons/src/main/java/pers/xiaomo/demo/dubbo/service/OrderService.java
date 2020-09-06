package pers.xiaomo.demo.dubbo.service;

import pers.xiaomo.demo.dubbo.common.Result;
import pers.xiaomo.demo.dubbo.dto.OrderDto;

public interface OrderService {

    Result<OrderDto> queryOrder();
}
