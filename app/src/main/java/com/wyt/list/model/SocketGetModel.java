package com.wyt.list.model;

/**
 * Created by Won on 2016/10/18.
 */

public class SocketGetModel {

    /**
     * type : login
     * request_time : 1476775785708
     * sending_time : 1476775802
     * code : 1
     * data : {"cataid":0,"nianji":0,"password":"","pic":"","schooluserno":150116,"studentno":"970177","userid":"13416535747","username":"\u6e292","userno":2200169,"xueduan":0,"xueke":0,"islogin":"1"}
     */

    private String type;
    private String request_time;
    private String sending_time;
    private String code;
    private String data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

    public String getSending_time() {
        return sending_time;
    }

    public void setSending_time(String sending_time) {
        this.sending_time = sending_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
