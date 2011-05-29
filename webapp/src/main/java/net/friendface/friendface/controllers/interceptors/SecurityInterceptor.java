package net.friendface.friendface.controllers.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import net.friendface.friendface.controllers.actions.FriendfaceAction;
import net.friendface.friendface.controllers.actions.SecurityAware;
import net.friendface.friendface.controllers.actions.SecuritySettings;
import net.friendface.friendface.model.entities.User;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 29.05.11
 * Time: 17:57
 */

public class SecurityInterceptor implements Interceptor {
    @Override
    public void destroy() {
    }

    @Override
    public void init() {

    }

    private Integer getIdFromParams(ActionInvocation invocation) {
        Map<String, Object> params = invocation.getInvocationContext().getParameters();
        if (params != null) {
            try {
                Object idObject = params.get("userId");
                String[] ids = (String[]) idObject;
                return Integer.parseInt(ids[0]);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private Integer getIdFromSession(ActionInvocation invocation) {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if (session != null) {
            try {
                User user = (User) session.get(FriendfaceAction.FRIENDFACE_USER);
                return user.getId();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getInvocationContext().getActionInvocation().getAction();
        if (action instanceof SecurityAware) {
            try {
                Integer idp = getIdFromParams(invocation);
                Integer ids = getIdFromSession(invocation);
                Boolean result = idp.equals(ids);

                SecuritySettings settings = new SecuritySettings();
                settings.setEqualIds(result);
                ((SecurityAware) action).setSecuritySettings(settings);

            } catch (Exception e) {
                // Do nothing
            }
        }
        return invocation.invoke();
    }
}
