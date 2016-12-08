package iterable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import persistence.Hero;
import commands.Command;
import commands.InteractionResult;

public abstract class IterableChild {
	
	protected HashMap<String, Command> commandMap;
	protected List<String> possibleCommands;
	protected String key;
	public IterableChild(){
		commandMap = new HashMap();
		possibleCommands = new ArrayList();
	}
	
	public void addReactions(NodeList reactionsList) {		
		for (int i = 0; i < reactionsList.getLength(); i++){
	        Element actionreaction_node = (Element)reactionsList.item(i);
	        String action = actionreaction_node.getElementsByTagName("Action").item(0).getTextContent();
	        Element reactionNode = (Element)(((Element)(actionreaction_node.getElementsByTagName("Reaction")).item(0)).getElementsByTagName("Method")).item(0);
	        String arg = reactionNode.getElementsByTagName("Arg").item(0).getTextContent();
	        String reaction = reactionNode.getElementsByTagName("Name").item(0).getTextContent();
	        addReaction(action,reaction,arg); //see iterablechild.addreaction
		}
	}
	
	public void addReaction(String command, String reaction,String arg){
		try {
			Class<?> cmd = Class.forName("commands." + reaction);
			Constructor<?> ctor = cmd.getConstructor(String.class);
			Command object = (Command)ctor.newInstance(arg);
			commandMap.put(command, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public InteractionResult execute(Hero hero,String key, String command) {
		try{
			if(this.key.equals(key)) 
				return commandMap.get(command).work(hero, this);
			return new InteractionResult(hero,"",false,false);
		}catch(Exception e){
			return new InteractionResult(hero,"",false,false);
		}
	}
	
	public String getPossibilities(){// including children possible actions
		String res = "";
		for (String cmd : possibleCommands){
			res += cmd + "\n";
		}
		return res;
	}
	
	public void addPossibleCommand(String command){
		possibleCommands.add(command); //enumeration of possibilities, not real command objects
	}
}
