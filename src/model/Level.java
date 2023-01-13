package model; 
	public class Level{

	//Constants
	public static final int ENEMYS= 25; 
		public static final int TREASURES= 50; 
	//Attributes
	private int id;
	private int requiredPoints;
	private int amountOfTreasures;
	private int cuantityOfEnemys;
	
	//relations
	private Enemy[] enemys;
	private Treasure[] treasures;
	private Dificulty difficultyLevel;
	
	//methods
	/**
	*<b>name:</b>Level<br> 
	* level builder<br> 
	* <b> post:</b> an object of type level will be created<br> 
	* @param id int si the identifier of the level
	* @param amountOfTreasures int is the number of treasures of that level 
	* @param requiredPoints int are the points required to be at this level
	* @param cuantityOfEnemys int is the number of enemies of that level
	* @param leveldificulty int is the difficulty of the level
	*/
	public Level(int id, int requiredPoints, int amountOfTreasures, int cuantityOfEnemys, int leveldificulty)
	{
		this.id= id;
		this.requiredPoints=requiredPoints;
		this.amountOfTreasures =amountOfTreasures;
		this.cuantityOfEnemys= cuantityOfEnemys;
		this.enemys= new Enemy[ENEMYS];
		this.treasures= new Treasure[TREASURES];
		switch(leveldificulty){
			case 0:difficultyLevel=Dificulty.NONE;
			break;
			case 1:difficultyLevel=Dificulty.LOW;
			break;
			case 2:difficultyLevel=Dificulty.MEDIUM;
			break;
			case 3:difficultyLevel=Dificulty.HIGH;
			break;
			
		}
	}
	
	/**
	*<b>name:</b>addTreasure<br> 
	* adds a treasure to the arrangement<br> 
	* <b> pre:</b> treasury data must be different from null<br> 
	* <b> post: </b>the treasure must be registered<br>  
	* @param  nameOfTreasure String is the name of the treasure
	* @param  image String is the URL of the treasure image
	* @param scoreGranted int is the number of points awarded for finding the treasure. 
	* @param  positionX int is the position on the x-axis of the treasure in the level
	* @param  positionY int is the position on the y-axis of the treasure in the level
	* @param  imageInAscii String is the randomly generated image that corresponds to the treasure. 
	*@return message of the positive method result if it was recorded negative if it could not be recorded
	*/
	
	public String addTreasure(String nameOfTreasure, String image, int scoreGranted, int positionX, int positionY, String imageInAscii){
		boolean comprobant=false;
		String message="";
		for (int i = 0; i<TREASURES-1 && !comprobant ; i++) {
				if (treasures[i]==null && !comprobant){
					treasures[i]= new Treasure(nameOfTreasure,  image,  scoreGranted,  positionX,  positionY,  imageInAscii);
					comprobant=true;
					message="el tesoro fue registrado exitosamente";
				}else{
					message="no se pudo registrar el tesoro por que se sobre excedio el limite de tesoros";
				}
			}
		return message;
	}
	
	/**
	*<b>name:</b>addEnemy<br> 
	* adds enemy to the arrangement <br> 
	* <b> pre:</b> enemy data must be different from null<br> 
	* <b> post: </b>the enemy must be registered <br> 
	* @param  enemyName String is the unique identifier of the enemy
	* @param  scoreRemoved int is the score taken away by the enemy when he defeats a player
	* @param  enemyScoreGive int is the score the enemy gives the player when he defeats him 
	* @param  Xposition int is the position on the x-axis of the enemy in the level
	* @param  Yposition int the position on the y-axis of the enemy in the level
	* @param  asciiImage String is the randomly generated image that corresponds to the enemy
	* @param TypeOfEnemy int  serves to store the type of the enemy .
	* @return message of the positive method result if it was recorded negative if it could not be recorded
	*/
	public String  addEnemy(String  enemyName, int scoreRemoved, int enemyScoreGive, int Xposition, int Yposition, String asciiImage, int TypeOfEnemy){
		boolean comprobant=false;
		String message="";
		if(!searchEnemys(enemyName)){
		for (int i = 0; i<ENEMYS-1 && !comprobant; i++) {
				if (enemys[i]==null && !comprobant){
					enemys[i]= new Enemy( enemyName,  scoreRemoved,  enemyScoreGive,  Xposition, Yposition,  asciiImage, TypeOfEnemy);
					comprobant= true;
					message="el enemigo fue registrado exitosamente";
				}else{
					message="no se pudo registrar el enemigo por que se sobre excedio el limite de enemigos para un nivel";
				}
			}	
		}else{
			message="el enemigo ya existe en este nivel";
		}
		return message;
	}
	
	/**
	*<b>name:</b>searchEnemys<br> 
	* search if the enemy is repeated or not <br> 
	* <b> pre:</b>  the enemy must exist<br> 
	* <b> post: </b>it will be determined whether the enemy is repeated or not. <br> 
	* @param  enemyName String is the id of the enemy which you want to know if it is repeated or not.
	* @return existEnemy will send a determination as to whether or not the enemy already exists. 
	*/
	public boolean searchEnemys(String enemyName){
		boolean existEnemy = false, comprobant= false; 
		for (int i=0; i<ENEMYS-1 && !comprobant; i++){
			if(enemys[i]!=null){
				if(enemys[i].getenemyName().equalsIgnoreCase(enemyName)){
					existEnemy=true;
					comprobant= true;
						}
			}else{
				comprobant=true;
			}
		}
	return existEnemy;
	}
	/**
	*<b>name:</b>countconsonants<br> 
	* search the quantity of consonants in enemies names  <br> 
	* <b> pre:</b>  the enemy must exist<br> 
	* <b> post: </b>it will be determined quantity of consonants in the enemies names <br> 
	* @return consonants1 which is in charge of saving the consonant counter  
	*/
	public int countconsonants(){
	int consonants1 = 0;
	char letter;
	String chain="";
	boolean comprobant=false;
		for(int p=0; p<=ENEMYS-1 && !comprobant;p++)
		if(enemys[p]!=null){
			for (int i = 0; i < enemys[i].getenemyName().length(); i++) {
			letter = chain.charAt(i);
			if (isConsonant(letter)) {
				consonants1++;
			}
		}
		}else{
			comprobant=true;
		}
		return consonants1;
	}
	/**
	*<b>name:</b>isConsonant<br>
	*is in charge of searching if the letter is a consonant 
	*<b> post: </b>it will be determined the letter is a consonant or not <br>
	*@param  letter char is a letter of the enemy's name	
	* @return  will send a determination as to whether or not the letter is a consonant. 
	*/
	public  boolean isConsonant(char letter) {
		return "bcdfghjklmnñpqrstvwxyz".contains(String.valueOf(letter).toLowerCase());
	}
	/**
	*<b>name:</b>countaTreasures<br>
	* search the number of times a treasure is repeated <br> 
	* <b> pre:</b>  the treasures must exist<br> 
	* <b> post: </b>will determine how many times the treasure is repeated in a level. <br> 
	* @param  trasuretosearch String save the name of the treasure to be searched.
	* @return countSystem will send a count of the quantity of times the treasure is found in a level
	*/
	public int countaTreasure(String trasuretosearch){
		int countSystem=0;
		boolean comprobant=false;
		for(int i=0;i<TREASURES-1 && !comprobant;i++){
			if(treasures[i]!=null){
				if(treasures[i]. getnameOfTreasure().equalsIgnoreCase(trasuretosearch)){
					countSystem++;
				}
			}else{
				comprobant=true;
			}
		}
		return countSystem;
	}
	/**
	*<b>name:</b>consultTreasures<br>
	* find the number of times all treasures are in a level   <br> 
	* <b> pre:</b>  the treasures must exist<br> 
	* <b> post: </b>it will be determined quantity of times appears every treasure in a level<br> 
	* @return message a message containing the name and the number of times each treasure is in the level separated by commas 
	*/
	public String  consultTreasures(){
		int countSystem=0;
		String message="", name="";
		boolean comprobant=false;
		for(int k=0;k<TREASURES-1;k++){
			for(int i=0;i<TREASURES-1 && !comprobant;i++){
				if(treasures[i]!=null){
					if(treasures[k].getnameOfTreasure().equalsIgnoreCase(treasures[i].getnameOfTreasure())){
						countSystem++;
					}
					
				}else{
				comprobant=true;
				if(treasures[k]!=null){
					name=treasures[k].getnameOfTreasure();
					message=message+ " el tesoro "+name+" se encuentra "+ countSystem+ " veces en el nivel,";
				}
				countSystem=0;
				}
		}
		}
		return message;
	}
	/**
	*<b>name:</b>consultEnemys<br> 
	* find the number of times all enemys are in a level   <br> 
	* <b> pre:</b>  the enemys must exist<br> 
	* <b> post: </b>it will be determined quantity of times appears every enemy in a level<br> 
	* @return message a message containing the name and the number of times each enemy is in the level separated by commas 
	*/
	public String consultEnemys(){
		String message="", name="";
		int countSystem=0;
		boolean comprobant=false;
		for(int k=0;k<ENEMYS-1;k++){
			for(int i=0;i<ENEMYS-1 && !comprobant;i++){
			if(enemys[i]!=null){
				if(enemys[k].getenemyName().equalsIgnoreCase(enemys[i].getenemyName())){
					countSystem++;
				}
					
			}else{
				comprobant=true;
				if(enemys[k]!=null){
					name=enemys[k].getenemyName();
					message= message+ " el enemigo "+name+" se encuentra "+ countSystem+ " veces en el nivel,";
				}
				countSystem=0;
			}
		}
		}
		
		return message;
	}
	/**
	*<b>name:</b>determinatedifficulty<br> 
	* will determine the difficulty of a level based on its number of treasures and enemies. <br> 
	* <b> pre:</b>the level must exist<br> 
	* <b> post: </b> will place a new difficulty at the level<br> 
	*/
	public void determinatedifficulty(){
		boolean comprobant=false;
		int countEnemys=0, countTreasures=0;
		for(int i=0;i<ENEMYS-1 && !comprobant;i++){
			if(enemys[i]!=null){
					countEnemys+=enemys[i].getenemyScoreGive();
			}else{
				comprobant=true;
				setcuantityOfEnemys(i-1);
			}
		}
		for(int i=0;i<TREASURES-1 && !comprobant;i++){
			if(treasures[i]!=null){
					countTreasures+=treasures[i].getscoreGranted();
			}else{
				comprobant=true;
				setamountOfTreasures(i-1);
			}
		}
		if(countTreasures>countEnemys){
			difficultyLevel=Dificulty.LOW;
		}else if(countTreasures==countEnemys){
			difficultyLevel=Dificulty.MEDIUM;
		}else if(countTreasures<countEnemys){
			difficultyLevel=Dificulty.HIGH;
		}
	}
	//getters
	public int getid(){
	   return id;
	}
	
	public int getrequiredPoints(){
		return requiredPoints;
		
	}
	
	public int getamountOfTreasures(){
		return amountOfTreasures;
		
	}
	
	public int getcuantityOfEnemys(){
		return cuantityOfEnemys;
		
	}
	
	public Enemy[] getenemys() {
        return enemys;
    }
	
	public Treasure[] gettreasures() {
        return treasures;
    }
	//setters
	
	public void setid(int id) {
        this.id = id;
    }
	
	public void setrequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }
	
	public void setamountOfTreasures(int amountOfTreasures) {
        this.amountOfTreasures = amountOfTreasures;
    }
	
	public void setcuantityOfEnemys(int cuantityOfEnemys) {
        this.cuantityOfEnemys = cuantityOfEnemys;
    }
    public void setenemys(Enemy[] enemys) {
        this.enemys = enemys;
    }
	
	public void settreasures(Treasure[] treasures) {
        this.treasures = treasures;
    }
}
