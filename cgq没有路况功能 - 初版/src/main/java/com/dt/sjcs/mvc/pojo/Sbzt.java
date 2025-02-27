package com.dt.sjcs.mvc.pojo;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Sbzt")
public class Sbzt {

    private String id;
    private String sbid;
    private String snh;
    private String rjbbh;
    private String xtsj;
    private String zdsc;
    private String dqsc;
    private String sbsj;
    private String dy;
    private String kzt;
    private String mnzt;
    private String szzt;
    private String sbwd;
    private String sim;
    private Date time;

    public Sbzt() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbid() {
        return sbid;
    }

    public void setSbid(String sbid) {
        this.sbid = sbid;
    }

    public String getSnh() {
        return snh;
    }

    public void setSnh(String snh) {
        this.snh = snh;
    }

    public String getRjbbh() {
        return rjbbh;
    }

    public void setRjbbh(String rjbbh) {
        this.rjbbh = rjbbh;
    }

    public String getXtsj() {
        return xtsj;
    }

    public void setXtsj(String xtsj) {
        this.xtsj = xtsj;
    }

    public String getZdsc() {
        return zdsc;
    }

    public void setZdsc(String zdsc) {
        this.zdsc = zdsc;
    }

    public String getDqsc() {
        return dqsc;
    }

    public void setDqsc(String dqsc) {
        this.dqsc = dqsc;
    }

    public String getSbsj() {
        return sbsj;
    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getKzt() {
        return kzt;
    }

    public void setKzt(String kzt) {
        this.kzt = kzt;
    }

    public String getMnzt() {
        return mnzt;
    }

    public void setMnzt(String mnzt) {
        this.mnzt = mnzt;
    }

    public String getSzzt() {
        return szzt;
    }

    public void setSzzt(String szzt) {
        this.szzt = szzt;
    }

    public String getSbwd() {
        return sbwd;
    }

    public void setSbwd(String sbwd) {
        this.sbwd = sbwd;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
