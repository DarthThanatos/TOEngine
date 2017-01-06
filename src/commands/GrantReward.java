package commands;

import persistence.Hero;
import quests.Quest;

public class GrantReward implements Command {

	private String arg;
	
	public GrantReward(String arg){
		this.arg = arg;
	}
	
	@Override
	public InteractionResult work(Hero hero, Object obj) {
		String resTxt = arg;
		Quest quest = ((Quest)obj);
		hero.putQuestState(quest.getKey());
		resTxt += " Otrzymales nowy przedmiot: " + quest.getReward().toString() + "\n";
		resTxt += quest.getComment()+"\n";
		hero.addQuestObject(quest.getReward());
		InteractionResult result = new InteractionResult(hero,resTxt,true,true);
		return result;
	}

}