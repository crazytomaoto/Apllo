package com.sz.apollo.ui.utils;

import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.gen.ContactAddressBeanDao;
import com.sz.apollo.gen.UserWalletBeanDao;
import com.sz.apollo.ui.models.ContactAddressBean;
import com.sz.apollo.ui.models.UserWalletBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DaoUtil {


    /**
     * 清空所有数据
     *
     * @return
     */
    public static void deleteAll() {
        ApolloApplication.getInstance().daoSession.getContactAddressBeanDao().deleteAll();
        ApolloApplication.getInstance().daoSession.getUserWalletBeanDao().deleteAll();
    }


    /**
     * 获取联系人查询指针
     *
     * @return
     */
    public static ContactAddressBeanDao getContactDao() {
        return ApolloApplication.getInstance().daoSession.getContactAddressBeanDao();
    }

    /**
     * 查询所有联系人
     *
     * @return
     */
    public static List<ContactAddressBean> selectAllContact() {
        List<ContactAddressBean> list = getContactDao().queryBuilder().build().list();
        return list;
    }

    /**
     * 保存新的联系人
     *
     * @param bean
     */
    public static void addNewContact(ContactAddressBean bean) {
        getContactDao().insertOrReplace(bean);
    }

    /**
     * 删除联系人
     *
     * @param bean
     */
    public static void deleteContact(ContactAddressBean bean) {
        getContactDao().delete(bean);
    }

    /**
     * 删除联系人
     *
     * @param id
     */
    public static void deleteContactById(long id) {
        getContactDao().deleteByKey(id);
    }

    /**
     * 查询联系人是否已经存在
     *
     * @param address
     * @return
     */
    public static boolean isContactSaved(String address) {
        QueryBuilder<ContactAddressBean> qb = getContactDao().queryBuilder();
        qb.where(ContactAddressBeanDao.Properties.Address.eq(address));
//        qb.buildCount().count();
        return qb.buildCount().count() > 0 ? true : false;
    }

    /**
     * 查询联系人
     *
     * @param id
     * @return
     */
    public static ContactAddressBean selectContactById(long id) {
        QueryBuilder<ContactAddressBean> qb = getContactDao().queryBuilder();
        return qb.where(ContactAddressBeanDao.Properties.Id.eq(id)).unique();
    }

    /**
     * 查询联系人
     *
     * @param address
     * @return
     */
    public static ContactAddressBean selectContactByAddress(String address) {
        QueryBuilder<ContactAddressBean> qb = getContactDao().queryBuilder();
        return qb.where(ContactAddressBeanDao.Properties.Address.eq(address)).unique();
    }

    /**
     * 查询联系人是否已经存在
     *
     * @param address
     * @return
     */
    public static List<ContactAddressBean> selectContactFromAddress(String address) {
        QueryBuilder<ContactAddressBean> qb = getContactDao().queryBuilder();
        qb.where(ContactAddressBeanDao.Properties.Address.like(address + "%"));
        return qb.list();
    }

    /**
     * 更新数据
     *
     * @param bean
     * @return
     */
    public static void updateContacts(ContactAddressBean bean) {
        getContactDao().save(bean);
    }


    /**
     * 钱包查询指针
     *
     * @return
     */
    public static UserWalletBeanDao getUserWalletDao() {
        return ApolloApplication.getInstance().daoSession.getUserWalletBeanDao();
    }

    /**
     * 查询所有钱包
     *
     * @return
     */
    public static List<UserWalletBean> selectAllWallet() {
        List<UserWalletBean> list = getUserWalletDao().queryBuilder().build().list();
        return list;
    }

    /**
     * 删除钱包
     *
     * @param bean
     */
    public static void deleteWallet(UserWalletBean bean) {
        getUserWalletDao().delete(bean);
    }

    /**
     * 更新数据
     *
     * @param bean
     * @return
     */
    public static void updateWallet(UserWalletBean bean) {
        getUserWalletDao().save(bean);
    }

    /**
     * 添加钱包
     *
     * @param bean
     * @return
     */
    public static void addWallet(UserWalletBean bean) {
        getUserWalletDao().insertOrReplace(bean);
    }

    /**
     * 查询当前钱包
     *
     * @param
     * @return
     */
    public static UserWalletBean selectNowWallet() {
        QueryBuilder<UserWalletBean> qb = getUserWalletDao().queryBuilder();
        return qb.where(UserWalletBeanDao.Properties.IsNowWallet.eq("1")).unique();
    }

    /**
     * 根据地址查询钱包
     *
     * @param
     * @return
     */
    public static UserWalletBean selectWalletByAddress(String address) {
        QueryBuilder<UserWalletBean> qb = getUserWalletDao().queryBuilder();
        return qb.where(UserWalletBeanDao.Properties.Address.eq(address)).unique();
    }

    /**
     * 查询所有以太坊钱包或者EOS钱包
     *
     * @param
     * @return
     */
    public static List<UserWalletBean> selectAllWalletByType(String walletType) {
        QueryBuilder<UserWalletBean> qb = getUserWalletDao().queryBuilder();
        if (walletType.equals(Constant.TYPE_ETH)) {
            return qb.where(UserWalletBeanDao.Properties.Type.eq(Constant.TYPE_ETH)).build().list();
        } else {
            return qb.where(UserWalletBeanDao.Properties.Type.eq(Constant.TYPE_APOLLO)).build().list();
        }
    }
}
