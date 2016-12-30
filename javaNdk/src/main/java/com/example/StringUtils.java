package com.example;

/**
 * Created by 328789 on 2016/12/30.
 */

public class StringUtils {
    /**
     * string转uncode
     * @param str
     * @return
     */
    public static String STRING2UNCODE(String str){
        StringBuffer stringBuffer = new StringBuffer();
        char c;
        String strHex;
        for (int i=0;i<str.length();i++){
            c=str.charAt(i);
            strHex = Integer.toHexString(c);
            if(c>128){
                stringBuffer.append("\\u"+strHex);
            }else {
                stringBuffer.append("\\u00"+strHex);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * GBK转uncode
     * @param gbk
     * @return
     */
    public static String GBK2Uncode(String gbk){
        StringBuffer result = new StringBuffer();
        for (int i = 0; i <gbk.length() ; i++) {
            char c = gbk.charAt(i);
            if(!isNeedConvert(c)){
                result.append(c);
            }else {
                result.append("\\\\u"+Integer.toHexString(c));
            }
        }
        return result.toString();
    }

    /**
     * 判断字节是否在0-255内
     * @param c
     * @return
     */
    public static boolean isNeedConvert(char c){
        return (c & (0x00ff))==c;
    }
    /**
     * utf-8 转unicode
     *
     * @param inStr
     * @return String
     */
    public static String utf8ToUnicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(myBuffer[i]);
            if (ub == Character.UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }
}
