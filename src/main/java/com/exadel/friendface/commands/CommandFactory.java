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

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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
            File configuration = new File("C:\\javadev\\workspace\\friendface\\src\\main\\resources\\activities.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configuration);
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Command create(String name) {

        // weak place
        if (name == null) {
            name = "null";
        }
        // end

        Class clazz = null;
        try {
            clazz = Class.forName(mapping.get(name));
            return (Command) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
