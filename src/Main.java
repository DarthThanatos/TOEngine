import map.IMap;
import map.location.Location;
import monter.IMapMonter;
import monter.XMLMonter;

/*usage only in simplistic tests*/

public class Main {
	public static void main(String[] args){
		IMapMonter mapMonter = new XMLMonter();
		IMap map = mapMonter.createMap("FileSystem//Locations//root//root.xml");
		Location root = map.getRootLocation();
		System.out.println(root.toString());
		Location east = root.west;
		System.out.println(east.toString());
		System.out.println(east.east.toString());
	}
}
