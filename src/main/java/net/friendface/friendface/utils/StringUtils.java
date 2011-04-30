package net.friendface.friendface.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.contains;

/**
 * User: S. Fink
 * Date: 2/1/11
 * Time: 7:07 PM
 */

public class StringUtils {

    public static String buildUrl(String host, Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(host);
        urlBuilder.append(contains(host, '?') ? "&" : "?");

        for (Map.Entry<String, String> param : params.entrySet()) {
            urlBuilder.append(param.getKey())
                    .append("=")
                    .append(URLEncoder.encode(param.getValue(), "UTF-8"))
                    .append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.lastIndexOf("&"));
        return urlBuilder.toString();
    }
}
