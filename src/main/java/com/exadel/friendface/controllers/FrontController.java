/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 23.01.11
 * Time: 20:03
 */

package com.exadel.friendface.controllers;

import com.exadel.friendface.commands.Command;
import com.exadel.friendface.util.servletutil.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    public FrontController() {
        super();
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
            throws IOException, ServletException {
        ServletUtil.redirectToUrl(this, request, response, url);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String resultPage;
        RequestHelper helper = new RequestHelper(request);

        Command command = helper.getCommand();

        resultPage = command.execute(request, response);

        dispatch(request, response, resultPage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
