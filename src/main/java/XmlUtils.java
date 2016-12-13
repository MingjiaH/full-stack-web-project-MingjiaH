import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.Date;


public class XmlUtils {

    public String buildNewsXML(String firstStar,String secondStar, Map<String, HashMap<Integer, News>> subscription) {
        String xmlDoc = "";
        try {
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("subscription");
            this.addAttribute(doc, rootElement, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(rootElement);

            for (Entry<String, HashMap<Integer, News>> entry : subscription.entrySet()) {
                String word = entry.getKey();
                if(word.equals(firstStar)){
                    Map<Integer, News> count = entry.getValue();
                    for (Map.Entry<Integer, News> interEntry : count.entrySet()) {
                        Element groupNews = doc.createElement("news");
                        UUID newsId= UUID.randomUUID(); 
                        UUID userId= UUID.randomUUID(); 
                        Date date = new Date();
                        addChildElement(doc, groupNews, "newsId", newsId.toString());
                        addChildElement(doc, groupNews, "userId", userId.toString());
                        addChildElement(doc, groupNews, "date", date.toString());
                        addChildElement(doc, groupNews, "firstTitle", interEntry.getValue().getFirstTitle());
                        addChildElement(doc, groupNews, "secondTitle", interEntry.getValue().getSecondTitle());
                        addChildElement(doc, groupNews, "content", interEntry.getValue().getContent());
                        addChildElement(doc, groupNews, "firstTopic", interEntry.getValue().getFirstTopic());
                        addChildElement(doc, groupNews, "secondTopic", interEntry.getValue().getSecondTopic());
                        addChildElement(doc, groupNews, "thirdTopic", interEntry.getValue().getThirdTopic());
                        addChildElement(doc, groupNews, "picUrl", interEntry.getValue().getPicUrl());

                        System.out.println("add Sucessfully:::::::::"+interEntry.getValue().getFirstTopic());
                        rootElement.appendChild(groupNews);
                    }
                    
                }
                
            }
            for (Entry<String, HashMap<Integer, News>> entry : subscription.entrySet()) {
                String word = entry.getKey();
                if(word.equals(secondStar)){
                    Map<Integer, News> count = entry.getValue();
                    for (Map.Entry<Integer, News> interEntry : count.entrySet()) {
                        Element groupNews = doc.createElement("news");
                        UUID newsId= UUID.randomUUID(); 
                        UUID userId= UUID.randomUUID(); 
                        Date date = new Date();
                        addChildElement(doc, groupNews, "newsId", newsId.toString());
                        addChildElement(doc, groupNews, "userId", userId.toString());
                        addChildElement(doc, groupNews, "date", date.toString());
                        addChildElement(doc, groupNews, "firstTitle", interEntry.getValue().getFirstTitle());
                        addChildElement(doc, groupNews, "secondTitle", interEntry.getValue().getSecondTitle());
                        addChildElement(doc, groupNews, "content", interEntry.getValue().getContent());
                        addChildElement(doc, groupNews, "firstTopic", interEntry.getValue().getFirstTopic());
                        addChildElement(doc, groupNews, "secondTopic", interEntry.getValue().getSecondTopic());
                        addChildElement(doc, groupNews, "thirdTopic", interEntry.getValue().getThirdTopic());
                        addChildElement(doc, groupNews, "picUrl", interEntry.getValue().getPicUrl());

                        System.out.println("add Sucessfully:::::::::"+interEntry.getValue().getFirstTopic());
                        rootElement.appendChild(groupNews);
                    }
                    
                }
                
            }
            
            try {
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(doc), new StreamResult(writer));
                xmlDoc = writer.getBuffer().toString().replaceAll("\n|\r", "");
                xmlDoc = writer.getBuffer().toString();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        try {
            this.validate(xmlDoc, "src/main/java/subscription.xsd");
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return xmlDoc;
    }

    private void addAttribute(Document doc, Element targetElement, String attrName, String attrValue) {
        Attr attr = doc.createAttribute(attrName);
        attr.setValue(attrValue);
        targetElement.setAttributeNode(attr);
        System.out.println("addAttribute finish");
    }

    private void addChildElement(Document doc, Element targetElement, String tagName, String tagValue) {
        Element tag = doc.createElement(tagName);
        tag.appendChild(doc.createTextNode(tagValue));
        targetElement.appendChild(tag);
        System.out.println("addChildElement finish");
    }



    private boolean validate(String xmlDoc, String schemaPath) throws SAXException {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(new File(schemaPath));
        Source xmlFile = new StreamSource(new StringReader(xmlDoc));
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();

        try {
            validator.validate(xmlFile);
            System.out.println("validation finish");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("validation fall down");
            return true;
        }

    }

}
