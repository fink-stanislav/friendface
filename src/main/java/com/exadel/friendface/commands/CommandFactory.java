/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 23.01.11
 * Time: 20:54
 */

package com.exadel.friendface.commands;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();
    private Map<String, String> mapping;

    private CommandFactory() {
        mapping = new HashMap<String, String>();
        configure();
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    private String getTagValue(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue();
    }

    private void configure() {
        try {
            Enumeration<URL> enumeration = getClass().getClassLoader().getResources("");
            File configuration = new File("C:/javadev/workspace/friendface/src/main/resources/activities.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(configuration);
            doc.getDocumentElement().normalize();
            NodeList activities = doc.getElementsByTagName("activities");

            for (int i = 0; i < activities.getLength(); i++) {
                NodeList activity = activities.item(i).getChildNodes();
                for (int j = 0; j < activity.getLength(); j++) {
                    Node node = activity.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        mapping.put(getTagValue(element, "name"), getTagValue(element, "class"));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Front controller configuration file error. ", e);
        }
    }

    public Command create(String name) throws CommandCreationException {

        // weak place
        if (name == null) {
            name = "null";
        }
        // end

        try {
            Class clazz = Class.forName(mapping.get(name));
            return (Command) clazz.newInstance();
        } catch (Exception e) {
            throw new CommandCreationException(e);
        }
    }
}
