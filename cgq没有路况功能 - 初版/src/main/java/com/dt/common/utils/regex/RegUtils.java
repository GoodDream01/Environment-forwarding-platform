package com.dt.common.utils.regex;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtils{
    /**
     * 判断字符串中是否包含中文
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否含有特殊字符
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str); return m.find();
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /** 判断一个字符串是否含有数字
     *
     * @param str
     * @return
     */
    public static boolean HasNumeric(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(str);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 是否仅含有数字和字母
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    /**
     * 判断是否为数字和字母
     * @param str
     * @return
     */
    public static boolean isNumAndLetter(String str){
        Pattern pattern = Pattern.compile("[0-9A-Za-z]+");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否有表情符号
     * @param str
     * @return
     */
    public static boolean isEmoji(String str) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断是否是IP地址
     * @param str
     * @return
     */
    public static boolean isIP(CharSequence str) {
        return isMatch("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)", str);
    }

    /**
     * 判断是否是URL
     * @param str
     * @return
     */
    public static boolean isURL(CharSequence str) {
        return isMatch("[a-zA-z]+://[^\\s]*", str);
    }

    /**
     * 判断是否是Email
     * @param str
     * @return
     */
    public static boolean isEmail(CharSequence str) {
        return isMatch("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", str);
    }

    public static boolean isMatch(String match, CharSequence str) {
        return str!= null && str.length() > 0 && Pattern.matches(match, str);
    }

    /**
     * 判断是否符合用户名校验规则
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isUserName(String str) {
        String regEx = "^[0-9a-zA-Z_]{6,20}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断是否符合用户名校验规则
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isPassword(String str) {
        String regEx = "^[0-9a-zA-Z_]{6,20}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断是否为手机号码
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isPhone(String str){
        String regEx = "^1(3|4|5|6|7|8|9)\\d{9}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}