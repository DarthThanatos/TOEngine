import java.util.Scanner;

import commands.InteractionResult;
import commands.MovementResult;
import persistence.Hero;
import map.IMap;
import map.location.Location;
import monter.IMapMonter;
import monter.XMLMonter;

/*usage only in simplistic tests*/

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
		IMapMonter mapMonter = new XMLMonter();
		IMap map = mapMonter.createMap("FileSystem//Locations//root//root.xml");
		Location root = map.getRootLocation();
		Hero hero = createHero(0,0,"Adrian","Brunatny Elf ze spalonymi wargami");
		Hero hero1 = createHero(1,0,"Robert","Czlowiek slusznego charakteru");
		root.addHero(hero1);
		System.out.println(
				"Main: " + map.getLocation(hero.getLocationId()).toString() );
		while(true){
			System.out.println("Main: Co chcesz zrobic?");
			boolean changedLocations = false;
			while(!changedLocations){
				String action = input.nextLine();
				if(action.split(" ")[0].equals("idz")){
					MovementResult movRes = map.moveToLocation(hero, action);
					System.out.println(movRes.getResultTxt());
					changedLocations = movRes.getResultBool();
				}
				else{
					InteractionResult result = map.interact(hero, action);
					System.out.println(result.getResultTxt());
				}
			}
		}
	}
}
