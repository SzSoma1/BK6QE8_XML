package domBK6QE81105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DomQueryBK6QE81 {

    public static void main(String[] args) {
        try {

            File xmlFile = new File("orarendBK6QE8.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);


            doc.getDocumentElement().normalize();

            
            NodeList oraList = doc.getElementsByTagName("ora");
            List<String> kurzusNevek = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                kurzusNevek.add(targy);
            }
            System.out.println("Kurzusnév: " + kurzusNevek);

            
            if (oraList.getLength() > 0) {
                Element firstOra = (Element) oraList.item(0);


                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(firstOra);
                
                
                StringWriter writer = new StringWriter();
                StreamResult consoleResult = new StreamResult(writer);
                transformer.transform(source, consoleResult);
                System.out.println("Elso kurzus strukturált formában:\n" + writer.toString());

                
                StreamResult fileResult = new StreamResult(new FileOutputStream("orarendElsoOra.xml"));
                transformer.transform(source, fileResult);
                System.out.println("Az elso kurzus strukturált formában mentve a fájlba.");
            }

            
            List<String> oktatoNevek = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();
                oktatoNevek.add(oktato);
            }
            System.out.println("Oktatók nevei: " + oktatoNevek);

            
            System.out.println("Hetfoi órák neve és helyszíne:");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                NodeList idopontList = ora.getElementsByTagName("idopont");
                for (int j = 0; j < idopontList.getLength(); j++) {
                    Element idopont = (Element) idopontList.item(j);
                    String nap = idopont.getElementsByTagName("nap").item(0).getTextContent();
                    if ("Hetfo".equalsIgnoreCase(nap)) {
                        String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                        String helyszin = ora.getElementsByTagName("helyszin").item(0).getTextContent();
                        System.out.println("Kurzus: " + targy + ", Helyszín: " + helyszin);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}