package map.element;

import org.w3c.dom.Document;

public class QuestItem extends Element{

	private String questTag;

	public String getQuestTag(){
		return questTag;
	}
	
	public QuestItem(Document doc) {
		super(doc);
	}
}
