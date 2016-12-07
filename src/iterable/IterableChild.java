package iterable;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import commands.Command;

public abstract class IterableChild {
	
	protected HashMap<String, Command> commandMap;
	public IterableChild(){
		commandMap = new HashMap();
	}
	
	public void addReaction(String command, String reaction,String arg){
		try {
			System.out.println("reaction " + command + " " + reaction);
			Class<?> cmd = Class.forName("commands." + reaction);
			Constructor<?> ctor = cmd.getConstructor(String.class);
			Command object = (Command)ctor.newInstance(arg);
			commandMap.put(command, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract boolean execute(String key, String command);
}
