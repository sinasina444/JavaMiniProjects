package Homework3;

public class DanceMove{
	
	private final String idealMove;
	private final String userMove;
	public DanceMove(String idealMove,String userMove){
		this.idealMove = new String(idealMove);
		this.userMove = new String(userMove);
	}
	
	public String getIdealMove(){
		return new String(idealMove);
	}
	
	public String getUserMove(){
		return new String(userMove);
	}
	
	public boolean correctMove(){
		return idealMove.equals(userMove);
	}
}

