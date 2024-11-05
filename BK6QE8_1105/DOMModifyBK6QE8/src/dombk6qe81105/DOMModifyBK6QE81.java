package dombk6qe81105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;

public class DOMModifyBK6QE81 {
	
	public static void main(String[] args) {
        try {
 
            File xmlFile = new File("C:\\Users\\Sziráczki Soma\\eclipse-workspace\\DOMModifyBK6QE8\\src\\dombk6qe81105\\BK6QE8_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);


            doc.getDocumentElement().normalize();

  
            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);


                NodeList oraadoList = ora.getElementsByTagName("oraado");
                if (oraadoList.getLength() == 0) {
                    Element oraado = doc.createElement("oraado");
                    oraado.setTextContent("Minta Tanar");
                    ora.appendChild(oraado);
                }


                if ("gyakorlat".equals(ora.getAttribute("tipus"))) {
                    ora.setAttribute("tipus", "eloadas");
                }
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult consoleResult = new StreamResult(writer);
            transformer.transform(source, consoleResult);


            System.out.println(writer.toString());


            StreamResult fileResult = new StreamResult(new FileOutputStream("orarendModifyBk6QE8.xml"));
            transformer.transform(source, fileResult);

            System.out.println("A módosított fájl mentve: orarendModifyBK6QE8.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
