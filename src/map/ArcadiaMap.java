package map;

import java.util.ConcurrentModificationException;
import java.util.HashMap;

import persistence.Hero;
import commands.Command;
import commands.InteractionResult;
import commands.MovementResult;
import map.element.Element;
import map.location.Location;
import map.npc.NPC;

public class ArcadiaMap implements IMap {

	private HashMap<Integer, Location>locations = new HashMap();
	private Location rootLocation;

	@Override
	public Location getLocation(int locationId) {
		if (locations.containsKey(locationId)) return locations.get(locationId);
		else return null;
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
	public  MovementResult moveToLocation(Hero hero, String cmd) {
		Location oldLocation = locations.get(hero.getLocationId());
		String way = cmd.split(" ")[1];
		HashMap<String,String> languageDict = new HashMap();
		languageDict.put("zachod","West"); 
		languageDict.put("wschod","East"); 
		languageDict.put("polnoc","North"); 
		languageDict.put("poludnie","South"); 		
		try{
			Location newLocation = (Location)oldLocation.getClass()
					.getMethod("get" + languageDict.get(way))
					.invoke(oldLocation);
			MovementResult res =  new MovementResult(hero,newLocation.toString(), true);
			oldLocation.deleteHero(hero);
			newLocation.addHero(hero);
			hero.setLocationId(newLocation.getId());
			return res;
		}catch(Exception e){
			return new MovementResult(hero,"There is no location " + way + " from here", false);
		}
	}



	@Override
	public InteractionResult interact(Hero hero, String cmd) {
		String[] elems = cmd.split(" ");
		String command = "";
		String key = elems[elems.length - 1];
		// help me please root -> [help me please, root]
		for (int  i = 0; i < elems.length - 1; i++) {command += elems[i]; if(i <= elems.length - 1 - 2 ) command += " ";}
		return locations.get(hero.getLocationId()).executeCommand(hero, key, command);
	}

}
