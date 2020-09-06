package pers.xiaomo.demo.dubbo.common;

import java.io.Serializable;

/**
 * 结果对象
 * @param <T>
 */
public class Result<T> implements Serializable {

    private boolean success;

    private String msg;

    private T data;

    public boolean success() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
