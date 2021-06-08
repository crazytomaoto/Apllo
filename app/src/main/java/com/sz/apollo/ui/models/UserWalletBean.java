package com.sz.apollo.ui.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.OrderBy;

import java.io.Serializable;

@Entity
public class UserWalletBean implements Serializable {
    static final long serialVersionUID = -15515456L;
    @Id
    private Long Id;
    @NotNull
    private String type;//钱包类型---阿波罗/以太坊
    private String address;
    private String phrase;
    private String pk;
    private String publicKey;
    private String keystore;
    private String remark;//钱包名称
    private String isBackedUp;//是否已备份  1已备份 0未备份
    private String isNowWallet;//是否是当前选中的钱包  1选中当前 0未选中
    private String walletExistWay;//a 创建 b导入  1 助记词导入 2 私钥导入  3keystore导入
    private String isAddUSDT;//1 添加 0 未添加

    @Generated(hash = 99231484)
    public UserWalletBean(Long Id, @NotNull String type, String address, String phrase, String pk,
            String publicKey, String keystore, String remark, String isBackedUp, String isNowWallet,
            String walletExistWay, String isAddUSDT) {
        this.Id = Id;
        this.type = type;
        this.address = address;
        this.phrase = phrase;
        this.pk = pk;
        this.publicKey = publicKey;
        this.keystore = keystore;
        this.remark = remark;
        this.isBackedUp = isBackedUp;
        this.isNowWallet = isNowWallet;
        this.walletExistWay = walletExistWay;
        this.isAddUSDT = isAddUSDT;
    }

    @Generated(hash = 357398999)
    public UserWalletBean() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPk() {
        return this.pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getKeystore() {
        return this.keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsBackedUp() {
        return this.isBackedUp;
    }

    public void setIsBackedUp(String isBackedUp) {
        this.isBackedUp = isBackedUp;
    }

    public String getIsNowWallet() {
        return this.isNowWallet;
    }

    public void setIsNowWallet(String isNowWallet) {
        this.isNowWallet = isNowWallet;
    }

    public String getWalletExistWay() {
        return this.walletExistWay;
    }

    public void setWalletExistWay(String walletExistWay) {
        this.walletExistWay = walletExistWay;
    }

    public String getIsAddUSDT() {
        return this.isAddUSDT;
    }

    public void setIsAddUSDT(String isAddUSDT) {
        this.isAddUSDT = isAddUSDT;
    }
}
