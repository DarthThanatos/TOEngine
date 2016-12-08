package commands;

import persistence.Hero;

public interface Command {
	public InteractionResult work(Hero hero, Object arg);
}
