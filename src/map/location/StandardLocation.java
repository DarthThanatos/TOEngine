package map.location;

import java.io.File;
import java.security.KeyException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import iterable.IterableChild;
import map.element.StaticElement;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference.Elements;

import persistence.Hero;
import commands.Command;
import commands.InteractionResult;

public class StandardLocation extends Location{

	public StandardLocation(Document doc){
		super(doc);
		NodeList elementList = doc.getElementsByTagName("Elements");
		NodeList npcList = doc.getElementsByTagName("NPCs"); 
		addNPCs(npcList);
		addElements(elementList);
	}

	@Override
	public void addNPCs(NodeList npcNodeList) {
		
	}

	@Override
	public void addNPC(String npcFileName) {
		
	}

	@Override
	public void addElements(NodeList elementNodeList) {
		NodeList staticElementNodeList = ((Element)elementNodeList.item(0)).getElementsByTagName("StaticElement");
		addStaticElements(staticElementNodeList);
	}
	

	public void addStaticElements(NodeList staticElementNodeList){
		try{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			for (int i = 0; i < staticElementNodeList.getLength(); i++){
		        Element staticElementNode = (Element)staticElementNodeList.item(i);
		        String filename = staticElementNode.getElementsByTagName("File").item(0).getTextContent();
		        File inputFile = new File(filename);
		        Document doc = dBuilder.parse(inputFile);
		        doc.getDocumentElement().normalize();
		        StaticElement staticElement = new StaticElement(doc);
		        elements.add(staticElement);
		        children.add(staticElement);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void addElement(String elementFileName) {
		
	}


	@Override
	public InteractionResult executeCommand(Hero hero, String key, String command) {
		if (key.equals(this.key)){
			InteractionResult res = execute(hero, key,command);
			if(res.regardsMe) return res;
		}
		else{
			for (IterableChild child: children){
				InteractionResult child_res = child.execute(hero, key, command);
				if(child_res.regardsMe) return child_res;
				
			}
		}
		return new InteractionResult(hero, "Chodzi Ci o cos konkretnego?", false, false);
	}
}
