package net.friendface.friendface.controllers.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import net.friendface.friendface.controllers.actions.FriendfaceAction;
import net.friendface.friendface.model.entities.User;

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
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        User user = (User) session.get(FriendfaceAction.FRIENDFACE_USER);

        // user is not stored in session
        if (user == null) {
            return Action.LOGIN;
        } else {
            return invocation.invoke();
        }
    }
}
