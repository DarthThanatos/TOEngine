package commands;

public class Communicate implements Command{

	String arg; 
	
	public Communicate(String arg){
		this.arg = arg;
	}
	
	@Override
	public void work() {
		System.out.println(arg);
	}

}
