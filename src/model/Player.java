 package model; 
	public class Player{
	//Attributes
	private String  nickname;
	private int score;
	private int level;
	private int numberOfLifes;
	private String name;
	//constants
	 public static final int ENEMY= 25;
	 public static final int TREASURES= 50;
	//relations
	private Level actuallevel[];
	private Enemy candefeat[] ;
	private Treasure find[];
	
	
	//methods
	/**
	*<b>name:</b>Player<br> 
	* Player builder <br> 
	* <b> pre:</b> Player data must be different from null<br> 
	* <b> post: </b>an object of type Player will be created <br> 
	* @param nickname is a String data type that serves as the unique identifier (id) of the player
	* @param score is an int data used to store the player's score
	* @param level is an int data that serves to determine the level where the player is at the moment
	* @param numberOfLifes is an int data that stores the number of lives a player currently has
	* @param  name is a String that stores the player's real name 
	*/
	public Player(String nickname, int score, int level, int numberOfLifes, String name )
	{
		this.nickname= nickname;
		this.score=score;
		this.level =level;
		this.numberOfLifes= numberOfLifes;
		this.name = name; 
		this.actuallevel= new Level[10];
		this.candefeat= new Enemy[ENEMY];
		this.find=  new Treasure[TREASURES];
	}
	
	//getters
	public String getnickname(){
	   return nickname;
	}
	
	public int getscore(){
		return score;
		
	}
	
	public int getlevel(){
		return level;
		
	}
	
	public int getnumberOfLifes(){
		return numberOfLifes;
		
	}
	public String getname (){
		return name; 
	}
	
	//setters
	
	public void setnickname(String nickname) {
        this.nickname = nickname;
    }
	
	public void setscore(int score) {
        this.score = score;
    }
	
	public void setlevel(int level) {
        this.level = level;
    }
	public void setnumberOfLifes(int numberOfLifes){
		this.numberOfLifes= numberOfLifes;
	}
	public void setname (String name ){
		this.name = name ;
	}
}
