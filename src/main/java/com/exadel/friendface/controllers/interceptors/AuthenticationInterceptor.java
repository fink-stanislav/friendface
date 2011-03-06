package com.exadel.friendface.controllers.interceptors;

import com.exadel.friendface.application.FriendfaceConstants;
import com.exadel.friendface.model.entities.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 30.01.11
 * Time: 20:50
 */

public class AuthenticationInterceptor implements Interceptor {
    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        Map session = invocation.getInvocationContext().getSession();
        User user = (User) session.get(FriendfaceConstants.FriendfaceUser);

        // user is not stored in session
        if (user == null) {
            return Action.LOGIN;
        } else {
            return invocation.invoke();
        }
    }
}
