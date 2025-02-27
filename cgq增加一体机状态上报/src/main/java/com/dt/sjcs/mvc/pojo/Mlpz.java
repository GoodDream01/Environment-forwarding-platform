package com.dt.sjcs.mvc.pojo;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Mlpz")
public class Mlpz {

    private String id;
    private String sbid;
    private String value;
    private String text;
    private String zdsj;
    private String ym;
    private String dk;
    private Date xtsj;

    public Mlpz() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getZdsj() {
        return zdsj;
    }

    public void setZdsj(String zdsj) {
        this.zdsj = zdsj;
    }


    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getDk() {
        return dk;
    }

    public void setDk(String dk) {
        this.dk = dk;
    }

    public Date getXtsj() {
        return xtsj;
    }

    public void setXtsj(Date xtsj) {
        this.xtsj = xtsj;
    }

}
