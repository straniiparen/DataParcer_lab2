package straniiparen.lab2solution;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements FileParser {

    @Override
    public List<Address> parse(File file) throws Exception {
        List<Address> addresses = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        NodeList nodeList = doc.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String city = element.getAttribute("city");
            String street = element.getAttribute("street");
            int house = Integer.parseInt(element.getAttribute("house"));
            int floor = Integer.parseInt(element.getAttribute("floor"));
            addresses.add(new Address(city, street, house, floor));
        }
        return addresses;
    }
}