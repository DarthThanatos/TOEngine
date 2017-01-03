package map.element;

import org.w3c.dom.Document;
import iterable.IterableChild;

public abstract class Element extends IterableChild {

	private String name;
	
	public String getName(){
		return name;
	}
	
	public Element(Document doc){
        super(doc);
	        
	}
}
