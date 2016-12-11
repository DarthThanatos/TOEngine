package engine;

import commands.InteractionResult;

import persistence.Hero;
import map.element.Element;
import map.location.Location;
import map.npc.NPC;
import map.npc.answer.Answer;

public class ArcadiaEngine implements IEngine {

	@Override
	public Hero moveToLocation(Hero hero, int currentLocationId,
			int newLocationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation(int locationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NPC getNPC(Hero hero, int npcId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getElement(Hero hero, int elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location modifyMap(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer talkToNPC(Hero hero, int npcId, String request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hero beginQuest(Hero hero, int npcId, int questId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer interactWithElement(Hero hero, int elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer guessWhatIsIt(Hero hero, int elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hero addToEquipment(Hero hero, int elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InteractionResult interact(Hero hero, String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

}
