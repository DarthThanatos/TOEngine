package map.location;

import iterable.IterableChild;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import map.element.Element;
import map.npc.NPC;
import persistence.Hero;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import commands.*;

public abstract class Location extends IterableChild{
	private int id;
	protected String key;
	private String description;
	private List<Hero> heroes;
	private List<String> possibleCommands;
	public Location north, west, east, south;
	private List<Element> elements;
	private List<NPC> npcs;
	protected List<IterableChild> children;
	
	private Document doc;
	
	public Location(int id){
		this.id = id;
	}
	
	public Location(Document doc){
		this.doc = doc;
		this.description = doc.getElementsByTagName("Description").item(0).getTextContent();
		this.id = Integer.parseInt(((org.w3c.dom.Element)(doc.getDocumentElement())).getAttribute("id"));
		this.key = ((org.w3c.dom.Element)(doc.getDocumentElement())).getAttribute("key");
		this.heroes = new ArrayList();
		this.elements = new ArrayList();
		this.possibleCommands = new ArrayList();
	}
	
	public abstract void executeCommand(String key, String command); 
	
	public void setDirections(Location north, Location east, Location south, Location west){
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
	public abstract void addElements(NodeList elementNodeList);
	public abstract void addElement(String elementFileName);
			
	public void addElement(Element element){
		elements.add(element);
		children.add(element);
	}
	
	public abstract void addNPCs(NodeList npcNodeList);
	public abstract void addNPC(String npcFileName);
	
	public void addNPC(NPC npc){
		npcs.add(npc);
		children.add(npc);
	}
	
	public abstract void addPossibleCommands(NodeList commandList);
	public void addPossibleCommand(String command){
		possibleCommands.add(command);
	}
	
	public void addHero(Hero hero){
		heroes.add(hero);
	}
	
	public void deleteHero(Hero hero){
		for(Hero someHero : heroes){
			if(someHero.getId() == hero.getId()){
				heroes.remove(someHero);
			}
		}
	}
	
	public abstract void addReactions(NodeList reactions);
	
	@Override
	public String toString(){
		String res =  description + "\nPossible commands:\n";
		for(Element element : elements){
			res += element.getDescription();
		}
		for (String command : possibleCommands){
			res += command + "\n";
		}
		return res;
	}
	
	public int getId(){
		return this.id;
	}
}

