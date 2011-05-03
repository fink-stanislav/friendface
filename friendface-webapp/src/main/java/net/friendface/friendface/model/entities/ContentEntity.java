package net.friendface.friendface.model.entities;

import javax.jcr.Binary;

/**
 * Author: S. Fink
 * Date: 01.05.11
 * Time: 18:44
 */

public interface ContentEntity extends Identifiable {
    Binary getContent();
    void setContent(Binary content);
}
