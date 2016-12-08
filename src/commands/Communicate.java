package commands;

import persistence.Hero;

public class Communicate implements Command{

	String arg; 
	
	public Communicate(String arg){
		this.arg = arg;
	}
	
	@Override
	public InteractionResult work(Hero hero, Object arg) {
		return new InteractionResult(hero, this.arg, true, true);
	}

}
