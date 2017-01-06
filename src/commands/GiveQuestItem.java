package commands;

import persistence.Hero;
import quests.Quest;

public class GiveQuestItem implements Command{

	private String arg; //name of the questObject to remove from our hero's equipment
	
	public GiveQuestItem(String arg) {
		this.arg = arg;
	}
	
	@Override
	public InteractionResult work(Hero hero, Object obj) {
		Quest quest = ((Quest)obj);
		hero.putQuestState(quest.getKey());
		String resTxt = "Oddales przedmiot: " + arg +"\n";
		resTxt += quest.getComment()+"\n";
		hero.removeQuestObject(arg);
		InteractionResult result = new InteractionResult(hero,resTxt,true,true);
		return result;
	}
	
}
