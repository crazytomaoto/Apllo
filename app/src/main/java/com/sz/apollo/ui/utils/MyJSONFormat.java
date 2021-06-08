package com.sz.apollo.ui.utils;

public class MyJSONFormat {

    public static String format(String mJson) {
        StringBuilder soruce = new StringBuilder(mJson);
        if (mJson == null || mJson.equals("")) {
            return null;
        }
        int offset = 0;//目标字符串插入空格偏移量
        int bOffset = 0;//空格偏移量
        for (int i = 0; i < mJson.length(); i++) {
            char charAt = mJson.charAt(i);
            if (charAt == '{' || charAt == '[') {
                bOffset += 4;
                soruce.insert(i + offset + 1, "\n" + generateBlank(bOffset));
                offset += (bOffset + 1);
            } else if (charAt == ',') {
                soruce.insert(i + offset + 1, "\n" + generateBlank(bOffset));
                offset += (bOffset + 1);
            } else if (charAt == '}' || charAt == ']') {
                bOffset -= 4;
                soruce.insert(i + offset, "\n" + generateBlank(bOffset));
                offset += (bOffset + 1);
            }
        }
        return soruce.toString();
    }

    /**
     * 打印
     */
    public static String print(String mJson) {
        try {
            String aaa = "";
            String willPrintString = format(mJson);
            int length = willPrintString.length();
            int index = 0;
            while (length > 2000) {
                aaa = aaa + willPrintString.substring(index * 2000, (index + 1) * 2000);
                length -= 2000;
                index++;
            }
            aaa = aaa + willPrintString.substring(index * 2000);
            return aaa;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String generateBlank(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

}