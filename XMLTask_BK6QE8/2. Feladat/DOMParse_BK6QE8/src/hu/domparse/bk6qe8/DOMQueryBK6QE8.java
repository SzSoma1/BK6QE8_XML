package hu.domparse.bk6qe8;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryBK6QE8 {

	public static void main(String[] args) {

		try {

			// DocumentBuilder
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			File xmlFile = new File("C:\\Users\\Szir�czki Soma\\Desktop\\XML\\XMLTask_BK6QE8\\1. Feladat\\XML_BK6QE8.xml");
			if (!xmlFile.exists()) {
			    System.out.println("A f�jl nem tal�lhat�: " + xmlFile.getAbsolutePath());
			    return;
			}
			Document document = documentBuilder.parse(xmlFile);


			document.getDocumentElement().normalize();

			// XPath 
			XPath xPath = XPathFactory.newInstance().newXPath();
						
			// a Restaurant_BK6QE8 root elem alapanyag gyerekelemei
			String expression = "Restaurant_BK6QE8 / ingredients";

			// alapanyagok, amiknek van attrib�tuma
			// String expression = "//ingredients[@*]";

			// alapanyagok, amik mint 500ba ker�lnek
			// String expression = "//ingredients[purchasePrice='500']";

			// alapanyagok t�pusa, amik t�bb, mint 200Ft-ba ker�lnek
			// String expression = "//ingredients[purchasePrice>200]/type";

			// term�kek elso k�t eleme
			// String expression = "//product[position()<3]";

			// 2-es azonosit�j� term�k 
			// String expression = "//product[@product_id='2']";

			// a harmadik term�k kiv�laszt�as
			// String expression = "Restaurant_BK6QE8/product[3]";
			
			// keszitunk egy listat, majd az xPath kifejezest le kell forditani es ki kell ertekelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("\nAktualis elem: " + node.getNodeName());

				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("ingredients")) {

					Element elem = (Element) node;

                        String id = elem.getAttribute("ingredient_id");
        
                        Node node1 = elem.getElementsByTagName("name").item(0);
                        String name = node1.getTextContent();
        
                        Node node2 = elem.getElementsByTagName("type").item(0);
                        String type = node2.getTextContent();
        
                        Node node3 = elem.getElementsByTagName("purchasePrice").item(0);
                        String purchaseP = node3.getTextContent();

						Node node4 = elem.getElementsByTagName("stockQuantity").item(0);
                        String stockQ = node4.getTextContent();
        
                        System.out.println("Alapanyag id-je: " + id);
                        System.out.println("N�v: " + name);
                        System.out.println("T�pus: " + type);
                        System.out.println("PurchasePrice: " + purchaseP);
						System.out.println("StockQuantity: " + stockQ);

				}
				
				// alapanyagok t�pusa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("type")) {

					Element element = (Element) node;

					System.out.println("Alapanyag t�pusa: " + element.getTextContent());

				}

				//product kiiratasa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("product")) {

					Element element = (Element) node;

					System.out.println("ID: " + element.getAttribute("product_id"));

					System.out.println(
							"Term�k neve: " + element.getElementsByTagName("name").item(0).getTextContent());

					System.out.println(
							"�ra: " + element.getElementsByTagName("price").item(0).getTextContent());
					
							if (nodeList.item(i).getChildNodes().getLength() > 3) {
								int db = 0;
								Node node4 = element.getElementsByTagName("type").item(0);
								while (node4 != null) {
									node4 = element.getElementsByTagName("type").item(db);
									if (node4 != null) {
										String type = node4.getTextContent();
										System.out.println("A t�pusa: " + type);
									}
									db++;
								}
		
							}
							
					System.out.println(
							"Le�r�sa: " + element.getElementsByTagName("description").item(0).getTextContent());
         
        
                    

				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}