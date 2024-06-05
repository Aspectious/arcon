package dev.aspectious.arcon.util;

import org.w3c.dom.Document;
import javax.xml.parsers.*;
import java.io.*;

public class XML {
    public static Document parseXMLDocument(String filePath) throws Exception {
        try (InputStream in = XML.class.getResourceAsStream(filePath)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File("test.xml");
            Document doc = builder.parse(in);
            return doc;
        }
    }
}
