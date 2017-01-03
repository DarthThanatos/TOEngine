package junitfive;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assumptions.*;
import persistence.Hero;


@RunWith(JUnitPlatform.class)
class FirstJUnit5Test {
	static Hero hero;

	
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
	static void initHero(){
		hero = createHero(0,0,"Rob","Desc1");
		
	}
	
    @Test
    @DisplayName("Test")
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }
    @Test
    @DisplayName("")
    void emo(){
    	assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }
    
    @Test
    void groupedAssertions(TestReporter testerReporter) {
        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
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
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        assertEquals(2,2);
    }    
    
    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
            () -> "Aborting test: not on developer workstation");
        // remainder of test
        assertEquals("a string", "a string");
    }
    @Disabled
    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
            () -> {
                // perform these assertions only on the CI server
                assertEquals(3, 2);
            });

        // perform these assertions in all environments
        assertEquals("a string a", "a string");
    }
    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
    }

    @Test
    void reportSeveralValues(TestReporter testReporter) {
        HashMap<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");

        testReporter.publishEntry(values);
       
    }
    
    
}
