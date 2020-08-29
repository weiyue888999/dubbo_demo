package pers.xiaomo.demo.dubbo.dto;

import java.io.Serializable;

public class MessageParamDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String msg;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MessageParamDto [id=" + id + ", msg=" + msg + "]";
	}
	
}
