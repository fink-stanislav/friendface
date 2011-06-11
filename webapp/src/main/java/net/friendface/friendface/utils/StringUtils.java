package net.friendface.friendface.utils;

import org.apache.jackrabbit.value.BinaryImpl;
import sun.net.util.URLUtil;

import javax.jcr.Binary;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.MalformedInputException;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.contains;
import static org.apache.commons.lang.StringUtils.indexOf;
import static org.apache.commons.lang.StringUtils.lastIndexOf;

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

    public static String getUrlParamValue(String url, String paramName) {
        try {
            // return org.apache.commons.lang.StringUtils.substringAfterLast(result, "&");
            return org.apache.commons.lang.StringUtils.substringAfter(url, "=");
        } catch (Exception e) {
            return "";
        }
    }

    public static String binaryToString(Binary binary) {
        try {
            InputStream is = binary.getStream();
            if (is != null) {
                Writer writer = new StringWriter();

                char[] buffer = new char[1024];

                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                return writer.toString();
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static Binary stringToBinary(String string) throws IOException {
        InputStream is = new ByteArrayInputStream(string.getBytes("UTF-8"));
        Binary result = new BinaryImpl(is);
        is.close();
        return result;
    }
}
