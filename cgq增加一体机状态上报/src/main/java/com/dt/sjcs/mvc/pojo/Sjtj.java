package com.dt.sjcs.mvc.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Sjtj")
public class Sjtj {

    private String name;
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String names[];
    private String values1[];
    private String values2[];
    private String values3[];
    private String values4[];

    public Sjtj() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getValues1() {
        return values1;
    }

    public void setValues1(String[] values1) {
        this.values1 = values1;
    }

    public String[] getValues2() {
        return values2;
    }

    public void setValues2(String[] values2) {
        this.values2 = values2;
    }

    public String[] getValues3() {
        return values3;
    }

    public void setValues3(String[] values3) {
        this.values3 = values3;
    }

    public String[] getValues4() {
        return values4;
    }

    public void setValues4(String[] values4) {
        this.values4 = values4;
    }
}
