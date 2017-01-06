package quests;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import commands.InteractionResult;
import iterable.IterableChild;
import persistence.EquipmentElement;
import persistence.Hero;

/*
 * This class represents details connected with a quest
 * @param conditionToStart - describes condition that triggers an event - default is just "any"
 * quest notification   
 * @param commentAfterCompletion - what is displayed on the screen after a completion of the task
 */

public class Quest extends IterableChild {
	
	private EquipmentElement reward;
	private String conditionToStart;
	private String commentAfterCompletion;
	
	public Quest(Document doc) {
		super(doc);
		conditionToStart = doc.getElementsByTagName("ConditionToStart").item(0).getTextContent();
		commentAfterCompletion = doc.getElementsByTagName("CommentAfterCompletion").item(0).getTextContent();
		Element rewardElement = (Element)doc.getElementsByTagName("Reward").item(0);
		if(rewardElement!=null) {
			String itemName = rewardElement.getElementsByTagName("Name").item(0).getTextContent();
			reward = new EquipmentElement(itemName, key); //questTag is simply the key
		}
	}
	
	public String getComment(){
		return commentAfterCompletion;
	}
	
	public EquipmentElement getReward(){
		return reward;
	}
	
	@Override 
	public String getDescription(Hero hero){
		if (hero.hasQuestState(conditionToStart) && !hero.hasQuestState(key))
			return description;
		else return "";
	}
	
	@Override
	public InteractionResult execute(Hero hero,String key, String command){
		if(hero.hasQuestState(conditionToStart) && !hero.hasQuestState(key))
			return super.execute(hero, key, command);
		else return new InteractionResult(hero,"",false,false);
	}
	
	@Override 
	public String getPossibilities(Hero hero){
		if(hero.hasQuestState(conditionToStart) && !hero.hasQuestState(key)){
			return super.getPossibilities(hero);
		}
		else return "";
	}
	
	@Override 
	public String getAllPossibleDescriptions(Hero hero){
		// a quest is (at least for now) a leaf of the tree. It does not have any children, 
		//so it's getAllPossibleDescriptions method is identical to getDescription method 
		if (hero.hasQuestState(conditionToStart) && !hero.hasQuestState(key))
			return description;
		else return "";
	}
}
