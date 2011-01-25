package com.exadel.friendface.tiles;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;

import javax.servlet.jsp.PageContext;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/25/11
 * Time: 11:33 AM
 */

public class HeaderViewPreparer implements ViewPreparer {
    public void execute(TilesRequestContext tilesRequestContext, AttributeContext attributeContext) {
        ArrayList<HeaderViewPreparer.HeaderItem> headerParams = new ArrayList<HeaderViewPreparer.HeaderItem>();

        String servletName = "fail";
        Object[] requestObjects = tilesRequestContext.getRequestObjects();
        for (Object o : requestObjects) {
            if (o instanceof PageContext) {
                PageContext pageContext = (PageContext) o;
                servletName = pageContext.getServletConfig().getServletName();
            }
        }
        if (servletName.equals("welcomepage")) {
            headerParams.add(new HeaderItem("registration", "регистрация", "регистрация"));
            headerParams.add(new HeaderItem("logon", "вход", "вход"));
        } else if (servletName.equals("logon")) {
            headerParams.add(new HeaderItem("registration", "регистрация", "регистрация"));
        } else if (servletName.equals("registration")) {
            headerParams.add(new HeaderItem("logon", "вход", "вход"));
        } else {
            headerParams.add(new HeaderItem());
        }
        tilesRequestContext.getRequestScope().put("headerParams", headerParams);
    }

    public static class HeaderItem {
        private String refvalue;
        private String reftitle;
        private String refname;

        public HeaderItem() {
            this.refvalue = "";
            this.reftitle = "";
            this.refname = "";
        }

        public HeaderItem(String refvalue, String reftitle, String refname) {
            this.refvalue = refvalue;
            this.reftitle = reftitle;
            this.refname = refname;
        }

        public String getRefvalue() {
            return refvalue;
        }

        public String getReftitle() {
            return reftitle;
        }

        public String getRefname() {
            return refname;
        }
    }
}
