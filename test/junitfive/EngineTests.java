package junitfive;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import map.IMap;
import map.location.Location;
import monter.IMapMonter;
import monter.XMLMonter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import commands.InteractionResult;
import commands.MovementResult;
import persistence.Hero;


@RunWith(JUnitPlatform.class)
class EngineTests {
	static Hero hero;
    static IMapMonter mapMonter;
	static IMap map; 
	
	private static Hero createHero(int id, int locId, String name, String desc){
		Hero hero = new Hero();
		hero.setId(id);
		hero.setName(name);
		hero.setDescription(desc);
		hero.setLocationId(locId);
		return hero;
		
	}
	
	@BeforeEach
	void printInfo(TestInfo testInfo,TestReporter testReporter){
		testReporter.publishEntry("test name", testInfo.getDisplayName());
	}
	
	@BeforeAll
	static void init(){
		mapMonter = new XMLMonter();
		map = mapMonter.createMap("FileSystem//Locations//root//root.xml");
		
	}
	
	@BeforeEach
	void initHero(){
		hero = createHero(0,0,"Rob","Desc1");		
	}
	
	@Test
	void checkHero(TestReporter testerReporter){
        assertAll("address",
                () -> assertEquals("Rob", hero.getName()),
                () -> assertEquals(0,hero.getLocationId())
            );	
        HashMap<String,String> report = new HashMap();
        report.put("name", hero.getName());
        report.put("description", hero.getDescription());
        testerReporter.publishEntry(report);	
	}
	
	@Test
	void checkGraphProperlyMounted(TestReporter testReporter){
		Location root = map.getLocation(0);
		Location loc1 = map.getLocation(1);
		Location loc2 = map.getLocation(2);
		Location loc3 = map.getLocation(3);
		assertEquals(root, loc1.getEast());
		assertEquals(root,loc2.getWest());
		assertEquals(loc1, root.getWest());
		assertEquals(loc2, root.getEast());
		assertEquals(loc2, loc3.getWest());
		assertEquals(loc3, loc2.getEast());
	}
	
	@Test
    @DisplayName("")
    void checkMovements(){
		String action = "idz zachod";
		MovementResult movRes = map.moveToLocation(hero, action); //going west
        assertTrue(movRes.getResultBool());
        assertEquals(1,hero.getLocationId());
        assertEquals(map.getLocation(1), map.getLocation(hero.getLocationId()));
        String roomDesc = "Przed Toba rozposciera sie standardowe pomieszczenie mieszkalne.";
        assertEquals(map.getLocation(hero.getLocationId()).getDescription(), roomDesc);
        action = "idz zachod";
        movRes = map.moveToLocation(hero,action); // after going west we want to go even further...
        assertEquals(movRes.getResultBool(),false);//... but we cannot
        //testReporter.publishEntry("res",movRes.getResultTxt());
        assertEquals(movRes.getResultTxt(), "There is no location zachod from here");
        //now go east and check if you are in root location
        movRes = map.moveToLocation(hero, "idz wschod");
        assertTrue(movRes.getResultBool());
        roomDesc = "Znajdujesz sie w poczatkowej lokacji zwanej root. Wpisz $help me root$ zeby wyswietlic pomoc ";
        assertEquals(roomDesc, map.getLocation(0).getDescription());
        action = "idz wschod";
        movRes = map.moveToLocation(hero, action); // move to location 2
        assertTrue(movRes.getResultBool());
        roomDesc = "Przed Toba rozposciera sie mroczne pomieszczenie. Widzisz kamienne podniesienie u stop poteznej kamiennej figury przedstawiajacej Winogrona";
        assertEquals(roomDesc, map.getLocation(2).getDescription());
        movRes = map.moveToLocation(hero,action); //move to location 3
        assertTrue(movRes.getResultBool());
        roomDesc = "Karczemna dzielnica rozposciera sie lasem szyldow i morzem glosow. Pijak stoi przed Toba wymiotujac. ";
        assertEquals(roomDesc, map.getLocation(3).getDescription());                
    }
    
    @Test
    void recognizingCommands(TestReporter testerReporter) {
        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
    	Location root = map.getLocation(0);
    	String key = "root", command = "krzyknij w", expectedResult = "Odpowiada Ci glucha cisza"; 
    	InteractionResult intRes = root.executeCommand(hero, key, command);
    	assertEquals(expectedResult, intRes.getResultTxt());
    	key = "sciana"; command = "przygladnij sie"; expectedResult = "To dziwna sciana";
    	intRes = root.executeCommand(hero, key, command);
    	assertEquals(expectedResult, intRes.getResultTxt());
    }
    
    @Test 
    void checkPlayers(){
    	//after changing locations, players list should not contain the hero who has just left
    	Location root = map.getLocation(0); // get root location, in which our hero sits
    	Location location1 = map.getLocation(1);
    	List<Hero> heroesList = root.getHeroes();
    	root.addHero(hero);
    	assertTrue(heroesList.contains(hero));
    	String cmd = "idz zachod"; 
    	map.moveToLocation(hero, cmd);
    	assertFalse(root.getHeroes().contains(hero));
    	assertTrue(location1.getHeroes().contains(hero));
    }
}
