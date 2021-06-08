package com.common.project.config;

/**
 * 广播
 * @author by benny
 * @date on 2019/3/15.
 */

public class EventBean {
    /**
     * 消息类型
     */
    private int type;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 消息内容
     */
    private Object bean;

    public EventBean(){

    }

    public EventBean(int type) {
        this.type = type;
    }

    public EventBean(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public EventBean(int type, Object bean) {
        this.type = type;
        this.bean = bean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
