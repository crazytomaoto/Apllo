package com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.utils;

import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.client.exception.CryptoException;
import com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.crypto.util.Base58;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.stream.IntStream;


/**
 * @author espritblock http://eblock.io
 */
public class ByteUtils {

    static String charmap = ".12345abcdefghijklmnopqrstuvwxyz";

    /**
     * charidx
     *
     * @param c
     * @return
     */
    public static int charidx(char c) {
        return charmap.indexOf(c);
    }

    /**
     * concat
     *
     * @param a
     * @param b
     * @return
     */
    public static byte[] concat(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * @param src
     * @param start
     * @param length
     * @return
     */
    public static byte[] copy(byte[] src, int start, int length) {
        byte[] c = new byte[length];
        System.arraycopy(src, start, c, 0, length);
        return c;
    }

    /**
     * @param src
     * @param start
     * @param dest
     * @param dstart
     * @param length
     * @return
     */
    public static byte[] copy(byte[] src, int start, byte[] dest, int dstart, int length) {
        System.arraycopy(src, start, dest, dstart, length);
        return dest;
    }

    /**
     * @param n
     * @return
     */
    private static int[] longToBytes(Long n) {
        ByteBuffer hi = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.BIG_ENDIAN).putLong(n);
        byte[] buf = hi.array();
        int[] a = IntStream.range(0, buf.length).map(i -> buf[i] & 0xff).toArray();
        return a;
    }

    /**
     * @param value
     * @return
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]);
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     * writerUnit32
     *
     * @param value
     * @return
     */
    public static byte[] writeUint32(String value) {

        Long l = Long.parseLong(value);
        if (l > Integer.MAX_VALUE) {
            byte[] b = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN).putLong(l).array();
            int j = 0;
            for (int i = b.length - 1; i >= 0; i--) {
                if (b[i] == (byte) 0) {
                    j++;
                } else {
                    break;
                }
            }
            return ByteUtils.copy(b, 0, b.length - j);
        } else {
            return ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.LITTLE_ENDIAN).putInt(Integer.parseInt(value))
                    .array();
        }
    }

    /**
     * writerUnit16
     *
     * @param value
     * @return
     */
    public static byte[] writeUint16(String value) {
        long vl = Long.parseLong(value);
        return new byte[]{(byte) (vl & 0x00FF), (byte) ((vl & 0xFF00) >>> 8)};
    }

    /**
     * writerUnit8
     *
     * @param value
     * @return
     */
    public static byte[] writeUint8(String value) {
        long vl = Long.parseLong(value);
        return new byte[]{(byte) (vl & 0x00FF)};
    }

    /**
     * writerVarint32
     *
     * @param v
     * @return
     */
    public static byte[] writeVarint32(String v) {
        long value = Long.parseLong(v);
        byte[] a = new byte[]{};
        value >>>= 0;
        while (value >= 0x80) {
            long b = (value & 0x7f) | 0x80;
            a = ByteUtils.concat(a, new byte[]{(byte) b});
            value >>>= 7;
        }
        a = ByteUtils.concat(a, new byte[]{(byte) value});
        return a;
    }

    /**
     * writerAsset
     *
     * @param v
     * @return
     */
    public static byte[] writeAsset(String v) {
        String _value[] = v.split(" ");
        String amount = _value[0];
        String sym = _value[1];
        String precision = sym.split(",")[0];
        String symbol = sym.split(",")[1].split("@")[0];
        String[] part = amount.split("[.]");

        int pad = Integer.parseInt(precision);
        StringBuffer bf = new StringBuffer(part[0] + ".");
        if (part.length > 1) {
            pad = Integer.parseInt(precision) - part[1].length();
            bf.append(part[1]);
        }
        for (int i = 0; i < pad; i++) {
            bf.append("0");
        }
        String asset = precision + "," + symbol;
        // amount
        amount = bf.toString().replace(".", "");
        ByteBuffer ammount = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN)
                .putLong(Long.parseLong(amount));

        // asset
        StringBuffer padStr = new StringBuffer();
        for (int i = 0; i < (7 - symbol.length()); i++) {
            padStr.append("\0");
        }
        char c = (char) Integer.parseInt(precision);
        asset = c + symbol + padStr;
        ByteBuffer ba = ByteBuffer.wrap(asset.getBytes());
        return ByteUtils.concat(ammount.array(), ba.array());
    }

    /**
     * writerAccount
     *
     * @param v
     * @return
     */
    public static byte[] writeName(String v) {
        StringBuffer bitstr = new StringBuffer();
        for (int i = 0; i <= 12; i++) {
            int c = i < v.length() ? ByteUtils.charidx(v.charAt(i)) : 0;
            int bitlen = i < 12 ? 5 : 4;
            String bits = Integer.toBinaryString(c);
            if (bits.length() > bitlen) {
                throw new CryptoException("", "Invalid name " + v);
            }
            StringBuffer sb = new StringBuffer("");
            for (int j = 0; j < bitlen - bits.length(); j++) {
                sb.append("0");
            }
            bits = sb + bits;
            bitstr.append(bits);
        }
        BigInteger lv = new BigInteger(bitstr.toString(), 2);
        StringBuffer leHex = new StringBuffer();
        int bytes[] = ByteUtils.longToBytes(lv.longValue());
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i];
            String n = Integer.toHexString(b);
            leHex.append(n.length() == 1 ? "0" : "").append(n);
        }
        BigInteger ulName = new BigInteger(leHex.toString(), 16);
        return ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN).putLong(ulName.longValue()).array();
    }

    /**
     * charCount
     *
     * @return
     */
    private static long charCount(String v) {
        long c = 0;
        for (char cp : v.toCharArray()) {
            if (cp < 0x80) {
                c += 1;
            } else if (cp < 0x800) {
                c += 2;
            } else if (cp < 0x10000) {
                c += 3;
            } else {
                c += 4;
            }
        }
        return c;
    }

    /**
     * writerString
     *
     * @param v
     * @return
     */
    public static byte[] writeString(String v) {
        long value = charCount(v);
        byte[] a = new byte[]{};
        value >>>= 0;
        while (value >= 0x80) {
            long b = (value & 0x7f) | 0x80;
            a = ByteUtils.concat(a, new byte[]{(byte) b});
            value >>>= 7;
        }
        a = ByteUtils.concat(a, new byte[]{(byte) value});
        for (char c : v.toCharArray()) {
            a = ByteUtils.concat(a, decodeChar(c));
        }
        return a;
    }

    /**
     * decodeChar
     *
     * @param ca
     * @return
     */
    private static byte[] decodeChar(char ca) {
        long cp = (long) ca;
        if (cp < 0x80) {
            long a = cp & 0x7F;
            return new byte[]{(byte) a};
        } else if (cp < 0x800) {
            long a = ((cp >> 6) & 0x1F) | 0xC0;
            long b = (cp & 0x3F) | 0x80;
            return new byte[]{(byte) a, (byte) b};
        } else if (cp < 0x10000) {
            long a = ((cp >> 12) & 0x0F) | 0xE0;
            long b = ((cp >> 6) & 0x3F) | 0x80;
            long c = (cp & 0x3F) | 0x80;
            return new byte[]{(byte) a, (byte) b, (byte) c};
        } else {
            long a = ((cp >> 18) & 0x07) | 0xF0;
            long b = ((cp >> 12) & 0x3F) | 0x80;
            long c = ((cp >> 6) & 0x3F) | 0x80;
            long d = (cp & 0x3F) | 0x80;
            return new byte[]{(byte) a, (byte) b, (byte) c, (byte) d};
        }
    }

    /**
     * writerKey
     *
     * @param v
     * @return
     */
    private static byte[] writeKeyStr(String v) {
        v = v.replace("EOS", "");
        byte[] b = Base58.decode(v);
        b = ByteBuffer.allocate(b.length).order(ByteOrder.BIG_ENDIAN).put(b).array();
        byte[] key = ByteUtils.copy(b, 0, b.length - 4);
        return key;
    }

    /**
     * writerKey
     *
     * @param key
     */
    public static byte[] writeKey(String key) {
        com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.utils.ByteBuffer bf = new com.sz.apollo.ui.utils.eoswallet.io.bigbearbro.eos4j.utils.ByteBuffer();
        bf.concat(writeUint32("1"));
        bf.concat(writeVarint32("1"));
        bf.concat(writeVarint32("0"));
        bf.concat(writeKeyStr(key));
        bf.concat(writeUint16("1"));
        bf.concat(writeVarint32("0"));
        bf.concat(writeVarint32("0"));
        return bf.getBuffer();
    }

    /**
     * @param v
     * @return
     */
    public static byte[] writeUint64(String v) {
        return ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN).putLong(Long.parseLong(v)).array();
    }

    /**
     * @param hexString
     * @return
     */
    public static byte[] writeHexString(String hexString) {
        return Hex.hexStringToBytes(hexString);
    }

}
