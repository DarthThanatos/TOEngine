package map.npc;

import org.w3c.dom.Document;

import persistence.Hero;
import commands.InteractionResult;

public class QuestGiver extends NPC{

	public QuestGiver(Document doc) {
		super(doc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InteractionResult execute(Hero hero, String key, String command) {
		InteractionResult result = super.execute(hero, key, command);
		if (result.regardsMe) return result;
		return new InteractionResult(hero,"",false,false);
	}


}
