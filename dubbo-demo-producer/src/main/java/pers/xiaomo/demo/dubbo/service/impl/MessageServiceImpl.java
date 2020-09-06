package pers.xiaomo.demo.dubbo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.xiaomo.demo.dubbo.common.Result;
import pers.xiaomo.demo.dubbo.service.MessageService;

public class MessageServiceImpl implements MessageService{

	private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Override
	public Result<String> send(String msg) {
		Result<String> result = new Result();
		result.setData(msg);
		result.setSuccess(true);
		return result;
	}
}
