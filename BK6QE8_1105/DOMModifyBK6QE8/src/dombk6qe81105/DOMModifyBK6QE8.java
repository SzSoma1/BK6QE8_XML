package dombk6qe81105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class DOMModifyBK6QE8 {

	public static void main(String[] args) {
        try {
        	
            File xmlFile = new File("C:\\Users\\Sziráczki Soma\\eclipse-workspace\\DOMModifyBK6QE8\\src\\hallgatoBK6QE8.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);


            doc.getDocumentElement().normalize();


            NodeList hallgatoList = doc.getElementsByTagName("hallgato");
            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Element hallgato = (Element) hallgatoList.item(i);
                if (hallgato.getAttribute("id").equals("01")) {

                    Node keresztnevNode = hallgato.getElementsByTagName("keresztnev").item(0);
                    Node vezeteknevNode = hallgato.getElementsByTagName("vezeteknev").item(0);

                    keresztnevNode.setTextContent("Janos");
                    vezeteknevNode.setTextContent("Nagy");

                    break;
                }
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);


            System.out.println(writer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
}
