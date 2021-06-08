package com.common.project.utils;

import android.text.Html;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by benny
 * @date on 2018/6/15.
 * @function
 */

public class HtmlUtils {

    public static String getHtmlString(String htmlData) {
        if (TextUtils.isEmpty(htmlData)) {
            return "";
        }
        if (!htmlData.contains("<p") && !htmlData.contains("</p>")) {
            htmlData = "<p style=\"font-size: 26pt;\">" + htmlData + "</p>";
        }
        if (htmlData.contains("<p>")) {
            htmlData = htmlData.replaceAll("<p>", "<p style=\"font-size: 26pt;\">");
        }
        htmlData = htmlData.replaceAll("size=\"3\"", "size=\"5\"");
        htmlData = htmlData.replaceAll("size=\"4\"", "size=\"5\"");
        htmlData = htmlData.replaceAll("微软雅黑", "宋体");
        htmlData = htmlData.replaceAll("font-size: 16px;", "font-size: 26pt;");
        htmlData = htmlData.replaceAll("font-size: 12pt;", "font-size: 26pt;");
        htmlData = htmlData.replaceAll("font-size: 10pt;", "font-size: 26pt;");
        htmlData = htmlData.replaceAll("font-size: 12px;", "font-size: 26pt;");
        htmlData = htmlData.replaceAll("font-size: 10px;", "font-size: 26pt;");
        return htmlData;
    }

    public static String getString(String htmlData) {
        if (TextUtils.isEmpty(htmlData)){
            return "";
        }
        if (htmlData.contains("href=")) {
            htmlData = htmlData.replaceAll("href=", "href1=");
        }
        htmlData = htmlData.replaceAll("548px", "100%");
        htmlData = htmlData.replaceAll("<p style=\"text-indent: 2em; text-align: center;\"><img", "<p style=\"text-align: center;\"><img");
        htmlData = htmlData.replaceAll("text-indent: 2em;", "");
        htmlData = htmlData.replaceAll("src=\"//", "src=\"http://");
        htmlData = htmlData.replaceAll("<img", "<img style='max-width:100%;height:auto;'");
        htmlData = htmlData.replaceAll("<p style=\"", "<p style=\"color:#666666;!important;\"");
        htmlData = htmlData.replaceAll("<p>", "<p style=\"color:#666666;!important;\">");

        LogUtils.d("html==="+htmlData);
        return htmlData;
    }

    /**
     * 字体颜色
     * @param string
     * @param color #FE3838 主题红
     * @return
     */
    public static String getString(String string, String color) {
        return "<font color=\""+color+"\">" + string + "</font>";

    }
    public static String getString(int string, String color) {
        return "<font color=\""+color+"\">" + string + "</font>";

    }

    /**
     * 加粗+颜色
     * @param string
     * @param color #FE3838 主题红
     * @return
     */
    public static String getStrong(String string, String color) {
        return "<strong><font color=\""+color+"\">" + string + "</font></strong>";

    }

    public static String getHtml(String description) {
        return String.valueOf(Html.fromHtml(description));
    }

    /**
     * 定义script的正则表达式
     */
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REGEX_HTML = "<[^>]+>";
    /**
     * 定义空格回车换行符
     */
    private static final String REGEX_SPACE = "\\s*|\t|\r|\n";
    public static String delHTMLTag(String htmlStr) {
        // 过滤script标签
        Pattern p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        // 过滤style标签
        Pattern p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        // 过滤html标签
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        // 过滤空格回车标签
        Pattern p_space = Pattern.compile(REGEX_SPACE, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");
        return htmlStr.trim(); //返回文本字符串
    }

}
