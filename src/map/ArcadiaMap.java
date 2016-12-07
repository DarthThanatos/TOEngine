package map;

import java.util.HashMap;

import persistence.Hero;
import commands.Command;
import map.element.Element;
import map.location.Location;
import map.npc.NPC;

public class ArcadiaMap implements IMap {

	private HashMap<Integer, Location>locations = new HashMap();
	private HashMap<String,Command> commands = new HashMap();

	private Location rootLocation;
	
	@Override
	public Location getLocation(int locationId) {
		if (locations.containsKey(locationId)) return locations.get(locationId);
		else return null;
	}

	public void addCommand(String name, Command command){
		commands.put(name,command);
	}
	
	public void getCommand(String name){
		
	}
	
	@Override
	public NPC getNPCInLocation(int locationId, int npcId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getElementInLocation(int locationId, int elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLocation(Location location) {
		locations.put(location.getId(), location);
	}

	@Override
	public void addNPC(NPC npc) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addElement(Element element) {
		// TODO Auto-generated method stub
	}

	@Override
	public Location getRootLocation() {
		return rootLocation;
	}

	@Override
	public void setRootLocation(Location rootLocation) {
		this.rootLocation = rootLocation;
		
	}

	@Override
	public Hero moveToLocation(Hero hero, int locationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
