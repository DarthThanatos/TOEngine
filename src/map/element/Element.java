package map.element;

import iterable.IterableChild;

public abstract class Element extends IterableChild {
	private String description;
	
	public Element(String fileName){
		
	}
	
	public String getDescription(){
		return description;
	}
}
