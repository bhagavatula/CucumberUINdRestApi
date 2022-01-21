package com.apitest.UIAutomation.utils;

import bsh.StringUtil;
import gherkin.lexer.El;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.json.XML;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;


public class XMLUtils {
    private Document doc;

    public XMLUtils() {
    }

    public static Document constructDocument(String xml) throws TestingException {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            doc = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException var4) {
            throw new TestingException((var4));
        } catch (SAXException var5) {
            throw new TestingException(var5);
        } catch (IOException var6) {
            throw new TestingException(var6);
        }
    }

    public static Document constructDocument(File xmlFile) throws TestingException {
        Document doc = null;
        if (!xmlFile.exists()) {
            throw new TestingException("File does not exist");
        } else {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                doc = documentBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();
                return doc;
            } catch (ParserConfigurationException var4) {
                throw new TestingException((var4));
            } catch (SAXException var5) {
                throw new TestingException(var5);
            } catch (IOException var6) {
                throw new TestingException(var6);
            }
        }
    }

    public static List<String> evaluateXpathToStringList(File xmlFile, String xpath) throws TestingException {
        return evaluateXpathToStringList(constructDocument(xmlFile), xpath);
    }

    public static List<String> evaluateXpathToStringList(Document xmldocument, String xpath) throws TestingException {
        List<String> valueList = new LinkedList();
        NodeList nodeList = evalNodeList(xmldocument, xpath);
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if (isTextNode(node)) {
                valueList.add(node.getTextContent());
            }
        }
        return valueList;
    }

    public static boolean isTextNode(Node node) {
        return node.getNodeType() == 3;
    }

    public static NodeList evaluateXpathNodeList(File xmlFile, String xPath) throws TestingException {
        return evalNodeList(constructDocument(xmlFile), xPath);
    }

    public static NodeList evalNodeList(Document xmlDocument, String xpathStr) throws TestingException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nl = null;
        try {
            XPathExpression xPathExpression = xPath.compile(xpathStr.toString());
            nl = (NodeList) xPathExpression.evaluate(xmlDocument, XPathConstants.NODESET);
            return nl;
        } catch (XPathExpressionException var5) {
            throw new TestingException(var5);
        }
    }

    public XMLUtils(File xmlFile) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(xmlFile);
        } catch (ParserConfigurationException var5) {
            var5.printStackTrace();
            ;
        } catch (SAXException var6) {
            var6.printStackTrace();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public static boolean validateXMLAgainstSchema(StreamSource xsd, StreamSource xml) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(xml);
            return true;
        } catch (SAXException var5) {
            var5.printStackTrace();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
        return false;
    }

    public static List<Difference> compareXML(String controlXML, String testXML, boolean isIgnoreOrder) throws TestingException {
        List<Difference> differences = null;
        XMLUnit.setNormalize(true);
        XMLUnit.setNormalizeWhitespace(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
        Diff diff = null;
        try {
            diff = XMLUnit.compareXML(controlXML, testXML);
        } catch (SAXException var6) {
            throw new TestingException(var6);
        } catch (IOException var7) {
            throw new TestingException(var7);
        }

        if (diff == null) {
            throw new TestingException("Unexpected error state: diff object is null");
        } else {
            if (isIgnoreOrder) {
                diff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
            }
            DetailedDiff detDiff = new DetailedDiff(diff);
            differences = detDiff.getAllDifferences();
            return differences;
        }
    }

    public Document getDoc() {
        return this.doc;
    }

    public String get_data(String xpath_input) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            NodeList nodes = (NodeList) xpath.evaluate(xpath_input, this.doc, XPathConstants.NODESET);
            if (nodes.getLength() == 0) {
                System.out.println(Thread.currentThread().getName() + ": XPATH -> " + xpath_input + "not available in current document");
                return "";
            } else {
                return nodes.item(0).getTextContent();
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return "";
        }
    }

//    public Node get_node(String xpath_input) {
//        XPath xpath = XPathFactory.newInstance().newXPath();
//        try {
//            NodeList nodes = (NodeList) xpath.evaluate(xpath_input, this.doc, XPathConstants.NODESET);
//            return nodes.item(0).cloneNode(true);
//        } catch (XPathExpressionException var5) {
//            var5.printStackTrace();
//            return null;
//        }
//    }
//
    public Node get_node(String xpath_input){
        XPath xpath = XPathFactory.newInstance().newXPath();
        try{
            NodeList nodes = (NodeList)xpath.evaluate(xpath_input,this.doc,XPathConstants.NODESET);
            return nodes.item(0).cloneNode(true);
        }catch (XPathExpressionException var5){
            var5.printStackTrace();
            return null;
        }
    }

    public void write (File file){
        TransformerFactory tf = TransformerFactory.newInstance();
        try{
            Transformer transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(this.doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            FileUtils.writeStringToFile(file,output,"UTF-8",false);
        }catch(IOException | TransformerException var6){
            var6.printStackTrace();
        }
    }

    public void printXMLToConsole(){
        try{
            TransformerFactory tg = TransformerFactory.newInstance();
            Transformer transformer = tg.newTransformer();
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(this.doc), new StreamResult(writer));
            String var4 = writer.getBuffer().toString();
        }catch (IllegalArgumentException | TransformerException var5){
            var5.printStackTrace();
        }
    }

    public List<String> get_data_list(String src_xpath){
        List<String> list = new ArrayList();
        XPath xpath = XPathFactory.newInstance().newXPath();
        try{
            NodeList nodes =  (NodeList)xpath.evaluate(src_xpath, this.doc, XPathConstants.NODESET);
            for (int idx = 0; idx <nodes.getLength(); ++idx){
                list.add(nodes.item(idx).getTextContent());
            }
        }catch (XPathExpressionException var6){
            var6.printStackTrace();
        }
        return list;
    }

    public List<String> modify_all_selr_ln_id(String src_xpath, String value){
        List<String> list = new ArrayList();
        XPath xpath = XPathFactory.newInstance().newXPath();
        try{
            NodeList nodes =  (NodeList)xpath.evaluate(src_xpath, this.doc, XPathConstants.NODESET);
            for (int idx = 0; idx <nodes.getLength(); ++idx){
                String content = value + idx;
                nodes.item(idx).setTextContent(value+idx);
                list.add(content);
            }
        }catch (XPathExpressionException var8){
            var8.printStackTrace();
        }
        return list;
    }

    public void modify(String src_xpath, String value){
        List<String> list = new ArrayList();
        XPath xpath = XPathFactory.newInstance().newXPath();
        try{
            NodeList nodes =  (NodeList)xpath.evaluate(src_xpath, this.doc, XPathConstants.NODESET);
            if (nodes.getLength() == 0){
                System.out.println(Thread.currentThread().getName() + " :XPATH -> "+ src_xpath+ "not available in current document");
                return;
            }
            for (int idx = 0; idx <nodes.getLength(); ++idx){
                nodes.item(idx).setTextContent(value+idx);
            }
        }catch (XPathExpressionException var6){
            var6.printStackTrace();
        }
    }

    public void remove(String xpath){
        String attr = "";
        if(StringUtils.contains(xpath,"@")){
            attr = xpath.replaceAll(".*@","");
            xpath = xpath.replaceAll("/@.*","");
        }
        try{
            XPathExpression expression = XPathFactory.newInstance().newXPath().compile(xpath);
            Node node = (Node)expression.evaluate(this.doc, XPathConstants.NODE);
            Element elem = (Element)node;
            if(attr.isEmpty()){
                elem.getParentNode().removeChild(node);
            }else{
                elem.removeAttribute(attr);
            }
        }catch(XPathExpressionException var6){
            var6.printStackTrace();
        }
    }

    public Node convertStringToNode(String content){
        Element ret = null;
        try{
            ret = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(content.getBytes())).getDocumentElement();
        }catch (IOException | ParserConfigurationException | SAXException var4){
            var4.printStackTrace();;
        }
        return ret;
    }

    public void append (String xpath, String new_content){
        Node new_node = this.convertStringToNode(new_content);
        Node importedNode = this.doc.importNode(new_node, true);
        this.append(xpath, importedNode);
    }

    public void append(String parentXpath, String childXpath, String new_content){
        Node importedNode = this.doc.importNode(this.convertStringToNode(new_content), true);
        try{
            XPathExpression expression = XPathFactory.newInstance().newXPath().compile(parentXpath);
            Node parentnode = (Node)expression.evaluate(this.doc, XPathConstants.NODE);
            expression = XPathFactory.newInstance().newXPath().compile(childXpath);
            Node existingChildNode = (Node) expression.evaluate(this.doc,XPathConstants.NODE);
            parentnode.insertBefore(importedNode,existingChildNode);
        }catch(XPathExpressionException var8){
            var8.printStackTrace();
        }

    }
    public void addNewNode(String parentNodeXpath, String existingChildNode, String childNode, String value) {
        Node parentNode = null;
        try {
            XPathExpression expression = XPathFactory.newInstance().newXPath().compile(parentNodeXpath);
            parentNode = (Node) expression.evaluate(this.doc, XPathConstants.NODE);
        } catch (XPathExpressionException var13) {
            var13.printStackTrace();
        }

        Text text = this.doc.createTextNode(value);
        Element childElement = null;
        String[] childNodes = childNode.split("/");
        boolean flag = true;
        for (int j = childNodes.length - 1; j >= 0; --j) {
            Element newNode = this.doc.createElement(childNodes[j]);
            if (flag) {
                newNode.appendChild(text);
                childElement = newNode;
                flag = false;
            } else {
                newNode.appendChild(childElement);
                childElement = newNode;
            }
        }

        if (StringUtils.isNoneBlank(existingChildNode) && existingChildNode != null) {
            Node existingChildElement = null;

            try {
                XPathExpression expression = XPathFactory.newInstance().newXPath().compile(existingChildNode);
                existingChildElement = (Node) expression.evaluate(this.doc, XPathConstants.NODE);
            } catch (XPathExpressionException var12) {
                var12.printStackTrace();
            }
            parentNode.insertBefore(childElement, existingChildElement);
        } else {
            parentNode.appendChild(childElement);
        }
    }

    public void append(String xpath, Node new_node){
        try{
            XPathExpression expression = XPathFactory.newInstance().newXPath().compile(xpath);
            Node node = (Node)expression.evaluate(this.doc,XPathConstants.NODE);
            node.appendChild(new_node);
        }catch(XPathExpressionException var5){
            var5.printStackTrace();
        }
    }

    public void convertStringToDocument(String xmlStr) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.doc = builder.parse(new InputSource(new StringReader(xmlStr)));
    }

    public String convertDocumentToString(){
        TransformerFactory tf= TransformerFactory.newInstance();
        try{
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty("omit-xml-declaration","yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(this.doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        }catch (TransformerException var5){
            var5.printStackTrace();
            return null;
        }
    }

    public void normalize(){ this.doc.normalize();}
    public String get_sql(String id) throws Exception{
        String sQuery = this.get_data("//sql[@id='"+id+"']/sql");
        if(sQuery.isEmpty()){
            throw new Exception("SQL does not exist for ID -> "+id);
        }else{
            return sQuery;
        }
    }
}
