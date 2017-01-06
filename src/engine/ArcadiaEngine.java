package engine;

import commands.InteractionResult;
import commands.MovementResult;
import persistence.Hero;
import map.IMap;
import map.location.Location;
import monter.IMapMonter;
import monter.XMLMonter;

public class ArcadiaEngine implements IEngine {

	IMapMonter mapMonter;
	IMap map;
	
	public ArcadiaEngine(String rootFilePath){
		mapMonter = new XMLMonter();
		map = mapMonter.createMap(rootFilePath);
	}
	

	@Override
	public Location getLocation(int locationId) {
		return map.getLocation(locationId);
	}


	@Override
	public String interact(Hero hero, String action) {		
		if(action.split(" ")[0].equals("idz")){
			MovementResult movRes = map.moveToLocation(hero, action);
			return movRes.getResultTxt();
		}
		else{
			InteractionResult result = map.interact(hero, action);
			return result.getResultTxt();
		}
	}

	@Override
	public void addHeroToMap(Hero hero) {
		Location location = map.getLocation(hero.getLocationId());
		location.addHero(hero);
	}

	@Override
	public void removeHeroFromMap(Hero hero) {
		Location location = map.getLocation(hero.getLocationId());
		location.deleteHero(hero);
	}

	@Override
	public String startGame(Hero hero) {
		String resTxt = map.getLocation(hero.getLocationId()).toString(hero); 
		addHeroToMap(hero);
		return resTxt;
	}


	@Override
	public Location getLocation(Hero hero) {
		return map.getLocation(hero.getLocationId());
	}

}
