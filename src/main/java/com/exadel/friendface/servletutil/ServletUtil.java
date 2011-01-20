
/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/18/11
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */

package com.exadel.friendface.servletutil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.commons.lang.StringUtils.left;
import static org.apache.commons.lang.StringUtils.right;

import java.io.IOException;

public class ServletUtil {

    public static void forwardErrorPage(HttpServlet servlet, HttpServletRequest request,
                                        HttpServletResponse response, String message)
            throws ServletException, IOException {
        ServletContext sc = servlet.getServletContext();
    	RequestDispatcher rd = sc.getRequestDispatcher("/wrongdata");
    	request.setAttribute("message", message);
    	rd.forward(request, response);
    }

    public static void forwardPage(HttpServlet servlet, HttpServletRequest request,
                                        HttpServletResponse response, String message,
                                        String url)
            throws ServletException, IOException {
        //url = right(url, url.length() - 1);
        ServletContext sc = servlet.getServletContext();
    	RequestDispatcher rd = sc.getRequestDispatcher(url);
    	request.setAttribute("message", message);
    	rd.forward(request, response);
    }
}
