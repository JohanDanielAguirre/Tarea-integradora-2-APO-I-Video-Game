package model; 
	//Constantes
	public class Treasure{
	//Atributes
	private String nameOfTreasure;
	private  String image;
	private  int scoreGranted;
	private int positionX;
	private  int positionY;
	private String imageInAscii;
	//relations
	
	
	//methods
	/**
	*<b>name:</b>Treasure<br> 
	* Treasure builder <br> 
	* <b> pre:</b> Treasure data must be different from null<br> 
	* <b> post: </b>an object of type Treasure will be created <br> 
	* @param  nameOfTreasure String is the name of the treasure
	* @param  image String is the URL of the treasure image
	* @param scoreGranted  int is the number of points awarded for finding the treasure
	* @param  positionX int is the position on the x-axis of the treasure in the level
	* @param  positionY int is the position on the y-axis of the treasure in the level
	* @param  imageInAscii String is the randomly generated image that corresponds to the treasure
	*/
	public Treasure(String nameOfTreasure, String image, int scoreGranted, int positionX, int positionY, String imageInAscii)
	{
		this.nameOfTreasure= nameOfTreasure;
		this.image=image;
		this.scoreGranted =scoreGranted;
		this.positionX = positionX; 
		this.positionY= positionY;
		this.imageInAscii = imageInAscii;
	}
	
	//getters
	public String getnameOfTreasure(){
	   return nameOfTreasure;
	}
	
	public int getscoreGranted(){
		return scoreGranted;
		
	}
	
	public String getimage(){
		return image;
		
	}
	
	public int getpositionX(){
		return positionX;
		
	}
	public int getpositionY (){
		return positionY; 
	}
	
	public String getimageInAscii(){
	   return imageInAscii;
	}
	
	//setters
	
	public void setnameOfTreasure(String nameOfTreasure) {
        this.nameOfTreasure = nameOfTreasure;
    }
	
	public void setimage(String image) {
        this.image = image;
    }
	
	public void setscoreGranted(int scoreGranted) {
        this.scoreGranted = scoreGranted;
    }
	public void setpositionX(int positionX){
		this.positionX= positionX;
	}
	public void setpositionY (int positionY){
		this.positionY = positionY ;
	}
	
	public void setimageInAscii(String imageInAscii) {
        this.imageInAscii = imageInAscii;
    }
}
