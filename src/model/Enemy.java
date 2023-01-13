package model; 
	//Constants
	public class Enemy{
	//Attributes
	private String  enemyName;
	private int scoreRemoved;
	private int enemyScoreGive;
	private int Xposition;
	private  int Yposition;
	private String asciiImage;
	//relations

	private TypeOfEnemy typeofenemy;
	
	//methods
	/**
	*<b>name:</b>Enemy<br> 
	* Enemy builder <br> 
	* <b> pre:</b> enemy data must be different from null<br> 
	* <b> post: </b>an object of type Enemy will be created <br> 
	* @param  enemyName String is the unique identifier of the enemy
	* @param  scoreRemoved int is the score taken away by the enemy when he defeats a player
	* @param  enemyScoreGive int is the score the enemy gives the player when he defeats him 
	* @param  Xposition int is the position on the x-axis of the enemy in the level
	* @param  Yposition int the position on the y-axis of the enemy in the level
	* @param  asciiImage String is the randomly generated image that corresponds to the enemy
	* @param typeenemy int  serves to store the type of the enemy .
	*/
	public Enemy(String  enemyName, int scoreRemoved, int enemyScoreGive, int Xposition, int Yposition, String asciiImage, int typeenemy)
	{
		this.enemyName= enemyName;
		this.scoreRemoved=scoreRemoved;
		this.enemyScoreGive =enemyScoreGive;
		this.Xposition= Xposition;
		this.Yposition = Yposition; 
		this.asciiImage = asciiImage;
			switch(typeenemy){
			case 1: typeofenemy=TypeOfEnemy.OGRES;
			break;
			case 2: typeofenemy=TypeOfEnemy.ABSTRACTS;
			break;
			case 3: typeofenemy=TypeOfEnemy.MAGICALS;
			break;
			case 4: typeofenemy=TypeOfEnemy.BOSSES;
			break;
			}
		
	}
	
	//getters
	public String getenemyName(){
	   return enemyName;
	}
	
	public int getscoreRemoved(){
		return scoreRemoved;
		
	}
	
	public int getenemyScoreGive(){
		return enemyScoreGive;
		
	}
	
	public int getYposition(){
		return Xposition;
		
	}
	public int setYposition (){
		return Yposition; 
	}
	
	public String getasciiImage(){
	   return asciiImage;
	}
	//setters
	
	public void setenemyName(String enemyName) {
        this.enemyName = enemyName;
    }
	
	public void setscoreRemoved(int scoreRemoved) {
        this.scoreRemoved = scoreRemoved;
    }
	
	public void setenemyScoreGive(int enemyScoreGive) {
        this.enemyScoreGive = enemyScoreGive;
    }
	public void setXposition(int Xposition){
		this.Xposition= Xposition;
	}
	public void setYposition (int Yposition){
		this.Yposition = Yposition ;
	}
	public void setasciiImage(String asciiImage) {
        this.asciiImage = asciiImage;
    }
}
