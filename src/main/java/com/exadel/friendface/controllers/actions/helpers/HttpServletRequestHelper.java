package com.exadel.friendface.controllers.actions.helpers;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: S. Fink
 * Date: 3/27/11
 * Time: 11:26 PM
 */

public class HttpServletRequestHelper {
    private HttpServletRequest request;

    public HttpServletRequestHelper(HttpServletRequest request) {
        this.request = request;
    }

    public String getPreviousAction() {
        String referer = request.getHeader("referer");
        referer = StringUtils.substringAfterLast(referer, "/");
        return referer;
    }
}
