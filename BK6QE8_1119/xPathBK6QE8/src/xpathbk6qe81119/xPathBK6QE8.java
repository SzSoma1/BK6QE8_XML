package xpathbk6qe81119;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class xPathBK6QE8 {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("studentBK6QE8.xml");

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            String query1 = "/class/student";
            NodeList result1 = (NodeList) xpath.evaluate(query1, doc, XPathConstants.NODESET);
            System.out.println("1) Összes student, amely a class gyermekei:");
            for (int i = 0; i < result1.getLength(); i++) {
                System.out.println(result1.item(i).getTextContent());
            }

            String query2 = "/class/student[@id='2']";
            NodeList result2 = (NodeList) xpath.evaluate(query2, doc, XPathConstants.NODESET);
            System.out.println("\n2) Student 'id' attribútummal és értéke '2':");
            for (int i = 0; i < result2.getLength(); i++) {
                System.out.println(result2.item(i).getTextContent());
            }

            String query3 = "//student";
            NodeList result3 = (NodeList) xpath.evaluate(query3, doc, XPathConstants.NODESET);
            System.out.println("\n3) Összes student függetlenül a helytol:");
            for (int i = 0; i < result3.getLength(); i++) {
                System.out.println(result3.item(i).getTextContent());
            }

            String query4 = "/class/student[2]";
            NodeList result4 = (NodeList) xpath.evaluate(query4, doc, XPathConstants.NODESET);
            System.out.println("\n4) Második student:");
            for (int i = 0; i < result4.getLength(); i++) {
                System.out.println(result4.item(i).getTextContent());
            }

            String query5 = "/class/student[last()]";
            NodeList result5 = (NodeList) xpath.evaluate(query5, doc, XPathConstants.NODESET);
            System.out.println("\n5) Utolsó student:");
            for (int i = 0; i < result5.getLength(); i++) {
                System.out.println(result5.item(i).getTextContent());
            }

            String query6 = "/class/student[last()-1]";
            NodeList result6 = (NodeList) xpath.evaluate(query6, doc, XPathConstants.NODESET);
            System.out.println("\n6) Utolsó elotti student:");
            for (int i = 0; i < result6.getLength(); i++) {
                System.out.println(result6.item(i).getTextContent());
            }

            String query7 = "/class/student[position() <= 2]";
            NodeList result7 = (NodeList) xpath.evaluate(query7, doc, XPathConstants.NODESET);
            System.out.println("\n7) Elso két student:");
            for (int i = 0; i < result7.getLength(); i++) {
                System.out.println(result7.item(i).getTextContent());
            }

            String query8 = "/class/*";
            NodeList result8 = (NodeList) xpath.evaluate(query8, doc, XPathConstants.NODESET);
            System.out.println("\n8) class root összes gyermeke:");
            for (int i = 0; i < result8.getLength(); i++) {
                System.out.println(result8.item(i).getNodeName());
            }

            String query9 = "/class/student[@*]";
            NodeList result9 = (NodeList) xpath.evaluate(query9, doc, XPathConstants.NODESET);
            System.out.println("\n9) Összes student attribútummal:");
            for (int i = 0; i < result9.getLength(); i++) {
                System.out.println(result9.item(i).getTextContent());
            }

            String query10 = "//*";
            NodeList result10 = (NodeList) xpath.evaluate(query10, doc, XPathConstants.NODESET);
            System.out.println("\n10) Dokumentum összes eleme:");
            for (int i = 0; i < result10.getLength(); i++) {
                System.out.println(result10.item(i).getNodeName());
            }

            String query11 = "/class/student[kor > 20]";
            NodeList result11 = (NodeList) xpath.evaluate(query11, doc, XPathConstants.NODESET);
            System.out.println("\n11) class root összes student kor > 20:");
            for (int i = 0; i < result11.getLength(); i++) {
                System.out.println(result11.item(i).getTextContent());
            }

            String query12 = "//keresztnev | //vezeteknev";
            NodeList result12 = (NodeList) xpath.evaluate(query12, doc, XPathConstants.NODESET);
            System.out.println("\n12) Összes keresztnev és vezeteknev csomópont:");
            for (int i = 0; i < result12.getLength(); i++) {
                System.out.println(result12.item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}