package dombk6qe81022;


import java.io.File;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import javax.xml.transform. Transformer;
import javax.xml.transform.TransformerException; 
import javax.xml.transform. TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource; 
import org.w3c.dom. Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class DOMWriteBK6QE8 {
	
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("DOMBK6QE8" , "hallgatok");
		doc.appendChild(root);
		
		root.appendChild(createUser(doc, "1", "Peter", "Nagy", "Web Developer"));
		root.appendChild(createUser(doc, "2", "Piroska", "Vigh", "Java Programozo"));
		root.appendChild(createUser(doc, "3", "Ferenc", "Kiss", "Associate Professor"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("hallgatoBK6QE8.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);

		
	}
	
	

	private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
		
		Element user = doc.createElement("hallgato");
		
		user.setAttribute("id", id );
		user.appendChild(createUserElement(doc, "keresztnev", firstName));
		user.appendChild(createUserElement(doc, "vezeteknev", lastName));
		user.appendChild(createUserElement(doc, "keresztnev", profession));
		return user;
		
	}



	private static Node createUserElement(Document doc, String name, String value) {
		
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
		
	}
	
}