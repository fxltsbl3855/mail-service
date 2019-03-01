package com.yto.tech.mail.wx;

import java.util.HashMap;
import java.util.Map;

public class WXMsg {
    private String touser;
    private String toparty;
    private String totag;
    private String msgtype = "text";
    private int agentid = 0;
    private Map text;
    private int safe = 0;

    public WXMsg(String content, String user) {
        this.touser = user;
        this.msgtype = "text";
        this.agentid = 0;
        this.text = new HashMap(1);
        this.text.put("content", content);
        this.safe = 0;
    }

    public WXMsg(String content) {
        this.touser = "@all";
        this.msgtype = "text";
        this.agentid = 0;
        this.text = new HashMap(1);
        this.text.put("content", content);
        this.safe = 0;
    }

    public String getTouser() {
        return this.touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return this.toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return this.totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return this.msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public int getAgentid() {
        return this.agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public Map getText() {
        return this.text;
    }

    public void setText(Map text) {
        this.text = text;
    }

    public int getSafe() {
        return this.safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }
}
