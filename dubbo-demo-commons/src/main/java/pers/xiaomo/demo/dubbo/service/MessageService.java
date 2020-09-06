package pers.xiaomo.demo.dubbo.service;

import pers.xiaomo.demo.dubbo.common.Result;

/**
 * 消息服务
 */
public interface MessageService {
	
	Result<String> send(String msg);
}
