/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/18/11
 * Time: 12:43 PM
 */

package com.exadel.friendface.util.servletutil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.exadel.friendface.util.stringutil.StringUtil.buildUrl;

public class ServletUtil {

    public static void forwardToUrl(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        ServletContext sc = servlet.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    public static void forwardToUrl(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response,
                                    String url, Map<String, Object> atributes)
            throws ServletException, IOException {
        ServletContext sc = servlet.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        for (Map.Entry<String, Object> atribute : atributes.entrySet()) {
            request.setAttribute(atribute.getKey(), atribute.getValue());
        }
        rd.forward(request, response);
    }

    public static void forwardToUrl(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response,
                                    String url, String name, Object value)
            throws ServletException, IOException {
        ServletContext sc = servlet.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        request.setAttribute(name, value);
        rd.forward(request, response);
    }

    public static void redirectToUrl(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.sendRedirect(url);
    }

    public static void redirectToUrl(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response,
                                     String url, Map<String, String> params)
            throws ServletException, IOException {
        response.sendRedirect(buildUrl(url, params));
    }
}
