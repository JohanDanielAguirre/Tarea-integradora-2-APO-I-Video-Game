package ui;
import java.util.Scanner;
import model.*;
public class VideogameApp {
    public static Scanner sc= new Scanner(System.in);
	//relations 
	private Game game;
	/**
	*name: VideogameApp <br>
	*builder of VideogameApp<br>
	*<b>pre:</b> gameresolution!= null<br>
	* <b> post:</b> a Game object will be created<br>
	* @param gameresolution is an int datatype that will display the resolution chosen by the user
	*/
	public VideogameApp(int gameresolution){
		game= new Game(gameresolution);
	}
	/**
	*name: main <br>
	* main method of user interface <br>
	* <b>post:</b> should be directed to the method of execution <br>
	*/
    public static void main(String[] args) {
		int gameresolution=0;
		do{
		System.out.println(" ingrese la resolucion en la que desea jugar\n"
		+"1. resolucion SD (640x480) \n"
		+"2. resolucion QHD1 (960x540) \n"
		+"3. resolucion HD (1280x720) \n"
		+"4. resolucion FHD (1920x1080) \n"
		+"5. resolucion QHD2 (2560x1440) \n"
		+"6. resolucion UHD (3840x2160) \n"
		+"7. resolucion 8K (7640x4320) \n");
		gameresolution= sc.nextInt();
		}while(gameresolution>7 );
		VideogameApp videogameApp= new VideogameApp(gameresolution);
        videogameApp.menu();	
    }
	/**
	*<b>name:</b> menu <br>
	* eexecute the program <br>
	*<b>pre:</b> the object of type VideogameApp must have been created<br>
	* <b>post:</b> directs the different sub-processes of the system<br>
	*/
	public  void menu() {
	 	int cuantityofplayers=0, iplayers=0, rlevels=1, Penemys= 0, Ztreasures= 0, cuantityofenemys=0, cuantityoftreasures= 0, position= 0, newscore=0, enemylevel=0, treasurelevel=0, randomnumber=0; 
		String  message=" ", trasuretosearch=" ";
		do{
			registerlevel(rlevels);
			rlevels++;
		}while(rlevels<=10);
		do{
			message=" ";
			System.out.println("menu de opciones\n"
		 +"si desea registrar jugadores presione 1\n"
		 +"si desea registrar enemigos presione 2\n"
		 +"si desea registrar tesoros presione 3\n"
		 +"si desea modificar el puntaje del jugador presione 4\n"
		 +"si desea ver si puede pasar de nivel ingrese 5\n"
		 +"si desea saber cuantos tesoros y enemigos hay en un nivel ingrese 6\n"
		 +"si desea ver la cantidad de un tesoro en especifico en todo el juego ingrese 7\n"
		 +"si desea ver cual es el tesoro mas repetido ingrese 8\n"
		 +"si desea ver cual es el enemigo que otorga mayor puntaje ingrese 9\n"
		 +"si desea ver la cantidad de consonantes en los nombres de los enemigos ingrese 10\n"	
		 +"si desea ver el top 5 de los mejores jugadores  ingrese 11\n"	
		 +"si desea salir presione 12");
			position= sc.nextInt();
			switch (position){
				case 1:
					iplayers=0;
					System.out.println("ingrese la cantidad de jugadores con limite de 10");
					cuantityofplayers= sc.nextInt()-1;
					if (cuantityofplayers<=19 && cuantityofplayers>=0){
						do{
						registerPlayer();
						iplayers++;
					}while(cuantityofplayers>=iplayers);
					}
					else{
						System.out.println("cantidad de jugadores a registrar invalida");
					}
					
				break;
				
				case 2:
					System.out.println("ingrese el nivel donde desea registrar los enemigos con limite de 10 niveles");
						enemylevel= sc.nextInt()-1;
					if(enemylevel<=9 && enemylevel>=0){
						System.out.println("ingrese la cantidad de enemigos en el nivel ");
						cuantityofenemys= sc.nextInt()-1;
						do{
							registerEnemy(enemylevel);
							Penemys++;
						}while(cuantityofenemys>=Penemys);
						game.determinatedifficulty(enemylevel);
					}else{
						System.out.println("no se pueden registrar enemigos en un nivel que no existe");
					}
				break;
				
				case 3:
						System.out.println("ingrese el nivel donde desea registrar los tesoros con limite de 10 niveles");
						treasurelevel= sc.nextInt()-1;
					if(treasurelevel<=9 &&  treasurelevel>=0) {
						System.out.println("ingrese la cantidad de tesoros en el nivel ");
						cuantityoftreasures= sc.nextInt()-1;
						do{
							registerTreasure(treasurelevel);
							Ztreasures++;
						}while(cuantityoftreasures>=Ztreasures);
						game.determinatedifficulty(treasurelevel);
					}else{
						System.out.println("no se pueden registrar tesoros en un nivel que no existe");
					}
				
				break;
				
				case 4:
					System.out.println("digite el numero del jugador que desea cambiar");
					iplayers= sc.nextInt();
					System.out.println("digite el puntaje que desea sumarle al jugador");
					newscore= sc.nextInt();
					message= game.modificatescore(iplayers, newscore);
					System.out.println(message);
				break;
				case 5:
					System.out.println("digite el numero del jugador sobre el cual desea hacer la consulta");
					iplayers= sc.nextInt();
					message= game.consultAvailableLevel(iplayers);
					System.out.println(message);
					randomnumber=(int)(Math.random()*2);
					if (randomnumber>1){
						message= game.randomevents();
						System.out.println(message);
					}
				break;
				case 6:
					System.out.println("ingrese el nivel del cual desea realizar la consulta");
					rlevels= sc.nextInt();
					message= game.AvailableTreasuresAndEnemysInALevel(rlevels-1);
					System.out.println(message);
					randomnumber=(int)(Math.random()*2);
					if (randomnumber>1){
						message= game.randomevents();
						System.out.println(message);
					}
				break;
				case 7:
					System.out.println("digite el nombre del tesoro del cual desea realizar la consulta");
					trasuretosearch=sc.nextLine();
					trasuretosearch=sc.nextLine();
					message=game.searchSpecificTreasure(trasuretosearch);
					System.out.println(message);
					randomnumber=(int)(Math.random()*2);
					if (randomnumber>1){
						message= game.randomevents();
						System.out.println(message);
					}
				break;
				case 8:
					message=game.mostRepeatedTreasure();
					System.out.println(message);
					randomnumber=(int)(Math.random()*2);
					if (randomnumber>1){
						message= game.randomevents();
						System.out.println(message);
					}
				break;
				case 9:
					message=game.mostvaluableenemy();
					System.out.println(message);
					randomnumber=(int)(Math.random());
					if (randomnumber>1){
						message= game.randomevents();
						System.out.println(message);
					}
				break;
				case 10:
					message=game.consonants();
					System.out.println(message);
					randomnumber=(int)(Math.random());
					if (randomnumber==1){
							message= game.randomevents();
							System.out.println(message);
						}
				break;
				case 11:
					message=game.podium();
					System.out.println(message);
					randomnumber=(int)(Math.random());
					if (randomnumber==1){
							message= game.randomevents();
							System.out.println(message);
						}
				break;
				case 12:
					message= game.finalizationimages();
					System.out.println(message);
					System.out.println("over runtime.......\n" 
					+ "ending process...");
				break;
				default:
					System.out.println("ingrese una opcion valida");
				break;
			}
		}while(position!=12);
	}
	/**
	*<b>name:</b> registerPlayer<br>
	* data must be provided to create a player<br>
	* <b> post:</b> will be sent to the builder of the player<br>
	*/
	public   void registerPlayer() {
		String nickname ="",  name = "", message="";
		System.out.println("por favor escriba su nombre real");
		sc.nextLine();
		name=sc.nextLine();
		System.out.println("por favor escriba su nickname");
		sc.nextLine();
		nickname=sc.nextLine();
		int score =0,  level = 0,  numberOfLifes = 0;
			score=10;
			level=1;
			numberOfLifes=3;
		message=game.addPlayer( nickname, score, level,  numberOfLifes,  name);
		System.out.println(message);
		
	}
	/**
	*<b>name: </b>registerTreasure<br>
	* data must be provided to create a Treasure<br>
	*<b>pre:</b> treasurelevel must be in valid range <br>
	*@param treasurelevel is int and this variable will store the data of the level selected by the user where the treasures will be created<br>
	* <b> post:</b> will be sent to the builder of the Tresure<br>
	*/
	public  void registerTreasure(int treasurelevel) {
		String nameOfTreasure= "", image="", message="";
		int scoreGranted=0;
			System.out.println("ingrese el nombre del tesoro en plural");
			sc.nextLine();
			nameOfTreasure= sc.nextLine();
			System.out.println("ingrese el URL de la imagen");
			sc.nextLine();
			image= sc.nextLine();
			System.out.println("ingrese el puntaje que otorga el tesoro");
			scoreGranted= sc.nextInt();
			sc.nextLine();
			message=game.registerTreasure(treasurelevel,nameOfTreasure,  image,  scoreGranted);
			System.out.println(message);
	}
	/**
	*<b>name: </b>registerEnemy<br>
	* data must be provided to create a Enemy<br>
	*<b>pre:</b> enemylevel must be in valid range <br>
	*@param enemylevel is int and this variable will store the data of the level selected by the user where the treasures will be created<br>
	* <b> post:</b> will be sent to the method registerEnemy in game<br>
	*/
	public  void registerEnemy(int enemylevel) {
		String enemyName= "",message="";
		int scoreRemoved=0, enemyScoreGive= 0, typeenemy=0;
			sc.nextLine();
			System.out.println("ingrese el nombre del enemigo");
			enemyName= sc.nextLine();
			System.out.println("ingrese el puntaje que reduce el enmigo");
			scoreRemoved= sc.nextInt();
			sc.nextLine();
			System.out.println("ingrese el puntaje que otorga");
			enemyScoreGive= sc.nextInt();
			sc.nextLine();
			
		do{	
			System.out.println("menu de tipos de enemigos\n"
		 +"para tipo ogros presione 1\n"
		 +"para tipo abstractos presione 2\n"
		 +"para tipo magicos presione 3\n"
		 +"para tipo jefes presione 4\n");
		 typeenemy= sc.nextInt();
		}while(typeenemy>5);
			message= game.registerEnemy(enemylevel,  enemyName,  scoreRemoved,  enemyScoreGive,typeenemy);
		    System.out.println(message);
	}
	/**
	 *<b>name:</b> registerlevel<br>
	 * the system will generate the data to generate a level <br>
	 * <b> post:</b> will be sent to the method registerlevel in Game<br>
	 * @param rlevels int is the number of the actual level to register
	 */
	public void registerlevel(int rlevels){
		int id=rlevels,  requiredpoints=(rlevels*10);
		game.addlevel( id, requiredpoints,0,0);
	}
}
