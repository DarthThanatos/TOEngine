package engine;

import commands.InteractionResult;
import commands.MovementResult;
import persistence.Hero;
import map.location.Location;

public interface IEngine {
	public Location getLocation(int locationId);
	public Location getLocation(Hero hero);
	public void addHeroToMap(Hero hero);
	public void removeHeroFromMap(Hero hero);
	public String interact(Hero hero, String cmd);
	public String startGame(Hero hero);
}
