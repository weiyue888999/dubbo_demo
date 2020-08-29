package pers.xiaomo.demo.dubbo.service;

import pers.xiaomo.demo.dubbo.dto.MessageParamDto;
import pers.xiaomo.demo.dubbo.dto.MessageResultDto;

public interface MessageService {
	
	MessageResultDto sendThrowException(MessageParamDto dto);

	MessageResultDto send(MessageParamDto dto);
}
