package map;

import commands.InteractionResult;
import commands.MovementResult;
import persistence.Hero;
import map.element.Element;
import map.location.Location;
import map.npc.NPC;

public interface IMap {
	public Location getLocation(int locationId);
	public Location getRootLocation();
	public void setRootLocation(Location rootLocation);
	public NPC getNPCInLocation(int locationId, int npcId);
	public Element getElementInLocation(int locationId, int elementId);
	public void addLocation(Location location);
	public void addNPC(NPC npc);
	public void addElement(Element element);
	public MovementResult moveToLocation(Hero hero, String cmd);
	public InteractionResult interact(Hero hero, String cmd);
}
