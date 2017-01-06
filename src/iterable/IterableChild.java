package iterable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import persistence.Hero;
import commands.Command;
import commands.InteractionResult;

public abstract class IterableChild {
	
	protected HashMap<String, Command> commandMap;
	protected List<String> possibleCommands;
	protected List<IterableChild> children;
	protected String key;
	protected String description;
	protected int id;
	
	public IterableChild(Document doc){
		commandMap = new HashMap();
		possibleCommands = new ArrayList();
		this.description = doc.getElementsByTagName("Description").item(0).getTextContent();
		this.children = new ArrayList();
		this.id = Integer.parseInt(((org.w3c.dom.Element)(doc.getDocumentElement())).getAttribute("id"));
		this.key = ((org.w3c.dom.Element)(doc.getDocumentElement())).getAttribute("key");
		NodeList actionsReactions = ((Element)doc.getElementsByTagName("ActionsReactions").item(0)).getElementsByTagName("ActionReaction");
		NodeList commandList = ((Element)doc.getElementsByTagName("PossibleCommands").item(0)).getElementsByTagName("Text");
		addPossibleCommands(commandList);
		addReactions(actionsReactions);
	}
	
	public String getKey(){
		return key;
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
		try {//guava
			Class<?> cmd = Class.forName("commands." + reaction);
			Constructor<?> ctor = cmd.getConstructor(String.class);
			Command object = (Command)ctor.newInstance(arg);
			commandMap.put(command, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public InteractionResult executeCommand(Hero hero, String key, String command){
		try{
			if(this.key.equals(key)){
				return commandMap.get(command).work(hero, this);
			}
			return new InteractionResult(hero,"",false,false);
		}catch(Exception e){
			return new InteractionResult(hero,"",false,false);
		}
	}
	
	public InteractionResult execute(Hero hero,String key, String command) {
		if (key.equals(this.key)){
			InteractionResult res = executeCommand(hero, key,command);
			if(res.regardsMe) return res;
		}
		else{
			for (IterableChild child: children){
				InteractionResult child_res = child.execute(hero, key, command);
				if(child_res.regardsMe) return child_res;
				
			}
		}
		return new InteractionResult(hero, "Chodzi Ci o cos konkretnego?", false, false);
	}
	
	public String getPossibilities(Hero hero){// including children possible actions
		String res = "";
		for (String cmd : possibleCommands){
			res += "	->" + cmd + "\n";;
		}
		return res;
	}
	
	public void addPossibleCommands(NodeList commandList) {
		for (int i = 0; i < commandList.getLength(); i++){
            Node command_node = commandList.item(i);
            addPossibleCommand(command_node.getTextContent());
		}	
	}
	
	public void addPossibleCommand(String command){
		possibleCommands.add(command); //enumeration of possibilities, not real command objects
	}
	
	public String getDescription(Hero hero){
		return description;
	}
	
	public String getAllPossibleCommands(Hero hero){
		String res = getPossibilities(hero);
		for(IterableChild child: children) 
			res += child.getAllPossibleCommands(hero);
		return res;
	}
	
	public String getAllPossibleDescriptions(Hero hero){
		String res = description + "\n";
		for(IterableChild child : children){
			res += child.getAllPossibleDescriptions(hero);
		}
		return res;
	}
}
