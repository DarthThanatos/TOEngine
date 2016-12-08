package commands;

import persistence.Hero;

public class InteractionResult extends Result {
	public Hero hero;
	public String resTxt;
	public boolean result;
	public boolean regardsMe;
	
	public InteractionResult (Hero hero, String resTxt, boolean result, boolean regardsMe){
		this.hero = hero;
		this.resTxt = resTxt;
		this.result = result;
		this.regardsMe = regardsMe;
	}
	
	public String getResultTxt(){
		return resTxt;
	}
}
