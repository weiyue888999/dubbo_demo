package pers.xiaomo.demo.dubbo.dto;

import java.io.Serializable;

public class MessageResultDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String result;
	
	public MessageResultDto(String result) {
		super();
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "MessageResultDto [result=" + result + "]";
	}
}
