package xpathbk6qe81119;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class xPathModifyBK6QE8 {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("orarendBK6QE8.xml");
            doc.getDocumentElement().normalize();

            NodeList szakNodes = doc.getElementsByTagName("szak");
            for (int i = 0; i < szakNodes.getLength(); i++) {
                szakNodes.item(i).setTextContent("Szabad Bölcsész");
            }

            NodeList targyNodes = doc.getElementsByTagName("targy");
            for (int i = 0; i < targyNodes.getLength(); i++) {
                Element targy = (Element) targyNodes.item(i);
                String updatedText = targy.getTextContent() + " (SZS)";
                targy.setTextContent(updatedText);
            }

            NodeList oraNodes = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraNodes.getLength(); i++) {
                Element ora = (Element) oraNodes.item(i);
                if (ora.hasAttribute("id") && ora.getAttribute("id").equals("MesInt")) {
                    NodeList helyszinNodes = ora.getElementsByTagName("helyszin");
                    for (int j = 0; j < helyszinNodes.getLength(); j++) {
                        helyszinNodes.item(j).setTextContent("XXXVII");
                    }
                }
            }

            System.out.println("Módosított XML:");
            printDocument(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("orarendBK6QE8.xml"));
            transformer.transform(source, result);

            System.out.println("Módosított XML mentve az 'orarendBK6QE8.xml' fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printDocument(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}