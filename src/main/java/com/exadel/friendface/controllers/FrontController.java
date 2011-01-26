/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 23.01.11
 * Time: 20:03
 */

package com.exadel.friendface.controllers;

import com.exadel.friendface.commands.Command;
import com.exadel.friendface.commands.CommandCreationException;
import com.exadel.friendface.util.servletutil.ServletUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.exadel.friendface.util.servletutil.ServletUtil.forwardToUrl;

public class FrontController extends HttpServlet {

    private Logger logger;

    @Override
    public void init() throws ServletException {
        super.init();
        logger = Logger.getLogger("logger");
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
            throws IOException, ServletException {
        ServletUtil.redirectToUrl(this, request, response, url);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String resultPage;
        RequestHelper helper = new RequestHelper(request);

        Command command;
        try {
            command = helper.getCommand();
            resultPage = command.execute(request, response);
            dispatch(request, response, resultPage);
        } catch (CommandCreationException e) {
            logger.error(e);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", e.toString());
            forwardToUrl(this, request, response, "/infopage", map);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
