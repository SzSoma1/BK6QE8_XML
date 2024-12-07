package hu.domparse.bk6qe8;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMWriteBK6QE8 {

	public static void main(String argv[]) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.newDocument();

		Element root = doc.createElementNS("BK6QE8", "Restaurant_BK6QE8");
		doc.appendChild(root);

		// ingredients

		root.appendChild(createIngredients(doc, "1", "Flour", "Base", "500", "1000"));
		root.appendChild(createIngredients(doc, "2", "Cheese", "Dairy", "400", "300"));
		root.appendChild(createIngredients(doc, "3", "Salmon", "Fish", "1800", "150"));

		Element element = (Element) doc.getElementsByTagName("ingredients").item(0);
		Comment comment = doc.createComment("Ingredients");
		element.getParentNode().insertBefore(comment, element);

		// products

		String[] t = {"pasta", "italian"};
        root.appendChild(createProduct(doc, "1", "Spaghetti", "2400", t, "Classic spaghetti with Bolognese sauce."));
        String[] t2 = {"salad"};
        root.appendChild(createProduct(doc, "2", "Greek Salad", "1800", t2, "A refreshing mix of cucumbers, tomatoes, olives, and feta cheese."));
        String[] t3 = {"japanese", "sushi"};
        root.appendChild(createProduct(doc, "3", "Grilled Salmon", "4000", t3, "Perfectly grilled salmon fillet with a side of sautéed tomatoes."));

		element = (Element) doc.getElementsByTagName("product").item(0);
		comment = doc.createComment("Products");
		element.getParentNode().insertBefore(comment, element);

		// made_of

		root.appendChild(createMadeOf(doc, "1", "1"));
		root.appendChild(createMadeOf(doc, "2", "2"));
		root.appendChild(createMadeOf(doc, "3", "3"));

		element = (Element) doc.getElementsByTagName("made_of").item(0);
		comment = doc.createComment("Made of kapcsolat");
		element.getParentNode().insertBefore(comment, element);

		// orders

		String[] i = {"Spaghetti Bolognese", "Greek Salad"};
		root.appendChild(createOrder(doc, "1", "2024.04.10", "4800", i,"Completed"));
		
		String[] i2 = {"Sushi box"};
		root.appendChild(createOrder(doc, "2", "2024.05.20", "1800", i2,"Processing"));
		
		String[] i3 = {"Spaghetti Bolognese"};
		root.appendChild(createOrder(doc, "3", "2024.06.15", "5800", i3,"Shipping"));

		element = (Element) doc.getElementsByTagName("order").item(0);
		comment = doc.createComment("Orders");
		element.getParentNode().insertBefore(comment, element);

		// ordered_items

		root.appendChild(createOrderedItems(doc, "1", "1", "2024.04.10"));
		root.appendChild(createOrderedItems(doc, "2", "2", "2024.05.20"));
		root.appendChild(createOrderedItems(doc, "3", "3", "2024.06.15"));
		
		element = (Element) doc.getElementsByTagName("ordered_items").item(0);
		comment = doc.createComment("Order items kapcsolat");
		element.getParentNode().insertBefore(comment, element);

		// customer

        root.appendChild(createCustomer(doc, "1", "1", "Emma Brown", "90210", "Elm Street", "12", "4", "+16505556789", "emma.brown@example.com", "true"));
        root.appendChild(createCustomer(doc, "2", "2", "Liam Davis", "10001", "Broadway Avenue", "7", "1", "+14155552345", "liam.davis@example.com", "false"));
        root.appendChild(createCustomer(doc, "3", "3", "Olivia Taylor", "30303", "Peachtree Street", "5", "3", "+17705556789", "olivia.taylor@example.com", "true"));

		element = (Element) doc.getElementsByTagName("customer").item(0);
		comment = doc.createComment("Customers");
		element.getParentNode().insertBefore(comment, element);

		// courier

		root.appendChild(createCourier(doc, "1", "1", "James Wilson", "+15555554321", "Bicycle", "true" ));
		root.appendChild(createCourier(doc, "2", "2", "Charlotte White", "+14445555678", "Car", "true" ));
		root.appendChild(createCourier(doc, "3", "3", "Henry Moore", "+13335556666", "Motorcycle", "true" ));

		element = (Element) doc.getElementsByTagName("courier").item(0);
		comment = doc.createComment("Couriers");
		element.getParentNode().insertBefore(comment, element);

		// Transform

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();

		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

		// File letrehozas

		DOMSource source = new DOMSource(doc);
		File myFile = new File("C:\\Users\\Sziráczki Soma\\Desktop\\XML\\XMLTask_BK6QE8\\2. Feladat\\DOMParse_BK6QE8\\XML_BK6QE81.xml");

		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);

		transf.transform(source, console);
		transf.transform(source, file);

	}
		//createIngredients

	private static Node createIngredients(Document doc, String ingredient_id, String name, String type, String purchasePrice,
			String stockQuantity) {

		Element ing = doc.createElement("ingredients");

		ing.setAttribute("ingredient_id", ingredient_id);
		ing.appendChild(createElement(doc, "name", name));
		ing.appendChild(createElement(doc, "type", type));
		ing.appendChild(createElement(doc, "purchasePrice", purchasePrice));
		ing.appendChild(createElement(doc, "stockQuantity", stockQuantity));
		
		return ing;

	}
		//createProduct

	private static Node createProduct(Document doc, String product_id, String name,
			String price, String[] type, String description) {

		Element pr = doc.createElement("product");

		pr.setAttribute("product_id", product_id);
		pr.appendChild(createElement(doc, "name", name));
		pr.appendChild(createElement(doc, "price", price));
		
		Node[] node = appendArray(doc, "type", type);

		for (int i = 0; i < type.length; i++) {
			pr.appendChild(node[i]);
		}

		pr.appendChild(createElement(doc, "description", description));
		
		return pr;

	}
		//createMadeOf
	private static Node createMadeOf(Document doc, String ingredient_id, String product_id) {

		Element mo = doc.createElement("made_of");

		mo.setAttribute("ingredient_id", ingredient_id);
		mo.setAttribute("product_id", product_id);

		return mo;

	}
		//createOrder
	
	private static Node createOrder(Document doc, String order_id, String date, 
        String price, String[] item, String status) {

		Element or = doc.createElement("order");

		or.setAttribute("order_id", order_id);
        or.appendChild(createElement(doc, "date", date));
		or.appendChild(createElement(doc, "price", price));

		Node[] node = appendArray(doc, "item", item);

		for (int i = 0; i < item.length; i++) {
			or.appendChild(node[i]);
		}

		or.appendChild(createElement(doc, "status", status));

		return or;

	}
		//createOrderedItems
	
	private static Node createOrderedItems(Document doc, String order_id, String product_id, String orderingDate) {

		Element oi = doc.createElement("ordered_items");

		oi.setAttribute("order_id", order_id);
		oi.setAttribute("product_id", product_id);
		oi.appendChild(createElement(doc, "orderingDate", orderingDate));

		return oi;

	}
		//createCustomer
	
	private static Node createCustomer(Document doc, String customer_id, String create_order, String name,
			String postalcode, String street, String houseNumber, String doorNumber, String phoneNumber, String email,
			String regularCustomer) {

		Element cElement = doc.createElement("customer");

		cElement.setAttribute("customer_id", customer_id);
		cElement.setAttribute("create_order", create_order);
		cElement.appendChild(createElement(doc, "name", name));

		Element cim = doc.createElement("address");
		cim.appendChild(createElement(doc, "postalcode", postalcode));
        cim.appendChild(createElement(doc, "street", street));
		cim.appendChild(createElement(doc, "houseNumber", houseNumber));
		cim.appendChild(createElement(doc, "doorNumber", doorNumber));

        cElement.appendChild(cim);

		cElement.appendChild(createElement(doc, "phoneNumber", phoneNumber));
		cElement.appendChild(createElement(doc, "email", email));
		cElement.appendChild(createElement(doc, "regularCustomer", regularCustomer));

		return cElement;

	}
		//createCourier
	
	private static Node createCourier(Document doc, String courier_id, String courier_demand,
	String name, String phoneNumber, String transportType, String isActive) {

		Element c = doc.createElement("courier");

		c.setAttribute("courier_id", courier_id);
		c.setAttribute("courier_demand", courier_demand);
		c.appendChild(createElement(doc, "name", name));
		c.appendChild(createElement(doc, "phoneNumber", phoneNumber));
		c.appendChild(createElement(doc, "transportType", transportType));
		c.appendChild(createElement(doc, "isActive", isActive));

		return c;

	}
		//createElement
	
	private static Node createElement(Document doc, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));

		return node;

	}

	private static Node[] appendArray(Document doc, String name, String[] value) {

		Element nodes[] = new Element[value.length];

		for (int i = 0; i < value.length; i++) {

			nodes[i] = doc.createElement(name);
			nodes[i].appendChild(doc.createTextNode(value[i]));

		}

		return nodes;

	}

}