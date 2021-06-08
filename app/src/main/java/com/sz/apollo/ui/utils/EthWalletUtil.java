package com.sz.apollo.ui.utils;

import android.util.Log;

import com.lambdaworks.crypto.SCrypt;
import com.sz.apollo.commons.constants.Constant;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;

import io.github.novacrypto.bip32.ExtendedPrivateKey;
import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.SeedCalculator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;
import io.github.novacrypto.bip44.AddressIndex;
import io.github.novacrypto.bip44.BIP44;

import static io.github.novacrypto.bip32.networks.Bitcoin.MAIN_NET;

public class EthWalletUtil extends WalletUtils {


    public static String generateFullNewWalletFile(String password, File destinationDirectory)
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidAlgorithmParameterException, CipherException, IOException {

        return generateNewWalletFile(password, destinationDirectory, true);
    }

    public static WalletFile getKeyStore(String password, ECKeyPair ecKeyPair, boolean useFullScrypt) throws CipherException {
        WalletFile walletFile = null;
        if (useFullScrypt) {
            walletFile = Wallet.createStandard(password, ecKeyPair);
        } else {
            walletFile = Wallet.createLight(password, ecKeyPair);
        }
        return walletFile;
    }

    public static String generateMnemonics() {
        StringBuilder sb = new StringBuilder();
        byte[] entropy = new byte[Words.TWELVE.byteLength()];
        new SecureRandom().nextBytes(entropy);
        new MnemonicGenerator(English.INSTANCE)
                .createMnemonic(entropy, sb::append);
        return sb.toString();
    }

    /**
     * generate key pair to create eth wallet
     * 生成KeyPair , 用于创建钱包
     */
    public static ECKeyPair generateKeyPair(String mnemonics) {
        // 1. we just need eth wallet for now
        AddressIndex addressIndex = BIP44
                .m()
                .purpose44()
                .coinType(60)
                .account(0)
                .external()
                .address(0);
        // 2. calculate seed from mnemonics , then get master/root key ; Note that the bip39 passphrase we set "" for common
        ExtendedPrivateKey rootKey = ExtendedPrivateKey.fromSeed(new SeedCalculator().calculateSeed(mnemonics, ""), MAIN_NET);
        String extendedBase58 = rootKey.extendedBase58();
        // 3. get child private key deriving from master/root key
        ExtendedPrivateKey childPrivateKey = rootKey.derive(addressIndex, AddressIndex.DERIVATION);
        String childExtendedBase58 = childPrivateKey.extendedBase58();
        // 4. get key pair
        byte[] privateKeyBytes = childPrivateKey.getKey();
        ECKeyPair keyPair = ECKeyPair.create(privateKeyBytes);
        return keyPair;
    }

    //通过私钥获取ECKeyPair-----导入钱包使用
    public static ECKeyPair getKeyPair(String privateKey) {
        ECKeyPair keyPair = null;
        try {
            keyPair = ECKeyPair.create(new BigInteger(privateKey, 16));
//            keyPair = ECKeyPair.create(new BigInteger(privateKey));
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
        return keyPair;
    }

    public static ECKeyPair decrypt(String password, WalletFile walletFile)
            throws CipherException {
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(password, String.valueOf(walletFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials.getEcKeyPair();
    }
}