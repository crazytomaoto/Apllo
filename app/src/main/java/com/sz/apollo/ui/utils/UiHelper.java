package com.sz.apollo.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.zxing.client.android.ui.CaptureActivity;
import com.google.zxing.client.android.utils.ScanUtils;
import com.sz.apollo.ui.activity.AboutUsAcy;
import com.sz.apollo.ui.activity.AddAddressBookAcy;
import com.sz.apollo.ui.activity.AddressBookAcy;
import com.sz.apollo.ui.activity.BackupEthAcy;
import com.sz.apollo.ui.activity.BackupPKOrKeyAcy;
import com.sz.apollo.ui.activity.BackupPhraseAcy;
import com.sz.apollo.ui.activity.ChangeCardNameAcy;
import com.sz.apollo.ui.activity.ChangePinAcy;
import com.sz.apollo.ui.activity.ChooseTypeAcy;
import com.sz.apollo.ui.activity.ConfirmPhraseAcy;
import com.sz.apollo.ui.activity.ContactDetailAcy;
import com.sz.apollo.ui.activity.ChooseApolloWalletWayAcy;
import com.sz.apollo.ui.activity.CreateApolloWalletSuccessAcy;
import com.sz.apollo.ui.activity.CreateEthSuccessAcy;
import com.sz.apollo.ui.activity.CreateEthWalletOneAcy;
import com.sz.apollo.ui.activity.FlashExchangeAotAcy;
import com.sz.apollo.ui.activity.FlashExchangeNboAcy;
import com.sz.apollo.ui.activity.FlashRecordAcy;
import com.sz.apollo.ui.activity.HomepageActivity;
import com.sz.apollo.ui.activity.ImportApolloAcy;
import com.sz.apollo.ui.activity.ImportEthAcy;
import com.sz.apollo.ui.activity.LanguageAcy;
import com.sz.apollo.ui.activity.ManageWalletAcy;
import com.sz.apollo.ui.activity.MiningPoolApolloAcy;
import com.sz.apollo.ui.activity.MiningPoolDdaoAcy;
import com.sz.apollo.ui.activity.MiningPoolLonAcy;
import com.sz.apollo.ui.activity.MiningPoolNBOAcy;
import com.sz.apollo.ui.activity.MiningPowerAcy;
import com.sz.apollo.ui.activity.MoreTokenAcy;
import com.sz.apollo.ui.activity.MoreTokenAcyTwo;
import com.sz.apollo.ui.activity.NoPassPayAcy;
import com.sz.apollo.ui.activity.NoticeAllAcy;
import com.sz.apollo.ui.activity.PaymentAotAcy;
import com.sz.apollo.ui.activity.PowerDetailAcy;
import com.sz.apollo.ui.activity.ProfitDetailAcy;
import com.sz.apollo.ui.activity.RecordAccountAddressAcy;
import com.sz.apollo.ui.activity.RecordBlockHeightAcy;
import com.sz.apollo.ui.activity.RecordTransHashAcy;
import com.sz.apollo.ui.activity.ResetWalletAcy;
import com.sz.apollo.ui.activity.SafeCenterAcy;
import com.sz.apollo.ui.activity.SetPinCodeAcy;
import com.sz.apollo.ui.activity.ShareAcy;
import com.sz.apollo.ui.activity.ShareNboAcy;
import com.sz.apollo.ui.activity.ShareSystemAcy;
import com.sz.apollo.ui.activity.SystemSettingAcy;
import com.sz.apollo.ui.activity.TransDetailOneAcy;
import com.sz.apollo.ui.activity.TransDetailTwoAcy;
import com.sz.apollo.ui.activity.TransferRecordAcy;
import com.sz.apollo.ui.activity.TranslateApolloAcy;
import com.sz.apollo.ui.activity.TranslateEthAcy;
import com.sz.apollo.ui.activity.WebAcy;
import com.sz.apollo.ui.models.BalanceBean;
import com.sz.apollo.ui.models.UserWalletBean;

public class UiHelper {
    public static void startHomepageActivity(Context activity) {
        Intent intent = new Intent(activity, HomepageActivity.class);
        activity.startActivity(intent);
    }

    public static void startTransferRecordAcy(Context activity, int position, BalanceBean balanceBean) {
        Intent intent = new Intent(activity, TransferRecordAcy.class);
        intent.putExtra("position", position);
        intent.putExtra("balanceBean", balanceBean);
        activity.startActivity(intent);
    }

    public static void translateApolloAcy(Context activity, boolean isFromContactBook, String contactAddress, String transType) {
        Intent intent = new Intent(activity, TranslateApolloAcy.class);
        intent.putExtra("isFromContactBook", isFromContactBook);
        intent.putExtra("contactAddress", contactAddress);
        intent.putExtra("transType", transType);
        activity.startActivity(intent);
    }

    public static void startTranslateEthAcy(Context activity, String tokenType) {
        Intent intent = new Intent(activity, TranslateEthAcy.class);
        intent.putExtra("tokenType", tokenType);
        activity.startActivity(intent);
    }


    public static void startTransDetailOneAcy(Context activity, String hash, String transType) {
        Intent intent = new Intent(activity, TransDetailOneAcy.class);
        intent.putExtra("hash", hash);
        intent.putExtra("transType", transType);
        activity.startActivity(intent);
    }

    public static void startTransDetailTwoAcy(Context activity, String hash) {
        Intent intent = new Intent(activity, TransDetailTwoAcy.class);
        intent.putExtra("hash", hash);
        activity.startActivity(intent);
    }

    public static void startScanAcy(Activity activity) {
        Intent intent = new Intent(activity, CaptureActivity.class);
        activity.startActivityForResult(intent, ScanUtils.SCAN_CODE);
    }

    public static void startChooseTypeAcy(Context activity, String type) {
        switch (type) {
            case "ALL":
                startChooseTypeAcy(activity);
                break;
            case "Apollo":
                startCreateApolloWalletAcy(activity);
                break;
            case "ETH":
                startCreateEthWalletOneAcy(activity);
                break;
        }
    }

    public static void startCreateApolloWalletAcy(Context activity) {
        Intent intent = new Intent(activity, ChooseApolloWalletWayAcy.class);
        activity.startActivity(intent);
    }

    public static void startChooseTypeAcy(Context activity) {
        Intent intent = new Intent(activity, ChooseTypeAcy.class);
        activity.startActivity(intent);
    }

    public static void startCreateApolloWalletSuccessAcy(Context activity) {
        Intent intent = new Intent(activity, CreateApolloWalletSuccessAcy.class);
        activity.startActivity(intent);
    }

    public static void startImportApolloAcy(Context activity) {
        Intent intent = new Intent(activity, ImportApolloAcy.class);
        activity.startActivity(intent);
    }

    public static void startManageWalletAcy(Context activity) {
        Intent intent = new Intent(activity, ManageWalletAcy.class);
        activity.startActivity(intent);
    }

    public static void startChangeCardNameAcy(Context activity) {
        Intent intent = new Intent(activity, ChangeCardNameAcy.class);
        activity.startActivity(intent);
    }

    public static void startCreateEthWalletOneAcy(Context activity) {
        Intent intent = new Intent(activity, CreateEthWalletOneAcy.class);
        activity.startActivity(intent);
    }

    /**
     * @param activity
     * @param isFromChargeBackup 如果是来自钱包管理的备份，不显示“暂不备份”//否则显示
     * @param backupType         1--助记词  2 私钥  3 keystore
     */
    public static void startBackupEthAcy(Context activity, boolean isFromChargeBackup, int backupType) {
        Intent intent = new Intent(activity, BackupEthAcy.class);
        intent.putExtra("isFromChargeBackup", isFromChargeBackup);
        intent.putExtra("backupType", backupType);
        activity.startActivity(intent);
    }

    public static void startBackupPhraseAcy(Context activity, boolean isFromChargeBackup, UserWalletBean userWalletBean) {
        Intent intent = new Intent(activity, BackupPhraseAcy.class);
        intent.putExtra("isFromChargeBackup", isFromChargeBackup);
        intent.putExtra("userWalletBean", userWalletBean);
        activity.startActivity(intent);
    }

    public static void startBackupPKOrKeyAcy(Context activity, int backupType, UserWalletBean userWalletBean) {
        Intent intent = new Intent(activity, BackupPKOrKeyAcy.class);
        intent.putExtra("backupType", backupType);
        intent.putExtra("userWalletBean", userWalletBean);
        activity.startActivity(intent);
    }

    public static void startConfirmPhraseAcy(Context activity, UserWalletBean userWalletBean, boolean isFromChargeBackup) {
        Intent intent = new Intent(activity, ConfirmPhraseAcy.class);
        intent.putExtra("userWalletBean", userWalletBean);
        intent.putExtra("isFromChargeBackup", isFromChargeBackup);
        activity.startActivity(intent);
    }

    public static void startCreateEthSuccessAcy(Context activity, UserWalletBean userWalletBean) {
        Intent intent = new Intent(activity, CreateEthSuccessAcy.class);
        intent.putExtra("userWalletBean", userWalletBean);
        activity.startActivity(intent);
    }

    public static void startImportEthAcy(Context activity, int way) {
        Intent intent = new Intent(activity, ImportEthAcy.class);
        intent.putExtra("way", way);
        activity.startActivity(intent);
    }

    public static void startMoreTokenAcyTwo(Activity activity, String tokenType, int requestCode) {
        Intent intent = new Intent(activity, MoreTokenAcyTwo.class);
        intent.putExtra("tokenType", tokenType);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startMoreTokenAcy(Context activity, String kind) {
        Intent intent = new Intent(activity, MoreTokenAcy.class);
        intent.putExtra("kind", kind);
        activity.startActivity(intent);
    }

    public static void startShareSystemAcy(Context activity) {
        Intent intent = new Intent(activity, ShareSystemAcy.class);
        activity.startActivity(intent);
    }

    public static void startProfitDetailAcy(Context activity, String symbol) {
        Intent intent = new Intent(activity, ProfitDetailAcy.class);
        intent.putExtra("symbol", symbol);
        activity.startActivity(intent);
    }

    public static void startPowerDetailAcy(Context activity) {
        Intent intent = new Intent(activity, PowerDetailAcy.class);
        activity.startActivity(intent);
    }

    public static void startFlashExchangeAcy(Context activity) {
        Intent intent = new Intent(activity, FlashExchangeAotAcy.class);
        activity.startActivity(intent);
    }

    public static void startPaymentAcy(Context activity, String amount) {
        Intent intent = new Intent(activity, PaymentAotAcy.class);
        intent.putExtra("amount", amount);
        activity.startActivity(intent);
    }

    public static void startFlashRecordAcy(Context activity, String symbol) {
        Intent intent = new Intent(activity, FlashRecordAcy.class);
        intent.putExtra("symbol", symbol);
        activity.startActivity(intent);
    }

    public static void startAddressBookAcy(Context activity) {
        Intent intent = new Intent(activity, AddressBookAcy.class);
        activity.startActivity(intent);
    }

    public static void startAddAddressBookAcy(Context activity) {
        Intent intent = new Intent(activity, AddAddressBookAcy.class);
        activity.startActivity(intent);
    }

    public static void startContactDetailAcy(Context activity, long addressId) {
        Intent intent = new Intent(activity, ContactDetailAcy.class);
        intent.putExtra("addressId", addressId);
        activity.startActivity(intent);
    }

    public static void startSafeCenterAcy(Context activity) {
        Intent intent = new Intent(activity, SafeCenterAcy.class);
        activity.startActivity(intent);
    }

    public static void startChangePinAcy(Context activity) {
        Intent intent = new Intent(activity, ChangePinAcy.class);
        activity.startActivity(intent);
    }

    public static void startNoPassPayAcy(Context activity) {
        Intent intent = new Intent(activity, NoPassPayAcy.class);
        activity.startActivity(intent);
    }

    public static void startResetWalletAcy(Context activity) {
        Intent intent = new Intent(activity, ResetWalletAcy.class);
        activity.startActivity(intent);
    }

    public static void startAboutUsAcy(Context activity) {
        Intent intent = new Intent(activity, AboutUsAcy.class);
        activity.startActivity(intent);
    }

    public static void startSystemSettingAcy(Context activity) {
        Intent intent = new Intent(activity, SystemSettingAcy.class);
        activity.startActivity(intent);
    }

    public static void startLanguageAcy(Context activity) {
        Intent intent = new Intent(activity, LanguageAcy.class);
        activity.startActivity(intent);
    }

    public static void startSetPinCodeAcy(Context activity) {
        Intent intent = new Intent(activity, SetPinCodeAcy.class);
        activity.startActivity(intent);
    }

    public static void startNoticeAllAcy(Context activity, int type) {
        Intent intent = new Intent(activity, NoticeAllAcy.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    public static void startAccountAddressAcy(Context activity, String account) {
        Intent intent = new Intent(activity, RecordAccountAddressAcy.class);
        intent.putExtra("account", account);
        activity.startActivity(intent);
    }

    public static void startMiningPoolApolloAcyAcy(Context activity) {
        Intent intent = new Intent(activity, MiningPoolApolloAcy.class);
        activity.startActivity(intent);
    }

    public static void startRecordBlockHeightAcyAcy(Context activity, String block) {
        Intent intent = new Intent(activity, RecordBlockHeightAcy.class);
        intent.putExtra("block", block);
        activity.startActivity(intent);
    }

    public static void startRecordTransHashAcy(Context activity, String txHash) {
        Intent intent = new Intent(activity, RecordTransHashAcy.class);
        intent.putExtra("txHash", txHash);
        activity.startActivity(intent);
    }

    public static void startWebAcyAcy(Context activity, String title, String url) {
        Intent intent = new Intent(activity, WebAcy.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    public static void startShareAcy(Context activity) {
        Intent intent = new Intent(activity, ShareAcy.class);
        activity.startActivity(intent);
    }

    public static void startMiningPoolNBOAcy(Context activity) {
        Intent intent = new Intent(activity, MiningPoolNBOAcy.class);
        activity.startActivity(intent);
    }

    public static void startMiningPowerAcy(Context activity, String coinType) {
        Intent intent = new Intent(activity, MiningPowerAcy.class);
        intent.putExtra("coinType", coinType);
        activity.startActivity(intent);
    }

    public static void startFlashExchangeNboAcy(Context activity) {
        Intent intent = new Intent(activity, FlashExchangeNboAcy.class);
        activity.startActivity(intent);
    }

    public static void startShareNboAcy(Context activity, String coinType) {
        Intent intent = new Intent(activity, ShareNboAcy.class);
        intent.putExtra("coinType", coinType);
        activity.startActivity(intent);
    }

    public static void startMiningPoolDDaoAcy(Context activity) {
        Intent intent = new Intent(activity, MiningPoolDdaoAcy.class);
        activity.startActivity(intent);
    }

    public static void startMiningPoolLonAcy(Context activity) {
        Intent intent = new Intent(activity, MiningPoolLonAcy.class);
        activity.startActivity(intent);
    }
    public static void startTestAcy(Context activity, String title, String url) {
//        Intent intent = new Intent(activity, TestAcy.class);
//        intent.putExtra("title", title);
//        intent.putExtra("url", url);
//        activity.startActivity(intent);
    }
}
