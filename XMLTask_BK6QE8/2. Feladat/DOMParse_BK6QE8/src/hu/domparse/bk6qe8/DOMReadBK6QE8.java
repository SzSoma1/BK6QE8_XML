package hu.domparse.bk6qe8;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOMReadBK6QE8 {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File xmlFile = new File("C:\\Users\\Sziráczki Soma\\Desktop\\XML\\XMLTask_BK6QE8\\1. Feladat\\XML_BK6QE8.xml");

            // DOM parser inicializálása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Dokumentum normalizálása
            doc.getDocumentElement().normalize();
            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

            // Ingredients kiolvasása
            NodeList ingredientList = doc.getElementsByTagName("ingredients");
            System.out.println("\n--- Ingredients ---");
            for (int i = 0; i < ingredientList.getLength(); i++) {
                Node node = ingredientList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("ingredient_id");
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String type = element.getElementsByTagName("type").item(0).getTextContent();
                    String price = element.getElementsByTagName("purchasePrice").item(0).getTextContent();
                    String stock = element.getElementsByTagName("stockQuantity").item(0).getTextContent();

                    System.out.println("ID: " + id + ", Name: " + name + ", Type: " + type + 
                                       ", Purchase Price: " + price + ", Stock: " + stock);
                }
            }

            // Products kiolvasása
            NodeList productList = doc.getElementsByTagName("product");
            System.out.println("\n--- Products ---");
            for (int i = 0; i < productList.getLength(); i++) {
                Node node = productList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("product_id");
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String price = element.getElementsByTagName("price").item(0).getTextContent();
                    String type = element.getElementsByTagName("type").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();

                    System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + 
                                       ", Type: " + type + ", Description: " + description);
                }
            }

            // Orders kiolvasása
            NodeList orderList = doc.getElementsByTagName("order");
            System.out.println("\n--- Orders ---");
            for (int i = 0; i < orderList.getLength(); i++) {
                Node node = orderList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("order_id");
                    String date = element.getElementsByTagName("date").item(0).getTextContent();
                    String price = element.getElementsByTagName("price").item(0).getTextContent();
                    NodeList items = element.getElementsByTagName("item");
                    String status = element.getElementsByTagName("status").item(0).getTextContent();

                    System.out.print("ID: " + id + ", Date: " + date + ", Price: " + price + 
                                     ", Items: ");
                    for (int j = 0; j < items.getLength(); j++) {
                        System.out.print(items.item(j).getTextContent());
                        if (j < items.getLength() - 1) System.out.print(", ");
                    }
                    System.out.println(", Status: " + status);
                }
            }

            // Customers kiolvasása
            NodeList customerList = doc.getElementsByTagName("customer");
            System.out.println("\n--- Customers ---");
            for (int i = 0; i < customerList.getLength(); i++) {
                Node node = customerList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("customer_id");
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String phoneNumber = element.getElementsByTagName("phoneNumber").item(0).getTextContent();
                    String email = element.getElementsByTagName("email").item(0).getTextContent();
                    String regularCustomer = element.getElementsByTagName("regularCustomer").item(0).getTextContent();

                    Element address = (Element) element.getElementsByTagName("address").item(0);
                    String postalCode = address.getElementsByTagName("postalcode").item(0).getTextContent();
                    String street = address.getElementsByTagName("street").item(0).getTextContent();
                    String houseNumber = address.getElementsByTagName("houseNumber").item(0).getTextContent();
                    String doorNumber = address.getElementsByTagName("doorNumber").item(0).getTextContent();

                    System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phoneNumber + 
                                       ", Email: " + email + ", Regular Customer: " + regularCustomer + 
                                       ", Address: " + postalCode + " " + street + 
                                       " " + houseNumber + "/" + doorNumber);
                }
            }

            // Couriers kiolvasása
            NodeList courierList = doc.getElementsByTagName("courier");
            System.out.println("\n--- Couriers ---");
            for (int i = 0; i < courierList.getLength(); i++) {
                Node node = courierList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("courier_id");
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String phone = element.getElementsByTagName("phoneNumber").item(0).getTextContent();
                    String transport = element.getElementsByTagName("transportType").item(0).getTextContent();
                    String isActive = element.getElementsByTagName("isActive").item(0).getTextContent();

                    System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone + 
                                       ", Transport: " + transport + ", Active: " + isActive);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

