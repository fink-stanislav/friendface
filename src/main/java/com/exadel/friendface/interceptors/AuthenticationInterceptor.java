package com.exadel.friendface.interceptors;

import com.exadel.friendface.beans.business.User;
import com.exadel.friendface.system.FriendfaceConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

/**
 * Author: sfink
 * Date: 30.01.11
 * Time: 20:50
 */

public class AuthenticationInterceptor implements Interceptor {
    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();
        User user = (User) session.get(FriendfaceConstants.FriendfaceUser);

        // user is not stored in session
        if (user == null) {
            return Action.LOGIN;
        } else {
            return actionInvocation.invoke();
        }
    }
}
