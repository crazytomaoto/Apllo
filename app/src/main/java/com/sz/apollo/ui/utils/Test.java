package com.sz.apollo.ui.utils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hysd.android.platform_ub.base.config.PlatformConfig;
import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.utils.EthWalletUtil;
import com.sz.apollo.ui.utils.MyWeb3jUtil;
import com.sz.apollo.ui.utils.ToastUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 启动页
 */
public class Test {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                TaskCheckBanlance taskCheckBanlance = new TaskCheckBanlance();
//                taskCheckBanlance.execute();
//            }
//        }.start();
//    }

//    public static void main(String args[]) {
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                test();
//            }
//        }, 10000, 10000);
//
//    }

    //当前钱包的余额
    private static class TaskCheckBanlance extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            EthGetBalance ethGetBalance1 = null;
            try {
                ethGetBalance1 = MyWeb3jUtil.getWeb3jInstance().ethGetBalance("0xb4b0e327f734d55a4d0662c409d23b8c21e8f876", DefaultBlockParameterName.LATEST).send();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BigDecimal nig = Convert.fromWei(ethGetBalance1.getBalance().toString(), Convert.Unit.ETHER);
            result = nig.stripTrailingZeros().toPlainString();
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            double myBalance = Double.parseDouble(s);
        }
    }

    static String pk01 = "16e796e24223c8ca0225c6c55b140a2f550dd9edcc69499bbbc189b9e5fd8e57";

    private static void test() {

        try {
            ECKeyPair ecKeyPair = EthWalletUtil.getKeyPair(pk01);
            if (null != ecKeyPair) {
                String publicKey = ecKeyPair.getPublicKey().toString(16);
//                System.out.println(Constant.LOG_TAG + "以太坊公钥+++" + publicKey);
                WalletFile aWallet = null;
                try {
                    if (null != publicKey) {
                        aWallet = Wallet.createLight("ppppppp0", ecKeyPair);
                        if (null != aWallet) {
                            String address = "0x" + aWallet.getAddress();
                            TaskCheckBanlance taskCheckBanlance = new TaskCheckBanlance();
                            taskCheckBanlance.execute();
                        }

                    }
                } catch (CipherException e) {
                    e.printStackTrace();
//                    System.out.println(Constant.LOG_TAG + "以太坊公钥+++" + publicKey);
//                    System.out.println(Constant.LOG_TAG + "iiiiii+++");
//                    System.out.println(Constant.LOG_TAG + "生成无效私钥+++");
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
