package hu.domparse.bk6qe8;


import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadBK6QE8 {

    public static void main(String[] args) {
        try {

            String xmlFilePath = "C:\\Users\\Szir�czki Soma\\Desktop\\XML\\XMLTask_BK6QE8\\1. Feladat\\XML_BK6QE8.xml";

            // XML dokumentum l�trehoz�sa �s beolvas�sa
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Dokumentum elemeinek ki�r�sa a konzolra
            printNode(doc.getDocumentElement(), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurz�v met�dus az XML elemek ki�r�s�ra
    private static void printNode(Node node, String beh�z�s) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
                
            //elemek elott enter    
            if (node.getParentNode().getNodeType() == Node.ELEMENT_NODE) {

                System.out.println("");

            }

            //tagek ki�r�sa
            System.out.println(beh�z�s + "<" + node.getNodeName() + ">");

            //tagn�v ki�r�sa
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                printNode(nodeList.item(i), beh�z�s + "  ");
            }

            //tagek lez�r�sa
            System.out.println(beh�z�s + "</" + node.getNodeName() + ">");

            //tag ut�n �res sor
            System.out.println("");

        } else if (node.getNodeType() == Node.TEXT_NODE) {

            //tag sz�veg tartalm�nak ki�r�sa
            String text = node.getNodeValue().trim();
            if (!text.isEmpty()) {
                System.out.println(beh�z�s + text);
            }

        } else if (node.getNodeType() == Node.COMMENT_NODE) {

            //comment ki�r�sa    
            System.out.println("");
            System.out.println(beh�z�s + "<!-- " + node.getNodeValue() + " -->");

        }
    }
}
