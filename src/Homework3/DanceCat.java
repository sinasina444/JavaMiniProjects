package Homework3;

import java.util.Random;

public class DanceCat{
    private static String[] parseMoves(String unparsedMoves){
    	int len = unparsedMoves.length();
    	String[] parsedMoves = new String[18];
    	for(int i = 0;i<18;i++){
    		if(i>=len){
    			parsedMoves[i] = "";
    		}else{
    			parsedMoves[i] = unparsedMoves.substring(i, i+1);
    		}
    	}
    	return parsedMoves;
    }
    
    private static DanceMove[] createDanceMoves(String[] moves,String[] idealMoves){
    	DanceMove[] danceMoves = new DanceMove[18];
    	for(int i = 0;i<18;i++){
    		danceMoves[i] = new DanceMove(idealMoves[i],moves[i]);
    	}
    	return danceMoves;
    }
    
    public static int getComputerLevel(){
    	Random random = new Random();
    	return random.nextInt(101);
    }
	
	private final String name;
	private DanceMove[] danceMoves;
	
    public DanceCat(String unparsedMoves,String[] idealMoves){
    	this(parseMoves(unparsedMoves),idealMoves);
    }
    public DanceCat(String[] moves,String[] idealMoves){
    	this(createDanceMoves(moves,idealMoves));
    }
    public DanceCat(DanceMove[] danceMoves){
    	this("Alex",danceMoves);
    }
    public DanceCat(String name,DanceMove[] danceMoves){
    	this.name = name;
    	this.danceMoves = danceMoves;
    }
    
    public String getName(){
    	return name;
    }
    
    public DanceMove[] getDanceMoves(){
    	return danceMoves;
    }
    
    public int getNumberOfCorrectMoves(){
    	int res=0;
    	for(int i = 0;i<18;i++){
    		if(danceMoves[i].correctMove()){
    			res++;
    		}
    	}
    	return res;
    }
    
}
