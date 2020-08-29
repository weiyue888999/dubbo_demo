package pers.xiaomo.demo.dubbo.service.impl;

import pers.xiaomo.demo.dubbo.dto.MessageParamDto;
import pers.xiaomo.demo.dubbo.dto.MessageResultDto;
import pers.xiaomo.demo.dubbo.dto.MessageResultException;
import pers.xiaomo.demo.dubbo.service.MessageService;

public class MessageServiceImpl implements MessageService{

	@Override
	public MessageResultDto send(MessageParamDto dto) {
		return new MessageResultDto(dto.getMsg());
	}

	@Override
	public MessageResultDto sendThrowException(MessageParamDto dto) {
		throw new MessageResultException();
	}
}
