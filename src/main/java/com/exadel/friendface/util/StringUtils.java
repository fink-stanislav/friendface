package com.exadel.friendface.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.contains;

/**
 * User: sfink
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

    public static String buildUrl(String host, Pair... params) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(host);
        urlBuilder.append(contains(host, '?') ? "&" : "?");

        for (int i = 0; i < params.length; i++) {
            urlBuilder.append(params[i].getKey())
                    .append("=")
                    .append(URLEncoder.encode((String) params[i].getValue(), "UTF-8"))
                    .append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.lastIndexOf("&"));
        return urlBuilder.toString();
    }

}
