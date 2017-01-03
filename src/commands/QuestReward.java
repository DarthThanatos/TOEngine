package commands;

import map.location.Location;
import persistence.Hero;

public class QuestReward implements Command {

	public QuestReward(String arg){
		
	}
	
	@Override
	public InteractionResult work(Hero hero, Object obj) {
		String resTxt = ((Object[])obj)[0].toString();
		
		InteractionResult result = new InteractionResult(hero,resTxt,true,true);
		return result;
	}

}