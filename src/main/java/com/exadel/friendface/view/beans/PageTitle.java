package com.exadel.friendface.view.beans;

import java.io.Serializable;

/**
 * Author: S. Fink
 * Date: 2/27/11
 * Time: 4:22 PM
 */

public class PageTitle implements Serializable {
    private String bundleName;

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }
}
