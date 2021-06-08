package com.sz.apollo.ui.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ContactAddressBean {
    @Id
    private Long Id;
    @NotNull
    private String address;
    @NotNull
    private String remark;

    @Generated(hash = 842045586)
    public ContactAddressBean(Long Id, @NotNull String address,
                              @NotNull String remark) {
        this.Id = Id;
        this.address = address;
        this.remark = remark;
    }

    @Generated(hash = 2128220939)
    public ContactAddressBean() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
