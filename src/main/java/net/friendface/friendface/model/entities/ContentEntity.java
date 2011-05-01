package net.friendface.friendface.model.entities;

/**
 * Author: S. Fink
 * Date: 01.05.11
 * Time: 18:44
 */

public interface ContentEntity extends Identifiable {
    <T> T getContent();
    <T> void setContent(T content);
}
