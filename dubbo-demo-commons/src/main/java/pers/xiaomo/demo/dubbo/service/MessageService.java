package pers.xiaomo.demo.dubbo.service;

import pers.xiaomo.demo.dubbo.common.Result;

import java.util.concurrent.CompletableFuture;

/**
 * 消息服务
 */
public interface MessageService {
	
	Result<String> send(String msg);

	CompletableFuture<Result<String>> sendAsync(String msg);
}
