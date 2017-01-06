package map.location;

import iterable.IterableChild;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import map.npc.NPC;
import persistence.Hero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Location extends IterableChild{
	private List<Hero> heroes;
	private Location north, west, east, south;
	protected List<NPC> npcs;
	protected Document doc;
	
	public Location(Document doc){
		super(doc);
		this.doc = doc;
		this.heroes = new ArrayList();
		this.npcs = new ArrayList();
		Element npcsElement = (Element) doc.getElementsByTagName("NPCs").item(0); 
		addNPCs(npcsElement);
	}
	
	public Location getNorth(){
		return north;
	}
	
	public Location getSouth(){
		return south;
	}
	
	public Location getWest(){
		return west;
	}
	
	public Location getEast(){
		return east;
	}
	
	public List<Hero> getHeroes(){
		return heroes;
	}
	
	public void setDirections(Location north, Location east, Location south, Location west){
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
				
	public void addNPC(NPC npc){
		npcs.add(npc);
		children.add(npc);
	}

	public void addHero(Hero hero){
		heroes.add(hero);
	}
	
	public void deleteHero(Hero hero){
		for(int i = 0; i < heroes.size(); i++){
			if(heroes.get(i).getId() == hero.getId()){
				heroes.remove(i);
			}
		}			
	}
		
	public String toString(Hero askingHero){ //askingHero - hero who tries to call a command
		String res = "Description:\n"; 
		res += getAllPossibleDescriptions(askingHero)  + "\n";
		res += "Possible commands:\n";
		res += getAllPossibleCommands(askingHero) +  "\n";
		res += "Znajduja sie tutaj:\n"; 
		for (Hero hero:heroes) res +=  "	->" + hero.getName() + ", " +hero.getDescription() + "\n";
		return res;
	}
	
	public int getId(){
		return this.id;
	}

	public void addNPCs(Element npcsElement) {
		NodeList npcsNodeList = npcsElement.getElementsByTagName("NPC");		
		try{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			for (int i = 0; i < npcsNodeList.getLength(); i++){
		        Element npcNode = (Element)npcsNodeList.item(i);
		        String filename = npcNode.getElementsByTagName("File").item(0).getTextContent();
		        File inputFile = new File(filename);
		        Document doc = dBuilder.parse(inputFile);
		        doc.getDocumentElement().normalize();
		        NPC npc = new NPC(doc);
		        npcs.add(npc);
		        children.add(npc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}