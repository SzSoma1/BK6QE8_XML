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



public class DOMReadBK6QE8 {
	public static void main(String argv[]) throws SAXException,
	IOException, ParserConfigurationException {
		
		File xmlFile = new File("hallgatoBK6QE8.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		
		Document BK6QE8 = dBuilder.parse(xmlFile);
		
		BK6QE8.getDocumentElement().normalize();
		
		System.out.println("Gyökér elem: " + BK6QE8.getDocumentElement().getNodeName());
		
		NodeList nList = BK6QE8.getElementsByTagName("hallgatoBK6QE8");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			if (nNode.getNodeType()== Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				
				String hid = elem.getAttribute("id");
				
				Node node1 = elem.getElementsByTagName("keresztnev").item(0);
				String kname = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("vezeteknev").item(0);
				String vname = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("foglalkozas").item(0);
				String fname = node3.getTextContent();
				
				System.out.println("Hallgato id: " + hid);
				System.out.println("Keresztnev: " + kname);
				System.out.println("Vezetéknév: " + vname);
				System.out.println("Foglalkozás: " + fname);
				
				
			}
		}
		
	
		
		
	}

}
