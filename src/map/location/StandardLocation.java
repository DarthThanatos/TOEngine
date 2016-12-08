package map.location;

import java.security.KeyException;

import iterable.IterableChild;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import persistence.Hero;
import commands.Command;
import commands.InteractionResult;

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
	public void addNPCs(NodeList npcNodeList) {
		
	}

	@Override
	public void addNPC(String npcFileName) {
		
	}

	@Override
	public void addElements(NodeList elementNodeList) {
		
	}
	

	@Override
	public void addElement(String elementFileName) {
		
	}

	@Override
	public void addPossibleCommands(NodeList commandList) {
		for (int i = 0; i < commandList.getLength(); i++){
            Node command_node = commandList.item(i);
            addPossibleCommand(command_node.getTextContent());
		}	
	}

	@Override
	public InteractionResult executeCommand(Hero hero, String key, String command) {
		if (key.equals(this.key)){
			InteractionResult res = execute(hero, key,command);
			if(res.regardsMe) return res;
			for (IterableChild child: children){
				InteractionResult child_res = child.execute(hero, key, command);
				if(child_res.regardsMe) return child_res;
				
			}
		}
		return new InteractionResult(hero, "Chodzi Ci o cos konkretnego?", false, false);
	}
}
