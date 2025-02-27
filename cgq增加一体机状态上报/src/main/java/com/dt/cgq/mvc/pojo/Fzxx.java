package com.dt.cgq.mvc.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Fzxx")
public class Fzxx {

    private String  id;
    private String  name;
    private String  count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Fzxx() {
        super();
    }

    @Override
    public String toString() {
        return "Fzxx{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
