package map.npc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import quests.Quest;
import iterable.IterableChild;

public class NPC extends IterableChild{

	private List<Quest> quests;
	
	public NPC(Document doc) {
		super(doc);
		quests = new ArrayList();
		Element questsList = (Element)doc.getElementsByTagName("Quests").item(0); 
		addQuests(questsList);
	}
	
	public void addQuests(Element questsElement){
		NodeList questsNodeList = questsElement.getElementsByTagName("Quest");
		try{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			for (int i = 0; i < questsNodeList.getLength(); i++){
		        Element questNode = (Element)questsNodeList.item(i);
		        String filename = questNode.getElementsByTagName("File").item(0).getTextContent();
		        File inputFile = new File(filename);
		        Document doc = dBuilder.parse(inputFile);
		        doc.getDocumentElement().normalize();
		        Quest quest = new Quest(doc);
		        quests.add(quest);
		        children.add(quest);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}	
}
