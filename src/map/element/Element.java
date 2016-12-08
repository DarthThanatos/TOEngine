package map.element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import iterable.IterableChild;

public abstract class Element extends IterableChild {
	
	public Element(Document doc){
        super(doc);
	        
	}
}
