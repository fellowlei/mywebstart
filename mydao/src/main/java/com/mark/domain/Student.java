package com.mark.domain;

/**
 * Created by fellowlei on 2018/5/10.
 */
public class Student {
    private long id;
    private String name;
    private String addr;

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
