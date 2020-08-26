package com.zfsoft.serviceImpl;

import com.zfsoft.dto.MessageParamDto;
import com.zfsoft.dto.MessageResultDto;
import com.zfsoft.service.MessageService;

public class MessageServiceImpl implements MessageService{

	@Override
	public MessageResultDto send(MessageParamDto dto) {
		return new MessageResultDto(dto.getMsg());
	}
}
