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
	private List<Hero> heroes;
	private Location north, west, east, south;
	protected List<Element> elements;
	protected List<NPC> npcs;
	protected List<IterableChild> children;
	private Document doc;
	
	public Location(Document doc){
		super(doc);
		this.doc = doc;
		this.heroes = new ArrayList();
		this.elements = new ArrayList();
		this.children = new ArrayList();
		this.npcs = new ArrayList();
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
				
	public void addElement(Element element){
		elements.add(element);
		children.add(element);
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
	
	public String getAllPossibleCommands(Hero hero){
		String res = getPossibilities(hero);
		for(IterableChild child: children) 
			res += child.getPossibilities(hero);
		return res;
	}
	
	public String toString(Hero askingHero){ //askingHero - hero who tries to call a command
		String res =  description + "\n";
		for(IterableChild child : children){
			res += child.getDescription() + "\n";
		}
		res += "Possible commands:\n";
		res += getAllPossibleCommands(askingHero);
		res += "Znajduja sie tutaj:\n"; 
		for (Hero hero:heroes) res +=  "	->" + hero.getName() + ", " +hero.getDescription() + "\n";
		return res;
	}
	
	public int getId(){
		return this.id;
	}
	
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
	
	public abstract void addElements(NodeList elementNodeList);
	public abstract void addNPCs(NodeList npcNodeList);
}

