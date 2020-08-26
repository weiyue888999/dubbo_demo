package com.zfsoft.service;

import com.zfsoft.dto.MessageParamDto;
import com.zfsoft.dto.MessageResultDto;

public interface MessageService {

	MessageResultDto send(MessageParamDto dto);
}
