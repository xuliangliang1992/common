package com.icloudwhale.cloudpos.base.event;

/**
 * @author xuliangliang
 * @date 2019-09-11
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class EventMessage<T> {
    private int code;
    private T data;

    public EventMessage(int code) {
        this.code = code;
    }

    public EventMessage(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
