package com.common.project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱号码验证工具
 * @author  benny .
 * on 9:47.2017/5/5 .
 */

public class EmailTool {
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
