package com.wyt.list.model;

/**
 * Created by Won on 2016/10/18.
 */

public class SocketLoginModel {

    /**
     * cataid : 0
     * nianji :
     * password : e10adc3949ba59abbe56e057f20f883e
     * pic :
     * schooluserno :
     * studentno :
     * userid : 520159
     * username :
     * userno :
     * xueduan :
     * xueke :
     */

    private String cataid;
    private String nianji;
    private String password;
    private String pic;
    private String schooluserno;
    private String studentno;
    private String userid;
    private String username;
    private String userno;
    private String xueduan;
    private String xueke;

    public SocketLoginModel(String cataid, String nianji, String password, String pic, String schooluserno, String studentno, String userid, String username, String userno, String xueduan, String xueke) {
        this.cataid = cataid;
        this.nianji = nianji;
        this.password = password;
        this.pic = pic;
        this.schooluserno = schooluserno;
        this.studentno = studentno;
        this.userid = userid;
        this.username = username;
        this.userno = userno;
        this.xueduan = xueduan;
        this.xueke = xueke;
    }

    public SocketLoginModel() {
    }

    public String getCataid() {
        return cataid;
    }

    public void setCataid(String cataid) {
        this.cataid = cataid;
    }

    public String getNianji() {
        return nianji;
    }

    public void setNianji(String nianji) {
        this.nianji = nianji;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSchooluserno() {
        return schooluserno;
    }

    public void setSchooluserno(String schooluserno) {
        this.schooluserno = schooluserno;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getXueduan() {
        return xueduan;
    }

    public void setXueduan(String xueduan) {
        this.xueduan = xueduan;
    }

    public String getXueke() {
        return xueke;
    }

    public void setXueke(String xueke) {
        this.xueke = xueke;
    }
}
