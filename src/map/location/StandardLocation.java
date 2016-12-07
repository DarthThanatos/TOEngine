package map.location;

import iterable.IterableChild;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import commands.Command;

public class StandardLocation extends Location{

	public StandardLocation(Document doc){
		super(doc);
		NodeList commandList = ((Element)doc.getElementsByTagName("PossibleCommands").item(0)).getElementsByTagName("Text");
		NodeList elementList = doc.getElementsByTagName("Elements");
		NodeList npcList = doc.getElementsByTagName("NPCs");
		NodeList actionsReactions = ((Element)doc.getElementsByTagName("ActionsReactions").item(0)).getElementsByTagName("ActionReaction"); 
		addNPCs(npcList);
		addElements(elementList);
		addPossibleCommands(commandList);
		addReactions(actionsReactions);
	}
	
	public StandardLocation(int id) {
		super(id);
	}

	@Override
	public void addElement(String elementFileName) {
		
	}

	@Override
	public void addNPC(String npcFileName) {
		
	}

	@Override
	public void addNPCs(NodeList npcNodeList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addElements(NodeList elementNodeList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPossibleCommands(NodeList commandList) {
		for (int i = 0; i < commandList.getLength(); i++){
            Node command_node = commandList.item(i);
            addPossibleCommand(command_node.getTextContent());
		}	
	}

	@Override
	public void addReactions(NodeList reactionsList) {		
		for (int i = 0; i < reactionsList.getLength(); i++){
	        Element actionreaction_node = (Element)reactionsList.item(i);
	        String action = actionreaction_node.getElementsByTagName("Action").item(0).getTextContent();
	        Element reactionNode = (Element)(((Element)(actionreaction_node.getElementsByTagName("Reaction")).item(0)).getElementsByTagName("Method")).item(0);
	        String arg = reactionNode.getElementsByTagName("Arg").item(0).getTextContent();
	        String reaction = reactionNode.getElementsByTagName("Name").item(0).getTextContent();
	        addReaction(action,reaction,arg); //see iterablechild.addreaction
		}
		System.out.println("commands for" + key);
		for (Command cmd : commandMap.values()){
			cmd.work();
		}
	}

	@Override
	public void executeCommand(String key, String command) {
		if (key == this.key){
			execute(key,command);
		}
		else{
			boolean someoneAnswered = false;
			for (IterableChild child: children){
				someoneAnswered |= child.execute(key, command);
			}
			if(!someoneAnswered){
				System.out.println("Wrong command, see help");
			}
		}
	}

	@Override
	public boolean execute(String key, String command) {
		if(this.key == key) commandMap.get(key).work();
		return true;
	}

}
