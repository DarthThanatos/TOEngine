package monter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import map.ArcadiaMap;
import map.IMap;
import map.location.Location;
import map.location.StandardLocation;

public class XMLMonter implements IMapMonter{
	
	IMap map;
	DocumentBuilderFactory dbFactory;
	HashMap<String, Location> directionDict;
	
	public XMLMonter(){
		map = new ArcadiaMap();
		directionDict = new HashMap();
	}
	
	private int locationAlreadyMounted(String fileName){
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(fileName);
	        int id = Integer.parseInt(((org.w3c.dom.Element)(doc.getDocumentElement())).getAttribute("id"));
	        if(map.getLocation(id) != null) return id;
	        return -1;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private Location mountGraph(String FileName){
		Location location = null;
        Location outerLocation;
        int outerLocationId;
		directionDict.put("West", null);
		directionDict.put("North", null);
		directionDict.put("East",null);
		directionDict.put("South", null);
		try {	
	         File inputFile = new File(FileName);
	         dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize(); 
	         Node locationNode =  doc.getDocumentElement(); //to-do : recognizing type of the parent node
	         location = new StandardLocation(doc); //here we pass all that can be mounted internally...
	         map.addLocation(location);
	         NodeList outer_locations_node_list = doc.getElementsByTagName("Locations"); //... but outer locations need intervention from this monter
	         for (int i = 0; i < outer_locations_node_list.getLength(); i++) {
	            Node outer_location_node = outer_locations_node_list.item(i);
	               Element eLocation = (Element) outer_location_node;
	               String direction = eLocation.getElementsByTagName("Direction").item(0).getTextContent();
	               String fileName = eLocation.getElementsByTagName("File").item(0).getTextContent();
	               if ( (outerLocationId = locationAlreadyMounted(fileName)) != -1)outerLocation = map.getLocation(outerLocationId);
	               else outerLocation = mountGraph(fileName);
	               directionDict.put(direction, outerLocation);
	            
	         }
	         location.setDirections(directionDict.get("North"),directionDict.get("East"), directionDict.get("South"), directionDict.get("West"));
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return location;
	}
	
	@Override
	public IMap createMap(String rootFileName) {
		Location rootLocation = mountGraph(rootFileName);
		map.setRootLocation(rootLocation);
		return map;
	}

}
