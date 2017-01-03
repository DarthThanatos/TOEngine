package map.location;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import map.npc.Person;
import map.npc.QuestGiver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import persistence.Hero;
import commands.Command;
import commands.InteractionResult;

/*
 * This class is responsible for the main attraction of this game: "Guess What Is It?"
 * Hero's state is checked for certain conditions.
 * If those conditions are met, on the player's screen displays an opportunity to 
 * initiate the quest associated with this chamber. If conditions are not met, 
 * a default msg is returned.
 * After quest has been filled completely, new item is added to hero'equipment, which is further connected with 
 * yet another quest
 * @param conditionToStart - describes condition that triggers an event - default is just "any"
 * @param markToRemainSilent - describes a condition, which, if exists in a hero's state, does not trigger any 
 * quest notification   
 */

public class GuessChamber extends Location{
	private String conditionToStart;
	private String markToRemainSilent;
	private List<String> possibleQuestCommands; 
	private HashMap<String,Command> questCommandMap;
	
	public GuessChamber(Document doc){
		super(doc);
		addQuests();
	}

	public void addQuests(){
		
	}
	
	@Override
	public void addElements(NodeList elementNodeList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNPCs(NodeList npcNodeList) {
		NodeList questGiversNodeList = ((Element)npcNodeList.item(0)).getElementsByTagName("QuestGiver");
		try{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			for (int i = 0; i < questGiversNodeList.getLength(); i++){
		        Element questGiverNode = (Element)questGiversNodeList.item(i);
		        String filename = questGiverNode.getElementsByTagName("File").item(0).getTextContent();
		        File inputFile = new File(filename);
		        Document doc = dBuilder.parse(inputFile);
		        doc.getDocumentElement().normalize();
		        QuestGiver questGiver = new QuestGiver(doc);
		        npcs.add(questGiver);
		        children.add(questGiver);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String getPossibilities(Hero hero){
		String res = super.getPossibilities(hero);
		return res;
	}
}
