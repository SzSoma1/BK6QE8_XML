package dombk6qe81022;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMRead1BK6QE8 {
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {

        File xmlFile = new File("BK6QE8orarend.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("ora");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String oid = elem.getAttribute("id");
                String tipus = elem.getAttribute("tipus");

                Node node1 = elem.getElementsByTagName("targy").item(0);
                String targy = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("helyszin").item(0);
                String helyszin = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("oktato").item(0);
                String oktato = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("szak").item(0);
                String szak = node4.getTextContent();

                Element idopontElem = (Element) elem.getElementsByTagName("idopont").item(0);
                String nap = idopontElem.getElementsByTagName("nap").item(0).getTextContent();
                String tol = idopontElem.getElementsByTagName("tol").item(0).getTextContent();
                String ig = idopontElem.getElementsByTagName("ig").item(0).getTextContent();

                System.out.println("Óra id: " + oid);
                System.out.println("Típus: " + tipus);
                System.out.println("Tárgy: " + targy);
                System.out.println("Nap: " + nap);
                System.out.println("Idopont: " + tol + " - " + ig);
                System.out.println("Helyszín: " + helyszin);
                System.out.println("Oktató: " + oktato);
                System.out.println("Szak: " + szak);
            }
        }
    }
}