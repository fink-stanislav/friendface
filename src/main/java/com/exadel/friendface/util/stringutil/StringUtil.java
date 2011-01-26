/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/21/11
 * Time: 11:55 AM
 */

package com.exadel.friendface.util.stringutil;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class StringUtil {
    public static String avoidNullValue(String textString) {
        if (textString == null) {
            return "";
        }
        return textString;
    }

    public static String buildUrl(String host, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(host).append("?");
        try {
            for (Map.Entry<String, String> param : params.entrySet()) {
                urlBuilder.append(param.getKey())
                        .append("=")
                        .append(URLEncoder.encode(param.getValue(), "UTF-8"))
                        .append("&");
            }
        } catch (UnsupportedEncodingException e) {
            Logger logger = Logger.getLogger("logger");
            logger.error("Url builder error. ", e);
        }
        urlBuilder.deleteCharAt(urlBuilder.lastIndexOf("&"));
        return urlBuilder.toString();
    }
}
