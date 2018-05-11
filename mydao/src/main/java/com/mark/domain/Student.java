package com.mark.domain;

import com.mark.util.MurmurHash;

/**
 * Created by fellowlei on 2018/5/10.
 */
public class Student {
    private long id;
    private String name;
    private String addr;
    private String tbName;

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
        initTbName();
        System.out.println("####" + tbName);
    }

    public void initTbName() {
        this.tbName = "student_" + (Math.abs(MurmurHash.hash64(name)) % 3);
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

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }
}
