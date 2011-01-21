package com.exadel.friendface.stringutil;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/21/11
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */

public class StringUtil {
    public static String avoidNullValue(String textString) {
        if (textString == null) {
            return "";
        }
        return textString;
    }
}
