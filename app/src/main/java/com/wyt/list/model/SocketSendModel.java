package com.wyt.list.model;

/**
 * Created by Won on 2016/10/15.
 */

public class SocketSendModel {

    /**
     * data : {"cataid":"0","nianji":"","password":"e10adc3949ba59abbe56e057f20f883e","pic":"","schooluserno":"","studentno":"","userid":"520159","username":"","userno":"","xueduan":"","xueke":""}
     * sendtime : 1476768183026
     * type : login
     */

    private String data;
    private String sendtime;
    private String type;

    public SocketSendModel(String data, String sendtime, String type) {
        this.data = data;
        this.sendtime = sendtime;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
