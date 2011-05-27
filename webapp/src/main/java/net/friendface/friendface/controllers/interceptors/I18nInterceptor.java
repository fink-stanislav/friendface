package net.friendface.friendface.controllers.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;

import java.util.Locale;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 2/28/11
 * Time: 9:45 PM
 */

public class I18nInterceptor implements Interceptor {
    public void destroy() {
    }

    public void init() {
    }

    private Locale getLocaleFromParams(ActionInvocation invocation) {
        Map<String, Object> params = invocation.getInvocationContext().getParameters();
        if (params != null) {
            try {
                Object localeObject = params.get("request_locale");
                String[] locales = (String[]) localeObject;
                return new Locale(locales[0]);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private Locale getLocaleFromSession(ActionInvocation invocation) {
        Map session = invocation.getInvocationContext().getSession();
        return (Locale) session.get("WW_TRANS_I18N_LOCALE");
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        Locale locale = getLocaleFromParams(invocation);
        if (locale != null) {
            SessionHelper sessionHelper = new SessionHelper(
                    invocation.getInvocationContext().getSession()
            );
            sessionHelper.putToSession("WW_TRANS_I18N_LOCALE", locale);
            invocation.getInvocationContext().setLocale(locale);
        } else {
            locale = getLocaleFromSession(invocation);
            if (locale != null) {
                invocation.getInvocationContext().setLocale(locale);
            }
        }
        return invocation.invoke();
    }
}
