package engine;

import commands.InteractionResult;

import persistence.Hero;
import map.element.Element;
import map.location.Location;
import map.npc.NPC;
import map.npc.answer.Answer;

public interface IEngine {
	public Hero moveToLocation(Hero hero, int currentLocationId, int newLocationId);
	public Location getLocation(int locationId);
	public NPC getNPC(Hero hero, int npcId);
	public Element getElement(Hero hero, int elementId);
	public Location modifyMap(String fileName);
	public Answer talkToNPC(Hero hero, int npcId, String request);
	public Hero beginQuest(Hero hero, int npcId, int questId);
	public Answer interactWithElement(Hero hero, int elementId);
	public Answer guessWhatIsIt(Hero hero, int elementId);
	public Hero addToEquipment(Hero hero, int elementId);
	public InteractionResult interact(Hero hero, String cmd);
}
