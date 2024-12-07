package hu.domparse.bk6qe8;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyBK6QE8 {

	public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {

		try {

			File inputFile = new File("C:\\Users\\Szir�czki Soma\\Desktop\\XML\\XMLTask_BK6QE8\\1. Feladat\\XML_BK6QE8_toModify.xml");

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(inputFile);

			// order attrib�tum�nak m�dos�t�sa
			Node csoport = doc.getElementsByTagName("order").item(0);

			NamedNodeMap attr = csoport.getAttributes();
			Node nodeAttr = attr.getNamedItem("order_id");
			nodeAttr.setTextContent("4");

			// order item m�dos�t�s
			NodeList kszList = doc.getElementsByTagName("ordered_items");

			for (int i = 0; i < kszList.getLength(); i++) {
				Node node = kszList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) node;
					String tagValue = eElement.getAttribute("order_id");

					if ("1".equals(tagValue)) {
						eElement.setAttribute("order_id", "4");
					}

				}
			}

			// customer items m�dos�t�s
			NodeList cList = doc.getElementsByTagName("customer");

			for (int i = 0; i < cList.getLength(); i++) {
				Node node = cList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) node;
					String tagValue = eElement.getAttribute("create_order");

					if ("1".equals(tagValue)) {
						eElement.setAttribute("create_order", "4");
					}

				}
			}

			// courier item m�dos�t�s
			NodeList courList = doc.getElementsByTagName("courier");

			for (int i = 0; i < courList.getLength(); i++) {
				Node node = courList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) node;
					String tagValue = eElement.getAttribute("courier_demand");

					if ("1".equals(tagValue)) {
						eElement.setAttribute("courier_demand", "4");
					}

				}
			}

			// Tartalom �r�sa
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			System.out.println("-M�dos�tott f�jl-");

			StreamResult consoleResult = new StreamResult(System.out);
			StreamResult file = new StreamResult(inputFile);

			transformer.transform(source, consoleResult);
			transformer.transform(source, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
