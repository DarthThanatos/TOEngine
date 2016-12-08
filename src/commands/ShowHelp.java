package commands;

import map.location.Location;
import persistence.Hero;

public class ShowHelp implements Command {

	public ShowHelp(String arg){
		
	}
	
	@Override
	public InteractionResult work(Hero hero, Object obj) {
		// TODO Auto-generated method stub
		String resTxt = ((Location)obj).getAllPossibleCommands();
		InteractionResult result = new InteractionResult(hero,resTxt,true,true);
		return result;
	}

}
