package monter;

import map.IMap;
import map.element.Element;
import map.location.Location;
import map.npc.NPC;

public interface IMapMonter {
	public IMap createMap(String rootFileName);
}
