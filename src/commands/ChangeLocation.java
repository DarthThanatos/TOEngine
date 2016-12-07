package commands;

import map.location.Location;

public class ChangeLocation implements Command {

	Location oldLocation;
	Location newLocation;
	
	public ChangeLocation(String arg){
		String[] ids = arg.split(" ");
		//oldLocation = 
	}
	
	
	public ChangeLocation(Location oldLocation, Location newLocation){
		this.oldLocation = oldLocation;
		this.newLocation = newLocation;
	}
	@Override
	public void work() {
		// TODO Auto-generated method stub
		System.out.println("changeloc");
	}

}
