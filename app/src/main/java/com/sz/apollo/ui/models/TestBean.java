package com.sz.apollo.ui.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TestBean {
    @Id
    private long Id;

    private int a;
    private String bbb;
    @Generated(hash = 1042270678)
    public TestBean(long Id, int a, String bbb) {
        this.Id = Id;
        this.a = a;
        this.bbb = bbb;
    }
    @Generated(hash = 2087637710)
    public TestBean() {
    }
    public long getId() {
        return this.Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }
    public int getA() {
        return this.a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public String getBbb() {
        return this.bbb;
    }
    public void setBbb(String bbb) {
        this.bbb = bbb;
    }
}
