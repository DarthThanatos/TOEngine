package commands;

import persistence.Hero;

public class MovementResult extends Result{
	Hero hero;
	String resTxt;
	boolean result;
	
	public MovementResult (Hero hero, String resTxt, boolean result){
		this.hero = hero;
		this.resTxt = resTxt;
		this.result = result;
	}
	
	public String getResultTxt(){
		return resTxt;
	}
	
	public boolean getResultBool(){
		return result;
	}

}
