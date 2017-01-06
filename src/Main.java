import java.util.Scanner;

import commands.InteractionResult;
import commands.MovementResult;
import engine.ArcadiaEngine;
import engine.IEngine;
import persistence.Hero;
import map.IMap;
import map.location.Location;
import monter.IMapMonter;
import monter.XMLMonter;

public class Main {
	
	private static Hero createHero(int id, int locId, String name, String desc){
		Hero hero = new Hero();
		hero.setId(id);
		hero.setName(name);
		hero.setDescription(desc);
		hero.setLocationId(locId);
		return hero;
	}
	
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args){
		IEngine engine = new ArcadiaEngine("FileSystem//Locations//root//root.xml");
		Hero hero = createHero(0,0,"Adrian","Brunatny Elf ze spalonymi wargami");
		Hero hero1 = createHero(1,0,"Robert","Czlowiek slusznego charakteru");
		engine.addHeroToMap(hero1); //need to delete this hero from map after logout! use method engine.deleteHeroFromMap(hero)		
		
		System.out.println(engine.startGame(hero));
		while(true){
			System.out.println("Co chcesz zrobic?");
			String action = input.nextLine();
			System.out.println(engine.interact(hero, action));		
		}
	}
}
