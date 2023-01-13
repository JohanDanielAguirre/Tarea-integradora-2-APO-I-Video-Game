package model; 
public class Game{
	//constants
	public static final int LEVEL=10 ;
	public static final int PLAYERS= 20;
	//relations
	private Level[] levels; 
	private Player[] players;
	private Resolution resolution;
	//methods
	/**
	*name:Game <br> 
	* builder of Game<br>
	*<b>pre:</b> gameresolution!= null<br>
	* <b> post:</b> a Game object will be created<br>
	* @param gameresolution an int datatype that will display the resolution chosen by the user
	*/
	public Game(int gameresolution){
	 this.levels= new Level [LEVEL];
	 this.players= new Player[PLAYERS];
	 switch(gameresolution){
			case 1: resolution=Resolution.SD;
			break;
			case 2:resolution=Resolution.QHD1;
			break;
			case 3: resolution=Resolution.HD;
			break;
			case 4: resolution=Resolution.FHD;
			break;
			case 5: resolution=Resolution.QHD2;
			break;
			case 6: resolution=Resolution.UHD;
			break;
			case 7: resolution=Resolution.UHD8K;
			break;
		}
	}
	/**
	*<b>name:</b> addplayer<br>
	*  adds a player to the player array <br> 
	* <b> pre: </b> player data must be different from null<br> 
	* <b> post: </b> the player shall be registered and saved in a position of the array.<br> 
	* @param nickname is a String data type that serves as the unique identifier (id) of the player.
	* @param score is an int data used to store the player's score.
	* @param level is an int data that serves to determine the level where the player is at the moment. 
	* @param numberOfLifes is an int data that stores the number of lives a player currently has.
	* @param  name is a String that stores the player's real name 
	* @return a message of the positive method result if it was recorded negative if it could not be recorded
	*/
	public String addPlayer(String nickname, int score, int level, int numberOfLifes, String name){
		boolean comprobant= false;
		String message="";
		if(!searchPlayer(nickname)){
		
			for (int i = 0; i<PLAYERS && !comprobant; i++) {
				if (players[i]==null){
						players[i]= new Player( nickname, score, level, numberOfLifes,  name);
						comprobant=true;
						message="el jugador fue registrado correctamente";
					}else{
						message="el jugador no pudo ser registrado por que no hay mas espacios disponibles";
					}
			}
		}else{
			message="el jugador ya existe ";
		}
		return message;
	}
	
	/**
	*<b>name:</b>searchPlayer<br> 
	* search if the player is repeated or not <br> 
	* <b> pre:</b> the player must exist<br> 
	* <b> post:</b> it will be determined whether the player is a repeat player or not. <br> 
	* @param nickname is a String that stores the id of the player that you want to know if it is repeated or not.
	* @return existPlayer will send a determination as to whether or not the player already exists. 
	*/
	public boolean searchPlayer(String nickname){
	boolean existPlayer = false, comprobant=false; 
	for (int i=0; i<PLAYERS && !comprobant; i++){
		if(players[i]!=null){
			if(players[i].getnickname().equalsIgnoreCase(nickname)){
				existPlayer=true;
				comprobant=true;
			}
		}else{
			comprobant=true;
		}
	}
	return existPlayer;
	}
	
	/**
	*<b>name:</b>addlevel<br> 
	* adds a level to the level array<br> 
	* <b> pre:</b> level data must be different from null<br> 
	* <b> post:</b>the level must be registered<br> 
	* @param id is an int data that serves as the unique identifier for the level
	* @param requiredPoints is an int data that serves to store the score required to be in that level.
	* @param amountOfTreasures is a data that int that serves to determine the amount of treasures that are currently in the level. 
	* @param cuantityOfEnemys is an int data that serves to determine the number of enemies in the level.
	*/
	public void addlevel(int id, int requiredPoints, int amountOfTreasures, int cuantityOfEnemys){
		boolean comprobant=false;
		if(!searchLevel(id)){
				if (levels[id-1]==null){
					levels[id-1]= new Level( id,  requiredPoints, amountOfTreasures,  cuantityOfEnemys,1);
				}	
		}
	}
	/**
	<b>name:</b>searchLevel<br> 
	* search if the level is repeated or not<br> 
	* <b> pre:</b> the level must exist<br>
	* <b> post:</b> it will be determined whether the level is repeated or not <br>
	* @param id is an int that stores the unique identifier of the level you want to know if it is repeated or not.
	* @return existlevel will send the determination of whether or not the level already exists. 
	*/
	public  boolean searchLevel(int id){
	boolean existLevel = false; 
	for (int i=0; i<LEVEL-1; i++){
		if(levels[i]!=null){
			if(levels[i].getid()==id && !existLevel){
			existLevel=true;
			}
		}
		}
		return existLevel;
	}

	/**
	*<b>name:</b>registerEnemy<br> 
	* adds a level to the Enemy  in enemy array<br> 
	* <b> pre:</b> Enemy  data must be different from null<br> 
	* <b> post:</b>will be sent to the method addEnemy in level<br> 
	* @param enemylevel is an int data that serves to store the data of the level at which the enemy is to be registered.
	* @param enemyName is an String data that serves as the unique identifier for the enemy in a specific level
	* @param scoreRemoved is an int data that serves to store how many points the enemy takes away from a player if defeated .
	* @param enemyScoreGive is a data that serves to store how many points enemy gives to player in case of defeat. 
	* @param typeenemy is an int data that serves to store the type of the enemy give by the user.
	* @return a message of the positive method result if it was recorded negative if it could not be recorded
	*/
	public String registerEnemy( int enemylevel , String  enemyName, int scoreRemoved, int enemyScoreGive,int typeenemy){
		String message="", asciiImage="";

		int Xposition=0, Yposition=0;
		Xposition=generateRandomX();
		Yposition=generateRandomY();
		asciiImage=generateasciiimage(typeenemy);
		message= levels[enemylevel].addEnemy(enemyName,  scoreRemoved,  enemyScoreGive,  Xposition,  Yposition,asciiImage, typeenemy);
		return message;
	}
	/**
	*<b>nmae:</b>generateimageInAscii <br>
	* will generate an image for the treasures 
	* <b> pre:</b> the treasure must have a name other than null<br> 
	* <b> post:</b>will be save the image in a message<br>
    *@param nameOfTreasure is a String data type that stores the name of the treasure given by the user.
	*@return the treasureImage variable that stores the corresponding image data
	*/
	public String generateimageInAscii(String nameOfTreasure){
		int numberOfCases=0;
		String treasureImage= "#";
		nameOfTreasure= nameOfTreasure.toLowerCase();
		switch(nameOfTreasure){
			case "monedas":
			numberOfCases=(int)(Math.random()*3+1);
				switch(numberOfCases){
					case 1:
					treasureImage= ("\n"            
								 + ".:^~!777??777?777!~^:. \n"                                            
								+".^!7?7777!!77!!77!!7777??7~: \n"                                         
							  +"^7??777!77!!!!!!!!!!!!77!7777?7~^^~~~~!!!!!!!!!~~~^^^::. \n"               
							+"^??77!77!!~~~~~~~~~~~~~~~~!!77!77?J?!!!~~~!!!!!!~!!!!!!777!!~^.\n"          
						  +":7?7!!7!~~~~~~~~~~~77~~~~~~~~~~~!77!7??!!7?7!?Y!!???!~~~~~~!!!7777~.\n"       
						+" ^J7!77!~~~~~~~~~~!77YY?77!~~~~~~!~~!77!7?YJYJ?JY777?7~~~~~~~~~~~!77??.\n"      
						+"~J7777!~~!~~~~~~7JY?7JY7?YYJ!~~~~~!!~~7777??77!JY7?JYY?~~~~~~~~!~!7!7J~\n"      
					   +":J7!77~~!!~~~~~~7Y5?~~JJ~~?55?~~~~~~~!~~77!7JY7!?Y!!7?7!~~~~~~~~~!7!7J5^\n"     
					   +"??777!~!!~~~~~~~75YY?!JJ~~~77~~~~~~~~~!~~7777J!~7?~~~~~~~~~~!!!77?JY555^\n"     
					  +":J7!77~~!~~~~~~~~~?Y55YYY?7!~~~~~~~~~~~!~~!7!7J?!!!!!!!777???JJY55555555~\n"    
					  +"^J7!7!~!!~~~~~~~~~~!7JYYY55YY?~~~~~~~~~~!~!777?Y555555555555555YYYY55555^\n"    
					  +"^J7!7!~~~~~~~~~~~~~~~~~JJ?JYYYJ~~~~~~~~~!~!7!!?YYYYYYYYYYYYY555555555555^\n"    
					  +":J7!77~~!~~~~~~~~7JJJ!~YJ~~!YYY~~~~~~~~!~~!777?5555555555555555555555555^\n"    
					   +"??!!7!~!~~~~~~~~J55Y!~JJ~~!Y5?~~~~~~~~!~~77!7Y5555555555555555555555Y55~\n"    
					   +":J7777~~!!~~~~~~!?JJ?7YY?JJJ7~~~~~~~~!~~77!7J55555555555555555YYYYY5555^\n"    
						+"~J7!77!~~!~~~~~~~~!!7YJ7!~~~~~~~~~!!~~77!7?YYYYYYYYYYYYYYYYYYY55555555^\n"    
						 +"^J?7!7!~~~~~~~~~~~~~YJ~~~~~~~~~!!~~!7!77?Y55Y5555555555555YYYYY555555~\n"    
						  +":7?7!77!~~~~~~~~~~~77~~~~~~~~~~~!777!?Y5YYYYYYYYYYYYYYY555555555555P^\n"   
							+"^7?7!77!!~~~~~~~~~~~~~~~~~!!!77!7?JY555555555555555555555555555P57.\n"   
							  +"^7??7!!77!!!!!!!!!!!!!!77!!7?JYY5555555555555Y5555555555555YJ!:\n"    
								+".^!7??77777!!77!!77!77???!7JYY555555555555555555555YYJ7!^.\n"     
								   +".:^~!77777??77777!~^:.   ..::^~~!!!7!!7!!!!~~^::.\n"     
										 +" ........ \n");
					break;
					
					case 2:
					
					treasureImage= ("\n"                                                                    
                                        +"..:^~!77?JJJYYY5PPPPPPPPPPPPPPP55YYJ?!~~^:.\n"                                    
                                    +":B#&&##&&#BBGPP55YYJJJ??????????JJJYYY5PG&#G&&##5.\n"                                   
                                    +"?@&##BBGBBBGGPGGGGGGGGBGBGGGBGBBGGGGGGGB#BBP#P5J@&\n"                                  
                                +".^!J#@@#P5P5PPGJ#J5#YYYJJY#JJJYYBGJ#PYYY5#YJPPYGJG5YG@:\n"                                  
                              +"~B&&&##@@#BJGJG5P7BJ?B77777?B77777P57GJ7777B77YG!G5PBB&@\n"                                  
                             +"7@&&&&&&@@@@&&#&#&G##G&GGPPGG&GGGGG&GG&GGGGB&BB&&#@&@@#Y^\n"                                  
                             +"P@&#B##B&G&BB&5PPP##GBBBBB&&#B####&BBB&&B&&B&GB#Y#PBP@B\n"                                     
                             +".#@&&##B&G&PG#???7JG7777775P!7777?G!77JG!YP?G?Y#Y&B@@#^\n"                                     
                               +"^J#@@@@@@@&@&&&##&#######&BBBBB#&BBB&&#&&&&&@&&&&@@&.\n"                                     
                                 +"Y@###G#GG#J?JGJY55#GPPPPGG&GGGGGPPP5#GYPB?PP7GJPP@B\n"                                     
                                +"5@&##GBB5BJ!!GJ777P5777777B?777777775P7?G7JY~YP5P@#\n"                                     
                               +"!@@&@@@@&&BB&P5G#55Y5#5YYYYY#PYY555YYYGBYGG5G5JPPG&&:\n"                                     
                              +":@@&&#&&&&&&&@@@@@&&@&#&&&&&&#&&&&##&&#&&&#&#&&#G#@&. .....\n"                                
                              +"^@@##GBBGBGB?BJYB???YB7???7GY777???7?B???B755JGP&@@@########BPJ~.\n"                          
                              +"~@@@&BG&G#GB7GY7B??77BJ7??7P57?????JJ#YJ5#YG&&@@@#57~^~~~!!!7J5B##P~\n"                       
                            +".#@&&&@@@@@@&@&&&#&&###&&###&&&&&&&&&&&&&&&@@@GB&P!^^!777!!~~!7777!?5#&5.\n"                    
                            +"P@&G#G#P&BG&#G&5Y5#555#555YY#5YYYJJ?PG???#&@GY##!::!Y7^::.~B7..::^!??!7B@P\n"                   
                            +":#@&&B&P#B5#B5BY77PY77557777557777775G?5B@@Y7#B^:^J?:::::!P@@G57^::::!?!7&&~\n"                 
                            +".#@@@@@&&@&&@&&&BBB&BGG&GGGGG&GGGGBB&&&&@@5Y##^^^Y7::::7&&P?7JP#&P:::::7Y~G@?\n"                
                            +"?@&##B#BB&###&BGG##GGG&BBBBB&BGBGG#&#BB@@PJP@7^^??:::::&@:.::..:JG^:::::^5~5@7\n"               
                            +"5&@@&##GB&GP#Y77YG7775P777!5P777?5G#PG@&55B&^^^P::::::G@Y^:::::..:::::::^5^B@:\n"              
                               +":!J@@@@@@@@@&&&@&###&###BB&####&&@&@@G??#B::!Y:::::::Y#####BGY!::::::::7J~@G\n"              
                                  +"P@&#B#B##GBBJ5Y#P55P#BPPPPP&#BBB&@B55#&:^~5:::::::.::^^~7JG@#~::::::~5:#@.\n"             
                                 +"^G@@@&####GG#J?75P777YG77777#PPPPB@#77Y@!^^P^::::::^7^:::::.~&&^:::::~5:B@.\n"             
                                +"7@@&#&&@@&&&&&&&&&@&&&#@&&&&#@&&&&&@@GGG&B^:!5:::::.B@!.:::::.5@J::::.J?^&@.\n"             
                                +"G@&GBBP&P5BBPPP#5JJJBPYY5YBGYPBB&BG#@&PPG@5:^?J:::::?@&7^:::^Y@&^::::!5:7@G\n"              
                                +"^@@#B&PBP5J5YP5G5777P57777P5775P#BPP&@&BB#@Y::!J~::::^P#&##&&#Y:::::7Y^~&@.\n"              
                                +".@@@@@&&&&#&BBB&@BBGB#GGGP#BGGB#&&#&@@@@BPP#P~:^J?~:::.:^~7@#:::::~?7:!&@^\n"               
                                +"?@&B#BG&BG&B5G##5PG##GBBB&BBBB#&#&&##&&&&###@&5~:^7?7!^^:::^^:^~7?7^^P@B.\n"                
                                +":&@&##PBB5P#PPP#77!P57777B?777Y#5P&PPG#P#&@@@@@&G7^^~!777777!7!~~~?G&#~\n"                  
                                 +".7G#&&&@&&@&##&#P5G#5555&P5PPB&#&@&&&@&@&&&&&@@@@&#PJ7!!!!7?J5G#&#Y:\n"                    
                                     +"...:^~!?JYY5PGGGGGGGGGGGP5YYJ?7!~^^:... .^?PB&@@@@@@@&&&#PJ~.\n"                       
                                                                                  +"...:::::...\n");
					
					break;
					
					case 3:
						treasureImage= ("\n"   				                                                                                                                                                                    
																				  +"......\n"                                                 
																  +".::::.:...... .::......\n"                                                
																+".~!~~^^::::::............\n"                                               
															  +":!7~~^^^^::::..............:.\n"                                              
														+"^7!~!?J?7!~~~^^:::^:.....:......:.::.\n"                                            
												   +":~~:!J???????7!~!!~^::^~:. .:^^....::::^:.\n"                                            
												   +":!?JJ?????????7!!!!~^^~~:. .~^:..::^::.\n"                                              
													  +".:~!7???????7!77!~~!!:..~!~::^~^:.\n"                                                 
														   +".^!?????777!!!!!^.~7!^^~~:\n"                                                   
															   +".!??7777!!77^~??~^~:\n"                                                      
															   +"~Y55Y555YY5P5Y55JY5?.\n"                                                     
															  +".PPYY5YJJJYYJJY??JJJ7\n"                                                      
															   +"!Y55Y??77!~^:::^^::.\n"                                                      
															   +":7JJJY55YJ?7~^::~~^^:.\n"                                                   
															 +":!??7!!!~^^~7???Y555YYY55J?!\n"                                                
														   +".!J?77!!!~::..... ....:^^^^:^^\n"                                                
														  +"~???77!!!~^:......      .........\n"                                              
														+":7J?77777!~^:.......        .........\n"                                            
													   +"~???77!7!!~^::....:..         .......:.\n"                                           
													 +".!??77!!!!!~^:::......            ........ \n"                                         
													+":???77!!~~!~^^:::......              .........\n"                                     
												   +"~???77!!~~~~^:::::......                .........\n"                                     
												 +".7J77777!~~~~^^::::........                ........\n"                                     
												+".7??7777!!~~^^^^:::::....~7^:!!...            ....:.\n"                                     
											   +".??777777!~~^^^^:::::::~7???!~77!!7^            ...::\n"                                     
											  +".???7777!!~~~^^^^:::::^JYY~!J~.7!:~7~              ...:::::...   .\n"                         
											  +"7???7777!!~~^^^^^:::::^JYY77J~.77. ..              ....::::...   .\n"                        
												 +"~7!~~^^^^^^^^^^^^^::::::^7JYYJ?7?7~:..              ....::::...   .\n"                         
										  +"..::............::^^:::::::::::7Y7~?77?7!^              ....:::...   .\n"                         
							 +"............:^::............:::^^:::::::7Y~.!Y!.??.^?7?.              ...:::...   ..\n"                        
						  +"...:::.....::::^~~~~^^::::::::::::^^:::::::YP5!?57:J?:~J?~.              ....::...  ..:::::::::.   .\n"           
						  +":^::::......::^~~~~~~~^^:^^^^^:::::^::::::^??7J55Y?YJ?7~:                ....::...  .::.:::::::.   .\n"           
						  +"^^^^^^^^^^^^^^!!~~~~~^^^:::::::::::^:::::::::::7Y7^??:.......            ....::......::..::::::.   .\n"           
						  +"^^^^^^^^^^^^^^!!~~~~~^^^^^^^^^::::^^::::::::::::::::::.........          ....::......::.:::::::.   .\n"           
						  +"^^^^^^^^^^^^^~!!~~~~~^^^^^^^^^::::^^^::::::::::::::::::::........        ....:::....:::.:::::::.   .\n"           
						  +":^^^^^^^^^^^^~!!!~~~~^^^^^^^^^::::^^^^^::::::::::::::::::::......       .....::::...:::::::::::.  ..\n"           
						  +".^^^^^^:^^^^^~!!!~~~~^^^^^^^^^^:::^~^^^^^:::::::::::::::::::................:::::::.::::::^^:::.  ..\n"           
						  +":^^^^^^^^^^^~!!!~~~~~~^^^^^^^^^:::^~~~~^^^::::::::::::::::::::::...........::::::::::::::^^^:::.  ..\n"           
						 +".:^^^^^^^^^^^~!!!~~^~~^^^^^^^:^:::::^:::::.:::::::::::::::::::::::::::.:..::::^^::::::::::^^^^::.  ..\n"           
						 +"^^^^^^^^^^^^~!!!!!~~~~^^^^^^^^^^:..................^^^::::::::::::::::::::::::^^:::::::::^^^^^::.  ..\n"           
						 +"^^^^^^^^^^^~~!!!!!~~~~^^^^^^^^^~^:................:^^^^:::::::::::::::::::::::::.................\n"               
						 +"^^^^^^^^^^^~~!77!~~~~~^^^^^^^^^~^^^^^^:::::::::::::^^^^^^::::::::::::::::..\n"                                     
						 +".:^^^^^^^^~~!!!~^~~~~~^^^^:^^~~~~^^^^::::::::::::::^^^^^:::.........\n"                                            
							 +".........    .^^~~~^^^^^~~!~~~~^^^:::::::::::::.\n"                                                            
											  +".........   :^^^^^^^^:::::::..\n"                                                             
															  +"..\n");                                                                                                                                    		
					break;
				}
				return treasureImage;
			
			case "diamantes":
			numberOfCases=(int)(Math.random()*3+1);
				switch(numberOfCases){
					case 1:
						treasureImage= ("\n"  
												  +"..:..::.::..::^:::::::::::.:::.::.\n"                          
										    +".::^~!?Y5YYY?7!!~~~~~~~~~~~~~~~!?5PPPY777!~^^:.\n"                   
									    +".~7?JJJ5YYJ??777??JYJ?7~~~~~!!!!7!!!!!7????777J?77??!:.\n"               
										 +":^!?55PP5J?7!!!~!77??????7!~~~~!?J??!!!!!!77!77!7JJ??77777~:.\n"            
								  +".:!YYY5P5!7J77!~~~~~77!~!!!~~~~~~~~~~~^^!7~^^^~~~~~!?JYY7!J5JJ7~!~.\n"         
							    +":~7Y557??Y!~!~7??~~~~~^!?5B##7~~~~~~~~~~~7#B777J~~~~~!??77!!75J?YJYP?^.\n"       
							 +".~7?BGY5Y75P775PJY5?~^::::5?!5&&G~~~~~~~~~~!5J~~!!&J:^~!!J5Y5GYJYYYYBYYP5?~^.\n"    
						   +"^?G5J?7^7~~~?7??PP5??5:7??75@B~~?P&G!!!~~~~~~!!^^..P@B7~!!^GJ7JBBGPYJJYJPGP5Y?J7^\n"  
						  +".7GBY!::....JBBGY7!:.!&~5###@@@Y!!!?PJ!!!!!!!7!^:::!#&@&5J~7&!:^!J5GB#PJYG5J?YYJY~\n"  
						    +":^~5G5JJ::##Y?7^:^J#@YYBB@@@@#?!!!~!!!!!!!7!^^~^~P&@@@&J~G@5^^::~??JP55Y?5Y7?~^\n"   
							  +".~55JJ~~??J~7J!?#&&G~P&@@@@@#BGPYJ?77!!!77??JJY@@@#Y!~J&&?:!~!?77JJ?7!7J7^^.\n"    
							    +".:7P^77~!?YYYJJ??J777??JJ5JJ55YYYYYJ7777JJYJ?YJ7^77~7JJ7??7J!J?!JGB5Y!.\n"       
								   +"::^^^~7P5YYJJ???7?~:~~~~!!7777???7!!!!~!7!~~~J@&JYYYYJ?7^!!~^~~^.\n"          
									   +".::!J?!~^^~^^!?~7?7??????7777???77!!J??7Y@&5YJJJY5BY~^^~:.\n"             
									    +"..:^7!?^..:~YGP!!!~~!!Y5P5J77JY5PYJYYYP&#5YY5PPPY7^::..\n"               
										  +".:~JB#P7!!7YY7!~5?7~~~!~J7:^777?Y5PB&5!~77?PB!^^^:.\n"                 
											 +":!YYJ7~~~~?J^J5YJ???777!!~~^^77777^~7!~!?7^:.\n"                    
											    +":~~~!^:~7?!?777!~7????!^~^~!~7!7JY!!~:.\n"                       
												  +".:.^!?J!~7JJJJ?7?JJ7~:!7~JJ!!7?7~:.\n"                         
													 +":7JJ7~^~?JJG#B?7!~!??P#PJ?!^.\n"                            
													   +".^^^^^!J??7!77?Y5JYJ!~^:\n"                               
														  +".:^^!~~!!777?J7~:..\n"                                 
															 +".^^!?J??7?7^.\n"                                    
															    +".:~^^:.\n"                                      
																   +".\n");                        
					break;
					case 2:
						treasureImage= ("\n"
					                                                                                         
                                               +"::..\n"                                                 
                                          +"..^J5J^^::!77!^.\n"                                          
                                       +".!!:^7~~^::::::^!!!?J!::\n"                                     
                                      +"...^5B5!!!!~^^^^::^^~~!~7::!!^..\n"                              
                                      +"^~:^~?5PYJPY!^^!~^:::. ..::^?5YP!\n"                             
                                     +":~~:!!?YYP5PJ?^ :?J?77~:^^:75J5P#B^\n"                            
                                    +"!!~^^7JY^.~77?5YJ?77??YY5Y5G7GG???7J:\n"                           
                                   +"JG57~7J5!....!5PY55Y5?YY??~:??YY?75GB7.\n"                          
                                  +"?BP?~!Y57.....^J~.^G5?~755Y?YJ7GP?PBB&BJ\n"                          
                                 +"!BGJ~!J5?.....:J~:..~!!!~YPGGGP?JJ!JJ?YP^\n"                          
                                +"^#GY!!Y5?......7~::::^!:..!PPP?!J55PGJ!!:\n"                           
                               +":PB5!~JP7......!~::::. .  :YG5?^^~775777^\n"                            
                              +".##P7^!5Y......~~::::. .. :?P57^::^^~???~\n"                             
                              +"G&GJ^!Y5:.....:^.::.. ...:!YJ7^::^:^^^~!\n"                              
                             +"!PGY~^?Y^.....:^..... .:::~??!^::^^^~^^^\n"                               
                            +"^7?!!~!?^.....:~.....  ..:~!7~:::^^^^^^^.\n"                               
                           +".!5?!5YY~.....^!:.... .::.^~~^:::^^^^^^^.\n"                                
                          +".:J?7P5Y!:::...~:....~^^^:~^^^:::^^^^^^^:\n"                                 
                          +":?Y!5GPPJ~^7J. ^:.:~!?7^^~^::.:::^:^^^^:\n"                                  
                          +":?Y~~~JP5?!JYJ??!:!J!::~7~::.:^::::::^^\n"                                   
                           +"~YY!^~7~~!?J5Y7.  .:^^!JYJ!~77~^~!7J7\n"                                    
                            +"7?7~^.:~!!JP!.       :PPGGBGP5J55J?.\n"                                    
                            +":JJ5YY?!^!J577!7J^.   JGBBP~^YGBB5.\n"                                     
                            +".~J5YPB#BGP5YYJ5YJ~Y57Y&&G?GBP5YY:\n"                                      
                              +".^!JPGGGGGBGYJ~~7~G#BBGY5GB#7.^\n"                                       
                                  +".:~?JJJ77JJJ7!Y55#&BB#BY!7J!^:.\n"                                   
                                       +".^~7775YY55B&#PGGY?JJ7!~:.\n"                                   
                                           +"..:~!?J?!??777!^:..\n"                                      
                                                 +"........\n");                           
					break;
					
					case 3:
						treasureImage= ("\n"
						
						+"::::::::::::::::::::::::::::::::::::::::::::::::::::....:.........::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::::::::::::::::..:::^~!~~~!777?7~^:::::.::::::::::::::::::::::::::::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::::::::::::..::^^~!!!7?777?YPBBBPJ7!~~~~^:..::::::::::::::::::::::::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::::::::::..:~7?J???JJJJPG#####BGPP5Y?7!???7~::::::::::::::::::::::::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::::::::.:^!75BBBGGGPGGGBB##B#B55PBBBBGY?!~~77~::::::::::::::::::::::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::::::.:^~!~PBGBGPY5PGGGGBBBBBB5Y5GGGGGBGP5J777~~::::::::::::::::::::::::::::::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::::.^~JJ!Y55PPP57^!JYPGGGBBGP555GGPPPGPPGB5JYYJ^.::::::::::::::::::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::::.!Y??75GPPPYY?7~^:^~?Y55PPPPPP55555PP??YPP??J~:::::::::::::::::::::::::::::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::.~J:^?GBBBGYJ?777!!~~^^^7JJ?!~????JY557!!7?YJ?~.^:^^^^^^^:::::::::::::::::::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::^P^^YP55YJ?!^^^^::::::.^?7~^^7J???JYY57!7777YY~:~^:~^^^^^^^^::::::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::.7J~?Y?J7~::.:::::::.:^7YYJ!~!YJ7??JJYPJ!!!7?5Y?.^?:~!~~~~^^^^^^:::::::::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::.!~JJJ777!::::::::::~?Y55P5J5PPJ7JJJY5GGP5?777YJ:.^:~77!!!~~~^^^^^^::::::::::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::7.!YYYPJ7:^^~~!77?JY55YY5JJ5P5YJJJJJ5PGGGPJ?YY?...:^J?777!!~~~^^^^^:::::::::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::!^!?#GJJPYPPPPPYJYY555555YYY55JJJ??J5PGPPP5?Y?^:^..^JJJ??7!!!~~~^^^^^^::::::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::~?7~JJ~?Y?J??77?YYY55Y55YJJYYJJJJJJYPGGP5PP5P?^:^:.~??????777!!~~~^^^^::::::::::::::::\n"
						+":::::::::::::::::::::::::::::::::.^Y!~~!^^!!!777YYJYY55YYYJJJJJJJJJYY5PGP5Y7~YP!^::.^??????????77!!~~^^^^^::::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::.!J77Y?~~7??7JP5YYY555YJ??JJJYJJYYYYP55P!:YJ?:.::.7???77777????77!~~^^^^^^::::::::::::\n"
						+":::::::::::::::::::::::::::::::::::.!5?5P5Y???JPGPP5555JJJJJYYYYYJ?!JJ5Y?^~JY7^:^^:~7777!!!77777777!~~^^^^^^::::::::::::\n"
						+"::::::::::::::::::::::::::::::::::::.~?7P#GP555GBBBG5JJJJY55YY5YJ~.!Y5PJ:!J?^^^:.:~?7777!!!!7777777!!~~^^^^^^^^:::::::::\n"
						+":::::::::::::::::::::::::::::::::::::.:^!?JPGJ5B#BPPPP5Y5PPP5YJ~^~!?7J??YJ7~:.^:^!!!!!!!!!!!!!7!777!!~~^^^^^^^^^::::::::\n"
						+":::::::::::::::::::::::::::::::::::::::.:^~!!!YYYPGGGPP5PBGPYYYJ555?JJ5G#Y!~^^!7??7!!!!!!!!!!!!!!!!!!~~^^^^^^^^^^^::::::\n"
						+":::::::::::::::::::::::::::::::::::::::::.:^~J5J77?5PGP?JJ5BBGGGP55Y7JYY55!!?J7!!!!!!!!!!!!!!!!!!!!!~~~^^^^^^^^^^^^^^:::\n"
						+"::::::::::::::::::::::::::::::::::::::::::::::~!?????YJ~:.?GP555?^~^^^^~!7????77!!!~~~~~~~!!!!!!!!!!~~~^^^^^^^^^^^^^^:::\n"
						+"::::::::::::::::::::::::::::::::::::::::::::::..::^~~~~!~!YY?JYPP7!7JYJJYJ?7!!!!!!!~~~~~~~!!~!!!!!!~~~^^^^^^^^^^^^^^^:::\n"
						+":::::::::::::::::::::::::::::::::::::::::::::::::::::::^~~!!777???????777777!!~~!!!!~~~~~~~!!!!!!~~~~^^^^^^^^^^^^^^^^^::\n"
						+"::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^~~~!!!!7777!!!!!!!!!!~~~~~~~~~!!!!!~~~~~^^^^^^^^^^^^^^^^^^::\n"
						+":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^~~~~~~!!!!!!!!!!!!!!!~~!!!!!~~~~~~~^^^^^^^^^^^^^^^^^^^^:\n");
					break;
					
				}
				return treasureImage;
			
			case "armas":
			numberOfCases=(int)(Math.random()*3+1);
				switch(numberOfCases){
					case 1:
					      treasureImage= ("\n"        
                                                                                                                          +"..n\n"                          
                                                                                                                       +".::n\n"                            
                                                                                                                    +".::.n\n"                              
                                                                                                                  +".::.n\n"                                
                                                                                                               +".:^:.n\n"                                  
                                                                                                             +".^^..n\n"                                    
                                                                                                          +".:~^.n\n"                                       
                                                                                                        +".~~:./n\n"                                         
                                                                                                      +":~^./n\n"                                            
                                                                                                    +"^!^./n\n"                                              
                                                                                                 +":!:.n\n"                                                 
                                                                                               +"^!^.n\n"                                                   
                                                                                            +".77.n\n"                                                      
                                                                                          +"^7Y?.n\n"                                                       
                                                                                       +".~YP?:n\n"                                                         
                                                                                     +".!5P7:n\n"                                                           
                                                                                   +"^?5J~.n\n"                                                             
                                                                                +".^J5!.n\n"                                                               
                                                                              +":7YJ~.n\n"                                                                  
                                                                           +".:7?!.n\n"                                                                     
                                                                        +".:!J7^n\n"                                                                        
                                                                      +".^7J!.n\n"                                                                          
                                                                   +".:!?7:n\n"                                                                             
                                                                 +".^7?!:\n"                                                                               
                                                               +":!7?~.n\n"                                                                                 
                                                            +".^7J7:n\n"                                                                                    
                                                          +":!J?~.n\n"                                                                                      
                                                       +":^!J7:.n\n"                                                                                        
                                                     +"^?YY~:n\n"                                                                                           
                                                  +".~JPJ^.n\n"                                                                                             
                                               +".^75P?:n\n"                                                                                                
                                             +".~JP5!.n\n"                                                                                                  
                                       +":~.:~?5P?:n\n"                                                                                                     
                                        +":?JJPJ:n\n"                                                                                                       
                                       +".5Y5?:.n\n"                                                                                                        
                                      +".5BG!n\n"                                                                                                           
                                     +".YGG^n\n"                                                                                                            
                                  +".~75GP:n\n"                                                                                                             
                                +".JBBGGJ.n\n"                                                                                                              
                              +".?GBBGG?n\n"                                                                                                                
                            +":?GBBBBG7n\n"                                                                                                                 
                          +".7GBGGBBG!n\n"                                                                                                                  
                        +".?PGPGGGBG^n\n"                                                                                                                   
                        +".~?PPGGBP.n\n"                                                                                                                    
                           +":YGBY.n\n"                                                                                                                     
								+"^!n\n");                                                                                                  
					break;
					
					case 2:
					 treasureImage= ("\n" 
																									+".::^.\n"                                        
																								 +":^~Y&@?\n"                                       
																								+"~G&#&@B:\n"                                         
																							  +"^P@@@@P:\n"                                           
																							+":P@@@&P:\n"                                             
																						  +":J&@&@5.\n"                                               
																						+":?&@@@G.\n"                                                 
																					  +".?&@&@G.\n"                                                   
																					+".7&@@@G.                                             ..\n"      
																	+"......        .?&@&@G.                                             .:~Y#!\n"  
																 +".^!7~^:::::.  .^J#@@@G:                                             ~5JY&@G:\n"    
															   +":!?!.      .:~7#&@@&@#:                                             ~#@@@@P:\n"      
															+".^7!:           ^@@&@@@5                                             ~B&&@@P.\n"        
														+"..:^^:.             ^5&@@&?.                                           .G&&@&Y.\n"          
													  +".....               .~?J!..::^^.                                       .P&&@@Y.\n"            
																		+".^J5^      .^^:                                    .P&&@@5.\n"              
																	  +".:?5^         .^!:                                 .5&&@@5:\n"                
																	+".:?J^           .:5.                               .Y&&@@P:\n"                  
																  +".:?Y^            .^Y^                .^~~^^:.      .?&&@@5.\n"                    
																+"..!J^             .7Y.              .~77^.  ..:::.~?G&&@@P:\n"                      
															   +".^~:              :?!             .^!~^.         ~#@@@@@&~\n"                        
															+"..:~:              .!5:            .~7^.            ^&&@@@&^\n"                         
														   +".^!:               .~?.        ...::..              :~?GPYP^^.\n"                        
														 +".:~:                 ^!                             .^YY^    .:^:\n"                       
													  +"..~~.                   ~.                           .:!7^        :^~.\n"                     
													 +".^7:                                                .:?J:           :J^\n"                     
												   +".^^.                                                .:!J:            .!J\n"                      
												 +":^~.                                                 :~?^             :J?\n"                       
											   +":~!.                                                 .~7^             .!P^\n"                        
											 +":~~.                                                 .~7:              .JJ.\n"                         
										   +":~~.                                                 .^7:              .^5~\n"                           
										 +":~^.                                                 .^!:                ^Y.\n"                            
									   +":!~.                                                 .~7:                  ?~ \n"                            
									 +":~~.                                                 .~7:                    :.\n"                             
								   +":~^.                                                 .!7:\n"                                                     
								 +":~^.                                                 .~!:\n"                                                       
							   +":~^.                                                 :~~.\n"                                                         
							 +"^!^.                                                 :~!.\n"                                                           
						   +"^!~.                                                 :~^.\n"                                                             
						 +":!^.                                                 :~^.\n"                                                               
						+".:                                                  :!~.\n"                                                                 
																		  +":!~.\n"                                                                   
																		+":~^.\n"                                                                     
																	  +":~^.\n"                                                                       
																	+":~^.\n"                                                                         
																  +":!^.\n"                                                                           
																+"^!~.\n"                                                                             
															  +"^!^\n"                                                                                
															+".~^\n");                                                                 
					break;
					
					case 3:
						 treasureImage= ("\n" 
																						+":?.\n"                                                               
																						+"!?.\n"                                                              
																						+":7.\n"                                                             
																						 +"^J:\n"                                                             
																						+".?J:\n"                                                             
																					   +"^JJ?.\n"                                                             
																					  +":YJ?^\n"                                                              
																					  +"!JJ7.\n"                                                              
																					  +"!J??:\n"                                                              
																					  +"!Y?J?.\n"                                                             
																					  +"!YJJJ?:\n"                                                            
																					  +".5YYYYY!\n"                                                          
																					   +":5YY5YY7                              .\n"                           
													+" ~YJ?~:.                            :YYYYYG.                            .!.\n"                          
													 +":~~!JJ7~                            7P555P:                          .:!!\n"                           
														  +"~?J^                          !GP5PP5.                   :~7?7777?J:\n"                           
														  +":?JY~                        !BPPPPG!                  ^?YYJJ555Y!.\n"                            
														   +"?JJJJ7!^^^^:::.            ^#GGGGBY.                 ~YJJJYJ~:.\n"                              
														   +"^YJJJJYYYJJY5P5!          .BBBBB#5.                  J5JJY!\n"                                   
															+":7PP555YYYY555G!         ^&####&7                  !5JJJY\n"                                    
															  +".^!?JPGPPPPPPG.        ^@####&#.          ..^~~!JP5JJJJ\n"                                    
																	 +"^PGGGPB7        ^@&&&&&@P      .?PBBBBBGGGP555YY\n"                                    
																	  +"JBGBBB#5!..   .!@#####B&J:  .5&&###BBGGPPPPPPP^\n"                                    
																	  +"~#BB##&&@&&PJPGGGB###BBB#BG5B&@@&&######BBG57.\n"                                     
																	  +".G&##&&&&#BGGB##&&&&&&&&&&&##BB#&&&&#J~^:.\n"                                         
																	   +":B&&@@BPGB##&&@@@@@@@@@@@@&&##GP#@#:\n"                                              
																		 +"~B&5YB#&&@@@@@@@@@@@@@&&@@&&#G5P~\n"                                               
																		   +"^YG##&@&&@@@@@@@@@@@@@&@@&&#G?.\n"                                               
												   +".^7JYYYJ?!^:.      .:~Y?~PB#&@PY&@@@@@@@@@@GYG&P&@###5~Y##BBBBGPY7^.           .^!!!!!~~^.\n"            
												+".~?YY55Y55PPPGGPYJ?YPG#&@@5?BBG&@?PB#&@@@@@@@@##&&GB@BGBG?B@&&&###BBBGGPJ~:...:~?YYYJJJJJJJJJ?!^..   ..:.\n"
							  +".~7???777!^:::^^!7YJJY55555PPPPPPGBB####&&&@PY#BP&@BPGB&@@@@@@@@@@B&&&@P5BGJB@&&&###BBGPPPPPP5YY555YJJYJ?J??JYY5YYYJJ?77^.\n" 
							+":J?~::^^~7JYYYYJJ?J?JYY555J!^~7YGGBBBBB###&&&@&PBBGG@@&&&&@@@@@@@@@@#@@@@YGB55@@@&&&BPGBBBGPP5555YYYYYJ!:.       ...::..\n"     
							+"..         :~7?JYJY5Y?7^.       .:!J5B##&&&&&&#GGBBG#@@@@G&@@@@@@@@@@@@@GPBBPB#57^..   .:~J5PPPPPP5?~.\n"                       
																  +"....:... ?BBBB#&@@@@@@@@@@@@@@@@&BBBBBB:                .::..\n"                          
																		   +"J@&#####&&@@@@@@@@@@@&#BGB##&@G!\n"                                              
																		 +"~&@@&&###BB###&&&&&####BBB#&&@&&&B!\n"                                            
																		 +"!#&&&&@@&&###BBBB######&&&@@&&&&&###!\n"                                           
																  +":!?JYPGB###&&&@@&&&@@&&&@&&@@&&B#&&&&###BBB5.\n"                                          
																+"~5PPPPGGGGBBB##&&P. .J@@&&&&&@#~    :7P#BGGGGB:\n"                                          
																+"5Y5Y55GBBBBBB#BJ.  :BGPPB###BP##!      :#PPGGG7\n"                                          
																+"YJJJYP5?7!^^:.     Y#!~7PBBBP!5B#       PGPPP5PJ~^:...\n"                                   
															   +".YJJY5?             JG~~?PBBB5~5B#       ^B555YJJYYYYJJ!:\n"                                 
															   +"7JJJYY:             !&BB#&&##BB#&&        ~PP55YYYYYYJJYJ~\n"                                
															+".:7JJJJY7               ^7B@&&&&GBG7~          :^^^~~!?J5YJJJ:\n"                               
														+".:~??JJJYYJ~                 .#@@&&&&5P.                    .7Y??~\n"                              
													   +":?YJJ??77~^.                  J&@@&&&&#G?                      ?J?! \n"                              
													   +"7J^.                         :&B@@@&&&&G#.                      7JJ7:.\n"                            
													   +"7:                           YB&@@@&&&&##7                       .:!7!~.\n"                          
													   +".                           :&P@@@@@&&&&B&\n"                                                        
																				   +"7BG&@@@@&B##B&.\n"                                                        
																				   +"55B&&@@@@&##B&:\n"                                                       
																				  +"&J#&#@@@@&&###!\n"                                                       
																				  +".@?B#B&@@@&&&##7\n"                                                       
																				  +"#JP#G#@@@@&&&&^\n"                                                       
																				   +"5YY&GG@@@@@&&&.\n"                                                       
																				   +"?B?&BP&@@@@&#&\n"                                                        
																				   +".@?P#5G@@@@&&J\n"                                                        
																					+"YGY#PY&@@@&&.\n"                                                        
																					+":&P##YP@@@&?\n"                                                         
																					 +"J&G#PJ#@@&.\n"                                                         
																					 +".#BB#YY@@!\n"                                                          
																					  +"~&B#B?BG.\n"                                                          
																					  +":@B#&5Y?\n"                                                           
																					  +"P@#B##JB^\n"                                                          
																					 +"^&&&B##B5P.\n"                                                         
																					 +"GG@@#BBB5G~\n"                                                         
																					+"~#B@@@#BBBPP\n"                                                         
																					+"G5#@@@&B##GB^\n"                                                        
																				   +":#J&@@@@###BGY\n"                                                        
																				   +"!GY&&@@@&BB#BB\n"                                                        
																				   +"Y5P&#@@@@#BBG#\n"                                                        
																				   +"G?G#G&@@@&##BB.\n"                                                       
																				   +"PJ5#G#@@@&##BB.\n"                                                       
																				   +"?5?B5P@@@&&#GG\n"                                                        
																				   +"~G?#G5&@@@&#BY\n"                                                        
																				   +".#?GBJG@@@&#&!\n"                                                        
																					+"5P5BY7&@@@&#.\n"                                                        
																					+"^BJBP!P@@@&?\n"                                                         
																					 +"PPPBJ7#@&#.\n"                                                         
																					 +":#GBG?5@@!\n"                                                          
																					  +"JBBBY!#G\n"                                                           
																					  +".B5P5!Y~\n"                                                           
																					  +"J@GBB5JJ.\n"                                                          
																					 +":&&#PBBYP~\n"                                                          
																					 +"JP&&PGBG5Y.\n"                                                         
																					+".BJ@@#GBBGB~\n"                                                         
																					+"JY5@@@GGBBG5\n"                                                         
																				   +".#!#@@@#GB#B#:\n"                                                        
																				   +":G!B&@@@#B#B#7\n"                                                        
																				   +"^5!#&@@@&BBGGJ\n"                                                        
																				   +"?J?#G&@@@##BBG\n"                                                        
																				   +"JY?B5G@@@&&#BP\n"                                                        
																				   +"~P7BYY@@@&##B!\n"                                                        
																				   +":#7G5?&@@@&BB~\n"                                                        
																				   +".#?PP7G@@@&#G.\n"                                                        
																					+"YPYG??@@@&#?\n"                                                         
																					+"^BYGJ~G@@&#:\n"                                                         
																					 +"5P55!!&@&Y\n"                                                          
																					 +"^BPGY~5@&:\n"                                                          
																					  +"JGGG?7&J\n"                                                           
																					  +":#PG5?G:\n"                                                           
																					  +"?&PGPYY!\n"                                                           
																					 +".&&GPGPYP:\n"                                                          
																					 +"?G&&PGBP57\n"                                                          
																					+".PY@@BGGG55.\n"                                                         
																					+"!5P@@&GGGPP!\n"                                                         
																					+"5?B@@@#BBGGP\n"                                                         
																					+"G!B&@@&BBGG#.\n"                                                        
																				   +".G!B&@@@#BBB#^\n"                                                        
																				   +":P7GB&@@&BGB#~\n"                                                        
																				   +":5JBPB@@@&BGB~\n"                                                        
																				   +".G?B5Y@@@&BPG:\n"                                                        
																					+"G75Y7#@@&#GG.\n"                                                        
																					+"P5YP75@@@&G5\n"                                                         
																					+"~GJGY?&@@&#~\n"                                                        
																					+".P5P57P@@&G.\n"                                                         
																					 +"!PPGYJ&@@!\n"                                                          
																					 +".GPGP?P@B.\n"                                                          
																					  +"!GPBPY&~\n"                                                           
																					  +"^#GBBPB:\n"                                                           
																					  +"P&GBBPP~\n"                                                           
																					 +"^&&#BBBPB:\n"                                                          
																					 +"5G@&B###B7\n"                                                          
																					+".B5@@#B##BP.\n"                                                         
																					+"?PP@@&BBBGG^\n"                                                         
																					+"PJB@@@#B#BG5\n"                                                         
																					+"#?B&@@@###BG\n"                                                         
																				   +".#?G#@@@&##BB.\n"                                                        
																				   +":BJGG&@@&##BB:\n"                                                        
																				   +".#YGGB@@@##B#:\n"                                                        
																					+"#5GG5&@@&#BG\n"                                                         
																					+"GPGPY#@@@#B5\n"                                                         
																					+"7BPGJ5@@@&&~\n"                                                         
																					+".#PGPY#@@&G.\n"                                                         
																					+"5BBGY5&@@!\n"                                                          
																					+"^#GG5YB@&.\n"                                                          
																					  +"P#BG5P@?\n"                                                           
																					  +"~&#BGGB:\n"                                                           
																					  +"Y&B#BG#:\n"                                                           
																					 +":&&BBBB#J\n"                                                           
																					 +"?#@#BBBB&^\n"                                                          
																					+".BP@@##BBBJ\n"                                                          
																					+"^BP@@#B#BGB.\n"                                                         
																					+"JGB@@&BBBB#7\n"                                                         
																					+"PPB&@@&B##B5\n"                                                         
																					+"BJB&@@@####B\n"                                                         
																					+"#YB#@@@&####\n"                                                     
																					+"B5BB&@@@&&#B.\n"                                                        
																					+"P5PBB@@@&&#5\n"                                                         
																					+"YBB#B&@@@&&?\n"                                                         
																					+"7&B#GB@@@&&:\n"                                                         
																					+":&B#BP&@@&P\n"                                                          
																					 +"P&##BB@@@!\n"                                                          
																					 +"~@#&#G&@#.\n"                                                          
																					 +".B###GG@7\n"                                                           
																					  +"7@##BGG.\n"                                                           
																					  +"Y@&&&#B.\n"                                                           
																					 +":@@#&&&&7\n"                                                           
																					 +"?@@&&&&&#.\n"                                                          
																					+".B&@@##&#&7\n"                                                          
																					+"^@#@@@&&&&G.\n"                                                         
																					+"7@#@@@&&&#@:\n"                                                         
																					+"J&&@@@&&&&@!\n"                                                         
																					+"B&&@@@@@@&@Y\n"                                                         
																				   +":@&@@@&@@@@@#\n"                                                         
																				   +":&BBB####BBGG\n"                                                         
																				   +".#BBBBB#BBBBP\n"                                                         
																					+".!5#&###BY!.\n"                                                         
																					   +"Y@&&@J\n"                                                            
																						+"~P5~.\n");									
																					
					
					break;
					
				}
				return treasureImage;
			
			case "armaduras":
			numberOfCases=(int)(Math.random()*3+1);
				switch(numberOfCases){
					case 1:
						 treasureImage= ("\n" 
													+".................:.....\n"                                                    
											  +".~7~^^^^^^^^^:::^~~^^^~!7~~~^:::...\n"                                            
										   +".::P&&&&B7^!?J77~::::^:^~^~~~^~^^~^^^!G#5Y~\n"                                          
									  +"..:^!!^^7P##&B57JPGGGP5J?!~!7!!~~~!77J??JYPPYP#Y...\n"                                      
									 +".:^^^^:~^^J#BPBGBG?^!5B#&##BBGP5PPP55P5P5P5YY5YP&BGG5::.\n"                                   
								   +".^::::::.^~:J##P#BB&BJJPGB#########BGPY??JJJJ?7??5@BB#B~^^:.\n"                                 
								   +":::..::.::!:~B#GBYB#################BBGP55YJ?7~~!7PGBG&~::...\n"                                
								  +":::....:::.!~^B#BBYBBB#&############BGGPP55YJ7!~^^!!77Y#J.....\n"                               
								 +".^::....:^::!?~BB#BBGYB#&&###########BGPP5YYJJ77~~^~!!7?PG:.....\n"                              
								 +":^:^.::::^::~J^BGY^7G75G&#######B##BBGP5YYYJ77!!~~^:~!~7JY^.....\n"                               
								 +"^^::::::::::^Y~BP7:~B~?P###B##BB#BBBBGP5YY5Y7~~!^~~^^7!?75^.....\n"                              
								 +"^::...:::::^J57#J^ ^G:75B##B#&#######G555YYJ7!~~~~^^^!!7!5B^..\n"                                 
								+".^:....:::^!P#~PGJ. ^7 JYB####&&#&&&&&&G55JJ77~~::^:::^~~77#Y...\n"                                
								+".^....::^^!G&#Y#PY .Y..GP#&&&&&&&&&&&&&B5Y?77~~^:::::::~^!~YB:...\n"                               
								+":^.::.^^!!5&&&GGGJ.?!.J55#&&#&&&&&&&&&&G55?7!~~^:::...:^~^~!G7...\n"                               
								+"^^:::::^~?&@&#5G#??J.!5:5#&&&&&&&&&&&&#BPG?~!!^^:::::::^!~~!YY...\n"                               
							   +".~^:.:::^^?&@&GG&#?J7?? :P##&&&&&&&&&&&##5Y5?!~^:::::.::^~?^!7P:.\n"                                
							   +".::...:::^J@&GB@&?5GY7  ~GB###&&&&&&&&&BPYP5J!!^^:.:....:~7~~!G!\n"                                 
							   +":::..:::^^5@GG&#5PP?Y:  ^YGB#&#&&#&&&#GGPY5J~J7^^:::....:^!7!!55..\n"                               
							  +".^:::::::^~B#BGB##G5GY:..~7YBB###BB####GGG#&G^!!!~::^:...:^!77775:.\n"                               
							  +".::::::::!!#&#PP#B##BY:. :775GPGGB####B#&&&#&^!~:.::::^~::^!?7?~^..\n"                               
							  +":::.::::^~J&&#PY#&###5:..~!..!!JB#&&&&&&&&&##^!!^:.::::^!?~~??7:...\n"                               
							  +"^::.:.::^~P@&#G5P&#&#J~??^. .!?PB&&&&&&&&&&##~!7^:..:..::!J???!:..\n"                                
							  +"^^:.:::^~~G@@#G5YG&&&##B5^:!!?P#&&&&&&&&#####~~~^.......:^~?J?^::.\n"                                
							 +".^^:::^^~~!B@@&#G55G&&&B57~^7JB###&&##&####BBG^^~^.......:~!!??^^..\n"                                
							 +".^^::^^:~!!#@@&#GGYYB&#PJ7^:.?B#B#&#&##&####BG~^~^.......^~!??J~^:.\n"                                
							 +":~^:::^:^~!B&@&BPPYJG#BPP7...7B########&####BB!^!~:::..:^^~7??J~^...\n"                               
							 +":^::.::::^^G&&&BGPYJ5#&##J...7B#&&&&&&&&&&&#GG!^~~:.:...:^^!7??:...\n"                                
							 +".^::..:::^^5&#&#BP5YY#&##G!..!#&&&&&&&&&&&&#BG!^~^.......:~~!?7....\n"                                
							 +".^::..:::^^P@&&&#BP5YG&##B7^^^G&&&&&&&&&&&&GPB7:^:......:^!~7?~....\n"                                
							  +"^::..:::~~5&&&&&#BPY5#&##7.::G#&&&&&&&&###P5#!:^:.....::^~~77^^:..\n"                                
							 +"^~^:.:::^~P&&&&&&BG5YPB#B!.:~G##&&#&#####B55#~:^::..:::^~~!YYY~:..\n"                                
							  +":~^:::::^~7&&@@@&&#BP5P#BY^~:JGGB#B##B##B5??#^^^:..:^^^7Y5PGPG5!~:\n"                                
							  +".~^:::::^^5&&&&&&#G55GG5JJJY77YYPBBB##B#PYYJP!77!777?7?7777JG#BJJ!.\n"                               
							  +"^^:::::^^B&#BBGBGGYJGP~. .~775BB#&#&&&##GP5P7!~^::...:^~7JY??7JP~.\n"                               
							   +":^:.:::^~#&&BBBYYJ?::^:.::~5B##&&&&&&&&##GGB:::::.....::^~!7?JJJP:\n"                               
							   +".!^::^^~!&&&&#BP55G7:.::~JGB##&&&&&&&&&&#BGP5::::....:^^^~77?YPGY:\n"                               
								+"~~^:^^^~&&&&#GYJ?P5^.^5G55PGB#&&&&#&&&&&&##B~::::.:..^::^77?JJ7Y7\n"                               
								+":~^^^^^~#&&&BG5JY7....~5B#&######&&&&&&##&##~:^::::^::^^^^~~~!!GG\n"                               
								 +"~^:^^^~#&&&##BYJ!. ..:JP#########&##&#####BY:::......:::^~~~!!P&.\n"                              
								 +":~::^:^#&&&###5?^.::.:YGBB#######&########BG:::....^G?:^~!~~!7Y@:\n"                              
								 +".~^:~::?#&#G#&57...:!?PBB##&&###PB&&######BB^::::::^GG~^~!!!77!!:\n"                              
								 +"^~^^~^::!B#G#B?: ..^JGGB#&&&##&#G#&&&&&&&###~..:::::P#7^~77~5G~.^\n"                             
								 +"~~^::::::!#&#&P?^..~YGB#&&&&&&&#B#&&&&&&&&&&?::^~^:.YB7::.. 5@BY7\n"                              
								+".~^^::::^^^5&&&#B!.....!!777??JYG#BB#BBGJ7!!Y~ ..~~^.!BJ     :?^75.\n"                             
								+":^^^^^~P?^^7&&&BPG57:~~BBBBBGPP5B#P7~^^.         ^#BGBGP~^:.:::^7??\n"                             
								+".~~~~^#@&~~^#&&&GBGYJJ?B#&&&&&&&BB5!^^:           !BB#G5~~:.::~~!!B\n"                             
								 +"~!~~~!YB7^:B&&&BBGY~^JBGB&&&####P5~^~.            G##BBJ!^::!!^7?B\n"                             
								 +".~^~^    ~?B&&&#GG5^^J########&&P5~~~             ?&@B#5~~^~^~^^GY\n"                             
								  +"^^~~:   ?&#####GGGJ7J&##&&BBB&@BG!~:             ~&@#BY!~:::^^^#:\n"                             
								  +".^~~~^:.B###&&&#BPYJ5&&&&&&&#&&#B7~.             7&&##P~^^:^^^^:\n"                              
								   +".~~. .:&&&&&@@#BJ~YG&&&&&&&&&###?^:             G##B#G!~^^^~^!.\n"                              
									 +"^.  .#&&&&&&&B5Y7G&&&&&&&&&#B&P7~             7B##BG!~^^^~!7.\n"                              
										 +":#&&&&&&&BGJ?B##&&&#&&&#5&G^.              7&BGY!!^^^!!!.\n"                              
										 +"~#&&&&&&BJY5PB&#B###&&#GY5?.                GBG5?!~~~~!!.\n"                              
										  +"Y&&&&#BPG5GP#####BG#&#YJJ~                 ?&BPYJ7~^~!!.\n"                              
										   +"G&B#B#BGYGP####&&BGPG5YY^                 ?&BB57~~~!!!:\n"                              
										   +"B#B#&#BBGB5####&&&&BPJJY~                 Y&BPY?7~~!!7^\n"                              
										   +"Y&&#&###J^7&&&&&&&&##GY?!                 ?BBG5??!~77?~\n"                              
											+"?&&&&##57?#&&&&&#&#BPPG!                  .!J5?7!~!77!\n"                              
											 +"^#&&####P##&&&&#&#B5!.                      .:~!!!77!\n"                              
											  +".G&####G&&&#&##BJ:                            .~7?J7\n"                              
												+"J#GPBG#B###B?.                                :J5~\n"                              
												 +":!PPGBB##J.                                   .!\n"                               
												 +".75B##5:\n"                                                                      
												   +".YBG!\n");                             
					
					
					break;
					case 2:
						 treasureImage= ("\n" 
																				+".:~!7^..\n"                                                        
																			 +":J#&@@@@@@&B?.\n"                                                     
																		   +"^#@@@@@@@@@&&@@@G:\n"                                                   
															 +":!PP:        ?&@@@@@@&@@&&@&&&&#!        ^G5~.\n"                                     
														 +"^75G##&BP:.:  .!BB5B#&&@@&@&&&&&&#BG&G!. .:.^GB&##G5!:\n"                                 
													+".^?P####BBBBB#@@@#PB##GGGPPGGGPBGGBGBPPPPG#&#P&@@@BB#BBBB##B57^.\n"                            
											  +".:!YPBB###GGPGGGGBGB&&&BPPGPBBGBGPYY7YYJPYPPGPGGGGB#&&&&GGBGGGGGPG###BBPJ~:\n"                       
										  +".~JPB####BGGGGGPPPPGBBPG&&#G55YGYYG&&#PPP5YY5G&BGG55GPG#&&&&PP#BGPPPPGGGGBB####BPJ^.\n"                  
										+".~B#BBBBBBGGGPPPPPPPGP5PG&&&BB###&#GPB&#G#B5G#PP#BBGB#&#####&&#PP5PGPPPPPPGGGGGGBBBB#G^.\n"                
									 +"?P#&&&&&BGBBGGGGGPPPPPPY7Y#&&&5PPPBB###&&#BG&BGG&##&&#&&&#BGGGG#&&##J75PPPPPGGGGGGBGGB#&&&&B57\n"             
									 +"7@@&&#&&&&#P5P5555YY55YYG&&&@&5Y5YYYYY5GB##BGG##PB#B#BGP5YYY5PP#@@&&&GYY55YY5555P5G#&&&&##@@&~\n"             
									  +"^&@@@@&BB#&&&&#&&&&&&&&&@@&&&#5YY5YYJ??Y5PGP5JJ!5PG55JJYJYPPGB@@&&&@@@&&&&&#&&#&&&#BG&@@@@#.\n"              
									   +"^J5#@&&&&&&&@@@@@@@@@&&&&&&&&BGYPYJJ?7??J5PJ?!JG5YYY5PPPGGPB#&&&&&&&&@@@@@@@@@@&&&&&&@#5J:\n"               
										   +"^?G&&@@@@@@@@@&&&&&&&&&&BPBGGP5YJ??7??55J5G5YYY5PGGBBBGBB&@@&@@&&&&&@@@@@@@@@&#GJ:\n"                   
												+".!##B##BBB##B#&&@@&#B&#BPP5Y??J?J5PGBGP555PGGGB#&&##&@@@&#BB##BB##B#&B^.\n"                        
												 +"^BGGGPGGPGBB##&@@@##B###BGGPPPPPPGPGBGPPGBGBBB#####&@@@&##BGPGGPPGGGB.\n"                         
												 +"GG5PP5PPGGGGB#&&&&&#&&###########5??P#&#######&&&&&&&@@##BGGGPPP5PP5G5\n"                         
												+"YBGGGGPGPGGBB&#&&&&&&&&&####BBBGG#B5PB####&&&&&&@@@@&&&@#B&BBGPPGPGBGG#?\n"                        
											   +"!#PPGGGP5PGG#&Y.Y@&&&&#&&#BBG55555PBG5P5PGBB###&&&&@&&&@@7.P&BGGP5GGGPPG#^\n"                       
											  +"^##BGGP5PPGB&B^   G@&&&&BBBBBP5Y5PPY55?Y5PGBBBBGPGBB#&&&@5   ~#&BGPP5PGBG#B:\n"                      
											 +"!BPPGP5YPGB&&7     .&&##&&GB####BGPPPPP5YPGB#&##B5YG#BG#&#      J&&BGP55PPPPB^\n"                     
											+"!BYYYY55PB&&P.       P@&&&&&#BB#&#GPPPPYPGGBBB#&&#GB#B##&@?       .G&&BP5PYYY5B^\n"                    
										   +":#&GP5P5PB&&!         ~@@&#&@&#BBB#BGY5JJY5PGGPGPGGB##BG#&&.         7&&G55P55G&P.\n"                   
										 +".#&&&&######5.          :&&B#&BBB#GG#GYGBGPGGBB5PGPP5PGBBGG&#           :P#&####&&&&G.\n"                 
										+":&@&&&&&&&&@^            Y@&GPBPPBB#BBGJ5PPPPPBP5G5YBBGPPP5G#&!            7@&&&&&&&&@#.\n"                
									   +".&@&&&&&&&&@5            .#&#GPGGBG##5GP555P5YYGBGGG5B#BBGGPG#&G             B@&&&&&&&&@#\n"                
									  +".#@&&&&&&&@@#            ^#@&###B#GGBBJG?JGG5?7Y5P?J5?GBGBB##G&@@G:           :@@&&&&&&&&@B\n"               
									 +"#@&##&&&&@@&.           J@@&&&&&&&&&&#G#P5GGPYJ5PG5G#GG&&#&&&&&&@@@!           ~&@&&&&&&#&@G\n"              
									+"G@&&####&&@B            .&@&&&&&&&&&&&&&&&@&&&&&&&&&&&&&&&&&&&&&&#&@#            .#@@&###&&&@J\n"             
									+"?@&&&&#&&&@5              :G@@@&&&&&&&&&&&##&&&###&#B###&&&&&&&&&&@@P.              G@&&#&&&&&@~\n"            
								   +"^&&&&&&&&@@7                J@&@@@@&&&&BG##GBP55JYP5P#B#GGB#&&&&@@&&@~                J@&&&&&&&&&.\n"           
								   +"B&##B##&&&~                .&@#G@&#BB#&&57PG#P55?J5PB#Y5JG&&&#B#&&B&@B                 7&&###B##&P\n"           
								  +"5&BBGGBB#B:                :5@@&#&@@BGB#BPG#PGBGBPG#&#BGG#GB####@&&&&&@J.                ~&#BBGGGB&?\n"          
								  +"#&#BBGB#G.                G@@&&&&&&&&&&&&&&&&&####&&&&&&&&&&&&&&&&#&&&&@@?                :BBGBBGB&#\n"          
								  +".5#&@@&Y                  J@@@@@&@&&&&&&&&&&&#B&&&&&&&&&&&&&@@&&@&&&&&@@5                  .G&&&#P~\n"           
									 +":^^^                    YB&@@@@@@&&&&&@@&&&&&@&&&&&&&&&&&&&&&&@@@@@@#                     ...\n"              
															   +"&@@&&@@@@&&&&B#&@@@@@@@@@@&##&@&&&&&@@&@@@5\n"                                      
															  +"7@&&#&&&#BGB#PPPB##B###&####GGB&&#BBB&&#&&@J\n"                                      
															 +".&&#BB&BGGGPPG5PP#B#JPBB#5BB&GG5#BGPPG##B&&@B\n"                                      
															 +"5@&#BB&BGGPPGBPGP#GBPBGGGGGG#PBJBGPPPGBBB&#&@?\n"                                     
															+"^@@&B##BGYPJ5#PBBP&#BBGP5PBB##P#PGBGGGG#B&##&@&.\n"                                    
															+"B@&#B@&GBB#BBPB@#G&&#GYYY5BGGBG&@GYPG#GGB@&#&&@5\n"                                    
														   +"7@@&&@@@&BB##B&@@#5##BY7!7YPGB#B&@@#G##B#@@@&&&@@^\n"                                   
														  +".@@@@@@@@@@&##&@@&&G5PYJ~!!?55PP#&&@@&#B#@@@@@&&@@#\n"                                   
														  +"J@@@@@@@@@@@@@@@@@@@BYPPJYYYPP5#&&@@&&&&&&&@@@@@@@@~\n"                                  
														  +"&@@@@@@@@@&&&&@@@@&&@&PGGYY5YP&&&@@&&&&@@@@@@@@@@@@B\n"                                  
														 +"7@@@@@@@@&&&&&&&&&&&&@@&B5JJ?G&&&&&&&&&@@@@@@&@@@@@@@^\n"                                 
														 +"#@@@@@@@&&&&&&&&&&&##&&&&#GG&@@&&&&&&@@@@&&@&&@@@@@@@P\n"                                 
														+"~@@@@@@@@&&&&&&&&&&&&##&&&&&&&&&&&&&&&@&&@&&&&&&@@@@@@@:\n"                                
														+"B@@@@@@@@@&&&&&&&&&&&##&&&&&&##&&&&&&&&&&&&&&&@&@@@@@@@P\n"                                
													   +"^@@@@@@&@@@@&&@@&&&&&&##&&##&&&&&&&&&&&&&&&&&&@@@@@@@@@@@.\n"                               
													   +"G@@@@@@&&&&&&&&&@@&&&&&&&&##&&&&&&&&&&&&&&&&&&@@&&@@@@@@@P\n"                               
													  +"^@@@@@@@@@@@&&&&&&@@&&&@&&&##&&&&&&&&&&&&&&&&&&&@&&@@@&&@@@~\n"                              
													  +"#@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#####&&&&@@@@&&@@&.\n"                             
													 +"7@@@@@@@&&&&&@@&&&&&&&&&&&&&&&&&&&&&@@@@@&&&&#&&&&@@@@@@@@@@@P\n"                             
													 +"&@@@&&@&&&&&&&&&&&&&@@@&&&&&&&&&&&&&&&@@@@@&&&&&@@@@@@@@@@@@@@~\n"                            
													+"J@@@@@@@@&&&###&&&&@&&&&&&&&######BBB##&&&&&&&&&&@@@@&&&&&&&&&@&.\n"                           
													+"&@@@@@@@&&&@&&&&@&&&&&&&&&&#BBBB#BBBBB######&&&&&&&&&&&&&&&&&&&@7\n"                           
												   +"~@@@&&&&&&&&@@@&@@&&&&#&##&###BBBB##B######&&&&&&&&###&&&&&&&&&@@J\n"                           
												   +"5@@@@&#####&&&&&&&&##BB#BBB###BBBPGBB#&&#B##&&########&&&&&&&&&@@5\n"                           
												  +".&@&&#B#&&&&&&&&&&###BBBB#BBB#BBBGGGB##&##BB####BB#########&&##&&@G\n"                           
												  +"^&##G5P#&#####BGBGBBGBBBBBBB#######B###########&######B#&##BGBBB&@#\n"                           
												  +":##GP5PBBBPYYYYJYGG55YPP5YPPGGBGGBBGPPGPPGBGPGGP5GGGGB##BGG#P?Y##&#\n"                           
												   +"~^!G~ .!^:PGJ75G##B#P?JJY55GGP5555?!7JYY55YJYPGBGBG5J5G5:::   :5Y:\n"                           
															+"^@&BPPY!75PBGG&B. .J?7!~^:^~!: .##BBGPY!?5PP5G#.\n"                                    
															+"7&G55JJ77J5GGG#7                J#GGP5?!7JJ55B&^\n"                                    
															+"J&B55JYJ??YYJ5#.                :B5JYJJ?JYY55B&!\n"                                    
															+"!&#BP5GPPPGGPBJ                  YBPGGPPPG5PB#&^\n"                                    
															 +"Y@&#######&&?                    Y&&#######&@7\n"                                     
															+"^@@&&&&&&&&@P                      &@@&&&&&&&@&.\n"                                    
														   +".&@&&&&&&&&@@P                      #@@@&&&&&&@@#\n"                                    
														   +"Y@&&&&&&&&&@@P                      #@&&&&&&&&&&@7\n"                                   
														   +"P@@&&&&&&&&@@P                      &@@&&&&&&&@@@J\n"                                   
														   +"B@@&&&&&&&@@@Y                      B@@@&&&&&&@@@5\n"                                   
														   +"B@&&&&&&&@@@@:                      ?@@@&&&&&&&&@5\n"                                   
														   +"B@&&&&&&&&@@P                       .@@&&&&&&&&&@5\n"                                   
														   +"B@&&&&&&&&@@^                        B@&&&&&&&&&@P\n"                                   
														   +"#@&&&&&&&@@B                         Y@&&&&&&&&@@P\n"                                   
														   +"&@@&&&&&&@@!                         :@@&&&&&&&@@P\n"                                   
														   +"&@&&&&&&@@@.                          G@&&&&&&&&@P\n"                                   
														   +"@@&&&&&&&@G                           :@&&&&&&&&@P\n"                                   
														   +"@&&&&&&&&@7                            B@&&&&&&&@P\n"                                   
														  +".@&&&&&&&@&.                            ^@&&&&&&&@P\n"                                   
														  +":&&#&&&&&&P                              G@&&&&&&@5\n"                                   
															+"........                               .J?!!~^::.\n");     
					break;
					
					case 3:
						treasureImage= ("\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&#BGG@@@@##&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#PYYJ777?J7&@@&!7PGB@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&BPY??JYY?7!!7G&@G!?Y5Y5&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&BBGP555PY55Y?!~J?YGBGGG&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&BBGBGPPP5YY?JY?JYBBBB&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&GPPPGPPBGPY5!^5PBGG&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&P?!75##@@@#PP#&#G#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Y7~75GG#&@@@@@#G5@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@P7J55JYJJ#@@@5GBB@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#P?~^!JPB&#B?!7??YJ5#@@&B&BP#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&5~.    .^::::!YGBY7?JP5#@@@&P7#&&GJ!~..^7P#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&P^      :^7?7^!!^^~!5G?YBPB@&#G5#P:        .:^!5&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#GP5YJ~::~!?J!~!7???7!G&P7!!7???Y#@&7..:^~!~~!!!~^^?#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&#BY7!!!!777?7~:::.:~??~7PB&&G?!~~~~~~~77!~~?#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#YYYJJJ??7!:....:~7?^~~!!???!~!77?JYYY#&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@&YYYY5P555Y7..:^!~~~~!!~~^YYY5555PP5P@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@PJYYY55PG?^^^:~~~~^~~!!~7JBBGGGGPP5#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&@@&G55P@@@@@&GGPPG5^^:^~~J?7~:~~!!7~^JBBPGGGGB@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Y?7!77??7~^~~#@@@&#G5G57^:^^~77JJ..:77!!~^7G&&&@@@@&B@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GJJ77J!~!!^^~J&@&@&PYJY5Y?77JYJJ!:J!?55P5?P&&@@@@@&!!&&&#BGY#@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&J?77?!!7!~!5PB&&@&&#P55PPJ?5PGP~757?#&&&BB&&&@&&&P7PPGGYJJJ@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@P?77!!!7!7YPBGB&@@@&&#G5PPY?J5J~YYJJPGPGB&&@&G#G5P55BBY55?B@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&?7~~~!!7??GGP&&@@@&&&#BJP5JJJ^~??J???5B&&&@&GPYJYJ5#5YP5Y@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@57!!^~!!?YPP#&&@@@@@&GBY5P55577!?J77BBB&&&&@&GGP55PP5555G@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@G~^:.:!J5GGGPB@@@&@&#5?~^!??P5?77Y55GYP&&&&&&#BG555555JY&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@5^^:^~?YPGPGY&@@@&&GJ~   :::~JY?YP57!~~?&&&&&G5YJJY55Y7J@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@G7~~~!?5GGGB&@@@@#P?~.  ::^^^~?YPP?~:^~^J&&&&B5P555JYY5J@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#?!~775PBGG&&&@@&BP5JYJ77!~~:!!7Y!^~:^^!!P&&&&#G5YJ?JPG5@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@?!~!JPGBP&&@@@&Y5Y~!YB#55P57~~!JJ~~~7JPB#&##&&BPYJJYPPG@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@57~!J5GP#&&&@&B5!~~:!??^7YPY~~~77!~~7~7YG####&&GPYJY5Y#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@G?!7Y5PB&&&@&BP?~^.^^7:^7JY?^^~~!!!~~^^^!5####&#PJ7!?5&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&:?!^7YG&&&&@#GY!~..^!^:7?J5?~^~~~~!!~^:^^?G#B##&GJ!!7P@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!.?!:~^J&&&&#GY!~:.:~~.^!?JJ~!?7?7?!^^^:^~~JBBBB##Y~~^G@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@P .^::^:~#&&&B5!~^.::^::^^!??^~77777~:^~^::.~YGBB#G~:^:G@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&. ~PJ?7!J#&&&5?:^. ..:~~::~J~^!7777!!::~^~:::!GGB&5?^~!P@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@7..GG!::^^?#&&#BP5?7!~~^~JY~J!:~77!!7!^:~::::~?GGB&B~ ^Y@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#  ^&G?~. YBB&&&&&#&&BG&&G#@@&@Y:~?7?7^~P#PPGB&BGGB#7!7^~&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@~ .J&PJ! :#&&&&&B##G55P#&#&&@@@@G~!!!~?BB#&BG&&GPGGBG@&7^&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B...B&G5PY?#&&&&&#GGPPPPG#&&&&@&&&&###BB##BBGGB&BPPGG#&&57P@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^..:&&&#BGB#&&&&&#GGGBGG###&&&&&&&&&@@&###BBG#B&&#PGG#BBYP@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@P ..!&&&####&&&&&##BPGBGB&&##&&&&&&&&&&&&##BBG#B&#BPPGGGG&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@. ..P&&######&&&#BBGPPGG#&#&&&&&&&&&&&&&##PPBPBB&#BPPGGGP&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@J ...#&&&####&&&BGBBGGPGGGBB#&&&&&&&&&&&@&G5PBGGB&BBPPGGGGG@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@#....~&&&######&#P55PGY!~J###B##&&&&&&&&@&GGPPPBP5PGG5PGGGGP@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@~ ...Y&####B###&&#P55Y!^^!5&#&##&&&&&&&&BGP555PB?7~J55PGGGGG#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@G  ...B&###BB##&&&&#B577!7??G&#&&&&#&&&&&&BP555BJ7?77Y55PGGGGG@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@: ...~&###BBB##&&&###5J55PGYJ########&&&&&&BG5~PPYG#GY55PGGGGP&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@J ... 5&###BBB##&####BY!7Y5Y!?B&#######&&&&&&#?:75PB#5YY5PPGGGGG@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@&. ....B&##BBBB#######G7::^!7^?B#########&&&&#GJ^~7JJ?JJYY5PGGGG5&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@! . . ~&##BBBBB#&###BBP7^~77?YYG##########&&&BY7~!?YPGYJJY5PPGGGPB@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@B .... 5&##BBBB####B#BP5~77J?5PBPB###BB####&&&G?YYYY5PBGJJY55PPGGP5@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@~ . ...###BBGGB####BB#PY7!YJ5GGBBBBBBBB#####&&#P55YY5G#GJJYY5PPPGP5B@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@G .... !&##BGGBB###BBB#PJ7~5PYB#B#BBBBBB####&#&&#P55JPG#PJJJY55PPPPP5@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@^ ...  P&#BBGGBB##BBBB#BYJ!5P5B##BBBBGGGBBB####&#BP5YPGB5?JJY55PPPPPYG@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@G  ... :###BGGGB###BBBBB#PJ755P#&BGBGGGGGBBB######B55J5GBY??JJY55PPP55Y&@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@^ ..   7&BBGGGGB##BBBBBBBGJ7JYB&#GGGGGGPGBBBB###B##P5YYGGJ???JY555P555YP@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@G       G#BBGPGGBBBBBBBBBBGY~7YGBBGGGGGPPGBBBBB#BBB#G5JJPGJ???JYY5555555?&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@^      ^#BBGPPGGBBBGGGGGGBG5~75##GPGGPPPPGBGGBBBBBB#GYYYPP????JJY5555555YY@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@P       J#BGGPPGGBBGGGGGGGBGP~~Y&#PPPPPPP5PGGGBBBBBGBG5Y77?J???JJY555555YYJP@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@:      .BBGGPPPGGBGGGGGGGGGGG~~5#BPPPPP555PGGGGBBBGGBGPJ777J????JYY555555YY?#@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@P       ~BGGPP5PGGGGGPPGGGPGGP:^!PG55P555555GPPGGGGGGBPJ!^^!?????JJY55555YYYJ?@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@:       5BGG555PGGGGPPPPPPPGB5!Y5GG5555YYYYY5PPPGGGGPBY!7??Y5???JYY5555555YYJ?Y@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@#G5YJ??P@@@@&&@@@@@@@@@@&&#&P:.~P#B##&######&@@@@@@@&BJ7~..!#@@@@@@@@@@@@@&&&&&@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#?  .!P&@@@@@@@@@@@@@@@@@@BBP:   ?@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@G~  .!?&@@@@@@@@@@@@@@@@@&#BG7   .?@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&5!..:!~B@@@@@@@@@@@@@@@@@&#PP5.    !&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B?7^:~YYP@@@@@@@@@@@@@@@@@@P5GG7    .:5&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@P?7!!JPPP@@@@@@@@@@@@@@@@@@BYB#P7:   .:~?P&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@P.    !YB@@@@@@@@@@@@@@@@@@@##@&BGY!~^: .~7#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@BGGGG#&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
									+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
					break;
				}
				return treasureImage;
			
			case "equipos":
			numberOfCases=(int)(Math.random()*3+1);
				switch(numberOfCases){
					case 1:
						treasureImage= ("\n"
                                                 +"....:::^^^^^^::...\n"                                                     
                                             +"..:^^^^^^^^^::........ .\n"                                                   
                                            +".:^^^^^:^::.......\n"                                                          
                                            +"^^:...:.... ....        ..........\n"                                          
                                            +".~~::^!~~~:.^^^~~:...:^^^~~~^::.\n"                                            
                                             +".^~::~~!!~^~^^^^::::^^~^^^...\n"                                              
                                               +".~^.~~~!^~~~^^::::^^:.^...\n"                                               
                                                +".~:^!~~~~~~~^::..:..:: :\n"                                                
                                                 +".7~!7!!~!~!~^: .^..^.^:\n"                                                
                                                 +".!7^JYY??7!!~^.:^:^^:^.\n"                                                
                                                 +".~!^77??77!~^^:^^^^:.:\n"                                                 
                                                +".:^~:!!!7777!!~~~~^~:.: .\n"                                               
                                              +".^^^!!^!?!!!77!~^^^^^^..: . .\n"                                             
                                              +"~::^!?~!7!!~!!~::..:::...   .:.\n"                                           
                                            +":^J?:..:^!!77!!!~^:.....     .~!~.\n"                                          
                                            +":^JGP?~::..::::::....    ..^7??!:\n"                                           
                                             +".:!?PGGPYJJ?7!!!~~^:.....:~~~7:\n"                                            
                                              +".P57!?Y5P5?!~~~^^:. ..:^~7JY5^.\n"                                           
                                            +".!~Y#BBPY?777777????~^^~~7JY?7~^.\n"                                           
                                             +"^~?GGGBBBBGGGPY?!~!7J77??JJ??J:\n"                                            
                                          +"..:~!~!?Y55Y?777!!!7???7!~^~7777?7~:\n"                                          
                                         +":~J5Y5PJ!^~7!!^~7??7!^:.:.  .::^^~!??:^\n"                                        
                                       +":JPJJ!^~~?JYPGP5JJ?7!!!~~^^^::^!~~~!!77??^.\n"                                      
                                    +".^~?5PG5:77~Y777!7?JY5PGGGGGPP5YJJYYYJJJJ?7!^:...\n"                                   
                                  +".~????!!Y7~?~7Y??JY555PPGGGGGGGBBGGPPPP55YJ7^^^^:.::.\n"                                 
                                +".^?JJJ7~7JPY^?~?BPPPPPPGGGGGGPPPP5555J!:^7YPPGPJ7~~^::::.\n"                               
                               +".?JJJ?^~JY5BY!Y^J#GGGGGGGGGGGGGGGGGG55557. .:7Y5GG5?!~..:::\n"                              
                              +"^PPYJ7^~7J5PBJ~Y~!#PGGGGGGGGGGGGGGGGGY~!5GP!.  ^?PGPGPJ!^^^^:.\n"                            
                             +"^GBGY7~^?YPGGBJ~5~7G5PPPGGGGGGGG57~^^~Y?..~YB5~  .!PBGGG5?!~^^^.\n"                           
                            +"^GBBPJ7~7?PBBB#?~P?^BGGGGGGGGGGGY!~::..!B5: .7GB7   ^GGG##B?!~^::.\n"                          
                           +".YBBBJ?^7J5BBBBG#J^7!BBGGGGGGGGGGBG5Y555GGG5: .JPG7. .!B#&&&#??7!~:\n"                          
                           +"!GB#P?~~JJPPYJJYG!!BJ~BGGGGGPGGGGGGGGGGPPGGG5^.!B##!.. ?&&&&&BJ?7~~.\n"                         
                          +".YG#B5?^?5Y!:^~~~~~~J7!#GPPPPPPPPPPGGGBP5PB###^.7&&&P~...#&&&&&YJ7!^^\n"                         
                          +"~5GBB57^YJ:.:?5G#BJ:^~!YB&&&&&#&##&&&&&&&&&&&&Y~5&&&B7.  ?###&&P?77~~.\n"                        
                          +"!PBGG5!^5Y.:5BG&@#^^7?~:^!JGB5!7&&&&&&&&&&&&&&&#####B!: .~#&&&&P7?7~~.\n"                        
                          +"!PBGGP!:J555#GG@&.:~7&#5!:::^. ~&&&&&&&&&&&&&&&&&##&B!. .~B&&&&P??!~~:\n"                        
                          +"!PGPGG?:?PGPGB#@G ~~#@@&G?^^:7G&&&&&&&&&&&&&&&&&&##&B7:.:~G&&&&5?7!~~.\n"                        
                          +"^PBPPPY^!YGGG#&@&:.!!5GP57:^:B&###BBGG#&&&&&&&&&&&&&G7!:^!B&&&#J?~!~~\n"                         
                          +".YGGPPP7~?YBGB&@&G^^~^^^^:^7GGYJJYJYPB&&&&&&&&&&&&&&Y?~^^7#&#&5??~~~^\n"                         
                           +"!5GGP5J!7J5GG#&&@&BPYJYJ5G#PJJJYYPG#&&&&&&&&&&&&&&B?!^^~B&&&G?J!~:~.\n"                         
                            +"JJBGP57!Y5PGG&&&&#BB##BBGBGB#&&&&&&&&&&&&&&&&&&&&Y!^^~G&&#B???~^^:\n"                          
                            +".??BGPY!!5PPPG&&&#5YJYJJJJJY#&&&&&&&&&#########&P~^7?B&###J7!~^:^\n"                           
                             +".77PBPJ~~YPPGB&&&GP55YYJJJYB&&&&&&&&&&&&&&&&&&B~^?P&&#BG?7~~^:^\n"                            
                              +".!!YBGY~:JPPGG&&&&&##BJJG#&&&&&&&&&&&&&&&&&&&Y~Y#&&&BP7!~^:::\n"                             
                                +"^!?PBP!:!PG5PB&&&&&&#B&&&&&&&&&&&&&&&&&&&&#P#&&&&#Y!!~^::.\n"                              
                                 +":~!?PGY~:!YY555B&&&&&&&&&&&&&&&&&&&&&&&&&#&&&##BGJ?~:::\n"                                
                                +"...^!!7Y55J!!7J??7JPBB#&&&&&&&&&&&&#BBPPPB##BGGGGPJ~^~:..\n"                               
                             +"....:^^~!!!7YPGG5YYYYJ?77??JY555555555YY5PGBBBGGG5Y7!~~~~^^:..\n"                             
                              +"..:^^~!!!!777?J5PPPPPP55YYY555PPPPPPPPPPPPPGP55J??7!!!!~~~^:.\n"                             
                               +"..:^^~!!!!!7777?JJY55PPPGPGPGGGGGGPPPPP55YJ?777!!!!~~~~~~:.\n"                              
                                 +"..::^~~!!!!!!!7777777????????????7777777!!!!!!!~~~~~~^:..\n"                              
                                     +"..:^^^~~!!!!!777777!!777777777777!!!!~~~~^^^^^::..\n"                                 
                                            +"....:::::::::^^^^^:::::::::....   ...\n");                                      
					break;
					case 2:
						treasureImage= ("\n"
														+".^JPP5PY^\n"                                                        
                                                    +".^?BB?:  .JGP!:\n"                                                     
                                                +" ^?PGPJ^.      .^7PGP7:\n"                                                 
                                              +".!GBY^.              .~5BP!.\n"                                              
                                            +".?BG!.                    .7PG?.\n"                                            
                                           +"?BB!                          ~GG!\n"                                           
                                          +"5BP.                            .YBY\n"                                          
                                         +"7GB.                               5B7\n"                                         
                                         +"PGJ                                ?GG\n"                                         
                                        +".#G^                                ~G#\n"                                         
                                        +".#G.            ^~!!77!:            :P#.\n"                                        
                                        +"^BB           .PG~:.:^!GP           .PB.\n"                                        
                                        +"~BB           :#7      ?#.           GG:\n"                                        
                                        +"?GG           .#7      ?B.           BG^\n"                                        
                                        +"5BJ            55   .  55            BG!\n"                                        
                                        +"5G7            JG7G#BJ!GY            PGJ\n"                                        
                                       +" 5G~      :~!J55PGBGBBBBGG5Y7!!:      YG5\n"                                        
                                        +"PB^      ~P5Y5GGPG555PBPGP5YPP:      7G5\n"                                        
                                        +"5B:         ^5GGPGBBGGBPGP5^         !BP\n"                                        
                                       +" 5B:         7#BGGGBBBBBBBB&^         ^BP\n"                                        
                                       +".GG.         :#BBBBBBBBBBBB#          ^#5\n"                                        
                                       +":#P.       75G#BBBBBBBBBBBB#5J!       :BP.\n"                                       
                                       +":#5       :&BBGGGP555PPGGGGB#B&       .PG.\n"                                       
                                       +"^#Y       :#GGPPPY7!!7YPGGGGBB#.      .5#:\n"                                       
                                      +" ~#5     :!P#PPPPPY?77?YPGGGGBB#5!:     J#:\n"                                       
                                      +" !B5  ^YB#&BBPGGP5YYYYY5PGGGGBBB###GJ:  J#:\n"                                       
                                      +" !G? 5#####GBGPP55PPPPPPPPGGGBBB#&##BB? J#^\n"                                       
                                      +" ~BPPB###GYBBPPGGGGGGGGGGGGGBBBB&G#&#BBYGB:\n"                                       
                                        +":?#G&#BJ^!~BGGGPGPPPPPPGGGGBG777P&##G#7.\n"                                        
                                         +"BB##PY   BG&G5#G5BB5B#G##BG   P#&BBG\n"                                          
                                         +"^B###5?  ?BGPP555Y555PPGGGGB?  YG&#BG.\n"                                         
                                         +"?####P..GBGGP5555YYY555PGGGG#P ^G&#BB~\n"                                         
                                         +"GB&#GP.?!^:::...........:::^^!7.G###BY\n"                                         
                                        +".####G7~!....             .....7~JG&BG#\n"                                         
                                        +"7B&##P7J:...               ...::?JP&#BB^\n"                                        
                                        +"PB##BY~!:..                 ....775###B5\n"                                        
                                       +".BB&#PP?~...                  ..:~?PG##BB\n"                                        
                                       +"^####P75J7!!^^::.        ..:^~!77Y7YP&#G&:\n"                                       
                                       +"JB&#BG:^~::::.::^^~7~~7~^::::..:.J.~G###BJ\n"                                       
                                       +"BB#BBY. J::..   .^!~::~!!^. ....:J .PB##PB\n"                                       
                                      +":B##BPY  :?:.:^~!:.      .^!7~:::!.  JB&BBB.\n"                                      
                                      +"?####5!   :?~?~....:^^^:::..:!?~J:   J5&#B#!\n"                                      
                                      +"Y#&BBP.   :G?:::^~:.:.....~:::^7B^   ^Y&###J\n"                                      
                                      +"PB##GP  .J5B5JJJYY77?????7??JJY5GB5  .5B##BP\n"                                      
                                      +"Y#&#GP.   ..^##BBBBBGBBGGBBBB&P:..   ^5&###P\n"                                      
                                      +"^####PY:    GBGP555555Y555PPGGB5    ^5B&#BB:\n"                                      
                                      +" ~B###BP?^:?#GGGGGGP5YY55BBGGBG&!.^JG#&##B^\n"                                       
                                        +":P###BGGGPYY5555PGBBBBBG55Y55GB#BB#&&#P.\n"                                        
                                          +":G#GG5YJ7777?JYPGBBBGPYJ?7?YPGBBB#G~\n"                                          
                                          +".&BGGGPPPPPPPPPPGGGGGGGPPPPPGGBBBBP\n"                                           
                                         +"~&###############################B&.\n"                                          
                                         +"J#BBBBBBBBBBBBB################BBB&:\n"                                          
                                         +"G#BGGGGGGGGGGGGGBBBBBBBBBBBBBBBBBB&~\n"                                          
                                        +"^#BGGGGGGGGGGGGGGGGGGGGGGGGGGGBBBBBBG.\n"                                         
                                        +".#GGGGPPPPPPPPPPPPPPPPPPPP55PPPPPPGGBB#\n"                                         
                                        +":&##BBBGGGGPPPPPPPPPPPPP55PPGGGGGBBB##&.\n"                                        
                                         +"^7?J5PPPPGGGBBBBBBBBBBGGBBGGGPPPP5Y?!.\n");  
					
					
					break;
					
					case 3:
						treasureImage= ("\n"
						
																												+"......::::..:......\n"                                                                                                                     
                                                                                                  +"..:^^~~!!???JJJJJJJJJYY5PPPGGGBBBBBBBGGPYJ7!!^:.\n"                                                                                                       
                                                                                           +".:^!7?????????7777777???JJYYY55555555PPPP555PGGGBBB###&&&#BPY?^:.\n"                                                                                              
                                                                                     +".^!77?JJJ?77!7777???JJJYY555555555555555Y5555555555555YYY555PPPGGBB##&&#G5J!:.\n"                                                                                       
                                                                               +".:^~!?JYJJJ??7??JJY5555555555YYYYYYY55YY5555PGGBBGGGGGBBPPGGGGPPP555555YYY555PGB##&&BPJ!:\n"                                                                                  
                                                                           +".:~?JY555YYYY55PPGPP5PP5YYYYYYYYJYY55PGGGB#####BB###########BB##BBBB#BBBBBGGGGPP5YYYYYY5PGB###GJ~:\n"                                                                             
                                                                      +".:~7J5GGPP555PPGGPPPP5YJYYYYYYY55PPGGGGBBBBBB##B#&&&###&#############BBB#############BGGG555YYYYYY5GB##B5?^.\n"                                                                        
                                                                   +".^7YPPGGPPPGGGGGGPP555555PPGGGGPGGBBBBBB###############&&##&&################&###BB##########&#BGPP5YJJJJYPPB##GJ^.\n"                                                                    
                                                               +".^!JPGGGGGGGGBBBBGGGGGGGGGGBB#####BBBB##BB##############&#&&###&#####&&&##&&&&#B#&###BBB&&#############BGGP555YJYY5GB#BY~.\n"                                                                 
                                                            +":~!JGBBBBGBBB####BGGGGBBBBBBBB&&&############&&&##########&&&&&&#&&&&&&#&&&&&&##&&##&#&&&&&&##&&##############B#BP555YY55PG##G7.\n"                                                              
                                                         +".^7YPGPBBGB##&##BGBBGGBBBBBB##############&&##&&&&#&&&&&&&&&&&&#&&####&###&&&&&#####&&&&&&&&&&&&&&&&&&####B#######BB###BG55Y5555B&#G!.\n"                                                           
                                                       +":!J5PGBGPG##&#BGGGGGBBBBB############&&&######&&&###########&########B####B#&#GB##&#####&&&&##&&&&#&&&&&&&&&&#########BB####BGPP5555G###P!.\n"                                                        
                                                     +":!JPPPGBB#&&#GPPGPPGGBBBB###B###B###&#&#BGG###B##BB#&#B####&&&#######&######&&&&&&#&&#&###&####&#####&&&&##&&&&&&&&###B####BBB#BBBBGP555GBBBP?:\n"                                                      
                                                   +"~JJYPPG#&&&#PP55PGGGBBB##BBB#&&##BBBB##BBBBBB#&#&&&BB#BBBGPPPPP5PP5YPG5YPPPGPPPGGPGGGBGGBGB###&&&&&#&##BB####B#&##&&BPG&&&#B#B###BBBBBBBGPPPGBGGPJ:\n"                                                    
                                                +".~YYY5B##&&#PP5PPGBB#BBBBB#####BBBBBGBBB#######BBBGPPPP5Y5YYYYPGGBGBPG#GB#P?YYY5YY55Y5PG&GPBBB5BGGGGGB##BB&&&##########BB#####&##B##BBBBGBBBGBGGGGGGGGJ^\n"                                                  
                                               +":?5Y5BB#&&BPPP5GGBBGGGBBBB##BBBBBGGB###&#BBPPP5Y5PPY?JJJJJJJ?J?55PGJY#5J55YYJ?YJYJJJYJJGP&P5##G#5#B55P5PPPGBBB##&&&#BBB#####BBBBB##&##BBBGGGGGBGGGGGGPGGY?^\n"                                                
                                             +".?5YYPB###GPGGBB#BB#B#&&&BBGBPPGGGBB&#BGPP55G55GGPGG5&J??J?J5JY55JY5Y55&PB5PPYB?&Y#PPPJGYGYPJG#5YY55YJJJJYYY555PPPPGG#&#&#BB#B##BBBGGB##BBBGGGPPPGGGPGBGBBGP5?:\n"                                              
                                            +"^PYJPB#&GBGGB###&B55#&&BGP5PPPGG#&#BPY5Y5YJYB5BPG55P?5JPYPP5PJB?G7J7757J?Y5?YY7P~P!#?!J!Y7Y77J5P5YYJPYP5JJ??J?JJY5GBBYPGGGG##&&#BBBBB#BGGGB#BBGGPP5PPGGGGGGBGGG5~\n"                                             
                                          +".!PJ!P#######&&&&@&#GPB#G5P5PP####PY555JJJ?7??755YJYGBPJYJ????77YYY7J775!J!??7!!!7!7!?7!7!!?!Y~YPJJ:5~57J??B?GJPJ?Y5PB5G&G5PPGPGG#&&#BBBBBGGGPPGBBGGPPP55PPPGGBBGGJ?.\n"                                           
                                         +":55!~YGPPPGP#B#&@&&@&&#B#GP5P&&GYY55Y77????7!7JJYJY???7JY7J57?J!!^~^^^^~~~:~:^^:^!^~!~^^^^^^^~^^^^^~~^5~7~YG!?^?!7?JY?JG5YYGGG#Y5PGGGB#&#BPB#BP55555GBGP5Y5PPGGPPGBGJJ^\n"                                          
                                        +"^PJ:!5PYYYYY5GB&#B#&#&&&&GPGGBB5PYY5PGG777!JJ5GYJ!?~!?JJ?Y!~^^^^~!!~^^:::::.::..::...:....::::::^^~~^!~^^^::^~7?7J7!!7JY?5??J????YJY5PPPPG&#P???7JG5555PBBPPP5PP5PGGGGYY!.\n"                                        
                                       +"!B5.~55YYYJJPG#GGBP5GBG5B&&&&BPPPP5PG5JJ7JJP?777?YJJJ!!!^^~!!~::............:......7G! :?^.:^.... .....:.:^^^^^^^::^!?Y?!^~?7JY?7!77?JYYYJ7^!7!5BG5GGYYJY5PBBP5PP5YYPPPGYJ!.\n"                                       
                                      +"7B5:^5YJYY??5GGPPP5PGY?5&&JJB&&@#B5PYJJJYP?7!~!??Y?~^^^~~~:. .... ................ .5BB ~P^ !B^ 7&! :YJ.........:^^^~:::^~JJ7!~~!?5??~^^^:~!?5PPG#&#5YPG5YYYYPBGP55YY555PGJ?~\n"                                       
                                     +":GG~.7J!?77JPPYJY5PGY7Y##Y?YYJ?JGB&&#BP?~~~77~5Y^:^:7!^:.  .7B:  .  ..     ...  .   !G.:BB~. .J5775!:??.   ...........:^^~^.^^77?Y!^~^:^!?JYGBBY5P5YG&&PY5P5YYYYPBG5YYY5Y5PP?!!\n"                                      
                                    +".5GJ~~?777!Y5J?JJYG5?Y#&Y7Y5?7!77~!Y&&&&&B57!!^!^^7~:.  :?5?..JY.  .  ..     ....   :7?  7B~   7@? .#B^  . ................:^~^.:^. .:^7J7JJ^^JGBP5Y5YYG&#5YPG5JPJYPGGYY5YY555!^?\n"                                     
                                    +"YP57~!!7~!5J!!!?5G??&&57JPJ!777!~~5Y7?5PB@@#BBP?!~~^:^PJ:.?&&5~&5.   ..........................:~. .J~  . .         .  ....  ... .:~!?Y?!~!7YY7^!GGGG55Y7G&#Y?PPJY5J5GGP5YYYY5Y!^~\n"                                    
                                   +"^B55J!~7?!57~~!JGY7B&B?J5J77!!~~!?Y5Y77J5PP5GB#P5GPY7^.^JJ?!?B~7P#5: .................................  ... .                ...:~!!77.^~557!!7?7^^7J?7?J5Y?P&#YJG5YJY5PPPYYY555J:?.\n"                                   
                                   +"PY?5J~~J?Y~^~~JP77#@?7YJ?G5P5^^?J7!!^~?J^.^Y5Y55J77JPP57^:^!JG5...:....................... . ^!:...... .. .......     ...:::^~~!Y?^. :~^.:7?7!!!?Y7^^!!~!7YPJY&&PJPYYJJYJ5G?JYJYY!!7\n"                                   
                                  +"~B??Y?^^?Y~^~^Y5~J@#~?Y?J55B?^!JJ!^:^!Y~ .~7..^!?JJJ?^^~Y55?~^:~!^:..  ................. .... ~~... ........... ....::^^^~~!7~~5&G: .. .~!^.^JY?7~7JP?:^~~!~75Y7P@B7YP5JJ??YPY?J55?:P.\n"                                  
                                  +"?G!JY!^!J~~^^Y?~5@B^JY7?YPP^?GP!^!~^77. ^!:  ...^!7???!!^:^7JYY?^^^~^.^~. ............ .......~^  .....  .....^^:^^^~!~~!7??~.7@P::JPY:...!?~:^YYYJ?7JY^.~^!~~J5?Y&#?JP5??JJY55YJYJ.Y~\n"                                  
                                 +".5J~YJ~7J^~^:Y7~P@G:YY75YYP? JJ~~!~?P~ .7~. ..  ..:^^~77777~:.:~?PYJ!^^^7J!......  ..::^7JYYYYYJ~:.  . ..:^^^~~~~~~~~~~!?7~^. ?@&57~:..:.::.:7!..JY^^~~7Y7:^!^~!?P?7@&J?P57J??J5YJJ?^~?\n"                                  
                                 +":B7~????!:^:Y?^5@B^JP~!~^^^.Y?^~~^!?: ^!.    .. ....:::^!!!7!~^:..^7Y5J7^^~!~:.....^755PGG55PPPPPPP!:^^^~~!!!~~~~~!~^!7~:....:Y!:.....::.:!.  :7. ~J~!7~!Y7.~YY5P757!&&J!PY7777J5Y??~.Y.\n"                                 
                                 +"^#~:??7!^:^7P:?&B^!P^!~~~::?5^^::?J. ^?. ..  .........:::^~!77!!~^...^!?YJ7~^^~^::!5GP5Y55YY5Y55Y5GBJ^~~~~~~!!!~!!!?J?~. .......:...............7: :57^7!!5G7~?YYJ?577&&?~5?77!7JYJ?7 G:\n"                                 
                                 +"^#!:!Y7^^:^G:~&&!~P~^~^^::7P!7?!JJ. :J .. .7?^:..  ......::^^~!!!~^^^:...~7YJJ7~^^JGJ5PGBBBGBPP5Y5PPP:~!!!!!77!!??7~:..::::::::::...       .... .7^ :JJ?Y!~5~.~??Y?^P^?&&~!G!!!7?JY77 5:\n"                                 
                                 +":BJ:!J7^:^J?.G@J:??.^^::.!Y^^^^~P~ :?  :. ^~~7!7PJ!!^.......::^^~!!!~^^::...:7Y5Y!JJ7BBBBGBBGGGPG#GY?7?77777???7~:..::::::::::........   ...  . . J: ~?^:^:^P^..:^^:?Y^?&B~?P~!77?JJ~ 5:\n"                                 
                                 +":G5.^JJ^.~P.~@#^~Y^Y777..?J:~^:J~  ^~ ....~??5J~~~^^. .........::^^~~~~~^::..  ~PGY7JPB####B#B###P77J??YYYJYJ!^..:::::::::::............. .  .    .!. ~J:^::7?:.:^^:.5!^G@?:G7!7!!?J^.5:\n"                                 
                                 +".PB^^JY~:!J P@J.7?:G???.!Y~^^~^Y: ^J.... ~77!7!YY?7^...............:^~~~~~^:.^5#B5P#Y??J5GGBBG5J??5B5:5P5P5~..::^^^^:::^:.:..............       .  ~: .7J~?J7G! .:.:.~J^7@B:!!~!!!7J:7J^\n"                                 
                                  +"7&7:!P?^J!.&&^^7~?P!Y!~GP~~~^??. ^? ....~~:...:.........^^^^^::::^:^^~~!!~?G&#B##PB&&&BGGPP5PB#&##Y~P#B5!:::^:^^:::^^:..................          .!. ^Y:^::7?:.:::::Y~:B@~:J~77!!~.7^5Y.\n"                               
                                  +":#5^:JY7Y::@B:!7:JG^G~.JY^!^^Y! .!~ :. !J^ .?Y~7Y: ......    ......:.:7JG#BBBB##&@&#GB&&##&###&&#GG#&G!::^^~~~^^^:::.................^:...         7: .J^^^^~Y~ .:.:.?7:Y@Y.57~~~~::!..PG^\n"                              
                                  +".P&7^^J5!.^@G.!?:!!:7..??:^^:J! .~^... J?~!J7 .~P^ .....            .!GP#B###BB######BGBBBBBB####&@&P??J?7777~::................... .!^^^!Y5PY^    !: .7^:^::J! .:~: ~?:^&# ?Y^:~:.~... J#!\n"                             
                                   +"~##!:~YJ.:@G.~?...:...77.:::J!..~~ ....:::...~7: .....     .     :?B&BBBBGGGGBBGBB#######BB#BGGGG57J?YYYJ?7!!!!7!!^:::::::::..... .^?JY77~~~^     ~: .7?^^^:JY::JGY!^7^:#@ ~Y^:: .^.... !#J\n"                            
                                   +".J&5^.!5!.@B.^7^.:... ?P7??!P?. ^! ................   .       .!G###BBBGGGGGB#BBBBBBGGGGGBPB##B5Y55J?77!^::.:...........:::^:.... .:::::.:.^~     !: .77~7!^YY..??77^~:.G# ^7:.. ...:. ..7&!\n"                           
                                   +"..5#5^:75!B#^:!~.:... !5^::::J: :!................   .      ^YB####GPPPGGGGGBGBBGPPGP5PGBBPPGB#&B5Y5?!^^^::::.......          ... :J7!~!~^~~:     7^ .?..:..?! .^:^~^^.:## :!:   ..^.....:J&^\n"                          
                                   +":.^G&5^.75?G!.~7 :::. :?7::~:?~..^^............   .     .^JG###BGPPGGGGGBBGPYYGGG#P5PPPBBGGGGP55B#G77!^.::......                 .J5  :G!.!Y^    .~. ^Y.::.^7^   .  !~ ~@5 ~~  .:.^^... ..~P&.\n"                         
                                   +":^.7B#Y..?5?7.:!.~7~?^ ~J.^^:~?^ :7............       :JP###BBBGGPGGGPP5J7777?PBG#BBGPPBBPPPP5Y?775G5?7^..:......                .~~  ... ~!.    ^: .!Y!~!:7!       !^.J&: ^. .:~!! ....  .~&5.\n"                        
                                   +".^^:!G#5^.?PJ: ^7 !P5!.^Y?!!^~J7..:7..........     :?P#B#####B5PGG5Y7!~~!??5GBGPPGGGBGPGPYYY?77!~^::!JYJ!:........                            . .~. ^?::.^!Y^      .~.:##    .~!7?^.....   :J@!\n"                        
                                    +".~~:~5#G! ~P5^:!:^?~J!:!J~^^~^!!..^~.......   .^7P##BB###B5J?7?!~^^~!7?J5PPGGYYY5555?JGJ!?7!~^^^::...^7Y5!. ..... .                 ...   ..  .7. :!~::: !^       ~: !#!   .!7?J?:^.:......~&B:\n"                       
                                    +"..~~:^JB#7.:YP7^! ^Y7J7.:Y~^^^^!!. ^~.... ..^JG###B#BP#Y^:::^^^^~!!!!JJPBP5J77777!?775P^~~^^:..:........^55^..... ..                ...  ..  .~. .7~....^?.      .~ .7!  .:!JYJ?7~^:. .:. ^.5@7\n"                       
                                    +".::~!^:7G#5:.!5J7:.7~:::.:?~^^:^7J. ^!. .!YG###&##B7..^#J^~~^~~!!!!!J5BYP!^^^^^~~~~:75!.^::.............  :?7:  . ..                .    .  .!: .??~~~^~P. :^~!..^  ::  .^7J55Y?!~.....  ...J&P.\n"                      
                                     +":^:~!^^~Y#B?::J5Y^.  ....:?~!77!~7:.^J5PG###&5::P@Y.^YGY!^^~~~~^~^^~??7:^::^^^^^::.JY..:.................  :^:. ... .               . .   :~. ^~:.::.~??^.^!^^. . .::..~YP5555?^..:....^~:^JB@7\n"                      
                                     +".~~:^!^.~JPBG!.~Y5J^.......7~^^:^!Y5GGPPP7^.7J^~G@P:~!^:^^^^:::::::::::::::::::::.:?7 ..........     ....... .. .  .~:.                 .^:  ^!^.:..~?  ^^:..    .~:.:?PGPP5Y5J:....:::^~!77G@J\n"                      
                                      +".~~:^7!^~!JPBP~:~?J?~:. .  ^7Y5PGBGP?^..~:.:^?Y?~::^::::::::.::::::::::::::..:.: ~7: .........         ... ....:~7!GG!.              .~^  :7~^.:.:!~   ...    .^^:.~5BBP5PP557...:..:^^~!7JB@Y\n"                      
                                       +":7!:^!7!!!7?PBP!^^7JJ!^.~?YPPPP??~7?7~::!?~^:.........:::::::::::::.........................                :YG^. .!GG!           .^^. .~!:^~!~^^.         .^~:.^7PGGGB555B5:..^.:::^^!!75B@5\n"                      
                                      +"..~Y?:~!!7!~!7?PBP?^:~7J??J57:^Y77!!!7?J7:.:~^. ...:.::::::::.:::...... .::.... ..  .. .  .               .....:. .^?~!5P?^.     .^.. .~~^::^.:~:        .:^^:.:7GBGPBB555BB7..:...~^^^:~?5#@5\n"                      
                                      +"...~G7~^~!77!~!7JP#BJ~^:~777!~!!~7?7~^^^~7!...:^^...::::::..........  . :!~:^!#!. .~77~^.  .               ....   .~~^^!J?:   .::.  ^~~^^::::^:        ..^^^^^JGBBGGBBGGGB#5^......^^^^^~~P&@Y\n"                      
                                     +".:::.^Y?!~~7??7!~!7?YGBGJ!~~!7?7~^.:~!~^::^?Y7~:.:::::.............        ^!.~G.  5B..:~7.                     .   :?7^.   ..::  .^~^~!~^::::.      .:^:^^~!YB#BGGBBB#BG#&P!^:. ..::...:^~G&@?\n"                      
                                    +"..:^::::!YJ!~~7??7!7!!7JPBBGJ!^~77JJ!^^^~!7!?~^~!!^: .::::.........       .:^^^&!   .~!!~:. ...                          .::..  .^~~^::::^^^: ......:^^::^!JG#BGGB##B####&&G7!777^:^^:..:^^JB@&~ .\n"                    
                                  +".. .:^^^^::7JJ?!!77????7!!7??5GBGY!^^!JYJ7!!7^~:^^:::^!::...::::....        ^!!~!P^  .!7::~B^.....                    ..::::.. .~?7^:::::.... ..::^^^^^^!?JG##BGGBBBBBB#&#&&GJ?7?J7?~J?~::::^G&@P. .\n"                    
                                 +"...  ^~^^^^:~JJJJ77?7J?JYJ7!7?7?JYPG5J?~~!7JYYYYJ~:..:~^77^::::...:::.....             .::::.......              ....::......^!:^:^!!^:. :  .:^~!~~^~7J5PGB##BGGB###B####&@&PJYJ?77?J?J~^~~::~G&@7  ...\n"                  
                                 +".... :~^^^^^^J#GJJJ7!???JJ???J!7???JY5P5Y?7!!77?5P5?7~^?^:::^::~::.:......:.... .         .    ....     . .. ..^........!~~^:^:::...::..:^~!!!!~~!7JPBBB###BGBB##B###&&&@@#PJJ55Y7~^::..^!~~!YB@#: .. .\n"                  
                                 +".... :~~^^~~^~Y&&PJY????7????JYJ?!!?JJJJYPP5YJ??7!!?JY5J?~^~:...:.?~~^^::..............:::..::::::.......  .... ..^~:?:~:?~:^:.:.^:^^!?J!!~~^!7JY5PB##BBGBGB#######&&&@@@#P5Y7~!7777::.^!!^.^P&@J  ....\n"                  
                                 +".... :~~^^~~~!!Y&@B55YYJ???77??????777YY55YJY5GGPPYJJ??7?YJ77!~~~^~^^:::.! !:Y!.!.!.:....^.:........:.^^^^::^^J7^^^^^:^.^:~!~!!JJ??77!^^:^~7JY5PGGGGGGB########&&&&&@@@&BPPYY7!^.:~!^::!~7!:?&@#^  ...\n"                   
                                 +".... .^~~~~!~!?75&&PP#5YYJJJ7J77?????JJYY55YYJ5P55PPGGPPYJ7!!~~~!77J???7!^~^^!:.^.!.!.::~J.!.^.^^:~^J.~.::.~ :.J^^^^!^!7!7???77!~^^:::^~7JPGGGPPPP5GB######&&&&&&@@@@&#GP55Y!^^!!~^^::~::^:~B@@Y. ......\n"                 
                                 +"......:~~~~~~~?J75&&7?&&G5YJ7J????77??JJYY555555PP5Y5P55555PPY7!7777!7JYYJY555555Y?7777!77~!~!!~~!!!J~?777777???JJ???7?7!7!!!~!!!7?JYPBGGGGPP555PGB###&##&&@@@@@@@@&&BGPP5J7~~~~~^::::^~^.:5&&P^........\n"                 
                                 +"......:~~~~~!~?5Y?5&&~:Y&@#BYYJJ???????JJJJY5555PPGPPGPP55JJYYYJJ?7777!!777???JJY5JJYYYJJJ?YJ?J?J5JYYYYJJJ???????J??JJ?JYYY55PBBB####BG55P5PGBBBPG#&&&&&&@@@@@@@@@@&#BGP5Y?!^:^^:^~^^:^!!JG&@G?:........\n"                 
                                 +".......^~~^!!!~J55YJ#@P!!G@@&BP5YJJJJJJ?J?J????JYYPGPPPPPYYY55YYY5J?J???777!!!!!!~!!!!777!!?77777!!!!77?77?JJ????J??J5YYPPPGGB#BGGPPGGPGGPPGBBGGB&&&&&@@@@@@@@@@@&&&&BP5YJ?~:^:~7~^^^~!:?B&&G7J.........\n"                 
                                 +".......:~~~!!!~7YPP5YB@#~.Y#&@&&#GPP5YJJJJJJJJ????J55PPPG555YJJJJYY5PYJ5YYJ??777!!7!!!!!!7!7?77777!!!!!!!7?JJJY55PGGGGGGPP555PGGGB###BPPGGGGBBBB&@&@@@@@@@@@@@@@&&&&&#PYJ77!~~^^~^.:~!!YB&&P!?!.........\n"                 
                                  +".......^~~~!!!~JP5PPYP&&GPY7B&&&&#BBGPP55YYYY555YYYYYYJJYYYY555YJYYJJ??777?JYY5P5J5JY5YY5PPPP5PPPPPPGGPPGBBGPPGPPP5555555PGBBB#B##BBGGBBBGBBB#&@@@@@@@@@@@@&&&&&&#BG5J!^^:~~~.^~^^!!7G#&&57?!:.........\n"                 
                                   +"......:^~!~!!~!5GPPG5Y#@#?~!YGB#&&&##BBBGGP555Y55PPPPPP5P5Y555YPPYJYJ??JYYYYY5PY755JJYJ7JY?7?55J???JYJYYYYYY55YYY555PPGGGB#######BBBBBBBB##&&@@@@@@@@@@@&&&###BGG5J?~^~~!~:^::^~!~YG&&#Y?J?~..........\n"                 
                                    +".....::^!~!7!~!PBPPPP5P#@G7?!75B##&&&&&##BBBGPP555PPGG#B#BBBGBGBGPP5Y555555YYYYYYYPPYYY5YYY5PP5YYY55YYYJJYJ555555PPPPPG##&&&&&&#BBB#####&&@@@@@@@@@@@&&&&&##BBGPYJ!!!~^!7!~^~!7YB#&&BPJ?J7.:.........\n"                 
                                     +".....^^~!~!7!~!PBPGPGPYG&&#Y^:7PBB##&&&&&&####BBBGGPPPGGBBBBBB###BBBGP5GP55YYY5Y?JJ???YY?J5YYPPJ?YJJJJJJYYY55PPPPPGB&&&&&&&&&#######&&&@@@@@@@@@@@&&&&####BG5J!!~~7^.:~^~^^?5PB&@&BBPYY7:..:........\n"                 
                                    +"......:^^~!!!!!!7PBGGBBG5PG&&G!..!5GBB##&&&&&&&&###BBBBGGGGBBBBBBGGGGGP555PPP55555YYYJJYYJYYJJ5GPJYYYYYY5555PGGGGGGB#&&&&&&&&&##&&&&&&&@@@@@@@@@@&&&&&###BBB5?!^~~~^^~!!^!?JP#&&&#BBG55J^..:........\n"                  
                                    +".......^^~~!!!!!!7P#GGBBBGP5G&&G~.:!5GB######&&&&&&&&&####BBB#######BGGPPP55Y55PPP555PP5YP55YYYYY55YY55555PP5PGGGB&&&&@@@@@@@&&&&&&&&@@@@@@@@@@@&&&&##BBGG5?~:::^!7~:::~?YPB&@&#B##G5PJ~:...:.......\n"                  
                                    +"......:^~^~!!!~!!!5##BB#BGBP5G#&B?:~7YPGB#######&&&&&&&&&&&&###########&###BBGPP55555P5YYJJJY5YJ5Y5555PGGGGGGB#&@@@@@@@@@@@&&&&&&&@@@@@@@@@&&&&&###BBG557::..:^:^.:~!!JG#&&BGGB##GBG?~^:.:.........\n"                  
                                    +"........:^~^~!!!~!!!?G&#BB#BBGP5P#&&GY7!7?PGGB####&#&&&&&&&&&&&&&&&&###&##&&&####BBGGGGPPPPPGGGGBBB########&&&@@@@@@@@@@@@@&&&&&&&@@@@@@@&&&&&##BGGPPY?^::...  ::.~!?PB#&@#55B##BBBG?!~:...........\n"                   
                                    +".........^^^~~~!!!!!77YB&#BBBGBBGP5G#&&GJ!7J5J5B#B#####&&####&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@&&&&&@@@&&&@@&####PGP5J?!:......:.:~!~JG#&@&GYYPB###B&G!~!^.............\n"                  
                                      +".......::^^~~~~!!!77!7P&&BBBBBBBGP55B&@&P55~:^75GGBB#B##&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@&&@@&&&&#&##BGPGGY77^::^:::...:::~JGB&&&#G55GB#BB#&&J!!!^............\n"                    
                                       +"........:^~~~~!!!!!777?P#&#BBBBGGBG55PGB##GG!::^^7YGGGBB###&##&&&&&&&&&&&&&&&&&&&&&&&&&###&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@&&&&&&&#BBBBG55Y?!^^:..  .. ...:~?G#&&&BPY5PG##BBB#&G!!7!^:.:.........\n"                     
                                        +"..... .:^^~~~!!!!!!7777?G&@&BBBBGBGGBBP5G#&#BGJ~::.~?JY5PGGBBBB###&#####&&&#&&&&&&&&&&&&&&&&#&&&@@@@@@@@@@@@@@&@@@@@@@&&&#&#BBGPG57?!7!^:......^~^:~75B&&@&#GJYPBB#BGBB&&B?!??!:..::........\n"                      
                                        +".........:^~~~~!!!!!7777?YG&@&##BBBGGBBGGPPPGB&##PJ?~:..^?!J5PGGGGBBBBB##B#B###############&&&@@@@@@@@@&@@&&&&&&&&&&&###BP5GPY5GJ!7^::^^. .....~5G#&&&&#GP55PPBBBBBBB&@#J!!7?!:...........\n"                        
                                         +".........:^~~~~~!!!!!7?77?JG&@&#&#BBBBBBBGGGGGPGB#&&#PY??^...:~JG?Y5PGGGGGGGBGGGGGBBB##&&&&@&&&&&&&&&&&&&#####BBPPGGPGYY?!^~?J5G?~:  .. :^75G#&@@&#GP5Y5GBB#BB###&&&#J77??7~:............\n"                        
                                          +".........:^~~~~!!!~!77?????JP#&@&&&##B###BBBGGPPPPPGB#&&&BBPJ77?~7^::~?J7??YJ5GGGGGBBBGGGGGGGPPGGPGPPYJYYJ~^^^~J57^^. .:. ..  : ..^?Y5B&&&&&&#BPY55PGBB######&&&&BY???7?7^.::..........\n"                         
                                             +"........:^~~~~!!!~!77??????YG&@@&&####BBBGBBBGGPGGPP5PBBB###B##G?!7?!:  ..7J7::.:~!J?^^^::PJ^..... .     .  .::.   ..::^~??5B#&&&@@&#BBPYYY5GGB#B######&&&&&GJ7????7~:.::...........\n"                         
                                             +".........::^~~~!!!!!!!77?JJJJJ5B&@@&&&###BBBBBBBBBBGPPP55Y5PGBBB###&&#GB5J5Y!^^:::.:^:....::.           ..:::~JJ?YPG##&&&&&&&##BGPP555PPPPGB##B####&&&@@&G5J?JJ?7?~^:...............\n"                         
                                              +"..........:^~~~~!!!!!!77??JYYYJY5B&&&&&&##BBBBBGGGBBB#BGGGPP5555PPYJY5PPPGGBB#####&####BBPGGGGGPBGGGBBB##&####BGB#BBBBBGP5PGP5YY5GGGGBB#######&&&@&&&#PJJ?JJ?77!^:::::...........\n"                           
                                                +"..........:^~~~!!!!!!777?JYYYJYYY5G#&@@&&&&##BBBBGPGBBBBBBBBGPBBG5PPYJ7!77??7?JJJYYYYJJYYYJY5Y555BBBBBG?JJJYYY5PPGPPGGGBBBGGGGB#BBBBB###&&&&&@&#BPY?JYJJ???7^..:::..........\n"                              
                                                 +"...........:^~~~~!!!!77777?JYYYYYYY5PB&&@@&&&&&&&#BBBBGGGBBBBBB####&#GGP5Y55YJ7?J???7!!7777?7!7J5GPPG#5YPPPGGPGBBBB#####GGBBBB#B##&&&&#&@&##G5JJYYYJJ?J?!^:::.............\n"                               
                                                   +"...........:^~~~!!!!7!777??JJJYYYYYYY5PB#&@@@&&&&&&##BBBGGBBGGGBGBBBBBBGPBB#GGGGGGPPPPPPPPGPGPGGGGGB##############BBB#####&&&&&&&&&&#BBP55YJJYJJ??J7!^..::.:........\n"                                   
                                                      +"...........^~~~~!!!!!!77???JJJYYYYYY5Y5PB#&&&&&&&&&&&&&&##BBBGBBB5PBG55PGPGGPPG5PGGGGGG#B#B#BBBGG#################&&&&@&&&&&&#BGPYYY55YYJJ??J?!^^:..::............\n"                                  
                                                       +"............:^~~!!!!!!77777???JJYYY55555555PGB#&&&&&&&&&&##&&&#######BBGPGGGPP55555P5PG5PGBBBBBB####&&&&&&&&&&&&&&&&&&&#BG5555YYYYYJJYYJJ?7~^:.................\n"                                    
                                                          +"............:^~!!!!!!!!7777??JJJYYYY55555PP55PPGB###&###&&&#&&&#&&&&&&&&&&&&&&&&###&&&&&&&&#&&&&&&&&&&&&&&&&&##BGP55555Y555YJJYJJ???7~::.::..:............\n"                                      
                                                           +".............::^~!!!!!!!77777??JJJJJYYYY555555555P5PPGGBBB&&&&&&&&&&&####&&######BBBB########&&&#####BBGGGP5555555YY55YYYJJJJ??7!~:::....................\n"                                      
                                                             +"...............:^~!!!!!!77777?????JJJJYYYYY555555555555555PPPGGGGGBBB#BB#G#########BBBBBGGGGPPPPP5555555555555YYYYYYJJ????7~^::::...................\n"                                         
                                                               +".................:^~!!!!!!777777?????JJJJJYYYYY55555555555555555PP55P5PP5555PPPPP55555555555555555555YYYJYYYYJJ?JJJ?7~^^::.:::..................\n"                                           
                                                                 +"...................:^^~~!!!!!!77777?????JJJJJJJYYYYYYYY5555555P555555PP55555555555555555555YYYYYYYJJJJJ?????77!~^:::::....................\n"                                               
                                                                    +".....................:^^^~~!77777777777?????JJJJJJJJJJYYYYYYYYYYYYYYYYYYYY55YYYYYYYYYYYJJJJJJJJJJJ?7!!~^^:...:::......................\n"                                                
                                                                        +"......................:::^^~~!!!!7777777777???????????JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ???777!~~^:::..::::.......................\n"                                                    
                                                                        +"..............................:::^^^~~~!!!!!77777777??7777?????????????????777!!!~~^^^:::....:::::.........................\n"                                                       
                                                                               +"....................................::::::::::::::::::::^^^^^^^:::::::::...:::::::::::...........................\n"                                                          
                                                                                   +".........................................::......:::............::::::.................................\n"                                                                
                                                                                     +"..................................................................................................\n"                                                                  
                                                                                          +"..........................................................................................\n"                                                                      
                                                                                                    +".........................................................................\n"                                                                             
                                                                                                        +"............................................................\n"                                                                                      
                                                                                                                         +".....   ......         .......  .\n");       
					
					break;
					
				}
				return treasureImage;
			
			default:
				numberOfCases=(int)(Math.random()*3+1);
				switch(numberOfCases){
					case 1:
						treasureImage= ("\n"
															+"!#@#!\n"                                         
														  +".?@@P@@?.\n"                                       
													   +".~J#@B7:7B@#J~.\n"                                    
													  +"J&@@P7^::::!5&@&J\n"                                   
													  +"!G&@BJ!^^:^JB@&G!\n"                                   
											   +":JY!     :!G@&Y~Y&@G!:     !YJ:\n"                            
											  +"!#@@@Y:      !&@#@&!      :Y@@@#!\n"                           
										   +"^5#@#?!P@&G?     :5B5:     ?G&@P!?B@#5^\n"                        
										   +"!#@@G!^J&@&5               5&@&J^!P@@#!\n"                        
											 +"^Y@@&@G!.                 .!G@&@@Y^\n"                          
											   +"~GBJ                       JBG~\n"                            
							+".^~~~~~~~~:                  .^~~~~~~~~.                  :~~~~~~~~^.\n"        
						   +"^#@&######&&5????J?:  .7J????J#@&######@&Y????????????????5&&######&@#^\n"       
						   +"J@#~^^::^:5@@######Y77?B#####@@B~^^^^^:P@@################@@5^^::^^^#@J\n"       
						   +"J@#^:~?!::Y@#J?????JJJJ??????G@B^::::::P@#???????????????J#@5::!?^:^#@J\n"       
						   +"J@#^:7B5::5@#????????????????G@B^::::::P@#????????????????#@5::5B7:^#@J\n"       
						   +"J@#^::::::5@&GPPPPPPPPPPPPPPP#@B^::::::P@&PPPPPPPPPPPPPPPG&@5:::::::?Y^\n"       
						   +"J@#^:~J7::5@@################&@B^::::::P@&################@@5::7J~:::Y^\n"        
						   +"J@#^:7BY::5@#J?????????????JJB@B~~~~~~^P@#JJ?????????????J#@5::YB7::P#!\n"       
						   +"?@#^:::::.Y@#???????????JP#&&&@@&&&&&&&&@@&&#PJ???????????#@5:::::::#@J\n"       
						  +"^P@&YJJJJJJB@@GPPPPPPPPPPB@@?~~~~~~~~~~~~~~~7&@BPPPPPPPPPPG@@BJJJJJJJ&@P^\n"      
						 +"P@&GBBBBBBBBBGGBBBBBBBBBBG#@#^::::^?PGP?^:::::#@#GBBBBBBBBBBGGBBBBBBBBBG&@P\n"     
						+".&@Y^^^^^~^^^^~~~~~~~~~~~~~5@#~:::~#@BYB@#~:::^#@5~~~~~~~~~~~~~^^^^~^^^^:J@&.\n"    
						+".#@Y^^^^?&P^^?&&&&&&&&&&&&&&@#~:::~&@P7P@&~:::^#@&&&&&&&&&&&&&&?^^P&?^^^:J@#.\n"    
						+".&@Y^^^^^!~^^^!!!!!!!!!!!!~P@#~::::~5&@&5~::::^#@P~!!!!!!!!!!!!^^^~!^^^^^J@&.\n"    
						+"P@&GGBBBBBBBBGGGGGGGGGGGGG#@&^::::::G@G::::::^#@#GGGGGGGGGGGGGBBBBBBBBGG&@P\n"     
						  +"~P@&YYYYYYYB@@GGGGGGGGGGGB@@Y^:::::^7^:::::^Y@@BGGGGGGGGGGG@@BYYYYYYY&@P~\n"      
						   +"J@#^:::::.Y@#J??????????J5#@&GPPPPP5PPPPPG&@#5J??????????J#@5:::::::#@?\n"       
						   +"?@#^:7#5::5@#?????????????JYPGBBBBBBBBBBBBP5J?????????????#@5::5#7:^#@J\n"       
						   +".~!^:~?!::5@&P555555555555555555555555555Y???Y55555555555P&@5::!?~:^#@J\n"       
							+"^~^::::::5@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&G?J?G&&&&&&&&&&&&@@5::::::^#@J\n"       
						   +"?@B^:7#5::5@&JJJJJJJJJJJJJJJJJJJJJJJJJJJJ?JJJ?JJJJJJJJJJJJ&@5::5#7:^#@J\n"       
						   +"J@#^:^?!::5@#J???????????????????????????JJJJJ???????????J#@5::!?^:^#@J\n"       
						   +"J@#~::::::5@&JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ&@5::::::^#@J\n"       
						 +"~P&@@#######&@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@&#######@@&P~\n"     
						+".#@P?????7????777777777777777????7????7????7????777777777777777????7????7P@#.\n"    
						+".#@Y^^^:!Y?^:!YYYYYYYYYYYYYY57^:757::757::757::7YYYYYYYYYYYYYYY!:^?Y!:^^:J@#.\n"    
						+".#@Y^^^:7GJ^:7PPPPPPPPPPPPPPGJ^:?G?^^?G?^^?G?^^?GPPPPPPPPPPPPPP7:^JG7:^^:J@#.\n"    
						+".#@P!777!!!77!!!!!!!!!!!!!!!!!77!!!77!!!77!!!77!!!!!!!!!!!!!!!!!77!!!777!5@#.\n"    
						 +"7#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#7\n");
					break;
					case 2:
						treasureImage= ("\n"
										+"..::...\n"                                                                 
							   +"...^~!!!!~~~:..:^~^^^:.\n"                                                      
							+"..:^!77!!!!~^::^!!!!~~~~~~^^^::::...       ...\n"                                  
						   +".:^7777777!^::~77!!!!~~~~~~~~~~~~^~~~~^. .^~77!!!^..      ...\n"                    
						  +".^~7?77777!::^??777777!!!!!!~~~~~^^^^. ..^!^::::^^^~~^.  .:~!!!!!:.\n"               
						 +"::~??7777?!:^~??77777777777777!!!!!!~. .^7!::...       ..:!77?JJJ?77.\n"              
						+".:^???????!::~??77777777777777777777~..:7?!!!!!!~~~^.   .!!?Y5P55PP57!\n"              
					   +".^^?J?????7::^?????77777777777777777~:^^7?77777!!!!7: . ^77Y55555555GY!^\n"             
					   +":^~YJJJJJJ^:^?J?????????77777777777~..^7?7777777777::::~7?5555YYYYYY5G?!\n"             
					   +":^~Y?JJJYJ^^~YJJJJJJJJ?????????777!...!7!!7!!77777^..:~!?55YYYYYYYYYYPY!.\n"            
					   +".~~^^~~~!!^^!5YYYYJJJJJJJJJJJJ????:::~?77777!!!!7^...^!?55YYYYYYJJYYYP5!.\n"            
						+"~~~~~~^^^^^^~~!!77?JJJYYYJJJJJJJ~::^?JJ????????!...:!75YYYYYJYYYYYYJ?7!\n"             
						+"^^~~~~^^~~!~^^^^^^^^~~!!7??JJJYY^::!YJJJJJJJJ??^:::!!5YYYYYYYYYJ?7!77?~\n"             
						+".^7GB5?!~^~~^^^~~~^^~^^~^^^^~~!!^^^75YYJJJJJJJ7:::~!JYYY5YYJ?7!!77??77^\n"             
						 +"^~5&B5557^^?Y?~~~^~!~JJ~~~^^^^^^^^~~!!7??JJJY~:::!7P55J?7!!77??77!!!!.\n"             
						 +".^7BB5YYJ^^J##PYYJ~~7J7^~~^^^~~~~~^^^^^^~^~~!^^:^!?J77!!7??77!!!7YJ!^\n"              
						  +"^~5BPYYY~^7B#PYY5~?7???GY7!~^^^~~^^~~~~!~^^^^^^^!!!7???77!!!7??5P7!.\n"              
						  +":^?BPYYY~^~P#PYYJ!57!57##GP5YJ!^^^^^^^^~^^~~~~!~!???7!!!777?J?JPJ!^\n"               
						   +"^~5GYJY7^~5BPYY!!J7YB?BBGP5557^^!GBY?7!~^^^^~~^!7!!77JPG?JY??YP7!.\n"               
						   +":^?G5JY?^^YBPYY!!?~?BYPBG5Y557^^~G#G5555YJ?!^~^~!!7JYGBG57!!JPJ!^\n"                
						   +".~!5PYJJ~^?GPYYJ7?!75?5G55555?~~~P#P55555557^^^~~!YPGGBJJ??J7P7~\n"                 
						   +".~~JYYYY!~7GPYYY5J!77!YG55555J^^~PBP5YYY555~^^^!!75P5Y5?7JJJ7Y7:\n"                 
							+":~77!777!7PP555Y5!!!!JG55555J~~~5BP55YYYYY^~^~!!?YYYYJY77??J??.\n"                 
							  +"...:^~!?J7?JJY5?!!!?P55Y55Y~~~5BP55YYY5?^^^~77Y55555555555J?.\n"                 
								  +"..:^~^~!!7777777P555555~~~5G55555Y57^^^!7J55555PPPPYJJ7~.\n"                 
								   +".......:^~!!777?JJY555!~~YG55YYY5Y~~~~7?Y55PPGP5Y?!~^::..\n"                
										+".....::^~~!!!7?J7!~YGP55Y55J~~~!??5PPPP5J?!~^::.....\n"               
										  +".........::^~!!!!!YYYY55557~~~77JGP5Y?7!~~^::.....\n"                
										   +".....    ...::~!!J!!!7?JY!!!~7J5YJ7~^^^^::......\n"                 
													 +".......::^~!!!!!!!!?J?!~^:::.........\n"                  
													 +".       ....:^~!!!!?7~:::......    ....\n");                
					break;
					case 3:
						treasureImage= ("\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&@@@@@@@@@@&&&&&&&&&&@@@@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&@@@@&&&&&&&&&&&&&&&@@&&&&&&&&@@@@@@@@@@@@@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&###&&&&&&&&&&&&&&&&&&&&&&###&&&@@@@&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&#######&&&&&&&&#####&&&#####&&&@@@&&&&&&&&&@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&@@@@@@@@@@@@@&&&&&#######&&&&&&#######&#######&&&@&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&@@@@@&&&@@@@&&&&#BB#BBB##&&&&###############&&&&&&&###&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&@&&&&&&&&@@&&&&#BBBGGB##&&&&##B#####BB#####&&#######&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&#GGGGPGB#&&&&&#BB###BB##################&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&##BGPPPPGB#&&&&#BB#BBBB#####&&####B######&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&####&&&&&&&&&&&&&###GPPPPGB##&&##BBBBBGGBBB#&&&&&#BBBBBB##&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&#####&&&&&&&&&&&####GP55PBB####BBB#BGGGBBB&&&&&&#BGGBB##&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&#########&&&&&&&###BP5Y5GBB##BBGGBBGGBBB#&&&&##BBGBB##&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&#########&&&&&&#BBBP5YYPGBBBBGGGGP5PGGGB####BBGPPGGB#########&&#&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@&&&&&&##########&&&&&#BBGP555PGGGBG5YYYJY555PP5P5YJ??JJYYYYYYY5PPGGGGG5YY555PGB@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&@@@@&&&&&&&&###########&#BGGP55PPGGPY?777??JY5PPPP5YJ?JJJYYYJJY5PGGBBGGPP5555Y5GBB&@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&###BBBB##BB###BP55YY5P5J7!~~~~!7J5PGP5YJ????JJJ??J5PGBBBBGGPPPPP55GBGPB&@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@&&&&&&######BBGGGGGBBBGGBGPYJJ7!!!!!~^:::^~7JYY?77!!!!!!!!7?YPGGGGPPPP55YY5GBB5JY#@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@&&&&&&&&&###BB###BGPPPPGGGP55PPY!^::^~~~^:..::^!77!!~~^~^^^^^~7JY555YJY555YY5BBGP5JJG@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&##BBBBBBBGGPPPPPPPY?!^:...:::::....::^~^^^^^^:::::~!7?JYYJJYY5Y5PBBGP5YJJG@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&##BG55Y??777!~^^:^^:..... ...........:::::::::::::^~!7?JYJJY555PBG5P5YJJY#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&GP5J7~^:..             .                           ....:^~?JJYYY5PGGGPYYYJJP5#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&####BGP5YJ?!!^:..                                      ..:~!7?5GGGBBGPY5Y5PB&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@##&&&###BBBB&#BP5J7!~~~~~^...                ..::^^~!77?J?JJJ?5B#####B#BB#&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&#&&&#BBBBBB#BBBBGPPP5YGGPP5J?!!!~77!^~^^~~777???YJ5P5P5??????5B&@&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&##&&##BBBBB###BBGGGGGGB#BBBPG5Y?7YGPJJ7777777??YPYGBBBPJJ?JJYP&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&###&&###BBBB##BBBBGGGGBBB#BBBPPP5YPGGJJ?77??????5##&#BPJ?JJ??JP&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&##BGB#B#&&#####BB###BBBBGGGGBBB#BBGGPPPPGP5P?7?????777J???777!!!7??P&&&&&&&&&&&&&&&##&&&&&&&@@@@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&###BGP5J?7?P&&B&&&&&######&BGBBBBBBBBGBGGGGPP5PGP5P?!!!!!!~~!!!!!!!!!!!7?7GB555PPPGPPGGGGGBBBB###&&&&&@@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@@@@@@@@&&&&&&##BBG5YJ77~~~~!5&&#&&########&#BBGBGBGBGGBBGPPPPP5PGPJPY^^^!~~~~!!!777!!~!!777GG5YY5YY55YY55PPPGBBB###&&&&&@@@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@@@@@&&&&&&&&&&#BBGG5JJ?7!~~^^~~~J&&##&BGBGBBBB#BGGGGGGGGGGGBBGGGPP5PGGY5P!~!~^^~~!!!!!77!!!!!!7BGP55P5555PP555PPPGGGBB###&&&&&@@@@@@@@@@@@@@@\n"
							+"@@@@@@@@@&&&&&&&&&&&#BBGPP5J?7!~~~~~^~~~!7#&##&##BGGGBG#BGPPPPPGP5PPBG5PP5PPPGPYJY7!!~~~~!!!!!!77!!!~~~YGP555Y55555PP555PPGPGGGB####&&&&&@@@@@@@@@@@@@\n"
							+"@@@&&&&&&&&&&&###BBBBGGP5YJ?7!!!!~~~!!~~!!YB#B##&&##BGG##GPP55Y555YYPGY???YYPGPYYY7!!7!!!7!!~~~!!~~!~~!JG5YYYJJYY5YY5555Y5PPGGGGBB####&&&&&@@@@@@@@@@@\n"
							+"&&&&&&&&&###BBBBBBBBGP55Y??77!7!!77777777?J5G#&&&&&&#&#&##BGGP55YYJ?GGYJJJ??YGGYYJ!77!~!!~~^~~!!^~~!!77PGYJJJ?????JJJY5P5Y55PPGGBBB####&&&&&&&@@@@@@@@\n"
							+"&&&&&##BBBBBBBGGGGGPP55YJ???7?777??JJJJJYYY555PG#&&&&&&&#&###BBP5YJJGBYJJJYYY5PJ557?!^~!!~^~~~!!~~~~!!!PP?777!!7?????JYYPP55PPGGGBBB###&&&&&&&&&&@@@@@\n"
							+"####BBBBBBBGGPPPPPPPP55YYJJ??????JJJJJJYY5555555555PGB#&&&&###BBGPYYGBPYYJ5PPPPYY5Y7~!~~~~^^~~!!~~!!!!7J?777?777?J??JJYY55GGPPGGGBBB#####&&&&&&&&&&@@@\n"
							+"BBBBBBBGGGGPPPPPPPPPP55YYYJ????JJJJJYYYYY55Y55PP5YJJJJJYPG#&&#&#&#BBGGP55YYPGBPJ557~~!~^~~~^~~~~~~!!7???7777?J?77J?YYYY555PGBGGGGBBBB######&&&&&&&&&&@\n"
							+"##BBBGGGGPPPPPPPPPP555YYJJ?????JJJJYYY55555Y5PP5J???????7???YP##&&&&#GPPP5555P5?Y5?^^~^^^~~^~~~~!!!~~~~~!!!7???JJJJJJ55555PPGBBGGGBBBB#########&&&&&&&\n"
							+"BBBGGGGGGGPP5555YYYYYJJJ???????JJJYYYYYYYYYY5YJ?7??777777777?J??JYPB&BPGGP5P5Y5??YY~~^^^^~!!7!~~^^^~~~~~!!7777?JJJJJJY5555PPPGBBBBBBBBB###&&&&&&##&&&&\n"
							+"BBGGPPP555555YYYYYYJJJJJ?????JYYY5YYYJJJJYYYJ7!777?77!!!!!!????777!!7JYPGBGPP5Y??YJ~~~~~~!~^^^^~^^~~~~~~!!!77?JJYYYYY555PPPGGGGB###BB####B##&&&&&&&#&#\n"
							+"BBGGGGPP5555YYYYYYJJJJJJJJJY5P55YYJYYJYJYYJ777!!7!!7!!!7!!??777777!~~^^~7J5G#G5J7!!!~^^^:::^:^^~~~~~!~!!!77??JJYYYY5555PPGGGGGBBBB#############&&&&###\n"
							+"BBBBGGP55555YJJJJYYJJJYJJYP555YYYYYJJJYJJ777?777!!!!~~!!7?7!!!!~~~~^^~~7!~^^~7?7^^^:^:::::^^^^^^^~!77!777???JYYY5PGP5PPGGGGGBBGGBBB#&############&&&&&\n"
							+"##BGGGPPP555YYYYYJJJJJJY55PYJYYYJJJJJJ?!!!!!!~~~~~~~~^~!!!~~~~~~^~^::^77!~~~^^::::::^::^^^~~^^~7~!777????JJJYYY5PGGGPPPGGGGBBGBBBBBBB##############&&&\n"
							+"#BBBGGGGP555YY?JYYJ?JY55555YJYYJJJ???!~~~~~^^^^^^^^^^~!~~~^^^^^^:^^.:^^~^^^~^:::::^^::^^^~~~!!7?777???JJJYYY55555PPGGGGGGBBBBBBBBBBBBB#&&######&#&##&&\n"
							+"#BBGGGPPPP5555P5Y55PPPP5YYYYJJ???77!~^~!!~^^:^^^^^::~!~^^^^^:::::~:.:::^::^^^^^~^^~~^~^~~!777J?7???JJJJYYY555PPPGPPGGBGGGGBGB#BBB####BB#&&#########&##\n"
							+"BBBBGGGGPGPPPGBBGGBGGP5YJYYY???77!~~^^~~~^^^:::^:::~!~^::^:::::::..:::^^~~~~~^^^^~!!~~~!7777Y5J?JJJYYY5Y5PPGBBGGGGGGGGGGGBB#BB########B###&&#######&&&\n"
							+"BBBBBBGGB##B#&&#BBGGP5YYYYYJJ??!~~~~~^^::::^^::::^~~^^::^^:::::::..^:^^~^^~~^^~~~!!!!!7?7?7JJYYY5Y55555PGGPGBBGGGBBBBBBBBBBBGBB####&&######&&&##&&&&&&\n"
							+"##BBBBB#&#B######BGPPP5555YYJ7!!!!!!!^^^^^^^::::~!~~^^^^^::^::::^::^~~^~~~~~!!!!7?7?77??YJYP555Y5GPGGGPBBGGGGGBBGBBBBBBBBBBBBB##B###&&#&#####&&&&&&&&&\n"
							+"###BB##&&&#&##BBBGGGGGGPGP5J???JY5?!!~~~~^^^^::!7~~~~~~~^^^^^^^!!^^!~!!!7?7!?J??JJJJJJJJ5555555PG#BGGGGPGBBB#GB#BBBBBBBBBB###B#&##&&##&&&&###&&&&&&&&@\n");	
					break;
				}
		}
		return treasureImage;
	}
	/**
	*<b>nmae:</b>generateimageInAscii <br>
	* will generate an image for the enemys 
	* <b> pre:</b> the enemy must have a type other than null<br> 
	* <b> post:</b>will be save the image in a message<br>
    *@param typeenemy is a int data type that stores the type of the enemy given by the user.
	*@return the enemyimage variable that stores the corresponding image data
	*/
	public String generateasciiimage(int typeenemy){
		String enemyimage=""; 
		int numberOfCases=0;
		switch(typeenemy){
			
			case 1:
			numberOfCases=(int)(Math.random()*4+1);
				switch(numberOfCases){
						case 1:
						enemyimage= ("\n" 				  
																	  +".^!77~^\n"                                  
																  +".~?YY?~:\n"                                     
																+":?PP?^\n"                                         
													+"~J7!~^^^^~YBBY77?JJJJ?77!~~.\n"                              
												  +":~!?YPB##BBBGG55YYY55PGBPY?!^.\n"                               
												+"^?YPPGBBBBP5YJ???????????JJ?7~:.\n"                               
											  +":75GBBBBBBBY???????????????????J?7~:.\n"                            
										   +"..?PGGPPBGBPGY???????????????????????JJ?!^:.\n"                        
									   +".:~777??????J5#BG???JYY?????????????????????????7!!^\n"                    
									  +"^!77!?5P5Y55Y?YBB5?????????????????????JJJJJJJJJJJJ5!\n"                    
									  +"..   Y#BGGGGJ?GB#Y??YY????JYYY55555PPPP5555PBG5555J^.\n"                    
										  +"^BBYY55J?PBB#J??JP555PP5YYJJY5JJY5P?77?YP5??J?:\n"                      
										  +"Y#57????YBBB#Y??JJYJJ????JY5GBP5J?J777YY5PPJP!.:^~!\n"                  
										 +":B#GJJ?Y5PBBBB5??????????YYJJJYPGGGGPPPPGPGJ?G5~~~7:\n"                  
										 +"7##BBBP5JBBB#G?Y!~!7????JJ????J?J55PPPP555YJ?YJ??JY~\n"                  
										 +"Y##BBBP?YBBB#G?Y?~^~!JYJYYYYYY5J?J?JJJ?????JJJ?????!\n"                  
									  +".~JG#B#BBGPGBB##GJ?5JJJ??J????7777?JJJJJ???????JYP5P?:\n"                   
									+".!YGGP#B#BBBPG#BB#GJ?YJ?7777777?JYY555J??????????????Y^\n"                    
								  +".!YYJJYYBB###BPG#BBBGPYJ????????J????????????????JJ?JJ?^\n"                     
								+":!JJ??????JPPBB#BPBBBBBPPPPPPPPPPPG5?????????????JJY5Y7^\n"                       
							 +".^7JJ??????????JYP555BBBBBPPPPPPPPPPPPPPPP5555555PPPPGGG7.\n"                        
						   +":~???????????????J?J???P5YP555PPPPPPPPPPPPPPPPPPPPPPGGGPPPP5?^\n"                      
						 +".!JJ??????????????Y5?????JJ??J??JJY5PPPPPPPGPPPPPPGGGGP5PGPPPPPGJ.\n"                    
						 +"7J??????????JJJJJ5GPJ???????????????JJYY55PPGGGGPP5YJJ??YGPPPPPPG!\n"                    
						 +"?????????JYJPJ~~PPPPGJ???????????????????JYYY555Y55YJ????YPPPPPPG!\n"                    
						+".?????????YYJJ?: JGPPGY?????????????????????JJJJJ?JJ????????5PPPPGY.\n"                   
						+"!?77???????????J~5GPPGJ??????????????????JYYYYY555Y55YYYYJJJYGPPPPG5^\n"                  
						+"?JJY????????????PGPPPPPYJ???????????JY555YYJJJJ?????JJJJYYY5YPPGPPPG!\n"                  
						+"!5YJ????????????5GPPPPPPPP5YJJYY55555YJ????????????????????????YGPPPP~\n"                 
						+"~YJ??J??????????PPPPPPPPPPPGPPP55YJ?????????????????????????????JGPPG5.\n"                
						 +":7YJJJ????????JGPPPPPPPPPPYJ????????????????????????????????????YGPGJ.\n"                
						   +"^!JJJJ??????J5GPPPPPPPPPY?????????????????????????????????????JGPG~.\n"                
							+".:7Y???????Y555YYJJ???YPY?????????????????????JJJJJ??????????PPPG~\n"                 
							 +"..^?JJ?????J??????Y5YJGPJ???????????????????J55555YJ??????JG#PPPPJ!.\n"              
								+".YY????????????PG5J5GPJ?????????????????????????????JYPGGGBPPPPG~\n"              
								+"^Y?????????????J5PGPP5BPJJ?????????????????????JJYPGBBGPPPBPPPPG~\n"              
							   +"^J???????Y?JY??????PBBBBBGGP55555555Y5Y55555PPPGBBBGPBPPPPPGPPPPG7\n"              
							  +"^Y????J??JJY5PY??J5GBBGBBGPGGPPPPGGGGBGGGGGBGPPPPBPPP5GPPPPGGPPGPGY\n"              
							  +".7J???J5PPJ??JPGPBGGPPPGBGPPGPPPP5555GPP555PP5555PPPP5GPPPGGPGGGGJ:\n"              
								+"^7JJ??JPGPPB##GPPPPPPGBGPPGPPGGP5P5PPPP55YYGPPPPPGGPGPPG#GGBGP7\n"                
								  +":7?JJ55J5#?~?PPPGGGPPPPPPPJ7^5P7.:YYPP7.^GPGPPPPPPPPPP?!!~~^\n"                 
									+".::.  J7. ^5GPPPPPPPPPGG~ .55.    :J?^?BGGPPPPPGBBP^\n"                       
										  +":. 5GGGGPPPPPGPPPPG~ ?5.     .  :!7PPPPPPPP5!\n"                        
											 +"?PPGGBGGGPPPP5JJ. ~!          .~PPPPPP5:\n"                          
											  +":^!PPPPPPPPP:              .?GGGPPPP5Y77!~^^^:..\n"                 
										  +".::...7GPPPPPPPGJ.            ^5PGGP5YJ???JJJJYJYJJ??!~^.\n"            
									 +":~!7!JJJJ?JY5PPPPPPPGG7            YGPP5??????J????J?J???JJJ?7!~~::^.\n"     
								   +".~YJJJJJJ???????JJYPPGPGJ           .JPGGPJJ??YYYYYJ????????YPYYPG5GGPY!\n"    
							   +".:~?J5JJYY??JJJYY?????J?^~~^.             :^!7??77^...:^J?JJJJ?Y5P55PBYYY!^:\n"    
							 +".7YYPBG5PPPPPPP55P5?????J~                                .:^^~~!!!7777^::.\n"       
							  +".:^^!?7???55JJJJYJ????!^.\n"); 
						break;
						
						case 2:
							enemyimage= ("\n" 	
								+"..........................................................................................\n"
								+"...........................:..............................................................\n"
								+".......................:::!J7~::::^^:::::.................................................\n"
								+"....................:~J????77??JYPP5YYJJJJJ?7!~::.........................................\n"
								+"..................~7?J?YYYJ??77777??JYJ77777??JJJ?!^......................................\n"
								+".................^Y?YPP5J???JJJJJJJ777JY?77777777?JJJ!^...................................\n"
								+"................~JY5J7~~~~~~~!!7JJ5PY77?YJJ?777777777?J?~.................................\n"
								+"............... JP?~^^^^^^^^^~~~!!7JPP?777?5777777777777JJ!:..............................\n"
								+"...............7J~:^:^^:::::^^^~~!!!75BJ777JY7777777777777?J!J7...........................\n"
								+"..............JJ^:^::::::::::^^^~~!!!!JBJ7775J777777777?7?JYBGP?^:.!J:....................\n"
								+".............?J:^::::::::::::^^^^~~!!!!JG?77P7777777????555G55GP5YJ55Y!7Y~.!:.............\n"
								+"............:Y^^^::::::::::::::^^~~~!!!!PY77Y5?7777??JJ5#5JYP5PPYYJJYY5YYY!JB!............\n"
								+"...........:Y~:^^:::::::::::::^^^~~~!!!!YG?77J5777?J5?JG5PYYY5PPP5YYYYYYYYY5BG?:..........\n"
								+"...........??:^^^::::::::::::^^^^~~!!!!!JG??7JJ77?J57?P5G?!?5YY5P55J?5YYYYJJJ5PJ..........\n"
								+"..........:5^:^^^:::::::^^^^^^^^~~!!!!!!5P??75?77JY7!YB5?~7JJP55P!~~~~755P5JJ55PY!~.......\n"
								+"..........~5:^^^^:::::^^^^^^^~~~~~!!!!7!GY??JY77?577Y#G!??YG7PY57~~~^^^~!!?YJ5YP5P7.......\n"
								+"..........!?:^^^^:::::^^^~~~~~~~!!!!!!!?P??J5777J5!?GJ5~!5BJJPPJ^~~~~:::::^!5P5JYP:.......\n"
								+"..........~?:^^::::::^^^~~~~~~~!!!!7Y??5??Y5?777J575Y!P?7?~~55G?~~~JPJ7!~^^~!75Y5?........\n"
								+"..........~5:^^::::::^^^~~~~~~!!!!JPJYYJYPY77777557P?!Y?~~~~!!!~^^!!!?55YJ?7?~7J..........\n"
								+"..........7B^^^^^::^^^^~~~~~~!!!7PGG5555Y?777777YP7P?!J57!!7^^::::J:!77~Y5YY?~!7..........\n"
								+".........:JP~^^^^^^^^~~~~~~~~!7YPPJYYY?777?7777755?PJ!7P!!7Y!~^^~^!7!~:^7~^^^7J?..........\n"
								+".........Y?57^^^^~~~~~~~~~~?Y5G5YJJJ?????7777777JYJJ5!7PJ!!!7???~?!~!!!!~~^:?5PP^.........\n"
								+".........Y7YY^^7~~~~~~~~~~7P55YYYJJ???J??777777775??G7!J5!!!!!!7!?7~~~JY7~^:J5?^:.........\n"
								+"........^57JJ!!7~~~~~~~~~~757?JYYYYYJJJ??77777777J5?5Y!!JJ!!!!!~~!7?7~!?7!^!J^............\n"
								+".........J7?J~~~~~~~~~~~~~?P7777777777777777777777?YJG?!!557!7?JJ?7~~77~~YJ?:.............\n"
								+"........:Y7757~~~~~~~~~~~~P5777777777777777777777777?JP!!!JPPYY5P555J7~7?J7...............\n"
								+"........JY77?57~~~~~~~~~~7B77777777777777777777777777J577?7PPYP5YYYPP55YY!................\n"
								+".......:~?Y77?P?~~~~~~~~~~P?7777777777777777????7777755JYPB#BGPYY55YYY#5..................\n"
								+"..........??7775Y!~~~~~~~~7P????J77?????????777?????????JJYYBB5PBP5GPPJ^..................\n"
								+"...........J5J??PY~~~~~~~~~~~^^^!JJ?77???JYYY5555YYY5Y555P55Y?7?P55PJ~....................\n"
								+"...........:5YYYG7~~~~~~~~~~~~~~^~!YY77??7??Y55JY5BB5YJ?77!!!!!!!77..:::^^^:.:::..........\n"
								+".......:^~!7Y5YYG?~~~~~~~~~~~~~~~Y5YPYYYY555PYJJJJPPY5YYJJJJJYJYY55JJYYYYYYYYYYYJ?~.......\n"
								+".....:Y55PP55555GY~~~~~~~~~~~~~~!G55YYYYYYYYYYYYYJJJJYYYYYYYYYYYYYJYYYYYYYYYYYYY555?~.....\n"
								+"......755PGGGGBBBG!!!!!!~~~~~~~~7BGPP555555555YYYYYYYYJJJJYJYJJJJJJJJYYYYYYYYYYYYYY5P?....\n"
								+"........:^^~~!!5GG?!!!!!!!!!!!!~!GBPPGGGPPPP5555555555YYYYYYYYYY5YYYYYYYJYYYYYYYYYY55G!...\n"
								+"...............:Y5PJ??7!!!!!!!!7!7JG555PPPGGGGPP5PPP55P55555PY5G?!?P?!755J5YYYY55555PB~...\n"
								+"................:YY555PYY55J?JYYJY5YJJ??JJJJY555PG5GGPGGGPGG~^^Y~^^~J~^~Y~~YP5555555GP....\n"
								+".................:YJJJJJJJ?JJJ?7?Y~~?YP5JJJ??7777JJ^!!7JYGG?~~~P7~~^Y7~~?J^!BPPPPPPGB!....\n"
								+"..................?GYJJJJJ?777?7JJ....~BP5YJJ???77P! ....!5?7?5J!!!7Y!!!J?!?BGGGPPP5!.....\n"
								+"..................!PYJJJJJ????7?J7.... Y5YYJJJJ?JJP^......:~!!JJ??JPJ?J5J?JJ???7!~:.......\n"
								+"...................~!JP5YYYJ7?YJ5:....~?55PJYYJYGGJ^........ ..:^^:^!!~^~~^...............\n"
								+".................!??7?Y7YJ?YYJ?:^....^PJY5P7?JYP5YJP^.::::^~!!~^..........................\n"
								+"................:B5JYPY?777J5?!:.....:?P5YYYYYYYYPGP?Y55Y55555YYY?!:......................\n"
								+".................?#P5YYY55Y5Y5G!.......PP55555PGGP5Y55Y55YYJYJJJJY5YJ!:...................\n"
								+".................?P5PP555PPPBY:.......^PYJ5PP5P5YJJ55JYGYYJYYYJJJJJJJYY!..................\n"
								+".................J55PPPPPPPGB~::......!GY5BP555YYJJ55YGG5YYYYYYYYYYYYYYP~.................\n"
								+"................^PYY55PPPPGGGGP5Y7~^:..~?YPPP55PPPPGGGG#GPPPPPPPPPPPGGPJ:.................\n"
								+"...............:5JYPPPPGGP5YYYYJYYYYYY7~^:..::.:^~~~^~~~~~!7????777!!~:...................\n"
								+"...............!GPPPPPG5YJJJJJJJJJJJJJYYYY?:..............................................\n"
								+"................7#P5PP5JJJJJJJJJJJJJJJJJJJG~..............................................\n"
								+".................!5BPPP5YYY555555YYY55Y5PGJ...............................................\n"
								+"...................!J5PPPP555PPPPPP5YYJJ7^................................................\n"
								+"......................::::...::::::.......................................................\n"
								+"..........................................................................................\n");
						break;
						
						case 3:						
							enemyimage= ("\n" 	
								+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
								+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
								+"&&&&####&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
								+"&&&&####&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##BBBBBGGBB###&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
								+"############&&&&&&&&&&&&&&&&&&&&&&&&&&##GGP55YYYYYYY5Y55PGBB#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
								+"###############&&&&&#########&&&&&##G55YYYYYYYYYYY555Y5555PPPGB#&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
								+"############################&&&#G5YJJJYYJYYYYYYYYYY55555555PPPPGGB#&&&&&&&&&&&&&&&&&&&&&&&\n"
								+"##########################&&&B5?77?????JJJJJYYYYYYYYYYY55555PPPPPPPGB#&&&&&&&&&&&&&&&&&&&&\n"
								+"#########################&&BY?777????????JJJJJJJYYYYYYYYY55555PPPPPPPGB&&&&&&&&&&&&&&&&&&&\n"
								+"########################&#5????77?77??777????JJJYJJYYYYYYYYYYY555PPPPPPG#&&&&&&&&&&&&&&&&&\n"
								+"#########################J777????7????7777777??JYYYYYYYYYYYY5555555PPPPPG#&&&&&&&&&&&&&&&&\n"
								+"########################J7777????77??77!!77?JJYY5555555YYY55Y5P55PPPPPPPPGGB&&&&&&&&&&&&&&\n"
								+"#################&&###&P777????7????777!7?JYY5PPGPPPPGPPPPPG5YPGPGPPGGGGGBBGB&&&&&&&&&&&&&\n"
								+"###############&##B##&#J7777?7??????7!7?JJYPGBBBBGGGGGGGGGGP55PBGGBGGGBB##BBB&&&&&&&&&&&&&\n"
								+"##############B555YY5PP5J7!77???777777JJYPB###BB#&BBBBBGBGP5PPGBBGBBB##&&&##B&&&&&&&&&&&&&\n"
								+"#############P5PPYJY5YJ5P?777?7777!7??JYP###B5JG##BB&#BGPPPPPPPGBB##&#GB&&&#B#&&&&&&&&&&&&\n"
								+"############G5PJ?JY55YJJY7777777!!77JJYPG##P555PGB##&#BGP5YYYYPGB#&###BB&&&#BB&&&&&&&&&&&&\n"
								+"############5P?7?????777?777777!!!7???Y55P55555PPGB#BGGPP5YJJY5PG#&#####BBBBBB#&&&&&&&&&&&\n"
								+"###########B55!!7J5GG5!~777!777!!!777?JJJYYY55PPPGGPPP5555J??JYYPGB#BBBBBBGGGGG&&&&&&&&&&&\n"
								+"############Y5!!YPB##P!~!77!777!!!77777??JJYY55PPPPPPP5Y5Y?77??J5PGBBBBBGGBBGGGB&&&&&&&&&&\n"
								+"###########GJ5?~JGBBB5!~!!77777777777777!?JJJY555PPP55YYY??777?JY55GBBBBGGGGGGGG&&&&&&&&&&\n"
								+"########BPYJ?JJ7!?5GBBY!777777777!!777777????JYY5P55YY5YJ777!!7?JY55GBBBBGGGGGGG&&&&&&&&&&\n"
								+"######G5JJ???7??7!!7JJ?77!7777?777777777????JJJYPYJYY5Y???J7!!!?JYY55PBBBGGGGGGB&&&&&&&&&&\n"
								+"###BPYJJJJ7??????77???JYJ!!7777777777777???JJJYYJJJYY5J?J5?!777?JY5PPGGBBBGGGGB&&&&&&&&&&&\n"
								+"#B5YJJJJJ?????????JJYY5GJ7!7777777??77777????YJ????YYJ??Y57JG##PY5GGB#BGBGGBGG&&&&&&&&&&&&\n"
								+"5YJJYJYJJJJJ?????777?JJJ?77777777??7?77?????J?77???JJ77?YGYY#@&B5PB#&#GGGGGGBB&&&&&&&&&&&&\n"
								+"YYYYYJJYYJJJJJ???7777777777777????77?????77?777?????77??JGGPGBBPPBB##BBGPGGPG#&&&&&&&&&&&&\n"
								+"YYYYYYYYYYYYJJJ??7?777777777777?777???J??7777????777!??J5P555Y5PGBBGGGGGPPGGPB&&&&&&&&&&&&\n"
								+"YYYYYYYYYY55YYJJ???77!77777!77777????JJJ?7777???7!!777?Y5JJYYJJY5PPP55PPGPPGGG#&&&&&&&&&&&\n"
								+"555YYYYY5555YYJJ?????77777!!77777?7??JJ???7777?!77777?Y5???JJJJJJ5PP555PPGGPGGBB#&&&&&&&&&\n"
								+"55555555555YYYYJJJ?????7777!777?7????????77!!7777!77?Y5???????JJ?J5PG55PPPBGPGBBBB##&&&&&&\n"
								+"5555555Y5555YYYJYJJJ???7777!!7???77??????7!!!!77!!!?J5?7???7??JJJ?Y5PP5PPGPGGPBBBBBB##&&&&\n"
								+"55555YYY55555YYYYYYJJJ?7??77!7?777??7????77!!7!!!77JY77!77????JJYJJYPGPPPPPPGGBBBBBBBB#&&&\n"
								+"555555YYY55555YYYYYYJJJ???7!77777?7?777?7!!!77!!!?JJ7777!777?JJJYJJY5PPPPPPGGGG#BBBBBBB#&&\n"
								+"YY55555555P55555YYYYYYJJ??7!7!!7777777??!!!!!!!7?J?7!!!7777???JJY5PPPGGGGBGGPGG#BBBBBBBB#&\n"
								+"5555555P5P55555555YYJYY?J??777!777!7777?!!!!!!7JY7!7!!!777???YPGB#####&#&&&#GGG#BBBBBBBBB#\n"
								+"55555Y55PPPPPP55PP5YJYYJYJ??!!!777!777??!!!!7?YJ777!!!7777?YG#&&&########&&&&BGB#BBBBB##BB\n"
								+"JJ55555PPPPPPPPPPP55YYYYJ?JJ7!7!7!7777?J~!!!7?J777!!!!7?YPB##BBBBBBBBBBBBBB#&#GG#B########\n"
								+"7?Y5PPPPPPPPPPPP555555YYY5YJJ7!!7777777Y~!!!!?77?77777J555YJJY55PGGGGBBBBBBBGBBGBB###BB###\n"
								+"77?J5PPPPPPGGGPP555P5555YYP5JJ?7777!777J7!!!!?7777??J??7???J5PGBBBBBBBBBBBBBBGGBBB###B####\n"
								+"7777J5PPPPPPPGGPPPPPPP555YYY5YY?7?J?777?J!!!7777777J??????Y5GBGBBBGGGGGBB####BGGB#########\n"
								+"77777JY5PPPPPPGPPPPP5PP555YJYPPY?7???777?J7!7??7???????JY5PGGP5555Y55PGPGGBBBBBB##########\n"
								+"7777!7J5PPPPPPPPPPPPP555555YYJYPPJ77?J?77?J?7???7?JJ??JY5PP55YYYYY55PGGPGGGGBBB######B####\n"
								+"7777!7?YYPGGPGGGGPPPP5555555YYJYPP5J???????JJ?????JJYYYY5YYYYYYYY5PPGGGGBBBBBB######BBB###\n"
								+"777!!77?YYPGPPPPPPPPP555Y55YYYYYJYPP5JJ?????JJJJJJJJYYYYYY55555PPPGBBGBBBBBBB####BBBBBBB##\n"
								+"77!!!77!7J5GGGPPPPP555555YYYYYJYYYJYPP55JJJJJJJYYYYYY55PPPGGGGGGGBB##BB##BB#BBBBBBBBBBBBBB\n"
								+"!!!!777777J5GPPPPP55Y5YYYYJJYJJJJJJJJY55P5YYYYYYJYYY5PPGGGGGGGGBBBB#####BBBBBBBBBBBBBBBBBB\n"
								+"77777!!7777J5PPPP555YYYYYYYYYYJJJJJJJJJJYY5PP5YYYYYY55PPGGBBBBB#########BBBBBBBBBBBBGBBBBB\n"
								+"777777777777Y55P55555YYYYYYYYJJJJJJJJJJJJJJJY55PPP5555PGGGBBBBB######BBBBBGBGGGGGGGGGGGGGB\n"
								+"7777777?777!?YY55555YYYJJJJYJJJJJJJJJJJJJJJJJYYY5PPGGGGGGGBBB#####BBGGGGGGGGGGGGGGGGGGGGGG\n"
								+"7777777?7!777?JJY555YYYYJJJYJJJJJJJJYJJJYJJYYYYYY55PPGGGBBBB#BBGGGPPPGGPPPPPPGGGGGGGGGGGBB\n"
								+"77777777777777JJJ5P55YY5YYYYYYYYYYYYYYYJJJYYYYYYY55PPPPPPGGGGGPP5PPP5555PPPPPPGGGGGBBBBBBB\n"
								+"77777777777777?YYYPPPPPPPPPP55555555YY5555YYYY5Y555555PPPPPPPP5Y55555555PPPPPGGGBBBBBBBBBB\n"
								+"777777777777777YYYPGPPPPPPPPPPPP5PP555555555PP5555PPPPGGPPP5GP5YJYYY555PPPPGGGGBBBBBBBBBBB\n"
								+"777777777?77777?YYYGGGPPPPPPPPP555P5555555P555PPPPPPPPPPPPPPP5YYYYYY5555PPPGGGGGBBGGBBGBBB\n"
								+"7777777777777777JYYPGPGGGPGP555555555555PP555555PPP555PPPPGP55YJYYYYYYY55PPPPGGGGBBGBBBBBB\n"
								+"7777777777777777?JY5GPPPPPP5555555P55555555555YY555PPPPPPPP55YJJJJJYYYYY555PPPGGGGGGBBBBBB\n"
								+"77777777777777777JYYPPPPPPPP5PPP55555P555P555Y555555555PP5555YYJJYJYYYYY555PPPPPPGGGGGBBBB\n"
								+"77777777777777777JYY5PPGPPPPPP55555P55Y55555YY5PP555555555P55YYJJYYYYYYYY55PPPPPGGPGGGBBBB\n"
								+"77777777777777777?YYYPPPPGGGPPPPPPPP5P5555555555555555555555555YYJJJJYYYY5555PPPGGGBBBBBBB\n"
								+"??77777??777?777?7J555PPGGGGPPPPPPPPPP5P55PP5555555PPPP555P555YYJJJJJJJYYY555PPPPGGBBBBBBB\n"
								+"????????????77777??Y55PPPPGGGPPPPPPPPPPPPPPP55555PPPPPPP555PP55YYJJJJJY55YY55PPPGGGGBBBBB#\n"
								+"???????????????????Y55PPPPPGGGGGPPPPPGPPPPPPP55555PPPPPPP5P5P55YYJJJJJJYYYY5555PGGGBB#####\n");						
						
						break;
						
						case 4:
							enemyimage=("\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~^^^7?~^^^^~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~^7B5^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~~!7!!?7!!??7!!~!!~~^^!^^^^^7BJ:^7##!^^^^^7~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!7?7?JJJJYJJY5Y55YJYJ?YY5BG?~^~J&#5J5##~^^~JBY~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!??JJJJJ?7??Y55PPPPGGGBB####B##GPGPGB#BB##GYJ5&5^^~^~^Y7^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~7!!7Y5Y5YYYJYYY5PGGGGBBB##BB#&#BBB#&#&&&&&&&&&BB#&#~^~~!^7&Y:^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~7!777?JJJYY55P55PPPGGGB##&@@@&#GB&#B#&&&&&&&&&&&&&&&@#?~~!!~B&P^^^^^^^^^~~^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~7!!?5YJ5PYPPPGGPPPPGBB#&&&&&&@@@@&B##5#@@@@@G#@&&&&&&&@@@#GPG##&B^^^~^^~^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!!YJ?5JJJY5PPGGGGGGGG#&&##&&&@&&&&@@@&B&&@@@@@@&@@@@&&&@&&@&&#####&&Y~^^^~^^^~JJ^^~^~^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~777?YG5PP55Y55PGPGBB###&###&&#&&&&&@@@&#&##&&B&@&&@@@&&&&&&@&GGGGBB###B5~^~!75#B!^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~777?JYY555Y5PPPPPG##&&&&&&&&@@&&@&@@@@@&&BGG&#7#@&#&@@@@@@&&&&#GGB#BY7!!~!JJ5B&B~^^~^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!7!JYYYY555PPP5PPPGB#&&&&&@@@@@@@&&@@@@@@#G5?~?G5?B&&&&&@@@@@@@&&&&B##PYJJ?YP55B&#~^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!~!!7JYY5YJY5555G#GGB#&&&&@@@@@@@@@@@@@@@@@#5P##B#&#YG@&&&&&@@@@@@@@&@&##BP55PPB#&&@J^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!!~~!77?JJJYJJY5PPGBBB#&##&&&@&@@@&&@@&&&@@&PYB#@BPG#&BGP#&##&&&@&#&@@@@@@&#&#5GG##&&@@&#BBB?^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~7!^~!77??JJJJYY55PGGGPGGG##&&&@@@&&#B#####&@#5PPP@&YJPP5PGBGGBG&&&&BGP&@@@@@&&#&&B&&&&&@@@&@&5!^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!^^77????JJYYYY55PPGGPGGGG#&&&@@@&BGPPPPGB#&B5PGBB&@&#P55PGGPP#&&&&&#BP#@&&&&&&&&&&&&&&@#B#&#!~^^~^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!:~??JJJJJYYYYY55PPPPPPGGGG&&&&@@@#PP5555PPBP5GGGBB&@@@#PPGGGGG&&&&&@&#BB#&&&&&&&&&&&&&&G?!7?YGJ^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^!~~7??JJJJYYYYY5555PPPGGGG#&&&&&&@@@#G5555PGGPGGGGGBB&@@&BGGBB##B&@&@@&&#G5G&&&&&&&&&&&&&BP5G#&Y^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^~^^^?J~?7J??JYYY5Y555PPGGG###&@@&BB#&&&@@@#G5YY5PBBGGGBB#&&&@#BGG#&Y&&@&@@@&B#BP##&&&&&&&&&&&&&&&@&7^^^~^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^!7!7BJJJ5YY555PGGGGP5Y55PPG#&@@&PGB#&&&&&@@&&#G55B###BB#&#&@&&##&&Y&@@&&&&@&&##GPB&&&&&@&&@&&&&&&&&G!^^~~^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^~^~7!!!JG#PGGGGB##BPY?777777??5G&&&&#PGP555PGGGB#&@&P5G&&#&&&&&&&#####G&@&&&&&&&@&&####PG#PG&GG&#B#&&#BGJ^^~^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^~^?7!!555Y?5##B&&G5YJ?77!!77?JPB&###&#GGG5YJJJJJJY#@&YPB&&&&&&&&&#BBG&B@@&&&&&&@@@@@@@&&#&&#&&#&&##B&G^^^~^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^~77YG7!7?P#BB#BPYJ???7777?J5G#&#BB###GGG5YYJJJJ?&@YY5PB&##&&&@&BGPB&&@&&&&&@&&&&@@&BGBB###&&&&&&@@&&5^^^~^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^~!JG5YY55B###BGPYJ?J????JJJ5B&&#BBB#&#GGPYYBG5G&&5YPPPPP#&&&&&&BGG&&@&&&&&&&&&@@@G???JY55PGG#&##&@@&&Y^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^~^!7?YGGP55Y5G#&#BGP5YYJJJJYJ55B&&##BBB&&&#GGP5PGB#B5555PP5B&@&&&&&##&&&&&&#7G@&@@@&#G5YJYYYPPPGGBBB&&@@&#~^~^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^!7?YGGBG5PPPPPG#&#BGGPYJJJJYYP#&&&#B#&&#GPB#BGGP5555PPPPBPGB#B&&##&&&&&&&&J^^G&@@@&#BBPP55Y55PPGB###&&&&&P~^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^~^^!7PGB##G55PBGGGGPB&#BGG5YYJJ5B&&&##B####GGPPG#&#BGGGPPYYYP5GGPBGB&#B&###&&#~^^^J&@@&BBBBBBBGGB#&#&&&&&&&&&&#G?^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^~7YPPPGGGBY5##GP5P##&&BBBGGGBB&@&&BP##BG########&&#GP55555PPGB#BY#&&&BGBB##&5^^^:J#B&##BBBGB###&&&&&&@@@@@@@@@&Y^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^~!7~^YB#&&@&#&@##&#5?YG#&#5?J?7^!G&&#&BB#GB#&&#BGBBP5555PPPPGB&#BGP###&#GPBB#&&!^~~?77J5?B&&######&@@@@@&&&&@@@@@@G^^^^~^^^\n"
							+"^^^^^^^^^^^^^^~^^!7GG#&&##&@&B#&&##&@@&BB#&G~^^^~~~~Y&@BG&&#&&GPBB5Y55PPPPPG#&&&##&#&&&&&G&&##&&BYJ??JJ5JP#&###&&@@@@&&&&GGB&@@@@@@@B!^^^^^^\n"
							+"^^^^^^^^^^^^^^^~^!P&&#&&&#&&&B&@@@&&@@#@@&#&B~!5B##G5#&&#&&&@P5B#55PGPPPG##&&&####&&#BB#5G&&&&&&&&&#J?!7#&###&&@@@&&#&&&&&&@@@@@@@@&5?~^^^^^\n"
							+"^^^^^^^^^^^^^^~^^JGBBB&&&&&#G&@@@@@@&@@@@@@#&##&&@@@@@@&&&&&&#GPBBBGGB#&&&##BGPPGGGGGGGYGBB&@@@@@@@@@#GG&&##&@@@&#&&&&&&&&&&@@&&@@@@@@&B^^^^\n"
							+"^^^^^^^^^^^^^^^^:!GB###&&##&&@@@@@@@&@@@@@@@#&@@@@@@@@@@&&&&&&&#G#&&&&&&#BGPPGB#&&#&&#####@@@@@@@@@@@@&@@&&&@@&&GB@@@@&&&&&@@@@@@@@@@@@5^^^^\n"
							+"^^^^^^^^^^^^^^^^!G&&B&&##G##@@@@@@@@@@@@&@&@@&&@@@@@@@@@@@@@@@&&&&GPB#&#######&&###@&G#&@@@@@&@@@@@@@@@@@@&@@@&#&&&@@&&@@@@@&&&&#&@@@@@@P~^~\n"
							+"^^^^^^^^^^^^^^^~G&@@&B&&B###@@@@@@@@&#@@&@&@@&&@@@@@@@@@@@@@@@@@@@@&&&##&&&&&#&&GB#&&GG#&&@&B#@@@@@@@@@@@@&&@@&&#&&@@@@&&&&&&@BP#&@@@@@@@Y:^\n"
							+"^^^^^^^^^^^^^^^^!5#@@###B##B#&&@@@@@@#&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@BB##@&##&&###&&###&&#G#@@@@@@@@@@@@##@@@&&&#&@@&#&@&&&&&&@@@@@@@@@@&BP\n"
							+"^^^^^^^^^^^^^^^^^^^Y@&BB###B#&&&@@@@&##&&&&&@@&@@@@@@@@@@@@@@@@@@@@@@@@&&##&#@##@&B#@&&&&&@@@@@@@@@@@@@&&BB@&G#&@@@&&#&@@&&&&&&@@@@@@@@@@@@#\n"
							+"^^^^^^^^^^^^^^^~^^^7&&##B#&###&&&&&&&#&##&#&@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@&@&#&@@@@#@&@@@&@@@@@@@@@@@@&&&GYJ!^G@@@##&&&@@@@@@@@@@@@@@@@@@@G^\n"
							+"^^^^^^^^^^^^^^^~^:7B#&&&G&@####&&&&####&@&&@@@@&&@@@@@@@@@@@@@@@@@@&&&@@@&&@&#@@@@@&#&@@@@@@@@@@@@@@@@@@#GPPYJ7&@&###&@@@@&&&&@@&BPG#@@@@@B^\n"
							+"^^^^^^^^^^^^^^^^^^G@@&&&@&##&@@@@@&&##&&&@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@&&&@&#@@@@@@&&&@@@@@@@@@@@@@@@@@@@&&#B5^7&&&#&@&&&GPB&&&@&BB#@@@@@@&^\n"
							+"^^^^^^^^^^^^^^^^^^~!B@@#&&##&@@@@@@@&@@&@&#5?7G&&@@@@@@@@@@@@@@@@@@@&&&&&&@&@@@@&&&&@&&@@@@&&@@@@@@@@@@@@@@@&BJ!~^J&@@&#&&&@@&&&&@&@@@@@@@#?\n"
							+"^^^^^^^^^^^:^75PBGBB&&&###&#&&&&@&@@@@&#G5Y?775BB#&@@@@@@@@@@@@@@@@@@@@@@&&@&###&&&&&@@@@@@@&&@@@@@@@@@@@@@@@@&B5?^7GB&&&@@@@@@@@@@@@@@@@@@@\n"
							+"^~!7777?JJ5G#&&&&&&&@&###&&&##&&@@@&&#BB#&&&@@@@@&&&@@@@@@@@@@@@@@@@@@@@@B#@@@@@@&&&&&@@@@@@@@@@@&&&@@@@@@@@@@@&BGY~^:Y@@@&##&&&##&&@@@@@@@@\n"
							+"^?#@@&&&&&&&#&&&&&@@@G~YB#&&&#&&&&##B#&&@@&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@&&&@@@@@&#&@@@@@@@&@@@@@@@@@@@@&#BJ~:Y@@#BBB#######&&@@@@@@\n"
							+"^^^7B&@@@&&##&&&&&&&7.!PBB########&&&&@&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@&#7:#@##BB######&&&&&&@@@@\n"
							+"^^^^^!5&@&##&&&@&&&BB&&&&&&&###&&&&@@@&&&&&&&&&@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@BJ&&##&&&&&&&&&&&&&&&#&&\n"
							+"^^^^~^^^Y&##&@@@@&@@@@@@@@@@@@@@&&@@@&&&&&&&&&@@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&###&&&&&&&&&&&&&&&&&&&\n"
							+"^^^^^^^^:J&#&@@@@@&&&@&&&&&&&&&&&&&@&&&&&&&&@@&&&###&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#&#&&&&&&&&&&&&&&&&&&&&&\n"
							+"^^^^^^^^^^7B&&@@@@@@@&&&&&@@@&&&&&@@&&@@&&&@@&&#&###&###&##&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&@@&@&&&&&&&@@@&&&&&&&\n"
							+"^^^^^^^^^^^~JG#&@@@@&#&&&&@@@&&&@@&&&&@@@@@@&&&&&&&&##&&&&&&######&&#&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&#B&&@@@@@@&&&@@@@@&&&&&@@\n"
							+"^^^^^^^^^^^^^^^~!7?B@&&&&@@@&&&&&@@@&&&&&@@@@&&&&&&&&&&&&&&&###&&###&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&#PJ!^?B#PPGB#&@@@@@@&&&&&&@@\n"
							+"^^^^^^^^^^^^^^^^^^^~G&@&&&@@&&&&&&&@&&&&&&&&@@&&@&&&&&&&&&&@&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#P55!:^^^^~7!J5GGG&@@@@@&&&&&&&@\n"
							+"^^^^^^^^^^^^^^^^^^^^^!Y&&&&@@@&&&&&&&&@@&&&@&######&&&@&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B^^^^^^7?J5B#BG@@@@@&&&&&&@@\n"
							+"^^^^^^^^^^^^^^^^^^^^^^.5@@&&@@@@&&&&&&&@@@@&GPPG#J!??YGBG#&&&&@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@G~^^^~?YY5GBBB&@@@@&&&&&@@@\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^J#@@@@@@@@&@@@@@@&BGGBBBP?77JJJJ5GG#GY#&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@7:^~!^7YJG#B&&&@@&&@@&&@@5\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^7YPB#GG##BBB###BB###BGGGBBGGBBBGBB5G#55J#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@#7^^~!YBBB#&&&&&#7YG###&5:\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~7?!~~~!GBGGP5YYPPPGGP5555PGBG#J~!~G@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@G!^^~!7Y&&&&&5?J5GB##&P^\n"
							+"~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~^^^^JG5YYJ7!~!!7?JJJJY555PP#^^^^P&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B7^^^^75G#J75B#&@&BY~^\n"
							+"J!^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^!G5J7~~~~~~!!7?YPP5YJYP#~^^^^~5&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B7^^^^^J!5#&&@G!^^^^\n"
							+"5J~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^YPJ!!!~!!!!!777?JJ5PPP#&!^^^^^^~!7B@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#J~^^~JB&&&P^^^~^^\n"
							+"??~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Y5Y?!!!!~!!!77???JJYP#&&J^^^^^^^^^^^#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&G~^^~?P&P^^^^^^^\n"
							+"~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^:YGPJ??!~~!!!!!7???JP5Y5B?:~^~^^^^^^^^^&@@@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&#BPJ7~~~^^^^^^^^^!^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^7GP77J!~777!!!7??JJY?Y5PGP^^^^^^^^^^~~^7&@&&#BBB&@@@&&G5&@@@@@@@@@@@@@@@@P?J7!!~^7P5?7!!!!~^^^^^^^^^~^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^!55J?7YGJPG5JJ?77?J5JJYYYY5P5!^^^^^^^^~~!!75PGGGBBBBBBGGGPP&@@@@#BB#&@@&&#Y?JY??7~^~?YY55PGGBY^^^~^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~?5PY77JGG#&GJJJJJJJ5GJJJ??7?JPG5^^^^^^^^^^^^^^^^^^^~~~~~~~~~!!P#BGGGGGPPGP55GGBBGP5YY?7777?P&&B7~^~^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^!PJ7!77!?B&&P?777!7YP#BYJ?77?JJ5YP?^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!7J5PGB###BGPG######BBGP5YJ7^~~^~~^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^:55J?JJYP#&#&G5YYJJYP#&BPY55Y5YYY5#G~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~~~^^^^~~!!!!~~~^^^^^^^^^^^^^^^^^^^^\n"
							+"^^^^^^^^^^^^^^^^^^^^^^^^^^~~?YPGGGGB#P?!?P#&&#&&&BG###BBBBBB#&&Y!!~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
						break;
				}
			case 2:
				numberOfCases=(int)(Math.random()*4+1);
				switch(numberOfCases){
					case 1:
						enemyimage= ("\n" 
																																				+".  .\n"                                                                                                                              
																																					 +".:.\n"                                                                                                                          
																																				+"~J?7^:~7~:.\n"                                                                                                                       
																																		   +". 7J?^~JYPY~~7~:^:\n"                                                                                                                    
																																		   +". ~J77!~!?P!^~!^.:!:\n"                                                                                                                 
																																			+"^^?7!7JJJ5P7~PY^: 7^\n"                                                                                                              
																											  +"..:::.                        .^.777?Y5PPGY!J5J^.?:\n"                                                                                                           
																										   +".::^!777?7!:                        .^^:?5P5YPY7YP5JYY.\n"                                                                                                        
																										+"...:~7JYYYJ55GG^                      ..:!:!PJ?JYYJ7!!!!7!.\n"                                                                                                     
																								   +"..:::::~!5J!77~JPPBB5J!                   :.::7^^?~~!7JY?~~::^7~\n"                                                                                                               
																								+".:^^^!77!!!7J5PJ!7YPGGGJ5GJ~:                ::^~?~^^^:^!JGBGP55PGG7\n"                                                                                                              
																							  +":~^:::^!?J?7~~7GBGP?7?5BYY5PYJ~             .~Y5!~YGY7~~77JPG55PPP5GB#P~\n"                                                                                                           
																							+":~~^:^^^~7P55YJ?55YPJ!!~!G5YYBPP!       ..^~^^!YBP?JGP##PY5PGYJJ77JJJ5GBB5!:...                          ..:^^~7PJ!:\n"                                                                  
																						  +".!~:::^~7?7P5J7!7?JYPP?77!5GGGP5###P!::^7YPPBG7~JG#5YGYP##&#P5Y5?7?!~?YYB5..^~~!!7?7!^:                :!^~~!~777?GGBP5Y~.\n"                                                              
																						  +"^^..::^!JYYP5YYJ?5P^~5P5YYYJ&#PY5BBPYJ???J5PP7!?BBBPP5YB#GB##GYJ?7JY?7JP#Y..^^^!!7JP555YJ7~.          !GG7~!7777!YGG5PGPGB?                              ...\n"                            
																						 +".:..:.^?Y5PYJ?Y5~~7J::.:~?Y5##P555J!^~!!7?J?YJ~7JB#GP5PG##BPG###BG5JJY5Y5P#G7^~~!?JYYPPPPP5GGJ:      .?PPG5!!7?777Y555555P##7                          .::::\n"                             
																					   +"...:::^7JYP55?~^::^!!?:^7::^P&&BGGPJ!~~~7777!77Y~7J?BYJ5P#&#BP55G#BB#G555PPPGB#BG5JJJJJYPP55PPB#GP5!^.:5G55G5???YP#&&#P55Y5BBB#!                 .::^^!7:~~~~\n"                              
																					 +".:^!7?Y5J7!?5PY?7!!!7JYG~Y@#J::7PB#GJ7!!77!~!7?7!55?JJY?77?YB##G555PBBB#BPGPGGGGGGGBBPYYYJY55YYPB##B&##BY55PJPGP#&@@@@@@@&#5B#BGGP:            .^!7!!!7JJ?777^\n"                               
																				   +".:~7J?YJ?7!!77?5PPGBB#&&#B~G@@@&?!!!7?JJ?J5?!!??JJ?JPG5YJ???J55B&#YJY5BBGBBBBBGGGGP5YJ??77??JYYYP5G#&&&&&5?YY5YYG#&&GB@@@&&@@#B#GGGPY.      .  ^?YYJ???JYJ???7 \n"                                
																				+":7?P5???Y?~^~!7?Y5P5GB#&#GPP5J#@@@@#7~~~^^^^!JYJJYYPG555P##PYJ?777Y#BJ?J5GB#####P5#&&&PPB#GY7^^7YYY5PPG#&&&PJJJY55JP#P?P&@@@@#GPB##BBP5P7    :?J :5P55YYJ?7!!~^:.\n"                                 
																			   +"^~..@&!7?5JJY5PPYBGG##&#GJ?JYBPP@@@@Y!!7!~^:::^~!7JPGBBBBGG&&#GP?7JJYGP5BB#BBB##BJJ?B#P55#&&#GGY?YGBGPPGB&&#77JY?Y5Y5G5&@@@@@@@&GBBBBGPP5Y. !PBGPJ5P5P5YJ?7!^:.\n"                                    
																				  +".J~~7?P?^~7Y?G#####GY?J?JPG..G@&7.:7!7!!~^::^!!!?Y5PGG##&&#GBB5Y55#####B#P55PY?7JYGP5PGGGB#@@#55B&&BB##&P?77YYJ5YPGG&&@@@@@@@#B#GBGPP5?7P###GGGGPPPY~:..\n"                                        
																				+".::.:~7J!    ^~GGGBG5YY5YJ5?. :^G~.:^^!?7????????YJ~:~5BBBBBB5B##G5P&#BBB&&#PY5Y5P#5##G&G55PBBBBBGP#&&&&&#??J7?5Y5PPGG#######B@&#&B#BGPYJ5#####BBBPP5!~7:\n"                                         
												 +":.                           .~~^:^^!!:  :?5PGGB&#G5YYYYP#Y .~~!: .::^!JPPPGG5Y5Y~ ..^?5GG#####&#PG#####&&#5##GPJPB##B##BGYPBGB##GB&&#&@&YJJY?JY5GPGGG&&BGGGB@#B###BBGP5?#&##&##BGBBBP!\n"                                          
												 +"!!^:.                        7J?!~^:. :75P5J5P##G5JJJPGB#&7. .7?^:..::?PPGGGGBPJ?!^..:::^75#@@@@&BB###B&&&BP&@B??YPBB55B#B##&##&&####BB&@&&57YY55GGBG5&&BBBB&&BB####BBGB#&##&&&&##G!:\n"                                            
												 +"7!7!!!~:.                   :7!^.   ^?PBPJJYGG5YJPBGPPG#&@@57?7. :~J5P5GGGGGB@&##GGJ!~:^^^^:!G&@&&#&#BG&@@&5#&#GJY5B#G5G####BBGBB#&#B5YB@&&&GY555GGBG5#&#BB&&&G#&####B&&&#&&&&@@@@&J.\n"                                            
												 +"^J?JYYYJ?7~:.                    .~?YGGPJ?YG555PB#G5JYB&@@@@&5?:~~^JGGP5PBG#&&@#B&&@&@&@@@&#J!!?P&&&&GG#&BGGBB&##GP#B####BGGB#B&#&&#PJJG&&BB#GPY5GGGG5G&GBGB##G#&#&##&&&&&&&&&&&&@@@&5:\n"                                          
												  +"J555555555YJ?7!~^:             ^YJP#BGYYPPPY??GBP5YJ5#@@@@@#GP^:!?JY555GB&&&&#BG&##&#&B@@@@BGY?!7&@@#PPPB#&&#####B##&&#BGGB###&&&B#P5P#&#PB&@G5PPGGG5YBPP55PGPB&&&#&&&&@@@@@@@@@@@@@@@P:\n"                                        
												   +":75PPPPGGPP5555YYJ?J7~^:..^7~7J5GBBBGPGP7:.  .JBPP55&@@@&PYPB5~7J~?5PPG#@@###B###B#BPG@@@#5BGP5G@@@@B??G&&&B#&&&@&&&&&#####B#&&#&GPG##&B#&&&BPG5G555YGPPP555GG#&##&#@G:!JP&@@@@@&@@@@@@5.\n"                                      
													 +".!5GB#BBGBGGGGPGGGGGGGB#GY!J75GBBBB##?  .  ..:~?PBB&&BPP#&@?^:^?JY?JPP&@@#B#B##&GGPG&&G55GGGG&@@@@@#?!JGB&&&&BB#&&&&&&&###&&&&####B#&&&&&BPP5YYJYY5GGBBPPPPGPB##B&@P    .!B@@@@@@@@&&@&~                           !\n"         
														+"^YB##B#&&&##BB#BB#&#Y!?JYYP####&@&B~  .!J7^.:~7YB##&@@&5:~!?7YJJPG#@@@B##B##&#5#B5JYJ5GB##&@@@@@@@B5PG#&&&&#&##BGPGBB#&&&&&&&&&&&@&&&B55J?77?J55B#&#GGPJY5PB#B&@G:      ^G@@@&@@@@&@@Y.                         ^5:\n"       
														  +".~5######GGB##&&G77?J5G55&&&&&5^::.. :P#GJ!7!~:~!!?Y777?!^?P??J5G@@@#B#B@&#&#5!!JYYPG&&@&&@@@@@@@@@#GGGB###BG5Y5GBBGB#&&&@@@&&&#&&&5P5Y????Y5PB&&&BP5YJ?5PB##&@@&#GJ~.  :P&@&&&@&&@@B.                         !Y.\n"      
															 +".7P#BPB#&&&&B7?55J5GB##&&?   :~^...:J##PPYYJ7!~~?JY5Y~.J5J5G##@@@#BB&@@&G!^!?J5PG#@@&&&@@@@@@@@@@@YYYYYPG#&&&&&&&##&@@&&##B#&&&&PBGPP55PPG###&&BBPGPPGB#&B#@@@@@@@@#Y^.^B@@&&&@@&@Y                         !5.\n"      
															   +"7#B#&&&&&P??YG5PB&&&&#&7  :~?!.:::7###BBGGG5J~~~!!?~:~555PBB&@@##B@@@5:.!JY5GG#@@@&&@@@@&&@@@@@@GGGGPG#&@@@@@@@&&&&BB##BB&&&&&&##B##BB####&&@&##B###&&&&&@@@@@@@@@@@@B?JG&@&&&&&&?                       ^G^\n"       
															 +"^PB#&&&@&&5JYYJPG#&&#&&#&&P7?JY7~^^~??JG##B#BBBG5J7!!777?YPGBBB@&##&@@5^:~JY5GB#@@@&&@@@@@&&@@@@@@BB&&@@@@@&#BBBBBBB###&#G#&&&&&&&B#&&&###&&&&@&&&#&&&&@@@@@@@@@@@@@@@@@@&#&&@@@&&&&~                    :P#^\n"        
															+"!G##&&@&G7?J5B5YBBB#&&#&##&&&#GP5?7!!^::7P&&B######BBGPPGB#####&@###@@Y~^~JJ5GG#&&&&&@@@@@#&@@@@@@@@&@@@@#P555PPGGB##&&&#BPB&&&&@@B#&&#BGG##&&&@&&@&&&@@@P~!YG#&@@@@@@@@@@@@@@&@@&@&@#:              .7JG#BY.\n"         
														  +".YB##&@#7. :??YGPG5PB#&#B###&&&@@#GYYY5!~!JJG@@@&&#&&&&####BBBBB&@&#B@@B777JY5BB#&&&&@@@@@@BB&@@@@@@@@@@@#5Y555PPPGGB&&&&##&##&@@&@@#GBBGGGBBB&&@@&&&&&&@@#.      .~?P#&@@@@@@@@@@@&&&&&B.          .7B&@#P^\n"            
														 +"^GB##&&7   .JYY5YPJJPG#&&&#&&##&&BBBPP55?!?55#@@@@@@&##&#######&@@@##&@@BJ7JY5B###@&&##&@@&#B#@@@@@@@@@@GYPP5PPPPPGGP#&&#B#&&&&@@@@@@#G#B##BBB#&@@@&@&@@&&@Y            .^YGB&@@@@@@&&&@&&B^....:~75&@@B?:\n"               
														+"~##B##P:   ^B#Y777?J5GB###&&##&#J~~!YPBGPP5PBB&@@@@@@@######&##@@@@&##@@@#5YPG###B@@@@&#&&&#&&&@@@@@@@@&Y75BGGGGGGGGBG#@&BB#&&@@@&@@@&###&&&&B#@@@&@@@#@@@&@Y                  :7B@@@@@@@@&@&&@@@@@@@G~.\n"                  
													   +":5#B##G    7&@GP@&B5PB###BB&&##B7:^~7?5G#BGGGB#@@@@@@@&GB##&&@&&&@@@BG&@@@&GGGB&##@@Y@@@@&@@@@@@@@@@@@@@@&&&&&#BB#B##BB#@#&#G#@@@&@@@@@&&#&&&#&@@@&@@@&&&&@&@B                     .^JG#&@@&&&@&#B5!:.\n"                     
													   +"7##&&5   ^B&&@&#@@@@#BB&#&#&&#&577??J5GB#&#GGG#@@@@@@@YYB#&##&@&&@@BB#&7#@@B###&#^5& ~@@@@@@P!5&@@@@@@@@@@&@@@@&#@@####B#&#BP&@@&@@@@@@@@&#&&&@@@@@@&&&&&&&&&&:                          7&@&&@J\n"                           
													   +"P&&@5  ~G&&&@@@#&@@@@@##&#B&&&#G5PPGGBBB##&#BGB&@@@@@@75BB#&&@@@&&@B#@~  #B!?##GG:^Y  ?@@@?YBPJ7&@@@@@@@@@&@@@@@@@@GG&&#B&##G&@&&@&@&&@@@&#&@@&@@@@&&&##&&&@@@~                           ~&@&&&^\n"                          
													  +":&#&G:!B@@&&&&@@@#@@@@@@&#BB&&#####&#P??J5G#&#BB#&@@@@@GP###&#@@@B&@#&G   7&J!J~?:PJ^  ^@@B   .~G@@@@@@@@@&&@@@B&@@@&&&&#&&&&&@&@@&#&&#&&@&@@G:.Y@@@###GB##&&@@5                 .!JYY7~:   !&&&&B.\n"                         
													  +"Y&&&BB@@@&&&@@@&G7Y&@@@@@&###B##&#B#&#GGGB#&&&###&&&@@@@###&@@&&! :BB&J   ^&&Y. Y  ^!~!P@@^      G@@@@@@@@B@@@@@@@@@&&&&@@&@@@&&&&#&&&#&@@@#7    :B@@&BGG#&&&@@&.             .~P#5!...^~:.  ~&&&&P\n"                         
													 +"~@&&&&@&&&&&&G?^.   :#&&@@@##GPPBY?YP#&&&&&&#&&&###&&&#@@@#B&@@BJ^   P&B^   ##5  ?J    .#@7       7@@@GJ@@@@&@@@@@@@&&@@@@@@&&##&&##&&&&@#?.        ^P&#PG#&&&&@#            ^P&@P:            Y&&&&!\n"                        
													+"!&&&&&&&@@#P!.       7&@BGB&#GP5G?!J5GB#&&#&&&&&&&GB&@&B&@@&B&@@^^Y   ?#B&P. #GP: .~?:  5@?       .#@@5  G@@@@&&@@@@@&@@@&&&&&BB###B#&&&&G             ?&PB#&&&&@B.       .!?#@@G~              .G&&#G:\n"                       
												+".^Y#&&&&@@@&&7.         7@@@B5B&&GPGYJY5GB#&##&@&&&##&BBGB@&&@@@@&@@!:G   :BP:BP.&JJ.    ^!?@J        G@@?   B@@@@Y .~G@&@@@&#####GBB#B#&&&&&:             ^#G#&&&&&#@@P7^~!?G&@@@P.                  G&&&Y\n"                       
						  +".^~!!~~^:..   ...^~!7YG#&&&&&#GJ^.           .B@@@@#BG##BPGGBB#&&&B#&&&@#B#&#BGG@@@@@@@@&@@?^   ^@&7 Y:G7J!     ~@7        J@@J   Y@&J@@Y   .#@@@#BBBB#G#####&&&5:!J7.           .GBB&&&@@@@@@@@@@@@@&5.                    .#&&@7\n"                      
						 +".......:75P5Y5PPPGBBGB###&BJ!:                Y@&@@@@#YYP##&&&&#BGB##&&&@&B#&BGB@@@&@&@@@@&@@B^  J@P#^:. ?J^    ?&^        :@@5  .G@G. Y@&.  7@@@#PPBB#B&#&&&&@G:                 .5BB&&&@&7B@@@@@&#57.                       :&&@B\n"                      
								  +"..^?JYY5P5PPJG@#7                   ^@&&@@@J.^YG#&&##GPGBBBB&&@@&&B@&&&@@&P&PPP!JG@@@@! #& ?G    .    Y&:     ....P@#.^P@&!    P@J  J@@&5PGB##&&&&@&&?                   .5GB&&&@G  5@@@#:                            ~@@@G\n"                     
											  +"!&5.                    5@&&@@B.    . .^7YPBBB###@@@@&#@&&&&&&#@&&&^  .Y@@@J@? :#        ~@:   :?555G#@@&#&B!       #@. 5@&GG##&&#&@@#P##.                  .JGBB&&@@:   ?@@@G                             !@@@G\n"                    
											 +":&5                      #@&@@@!               .:^7:^B&G&#&&&&B@@@@&J    7@@@G  :7        5P   7?:    :&@J.          ^@G7&@###&&&B@@G^  .P!.               :7G#&&#@@@G     5@@@~                             ~&@@B.\n"                  
											 +"B#                       #@&&&5:                     ^B#&&&&&B&@@@@?      B@#             ?Y  ~.       5@5            &@&@&&&&&&###~     .^^          .~?5B&&&&@@&@@@?     .#@@#.                             .P@@&:\n"                 
											+":&J                       P&&&&B^                     .5&#&#&G#@@@@&!    ^P@@.             .:           :&@~           P&@@&&&&@&#P.                  7&@#G&@@&@@@@@@@^      G@@@?                               :B@@?.\n"               
											+"^&!                       !&&&&&P                     ~#&#&&&@@@@@@&~  :PY~#P                            ^&@:          B@@@@@@@@&B.                 .P&&Y  5@@&@@@@@@G       G@@@!                                 ^P@@5:\n"             
											+".&?                        ~G&&&&G:                    !&&B#@@@@@@@B  :Y. ~#.                             .B&^        7@@@@@@@G7#B                  G@@P    P@@&@@@@#.       B@@7                                    .J&@&P!:.\n"        
											 +"JY                          ~B&&&#5.                   5J#@@@@@@@@~     ^B.                                7&7      .#@@@@@&~  &P                  P@Y.    Y@@&&&@B        7@&^                                        .7P###BG5YJ7^^\n"
											  +"7.                           ~G&&&#^                  ?&@@@@@@@@P     ~G.                                  :#!     J@@@@@#.   7P?~                .J     .B@@@@@@7       !@P.                                              .::::..\n"  
											  +".^                             ~G&&#^                 .~G@@@@@@@^    .?                                      P    .&@@@@G.                          :. !J.P@@@@@#:      !@?\n"                                                         
																			   +"?#&P.                   :P@@@@&                                                  Y@@@@B                           :&5?5&@@&@@@G.      !&^\n"                                                          
																				+"7#B~                     #&&@&.                                                .&@@@#.                            7&@@@&#BJ^.       ^B:\n"                                                           
																				+".GB!                     ^G##&&5:                                              !@@@&:                              .JP~.            Y~\n");                                                           
			        break;
					
					case 2:
						enemyimage= ("\n" 
						
																	+":^JYJJJJ?!:.\n"                                             
																  +"^JBBGJ7Y&G5PP5J~.\n"                                          
																+".YBBB#Y  ?&Y.!GBBJ~.\n"                                         
															   +".5BPJ~GG7~BG:7#P^7GJ~.\n"                                        
															   +"?BBG7.!BY?BYJGJ:  PG!^\n"                                        
													 +"....:::^^^:YP55P55##B#BG7:~77GBY7^\n"                                       
											  +"..:^^^!777???77??7!!7!:JBGPG#BYY5J7PBPJ~\n"                                       
									   +"..::^~!!?77??77?7!!!!!!!!7??J?JPP55BB?7^..PBPY~\n"                                       
								   +".~??7!!7JJJ77777!!777777777777?JJJYYPB##BY55PPBBGY~ \n"                                      
								  +".YPBBGGGGGBBGGGGP5YJJJJYJJYYJYYYYYYYYYG####J.:?BBP?:\n"                                       
								 +".JPG##&############BBGPYJJYYYYYYYJYYY5G#####GJ!7JYY!\n"                                        
								 +".5B###B##BGGB########BP5JJYYJJYY55PGGB#&&##PPGPJJ?!.\n"                                        
							  +"..::77?7!~7JJ5GGGP###BB##BGYY55PGGBBBBBBBY??J5PBG5JJJ?7~:....\n"                                  
						   +".~JJJ!^~7?J55PGGPPPPG##BBB#GYG#BBBBBBBBB##5!^^7JYYYYJYYYYYYJ??JJ~.\n"                                
						   +"^B&&&#7PBGPPPGGBBB#####B##GY5GBBBBBB######5Y55YYYYJYGBBBGPYYYYYYJ:\n"                                
						   +":YBB#BY5J!~JGGPPPP######G577PBB#############BB5YY?.J&BB##BB5YYJ~..\n"                               
							+".::~~~~.   ~!!!~:~77777:.  ..:B###########BBPYYJ!Y5??JPB#BBYY7\n"                                   
														  +"~5&#B###BBBBYJJJYJP57JYYJ5#BB?JJ???:\n"                               
														   +".JB####BBBGJJJYYGGPJYYYYJ5BG7JYYYY:\n"                               
														   +"..7#####BBGYYYYYBBBGYY5J!GG??YJ77!.\n"                               
													+".^^7JJYGGGB#######BBPYYYB####P!JPJ7JY~\n"                                   
											 +"..:7!Y5PBBB###BBBBGGBBBBBBGGYYYYG##BY5J??JYJ7:\n"                                  
									   +".::.^!?JYGBB#BBBBGGPY5YY5PGBBBBBPJYYYYYYJJJJ?Y5YYYY!.\n"                                
									 +":7YP?!!77????JJJJYY???77JYJ!!!!?BBBPYYPBGPYYYYYPGGPPGPY7^\n"                              
									+":YP5J77?J???!~^~~77!!!!7J5J777!~!5GB#BB##BBPJYY5GGBBBB#BPY!\n"                             
								   +":YGYJJY555PP5YYJ?!?J7!7JYY5?7???!Y555B######BBBB####BB##BBGP!\n"                            
								  +"^5GPP555?!~~~!5P5Y?~!??YY555?777?7J55PB########B###GBBBBBBBBGP?:\n"                          
								 +"^5BB#BGY~:::::..75YY?!75PGGB5???77?~YGB##BB##BBB5PGPJJB#B#BBBBBG5~\n"                        
								+"^GBB##5G?!!!!!!!?JBYJJ?JGBBB&57?7777~P#B##B###BG5JYG5YJ?PB##BBBBBBG7\n"                        
							   +"~GBBB##GGPYYYYYYYYPGPPPGB####G???77?7?B#####B##BG5JYGPYJ..^!P##BBBBBG7\n"                       
							 +".!GBBBB#&#BBG55Y55PGBPPPGB#####57?7??7?G###B##B##BGPJYGPYY:   .~5BB#BB#G!.\n"                     
						   +":!JBBBB#B5G&#B######BPPPPGB#BB###577777JG###BB###B#BP5J5#GP?.      ^!5B#BBB??~.\n"                  
						   +"?5BBBBBY~.^5######BGGGBB##BBBBB#B?!!!!~?###BB#######G55G##G^          ^PBBBBB5~.\n"                 
						   +"JPGBBBG!    JBBGB##B##BGB#BBGGGBGPPPPPPJ5####&#BBBBBGPGBG7:          :JGBBBP?~:\n"                  
						   +".!YGBBBB7   :J5BGGBBBBBBGB#BBBBBBGPGPPGPB&#577^..........           ~5BGBGP7:\n"                    
							 +".~5BBBB?    ^GBBBBGGGGGGGGGYJJ?YPPPPPGYY~                       .7BBGGP?!\n"                      
							   +".~5GBB5: . !GBBB7.::.....     7PGPGG~                        :5BGGG5:\n"                        
								  +"!GB#G:   ~GBBP^             7PGPB!                        5#GG5~.\n"                         
								  +":B##J.    ^G#&J              PBB&7                        ~B##?\n"                           
								  +":B#P:     ^G#5:              PBBG^                         ^G&5:\n"                          
								 +".Y#B~     :P#Y.               5BB!                           ^G&J.\n"                         
								 +".##Y      J#B.                PBP                             ~##~\n");  
			        break;
					
					case 3:
						enemyimage= ("\n" 
						
																						  +".Y^\n"                                                                                                          
																						 +".55Y:\n"                                                                                                         
																						 +"PGBY5.\n"                                                                                                        
																						+"Y#GGPPY\n"                                                                                                        
																					   +"JG#GPBPY?\n"                                                                                                       
																					  +"YGB#BGBBG5!\n"                                                                                                      
																					 +"J&##&BBB#&B5^\n"                                                                                                     
																					+"?##&&#B#&###BY:\n"                                                                                                    
																				   +"7###&&BG##BGG&GJ.\n"                                                                                                   
																				  +"!#&B##BB#B#BPB#&GJ\n"                                                                                                   
																				 +"~##BG&#GG#BGPPB##&#?\n"                                                                                                  
																				+"~###P#&#5BB#G5PB##&&B7\n"                                                                                                 
																			   +"^#&##G##GGBB#GPG##&&&&B^\n"                                                                                                
																			  +"^#@&&G#&B5GB&BGG#####&&#G:\n"                                                                                               
																			 +"~#@@&#P&#GP#&#BGBB##&&&&&#P.\n"                                                                                              
																		   +".J&@@&&B#&B5B&#BGBB##&&&@@&##P.\n"                                                                                             
																		 +"^Y&#&@@&&B&&BB&##BBBB#&&&@&#B#&&#P7.\n"                                                                                          
																		 +"P&&##@@&#&@&B&&&##B#&&&&&#GP#&@@@@B~\n"                                                                                          
																		 +"5&&B&@@&&&@&#&&####&&@&BP5G&@@@@&GJ~\n"                                                                                          
																		 +"Y&&B&@@&&@&&&#&#&&&&&&GPG&@@@@&BGPJ!:\n"                                                                                         
																		 +"5&&B&@@&&@&&&#&&@@&&&BG#@@@&&BGP7...^?.\n"                                                                                      
																		+":Y&&#&@&&&&&&#&&@@#B#&&&@@#GGGGG5!~~!7?!\n"                                                                                       
																		+"^?&#&@@@&&&&#&@@&BB#&&&&GYYJJGB#P5YYJ77~.\n"                                                                                      
																	   +"~^7#B&@@@&@&##@&##&&&&#P7!?7?PB##57:.^!~.\n"                                                                                       
																	  +".7~?GB&@@@&&#####&&&@&GJJYJ?5#B###B7~.  ^:. .\n"                                                                                    
																	  +".^.^5G&&@&&&&&##&&@&#55GGGB#&###BB#B?!~^:^:^^^\n"                                                                                   
																	  +":^.!PP&&&@&&@&&&@@&#GB#BB##BGGGBB#B7PY7::.~!~7^\n"                                                                                  
																	 +".^!!5PG&&@@@@@&@@@&#BGY?PGP7JYPGP5?. .75Y^ !PY??.\n"                                                                                 
																	 +"~Y5PBGB&&@@@@@@@&##BPJJPGP5Y5GBJ!^     .!5PPPGP!^\n"                                                                                 
																	 +"!GGB&B#&&@@@@@@&B#5^...?7!??Y5?~~        :Y^?G!...\n"                                                                                
																  +":. 7GP!BB#&&@@@&&##BPJ?7!7!::^~7!^~.        :7~GY~!7?.\n"                                                                               
																  +".~.?G5^J5&&&@@&##B57Y77~...::::^^^^.        ^J5G57!JY!\n"                                                                               
																  +". .75P5^7&&@@&GP5Y7~J^^:...^~~^::::         .JPJJ:~!!!\n"                                                                               
																 +":! .JBG7 J@@&PJJ!.^~Y5 ....~75?7~7~.          :Y!~.:^!^\n"                                                                               
																 +"^7.^BB~  J@5::~7~.~:^J: .^~~YPP55Y~            ^~...~:.\n"                                                                               
																+"..~!B#^   .: ..~~~^^:.~^^J!.!PGBG?:..            :.:~7..\n"                                                                               
																+".^7GB.      7^~!^??^..:^7^.:5GPB5YJ75:           .^^!! .\n"                                                                               
															  +"...^J#!      :Y?JPBBGY7~^~~~!JYJ5PB&&#&5^JYJ.       !!!^.:\n"                                                                               
															+"......YB.     !J5Y!!YGP5?!!JJYJY5PGBBB##BGJ@@&#5:    ~!~^^:.\n"                                                                               
														   +"..:~^7G&~     ~BJ7?!~7JP55!?!7~~7??J?J5PYYGG@&##&&P:....:^~!.\n"                                                                               
														   +"..?G5B&B.    ~##BJJ?7?7777!!7!?7!~~~!!?JYB#B?G&&&@@@B7~ :J55\n"                                                                               
															+"..?J!P      &@@@&#J7!~^.::::!!^~~~7J7!?5G#&^ .!G&@@@@B :G&B.\n"                                                                               
															  +".. ^     :@@@@&#P!^~!!:.::^~^7??Y5Y?Y5GB@J    .!G@@5 ^GBG:\n"                                                                               
															  +"...:     ?@@@#PG5!^^!??~~!^^7?J?7?!7J?G#@G       ^~^~YPBP:\n"                                                                               
															  +".. .     P&&G??7?~:^~?^^?!!~7!!!!:..^!G&@@~        .:#@@&#5~.\n"                                                                            
															  +":.      .GPY!~!^^^^!7^:^::^J57~~:....~5#@@B         :#@@&5#&&Y.\n"                                                                          
															  +"!.      ?G?7^:::::!P!:::::^~7YJ5^....^?G&@&~        5&@&@@@@@@@B~\n"                                                                        
															  +"?.      BP57^^~^::~?!^:::::~~!JGP7^..!?JG#&G       ~#&&@@@@@@@@@@&J.\n"                                                                     
															  +".      ~&#Y7^^7::^?~~~^^:::~^~7~JBY~??5J?5##       G&#&@@@@@@@@@@@@&G~\n"                                                                   
														  +".:         ?PP!:.!??~J!^~~~~!!::^7~!?YY7~~!7??YB.     7&&&&@@@@@@@@@@@@@@@&?\n"                                                                 
														  +"!?  .     .YYJ:..~YJJY!~~^~~!YJ^:^^7JY55Y???JYJP7    .B&&&&@@@@@@@@@@@@@@@@@@5:\n"                                                              
															  +".     :?!J~:~?~^^^^!??^~!755?!~~?5PB#G55BGGBP:   .Y&@&@@@@@@@@@@@@@@@@@@@@@#7.\n"                                                           
																	+".!~~!7GY!^::^~?J!~~!!5G57!7?5B&&BPB#B#BY      ^5&@@@@@@@@@@@&@@@@@@@@@@@P:\n"                                                         
																	+".~^~!JB?!^^:^7JY777!~JPG555J?YG&&B5GBBB#7       .~P&B77PB&@@@@@@@@@@@@@@@@B!\n"                                                       
																	+".~~~??PJ??7~^!Y?7??J~?Y5PGG5JJYPB&BPP##&#.         .^^:.^!5&@@@@@@@@@@@@@@@@&Y:\n"                                                    
																	+"^^^^!?7PY!Y577YY77?7~?JYG#BGYJY55G#BYG##&7               ..^75&@@@@@@@@@@@@@@@@B~\n"                                                 
														  +".JBB!    .77^~77JY&G!75GB5J??JJY5G#BBBBYY555PGG5G#&Y                     ?#@@@@@@@@@@@@@@@@&J.\n"                                               
														  +"^#&B7    ^?J77??P&#@#??YGGGGYY5PGGBB#BBGYY55PG#####Y                       ^P&@@@@@@@@@@@@@@@@G^\n"                                             
																  +".!?YYY5P&@@@@&57?PB#BB##BGPPGBBGPYYPB###&#BY                         :?B@@@@@@@@@@@@@@@@&?.\n"                                          
												  +"..              !Y55PB#&@@@@@@&BPYYY5PBBBB#BGGB#BGPPB#&&&&&#.                         ..:JB&@@@@@@@@@@@@@@@5:\n"                                        
												+"5#&!      :5B5  .GGB####&&@@@@@&&##GGGBGGBGB&&BG##&#BBB#&&&&&:                             .:?B&@@@@@@@@@@@@@@#!.\n"                                     
												+".@&@!      .7&Y  J#B#&&##&&&@@@@@@&&#B##&#B#&@&#BB#&&&#BB#&&&&^                               .:!P&&&@@@@@@@@@@@@&5:\n"                                   
												 +":&@?        .   #&###&####B&&@@@@@&&#&&###&&@&##&&&&&&&&&&&&&7                                 .^7JB#&@@@@@@@@@@@@@B!\n"                                 
												  +"7@B           :&#BB##B&##&&#&&@@@@&@@&&##&@@&#&&@@@@@@@@@&&&G                                    .!J5B#&@@@@@@@@@@@@#J:\n"                              
												   +".!           ?@###B##&&@@@@&&@@@@@@@@@&&&@@@@&&@@@@@@@@@@&&&:                                      :!??Y#@@@@@@@@@@@@@G~\n"                            
																+"G@&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!                                         .:^!P&@@@@@@@@@@@@&J.\n"                         
														+"!#P   ^B@@@&&####&&&&&&#&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                            .^?B&@@@@@@@@@@@@P^\n"                       
													   +"^?~.:?G&&&#&###&&&&##B##&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#                                              .:~?P&@@@@@@@@@@@#?.\n"                    
											 +".:!5B7      ^5G5J7!!!!!?G#&&&&&&&###&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B.                                                .^J&@@@@@@@@@@@@P^\n"                  
										  +".^?B#&&@@: .......        .5&@@@@@@@@&&&@@&&@@@@@@@@@@@@@@@@@@@@@@@@GJJP^                                                   ^5#@@@@@@@@@@@@#!\n"                
									  +":!YB##&BB&&@@GGB&&&##B5PY7!!77Y&@@@@@@@@@@@YYG#&#BGPY7~^:^^~~!!7B@@@@@@@?.:::..............                                       :7G&@@@@@@@@@@@@Y.\n"             
								 +".^?P#&&@@@&@@&&@@@@@@@@@@@@&&PPPPG#@@@@@@@@@@@@@BYJ??77!!!777????JJJYP&@@@@@@&????777!!!~~~~^^:::................:::::::........:::::....:!5B&@@@@@@@@@@@G~.\n"          
							+"..:7P&@@@@@@@@@@@@@@@@@@@@@@@@@@&&&@@@@@@@@@@@@@@@@@@@@&BP55YJJJJJY55PGGB5P&@@@@@@@5Y5YYJJJ???????77!!!~~~~~~~~~~~~~~~~~~~~!!!!!!!!!!!77777??????Y5G#&&&@@@@@@@@&5^.\n"       
						   +".J#@@@@@@@@@@@@@@@@@@@@@&@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#BBB##&&&&&&B#@@@@@@@@##BBGP5JJ????????777777777777777!7!!!!!!!7?JJYYYY555PPGGBBBB###&&&#&B5JJY5PG#&&&B?^.\n"    
						 +".::.^J&@@@@@@@@@@@@@@@@&&&&&&@&&@@@@@@@@@@@@@@@@@@@@&&@@&&##&&&&##&&&&&&&&&&@@@@@@@@@@&&&&&#G5YJ?7777777!!!~~^^^^^^^~~^^^^^^^^^^~!7??JY555PPGGB##&&&&&&@@@@@&#G5J7!~!?JYJ~.\n"   
						  +".   .:~7JJY5PGGGGGGGGGBBGGBGYYYY555555PGGGGPPP5YJ??JJY55Y55PPGGGGB###&&&&@@@@@@@@@@&&&&&##GPY?!!!!~~^^::.......     ..................:::::::::^^^~!7??JYY5PGBBBG5?77!~:.\n"    
								 +"............ ......................................:::^~!!7?JY5GB&@@@@@@&&#P5JJ???7!~^:...\n"                                                                            
																						   +"......::^^~~~~^^::........\n");     
			
			        break;
					
					case 4:
						enemyimage= ("\n" 
							+"GGGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGPPPGGPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPBBB#&#################BBBBBGGGGGGGPPPPPPPPPPGGGGPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPG###GB&&###############&&&&&&######BBGPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPB#BB#BG#&&#############################BGPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPB#GGBB#BB#############################&##BBGPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPBBGGGGGB#B##################################BBGPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPYPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPG#BPP5PPGG#######################################BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPJPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPG#GJY55PGG#########################################BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPJ5PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPPPPPPPPG#5?JYYPGB###############BBBBBBBBBBBBBGGGGBBBB#####GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPP5PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPJ5PPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPPPPPPPPPBBJ7JJ5PG###############BBBBBBBBBBGGPPPPPPPPPPPB###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPP5PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPJ5PPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPPPPPPPPP#B??YY5PG##############BBB###BBBBBBBGPPPPPPP55JG##BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPP5PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPYYPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGPPPP?5PPPPPPG#G?JYY5PG################BGPP5YYJJ5GBGGPPPPP5YJB##BPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP5YPPPPPPPPPPPPPPPPPPPPPPPPPPPPBPPPPP7PPPPPPPG#PJYYY5PG##############&BPPPPPP5Y77?PBGGPPPP5?J###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP5YPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPP5JPPPPPPPBBPYYYYPPG###########&&&#GGGG#####G?7JGBGPPPPGPG###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPYPPPPPPPPPPPPPPPPPPPPPPPPPPPGGPPPP5YPPPPPPPB#B5YYYPPB########&&&&&&###BB######GJYG#BGPPGB####BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP5PPPPPPPPGPPPPPPPPPPPPPPPPPPBGPPPPY5PPPPPPPG##GP5YPG#########&&&&&&&&&##B####BGPPBBGGPPPGB###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPGPPPGPPPPPPPPPPPPPPPPPPPPGBPPPPPYPPPPPPPPPG##GPPGG####&###&&&&&&&&&&###BBBBBGBBBGGPPPPGB###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGPPGGGGGGGPPPPPPPPPPPPPPPPBBPPPPPYPPPPPPPPPPG#BGGGG###&&###&&&&&&&&&&&#########BGGPPPPGB###BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGPGGGPGGGPPPPPPPPPPPPPPPPPBGPGPPP5PPPPPPPPPPP#BGGGB###&&#&&###&&&&&&######BBBGGGGGPPGGGB###BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGBGPGGGGPPPPGGGGGGGGGGGGPPPPBPPPPPPPPPPPPPPPPPG##GGGB###&&&###############BBBGGGGGGGPGGGB####GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGPPGGPPPGGGGBBBBBBBBBBBBBBBGGBBGGGPPPPPPPGGBBBB#&&BPGG####&########BBBBBBBBBGGGGGGGGGPPGGGB####GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPGPYY55Y5555PPPPPGGGGBBBBBBBBB#BGGGGGGGGGBB##&&&&&&GPGG###&&&######BBBBBBGGGGGGGGGGGGPPPGGG####BPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGG5Y5PGPPP5555Y55Y55Y555PPGGGB##BBGGGGGB##&&&&&&&&&BGGG###&&#######BB###PPGBGJ?5BGGPPPPPPPPB###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGG5Y5GGGGGGGGGGGGGGGPPPPPGGPB##BBBBBB#&&&&&&&&&&&&#BGGG###############&#GG#&&BY5GGPPPPPP55JG###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPGPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGG5Y5P5555PPPPPGGGGGBGGGGGGBBBGGBBB#&&&&&&&&&&&&&&&BBGB###&&#####BB##&&&#####BGGGGP555PP5YJB##BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PGGGPPPPPPPPPPPPPPPPPPPPPGPPGGGGGGP5Y5P5YY55555555555PPPPPPGGGGGGGGG#&&&&&&&&&&&&&&&BBGB###########B###&&&&&####BGGPPPPPP5JY###BPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PGGGPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGP5Y55YJ?J55555YYJJJYY5PGGGGGGGGGGG#&&&&&&&&&&&&&&&BBGB######&################BBGGPPPPGGGPB###GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGPPPPPPPPPPPPPPPPPPPPGGGGGGGGP5Y5YJ??JY5555YJ???J5GGGGPGGGBBBGGB#&&&&&&&&&&&&&&#################BBBBBBBBBBGGGGGGGGBBBB####GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGPPPPPPPPPPPPPPPPPPPGGGGGGGGGGP555YJ?7JY555YJ???JPGPPPPPGGB#&&BPGB&&&&&&@&&&&&&&&&###########################BBBB#########BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGPPPPPPPPPPPPPPPPPPPGGGGGGGGGGG5YY55J7?JY55YYJJY5PPPPPGGGGB#&&&#GPB&&&&&&@@&&&&&&&&&&&&&&BG#&&&&&&&&&&&&&&#################GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGPPPPPPPPPPPPPPPPPPGGGGGGGGGGGG5Y5P5J??YY5YYYY5GPP5PPGB#BBB&&&&&#GG&@@@@&@@&&&&&&&&&&&#&&G7?#&&&&&&&&&&&&@@@@@@@@&PB&&&&&&BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGG5Y555JJYYYYYY5PGP55PPBB##BBB&&&&&&#B#&@@@@@@&&&&&&#&#####&&5^J&@@&&&&&&&&&&&&&&&&@@G!B@&&&&#PGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGPYY55YYYYYYYY5PPP55PGB#&&&#B#&&&&&&&&&&&&@@@@&&&&&&&#####&&&&Y~Y&&&&@@@@&&&&&&&&&&&&&57#&&&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PGPPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGPYY55YJYYYYYPGGPPPGB##&&&&BB#&&&&&&&&&&&&&@@@&&&&&&#####&&&&##?~P&&&&&&&&&&&&&&&&&&&&&Y?#&&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGGPJYP5YYYYYY5GPPPPGB#&&&&&#GB&&&&&&&&@@@@@@@@@&&&&&&###&#&&&YJJY7~YPPPPGGPPPPPPGGGGBBB#B?J#&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGGG5JYP5YYYYY5GGPPPB#&&&&&&&BGB&&&@@@&&@@&@@&@@&&&&&&####&#&&Y7J55J~~5PPPGGGGGGPPPPP55555P57G&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGG5JYP5YYY55GGPPGB&&&&&&&&&BG#&&@@@@@@@@@&&&@@&&&&&&###&#&&#?Y55PY5YPPGPGGBBGGGGGGGPPPGGG5JG&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGYJ5PYYYY5PBGGB#&&&&&&&&&#BB&&&@@@@@@@@&#&@@@&&&&&&#&###&&PJ5PPPGGGGGPPPPGGGGPPGGPPPPPGGGPB&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGPYJ5P5Y555GB###&&&&&&&&&&###&&&@@@@@@&##&@@&&&&&&&&&&##&&#55PPPPPGGGP55PGGGGGPPPPPPPPPPGGGB&&#GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGPYYPP5555G##&&&&&&&&&&&&&#&&&&&@@@@&&&&@@&&&&&&&&&&&&#&&#5PGGPPPP5555Y5PGBGGGPPPPPP555PPPPP&&#GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGPJJPP555P###&&&&&&&&&&&&&&&&&&@@@&&&&@@&&&&&&&&&&&&&&&&&GPGGGGPP5YJJJJY5PGGGPPPPPP555555PPP&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGG5JYGP55PB###&&&&@@@@@@@&&&&&@@@@&&&@@&&&&&&&&&&&&&&##&&B5GGGGPPP5J???JYY5PGGPPPPP5YYYYYY555#@&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGG5J5GP5PG#####&&&&@@@@@@@@&&@@@@&&&@@&&#&&&&&&&&&&&&&&&BYPGGGP5Y55Y????JJYY55PPPPPY??JJJJJJJ#@&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGG5J5GPPG###&&&##&&&&&&&&@@@@@@@&&&@@&#G#&&&&&&&&&&&&&&B5PGGGP5YYYYY??7???JYJJYYY5Y?~~777???7B@&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGYJPG5P#####&&&&&&&&&@@@@@@@@@&&&@&&BP#&&&&&&&&&&&&&&G5GBGGGP55YYYJ?77???JJJ???777~::^~!777!B&&#GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGPJJPP5PB#&&&&&&&&@@@@@@@@@@@@&&&@&#GP#&&&&&&&&&&&&&#PPBGGGGGPP555YJ7777??????7!!~^:.::~!!!!~G&&&#BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGPJYGP5PG#&&&&@@@@@@@@@@@@@@@@&&&&#GG#&&&&&&&&&&&&&B5GBBGBBGGPPP55Y?7777777??77!~^:...:~~~~~~5&@&&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGPJYGPPPPB########&&&&&@@@@@&&&&&BG#&&&&&&&&&&&&&&PJG#BBBGBBGGPPP5Y?777777777!!~~^....:~~~~~~J&&&&&&BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPPGGGGGGGGGGGGGGGGGGGGGGGGG5Y5GGPPGGGP??7~~7??JJ5G#&&&&&&&#B&&&&&&&&&&&&&&BYJG###BBBBBGGGPP5J?77777777!!!!!^...:^~~~~~~7&&&&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGPY55Y555PG7^^!Y5P5PPPPGGB&&&&&&#&&&&&&&&&&&&&#PY5B######BBGGGGGPYJJ?77777777!~!!:...:~!~~~!~7#&&&&&GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPP55PP^:!Y5P5PGGGBBB#&&@&&&&&&&&&&&&&&&##BBB########BBGGGGPP5YJ?????????7~~^:..:^~!~~!!!7G##PYPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG5::7Y55YPGGGBB##&&@&&&&&&&&&&&&&&#BBGB##########BBGGPPP5JJJJ???????7~^:..:^~!!!!!!!7GGP5Y5GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG?:^?Y5Y5GGGBBBB#&&&###&&&&&&&&##&&#BBBBB#########BBGPPP5JJJJJJJJ???7~:...:^~!!!!!77?B#B5?JPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG~:~?55YPBGGBBGGGGGGGGPB&&&&@&#&##&&&&&##BGGG####BBBGPPPYYYJJJJJJ???7~:..::~!!!!!777?G&#PJ?YPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG5::!J55JGBGBBBGGGGGGGGGGG##&#5G&&&&&&@&&&&###BB##BGGPPP5YYYJJJJJJ???!^...:^~~!!!777775&#GY7?5GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGJ:~7YP5YGGGB#BGGGGGGGGGGGGGG55G&&&&&&&&&&&&&#BGB#BGPPP55YYJJJJJJJ??7~:...:^~!!!!777??J##B5?7YPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG!^77YP5PGPGB#GGGGGGGGGGGGGGG#&##&&&&&&&&&&&&BBB##GPPPP55YYJJJJJJJ?7~:....:^~!!!77?????G&#G?7?5GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGP!7??Y5YPGPPBBGGGGGGGGGGGGB#&&&&&#&&&&&&&&&&&####GPPPPP555YYJJ?JJJ?!^:....^~!!!!77????75##BY77JPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPG#BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"PGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGP!~~?Y5Y5P5PBBGGGGGGGGGGB&&&#&&&&&&&&##&&&&&####BBGPPPP555YJJJJJJ?7!^....:^~!!!!77????7?B##P777YGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG5B##BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGP~^7Y555555PBGGGGGGGGGG&&&&&&&&&&&&&#B###&&&#####BGGP555YYYJJJJJ??7~:...:^~!!7777?????7~J##B?!7?5GGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBB#&#GGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGJJYYYYYJJJY5GGGGGGGGB#&#&&&&&&#BP5JJJYPGBB#######BBGP5YYYJJJJJ???!^:..:^~!777777?????~:~G##G!!7JPGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&#B#&&BGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG5JYYYYJ?7????5GGGGGGGBB#&&&&&BJ?777???JY5PGBBBB####BBBGP5YYJ??????!^:.:^~~!77777???J?~^:^?B#BP!?YYGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&#BB&&&#GGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG5!J5PPPY?7???7J5GGGBGGB#&&&&&#Y7?JY5PGGGGGGGGGGBBBBBBBBBBP5YJJ?????7^:.:^~~!7777??JJ7~^:^^~YBB5!?Y5GGGGGGGGGGGGGGGGGGGGGGGGGGGG&&&#B#&&&#GGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGP7!YGGGP5???????5GGBGGB&&&&&&&BJ5GB#&&&&&&&&&&##GPP55PGBBBBG55J?????!^::^^~~!777??J?!^::^~~~~5BY?J5PBPGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&&&&&&&BGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGY?!5BBBG5?J??JYG#GBGGB#&&&&&&&PJYYPPGBB##&&&&&&&#G5YJ?YPGBBBGP5J???7!^^^^^~!77?????~:::^~~~~~!GPYGBBB5PGGGGGGGGGGGGGGGGGGGGGGGGB&&&&&&#&###GGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGYPBBBBP5YYPB#&&BGGB#&&&&&&&#Y??JJY555PPGB#######G5J?7J5GGBGGP5YJ??!~^^^^~!77????!^:^^~~~~~~~JPP&###BBGGGGGGGGGGGGGGGGGGGGGGGGPB###&#GBGPGPGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB####BBBBBGGG###&BB##&&&&&&&&GY??JJY55PPPPGGGB#####PY?77?Y5PGGPP5YJ?7~~^^^~~!7???!^^^~~~~~~~~~!5#&&&&###BBGGGGGGGGGGGGGGGGGGGGPY55PGGPYYJ~7J5GGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#########BGGB&#&###&&&&&&&&&PYJJJY55PPGGGGGGGBB###B5J?77?JY5PPP55Y?7!~^^^~~77??7~^^~~~~~~~^~~~7###&&&#####BBGGGGGGGGGGGGGGGGPYJJJJJYYJJ!.:!7GGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB#&&####BGGG#&##&&&&&&&&&&#PYJY555PPGGGGGBBBBB####G5J?77??JYYYYYYJ?7!^^^~~77?7~^^^~~~~~~~~~~~~5BGB######B####BGGGGGGGGGGGGG5JJJYYYYJ??:...!GGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB#&&&##BGGBB#&&&&&&&&&&&&#PY55555PGGGGGBBB#######BPY??7????JYYYYYJ77!^:^~!77~^^^~~~!~~~~~~~~~!5GGGGBBB#########BBGGGGGGGGPYJJJYYYYJ?7....!GGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG##&&&GGBB#&&#&&&&&&&&&#P5PP55PGGGGGGBB########BPYJJJJ???JJYYYYJ?7!~::~!!7!~~~~~~~~~~~~~~~~~?GGGGGGGGBBBB#######BBGGGGPYYJJJJJJJ?7!....?GGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB##GBB#&#B&&&&@@&&&&&G5GGPPPGGGGGGBB########BPYJJJJ???JJYJJJJJ?!~::^~!!!~~~~~~~~~!!!!!~~~!5GGGGGGGGGGBB###########B5PP55YJJ??!!:...:5GGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB#&&BB&&&@@@@@&&&&BPPGGGPPPPGGGBB#########G5JJJJ??JJJJJJJJJ?7~:::^~~~~~~~~~!!!!!!!!!!!!?PGGGGGGGGGGGGGBB####BBBPPGGPP5YJ?7!^::..:PGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&#BB&&&@@@@@@&@@&#PPGBGGPPPPGGGBB########GYJJJJJJJYJYYJJJJ?!^:.:^~~~~~~~!!!77777!!!!!!!YGGGGGGGGGGGGGGGGBB##GPPGGPPPP5YY7!!77:.~GGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&BG#&&&@@@@@@@@@@&&BPPGGGPPPPPGGBB########GYJJYYYYYYYYYYJJJ?!^...:^~!!!!!777777777!!!!!!7PGGGGGGGGGGGGGGGGGGBBGGGGGGPGPPYJJ5P5J!YGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&BB#&&@@@@@@@@@@@@&&#P5PGGPPPPPGGBB########GYJJYYYJJJYYYYYJJ?!^:.::^~~!!777?7????7777!!!!!JGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPP5PPPPGPGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB#BG#&&@@@@@@@@@@@@&&&&#P5PGGPPPGGGBB####&##BG5JJJYYJJJYYYYYYJ7!^:..:^~~!777???JJ????777!!!!!YGGGGGGGGGGGGGGGGG555PPGGBBBGGGPPPGGBGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBGG#&&&@@@@@@@@@@@@&&&&&#GY5GGGGGGBBB####&##BG5JJJJJJJ?JYYYYJ?7~:....:~~!7?????JJJJ???777!!!!75GGGGGGGGGGGGGGPYJYY55PGGBBBBP5PPPGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBGB&&&@@@@@@@@@@@@@&&&&&&&#55PGGGBBBB####&##BBGYYYJYJJJJY5YYY?!^:...::~!!7?????JJJJJ???7777!!!75GGGGGGGGGGGPY?JJJY5PPGBB##GYY5PGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBGB&&&&&@@&@@@@@@@@@&&&&&&&&&P5GGGBBB#########BB5YYYYJJJYY55YY?~::...:^~!!??????JJJ?????7777777775GGP5YYYY??7?JYY5PPGGBB#&B5Y5PGGPGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB#&&&&&&&&@@@@@@@@@&#####&&&&GPGBBBB##########BPYYJYJJJJYYYYJ7^::::::^~!7??????JJJ????????777777?PPPPPP5YJJJJJY5GGGGGB#&#G555PGGPGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB#&&&&&&&&@@@@@@@@@@&BBB#&&&&@#PGBB#######&###BBPYYYJJJJJYYYYJ!^:::::^^~!7??7???JJJJJ????JJ???7777JGGPGGGGP5555PGBBBB##&&B5PPPGBGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&&&&##&&&@@@@@@@@@@&####&&&&@&#B#########&###BGP5YJYJJJJJJJJ?~^::::^^~~!7J?7777?JJJJ????JYJJ?77777JB###BGP55PPGBB##&&&&#PPGGGBGPGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&##&&&&&@@@@@@@@&&###&&&@@@@&#############BBGP5YYJJJJJJJ??7^^:::^~~~~!7J??????YYYYJJJJYYYYJ?7??77Y###GGPP5555PPGBB#&&BPBBBGGPPGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&##&&&&&&@@@@@@@@&####&&&@@@@@&#########&#BBBGP5YYJJJJJJJ??7^:::^~!!!!!7?????JJY555YYYY5555YJ77??77G&#BBBGP555555PPPGGPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPGGG5G&&&&###&&&&&@@@@@@@@&&#####&&@@@@@&###########BBGPP5YYJ?JJJJJ??!^^::~!???7777777?J?Y555555555PP55J?JJ?7YGGG###BGP555555PGPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPP#&&&###&&&&&@@@@@@@@@&##B###&@@@@@@@&##########BBGPPP5YYJJJJJJJ?!^^^^!?JJJJJJ7~~~!77?Y55PPPPPPPPPP5YJYJ??YGG##BGBBBGP55PPGPGGGGGGGBGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&###&&&&&&@@@@@@@@@@&#BBBBB#&&&@@@&&########BBGPPPP55YJYJYYJJJ7~~~!7JYYYY55Y7!!7?YY55PPPPPPPPGGGP5YYYJ??YGBGGGG#&#BGGPGGGBBBBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB#&##BB#&&&&&&&@@@@@@&&&&&&&######&@@@@&########BBGPPPP5555YYYYJJJ?!!!7JY55555P55J??Y5PPPPPP55PGGGGGGP55YYYJJ5GGGGG#BGGB##BB##BGB#GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBGB##BGB#&&&&&&&&&&&&&&&&&&BB#&&@@@@@@@@@@&#######BBGPPPPPP55555YJJJ?7!77Y5PPPPPP5P5YYPPPPPPGGGP5PGGGGGGPPP555JJ5GGGGGGGGB#BGG#BGGBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#BBB#BB#&&&&&&&&&&&&&@&&&&&&&#######&&&@@@&########BGPPPPPP555555YJYJ7!!7YPGGGGGPPPP5YPPPPPPGGGGPPGGBBGGGGGGPPPYYPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBGPPPBB#&&&&&&&&&&&@@@@@@@&@@@&&&&&&&&&&@@@########BGPPPPPP555P555YYY?!!7J5GGGGGGPPP55PPPPPGGGGBBGPPGBBGGGBBGGPPYY5GGGGGGGGGGGGGGGGGGGGGGGGGBGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBGGG#&&&&&&&&&&&&&&&&@&@@@@&&&&###&&&&&@@@@@&#######BGPPPPPP555555555YJ7!!7YPGGGGGGP555PPPPPGGGGGGBGGGGBBBBBBBGGPP5Y5GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB##B#&&&&&&&&&&&&&&&&&&@@@@@@@@&###BBBBB#&@@@@&#######BGPP5555555YPPPPP5Y?!!7J5PGGGGGP555555PPPGGGGGBBBGGGBBBBBBGGGPP5Y5GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB####&&&&&&&&&&&&&&&&&&&&@@@@@@@&&&&&&&GP#@@@@@@&######BGPPP555555PPPPPPP5Y?777YPGGGGGP5555YYYJ5PPGGGGBBGGGGBBBBGGGGGPP555GGGGGGGGGGGGGGGGGGGBBBBBBBGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&&&&&&&&&&&&&&&&&&&&&&@@&@@@@&&&&@#P#@@@@@@@&######BBPPP555Y555PPPPPP55Y?7!?YPGGGGP55YYYJ??Y55PPPPGGGGPPGGGGGGGGPPPP555GGGGGGGGGGGGGGGGGBBBBBBBBGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@&#&&G#@@@@@@@@&######BBGPPP55YYYY555PPPPPPYJ??J5GGGGP55YYJJJ?JY5555PPPPGPPPGGGGGGGGPGGPPPPGBGGGGGGGGGGGGGGBBBBBBBBGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@&##&@@@@@@@@@&#######BGGPP55YYYYY55PPGGPPP5YJYYPPPPP55YYJ????JJJJYY55555P55GGGGGGGGGGGGGGGGGGGGBBBBGGGGGBBBBBBBBBGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&&#####&&&&&&##&&&&&&&&@@&@@@@@@@@@@@@@@@@@@@&#######BGPP55555YY555PPPGGGPP5YJYYYYY5YYJ????7777Y5JJ55YJY5PPGGGGGGGGGGGGGBBBBGGBP555GGGGGGBBBBBBBGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG#&&&&&###&&&&&&&&&###&&&@&&@@&@@@@@@@@@@@@@@@@@@@&B######BGPP555YYYY555PPGGGGGPPP5YYJ??????B57?777JGGPPGGGPGGGGGGGGGGGGGGGGGGBBGGGG?55?GGGGGGBBBBBBBGGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&&&&##&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@&B#&@@&B######BGPP5YYYJJY55PPGGGGGGPP55YYYPGGBB#@&PY5PGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBGGBG?55JGBBBBBBBBBBBBBGGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGB&&&&&&&&#&&&&&&&&&&&&&&&&&&@&&&&&&&@@@@@@@&BB##&@&GB#####BGP5YYJJJJYY55PP555555BP5P#&@@@@@@@@@@@#GGGGGGGGGGBBBBBBBGBBGGGGGBBBBBBBBBJJPJGBBBBBBBBBBBBBBGGGGGGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGBBG&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@#B&##&&@@@@&&&@&&&BG######BGPY???JJ???JYYYY5YJ5G&&&&@@@@@@@@@@@@@&GGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBJ?5JPBBBBBBBBBBBBBBBBBBBGGGGGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGGGGGBBBBBBBBBB&&&&&&&&&&&&&&&&&&&&&&&&&&&@&&B#&BB##&@@@@&&&&&&GGB##BBBBGGY775GGP5YPPPGGBGGGB@@@@@@@@@@@@@@@@@@BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBJYGJPBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGGGGBBBBBBBBBBBBBGB&&&&&&&&&&&&&&&&&&&&&&&&&&&#BG#@&###&&@@@&#&&&#GBBBBBBBBBBGPGBBBBBBBBBBBBBBB#@@@@@@@@@@@@@@@@@@#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBJY?JPBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGGG\n"
							+"GGGGGGGGGGGGGGGBBBBBBBBBBBBBBBBBB##&&&&&&&&&&&&&&&&&&@@&&#BBBBB#@&@&#GB&@@&&&&&BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB&@@@@@@@@&&&&&@@@@@&BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBJ5YJPBBBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGG\n"
							+"GGGGGGGGGGGGBBBBBBBBBBBBBBBBBBBBBB#&&&&&&&&&&&&&&&&&&&#BBBBBBBB#&@&@@&&&@@@@@@&BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB&@@@@@@&#&&&@@@@@@@@&&##BBBBBBBBBBBBBBBBBBBBBBBBBBBBB5YYJGBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGGG\n"
							+"GGGGGGGGGBBBBBBBBBBBBBBBBBBBBBBBBBBB#&&&&&&&&&&&&&##BBBBBBBBBBBB&@&&@@@@@&@@@@&BBBBBBBBBBBBBBBBBBBBBB########&@@@@@@&&@@@@@@@@@@@@@@&&######BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGG\n"
							+"GGGGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##&###B##BBBBBBBBBBBBBBB&&&&&@@@@&&&@@&BBBBBBBBBBBBBB################&@@@@@@@@@@@@@@@&&&&&&&@@@&&&##########BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGGG\n"
							+"GBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#&BBBBGBBBBBBBBBBBBBBBB&@@@&&@@@@@@@@&B####################&&&&&&&&&&@@@@&@@@@@@@@@@&&&&&@@@&&@@@@@@&&################BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGG\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##BBBBP#BBBBBB########&@@@@@@@@@@@@@@@&#######&&##&&&&&&&&&&&&&&&&&&&&&&@&@@@@@@@@@@@@@@@@&####&@@@@@@&&&&&&&&&#############BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGGGGG\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB######BB#BBB###############&@&&&&&&&&&&&@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@&#&&@@@@@@@@@&&&&&&&&&&&&&############BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBG\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#########BBB#############&&#&&&&BGPGB&&&&&&@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&#######BBBBBBBBBBBBBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB######################&&&&&&&&&&&&#B#&&&&&&&&@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@&&&@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&######BBBBBBBBBBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBB####################&&&&&&&&&&&&&@&&&@@@@&&&&&&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&######BBBBBBBBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBB#####################&&&&&&&&&&&&&&&@@@@&&&&&&&&&&&&&@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&######BBBBBBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBB##################&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#########BBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBB###############&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#####################################&&&&&&&&&&&&##############BBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBB#################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#############################################################################################BBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBB####################&&&&&&&&&&&&&&&&&&&&&&#################################################################################################################BBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##########################################################################################################BB#BBBBB#####################################BBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB###############################################################################################################################################BBBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB###################BBBBBBBBBBBBBBBB###################################################################################################BBBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#####################################################################################################################################BBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#################################B#######################################################################################BBBBBBBBBBBBBBBBB\n"
							+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##################################################################################################################################BBBBBBBBB\n");
			        break;
				}
			
			case 3:
				numberOfCases=(int)(Math.random()*4+1);
				switch(numberOfCases){
					case 1:
						enemyimage= ("\n" 
						
																																				  +"^7~\n"                                                                                                                                                                 
                                                                                                                        +".                 ......:?PP7\n"                                                                                                                                                                 
                                                                                                                        +"!:              ^5B###BGGGBY^\n"                                                                                                                                                                 
                                                                                                                        +".!^   .         7#&&&&&&#G5Y!\n"                                                                                                                                                                 
                                                                                                                         +"!7^~^.     !PBBBB#########BPY^\n"                                                                                                                                                               
                                                                                                                        +"!7YP7:. .  ^#@&&&&##BGGP5GBBGY57.\n"                                                                                                                                                             
                                                                                                                        +".^~~!~~77!7P#&#&&######BBGGP7..~?^. .              ....\n"                                                                                                                                       
                                                                                                                      +".:.:^!???JB&&&&&#####BGGBGPPY5?     :^^^!!~^^:^:.:..   .:^!7~.           ... ...\n"                                                                                                                
                                                                                                                    +".:!YPBBB#&&#BBBBBBGBBBB####B5!J?~    .7?.               ..  :!?^.      ......::^^::^:....\n"                                                                                                         
                                                                                                                 +".?G#&@@&&&&&&&&##BBBBB###B##BJ^..^:...  :?7.    .:^^..        .:!J^    .:^:::^~~^^::.......:::.\n"                                                                                                      
                                                                                                               +".?#&&&&&&#GPPP5PPB#&&&&&#BPJ7~~^~!!^:::..!77^     .:^::!J!.   .:~7YP. ..^~~^~~^:.... ..:::::^^^^^..\n"                                                                                                    
                                                                                                              +"!B@&&&@&#P55J????77?JJPBB57!7JJ??!!~~^:.:!5G!.       .  .7P!.:~!!7P5^.:!~^~^:...:^!?JJJYYJ7~^^7~~!7??7~.\n"                                                                                                
                                                                                                 +".^          ~P&@&&@@BGGP5YJ?77!!7!!~~~~7?YYJ??77!:.:JY55J^.      .^.^?5PY77!7YG5!^^^^~~^:^!?5P5YJ?777~^~~!!::^!~~!???7~~!!^.\n"                                                                                         
                                                                                                  +"!5Y~^.     7#@@&@@&#G5YYYJ7!!!~~~~~~~^::^~!!77?7!^^!Y5J~^!?J?7!!7?YYY?!!77J55J!~~~~~~7?Y55J?~:.      .:~7?!~!7~.  ^J?..:^!777:  ..\n"                                                                                  
                                                                                                   +"7B&&#5?~^:~#@@&@&&#B#&&&&&G?~^^^::::::^~~^^^^^^^^^^~!77?J?????????!!7?JYYJ7~~~~!7JY55YJJ~. ...:^~~~7Y5Y7^....77~^J7.     .:7J?!^^:..           ......\n"                                                              
                                                                                                    +"~5B#&&&&&&&&@@@@@&&#####&&&B57~^::~~^:::^^^^^~~~^^^^^~!7??7!77???YYJJ?!!~^:!?JY5YJ??7!^~77JYYYYJ?77!~^:^!??7?YPP7:..   ...!Y5J^.:^:.         ::.      ......\n"                                                      
                                                                                                     +".Y#&&&&&@@@@@@@@@#PG#BBB&&&#B57~^^~7?JYYJ??7?????JY55YJJJJYJYJ?7777!~~~!7JY5Y7~~7YPPYJ?77!7777~~~~~^^^^^!!!7!7Y7^.... .:!YY?~:..:7^.       .^:..:^^^^~~^^^:::.\n"                                                   
                                                                                                    +"~YGB###&&@@@@@@@@@#GGB#BBBGPPGBGY7~~!!~~7YPGGGGGGPP55PYJYJ?777777!!~~~7JYPP5!~7J5PY?!^^~!7JJ?777???7?777??7!!!~^!!^::^~:^!JJ?7~^~7?~....  :~7~~7777!~^^^:^^^^~^\n"                                                   
                                                                                                  +"^YJ: !G#@@@@&&@@&&@@&GPPPY7!!!?YPBBGY!~!??77777?JYY55YJJ?7777!!!!!!!7?YPGBBBPYYJJ?7~~~~7?JYJJ??????????J???777?????77!!7?JY5555Y????~~?GG5J?JYJYJ?7~^:......^~:..\n"                                                   
                                                                                                 +"^J.  :G@@&&&@@&@@&@@&GY7~~~~!JPP55GGP5P5?!?J5GGPY?777!!!!!!777??JY5PGBGGGGPYJ7!~~!!77?JJYYYYJ???JJJJJ?!!~^::^~!????JJJ?????JYY55PP5?^. .^^:::::...\n"                                                                   
                                                                                                +"~7   .B#BB#&@&&@@&&@@#BPJYPBBGPYJ?7!~~75GPJJ?!7YPGB##BGPPPPPGGGBGGPP5YJ?7777!!7??JJJJYY5YYYJJJJJJ?7~^:..:~!7???JJJJJ?????????7JJJJYJ5G~.\n"                                                                              
                                                                                               +".!!   ?@7:G&&G5#@@&&&&#BBBGY!7~:.^^~!JG##PJJBBPJ???JJYPGBGPPP55YYYYJJ?Y55555555555PPPP55YYJYJ??J?7~^~!7JJYY55555YY5555Y55YYYJJ??7?J?7!!G?.\n"                                                                             
                                                                                                +"~!   :#?!#@5^7B@@&&@@#57?J?!:...:!J?PGGPPY75#&#BPP5YJJJJJJY5555555PPGGBBBBGGGGGGGP5YYYYYYJ?Y5YJJJJY5PPP5PPP555PGGBBBBBBBBGGP555Y??77~:JJ~\n"                                                                             
                                                                                                 +".:   .!5B@J.~5&@&&&@@GJYPB#G7..:::..:~^^::J#&###B#BGGGBBBBBB#######BBBBBBGGPP55YYYYYYJJYYY55Y55PPPPPPPPGGGGGBBBBBBBBBGBBBGP55PY55J777Y7.\n"                                                                             
                                                                                                        +".5@? :G&#&#&@&&BYG#B5?~....:..::..:JB&#GGBB#&###BGGGBBBBBBBGGGGGPP555555P55YJJY5PP555PGGGPPPGGBBBB#BBBBBGBBBBGGBGBBP5J~.^5PYJ77:\n"                                                                              
                                                                                                         +":&G~5&&#&&#&&&&GJ?J?J7:..:....:..^YB&&GPGG&&########BBGGGGGGGPPPPPPPPP5YY5PPPP55PGBBGGGGBBBBBGPP555GGGB#GPBBGGBBGGBJ:.:!JYYY?~.\n"                                                                              
                                                                                                          +":G@@@@&&&&#&&&@P?7?J?!:::....:..~YB#&BPYG#&&&&##B#######BBBBBBGGPPPPGGGGGGPPGBBBBBBBGGPPYJ7!~^^^::..:^755^5BBGBBBBG57~!?J557.\n"                                                                               
                                                                                                       +"?G?7#@@@@@@@&&#&&&&BJ77J55Y7~~!~:..!YB&#&BY5#&&&&&&&&&&####BBBBBBB####BBGBBBB####BBBB##BBBBBBBBB##BB#BP7.  ^G:~GBBBB#Y^..!557:.\n"                                                                                
                                                                                                      +":5&@@@@@@@&&&&@&&&&&&B5JJ???JJ7!^:.:?G#&B&#GJ5#@&&&&&&&&&&&&&&&&&&&&#####&&&##BBB###&###&######PY7~^::~7?YJ..J.^GBBBBGYJ?Y5Y7~:\n"                                                                                 
                                                                                                       +"P@@@@@@@&#&##@@@@&&&&&#BP5J!~!!^:!5BB###&&#PY5#&&@&&&&&&&&&&&&&&&&&&&&&&&&&###########BGPPYJ?????7~~:    .~7^7P#BBP5JJJ?!~~7?!~^:.\n"                                                                             
                                                                                                   +"7P~5&@@@&&@&G&#B&@@@&&&&##&&&#B5YY5B##B###B#&##B5Y5B&@&&&&&&&&&&&&&&&&&&&&&###&&&#&&&#BG5J?!^^::..    .^!.  :?PG##G5!.  .^7^.  :~7~~^:.\n"                                                                           
                                                                                                  +". :Y&@@@@@@@@BP@&##@@@&&&&&##&&&&&&&&&&#BB##G#&#&&#P5Y5G#&&&&&&&&&&&&&&&&&&&&&&&&##GPY?!~:. .    .. ..    7!7PPP5Y!:.        7^    ^~~^:\n"                                                                            
                                                                                                 +".#?7G@@@@@@@@#5P@@#B&@@&&&&@&&#&##&&&&&#GGB##B#&#B#&&#BPY5PGB#&&&&&&&&&&&&#BPJ?!^:..             :.      .~5J!^:.            .     :!^:.\n"                                                                             
                                                                                               +".:~Y@@@@@@&&&&&&#B&&#G#&&@@@@@&&#&&&##&#5PBG##BB&&GJP&&&&&&BGP55555PPPPP55YJ7^.        ..::^~~~!7?5Y?~~!!^:^:\n"                                                                                                          
                                                                                                +".~YG&@@@@@&##&&&&&@&&##&&&@@&&&#&&&#BPJ!?G###BG&&P~7G&&&&&&&&#BBBBGGGGPPPP555555555GGPPP555YY55J!^::::::::.:^^:.\n"                                                                                                      
                                                                                                   +".5@@@@@&&&&&&#B#&@&&&&&&&##&##G5555J7~J&&#BG##P~:~P#&&&&###&&&&&&######BBGPP5YJ??7!!77~^^:.                  .\n"                                                                                                     
                                                                                                   +"B@@@@@&&&&&@&GBBB#&&&&&&###&##PYY5Y7~!5&&#BGG##!..:?P#&&#B5?7J5GGBBBGPP5YJJ????77~:...\n"                                                                                                                             
                                                                                                   +"P#5B&@@@@&&@&B#BP5GGPPGBB#&&##GY55J!?G#PGB#GYP#G7...:!5#&&&BY!:.................^^::          ....\n"                                                                                                                 
                                                                                                    +"^5#&@@@@@&###BPJ?Y55G5JJB&&&&#BGPPGPJ!!PBBBPJ7PBP~:..:!JG&&&&B57~::....    ..:^77!!:.:^:^~~^.\n"                                                                                                                     
                                                                                                     +":&@@@&&&&##&&BPPYP##B5Y&@&&B##P5P5^::^YPP#B57^^~!!~^^:..^75B#&&&#GGP5YJJJJJ5PPPPP5Y?!^.                                         ..\n"                                                                               
                                                                                                   +".?&@@@&&&@@@@#BBBB5B#Y5PBPBBPY?7!?5P!^:.7Y5JG#B5!:........  .:^!?YPBBBBBBBGBBGPYJ?77~:..       ...                               ..\n"                                                                                
                                                                                                   +"~JYBBGP&@@@@@&&&&&BBGY?JY5J?J!~~^~?J!^...^7?~!5B#GY?!~^.:^~~~^:..:::^~7?7~^:^^^^~77~^..^.                                      ...\n"                                                                                 
                                                                                                       +".:.~G@&&&@&&&&@&GY5PPB?^!^~!~~7YJ?7!:..~!^::!JY55P55555555Y?!^^~!77~:..^!7777!^::::^::.   .  .......                      .^:.\n"                                                                                 
                                                                                                           +":7Y7P@&##&&&&&B5YBG55~~7^^^:^:~?J7~::^~^:.:^^^^~!7???JYYJJJJJ7~^^!?J???!~:...^~:~!!^:......           ....          ..::.\n"                                                                                  
                                                                                                          +":!?Y#&@@&&@@&&@B5PBP5P5Y?:::^^^~7JJ!::::::^^^^^^~~!!!7?^.:JYJ?7!7?????7!^^^^^~!77!~^^^^.... ..............          ..:::.\n"                                                                                  
                                                                                                           +".~~P#&#&&&@@&#BGG#P5B5?~...^:^7YJ?!!!7!!~~~~~~~!7!7?7^   :~77??JJ?7!!!!!7??77!!~^^!!!!!^.............               :^^::.\n"                                                                                 
                                                                                                              +"::^B##@&@@@@@@BYYB!.::..:^!J5YJ?~^^^^^^^^~~~~!7J~.        .^7J?!!7??????7???!::~~~!7~^::......                .  ^~^^:.                          ......::..........\n"                                     
                                                                                     +".:.        ..      .~^.  .J&###BB#&&@&#P5B#~^!::::...^!JY?!!~~^^:::::^~7~:          .:!7777JJ??77!777!!!~~~~^~~~:.            ..........  ^~^^::.                   ...:::::::^!!....::::...\n"                                     
                                                                                     +"^P&G5J~77!:G&^ .~7.7@: :Y#&#GB#B5PG#&&#G#&#??J^..:..^!YYJ?7777???????J!.       .......:::::~!!777!~!?Y?7?77????7!^:::::::^^^:::^^::...    :^^^^^:.            ....::::::::::^~?~. ..:....\n"                                        
                                                                                     +"!#@@@@#PG@&&@&B#&#GB@GPG###BGB#BY5G5B#&##B5PP?:..:^.:7JYYJ7~::^^:.:~!:.    ......::::^^::::..::~7?JJJ7:.:^~~!!!7J!~~^^~^:::::::......     .:^^^^^:.        .:::::::::::::^^~!~:...::::.\n"                                          
                                                                                    +"~B@&&@@@@@@@&&&&&#&&&#&&&&&#BB##BJYGP5PGB###&BJ^~!7?:.^!!7YYYJ7!!~7~:.    .......:::^^~^^::.....^7J???7~^~~!!!~~!7~:^^^~^:......            .^^^^::^:.   ..::::^:::::::^^^^:::...:::::\n"                                            
                                                                                   +".G&&&&&@@@@@@&##&#B&##B##B#&&&&&&#YP#&GGBY5GB#GJ5GG5~77::^~!J5YJJJY7^..  ..:::....:^~!!!!~:.....:!JY??77!!!!~~~^::^:....:^^:..               .:^^^^^^^:::::^~~^::::::......    .\n"                                                   
                                                                              +".^!!~!#&&@@@@@&@@@&&@@&&&#BBBB##&&&&&&&B#&&###G5PBBBGBBG55GP?~!?YYYYY?JGP5YJ7~^:.... .:~~!!77!~:...::~?YY?7!!~^^^^^^^::^:....:^^^:^..              .:^^^^^^~~^^^^^::....\n"                                                                
                                                                               +":B@@&@@&#&&&@&&&&@@@@@@@&&##BG#GB&&&&#####&&BGGBBB#BGBBBGGP55YYYYYY77?JPP555YJ~:.....:~!!77!~^:...::!7?7!~~^^^^:::^^^^~^::^^^^^::^~^:..            .:^^^~~^^^^:::.\n"                                                                     
                                                                              +":75#@&&&&#BB###&&&@@@@@@&@&&&&&&##&&#BGB##B5PB&GYG##BPGGB#G5Y?J5J77??JJYGG5555Y7^..:::^~!!!7??~^:..::~!!~^^^:::...:::::::::^^^^^:::^~~^:..           :~~^^^^^^^^^:\n"                                                                      
                                                                            +".75G&&&@@@@&&&&&&@@@&&&&&&##&&@@&&&&&&BB&BP5YJ77##5!G#BBGPPPGG55PGPJ7~77!7?JJJYYJ7^.:^::^~!!!7JY7^...::^~!^::::::.:::..:^^^^^::::^^^:^~~^::....      .:^~^^^^~~^:^^^:\n"                                                                     
                                                                          +"!GB#&@&&@@@&BB####&@@@@&###&&###&@&&&&&@#GPBBGB###&P?7#B5YY5PJYPBBBBGBG555J~~~~!?J?!^:::::^!???777~:..::.:~!!~^~!~~~!!~^~!!~~^:..:::::^~~~^:::....   ^~~!~^^~~^~^^^^^^^^.\n"                                                                   
                                                                           +".~5#&&&&&B###BGGB#&&@@@&&&&#&&##&&&BGG#&&&#Y~~?JJJJYG#BPJJ?7!~7J?5GP5YJYYJ!!!!!77!~^^::::^^!??7!^.       .^~~!7??77?????7~:...::^::::^~~^::::...^!7!!!~^:..~~^~^^::^^~~:\n"                                                                   
                                                                           +".Y~?B@&#GYJJ5GB##&&&@@@@@@&##&&#BG#BBB&##&&&B!?G&&&&&###BGGP5JJY7!7YYJJY?!~~~!!!!!~~~^:.. .:^~~:.  ..........:^~!?J?7!~:...:^~!!!~~~^^:::::..:~!JYJ?!^:.   ^~^^^^::^^^^^:\n"                                                                  
                                                                             +".G@@&&BGYJ5Y5GBBBBG#&&&@@&##&&B5PBBPBB#&&&&BGPGB###B#####BBBBGGPY!777!7?!!!~~~!7!!!~.      ..:::^~7!!!7!~~!!^:..::...  .~?JJJJJ?7~^:::....^?Y5YJJJ^.     :~^^:^^^^::^^^.\n"                                                                 
                                                                             +"!#@&&&&#BG5PPG#&BJYPBB#&&@@&####GYJ5BGB#&&&&&#GPPGGBBBGGGBPY7!!!~^~~~^~!~~^:^~!?77!^.   ..:^~!~!!7???JYYYJJ?77~^.... ..^7?J?7!!!7!!~^^^^!7J???Y?!!^.      :^^^^^:^^^^^~^\n"                                                                 
                                                                             +"^G@&@@@@@@&&&@@@&YYYJJG&&@@@&&GPBG5J5GBBB##BBGBBBGPPGGPGGY!^::.....:~~^::..:^~!!~^:.  .^^^:^~777?J????JY555J?77!~~~^..:~?JYY?7~~^^~!7J5YYY?7~:^~^:~~.     .^^^^^^^^^^^~~.\n"                                                                
                                                                            +".J#&&&&&@@&&&&&&@&GP5Y?PGPBGB&&&#BGGP55PGGBBBGGGBBBGP5PP5J777!~^:::..:^~^::.......   :^^::^~7?????7!~~7?JJJJJ?!!!!!~!777!7?JYYYYJ?7?JYY5YJ?!:. .^~^~~^    .^^^^^^^^^^^^~!.\n"                                                                
                                                                         +":!5B######&@@@&@&&@@&&&#GGBGG5YYG&&&&&BG5PG5PGPYYJJYYJJ5PP5YJJJ?!~^^^^^:.:^~~~~^^.. ..:^^^:^!??777???777?J??JYYJ?~~~~77?JJ7~~~~!J555PGGP5Y?!^:::...:^^^~~.   ~!^^^^^^^^^^^~!.\n"                                                                
                                                                      +".!YB##BBBB##&&&&&&&&@@@@@@&&@@&#&GYY5GBPGB#GP5Y55PPP55JJY5GGP555Y?77!~^^!~:.:^?G5Y?!....:^!!!!77?JJ?7JJJ?7?YYJJYJYJ?!!7?J55YYJ?!^::^7JJY5PPP57~::^^::.:^:!!~.  :^~^^^^^^^^^^^^!.\n"                                                                
                                                                   +".~YPGBBBBBBBBB#####&&&&&&@@@@@@@@@@@#PP#GY7?5B&#GGPYJJJY555555555P5J?777777!^^^~?5GGP?^:...:^^~7??77?55YYJYJ7!~^~!!!??!^!?YJ?JYYYJ7!~^^~7!!7JY55J?!~^:::::^^~~^..:~^^~~~~~~~~~~^~!.\n"                                                                
                                                                +".^J5PGGGGGGBGBBBBBB#####&&&&@@@@@@@@@@@@&&&###??GB&#GGP5JJ??JJ?J?Y5P555YJ?77!!~!??!?JYP57^::::^~~?JJYJ??J77J5Y7:    ..^7??7!7J?7?Y55Y????J?7^^~!~!?J???!::::^^^^^^^~!~^~~~~~^^^~~~~~^.\n"                                                                
                                                              +":7Y5PPPPGGGGGGGGGGGBBBBBB##&&&&&&&@@@@@@@@@@@@@57!77J5G#BPP5YJ???77?JJ5555YYYYY55P5J77J5G5?^::^~~~7JYYY55YY?!7!^:       .:::..:!?JYY5YYJJJY5Y7!!7!:..7?JJ7^^^::^~!!~7!~~!!!!~~~~~~~!!~.\n"                                                                 
                                                            +"^YPP5PPPPPGGBBBBBBBBBBBBBBB#####&&&&@@@@@@@@@@@@&B5Y5YJJ5PB#GGP5????J?7!7JYYYY55P555Y?J??J55J~~^:!~~7?JY55Y7!~^:.          :^~~^:^77!!?JYY55JJJJ??7!7. !JJ?7~^^:^!JJ?!!!!~!!7!!!!~~~~!!^        ........  .:...\n"                                           
                                                         +".!Y5P5PPPPPGGGGBBBBBBBBB#######&&&&&&&&&&&@@@@&@@@@@@@@@G5B@J?PGGP55YJYJJJY?77?JJJ?JY5Y?!7Y5JYJ!^~~^!77?YYY55Y7^:...          ^7????7?7~^!?Y555YJJY5Y?!!^:!7!~^^^~7JYY?7!!~~!!!!!!!!!!!!~~^ .......::^^^^^::...:::..\n"                                         
                                                       +":7PG55GGGPGGGGGGGGGBBBBGGGBBB####&&&@@@@@@@@@@@@@@@@@@@&@@BB&@J!7~JPGBGP5YJ?J?77?JJ??JYJJ7!!?J?7~^.^!!7~^!J5Y5PP5J7~~!!^.     ..^~^^!?JJYJJJJY55PPP5YYYYJ777!^^~!?JYYY?7!!77!!!!!!!!!!~~~^^:^:^^~~~~~^^^^^^:^~!^...:::.\n"                                        
                                                     +":JPGGPPGBGGGGGGGGGGGGBGGBBBBBBBBBBB##&&&@@@@@@@@@@@@@@@@@@@@@@&P!~7JJJY55PPPP5JJ?????77777~^^^::::::..:!77~^!?Y5YY55Y??J?7^:::..:!7!^:!JJYYYYYY5555555YYJ?7777?JY55YYY5J!!!!7777777!~~~~~^^~~~^^^^^^^^^^::::::..^7!^..::::..\n"                                     
                                                   +"^JPGPPGBBBBBBBGBBBBBBBBBBBBBBBBBBB######&&&&@@@@@@@@@@@@@@@B#@&@&&&&&&!:^^^^~75PY?77777777!!~^:..::::....:~?Y?~!?Y55555YY?7!!!7!^~7Y?7!7?JJJ???7????7777???JY555555YYYYJ?7!~~!777!~~^^~!!!!!~~~~^^^^^^^^^^^^:::::^!7~:......:....\n"                                  
                                                 +":J5GGPGGGBBBBBBBBB#############&&&&&&&&&&&&&@@@@@@@@@@@@@&G7. 7@@&&@@&B5YJJY5Y?~~7JYYJ??J55YJ?!~^:^!?7!~~:..:7Y5J??YY555YYYJ?!~~~?JJJYJ?????77!!!77??777JYPPP5PPP55YYYYJ?7?7!!~~~~~^^~~~!!!~^:::.....::...::^~~~~^!!^.....::::.......\n"                                
                                                +"!5GPPPGGGGGGGGGGGBBBBBB######&&&&&&@@@@@@@@@@@@@@@@@@@&B?:     7@@&&&&&&&@&&&##BP?!!~!JPP5J!~~7JYJ?7?JJJ?J7~::^!JY?!??JYYYYJ7!!?777JJ?JYYJJJ??????JJJJJJY5PPPP5555555YJ7!~~~~~^^^~!!!!!~!7!^.                  ........::.:::..\n"                                       
                                              +".?5PGGPPGGGGGGGGGGGGBBBBBBB####&&&&&&@@@@@@@@&@@@@@@&#5~.        7@&&&@&&&&&&&##&##G5P?!!!!^:!7?PP55?77????YJ?7^::^7?7^^^!7777777!!!!!777777??77777?J?7!7?Y5PPPPP5YYYJ?!~^^^^^~!!!!!7!!!??!^.                          ......\n"                                           
                                             +".7PPPGGGGGGGGBBBBBBBB#######&&&&&&&@&&&&&&@@@@@@@@#5!.            7@@@@@&&@&&&&&&#PGPPBBG5?^^YGGGG5Y5J?7????JYYJ!~^^:^!!^::...::^^:^^^~!!7!!!77!777!77!~!?JY55YYYJJ?77!~~!!7?JYJ??7!!!77!^.\n"                                                                              
                                             +"^5GGGGPGGGBBBBBBB######&&&&&&&@@&&@@@&&&@@@@@&#5!.               :5@&&@@@@@&&&&&&#P55YYPPY5PPP55PJJY5YJ??777?JJ?7!~~:.:^^~!!~:.   ..:^!77!!!!7^:~!!!~^^~7JJJJ?7!!77???JY55PPPYJ777?777?7~.\n"                                                                               
                                             +":YBGGGBBBBBBBBBBB#######&&&&&&&&&&&&&@@@@@#J^.                   J&&&&&&@@@&@@&&&B5JYYJJJJ!7J55J?YYYYYYJJ?7777?J?7!!^::..::^~!~^::^~~!77???77!~^^:^~777?JJJJJJYYYY5P5Y5555Y?7!~~!!!7?JY555J^\n"                                                                             
                                             +".!#BBBBBBBBBBBBBBBBBB###&&&&&&&&@@@&@@@@@#?.                    !#@&&&&&&@@@@@@@#PY!7YJ?7!~~7JYYJJYYYYYYJ?7!!~!?!777!^.::.....::^^~~~~~~~~!~^::::.:!JJ???JJJJJYJ???J?????7!~~~~7?YPGGBBGGG#BJ.\n"                                                                           
                                              +".?B#BBBBBBBBBB####&&&&&&&&@@@@@@@@@@@@@@@@P:                 .J&&&&&&&&&&@@&&&@B5Y!7J777!~~~7JJJJY5YYYYYJ??!~~^^~7J!^:.........::::::^^~!!!!~:...:~77!!777??77!7777!!!!~^^~!!JPGBBBGBGGGGBBB!.\n"                                                                          
                                               +".J##BB#####&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@&P!:..           .!G&&&&&&&&&&&&&@@&&#Y!!??!!!~^^^!?YYYYYYYYY5YJJ?!^:^^!7^::...:~~!~!!~^^^~~!!!!!!!~^::.:~!777!!!!!~~!!!~!!~~~~^!7!!JPGBBGGGGGGGBB7.\n"                                                                          
                                                 +"~B&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@&&#B7.     .7#&&&####&&&&&&&@@@@&G?^7?7!!~^^~!7JYYJY55YY55YYJJ7!~^::^:::.:^~!!!!!!~~~~!!!~~^^^^~!!~^^~!7777777777!!!!!!~~~~~^:   ..^75GBBBBBBB7.\n"                                                                          
                                                  +".J#@&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@&J      ~G&&#####&&&&&&&@&@@BJ!!J?~!7!~~!!7?JJJY55YYYYYJJ??7!~^::..::^!7?777!!!!!!!~~^^:^^~~~!!!!7???7????JJ???77!!!7!!!.         .^75G#BJ.\n"                                                                           
                                                    +":Y#&&&&&&&&&&&&&&&&&&&&&@@&&&@@@@@@&&@@@@@&@@P        ^JG#&&&&&&&&&&&&@@G7~JJ?!~!777!!!!!7YYYYJJYYJJJJ??77!^::..^~!7777?J?77!~~~~~~~^^^^~~~~~!777?JY55YYYYYY???77?7:              .^~.\n"                                                                            
                                                      +".7B&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@&&&&@@&&@&#557.       :!JG#&&&&&&&@@&Y~!YJJ?~^^~~~^~~!JYJJJJJJYYYJJJ???7~:~~^^^^!7777777???7!!~~~~~~~~~~!!~^^~7?J5PP5555YJJYYY?~\n"                                                                                               
                                                         +"^?G#&&&#&&&&&&&&&&&&&&&@@@@@@@&&&&&@@@@@@@@@@B^         :P&@&&&&@@#~~??!^^~~^^:^^^^^!?JJJJYYYYYJJJJJJ?!^~!!~~^^~~!7??77???777!!!~~!!~~!7!~^~~!777?JJJJYYY55Y^\n"                                                                                                
                                                            +".~P#####&&&&&&&&&&&&@@@@@&&&&&&&@@@@@@@@&@@B~        .P@&&@@&&&B^!?~!~^::^^.:^^^^~JYJJJJYYJJYYJJY5?!~~!!!~~~~~!!7777???JJJJ?7!!77!!7?7!~^^~!!77!!!!7?JY5:               .\n"                                                                                 
                                                               +"J&&&&&&&&&&&&@&&&@@@@&&&&&&&&@@@@@@@@@@@@Y        .G@&&@@&@@#!~7JJ?!:.^!~::~~~~~?YJJJYYYYY55Y5YJ??~^~!!!!!!!!77?????JJJJYJJJ?777777!!!7777!!~~~!!!7!:               ::......::.\n"                                                                        
                                                               +":5&&&&&&&&&&@@@&@@@@@&&&&&&&&@@@@@@@@@@@@B        .P@&&&&&@&#J^^!!^:::~?~^:~^^^^7JJYYYY55YY555JJY5!::^^:^^~~~~!77?JJJJJJYY55Y???777????7!~~~~~~~~~^.              .^!^.       .. \n"                                                                      
                                                                +".!B&&&&&&@@&@@@@@@@&&&&&&@@@@@@@@@@@&@@@#:       .5@@&&&&@&P5J7^:^^^!7~:^~^:::^!JYYYYY55YYY5P5JJYJ!:..::::^!7?7?JJYYYYYYY55PP5YYJJ?7!~~~~~^^~!!~.               .~!.        ::::.\n"                                                                     
                                                                  +"P&&&&&@@@@&&&&&&&&&&&&@@@@@@@@@@@@&&@@B.        .!P&&@@@&P5YYJ?7!^^:^~!7!!!~^~?5JYYY55YYYYY55Y?5J~^::::^~7!~~777?YYYJ??JJJYYYJJ?7!!!!~!!7YG#&#G5?:.           :?:  :.   ^!~\n"                                                                         
                                                                  +":G@&&@@@&&&&&&&&&&@@@@@@@@@@@@@@@@@@@G:            .~YG&&GP5J7~^~~~!77!^^^~~^^!?YYY5555JYYY5P5YYYJ7~^::::^^^^~~~!7???????77?JJJ?????JPB&&&#####BBGPY!.       .!? .^^   .~:\n"                                                                          
                                                                  +"5&@&@&&@@&&&&&&&&&@@@@@@@@@@@@@@@@@@Y.                 .~5PY!:~!?J7!~^::::::::^~?YYY555J5P55PPPYYY7^::::^^^^^^^~~!7?JJJJJ??JY5PGB#&&&&&&&##BBBBBGPGGPB^      .77 ^!..^~~.\n"                                                                          
                                                                +"^P&@@@@&&@&&@&&&&&&&@@@@@@@@@@@@@@@&P^                    .7Y7^~!7~:::::::::::^:::~J5JY55555YY5PPYJJ?^:^^^^~!?YPGBBB###&&&&&&&&@@&&&&&&&#BBB###BBGGBP5G&!   .:~JPPYJ7^~:\n"                                                                              
                                                               +":5@@&@@&&&@&&&@@@&&&&&&&&&@@@@@@@@@@Y^.                    .~!!~^~^:^^^^:::^^::~^^^:JP?JY5555Y55PP5Y5GJ^::7P&&&&@@@@@&&&&&&&&&&&&&##########BBBBBGGGGPPGB^.7PPPGBP7:\n"                                                                                   
                                                                +"~#&@&&&&&@@&&&&&&&&@@@@@@@@@@@@@@@@@&5^                    :!!~^^::^^^^^:... .~5G5YPPYJYYYY5YY5555PPPPJ^YPGB#&&&&&&&&##########BBBBBBB##BBBBGGGGPGGPG##YYJ!. .YY^\n"                                                                                     
                                                                 +".5&@@@&&&&&@&&&&@@@@@@@@@@@@@&&&@@#7                      .7!~~^:^^..         YBPGBBY?J55YY55YY5555JJYGGP5PPB&&&&&&#####BGGGGGGGGGGGGBBBBGGGPGGGPG#&&&B!..^J5J.\n"                                                                                      
                                                                   +".!5G#&&@@&&&&@@@@@@@@@@&&&&&@@@&@P                       .~!~:..            ?BBGP55YYYJY555JJY5P5J?JP5PGPB##&&&&&##BGGPPPPPPPPPPPPPGGGGGGPGGB####&&&Y77J7:\n"                                                                                         
                                                                      +".Y@@&&@@@@@@&&&&&&&&&&&@@@@@@@P                        :~:               !PPJ?JY5YJYYY55YYJYYYYYY55PPPG#&&&&&&##BGP55555555555PPPGGBBBBB##&&&&&@&G7:\n"                                                                                            
                                                                       +":!B@@@@@@@&&&&&&&&&&&&@@@@&&7.                         .                .!YJJYJ5555555YY?77?JY5PPGB##&&&&&######GPP55555555PPPGGB#######&&&&@@&&&@&J\n"                                                                                           
                                                                        +".Y&@&@@@@@&&&&&&&@@&@@@G?:                                              :JYYYJJY5JJJY?7!!!7?J?7J&@@&&&&#&&#BBB##BGPPPPPGGGBBBB#####&&&&&&&&@&&&&@&Y.\n"                                                                                          
                                                                       +".7#&&&&&&&&@@&&@@@@@@#J:                                                  ^JYJ??????7!~~!7!^..   B@&&&&&&&&########BBGBB###&&&&&&&&&&&@@&#&&####&@B^\n"                                                                                           
                                                                    +"7B&@@&&&&&&&&&@@&&&@@#~                                                      ~?J?!!7777!!!~:.      ^G@&#B##########&&&&######&&&&&&&&&&&##&&&#GBB&@G^\n"                                                                                            
                                                                  +"~G&@&&&&&&&&&&&&&&&&@@@Y.                                                        ^?7!!777!!^.          .?B&&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&BPBB&#?.\n"                                                                                             
                                                               +".!G@@&&&&&&&&@@@@@&&&@@@&~                                                           ^7!!!7!^.               :?#@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&####BB#&G^\n"                                                                                               
                                                              +"~G@@&&&&&&&&&&&&&@@&&@@@P.                                                             ~77!7.                  :G&@@@@@&&@@@&&@@&&&&&&&&&&&@&#GBBB#&&@5:\n"                                                                                                
                                                             +"~@@&&&&&&@@@@@@@@&&@@@@&!                                                                :~~.                   7@@&&&&&&&&&@@@@@&&&&&&###&&&&&#&&&@&@@~\n"                                                                                                 
                                                            +"^#&&&&&&&&&@@@@&&@@@@@@G.                                                                   .                   .P@&&&&&&#&&&&&&&&&&####BB##&&@&&&@@@&BGJ~^YJ?JBG?\n"                                                                                        
                                                            +"7@&&@&&@@@@@@@@@@@@@&5^                                                                                          !#@@@@&&&&###&&&&&&&&####&&&@@@&&&&#GP#BGPB&&#GBB7:\n"                                                                                      
                                                            +".?P#&&&&&&&#BG5YJ!^.                                                                                              .:Y#@@@@@@@@@@@@@@@@@@@@&&&&&&@&&&BBBB7~!^7YJ7P#PY!.\n"                                                                                    
                                                                 +".....                                                                                                           .~J55Y55GBGP5J!75&&&#G5PPP55B&@P##?JYYY?7?~JBBY:\n"                                                                                     
                                                                                                                                                                                                 +".P&BPG##B##BYYBP5G&Y?5P5YY7?JY5J~\n"                                                                                    
                                                                                                                                                                                               +":?Y#&&&#BPGP5##P7&B5PBBP?GB5?77JGJ:\n"                                                                                    
                                                                                                                                                                                               +".J&@@@@G5##Y?#&G?B&#PP5YBGY?!7YPY?~\n"                                                                                    
                                                                                                                                                                                                +"^&@@&#G55B##&GYYPB#&BP5GJ?YGPPG^ .\n"                                                                                    
                                                                                                                                                                                              +".!B@@@&&BG5555JYPBP#@@&&&&GG&@B7.\n"                                                                                       
                                                                                                                                                                                              +".J&&@@&@&BGBG5BBGPB&&&@@&&BGP#&P.\n"                                                                                       
                                                                                                                                                                                                 +".J#@@@#5GGPGGPG&##@&&&@#5?JG##^\n"                                                                                      
                                                                                                                                                                                                  +":?GB&@@@@@&&&&#P5&&&&&&#5!5#P#Y.\n"                                                                                    
                                                                                                                                                                                                    +".^YP!:!?.7B@#5Y5B#&&&&#GY5&GGG.\n"                                                                                   
                                                                                                                                                                                                              +":Y###BGB&&&&&&BP5BBPG!\n"                                                                                  
                                                                                                                                                                                                               +".P##@BGB&&&&&&BPPGBGBJ.\n"                                                                                
                                                                                                                                                                                                                +"^G@&#BG#&&&&&&&BP5GB#G^\n"                                                                               
                                                                                                                                                                                                                 +"Y&#&#BB&&@&&&&&&B5PBBB7.\n"                                                                             
                                                                                                                                                                                                                 +":5@&##B#&&&&&&&&&&GPBGGY.\n"                                                                            
                                                                                                                                                                                                                  +"~@&#&&B#&&&&&&&&&#BGPGBG:\n"                                                                           
                                                                                                                                                                                                                  +".Y&&#&&#B&&&&&&&&&#BBPG&7\n"                                                                           
                                                                                                                                                                                                                    +"B@&##&#B#&&&&&&#BGY5B@!\n"                                                                           
                                                                                                                                                                                                                    +"~B@@&###BBB#BG5Y5PG&@#:\n"                                                                           
                                                                                                                                                                                                                     +".7G&&&&&BG5PGB##&&@&7\n"                                                                            
                                                                                                                                                                                                                        +".^!JP#&@@@@@@@@&Y\n"                                                                             
                                                                                                                                                                                                                              +"...^!7?77:\n"); 
					break;																																																							  
					case 2:
						enemyimage= ("\n" 
			
																																		   +"....\n"                                                                                                                                                
																															  +"...             ..\n"                                                                                                                                               
																					 +":~7!^..                                ::.                ...\n"                                                                                                                                             
																			    +".~~~!J?7!77?7.  .!:           :.          .::                   .::.\n"                                                                                                                                           
																		    +".:^~7~~!77777!7~:.  .!7:   ..^~^.            .!^                     :::.\n"                                                                                                                                          
																		   +".~7!!!~7?7:.. ... ..:~^.    ^7!^:.  ..        ~^.     .   .:^~^       .^^.\n"                                                                                                                                          
																		  +":77~^^~!??7!^^~77777??77!!~:.:^~!!!^:.         ~:    ... .:~.  ..  .  ..^~:\n"                                                                                                                                          
																		 +"^!!^::~!!7??77??77JJ!777?J??7!!!?77!^.         .!:.  .....^^:   .!:.~  .::^~. .:\n"                                                                                                                                      
																	    +".~!~~^^~7!~~!!7??7!7?!777?J?77!7??J!.    .^.     ~..  ~:.^^^::   .~::~^..::::^:7~\n"                                                                                                                                     
																 +".::..:^^^^^^~!~^~~!!!!~~~~~~^!77!!~~!!!77!^. .:~7.    .~7:..:?!^:~~^...  :^~!J^ .:..~J!.\n"                                                                                                                                      
															   +".^!777777!~~~^^~~^~~~^^^^~!^^~^!!!!!7????!^^~~~^~~.      ^5^..!!J?~YJ7~^^~75GY~P~ .:.:!7:\n"                                                                                                                                       
															   +".~!!~^~~^^^~~~~~^^^^^^^~~!!!7?77!!!7??7?J?^!!!!~~: ..    .?^.:7!7?JPBGJ77?J5Y7:5~.:..:^^.\n"                                                                                                                                       
															  +".^77~^~!^::~~^^^^^~^:::^^^~~7??7??7?????JJ?!!77!7^:^^.    .!!:.!~!??JP5!~^:.:~~:?~:^....~^.\n"                                                                                                                                      
															  +".^~^~!~:.:::^~!~.... .:^^!777!^..:^~~~!??JJJ????7^..       ~!:.!?77775Y~^!~.  .~J~::::..77:\n"                                                                                                                                      
															  +".^!^^:...:^~!77~.    . ...::.  ..^!!~::^~7?????7!^..:.     ^7^.^JJ?J?5Y^:!J?. .~7!^^:..:!!^.\n"                                                                                                                                     
														   +"..:!7!~^..:::^~^:....       .^7:.^~!!!!!~~^!!!77!~!!7!^       .!~:^?JJJ?JYYPG5~..:!J7~^:..::~7~.\n"                                                                                                                                    
														  +":~~~!~:^:..::^^^:.. .    .^:..^777??7~~!!~^^7J?7~^^7?!~::.      !!::?YYY7?Y555?~7!~~77~^^..:.~7!^.\n"                                                                                                                                   
														 +".!!^:!~::..:::^~!~^.  ..  ..::..:77!7??7!!!!7JJ?7~::~~:....      !!^^?PP5Y5G#PYJ7!^~???!:7^...:~~7^.\n"                                                                                                                                  
														  +"~~:.^^::.:^^^^~~.    .!^       .7J?J!!77?JJ?J?!!~:..  . .       !7~^75GPGPYJ~^^::7G#J?7^P~.::.:7!~^:\n"                                                                                                                                 
														  +":~~:::.:.:!~~:..      .:  .:.   .~7???????7!!!~^.. .^^.        .!7!~!YGGBBPJ~~!7G##G?J7^J7:.:..~J7~!:\n"                                                                                                                                
														  +".^~!^:::.:~7~.      :^^:..:....  ..:!?7?7!^::.  .:!!~.         :~!!~^?PGGPGBBB&&&BPGJ7~:^::....:JJ7!7^.\n"                                                                                                                              
														    +".^~~^::^!!!^.     ^!!!^^^!!:     .~!!~!!^.:. .              :!^!7~~JPPBG555GBPPGGP5!::^JJ^ :: .!!^!!~:\n"                                                                                                                             
															  +":~!!::!77!^     :~!777!..        ...  ...  .            ^~~^^!7^~?YP&B5YBGPPBB5~..:.Y?!7^.  .:^:^^!J!^.\n"                                                                                                                          
															    +":~~:.~!!^     :!7~~!!:...    .:. ^  .:.            .^7~~!!??~:!YP#&B55#G5BP~..!Y?.?!^~Y^ ..  ^5GBBP5J~\n"                                                                                                                         
																 +".::. :!!.   ::^!~^::.                           .^JJ~:~77G?::^YGPGGGGGBP7  :??!^.^^~!!:..J~.:~JYY55PPY7^\n"                                                                                                                      
																   +"..  :!   .7~.                              ..^77?7^J?7JG~ ^!PG7!YGBBJ:  :!???~..!!.^5Y:G7:::       .^~^.\n"                                                                                                                    
																  +"^: :.   ::..~:                           .:^^7Y?!!7?Y?JB!. .~5BP~JBB^ ^#J!!!YGY:...:G&P!Y^.:.....  .     :^:.\n"                                                                                                                
																  +":. .^^^:~~:.::.                        ..::::^~?JJ?!JPP!    :!P#??PBY~:^JG5^~J5J.!7. ?BB^::.::....     ... .55J~.\n"                                                                                                            
																  +".:^^~!7!~^..:.                       .......^~~!???YG5~ ..:PGB#BY7?GBBP~.~7..:JG:.^: ~GG:.:.:~^.::..... ::..GBB##Y~..\n"                                                                                                        
																  +".^7!7!~!^...^.                      ^^.  ...:!7!7??J7!~~?5B#BGGPJ5BGY!?GG7:^^5BJY7:  JG7:^^.:^^::^~~!^:::.:!GBP??J??JJ!:\n"                                                                                                     
																    +":7J!:..  ..    .^~.            .:~:....::^JJ775G5!!5BJ!!!::^^^!7!~~::^!#B: ^7^^YY: PB^:^^.:^~~^~~~~^:^~^:^^^~!JB#PJYYJJ7^.\n"                                                                                                 
																	 +"~~!7?J7^   ..:~7!.           .^7?^^~^~!!~?J5GJ~!G&G!:.  .:..:...:.:?B7B#^ ^JY7??^ YG^^^^:::^^^::~~^^^^^~~7??!~B#J~?J?J5J~~!7~\n"                                                                                             
															 +"..         ..!7~^..:~!!!!^^^         .:~77!~7?J??JY5J^!G&&5~^:^^::. .  ..?GGYP&B^.?G77~~~.~7!57:::~~~~^^~^~~^?5Y7?YJ7BG5J?~::^~75GGY:\n"                                                                                             
															 +":J?:        ..~JY^..  .:~^::.   .     ^?!!!7!JJ?YYP57~5#G#P7^.~J!^:. ..:5B##B#&B! ~Y!^5G~.!7^~!!^~~^^:~~^!7!~77!!7!!!~~:  .^?5#&P~\n"                                                                                                
															  +".!Y!:    ^G#..Y5?J5^.  .~~:...  ..   :?!~7J5JJ5Y5GP^?GG7PGJ^^!!^:::..:?BGGBB&&G7.!?~GG?^:5#BBBGY^..:~JYY&J!57!?5B5~. .^7YPB&B&P.\n"                                                                                                 
															 +"^. .7?!^  .Y@!^JJ7JPJ^.:^~^:7!~~^:.   .!~7?JYBG5J?YJ^5BP^J5J^~~~~:~~.YJ!^!G&&##B?.7G5?!^::5&#5J!:^^^!~~!!?!75J7~::.:!5BBBB##&#@5\n"                                                                                                  
															 +".J?. ^JYY7~!#PJ?7~JG5^:^.  .^^:..     :??!?Y7#5!5Y?7!GB577??~^77:::~YBP5PYGG??GBY.JB5JJJ7!??~~!7?!!?55J?J5PJ:...^7G#BB##G55PG#@P.\n"                                                                                                 
															  +":JP!..7BB5Y##Y:~~Y#G~:^~^.   ..     .?#P7?5JPY?PBJ^~B5!J?55!~!!^:!YGGBB#P5J??B#Y^JGJPYJY^~JGB5?7?5PYPY775?^^?5PBGGPPBPPBBB5!7PY:\n"                                                                                                 
															   +".7#P7~YBBB##GYGPB&B5?777??7?!.     :~?#5??Y55YJP7^JB577YBBY7!!~!P##PGGP?777?G#J^J57&&B5YGB5PG5J7?YP?!~~?YPBBB##P5YJJYJ?PJ!75?!~\n"                                                                                                
																 +".YG5?7~~5#&#BBB##GGGP555Y5P?      :5&#Y!?YPG55~^P#GJ75#@#7~!J&&&&&#P?JJJJJB#5!7Y5BPJJ?YGBBYYBY???75GBBB#&&####&&#G?^::7PG???!\n"                                                                                                 
																		 +":!YPY5GBGGP5Y5PGBBBG?.    7&#&#P?YYY5J~!#&#GP7Y##J!Y&@&&@@BJ7JYPGPBBY!P&BJ?YBJPB5??JY??YG###&B#&&#&&###&&GBBBP?~^^7PP\n"                                                                                                 
																	  +"....^~~^:~7YGGGGB##BB#BPY?!!^:~YG#BJJJ5P?!?#&&&#P77PB&&B57?BJJPPGGP5JGB57P#G55PPBG5J77?5BB#&&###&&B?PB##&#&&Y?77?PG5!^?5\n"                                                                                                 
																	   +"..:~!~^^^^::!G@&&&#BBP~5P55J7?5YPBB~!55~!5#&#BGP5?7G&&G!^~YPB#BP5YJJB#Y~JPYP5YYJ7!7JG#&&&&&&&##B&B?5GGPYG##YY5P555PBY??:\n"                                                                                                
																				    +".YYJYB&#BBBBGP5JJPBGGBG7~Y~7G&#&&#B##J?PP!??JJGGPYJJYYJGB5~7555?!~!JPB&&&&&####&&&####PJ??J5GBGBBGGGGG#BGP7.\n"                                                                                               
																					    +".G#&&&#BGGGPPG##G5&BJ^^Y#&5P#&&&#57?YYGPYJPPYJYYYJ7P#Y~7?!!?5G####&&&BGY5B55G#&##B?JG#BB##B#&&&&&&B5PB5.\n"                                                                                               
																					  +".!G&&&&####BBBBB###PP##!?P#BBGB@&G7:7G&G5YY5YYYYYJ77!PB?:^!?G#&##GGB#&&#G?Y#PGB#BPG#B##BB##&&##BB#&@#PPG#:\n"                                                                                               
																					   +"..!B&&#&&&########B5G#BGG#&##&BGGB#&&#GPPY?!~~7?7?5PBB5?G#B##BGBGB#B#&&B5G&##&###BBPGGG#&###BBBB#&&&BPPB7.\n"                                                                                              
																						 +".5&&&&&&#####&&#BGGB##&&&@#P5PB####BGGPYJ??JJGGB#BGB#&GPGBG5PBG5PBB#&BG#&&###G5J7!YG#5?7JGGBB##&@&#BGBG~\n"                                                                                              
																						  +"^?5&&&&&&&#&&&&#GGB&&&#&#BG55B&&&#GPPPY!YYYG&#GGPPGB#P5G#?:^!7?5PB#&BGB&#GPY?7775B&B::~!5GB###&@&&BG&#!\n"                                                                                              
																							 +":G&&&&&&&&&&#BB&&#B#GGGGGPPJ!YB#55J~....75GGGGGBB#P5GBBP!~^!J7P##&###GPY7?J5B&#Y7555PGGGPB#&&&&#GG&5.\n"                                                                                             
																    +".:..:.                      J@@&&&&&&&#B#&@G555PGBGPBB5B##P5~    .:~YBBB###&BP5PB#G5YYJ?5BB###&#PJ?J5GGG?!JJ!~!PYY5G#&&&&#GP#B?\n"                                                                                             
																    +":BBY?G7.                   .7@@@&&&&####&@@#GGB#&&#&&&&&&#GJ!~.:~!!5B#&&&&&#PYPB&&&#GPBBGB#&##B5??5BBGY?7~:::~!YYJP#&&&@&B5B&B.\n"                                                                                            
																	 +"5!!5GBP7^                  :#!5@@@&##&&&##BB#GGB##&&&&##&&BYJ??JYP#&&&##&&BP5B###BG5YB#BB#&&B?7JP#BG55B5^^^~~J5YJ5B&@&@&BPG##?.\n"                                                                                           
																	 +":!7JG#&#P!.                    :G&P!!~!5&GBBGGBBG#BB#GB##B#&####&&&&&&@&&&BG#&@&GG#GJG##BG#&B7JB&B5PB#BG5?!!?P5YYP#&&&@&#G5P##:\n"                                                                                           
																		 +"!7?P#5JJ7:                   ~7!7::?&#&&G#&&B&&###&&&P55?!~75B#&&&&&#GB#&&###GBGJPBBBGB#BJ5PYJPG5JY5J!PP7GB##B&&&&@@&BPG#&J.\n"                                                                                          
																		    +".!7??7JJ7.                ^?J^:..^7?Y#BB##BB&&BY?GY~^!!^:.:^7GB7:::~7Y&5JBGBBY5GBBBB##GGGPBG5J??J5Y55Y#&&#G&@&&&@&BGPG&B~\n"                                                                                          
																		 +".^~^^:..::~?57:             :~?J!^!?~!~!5#&#B####!^^7BPP&&G?~~^J#:!55PY7~J775&&##5PBBBGB#BP5YJJY5Y5B##BB#BBPY5&@@&@@&#BPP#&J\n"                                                                                          
																	    +"^J7~::..^7:.^!7?7.         :?Y5PPYYJYPGGGG#&&##PYBGGBBB####&G~~JY!:757755?5?7YPPB&5PBBBGBBPJ7??PBBGBBB5PPPJJJY&&@@&@@&&BP5G&5.\n"                                                                                         
																	   +":J7~~!^:^~^::^^J55BJJ?      :J#BB#B5YP&PPBBGJ~^##P###B5YGP~!!!??Y! :!?J?7!?PYJYPY5GYPBBBBPY7?5G#BGBBBP?7Y5YY?YB@@@@&&&&@#GPP##J\n"                                                                                         
																	   +":JJ!^^!7!!~!!7?::J##&#P?.    ~&&#BBG55#PPPY~~~~G&#B&B??!!?5PGBBP~..~~^^^~~!5PYJPGBG55PGGBGJYGBBBBGPY7~!GGPYY5B&&&&&&@@@&&BP5#&G.\n"                                                                                        
																	    +":P5J77Y~.!5Y?Y. .G5G&##?.   ^&&####BBBBGPGBB#&&@@&G5P#&5~^#&BP#&#G7^::~!YBPPJ7Y5GBYY5PGBBGBGGG5?!~?PPYGPPGB&&&@&&&&@@&&&#P5B##Y.\n"                                                                                       
																		 +".?J7!Y55P5PPYJ?!:  :!G&&5^7B&&&##B##GBGGGBBB#&##&P?5@@Y^!JP5!?J5B@@@#J!Y&PPY~JG&&5?J5GBBGBPYY555Y?5GGB#BB&&&&&##B#&&&@@#5PGGB#~\n"                                                                                       
																		   +".:^^::. .:7YYYB#5^  ~G&&##B&&###&&#GBB5BBB#BBBBB5?J5YJ5B&&GG5?~PGG#&&&&BP?~GP5BB?75PG5!77??YPGG55PGGGGB&&#PGPGBBGB&&@&GPPGBBJ:\n"                                                                                      
																					    +"^JB&&#5~~B#&##&&#G#&##BB#&##&&BJ?G#BP5G#&@@@&&&&&#&#GG##&&#577PJ?GB?7PY5J!?????777?JYY555G@@#GB#&&#GGBG&&BPPG#&B~\n"                                                                                      
																						   +":JB&&&&###&B&&&##&&&###BB#GYBB775&@@@&PJB@&@&#GGG#@#&&&&BJ7JPGPP775Y5?:^7JJJY5PBGB#&&##&@&#&&&&##BGGG&&5PGG#&J\n"                                                                                      
																							 +".5&&&&&&#&&&&&#BBBBPYG&#7Y#&5!5&&&@#::5&#PPPYGGB&#&&&@BYJ75#&G?75557..:~75B#&&&&&&@@@&&&@@#GPBBGPP#@&BGPPP#G!\n"                                                                                     
																							  +":JG#BJ7G&&BGBBBGGBGG#&&&B5J7G#&@@#J~!YPYJ77J&#GBBGG##G5J~?PB&GY5PGP5B&&#&##&###&&&&&&&@&#PGBGB##&&@@&G5PG#&P\n"                                                                                     
																						   +":7~    ~5B&&##P&#BB#BGG####&&&&@@&&@PJG555YP77BB##B##BB##G7^7G#B#&&&&&######BB#&#&@&&@@&&@&Y5GBPB&@@&&&&GPGBBBG~\n"                                                                                    
																					   +".^ :?Y?.   .:~J~7G&###BGG5YG##B###&@&&&#5!^!!!7?7JBG##G&&#&##BJ^YBB&PP&B#&#&##&########B#&&BBB5^P#GP#&&&@@@&BPPGGGGB^\n"                                                                                   
																					   +":?Y7?YY.    .JGP#@&PG##G5JJ5#&#&##@@&&#&5!JPGPP7~J&##BG&&#&#&#?~JGG5?!?&@&&#&&&&&###GPPG#&#PG##BPG&&&&&&&&&&&BGPBBG&5:\n"                                                                                  
																					   +"!G?7J5YY7^  ^J?YGBGGPG#GYJYPB#B##&&&&#&BY?7?5PG?7P&###B&&#B&@#J~!5#?Y!.B&&&&##B#BGPGGGB&&&&#&&&@@@@@@@@@@&&&&&GY5PG#G7.\n"                                                                                 
																					 +".7YYJY55PPP57!~~~???PPPGBG5Y55#&#&&&&&&&&#Y?5YJYPJ5#&&&#G5PB&&&#57^!G##J.5B#&##BB##BGB#&&&&&&&&&&&@@&&&&@&&@@&&&GP5P#BG5~\n"                                                                                 
																				 +"..:!PYJYY5PPPPPY~!!!!!7YPGGGG57!5PB&&&&B?YB#5B5YGBGPYYB&#&&#G55G##@&PY?J5Y?!!YPB&###BPPGB##&&&&&&&&@@&&&&&&&@&&&&&&&##Y5BGBGJ~ \n"                                                                               
																			   +".!5GGGB###BGGGBGP5?!!~~!J5P??Y5?7?PJ5BB#B57YB&PP55G5Y??JB&&&&&&&&&@&&BJ7?YPPJ!!JPB#&###B###&&&&&&&#&&@@&&&&@&&&&&&&&&&#BY5B#B#G!^.\n"                                                                              
																				  +"..::^!5#&#B#BP?~^:^?555P~?GP5B#P5PBG###&&&#BGGPJ7J55B#&&&&&&&#&@#?::P55BGGGP??P&###&##&&&&&#&##&&@&&&&&&@&&&&&&&&&&&GP55BB#B~7:\n"                                                                              
																						 +":?G##@G~~7~^JPPY?5PPP#&&#BGGB&BJ7?B&B5GBYGBB#&&#&&&&&&&@&: :JY!!P&&#BP?!?P&&B#&&&&&&##&&&&@&&&&@@@&&&&&&&&&&&BGY7P##G5~~!\n"                                                                             
																						   +"^G#&5^^^^~?YYYGGPPGB####BGB#G5J?G&BPBBGBBBB&&&&&&&@&&@&&G55GJ7Y#GPGY5?~?G&&#&&#&&&&&&&&@@&&&&@@@&&&&&&&&&&&&BY?JBG5B7!?^\n"                                                                            
																						 +"^!~!#&5::77~~J?J@@&&#GB##&#BGG#&##BG#GPG#GBBBB######&&&&&#@&777?YGJ?J7Y555G#&#BGG###&BGB&@@&&&@&&@&&&&&&&&&&@&#GPJPPYG#J^G:\n"                                                                           
																					    +":Y.  ?B5~~??!!YY5&#&&#GGBB#&#BB#&#BBB#GGGBBBGBB#BPJ7?Y##&&^YBJ::^~7!~~7JJYB###BPJ75G#&BPPP#&&&&&&@&&&&&&&&&&&&&&#BJ5PPBB57?5~\n"                                                                          
																					    +".?~^!?~!?!!??~~~!J?JP&#BBBG#&##B5:~5##B###&#GPPBBPY^^G&&#&G~:!????7?YPBGB&#BB#GBG5YPB&@#GG#&&&&&@@&&&&&&&&&&&&&&&#5Y5GBGY7~G5:\n"                                                                         
																						  +".^: ^7?!^7Y!!7P@@GY&#BB#5PB&##B55B#B###G5PB#G55GP!!GP7~75P5Y!!7?YG#&@J?&####GPGBGBB#@@&&&@&&&&&&&&&&&&&&&&&&&&&BBJJPPGPYY?PG.\n"                                                                        
																							 +"~?!^!J5Y5YJPG##B####5JP#####&&##B##GJJ5BBBBB#Y?B?~~!~?P#BBBBGB#B#Y5&&#BPY5PP#BG#@@&&&&&&&&&&&&&&&&&&&&&&&@&##5YPBGGGGY5GG:\n"                                                                       
																							  +"!J7!7?JY!5&#BBBB####PYYP#&#B5PB#BGGBP5YJG#&&&57?::^777777PGY#GG&5G&&#P5PPPPG##B#&@&&&&&&&&&&&&&&&&&&&&&&&@@&G5JJBPGBGBJ7&P!.\n"                                                                     
																							  +"7Y??JYYY?G&#BBBBB#&&B5J5B&#P~~5&GY5BGP5PGB##BGY?!^~7!!7?#G?~JPY7PB&&YY5PYYPBG##B&@&&&##&&&&&&&&&&&&&&&&&&&&&GBPJ5PB###57P#&BJ.\n"                                                                   
																							 +":7JJJYJJ5YB&BBBBGGB#&#GYY5B#&B##BBP5GBBB####G~~?PY7!77~?BBPJ77YYYG#B55YYPYYPGP##G&@@&&&&&&&&&&&&&&&&&&&&&@&&&B#GJ7YB&BP5?^B&##B5~\n"                                                                 
																						    +".!YJ?J7?Y7Y#&B###BBGB&##B5JP&&&&&&BPPB##B#BP!. .:~?PP?JY55YPPY5GBBGBY^YY555Y5PGGB##&@&&&&&&&&&&&&&&&&&&&&&&&&&&B5J7YPPG#BJ!YB##&&#P7:\n"                                                              
																						   +".~77~^~~^:~7G&B#BBBBGG#&#BBYG##BPP&#BGB&&B5~.    ..77?P&&###B#&GB#B5#P:YYY5P5YPGPG#B&&&&&&&&&&&&&&&&&&&&&&&&&&&@BPY7!5#GPPPB!Y&######GJ^\n"                                                            
																						    +":7J?7!7?JY5B#B#BB#GGBB##BBBBB#J!?B&#BPY~..    .:!J!75###BB&#BY!7Y5J&B^Y555PYY5PGGBB&&&&&&&&&&&&##&&&&&&@&&&&&&&&#57~JBPPPG#5YP#&&#####B5^\n"                                                          
																							  +"~YPY5PB&&&###B##GGBBBBGB#&&&BBBB5!:.  .. ..:~!7~^JGB##B#&#G5J77?B#5^JYY55YJYPGGG##&&&&&&&&&&&&&&&&#&@&&&&&&&&@#P7^!JPGBBGGJ7#&&#&&##B##B?.\n"                                                       
																							   +"~##B&@&&&&####B##BBGP55BBP?!^.         .:~~~^~!J#&GB##&##GYJ!7P5&#!?JY555YYYPGBB##&&&&&&&&&&&&&#&&&@&&&&&&&&&&BJ~~?B#&&#5??5#&&&########BJ:\n"                                                     
																							   +"~&&JP@&&#####BB##B5GGGG?.           .:^~~~!!7YG#&#B#&&&#&#JJ~!GG5Y?7JYY5P5JJ5PGG#&#&&&&##&&&&&#&&@&&&&#&&&&&&&&P7^!GGGGGYPP7G#&&##&&#BB###BP!.\n"                                                  
																							   +"~#&!5@&&&#&&BGBPP55GGPPJ. .:......:^!!~!7JYYJ5B##BB####&&#YJ^7#G5J?~?5YGP5JY5PPGB##&&&&&&&&&&&&&&&&&&&##&&&&&&&#Y^^JJY55B&#7?G&&&&&&&######B#G5!.\n"                                               
																							   +"~##~?&&&&&&&#BGP5YYGP5J^. .::::^^^~^^~?5PP5J?YB&BB&#&&B#&#YJ^!B&PJJ:?P5GP5YY55PBGB#&&&&&&&#&&&&&&&&&&&&&&&&&&&&&B7:!JPBYG#G?!5&&&&&&&&&##B######BY^\n"                                             
																							  +".J&&:~G&&&&&&&BGGP55P555. .::^^:^^^^^7YYY5PPY?JB&##&BBBB#@&PY^!#P#B7^?55PP5YYY5GGGG#&&&&#&&&##&&&&&&&&&&&&&&&&&&&#5~:75PBP5P55J5&#&&&&&&&###########G~.\n"                                          
																							  +"!G##::5@&&&&&&#BGP55555J..::::^~^^^!JJJYY5PP5JYB&#&@BBBB###G5!7GGY5G^!YPGP55YY5PPBBP#&&##&&##&&&##&&&&&&&&&&&&&&&@B!:7Y5YYY5BB7~#&###&&&&########BB#&#BY~.\n"                                       
																							  +"YBGG:.?@&&#&&&#BBP55PGJ..:.:::^^^:~PJJYYJY5PPY5#####B#&####B5?7Y&#57!~7PGGP5YYY55GBGG###&&&&&&#####&&&&&&&&&&&&&&@#Y~~P&YGGPYP5J?#&#B#&&&&&############B#BY^\n"                                     
																							 +".5GPG!.~@@&##&&&#BG55P7:..:::~^:^~^?&5JJJ?J5PGPP##BB&B###B&&#5J7?#&#YY~!PGGP55Y5P55G#PBY^?P#&&&&&&&&&&&&&&&&&&&&&&&&#?!7P#GB&BGGP7#&&###&&&&&&#######B###B###G?:\n"                                  
																							 +"~JPGP7.:@@&#&&&&##GP5~..:::^~~~~~.7B#GYJJ?JYGBGB#####&&##&BB&5Y?7Y5YJY!75GGPP5Y555YG&BBP!   ..^?5GGGG&&&#&#&&&&@@&&&&#?~!YJ5B&GP577G&###&&&&&&####&#############P!.\n"                               
																							  +":P#:  :@&&&&&###&#J...:^:^~~^:^!?P##B5JJJY5B######&&&#BPG#&#55?~#Y?G&7~PGGGPYYYY555P#GBG            5&@&&G!:~!!!~:~YG5?!!5B&&BBG5~J######&&&&&################B##B?:\n"                             
																							 +":G5?P. .#&&&&&&&&#7.::.::~~^::^7G##B#G555PGG##BB#&##&@&##&B#&GP?~&PJB#?~5PGGPY5555P5JB##G!           .B@&#7.         .PP77YB&#G5G#5?Y&&######&&&&&#################&#5^\n"                           
																							  +"!JJ^  .P&@&&&&BY:.:::^^~~~^.^Y#&###BBGGGBBBBGGG#&GG&#B#&&#&&BP?!GBGG#J7YPGGPY5555PP55##BG:            !G&?.          ?PY!JG#GPPB&#5J#&#######&&&&&&##########&&#B###BBGJ:.\n"                       
																								    +":#&&&&#Y:.:::^^^^^:.:755PB&&#B##B##BGP5YP#&BG&###&&##&55J!P#B5B5JYGGGPY555YPP55PB##7.           !BB#P~         .YG!75BPPGB##G55#########&&&&&&&################BB#&BY^\n"                     
																								    +"^@&&B?:.:^::^~~^::~Y#&PYPB###BBBB#BP5YJJ5#@BG#BGB#BB##BY7?Y5G#&#PJGBGPY55YY5P555PB#B~           !##BBY.         ~GY!JB#PGBB##BYB#######&#&&&&&&##################BB##BP^\n"                   
																								   +".7&G7...:::^~^::^~JB&#B###BB#####BBP5YJJJ5B@#G&B&&BB##G&P?J~YBBB#P7B#GPY55YY5PP5P5P#&Y           :5#&&B5.        .7B??55GBB##&#5PB&#####&##&&&&&&######################&#Y:\n"                 
																								   +".:^...::.:^!^::~?5GB&&BBB#BBB##&#BGP5YJJJYB&&#&GGBB#&#G@G?Y7Y55G#G?B#G5Y555YJ5PPP55P##5:          ~&##&@7         .Y5J?55G##GPGBJP&&#########&&&&&###############BB#######BP?~.\n"             
																								   +"....:::^^^^^~!JJJJPPB##BB##BBB#&#BGP55YJJYG&#B#&##&B##G@P7?~PB5P#BJGBGYJ555YY5PPPPP5G#&&5^        ^@&B#&&!         :G5!5PGGBBBBB5J5&&##&######&&&&&#########################5BG7^..\n"         
																								 +".:..::::::^~!75G5YJ?Y5GB#####BGBBB#BPPPP5YJYG&&#&&&&@@#GG@GJ5!PBJ5GGYPBGYYYYYYY55GPPPPPG#&@@#?.     :B&#B&@B~         5P77B##GP#&#G??B&&##&###&&&&&&&&&####################BBBYB&BPPPJ^.\n"      
																							  +".......::^^^~JPG555PYJ??Y5G&&&###BBBBBBGPPPPYJYG&#B####&##BG@BYY7!5#GG5YYGGYYYYYYYY5GP5PPPPGB&&&@@#J~. .5#G5BB&#.        ~PP!YGBBGB#BBP5YB&&#####&&###&&&&###############B##B##GJ5PGGGPPG5Y7.\n"    
																						    +"........^^:^7G###B555PYJJ?Y5P##&&###B#BBGPPPPP5JYG&##&#&##&&BB@#PYY77P#&B5JPG5J55555YYPPPPPGGPB#&&&&&@@G^ ?YP5~Y#&5^        ?#77JGB&#B###GYG############&&&&&&##############BB##PJJBPYP#BGGYYPG.\n"   
																						   +"..   .:^::^JB&&&#GP555P5JJJY5P###&#BB##BBGP5PGG5JYG&&#&&#BB####&#G5P7?B&&&G?Y5YY55555555PPPGGGGGB#&&@&G~.  ?!5G !5G&Y:       ^P5~~JPB##BGBBGPB###########&&#&&&&############&&#BY7JGPYY5BBP55YPP.\n"   
																						    +"...:^:^!Y#&@@&&#BGP55PPYJYJYPBB##&GG##BBBG55PP5YYP&#B&&##&#BB#&&#YGYYG5PB&YJYYY55PP555Y5PPGGGGBGB&&5^     ..~J!. ~#5^        ^#J^~55G&##BB#GG#&&#B#########&&&&&##&#####&&&&&BY7JPGGGGGPP5JYPP?\n"    
																						   +".:^:.. 7#@@&&&&&#BGG55PP5JYJYGBBB#&5G##B###5Y5PP55G&&B##B#&#BB#&@#5G?Y&P5G&BY?YY555P555YY5GBGGGGB#B^          :Y.  ^Y7..      .PG?^7JYPB##B##B#&###########&&#&&&&&#######&##BBGG#BP#G555YJ?YB5.\n"    
																					   +"...:::.    G@&&&&&&&#BGP55PG5YYJYP#BB#&5P#####&PY55PPPG&&#&BBBB&##&&&B5BJ?GGGGB#J?5Y55PP555YY5GGGBBGB#G^           !.   :^.        ^BP~^55?5PB&&#BB&###########&&##&&&&#######B55B###BGP&5JPJ??YPP~\n"     
																					 +"..:::..     :#&&&&&&&&##BP55PGP5Y5Y5##B##5PB##B##P5YYPPPG&&&&B#&#&B#&&&BYBJ?G#55#BYJYY5PP555YYY5PGBBBGB#BJ.                           5BJ^7GBY5GBBBBB##############&&#&&&&&###BPJJG##B#&#BGPY?775B~.\n"      
																				  +"..::..        .G&&&@&&&&&&##GP5PGP5Y5Y5##B##55B####BG5YY55PG&##&####&GB@@&#PGJ7YP5G&B5JY5PPP5555YY5PGGBBBBB#B:                           ~PB7:Y&PGBBPPGBG###&###########&&##&&&&#PY5GPGGBBBB#BP5YP&B^\n"        
																			    +".:::.           ^&&&&&&&&@&##BGP55GGP55Y5###B#P5G####BBPYYYY5G&######B&BB@&&#G5JJYG5P&B5J?YPGGPP555555PGBBBBBB#7.                           ?#P~!P&###GGBBPG#&#############&&##@@&GG##GYPBBB#BBBBBBG7.\n"         
																   +"...     ....::..            .?&&BY5&@&@&###GP55GBG55Y5###BBP5P######G5YYY5B&#B#B##B#B#&&BB&5YJJGBBB&GJ!Y5PPPP555555PGBBBB##BB7                           ^PBJ~7#PPPYPGBB5G###&##############&&&##&#GPBBGGGGG#&#J.\n"           
															   +"....    .........               ~B#&5^7&@&@&###BGPYPBBP5YP#&###P5P######BPYYJ5B&BG#&###GG#@&###55?!P&BYB5!:J5PGGP5555555PGBB##B#BG7                           ^BPJ7J55J?B###P5G&##&&###########&BB#&&###BBPJ?YPBYJ~\n"             
														   +".....   ..........                  ?@#@Y.!&@&&###B#BGY5GBG5YP#####P55G#####BPYJJ5#&#B####&BGB&##&#55?~&#GB?57.!PGPGPP555555PGBBBBB#B#B^                          .5G57!GBP5?P#BPY5###&&&###B#######PB#&&&&#GP5YP57.\n"                
													   +"...::::.....:::::..                    :P@#@? !&@&&&&&#BBG5PGBGP5P#####P555&###BBG5Y?5&&##&BGG#BG#&#&&&YY7^#BPB5GJ:~YPPBG555PPP5PGGBBB#BBBB#:                          ^GP7~YGBBJJPB#PYG#&#&&###########BGGB&&&&#GGG7.\n"                  
												 +"...:::::...:::::::::..                       5##&@7 ?&@&&&&&&BBB5PGGGP5PBB###P55Y#&#&##B5YJP@&&&&&@@@&&&&#B#&YY!:G5P&&Y~^:JGGGP55PPPPPPGGBB##BBBB&5:                          5B57!5#PPPPYBB5JB####&&#########&P5BB##P:\n"                       
											 +"..:^^^::....::::..:::..                         .B&#&B^ 5@@&&&&&&B#BPPPGBGPPGBB#&G5YYB#&&#&#P5JYPP55GGBBG@&&&BB#&PJ!^JB#P5YYJ^YGPGP55PPPPPPPGB###BBGBB&Y.                         !PG?7YGPG5JY?5G?5B&###&&####&#B#&G5PPGP!\n"                        
										  +"..::::::::::::::::::::.                            ~B&&&J..G&@&&&&##BBBGPPGBGGGGBBB#B5YYP#&##&&B5YJJYJJY555G@#GB#BB&GY7^~B&GBY7!:?GBGPP5PPGPPPPGBB##BBGGB#BJ                         .~Y5J?J5##P5JYPP?P&&##&&###&&#B###GY55PP.\n"                       
										    +"..::::::::::::::.                               .G#&&&7 .G&&&&&&##BBB#GPGBGBBBB###BPJJ5B&#####P55YYYJJ55PB&BG#&##&&5!!~B&5&@&Y.7GBGGPPPPGGPPPGBBB###BBBB#G^                          ^#Y7?YB#B#5Y5BJJP###&&&###&#####B5555PJ.\n"                      
											    +"...::::....                                .?&##&@^ .P&&&&&&###BB#GPPGGBBBBB###PYJ5B######G55YY55YY5G#&#B&#&&##PJ?^~Y&@&BY:!5BBGPPPPGGGGPGGBB###BBBBBB#:                         .PGY??PBBPPGPY?7JB###&&&&B####B#BG5PG5J!.\n"                     
																						   +"^B&&##B. .G&@&&@&&##BB#BGPGBBBB##BB#B5JYG######BP5Y555YJYP#&BG#&&B#@BY?:!?JYPPJ~7P#BGGP5PGGGGPPGBB###BBBBBB#5:                         :#B??5PPGGBBGY775###B#&&&###B###BYGB55P?.\n"                    
																						   +"J&B&&#?  .#@&GB&&&&#BB##GPPBBB###BB#B5?JG#######G5Y5555YJ5&&#B#&&&#&GYJ.Y&G~Y&&?~JBBBGP5PGBBGGPGGBB##BBBBBBB&5.                         5#PJJPBGPGGPPPY7G&&#B#&&&##B####G5YJ5YJJ\n"                    
																						  +"~G#&#&#.  .#G^.P&&&&#BBB#BPPBBB######GY?JG#&#####G55555PPJ5&&##BBBB#&GYJ~5&@??Y5Y!?BBBGP5PGBBGGPGGBB##BBBBBBB##5                         :P&5?JPBPPPPYBB7JB&&&&&&&########PJYPP5J.\n"                   
																						 +".B&#&#BY    .  .B#&&&##BGBBG5GB#######B5Y?5#&#####BP555PPPYP&###B&##&&B5J~Y#&75#&#77##BBG5GGBBGPGGGBBB##BBBBBBB#B!                         !#B5J5PGPPGB##P!Y##&##&&########GPPP55JJ.\n"                  
																						 +".G&B#&P^       ~###&&##BBBBGPPGB######BPY?YB&&&&##BG5YY5P5YP&#####&###BP?7?B@GPP5Y!?BBBBGPGGBBGGGGGBBB###BBB#BGB#B:                        .?&GYYGG5YYY5PB7~J&##&#&&###BB###G?Y5PP?~.\n"                 
																						  +".Y&#@J       :5BG#&&###BBBBGPGB######BPY7JG&&&&&##BP5Y555YG&#&&#GB#&&&GJJ5PPB####?7B##BGPGGBBBGGGGBBB#######BGB##?.                        .B#GY55PP55PP5Y~~GB#&#&&&###BB#&B?5GG5!J7\n"                 
																						   +".?B&?       ^5GPB&&&##BB##GPGBB##BB#BG5775&&&&&&##G5555YYG@#######B#&BYYYYY##G5@GJP#&#GPGGBBBBBGGGBB######BBBBBG5!                         .B#5JGGG##BBPGJ!7B&&&&&&&&###B#G7PPYJP57^\n"                
																							 +"~P!        ^GPG#&&##BBB#BPGBB######G577Y#&&&&&&#BPPP55YG@########B&#5YJYPPBGY&BYYB##BGGBBBBBBGGGBB######B###B5PP?                         7B#5YGBJPBBB#G7^P#&&&&#&&&####BJGP55GYJ5.\n"               
																							  +"7^         ~5PG@&&#BBGB#GGGB#&&###G5?7?P&&&&&&&#GGGP55B@BB#B#&##G##P5Y5GY5G#G#BP####BGBBGBBBGGGBB####&&#B##PJG#5.                        .Y&GY5GPPGGPB#P7!P##&&#&&&&###GYPBGJPYYP:\n"               
																										 +"?JYP&&&#BBGG#BPP##&&###B5?7?5#&&&&&&#BBGG5PB@&##BB#BB&#BG5Y5B&G5GG#B5B###BGBBBBBBGGGBB#BB#&&BB#P?GGBBY.                        .5&PJ5#G55PP55Y~JB##&&&&&&&#BGPPBBPP555\n"                
																									    +"7&5YPB#&###GGB#GPB#&&&##B5?77YB###&&&&#BGGP5PGPG#&#&&&&#GP5PP5G#BP#G5PGB##BGBBBBBBBGGB##BB#&&#B5P#G55BB5:                        :&#YJGGB##BBBGJ7J##&&&&&&&&GG#PBB5GPY~\n"                
																									   +"~GG#PYG&&&&BBBGBGGB##&&&&B5?77YB####&&&&#BBGPP5555PPGB####GY5YJBBB#&&B5PB##BBBBBBB##BGG##BBB&&#BPB&P#B5PBP^                        !#BYYBBGP5PP5G?!5##&&&&&&&BGGGGPPYP7\n"                 
																									  +":JBG#BPP#&&&BBBGBBGP#&&#&#B5J77JG#&####&&###BBGPP555PGGBB#&#55Y?BGG&&&#GYG##BBB#BBBB#BGB#BBB#&&&&BB#PP55GBG7                         5&BY5PGGPPGP55J~G##&&&&&###GGGPYGJ:\n"                 
																									  +"JGGGBGP5#&&#BBBBB#BPB#####BP5J?JP#&#&###&####BBBGP5PPPGB##&#P55YYB#G&BB&5G###BB#BBBBBBBGB##BBB#&#&B5BPPGB#7                          .Y#P55GBBPG&5PP!7G&#&&&&&##B#PY5P^\n"                  
																									 +"?GGPPGBGPG#&&#BBBBBBGBBB###BP5??J5B##&###########BGGGP55G##&##5Y57G#PGB##PGB##BB#BBBBBBBGB#BG5#&&&##BG5G##7.                           .7#BPPB###&PB#P^?B##&&&&&###PPG^\n"                   
																								   +"~P&&#PGGBBG5G#&##BBBBBBGB#BB##BPYJ?YB######BBB##&&###BBGPPPB##&&P5P7PBGGBBBBGB##BB#BBBBBBGGB#BGBG#&&&#GY5G#&~                             .P#GPPGB#&BBBG5!7&###&@&&BPGP!\n"                    
																							    +".?B&&#&&#B5PB#GG#&##BBGBB###BBB&B~::!??G####BB&BGGB####&&#BBGGBB#&&BP5YJP&###BGPG###B#BBBBBBBGBBGG&PP###BP5YP#BG!                             :B#PG###B##BG#Y7Y##B5#&&#PP?\n"                     
																							  +".?#&&##&&&&#BGGGBBB&&#B#BGB#&##BG&B.     ~P&&#GG#BPPGBBB#&&&##BBBB#&&#GY5?5#GJP&BPG#&#B#BBB#BBBGPJ5#B&BP&&B5YBBGGPP?                             7GBGB#BGBBGBGB5~P#G!P&@&#Y:\n"                     
																							 +"!B&#####&&##&&#BGBBBB#&##BGGB##B#B&G.     .?&###BBBGPPGBBB#&&&&######&&#55JYBBPP#BBBB&###BBB##GPPJGBB#&BP&#GG#BBBBGJ:                              ?#5PB&#BBBBB#BJ7PG?YB&&&7\n"                      
																						   +"~G&#B######&&&&&&&&###B####BBGG###BB&G       !@#####BGPPPPPGB##&&&&&&&&&&#P5J5GBBPP#&BP#####BBBGPB#5PB#GGP5B#BG##B57~.                               .7&YYG&&###GB#BJJYJPPG&@7\n"                      
																						 +"^P#&&&&&#&&&&&&&&&&&&&&BYYB&#BGGG###BB&G       ~@&&&&##BPPPPPPGBB#&&&&&&&&&&#5YY5BBB#GBBP######BGG##GGPBBJY5PG###GY^                                    .PPY5#B#&BGB#BBY??5GBB&!\n"                      
																					   +"^P&&#B&&##&##&&&&&&&&&BY~.  ~G##BBBB##BB&P       :B#&&&&#BGPGPPPPPPGB#&&&&&&&&&5555&GB&GGBBG#&&#GGB#GB&B##GY55P&&Y..                                       .BGJJB#B#&##BBG5?5PPP#P~\n"                     
																					   +":P#@@&@&&&@@@@##BG5!.        .5B##B###BB&P       .7#&&&&##BGGGP5PP5PGB#&&&&&&@&BP55#B#&PGB#5#&#BPG#&###GGPYY5B&&@Y                                          :PB!YB&&##BBBBGY5PP55G5^\n"                    
																						 +".:^~!??7!^:..                .~?YBBB##&5        :&&##&&#B5Y5PPPP5PPGGB#&&&&&&&G5P5##BBBBGPPGB&GB&#&#BBB5YG#&&&&&#P:                                        7#P~?B&###&#BGGYB&B55?^?~.\n"                 
																														  +"..:~J5!        .&@&&&&&&G55PPP55PPPPGBBBBB#&@BPPY#&###BG#&&#&#B#GB#BGPG&@&&&&&##@B~                                        !#?~?##B#&####GGGPGG?:J!.\n"                 
																																		  +"?J7!???J7!7?!^. ...  ........^JBPGB#B##BP####GB#BBBBG#&@&&&&&###&@J                                         7BY~!5#&##&&BBBGP#P7!J7:\n"                 
																																									    +"~#G5PB#&#BB#BGB&&BGGPB&&&&&&&&#&&&&&#?                                         PGJ~JGBB#&#B###BBGY!?J~\n"                 
																																									    +".YGP5G#GPB#BBBBBGPPPB&&&&&&&&&##&&#&@#                                         :PB!~!JB###&#GGB##57?P?\n"                 
																																										 +"^BBPP&BB#5#&BGBG5J#@&&&&&&###&#&&#&@&                                          ^5G~^?BPGB#####GY?7YP7\n"                 
																																										  +"5BP5###&GGGPBP7. :G@@&&&&###&&&&##@&.                                          :G?~~J55P###&&G77P#J\n"                 
																																										  +"^5G5PGGPGB#GJ.    .J&&&&#&&&#&&&&#&&J.                                          :5J^^7JJJJ5P5?YGG!.\n"                  
																																										   +"?BGYPGGBG?.        J#&&&&&###&&&&#&&:                                           !5Y~~~~:~!!?G5~\n"                     
																																										   +"^5#PBBP7.           J&&#&&&&&##&&#&@!                                            !GGBBGGGGGG5~\n"                      
																																										    +":P#P7.              7#&&&&&&##&&##&P:                                             .....  .\n"                         
																																										    +".!J:                 !@&#&&&&&&&&&&P^\n"                                                                              
																																																  +"?&@####&&#&&@7\n"                                                                               
																																																   +"7#@&&&&&&&&#:\n"                                                                               
																																																    +":PB&&&&@&P~\n"                                                                                
																																																	   +"......\n");
			        break;
					
					case 3:
						enemyimage= ("\n" 
					
																							 +"P~\n"                                             
																						   +"!@7\n"                                             
																						  +".&@G\n"                                             
																						  +"5@&G^\n"                                            
																						 +"J&&5~7\n"                                            
																					    +"7@&&~ ^\n"                                            
																					   +":&&&G. ..\n"                                           
																					   +"B&&&G......\n"                                         
																					  +"7@&@&#7..:^J7:\n"                                       
																					 +".&@@@&&G7.YYPPP!..\n"                                    
									   +"?:                                             5&&&&#B##BBPB#&##B7\n"                                   
									   +":#.                                    ....::~&&&&#P5P#&5 Y#&&&G7.\n"                                  
									   +".#^                            :!J5G##&&&&&&&&&&&###&&&&B^::^G&G?..\n"                                 
								   +":P  YB.                           ~&@@@@@@@&@@@@@@@@&&&##B#&&&&#GG&#B5!~!!.\n"                             
								   +"!@^7&Y                             .^YG&@@@@@@@@@@@&@&#PP#B@@@&&&@@&#@@@@@&~\n"                            
							    +"..!#B7@&Y                                  :GG@&&@@@@@@@&##&&#B##B&&@@@&@@&@@B.\n"                            
							    +":###^^&@&:  ~                             7G#P&5&#5&&@&&&@@@&&&&&#5#&#&&#&#B&G\n"                             
							    +".&@&&YB@@^ B&                             ~&#B~!:  5@@&&&@&&&@5..... ^#&&GPJ:~.\n"                            
							    +"!@@@&#&&#.~@!                              ~&^ .^5#&&#&&&&&@@@#?..:JJB@@@&&GG&?~.\n"                          
							   +".&&@&B&@#&?P@P.                             5#P5#@&#&#G&&&@@&&P~~?&&@@@@&&&! ~##BY:\n"                         
							   +"5@G@#P&#P&#@@&.                           ~#&&&&&&&&@BB&@@@&&@&&@@@@&BBPGP5^  ^!B&J\n"                         
							   +"~@G##5##5&@@&^                          .Y@#&@@&###&&&@@@&&@@@@@@@@@&PJ?7?G?..  ~P^\n"                         
							    +"P&#&PPBP&&@#                        .7B&&&B&@@@&&&&@@@#&&#@@@@@@@@@@@@&#&&&GJ!!?:\n"                          
							    +"^..P&##G#@&J                         .::Y&&@@@&&&@@@@@#&&&&@&&@@&&&&@@&#&@@&&&&#.\n"                          
							   +".: ~YG#GGB&&.                         .?#@@@&@@&&&&&&&@@@@@GB^:&@@&&#@@@@&&@@#P!.\n"                           
							   +".J#PGYJJYYB5^:^.   .~:            .~7P@@@@@@B#@@&&#BB#&&&&!    G@@&#.!@@Y:^:.\n"                               
								 +"&&B#YJPBB&&BBY ~5#?            J&@@@@@@@@&#B#&&G#&&&@@&?.^    #J..  :&5\n"                                   
								 +"P@&BBG5#&##&&@&B&@!       .. .B@@@@@@@@@&##BBB###BB&&@&5YPYJ! !:     .^\n"                                   
								  +"~~...7G&&&&&&@#@&.     :B&&B&@@@@@@&@@@&@#PBB#&G#B&@&@&##&&G\n"                                             
									   +"^B@G?G@BB&#&~.  .:5@&@@&&#&&@@@@@@@&&@BBGG5B&&&@&@@&&&P^:\n"                                           
									    +"..   ?!B&JB&B55B#G&&@@@@P?P&@@@@B5~7@&BB##GPGB&&@@@@&PG#5~\n"                                         
											  +"~#^ P&&&&&&#B57YY~   .:^~:  :&@@&&&#BGPPG#&@&@&#&#BG:\n"                                        
											 +":B:  :~:?P#&G.              ?BB&&&@@&&#GPPG#&@@@&&&B#^\n"                                        
										    +"^P.         .JBGBY:        .B#B#&&@@@&&#BGGGB#&@@@&^^.\n"                                         
										   +":!             5@@@#^      :G@&@@&@@@@@@&#BBBB#&@G^   .\n"                                         
														  +".775&B5^   ?&BB@@@@@@@@@&&###&&&B!     ..\n"                                        
															 +"J&^!YPJG@&#&@@@@&&@@@@@&#PJ~..     ..~\n"                                        
														    +"J&@7 :G@@@@@@@@@&B#@@@&&#G!?.      .:^P.\n"                                       
														   +"P@@@#G@###&@@@&#&###&@@&&#GPP^       .YB^\n"                                       
														 +".YB#&&&@@&&@@@@&#&&B#@&&&&#&&&B5^      .~G5\n"                                       
													  +": J&B&#&&&@@@@@@@&#&@&BB&&P#&###&BPY:      ^B&^\n"                                      
												   +":^?&#@&&####&@@@@@@&#@@&#&&&&#&&&&BB&BB?.    . ~BY\n"                                      
												  +".&&&&&&BGB###&@@@@&&&@@@@#G##BB&#BB5B&&GJ7: :~7~.!B.\n"                                     
												  +"5@&##BBBB#&@@@@@@&&&@@@@@@#P#&&&&#&B#&&B5YJP#&#5!~5!\n"                                     
												  +"5@&&&&@@@@@@@@@&&&&&@@@@@@@&B#&&&&@&&&##&&&&BGBG5GPP\n"                                     
												  +".G@&&&&&&@&&&&&#&&@@@@@@@@@@@&GG#&@&&&&&&&&&BBPG&&@&G^\n"                                   
												    +"^P&&&&&&&#&#G#&&&@@@&@@@@&&@@#GG#&&&&@@@&&&&&@&&&BG&^\n"                                  
													  +"^B&&&&#B&#PGB&&@@@&@@@@@@@@@@@&&&##&&@@&&&&#B&PBYB.\n"                                  
													    +"^G&@#P#&55B&&@@@@@@@@@@@@@@@@@@&&&####&&&&@@&&G&!\n"                                  
														  +".!BBB&#P#&&&&&@@@@@@@@@@@@@@@@@@@@&&&&&&&@@@&BP^.\n"                                
														    +".B&&#BB##&@&@@@@@@@&@&@@@@@@@@@@@@@@@@@@@@@&GYP^\n"                               
															 +"7&###&#B&@@@@@@&&&&@@@@&@@@@@@@@@@&&@@@@@@@@&&G!.\n"                             
															 +":BGPB##BB&@&&@@&&@@G5&@&@@@@@&@@&@@&&@@@@@@@@@&5JY:\n"                           
															  +"~#GPG5YG#&&@@@@@@G  ~@##&@&B#&&#&&&&@@@@@@&BJP&##@G.\n"                         
															   +":BGPB&&#&@@@@@@B.   B@&BBBGB&@&&&&&@@&^...    ^JB@@Y.\n"                       
															    +".?G#&&&&&@@@@@G!    G@&&@#B&&&&&&&@@!           .~5P:\n"                      
																   +".!&#####&@@@~     7G@@&P5B&&@@@B~\n"                                       
																	 +"!G###&&@@&:      !@@@&&#&@@#~\n"                                         
																	  +"~&#B&@@@&?^     Y&#B&&&@@@#^\n"                                         
																	  +":&##&@@&G:^.    ^&GPG#&&@@P\n"                                          
																	  +"7&P@&@@&@G.     7BG###@@&@P.\n"                                         
																	  +":.?@&@@@@&B?   .BB&@&#&@@&&!.\n"                                        
																	    +"?^Y@@@@&&7    .7YG@##&#BG^.\n"                                        
																	    +". #&#&&G.       :#@@@@5.\n"                                           
																	  +"!~~G@#B&#.       :Y#@@@&&~\n"                                           
																	  +":GBGJ!^^          :@&@@#P^\n"                                           
																					    +"7@@@@@P7.\n"                                          
																					    +".5##&&B7:\n");     
			
			        break;
					
					case 4:
						enemyimage= ("\n"   
																																		+":\n"                                                                  
																											   +":                       .7\n"                                                                  
																											   +"!^                      ~.\n"                                                                  
																											   +".!                     ^~\n"                                                                   
																												+"^^         .        .^Y^.\n"                                                                  
																												+"^5~::.     ~:    .^::YY^:                       .\n"                                          
																											  +".::7!..^^   .J~   .~..^J^.:!     ..            .~~\n"                                           
																										+".     ~:..?: :!  .:7~..  :..?7.:~^   .^~            :!:\n"                                            
																						  +".^.           .:    .::.YY7.   .!5Y~:..::55? .   .:7Y.    .....:~!.\n"                                              
																						   +".^..          !Y:.    .?5YY5PGB5JP#BBBBJ?Y#J~~~^77!..  ^~::.7PP^.\n"                                               
																							  +":!!^.:^^.  .~Y?::?5G5JYBP5Y5J?5YJYYY??5PGB#G??P5:.  ^..^7!:^^\n"                                                
																							   +"^Y!^..:!   .JY5#BPY5J7Y!:  ^!?~77YJ!  .^!YYJGBB5?J^^~J?:: ~^\n"                                                
																							   +"^ :~?7!. ^5BGPYYJY?7~J. :^^Y7~~7!???.:!??JJ??7JBB5?YBJ ::^^\n"                                                 
										 +".:                                                    ::^:^JP??#GPG5. ^5Y?J!.:7J~Y~. ~7JJ?!Y!^!5GPGJ.^?JYPBY!.      .:^\n"                                           
										  +"G5.                                                   ..  :P5YJ5?J7~^~PPY5!!J5~ ^!~755Y^ ?J~.!YGGPY. ..~5#P5G~::~7Y^.\n"                                            
										  +":&&J.                                           ..:7^.:  5&PPG?!!YPGGJ:!J55P5PY55PGBBPJ!:!5Y?5Y5YJ~:~~^:~JBP?Y5?!:.\n"                                              
										   +":&&&P~                                           .^^?57J#5BY57:^J5GP5Y?5G##BGG######BBPPPPGBBGGGPGG5GGBPYJYGJGG^   ::^:.       ...:.\n"                            
											+":B#&&#Y5~                                    ...  ..~#55B! ^Y?7Y5G#G5&GP&###BGBBGB#########&&&&&&&&&&&&&&#&&B#B7~J!:..^7!^^^:.^^..\n"                             
											  +":G&&&#BJ^.. .:^.                .:::   .:^:^:^:  ^#G5B5~!~~7G##5G&G&##BBB#B##G#YBBB##&#5PBB####&&&#&&&&&&&&&&@@&&#57:55!:.          ..\n"                       
												+"~YYPBBGPY7!!!7!~:......    .... ...::7G?.^^^!~.5#Y5J?!J: G#PG&&B#57~7JPP##GB&YYGB##BP5BGGGGGG###BBBBBBB###&&&&&&@#~~.             .^^.\n"                     
												   +"!####GY77!77??7J77???7!~~~^~~~~^^^:!~^~^?P5Y5J555?JJ:5#G#&#GGP!~~JYPBP##G5PGPGGBG5??!~B&#GP5PYJJJYYYJ?!!YPB&##&@B.                ?7\n"                    
												+".7JY55G#BBGGP5YY?7?J?7!^^~^^^^^::::^^::^^^~^..#B5GYPY57J&BB&&&BY!YJ7PPG#5B&#GPGPPGPPPJJY^^7JJY555PGGGPPPP5555?GGPPB@&:      :~~~!!!7~~BG^\n"                  
													  +".P&##GPPPPP55J7?!!!!77~7JY555JJY?!!^~!7?GYGY.^?YJ5&&@@#B&G5??Y5G###&@&&&#BBBGGGPPP555GB#&&&&&&&@@@@@&#BBGJ?J7G@G      ..   .^!YBBBBY^\n"                
													+".75P5Y?YBBBBGP5YYJ75J5P?G&&BP5PPP5YJ??JJ!?77P7~!!!77?B#&^?5PYB##GBB#&&##PYPJ5Y?Y5PPPB&&&&&@&&&@@@@@@&&BPBBBY7JYJ@Y         .~~^^~?5GGBBPJ.\n"             
													+"...    :5#BGGBBG5PPGGBPP#&GBGGBBGGP5YJJ5PY7!7Y?^:^!YJGPG5?7PPJYJ5BB#&#G5PPG5GBGJ7YBGGG&@&@@PP&&@&#B####&#BBBY5GG&.       :!~       ~###BBG:\n"            
							 +".                              JP7:~##G#BGB#BP5BGP!YJ:^?JPPPP5YY5Y?~^!Y7G55JGB55JG?^.~^YGB&B5B#P?^:^?G##5!JJBG&@@@G5B#&&BBBBGPBG75&BG#&5         .?B?.     JBBGGBB:\n"           
							 +":PY^.                         .:    ~. 5BBYG5?7?!7:..    ~PYJYJY5Y55555PBB57!Y??J~..:!75B#&GB&#G7~^:JPB&&55YP##&&@@GG&#GG##B5?J5!~Y##&B            :B&Y^  ^G##B#B#B7\n"          
							  +".P@&P?^..                          .:^!!!~^^^^:^^~~!!!~~~Y5Y5YY5PPBB!~7?J!!~!7Y:.. . ^PB#&B&&GPJ?!7Y5G&&B55B&&&&#B&BBPPPB##P5GGB55&#&5:.            J@#5YJG&@&@#B5BP~.\n"       
								+"^G&&&#G5YP?^     .~~:::::::^~!777!~:^~::^!7?YYYJJJJJJJY77?YPP5P#BY: :755^..:7Y!:....YGB&&#&&Y5J7J5P&&#Y!P&&#B5YPBGP5GGB##5G&####B#&GY!^..          Y@&BB@@@@@&@&BB5~\n"       
								  +"~J5#&&&##BPYYJ??777???7?????!^^^^!!~JGBP555Y5J77??J555?7!!?55PPJY?Y5B5.....!GG55Y~~5PB&#B&&&BBG&&#P!^P&&GJY55BB5GGGGB#&#BGGGBY755!^:::: :7^~.     5GJ&@@@@@@@@@#5J~\n"      
									 +".~YJ?Y##BBGG5JJ??JJJ?JJ?^!~~~75^5&#P5YGPGGGGPP5J??JJY5PBP5?~?JYYPP!^??!~~~GP~~5PPPGB##BB#####5??5#@&GYJ?5B&@&#PYJPB#BB#G^^.:.                   ~@#&@@@@@@@@@&B?~\n"     
										 +".!##BB##BBGGPP5P55Y7J?7YYGPYG#G55G5:~~^?Y?7?J?JY5PGYJJ?!!7J5G5:.~B#5J7?~~??5?!?5YGBB#&&&BPB&&&BYY?P&@@@@@@&#GBBBBB&P.                        ?@&B@@@@@@@@@@P?.\n"    
										+"^!~:...7&##GPPGGG5YPPY?GPPGGYJPG?.:     .~YJJJYJ5Y#5!JY5PP####! . .G#GP7^^7J7 :?7!~^:~J?7~!!75J7JG#&@&&&&@@@@@##BB#&Y:                         !B5@@@@@@@@@@@G7\n"    
											  +"755?~:^5GGPYGB#P#GPGGGY???7!:      .!7J5PGPPJ?7?J?7~7JGG?.   ~B#BG7^7!:.!5J??!~^^~^~7?J?7JB#&&&&&&&@@@@&5~.:!^...:.                        ^5&@@@@@@@@@@B.\n"   
											 +":^.     .55^ .PY:!GB~...!^.:.        .^~!^!?5Y?.::^7YGG5YB7  .?&##G?:...7J77 ^7^77~~!^~Y5GGG#&@&&&&&@@@#7^::.                                 ~G&@@@@@@@@5\n"    
													 +".^    .  :B^     ::.      :~!~!!~JPY??J!?YP##P5PBJ!  :Y#P^ ..:!7??7~:^!?77YP7:!Y5GB###&&&&&&@&@#^.:.!~                                 ?G#@@@@@&@5\n"    
															 +".?.           .:~?J7!~J!GGJ7!!JPP5P#G55PG7^!. :^  :!?PG7:^^.~~!7~^Y&GJ5GB#B#GB&&&&&&&&@5!!: .!                                 ^75P#@@&&@&\n"    
													 +".               ^7!!?JYYYJ777YPJBBGYJ..7~YPP5PGGPGY7P7:^7G#&BGBGY7^.~~!^:.J&&####B#&###&&&&&&&@&^.!5J:.                               :P?!55P#&&&@^\n"   
													 +"^JGPY?77~?Y?!!7JJYYY5PPPP5YY5GGGY5P7.. .~^::.:.^5P55GBBBP75B#P5GP5Y:.~~77!~?P&#B##B#&&&&&&&&&&&@&!.^!!:                               !PP!!P55G&&&P\n"   
													   +":7G&&#&&&######BBBBBBBGGGPBGBGJJJ!~^^.      ~PYY5GPY^: 75GB#5P5555~:!7J!^!&&#BBBB#&&#&&&&&&&&&@&Y!:^?5?.                           .?YG57?PJYG&&&.\n"  
															+".:~:.7&B5G&&#P##BPP#G?GB. .:!~.      :JPYY5GPP5Y~?5YBGYGGY5G55:.!J!!B@&&B##B#&&##&&&&&&&&&&@@@&&#?!:                          !?JJGY75Y7JG&@Y\n"  
																+".~.  :#Y: .!B: :. 7Y          .^?PGPGPPB#?.::55YPGJG&G5GPY!:JPJ5&&&&#B###&&##&&&&&&&&&&&&###BY~!~.                       .??575P?YG?7YB@&.\n" 
																	 +"^.     .     ^.        .:5GGBB#BGB!:~  ^B5PGGP5&&GPPY!!GG5B@BG&&####B#&#&&&#GGGGGB&BBBB5YYY5Y7^.                    .JYPYYPYYGY!?P&@?\n" 
																						 +".~P##&&&#PYP7   .  PBGGB#JY##G5P?~7GPYG@&#&&##B#BB###&&&GP55Y5#BGGPYPGGBG5J77^:~~:.^:.  ....::?P75PPPPPJY#P7J5B@#\n"
																				   +".^7YGB#&G7BG:!B        .5BB##B:.B&BBPY7!!5GPPB&###&&#&&##&##&&#P555PBP555PGBB##BPPY?#BJ??5Y77!!!!?YBB##PJ5PGPYYBP555B@&^\n"
																					+":~~~: .  :   .        J&&&#5::&YYPP57?!~JG5Y?YG&&#&##BB#B#B#&&BPYJJGP5GPPB###G5GB5B&PPYP#B555PP5PB##BPGPP5GG5PG55PG#@@?\n"
																										+":&&&Y~B.~P^:?5P7YB7~?PG5J5G#&###&&B#B####&&PJ?JBPYYYG##BGGGBPG&GY5YP#&BGGBGPB&#&&&&###&BPBGP5YYG&&?\n"
																									  +"^P&G.^. ^:^.^J5Y?G&P?YYYJYBB#&&&##&&#&#&#BB###&##&#BGJPGG5PB#BPP&P5PPPB&&BGGGB&&&@@@&&@@&GBBGGGJ??GB7\n"
																									+"^JJ!       :!!YY?J&#?!5PYJ5G&#&&&&&#&#####&&&&&&#&&&&#5YJ555GBB5YJ##P5YPYG&&#GB&&B&@&&&@@@BGBGGGBGY??P~\n"
																											+"^J55YJ?JB&P!!JJJ?5#&#&&&&&##B###&&&&#&&&&&&B5GG55Y5PGBPJY?B&GY5BG5PB#G#&G#@&&&&@@#GBBGBBBG5J7?:\n"
																										+".^~7BGJJYP##P!!7JY5?PB&&&&#&##&#####&&&&#&&&#5JPB5PBPP55#GYYJJ#&G5YPBBG55G#BB&&#&&&&&55PPGGGBBGP5J:\n"
																									 +".^^757JGPB#BPJ!!!!JJ5PB&&#&&&#&####&&&&&&@&&&B5YYGBGPY5GGPBB5J5JB&&PPYJGB##B#P5#&&&@&&&BPPP5GBBY7??JY:\n"
																								  +".^?YY?JPB&@BY7777?YJPP5G#&&#&&&##&##&&&&&&&&&BPPP5GBBPPGG?7JB&BPGGB&&GPPPJPBBGGPJ#&&&@@@&BPPGGG5?:\n"       
																								+".~GGPPB&@@@@@57?JY55GBGGB&&&&&&&&##B#&&@&&&BPJP55BBGB#GGPG55P5#JJPB&&&#PY5GBBGPP5P&@@@&GY77??7!^.\n"          
																							  +":?JP##&&&&@@&#GYYPGB#&&#&&&&&&#&&&####&&##G5PG5YGPPBGB#BBPGBPPGB#BPPB&#GPGB###BBBB5PPJ7~.\n"                    
																							+":YP55#&#&&&&BPYJG##&&&&&&&&&&&&&&&&&##BBG5YY?Y5GGGGP5BBBGPPPGB###B#B##BBBBBBBGGP5YY^.\n"                          
																						   +"!BBB###@&&&BJ^: J@&&&&#&&&&&&&&&&&&#BP5555Y5YJYYYPG55YGGBGJJJ5G#BBBBBBBGBG5?~::~?Y!.\n"                            
																						 +".JBB&@@&&#BJ^.   :@&&&&#&@@@@&&&&#BGGGGP5PP55P5Y5YJJJ55?Y55BYJYBBBGB##BPY7^.\n"                                      
																						 +":~^~!!7!~~.      :7!!~!!!!!!!!!~^^^^^^^:::::^^:^::::::^:.::^^:^^. .:..\n");
			        break;
				}
			
			case 4:
				numberOfCases=(int)(Math.random()*4+1);
				switch(numberOfCases){
					case 1:
						enemyimage= ("\n" 
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYPYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJGBPYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYYJJJY55P5PP5YYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ?7?JJJJJJJJJJJJ5PGPPPPP5YYJJJJJYJ??YYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY5Y5YJJ?YYJYJJJJJJJJJ#PYYJJJYY5YYY5YJJJJY55??JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5BP55JJJJYJJJJJGBBGGGP5Y5PGPG5YYYY???!!!!?YJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ??J5JYYYJJY5Y5PBGGGP5YYY5YYY55PBBGBBBGP55P55YJJ5GJ?JYJJJYPYJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJYYYYYPGP5YJJJ?JJJYGBGGG55PPPP5PPGGP5PPPB##BG5J5GPP555?JJ??JJJ55J7?JJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJYP5YYJJYBBGPJ?JY555PGBGGGP55555PPPP5YY5PGBBBBBG5JJ5G5YYPGGGP5J???777J5YJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJYYJJJJJ5GB#P55PPPGPPGBPPPPPY77??YY55PPPGPPBBB##GP?755Y7J!??5BB5??JJJJ5P5YJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJY5PPGGPPGGGGGG5YPPGGP?~~~!~~77?JJ5PGBBBBB#BGPJ7Y57!!7J75G5?YYYYJ??JYYYYJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJY5J5PGGGGGPPPGGBGP5Y555PPPPPPP55PPPPGBBB##BBGPP?!5P5YYGPP5?PPJ5PBGJ777?Y5YYYYJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJYYY555YJ5PGGPPPPGGBP55YYJJJ5Y77~!7!7JYYPPBBBBGPY5BGPPY!!YPGGGGPYYP5?YJGBJY5YJ77777?5PYJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJ?JP5YYY5JJ5PGGPGGBBBPYJJJJJJJJJJ^:~!?JYGPPP5YJ7!~~^!Y5PP5Y7!7777??JJYPPGGG5YYJYGY5555?!?B5JJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJYY5PGGGPP55PGGPGGBG5YJJJJJJJJJJJY?7JY5YYGP5YJ????JJY5PGGPPP5555PP5YYY55PYJ?J5YY55YYP5PGY~?YJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJ555JJYGBBBP5PGPGGBPJJJJJJJYYYJJJJJYYYJJJJY55PGPPPPPPPPG##BGPPGBB##GPG#P55GGGP55PPYY5GG5J???YYJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJ5YJJJ5PPGBBPPGPBGG5JJJJJJJJYYJJYYYJJJJJJJJJJGBBBBGGBBBBB##B########BB5JJJGP55YPGBPYJY55PY5GB5JJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJYJJJ5G55PGBBGPGBBBYJJJJJJJJJJJJJJJJJJJJJJJJJYPGGB#BP5555Y5GBBBBB####GJJJJYYJJJPBPPPP5PPYJJPB5JJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJ5PP5PGGGGGGGGPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY5GBBGYY555PPG##BBGGGG##BYJJJJJJYPYYJ5BBGYJJYGG55JJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJY5555PPGGPPB5GBYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYBBBGGBBB###GPPBGGGP555YJJJJJJJJJJJPGY?YJJJ5P5YJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJ5555PPGPGPPGYGGJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY55B#BBGPPPP5PPPB#BGPY?JJJJJJJJJJJPGYJYJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJYP55GGGGGGPPG5BPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYY5PGPPBBGGG#GJY55YJYJJJJJJJJJJYYYJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJP5J5GGBBGP5PG5BPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY5PBGYPBBPP5PJJYJYJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJYJYPPPGGBGPGGGBPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJPGP55GGGG5YJJJY?JJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJP5PPPPGGGBP5B5JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJGBBGGPPGYJYYJJJ!7JJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJ5PPPPPPG5YG5?GPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYPGGY!?GPGPPP55JJ!7JJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJYPPPPPPPPYYPY?PGYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYPGPPGPPPY5PG5PPYJY!?JJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJYP5PPPGGPJYGY!5B5JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5GGGGP555JYPYJJJJY7?JJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJ5PGGPGG5?YGJ7YGPYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5BGGP55JYPGJJJJ7?JJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJPGGGGGGGGGPPG#GJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYP5555Y5PBYJJ!?JJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJYGPGBBBBGG55GBYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJGP5Y5YYBP5Y77JJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJ5PPGGGBG5PY?YGGJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJGJ?JJJJBY5P^?JJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJPPGGGGBGY5P?J5BPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5G5Y5JJJP5PP:!JJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJPPGGGGBGYJGY?JPB5JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYY5JJJJJJJY~!!JJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJPPGGGGBG5?5P7?5BG5JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY7!Y7?JJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJPPPGGGGG5?JGY?J5BGJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ!YJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJPGGGBBBGPPGGPPGBBGYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ?JJJJYYJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJ5BGBBGBBGPG5JJYYGBP5YJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYYYJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJYGGGBGGGB5YPY!^!JGBGP5JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYYJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJPGGGGGGGG55PY!^!JPBBGJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJYPPGGGGGBG5Y55!~~7Y5PG55JYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJPPPGGGGGBP5JJ5Y7!777GBGYYJYP55YJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJYPPPGGGGBPPY??5P5P5JYGGBGPBBGP5YYYY?7?JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJY55GBBBBGPP5555YYJYYJY5GBBPJJJJJYYJJ?777?JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJ5BBBGGGGGGGPP5555J??J5GBG5JJJJJJJJJJJ?7?JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJPGGBGGGGBBGGGBBPJ7777?YG#BP5YYYYJJJJJJJ?JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJGGGBBBBB##BB#PY??7?77!7YG#GGP5Y5YJJJJJJJYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJYGGBBGGBB#BBGPPY?7!~!77?!?5GBGPGY???JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYPGGGGGBB#BG5PGPY7!!~~?5JJ?JG#BGP5Y????JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJPPPGGGBBGPPPGGGPY?77!J7PBY?JPBBBBBGPYJ??JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYGBBBBGJ!JG#BBGG5Y5Y7!PPGB5?5P5PGGGBBGY??JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYGGPGP!:?BGGBGP?7?55Y5YYP#G55J5PJ5BG#PJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYP5PP7^PB5YPBGY7!JYYJ7?J5BBPYJ5YYGY5PJJJJYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5Y5P?!BG5??5GGY77YJ!~^!?PBBGJY5?55J55JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY5YP?7BPYY7?PGPJ!?Y7!~^!?5BBP??J7YJJPYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5Y557G5J??!YGGP?!JJ!!^^7JG#B5??77?7JPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY5YP?5J?7!775BGP?7Y7!!:!JP##GP5YYY?!YPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJPJPYJ#?777!JGGGPJ?J7!^~JP#BGGP5555??55JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ55Y57#B?7!!75GPGP?7?7^~JP####BBGPPP77Y5JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY5P?5#B7!7!YGP5GB5Y7~~YB#BB###BBBBPY?JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5#GY#&P~77J57J#GPGP5JGBGBBBBBBGBBBBB5?7JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYBBBB##J~JY?^PPYJY5PGBPGB#BGGG55PGBBBBP??JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ5PPPGB##775!?BYJ7JYYYYJ5GBBBPP5JYPP5PPBBY7JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJPY5PPB#&P!Y~PBP?!JY?!~7?PGBBG5Y5?Y5YYY5GBY?JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYPJPP5BB##5?!GGP?7JJ77^^!YBBBB5?5YYYYJ?JPPJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJP5JPG5GB##G7Y5PP7!Y?!!!:!JGBBBP?JPJ7Y5?7PYJJYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJY?JPB5GB##G!P5JGJ~J?!!!:~JPGPYJ77J7!?Y7!PJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n"
							+"75JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJYY5PPBG!PYJJJ!7J7!~~!???JJJJJ??????7JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ57\n");
			
			        break;
					
					case 2:
						enemyimage= ("\n"
																+".                                                                             :\n"                                                
															   +":&J.      ..                                                         ..      .J&:\n"                                               
													   +".?       #&BGY77JPGGG?:.                                                 .:7GGP5J!7YGB&#       ?.\n"                                       
													   +":@P!.   .7#BBBBGBGBB#BGY7!:.                                         :^~!7?B#BGBBBGBBB#7.   .7P@:\n"                                       
												+"57      P&#BG5PGGPPBGPPG&@@@@@&P?777?~:^~.                           .^^:~^?JY7JB@@@@@#Y7YGBPPGGP5B##&P      75\n"                                
												+"G&GJ~::!JBBGBPYPB#&&&#BGGB#&&#BGGG5GBBBGGGPJ~:::...:?YYYY5J:...:::!JPGGGBBB5J?YY5G#&PY7?PPGB&#BGPGBPPGJ!::~JG&G\n"                                
												+"~&#BBBGBBGG5557~Y&@@@@@#GGPBGPGGP55PGGB##&&&###BGPGBBGPGPGBBPPGB###&&&##BGGGGGGYY55GYYY#@@@@@&B555GGPPBBGB#B#&~\n"                                
											  +".YPGGGGGY5G&@@&#@P7YPB#GGGG55??7J?YPG####BBBPPPGGPJJPPP?Y555PBGP5PPGPPPGBB###BBG5?!!~?5PGGGG#BPPB&@@@@@&GPPPPGGGGY.\n"                              
											 +"^BBGGGPGJYY5G##&&&#BGGPGGGGY~~777J5G#BBGGGP55GGJ?777777?7?Y5GGGGPP5Y5YGG5Y5GGGBB#GY?!!7?JPBGGGGGGB##&&#GY7!~75GGGGBB^\n"                             
											 +"^B#&#B5P??JPGPPGGPGBG###BPP5PGGGPPPYJY5GPJ?PBPYY7^~JPB#PY?7?YG##BBPGGPPGBP?JPP5J7??PPG5PP55GB###GGGPGBPP5Y7~^7PG#&#B^\n"                             
											   +".^J#&#GGBBGGBGGGPPPPGGGB###BB#JYJYPGG575##5JGPYYGG5P???7?7???P5G#GGGGGG##Y?5GP55P5J#BB###BBGGPPPPGGGBGGBBGPB&#J^.\n"                               
												  +"~@&&&&&#&Y!7JJ5B##BBBGGPPGPB5YPBP55#&#PPJPGPGB5577???7???7755#PPPGGG5#&B5YP#BBGBGP5YY5GBB##G5J?7!Y&#&&&&&&~\n"                                  
												   +"&@@#YGG&BGY?7!GGJY5PPPGBG555?5Y!?B&#GYPPPGYP#BP???J7~7J???PB#PYGPGGYG#&B?!5GGGBGBB5!^^:!JGG!7JYP#&&&&&@@&\n"                                   
												   +"5#&&#BPG#@&&#GY?777??G#PJJ!J?!!~P&&#BYPGPGBJ5GBBP5Y???Y5PBBG5YBGPGPYG#&&P7YGPPJ?PG#P^^^!7?YG#&&@&&@@@&B5J\n"                                   
												   +"~&P5PYG#&@&@&&&&#BPJJG?777?7!~7?5&&&P5YGGPB#B5YYYY5PP5Y55YY5B#BGPGY5P&&&5?PGJ?757~JGJY5G#&&&&@&@@&&&#G?7:\n"                                   
												   +".#G?5?!?Y&#&&&&&&&&&&#P5?!7^^?J5JG&&B5YYGGPGGBBBBBGP5J7JPGBBBGGGGYY5B&&BYP57^:!77!5#&&&&&&&&&&#&####BG#B.\n"                                   
													+"Y#G5Y5PB###&##&&&&&&&&&##PJ?777?JYB&#PJ?YJ?Y555555J!^~7?J55Y?JY?JP#&BY?!~~~??YPBG#&&&&&&B5?5PB&#&##&&&Y\n"                                    
													+"~@#G###GB##&&&###&&&&@&&&&&&#GY?!~~!YPGPJYG##GYJY?7?7?JYYGB#GYJGGPY?7!!?JP#&&#&&@@&&&##GJJJGYB&&&&&#&@~\n"                                    
												   +"^G@@@&&#&&&&&&&#G#GB##&&@@&&&&&@&#P5?7!~B&GY?P##BBPJPPBB###5?YG&B~~7J5G#&@&&&&@@&@&##BG#GY5B&PG&&##&@@@G^\n"                                   
												 +"^P&@@@@@@&##&&&&&&#BGPGG&#P5B&&&@@@@&&&&PP&#5B#&#&&#B5B5BB&&#&#B5#&PP#&&&&@@@&&BBGP#&GGPPB##&&&&&##&@@@@@@&P^\n"                                 
											   +":Y@@@@@@@@@@@@@&&&&#&&&#BB#&P7J5G###&@&&&#&&#&&BB#&&&&&&#&&&&&&#BB&&#&&&&&&@&&##J!75B&#B##&&&&&&&&@@@@@@@@@@@@@Y:\n"                               
											 +":?&&&@@&&@@@&#?~B@@@@&&#&&&&#&#GB###BGG#&&&&&&&&&#&@@@@@@@&@@@@@@@&#&&@&&&&#&#GGB###BGB###&&&#&&@@@@B~?#&@@@&&@@&&&?.\n"                             
										   +":?P@@&@@&&&&&#~.   :P@@@@@&##&&&&####B#&@&#BGGB#&&&&&@@&B5J7~7J5B&@@&&&&#PYY5G#&&&#B###&&&&&##&@@@@@P:   .~#&&&&&@@&@@PJ:\n"                           
										  +"J@&@@&@&&&@@#Y.       .&@@@@&&#BBJ5#B#&&BGPPGG##BB#&@@#55?^~:.:~^?55B@@##5YYB###&&&&####PJBB#&&@@@@&.       .Y#@@&&&@@&@&@J\n"                          
										+"7P#&&@&&B#&&@Y.          .G@@@@&#BG55GB&&&&55JP5G#&&#@@&YYY7~!:.:!~7YYY&@@#&###&#&&&&&&#BG55GB#&@@@@G.          .J@&&#B&&&&&#57\n"                        
									  +"^#@@@&@&&&&@&P^              ?&@@@@##BB#&&&&&BB##GB#&#&&@@G55J?Y?!?Y?Y55G@@&&&&#&&&&###&&&&#BB##@@@@&?              ^P&@&&&&@&&@@B^\n"                      
									+"!#G@@&&@&&&@&#J.                .P@@@@@@@@@@@&&&#GB&&&&&&&&&&&BG555Y555GG&&&&@&&&#&&BB&&&&@@@@@@@@@@@P.                 J#&@&&&@@&@@G#!\n"                    
								   +"!@@@@@@&&&&@P~                     !#@@@@@@@@@&##&&B5&&&&&&&&&&&&##BBB##&&&&&&&&##&&5G&&##&@@@@@@@@@#!                     ~P@&&&&&@&&&&GJ7^\n"                
								+".!?YJB#&&&&@@#?                         :?B@@&@@@@&&&&&B&&&&&&#&##&&&&###&&&&##&&&&&&&&B&&&&&@@@@&@@B?:                         G@@@&&&&####&&&G!\n"              
							   +"YG55GGBB#&&&&&&#J:                          .B@@@&&@@&&&&&##&&G5GB#&&&&&&&&&&&&##B#&&GB##&&&@@&&@@@G.                          7#@@&&&@@&#BBBB##B@Y\n"             
							  +"P@BBB######&@&&&@@@B:                          B@@@&&&@&&&&#BBBG#&@@@@@@@@@@&@@&@&#PPJ!?5B#@@&&&@@@B                          !#@@@&#&&&#B##&#B##G&@?\n"            
							 +"5@G55B5B##BBGB&&&&B#@@Y                          ~G@@@@&&&&&&&#&@&#&&&&&&&&&&&&&&&#&&&555JG&&&@@@@G~                           #@@&&&&&#BGB###BG#&B#&@?\n"           
							+"P@&#5JJ?5GGGG5JG&&&B&&&&                            .~P@@@@@&&&&&B##&&&&&@&@&@&&&&&#BB&###B@@@@@P~.                             &@@@@@&#BGGGGGGGGB#&&&@@7\n"          
						   +"?@@@&PPYYYY55P5PJ5#@@@&@#                               .~Y5G&@&&&&@@@@@@@@@@@@@@@@@@@&&&@@&P5Y~.                                ~&@@@&#BBBBBB#BGGG#&&&&@G\n"          
						   +"G@@&#&GGPPGBGPYJ?JYG&@&#:                              7GPG#G#@@@@@@@&&&&&&&&#BBG&&&@@@@@@@#G#GPG7                                Y@&BGGPGGPPGGGBB&#&#&&@J\n"          
						   +"~@@&B&&#PPGGPPP5YY5PYBP                              .B@@@@@@@@@@@@&&&&&&&&&&##BB&&&&&@@@@@@@@@@@@B.                           :JP#B5YGGPGPGGGGGGPB&&#&@&:\n"          
							+"&@&B&#BPPGGB#&#&#&#GJY5!^                          !&@@@@@&&@@@@&#&@@@@@@@@@@@@@@@@@&#&@@@@&&@@@@@&!                         ?#&GBJY?P?5GGGGPPGPGB#&&&@&.\n"          
							+"#@&#&##5P##&&&@@@@@&&P?Y5?!                       Y@@@@@@&&&&&&&&&@@@&@@@@@@&&@@@@@@@&&&&&&&&&@@@@@@Y                      .P&##GGP5YJJ5PPPGGGPPG#&&&@@G\n"           
							+"P&#&##B5JG&@@@&B&&@@&#5??JPY:::                  :@@@@@&&&&&&@@&&@@&@@@@&@@@@@@@@@@@@@&&&&&&&&&@@@@@@:                    ^G##BGGBGGGGGGGGGGGGGGBB#&&@@^\n"           
							+"J#&&##P?JY&@@B7J7J5#@&#PY5P#&&@J               YG:P@@@@@&@@@@&@&@@@@@@@@@@@&@@@@@@@@@@@&@&@@@@&@@@@@P:B5                 ^#BB###&&GBBGB&&##GGG#BBB#&&@&.\n"           
							+"P@&##BGYP&@#B@@@&BG#@######&&&@J          :GG5BP!Y@&@@@&B&@@@&Y&&#&###BBB@@@BBB#B#&#&&Y&@@@&B&@@@&@Y!PB5GG:            .GGB#&@@@@&&&&&@@@&#BBBGPGGGPGJ\n"            
							  +"5PG###B#&@#@@&&#&##&@@@@@@@&B&@!         #@@@&P@@@&#&@@@###@@!P##&B&&GG&&@&&GG&&B&##P!@@#B#@@@&##@@@5&@@@#            P&&&@@@@@@@@@@@@@&BGGGBGPGG?7B&P.\n"          
							  +":?YPGBB&&@@@G55P5Y5J&@@@@@&GP?B@7        @@@@@BB&GBY~?&@@@!P@G!5?7??7??7?Y?7?77????5!G@P!@@@&7~YBB&BB@@@@@           .@&&&@@@@@@@@@@@@@@&PP55GGGPYB&&&Y.\n"         
							   +".7G&###&&@GPGGPGGP55&@&&&#GBB&@&.      .P@@@@@#P?~!: :#@&~:&#.::~~~~!7^^:^^7!~~~~:..#&:~@@#: :!~?P#@@@@@P.       .^?GBBB#@&&@@@@@@@@@@@@BYYYGGPP&&&&BBP\n"         
								 +"B@&&&@@@BGBBB#GBB##&&#&@&&&@@@Y        .JGPB#&&BG!. .Y!..!.. .::..^:. : .:^..::. ..!..!Y. .!GB&&#BPGJ.       ^?PBB55PB&@@&@@@@@@@@@@@@P775GGB&&&&BB&&~\n"        
								 +"J@@@@&&@@##&&&?JYPB#@@@@@@@@@&!                 :::    . ...                      .. .    :::               .GPPPGY7Y&@@@@@@@@@@@@@@@B77PGG#&&#GG#&&J.\n"        
								 +".&@&@&&@@&#&&@G!P5J5G&@@&@@@@!                                                    .                         J###GGG55G&@@@@@@@@@@@@@#55GB#&&#GB#&&#7\n"          
								  +"^@@@@@@@@&&&&&P7555?Y&&&&@@@~                                                                              7&&####BGGPGB&@@@@@@@@@#PB##&&#B#&&&&!\n"            
								   +":#@@@@@@@@&&&@YJ?7?#&@@&@@&                                                                                ~&@&&&&&&&####&&&&&&&&#&&&&&&&&&#&@&7\n"            
									 +"Y&@@@&#@@&&&BJJB@@&&@@@@@:                                                                                .?PG####&BB57?YBBGBGGBBY?&&&&&BB&&&P\n"            
									   +"7PPG5G@@@@@@@@@##G5&@@@B^                                                                                                       ~&#&#B#&&&@!\n"            
										  +".  :?B@@@@&&&&##&&@@@7                                                                                                     ~Y&&@&&@@@&P^\n"             
												+":B@@@@@@@@@&@@&.                                                                                                    :JJGB55Y7~^\n"                
												  +"!&@@@@@@@&@@G\n"                                                                                                                                
												   +".5@@@@@&&&&#\n"                                                                                                                                
													 +".!B@@@&&&~\n"                                                                                                                                
														+".!PP?.\n");     
						
			
			        break;
					
					case 3:
						enemyimage= ("\n" 
														   +"..::~7^::.\n"                                                                                                                                           
													+".:~?JJ555YJ7~^..\n"                                                                                                                                   
											   +".:::^^~^^^~~!?5GB###BBPY7~^.\n"                                                                                                                            
													+".:~!J555PPPPPPGB###&&&#BGJ7^.\n"                                                                                                                      
														  +".^!J5GB######&####&&&&&B5J!^.\n"                                                                                                                
															   +".^!?YPG###&&##########&#BPY!:.\n"                                                                                                          
													   +"..::~7777!~!!!7?JYPPPGB##&&&####&&&&&#BPJ7~~7!:.\n"                                                                                               
															+".:~!7J5PGBBBB#BBBBBBBB######&##&&##&&&B###BGJ~.                    .^7?JJJ!:.      ......        ..::.   J&B5?!:\n"                           
																  +"..:~7?YPGBBB##&&&&&&&&&&&&&&&&&&&&@@@&&##GJ!:.               ...::~J#PJ!~          .  :: :~:^~:.    J@@@@@&GY!:\n"                      
															   +".:^~!!~~!!?Y555PPGGGB####&&&&&&&&&&&&&&&&&&###&#G5!:                   ..YP5~ :.      ~ .P?..       .   :G&@@@@&&&#P?^\n"                  
																 +"..:~!?JY55PGGGBBBBB###########&&&&&&&&&&&&&#B##&#G5Y7.                 :@G7 .5!. . ^Y ?Y~    :G55!::^:.^^!5B###&&&&&#Y~.\n"              
																		+"...:^~7?YYY5PGGBB###&####&&&&&&&&&&&&&BGBB#&&#GPY!.              5#G~^JJJ::  .^YJ!     !&Y^:^!!.:~:^~~~^!5B##B#BGJ:\n"            
																			  +"..:^~7JYPPGGGBB######&&&&&&&&&&&&&BBBB###&&&&GJ!:.          .~?5P5?PY::~:..     !G&&&#BPJ7!J7~:~77:..^~~7YGBBP!.\n"         
																		   +".:^!7?Y5PGB######BB###&&&##&&&&&&&&&&#BGBBBB######&#BPJ~.         Y&#&G5B^.         :G@@@&&###GJY??J?!::.   ^5GBB#G!\n"        
																				+"....:^~!?J???YPPPGGB######&&&&&&&&#BBBBB########&###G?.      5@#&BPY7!.       .:?#&@@@@&###B5J?7!!:.    .^5BBB#P:\n"      
																					 +"..^7?YPPGBBBBB##&&#####&&&&&&&&#B#################7    :G@@@@#BG7:.   .~77J5B&@&&@&&&&#G#BBY7^.. ..   !PGB#G~\n"     
																				   +".:^~!7??77?YYY??5GGGGB##&&&&&&&&&#BBBB####&######BB&G   :75BGB&BG?~:..:!JPB####&@@@@@@&&##GPY77~:....:.. :JPGBB!\n"    
																							  +"..^7YG####&&#&&&&&&&&&&&#BBBBB########BB5. .~JG&&BB@@@@&#BGB##&###B&&&&&&&&####BBY75J7~:..^~:.. ~PGGG!\n"   
																							+":~7?YY5P55PPGBB###&&&&&&&@@@&#BB#######P?7^:~J#@@@@@#&&&&@&&#BPP###&B###&&@@@@@&&&&55G5J7~: ..:^^. ?PGGG^\n"  
																							+".....::^~7YPGB#######&&&&@@@@@@@@@@@&BGJY55PPB#@@@&&&&@&&@@@&G57?G#BGB##&&&@@@@@&&&#GP57~^:...::7. :5GGGP\n"  
																								   +":7?J????JPB#&&&##&@@@@@@@@@@@@BGG5PBGGPB&@@@&@&&&#@@@&GG7^JBGPB###&&&@@&#BGGBGGB57^??^:~^~:: 7GBBB~\n" 
																										  +"^J5P5PYG&@@@@@@@@@@@@@@#BBGPBBPYP#@@&@@&@@@@@@&P?^:^7:.!PB#&&#&&&&&@@@&&#P?BP55?^.^!^ .PBBBY\n" 
																										  +"....  .&@@@@@@@@@@@@@&@@#GPP#&&5?5#&@@@@&@&&&&B~  .      .^Y#@@@@@@@@@@@&&JP#G5Y?:..   !BBBP.\n"
																												 +"?#&@@@&&@@@@@@@&@@&#P#@@#Y7PG#&@@@@@@&&?.            ^GB&@@@@@@@&&&&P5:~G5J! .. :P#BB^\n"
																												  +"~GGP&@@@&@@@@@@@@&&G&@@G!~5G5P#@@@@&@#.              ^?5#@@@@@@@@&&G5GBJJY. ~~.~B#BB~\n"
																													+".B&#P&@@@@@@@&&&&&&@&Y~PBBBBGG@@@@@#Y?:              .?#@@@@@&&&&#&#GBP7Y~~P^?##B#!\n"
																													 +":..B&P#&@&@@&&&@@@&&PJGB###&&&@@@#BB!:                ^G@&@@@@@@@&&&#BB5?7~JY&###~\n"
																													   +".^.:&&Y5@&@@@@@&&&B5G####&@##@GJG^                    @@@@@@@@&@@&&&BGP^~JY&###~\n"
																														+":..^  P@@@@@@@&&&5GB###&&@@&&#@B:..:                 5@@@@@@@@@&&&&##GY?^5&&##~\n"
																													   +"5&&:   5@@@@#J&@&GYB####B#@@@&&@J^^!!..                .G@@@@@@@@@@&#&P&Y~P&#&#:\n"
																													+"7GYP@&5  5@@@@J. 7J!5G#####BG&@@&&B??JJ~::.                 ^&@@@@@@@&&@###PY5&#&B.\n"
																												   +"7&&@YY#BJ~&@&&!      ?B####&#B5#@&#G5GGPYJ7!:                 7@@@@@@@@@@&@G#5#&#&G\n"
																												 +"^?5BG&#5PGBB&@B?PP!:  .5B####&&#BPG#BB#B##GPYJ?~.              :#@@@@@@@@@&&@B&J&&&&5\n"
																											   +".^GB5Y7~?PGBGGPG&#&&5. .YG######&&&B5G&&#######&&&B:             P@@@@@@@@@@&@#&#J&&&&!\n" 
																											  +"~PGGJYPGB####B#&#G!::. :#BG##&####&&&B#####&&&&&&&##7             .&@@@@@@@@@@@&&JP&&&&\n"  
																										   +".~PGP5YGBBPGBGB&#&5.     ~#&#B##&&&##&&#&B5B&&&&&&&&&@&#:             ^@@@@@@@@@@&@&PB&&@5\n"  
																										   +"~?5PJGBBGPY5BPYB#5      7&&@#B##&&&&#&&##GY&&&@@@@@@@@@&~             .@@@@@@@&@@&@#G#&&@!\n"  
																										  +"^75#&BBGGPG#&#.  .      Y&@@@&B##&&&&&#&#B5G&&@@@@@&&#YGB.             ~@@@@@@@@@&@@#B&&@P\n"   
																									  +":~YP###&##&##&&&5.         P@&@&&&#&&&&&&&&&&&GB&@@@@@@&&##!JBJ.           7@@@@@@@@@@@&&#&@G\n"    
																								 +"..~JGG##BB&&&&&##P?~.          G@@@@@&BB#&&&&&@@@@@@P5@@@@@@&&&@5!#&~           .#@@@@@@@@@@@&&@J\n"     
																							 +"..^?J5G5PB#B##&&BP7:.             P@@@@@B^ .~&&@@@&#@&JB&Y&@@@@@@@@&&&@P.            ^@@@@@@@@@@&@&:\n"      
																						 +".:^!YPY5GPPG####BP?^.                5@@@@@5.    P&@#&#?7Y~~?!B@@@@@@@@@@@B.              5@@@@@@@@@@@J\n"       
																					 +".:~7YYYP5GGGB##BGP?^.                   !@@@@@?    .&@@@&@@@B#&@#.7@@@@@@@@@@&.               Y@@@@@@@@@@@~\n"       
																				 +"..~!?YYYGGB#B###B5?:.                      .&@@@&~      B@@@@@@@@@@&!~&&&@@@@@@@@Y                &@@@@@@@@@@B\n"        
																			 +"..^~!7?JY5BBB##BG57:.                          Y@@@@~        #@@@@@@@@#P^ P@@@@@@@@@@:               .&@@@@@@@@@@:\n"        
																		 +".:^~!!!!?YG#####GY!:.                             .@@@@?         !@@@@@@@&75.  G@@@@@@@@P                 Y@@@@@@@@@Y\n"         
																	 +".:^~!!!!7JPGB###GY~:                                  ?@@@B           P@@@@@@PY~.  Y@@@&&&#7                  :@@@@@@@@G\n"          
																 +".:~!!!!!7J5G#&&#BY~.                                      B@@@^          .&@@@@@&#@G~  5@@@@@#:                   .@@@@@@@B\n"          
															 +"..^~!7777J5G#&&&B5!.                                          &@@B            !&@&&@&&&G.  P@@@@@P                    .@@@@@@P\n"            
														+"..:^~!7777JPB#&&&B57:                                              &@@Y             .&@@@&&#:   #@@@@P                      ~@@@&!\n"             
													+".:::~!77?JYG#&&&&#57:                                                  #@@!              ?@@@@#B. :!@@@@P.                       ^P?.\n"              
											   +"..::^~!7??Y5G#&&&&BP7:                                                      P@@~              .&@@@&B:G@@@@@&.\n"                                          
											+".:^~~!7?J5GB&&@@&GY~.                                                          ^@@?              :&@@@&#Y@@@@@@&.\n"                                          
										 +".~!777J5G##&@@&B57^.                                                               &@P               .#@@&P.@@@@@@#.\n"                                          
									 +".:!7?JY5B#&@@@&GJ~.                                                                    ?@#                ?@@&&.7&@@@@@P\n"                                          
								   +":7Y5PP#&@@@#GJ~.                                                                          #@:               .&@@&: #@@@@@@?\n"                                         
							   +".^?PB#&&&BPJ~:.                                                                               :@Y                B@@&~ &@@&@@@&~\n"                                        
							+".~YGBBPY7^.                                                                                       J&              .~#@@&5^Y@@&B#@@P .7\n"                                     
						 +".^7J7~:.                                                                                              ?            7G7&@@@&###B@&&.7@@~J@.\n"                                    
						 +".                                                                                                                 ?@J?#&@@@&P&&P@@:?@@&&@:\n"                                    
																																		  +":@#GB&&@@@@##@BB@~.G&@@@:\n"                                    
																																		  +"?@&@&G&@@@@@@@@&&.  ?@B7\n"                                     
																																		  +"?&B&#GGJPGYB#B5G:   ::\n");  
			        break;
					
					case 4:
						enemyimage= ("\n" 			
																				+".^.                                                     ^J.\n"                                                            
																			   +".:~Y7.               ^5                  J7            .JP: 7^::\n"                                                        
																			 +".:~::GB5~.      ~G~!  ^&J                  ^@?. ~!55:  .~YP7^G&J~7!^:...\n"                                                 
																 +"::.:~?Y5PGB#&&&####5YJ!..^7G&&&&!G#&^  :^:   .    .^:   #&B?G&#&&J755?5#&#B#&&&&&#BBBPP57~!~:\n"                                      
															   +":JP#&&@GP?Y7~^~Y&&#B#GYYP5P#&#BG#&&##G :YP!.         ^55! 7&#&&#BG555YPPB&#&B#&&G~.::!^7?G&&#YY!.\n"                                       
																+"...^!~   :??7JB&&####BBPJYYJ5BG&&##&#5G7              ^PGB&&&#&G?55PG&&#B####BB#BP55?:   ^..\n"                                           
																	  +":JGBB#B#BBB#&#&&#&#BBPJ5P5##&&#B:^^            .~.J&#&&BPYG#BBB&&&&&BBGG#&&&##B##Y:\n"                                              
																  +"^?P#&&#&&&#&&#GB####&&B##BBP5YY&##BGBG.             ?B#G###5PB##BB###&##BB&&&&#&#&&#G&&#PJ:\n"                                 
																  +":^~7J5#&&&&&&&&B####&#PB&##BBGPB#&#B!   .    .    .  :Y#&#5GBBB##BGPPB##B&&&&&&&&&&#G57~:.\n"                                           
															 +".:~??:^^~P#B#@&&&&&&&###BPPPG&&&#B#GJGB#GP##PB#GPPPGGB#GB#B5&P7G#BB##&GG?5BB#&@&&&&&&&&&GBBP~7!?PY7^\n"                                      
													   +"..    .?GBGGGGGGBB&&&&&&&&&&BBGP?PBB#G##GGYJ&BJ??!~7^:....:^!!!7??Y5Y5G#&GGB#G5GGBBBB&&&&&#&&@&#BGGBGGGGJ:     ..\n"                               
													 +":Y^      .JGPGGBB&##@&&BB##&###BBBGGBGB5##B5PPY:        . . .       :5G5B#&PGGBPBBBPPB######B#&&&B##BBGBPP~.      7P.\n"     
												   +".Y&!     .?GPGGGG&B5JP#&&&&&&#####PGBGPBGPP#BBGP!..~.    ~PYPJG:      .?GGGGP5GB5G###5B#B##&&&&&&&GJ?PBPPGPG&@?.     ?@Y.\n"                           
												 +".?#@5..^!7Y&@GY??5PP7~:^~7P&####&BG##GBBGGBGPGB#B575B#JJPYYJ&&&&#5J7~^::^J5PGGGB##B#BGPB#BG#&##GGPJJ?YJJJPJ:^7JP@&G5J!^~&@#?.\n"                         
											   +".?&&&@&####B&#.    ^P7Y5GYYJJY55PBBBJ:Y#PPBBB##BB&&BG?:P?~~: .PG@5J..:^^7?5GGB#BB##BPPGPBBJ.5GGP55PP555BP5~^5     ^&#GB#B####@&5.\n"                       
											 +".J&@G~...:???GBJ~:~~~G5^^JGPGBBGBB55GG7~YGPGGPGBBBBB#PPGPJ!.   ~?.&.P:    ?PG5G#BBBGPPPB#55GJ75B5Y5#GGBB57~.:~#P7!~~7P#P??7... ^5&&5.\n"                     
										   +".J&#J:  .^7Y5YBBGJ:..~PGJJ7!^!?PG#PGYJPG#P?5BG#PBGG#BBB#GPB#Y~. .B7.G 5B  :J#BPB#BBB#&GBBGB?JYYGB5?JB?.P#GPYY?YYJB?.  :JPBBPG5Y~:. .!B&5.\n"                   
										  +"?#G~  :?5PPY?7GPJJ!..:?BG57^P!PGGGB5J7P7^BG5P55#&#B#@#BB##BBPBG!.5B:.5 .BP~Y#GBBB#&BG&@#B#&G5G55B5:?GYY7G#BBBBGPP5GG!^::?YYGJ:~?5GGJ~  :Y#5.\n"                 
										+"!&G.  ^JJ?!?7  ^BJ7^....^Y:5B5G####G5JGGBGYB#B#B5YGGG#&#B&###B###5G##&BG5G:BBB#######&B#&BGGGJP##B#GPBBGPJPB##B#&&??PP.   .:!?P:.:Y!^~!7^.  G&7\n"                
									  +".P&Y.:7^^^!!~PJ!YP.        :.YJ~B&##BBGGGGGGGG5G#&#BYY5GBB#BPGGBB&&&GB&&BB#&G5#&&BBBPBGB#BBG55YB#&#P5GGPGGGBBB57P##&P:.^        .P!^7?!?7~^!J^:J&G^\n"           
									 +"7#GY7J55YY7:..   .            . :?#GP7~G77YGGGBBB#BBGGGPGBGGB5YG###&&B##&&&&&&BB&&#&#GJ5GGGBG5PGGBB#BBBBGGJ~~.5: Y^5B                    .!J5PPYYYG&J\n"             
								   +":G#P555PY!.                         #7^^ ^!.^^PYYGB###GBBGBGGG####GB#BPPPGB##BB##BBB#BGB###GBGBGBBG##BBPY?~~! ~!!  ?.PB                        ^J5555P#B~\n"           
								  +"!&BPY557:                            BJ.~  75:^.~GG##B#5Y?..!?JBBB&&GGGB##GB#BB##&&##GG#B##G7J!. PJJ#B&#G5.  .:YG:  7.?B                          .~Y5Y5B&?\n"          
								 +"P&GY5Y~.                              J5 ?.:75YY5PBG5PPGY!J:~~!YYJ:P&#GB#&&&#BB##&&&&#B&#:^YYY~~^:J:JG55GGPYJ7:7GG!:!? P5                             :?5YP&G\n"         
							   +":B#55J^                              ..^7P!55YP5GB::^~PPGGYJYJGBBBB###BPG#&#GB&###&5B&&&&##B#BBBGB5?JJGGPGJ~:~?PG5Y5P?P^^B.                               .7Y5B#^\n"       
							  +"~&B5Y:                               .::^^J57PB? 7?:..~5Y?!?YYGBB5Y7Y##PG#&&BPB&#B#&PGB&&&&&#&#GBBBB5J5!7JP^     ^JB5JP5!5?.                                 .75G&!\n"      
							 +"!#BY:                                      .#:~#J??:!YGBBGGGB#BP!. ..^B##&&&G#&##&&#&#&GBBG&&#G&@G7^YB##GGGB#PY~:.  .GY&7 #:                                    .JG#7\n"     
							+"^B5~                                         !P:BBB##BBGY!5PG5JY^   .?B#&&&#&&&BG&@&&&BB&&BB&&#B#&G!  ~Y5PBGJYBGB#BP7.#P5Y?G                                       :JG!\n"   
						   +"!B!                                           .YGJJGJ^.:.  :5~B~^   .GG#BB&B&@@#G#&@&&&#B#@&###&&&&5J   !YJJ?  .~^!~!~JP?.5#.                                         ^G?\n"   
						  +"!P.                                             .B! Y!       J!YP555JG&#BBB&&&Y7&#&&5B5&##B~J@&#B#&B577~^7G75          .!:.B^                                            J7\n"  
						 +"^~                                                .G~^:     .^JBB##&&&&BBG?#&&~.~&&@Y.?.G&&#: Y&&!PBY~Y#GGGPP7           ^:G~                                              ::\n" 
																			 +"P7^   :~5B&BB#B&#BGBBJB&&P7!BB&&5^~!B&&BG?7B@B!GBP?JG&&&BP7.         7P!\n"                                   
																			 +".55 :YP5BBJ J##BGGBGB&###BY^#&@#^~?~7@&&?7P&#&#5YGPGGPG5#BPY~       ^B!\n"                                     
																			   +"5YY77YB. ~##PG~.:^B####&5~B&@!  !^ #&@!7#&##&B?7^JP5G: 7BJYY^    .P:\n"                                                    
																			  +"!G!^  Y?:YBPG~G7J:^&##&##P #&&^^~GP^G&&:?#B&###~..~P5#G. 7J :7?. .:\n"                                                      
																			 +"^G:  ..#Y#5^ Y5^~!GY#BG?.JG5&@#:.... P@&5J5 JB#&J.P!7!P7P. B.  !P\n"                                                      
																			+"Y:    ~P5~ .~?P   :^.    :P5P5.      .5#GB:  .~??Y~ .#! .~.5!   !G\n"                                                        
																			+":Y     PP. !7. !.          77            ^Y          ^7:7 .?Y?    J^\n"                                                       
																			+"!^   .7B..?:                Y.           Y.          :  .?: G5    :5\n"                                                       
																			+"Y  ~!~?P.?.                 :^          .^                7.:B^   .J\n"                                                       
																			+"!:J^  :BJ   .                .          .                 .!~5^7~  7:\n"                                               
																			 +"5:  .!BY                                                  :&~  :J:~.\n"                                                      
																			 +"~. !~..^                                                  !P7.  .J^\n"                                                       
																			 +".: :                                                         ~!  ?\n"                
																			  +".:^                                                          ^ ::\n"                                                       
																			   +".Y.                                                         ^.:\n"                                                         
																				+":7                                                         P^\n"                                                          
																				 +".^                                                       ?~\n"                                                           
																				  +".:                                                     ~^\n"                                               
																					+".                                                   ^.\n"                                                             
																																	   +".\n"                                                               
																																	 +"..\n");   
			        break;
				}
		}
		return enemyimage;
	}
	/**
	*<b>name:</b>registerTreasure<br> 
	* adds a level to the Treasure  in treasure array<br> 
	* <b> pre:</b> Treasure  data must be different from null<br> 
	* <b> post:</b>will be sent to the method addTreasure in level<br> 
	* @param treasurelevel is an int data that serves to store the data of the level at which the treasure is to be registered.
	* @param nameOfTreasure is an String data that serves as the  identifier of a treasure 
	* @param image is an String data that serves to store the url given by the user.
	* @param scoreGranted is a data that serves to store how many points the treasure gives to player in case of find. 
	* @return a message of the positive method result if it was recorded negative if it could not be recorded
	*/
	public String registerTreasure(int treasurelevel, String nameOfTreasure, String image, int scoreGranted){
		String message="", imageInAscii="a";
		int positionX=0, positionY=0;
		imageInAscii= generateimageInAscii(nameOfTreasure);
		positionX=generateRandomX();
		positionY=generateRandomY();
		message=levels[treasurelevel].addTreasure(nameOfTreasure,  image,  scoreGranted,  positionX,  positionY,  imageInAscii);
		return message;
	}
	/**
	*<b>name:</b>modificatescore<br> 
	* modifies a player's score <br> 
	* <b>pre:</b> there must be a player<br> 
	* <b> post:</b> the player will be assigned a new score. <br> 
	* @param iplayers is an int that stores the data of the player whose score you wish to modify.
	* @param newscore is an int that stores the score that you want to add to the player
	* @return a message of the positive method result if it was changed negative if it could not be changed
	*/
	public  String modificatescore(int iplayers, int newscore){
		String message=" ";
		int modificatescore=0;
		if(iplayers<=(PLAYERS-1) && iplayers>=0){
			if (players[iplayers]!=null){
			modificatescore=players[iplayers].getscore()+ newscore;
			players[iplayers].setscore(modificatescore);
			message=" el puntaje del jugador "+iplayers+ "fue modificado correctamente";
			}else{
			message="no se puede modificar el puntaje de un jugador inexistente";
			}
		}else{
			message="no se puede modificar el puntaje de un jugador que no se haya en el rango";
		}
		return message;
	}
	/**
	*<b>name:</b>consultAvailableLevel<br> 
	*  check if the player can pass to the next level and if not, it gives the reasons why<br> 
	* <b> pre:</b>player and level must exist <br> 
	* <b> post:</b> the system will inform whether or not the player can pass to the next level. <br> 
	* @param iplayers is an int that stores the player you want to know whether or not he can level up. 
	* @return message returns a message with the given case
	*/
	public  String consultAvailableLevel(int iplayers){
		String message="";
		int nextlevel=0, pointsRemain=0;
		if(players[iplayers]!=null){
			nextlevel= players[iplayers].getlevel()+1;
			if(nextlevel<=19){
					if (levels[nextlevel].getrequiredPoints()==players[iplayers].getscore()){
					message="el jugador " + players[iplayers].getnickname()+ " puede pasar al nivel " + nextlevel;
					players[iplayers].setlevel(nextlevel);
				} else{
					pointsRemain= levels[nextlevel].getrequiredPoints()-players[iplayers].getscore();
					message= "el jugador " + players[iplayers].getnickname()+ " no puede pasar al nivel " + nextlevel + " por que le falta " +pointsRemain + " puntaje" ;
				}
			}else{
				message="el jugador "+players[iplayers].getnickname()+" ya se encuentra en el maximo nivel" ;
			}
			
		}else{
			message="no se puede realizar la consulta de la capacidad para llegar al siguiente nivel si este no existe";
		}
		
		return message;
	}
	/**
	*<b>name:</b>generateRandomX<br> 
	* generates a random position on the x-axis<br> 
	* <b> pre:</b>the resolution of the screen must exist<br> 
	* <b> post:</b> a random position will be generated based on the screen resolution. <br> 
	* @return number1 stores the corresponding random value
	*/
	public int generateRandomX(){
		int number1=0 ;
		switch(resolution){
			case SD:
			 number1=(int)(Math.random()*Resolution.SD.getwidth()+1);
			break;
			case QHD1:
			 number1=(int)(Math.random()*Resolution.QHD1.getwidth()+1);
			break;
			case HD:
			 number1=(int)(Math.random()*Resolution.HD.getwidth()+1);
			break;
			case FHD:
			 number1=(int)(Math.random()*Resolution.FHD.getwidth()+1);
			break;
			case QHD2:
			 number1=(int)(Math.random()*Resolution.QHD2.getwidth()+1);
			break;
			case UHD:
			 number1=(int)(Math.random()*Resolution.UHD.getwidth()+1);
			break;
			case UHD8K:
			 number1=(int)(Math.random()*Resolution.UHD8K.getwidth()+1);
			break;
		}
		return number1;
	}
	/**
	*<b>name:</b>generateRandomy<br> 
	* generates a random position on the y-axis<br> 
	* <b> pre:</b>the resolution of the screen must exist<br> 
	* <b> post:</b> a random position will be generated based on the screen resolution. <br> 
	* @return number1 stores the corresponding random value
	*/
	public int generateRandomY(){
		int number1=0;
		switch(resolution){
			case SD:
			 number1=(int)(Math.random()*Resolution.SD.getheight()+1);
			break;
			case QHD1:
			 number1=(int)(Math.random()*Resolution.QHD1.getheight()+1);
			break;
			case HD:
			 number1=(int)(Math.random()*Resolution.HD.getheight()+1);
			break;
			case FHD:
			 number1=(int)(Math.random()*Resolution.FHD.getheight()+1);
			break;
			case QHD2:
			 number1=(int)(Math.random()*Resolution.QHD2.getheight()+1);
			break;
			case UHD:
			 number1=(int)(Math.random()*Resolution.UHD.getheight()+1);
			break;
			case UHD8K:
			 number1=(int)(Math.random()*Resolution.UHD8K.getheight()+1);
			break;
		}
		return number1;
	}
	/**
	*<b>name:</b>podium<br>
	* will generate a podium with the top 5 players based on their score.<br>
	* <b> pre:</b>the 5 players must exist <br> 
	* <b> post:</b> the system will to report the podium. <br> 
	* @return message returns a message with the given positions
	*/
	public String podium(){
		String message="no se han registrado suficientes jugadores para poder realizar un top", Top1="",Top2="",Top3="",Top4="",Top5="" ;
		boolean comprobant=false;
		int top1=0,top2=0,top3=0,top4=0,top5=0, comprobantMinimunNumber=0;
		for(int i=0;i<PLAYERS-1 && !comprobant; i++){
			if(players[i]!=null){
				if(players[i].getscore()>top1){
					top2= top1;
					top3=top2;
					top4=top3;
					top5=top4;
					Top1=players[i].getnickname();
					top1=players[i].getscore();
				}else if(players[i].getscore()>top2){
					top3=top2;
					top4=top3;
					top5=top4;
					Top2=players[i].getnickname();
					top2=players[i].getscore();
				}else if(players[i].getscore()>top3){
					top4=top3;
					top4=top5;
					Top3=players[i].getnickname();
					top3=players[i].getscore();
					
				}else if(players[i].getscore()>top4){
					top5=top4;
					Top4=players[i].getnickname();
					top4=players[i].getscore();
				}else if(players[i].getscore()>top5){
					Top5=players[i].getnickname();
					top5=players[i].getscore();
				}
			comprobantMinimunNumber++;
			}else{
				comprobant=true;
			}
		}
		if(comprobantMinimunNumber>=5){
			message="";
			message=("el top esta compuesto de la siguiente manera: en primer lugar el jugador "+Top1+" con un puntaje de " +top1+"\n"
			+" en segundo lugar el jugador "+Top2+" con un puntaje de " +top2+"\n" 
			+" en tercer lugar el jugador "+Top3+" con un puntaje de " +top3+"\n"
			+" en cuarto lugar el jugador "+Top4+" con un puntaje de " +top4+"\n"
			+" en quinto lugar el jugador "+Top5+" con un puntaje de " +top5+"\n"); 			
		}
		return message;
	}
	/**
	*<b>name:</b>consonants<br> 
	* will generate a a counter with the number of consonants of the enemies' names <br>
	* <b> pre:</b>the enemys must exist <br> 
	* <b> post:</b> the system will to report the number of consonants. <br> 
	* @return message returns a message with the numbers of consonants
	*/
	public String consonants(){
		int numberOfConsonants=0;
		String message=" ";
		for(int i=0; i<=LEVEL-1;i++){
			numberOfConsonants+=levels[i].countconsonants();
		}
		message="la cantidad de consonantes de los enemigos en todos los niveles es "+numberOfConsonants;
		return message;
	}
	/**
	*<b>name:</b>searchSpecificTreasure<br> 
	* will generate a a counter with the number of specific type of treasure <br>
	* <b> pre:</b>the treasures must exist <br> 
	* <b> post:</b> the system will to report the number of a specific treasure in the game. <br>
	* @param treasuretosearch is a String data type that will store the name of the treasure given by the user.
	* @return message returns a message with the quantity of the treasure
	*/
	public String searchSpecificTreasure(String treasuretosearch){
		String message=" ";
		int count=0;
		boolean comprobant=false;
		for(int i=0; i<LEVEL-1 && !comprobant;i++){
			if(levels[i]!=null){
				count+=levels[i].countaTreasure(treasuretosearch);
			}else{
				comprobant=true;
			}
		}
		message="la cantidad de tesoros del tipo "+ treasuretosearch+ " fueron "+ count;
		return message;	
	}
    /**
	*<b>name:</b>AvailableTreasuresAndEnemysInALevel<br> 
	* will generate a a counter with the number  of treasures and enemys <br>
	* <b> pre:</b>the treasures and enemys must exist <br> 
	* <b> post:</b> the system will to report the number of a treasures and enemys in a level <br>
	* @param rlevels is a int data type that will store the level to search the treasures and enemys given by the user
	* @return message returns a message with the quantity of the treasures and enemys in a level
	*/
	public String AvailableTreasuresAndEnemysInALevel(int rlevels){
		String message=" ",countTreasures="", countEnemys="";
		countTreasures=levels[rlevels].consultTreasures();
		countEnemys=levels[rlevels].consultEnemys();
		message="los  enemigos en el nivel "+ rlevels+ " son "+ countEnemys+ " y los tesoros son "+countTreasures;
		return message;
	}
	/**
	*<b>name:</b>determinatedifficulty<br>
	* <b> pre:</b>the level must exist <br> 
	* <b> post:</b> the system will to change the difficulty of a level <br>
	* @param leveltoregister is a int data type that will store the level to change the difficulty
	*/
	public void determinatedifficulty(int leveltoregister){
		levels[leveltoregister].determinatedifficulty();
	}
	/**
	*<b>name:</b>mostvaluableenemy<br>
	* search for the enemy with the highest value in the entire game
	*<b> pre:</b>the enemy must exist <br> 
	* <b> post:</b> will send a message with the  score it gives and its level. <br>
	*@return message with the score and level of the most valuable enemy
	*/
	public String mostvaluableenemy(){
		int scores=0, positionI=0, positionJ=0;
        String message="", name="";
		boolean comprobant=false;
        for(int i=0; i<LEVEL-1; i++){
			comprobant=false;
            for(int j=0; j<levels[i].getenemys().length && !comprobant; j++){
                if(levels[i].getenemys()[j]!=null){
                    if(levels[i].getenemys()[j].getenemyScoreGive()>scores){
                        scores=levels[i].getenemys()[j].getenemyScoreGive();
                        positionI=i;
                        positionJ=j;
                    }
                }else{
					comprobant=true;
				}
            }
        }
		if(levels[positionI].getenemys()[positionJ]!=null){
			name=levels[positionI].getenemys()[positionJ].getenemyName();
			message = "el enemigo con mayor puntaje es: "+name + " y se ubica en el nivel: " + (positionI+1);
		}
        
        return message;
	}
	/**
	*<b>name:</b>mostRepeatedTreasure<br>
	* Generate a counter to determine the most repeated treasure at all levels.
	* <b> pre:</b>the treasures must exist <br> 
	* <b> post:</b> the system will to report the most repeated treasure in a game <br> 
	* @return message returns a message with the name of the treasure most repeated
	*/
	public String mostRepeatedTreasure(){
		String message="", repeatedname1="", repeatedname2="";
		int count1=0, count2=0;
		boolean comprobant=false;
		if (levels[0].gettreasures()[0]!=null){
			repeatedname1=levels[0].gettreasures()[0].getnameOfTreasure();
		}
		for(int l=0;l<LEVEL-1;l++){
			for(int r=0;(r<levels[l].gettreasures().length); r++){
				if(levels[l].gettreasures()[r]!=null){
					repeatedname2=levels[l].gettreasures()[r].getnameOfTreasure();
					count1=0;
					count2=0;
					for(int i=0; i<LEVEL-1;i++){
						comprobant=false;
						for(int k=0;(k<levels[i].gettreasures().length) && (!comprobant); k++){
							if(levels[i].gettreasures()[k]!=null){
								if(repeatedname1.equalsIgnoreCase(levels[i].gettreasures()[k].getnameOfTreasure())){
									count1++;
								}
								if(repeatedname2.equalsIgnoreCase(levels[i].gettreasures()[k].getnameOfTreasure())){
									count2++;
								}
							}else{
								comprobant=true;
							}
						}
					}
					if(count2>count1){
						repeatedname1=repeatedname2;
					}
				}
			}
		}
			
		message="el tesoro mas repetido es "+ repeatedname1;
		return message;
	
	}
	/**
	*<b>name:</b>finalizationimages<br> 
	* a completion image will be generated based on the players' scores.
	* <b> post:</b> the system will to generate a message with the corresponding image<br> 
	*@return  a message with the corresponding image
	*/
	public String finalizationimages(){
		String message="";
		boolean comprobant=false;
		int totalpoints=0;
		for(int i=0; i<PLAYERS-1 && !comprobant;i++){
			if(players[i]!=null){
				totalpoints+=players[i].getscore();
			}else{
				comprobant=true;
			}
		}
		if(totalpoints<=200){
			message=("\n"
				+".:::::::.::::::^~^::^:::^:::::^^^^^^:^::::^::^:^^^^^^~^^^^^^^^~~~~~~^^~^^~^^^^^:::::^::::::...:..::::::.....:^~!!!!!!777777777!7~~^::^^^^^^^^^:::^~^:::::^:.\n"       
			   +"..:::^^:::^^^^^~^^^^^^^^^^^^^^^~~~~^^^^^^^~~~~!7???7?7?7777777????77??????!!~^~~~!!!~~^:::^::^^::^^~!!!~~!^^!7?77!!!!777777?7!!~~^::^~!~^^~~~!~^~~!~~!!!!~~^\n"       
			   +"..:::::::::::::^:::::::::^:^^^^^^^^^^^^^^^^~!~!!!7!!!7!!77777!?777!!777777!~~!!~^^~~^:::::^^~~^~^!?77???7?7?????77??777?7!~~^^^^^^^^~!!!~7!~!!~~~!~!!777!~~:\n"       
			   +"..:::::....:::^:::^^^::::.^^^^^^^^^^^^^^~~~~!77!777777!!!!7!7!?777!!7!77!!~^^^^::::::^~~^!!77????77??77?777!!7??77?!7!!~~^^!!!~~~!~~!!!!~~~~~~~~~~~~~~!!!!!~\n"       
			   +"...:::::::.:::::::::::::::^^^^^^^^^^^^^^^!!!!7?77!77777!!77!!!!7!~!7!!!!!!!~~^^^^^~!~!!!77!77?7777777!7777!~^^~~~~^::^^:^^~!!!!~!~!~~~~!~~~~~~~~~~^^^~~~~~7!\n"       
			   +".......:::^~!J?!!~~^::::::^^^^^^^~~~^^~~~!~~!77!!!!!77!!!7~~!!!!~~~~~~!!!!!!~!!~!~!!!!!!!7777!!!!77~~^~~~^:^::^^:::::^~~~!!7!!!~!~~~~~~~^^~~!~~^~!777?!~~~7?\n"       
			   +"..:..:.~5GG#B&&&###BGJ:^^^^^^^^^^~~~~!!7?7!77!7?7!!77!!!!!~!!!!!!~!~~~~!!~!!~!~^^~~!!~^:^^~~^^^:^^^^^^^^^~~~~~!~~^^^^~!!!!!!!~~~~^^^~^:^^^^^^~?7??????7?7!75\n"       
			   +"::^:..:J&&&&#B#B#BB##5^^^^^^^^^^~~~~~!!!7!!!777?77!77!777!!77!!!~!!~~~~~!!!~!!!~!?5BGP5J!^^^:::^^^~^~~~~!!~!!!!~~~~^~!~~~~~~~:^^^^^~~^~7?777?J?????7????~~^~\n"       
			   +"::^^^^:?#&&&&###&B###P^^^^^^~^~~~~~~!~!!!!7!!77?77777!!!!!!~!~~~~!~~~!!77JJJYY55PGB&&&&&#BBG5J77!~~~~~~~~~~~~!!~!!!!!!!~~~^^^^^~~~!7??YJ?J?JJ?JJJJ?77!!~^^^~\n"       
			   +"::^:^^^^~P@@&&@&@@@&?~^^^~~~~~~~~~~!~!!!!7?777!7??7??7!~~!!!!!77?J?JYYY55GGPPPPGP5#&&@&&@@&###BBGPYJ?!~~~~~~!!~!!!!77777777!?J??J?JJYJJ?J??JJJJJ???JJ7~~^~^7.\n"      
			   +".:^^^^^^^JB#&GBB&#&B~^~~~~~~!~~~!~!!!!77!!!7!7777777777!!!!!!75P5PPGGBBBPP57~~^:^~GB#&&&PPG#&&&&&&###BGP5J77!!!7!!77777????JYJJJJJJJJ?JJ??JJJYYY?77?77!~~~^7:\n"      
			   +".:^^^^^^~JGB#YJJ#&#B~~^~~~^~~~~~~~~~~~~7!!!!~~~^^^~!!!!~~^^^^~?G&&&&&#&###BY?7!^^~5G#&&#!^~~7?J5PB#&&&&##BGGPP5Y?777!!?77???JYJYYYJJJ????????J???7!~~~~~~!~7.\n"      
			   +"..::::^^^YPG#5JJB&&#~^:^::::^^^:^^^^^^^^^~^^^^^~^^~^^^^^^^^^^^:YB&&&&BGGB######BGGBP#&&#!~~!!~~^~~!75PB&&&###BBBGGGPYJ!!!77?JJJJ???????J?????JJ?J??777!!7!!~\n"       
				 +"....:^^?5G#5PPPP#G!!7?7YYJY~^::^^^^^^^^^^^^~~~~~~~^^^^^~~~~^^?Y#&&&J:^~!J5PBBBBBPPB&&&YJ?77!!!~!!!?~~!?J5G#&&#&&#&#P7!777???J??JJJJJJJJ?JJJJJYYYJJ????J7!~\n"       
				+".....::?PG#GGBGG##GPBPGPPG&5::::^^^^~~~~~~~~~~~~~~~~~~~~~!!~~?5#&&@J^^^^~~~!77?JP5B&&&GBGGGY7?!!7!J7??JJJJ55GG&&&&P?7!!7777!77????JYJJ?JJJJJYJYYYJJJJ???7^\n"       
				  +".:..:!YPGB55YG###JYPGGB#&&?^^^^^^~~~~~~~~~~~~~~~~~~~!~~!!!~~75#&@@J~~!~~~~~~~~~YYB@&&PB###5?JYJYJY?777!77!7YG#&&&?!7??7??!??JY?JJYJJJJJJJJJJJ??77??????7^\n"       
			  +".:~??5GGP&#5PBY?JYG&GBGP5GB#BG^^^^^~~~~~~~~~~!~~~~~~~~~~!!~~~~~~75&@@&?~~~~!~~~~~~~YYB&&&YG###?!!!!!7J!77??7777YG#&##J????7????YYJJ?JJJ??JJJJJJJJ?77?77??J7!^.\n"      
			  +".!GG5G&BGG&55BP?JJP#&@@BPYPP#J^^^^~~~~~~~~~~^^!~!!!!~~~!!!!!!!~~7Y#@&&7^~~~!~~~~~~~YYB&&&Y###B!!!77???7????777!?G#&##J?J???77??J????JJJJ?7??????7?J7!~7??7~~^\n"       
			  +".~Y5J5PGG5#5YGY??5PBB&GGPY5G&7:^^^^^^^^:::.::^~!~~~!~~!!!!!~!!~!7Y&@&&7^~~~!!~~!~~~?7B@&&YG#BB?7??????!!77!!7?7JG#&&#?7????????????7?77!!7!7??7!~!!!~^~~~^~^^.\n"      
			  +"~YP55YYJJGP5PYYYP&GB&#BBYP#&~......::::^~^~~!!~!~!~~!!!!~~~~~~~!J#&&&7^~~~^^^^^^^^~!G&&&JGBBG?7?777?7~!7!!~!!!?G#&&#7!7777!7777777~7~~!!!!!!!!~~~^^^^^^^~~^^.\n"      
			  +".7JPBP??J5PPPG?Y5GBB#B&&#&B#B..:::^^~~~~~~~~~!!~~~~~~!!!!~~~~~~~!?#&&B~^:^^^^^^^^^^!!G&&#JPGBG!7?7!?JY5P7!!~~!!?P#&&#~~~~!!!!!~!~~!~J~~~~~~~^^^~~~^^^^^~~~~^^.\n"      
			  +":JYPPYPG5GGYPGY?5B##&B##B&#&G:^^^^^^^~~~^~~~!~~!!!~~^^^^^^^^~~!!!?#&&#~^^^^^~~^^^^^!~G&&G7PGBG!!77??B&#Y~!~^^^^7G#&&#~~~^~~^~~~~~~~!J^^^^^^^^~^~~~:^^^~~~~~^^.\n"      
			  +".7J5J5BPPGBB5BG?5PB#B#BPYGGPGJ!!7!~:..:..:::^^^^^^:.:::^^^~~~~~~~7B&&B~^^~~^:^^^^::!!G@&G!5PPP^^^7J5B&#Y:::::::!P#@&G^^^^^^^^.::^~~!?~^^^^^^~~~~~~~~~~^~~~~^:\n"       
			  +".?YYPB#GP#&&#B#BGGGB#@&&#PGG#@&&G^..... ....:::::^~^~~^^~^^^^^^^~!B&&#^::::........~~G@&G7PPPY.:~5G@&@B^:^^^^~~!5#&&G^^^^^^^^::::^^!5~~~~^^~~~~~~~~~~~~~~~~^^.\n"      
			  +".?YP5&GPYG&&GPPGG#&&&&&&##GBGB5G~... ....  .::::^:::::::::..~!!!!~#&@&:.......::..^!~G@&BJPPG5^^!B&&&&Y~!!~^^^^!J#&&B^~~~~~~~^^^^^^75~!~~^^~^~~^^^^^^^^^^^:::\n"       
			  +".YY5JP5YY5BY55JYGB&@&&&&&##GB#J^.    ......::::......:!777!7J7777~#@@&!!~~!~^::^^:~~~P@&#?55PJ^^~J&&&&?:^~^^:::~?#&&G~~~~~~~^^~~~~~?5!~^^::::^^^:.::::.:^^^^:\n"       
			  +":YJPYPJJP5JP&G7YGBG&&GB#GPB&###^~. .....:::.:^^:::...^7!?JY5Y7JY7~#@@#?7JYY!::....:~~G&&#?5PPY^:^~5B#&#Y7:.....~!B&&P:::^^^^^^^~!!^YP~:::::^~~~^^^^^^~^~~~^~^.\n"      
			  +"~YPGP55GG55GB5JP5PJ###&#G#&&##&BG!^:.::::^:::::^^^^::^!!J5YYY7JY?^#&@B7?YJY:.. ...:~~G@@&?YPP5^:^!~Y##&#G!::...^7#&&G^::^~^^~^^^^^^Y57^^~~~~~~~~~!~~~~~~~!~^^.\n"      
			  +"!GPPPGP5PG555GP##GPG5B&B#&&&#&B&5Y!.........::^:^::::^!7J5Y55??J?^#&@#77JJY^..::^^~~^G&&#7YPPY^^^^!#&@P~^^^::^^~7B@&B::::::::::~~~755?~~~~~~~~~~~!~~!~~~~~^^^.\n"      
			  +"!#G55JGB5BBP5GGB#BB#&&&PBBB&&&#&Y^.    ...:::.:^^:...:!7?YYY57?J?~##&B!!7JY~^^:^^:^~^5&&B7YPPY^^^^~#P#Y^^^^^^::^!#&&P::::::^~~~~~~JPYJ~^~~^^^^^~~~~~~^^~^~^^^.\n"      
			  +"~5YY5JPP55PG&#55#&@&&#&&###&#GBB&~ .....:~7!!^^7?7:...77JYYY5??YJ!##&#7!7JJ^......:^^5&&B7YGG5^^^^:JBPB^~~~~~~~~~##&G!~~~~~~~^^~~7JPJJ^^^:..:::::..:::::::....\n"      
			  +"7BBBGP5GP?YPGBP@@GB@@&&###&&#GGB##:....^^!7!~!^~7!...:77JYYYP??J?~B##B7!!?J:......:^~Y###7YGGY:..:::#5BJ~^::^^^~~B&&G~^^:::::.:::^7??J~......................\n"       
			  +"!BGBY5GP5GPJ5BP#&##&&#B&#GBBBBBB#&G...^^77^^!~~^!~...:7!?YJJ5?7?7~B##B7!!7J:......:^^J##B?YGGJ......::~^^:.....^^B##G:...........~7?JJ7......................\n"       
			  +":5555JJ5BYP#BGBG#B#&&&&&####GG#PP#B!:~^!~:^^^!~^~^....7!?YJJY?!J7^###B7!!7Y^......:^^J###?YBGJ........ ........^~G##G:...........!77?J?......................\n"       
			  +":?J55PPG&Y5&&PB&B&#G##GBGB#GB5GBB#GG7^~!!!!!!~?~~~...:7!?JJYY7!?7^B###777?Y^.......^:Y###JYGG?.................^~B##P:........:7.?????7....!!!:..~!!^...... .\n"       
			  +".JYGGBB5G575JP&&&@&BBGB##GG55GGGBGBBY!?J?77!J?7!~^^::~?7JYYJJJ~77:GB#B!!!?Y!^:... .^:Y###JYPG?....  .^~!~~:  ..^^G#BP:.....::^~Y:!!7:?!..:~7?7..^!~!!:.....\n"         
			  +".J5Y5P5PBG7GG&&&#&@@&BPP&BGPY5PYB5G5GY!^:~P^^^!?J!!Y5GGY??J7JY~7^ GBBB!~!!Y?77!~^:^^:5##BJYPP7.^^^^..!?7??:   .::P#B5:..   ~7~7J^7!?:77:.^^~J7::^^JJ~^:...\n"          
			  +".55PJJP?GBJPB#&&BB&@&#PYG##BY5GGGGBBPP7: :J~^~~?!77~JPP?Y77!5J~7^ GBBG!!!!J???777!7:.Y#BBJYGP7:~!!~: ^?777.    ::P#GY.     ~!7~~!~~~~!~7!!^^77..:!J?J!^^:.:~^\n"       
			  +".YJY55PG&BBGPBB&BB&&@#P5Y5GPPPPPP#PPPPG~  ..:^~~^^!~^J5!!!7JY5?7^ GBBG!~!!JJ??J?J?7^.Y##B?YP5?!77!!^.~7777..:..: G#GY.^. .:^.!!!!^:::.^JJ7~^7^ .~7!J?!7~~7?77.\n"      
			  +":YY?JJ5YYPBGGGGB&&&@@@BGGGPBBYYP5YGPPP5G:  .....::^?~~JJ~!!Y!???: PGBP!7?J?J?J?JJ?7: JB#G7JP5J7??7!7?7!!!7:^^7!: PBGY.!^. .:^:?~.  ..!7!7!!^. .~7!!!!!!~~~77!:\n"      
			  +".~~^!~^^~~!777Y&&&&&@@@BBPJJ55JY5YJ5GP5PG!^7:~:!5^.!?!!77~JJJYJ7: GGGY??7!!Y5J?!JJ!: J#BP??G5?~~JY7??!!!!7~.::7^ PBG5!!^.  :: ~?7~ .!?!:.... ^!??77!!!~!!~^~!.\n"      
			  +".!!!7?!??5?GJPG&@&@@@&&PBGBG5PPGGBGBBBBBBB5!^!^!Y:.:!J~~!?JY555Y: PGBJ!!~7J????77J?. Y##BJ?PY?!~7YY?J77?7~^!??:. PBP57^. . .:.~GPY^::.::   .~7!~^::^::^:.:^^:.\n"      
			  +"7&####B&#JJ#PYB@@&@&BBGB#BGPBBBBB####5!^:.::^^::::..~?JJYJYJP5YY: PBGY7?Y????JJJ?7J. Y#BG??P5?~?!Y??5???^^?YJJ!: GGPY...   .:YPPBJ. ^77: :~7?~.:.!77~!!:~!~:^.\n"      
			  +"J##&&&&@PJYGGPJ@@&B55PPG##&5B##&&BB#J:.^~!77~7!!!!7!.?5JJYYJYYYJ. PGPJJYJ?JJJ7J??7J. J#BB?JGPJ!!~!7J??!:!??77!:. 5PPY:^:::::75PG&&! 5J5?^!^??^!~:7?~^7?:^?^.~~\n"      
			  +"5@&&@@@@P5J5PG5&&BPYYPPG#GBBPB&&&PPP:..^7YY?.?777!7!.JYYY5JJYYYJ. PP5JJJJJ?7JJ?????. Y##BJJBGJ^~^~7J7!~!!^^^^:^^ PGPY::.::..!55G#&G7G5P5P5?JY?^~^!!~:!?~:.:.^^\n"      
			  +"5@@@@B7?B55G5PB&BBG5YPPGG&&&&&&&BG5Y.. .^7!^ :~^^^^^.???JJ??J??J:.P5PY7?7?7~77!7777~.YGPPJJYYYJJ?!7?7!77^^^^^:^: GBPJ .. .:..B#&&&&#BBB&@&B!^........:......:.\n"      
			  +"Y@&#B?!?&5JPPYGBG#BBPPGBB@@@@&&#GBPJ   :...: ...:.::.7!?JJ7....:. ..::.^^ .~..     :.... . .  BBBBGGPPPPYJYJ?777!GP5Y^:.. ..!PB##&B5B#&@&GG?\n"                        
			  +"??7~7JY#@#5PG5BBBBBGGBBGG@@@&BB&GGGJ..::.:^~.:^^.:^:.7!?J?.           ....::^^.....:......:...PGGGPGGGGGPGGG5PPPPG55PP5YY.  YGPPB#&B##&&PGP5..:.:^:   ...\n"           
			  +".:7..~!?&@##G5G#&BGGG#BGG#B#B@@@GPGJ~~~~~~~!!7!~~~77~!!77!:.  :....^::~~!!!~!~^~:::^::... ..     .^G##BGBBBBGP555PGP55P5P5~^GGGB&&&##G&#&&G5:.^:~!~  .!~   .\n"        
			  +"7?!!JP~G@&#G#&&#BGPP#BGG&BPG&&&G5BY... .:::::^:^!?7~77??!    ^^.   . .:~:~~^~^.^::^~^^:^... .   .~5PPGBBBGBBG55PPP5555PPPBB#B#&#&&&#PG&#&B?Y!..^~:  .^^.  ::\n"       
			  +":7?YB###GB&#G#BB&#BBGGB#B&GJY5G#&YGJ  .:^      ::~!7!?7!7?:...... . .:^ ...:.~.:..::.:^^:^^~~.. .  ...7####GBP55PPPG55PPPGGB&&&@#&@@&BG&&&&!..:.. ..   ^....^:\n"      
			  +"^55JY?YP5PB#B&G##G#BGBGB#&&#B#GJPPBJ !!&@P. .:. :^^77?7??J~.  :..::.~YP:......:^:^~!~~!~!^:~7~:.:..:::!Y5JYBGGGGGGGGGGGBB#B###&B&@@@@&B&#5!~:.:::......:::^:^.\n"      
			  +"?G#PGGBB#BG#B#B#&#BBBBB##&#&B~.~#BG5.YGB&&5  .. .^~PY?7YJJ:.. ..PBP^!P&P   ....^^^:.:...^^.^~~:.^^..::.   .GBGBB555PGPGB###&G&BB@&&#BBPG?..^::?J7?. .^~.:^^^:\n"       
			  +"!YBBGGBGB&G#BBB#&BBBBBGB#&P!PBP&#PG5:7GG&&J:^~7?JYPBP?JJJJ.   :7BGJ^^P#G!:. .:77~.   ..        ....:... ..~?J??PP5Y5PGPGGGBBGGBBB##PJPPG?:~~  7?7~..^JY:...:.\n"       
			  +"!GGBGBPPB#B#B#&B####GGG#J:^^57^BG5GGBJB#&&B&BJ?JJPG#GYGPP5   ?GP5YP55YBBGBG^^JJ??..:. .         ...:^:    .... ^BGPP5PPPP5P5P5PGGGBGPPBP5PPY: ^^:.:~PJ7J^..:.\n"       
			  +"!GPP55PGP#PBB##G#BGGGGG!.^75^~?BGGY?GGBG7~?PBBYYYPP5GPPP55^ ?PY?!J!YYYGPGGP#GYJ77JJPYJ^   ......::..::.      .:^PP55PPP5PP5YY5PP5PPP5GBPPP55?::. .YBG7?Y7:..\n"        
			  +"~YJJ5BB#PGGGP#&B#BGBG?~ ~5#!.??B##~  ... .PG#&BYYY5JPGPBGB5YJ5YJ!!^P?JPGP?^?YY5PP#GPGB!:..^:.:.. .. ...          ..:GPYJYYYY5PPP55YYYP5JJYYYY555^^JPPY7Y7??~:\n"       
			  +"^75YBBPPBBGBP#JP##GGG~:^?GBYP5^B#5G    J:.B&#&&5~JGB####&#PP5J?J5!:5^~7GGBG55GBGYBGPYY?GJYYY?7!:~??:!5?~:..:^~^... .??7Y55555P5GPG55PPP5Y55G555P??Y57~!YY~^7?.\n"      
			  +"^55B#Y5Y#G5#G#?GG####^ ^JPBB&&5G5#&?  .G^:J&##&&#GBB#&&&&&#5PPYP5Y557775PBBBPBGPPB#55JJP55P77GJ:J###PG!J!JJ7JP5?5P!!:^~5PPP5P5YGGGGPYYP5YYP55Y55Y5PYYYYYP?GJ~:\n"      
			  +".!PPYP#&GPPBJ~~!^~5Y^ ..JJ?PPGBPBG##?:~G:7^5&BPG#GPB#&&&&&5~J::~!?75GGG?YPGJJB!PY!P?JPY?YJ?^!5YJY5PJPYJ!7JY::7?YPGGPPGPBBGBGPB#&B5P?75G5PPPPPJYY?5G~PP7?BB&5~:\n"      
			  +":J5P5P&#&BJYY77::~?.. .^B7  JBPPG5Y57~?G~JY5PYYJYPPB&B57~G#&#..^!JG5^~!~J^7?YPP5577!?J!~: .~:!^.:^.:G5^?5Y:.~5YP#PJ:^PBP5YY?YYYYP#P~^7J7YYGBBP5G##Y?Y~7YGB#5~^\n"      
			  +":!77?Y##&&G@&BPY!?P? .^B#5!!JG#PPY7:   ? :!?YPGG#PPG#BJ!~P#&#5~?77G&B7??JJ7^~5&###B&B7^.:^^!7! ?7~5P&BGGP:?~!GB##P?77G&P7J!^!Y7?B5~??B#J7P&&B#!!P&B:~!~PBGGPY:\n"      
			  +".~77~..7BB#@&G5Y!?!~^P5G#GPJPPPB7?. ...?..^!Y~J&&&YP#B?:?#Y##&7::~5G#GJP?~5..?&###G&G...:~??5:.GPJY5##BJ?!P575G5J?JGJ&##JJY.7YJG#YJ^?B&YJ#&&#G!PB@#~J:.P#GYJ5^\n"      
			  +".~!YG~^~JP&&B5PYYY?5YYY7YB#B#GPGJ????775^:^7YBPGB&5PBG!.5&G##BP:!YBBGB55J7^  G&#&B5PB..7Y~.:Y7J?.  ^75?7?!^!?Y5PJ?!JYJYGB55JJP5YPJ?GGB#Y5P??!^^Y#BP!~^~55!!JJ~\n"      
			  +".~!!YPG^.Y&#GYJ?!!!!!7?PGGG7^... ^!:.^7P?~7?YGYGBGPGG5^:G#GGG?5J7?Y?^~!~J7?YY###GG?^?!J55^ .J?Y~   .!JYYJYJJ55G7Y^7!^?PG#PBB^  ~55.:J!PBBG  :~Y..~PG^.~~Y7.:7~\n"      
			  +".~?YPPG##BGYY777!~7!!7JG#7      .Y.  .:P#J7^^^!YGB&G5YJ7!??~::5&&P~.77Y!J?G########&#BJ... .Y?J^   .7YGYYPP5##Y:7.JG:^P&#G#B  .7G#5?7?P#&&Y   ?P7~YG5 . ^JGG?:\n"      
			  +".P5!?JYGJJG7~::^7Y7~JG#&^  .   . !.  J&#G~:. .:?5BY7PB5!.   :!75PBB?77?YYPG#&##B&&#GYYG~:^:75P7^ : ^?JB5PGYP&&B?Y?5~^J#BPGBB!. .~J55PGBB&&G:..:5PBBBG7:!Y#BJJ7\n"      
			  +"^G75#GY~.5@Y~:!55PP7G#@? .:  .. :Y. ?#BGBBY??!7YBG:YG#Y!~. ..^^?G?JBB&&&@@@#&&&&&@@&#B#&P!~5YY~: : !5JB5BBY5#BP5Y  .7GGPPGPGP^. J#BGP5YPGBG:~~:G##BGG#@@&PJJJ~\n"      
			  +"^YBG?77P#@&J:.!B&G&&B&#~:..    .B5~^B&#&&&BG#&&&@?^PP#G~^7:7B5G@#~ .~P&&&&#J!G####&&&&B@@B?J5~::.:.JGYBPB#57B##G. ..:JYYG#&&B?..YBB&&&BBBBB#G. .~JJP&&@@G577?^\n"      
			  +":PG!~?J#&@@G5!!#@&@@@&P!:   ..^Y&BYJP&G#&#&B&@@@@~7GP#@&&@@@@@@@&P^^. ?&&&&5~775#&&&##B&&@#?Y..: ^:5GJBBBBGYB&#Y ..  JY~G&@@G~   :~G#&&&&@@&BPJ.:?JP&&&@#5YYG~\n"      
			  +":JPY5#GB#@@@@&JJ@@@@@@&7: .~J5GB55YPGB##&&@&#&@@#:!GPB&&@@&@&@@@&GJ~YG#&&&BG5!..YB#&BB&&G#@P! .. ^^PP?G###BY#&&~.:  .Y5PBG#G55B?~!~7G#&&B5PG5YP^^~5Y&&GBYJJ7!.\n"      
			  +":Y5B#&@&#&@@@@@BB@@@@@&~:.^YG#B#GGPBB#&&&@@@&#&@?.YBPB@&#B&&#&&#&GP#&@#&&@G5G5^^^^7GGB&&G&@B! :^.:?PJ?GBBGBPB#G.:   JB#&#B##5~^!^:~7PBGB&Y~:5555~^JJB&#G?JJ7?:\n"      
			  +".!5B#B&@&&@@@@@@@&&@@B7~^~YGP#G&@&#&&&&&&&&&#&&@~:5BP##5~:?J5B&P&GP5&#&#&@&YGP?^J5::JB&@@#&@5 ~~:^GG?J##BBGGPGY^:^J^?&@&##&G   ~:^~::?Y5P#! .!BPP:?5P&&&Y7J75!\n"      
			   +"7GGGBB&&&@@@@@@@@@@Y~!775BGGB#&&&@@&#&&&&&B#@&&::Y#5B&G5YPP#&@@&G?!YBG##@#PG5J?Y#Y.J#&@@##J: ^..7GGPPGGBBG5JY5~^Y7~JP@&&&&.  :?YJ55^^&BPB&! .75J:77P&@&?^^~G5\n"      
			   +"~BB#G#&@@@&B5@&&&@#7?!7?GGGGB&&&&&&&@&&&&BB@@&G.:5B5&#???Y5#&&@##575G#@#@&GBYYPYB&#G@@@@&#5.....^7?7PGGPY?777!.   .?G##&&B   ~J5B5G5~&BBBB&!.~:~^7YP&@&7^^7&P\n"      
			   +"^B#&PB@&@@#BP##&&&P!J^J5GJG##&&&&@@#&&&@&&&@@@J.^P#Y&#YPBBG#&&@&B#5G#&@&&@#BGGBB&@@@&&&&&B&!     .::~!~^^:::^!.   .PBYP#B7   .7G#GBB?GPG#PPJ!J~7575G&@#!^7#@5\n"      
			   +":P&BB##&@&B##&&&&G!!~^!PPPB#B#&&&&GP&&&&&&&@@@?:~G#5&#PBGBG#&&&&&#GG&@@@@@B#&&&&#&&&&&&&@G&#.      ..:::.   ^!    ~BGB#&&&7 .^?#&##P^^~7YGBGGY?JG?5GB@#!J#&&P\n"      
			   +":P&#&B#@@####&&G?!^::75555##B#@@&JBG###&##&@@@!^~GBP&B#&&##&&#B##BB#&@@@&@BP#&#&@@&&#&&@@G&&P               !~   :BGB#PG&G::^YGGBBGBJ7###GP?????GYYGG&&G#G#&Y\n"      
			   +"~P5G&#@@GG&B&#BYY!~!JGGP!?#&##@@###5#&#&##&@@@:~!BBB@@@@@@@#P?7YB#&@@@@@&@#YP#&&@@@#BB#@@#&&&J             ^?7.. Y&PP5YJG? ^!PYYJGGB&&&&@PY?JJJJPYYG#&P7!BGBJ\n"      
			   +"JYYG&@@G?5@&@&YY5~?5GBB?~5BBB&@&#BB#&&###&&@@#^~?GGB#BG#@@@B?!7J&&&@&@&@&@&5B&@&@&@&BBB&@##&7.             :^: . ?#JJG5?PG?BGY??JYBG#BB#G5YJYY5GGGP&@@P^?###Y\n"      
			   +"7?7Y@@&5JP#&#P5?7?J#BBB!PB#GG&@#BGBGG&&BG##&@G~!YBBBGP5B@@@5  .B@&@@&&&&&@@#&&@@@@&@&B#@@&&&J                  ^..B#^YY??#@&Y?JJJJJ5G&B#5Y5#B&&&B&@&&&Y!BB##J\n"      
			   +"Y75@G55YJG&GGPYJY###B5PGGPGB&@@GGBGPBGBGB&@@Y~!5&GB#GG&@@@7  .&&&&&&#B#&&@&&@@@&@&@&GB&?B##5                  .  7&^..G#&@&577YYYJ5B&&&~!75##GBB#&#B#YGB?Y7?\n"      
			   +"~5?B&??Y?J#&5BGGBGGG#BJBBGB#####PPPGPGG#B#@&&?~!5&B##5YB@@@?  !&&&@&&&GG&&@&&@&&&&&@&PB&7.7G!       .:..     ..^.:P&P!~B&@P5GGJJ55?5GB&@J^:Y##55GGBPJ?J#Y77!J\n"      
			   +"^J?G&57YYG#&7J5J555PG#?5PGPP5PPGBGB##BG#PG@@&?7~5BG#&G5P@@@#  7@&&@###PP&&@&&&#&@&@&5GB&Y   !:      :^^^^...:^:...:~!^!G&P:^!7~!YJ!P#B&@7:YBB&!^^~7?Y~YG~~?J^\n"      
			   +".~!G#&Y!~5G##?7!~~!7JPYG~~!75GBBBGGBGYYBBY&@#?!~G&G#@BG#@@@7  ^@&&&B#BPP&@@#BB#&&&B: P&&B:       ..     :..:::.... ..:7B#:::~J5BBBGY##&B~~77??~^^!?~^^:^..:.\n"       
			   +":7?7YPP7~!?B@#YYPJ?775J5^^7P#&#5J!~!~^:~G7B&BJ!7&@G&@&B#@@&   ?@@&&G#B5B&&&&BPGGBP?!7G@@@P5?JY??Y5J??77!!^^~~^:.:...^~7#G~7?G#&BPY!~B#&^~7?YYJ!~^^:.... ..\n"         
			   +"^J?^^^^^::~!?7777!^:!J?BPGBB5?~.....:::^7~Y&G7!7&BP&@@BB@@@. .B@@&BGBBP&&&&&&##BBB#BBB&&@#BBBB&#&#BBGGGPY?!7!!^^::7?~!P#P55GP7^.   .B&Y.^!5PGP!^:.::...^.\n"          
			   +"75?!!777!^77!~:^~::.:^~~7~~:....:..   ...JB#B5!5@GP&@@&B&@@. 5&&##BPGPG@@@&&@@##BGB#GBB&&&BBBB&&#G5?7!!~::...  ........            :B&P~PBBBG5~..\n"                  
			   +"::~~^7!~7?!::^:^:.....:::^........   .  ...:7B5G@#P@@@#B&@& ?#&&#&BP#BB&&##&@&#&#GB##B&&##&&#BBGBG5JY55J??!:.  ..    .             5GBBG##P7.          .\n"           
			   +".^::.^7!~:.........:....:.          . .:~!?J5#&@@B&@@&&&@Y P#B#GBBB#&@##&##&&#&##BBP?JJ7!77JYJJ!~^^^^:....         ..  ..         :::^~!?Y~    .::.  .:.\n"          
			   +"....:^.^:..:.:^~~~~::^^^....        ..  .. ~P77^:JB@@&GP55^ PP?JPB&&#B#GBBGGPYYJ7~:.                         .      ......    .....            .. ..    .:\n"         
			   +"..:~!^~^::~~!!~~~~~7^^?!^..           ..  :: ..  ?P~.   .:.^^~~7!!~:.....                          .::.      ...   :^^:^~7^.   .....          .. ..:....\n"          
			   +"::!?YYPY5YY?777??7?~77::::.::::..              .                       ..  ...   .......  .      ...:^~!^^^^^~^~?!.     ..  ..:.7~...:.     .  .........:.\n"          
			   +".~?GGGGPP#PPGGP#GPY?JJ?!~~55YY?JJ!...  .:^......               ..:^!~~~~^?Y??7:.  ....   .^~!:   ::::^^~~!~^~.!:..   ^. ..     ^7...           ....  ..\n"            
			   +".^?PGG#PPPP5#B###PG#B#GP55G5GPJ??7~!Y7?7???!!?5J!~~^:~~^?!7!!?!?J^!?~?!77!!77?JY7~.::~^!~^^^^7:....:~:   .:..^. :J~:^7!^::..      ....... ...    .:^:..\n"            
			   +".75####BPPPBBGG##GB&###GGG#GGGGPGPGGGPP5G#G55PBGGP5&55Y?7!!7YP55YYJ5J??JJ?~^!~YYYYJ77^:^7?J7:7~~~!!?!!!!~..  ..^~~^!7::~J~~::^!7:..        .:..    .!^....\n"         
			   +"~5#BBB#G5GB5G#GP#BGBBB#BGGBBGB#&GGBBBPB#BPBG5GB#BGG5J5PBGBGGBBG####BBBPPPPJ?77?5PB5^!J7?7~~7J57!!!777!757!!~:^:::^:::~7J?^ .^~^::: .^:..::....     .. .....\n"       
			   +".:~77!J5?Y555GP5PPYJ~75PJ!7??JJ~?JJJ55PP5J7YJ5GPPJ?7?YY?!JPPY5J5PBGP555555YYJ?!J?JJ?!7~!~~??77^:^~!~~!^^^:~?7J?.::::: .:.:^^.   .....!~::... .......\n"              		
			   +"fuisteis ejecutados por traicion a vuestra patria\n");
		}else if(totalpoints<=370){
			message=("\n"
				+"BBB##############################BBBBBBBBBBBBGGGGGGGGGPPP5555555YYYYYJJYJJJJJJJ????????????77777777777777777777777!777!7!!!!!!!!!!!!!!!!!!!!\n"
				+"PPPPPPPPPPGPGPPPPPPPPPPP555555555YYYYYYYYYYYJJJJJJJJJJJ?J???????????????7???????77???7?777777777777777777777!7777!!7!!!!!!!!!!!!!!!!!!!!!!!!\n"
				+"JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ???????????????????????????????77777777777777!!!!!777777777777!!!!!~~!~~!!~~!!!~!~~~~~!77!7!!7~~~!!!!!!!!\n"
				+"JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ??J????????????????????????????????JJJJJ?~!?7!!~~!~~!!7777~~!~~~~~!!~^^^^~~~~!!!~~!7!^^~~~~~~~7!!!~~~~!!!!!!!\n"
				+"JJJJJJJJJJJJJJJ??????????7?!77777?77???77???????7?????7?????77?YY?J5PY57777!!~~~~!~!~!!~~^^^:::^^^~~^^~^^^~~!!75PG55PPJ!^~~~~~^^^^^~!!!!!!!!\n"
				+"JJJJ?JJJJJJJ?P?7777!!!!7!!7!!!!~~~~~~~~!~!777?J!~~!!7!!~!!77!!7J5~~?JY?~~~~~~^~~^^^^^^^:::::^^^^^^^~~!~~!~!!!!7P?G&&&&&&!~~~~~^^^^^^~~~~~~~~\n"
				+"?????????????J!7~~~~~PGP5J?!~~~~!!!~~~~~~~~~^~~~~~^~~^~~^^^~7YG&@&BBB#&#BPY?~~!!!~!!~~^^^^~~!!5GGG##&&#GBGBBJ^~J&&@#?7&&!~~^~^~^^^^:^^^^^^~~\n"
				+"77777777?777!!!~~J5G#@@@@GGB77Y5GBBPGB5BY!~~^~^^~~~~^~^^^^!G&@@@@@&&&&&&@@@&#J^~^^^^^^^^:Y&&&&#&GGB&&J!G#B&77J??B&BYGB#G^^^^~^^^~^^:^^^^^^^^\n"
				+"777777?P?!!!!~~~!!!Y&@#JB&GBP&P?JG@#G&5BGPJ7^^YY^^:^^^^^:7&@@@&@&&&#&##&&@&&&&5^^^!J7:^PGPBP##PGG55PG5?YY!5PG&#&#BB#P5&&P5?Y^^^^^^^:^:^^^^^^\n"
				+"7!777!J#J~~~~~~~!!!JG&BYB@#B&GJJG#GJ5YPJ!YPBGP@#Y??JJ~^:?&&&&&#&&&&#&&&BG##G&#&P:Y&PPB5@&B577~^^!!!!777??!~~77?J5PP#&B##J&&&?:^^^^:::^^^^^^^\n"
				+"!!!!7!7BY^~~^^^~5B&#G&#GGB??JJYY??J55Y??77!!7JPPY#&JB&7~&@&&B&&BB&@@@@#BPB##B@#&!B&7Y5BPYJ!!7YGGGGBBBGGGBBGBBBBG57~7??G#@&77~^:^^^::^^^^^^^^\n"
				+"!!!!!!!GP:^^^^^^~!&BPG5J!~!YB#&&&&&&&&&##&##GY~^J#GGB&JJ@&@G#BB5G#&@&&#BBBBGP#@@P5&JBP&#B?7###?YG#P~^^^^!!?5BGJYG##BY!~?P#7^^^~^~~::^^^^:^::\n"
				+"7!?J~!~PB^^^^^^:755Y!~!YG&@@&#5YY5Y5PY7?7JG&@&G55&B&#&PP@&&#&#BPJPP#BG#G5#BGP&@@GP&5&P?YBG#P?&5?BGBG5?PG&Y5G5#75~^75B&G7~!JY7^^^~~:^::^^^^::\n"
				+"755Y~~~Y#~^^^^!BBY!?5#&&#BP55Y5GJB##@#&&#5&#G&GJG&&&@@@P@@@#&#5PGBPGBGGB5BGBB@@@?#&GY#??Y##?JP~:5#BGPBGG@@#5&&&@#B5YY?P#P?5Y&5:^^^~!~^::::^:\n"
				+"!!!!~~~?&7~~~?&PY7G&@#5JYY5B&#B#&BP&&&&#BG!G&#G!P@BJ@&@5B@@&BBP5&BB#&#PGPBBB&&&??&&&&&#@B^7J#BY!5#JY?BGPGGGPG#BGGG5YP?JG#J5#&&^:^^~77!!!^^^^\n"
				+"!!!~!~~~GJ~~7&Y#J#&&5J5YYG#B#GGBB55#&&&5P#Y#B5@@&@#G@@@&JG&@&&#GGGBB&#PB#B&&@@?~&#&@@&#@&PJ5#J?#&Y?J5GGGBB5YJ?7?7777??J5J75#&5^:^:^777!!!~!!\n"
				+"!!!!!~~~PP!~?BPBY#P5Y55PP5PPGJ!!JJJJ5PGBB57Y##@@@@@@@@@@@PP#&@@&&#&###B#&@&##Y5@@&&&@@#@@&5PG#G555GBGGGP&&GPP5YYJ??JJ???JJ5GB#BY^:~7!77777!!\n"
				+"!!!!!!!!PG!!!7JYP#&BY55YYYYYYB#B&&B?!7?7!~^^!JPP&@@&@@@@@@&#GG#&&&@&&&&&&&###&@@&&&&&&@&@&BGB&#B&@#B#BB#&&&&&&#BGP555555PBGPJG#!^^^~~~~!!!!!\n"
				+"!!~~!~!!YG~?PGP5GGPYJYY555PPPG&&&&&#BB###B#BBB#&&&&@@&&B#&&###BB#&##&&&##&&&&#G#BB#BBGBBBP55JJ????!777!!7!!!!~~~^:::::::5@BBB#B7!::^^:^~~~~!\n"
				+"!!GP~5J7G&YJ&&BGGBGB##BGP5PP55PPPPPPGGGBBBBBB#BGG5Y55J?77!!~!!!?7?JJJJ777?JJJ7!!!!!!!77??JJ???JJYY55Y5555PPPPPP5Y5555?7^~JY!B&G:.^^^^^:::::^\n"
				+"!~#&P@!~?@&:J5GJBPB!~~~^^~~~~~!!!7?77!!!!!!!!!!!7!!!!!!!!7???JYJYY?????JJY55PPPPGBB#####BGPYJ?JJJ77?7?????J?7!~!77!^^:P?:::^G&B:.^^^::::::^^\n"
				+"5!B&Y&P~?&B^7P#?B&#!~7PPGGGGPPGGPGGGGPP555P555PPP55YYJ?JJY5Y5YJ?J77!!!!!77!~^^~?7J?!!!7?7~::~YJJ5YJ^?P5!::?P?7YJ7?PP~.GJ^~JBPB#^.:~^:^:::^^^\n"
				+"Y77?!!?77YP^~GGY^!~7~7G^~~~^^^^^!^^~~JY5PY~~J5G5GB5!?GYY55YG77BG~:^^^7#&YJ5#G!:5@#~~~~!#&!^^?#~^#@P^!&&~^^PB5#!::::B&:55^G#5#Y!~^:^^^^^::::^\n"
				+"!!!!7!!!!Y#~:G?J7!~^~JB~^G!^^^^Y&^^~BP!7JB&?~?@!~G@5!J??&?~~^:#P:^:^^^#&^::~&@?!@G^~~~^J@5:^#J::P@P::?@5:!&J&Y::::.7@?J?~&5##!^~~.^^^^^^:.:^\n"
				+"7!77777??5G~^?G?~?Y^^5B^!@#^^^~&@!:P&^~!!~B&~!&7:J@5^^^~#7~^^^BG^^^^^^B&^:^:5@&!@5^^^:^^G&~J#:::J&G^::G&!PP:&P.:::.~@?!?:B#B&#~^::^::^^^::^^\n"
				+"?!7!?!?775B~^^^PJ^PY~BP:7G#B^^GJ@P^&G:^~~~?&?~&GY&#~^^~~B?~!~:PB^^^^^^G&~^^:J@&~&P::::::^###Y:::7&B^^:^&&#~.7&J:..^GB:!J.^#B5G#~:::::^^::^^^\n"
				+"7777!77!7Y#7::^!#!JB!#Y:JG^&G?G:##^B&~:^^~!#7^BJ^Y&P~~~~BJ!7~:B#:^^^^^G@!^^!#@?~&&^::^^^:!&&^:::7&B~^..7&J.:.~5Y??Y?..^P..^PP!#J::::::::^:::\n"
				+"7!!7!!7!7?BY.:~PP~PY7G5:PG.~&&^:5@~~B#7~~!5J~~BG~^!BB?!!#G!!~7&#!:^^^?G#G5PG5!~5PPJ!!!!!~^7J^^^^7?7!~^~^?~~~!!!7??77??5&^:~?J?BJ^^^:::::::::\n"
				+"7~7777!!7?GB^^GYPBY~!YY^GP~.!7.:~PY!:!?JJ?!:^!?J?~~~!77???J7!Y????JY55YPGP5J?J55YJY5PPP55Y??JY55J777?5PP5BBP&BB@B&&@@&Y!7#&&&YBJ^^::::::::::\n"
				+"7!!!7!77~7J#7:P?5J!~^Y#?JJYJJJYY55PPP55555YJJ???JJJ?77??7!YPYY&&&&&&P5Y&&G77J5?Y7?~:....:^75PPJ7!!!!~!!~^~GG??P&JG#&&J^^JBPG&G#J::::::::.:::\n"
				+"!~7!!!!!!!7#!:YPJB&&G7P&@&B&@B&BG#5JJ55J~~!!^::::^^^^:^^!PB5B#Y5@@@&!55G@JY5GB5&!5&#PGGBP5Y?!7BB7:::~?P57?YGG@@&YB&&P:~5@G75&##7:^^^:::^:..:\n"
				+"?~!!!!!!!77B5:J#~#B#&YJ5@&G5B:P&##~?BBG#G5Y???77??JYJJYJJGBB&G!5B&&&#GG~J??:7B#JGB#BGPG&J5GPPP5B&#YP&&BJ:PB#BPPG?J##P.~#BB?Y&&G~:^^::::....:\n"
				+"!77~!~!77~7PG:7&BPYG&G5B@@@&YJB##&&@B&G&&&#GPP5GGPPG5&BGG&&&#GBBB&G!?7B#5~5#B#Y5PYYYYPB##BPYJJJYG#G&#####&&&&###~.J@&G7YG&J!&#P::::::......:\n"
				+"!!!~!!!!!!~P?^~P@P!GB?!!#@&&5@@@@@@@@@@&&&&&&&&&@@&&&&&&&&&&&##&&&&#BB&&##B#BGP5YYP#&@@@&&@@@@@@&&@&&&&@&&&&&&&&P::P#Y5YP&?P&PY^::.::.......\n"
				+"!!!~~~!!!!!G?^J&@#~&G!^#&&B!Y&@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&#GGG##GGB5J?7?7~^^^^^^^~Y&@&&&&&&&&&&&&&&&&&&&&&&&&&B^..5YYPG##&~ !#:::::.......\n"
				+"!7!!!!7~~!?P~^J@@#!&@Y7&@&!^P&@@@@@@@@@@@@@@@@@@@@@@@@@@@&&B5YJJ??777!^::^^^^^^~7~~~^!#@@&&&&&&&&&&&&&&&&&&&&&&&B~..J!7??7JBP!BG:.:::.......\n"
				+"7!~!!777~^!P~:P#?GP&#575&#:^B&@@@@@@@@@@@@@@@@@@@@@@@&&&&##GP5J!!~~^^~~^^^^^^~~7J7!!~^!&@@&&&&&&&&&&&&&&&&&&&&&&B7..^7!7!!~!!5&@B!..:.......\n"
				+"~^^^^^^~^^!5J:B5~:J&P7!7PG:^G#@@@@@@@@@@@@@@@@@@@@@@&&#BBGPP?7~^:::^^7J^^^^~~~~!YJ?!~~~5@@@&&&&&&&&&&&&&&&&&&&&&GY....?&###7.GB5~:..........\n"
				+"^^^^^^^^^^^JJ:B&&GJJ7777J!:^P#@@@@@&@@@@@@@@@@@@@@@@&BPBB5Y!::::::::^~Y7!^^^~~^~?YJ7!^^^B@@@@&&&&&&&&&&&@&&&&&&&G?:...Y@&&@?.G7J5...........\n"
				+"^^^^^^^^^^~5~:P#@5^!P5PY^:::P#&@@@&&&&&@@@@@@@@@@@@&#GYPGY!^:::::::::::^^^:^:::^~??77~^:Y@@@@@&&&&&&&&&&&&&&&&&&G?:...57.Y@7.P5BG:^::.......\n"
				+"^^^^^^^^^^^5~^!5#G^Y@@@@J:^:5B&&@@&&&&&&&@@@@@@@@@@&BG55Y?7^:::::::::::::::^::::^~??!!~^7&@@@@&&&&&&&&&&&&&&&&&&#?^..7&!.?@G.5G?5&5::.......\n"
				+"^^^^^^^^^^^5~^GBBB^?@#!YY:^^5B&&&&&&&@@&&&&&@@@@@@@&#PYYY5?77^:::::^^^:::::~!~::::~77~~~^B@@@@&&&&&&&&&&&&&&&&&&#?^.G&Y~GB@#.5Y^@P:::.......\n"
				+"~^^~~^^^^^~5~7JG##~J@P^~#5~^5##&&#&#&&&&@&&&@@@@@@@&G7?J??YY?~~^::~7!~:::^7!!?J!~^^~~~~^^Y&&@@@&&&&&&&&&&&&&&&&&#Y^.GP:~@&&#:JG!&Y..........\n"
				+"!!!!!!^^^~75~!BP7#^J@&B~7&P:J&#&&##B#&&#&&&&&@@@@@&#YJJ7~^^!!~~~^~!7Y~::::YPY?7777::::::^5&&@@&@&&&&&&&&&&&&@@&&&P7.:!?~B@&5^^JY!~7.........\n"
				+"^^^^^^^^:^!5?:5#!&!?@#@&~5Y:7&BBBB#55&#B&&&&&@@@@@&PY??~::::^?^:^!J5G7:::!?55PJ::^:::::::J&&&&&&&&&&&&@&&&&&&&&&&5J::.J!:?@&JPPGBB@7........\n"
				+"^^^:^^!!~^!YY^J5P5~!?B@#JJ~^!&PG#BGBB5P##&#&&&@@@&B5?7^:^75J??~^^!P#&B!:.~G##P7::^~~~::~^~#&&&&&&&&&&&&&&####&&&&PY:::?G!7#B:?#B@P5~........\n"
				+"^^^^^~7!?^:7YG&7^7G5&@G^J^^^~#PGBGGGGPB##B#&&&@@@#5?7~:7BPJ77?J?7~7J5B?:..^Y?7!J5PPP57~7~!PB&&&&&&&&&&GB####GB&&&PY...:!!~^^^?BGY7^:::^~~~^^\n"
				+"~~^^^~~!~~^?G7B&GBJ^P&G?7:^^~#G#GPPP5YPBBB#&&&&&&#?~^:!PJ?5G#&&&BP57~~!^.:^75#&&@@@@@&B5~:YB&&&&&&&&BG####GYB##&@G5::PYJJJJYY??&&@G:..:^^^^:\n"
				+"~^^^^^^~^^^!G^:P@#Y^:^^^^^^^~&BGPP5GGG######&&&&@?::^~!7G&@@@@@@@@&&##577P#&@@@@@&&&&&&&5.!B#&&&&&#B#&#BBBJ5BBG&&BP:^#J##&&&&GYB5J~.........\n"
				+"^^^^^:^^^^:~B^:!P&G??7?!7Y7^^##&BPPGGGG&#&&&&&@@#^::.^P&@@@@@@@@@@&@@@@@@@@&&&&@@&&&&&&&B~.7#&&&&&#&&#BGPPGGGGB&&BP::B7&@#57BB^:..::::::....\n"
				+"^^^^^^^^^^:~B~~&&&BBBBBGPG#!^#&#BGGBBGB##&&&&&@&7~:.7&@@@@@@@@@@@@&&&&&&&&&&&@@@@&&&&&&&&J::?&&&&&&&#G5PPGBG#B&&&#P~:PJB&5BP^:::::::::......\n"
				+"^^^^^^^^^^^~B7:^~P??##&&@PB?^##B#BGBB#&&&&&&&&&5.::!#@@@@@@@@@@@@&&&&&&&P7J#@@@@@@@&&&&&@P~.:?&&&&&&&#GPJJ5B##&&&&Y!:YYBJYP?~:...::.:.......\n"
				+"^^^^^^^^^^^!BY.::.::?GBB&&B7:B#BPPPBB##&&&&&&&#:::~B@@@@@@@@@@@@@&&&&&#~::!B@@@@@@@@&&&&@#~:.:B&&&&&&##B5YP&G#&&&&Y~.?JG#Y7#&?.::::::.......\n"
				+"^^^^^^^^^^:!BP:::^^~!7P7J&57:B&GY5GGBBB&&&&&&&~.::Y@@@@@@@@@@@@@&@&&&&&#GB&@@@@@@@@@@&&&&&J!:.~B&&#&#B##5PPGPP5B&&P7.7J5@BB5Y:.::::::.......\n"
				+"^^^^^^^^^^:~GG:::::~&BG?#B?7:B&55PG##&&&&&&@&~..:~&@@@@@@@@@@@@@@&@@&&&#GY~?PBBGB&@@@&&&&&G5!:.:G&&&##&#GBPYY5YG&&?!.!75@7~BY:.:::::::......\n"
				+"^::::::^:::~GB::::::7B#B@#!7.G&5PB##&&&&&&@B^.~:.5@@@@@@@@@@@@@@&&@&&@#??77JP5Y5B&&@@&&&&&&&5?!.^G&&&@&B##PPG55P#&?~.!7P&!7!5B::::::::......\n"
				+":::::::::::~PB7::::^?#&^G&7J.Y&B#&&&&&&&&&5:.^?~^&@@@@@@@@@@@@@&&@@&&PPGGGGP55J?J5P&&@&&&&&@&557.:!&&&&&#&#&#GBB#&57:!75@#P#&@P:::^:::......\n"
				+"~^^^^^^^^^^^YB#?.:^~#?55B&!5.J&#&&&&&@&@#!...JP??@@@@@@@@@@@@@&&&&&&&B55YP5YJ!^~77B&&&&&&&&&&#5G7:.7#@&&&##GGPPGB&P7:!?5&P^!?GBP:^^^^^......\n"
				+"?~^^^^^^^^^^~P#&~:^^5GB5&&!5:Y&&&&&&&@@B~.::!JYY#@&&@@@@@@&@@@&&&&&&#BGGP5YY~^^~~J&&&&&&&&&&&&GPG5^.~P&&#&#BGPY5G&G7:^JJ&&&5:~B@?:^:^^......\n"
				+"5P~:^^^:::^^:?##~^!77!:!&&^Y:J@&&&&@@&Y:.::J?YB&@@7P&@@@@@@@&&&&&&&&Y~:.:::^:::~5&&&&&&&&&&&&&&BJJY!..~G&#B#&BPGG&P!:^57#&B&#:~&#:^::^......\n"
				+"P#^:^^^^:::::?#P.:5J::GGG&^?:?&&&@&BJ!::::J5P#@@@G:P#&@@@@@@@@&&&&&&#P7:::::~!Y#&&&&&&&@&&&&&&&&BJ!J7::.?PBBB&####P?^~B7&BYJPB^Y&~^^^~:.....\n"
				+"G#Y:^^^:::^:^P#P.~#?.B&5B&^?:7&@&57:..^::7YG&@@@&^~GP&&&@@@@@@@@@&&&&&&#####&&&@&&&&&&&&&&&&&&&&&BY7JYJ~:!?P#&&&@&GP^~#7B7?B?57J&~^:^~:.....\n"
				+"B#&Y::^^:^^:~PB&~~&:?G~!!B!Y^7&&G.:^^^::?Y&#&@&@Y.B7J###&@@@&@@@@@&&&&&@@@@@@@@&&@@&&&&&&&&&&&&&&&#7^!PPJ??5GG&@@@57^^#?#J777!~#?^^:^~:.....\n"
				+"#B@&57:^^^^^^YB#^.B^777?~?JY!!&#~:~!JY7JP#&&@&&@B?Y5^?GPB&@@@@@@@@@@@&&&&&&&&@@@@&&&&&&&&&&&&&BG&Y?BJ^.JB5JJ5PB&&@Y!~^#?&&BYJJG7:^^^^~:.....\n"
				+"#GB###^::^^^^Y#Y::7P!!7JP&YJ77&#?~?!YYJG#@&&@@&&@&B!?~?YPGB&@@@@@@@@@@&&&&&&&&@@&&&@&&&&&&&&&&BG5G?^JG7.~G#PYGBB&@5!^:#?#GP?B&~:^^^:^~:.....\n"
				+"PPPP#&5^:^^^?G#7:^:^J&5PGG57J7&&GY?!!Y!5&@@@&&@&&&&&Y~7JJYGB&@@@@@@@@@@@@@&&&&@@@&&&&&&&&&&&&&B5Y??J~:7G7:^JB#B#&&G!^.P?&B7PJB~:::^:^^:::...\n"
				+"BGGG&@Y:^^^^5B&7.^^^^G?J?&G75?&@&&#&BPP&@@&@@@@&&&&&@B7~:??P#&@@@@@@@@@@@@@@@@@&&&@&&@&&&&&&@#Y7!7JJ^:5&&#5~~5#&&&G!^.YJ&#?PG7:::::^~::^:...\n"
				+"#GB##@P^::^^PB&J:^!!!YGPP@&?Y?#@@@@@@&&@&@@@&&&&&&&&&&&P~!7?P#&&@@@@@@@@@@@@@@@@@&@&&&&&&&&&&GY7!7J^7#@&&&&&B5YG&@B7^.?Y&&&@@#!^:^^~^:.^::..\n"
				+"&PG#&#&#B?:~PB#5^^!!~5@G#5&J5JG@@&@@@@@@@&&&&&5B@&&&&&&&&57!JP#&&@@@@@@@@@@@@@@@@@@@@&&&&&&&BY!!?Y5B&&&&&&&&&&#&&&#7^.G!&5!5&#GB~!!~:..:~:..\n"
				+"#GP&&#BB@B:~PB#P^~^77G&J?Y&?5?G#&&&&&@&&&&&&&G~G&#&&&@&&&&&G5JPB#&&@@@@@@@@@@@@@@@&@@&&&&&&B77YGG&&&&&&&&&&&&&&&&&&7^.G?#&G#&GY#5~7^...:^:..\n"
				+"##&&@&B#@&!!PB#G^~YG~7GYB&&Y57G55P&&&@@@@@&&&7^P&&&#B#&&&&&&&#Y5#&@@@@@@@@@@@@@@@@@&&&@&@&&##&&&&&&&&&&&#####BGGGBG5?!!Y#&PY???PJ~!^:.......\n"
				+"#####&&&&&&#&B#B:^7!Y!7??7BB5JGBPGBB#&#BBBGGGYYPGGPPGPPPPPG5Y5YJ5GBBGGGGGPGGGGPPPPPBGYYYYJY55YJJJJ?77!!~!?JJPJ!~^^!&#B?G#G^?55Y~#7^^:.......\n"
				+"##B#B&&&&##&@&&&::~P~^GJ5~?&B&&#YGBJ&P!YY57^^~~~^^::^^::.::!7?~!!~!##Y^:!?GG5Y5G!YBP!~:....:!~^!77!^~^~!!^^!&&##!?BP&&#&BGJJYB#:&Y:::.......\n"
				+"&&B&#&&&&&&&@@@@~:^G^.?555#B#@#J&@##&P&B#&@P?J!!!!7!~7!JYY7GGGYY7^#@&&#Y5^?J7!?&PPG5&&JY55PYJ?YBBBGJ7~5&&G7!&BGBJ:JJ&&PB#B@B&@~^@?.::.......\n"
				+"&&B&&#&&&#&&@@@@!::55..P@B#B7JB&5P&&&P5?GB&BP57J55GGPP555P5!Y5GPJ5?&##G?P?GGBY5#BJPPG55G?7??YYJ7?7?J5??Y?..:GB&&G^^BJP7.PB&G?7.5@~.::.......\n"
				+"&&G#&#&&&##&@@@@~::~&?:^YPB::Y5#J?55P?77????77JJYJJ???JJ???7?YPP?P&&&#G7?JPG5?7PYPGY7?J!~!!~!7!!!~!!~^^^^^~7?~~!~~YPG7^.?@#P^.!&#:::::......\n"
				+"&&########&&@@@@?:::JG7^:&7:^?JY5^~~^^~PGPPGB?~~7PBGPGY~^^^~!~~~J##PG#5~::.~5J?Y5!....7P5JPG?............75JPY....:J?:5::#&J^?GG!.::::...:..\n"
				+"!!7~~~~^^~7P###&7.::~5YJB@~:~5JGY^~^^^!@577757^7&&7^^5@&?^^~~^:J@#~^^5@#:.Y@J.::#@5..G&J...#@G......:...G#..^:....^YJ^Y::#&&#G&7.::::::.::..\n"
				+"~^~~~~~~~~!PB#&&7.::?&5#B@?:~5YP5~~!!!?&&&&#P!^#@P:^^^#@#^^^^^!@@J^~^^&@?!@&:...Y@&^7&&^...Y&@~.....PY75@B?!......^YJ^P::&#7!^G..::::::.....\n"
				+"^^^^^^^^^^!PB##&!.::.Y~~^&P:~PPPY~!!7!~~!7P&@&~&@Y:^^:P@#^^^^:?@@J^^^^&@P?@&~.:.!&@~J&&~...?&&~.....:?B@@Y7B!.....:JY~P:^&&!JG~!!::::::.....\n"
				+"^^^^^^^^^^7GB##&!.:?775PB&Y::5P#Y!!!~~!^^^^7@&^G@B::::B@5:::::^&@B::.!@@!~@@?.:.?@B.~&&Y...?@P......:.:&#...:......YY~5::~YG#7?GP7.!?.:.....\n"
				+"^^^^^^^^^~YB##&&5~.GBB~G?~::^Y5&J:^^^~GJJJYB#7:^G&BJ7P&5:::::::~P&GJJ#B!.:J##J!7B5:..~B&Y7JB5:.....557B&!..........P?~G:::::77J@G:~#?.:.....\n"
				+"^~~~~~^~~!PB###&&#7!#G~7::::^PJ&G~~~!!Y5P5P5?7???YPGP5?!!!777!!!!7YP5?!!!~~~7??7~^^^^:^!J??~^^~^^^^JGBP!^^^^^~~~~~!P7!G:::::7J5@Y7Y5::..:::.\n"
				+"^^~^~~~~~?B#####&&&PJPG7:::::P7PP5YYJJ?7777???7??77?7???77!!77!!777777???77!!!!!~~~~~!!~!!!!77!!!~~^^^^^~~~~!!!!77?7J7G::^::J#BJ&&G~^^:.::::\n"
				+"^~^^^~~~~JB###&&#7^PG.?J.::::??55YJJJYY55555555YYYYPG&##GPBB&#PP&&#&&&&###&&&BPPGGGBB&BG&@&B#B&&&##P!!7?JJYYYYJJY???J?5:::^!&?:?@B:::^::::::\n"
				+"~~~~~~~~~!JB#&&&#?GBB~7G7JJ5PGBBBB&&&&&&&&&&###BGBBGG&GB&B&&G&GPP&GGB@@@&&@@P5#&&#&G7&&&@B&&&&#&&B#P77?JJJJ?J5555555Y5PPPGB&&J~G&BG5J!^:::::\n"
				+"!!77!77!~!!YB&&&&Y5GP7?JY5P#BGGGPGBG555555PPP5J?JJ??77?7?JJ?77?77?77?JYYYYJJJ??Y???77??JJJYJ?????Y5JJ??7??JYY555JJJJYYYYYYYYPPGYY#@&PG~:^:::\n"
				+"!7777!!!~7JG#&&&&P!~?:~!!P#P5PGB#B#PG&&&#&&&&BJ?!7#&5P5Y?BYJJ##G##7GPGGBGGP5&#5JPBP#5B&PJGB&#PGB&&##&&&#&#&#PGY77JJJ#B@@@B5#5&GJBY#?P!P^^:::\n"
				+"7777777?YG#&&&&&&#..:.^!~YGJ?~P5PG#PY5BP5B#G@&B&&BB5PG&GGB^7775GG5P&&P~Y~??7P55?5PB?B5G&55B@&5&&&G575&GG&BG5GJ7:~7Y5PGBBY^~GJPJGG?7GJ7P^::::\n"
				+"!77775GB##&&&&&&@@J. ...JB^::.^7YYP#BPPY5GJ5&~77?!:J:^GJJ&G7##PJ?5##@G!?PY~?Y#PPPJ5G5P#PG7^BG5B#J5J55B#&&&BGPGYP&5?55Y7^^^^!J!:5@&~~!!::::::\n"
				+"777?JG#####&&&&@@@@&GY7^........^7?J5B&#BPJYJJYYPB?!7!P5?J?7^~5BBGG#&#&&&@#&@PG&GPJ:^J5PPBBGBBG#B5PGYJP5555PGB##P?7~::^^:::::::~PJ^:::::::::\n"
				+"!77JG#####&&&&@&@@@@@@@&#BG5P55Y7J5PG5?JYJG&@@@@@@@@@@@&#BG5J?J7YGBGB?7B&&BG##G5577JJJ?5&@@@&P5JYYYY555J!~~~^:::::::::::^^^^^^^^^:^^^^~:::::\n"
				+"!7?5B####&&&&&&@@@@@&&@@@@@@@@@@@@@@@&##B&@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&@@@&&@&@@@@@@@@@@@&BBBGGGGGBP7^~7777!!7!!!!!!!!~!~~~~~^^^^^^:::::\n"
				+"!7YB###&&&&&&&&&&&&&&&@@@@@@@@@@@@@&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&@@@&&&&@@@@@@@@@@@@@@@@@@@@&#P!^!????777777!!!!!~~~^^^^^::::::::\n"
				+"7JPB##&&&&&&&&&&&&&&@@@@@@@@@@@@@@&#&#&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@&&@@@@@@@@@@@@@@&&&&&#5!^!???77777!!!!!~~~~^^^^^^^^::::\n"
				+"?PB##&&&&&&&&&&&&@@@@@@@@@@@@@&@@&#&#&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&@@@&&&&&@@@@@@@@@@@@@@@&&&&##GJ^^!????77!!!!!!~~~~^^^^^^^::::\n"
				+"YG##&&&&&&&&&&#&@@@@@@@@@@@@&##&###B&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&@@&&@@@@&&@@@@@@@@@@@@&&##BB#BP7^^7?777777!!!!!~~~^^^^^^^^::\n"
				+"PBB##&&&&&&#BG#&&&&&@@@@@@@@#PGP5PP&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@&&&&&&@@@@@@@&&&&@@@@@@@@@@@&#GG##BBGJ~^!77777!!!!!~~~~^^^^^^^^::\n"
			+"tu y tus companeros lograsteis escapar del gobierno ahora ustedes son fugitivos\n");
		}else if(totalpoints<=620){
			message=("\n"
			+"	???????????JJJJ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????77???????7??????????J?????????????????????????????????????????????????\n"
			+"	???????????J??????????????????????????????????????????????????????????????????????????????????????????????????????????????????7??????????????!7?!???JJJ???????7?????????????????????????????????????????\n"
			+"	?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????77?????77???77!??????7!!~^^^?J?JJJJ??7???J777??77??????????????????????????????????\n"
			+"	????????????????????????????????????????????????????????????????????????????????????????????????????????????7?????77777?!~!~?7~^::~^^~~^!7~~!!^^~77!~^~77!7?77~^!~~7????????????????????????????????????\n"
			+"	????????????????????????????????????????????????????????????????????????????77???7777???????????JJ?????????????775PPGPPY77!!7~.::.::....:::~!^~~~~~^:^~!7^!7~!!7~^!JJ??77???????????????????????????????\n"
			+"	???????????????????????????????????????????????????????????????????????????777??JJ?7!!!7??JJ?JJ?JJJ???????77?7JB#GY5&&#BGGGPJ~.~^:^^:::...:^:::^:^^~!~!?Y7~~^^^!?~~?YJ?77777777?????????????????????????\n"
			+"	?????????????????????????????????????????????????????????????????7??????7?77YJ?JYJ7????7777?JJJJJJYJJJJ?!~^!!~P&#BB&&@G^^?5YY?^^!!~:.^^::^^..:~!??777?J?7!^^7!^!!7?JYJ?777777777777?77777????????7??????\n"
			+"	?????????????????????????????????????????????????????????????????7??????7?7777!??7?77JJJJYY?JYJYY55YJ!~:.7&&BP&GGB##@B::~J?J!~Y?J5?~!^^^!YY7^~7YYYYY7J?J5YJ?JY?JY?^7Y77777777777777777777?7777777777????\n"
			+"	????????????????????????????????????????????????????????????????777??7?777~~~~~!!~~~~^~?7JYYJY7~7!!~^JPY75PB#G!^PB#BG7~?Y5P5~~?5???77!!Y?JP?7?5YYYJ7^7!!JYYYY5YYJJ7.~!7????77777777777?7?7777777777777??\n"
			+"	?????????????????????????????????????????7??????????????????7?7?7?77??77!^^!!7!77~~^:~^:~??Y??~.!P5!?G##?:?5Y55YBBGGGPB##BB#Y~~YYY??J?JPY?JPJJ555JY!!7:^::!JY55J?J57...:~7??77???77777???7?777777777777?\n"
			+"	?????????????????????????????????????????7???????????????????77?????777777!77!^~!~^^::^~Y5JJPG??PB&?^JGBB5J7YGPG5GGG###BBPGPYPYGBB55P5PGPJJJ57J5JJP5~..:^^~J5??YJ?55:~^. .^~^^^^~!??77:~??7777777777777?\n"
			+"	??????????????????????????????????????????????????????77777????77?7777?J?!!~!~:^7?^.:!JP&G7!B&#~^5GP?7~PB577GB#G?JPGGPYYYJ55J55PBJ??YYYYYYPYJ55YJY?P5!^:~~?JJJ7??J?J~~JJ^^!77^^~!7?~.. .7777??7777777777\n"
			+"	????????????????????????????????????????????????77777777777???7?7!~~!!!~^^~^^^!7:.:~JGJ5#BY77BB7!~P#GYYPPBBGPGG?JJ!^^^:::^~7YJ?7777!!???7?J5YJY5Y5JPGY!!?7~:^77!75PJ!^~7!JYJY7?PP5J!......:^~77777777777\n"
			+"	????????????????????????????????????????????????77777777777????7!^~7!^::^~~~!!:::!5G&5^YB&#7~PBGJYGB&#Y?JGGBBBBBBGPPJ?!::^?5G5!~~?7^~77~~!7JP?77JJJ55?!~~::.:^7J~?555J^~7JY5?YGG555J^.    .. ^?777777777\n"
			+"	???????????7????????????????????????????????????777777777777??!~77?!7?!77JY?:.:!5J?B&#J!7G5~?###&P75&&&#G#GPPGBBGGPGPYGPJYY5Y5?Y5GB?~?5J~~7YJJY5PPPGGYJ!^^^::!!7??557J:.^~???5BJ^~?7. .::. ...::::~!!!77\n"
			+"	7??????????7????????????????????????7777777?????77777777777???!7!!!!!77!!~^:^75##???B&#J~JB#B55BB#B###B&&##BYY5PGBGPP555Y5PGPYP#BBBP5PBBGB##BP55BBGGGPGP?!7~:7J?7JY77J^.^!~~?5?:..:7~~!!Y^:^^...^^.:^^^~\n"
			+"	7??????????7????????????????????????7777777?????777777777777??!!7!~!!7Y!:.:7P5JB&G?7YG575#&#GP5B&&#B5GB##BGYY55PP555YYJ~~7?Y5GG??55YPPG&&&#BJ!?YGG5GG55BG7~~~?JJ?~?Y??!!J5YJ?77^!!~J55?!7::!!::.:77!^^^^\n"
			+"	??????????????????777???????????????7777777777777777777777777?7!77!!!~::^?G&G!75B&B?!~JB#GGBBBG#&#P55G#B##P55GP5JJJJJ?J!!5GB#BG7YPGG5P7P&&Y!?Y5PGPY5YJPBJJ5YJ~^77.!JJ7~J557JPP7~7?JBBBBYJJJ?J?^.. .:::^^\n"
			+"	?????????77777777777777?77?777??????777777777777777777777777?7777?J!::~?G5G##P??#&P?!7JJ7!!?JJ5PGBB#G5PBGBB#BPJ?GBBGPGPPBGB&&&&PJ5GGJ!J&&J!!JG?YGPGPJ5#Y~YBY5Y^~^~7J?!!5J!7JJY~:~Y#BBB###5~!?JY7^...:.^!\n"
			+"	??7??7?7777????777777777777777777777777777777777777777777777?77??7^:~YB&BG##B##G?:.^JY??J77JJYYJ??55Y5GGP55YYJ!7JPGPP5PBGGP&@&&#J7P57!B&Y7~7JYJPP5P#P5J7!7BY!7^^!7~~7!7!?JY5J?^7P##G5J!~7P?!~!5~::^^7?~:\n"
			+"	7??7777YPPG#&B##GG5J7?77777777777777777777777777777777777777?7?!::~5BG?!^:.......~YPGBBGBP55?JYYYP555YJJ7?JJ?Y5!?GG55Y#5P57GB#&&G^JY!7##G?7^!7J#5755?5JP?7?JYY!^^~!~7!~???JJ7^P#BBBBBBY^~PPJ7::.:~!7J?~:\n"
			+"	77?77?B&&&&#&@@&#&@&J!77777777777777777777777777777777777777?^:^~~:::...:^!~!7JPPGP5YB######BPGBGP#BGPJYG55P5PBPJYPPPJ#Y?PPY!5&&J7!^::7#&57!?JPGYYP577Y?77!!!!7J?7!!77!YJ5Y!~?GBP555PPY~^::~^::^::^^^!~:\n"
			+"	77777&@@&&@@@@@&@@@@#B!777777777777777777777777777777777??7?5J77!~?Y5PGP5YJJYPPB5?JYY5GPJGBB#####BGGBBP?PG555Y5P5?PY?JJJYP5Y?JPP?~~:.:JPP?^7?Y5JJ??Y7?J!~^:~J??Y?7?~!!7!77~^!?!JP?YJ!7~^::^......:!?7?7~\n"
			+"	777!5@&@&&&@@&&####&&P!!777777777777777777777777777777777~5B##BGPP5?!:....!?JYYYJJYYPY55JJY55PB#&BGB#BB5P575YJYJ5GP?!YPGPY?7J~::.::^^:^^:.~YJJ?7Y?!!!!~^:^^77?J!^~!~~!!77^.:::~7?!!~:.....:::::.::?PJJJ!\n"
			+"	?77!Y&@B5GB&GYPB#&&G77!!777777777777777777777777777777!^.^?J7~^:::...:^^~!YGGPGPGBBGB5JJ5PGBB#B#&&####B5Y#G5JJBYYPPPJ?YJ?7!!!7~:^~^..^::!^~?J7^~75J~^^:..::^77!~^?J7~^~!!?7^.:.:~:.:...  ::.....:~!?J557\n"
			+"	7777!B@BYPY5P??P#&BG!!777777777777777777777777777777!J!~7~::^~7Y55GGPBB#BBGBGGPPGPPG55BBBBB#&&#&&BYPPGBGJ##GPYG57?5???J??JY5J?7JJ??^:^^:^^!7J?7!!!!^::...:^77^^?JJYJ7~77??J^~~^......      ...^~~!!!~77?\n"
			+"	77777!##7PGGBBP5B#BB?!7!7777777777777777777777777?7JG##BGPGPJY5GBBG5?77?7~!~^^^?55YY5GPG&&#B&#B#P57^J7YG5PBG5Y5YJ??!YY?7?5PGBP5GGPJ^^:^^:~!!JJ55?77:^::^~!JJY7~!J77!77?YY??~?J?^^^:......:^^^^!!~^^^^!YY\n"
			+"	?7777!JP5P###BB###B7!777777777777777777777777777!^:JPJ77!~^..........^7!!^^!!~!JJYJJYYJ??JYJ?J?5Y7~~!7~?55GPPP55PPP5PP5J5BGP5PY5PPP5YPJ~:7J?J5GGGY!???7~!!J?J?77?YGJ??JJJJJ?!YJYY7~~!7~^!!~!~~~~::::::~!\n"
			+"	7777777!!?YGB#&&&&?~777!77777777777777777777777?^~~^...:^!!77J5YY5GBB#P5PPYGGPYYYYYJJY5P55GGPGPY##PY??7YGPYYGP55P55G5555P5Y55PGGBGBBGBBGPGGGGGBBGPYYJYY?YJY77YY?YBBPYJ5YJ?JP7?YYYYJ777!77~~!!?!::.::~!^^\n"
			+"	7777?~.     .^JB&G!77!!7777777777777!7!~~!7!~7P#BGPPGGPBBBBGGBPP5P5J5PJ!~J~!Y5G5PB#BG#&#BG###&#P##&GPB5PJ?JY55PPPY?YJPGY5P??YGBBBBBBGGGGBGBBGGGBGBBGGY????5P55JJGGG5YYJ!?J5YJJYJJ?YJ?!~77!!~!7777~~~~!~~\n"
			+"	7777:          ..:~777!7777777777777????JYPB5P&5??JJJ?7?J7J?J?7?!^^^~JPPG7:^P?!~~7?PPG#GPGBGPPPBBGGBGGGPPPBBPB#BJ~?YYGP555YYPBGGGGGGGGGBBBBBBGBBBBBBBBGGGYJJYYJ5G##BBG57?J7YPY?JP5JJJ77?!!!~!??7??JYJJ7!\n"
			+"	7!?.  ..          :~!777!77777JYPGBBB####&&&&&P77777777!7??JY5Y?!::!~JY5GJ!77~!!:^!!JPBG55555P5YY5YPPYY?P5?JJJ??775?!J5YY5PPGGGGPGGGGGGBBBBBBGGGBBBBBB#####GPPGB##BB#BP7?5JYJJY?JY5?7!7??7~~~!J7~~7?J??J\n"
			+"	777  .!^.       .?PJJYYJJJJYPG######BGGBBBBB5J777777777!~!?77PG577??!!J777YP~!77!~~!!!?J7~JGGG5YY5YY55JJ!!~~!!7?JYP5JYPP555555PPPPPGGGGGGBBBBBBBBBBBBBB#####BBBBBBBGBGP5PJ!J??JY??YJJ?YYJ7?7~~!~~~~7!7Y5\n"
			+"	7!7. .!7~^...  .5JYJJYPGBBB####BP5J?77!!7777?777777777777~!75BGG55Y55J?7Y5GGGPY?J5555PY7!!?JYYJ?Y?~7?JJ!~!!~7?J?5P555YY55YYYY55555PPGGGGGGGGGGGGBBBBBBB###BBBBBGGGBB#BBPGPY7!???JJY5PJYY77?J?77?777!!JY?\n"
			+"	777  .:!7!~~~^.J&BGB#####BBGPY??777777777777777777777777?!^7J5YYJY5JJ?7?5555Y5Y?J555GBGGJJJ5PYYJ~!JYJJYY7!J55PPPPPPP5YY5YYYYYYYYY55PPPPGGGGGGGGGGGBBBBBBGGGBBBGBBBGGBBBGBG5YJJ?J?JJJYYJJJJ?J5J?JYJ?JY5Y?\n"
			+"	!7!  .:!!!!!!!!!5YY5PP5YJ?777777777777777777777777777777777!!?JJJYJ5J?JJJ5PP5?J55P5PGGPP55JYP5!~!7Y5Y5GBGGGGPPPPPPPP5YYYYYYYYYYYY55PPPPPPPGGGGGGGBBBBBBGGGGGGBBBBBGGGBBB##G55J?!!7?J?JYYJJ??J?7JJ???J???\n"
			+"	77! .^^!!!777!!7!!!~!77!!77777777777777!!!!!!777!!!!!!777777!7?7J?!YJJY?JY5YYJ7J555?775YYYJ?YY?5JY5?7PGGGPPPPPP555PP55YYYYYYYYYYY5PGPPPPPPPGGGGGGBBBBBBBBGGGGBBBGBBBBBBB#B##BPGPYJ7JYYY55J?J?77??77!??77\n"
			+"	!77  ..::^!7!7?Y7777777!77777777777777!!!!777777!!!!!!!!!7777!!!??!77JYJJYYJJYYJ?J!!!7Y?JJ?7JJJ5YP57YGPPPPPPPP55PPPPPP5555YYYYYYY55555PPPPPPGGBGGBBBBBBBBGGPGBBGGBBBBBBB#######BPPY5GPYJJYYYJ?!~777YJ??7\n"
			+"	7!7. ...::^^!77!~~77!!77!!!!!!!!!!!7777!!!!!!!!!!!!!!7!!!!77777~77~777??77!~!!????7777??7?Y?!!JJ??J5GGPPPPPPPPPPPPPPPPGGPP555YYYYYY5555PPPPPPGGGGGGBBGGPPPPGGGBGGGBBBBBBBBBBBBBBP5PGGGPP5Y555J?7~^^!JJ?7\n"
			+"	!77.. .^^..:^!!~~!!!77777!!!!!!!!!!!7!!!!!!!!!!!!!!!!7!!!!!!!!7777!!!7J??77!!~~~7Y5YJY5PJ7P?~!!!JYPGGGPPPPPPPPGGGGGGGGGGGGPP55YYYYY55PPPPPPP5PPGBGGGGP5555PGGGGGGGGBBBBBBGBBBGGBGGGGBGGPPP5PPY?!??JJ?JJJ\n"
			+"	7!7. :^::::.::^~~~!!!!777!!!!7!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!77777777??7!!!!!?7??YGP5GGJJ7!57~?Y5GGPGPPPPGGGGBBBGGGGGPGGGGPP5555PGGPPPPPPP5555PPPPPP555PPPGGPPPGGGGGGGGPPPGPGGGBGGGB#GGBG555Y?Y5Y?7Y55\n"
			+"	!!7.   .:::..  ^!!7!!!!77!!!!7!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!777??!~7??555PG5J!~7?7?Y5PGGGGPPGGGGGGGGPPPGGGGGGGGGGPPPPGGGPPPPPPPP5YY555555555555555PPPPPPPPP555PP5PGBGBGBBBB##GGBGG5P5JY5PP\n"
			+"	77!..  .::.  :~!777!!!!!77!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7777777?7?JJJ55YP55P5555PPPPGGGGGGGGGGGP5PPPPGGBBBBGGGGPPPPGGGGPPPP555555555555555555YY55PPPPP55YYYYYY55PGBBBBB####BBGBGP5555P5\n"
			+"	77!PY:..   .^!7J?!777!!7777!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!777???7??!7YYJJ?5GGPP5555PGGGPGGBGGGGGPP5PPPPGGGGGBBGGGGGGGGGGGGGPP555555555555555555555PPPP55555YYYYYY555GBBBBBBBBBGBBGPPGPPG5\n"
			+"	77!JY. . ..~7777!!!!!77777!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!777??77?7!J5YJPGGGP5555PGGGGPGGGGPPGGP555P5PPGGPGGGGPGGGGPPPPPGGGPPP5555555555555PPPPPPPPP555555YYYYYYYJY5GGBBBBBP5PY5PP5YYYYY\n"
			+"	!!77^ :?PPYYP5YYYYJ?7!~~!!7!!!!!!77!!!!!!!!!!!!!!!!!!!!!!!!!!7!!7!!!!!!!!!!777777???777?JPGP5YYYYYY5555PPGP55PPP55555PPPPPPGGGGGGGGPPPPPPGGGGGPPP555P555PPPPPPGGGP5555555YJJYYYYJYY55PPGBBBBB#B555YJJ?JY\n"
			+"	7!!7~:P@@@@@@&@@@@@@&#YPY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7777???77??J5YJJJJJJJJY5YY55555Y55555P555555PPPPPPBBBBBBGPPGGGGGGPGPPPGPPPPPPPPPPPPPP555555YYJYYYYYJYYY5PPGBGGBBGG55Y??JJJ\n"
			+"	!77!!?#&##BBBB###&@@@@B#@P!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!77!7777!77!777????????JJJYYYYJYY555555555555555PPGG#####GGGGGGGGGGGPGGPPPPPPPPP55555555555YYYYYYYYJJYYYY555PGGGP5P5YYJJ??\n"
			+"	7!!7~P&###&#G#&&&&@@@@@&&&G!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7!!!!!!7!!!!!!!77777777?????JJJYYYYYYY55555YYY5555PGBB##&##BBBBGGPPGGPGGPPP55555555555555555YY5555YYYJJJYYYYYY5GGGPP5?777??\n"
			+"	!!7!7####&&#B###&##&&@@@@@@5~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7!!!!!!!!!!!!!!!!!!!777777777????JJYYJJJJJJYYY55PPPPGGBBBB##&#B#BGPPPPPGPPPP55YYY55555555YYYYYY555555YYYYYYYYY555PGGGJ777YG\n"
			+"	77!!P#####BB##BB###&@@@@@@@&~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7Y555YY?77??????JJJJJJYYY55555555PGGB######BGGGGGBBGGBBGP555555555YYJYYYYY55YYY5YYYYYYYY555PP55Y775GB\n"
			+"	!!~?######&########&&@@@@@@@J~7!!7!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!JGBGPGBBBBJ77777????JJJJJJJYYY5YJY5Y5PPPG##&&#BBBGPGGGGBBGGPPPPP555YYYYYYYY55YJJJYYYYYYYYY5555YJJJY5GPB\n"
			+"	!7!!#&###&&#&&####&&&&@@@@@@&J!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!777!!B#!7?7!7GGY7777777???????????JYYYYY55PP5GGB#####BGGGGGBBGGGBBBGGP55Y5555YY5555YJJYY5YYYYYYYJJJ?J??55GPB\n"
			+"	77!~5&&&@&@@&&##&&&&@@@@@@@@@@#B57!7!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7!!!!!!!7!!!!!!!!!!!!!!77!J577Y!^!??7YY77!7??7777777777?JJJ555P55P5YPG######BBGGB#BGBBBBBBGGPPPP555555YYYJY5YYYYYJJ??7???J7?PPGGG\n"
			+"	7!!!~#@@@@@###B##&&&@@@@@@@@@@&BGG?~!~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7!!!!!!!77!!JP5GPGP!!7!7!J?Y5!!?7J5B577777??777777777??JJJ?JYJY5Y5PPG#B##########BBGGGGBBBGPGGP5P5YYYJJJYYYYJ?J???????7??YPGBB5\n"
			+"	!!!!!!B@@@####BB#&&@@@@@@@@@@@@5YBP~..:^!!!!!!!!!!!!!!!!!!!!!!!!!!!7!!!777?Y5YJ7!!!JP5~:^~JBBY!!!7!?PG7~7Y577J^^!~^:^~!7777777?J?7JYJYY5YJYY55GGB#########BBBBBBBBBGGGPPPPYJYYJ???JJ???JYYJ?7??J5555PGP5\n"
			+"	!!!!77~P@@##&#BB##&@@@@@@@@@@@@@@##PG^::~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!JPBGGB#BBBB?^G5^JY^5GP#&!!7!!!7Y55PG^:~^^^....!.^77777777??JPBGBBBGGPYYYPPGBBBBBBBBB###B####BBBGGGGP5Y55YY5Y?7?JJJYY??JJ5P55YYP5PP\n"
			+"	7!!!!!~P@@#&BBB#&&&&@@@@@@@@@@@@#P&57::^::^~!!!!!!!!!!??Y5PG5J7!!!!~~?BBG7!J?5G#&7^P5~!~JG7JG&Y!!!!7!~!PY:.:7~.... :^:^^7777?77?7BBG5YPPGBBYJ555PGBBBBBBB######&&#BBBBBGGP5PP55JJJ?J5PPPP55Y55YP5YY55Y5P\n"
			+"	7?JP5YYB@@##B#&@&&#&@@@@@@@@@@@@B5JY:::::::::^~!!!!7~JB#&B###&&5!!!!!?#G~~5P^GBB#7~?J?!!J?7YYGY~!!!!!JJ?^...  ... . ...:77777????#GJ?Y5YPGB5?YYJJJ55PBB##BGB##########BBGGGGGPPPP555PGGGGP5PG555YY5YYJJ5\n"
			+"	JPG##&@@@@###&@&#G&@@@@@@@@@@@@@P&JY~:^^^!^^^^:::~!!~B&B7!5B#&&&#7!!!!JJ~7?~?P5GG!!!~JGY!~75JY!!!!^:.7~.^:  ..:. ....  :?7777??JY57?5G?YBPGGJJJ?????JYPB#BGGGGGBB##BBBBBBBBBGGGGPPGGPPP5YYJJ5YYP555YJJ?7\n"
			+"	G##PYPBB&&#&&&&BG#&@@@@@@@@@@@@&5@&P~~^^!5Y!!~^:^::^^PG?PG?##B&&@B~!!!!!?5Y?GGGPJ~~!!!PGGGBBP5~~7~      . :^..:.:.:^~~:!Y7!777777!?Y5JJPGGP5JJJJJJJJJYYYY5555YY5GBBGGGGGBBGGGGGGPPP5YJY5PPYJ5P5P5J??7777\n"
			+"	#@5~?#G!#&&&&#BG&&&@@@@@@@@B&@@BG@&G7~^:Y##Y7!~^^^:::~77??PBGGBB&&!!!!!J5GPYPGP!~!~!~::^7PG5PP?7::. .:~!^^~!!!???7??YGB5?7~^^^^7^:!5G5GGG5YJ????JJJJYYYYYYYJJYYYJYY55PGGGGPPPP55Y555YYJ??Y5Y55??J?777?77\n"
			+"	5P7!!?~?#&#BGB#&&&@@@&@@@@@GG@@G#@&BG^:^5#&BPJ7!~~^^^::~!5B#BBBG#P:...^!!7YGGBP~!!!~~~~~~!?PPPP!~!?Y5J7???JYP5P5GGGGGP5J:. .......:!JGGPPJ????77??JJJYYYYYYYYYYJJYY55555555YYYYYYYYJJJJ????77777???????7\n"
			+"	!?55GPP#####&@@&&&&@@G&@@@@@J#@P&@&G@J:^J&&&PYJ77!~^^^^::^Y#&&B#P.  . YBP5:...^^:::~!!!!!7!7PG5JGBY!:    :.^GP55GGGBP5Y!....:~:...::?GGBBP?J77??JJJYYYYYYYYYYYYJJJY555Y55555YYYJYYYYJJJJJ??777777???????\n"
			+"	~:7G#BGBB##&&&###&@@&7G@@@@@GP&G@@@P@#:^?#&#5!~^!!~~~^~~!~~?BGP&&5:.. .P##~ . .:~7!~~~^^::~~?P5PP5^   . .5PPPGPPPPPGP557.::?J!!....:~JGBPJJ777?YJJJYY5YYYYYYYYYJJYYYPP55555YYYYJJJJJYYJJJJ????7777??????\n"
			+"	55P?!5BBBBB#&&&@@@@@B~?G&@@@@PG#@@@G@&::~P&&P7!~~7??J?~^~~~!!7?PYPP!~..~#GG!::^~^^?JYYYPGY7~:75JY!~..  ~5PJ7??7?YP55PP5Y7^YG^~!.^^:~^:J55YY?77?JJYYYYYYYJYYYYYYJJJYY555555YYYYYJJJJJJJJJJJ??????????????\n"
			+"	BB&#Y^~G#####&@@@@@BYPY5&@@&@B5&@@@P@#5GB#&&#GPGPY?7~:..~?!^^~!5~Y@G~..~PBGBYY?YG!77^!?77JJ^ :P#GP5YYY?J?Y~.  .::7555555YB#^:~..^^~!^~YYYYYY!:~7?YYYYYYYJYYJJYYYJJYYY5Y55YYYYJJJ?????JJJJJ??????????????\n"
			+"pese a vuestros esfuerzos, la caida de la union fue inevitable; fue la propia gente quien decidio rendirse. Ya no hay quien detenga a los invasores \n");
		}else if(totalpoints<=750){
			message=("\n"
			+"	YY5555YYJJJJJJJJJJJJJYY55YYYYYYYYYYY55555555555555555555YYYYYYYYYYYYYYYYYYJJ5PPPGBBGGBPGBB#&##BBBBBB#BBBB#BGP5JJJJJJJJJJJYJJJJJJJJJJJJJJJJJJJJYYYJJJJJJJJJJ?????7!!~~!7?JYYYYYYYYYYYJJJYYJYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJJJJJJJ?JJJ??????????77!77!!~~~~~^~~~~^^~~~~~~~^^^~^^^^^::\n"
			+"	Y55YJ??J??JJJJJJJJJJJYYYYYYJYYYYYYY555555555555555555555YYYYYYYYYYYYYYYYYYYYYPGGGGBBB#BBGP5YB#BBB##GGBBBBGG5JYJ7?JJYJJJYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ?????7!!!!77?JJYYYYYYYYYYYJJYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJJJJ???????77???7!~~~~~^^^^^^^^^^~~~^^^~~^^^~^^^^^^^::\n"
			+"	P5YJ??JJ?JJ????JJJYYYYYYYYYYJJYYYY55Y5555555555555555555YYYYYYYYYYYYYYYYYYYYY5PGGGB#####BBG??PGGPP555YYJ??YJ?!~^!JYJJYYYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ??JJJ?????77!!!!77???JYYYYYY5YYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJJJJJJJJJJJ?????77777!!7!!!!!~~~^^^^^^^~~^^^^::^~~^^^^^::^^^:::\n"
			+"	PYJJJJ??JJJ??JYYYYYYYJJJJJJJJYYYY55555555555555555555555YYYYYYYYYYYYYYYYYYYYJY5PGBBBBB##BGG5GBB5??5PYYYJ7~7JJ?!^.:7YJJYY5JJJJJJJJJJJJJJJJJJJJJJJJJJJJ??????????77!!!!77?7??JYYYYYYYYYYJJJYYYYYYYYYYYYYYYYJJJJJJJJJJJJJJJJJ??JJ??777!!!!!!!!!!!!~~~~^^^^^^^~~^~~^:..::^^~~^:::::::::\n"
			+"	5YJJJJJ?JJ??J5YY5YJJJJJJJJYYYYYYYYYYYY555555555555555555YYYYYYYYYYYYYYYYYYYJJJYYYG#B#B##BGJ?PP55P555YJJ557!JJJY5Y~:7JJYJJJYYJ???JJJJJJJJJJJJJJJJJJJJJJJJ??????77!!!~~!77!7?JYYYYYYJYYYJJJYYYYYYYYYYYYYYYJJJJYJJJJJJJJJJJJ???J??77777777!!!~~~!!~~~~^~~~^^^^~~^^~^^:::..^^^::^^^::::\n"
			+"	5JJJJJJ???J5PP5Y5YJJ?JJJJYYY55YYYYYYYYYYYYYYYYY55555YYYYYYYYYYYYYYYYYYYYYYJJJJJJJ5GBB#&##GJ??J?JY5555YJYP5J7?JJYB5^~?YYJJJYYYYJJJJJJJJJJJJJJJJJJJJJJJ??????77777!!~~!!!!!!!77??JJJJJJJYYJJJJJJJJJJJJJJJYYJJJJJJJJJJJJ?JJJJ?????7777???77!!!~!!!!~~^^~~~~^^^^^~~~~~~^^:..^^::^^:::..\n"
			+"	YJJJJJJJ?JPGPP5YYYJJ???JJYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJYJJJJJJJJJJYPGG##PY??JJJ?JJYYJ??JYY5J?JJJJY!!?JJJJJJJJJJJJYYJJJJJJJJJJJJJJJJJJ??777777!!77!!~!!!~!!~^^~!!!77??JJJJJJJJJJJJJJJ?JJJJJJJJJJJJJJJJ?????????7???????7777!!!~~^^^^^^^~~~^^^^~~^^^^^::.:^~::^^^:::.\n"
			+"	YJJYJJYYYPGGPPP5Y55YJYYYJJJJJYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJJJ?JJ??J??BBY7????????JJ?JJ????JJ?JJJJ?7?JYJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ??77777777777!!!!!!~~~~~~~!!~!77??JJJJ?JJJJ?JJJ???JJJJJJJJJJJ???????????????????777777!!~~^^^^::^^^^^^^:::::^^::...:^^^^^^^:::\n"
			+"	JJYYYY5PGPGGGGP5555YYYJ??JJJJJJJYYJJJYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYJJJJJJJJ??J??JJ????PB57?J????77?J??????JJJJ?JJ?JJJJJJJJJYJJJJYJJJJJJJJJJJJJJJJJJJ???777777777!!!!!!!!!!~~~~~~!!!!77?JJJJJJJJJ?JJJ?????JJJJJJJ????777???JJJJJJ??????77777!!~~~^^:::::::::::...:::::::...^~^^^:::.\n"
			+"	55PPPPPPPGGGGGPPP5YYYJJ??????JJJJJJJJYYYYYYYYYYYYYYYYYYYYYJJJJYJJJJJJJJJJJ?JJ????????YGPY?????????????JYYJ?JJ??JJJJJJJJJJJJJYYJJJJYJJJJJJJJJJJJJJJJJ????7777777!!!!!!!!!!!!!!!!!!~~!!77?????????????????????????????77???JJJJJJJJ???7777777!!!~~~~^^^:::::..:::::^^^:::.. :~^^:::::\n"
			+"	5555PPPPPPGGGG55YYJJJ?????????JJJJJJJJYYYYYYYYYYYYYYYYYYYJJJJJJJJJJJJJJJJJJJ????????5GBP7!Y?J???????YPB##B57?JJJJJJJYYYJJJYYJJJYYYYYYYJJJJJJJJJJJJJJ????77777777777777!!!!!!!!~~~~~~!77?????????????????????7?????????????????????77!!!!!!!!!!~~~~~^^^^::::::::::^^^::. ..:::^^^:::\n"
			+"	555PPPPPPP555YYYYYJJJJJ????J??JJJJJJJJJYYYYYYYYYYYYYYYYYJYYYJJJJYYJJJJJJJJJJ?JJ??JYYG#BBPJ??J?????JPB###&B#GJ?JJJJYYYYYYYYYYY5YYJYYYYYYYYJJJJJJJJJJJ?????7777777777777!!!!!!~~~~^~^~~!7???7777??????????????777?????????????????77!!!~~~!~~~~~~~^^^^^^^::::^^^::::::::........:::::\n"
			+"	PPP5PPPP55YYYYYYYYJJJJJJJJJJJJJJJJJJJJJYYYYYYYYYYYYYYYYYYYYYJJJYYYJJJJJJJJJJ?JJ?JPBB#B#BY?J???JJ?JG####BBBB&P?JJYYYYYYYYYYYYPGJJYYYYYYYYJJYJJJJJJJJJ?????777?777777777!!!!!!!~~~~^^^^~!77?7!!!7?????????????????????????????77777!!!!!!!!!!!!!~~^^^^^^^::::::::::::::^^^^. ........\n"
			+"	PP55P555YY55JJYY5YJJYYJJJJJJJJJJJJJJJYYYYYY55YYYYYYYJJJJYJJJJJJYJJJJJJJJJJJJJJJJ5BB&&G#GJJJJJJ?JJ5##BBBBGB5JYJJYYYYYYYYYYYJ5BBBJJJJJYJJYYJJJJJJJJ??????7??77?77777777777!!!!!!~~~~^^^~!77???7777??????????????7777????????777777!!!!!!!!!!!!!!~~^^^^^~~^::...::..::^^~~~^:...::::..\n"
			+"	PPP555555YP5J5YYYYYYYYYYJJJJJJJYYYYJJJJJY555YYYYYYYYJJYYYJJJYYJJJ?JYYJJJJJJJJY?PGGB#G5#PJYYJJJYJJP#GGGGP5YJJJYYYYYYYYYYYYYYG#BBB5PBBB5?YPP5P55555YYPGG5J77?77??77777777777!!!!!~~~~~~~!!77777????????????????777777??????777777!7!!!!!!!!!!!!!~~~^^^^^~^:....:....:^^^^~^^:....:...\n"
			+"	55555555YPPYY55YYYYYYYYYYJJYYYYYYYYJJJY55YYYYJJYYYYYJJJYJJYYJJJJ?JYYJ?JJJJYJJJ5BBB##55#PJYYYYYJJ5B#GPGBPJPPJYYYYYYYYYYYYYYJP####&&&##@####B###BBB##BGBBB57??????77777777777!!!!!!~~!!!!!7????????????????????????????????77777!!!!!!!!!!!!!~~~!!~~~~~~~^~~^:..:....:^^^^^^:::......\n"
			+"	55555555PG5Y5Y5Y55YYYYYYYYYJYYYYYYJJJJY5Y?JYJJYYJJJJJYYYJJJJJJJJJYYJJJJJJJJYJ5BBB#&#5PBPYPGGGBPY5&&#BBGPG@BYYYYYYYYYYYYYY55G#&#&&#BBB##B####&&#BBB#BBY5P57?J7??????777777777!!!!!~!!!!7?????????????????????????????????77777777!!!!!!!!!~~~~~~!!~~~~~^^~~~:::::..::^^^^::::...... \n"
			+"	5PPPP55PGP55555Y5YYY5YYYYYYJYJJJJJJJJYYJJJYJJJJYJYYYYJJJJJJJJJJY5YYJJJJJYJJYY5#####&##BGGGPPGB#BG#&&&###BP5YYY5YY5YYYYY5PGBBB&@&&#BB#BBBBB##BBGGGBBGGJ75P????????????77777777!!!!!77??????J???????JJ????????????????77??77777777!!!!!~~~~~~~~~~~~~~~^^^~~~~^:::...::^^^^::::.......\n"
			+"	5PP5PP5GP555Y5Y555555YYYYYJYJJJJ??JJY5YJJYYYJJJYYYYYJJJJJJJJJYYYJJJJJJJYYJJYY5BB######BB#&#B####&&##&&##B5YGGYJYYYYY5Y5GBBB###&##&&###B&&#GGB#BBBBGGGPJ7???????????????7777777!!!777777?JJJJJJJJJ?JJ????77777??????7777???77777!!!!!!!!!~~~~!!!!~~~~~~^~~~~^^^:.  .:^:::::.........\n"
			+"	5P5555GG5555PYYPYY5YYYYYYYYYJYY??JY5YYYYJYJJYYYYYYYYYYYYYY5YYJJJJJJJYYYYYYYYY5B###&&&#B#&&@&&&&###B#&##BBBB#GJ77YYYY5GBBGBBB###BBGGPPGPPBGPB##B##BGGGBB5???????????????777777777777????JJJJJJJJ??777777777777777777777777777!!!!!!!!!!!!!!!!!!!!!~~~~~^^^~~~^^.  ..................\n"
			+"	5PPP5PB555PGBP5P5Y5Y555YYJY55GPJJY55YJYYY5JYJJYYYYYYYY555YYYYYYYYYY55YJYPG5YYYY5G#BGBBB&@&&&&####BB##BP5PPPGP5?^!5YPBBGGP5PB&&BGGPPPPGPGBGPG#BGGGGBBG5J?????????????????777777??????JJ??JJJ????77!!!77!!!!!7777777777777777777777777777!!!!!!!!!!!~~~~~~~~~~~~:..:::::.....::::::::\n"
			+"	YPPPPBPP55PGP5YP5555YJ555YYY5P5JJ5PP5Y55Y5?YYYYYYYYY5555YYYY55YYYYY55YY5PP5YJYYYYYJJJBPG#&&#########BG5Y55J!JGBG7?5GBBGP5PPGB#BBBGGGGBB#GGB#&##BGGBG5JJJ???????????????????777???JJ??7?????777!!!!!!!!!!!!!!!77777777777777777777777777!!!!!!!!!!!~~~~~~~~~~~~~^^~~~^:::::::^~^::::\n"
			+"	GPPGGP5GP5PGP55PP5555YGG555555Y??5PP5YYYY5?YYYYY55555YY5Y55555YYYYYYYYYYYYYYYYJYJJYJYB5YG@&&&&###&##B#BGPPGJ7YB#5!JPGBP55PPPPB&#######&&###&&#&&GPPY????????????????????JJJ?????????777777!!!~~~~!!!!!!!!!!!!777777777?777777777!77777777!7!!7!!!!!!!!!!!!!!~~~~~~~~~^^^^^:::^^^^^^\n"
			+"	GGGP55BGGPPGBG5PGPY5PBBBGP55P5YY555555YYY5J5YJYYP55YYYYJYGBBPYYY5YYYYY55YYYYYJY55YYJYGY5B&&&&&&&&&######BBBGYJP##55GGPPGPPGBB######&&#&####BGB&#5?J?JJJJJJJ???JJ????????JJ??????7777777777!!!!~!!!!!!!!!!!!777777777???????777777777777777777!!7!!!!!!!!!!!!!!!!!~~~~~^^::..:^^^^::\n"
			+"	GGBGPPPPGPPGBBBGGBBP5GPPPGGPP5Y5PPPP5P55P5YYYYY5555YYY5PGGGGP5Y5555YYYYG5YYYYYPB#G5YYGPG#&#&&&&&&&&&####BPY55Y5BB#GGGGBBB#&&###&#&&&&#&#BG5Y5P##PJJ?JJJJJJJJJJJJJJJ?????J????77777777!!!!7!!!!!~~~~~~~!!!!!77!777??????????777777777???77?7777?7!~~!!!!!!!!!!!!~~~~^^::::.  .:^^^^^\n"
			+"	BBBBPJJ5PPGGGGGBBBBGPPGPGGGGGY5PGP5PGGPPG5Y55Y55J5YY55YGBPPP5Y555P5Y55YGPYYYY5BBBBGGGGB##&&&&&&&&####BBBB##B###&###&###BB&#BBB##B#&&##GBBBBPY5JJJJJJJJJJJJJJJJJJJJJJ?????????777777777!!!!!7777!!!!!!!777777777????7???????777777777777777?77JJ~!!!~~!!!!!!!!!!~~^^:..:^^:...:^~~~~\n"
			+"	BB##BGGGPPGBBGGBBBGGBGGGBBBGP5PGP5PPPP5PGGYPP55YJ5J555Y55YPP5Y5JY55Y55YPPYJY55J5PP5PGB###&&#####BB#&#GGB#&&#&&&&#&&#####&####B##BB##&#&&#BPJJJJJJJJJJJJJJJJJ?JJJ????????????7777777777777!777777777777777?77777??777777777777777777777777777J5?!!!!!!!!!!!!!!!!~~~~^^::::::::^~~!!!\n"
			+"	BB#BBBBGBBBBBGGBBBBBGP55YPGGPGGGGPG5PPGPP5J5GPYYP5P55PP5555PYJYY5P55P5Y5GY?YPPJJ55YYG###&&#BBB##&&#BB######&&&##&&&&&&######BBBBBBB##BBB5YYJJJJJJJJJJJJJJ????????????????????77777777777777777777777???777???7???7777777!7777777777??777777JP5?7777777777!!!!77!!77!!!~^^^^^^^~~~!!\n"
			+"	B##BB#BGBBBBGGGGBBBGPGBP55GBBGBGGBP5PGGPGBPYPP55P5PPGGBG555PY?7PGGPPPP5GB5J555Y5PP55###&&##BB#&&&####&&##BGGB#&&&&&#BB##GBBGPGGPGGGGPJJJJJJJJJJJJJJJJ??77????????????????????77!!!!!!!!7777777777????????????77777777!??7!77!777777777777!7PB5?7777777777!!!!7777777!~~^^~~~~~~~~~!\n"
			+"	BB#BGBBGBBBBBGGGBBGBBGBGPPBBBB#BGP5PGBGPGB55P5PPPGPGBB##BGGGGP5GPGPPG5BGGP5PPP5YYJYG##&&#GGB###&&&&@@&&&&&###&&&#BGGGGBBGBGPPPPPGGGPYJJJJJ??JJJJ????77?????Y5GGY7~7??7????7??7!!!~~!!!!!7777777777???????7777?7!!!7!!!?JJ!~!!7777777777777JGGPY7?777777!!!!!!!!!!!!~~~^^^^^^~!!~~!!\n"
			+"	BB#BGGGBBBBBBGB#BGGPPGGGBBBBBGGGGBGGBGBBBGJPPPPPGGPPGBBBGPGBGGPGGGGGGGGPGPYY55YYY5P###&#BB#&###&&&&&&&&&&&&&&&&#G5PGGGGGGBGPPPPPGGG5?JJJ?JJ????????????????PBGPP5Y77?????77777!!!!!!!!!!JJ7!77J7777??777777??J7!~!!!!!!JPY7777777777777?7JPGPP5J?!!!!!!!!!!77777!!!!~!!!!!!!!!!!!!!\n"
			+"	B##BGPGGGBBGP##BPGG5J5BBBBBBGG5YGBBBBBBBGG5BGPGGGP55PBBGGPBBGPPGGGGGBGPGGGGP55PPPG#####BB#&&#&&&&@@&&##&&&&&###BG5PGGGBGGGPPPGPPGGPJ?J???JJ????????????7???Y5PPPPGY???7????777777777777!5#5!~JG?777777?777?J?!!~~~~~~~!JPPP?!77777777??775GGP555YJ?77!!77777777!!!!!7!!77!!!!!!!!!!\n"
			+"	BB###BBBPPGBB#BBBBBBPYBBGBBGBBGGGGGGBBBGGBGBPPPPPGGPPB#BGGGBGGPPBBGPGGP55G#&BGB######&&#&&&#&&&&&@&###&&&&&####BPPPGGGBBBGPPGGGGGG5J??JJ??????????????JY5PGGGGGG##5?7??77?7777?7777777775#BP5GPJ??J???7YYJ7~~~~~~~~~~!~?PPGGJ?7!7777777!YGGGG5YYYP555J777777777777777!7777!!!!7!!!!\n"
			+"	PG###BBB##BGB###BBBG#PGGPPGBBGGGGGGGBBGGBBBBG5YYGBBB##BGPGPPGBBGPPGPYYYY5GBB######&&&&####&&&&##&&#B#&@&&#&#B##GPGGGGGGBBPPPPPGGGPY?????????????77?JY5555PBBBGPPY7J???J555YJ7777777!777YBBBGPPPYJPG5YJ?P57!!!7!!~~~~!!!7JPGGGP5YYJJJ?777PGGGP5Y55P55Y?!77!!7?777!!7777777!!!!!!!!~~\n"
			+"	PPPG#&#####GPBGGB###BGGGPPGBGPPP5PBBGBBBBBBBBGP5BBGB##BBGBPGGGGGGPGPYY5GPB#B#######&BB#&&&&@&&&&###&&&&&&###B##BGBGBGGGBGPPGGGGBG5J?JJ??JJY5YYYY55PGBGPPGGBBBP55J!!YJJ5GGBGPY??JJJJJY5G#####GPPPPY??7!7P57~!!~~~~~~~!!!7YPPGGPPGGPGGPYJ5PGBGPPPPPGGY7!!!!77777!!~~!!!!!77!!!!!!!~~~\n"
			+"	PPPPG####&#BB#BBBGGP55G##BBBBBBGGGGG5Y5GBGGBBBB#######BGGBBGGP5PBBG55PP55G###############BGBB#&##&&@&&&#&&#BB###GBGGGBGGGGGGGGGBGP55YY5YYY5GGGPPPGBBBBGGPGGBBBGP5Y5GG55GG#GGPPPPGGGGB#######BGPGB?~??7!5P?!????7777?JJJJYP5GPPPPPGPPP55GGGGGGGPPGGY7!7?77!!~~~~~~~~~~~!!!!!~!!!~~!!\n"
			+"	GGGGGG#&###BBBGGGGPP5PPGB#BB#BBBBBBB#G5PBGGBBBB######BBBB#BGBB5YBBPY55YPB###&&#&&&#B##BGPPPGB####&&#&&&&&&&##B##BBGGGGGGGBGGBBG#PYPGPY5PYYPGPGP5PGB#B#BBBBGPPGBGGGB#BGGGBB#BGGGPP5PB####BB##BGGG5YJ???JPPJ7!!!!!7?J?77!!JG5PGGPPPPP5PPPGY5GBGPGGGJ7?YJ!~!!~~!!!!~~~~~!~!!!!!7!!!!!7\n"
			+"	GGGBBGB####BBBBBBBBGGBGGBBBBBBBGGBBBBBGPG5PB#BBB######BGGB#BGPPG#G5555PG###&&&&&&&&#BBBBGGGB####&&#B&&&&#&&&#####BGGGBBBGGBBBB#BYY5PPG5J5PGGGP5Y5B##BBBBGGBGGBBBBB#BGGPP#BGBGY77!?G###&####B##GGP5?~!~7YPJ~~!!!!!~!!!!!!7GGPPGPPPPPP5?JY~7GBGPP5JJ?7!!~!!!~!77!!~~!!!!!!!7!7!777777\n"
			+"	PGGGGB##&&&#B###BBBBBGBBBBBGBBBGGGGBGBBP5PB####B########BBB#BB##BBPPGB#B#&####&&&####BBB#########&BB&&&&&&&##&&#&BBBBBBBBBBBBBB5PY!7PGBP5Y5BGGPPG###BB##GGBBGB##BB##BBBB#G?7!!~~7G##&#######B##BBGJ~!!!YGY~!!!!!!!!!!!!~7YGGGGPPPPPY77J?~7JJ?7!~~~~~~!!~!!!!!7!!!!!77!??!!J7??J????\n"
			+"	BBBBBB#####B##BBBBBBGGGGBBBBBBBBGGGBBBBBGGGB##############BB############&#####&&&####BBGBB###&##&#B&#B#&&#&&##&&&###BBBBBBB##BBB5?~!YGGPP55PGPPG########BBBBBBBBBBBGB##BB?~~!!!?B&##&&###BBBBB#BP?~~!!~?55?7~!!!!!7!~~~~!?PGG55PYJ?!!JY~!~~~~~~~!~~~~!!~!77???JJJJJJJJJYJJYJJJJ????\n"
			+"	BBBBB##########BBBBGGGGGB#BBBBBBBBBBBBGGBBBGG##B#####################B#&######&&&&&#####B####&&###&#PG#&##&&&#####&####BBB####B57!^~J5BBBGGGGPG########BBBB######G5?JPGPY???JPGB#######BBG#BYJY~7?7!!777YPY?!77?J?777~~!7?5P?!77!77!7JJ!!!!77?Y5J???7??JYPGP5???????????777????JJJJ\n"
			+"	BBBBBBB#####BGBBGBBBBGGBB##BBBBBBBBBBBBBBBGGGB###################BBBP5GBB###&&&&&&&&#######&&&&###&BG##BB&BB&&####&&&&#####&&#YJJ!~JP5BGB#BGBGB############BBBBBGBBBP5YYY5PGB########BGGB#GY7~:7JYYJ5GPJPGYJJY??7?JJJJ?77?5?!7?JYYYJ55?????YPGG5???????775BB5777???7?J??77?JJ?JJJJJ\n"
			+"	BBBBBBB####BGGBBGPPGBBB####BBBBBGGGGBB##BGGBBBBBBB###############PPPPPG########&&&&&BB###&#&&&&####GGGGPG###&##&&&&######BB##PJP5PGPGPB######B#########BBBBBBB####B##BGP5GG##BBB#BGGBBB#BBP5555J?7~!J5Y?5G5?!?77!7Y7!7?J?J57?JJ???7JYJ!7777YG5?777????7?7JPBGPJJJJJJJJJJJJJJJJJJJJJ\n"
			+"	BBBBBB#BBBBBBBBBBBBB#BB####BBBBBBBBBBB#BBB#BBBBBBB#############BB5PGGGG#&&&&&&#&&&&&#######&&&###@&GPPPPGBB#&##&&&######BBBPPBG##BGPPG#&#########BB#####BB#BBBGGB#######BBBB#BB#BGBB#BBBBGPPGPGP5JYGGP5YP5J77?J?77J?!!!777?7?777777YJ77?77JGBY!7!JYYY5????YPPGY?JJJJJJJJJJ???JJ?JJJ\n"
			+"	GGBBB####BBBBBBBBBGGGGBB#########BBBB###BB#BGPPPPGB#B###B######BBPPGGBP#&&&&&&&&&&&&#####&&&&####BGPPGGGGB#&&&&&&####&&#BB&&#&BB#BP?7B#&#################BBGGBBBGB#####BB##BB####BB##BB#BPPGGBBG5Y5GGGG55YY555P555YY?7777!?7777777J5?77777PBPY!77PGP5PGJ7JPBGG5???77777?JJJ?JYYYY55\n"
			+"	BGGB#####B##BBB#BBGBBBBB########BBBBBBB#BBBBP55PPGBBBB##G###B##BPPPPGGGB&&&&&&&&&&#####&&&&&&##&#PPGPGGGGBB############BBB#####BGY?7Y&##################BBBBBBBBGGBBBBGG#######&&#######BPPPG##GPPPPPGP5Y5YY5P55J7???77777??77777?5Y???7?YBBGPY7?5GGGGBYJPBBBB5?????7??JYYYYY55Y555\n"
			+"	BBB####BBBBBBBBBBGGBBBBB##BB##BBBBBBBBB#BBBBGGGGGB#BB#####B##BP55GGPGGBB&&&&&##&&&####&&&&&&&&##&BBBPGPPGGGGB###&##########BGPGGPP5G&#&&############BB###BBGGGBBBGGBBGPG#&&&&##########BBGPPGBBBBBBGPG555YJJGG55Y?5?7???JYY5JJYYYYG555P5Y5BBBGP55PGBBBGPBBBBBGY??JJYJYYYYYYYYYY5555\n"
			+"	GB#######BBBB#BBGGBBBGBBB#BB#####BBBBBBBB##BGGGGGB#BB#####BBGPPGBBBGGBB##&&&&&&&#######&&##&&##&&#GPPPGPPPGGBB####B##&&&&##BPPB#GPB&#&&#############BBBBBBBB#####BB#BGB####&&####&####BGGGPPGGGGBBBBBP5PPPGGGG5555PP555555PGP55PPPPPPPPPP5PBBBGBBBBBBGGPBBBBB5YYYYYYYYYY5YYYY555555\n"
			+"	G###B##B#BBBB##BGGGGBBGB##B#BBB###BB#BBBB###BBGBB#BBB#####BBBGG#BB#BGBBB##&&&&###&&&#B#&&#&&###&#BGPPGGGGGBGBB####B#&&#&##BGGB#B5B&##########BB#BBGGBBBBB##&&#####BBBBB###########B####5PPPGGBBBBBGGPYPPGBGGP5PPPGGP5PPGGPGGGPPPGBGP55PPPPGPGBBBBBGBBBBBB##BG555555YYYYYYY555555555\n"
			+"	###B######BB###BBGGBBBB#####BBB########BB###BBBB###B#####B#BGGBBBB#BGGGB###&&&#####BB##&&&&&##&&&#BBBGGGGGBBGBB#B###&&#&&#BBB#B5P#&##############BGGBBBB#######BBBBBGGB#####BB##B#BBBBB5PPGBBBBBBBGPPPPBGGGPBPPPYYGGPPGPYY5GGGPPGBG5JYPPPPBPPGB#BGGGBBBBB#BG5555Y555YYY55Y555555555\n"
			+"	#BB############BBBBBGGGB####B################B#############BGG#BG##GPBBBBB#&&&&#BGPG##&##&&&&#&&&####BBGP55PPGBB##&&&&&&#B###BB5B#################BBBBB#########BBBGGB#&#########BBBBBGP5PPGBBBGGBGGPPGGGBGPB5Y5555PPPPP5P5GGGGGGBGG55PPPGBP55G#BBGGBBBBBBGP55555555555555555PPGP55\n"
			+"	#BB######BB######BBBBBBB####B########BB######B###############BBBGGBGPBBGGB##&&&&&###&&###&##&&#&&&#BBBBGPPPP55PGGP#&&#######BBB5B#################BBBB#######BBB######&###########BGB##GPPPPGBBGBBBGPPBGGGBGP55PPYJGPGPPPPGGBPPGGBBBGGG5YGP55PG#BBBBBBBBB#G55555555P55555555PP55PPP\n"
			+"	BB#################BB###################B####B################BBB#BBGBGGBGG#&&&#&&#B###B#######&&&##BBBBBBBGGGGGGJ?JG###&#BGBGGB#B####&###########BBBB##B###BBBB######&###########BB##GPPGGGGBBGGBGGPJGBGGBGP55PP55G5PGP5PPGP??5PGGGGGGP5GPPPGB#B#BBBB###BG5P55PPPPP555P555PGP55PPP\n"
			+"	BB##################BBB#####B###########B####B#&#####BBBB#####BBBGGGBBGBP??G&&&&&#BBBBBBBBBB#######&&&#B######BBGP57!?P##BGGBGB##BB###&###########BBBB##BB##GBB##B####################PPGBBGGGGGGBGGGYPGGGGGGP5PPGGPYPGGPPPPPYY5PPGGGGPPGPGGBB###BBBBBBB#BP55P5PPPGGPP555PPGGPPPPPP\n"
			+"	BB#################BBBB#############BBB########&####BBGGGB#######BGGG#GGP55P&&#&&#B#BBBBBBB#B#####&&&&&&#######BGBGBBY~5BBGBGGB##BBB##&##BB###B##BBBB####B##B##########BB##########&#BPP#BBBBGGGGGGGPGGGGPGGGGPPPPPP5PPGGGGPPGGGPGGGGGPPGPPGB#BBBBBBGBBB##BP5PP55P55P555PPGGGPPPPGG\n"
			+"	BBBB################BBB######BBB####B##B####&BB#&&#BBBBBBB#######BBBBBBBPPPPBG5PGB#BBBB#BBBBPYYPGB##&&##BBBB####B#BB#BYPBBGGGBB#BBB######BG####BBBBBBBB##########B##BB############&#GPPPBBGBBBGGGGGGGP5PPGGGPPPPP555PPPPPGGGPPGPGGGGGGGPGGBB#BBBBBBBGBBBGB#BGGGP555PP555PGGGGPPGGGG\n"
			+"	PGGBBB##########BB##BGGB####B##BBBB####BB###&BGB##&#BGBBBBB####BBBBBBGBBBBGGP5Y555B#BBBBBGBBP5J7?5BB###BBBBBBB####BBBBGBGGPPB#BB#B###&##&GG&B#BBBBBBBGGBB#BB###BBBBBBBBB###########BP5PPGBGBBBGGGGGGPGGGPPGGPPPPP5Y5GPPPPPPGGPPGGGGGGGGGGGGGGBBGB#BBBBBBBBGGGPGGPPP5YGGPPPPGBGBBGGP\n"
			+"	PPGBGGGBBBBB#BB#BGB#BBGBBBBBPBBGBBPB###BB####BBBBBBBBBBBBB#####BBBBBBBBPGBBBBBGBGBBBBGGBPYYBBBGGPPGBBBBBBBBBBBB#BGG#BPBBGBPPB#BBB##BB#BBBPP#BBBBBGBBBGGGGGGGGGGGGGGGGBBGB########&#BBBGGBBGBBGGGGGGGPGGP55PPGG55PP55PPPPPPPPPPGGGGGPGGGGBBBGGBBBB##BBBBBBGGPGGGGGPPG55PPPPPPBBB##GP\n"
			+"	PGGPGGGGBBGGBBGBBBGGGBBGPPPP5PGGGB#BGB#GG#BB#BBB##BGGBBBBGB###BBBBBBGBBPGBBBBBBB##BBBBGGG5?GBBBBBBBB#BBBBBBBBBB#GGGBB#BBBB5P#BBBBGPGBBBBP5PBGBBBGGBBBGGGBBBBGGGGGGGGGGBGBB########BBBBBBBGGGGGBBGGGGGPPPPP55P55PPPPPGGPPPPGPPPPPPGGPPPGB#####B###BBBBBBBBGGGPGGGGPPGGPPPPPPGBBB##BG\n"
			+"	PGGGGGGGBGPPGBBGGGGGPGGGPPPPPJ5PGGGBPGBGGBBBGPGBBBBBGGBBBGBBBBBGBGBBGBGGBBBBGB#BBBBBGBBGGPJ5BBBBBBBBBBBBBBGGBBBBGBBBBGBB#GPG#BBB#GGGBBBBPPPGGGGGGGGBBGGGGGGGGGGPGGGGGGGGGG###BB###BGGGGGGPGGGGGGGPPPGPP55555PPPPPPPGGGGGP5PGPPPPPGPPPPPGB#####BGGB##BBBBBBBBGPGGGGGGGPPGGPPGG#BBBBB\n"
			+"	GGGBBBBBBBBBBGBGPPGGGPY5PPPPP5P555JY55PP5GBGPPGGBGPPGBBBBBBGPPP55GGPGBGBPP5Y555P5GB5G5PGGPP55PGBBGGBBGGGBB55GGGBGG5YYPBBGPGBBBBGGGPGB#GPGPPGGGGGBBGGGBGGGGGGGBP5PGGPPGGGGG#&#BB##BGGGGGGGGGGGGGGPPPPPPP55555PPPPPPGP5PGBB5Y5PPPGPGPPPPPPG####BG5PB##BBBBB###BPGGGGBGGGPGGPPPPGBBBB#\n"
			+"	BBBBB##########BGGBBGGGPPPPPGGGPPPPP5PGGPGGGPGBPP5555PPG5PP5YY5JJYPGG#BB5YJ?JYJY5BBY5Y5PPGG5??JY5YYP5YY5GPP5GGGGGGJ?P#BBPPP555YYYYYY5G5GP55PPPPGGP5GGPPGGBGPGGPPGGGGGPPGGG#&#BB#PGGGGGGPGGPPGGGPPPPPPPPPPPP5PPPPPGGP5PGBBGPPP55PP5P555PP5PB##BGPPGBGGGBB###BBGGGGGGGGGGGPPPPPPB#B##\n"
			+"	PPPPPGGPP555PPGBBBBGGGGGGGGGGGGGPPPPPPGGGGGGBBBBGGBBGPPPP5PGP5P5PPPGBBGGPYYY55YYYPGYJ?Y5GGGGYJJJYY5JYYYYP5JPGBGGGB5YG#BGP5YYYJ?JJ?JYJJ5PYJJYJ55555YYYYYY55JJ5PPPPPGPPGG5YGBP55GPY5GGGGGPPPPPPPPPPPPP55PPPP55PPGGGBBGGPGBBBBBBGPPG555555555G##BBPPPGPPGGG#BBBGGGGGGGGGGGGGGPPPPGGB##\n"
			+"	#BGPPPPP5P5YY5PGGGGGPPPPPPP5PPPGGP555PGGGGGBBBGGBBBBBBBBBPPGBBGPBGGGBBBBBGPGP55?JYY55?7YGGGGPYY55J5G5JJYY?PGBPPGG5JYGBBGPP5YJ7???JYJ?JJJYJ???JYYJJ??YYJ??YJJJYYJJYYYYJ?J5P5555PYY5GGPPPPPPPPP55555555555PPP5PGBBBBBB#BBGB##GGBBGGBG5Y5P555PGB#BP5PPPPGPGBBBBGGGGGGGGGGGGPPPPPP5PGBB\n"
			+"	############&#BBBBBBBBGGGGGPPPPPP5555GGGPPGBGPGGGGGGGGBBBBBGGB#GBBBBGBGBBBBGGBBPGGPPGPJPGGPGPY5G5JYG5YYYP5BBB##BGPJYPBB5YPGPYYYPPY55YJ7Y5YJYY?JJ??JJ?!!J5JYYYJ???Y5J?J5YJ5GPP555Y5P555YJYYYYYJJYYYJJJY5PGGGGBBBBBBBB#GGBB#BGGBBBG5GB5Y5GGBGBGGGP5GGBG5PGGBBBBGPPGGGPPPPPPPPPP55PPGB\n"
			+"	######&&&&&&#BGB##BBBBBGGGGG&&#BGPGBGGBGPB#GPGGGGGGGGGGGBBGGGGGPPPGGGGGBGPGPGGGGPGGPGGPPPPPG55GG5Y5GPP5555GBGBBBP5P5Y5BGYJ5GPYJ55YY5Y?5YYY??5YYJ???JYJ77?5PJYY??JJY??JJJ55GGGPPP55YYYY5YJJJJJJ5YJYY5PBBBGGBBBBGGGGBBBBGGBBGGPPPGBPPGGGGGGBGBBBBBGGGB#BBBBB##BGGGGGGGGBBBBB#BBBBBBBB\n"
			+"	##BBBBBBB##&#BBBBBBBBGPGPPPGBGGGGPPBBGGGGBGPGBGGBBBBB####BGGBGGGGGBGPGBPPGGGGGGGPPGGPGPPPP5P5PPPG55555555555PP555JPGGPYJJ5G5JJY?55555YG5J5P5Y5Y??JJ?J?7!?55JYJ?????77JYYY5555YYYYY5PGPGPG5JJJ5G55PGGPPPBGGB#BBGGGGGGGBGGGBBGPPPGGBPBBGBBPBBBBBBBBBB######BBBBB#BGGP5PGGGGGGBB#&#BBB\n"
			+"	B####BBGPGBBBBBBBBGGBGGPGGGPGGGGGGGGGGGGBBGGBBBBBBBBBBBBGPPGGGGBBBBGPGGPPPGGGGYPGGGPGGPPGPYY55Y5GG5YJJYYJYP555555PPPGGPY?PBG55YYYY55PPPP5PP5YYJ77??7??77?JJJJJJ?7J?77?77YJJ??JJJJJJYYYYPG5JJJ5P55YYY5PGGGGPPGPGBBPYB#GGBGGPY5GBBGBGGGBBBPJGBGGGPGGGGGGGBGGGGGBBGGGGPPGP5PPYJJYPG###\n"
			+"	G#######GB###BBGGGGGGGGGGGGGGGGGGBBGGGGBBBBB#BBBBGGGGGGGGGGGPGGBGGGGGGPGGPPGGGPGBGGBPGGPPPP55PPPGPP5Y5555PPPGGGPPGBGGPPP5GBGPP55PGGGGBGPPPPG5Y5YJJJJYYJJYYYYYYYYJYYY5J?JJYYJ???JJYJY5Y?JYYYY5P5YYJYGG5PGP55GBBBBBP5BGGBG55PPPGGGGGB5PBBBPYPPPPPPPGGGGBBGGGGGGGBGGGGGGGGGGP5PPY??J5G\n"
			+"	########B##BB####BGPBBGGBGGGBBBBBBBGGGBBBBBBBBBBBBBBBGGGGGGGGGPPGGGGGBGGBBGGGGGGGGGGGPPGGPPPGBBGPPGGGGGGGGPP55PGP5PP5PGPGPPGPPPGGGGGGGGBPPGBG555YY5P55YYY5YY5YY555Y5P55YY5YJ???JJYJJYJ7!?JJJJYY?JJY55PGGPPPGPGGPGGPGPGBBPPGGGGBBB#PGBBBBPGGBBP5P5555PGBBBBBBBG##BBGGGGPGGGGPG5JJY55\n"
			+"	&&###&&###BBBB########BBGGGBG#PPB#BBGGBBBBBBBBBBBBBBBGGGBGGBGGGGGGGGGGGBBGGGGGGGGGGGGGPGGGGGGBBGGGGGBGGGGGGGGGPPGPPGGPPPPPPPPPPPPGPPGPGBGPGGBPP555PPY5YYY555P55PP5P5P5JY5YY5YYYYY5YJYY75YJJYYYYJY5555555YJ5PPP5PPPPPGGGGGGBGGBGPGGGBBBGGGBGBBGBBGGGGB###B##BBBBB##BGGGGGGGGGGGGGPP5\n"
			+"	B###&&#&&&####BBBBB#BBBBBBBBB#GPB#BBBBBBGPGBBBBBBBBBGGGGBGGGGGGGBBGGGBGGGGGGGGGGGGGGGGGPPGBBGBGGGGGGGGGGGGGGGBGGGGGGGGGGGGGPPPPP5PPPPGGGBGPGGGPPPPPPPPP5Y5PPPPPPP55P5?JPPY555555YPYY5PPP55555YY555555YYY55555P555P5PPGPGGGP5PPPPPPGGGPGGPBBGB##BBBB####B#&#####B###BBBBBBGBBBGGGGP5\n"
			+"	B###&&&&&&#######BBBGGBBBBBBBGGBBB#&####BB#BGGGGBBGGGGGGGGGGBBBBBBGGGBGGGGGGGGGGGGGGGGGGGGGGBBGGGBGGGGGBGGPGGGBBBBBBGGGBGGGBGGGGP5PPPGGGGGGGGGGPPPPPPPPP55GPPPPPPP5PPPPP5555555PYP55PPPP55555Y555YY5555PPP5555555P5P555555PGPPPPPPPPPPGG5BG55GG5PGBBGG#BBBB#######BB#BBBBBBBBBBBBBB\n"
			+"	&&###&&&&&#######BBBBGGB##B#BBBBBBB####&&####GGGPPGBBBGGBGGGGBGGGBBBBBGGGGGGGGGGGGGGGGGGGGGBBBBBBBGBBGGGGGGPPPGB#BGGGGBBBBBBBBBGGGGGPPGGGGGGGGGPPPPPGPGP5PPPPP55PPG5P55PPPPPPPGPPGP5GGPP5555Y555555YYPPPPPP555YY55P55YYYJ5PPPPGPPPPPGPPGPPPP5555PPPPPGGGGBBBB##BB##BBBBBBBBBBBBBGGG\n"
			+"	&&&&&&&&&&########BBBBBB##BB####BB#BBB###########BB#BBB##GBBBGBBGBBBBBBBBGGGGGGGGGGGGGGGGGBBBBB###BGGBBBBBBBBGGGBBGGGBBBBBBBBBBBBBBBGPPGGBGGBGGGGGGPGGGGPPPPPPP55PGPPPGBPPPPGGGPGGPPPPPPPPPPPPPPPP5YYPP55555555555P555555555PPPPP5PP55PGPPP55555555PPP55GBBPPPPGB##BGGGGBBB#BBGGGGG\n"
			+"	&&&&&&&&&&######################B###BB###BBB#BBB#####B#&#B#&#B##BB##BBBBBBGBBBBBBBGGGBBBGBBBB##BB######BBBBBBBBBBBBGGBBBBBBBBBBBBBBBBBGGB#BBBBGGGGGGGGGGGGGGPPPP5PGGGGBGPPPPGGPPPGP5PPPPPPPPPPPPPPPPPPP555YY55PPGP5PPPPPP555Y5PPPP55PP5555555555555P55PP555PPPPPPPPPPP5PPPGBGGGGGGG\n"
			+"	&&&&&&&&&&&&&########################B#####BBBBB#BBB#BB######B###########BBBBBBB#BBBBBBBBBB####BBB##&##BB##BBBBB#BB###BBBBBBBBBBBBBBBBBBBB###BBGGGGBBBGGGBBGGGGGGGGGGGGGGGPPGGGPPGPPPPPPPPPPPPPPPGGGPGGGPPPP555PGGGGBGPGBPGG555YYJJYY555555555555555P55PP55P5555555PPPPPGGGGPGBGGGB\n"
			+"	&&&&&&&&&&&&&&&&&&&#####################B##BBBBGBBB####BBB#BB##B###&&&#######BB####B###BBB###BBBBBB##############B####BBBBBBBBBBBGGBBBBBBBBBBBB##BBGGGGBBGGGBBBGGGGGGGGGGGGGGGGPPGPGGGPGGPPPPPGGGGPPPPGGPPPP55PPGGBBBBBBGPP5YYJJJYYY55YY5P55555PP5PP5555555P555P555P555PP5PGPGGGGPG\n"
			+"	&&&&&&&&&&&&&&&&&&&&&########################BBB#BBB#&#####BB##BBBBB##########BB##############B#BBB#BBB##B##############BBBBBBBBBBGGGGGGGBBBBBB###BBBGGBBGGBBBBBBGGGGGGGGGGGGPPPPPGGGGGGGGGGGPPPPPPPP5PPP5PGGGGGGBBGPPGGGPPPPPYY55YYY5PPPPP5PP55YJ55Y55Y5PPPP5PPPPPPPPPPPPPPPPGPPPP\n"
			+" gracias a vuestra ayuda los ejercitos de la union lograron reorganizarse y lanzar una contraofensiva que les permitio llevar la ventaja sobre los invasores. Aunque esta sera una guerra larga gracias a tu ayuda se salvaran miles de vidas\n");
		}else if(totalpoints<=1000){
			message=("\n"
			+"	&&&##############BBBBBGGP555PPGGGGGGB##BBBBBBBBBBBBBBBBGGGGGGGGGGGGGGGGGBGGGGBBBBBBBBBBBBBBBBBBBBBBBB################BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##################BBBBBBGGPPPPPP55555PP5555PPPPPPPGGGGBBB#################BBBBBBBBBB###########################\n"
			+"	#################BBBBGGGPP5PPPPPPPPGBBBB#BBBBBBBBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBGGBBBBBBBBBBB#################BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB###########################BBGPPP55PP555PGP5555555PPPPGGGBBBB#################BBGGGGGGGBB##########################\n"
			+"	################BBBBBGGP55555PP55PPGBBBB#BGBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGPGGGGGGGGGGGGPPGBBGGBBBBBBBB##################BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB########################B#BBGGPP555PPPPGP5555PP555PPGGGBBB###################BBGGPPPPGGGBBBBBBB###################\n"
			+"	####BBB##########BBGGPPP55555PPPPPGGBBBB#BBBBBBBBBGGGGGGGGGGGGGGGGGGPPG5J5GGGGGGGGGGGGP5?PBGGGGBBBBBBB########PB#######BBBBBBBBBBBBBBBBGGGGGGGGGBGGGGGBBBBBBBBBBBBBBBBBBB#################B#BBBGPPPP555GG55555PPPPPGGGBBBB##################BBBBGGPPPPPGGGGGGGBBB##BBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBB###BBBBGGPPP55555555PPGGGBBBBBBBBBBBBBGGGGGGGGGGGGGGGGGGPGY!5PPPGGGGGGGGGPPPP7JGGGGGBBBBBBB#######JG######BBBBBBBBBBBBBBGGGGGGGGGGGGBGGGGGGGBBBBBBGGGGBBB#B##BB#################BBBBGGPPP5Y5B55555PPGGGGBBBBB#############BBBBBBBBBBBGGPPPPPGGGGGGGB#####BBGBGGGGBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBGGPPPPPPPP5555PGGBBGBB#BBBBBBBBBBBBGGGGGGGGGGGGPPPP^7PPPPGGGGGGGGGPPPB?:5PGGGGBBBBBB#######JG######BBBBBBBBBBBGGGGGGGGGGGGGGGBGGGGGGGGGGGGGGGGBBB####GPBBBB################BBBGGGGP55GG5PPPGGGBBBBB##############BBBBBBBBBBBBBBGGGGGGGGGGGGB########BBGGGGGGGGGB\n"
			+"	BBBBBBBGGBBBBBBBBBGGGGPPPPPGPP5555PPGGGBB#BBBBBBBBBBBBGGGGGGGGGGGGGPPJ.755PPPGGGGGGGGPPGG7.?PPGGBBBBBBBB#####B5B#######BBBBBBBBBGGGGGGGGGGGGPPGGGGGGGGGGGGGGGGBBBBBBBBBBYY##BBBBBBBBB####GG############BB&PPPGBBBBBB#################BBBBBBBBBBBBBBBBGGGGGGGGB###########BBGGGGGGGG\n"
			+"	BBGB###BBGGBBBBGGGGGGGGGGGGGGPP555PPGGBBBBBBB##BGBBBBBGGGGGGGGGGGGPPPY::Y5PPPPGGGPPPP5PGY..JPPGGBBBBBBBB#####BB########BBBBBBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGPBBBBBBBBBBG5P#&#BBBBBB##B#B#PG##BBBBBBBBB##&&BGGBBBBBBB####################BBBBBBBBBBBBBBGGGGGB################BBBGGGG\n"
			+"	BGB######BBBBBBGGGGGGGGGGGGPPPPPPPPPPGBBBBBBBB#BBBBBBGGGGGGGGGGGGGPPPP7.:7Y55PPPPPP555P?: ~PPGGGBBBBBBBB###B#B#######BBBBBBBBBBBBGGGGGGGGGGGGGGGGGGGGGPGGGG5GBBBBBBBBBPY5G####BBBBBB#BBBPP#B#BBBBBBBGGGB####BBB#BBBBBBBBBB################BBBBBBBBBGGBGGGGB#####################BBG\n"
			+"	GB#########BGGGGGGGGGGGPPPPPPPPPPPPPGGGGBB#BBB#BGBBBBGGGGGGGGGGGGGPPPP5?..:!?JYYYYYJ7J~ .75PPGGBBBBBBBBB####BBBB#B###BBBBBBBBBBBBBGGGGGGGGGGGGGGGGGGGGGGGG5YGBBGBBBGBP5PGB#####BBBBBBBP5GB###B##BGBBBGGGBB###B####BBBBBBBBBBB###########BBBBBBBBGGGBB#BGB##########################\n"
			+"	BB##########BBGGGGGGGPPPPPPPPPPPPGGGGGGGGBBGBB#BBBGGGGGGGGGGGGGGGGGPPPPPY7:  ..:::...~^~YPPPGGGBBBBBBBB##########B#BBBBBBBBBBBBGGBGGGGGGGGGGGGGGGGGGGGBBG55GB#BBBBBGBGP5PBBB###BBBBBBBPGBB######BGGBBBGBBB#########B#BBBBBBBBBBBBBBBBBBBBBBBBGBBBGB#&##############################\n"
			+"	#############BBGBBGGGPPPPPPPPPPPPPPGGPGGGGGBBBBBGGBGGGGGGGGGGGGGGGGGPPPP5P5J?!^^^^^~JYYPPPGGGGBBBBBBBBB############BBBBBBBBBBGGBGBGGGGGGGGGGGGGGGGGGGGGGBPPGB##BBBBBBGP5PBB######BBB##PYGPG#####GGBBBBBBBBB########BBBBBBBBBBBBBBBB#BBBBBBBGBGGGB##&&##############################\n"
			+"	############BBBBB#BPPPPP555PPPPPPPGPPPGGGGBBGGBBGBBBBBBBBGGGGGGGGGGGGGGGPPPPPPPP55PPGPPPPGGGGGBBBBBBBBB###########BBBBBBBBBB##BBPGGBBBGGGGGGGGGGGGGGGGBGG55P###BBBBGBBP5PB#BGGBBBBP&#BP5PYP#####BBBGBBBBBBBBB################BBBBBBBBBBBBGGBBBBB###&&##############################\n"
			+"	#########BBBBBBBB##PPPP55PP5PPPPPGGGGGGGGGGGGBBBBBGBBGGBBBBGGGGGGGGGGGGGGGGGGGGGPPGGGGGGGGGGGBBBBBBBBBB#############BBBBBBB###&BGGGBBGGBGGBBGGBBGGGGGGGBP5PB####BBBBBBPGGB##GGBGB#P&BBP5PGB######BBGGGBBBBBBB#########&#######BBBBBBBBBBBBBBBB#####&###############################\n"
			+"	#########BBBBBBBB##GPPPP5PPPPPPPGGGGGGGGGGGBBBBBGGGGGGGGBGGGGGGGBBGGGGGGGGGGGGGGGGBBGGGGGGBBBBBBBBBBBBBB######B########BBB##B##GBBBBBBBBGBBGGB#BGGGGGGGGGP5G####BBBGP55BBBGY5GGGBBG#BBGGGGGB#&&#BGGBBBBBBBBBBBB###############BBBBBBBBBBBBBB######&################################\n"
			+"	#######BBBBBBBBBBB#BGGGPPPPPPPPPPGGGGGPPGGGGGGBBGGGGGGGGBBBBBBGGGGGGGGGGGGGGGGGGGBBBGGGGGBBBBBBBBBBBBBBBBB####BB##########BB##BBBBBBBBBBBBGBB#BGGBGGGGGGGBGPGBB#B######BPPP5GP5GBBG#GPGBBBB####BBGGBBBGBBBBBBBBBBB#########&&#BBBBB###############&&###############################\n"
			+"	#######BBBBBBBBBBB#BGGGPPPPP55PPPPPPGGGGGGGBGGBBGPGGGGB##BBBBBGGGGGGGGGGBGGGGGGGGBBGGGGBBBBBBBBBBBBBBBBBBB###BB#GB########BB##BBBBBBBBBGGGB##BBGGBGGGGGGGGGGGGGGBB###&##GPPPG5YB&#G##GB#BGBBBBBB#BGGGBGGBBBBBBBBBBBBBBB####&#&#BB#################&###########################BBBBB\n"
			+"	#########BBBBBBBBB##GGGGGGGPPP555PPPPGGGGGGBBGBBP5PGBB#BBGBBBBGGGGGGGGGGBGBGGBGGBBGGGBBBBBBBBGBBBBBBB##BBB##B#BPGB#######BBBBBBBBBBBBBBBBGB#BGGGGGGGGGGGGGGGGG55B#######BGGPGP5B##GB##B#BGGGBB##BGGGGGBGBBBBBBBBBBBBBBB###&####################BB##BB#################BBBBBBBBBBBBB\n"
			+"	######B##BBBBBBBBB##BBBGGGGPPPPPPPPPPGGGGGGBBBBBGGPPGBBBGBGBBPGBBGGPPPGGBBGGGBBGBGBGBBBBBBBBBBPGBBBBBBBBB###B#G5##B######BBBBBBBBBBBBBBBBGB#GGGGGGGGGGGGGGGPPGPY5G#BGGGGBBBBBPPB##GG#&####BBBB##BGGGGGGGBBBBBBBBBBBBBBB######################BBBB#BBB###############BBBBBBBBBBBBBGG\n"
			+"	######B##BBBBBBBB###BBBBGGPPPP55PGPPPPPGBBGGGB#BGBBGGBGGBGPBBPGGGBBBBGGPGBGBPPBBBBGGBBBBBBBBBGGBBBBBBBBBB##BB#G5B&#BBBBBBBBBBBBBBBBBBBBGGGB#BGGGGGGGGGGPPPPPPPP5PB#GGGGP5GB#BGB#BB##############BBBBGBBBBBBBBBBBBBBBBBBB#&#####&&############BBB##BB###BBBB#BB##BBBBBBBBBBBGGGGGGPP\n"
			+"	########BBBBBBBBBB##BBBBGGGGGGGP5PPGG555PGBGBBB#BBBBBBGBBBGGGGBBBBB###B#BBBBGPPGGBBBBBBGGBBBGB##BBBBBGBBB#BBBBPPB&#BBBBBBBBBBBBBBBBBBGBGGGBGGGGGGGPPPPPPPPPPPPP55B#GGPPGY5G#BBBB5PBB#&##GGGPGGGBBBBBBBBBGBBBBBBBBB####B##&#####&&########BBB#BGB&BB###BBBB###BBBBBBBBBBGGGGGGGGPPP5\n"
			+"	########BBBBBBBBBBB###BBBGGGGGGGGPGGGGGBGPGGGBBBBBGBBBBBBB####BBBB##BB#BBBB###BGGPGBBBBBBBBBBB##BBBBGPGBBBBBBBJG####BBBBBBBBBBBBBBGGBGGGGGBGGGGPPPPPPPPPPPPPP5PPJPBGPPGGYYPBGB##PYP#B#&GB5YYYYGB##BBBBBBBBBBB#################BGGBBBB####BBBBBBB&#BBBBBBBBBB###GGBBBGGGGPPPPPPPPPP5\n"
			+"	####BBB##BBBBBBBBBB###BGGGGGGGGGGBGPPGGGBBBGPPBBBGGBBBBBB#BBBB###BBBBBB#BBGBBBB###BGGGPGGGGBB##BBBBBB#BBB#BBBPYB##&&&##GBBBBBBBBBBBGPPPGGBGPPPPPPPPPPPPPPPPPP5P5YGB#BBPPYYYPGBBBG55GGBBGBGYY5G#####BBBBBBB#B##&########BG#####BGGGBBGBB###BBBB#&#BBBBBBBBBBBBB##BGGGGGPPPPPPPPP5555\n"
			+"	##########BBBBBBGBB##BBBGGGPGGGGPGGGGGGGGGGBBBGBBBBB#BBBBBBGGB#######BBBBBBBBBBBBB###BBPYY5GBBBBBBBBBBBBBBBBG5G####&&#BPGBBBBBBBBBGPP#BGGGPPPPPPPPPPPP555555PPGBGGBBBBP5PPPGB#BGGGGPGGGGPG5P#&####BBBBBBBB####BBBBBBB##GPB#B##BGGGBBB#BB###BBBB#BBBGGBGGGBGGGGGB#BGGPPPP55555555555\n"
			+"	######&&###BBBBBBBG##BBGBBBGBBBGGGGGGGGGGPGBBBBBB#BBBBGPPPYY55PB#BBBBB#BBBBBBBBBBBBBGGGGBGGBBBBBBBB#BBBGGGGGGBBB######BGGPGBBGGBGPPB#BGGBGPPPPPPP55555555555555GGGBBBB#BB##BB#GBBGBGGGGGPPG#######BBBBBBB##B#BP5PGGBBB#PPB#BB####BBBBBBBBB##GG#BGP5PGGGGBGGG55PPGBBPPPPP555Y5555555\n"
			+"	######&###########B###BBBB####BBBBBBGGBBGGGGBBB####BP55P5555PP5YPGB#BBBBBBBBBBBGGGGBBBPPBGGBBBGBBBB##BBBGGBB#BB######BGGGPGBGGGPPG##BPPBGPPPPPPPPP55555555555PPPPGGBBGGBBB###GG#BBGGGGGGGGB#B######BBBGBGBGBGG5PB##BBBBGGBBBBBBBBBBBBBGGBBB##GBBPYGGGGGGGPY55PPPPPG#B55P5YYY5555555\n"
			+"	############&&&&&###&&#############BBBBBBGGGBBB####BGGBGGBBBBBGP5PGBBBBBBBBBGGGBBG5GBGBBGGGGGGGGGGGBBBBGYYGBGGB######BBBBB#BGGPPB#BGGPGPPPBBBPPPP555PP555YYYY5PPPPPPP55PGGPB#BGB##BBBBBBBBGBBGB#B##BGBBBBGYGBBGBBGBBBBBBBGGGBGGGGGGGBGPGGGGB##BP5GGPGGGGPP5PPPPP5PPPBBPY5555YYYY555\n"
			+"	############&&&&&&&&&&&&&#####&&########BGGGBBBBBBB##BBBBBBBBBBBBGGBBBGGGGGGGGGGGBBGGGGGGGGGGGGGGGGGBBGG55GBGBB##B###BBBBBBGGGGBBGGGGGPGBBBGGGGGPPPPPPP55555PP55PGGGPPGGBGGBB#BBBB##BBBGGGGGBGGGGBBBBBBBBBGGB#BBGGGGBBGGGPPGGPPPPPPPP55PGGPGG&G5PGGPGGGGGPGGGPPPPPPGPGBGPY555YY555P\n"
			+"	############&&&&###&&&&&&&&&&&&&&&&##########BBBBGBBBBB#BBGGGGGGGBGPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBGGGGGBBBBB##BBBBBBGGGBBBGGGPPGBBGGGGGGP55555555555555555PPPPPPPPPGGGGBBBBBBBBGBBGGGGGBGPGGGGBBBB###BBBBBGGGPGGGGPPPPPPPP5555Y555PGGPP##PPPPGGGGGGGPGGGPPGP5PGP5GBG5YPPPP55P\n"
			+"	#####################&&&&&&&&&##&&#######&&##BB##GBBB##BBBGGPPGBGGGP55GGGGGGGGGGGGGGGGGGGGGGPPPGGPPGBGGGGGGGGGGGGBBBBBBBBBBBGGBGPGGBGGBGGGGP55PGGGGP5YYYYYYYYYYYYYJJJJJYY5555PGGBBGBBPPGGGGGGGGGGPPGBBBBBBGGGGGGBGPPPPPPPP55PPP55YYYJY555PGPPGP#P5PPGGPPGGPP5PPPPPPPGGGPBB#BBGPPP5P\n"
			+"	########################&&&&&&&&&&#&&&&&&&&&&#B##BBBB##BGGGBBGGGBGGGPPPGGGGGGGGBBGGGGGGGGGGPPPPPPPPPPGGGGGGGGGGGGGBBBBBBBBBBGGGGGGGGPPPPPPGGGGGGPP555YYYYJJYYJJJJJJJ?J???YYJY555PPGGGP5PPPPGGGGPPGGGGGGGGGGGGPGPPPGPPPPPPPP55P5555YYY55555PGG5YB#PPPP55GGGPP55PPPPPPBBBBBB###BGPGGP\n"
			+"	###########BBBB##########&#&&&&&&&&&&&&&&&&########B####B#BBGGPGGPPPP55PPPGGGPB#GGGGGGGGGPPPPPPPPPPP5PGGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPPGGGGGGGGGPP555YYJJJJJJJJ???JJ??7???JYY5YJJ5PPGGP5PPPPPPPPPGGGGPPPPPPPPPPPPPPPPPPPPPPP555Y5555Y5Y5P5PB#555#BPP5PGGGPPPPPPPPPPGBB##BB#BB#BBBBB\n"
			+"	##########BBBBBBBBBBB######&&&&&&&&&&&&&&&&######&&######BBBBPPP5PP55555YYYY5GBP55PPGPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGPPPPPPPPGGPPPPPPPPPPGPPPPGGP5555YYJJJJJJJ??????J?????JJYYYY55PPPP55PPPPPPPPPPPPPPPP5555PPP5Y55555PPPP55555YYPP5Y55PPPBBGPPG#BPYPBP5PPPPPPPGGGB########B######\n"
			+"	#######BBBBBBBBBBB##B##BB#####&&&&&&&&&&&##&&&###&&&######B#BBPPPPPPPPPP555YGB5JYJYYY5PPPPPPPPPPPPGGGGGGGGGGGGGGPPPPPPPPPPPPPPPP5Y55PP5PPPPPPGPPPP5YYYYYYYJJJJJJJJJJJJ????J??JJY5555555P55PPPP5555PPPPPP55555PPP5YY55555PPP5Y55YYY5PGPPPPPP#PBBGPGB#PPGGGGGGGGGGGB####BB##########&\n"
			+"	####BBB########BBBBBBB#BB#B###&&&&&&&&#####&##&&&#&&########B##BBBBBGGPPGP5G#5YY555YJJYY5555PPPPPPPGGGGGGGGGGGGPPPPPPPPPP55PPPPPPPPGPPPPPPPPPPPPP555YYYYYYYJJJJJJJJJYJJJJJJJJJJJJJJYYY55555555555555P5P55555555P55555555PPP55YYY5555GBGBGPBBPGGGGGG#BGGBBBBBGGGGB##&##B##########&&\n"
			+"	####BBBBBBBBBB##BBBBBB#BB#B####&&&&&&############&&##########&&#BBBBGGGGGPB#G5555555YYJJJJJJJYY5555PPPPGGGGGGGGPPPPPPPPPP55555PPPPBPPPPP555555555555YYYYYYYJJJJJJJJJJJJJJJJJJJJJJJJYYYYYYYYY555555555YYY55555P55555555PPPPP5P55P55PGGBBBBB#GGPGPGPPG##GGGBBGBGGBBB####B#########&&#\n"
			+"	#####BBBBBBBBB##BBBBBB#BB#BB##################&&&&#######&###BBBBB#BBBBBB##BGBGP5JY5PP5YYYYYYY55Y555555PPPPGGGGGPPPPPPPP5PPP5PPPPBP555555555555555YYYYYYYYJJJJJJJJJ????JJJJJJJJJJJJJJJYYJYY55YYY5555YYYYYY5555555YY55PPPPP5G5Y55PPPBBBBGB##BGGGGGGGPB##BBBBGBGGB###&###############\n"
			+"	####BBBBBBBBBBB####BBB##BB#B#####B############&&######BB###BBBBBB##BBBBB##GGGBBGP5PG##BGP55555PPP5555555555PPPGGGPPPPPPPPPP555PPGG555555555555YYYYYYYYYYYJJJJJ??????777???????JJ?????JJYYYYJJJYY55YYY5P5Y555PPP555YY55PGP55PG5PPPBBBBBBB#&####B#BB#BB#&#BBGBGBBB#&&&&&&############\n"
			+"	###################BBB##BB#######B############################BBBBBBBGB##GGGGBBBB#######BGGGGGGGGPPPPPPPP55PPPPPGGGGPPPPPPPPP5PGB5P5PPP555555YYJJJY5YYYYJJJJJ?????777777???????????JJJJJJYYJJY5555PP555P5P55PP55Y55555PPPP55GGGGGBBBBBB#&&##&#####&####&#BBBBB####&&&#######&&&&&&&\n"
			+"	###################BBB##BB######BB##############BBBBBBB#########BBGGGB##GPGPGGBBBBBBB#&####BBBBBBBGPPPPPPPPPPPPPGGGGGGGGPP5PP5PGP5P55P555555YYYYYYYYYYYYJJJJJ??????77777?????????JJJJJJJJYYYY55PGGPP5555PBGGP555YY55P555PPP55PGGGGBGBB##&#B#####&#&&####&#############B#####&&&####\n"
			+"	BBBB####BB##B#B#####BBB####B#B################BBBBBBBB###########BBB###GGGGGGGGGGBBBBB######BGGGGGGGGGGGPPGGGPPPGGGGGGGGPP555YP5YY55PP555555555YYYYYYYJJJJJJ???????????????????JJJYYJJJYY5P5PPPPPPPP55PBBBBBBGGPPPPPPGGGGGGGGBBBBGGB####&################&&####&###########&&&&&###\n"
			+"	BBBBBBBBBBBBBB#####BBBB##BBB#BB################BBBBBBB###############BBBBB#BBBGGBBBBBBGB###BGGGPPGBBBBGGBBBBGGGGGGGGGGGGGGPG5PGP5555PGGGGGPPPP5555YYYYJJJJJJJ???????????????JJY555PPPGGGGGGGGGGGGGGGPGGBB#B#BBBBBPPGGGGGGGGBBB###BGGBBB#&#&#&######BBBBB#&&####&##########&&&&&&&##\n"
			+"	###BBBBBGBBBBB########B#BBB##B#######################BBBBB#########&#BB#######BBB##BB#BBBBBBBBBGPPPGGGGGBBBBBGGGGBBBBBGGGGGGGBGGPPPPPPGGGGGGGGGGGGPPPP55YYYYYYYYYYYYYYY5555GGGGGGGGBGBBBBBBBBBBBBBBBBBBBBBBBGGGBGGGGPGGGBBBBBBBBBBBBBGB########B#&##BBBBB##&B#########&&&&&&&&&&&&&\n"
			+"	####BBBBBBBBBB########BBBBB##BBB#######################BBB##############################BBBB###BBGGGPPPPP5GBGGBBBBGBBBBGGGGGBBGGGGGPP5PPPPPPPPPPPPGGGGGBGGPPP5555PPPPPPGBBBBGGGGPPPPPPPGGBGGGGBBBBBBBBBBGGBBBBGGGBBGBBBBBBB##BB####BBB##BBB##BBB##BB##BBB#B#&##B####&&#&&&&&&&&&&##\n"
			+"	########################BB###BBBBBBB####B############BBBB###################################&&&&####BGP5PPGGGB#BBBBBGBBGGGGBGPPGGGP5YYYYY55YYYYYYYYYYYYYYYY55YYYYYYYYYYYY55PPPP55P555PPPPGPGGGGBBBBBBGGGGGGBGGGGBBBBBBBBBGBB########BB###BBBB#########BBB#B#&&########&&&&&&&&&&&&#\n"
			+"	&&&##########################BBBBBBB##BB#############################################################BGGGB#BBBBBBB##BGGGBGGBGGGGGGP55PP5555YJJJJJ????????????J?????????????JYYYY55555PPPPPPPPGPGGGGGGGGGGGGBGGBBBB#BBBBBBBB###############BB#######&##BB#####&&#######&&&&&&&&&&&&&\n"
			+"	&&&###########&&&&&############BBBBB#BBB################################################B###########BBBBB##B##BBBBB###BBGGBBBBBGBBBBGGGGGGPP55YYJY?!7??77!!7?JJ????JJJJJ?JJYYY5555PPPPGPGGGGPGGGGGGGGGGGGGBBBBBBBB#BBBB######################################&&&###&&&&&&&&&&&&&&&&\n"
			+"	&&&&&####&&&&&&&&&&&#############BB###########&&##&&&&&&&##########################BBBBBBB#############B##B####BBB######BBGGGGGGGGGGGGGGGGGGPP55YJ7~~7?7?77?JJJJYYY55YY5YY555PPPGGGGGGGGGGGGGGGGGGGBGGGGBBGB#BBGGBB#BBBB###################################&&##&####&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&#########################&&&&&&&&############################BBBB#####BBBBBBBBBBB####B###########BGGPP5PPPPPGGGGGGGGGPPP555YYYYY5YY555555PPPPPPPGPGGGBGGBBBBGGGGPGGGGPPGGGBGGGBGGGGGBBBBBBBBB############################&&&&&&&##&&&#&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&###########&######&&&&&&&&&##&##################################BBBBGGGBBBB#######BBBB#######GGPGGGPPGGGGGGGGBGGGGGGGGGPGGGGGGGGGGGGGGGGGBBBBBBGBBBBGGGGGGGGGPPGGPPGGGGGPGGGGB#BBB#B########B###################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#############################BBBBBBGBGBBGBGGBBBBBBBBGGGGGBBBB####B####BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPGGGGGGGGGGGGGGGGGGPPPPGGPPPGGPPGGPPPPPGGB###BBBB#########BBBBB##&######BB##&&&&&&&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&####&&&#################B#BBBBBBBGBBBBBBGGGGBBBBBGBBBBBBBBBBBB######BBBBBGGGGGPPPPPPPPPPPPPPPGPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGP55PPPPGPPPPPGGB#############BBBBGBBB########BBB&&&&&&&#&&&######&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&###################BBBBBBBBBBBBBBBBBBBGGBBBBBBBB###BBBBBGGGGGGPPPPPPP5555555555PP555PPPPPPP55PPPGGGGPPGGGBBGBBGGPPPPPPPPGGGPGPGGGBB######BBB#BBBBGPGBB#BB#######&&############&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&########################BBBBBBBBBBBBBBBBBBBBBBBBBBBBB##BBBBGGGGGPPGPPP555555555555555PPPPPPPPPPPPPPPGGBBBBGGGGGGGGGGGGGGGBGGGGGGGGGBB###BBBBBBBBBGGGBB#BBBB#BBB#####BB####BB######&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#########################BBBBBBBBBGGBBBGGGGGGBBBBBBBBBBBBBBBBGGGGGGPBP555P5P55555PPPP555555PPPPPPPPPGGBBGGGGGGGGGGGGBBBBBBGGGGGGGGGGGBBB##BBBBBBBBGGGGGGGGGGGGPPPB##BBGG##BBBBBGGPGB##&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&###########################BBBBBBBBBGGGGBGGGGGGBBBBBBBBBBBBBGGGGGGPP5JJYY?!?J555P555PPPPPPPPPPPPPPGGBBGGGGGGGGBGGGGGBBBBBBBBGBBGGGGGGGBB####BBBB##B###BBGGGGGGGGPPPPGGBBGGB####BBGGGGGGB#&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&######################BBBB######BBB#BBBBBBBBBBBBBBBBBBBGBBBBBGGGGGGGPP555555YJJ5PPGP555PPGGGGGGGGGGBBGGGGGGBBBBBBBBBBBBBBBBGGGBBBBBBGGGGBBBBBGGGGGBBB#BBBBBBBBBBGBBGGGGGBBBGGB###BBB##BGGB#####&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#########################BB########B########BBB#BBBBBBBBBBBBBBBBBGGGGGGGGGGPPGGGGPPGP55PPGGGGGGBBBBBBGGGGGGGGGGGGGGBBBBBBBBBBGGGGGGGGGGGGGPPPGPPPPGGGBBBBBGB#B######BBBBBBB##BBBBGGGB##BGGG##########&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&########################################BBBBBBB##BBBB######BBBBBBBBBGGGGGGGGGGGGGGGBGGGGGBBBBGGGGGBBGGGGGGGPGGGGGGGGBBBGGGGGGGGGPPPPPPPGGPPPGGGPGGGGGGGGGGGBB###########BBB#####BGGGGGBGGGBBB##B###B##&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#######################&&&##############BBBBB##################BBB##BBGGGGGGGGGGGGBBBGBBBBBBBBGGGGGGGGGPPGGGGGGGGGBBBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBGBBBBBB##############BBBBBBBBBBB##############&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&###################&&#####################################BBBBBBBBB#########BBBBBBBBBBGGGGGGGGBBBBBBBBBBBBBBBBBBGGGGGGGGGGGGPGGGGGGBBB####BBBBBBBBBBBBBBGGBBBGGGGBBBBBBBBBBBBB###########BBGGBG5PGGPGGB###&&&###########&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&######################################################BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGBBBBBBBBBBBBBBBBBBBBBGGGGGGGGGGPGGGGGGGGGGGBBBBBBBBBBBBBBBBBGGBBBBGGBBBBBBBBBBBBBB###########BGGPP5PPP55PGGB#&&&&&&&#&&&&&&&&&&&&\n"
			+"	###&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#####################################################BBBB#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGGGGGBBBBBBBBBBB########BBBBBGGGGGPPPPPP55555555PPGBBBBGGGGGGGGGGGGBBBBBBBBBBBBBBBB################BB#BP5GBBBBGGGB#&&&&&&&&&&&&&&&&&&&&\n"
			+"	#&#&&&&&&&&&&&########&&&&&&##################################################B#####B##################BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB############BB##BBGGPP5555YJ????JJY5PPGGGGGGGGGGGGGGBBBBBBBBBBBB###########################BBGB#&&#####&&&&&&&&&&&&&&&&&&&&\n"
			+"	#######&&&&###########################################################################BBBBBBBBB#####BBBBBBBBBBBBBBBBBBBBB######BBBBBBBBBBBBBBB#########################BGGPYYJJ?7?77!!!77?JYYY5PGGGGGGGGGGGGGBBBBBBB#BBBB######################&&&#####&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	#########################################################################################################BBBBBBBBBBBBBBBB############################################BBBBGGGGGGGGGPGGGGGGGGGBBBBBBBGGGGGGGGGGGGGBBBB##########################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	################################################################################################################################################################################################BBBBBBGGGGBBBBBBBBBB#######################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	#############################################################################################################################################################################################BBBBBBBBBB#BBBBBBBBBBBB#BB################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"gracias a vuestra ayuda los ejercitos desorganizados de los invasores estan siendo facilmente cazados.\n" 
			+"Los ejercitos invasores estan resguardados en sus fronteras.\n" 
			+"ya solamente es cuestion de tiempo que los derrotemos\n"
			+"¡LARGA VIDA A LOS HEROES! ¡LARGA VIDA A LA UNION!\n");
		}else if(totalpoints>1000){
			message=("\n"
				+".....::.................:::::::::::::::::::::::::::::::::::::::::::^:~^::^^^^^^^^^^^^^^^^^^^^^^^:::::::::::......    .....................................:::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^:::::::::.....           ..........P@&&&&&&&&&&&&&&&&&\n"
				+".............................................:::::::::::::::::::::::::^~::::::::::^^^^^^^^^^^^^^:::::::::::..........     ............................:::::::::::::::::::::::^^^^^^^:::^^^^^^^^^^^^^^^:::::^^:......................:...Y&&&&&&&#BBB&&&&&&\n"
				+"..........  ...     ...    ..................................::::::::.^7:::::::::::^^^^::::::::::::::::::::............  ................................::::::.::::::::::::::::::::::::^^^::^^^^^^^^^^^^^^^^:::::........::::::::::~!!J&&&&&&&&####&&####\n"
				+".............                  .........  .........................:.::7^.::::::::::::::::::::::::::^:::::::::::::..................................................:..::::::::::::::^^^::^::^^^^^^^^^^^^^:^^:^:^^::.~PGG^..:...::.^&@@@@&&&##B###&&######\n"
																+"..  ...............:...!^.::::::::::::::::::::::^^^^^^^::::::::.........................   ........ ..................:..:::::::...::::::::::::^^^^^^~^^~!!^:^^:~#P:.J@&@5:....:::.:&@&&&&&##BB#B#####BB##\n"
																 +".  ................:..^~.....::::::::::::::::^^^^^^^^^:::::::::.........................   ....... ..............:^.....::.....::::.::^^^:^::::::^^^^:J&&&B^^^::~G#~.77#&BB5^.::..:&@&&&BGGGGPGPGPPPP55PP\n"
															 +"...........::::::::^::::..^!...:::::::::::..::::^^^^^^^^^^^^^^^^:^^:::::::.::::::::..........   .... ................::::.^^^^^:::^^^^^^^^^^^^^^^^^::::^^:?@&&&^^^^:^:?#J.J&&&&&&J^....&@&#PP5555555P55PPPPGG\n"
													+".  ... .............:..::^^^^::::^^:~.:::::.::::::...:...:::^^^^^^^^^^^^:::^^::::::::::^:^^^^:::::....^.    ....  ..:^:.::::::^^^^^~~~~~^^~~~~^^~~~~~~~^^^^^^:^:^^^^7B&#GP7^:^^:^Y7P@&&&&BB&?J5Y#BBGGBGGGGGGGGBBB#####\n"
				+".  .....    .................    .....    ........:....::....^^::.:::^:.~::::::.:::.....:::::^^^^^^~~^^^^^^~^:::^:^^^^^~~~~~~~~^^^^^^^^^^:^::::::^^^:^^^^~^^^~^^^^~~~~^~~~~~~^^^^~~~~~~~~~~~^^^~~^^^^^^^7###&&&B7^^^^:Y#&&&&@B~&@&@@#B#BB##########&&&&&&#\n"
				+".........................:...      ...  . ... ...::.....:^:.:^::::::::^:~^:^::^^:::^^^^^^~~~~~~!!!!!!~~~~~~~~~~!!7!~!!!!!!~~~~~~~~~~~~~~~~~^^^^^^^^^^~~^^^~~~~^^~~~^^^^^~~~^^^:^^~!!!~~!~~~~~~~~^^^^^^^!&##&&&&&&?^~~~^J&&&&##YB@&@&&&&&&&&&&&&&&&&@&&&@&&\n"
				+"............................:.  .. ..........::.:::::.:..:.:::::::::::^^7~:^^^~~^~!~^~~!~~~~!~^~!!!!!~^^~~~~~~!!!!!~~7~!7!777!!!!~!!~~~~~~~^^^^^^^^^~~~^^^^^^!~^:^^^^^^~!~~~!~~^^^^^~~~~~~~~~~~~~^~^~^^G@&&&&&&&&&~~~~^!#@&#BBBGPGBP####BBBBBBGGBGBBBBGBBB\n"
				+".........::....::..^~^^:.:::^^::::::^::^:^^:^^:::^^.......::::~!!7?~^^:^J!^~^^~^^~^^^~!!~^^~~~~~~!!!~~~~~!!~!!!!!~!!~^^~7!!!!!~!~~~~~^~^~~^^^^^^^^^^~~~~^^^^^~~^:^~~!^^~~!~!~~^^^^^^^~~^^~~^^~~~~~~^~!^P@@&#&&&&&@5^~^^^B@@&@@@&&&&&##&&&&&&&#&&&&@&##P555\n"
				+"...:::::::::::::^::^~^^::::::^::^:^:^^^^:::::::::^^::::^^^^^^^7!JJ?^:~~!J!^~~!~^~~~!!7?7!7JJ!^~~~!77~!?7~~!!!777!!77!^^~77!!!!~!!~!!~~~~~~~^^^^^~~~~~~~~~^~~^^^^^~~^~~~~!!!!!~~~^^~^^^^^^~~^~~~~~~~~~^?&&@@&&&@@&@&~~~~5&&@&&@@@@&@@@&@@&&@&&&&&@&&@@&B5PP\n"
				+":::::::::::^::::^^^^^^::::^::^^:^:^^^^::.:::::^^^^^:::^^^^::^^!~JYY5YJ~^?7~~7~!~!!!!!7777!7?J?777?!7~!7!!!7!!7!??~7??7~!77777!!!!!!!!!~~^!~^~~~~^~~!~~~~~~^^^~~~~~~^~!~~!!!!~^~^^~~^^~^~~^~~~^^^^~~~!~7B&&&@&#&@&&@7~~^Y@#P&&&@@@@@@@@@@@@@@@@@@@&@@@@@B55\n"
				+"~^^^^^^^^^^::^^^^^^^~^^:.:^^:^~~~^~~~^:^:^^^^^^^^^^:^^^^::::^:~!?GGPPP~~~?5G5J!!!!7!!!~77??77JJJJJ77~~!777777?!7?!7??!~!??!!?!!!~7!~!~!!~!!~!~~!~!^^~~^^~~~^^^~!~^~^^~^^^~!~~!~~~~~~~~~~~~~~~~~~~~~~~~^!&&&&&&&&@@@#~^~^Y@B7@&@@@@@@&&&&&&&&&&&&#B#&&#BP5Y\n"
				+"~:::^^~~~^~::^^~^^^^^^^^^^~^^~~~^^^^::^~~~~~~^^~~^^:^^^:^:^^:^~7?YG5YP77555PPG577!!!?7?7!7??????JY?!777?J?JYYY?!7??77~!777~~!~!~^!~~!^~!!77!~!!~~^~!~!!~!7!!!!~~~^~^^~~^^~!^~7!~~~~~~~^~~~~~~~~~~~~~^~!^B&&&&&&&@@@@G!!~:5&#@&@@@&@@&&&&&&&&&&#BGGGGPP&BY5\n"
				+"~^^^^^~^^^^^~~~!~~~^~~~~~~~~^~~!^:^^^.::^^~~^^^~~::::::.^^^^^^~77J5YY5P5P55PJG&GJ77J???J???7??JYYYJYYJ!YY???J?J?7J???7!7??7~!~~!~!!77^~7!!?7!~^~^~~~!77!!7777!^~!~^^^^^~~~!!!7~~!^~~!~^~~~~~~~~~~~~~~~~^#&&@#&&&&&@@@@#^7P&&&&&&&&&&&&&&&&&####&&&&###&#55\n"
				+"!!~^^~!~^^!!!~!!!!!~~~^~~!~~!77!~?55YY?^^^~~~^^~~^:::~^^^^^:.^~?JYY?7YY5Y5Y?!?B&&#5JJ???JJJYJYJ?JYPPP5Y5YYJJJ????7??JY7!!7?~!!!?~~!!!~!!!!!~~~^^~~~^!!!!~!!7!!~!7~~^^^~^~~~~~!!~!^^~~~~~^^^^^~~^^^::...:B&@B&@&&&&&@&&&B&&&&&&&&&&&&&&&&&&&#####&##GP##BP5\n"
				+"5J~~~?7~~~!!!~!~!!!!~!!!~!!~7!7~~^55PY!^^~7!~~!!^~!^~Y7J7.^!!777?Y?7!7?7!7J!!?5PB#&&PJYJYJ??5PP5YJ?JY55P55YY57~~~!?J7J5J7!~!?7?!^^^~!!7!!7!7!!^^!7!^^~~~!!~777!!777!!~~~!7!~!~!!!^^~~~^~^::^^^^^^^::.:.~#&@^!&@@@@&&#&&&@@@&&&&&&&&&&&&&##BGGBBBGGGYYYY5GG\n"
				+"PY!!!7?^777!!777~~?7!!777!!~??77!^J5Y!7?!!7?77??77777J?7?7?JY?!!~!!~^!~^:^7!!J5YBG5G&@#55PPY55PGGPP5YJJ5P55P55?^~!~~?5Y5J!!7?7!!!!?7!77!77!!!!^^~7!!~!!!!?!777!!!7!~!~~!~7!!~~~~~~^^^^^:~^^^^^^^:^:^^::.B#&~:!&&&@&#B&&&@&&&&&&&&&&&##&#&&#&&&&&&&&#BBYYG#\n"
				+"JJJ?7?!!JJ???77??JJJ?????777??JYYJJ?!7??JJ???7??757!?7??YYYJJ?~!!~?775YJ??Y5555GB5P55G#&&GYPP5YYPPGGGPYY5555P5PG55YY55YJJ!!7J7!7!7J?!7777777!!^^~77!~!!!~77777777!7!!!!7!^!!!~~!~^^^^^~~^^^^^:^:^:::::..G&J.::P&&&BB&@@&&&&&&#&&&##&PP&##BBGB#&&&&&&&&&###\n"
				+"55G5P?!J55Y55Y????J!?Y?J?!J??7????!!?PG5PGG5555JJ5Y??!?YJJYYYYYY?Y5Y55J5JJ?7JYPPBBP5JYYJG&&BYJ5GYJ5G5!Y5PPP5YYJ5GG55YJ55Y~~!77!77!77~7?!~~77!7^~!7!!!77!~!!!!7777!~7!!~!!~!!~~~!^:^~^^^^:^^..:.^^^:^~^ ~&G~:~!B&&GB&&@@&&&&&&&#&###&BB&####BBGG##BGBB5555Y\n"
				+"JYJYJ7YJJJYYJJ?5J?J?YYJJ!!Y7?777??!JYYY5PPG55PY77?JY77??5Y????!!J!JPJYY?!~?!^~5PPB555YYYYJ5B&#YJP5YPPPJGGGGP555YPG5YYY5YY!^77!7?~~7?77??!~?J??!~!?777!!!~7!777!!!!~!!~!~!!~~^~^^^~~^^^^^^^^:......::^7YB#BJYGGB###&&&&#&&&###G5&#B##B#&&&&&&&####BPBB##GPY\n"
				+"J55JJ?JJYJYYY?J5Y5JJ?JJ7!7?7???77~?5?Y5PYPG5PGJ?????7YPYYY?Y57:7?~J5JY??7^7!~7PYPBP5G55YYYJ?5B&&#J?5G55GBGGP55PYPGY5P5YY5?77!!77~~7?!!??7!7!!!~^7!7!~!7777!777!!!7~!!~~~!^^^^^^^:^^^^^~^^^^:.:... .:::Y#BB#GBBBB#&BB##&&#####B#&BBBBBPB@&&&&#BBGGBBBGBPG##\n"
				+"YPY?JJ7?YJPP?JPGGBP?J??7YPJYGP7?7??PJJYY55P55?GJ?????YPJJY?5P^:7^~??777YJ7J?J5YP5PBPPGPP55YYJYPG#&BJY5JY5PP5PPP5PP55P5Y5J?Y7!~?7~~77~!?!77!77~^~!!7!~~!!77!!77!!!~~~~~~~~^:::^~^:::::^^^.:...: ..::^:~G#BBBB##BB#BBB##########&#GBGBGGB#BGPPP55PG5JB#G5P&&\n"
				+"5YJY?J?75JYYJPYGGBGYYJ?J5GYY5YYY7J??JJJYP555JJY?JYYJ??5YJY7J!.^J7!~^~!?Y7?5?YYJ!..GBPGGGGP5555YYJJB&BG55PPG5GGPGPPPPP555PYJJ?~J?~!!7!!~~!~!7!^^~!!!!!!!777!~!~!~~!7!!!~~!~^:^!~^~^::::^^::^^^:  ..^^.!#########################&B&&BBB#&&&#BBB#5&&GGB#GY55\n"
				+"?YYJ??Y?YJJYJ??JY5PYJ?JJPGPYPPP?Y5YJYYY5P5YJ7?YPGPPP5YYJ?!7???J55J?JYYY5JJYY!......:~G5YB5~~!~JPYY?J#&&GBPGPGGPPPPPG5YP5JY5J?7Y?!?~:~!~~~!7~!~^~!!77777777!~!~!!~!!~77!!~^^::::::.::.::...:.... .:^^:!B#BBBBB##BBBBBBBBBBBBBBBGG#&&&&&&&##&&&&B?&&&5JP?YJY\n"
				+"?PYYYYP55J?YYJJYJ?J7?JYYY5Y5PPYJ55YY55Y5JJ7?J5P55555P5JY????JYYJJJYYY5JYY?JJP^     ~G^^GP^:.  7P5PYJJYB&@#GGPGPGP555Y5PPY55JJJJJ7?!~~~~?7^!777~~~^^^^^^^^^^~~^^~^^^^^^^^^^~!~^^^^::::^:::...^:..:^?##&##&##############BBBB###BB#7Y&&@&&&&@&@&&&PJJJJY?7Y?\n"
				+"YGPP5PP5YYYYPYY555Y?JYY55P5PP5YYYYY5YYJJJJJJJJJYY5YY!J5YJ5PPYY55Y55555Y55YYPP^~7!:.J7 GBPGPP..YG55P5?JJYG&&&BGP55PP5P5PP5555J?!~?77!~!!!?!~~7!!~!~^^^^::::::::::...:.:::^~!~^::^~...:^^^^::^:::.:..^^?#B##B##BBB##BBBG&&#GBBB##BJYBGG##&&&##B#GP5Y?YBG5YY!\n"
				+"PBBBBGP5?~PGGBGGPPY7?GPPP55Y?!!5GPGP55Y55555555PPPPY7GG5PPPPPPGGPPGGPGGGPYPGPPPGBGPY. ?&BBPB5  YG55?..:.~JYB&&BPPGG5P5PPY5G5Y!!7J?!~7?J~!!!~~~~~~!^^^:::^^^^::::::::!!~!~~^^~~~~~:^^^^^^^^^:.:...  ...GGGGGGGPPGGGGGGB#&&#BGGB#BB###&##&G5B&######BB&&#GJP\n"
				+"BBGBBP?Y?75PPP5PJ7J?YP55555JJYJJ55PP55YY5555PPPPP5P55PPGGGPP5PPPPPGGGGG55PG5PGPGGGGG^  5&#B#&?  5BPGP .?JYYJ75&@&GGYPY5PY5G5J??JJ?~^777^~!77!!~~~~^~^^^^^^:^^^::^^^!!!~!~::^^^^:^:^^^^^^^:::::.:~^:^^!GBBGGGBGGGGGB&&&##BB##BBG#BPBB##JPBBBB###&BPP#&#Y?JP\n"
				+"GPGBGG7~!7P55PPPY~!7JP5P555Y?J!?5PPP5YYP55555PPPPP5P555GGGGGG5PP55BGGBG5PGPPGGGBGGGBP::! !G###&~  YBG7  JG55YYJJ5#&&P5Y5P55Y5J?JJ??~!?!^~!!?77!~!!^^^~^^^^^^^^~!~~!7!~~^^^^^^^^^::^:^:::::::...^:..::~!PBBBB##B##BB&&&&&BB##&#BBGBG#&B&#B###B#BB&&#B#PJBPJY\n"
				+"55PP55!!7~YY555PJ!77?Y55555J?J7?555YYYYY5P5PPPPPPPPPPPPPGGBGGPPPPPGGGG5PGGGGBBBBBB#BB#J.  .:~7!   ^.  :PY5555YYJJJG&&BGPPJJ5YJ??~7!~?!^!7~7??!!!~~^^!~^^~^~!77!~^^^:::^^^^^^^~~^:^::::^^..  ::~^^!??!7GPPPGGGGGGGG####&BBGGB##G5PGG####&#BB&#B###GY75G5J?!\n"
				+"Y55YYYY55Y5Y55YYJJ5J??55Y55PPPY5P5PP5Y55PPPPPPPGGPPPPGPGGPGGPPGGGPGBPYPBGBBBBBBBB##B####P7:.       :!Y5YYY55555YYJ?Y5#&#BJJ55J!7!7!^?JJ??77???!!7!~~~^^^^^^~~^::::^^:::::::::^^^::::.:. .:^~!!J775J~!!?JYJJPGPYYYP#&&&&&&&&@&&&GGGGB&&##BG####&B&P5PPGBGBB\n"
				+"5PP555PP5PPPP555Y5PY5YY55P55P55P55PPP5PPPPPPPPPPPPPPPGGGPPPPPPGPPGGPPPGBB######B#######&&&BGGPGB!  ^BBGPPP55P5YY5YYYJ?JB&&BYYJ~J?77??7??77??7!!!!!~~~^^^~^^^:^::.........::::::^::::^^:.75J??7?7?J??JY5PPPGBPYP5555P#@@@@@@&BPJJBBB#B&&#GGB##&&P&GB###B55B\n"
				+"PPPPPP55P55PPPPPPPPPGP55PPPPPPPPPPP5PPPP5PPPPPPPPPPGGGGGGGPPGGGGPGBPGBBBB#########&&&&#&####BB##&?~?BBGGGPGPPP55YJY5JJ??JP#&#57?7?J?J???7?YJ777!~^^^~~^^~~:::::^:::::::^:::^^^^^:^^~~?G#&&##&&&&&#&&####&&&BGPGB&&#&&&&###GPBGGYY??55PGP555G5Y?JB5YPGBG5?J\n"
				+"BBGGGGGGBGGGGGGGGGGGGGGGGGGGGGGGGGGPPPGPGGGGGGG5PP5PGBBBGGGGGGGGBGPGB#####&&&&&&&&&&&&&&&&&######&&##BBBBGGPPPP55YY?YJ?J???Y#&B5~????JJ???JJJ?7!~^^^~~:^^^:::^^.:::::::^:::^~~!~^~~^^:..5&#&&#&##&&&&&&##&#B#&#PBGPB######BGP55JJYPGPG#JJJJG5PP?7J5JJB5??5\n"
				+"BBGPPPGGPPPGGPPPPGGBGGBGPPPGPPGGGGGP55PPGGGGGGG?YYY5GBBBBGGGGPGGGPGB#######&&&##&#&&&&&&&&&##&&&####BBB##BBGGPP555YYYJ????77!JG&#PJJ?YJ?JYYJ?!~~~~!~^^:^^^^^^~!::::^::::^::.:^^^:^^^:::.^~~^!#######&&&#&&&BB@@&#BB#&##&&BGPPG&&&@&&#BB?Y5JPBGPY?J??5PPYYG\n"
				+"555YYJJJY5YPPYYPPGGGBBBGGPPGPGGGGGGGPPPPGGGGGGGGP5GGGBBBBBGGGGPGGGB#######&&&##&##&&&&&&&##&&&&&&&&&#####BBGGGGPP555YYJJ???777?YG##GJYJ?J5J?7!~7!^~:^^^~^^:::::..^^^: .:^:..:^^^::^:::::.....^.^:?BG&&&&&&#B&@&@@@&&@@@@@@@@@@@@#&@&&&#P#&GB&&BB###GG5GP55\n"
				+"JY555Y55PP55555PGPGPPPPPPGGPGGPPGGGPPPPPGGPGGGGBBBBGGBGBBGGGGGPGG#######&&&&&##&&&&&&#&&###&&&&&&&&&&&&####BBGGGGGGGPPYJJJ?????JY?Y&&#Y?JJ??77?!77!~~^^!!~:!:^~~:.....^!^:.:^:^:::::......::..   .::YB#####&&&&&&@@&&&##&&&@&&&&B#&&&&&&&&&&&&##&#&##GGJJJ\n"
				+"GGGPGGPGBG5BP5GGGGGGPPPPPGGGGGPPP55PPGGGGGPYPGGBGBBGPGBBBGGPGGGG##&&&##&&&&&&&##&&&&#BB##&&&&&&&&&&&&&&&&######BBBBBBBBG5Y????7!JJ??YB&&5?JJJ????7!!~~!77^:?7!!!.  !YPP5YYJ7:::.::..::::::..... . .: .J&B#####GB&&@@@@@@@@@@@@@@&&#BG#&######&&&&&@GG#BPB#\n"
				+"PGGPG#BGGP5GPPPGPGGPPGGPPPGGGBGGGGPPPPGGGBBGBBBBGBGGGGGGGGGPGBGB&&&&&&&&&&&&&#&&##&&##B##&&&&&&&&&&#&&&&&&&&&&#&&&###BBBGPYJ??7755YYJ?YG&&5J??777!~~^^~~!^:~!~~^ ^&#BBBBGGGB~::::.....::::::::.   ^~^:!&G#BB#B####&@@@@@@@@@@@&&#BG5?G&#&#&&&###GYYG#&&&#B\n"
				+"55GGYYYGYYYJYBBPPPGP5GBBBGGGGGGGGGGBBBBGGGBGBBBBBBBBBBGGGGGBBPG&&&&&&&&&&&######&##BB##&&&&&&&#&&####&&&&&&&&###BB#BGGGGGPYJJJ??YYYYJYJJJG&&GJ777777!7!~~^:::::...P&&&@@@&##?7~^:^^:::::^JY!:..   .~::!&BBB##&&&#####&##BB##BGGGBP##GBPYYY5YJ555G5YG#BB&B#\n"
				+"5555JYYG577!J##PPGGP5B#B#BPBGGGGB##BBBBBBGBBBBBB#BBBBBBBGGBB55#&&&&&&&&&&&&######BBBB&&&&&&&&##&&#B#B######BBBBGPPPP5GP55YJJJJ?YYJJJ?JJJ??75&@#5?777!7!!~:.....    B@@@&@&&B5GBP?~~?J?7J&@&&J^:. ..:^^?#B#B##BB&@&&&@@#BG#@@&@&&##&&#GBB#BB####B####&&&@BG\n"
				+"YJ7??5YPG??7?BBGPGGGGP####GBGBGGG##BBBBBBBBGB#BBBBBBBBBBB###PB&&&&&&&&&&######B#BBB##&&&&&&&##&###BBGBGGGBGPPGPPP55Y5PPY5YJJJ?JYJJ?J?JYJJY?!^?#@&BY7777?!:.....    P&#&&#&&B##PJY5JPGYJG&B^^!PP:....!??PPPGGGGB&&&@&&&@&&&&&&&@&&&#GJYGGBPPPGB#&##B#GJ?YGP\n"
				+"?77??55GG5JJYG#BGGGGBPBBBBBBBBBBGB#BBBBBBBBGBBBBBBBBBBBBBBBBB&&&&&&&&&&###BB###GGBB#&&&&&&&#######BBGGBGGGGPPP55555555YYYJJJJJY5YJ??77?JJY??!!~?B&&#5~^~~.....   .^B&&&&@@@@@&BGPGP555Y55GP~....~:::JGBBBGBBBBB&@&&&#&&&##BB#&&&&&@@#&&&#&&&BGB&&BPPPBGG55\n"
				+"?77?JPPYPGPPPPGGGGPPBGGP55BBBBBBGGGGGBBBB#BGBBBBBBBBBBBBBBBG&&&&#&#&&&&&&&#####BBB##&&&&&#########BBGGBPPPPGPPP55P5555YYJ????JYJJJ?J?J?JY?7?7J?~^?P#&#J^:. ...:?GB&&@@@@B&@@@&&&BGGGPPBGGG#GG:::^~!~:.YBGBB###B#@&&@@@@@&@@@@&&&&&@&&&&&&##&#BB##&BJJGGP5G\n"
				+"????Y5Y5GGBBPPBBBPGPG#BGBBBBBBBBBBBBBBB#B##PGB##BBBB#BGGBBB#&&##&&########B##BBBB##&&&#&###&#####BB#BGGPPGPPPPP5555Y5YYYJ????55JYJYY?JJYJ??7777!!~:~Y#&&P^^:~PB&@@@@@&P: ::Y&&&&&&&##&&&#&&@&Y7::^:^!?YGBBBBB##P&&&&@@@&@&&&&@&&&&&#&&&&&&&&##&&&#PB55B#BP\n"
				+"Y?!7J5PB##BBGYGBGGPPPBBBBBBBBBGGB#BBBGGBBBBYG##B###BBBGGGBB#####&&#&&#######BGGB###&&############BB#BGBGPGGP5555Y5YJYYYYJJ?JJ???77!7777??YJ?J?7??!:^:^J&&##&#&&&&GY7!:.^ :..Y&&@&@@#B&@@&@@&GGG!.:^~?5YPGGGGGBJ.&&@&@&&&&B&@&&&@&&@#G#&#&##GBB&##GG&GJJG#5\n"
				+"Y??Y5PB#&#BG5?B#BGP5PBB####GGBBBGBBBBGGBGBBPPB###BBBBBGPGG#&###&&&&&&&&&&##BGGGB#######&######B#BBB#BGGGPPGGP55YYYYJYYYY?J?777YJ77!!?7JJ7J7^^^^~^:^:::.!G&&@&5?~~!~^~.:! .:~P#&@&@@&B&@@@@GYP5BY.:^GBBGBBB#&B7.7@&&@&&&&##&&@@@@&&@#JP&&&###BBBG#PGGGPGGGP\n"
				+"PJJYY5B####BGJG#BGPPPGB####BGGGGGB###BBBGGBGPB###BBBGGPGGG#&B#&&##&#&&####BGBBB####&###&#########BBBBBGPPGGGP5YYYYYJJJJJJYY???YY??77?!JY~!!~~::^..:....  ..J&@&5!^~^^^!~.!PB&@@@&@@&#&@&&#BBB#&Y...5######&P^:~#&#&##&&&BB&&&@@@@&@&GJ&&BBBB#BB###PGBB&BG&\n"
				+"B5JY5G#BBB#BG5J#BGPPGGB#####GGGGBBB###BBGGBBGB###BBBBGGBGGB##&&####&&#BGB#BPGB#######&&####B###BB##BBBGGGGGGPPP55YJJJJJJYYYYJJYJJJJ??J?J77!!~!!^:^^:...:....?G&@@#Y777~!P#&&@@&&&&@&&@@&#&&&#&G:...^G####&P~^7##GGPGG###BB#&#G5YGGB#BPY?!J55#@@&PGGGB#&&B#\n"
				+"#PJ5G##BBBBGYJ!G#BGGGGBB####BBBBBBBB###BGGBBBG#BBBBBBBBBGGBB###BBB##BB#&#BBBB###############B##BBBBBBBBBGGGGGPGP5YYYJJY5J!??J55PYJ5Y?J7?55J????!:^~^:...:..:!77Y#@@#P!^#&@@&&&&&@@@@&&#BGGBBB7.......:5&B!::7##BG##5PBGBGGBG5PBPJJ5G##5JG&GP&&#&##B&&BBGY5\n"
				+"#PY5PPG##BB577!JBBGGBBBBBBBBBBBBBBBGGGGBBGGGBPBBBBBBBBBBBGBG#BBBBB#B&&&#GGB##&&&##########BBB##B#BB##BBBBBGGGGGGPPPP55PJ!!?JYY5YJ?J?YJ??J?~:!77?~~^^~^:.....^~~~~7G&@BYG#&&&@@@@@@@@&##BG5Y55:.:^::^^.7P::^~G##G&&BB#GPGPPG&BBG&#YJB#BGB#@#B#&#P5BGPGYGB5G\n"
				+"#GJYYJY#&##PYPJJBGGGGBBBBBBBBBBBBBBBBBBBBGGBBPBB########BPGBBGG#&&&&&&&#B####&&##########BBBB##B##B###BBBBGGGGBBBBBB#&G?JJYYY55YYYJJYJJJY5JJYJ?7^~::^^::^:^^^:^^^^:~#&&&&@@@@@@@@@@@&###BPYY~^^:~^^^!!~.:JBPPG######&G5GG5P####&&GPPJ!Y#@#G&B&&GG?YP?5BPYP\n"
				+"&GJJ?7?GBBGGGGB7GGPGBBB###BBBBBBBBBBBBBGBBBBBGB######&##&######&&&&&&#BB###&&&&#####BBBBBBBBB##########BBBBBBB###&&&G7^!Y5YYY555Y5JYJJJJ5P555Y7!~~~^~!!^!??J?~??^~^:~B@@@@@@@@@@@@@&&&&&&&#?!!!7!~~~!::^^^B@&&&##&@@#PP&&PGB&&&#&&#BPGG#@GB@&#J7?~~!55#&B#\n"
				+"&BJ5YPG#BGPGBGBYB#PGGBBB#####BB##B#####BBGGGG5P&&##BB&#####&&&&&&&&&#BBBB#&&&&##&##BBBBBBBBB#####&#B#########&&&@@B?:.^:~!?JJY5YJJJYJ?????J77!!~!!JJJJ?!?YYJ!!??Y!:.7@@@@@@@@@&@@@@&&#&&&@&Y777?!77~..^:^.G@&#####@@#BB@@#BB@@@&&#&@@@&G&BB#BBGBBBBG##G#BG\n"
				+"&&GB#&###B#GG#P7P&GGGBBB#####BBBBBB#&&###GGGB55&##BGGBGP5PB&#&&&&##BBB##&&#&&&&&###BBBBBBB######&#&#BBB####&&@&#BY?J7JJJY5PPY5YYYYYY??7??77!!!7~77JYJ77J?7J!~7YJJ7^5B#&&&@@&@&&@@@@#J?JP&&GPP!77~!^..:^^^7?G&&&&&@@@@@&@@@@@@@@&&@#&@@@&G&&&#@@@&#B&#P5PPG\n"
				+"B#BB##&BB#BPYGY7J&BGGBBBB#&&#BGBBBB##&##GBGBBGPBGGPYPP5Y5PPB##BBBB#B#&&&&&&#########BBGBBB######BBB#BB##&&&@@#Y:?J!J5PJJG55P55555Y?77777?7!!~?Y7~~Y5YYYJJJJYYJYY?!7&&###&&@@&@@@@@@BP5Y~!YYGPPPJ?^..^::^::^~?@@&@@@@@&@&@@@@&@@@@@@@@@&@@@@#B@@@@@#B&#####\n"
				+"BPB######&BY!777JBGBGBBBB####BGGBBB#####GGGGGG5P555P5PPGP5PGBGG#&BBB#&&&&&&&#####BBBBBBGBBBBBBBBGBBBB#&&&@@&P??^^?JJ?YJ?YYYYY5YJJ?!!!!~!!!^~!?57:^JY77JGPY5PPYJ?!~:^P#&@@@&&@@@@@@@@@@@&5J~~5G?7:..^^~7JY~!55G#&&&&&&&@&&&@@&@@@@@@@@GG@@@&&#&@@@&BPB&GGGP\n"
				+"BGB#&&#B#&#Y?7JJJG##BBBBBBBBBBBBBBBBBGGBBGGGGGPGP5PPY55PPGGGB#BBBBB#&&&&&&&##BBBBBBBGGBGGGGGGGGGGB&&&@@@&&&~?JJYYJ?JJY5555YJJJ?7!!~~~~!~!!!!~^?!^^??!~?555YYYJ?7~^5#GGPB&@@@@@@@@@@@@@@@@@#PBG57~!!~~77J?JG#G5G#&&&&&&&@@@@&@@@@@@#GG5G@@&B&&&@@@@&Y??PGGG\n"
				+"P&&B&#&GG&&PJ??J?5##B######BB##BBB#######BB#BGPPYP5Y?77JG5GP5PG#&&#&&&&##&#####BBBBBBBBBBBBBBB#&&&@@&&&&&#~^??7Y5YYYYJYYJ??77!~~~~!!!!77~^^^^^77^^~!~~7J?J???J?!?5@@&&&B&@@@@@@@@@@@@@&BG#@@@@&#BPP!^~~7YPGPB#GGP55P5YY5PPGGGGGG#&&BG#&@@&@@@@&&&#GGPJJBBB\n"
				+"J&&BB&&BP&&GJ?J??JGBGGGBGBBGBBBBBBB##GBGGPPPGPGPPP5P5YJ7J?YP5YYYGB&&&&&##&&#&&#####BBBBBB##&&&&&@@&&&&&&&Y!7JYJYYY5YJ7?7777!!!!!!!7!!!~~~^^^::^~^::^~!!!!!7P&@&#&@&&&#&&@@@@@@@@@@@@@&##&BG@@@@@&B5J^!?55#&&&#&##&&&&&&#######BBPGB#B&@@@@&&&@@&#&BB####&P\n"
				+"J&&BP&&#P&&BJJ5PGPGBBBGGBBGGBBGBBGJPBGPGGPG5PPGPPGG##P?JJ5J?JY??Y5YYP#&&&&###&#########B##&&&&&&@&&&&#&&577777???7777777777777777!~~~~^^^^^^^^^!~~!!!!!!~?@@@&@@@@#B&&&@@@@@@@@@@@@@@@&&@@B!B@@@@&#B5J&&BBBPGB####&&&&&&&&&&&&&@&@&&&@@@@@&&&#&&@&&#B&&#B&\n"
				+"J&&&5&&&BGBGPGG55YGBBBBG#BBBBBGGGGPBGGPB#BGPPPPPP5YB#GYJYYJJJJ75PPGPJJ5GB&&&#B##B#########&&&&&&&&&#&&@J^!77??7J??7????777!!!!!!!!!~~!~~~!!!!!!~!!!7!~^~~P@&&&&@&&&&&#&@@@@@@@@@@@@@@@@@@@#!!@@&&@@&GP&BG#@@&#B55PPGGPBBB###&&&##B#&@@@@@#B##B#GBG5B#B&#PP\n"
				+"JP@&GGGGBGGP5YY55YPBGBGGBGGBBGGGGGGGGBGB#BBGBBBY5GP5PJ755YY??75GYGP5PYP555P#&&&##BB###&&&&&&&&&&&&#&&&P~~?!??????7!7?777!!!!!!!!77!!!7!7?77!!!!~!77!!!~~^G@&&&&&&&&&&&&&&&&@@@@@@@@@@@@@&B5G#&@@@&@@&G&&&@@@@@@@&###########&&#GGB@@@@&@&GGGB#&&5BBBBGP55P\n"
			+" Logramos ganar, todo gracias a vuestra ayuda\n"
			+" el mundo libre estara en deuda con ustedes eternamente.\n"
			+"¡LARGA VIDA A LOS HEROES! ¡MIL HURRAS A NUESTROS SALVADORES!\n");
		}
	return message;	
	}
	/**
	*<b>name:</b>randomevents<br> 
	* the system will generate random events.
	* <b> post:</b> the system will to generate a message with the corresponding event<br> 
	*@return  a message with the corresponding event
	*/
	public String randomevents(){
		
		String message="";
		boolean comprobant=false;
		int numberofevent=0, countplayers=0, randomplayer1=0,randomplayer2=0,randomplayer3=0;
		numberofevent=(int) (Math.random()*9+1);
		for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){
						countplayers++;
				}else{
					comprobant=true;
				}
			}
		comprobant=false;
		switch(numberofevent){
			case 1:
			message=(" encontrasteis una fogata donde tus acompñantes mas cansados se quedaron a descansar mientras el resto hacia guardia\n"
			+"	555555555PPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGBBBBBBBGGGGGBBGBBBBBGGGGPPPGGGGPPPPPPPGGGPPPPPPPPPPPPPPPP5555PPPP5555555555555555555555PPPPPPPPPPPPPPPPPPPPPPPPPPGGGGPPGGGGGGGGGPPPPPPPPPPPPPPPPPPPPGGGGGGGPPPPPPPGGGGGGPPPPPPPPPPPGGGPPPPPPPPPPPPGGGGGGGG\n"
			+"	555555555555555PPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGBBGGGGPPGGPPPPPPPPPGPPPPPPPPPP5555555555555PP55555555555555555555PPPP5555555555555PPPPP55PPPPPPPPPPPPPPPPGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGPPGGGGGGPPPPPPPPPPPGGGGGGG\n"
			+"	5555555555555555555PPPPPP55PPPPPPPPPP5PPPPPPPGGGPPGGGGGGGGGGGGGGGGGGPPPPPPPPPPPPGGGPPPPPPPPPPP55PPPPPP555PPP555555555555555555555555555555555555555PPPPPPPPPPPPP55PPPPPPPPP55PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP5PPPPPGGPPPPPPPPPPGGGPPPPPPPPGGGGG\n"
			+"	555555555555555555PPPP55555555555555PPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGPPPPPPPPPPPPPPPPPPPPPP5PPPP55555555555555555555555555555PPPP55555555555555555PPPPPPPPPP555555PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPPPPPPGGGG\n"
			+"	PPPPPPP55555555555555PPPPPPPPP555555555PPP5PPPPPPPPPPPPPPPPPPPPGGGBBBGPPPPPPPPPPPPPPPPPPPPPPPPPPP5555555555555555555555555555PPPPPPPP55555555555PPPPPPPPPPPPPPPPPPPPPPPPPPP5PPPPPPPPPGPPPPPPPPPPPPPPPPGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGPPPPGGGGGGGG\n"
			+"	PPPPPPPPPPPP5555555555555555PPPP5555555555PPPPPPPPPPPPPPPPPPPPPGGBGGGGGP55PPPPPPP5555555555PPP555555555PPPP55PPPPPPPPPPPPPPPPGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGGGGG\n"
			+"	PPPPPGPPPPPPPPPPP555555555555555555PPPP555555555555555555555PPGBGGGBBGGPPPPPP555555555PPPPPP5555555555PPPPPPPPGGGGGGGGPPPPPGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGPPPPPPPPPPPPPPPPPPGPPPPPPPPPPGGPPPPGGGGGGGGGGGGGGGGGB\n"
			+"	GGGGGGGGGGGGPPPPPPPP55PPPPP555555PPPPPPPP555PPP55555555PP55555GGGGGGBGBP55555555555PPPPPPPPPPP555555PP55PPPPPPPPPPGGPP5PPPGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP5PPPGGGGGGGGGGGGGGGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGGBBG\n"
			+"	GGGGGGGGGGGGGGGGGGGBBGBBBBBGPPGPPPPPPPPPPP5PGGP5555PPPPPPPP555GGGGGGBGGP55P5555555PPGGGGGGGGGGGPP5PPPPPPPPPPPPPPPPPPPPPPPPPPPGPPPPPPPPPPPPPPPPPPPPPPPPPP55Y5GGGPPPPPPPPPPPPPPPPPPPPPPPGGGGBGGGGBBBBGGGGGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGGGGGGGP\n"
			+"	GGGGGGGGGBBBBBBBBBBBBBBBBBBBBBGPPPGGGGGGPPGGGGGP55PPPPPGGPPP5PGGGGGBGGBP5GGP555PPPGGGGGGGGGBBBBBBGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPY???J5GGPPPPPPPPPPPPPPPPPPPPPGGGGGGBBBBB##BBBBGGGGGGPPPPPPPPPPPPPGPPPPPPPPPPGPPPPPPPPGGGGGGGGGGPPPG\n"
			+"	GGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGPGGGPPP55PPPPPPPPPPPPGGGGGPPPGGGBBBGBBP5GBGGGGBGGGGGGGGGBBBBBBBBBBBGPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGPPPPPPPPPPP5JJYPGPPPPPPPPPGGGPPPPPPPPPPGGGBBB########BBBBBGGGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGPPPPPPPGBGPPGGG\n"
			+"	##BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGPGGGGGGPPPGPPPPPPPPPGBGGGGPPGGGBGBBGBGGBGGBBBBBBBBBGGGBBBBBBBBBBBBGPPPPPPPPPPPPPPPPPPP5PPPPPPPPPPPPPGGGGGGPPPPPPPPP5P5J?JPGPPGPPPPPPPPPPPPPPPPPPPGGB#############B#BGGGGGPPPPPPPPPPPPPPPPPPPPPPPPPPPPGGGGPPPPPPPPGPPGGGG\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGBBGBBGGGGGGGGGGGGGGGGGGBBGBGGGGGBBBGGGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGGPPGGGGPPPPPPPPPPPPPPPPPPPPPPPGGGGGGBBGPPPPPPPPPPPPY?JYPPPGPPPPPPPP5PPPPPPPPPPPGGB#GPPPPPGB#######BGGGGGGGPPPPPPPPPPPPPGPPPPPPPPPPPGGGGPPPPPPPPPPGGGGB\n"
			+"	BBBBBB#######BBBBBBBBBBBBBBBBBBBGGGBBGGGGBBGGGGGGGGGGGGGGGBGGBGBBBBBGGBBBBBBBBBBBBBBBB#BBBBBGGGBBBGGGGPPPPPPPGGPPPPPGGGGGPPPPPPPPPPGGGGGGGGPPPPPGPYYPPPJJYPGPPGPPPPPPPPPPPPPPPPGGGGGGP5JJYYYYYYP#######BBGBBGGGGPPPPPPPPPPGGGPPPPPPPGGGGGBBGGGGGGPGGGGGGGB\n"
			+"	#############BBBBBBBBBBBBBBBBBBBGGGBGBGGGBBBBBBBBBBBGGGGGGGGGBBBB#B#BBGGGBBGGGGGGGGGGGGGPPGGPPGGGGGGBGGGGGPPPPPGGPPPPPPGGGPPPPPPPPPGGGGGGGGPPPPPG5YYYYYYYYPGGGGGGPPPPPPPPPGGGGGGBBGP5YJJYJ??Y55YG#######BGBBGGGGPPPPPPPPPPPPPPPPPPPPPPPGGGBBGGBGPPGGGGGGBB\n"
			+"	############BBBBBBBBBBBBGGGGGGGGGGGGGGGPGGGGGGGBGGGGGGGGGGGBBBB##BGBGGGPPPPPPPPPGGGGGGGGGGGGGGGGGGGGPPPPPGGPPPPPPPPPPPPGGGPPPPGGPPPPPPPPPGGGPPGPPGPP5555Y555Y5PGPPPPPPPPPPGBBBB##BP5JY55JJYY55PPP##BPGPGB#BBBGGGGPPPPPPPPPPPPPPPGPPPPPPPPGGGGGGP555PPPGGGB\n"
			+"	###############BB####BBBBBBBBBBBBBBBBBBBBGGGGGGGGGBGGGGGGGBBBB#GPPPGGGGGGGGGGGGGGGGGGGGGGPPPPPPPPPPPP5PPPPPGPPPGGGPPPPPGGPPP5PGGPPPPPPPPPPPPPPPPPPPPYY5PPGBGP5PPPPPPPPPPPGGGGGGGGG5Y?7JY55YY5PPPPPY?Y5PPPB#BBBGGGGGGGPPPPPPPPPPPGPPPPPPPPGGGPGP55P555PPPPP\n"
			+"	###################B##BBBBBBBBBBBBBBBBBBBBGB##BBGGGGGGGGBBBBBB#GPPGGGGGGGGGBBGPPPPPPPPPPPPPPPPPPPPGGPPPPPGGGGPGPPPPP5PP5PPYY5PGPPPPPPPPPPPPPPPPPPPPYYY555GGGGGPPPPPPPPPPG5J?J5PPPPGG5JJY5PGPPPPP5?J5PP5P5PGBBBBGGGGGGGPPPPPPPPPPPPGGGGGGGGGGGGP55P5PPPPPPP\n"
			+"	###################BBBBBB#BBBBBBBBBB#BBBBGBBBB##BBBBBBBGGGGGB##BBPPBBBBBBBGBBGPPPPPPPPPPPPPPPPPPPPPPPPGGGGGGPPPPPPPPPP55PP5Y5PGGPGPPPGGGPPPPPPPPPGPYJ555PBGGGPPPPPPPPPPP5YY5PPP5GPPG55PGGGGGGPP5YYPPYYYJJYYYPG#BBBBGGGGGGPPPPPPPPPGGGGGGGGGGGGG55PPPPPPPPP\n"
			+"	###################BBBBBBBBBBBB#BBBBBBBBGBBB######BGGGGBBBBBB##B##BGGGGBBBBBBBGPPPPPPPPPPPPGGGGGGGGGGGPGGGGGPPPPPGGPPPPPPPGPGGGGGGGGGPPPPPPPPPPPPGY?J555GGGGGPPPPPPPPPGP5PPPPGPPBP5Y55YY5PPPPGGP5PPY55JJ??YYYYB##BBBGGGGGGPPPPPPPPPPPPGGGGGGGGG5PPPGPPPPPP\n"
			+"	######################BBBBBBBBBBBBBB#BBBBBB##BBBBBBBGBBBBBBBBBB####GGGGBBGGGBBGGPGPPGGGGGGGGGGGGGGPPGPPPGGGGGGPPPP5PPPP55PGGGGPPPPGGPPP5PPPPPPPPGP??5P5GBGGGPPPPPPPPPP55PPPGBBPPGBP5YYY5PGPGGBGPPG555Y5JYYJY55P####BBBGGGGPPPPPP5PPPPGGPPGGGPPPPGPPPPPPGGG\n"
			+"	###################BBBGGGGGGGGGGGGBBBGB#####BBBBBBBBBBBBBBBBBBB##BBGGGGBBGGGGGGGGGPPGGPPPPPPPPPPPPGPGPPPGGGGGPPPP5YYPPPP5YPPPPPPPPPPPPPPPPPPPPPGPJ?5PPPBBGPGPPPPPPPGGGGGGGGGGBGPPGBBGPPPGGGGGBGPPBPPP55555PGGGGB###BBBBGGGPP55PPPPPPGGGPPP55PPGGGPPPPGGBBB\n"
			+"	#############BBBBGGGGGGGGGGGGGGGGGGGGGGGGGBBBBBBBBBBBBBBBBBB#BB#BBBGGGGBGGGGGGGGGGPPGGPPPPPPPPPPPGGGGPPPGGGPPPPPGGPPP5Y5P5Y555PPPPPGGGGGGGGPPPPG5?J5PPGBG5PPPPPP555PGGGGB######GPPGBBBGGGGPPPGGP5PPGGPPPPGGP55PGB###BBGGGPPPPPGGGPPGBGPPPPPGGGGPP5PPGGB###\n"
			+"	B########BBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPPGBBGGBBBBB####BBB#BGBGBGGGGGBGGGGGGGGPPPGGPPPPPPPPPPGPPPGPPPPGGGGPPGGGPGPY5P5P555YYYPGPGGGGGGGGGPPPPY?J55PBBPPPGPGPYYJ?Y55P5PGB####BPPPPPGPPPPPPPBBBGP5PGGGGGGP55YPG###BBGGGGPPPPPPGBGGGGP55PPPGGGPPPPPGGBB###\n"
			+"	GGGB###BGGBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPGGBB#######BBBBB#BGGGBBGGGGBGGGGGGGGPPPGGPPPPPPPPGPPPPPGGPPPGGGGPPPPPPGP5PPPPPP555YYGPPPPPPPPPPPPP5J55PPGBGGPGGGGPYYJJJJ55PPPPG####GPPPPPPPPGGGGGGGBBGP5PPGBPPGB######BGGPGGGGPPPPPBGGP5PPPP5PGGPPP5PGGB#####\n"
			+"	BGGPGGBBBBGGBBBGGGGGGGGGGGGGGGGGGGPPPPPPPPPGGGGG#######BBBBBB#BGGGGGGGGBBGGGGGGGGPPPGGPPGGPPPPGPPPGGGGPPPGGPPP5PPGGP555P555PPGGGPGGPPPPPPPGPPPPYY5PPPBBPGPPPGGP5YYY5555PPPPPGBB#BBBGGPGGPGBBGBBBBB##BBB############BGGGGGGGGGPPPGP5PP5555PGGGP5PPGGB######\n"
			+"	GGGGGPGGBBBBBBBGGGGGGGGGGGGPPGPPPPPPPGGGGGGGGGGG#####BBBBBBBB#BBGGGGGGGBBGGGGGGGGGPPGGPGBBGPPPPPPPGPGGPPPGPPP5PPPPGG5YJYPG55GBBGGGGGGPPPGGGPPP5JY5P5GBGPPGP55YY5PP5Y5P55P55PPGGGBBBBBBBBGGGGBBBBBBBB#&###BBBB######BBGGGGGGGGGPPP55PGP5PGGGGGGPGGBB#######\n"
			+"	BBBBGGGBGGB#BGGBBGGGGPPPPPPGGGPPGGGGGGBBBBBBBBGB###BBGGBBBBB####BBBBGGGBBBGGGGGGGGGGGPGGPPGPPPGPPPGPGGPPPGGPP5PPPGGPYJ?J5G5JYPGGPPPGGGPY5GGGGPYJY555BBBGPP5JJJJJ5PP555GGP5GGPP555PGBBBBGGGGGGGGGGGBB####BBBB########BBBGGGGGGGGG555PP5PPGGGBBGGBB###&&####\n"
			+"	B#####BBBBGBBBBBGGGPPPPPPPGBGGGGGBBBBBBB###BBBBB##BBGGGBGGGBB####BBBBBBGBGGGGGGGGGGPPGGGPPGPPPGGPPPPGGPPPGBGPPGPGG5YJJ?JY5PYJJ5BGGGBBBPPGGBBGYYJ555GBBGPGPYYYYY5PGBP5PG55GGYJYPGBBGBBBBBGGBBBBBBBBBBBB##BB##BBGGGGG#BGBGGGGGGGGGPPP5555PPPPGBBB########&&&\n"
			+"	######BBBBBBB##BBGPPPPGGGBBBBBBBB#####BBBBBBBBBB##BBBBGGGGGGGBBBGGGGPGBBBGGGGGGGGGGPPGGPPGPPPPPPPPPPPGGGGGGGGGGGGGPYJJJJJYP5JJY5G#BGGGGGGGGBBYYY5Y5BBBGPP55555PPGGGBBGG55B5YG##BB#BB####BBBBBBBBGGBB###&#BBGP5PGGBGB#BBGGGGGGGGGPGP55PPPPPPGG######&&&##&&\n"
			+"	################BBBGBBBBBBBB######BBBBBBBBBBBBBBBBGGGGGGGGGGGPPGGBBBBBBBBBBBGGGGGGGPPGGGPPPGGPPPPGGGGGGGGGGGGGGGGGGPYJJJJY55YJYYPBGGPPPGGGGGGYYJ55PBBGGG5YYYY5PGGGB#&#BBGGPG#BBBGBBBGB#######BBBBBBBBB##BB#BBPGGGGGB##BBGGGGGGGGPP5PPPPPGGGB#BBB###&&&&#&&\n"
			+"	#####################BB####BB##BBBBBBBBBBBBBBBBBBBBBBGGGGGGGGBBBBBBBBBGGBBBBBGGGGGGPPGGPPPPGGGGGGGGGGGGGGGGGGGGGGBGGPPPYJY5PYJYY5BBGPPPGBBGGGPYY5PPGPGGGGPGGGGGGBB######BBBBBBGBBB#BB###BBBBBBBBBBBB###&###BGPGGGGGB##BBBBGGGGGGP55PPPPGPPPGBBB###&&&&&#&&\n"
			+"	##########################BB###BBBBBBBBBBBBB#BBBGGGGGGGGGGGBBBBBGGGGBBBBBBBBBBGGGGGPPGGGGGGGGGGGPPPGGPGGGGGGGGGGGGGGGGGPYJ5PYJYJY5GGGGGGBGGGGGGPPPPPGGGGBGP5PPPPPPB########################BBBBGBB#B#######GGGGGGGGG##BGGBGGGPGBP555PPPPPPPGBB#####&&&&&&&\n"
			+"	###################BBB####BB#BBB#BBBBB##BBGBBBBBBGGGGGBBBBBBBGBBGBBBBBBGGGGGGGBBGGGGGGGGGGGGGPPPPGGGG5PGGGGPPPPPPPPGPPP5YJ5PPJJJ5PPPPGGGGGGGGGGGGPPPGGGGGPYJY55PP5PBBB###############BBBBBB##BBBBB##BB####BGGGGGGBBBB##BPGGGPGBBPPPPPPPPPPPGB######&&&&&&&\n"
			+"	###################BBBB######BBB#BGBBBB###BBBBBBBGGBBBBBBGGGBBB##BBGGGGPPPP5PPGGGGGGGGGGGPPPPPGGGGGGGP555PGGGGGPPG5YYYJYJJ555??JY55YY5PPPPPPPPPPPPPGGGGGP5YYY55555PBGB###################BBBBBBB###########BGPGGGBBB#B##GPGGGGGP5PGGGGGGPPGBB####&##&&&&&&\n"
			+"	######################BB####BGBB#BBBBBBBBBBB##BBBBBBBGGBBBBBBBBBBGGGGPPP55PPPPGGGPPPPPGGGGGGGPPPPPPPPPPPPPGP5PGGGGYJYYJJJ?J55J?JJJJJJJYY5PPPPPPPPPPPPPPPP5YY5P555PBB#######################BBBBBBBB#########BGGGBBBBGBB#GPGPGBP5PPPPPPGGGBB######&###&&&&&\n"
			+"	#####################BBB####BGBBBBBBBB##BBBBBBBBBGGBBBBBBBBBBGGBBGGPPP5PPPPGGGGGPP55PPPGGGGGGGGGGPPPGGGGPPPPPPPPPP5YJJJJJ7?55Y?JJJJ??JJY5PP5PPP5555555PP5YYY5G55PB########################BBBBBBBBBB#########BGGBBB#GPGBGGPPPP5PPPPPPPPPGGGGBBB###&&&&&&&&\n"
			+"	&&&################&#BB#####BB##BBBBBB#BBGBBGBBBBBBB##BBGGGGPPPPGGGPPPPPPGPPPPPPPPGGGGGGPPPPP55PPGGPPGGPPPPP5PPPP5PPYJJJ??7J5PJ??JJ??JYYY555PP5555555YY55PGPGGPB#########BBBBBBB#########BBBBBBBBBBB############BBBBBGGGGP55PPPP5PPPPPG##BBBBBB###&&&&&&&&\n"
			+"	&###&&#####################B##B##BBBBBBBBBBBBBBBBBGBBBGGPPPPPGGBGPPPGGGPPPPPPPPPPPGGGGPPPPPPPPPPP5555PPP555YYYY555555YYJ??7?55J??JJJJJY555555555555YYYYYY5PPGGBB#####BBBPPPPGBBB#########BBBBBBB###############BBB####GPP55PPPGGGPPPGGG#&&&&#######&&&&&&&\n"
			+"	##&&&######&#########&#####BB#BB##BBBB##BBBBBB#BBBBBGGGGGGGPPPPPPPGGPPPPPPPPPPPP5PPGGGPPPPPPPP5555555555YY55555Y5YY5YJJ?77JY5PY?7??JJJY555555Y555YYYYY5P55555PBBBBGGGPP5Y5PGPPPPGGGBB###BBBBBBBB#################BB#BBPP555PPPPPGGGGGGGB#&&&&&&####&&&&&&&\n"
			+"	&&&#####&&&&###########BB#BB##BB##BBBBBBBB#BBB#BBBBBGPPGGPPPPPPPPGP5PPPPPPPPPPPPPGGGPPGGGGP55555555555YYYYY555555YYJJJ???J55Y55YYJJ5YYY55555YYYY5555P5PGGPPPPPPPPP5555YJJYGP5PPPPPGPPPPGGBBBGGBBB################BGPGGPGP5555555PPGPGGBB##&&&&&&###&&&&&&&\n"
			+"	&&#####&&&###&#####&#####BB###B###BBBBBBBBBBB####BBBPPPPGGP5PGGGGPPPPPPPGGGGPPPPGGPPPPPGGPP5Y55555PPP555YYYYYYYYYYYYJJJJ5P5YY5YY55YYYYYJYYYY55YYY55555555555555555YYYYP55PG5YYYYYYYYY?J55P5YY55PB##############BBBBBGPPGG555P555PPPGGB#####&&&&&&#&&&&&&&&\n"
			+"	#####&&&&####&###&#######BB###BB#BBBBBBBBBBBB####BBGGPPPPGGGGPPPPPGGGGGGPPPPPP555PPPPPPPGPPP55PGGP5Y555YY5555Y5YYYYYJJY555YYYYYY55YYYYYJYYYYYYYYYYYYYYY5YY55555555YY55PGBBBGPP5YYYYJY55PPYJ?JYY5B#########BBBBBGGGGGBBGBG555P55PPPPB#######&&&&&&&&&&&&&&&\n"
			+"	###&&&&&#########&&#######B###B##BBBBBBB##########BGGGPPGGPPPPPPPPGGGPPPPPPPPPPP555PPPP5PPPPP5PGP5555YYYY55555YYYYYYYYYYYYYYYYYYYJYYYYYYYYYYYY55YYYYYY5YY55555555555PPPPPPPPPGGGGP5YY5YP5JJJYYY5G#######BBGGGBBBBBGGGGPPGGGP555PPGGB#######&&&&&&&&&&&&&&&\n"
			+"	###&&&&&########&&&&######BB###BBBBBB#########BBB#BGGGBGPPPPPPPPPPGGGPPPPP5PPPP55555PPPPP5PPGPPPPPPP55555YYYYY55YYYYYYYYYYYYYYJYYYYYYYYYJYY5YYYYYYYYYYYYYYYY555555PPPPPPP55555PPGGGPPP5PYYYY55PPB#BGGGGBBGBBGGBBBBBGPPPPPPPGGPPPPPPGB###&###&&&&&&&&&&&&&&\n"
			+"	##&&&&&&&##&&#&&&&&&#######BB##B#######&###BBBBBBBBGGGBGPPPPPPPPPGGBBBG55PPP5555PPPPPPPPP5555PGGP55Y555555YYJYYYYYYYYYYYYYYYYYJYYYYYY55YYYYYYYYY555YY5YYYY5555PPPPP5555PPPP555PPPPPGPPPGPPPPPPGGGP5PPPGGGGBBPPPGGPPPPGGPPPPPPBBGGPPGB###&####&&&&&&&&&&&&&\n"
			+"	&&&&&&&&###&&#&&&&&&&######BB######&&######BBBBBBBBBBB#BBGPPPGGGGGGGGBBG55555PPGPPPPPPPP555555555555555555YYYYYYY5YYYYYYYYY555YYYYYYYYYYYYYYY5555555555555PP555PP555555555555PPPPPPPPPPPPPGGPP5PPPGGBGPGGGGPPPPGPPPPGGPPPPP5PPGGBBBB###&&######&&&&&&&&&&&\n"
			+"	&&&&&&&&#BBBBB#&&&&######&#####&&##########BBBBBBBB##BBBBBBGGGGPPPPPPGBBBGGPPP55PPPPPPPPPP5555555555YY5555YYYY5555YYYYYYYY5555YYYYYYYYYYYYYYY5YYYYY555555555555555555555555555555555PPPGGGP55PPGGGGGGGGGGGGGGGGGGGGGGGGGPPPPPPGGGG####&&&&#&###&&&&&&&&&&&\n"
			+"	&&&&&&&&&#####&&&#BBBB######&&&&&#######BBB######BB#BBGGGB#BGPPPPPGGGGGGGPP55Y55555555Y5PPP555555555YYYYY55555Y55P555YYYYYYYYYYYYYYYYYYYYYYYYY5555555555555555555PPP55PP5555555555PPPGBGP5PPGGGGGGGGGGGGGGGGGGPG##BGPGBGBBGG#&#GGB####&&&&&####&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&#BBBBBBB##&&&######BBBBBBBB###BBBBBBBBBBBBBGGGGGGGGGGPPP5555555555555YYY5P55555555Y5555YY555555Y5PP5YYYYYYYYYY5YYYYY5YY5YYYYYYY55555PP5555555PPPPPPPPPGGPP5555PPPPPPPPPPPPPGGGGGGGGGGGGBBBBBBBG#&#GGGGGB##&&&#B######&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&#######&&&&&#BB####BBBBBBBB###BBBB#BBBBBGGGGGGGGPPP55PPPPPP5555555555PGGP5555555555555YYYY555YYY555YYYYYYYY5555YY555YYY5555555555555PPP55555PPPPPPPPPPPPGGGGGPPPP55PPGGGGGGGPGGGGGGGGGGB######B#&&#GGBBB#&&&####&&###&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&####&&&&&&#BBBBBB#####BBBBBBBBBBBBB#BBBBGGGGGBGGPPPPPPPPPPPPGPPPPPPPPPPPPPP555555PP555555555555Y55555555YY55PP55555555555555555555555P555PPGGPPPPPPPPPPPGGGGGG55PGGGGGGGGGGGGGGGGB###BB########&&&&#B###&&&&###&&&####&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&###&&&&&##BBBBBBBB#####BBB##BBGGGGBBBBBBBGGGBBBBBGGGGPPPGGGGGGGGGGPPPP55PPPPP5555PPP55555555P5555YY555PP5555P5555555PP555555Y5555555555555PPPPPPPPPPPPGGGGGGP55PGBGGGGGBBBBGGGGB###############&&&&###&&&&&##&&&&&&#&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&##BB######BB######B#&#BBBBBBGGGGGGGGGBBBBBBBBBBGGGGGGGGGGGBGGGPPPPPPGGP555555555Y55555555555555555555555555YY555PPP555555555555555PP55PGGGGGGGGGGGGGG55P5PGGBBGGGGBBBGGGB#&############&&&&&##&&&&&&&##&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&####&&&&&#BB########&&##BBBBBBBBBGGGGGGGGBBBBBBBBBBGGGGGGGGGBBBGGGGPPP555555555555555555555555555P555555555555555PPP5P5555555555PPPPPPGGGGGGGGGGGGGGGP5PPPPPPGGGGGGBB####################&&&&&&&&&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&####BB#&#####&&############BBB#BBBBGGGGGBGBGGGGGGGGGGGGBGGP5555555PPGP5555555555555Y555555555555555Y55P55555555PPP55555PPPPPGGGGGGGGGGGGGGGGGGPPP5PPPPPPGBBBB################&###&&&&&&&&&&&##&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"las vidas de los  jugadores con menos de tres corazones ha sido restablecida.\n"
			+"los jugadores con 3 corazones o mas obtienen 4 puntos\n");
			for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){
					if(players[i].getnumberOfLifes()<3){
						players[i].setnumberOfLifes(3);
					}else{
						players[i].setscore(players[i].getscore()+4);
					}
				}else{
					comprobant=true;
				}
			}
		break;
		
		case 2:
			message=("de paso en vuestro encontrais una vieja taberna, donde todos ustedes deciden descansar y dejar sus arams afilando\n"
			+"	@@&&&&&@&@&&@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&@@&&&&&&&&&&@&&@@@@@@@&@@&&&@&&@@&@@@@@@@@@@@@@@&&&&&@@@@&&&&&&@@@@&&&&&&&&&&&&@&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&@@@&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@&&&&&&&&&@@@@@@@@@@@@@@@@@&&@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@&&&&&&&&@@&&&&&&&&&&@@&&&&&&&&&&@@&&&&&&&&&@@@@@@@@@@&@@@#B##&&&&&&&@@@&@@@@@@@@&&&&&&&@@&&&&&&&&&@@@@@@@@@@&@@&&@@@@&@@@@@&&@@@&&&&&@@@&&&&&&@&&@@@@@@@@@@@@@@@@@@@@@@@&&&@@@@@@@@@@@@\n"
			+"	@@@&&@&&&&&&@@@@@@@@@@@@@@@@@@&@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&#&&&@&&&@@&&&&@&&#&&&&@@@@@&@@&&&&&@@@@@@@@@@@@@@@BBGG#&&&&&&@@@@@@@@@@@@@@@@@@@@@&&@@@@&&&&&&&&&GYJJ###BB&@@&@@@@P#&&&&&&&&&@&BB#&&&&&&@#B&@@@@@@@@@@&&&&&&&@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@&&&&&&&&&@@&@@@@@&@@@@@@@@@&@@&&&&@@@@&&@@&&&&@@@@@@@@@&##&&&&&&@@@@@#BG&&&&&&@@&PYG#&&&&&@@@@@@@&&&&&&&&&&@@@@@&&@@@@@@@BGBB&&&&&&&@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&@&&&&&@B?!JP#BBB&@@@@@#P5P###&##@@BJ7P####&GG#P&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@&&&&&&&&&@&&@&&&&&&&&&@@&&#G&&&&&&&@@&&&&&&&@@@@@@@@@@@@&##&@&&&@@@@@#BB&&@@@&@&5YG&&&&&&@@@@@@@@&&&&&&&&&&&&&&&&&@@@@@@@BGBG#&&&&&&@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&@&@&B?!JB&@@@&&@@&P5P###&#&&G7?B&####@#PGB@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@&&&&&&&&#PG5B#G5GBGG&@#JJG#&&&&&&&&&&&&&&&&&&@@&&&&&&&&&B55B&&&&@&@@&##&&&&&&&GG#&&&&&&@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&GGBG#&&&&&&@@@@@@@@@@@&&&&@@@@@&&&&&&&&&&&&&@&&&&&&&GP&@&&#B#&@&P55##&#&#B5YG#####&@#&&#&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@&&&&&&&#P#&PG5JYBGYY&@&&&&&&&&&&&&&&&&&&&&&@&@#GGGGGBBGGGBP5YB&&&&&@#GG&&&&&&B#&&&&&&&@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&G&&@@@@@@@&&&&&@@&&&&@@@@@@&P^:JYYPGB&PJP&&&&&B5G##B###&&&&@@@@&&&@@&@@@@@@@@@@@@@@&&&&&&&@@&&&&&&&&\n"
			+"	@@@@BPGBBGGPG###&BG&&&&&@@&&&&&&&&&&&&&&&&&&&&&&#&&PB#BB##BBBB##P5P#&&&&&#B&&&&&&&&&&&&&@@@@@&&&&&@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&@@@@@@@@@@@@@@&&B?~^:.  .^!J5PGGG&&#&&B#&#BB#&@&GB&&&P??G&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&&@&55PGP5B&&&#&GB&&&&@@&&&&&&&&&&&&&&&&#BBBGGBGB&GB#B####B####BG55G&&&&&&&&&&&&@&&&&@@@@@@@@@@@@@@&&&&&&@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@&&&&&&&&&&&&&&&&&&&&&&BPY7^.:^^:     ^~7YG#&&###&&###&&@?..:^&#.:!5#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	B#P5@&GPBB#&&&&&&B#&&&@@&&&&&&&&&&#BGPG#&&GGBBBBBB&GB##B##BB##&BB#BBYPB&&&&&&&&&&@&&@@@@@&&&@&&&&&&&@@@@&&&&&&&&&&&&&&&&&&&&&&&#&&&#&&&&&&&&&&&&&@@&&&&&&&&&&&&&&&&###&###Y5?:^~::~. .:  ..~JY5###&#&&####&B. .: P& !^.^B@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	PY5Y5&@G5GGGGB##&##&#@@&##&&&&B##PPPPPGPB@&GBGBBBB&BBBBBBBBBB#&BBB##BP5P#&&&&&&&&&&@@@@@@&&&@@&&&&&&@@@@@&&&&&&&&&&&&&&&&&#&&&&#&&&&&&&&&&&&&&&&&&&&&#&&#&&&&&&&#########BY5!^~^^::   .::...!5P#&#&#&&####5. :!.^~5~?B5?~5#&&&&&&&&&&&&&&&#&&&&&&&@@&&&&&&\n"
			+"	JJJJ7!5&B~^~~!?5BB#&@@###&&#G55PGBGPPPGGGB&#BBBB#&&#BBBBBBBB#&#####B#&B5P#&&&&&&&&@@@@@@@&&&@@@@@@@@&&&&&&&&&&&&&&&&&#&&&&&&&&#&#&&&&&&&&&&&&&&&&&&&&&&##BGG5P&&&&&&&&##&#557~~!~!:    .^^. .JPP&####B#B##G7.!?~7J..^#BG5!?#&&&&&&&&&&&&&&#&&&&@&&@@&&&&&&\n"
			+"	Y?~~~::!G#5~^::^JB&@&#&&###Y7YPPPGBBPGGGGG#@#B##&&&@&&&&&&&&@&&&&&##&#####&&&&&&&#&@@@@@@&&@@@@@@@@@@@&&&&&&&&&&&&&&#GGB###&&##&#&&&&#&###&@@&&&&&&&&&GP5555YP&&&&&&&&#B#&5557~~!^.      ^^:~J5P###&#GBBGPY:.7.~GB?:.77PBBB#####&&&&&&&&##&&&&&&&&@&&@&&@&\n"
			+"	77:.~^::.:YBG###&&#P#&#####BPJYPPPPB#GGB###@&#&&&&@@@@@@@@@@@@@@@@&&####BB#&&&&&&&&&&&@@@@@@&@@@@@@@@&&&&&&&&&&&&&&&&&#BBB##&#BB#&##BBBB&&&&&@@&&&&&#&Y?5PP55B&&&&&&&&##&&#PGB?~~^.     :~!!~~5###&##GGBGY?:~7.:P##PY?J7?GB#####&&&&&&&&##&&&&&&&&&&&&&&&&\n"
			+"	~~.:~^^.   :~77!!!YYG&#&#5?YPGP55PPG#&&##&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@&GGB&&&&&&&&##&@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&#B###&##&&&@@&######&&&&@@&&&@&#&BY???JPB###&&&&&#B&&&#BBB55J7~:~77Y55?!JG&##&&BPPBBP:.:Y~!##G&BJBPYYJ#####&&&&&&&&##&&&&&&&&#BBBB#&&\n"
			+"	!^ .~~^    .^^^^:^Y5P&#GJ?JJJJYPPPB&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@&BGB&&&&&&&&&&&@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&@&&&###&BG#&BG#@&#&&@&@@@&&&&&&&&BP?^::~~~!Y5&&&&&##&&&&&&##BBGJ!!~!#B:::YB&&#####&&&&! :~..^5!G!^BJ?JP###&#&&&&&&&&###&&&&&&&#B#####&\n"
			+"	~: .:..     :^^^:!555BGY?J?JJJYJ5#&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&BBB&&&&&&&&&&&&@@@@&&&@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&#GB#GGB&&&&&&B#&&&&######JJ!~~^^!^^7Y&&&&&#&&&&&&@@@@&@GJYYG@&!?7?P#&&&&##BB#G..!..::7~JPJ77!Y&###&#&&&&&&&###&@&&&&&####&##&&\n"
			+"	~~: :^      :~^^?5PJ?JYPGGPYY5YP&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&BBB&&&&&&&&&&&&@@@@@@&@@@@@&&&&&&&&&&&&&&&@&&#&&&#G#BGPP5PPPPB###GB#####B###Y!PGGGGGG5?J555&&&#&&#&&&@@@@@@@@@&@Y#B#7?P##&#&5PBG5~.. .?~?G5J5B#YG&#####&&&#GGBBGGG&&@@&&&@&&#B#&@&\n"
			+"	7!!~~7~::..^!!7JP5P??JJJJY5PGGB&&&&@@@@@@&&&&&##&@@@@@@@@@@@@@@@@@@@@@@&GBB&&&&&&&&&&&&&&&&&&&&@@@&&&&&&&&&&&&&&&&&&#GP5GGP5JJYB&#####GYGP#&B#BBBBBGYG!~!~!7~^?5G55&&&#&#&&&&&@@@@@@@@#&B#G#Y?G&&&&&BYYPPPGY .5~~JGGYPGY5#&##&G5P?P#BBBGPP#G#&&&&@&&&&@&&&\n"
			+"	G^:.!PY!!!75J!5P5B&5?JJJJJJYY5#&&@@@@@@@@@@&BBBBG#@@@@@@@@@@@@@@@@&@@&@&BBB&&&&&&&&#&&&&&@&&&&&&&&&&&#&&##BB#&&###BGG5G#5J#5JY?G@G5P#&&BG5JJGBGGGGGB5BJ...   :7YPPG&&&B&&&&&&&&@@&@@@@@&&#7GJ5B&&&&#GPB?YGG#5~. !5G#5GPP##&##?7YB55GGGPPPG#BPG&&&&&&&&&&&&\n"
			+"	G^7!Y#G~!7Y#J7B##&BYJ555YJJYY#&&@@@@@&&@@&&&#B##BGG&@@@@@@@@@@@@@@&@@&@&BBB&&&&&#B#&&&&&&&@&&&&&&&&&&&&&#GGGB&####B5?!JJJ~77P#?G&YYG&&G?!^^?PPP55PG#GYGY:::^~~^!YPG&&&&&&&&&&&@@@&&&@@@@&B7?7GG&&&#&#GBBBBBB##PJGG55G##&#&#&!!~5#5B#B#GGGGP&GYP&&&&&&&&&&&\n"
			+"	P~5P5!Y@&&BGYG&#&#Y??JYY55P5G&&&@@@@&#@&&&B#&###B#G#@@@@@@@@@@@@@&&@@&&&GBB&&&&&&GGB##&&&&&@@&&&&&&&&#GGGG####BBBBB#5PGYP^P&#:JY^BB!B&P!55YPBPPPPGG&P5PBG5YJ7!??B@&&&@&&&&&&#&@@@&&&@&&&&##B5PG&&&&&BP5YB##&&&&&&&PB####&&&J~:J#PG&G?GPP5#Y##?5#&@@@&&&&&&\n"
			+"	G!#&7JP#&@BGB#PG##PP555YY555#&&&@@@@B&&BBG55G#&#GB&#&@@@@@@@@@@@@&&@&&&&BBG&&&&&&GGBB&&&&&&&@@&&&@####BGGB##B##BBBB###&BB#GG&B#G^?^~^~B##GG#BPPPGBBYJ555GGGPGBJ!JG@@&&#&&@@&&@@@@&&&&&&&&&&BPPG&&&&&#5B&&#&&&&&&&&GBB5Y5GG&~^:?BP5GGP?YY~JYBBYJG&#G#@@@@@@\n"
		    +"	B7BYJ#G&&#5YGGJP#JYPPPPGGPPB&&&@@@@@&##77?YJJYB#BB&#&@@@@@@@@@@@@&&@&&&&BBG&&&&&&BGBB&&&&&&&&&&&@&GGGGGBBB#&#BBGPP5PGBBGGG^PGGGG7~^J~5B##BGPPPPPG&7^JBJ?GGPYJ?^~75&@@&&&&&@&&@@@@&&&&&&&&&&BPPG&&&&&&&&&&&&#&&&&&&GPGPGBBB#^.:!YY555JJYY?YYYJ!?P#&&&&&&&&@\n"
			+"	B?J7P#&@&#PPBPYGP77?JJYJJPB&&#&@@@@@@BB?~^7?JJB##B#&@@@@@@@@&@&@&&&@&&&&BBG&&&&&&#BB#&&&&&&&&@#Y&&GGB#########BP55GGPYP55??G##&P::^^~5GGPPPGP#5P&Y7G#5!5J?7!^7?5??B#&&@@@&@&&@@@@&&&&&&&&&&BGGG&&&&&&&&&&&&&&&&&&&Y5Y?JJJ?P7:^7YYYYJY555?YYJJ?7G##&&#&&&&@\n"
			+"	GP5Y5PB@#GPPPP5PJJYYYY55YYB&&#&@@@@@&BBG7~!~7Y#&&##&@@@@@@@@&&&@&&@@&&@&BBB&&&&&&BGBB&&&&&&&&&@55BB#BB#BGBBGBBBBGP#5YPG#PP55GBGPPGBYYPGPPGBBG#PPB?5&P7J5?!!7Y5Y5Y!JPB#&&&@@&&@@@@&&&&&&@&&&#GG#&&&&&&&&&&&&&&&#&&&5Y5PPPPGG#5J?JYY555555PPPGGB&&GP#&&&#&&&\n"
			+"	GGPP5YG@G5P55YYJP#5555PPPP&&&&@@@BBB###BBY7YG@B&&&&@@@@@&&&&&&&&&&&&&&&&BBB&&&&&&#BB#&&&&&&&&@@#55GBBGG#&&#B&&&&#BBPJYG#G#BGG&PG#G#B#BB#B###5PPGJ?P#PJ5G~:J555PGB?~JPB#&G#&&&@@@@&&&@&&&&#&#GG#&&&&&&#B###&B#&&&&&&&#P5JYYB#GJY??5#5J55Y5Y#@#&&&&&&&##&&&&\n"
			+"	BBGPPPG#GGGGP55P#GJYYYY55P&&&&@&GGBB#&&&###B#&&&&&&@&&@@@@@@@@@@@@@@@&@&BBB&&&&&&#GBB#&&&&#&&&@&GGBB#&&B&&&&&B#@@&##GBB#G#P#G#5G#GG#GBBBB#BPPB&&B#&#&5Y5?~J5GGBBB5Y55JPB5#&&&@@@@&&&&B#####BBB#&&&&&#####&BGB######&BYJJYP&BB#&BJJ5YG&B#&B5#&@@&&&&&&&&&&&\n"
			+"	BBBGGBGGGBBP5G&&&P5PPP5PGG&&&&#PGB#B##&&&&#BB#&&&&&&&&&@@@@@@@@@@@@@&&@&GBB&&&&&&#BPJP&&&&&&&&@&####&&#J&&Y5@PP#BPYGBBGGPG5BB#PGB#GGPBGGPGP5#&#B##BB&#555?5PG##G&&G5PJ5BG&@&&@@@@&&&&BGPPP5GBBB&&&&&&##&&&GPPG#BB#&&&&PG&@@@&YYPJJJ5PB#&#GB#&@&&&&&#&&&&&&\n"
			+"	#BBBPBPPPGP55B&&&BGGBBGBBB#&&#PBBB####&&&#&&#&&##&##&&&&&&&&&&&&&&&&&&&&GBB&&&&&#BBBPG&&&&#P&&@#&B5?Y?555PY7Y55J77?5BBPPGPGGPGBGGGGPGGGGGPYPBBJPBGBG&&##B####PJP&@B5PGG#&&&@@@@@@&&&@&&&&&&#GBB&&&&&#####BGBBBB####&@@@B@@@@@5?JYY55GBB#B#&&&&@&&&##&&&&&&\n"
			+"	##B#P#PPPP55G&&&&GYJJY5YPB#&&BBB###&&&&###&###BBG#&#&&@@@@@@@@@@@@@@@&@&GBB&&&&&&BG###&&&&&B&&&##&BGPJJJJYY5YY55PB&&#BBGPPG5PPGYYPPGGGGBB#&GGGYPBGPG##G5PY?J55GG#@@#G##&@&&&&@@@@&&&&&&@@&&#BBB&&&&&#####G5PG#BBG#&@@&GBB&@@@&GPGGGB####&&&@&&@&&&##&&&&&&\n"
			+"	####B#BBBGGG#B5#J~~~77J?!5#&&@&&BBB##&##&&&BB##BB####&&@@@@@@@@@@@@@@@@&GBB&&&&&&BB#####&&&&&&&&B&&#BGP5YJ5PBBB#&&&&#&&&&&&##BB&&#&&&&@&&&&&#BPP7~:.:7!5#GBB#&BB#BGBB&&&@@@&&@@@@&&&&&&@@&&#BGB&&&&&&####B5PP#GBB#&@&G5G5#@@@#PGB#GGB##&&&@##&@&&&##&&#&&&\n"
			+"	####BBB&#PGPGY?JJ?JJJJY?!P&&@@#&#BB#&##&@@&&&&@&@#B##&&&@@@@@@@@@@@@&&@&BBB&&&&&&&#B##GGG#&@&&&#&&GY?JJY?7?JPBB@@@@@##&&&&&BGB#&&&&&&&&&&&&&##G#5:..:!75#&&&&&&&&#B&&@@&&@@@&@@@@&&&&&@@&&&#BGB&&&&&&####G5PPBGBB&&@@&B&&@@@@#GB#@###&&&@@&5#&@&&&#&&&#&&&\n"
			+"	####PPG##GBGGP5B5Y55YJJ??PB&@#PB#&BG#&&@&&&&&&@@&BB##&@@@@@&@@@@@@@@&&@&GBB&&&&&&&#####BB#&&YPP##B5YYJ!~::!7?YJP&@@@&#&&&&&BPG#&&&&&&&&&&&&&&#&&&&Y77?YB&&###&@&&&&@&&&@&&&&@@@@@&&&&&@@&&&#BGB&&&&&&&&&#PPPPBBBB&@&&&BB&@&@@@#P&@@@@@@@@@@Y&@@&&&#&&&#&&&\n"
			+"	####BB#BBBBGGGP&P5BGPYYYJ5B&@PG##@@&&@@&&#&&&#&&BGB##&@@@@@&&&&&&&&@&&@&GBG&&&&&&&###B###B&&&&&@&&BYY!:. .^7!^7B#&&&&&&&&&&BPG#&&&&&&&&&&&&&&&&&&&#BB#&####B##&&&&&@@&&&&&&@@@@@@@&&&@@@@@@#BGB&&&&&@@@@@BGGBBBBB&&GBBBBBBB#@@BP&@&&&&&&&@GG&&&#&&##&&#&&&\n"
			+"	####&#BBBBGGBGPB5J5PPP5?!5#@@P#@&&&&&&@#Y!JY#&&&@&&&&@@@@&&&&&&&&&&&&#&#BBG#&&&&&&######B&&&&&&&&&#5Y~~~~!!~JY5BB&&&#&&&&&&BPG#&&&&&&&&#####&&#&&&GGBBBBB###B##&&&&&&&&&&&&&@@@@@&&&&@@&&@@#BGB&&&&&&@@@@&BGB####&#GG###&#&&&&BP&&&###&&&&P##BB#&###BB#BBB\n"
			+"	######BBBBGPPGGPGG#BPPJJ5G###B###BBBBBBG5555GBBBBBBB#BBBBBBBBBBBBBBB#Y!~5BG#&&&&&&&#####BBGG##BBB#BBBBBGBBBBBB#BBBB#BPGGGGGGGGBGGGGGGGGPPGGPPGGPPPPPPPPPPPPPPPGGPPGGGPPGGGGGGGPPGPPGBBBBGG#&#BB&&&&&#GGBGGGGGGGGGGGPGGGGGGBBBBBGBGBBGGGBBGGBGGGGGGBGGGGGGG\n"
			+"	&&&&#BBB##BBBG#BGPGGGGY?5G&&#BBGGGBBGBBBBBBBBBBBBBGGBGGBBGBBBBBBBBGB&&BBB#B&@@@&&&&##&&&&#BGB&&&&&&&&&&&&&&&&&&&&&&&&&BPBBBBBBBBBBBBBBBGPGGGGGGGGPGPGGGPPPPPPPPPPGGGGPPPPPPPPPPPBBGBBBBBGBBB#BB&@@@@#BBBBBBBBBBBBBBBBBBBBBBGGBBBGBBBBBGBBBBBBBBBGBBBBBBBBB\n"
			+"	BB#####P?Y5?GBY5Y7B##BGG##BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB###BBBB#&&&####BBBBBBBBGGGG&&&&&&&&&&&&&&&&&&&&&&&&#GGBBBGBBBBBBBBBBP5GPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPGBBBBBBBBBBGGBB#&&&&#BBBBBBBBBBBBBBBBBGBBBBBBBBBGGGBBBBBBBBBBBBBBGBBBBBBBB\n"
			+"	55GGP5Y5YYJJYJ??7?YJJJ?YGGPGGPPPPPPPPPPPGGGGGGPPPPPPGGGGPPPPPPPPPPGGGPGGGGGPPPPGPPGGGPPGGPPGGGGPB#BBBBBBBBB#BBBBBBBB##B##GPBGPPGGGGGGPGB##GBGBBBBGGGGGGGGGGGGGGGPPGGGGGGGGGPPGGPGBGGPPPPPPGGGPPPPPPPPGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG\n"
			+"	5BB5J?JJJJYYJYJJ5GPPGGGBGGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBGBBBBBBB###BBBBBB##B#BB###BBBBBBBBBB#&&BPPPPPPPPPPPP55PPPPPPPPPPPPPPPPPPGGPPPPGGGGGBBBBBBBBBBBBBBBBB#BBBBBBBBBBBBBBBBB###B#B#BBBB###B########B###BBBBBB\n"
			+"	###G5YYYYJ5YY55#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@&#B&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	&&##&&&&&&&@&G?75??J#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&@&&@@@&@&&&&@&##&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&@&&&&&&&&@&&&&&&&&&&&&&&&&@@@@@@@@@@&&&@&##&@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@&&&&&&&&&&&&&@&&@&@@@@@\n"
			+"	&?YYJ#@G&B5PPJ~^~7?^?@@@@&@@@@@@@@@@@&&&&&&@@@@@@@@@@@@@@@@@@&&&&&&@@@@&&&&@&##&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@&#&&#&&&&#&&&#&@&&&&&&&&@#&&&&&&&&&&#&&#&@@@@@@@@@B##&@&##&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&B#&#BB&&&&&&&&&&&&#&@@@@\n"
			+"	&YPG5&&B&YJ5BBBG5?~~~@@@@&@@@@@@@@@&&&&&&&&&&&@@@@@&@@@@&@@@@@&B##B#&&&&&&&@##&@@@@@@@&&&&&&@@@@&&@&@@@@@@&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@&#&&&&&&&&&&&#&@&&&&&&&&@&&&&&&&&&&&&&#&&@@@@@@@@&JB&&@&#&&@@@@@@@@@@@@@&@@@@@@@@@@@@@@@&B#&&&G######&&&&&#&#@@&&\n"
			+"	@G5JG###P5PPB#B#GJ?!JG@@@&&&&&&&&&&&#BBGGBBBB&@@@&BB#&&@@@@&&&&####&#B#&##&@@@@@@@@&&&###BBGGGGB#&&&&&&&@&#&&&&&&#######&#&#B&@@@@@@@@@@@&&&&&&&@&&&&&#&@&&&&&&&&@&&&&&&&&&&&&&&&&@@@@@@@@BY&&&&@@@@@@@@@@@@@@&@&#@@@&&@@@&&@@@@@&##&#&GPBGGGGGGGBBB##@#&&\n"
			+"	@@&5G&&&&@##PPPPGJ?~?YB&&&&&&&&&&&&&&&#######&&&@#GB#&&@@&BB##&####&&&&###&@@@@@&&&&GGBBB####BGB##B#BB#B&####BBB######&&&&&&#&&&&&&&&&&&&###&###@##&#&#&@&&&&&&&&@&&&&&&&&&&&###B#&######&?5#GB&&&&&&&&&&&&&&&&##B##&&&&&&&&&@@&&&#&#&#GGBBG#B5PBBBBGB&&&&\n"
			+"	&&&B#BPGBPY#BBBGBBPJG#&&&&&&&&&BGG#&&#####B#####BPG##B&&@&##&&&&&&&&&&&&###&@@@&&&&#BBBBGB####&&&&&&########&&#&&&&&&&&&&&&&&&#&&&&&&&&&&#BBBBBB@#BBBBB#&BB######&###&####&&BBGGGB###B####?G#GG#&#&&#####&&&&&###BB&&&&&&&&&#&&&&#B##&#5GBGBBPGGPGBBGY#&&&\n"
			+"	B&&&&J??7J?P&&&&&&&&&&&&&&####G5YPB&&&&&&&&&&&##G5PBBG#&&&&&&&&&&&&&#&&&&#&&&@&&@&#GPGBBBB######&&&&#&&&&&&&&&&&&&@@&&####&&##&&&&&&&&&&&#BBBB#B@#BBB#B#&BBBBBBB#&BBBBB#BB&&B#B#B#&##B#B#5?B#BB&&&&&&&#######&&&&&&&@&&&&&##BB#&&####&B5GBGGGGBGBGGGGBGBBB\n"
			+"	##B&PYJ5YY?Y&&&&&&&&&&&@&&&####B##&@&&#&##&&&&&&&&&&&&&@@@@&&&&&&&&&&&&&&##&&&##&&#BGGGGBB#&####&&&###&&&#&&&&&#BBG@&#&&&&&&&&&&&&&&&&&&&#BBB#BB&#BBB#G##BBBB###@&B#B###BB&&BBB###&#&#BBBPB##BB#&#####BBBBBB###&&&&@@&&&&##BBB#########GBBBBBB###B###@@&&&\n"
			+"	&&##YPJ5GGGB&&##&&#######BB###BBBBBBBBBBBBBBBBBB######B#BBBBBBBBBBB###############BBB#&#B##&#&&######B#&##BB#&5G##&&@#&&&&&&&&&&&&&&&&&&&#BBGBGG#BGGGPPBBGGGGGGB##GGGBBBGB#BGGGGGB#BBBBBGPGBGGGB#BBGGGGGPPPPGGGGB###B##BBB###B#######&&#&&&#&##&##BBBBB#B#\n"
			+"	@&#B&#GB#&BBBBBBBBBBBBGGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB####B##&##&####B#BB#B&#BBP5BGG&&@&&&&&&&&&&#&&&&&&&##GPGPPPP##GGGPGB#GGGGGGGBBPPPGPPPG#BGGGGPBBBBB#BPP##BBB#&#BBPPPGGGGGGBBGBBBBGBGGGBB######BBBBBBPPGBBBBBBBBBBBB#BPP\n"
			+"	@&B&&&&#&#BBBGGG5J5B#&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&##&&&###B###&&&#&#&####BBB#&GB##G#&&@&@&&&&&&&&&&#&&#&&#&#&BBBBBBB&&BBBBB#&BBBBBGGB#PPPGPGGB&#BBBBB#&##&&BG#&&###&&&###&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#PGB#&&&&&&&&&&&&#PG\n"
			+"	5Y5@&5JG&#GBG??!~~!!!?YG#&#&&&&&#####BBGPPPPGB##&&&##&####&&#####&&###&####&@&##@&BGBBB###&&&&#&&##&###&#&BGBBB&&&&&&#&&&&&&&&###&#&&#&&&#B#BBBB&&BBBBB##GGGPPPGBBGGGGBGGG&GGGBBB&&##&#GB&&####&&&##&@&&&&&####&&###&##&###&#&&#####&&##&###&&#####&&#&##&\n"
			+"	?JP##BBB5Y555YJ77?777?775GGGPBBP5Y5PPPB####BG~!#&#&#######&&#####&####&&##B&@&&&@&BBGGBBB##&###B&&#&#####&&#&&&&&&&&#&&#&###BB###&#&&###&#BBB#BB#&BBB#B##GGPGGGBB##&&&&&&&&#BGGGB#&&##BP&&&##BB#&&#&@@&#&&#####&&######&###&#&&#####&&BBB##&#&##&##&&#&#B#\n"
			+"	5YJYY5PGGGBPYYY55G55PPPPPP555P!^!YPGBBP5PYYJ??7G&#@#######&&#####&######B#B&@@@&&&##BBB&######&&#&#&&#&##&&&#&&&&&&&#&&#&&&&&&##&&#&&###&#BBB#BB#&BBBBB#&BBB#&&&&&&@&&&&&&&@@&##B&&###GG##GPPP55PBGPGGPG#&&####&&######&###&#&&&##&#&&###&&##&#GG##&&#####\n"
			+"	PGP5B&&@@@@&PPBGGGGBBGGGGGGGGGJ5GPPGGG5J??YPPYG@&@@&&&&&&&@@&@&&&@&@@&&@&#B#@@&&&@@#BBB&###&&&&&&#&&&#&&&&&&&&#&&&&##&#&&&&##&@@@@&@@@@@@&&&&&&&@@&&&&&&#B#&&&&&@&&&&&&&&@&@&@&&&&@&&G555GBBGBBGBG#BGP5YYP&@@&&@@@@@@@@@@@@@@@@@@@@@@@&&##&@&&GG#&&&&&@@#B\n"
			+"	?55B&&#BBBG5B&&&&&&&##&&&&&&&&&&&&&&##BP55PGP?7&@&@@&@@@@@@@@@@@@@@@&&@@&###&@@@&@@&&##########&#&####&&&&&&&&&&&##&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@&B#&@@@&@&@&@@@&&&&&&&&&@&@&&&JPGBB###BGBBBB#BBBBBG###@@@@@@@@@@@@@@@@@@@@@@@@@&##&&@@@@@@@@@&&&&##\n"
			+"	?Y777!!!!!!!G#######&&&&####&####&&##GGB##GP5?~!&@&@&&&&&&&&&&&&&&@&G&&&&&&&&@&@&@@@@@&&&&&&&&&&@&&&&&&&&&&&##&&#&&@&@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#@@@@@&&@@@@@@&&&&&&&&&@&@#5BB######&######&####&&&#G@@@@@@@&&&&@@&&&&&&&&@&B55GB&&&&@@@&@&&&B5PG#\n"
			+"	7JY7????JYJJG#&&##########&&&&&&&&#&&#BGGGGGGYJ~!#&&&&&&&&&&&&&&&&BG&@&&&&&&&&&&&&&&&&&&&&&&&&&&@&&&&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&@@YG@&&@@&J&@#&&@&&&&&&&&&###&&&&&&&&&&&&&@&&&&&&&&##&@&&@&BGGB&&&&&&&&&&&#J7YP#&&&&&&@&&&&BY?YG#\n"
			+"	?J####&&&&@&YB###&&&&&&&&&&@&&&&&&##&&#BBGBBBBBG5PB&&&&&&&&&@&&&##&@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&@&&#&@@@&G&&#@&&&&@&&##GPB&&&@@@@@@@@@@@@@@@@@@@@&&#BBBGPGG##BGGGG#&&@&&&#GGGB#&&&&&&@&&&&BGGBB#\n"
			+"	J7#@@@@@@@@@#B##############&&&&&&&&&&&&&##B#BBBB#BB#&&&&#B&&&&&@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&&B&#&&##GG#&&&&&&&&&&&@@@@@@@@@@&@@@@&#GGBBBB#######B&&&&&&######&&&&&&@&&&&######\n"
			+"	GY#@@&@@@&&@&##&&&&&&&&&&&&&&&&@@@&&&&&&&&&&&&##B###&&&&&&&&@&&@@@&&&&&&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@&&&&&&&@&&@&&&#B##&&&#G#&&&&&@@&&@@@@@@@@@@@@@&@@@@@&#&&&&&&&&&&@@@&&&&&&####B#&&&&&&@@&&#B#####\n"
			+"	##@&#BBB#B##5P#&&&&&&&&&&&&&&&&&&&B##BB&&&&&&&&B&&&@@@@@@@@@&B#@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&###&&G#&&&&&@@@@@@@@@@@@@@@@@@&@@@&##&@@&@@@&&@&&&#&&&&&#B###&&&&&&&@@&&&B####&\n"
			+"	@&&&##&&&#&&#PB#B########&##&&&&&&@&&#&&&&&&&&BB&@@@@@@@@@&&B#&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@&&&&&&##BG&&&&&@@@@@@@@@@@@@@@@@@@@@&&#&&@@&@@&#&&&##B&&&&&#####&&&&&&&@@&&&#####&\n"
			+"	@@&&&@&&&@&&@&#&&&&&&&&&&&&&&@@@@&&&&&&@@&&&@&#B&@&@&&&&@@&#B##@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##&&&&&&#&&&@@@@&&&&@&&@&B#&&&&&@&&&&&&&&@@@@@@@&@&@&#&&&@&&@&#######B&@&&&#####&&&&&&&@&&&&#####&\n"
			+" todos los jugadores reciben una bonficacion de 1 punto y 1 corazon extra\n");
			for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){					
						players[i].setnumberOfLifes(players[i].getnumberOfLifes()+1);
						players[i].setscore(players[i].getscore()+1);
					
				}else{
					comprobant=true;
				}
			}
		break;
		
		case 3:
		message=("habeis sido asaltados por un grupo de bandidos mientras pasabais por una vieja ruta comercial.\n" 
		+"todos aquellos que tenian fuerzas para pelear lo hicieron\n"
		+"los defensores de la caravana resultaron heridos y en la huida se perdieron algunos objetos\n"
		+"	:.............................    ................................::......................:.::.:::.........:. . .:::::::::::::::::...............:.........................................   ............................................................................   .............. .............:::\n"
		+"	....................... ........................     .....................................::................:::..:::::::::::::::::...........................................................................................:^:..........................................................................::\n"
		+"	........................................... .... .  ......................................:..:::....................:..:::..:::::....................................................................................:^^^~!7??J!:.........................................................................::\n"
		+"	................................................... ...............................................................::......::.................................................................................::::^~~!!!!~^^:..............................................................................:\n"
		+"	....::........................................................................................................................................................................... .....................:::^::^^^^:^^:............................................................  .             ...........\n"
		+"	....::........................................................................................................................................................................... ..    .......:::^~^^:^^:::..............................................................     .                       .....\n"
		+"	:........................... ... .................................................  ............................................................................................     ...:::^^^^^^^::................................................................                              .         \n"         
		+"	:........................... . ........ . ........ ..................................  .... ....... ....................................... ......................................::~~~^^!^::...................................................:..............                                 .  .........\n"
		+"	:::..................... ....  .......................................... ... . .... ....   .....     ..........................................................   .... ...::^~^^:^~:.......:::::::::::^::^^:^~^:^:^:^^^::::::::^^^^^^^::::..............                                  ..............:::\n"
		+"	:.....:.............       ......... .. .....................   .....         .            ...      ...................................... .................... .   ..::^~~^^:...^^.. .................. .........   ....................  . .......^..                             .....................:::\n"
		+"	:...................      ..  . ...  ...      .  ...........   ..                   ..   .. .    ... .................................... . .................. ...::^^^^:.....:!~.......................................................................                      .......... ................:::\n"
		+"	.............  .....  . ....  .  ... ....     .  ..... ..       .           .   .  ....           .    . ... .............................................:^^^^^~!~^^^^:^^^^:...... .............  ..............  ..  ...........................::.....       .........................................:::\n"
		+"	............... .........    .  ...          .      ...                                            .  ..... ..........................  ......  .......:~~~!^:::~~~^::::.....  ...   ......  .. ...  .. ...........    ....  .............:....:::.:....:...................... ........................::::\n"
		+"	.....   ....... .. . ..      . .                                                                       .....................................   .:^~~~!7^:..   .      .    ....       .                  .... . . ...      .  .    .  .    ......::....::..........::..::^:.....:.............:::.....:::^~^^\n"
		+"	 .  .   . .  ..   ..                                                                         .          ...............................       :7!?PP~~^...:::^~!~~~!7?J?J?JYYYYYYJJJJ???777!~^^:::...       .    .                            ...::........::....:...::::...:..:...:::::..:::........:::::::\n"
		+"	 .  .   . .. ...   .                                         .                                          .....................  ...    ....::?YYPJG&#JJ~?PGGGBBB#B###&&&########&&####BB##B#BBBB#BPP5J?7~^:...   ..                      .. ......::.....................:::::.....::.....................:^~\n"
		+"	 .  .  .  .  .    .                                            .               .         ..         ..  .    .........           ..^!?PGBG55P?7JP#&#7Y?G@&&@&&&&@@@@@@@@@@@@@&@@@@@@@@@@@@@&@@@&&&&&&####BG5?~^..  .                 .  ..   .. .^:...................:.::..............................^!7?\n"
		+"	..      .. .          .                                                                   ..... .......  .       .             .!JG#&&#5777!~~^^G@&B&@@@@@@@@@@@@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@&&&#G57~..             ... .  .. .     ::.................:.:.................................:^~~\n"
		+"	..     . ...        .                                           ....                 ..   ..:::........                      .~YBG#&G?~~~~!??J5G#&&@@@@@&&&&@&&&@@&@&&&&@&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&P?7:.  . ...................    .^.....................................................:::^!\n"
		+"	......  ...                                                  .                           ....:::^:...:.                    .^7PBP5BJ~~!!!?5GG#&&&#&&##B###&########&&&#&&&#&#######BBBB#&&&&&&&&@@@@@@@@@@@@&&&#BYY?:.  ................... .....::...:................................................:::^~\n"
		+"	........                                                   .. . .                          ...::::....                  .^~^!?GP7YJ?5GGGPG#####B####&#&&&&&@@&@&&&&@@@@@@@@@@@@@@&&&&&&#&&&&&#&&&@@@@@@@&&&&#BGPGYYJ^:...................::...........::..................:::::....::................:::^^~~\n"
		+"	.....                                                 .. .... ..                          .........                   .^~~^~?YPJYYGBBBB#&&&&&&&&@@@@@@@@@@@@@&@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&#&@&&@&&&&&&BGGGBYYJ7!^.  ... ...::: . .......:....................................................:::::^!~~\n"
		+"										      																		  ..:^~^^^^~755B&JYP55B&&&@@@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@&@@@@@@@@@@@@@@@@#JJP&#&@@@@&&&&##BB#G?J77J!:.......^7YP?~:....       . ......................................................:::\n"
		+"																						    .   .           		.^~^^~~~~^!JPB&&P!77?JY&@&&#PYJJ5#@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@&@&&@@@@@B^  .^7Y#&&&&#&#GGB#G!~7J?Y^.... .~~YGB#GJ^...    ............... .............................................::\n"
		+"	.                                                                                                ..  ...      .::::^^^^^^^^75P#&&#777!J##5!^^^~~~~5PJB&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&P:^..   :.J&@&#GB###G7!7G#!^..    ~~YB#B^:.....   .::::.........^!!^:.    ...................       .........:::::\n"
		+"																									...  ...    ::::::::^^^~~!?PG&&&@P??5BJ!^!!7!!JGBB5JB&@@@@@@@@@@@@@@@@&&@@@@@@@@&BP5GB#G:    ..  ..^YBBGYYJ??JPGPGB!:....:^~7BBP^:...............  ^7!?YJ5B#PGY77?~::................. ...................:\n"
		+"									 																	.......  ..:^::::::^~^^~~?PB&&@@&?J5YJ7!YPJYB&&&@&@@&@@@@@@@@@@@&@@@@@@@@&GY7~!~~:.:~?7!~...:^^^~~77!~!77?5GGY!JGB7....^!7JGJ^............ ..   ..7J5BG?YPGPB&#BG5J?!~:..... .... .......................:\n"
		+"																						.....  ..... ......   .:::^^:::^^^^~~~!J5B&&@&@#PYJ?75&&#BP5555PGBB5J5GGGBB#&@@@@@@@&&5!^:^!7!7~!~7??!!^^~~~!JYPGP5PGGBPY7!7~PBB7:^~!7YPY:.....................:JYGGPPPPY?JPBGBBPBGPY?!^:::.:^^.........................\n"
		+"																			....      .... ............ .      ::^^^^^^^~^^^~!~7J5#&&#&@@#PGG#&#G55YYYJJGGPGBJJ77?~77JB@&@&BP7:.:!YJ~..:YY!~^:^^^~~?5PYYG###B5?~~~!?7!55?!~^!?55^.. ....................:?55YP#BB#P?YJJBGB#P?Y555Y?JPYJ??!:......................\n"
		+"				 						 ...  .           ............:::.                 .........   ......^:^^::^^:^^^~!~~~~75#&&&&&&@BY5B&#7??~!?JYGPY7PPPPGBP5~:!PGYJ77~7Y5P!.  .B#5J7?JJ5GBBBP5JP###BG?!!^77!JJ!7777YPP7:..............    .. ...:. .:~B##&#55YPGYGG5YYJY5PGPG5YJ???7^.........:.:.::..:::\n"
		+"		 		 .........                  .... ...........                                         ...... .^^^^...::^^^^~~^^!YYG#&&@&&&@BYY#BYP#GYJJJ?YP7?PPP55BBGJ7^~JJY5PPYY~    .BGBBB##GB?7~~?5P##BB5?~~~:~JBG~~JGGGY~:........ ...................!PGGBBGGBBB&PY55GYYGJ~~~JYJYY????7!!^..........:::::::^\n"
		+"	:::.....                          ..            ....                                           ......   ^^:::...:^:^^^~^~~YPYB#&&&&@&@&PYB!^Y#???!^:?!:?Y5YGGP#B7!~~~YP55Y57:. .. J55YY5Y^Y^^~~5PBBBGJ~::^?G&&&BJ5G#J:............................ . :?GGB&&&&#B5YG####BG5J?!GPPYJJ???!!J!......:.:::::.::^~\n"
		+"	..... ...      .                  .....             .:.                           . ..................::!::::...::^!^^^^~^PBYG##&&&@@&@G?J!7#57777!!77!?777!??B#J!!~~~?5PP?::. .. 7YY7::.7Y..^JBG5?!::!JP#&#&BPP5#BB!................  .....:........  .?#PB#&&#!5BBBGJ!J?~?7#&&BP555?75Y~........::::^:::^~\n"
		+"	..........   ...   .            .........     ..  ....:::...........  ..     ..   .    ......   .....  :~:::::::::^^~^^~^~BPPGB&&&&@@@&G!!7BG?!~~^^^~??~~Y?J7JGB#5YPPYJ5GY^...:::.^!. .^~PB!YYP7~^ .7PB###GGJ7BJJBGB7....::..............::.....::^:::...5YJ^YBB75##&B77PP5P55G#&&&BP5BBJ7~^:....::::^^:::^^\n"
		+"	........  .....         .......^::.  ...           ........         .         .  .     .^:.    .......:!!:::::::::^!!::~^J&YPBB##&@@@&&B~!GY~~~^^^^^^^!7~JPPYYPB55GP5GBY5BP:....^!!?Y5J~7J~:!^~77!7GGPJY5?7~!G#!P#BB~::::.............:.:::....::::::::..^BP^^:?7P###BJ77?5PPPPPGB##BG5GGY???!:...::::......\n"
		+"	..........::.. .:7!^.. .........            . ...     ....:::...           .......    .:.     ...   .:^~~:::::::^^^!^:^~^G&5PBGYP&&&@&@P~G&G!~^~!!!?~~7?7?B##75YPP&&&#####B!    ..:~!:  ...^~?J57:~PJ^7J: .~?&BJ#PGG~:....................::::.^^^~^^^^^^:5B^..^7B##B##GY7YY5GGGGPPPPGPG#GG5~:...:....:.....\n"
		+"	.............  ^!!JGY~~:...:..                 ..............:^:...     ......  .   .:.     ....      .~~.::::::~^^!^:^^~BPY5PGGY&&&&@&75&&&B?!~~^~~^!7Y?7B#&JGP?J5&&&&###5?~^:.:^~^^^^~!!!7J??7!!?!:7J: ^!~B&#B5:PP~:::.............:......:~^7?7!~::...!7J~..:7J#&####&BGGGB##BB#B##B&&G7!7^:^^^:::.:^::..\n"
		+"	... .......   .:~??55JY5Y^.                      ....      ......:::.................    .  ....      :!~::^^:^^~~~!^^~^7#G5YYGBY&&@&@YJ&&&&&Y!7J5J7J5PJJ5P&&#J5G?!B&&&&#B~~~~!!PG55?!~~^^^~~!!!!!!~?J~.!77#&##G:.7P~.......::::..:..........:^^~:.........:Y...^~7?B###########&&G!5BB##&B!^^:::^::^::^~:..\n"
		+"	........  .. .~~!Y5?77Y5#G~.                        . ..     .......   ..          .   ...  .         :~~.:^:::::~!!:^~^Y&&G5PBB5&&@@G7&&##&&?^~!!!?Y55YYG5G#&&Y&&P?PGBGY7..   .75JJ55J7~~~!!7JYJJY5PGGY?J#&&&#5. :5~......:::::::.:::.....::..~~:..........77:.^!!^!5BPGBBGB#&GJ5PPG5P#BBBJ::^^^~!!~^^^^::.\n"
		+"	.............:!!YBJ~~^^J5B5~....             ..      ..   ......                   .   .             .:~~::^~:::^~~!^^!^G&&G5B#B5&&&&7#B?P&&&P!?J?77YGGBGB#P#B#B5#&J7PP5J5^ .^^^J7!77?YPJ5PPGPGBBBBGB##BGPYJJ?!!..~PP5J!:.. .^..::::.:.:::.^77~!7!~^:........J..:!!~!!YJ!PP75BBGYPJ55JP#BG#J:.::^~!7!!!~^^:.\n"
		+"	...  ..   . :~JGG5YJJP5?JPP7.  .....              .....  .:....                          ..  .    .  .:!~::^7^:^^!77~~!!5#&BYGGBG&&@#YG!~B&&&57!!?Y5PPBBPPPGB&B&GBBG775G555J5PY?7~^77Y5?Y5YY5PBBBGP5J??~^^::^~^^.:7JJPBBB?7J!:.::.......::::!?7~~~!!^:.......?7.^?!...!??YGGP55555YY5J5#GB#Y:..:^^^^~~^^:...\n"
		+"	.......     :!G##5!^~~!?77J5?^..   .....       .. .......:....                   ...  .        ...   .^!7~^:??~^^!Y7!!~77?JJ??YP&&&@BP?75B&@&?J!!~~77?5B#BJY5P&B&P&&J?~7GG57!:::..:7??J??PB##GJ~:::::!7!~~!J5PGYJYY55PGB#&&#5:....:^::..:^!~!^:..^:::^::.....:5:~P?...^7??YGP5Y5PJ55P5PBGBBG7^..............\n"
		+"	.... ...~.. .~B#P:. .:...!JPG!^^^:. .::..     ... . .........                   ..                   :~7J!^^7P!~^~57!!~!5PBY7!!P&&&@G5??G#&@&?7?!~~~~^~JG&&#BJ5&G#G&B?J7!7!?!~...!55J?JPPPBP7:.^^^:~55J?!7?J?J?J~:::::^~~J#@@G:....^:~^^!7?!^...:~~^^~~~::^~!:!?^5!...7!7775PP55GY5P5PBBP#BGGPJ7~^..........\n"
		+"	.......~^.  ^!B5^   ..::.:7JB?...:^~!~:.:..............                        ..                    ^~7?^^^~YY~~~77^~~~JB#YJ?PB##&@&#5JP&@@&J?Y?!~!~^^^^!Y&@G5#&B#P&G?J!~^^~~~~~~:.:::YPY7^.!J7~:7BPJJ?77?7J5PB^         .J&&B?!^::.:!~~~^::::.::^~!!77!!!~:..J!P~..~7?!?7JP55PPP5P5GBBGPGBBBGB#PY!:.......\n"
		+"	~?J~:.:~.. .:YYJ^...   .:^?JG5~...^^:::........ ..:..                        .                       ~~??:^^^~JY~~?7^~7~~J55P#PP##&@@@&#G5#@&7BPG!5!^~^~:^5#&&BYBB#BGP7!!~^^::^77^ :~:5?:~:.YG!::7B&5!^~Y7~!7J5?.         ~^BG555Y7^^^~~~:....::^~~!77?!~~^^^~~Y5B~.:77?!77?PPPGBBBBB#YY#BGG5?!~!~7~:......:\n"
		+"	???J!:!:...:^G7~...:^:....^?JG7..:^^.::...........                    ...                            ~:77^^^:^~JY7J5Y~~!7!~!?PP?GB#&&&&&&GY&&B#&G!BJ^^!???P#&#GJ5G5#55!~~!~^^^::^7?7:J7:::^5&?!~!5#BJ~~5?!~^^!5~.         .:?!~~:....^...:~^::^~7!77!!!^^!?JYGPGGP7777J77?J?YJJYPBP5GB5^~5GGPY!~^:..........\n"
		+"	77!~~^^.^^:^^?!^:7YJY555YJ!?!5BJ!~^^:::::....::...              .    .                              .~:^~^:~~^^^~!7YGBY?!77JGB5YP#&#&&BGGGGBG&&###&J.:^!!?5B&&BB#PBG&J?!~~~^^^^::~PG5Y:~:^Y#G7Y!?7GY!~5^^!^^^5#B5:          .7#BY:...::::~77~~!7!!!!^:..:7YJJ5GBB#Y?7JY!77?~~?JY5GGPGBPY~....:::^^:.........\n"
		+"	~~^:^.::....:^^^::^:~YJY5YJJ?J?^:^::::......:.                      .              ..               ^^::^!?^:^~7J???JYGGGB###&5JY##BPPYJ?J5PGBGBB#&G::.^^^~?#@@&GYBB5#7PP?!^~~^^?P##B~~^~JB#P??~J?5?^J^.7!!^!BYYJ:.  .   :. ~Y@@Y:.   .:!!!!7!!!^^:.. .^!^^7Y55?!PGJJ?!!77?7!??Y5GPPGGGP5J~..   ............\n"
		+"	!!~:~^:~^~~~~!!!~^:^^~~~^::~~:.. .::::...                          ..          .        .           ~7YB##GYYYPGBB#&&&&###&##BP5Y#PGY?YYJ5GB#&#B#B&&^..^~^~^?#@@Y?#&J##7G?!~^!~!7Y##G~^^YYPG5J^7Y57J7? .JJ55GG:.~:..    .:!G@@@P:.....~~^:::::::^~~~^^!~..^7JJPJ~!PB57!777??~7?JPGBBP5P5YP5J7~^::...........\n"
		+"	!^!7!^^7!~!~~!!!77777!~!!^^:::::::::...                   ...    . ..   .      .      ..           .G#&&&&&@@@&&@&&&#BGP5G###&BJJ&B######B##&&#&#&&&B~^~~~~^^Y#&&JY#JG&B!^^~^~~#&Y?G?:^7Y~J5PJ!G5J7YP: ~~YPY!7::.       .7&@@@B:.....::.....:!77?5P5J55Y?J?Y5GG7J7J5GY!7?7!?!!??5G##BBGGG555P5?7!~:.........\n"
		+"	7!?J7~?Y?!7~!!!!77555?~^~^^^^::::^.. ...                .....                                      ^PB&&&@@@&BBBBGBGGGG#&&&&@@#JJ&P#&&&&&&&&&&&&&&&&#P~~^^^~~~J#&&BGP#&G~~:^!PP?PBY?^.:!~YYY5JPP?~!YG~^JY7.^.:..       .^YBBB5:........ ....... .:J5YJPGPPYJ5BJ7YJ7JG#P?77?Y!7YY5B####BBGPPGGY?7~^:^.......:\n"
		+"	~~^:~7JY?7~!!!~!!!YJYYY!^~~!~^:^::.......              .           .    .  ..               .      ^7P&@&@&#GGBGBP&&#&GB&##BGBPY?BB##&#B##&&&&##BB##G&5^~~~^^!!5#&&&&&&!:^~!G5BG7YGP~:.:!YPP5PB::7P~7YYYPY~^~^..       .!!..................::.:~~^:. :JY???5GY5JJY!!5G5JJ?Y!!?YPB####BBGP5PY?7^^:::.....::^\n"
		+"	?^:^^^^!!~~!!~~!!57~55J?!!??J7~:::.::^^...            ..       .        .. .                      ^#@@@@@#&&&G&#&5J^^^7G##GJ?:~?7GBY..... Y@&5..    ?&&7^^^^^^?P#&&&&&?:~5PYB#PPB??Y^.:~?Y5J!5Y...JJ?GGYYGBG!:.::^~~!^:^7^...............::.:^~~:.  . ~JJ777YBY7~7Y?~^?PGYYJ77JYPB##BBBGBGP5YJ?J~:........^^\n"
		+"	Y7~!::75?~~~~~~~YG??YGG5Y?!?!!!^^~~^^:::....         ..                   ..                      :YY&@@@#&@&P#B57Y7!~7PBBGYG77J7PG5YJ?~.~YGGP55J?^ 7B@#!~~~~^:^J#&@&P^!JGBBY##GPGY^.:^!7PPP!77. ..:7777?PP?!??JJJ?7JPB5^.  ........  .....^!!:.......^7?!!!7G?7!7P!!^^~PGY?7?Y5G##&##BGY77!^^~~~^^:......:.\n"
		+"	7!!7^~?JY?77!!!JG77?5P55P5?JJ7JJ?7?77!!!~~~~:^~~^~^.^::^::.:::::.....::........      .               ?&&&G&G!G?. .7YYY55B#B5J7??75#GGBBBPP55P5GBBBG:!P@@G~~!~^^~!Y5B#?7J5GPP5?PG?~~!!:^7YBBG:.. ..   .:?7PPJJ7:....  .5B^..        .... .~Y?:........^~~^^^~~Y??!~Y!!~!~YB57?Y5PB#&#####PYJ^.............:::\n"
		+"	!7?!!7?7!77!!~?#P~~Y7^::!GGPPPG5?7Y5GP5?7777^~77JJ?!7?????!??JYY?~77J?JJ!!!!!!!!!!!77!::......        .:!P&5^G#^:JY7!!JP&#BJ??J7?Y#B##B7J??JPPGGBBBJ~Y@@@B?~~7??BB#####BBBB##B7!::^~^^7YG#&B:   .     .?7P5^..    .  .55.      .  .....^??^..::..... .7!~^~!~?GJ!~?7?7?7YBPY5PPB###B#&&&#J?~:...............\n"
		+"	JY??7!!~!7?7?J5GP5!:!?~7GPP?!!^^7JGGY?^:::^^^..:..^:. .....  ....:^.......:........::... ...      ...   ~G#5^PB:.P#JJ??P&####BP5PJ#P57?^^^~7?JYPGBB5~?&&@@&G55JB&&&&&&&&&&&&&B~..:~~!?5#&&&5.   ..:^!~~?JJ?:!:..  .  ^Y...........::.^??^:^::::... .~YY7!~~!~~?5?~~?Y?Y?~~::^~7J5B#PPGB&&7..................\n"
		+"	YJ?77!7!!~?PBBGY!^!YGGP5GGPP?!7?GGJ~:.::...::..^ .^.:~~^^!~:..  .::...   ..          . .....      .     :G#P^GB:.YB55JJ5#B##GGGYBJ&G~^7J77~~!YGGB#BBY7&&&&&@#7B&&&&&###&&&&&@5:~7?7?Y5B#BPP?~!7?77?JP?7YJGJ7^JY~....:~7:..........^?J!:  ..:::....^5BGY7!!^~~~!?YY7~7Y~.    :!~:^!BGP55G#!.....  ..  .......\n"
		+"	PYY????JJ?JPY!^!!YGPGGBPGBGP?~7JJ~^:::^^...:..:^. :~~::^!7!!^.. ......     .     ...........      . ....:P#B^PG~~7JGBGBY####GBPYG?#G^:^!!7JY7JPGGYPGP!&&&&&&5G###&&&&##&&&&&#5Y5GYY5GGP55GBPJJ7!?7??7~7!7~~775?7^~^!?~^....::^^::7J~.  ......   .!PBG5JY?777!!!~7JJJ~:.:. .....:::!PGG5J7...     ...........\n"
		+"	55Y55PG5??7!^^75B##GP5PBBY~^~JY7^^^^~!!!~^:....^:.:~::^^!!!!?~^^^:::....:.... .. ..................  ...^5BG7JJ?J?YJP#B5#BBP5G??GJBG^^JJ!~!?Y5PJ!P5557#&&&@5Y#BBB&&####&&&&Y7?55PPGPPGBGBGB&J~:.:     .^~JJ7^~75P5BB##J?Y?JJ555PY~.  ....   .  .Y5GPPY755JJ7~!!77!!~^:.   ..::^~~~7~~:....:..       ........\n"
		+"	PGGPPBGY~???7PG##BGGPP5##?^!PYJ5Y~~^^^:^~?!:....^::!~^~~^^~77~!~::::::!~:.   ......................:^~!!!!!!!!7777JJPBBB###G5!:7GYGB~~Y~????Y!..~Y5PG?G#&#G?#BB###&&&&##&&GY?7J5P#BB55PGG55GP:  .      :!JY?Y5PBPGGB##G7JGP5555P^.  .:..   .. :YGGPP5Y7?5G5P?~!!!!!::..:^^7!7!!!7J~.      ....    .. ......:\n"
		+"	#BP5BY?YPY5~^Y##BBBB5??JGG~^!~!77^.....::!7^... .:^?7~^::!7!7~^..:~?77~...       ..... .....:::^^^!!!!!77!7??JJ5GGBGGGBBG##Y~:~JGYP#7JY7^~^^!!~~^?5557YPPP5#&&&&&&&&&&&&&#GBPY5PB&G&#555PGGPBB~..     .:~~!!!7?5######B5!?PGGP5P5~.....     .~PG555PYJJ?JP?~7^:::~: :J^^~77::!^!7^..        ..   ...........\n"
		+"	GPP#Y!JBBGGPGBP77?55~::.^5^:^:...:..:.:~^:7~::  :^^^:::::^^^~~:^!JP57~:......  :^~!?~~^^^^~~!!!~~!7!!!!?J?YGGJJJB#&&&&&#B#GJ~!7JP55&55Y?Y5PY^.^7!555YY55G#&#&&&&&&&&&&&@&#&#GB&@&#G#YY5PPP5?775J^.    .:^~!7??7Y&&&&&&BG5?:.~YBG55?~...   :?PGPYJ7!!JP5?7?5?^.^!:.^.:^~7~:. .!:^7:.          ...............\n"
		+"	PG&#57Y5JGB##5!!~~~~^....~..~^:.:~:.:..^^^7:~7....... ...  ..~JGG5!:^!:.....:~!?5GBBY7GGGG7!77!7!77!!77?5GB#GJ7^?Y?Y55PYP57YJ??75PJ&PJJ!YGGJ:^~!YG5PPB55G#&##&&@&&&&&&&&@&&&@@GJ:PGP5#G5PPPJ7~7B5^     .~!7????5&&&&&&&&#P7. .!GPJ?5??7^~YGPPGP55?7JPY?7J5?Y5!^~.:Y~757^.   :Y^^P!.:         ...............\n"
		+"	5#&G5YJYYG#B#BY!~^^^.....~...   .7^.:..!::7:~Y^:.  ..... .:75GBJ~::!JJ~..~?YJ7~75#YJ?5Y5B#BY?J5PGG55YJ?!^~Y5J^!^P5??GYJ?P5JB5J7!YP7&B7^!Y55Y7JJ5GPJ55BGGGB&&&&&&&&&@&&&&@@@&G!   ~#Y5PP5PPP!!~75JJ7^. ..~~77?JJB&#&&&&&&#PJ... ^P57!!!7JPGBGGGGP5P5PY7755J!~!^!7 ...^^..  ..:P~~Y!^^.    .  .:..............\n"
		+"	!J55??7?YYPJYYPY??!~^.::.^...... ...7!~7!7Y7!?~^~.... ..^JPPPG5~:^^~Y?...~^.   ..:^^?JJJYG&&BP5Y?~^::....:?5BP55#BP?GBJJGGYGJYYY5G?##5^!!5GPBG55G555GPGPBG#&#&&@&&&&@@@&&B?.     ~YPYYPBBPYJJJJJJJY5?!^^!!77?JP&##&##B###B5:.   :5PY~~~^~75BBJ~7?Y55YJ?!?Y5Y?~^^..^::^:^^::  JYJBYJG!^.  ..:~:. ........:~!!\n"
		+"	?!??7!7??JJ7~7J55PG5J!!?7^~~^::~^:..:!JPP5Y7~^:!Y:...^!YGG?:.:::::^~!7~!!~:::....:::::^^^^~~^:^^^^::......7YPPPYB#GJ5&B&#BJ5~?YJ5BJB&GJ7JPPBGPGBBGBPGBGGB5#&#&&&@&&@@@&P~      .!55P#G55G&BPPYYYYJYJYY5PY?J5GPGGPGBB5YYP##Y....^75BP5?7!~^~J!.  ^?777~::^Y?77!~~^~^7YY?!!~^::^P##&&&Y?!^~?7^..........:!7??!\n"
		+"	YY7:!^^^~??JJ~?5PGBGBP55YJ?7^:...:::^::^!!!:~7?JY~~JP555!::::^::^^~~~77~~~~~?J!^:.:::^^~:^^^:~77~:........!JJ5P5G#BJ5#&&BBYJ^7J!J#5G#GGY55PBBB&&PYY!7JYPB7B&@@@@@@@&P7:       .!?!:^7GPY5G###BPYYYY555GGB55555PGBGB#BPB&&B~~7??7YY7!!?PG55?^.   ~^.^^:.  :!7^..^::. .7PPY77!7!~^~JG55PBJ!?77~:.......:~?5?^:\n"
		+"	^7^~^~^~^~??77JGBBG5J77?7JY?^. ....^~^^^:^!7?7J7?JJYGJ^...:^::^^^^^~?7~~^77??7Y?:..^~~~!~!!~::.. .. ..    !JJYY55G5JYP55YJJJ7?Y?75PG&P5PPPPPGGBP5J~^~!7?#B&@@@&#GJ^.        .!777~^. ^G#GPPPG&&BGBBGGB###B555PPGGGB#&&&&&5Y5Y?77?7!7?JJYP5!.    .:  ..    :.::.....   .Y5G57!~::^:..!??~~~!J!7!:...^777?!:::\n"
		+"	^7^~::::~^:^^~?5J~.     .:~PP7^:. .:.:757!77!!!77?PGP^....:^~~^^^^~!?Y?7~~^!??Y7^^!~~?J^..   ............ .?Y5YYJJJJ?JJ??~7JJJJ??7Y5&BGGGBB#B##&&&&&&&&@&#PY?^.           .~J?J7^.    .Y##&&&&&&&&&&&&&&&&####&&#G5JGG#BY?777777777?JY5J5^.     ..  ..    .    ..      ^PJPP?7!!^:...:^!?!JPJ!!~^~7YP5!:....\n"
		+"	:7J~^:::7~^!J5Y^         ^:JY55!.....!?7!?7Y?5?YPB##?:...::^^....::::~!77??7!^::~~!!?Y~..............   ...~?JYP???YYJ777?7?777!7J?Y&&&&&&&&&&@@@@@@&GJ!.                :?7~Y7.        ~YPB##&&&@&&&&&&&&&&&&&&&&#GGJJJ!?7?Y5YJ???JJJJ??..      ..             ..     .!J?YP?7#BYJ???YB#5!!?P5!JY5P?:......\n"
		+"	::~^^~^^7:!7Y?.         .7?7JBGY?:. :!!77~~P##B#BG?^...::^::...:::^~!^::~:.    ^!!!~7~............ ..   .. :???5JJ?YY777?Y?7777!7YYJ&#B#&&@@@@@@@@&7..                  ~JY75!.  :.   .  .~J5Y5PPG##B&&&&##&BB#BGP!^7Y5Y??YJYYJYYY5Y?Y55J.    .~!:..:^:.^~7~.           :Y7!JY!5B?J5GB&&PJ!!YY?~JG5~........\n"
		+"	!~::::::!^^^~.         .?JY?5BJJB!  .:!Y5J5PB&&G5J. .........::.:^:..:....    .!~!!~:............. .    .. .JJ?YYY??Y777?JJ?77!!!JYJ&&B#&@@&@@@@@G:                    !?555:    ..   .... ..:::^^7PBGYYYYYYJJY7~^^. :!?JY5GYPP5Y?JYYYY?7^~???J5PY?YYYYYJ5YYJ?~^:::.   :7557!!~7PBJ!!J&GJY7775BB5YPGY~:.....\n"
		+"	!YJ~~~^:~!7?~.        .!?JY5GBJ!BPJ^:^7YB#GP&&#PPY.    .:..^:.:!:.  .^.....   .~^::..:.................  ...7YJJY?77J?777??77!!!!JYJ#GG#&&G&@@@@P... ..              :~YPG7.     ..  .  ....       75YJ?7JJJJ?^::.... ^JJPJ5PPY5PPJ~^~~!7?YYJJJ??JYYYYJJJ5YJ7Y7^!7^..:!7JY5577Y#PB?77B&YY!!75BG?!75B#&B57^..\n"
		+"	7?JJ!77~~!!77.       :!?5PPGP!7:B#Y!7^75G#B#BGGG?:.    :.:~..!5~.:...^^:.^^.:^..:^..........................:J??YJ77JJ??7?J!!!~!77J?BPG#&@P&@@&7  :. ..            .??7YY:       ...  ......:^:.^?J?:^^^^PG5Y^ .^^....?JYGBPJJYYY5YJ7~:.^~~?J?J??YJ?JJY5P5Y5^!!...:^!7?7!75J7?&#GP7?J&G7!?PG#G7!PY7YPPBP#B::\n"
		+"	!??7~!7!!!?77!^^:.:^?PGGBB#B!^!~PJYGG~YB##Y~!YP7:    ...^!..JPY7^^7~.:~~^:.:^~:::::.....::::.......:......:..7J7JY???Y?77?J7!!!777YYP55B##B#&&~   . ..            .7BB5~          ... .::..^~!!5557~~5BB#&#G7..:^^...?JJY55P5YYYYY5Y?JYJJ???J!?77!^7?Y5PGP55Y!7GP55YYJYJJY5J77B&#YY?5B7J5B&BYGY5BY?J5G##&&~.\n"
		+"	7Y?!~??77JJ???YYPGBB&#&#BG5^^J7?5P&&&Y7#&BYJ::::~:.....!!..!5!:.:~:..:~!!!~77~^::::....:::^::.:..:.::^::::.:.:?77Y77!Y?7!!77!!!!!!?5PGBB&&&&5.      .            .?5BY:             .   ^~::75PGG?~^7^Y##&BG^: ...^^75Y5GGY5BG55Y55YYY555J7~!J?5P!YG#BP#BPP?5P77&@G5PPP5J?5J77#&GBYY5?!?##577PG57JPGB#&&&&!.\n"
		+"	J?7J?PY?7~~^~!^::^7??JJ?~~~~??5#&&@&GB!B##GJ!:.....:.:7?..~?^. .:...:^~?J??J?~:..^^:^^::::::::::::^^^^^.^:::::7?7J?77JJ?!!!7!!!!!!7?P@@@#PP7.      ..            ~^JY.              .    :!YPG#B5:7!BJ5#&&&P.:. ..~7YPP#G#B5JGGGPGP5Y?!~^^!J5BBGP5G5##BBYYYJ?PJ7JB#GGGP55PPJ!Y&B#PJJ5P#GP?777YG^..Y#BB&&&G^:\n"
		+"	?PGGJ~....~PP:...:!!7~!?!7YJ5&&&&&&BPB~5&&#PJ?~.:..:^??~.^!7.  ... ..:^~!YJ7!:^!7?J??7?JJY?^.....::..^::.:::::^????7!??777!?77!!777J5&&&555        .            .!~:.     .       ..:.   .?Y55Y?7^YYGPB&&&&!.:^.^~7YPG5GP5B#P5GBGY^:::!7Y5GGPJ7!?55G#B#55P7YYP5GPYB#PYJ?7!!?!P@#Y7Y5G#BYJYJ7JGJ....^5#&&&?.:\n"
		+"	GJ7?J?7YPBGP!..^J5?~..!77!:::!5#&#BBG#55#&&#G5Y~:^::!?^..:7:.....:::!7~^:J77?YYY?777!777?5BB!^^.....::^J7:^:::^7?7?777?77!!77!!!7!!J5&&@#57        .          .::Y^       ...     ..::.  .^:... ..!B&&?#&&&!^7?7?5PGGPYP#G5G#BYYBPY?~!PGGPP5YJJYPGB##B#GG#BBBGGGPGB&B5PPBBBP~J#55PG&BYJ55?YPG7......?G#&&5::\n"
		+"	G?.:::.:^^. .7J?~.   ^^..      7#P5BGGJ?Y5YJYPPJ::^^:^:..!?!5BGY7??^::::.!?YY??7777777777J5BP7!!:.::^:5~J^^:::^~?7??77777!!!7777777J5&#&77P.       ..        .~::7             .. ...:^^.......^^.?P#&JY@&&YJ5J5G#BBBPPJ!J5JP#BGBBJ^:^YY55YJJ??YYPPG##BGGGY?77Y5PGGPGBBBBBBPG####BB#PY5Y7P#Y. ......~J5&@P::\n"
		+"	G#7   ....~JY!.   .:!:..       :GJ7!GPY!J!7JJ?5!::~~^^!!7P?~:^!JY55J?JYY~!J?7??777777777?YG#BJ?Y!:.:^~B?7J.^:::~?777!7!7!!!~!!7!7?7YJBB&GJ7:        ~.    .~!^::~.                    .~.....^~^.::?GPGY&&&PP5PB##GGP5~.::PP55GB5Y55J?JJJ???77JYP#P5G#BPYJYPPY?JYP555B55JYBB&BB#GPBPY55G&&5J~.......~??#&G::\n"
		+"	5GP^     .Y7.   ^??Y~...     ...~^7~.JYJ7~^Y#BJ!:^!!!?Y?JY7::.....~~!^!?J5Y77???7!77777?J5##G5Y??~:::7G5~Y::::.^7?!7?77?!~~!~!!!77!?YG&&@5~J.       ^Y.  .!J~ :?:                     :!.   ^:....:75P5?#@&GB#BGB#Y~P7..:?PBJJJYPG5PBP5J??YJJY5Y####BGB&&#GGB#&BP55#&#5:.:JBB55YPPJ?G&&@BJ5PBBGP^...~?YB&&~:\n"
		+"	Y7~#5^ .^?^    :!!7^ ..        :~~?7::J.^P:?P55?:^?J??^^~!~:.    ..:^~?J5Y7777??777777?JYB#GP5Y?Y!^^^7~J7?7.^:.:~??7?777?~!~~!!!!7!J5P&&&!~Y~        ?B7~?~. .YJ.               ....^^!?7~:.   . .^?Y55?P@&##B##BPY.~J..^~P#PYJYYPBGGPYJYY5YY5GB&&&#PPPPB&@&BPG##&PPGPYY5P555J5Y!~?#@@@#?J55J7?PG!:.:JP#&&Y:\n"
		+"	B^YGYY5Y!.     ..!^  .       ..:7J7!~^?:?P7!PPP~^~7?!.   .:^~~!7!?!~:   .!~!77???77777J5BBG55Y?5G?!^^5~~Y~Y:::::^!J?7?!!?7!!~!!~!!77YG&&&J!JJ.        5JB5:. ~Y:...      ......:~~~^^!7?Y5P5~... .^YPGP7B@&#&#B##BG?.~7^~?!B#YJ55Y5GGPPJ7?Y5PP5#&&#GG#GGBB#&P555PB#&&BGP55JJJJJ~~Y&@@&5^5Y^^^^~75#B!:JPB&@&~\n"
		+"	P:J?JB~.       ::.   ......  ..^~77!~~77J7~7P57^!??Y:           .:::^7!....:~JJJJJJYPGBBBG5YJYY?JY57^5P:J~J!:~^:^!7?7J7~!?7!!!7!!!!7YG&&@B755^       ^75B#BY7J7:^:.::^:::::.:~!7?77??JYYJPBBG#J^~YPPGP7!#@@#B#BBGPGBP!:^?5?!B#BPPPPPPBBGJ77JPBPGB#BGB#BGPY777?P###G55GGBB####BG#&@@@B~. ~J7!!7J55Y?^7Y5P#&@B\n"
		+"	P:.~5#:     .:.. .     ..^!!^:~~7!:^^7JYJJYY!::^^~?:          ..     .7P?^. ^5GGB####B###GYGP5PPGBG7!G#Y^77?.~!^~!!!7J???????77777!?JG&&@@#B#Y.     :!?7YPG&&GJ7!^..::^^^^^^~7JJJ555G##&&@&PG&@&&&&&G~7?JYYP55PPPPGGB#P~^^G?Y&#BGGB#BB#BP5YYP&&BP#GYY?77!7J5G&@&#BB#&@&B#&@@@@@@@@@@#J:. .:::....  .!BB#&@@G\n"
		+"	P::!Y#~:^^~~7~:::. ..::.^?YJ5?~^^::^!JY5YY?::::. ^:             ....   .?BGJ!~!!!75BBBBB#BP5Y5B##G?~~B#B~^~7:~7!~!^^::^!77?YYJJ555Y555#&&&&#&G.    .7?~.:7YP&@&#5??7~^~!!?J5GBB#&&&&@@@@@BJ#@@@@&#B#P7J#BPJ~^^^:^^~~!7J?!:757##BP55P55GB###B#@&@&G!^^^!JYG#&&@##@@&#&&BPJ?YPGB##&&&&&#P!..     .:7G5JG&&&B7:\n"
		+"	5.^7YPP#GJ7^. .. ......:!7?Y?^^^:...!JJ?7^::. . ..      .                .??^~~~!!J5GGGGG55GPB###P7~^Y5P?:^~^^7!~7:::::.::~?J?JGBPGPGPB#&&@@&#:~.  ~!!  :!?5PB@@&&&&&&&&&&@@@@@@@&@@@@##PP&@@&&G7!##^!JP&&#GBGPJ7!~~^::::::::~7?7??JJYY5B#BB@@@&#G~!!JGB#B##&#??B&&GYG##BBPJ?7!!J??P#&#G!.....~7J#&BP#&&P:::\n"
		+"	#~!?JBG!^^.     ..  ..^!J5Y?7.:^^::...            .:^~~77!~~!!!7???77?7!7. :7777~!YPBGB#BPPPBB###G77!P!!J~^~!!~^^^~::.:::^:^7~75GBPPG55PG#&@@&~~: :~7^^~?Y55GY&&&##B&&&#&&@@@@@@&&&@@&GGGBBGPP5775&&P5YGGYYYYGGPPPGGPYYJYJJ7!!~^:^^~^~~^77Y####GGG#BB#GG##G?#B77J5#&GJ?P##BBBGPJ???P##BG!....:!^~G&&#&&J^:::\n"
		+"	J~!PBBBJ~:     .  .:~?5P5J7?7^::::.        .::::^777!7Y5YP5YJJJ7!!7?5GBP?!.  :!7?YPBBBBJ!~^~J55G#G77~PB!7!:7??~~~~7~~^...:::^7GBPGGGBP~??#&&&&5~7?J55YYPPY!75P##&BBBG###&&&&&@&&##&&&&#??!!~?YGY5G&&BB#P?JG#P5Y5JPBP5555BG5PPGGP5JJ?77555~~Y#B5PP7PBBP55J5J?##J!7J5#&#5?J5PGPPB##BG5GBBB?:...^~^^?B&##Y^::::\n"
		+"	...Y5JJ7!~::~!~~~77YGY7??JJ?7^:^^. ...........   .     .^!7?~~::!!?5GGBBPYJYJ~^!JPBBB57:...:^^^~7~^~~JGB!~^~!?!!7??J!^?J~.::^^J55YG#BP7~J5GBGGGPGB?7^^!!~^~75&#GGPGBGBG#####BGGGGBBGGG&J~G&##BBBBB###GJ7YJ?5555PPPBG5GBGPG?Y?P##BBGPGB##&###&@@@#J557PY77JJJG#GJ?775G##GJ7?JGBP5PB#####BG!::.:77J5B&G~:^^^::\n"
		+"	?~7BBG5J~~~^~~^?G#BY!^!???J?!:^^::~~!:.   .....^7!777??J5B#&#BB&&&#GP5?7?JY55PJ^~JPY!^::..:.:::::::~^J5P?~~~^!7~77JJ~~?57^.:::~Y5YY??7JY7PPY??7?Y?^ .:^7!~?G#PJPBGPPGB5BB#&BJ?7~7PBGBG&G^7G5J?!7JY55Y5GGPJ5BY!YPBBYJ7JBBGBG5Y&&&&BYJY?YGGG#BB&&&##B#&&#GPJ?7!7GB5G5Y55PPP?75YY5GPYY5P##BBY^...:~JYJ7:::^^::^\n"
		+"	&PJYYYJ!!!7?Y7~PPPYJP5YYJJ?~^^~^~~:.    :~~~777?JJYPYY5PG#BBBBG5PBBBPPPGB#P5YJYYJ75Y7^::::::::.:::^~:!7~:::!?!7!?JJ?~Y!?~!:^~7?YP?YGGGGBBGYJY?JGG!^:.:!!7!J&#YGGGPPPBPBGG#BP?^?^JGBGBB&@&GPY?77!7JPGY5B#Y?P?Y?5PB#BB55GB#G?P#&&&BJ!7Y7J5J?G?YBB#&&&#B###&#BGGPG&PJP5YYJ?YY?J5P5?J5YJ?YPBBBG?:...:......:::::\n"
		+"	YY5YJ7~^:^^~!^7B5JB&###P?YJ7~:..    .:~!777!~~~!!~!!~!777?JJ????JJ???7!YB##G55!JYYPBGJ:....:::.:^~Y57?^..^~~J:^:7?77!?JJJ~?5PBGPYPGGGGPPPP55J5GPGY7!~~7?Y5B&Y?JBG5555PGBBG5YY77~7PP5GB&@&&#J!~JPGP5PPY7^7YBJ!YYPG#B#BGBB55B&&#Y!^.^!?J?JJG5JBB#&&GYBJGGPJB&GYG##&P7YG#BGBY!!~YPGJ?55YJYPG###P~........:..:::\n"
		+"	!?&&&&BPY?7~^!PGJY&&&@@P~^..      :^!7!!!!!!!!!!!!!!!!!!!77!!??JJYJ5GBGGGGB##BY~5PGBJ^..........:^J^!J?:^!7~?~^^^~J5J5GG55G!!^^:^~~~~~~~^^~GP?555PY?~::~!5&&BB#BGPGGJYBGBBG5YP?:~YY5PB&&&BP!!^!55PGY~:^7Y775^!7B##B##P?YB&&&5:::^^^7JJ?JGG5#&#&&BY5#B?PP?7B&#JYJY7??7?PGBPJ?7###BB#5YP55GB##&B?^......:::::^\n"
		+"	^^!J5GB#####BBPBP#@@&P^.      ..:.^~77PG5PYYY??JJJJJ?77?777??Y5555PBBP5PGG#BBBBGB57^::::.........:!:!J~:!?YPY~?~JJJ57?^:7J777777??YYYJ:^^^^GBG#BG##5:.  :^G&5PGB#BGP??GB###BBBP:7J.!Y#@&#P5^~~7555!^^!!77?J5!5B##BGY?!P#&&B#G~:^^^~!5PY7GB#&&&BB?!75##BPP7?P&&5???!JPY?YP?:^:J#GBGPPYJGP5GGPPGPY~:.....:::^^\n"
		+"	.:~^7Y7JY5BBB##&&&BY:     .:~JGP~~~7?J5JPBBB##5?5Y5G5?JYYY555Y5#&BP5PGGP?J5GGPGBB~:.:::...::^^:~!7YPGB5?GBGGY77!7!!!^?J^~5YYY55J?JYYJ~~^^^!BBGGGBB5Y~.  ::P&Y?JJJ7~!7????Y5GBGP~Y^.?G&@#G5YY5Y?YPBGYJPP5PBB5JB##Y~~^JB&&GGGB#G7!7?JGYJPJ7B&&&GPJ7!Y5Y&#?BJ7!5&#P7J?!7YG#BGG57?BB&G?Y7~^::........:^^^^::::^^\n"
		+"	.!!~7^:^^~JY5B#&#~.  ::~75G##BJ:~!!!!!!!JY5YJ5GG##&#GP5?J7J5B&&&&#PGBP57~^!55PBBJ:^^^:^~!?Y5PP77GBGP5YYGP?GJ!^~7JJ?J^7P!:~~!~~^::~~:~7JJ~^^!YP?^:^J5P7...!B#GGGP5P5GGGGGBB#####5Y~~G#&&###B#PJ5YPBBG55P55PPP7G##?!PGB&@?!YPGBGP5?JPB5PBPJG&#PGP7?57???#?YGY7!G&&#PJ?!~!JPB###BG5G5~.              ..:!7?!~^~\n"
		+"	:~^.. .:~7YJJG&&!:.^?5JJ5YYY7.  ^?77!!7!??7?J?YBBBBB&GBGPG#&&BGP5J?55YJ?77YP5JP5~~~!7?PG55J?77J5PP^..:~YJ5&??~~777~~::...:!?!~^::^Y5YYBBJ~^^^!PJ^.~7YJ7^^5&B&&&##&BGBBBGP55YP5B?!YGG#&#?7YPG5JY5P7^?7~.!7Y5B7JBGG##&&G?.!?55BBGP?7JP55BBYG&GPBJ?7JP?~JPB7YG?!7G&GBPGJ!!~~?5Y5B##BBGP7~!^...         ...::~^:\n"
		+"	.. ...:!YJ7?PG#P!~77:.... ^:   :!~?!~~!7Y55YJY5G5??B#&&&@@@&#B#BBPBGY?7??YG5?!7Y7^~JGBBP?!?7PPGB?~...:^JGBB?J~~^!Y7^::..~^^JJ~!~^^?BG5G!. :^^~!BBY7^^!7!^5&G55JY5J7!!77777~~~!P?7Y&7B&#7~^^7!::~^::!J!^7?YGGJ?PY555Y~::::?PPBGGP?~!5BB#GJJ&GBP7J?!7PPY7PG?GBJ!~?5JYBBY7!~7!. .:!YPB#PBB5?JY!^:....:^!J?7JB5~\n"
		+"	. ..:~JY~:JP5BG~5PP5?!^..~:  .^!~7YJ?7~~!??YPPP5J?7?JJP#@@@&&##BGPYJ???Y55G5JJ5GGPGBBBBBBBBG5J!^.....::?55?7JY^:^:!!7YJ~!?!7~^~~~~!G#G^.   .^^~!J5PY?J7~~J&&PJJ?YY?!~:..~7JJ?YY5B~~~G&@B!^^^~^::::^7JYYY5PGGY7BG55?^:::^^:!5GGGGJ~^!JG##G7#PP#Y???7!?GBYGB?BB5!~5BPPB#G5Y7^...    ..^7?5GBY?Y5YJY5PGPYJ7?J77\n"
		+"	....~?7:^5??BGJ^.:.....^!:  .~~^7J!5P?!!7!?J??JYJJ?7?!7YPGB&&#&#GBP5Y5PYJP#GPG#B#BBB##B5!~^^:::......::^777B557::77JPPPY75?P5~!!!7!GP:.  .:.:~~~7~!GGY7P77#&&#?~^. ..  .^ .!JJ5G#P5#&&&&Y:^^~~^^^^~7?JYP5PPGJ?GGP57~!~~^^^~!JPY55?^:.:Y#B7#B5YG?7?YY?!?PYGPJB#P!P#BY?7!^75PYY?77^^.  .. .^5B55GJ!~:..  .::::\n"
		+"	..:77:.~Y~~BGJ?J:. .:!?~. .^!!?7J?7?77JJ?77777?JJJJ777!!7YP55YYJYJ7775Y5B##B#BG5#B##BP5J^:...........:^~J57#GYJ::J?55YYYJY???~7J???Y^   ..:^!7^^7Y~?BGJY?~Y&&&? .. .. .::.^:!^775GJ?5B#BG::::::::^~7?JYYJJJJ7JJ7?777??777!!!777?JY7^.. ?#?Y#GJY5JJ?J5P5Y?~#BG##5B#P!:. . ..^~!JPB5!^:..   JG5#!.   ...::..  \n"
		+"	^~:...~~.:YY!^^^?7..:!:  .:^^~~~~!~^^::^77!~!!~~^^~!!!JB&#J!^^^^^^^^~JG#BGBB5GY##B#BBGGY~:.........:::::~YJ#P?Y!!7JYY!^?G5?Y!^7YY??!:.   ..~7?!^~JPGB#5JYYG&@@#... . ..::.:^!~7J?55G5G5GB?::.::::^~!77!~!!!!!??777??JJ?J?77!!!!!~!777?7^YG?P#GG#&&&&&&BYP!?P!YB#BY^.:~^::::::.::^^~7J??7~!YYG5.  ......     \n"
		+"	:....:..^JJ!~7J?J^.^!. ....^^^~!~!!~^^:^^?5Y57?7~!~JGGBBBBBBB7^^^^?PPBB#GB#G?Y7BBPBG5YYY!::.....::::...::~PGY^!~~~!?GJ^!Y7J?J~75Y?~:.. .  ..~J?~^~5GPBY5J5#&@@#........::.:77~~~7??J5G?!YP?:..:::^~~~~!!!!~!7Y7!!7777?7!!!!!!!!!!!!?Y5J?J#&&@@@@&#B##&&??J?YJYGBGJ~^?^.       ...:^:^~!?JP5YPY?~^:..     .  \n"
		+"	..::.  ^??77?!JPY!^~. .. .:^~~!!~^^^^~7?7P#GY7GPGBB#&&&&#####577JGB#BBBGP#BY!J?BGGY55JJ7?!^....::::.....:^5^~^:^~J5GPY^^7!YJ?!!P57:.::.::::..7J?~^7J^?J:^5B&@@G.:......^^~:^7~~!JYP555P5?7J?:::^~!^~!!!~!!~~!J~~~!77JJ!7?7!~~!~!!JYY?777G&&@@&&#7!!!JY577!!!7??JPBBGY^.            .....:?PGY?PGGBGY!^:..   \n"
		+"	::..  .?J?J~~^~?5?^     .:^^^^^^!!7JYPGPPBBGPGYJ7Y5GB#&##BBB#&##B##BGP5YPB5!~^~?PPJJJJJ?J!::::^::^^:.....~^^::~Y?YPP5PG?:^?5!~7J!^..:::::7J?^:YJ!7!7J5~.:.:7#@B:..:..^!!!?~77~7J5YJJJYYPG?^~J!!7!~!~~~~!!~~~!J?5YJ??7!!7?777!!7Y5Y??7?77#@@@@&B7!!!!~~~!!!~~~~^7!7JBBGPY7:.         ... ^75J..:!~:~?5PYJ7~:.\n"
		+"	..   .7??J5!~7YPJ^   .:7??777J5PBB##BG55PB&&G7~~YYYP##&&&&&&&&#BB##&#GB##BGPGG?!7YJJJJ5YJ!^::^^~~^^:.....:^:^JJ~:J5YJJ7~..::.:^::..   ..:^^~!!Y!~5??PP:.:...!P#J:^.:!77~7YJ7!7JPGPY???PYJY7!77!~~~!!!~~~~~!J?YYJ??7!77?7!!!:^?YJ??J??7?7#&#BP?!77!!!!!!!~~~~!!!~?J7JB#BY?7!~^^:~!!~. ...?BP:.:^:.    .:~7?JJ\n"
		+"	^!...^^??!?!~77!.    .!Y5GBBBGP5YJ?7!^~~?P5Y5PGB###&&&&&&&&&&#&###GGGPJ5GP####&G7YYJ555PP~:~!~^^!~!:..:.:^^!PP!~^^:.. ...~:..!!~~~^:..::.:!!7P7!Y?75#?..: . ::YGY^:!7J~!PGGPJYP5J7JPYPGP5Y?!!!~~!!!77!!!!75YJJJJ77!!!~:::^~::!^:^~J!^^^^!^^~::^^:~!???!~~~!!!!!J?JY5BBG5JJ?77?JY7:.   :~7Y!:::.          .:^\n"
		+"	.^:^!:.~77~J5Y^.     .^?YJ7!!~^::^~^!7^..:^^J#@&&&@&&&&&##&&&BGB557~7~:^^!??JJYBGYJYYPPPJ::!7~^!!~^....:^:~5B5~~:.....::^!7~~7~!:...:::^:^^JG?~JP7PBY^.:... .^:5P77!!!!5#GY!75BY~^?PGG55?7!!~^~!7!!!!!!?5G5!?7!!~~!~~:::.:!::^:^^:!~^~:.~:.^::7^:J5555YJY5Y7???J5?YPGB&BG####P!.. .   .7Y7^^:.              \n"
		+"	.^.~Y^:7!JJYP^.     .:^~^~^:::^~~7~~57~!!..^^!P&@&&&&@&#P5&GPBGGGPJ^^:::.~!!!77?YJ?5P5Y!:::^^^!!~^^:.:^~^!GGY77~:..^~^^:::~7J5!...:.. .^!!..~~~YP5B#J.  .:..:!:~5G?!~?J77?77~7Y~!YGBG5!!7!~~~^~!~~!!!7YGPJJ~?!!!~!!~~!^^::~:.~^::.~!^Y!^5?^7^^Y~:YPPGGBB#PBGB#B5GGB#&&#GBBBB#B5:.... .^75^::.....           \n"
		+"	^!:~J~~?YYYJ^.     .^:^^^~~!~!J??J~^!~~G#P^.:^!?&@&&@&Y!JB#BGGBGP5Y77~:::~!!!7!7?!^^^~~^~^^~~~~~!!^::^^^:^^.:~!!!7!~::^~!!^7PPGPY7:..:^:::......:?B#7?~~!~~:!!~:J#G77J5YJ5?!7?555GB57~7?!!~~~!~~~~!!75GYJY?^7~!~~!~7Y^:~::~:.::^^.~^:Y7:P7.^^^?~^YPPPP55PPGBBB&&&&@&&&BPGBBBBPP!..  .:!Y7..^........        \n"
		+"	~7~^~:~Y55?^.     ::~!!?!7Y?7JJ!7?~?77!7PP57..~!!&@@#7~~P#&##GBBGGB?^^^^~^~!!~^~~!~!!!!!7!!7J777~~~^777!...:~~!~YJ^::::^^!~JGPB#P?::...:..:...:.  .^7?5GGP55J!^:7?JBJ77??JYJJ5P55P?~!J?~~~~!7!~~!!~?PPJY?!~~?~~~~~~YY^::^:????~~7~~~~YY~P5!?~^7~^5BGBBGPGBBG&&&&&&&B#BPYJPGBGPP7.. .:^?J^..:................\n"
		+"	PPJ!!~7555~.     :^^!?577?5!!JJ55J?GY!~!GJ?J5~.^~?&&BPP?7G&#BGGBBGBG~::^!!!~:^~~~~^~!7~~77?Y?7!~.:^~7?: .^!?55~^Y^::^::^!7!!JBGGP:~.....::~:. ...^....:^:~!J7!?~::!GB!!!~^^~!~^!?!~7J7~~!!?!^~!7?!JGPY5?~!~7?!!!~~!7G?7!!7G###5BBBGPGBGYBG?!^:!^^YGGGGGGBB#&&&&&&&###BP?JPPGGBG7.. .^Y5!:...................\n"
		+"	G&#B#BBP!.      .:^77?5JYY7J7JYPJJYP!^~7GY555Y~.~!JJ7?5GYJ##BPPGBGGBJ^^~~~~~^~7?7!^7!!!!?JJJ777!.!~.~. ^?J!!5GJ:J7~^^^~~7?55GPJ5P!^^:..:. ..^~.  .^..~^7JJYY55PBGJ5B&J^^~!777JJ!~~7J?!~~7?!^^7J775GPY5?!~!!?!!~~~~~7GP?Y5JJYBB#BBGP55BPGBBPYYGBY?5BGGGGBBB&&&###&&#GP5PYJGPPGB#P.. .5#P::...................\n"
		+"	@&&&&#J^.     ..^!75YJJJ#Y?5?!??75PJ~!!YG?7!!~Y7?7~!!JYJJJ5#BBB#BGBBB7~~!!77~~7?J?~~~~~!YY77^^~!~::^!.:^5G:^~5B^.YPYYJJY55YP#PYPYP..^:...    .~~. .^:~7?5PPGP?!77~!JP#Y~^^:::^!~~!?J7~~7J7~755?J5GPPGY~!!!!?~~!!~~~75P55GGPG##P??7JPBBG#BGB#BBB55GYG5PBB##BBBGG#&BG5555Y5GGGG##G^..7B#!...................:^\n"
		+"	@&&#5~.     ..:^~!!J?57YG?J?7!!~75P???7PP77~~~~7!!~~!!!!!7Y5GPGBGBPGG5!!!^::^!?JJ7^7JJ?J7^:....~!!J?7!.~^^..~7PBJ?PP55Y55YPBBGYPG?^~:.^.       :^^::~~Y5J^::.:7?7~^~7JPB7^...~~~~!??~~?5?!JPJ75GGPPGBY!~~!77~!!~~~~!?!?YYJ5B#BB5JYPGPB#GGBGBPYGJP#5BGBBGPPPPGBBBBGGP555YPPPPB#BGJ7?5??^...................:^\n"
		+"	@@G^.      ...^~~~!?YY~YP?Y7!^!!P5?~77JG5!~~~~~!?J?77!~~!?7JYP#BGGGGGGJ7!~^^^^!!?JY5PPGY^.::.::^~?GG5!:.!^^~:~?GB5JY5P555YGP5YJYB^ !7:~~.   .:^::~7!77!~~::~!~!!~^^^!!JGP!!7JY!~~~777JPY7557?G#BGGGBPY!~~~7?~~!!!~!J7^?Y?7~^~JPYGPJ5B#55GBGBGGBBBBBGBGGGBBB###BBGGP55YY?JJYYGGPPGGY~7Y^.................:::^\n"
		+"	B?...  .    .:~~~~7J?77?7JJ77!~JB?~7!!P##7~~~!!!7!~!!~~~!~!!JPB&BGGGGBG7^:^~^~~!?Y55PGY7^^~~~~~!JYPG7::^!. .~!~~JGG55J?JYPGPGG5PPY!JBP5J~::^5PPPGB5YJ??????JYYYY57^::^:7Y!~^!^!~~~~!7JJJJ!7YGB#BB##B5?77!!~?~~~~~!??~~?5YJJ?5BB##Y7?!??77??5BYG5?YGBGPPP55YYYYJJ???7???YY55B#&&##B#Y^:..................:::^\n"
		+"	:.....     .:~~~^^!7!~!~!P!7!!7?JJG#GY&@@?~~!~~~~~~~^^~~~~~!75JB&#BYGGGJ^::^^~7YPPGGGY!^:^^!?JJYGP7!::..~^..^~7J!!!!77?J5PGBBG5YYYYY5YYJ77!~^^:^^^::.::::........:::::::~Y?7~^777!~~?P5?!?5GB#&#B#&GJ?7?JJ!7~!~~~!J7!~~!?YJJ5PPY7~~~^^~~~!7GGYGYY5BG?77JJYYY5YJJJY55?!?J5PGPB&&&BGBBJ^:..................::^\n"
		+"	?!~^..   ..:^~~~~!~!~77?J5?J?P&BP##&##@@@B7!!~~~~~~~~~~~~!!!!!!G&B#Y5PPP~:::^!Y5JPBBB57^~!~77??5GGYJ5PY^:^^!J5PGGPYJ?7??7~:...  . ........                        ...:::~~^^~~?PG5?77J5PBBBB##GG###BYJ7??Y7~7^~~~7?!777~^^^^^^~~^~~~~~~~^~?P7JPPJJGBGGGBPJYYY??77JY5J7?YPP5JB&&&#BGGBY^:::^^:::::...:....::^\n"
		+"	!J?:....  ....^~7555PG5JJYY5P&#BP5GB#&@@&&J7!!~~^~~~^~^!~~~~~~?G##&G?G5G?:^:~!!JGGGBBPJ7!~!~:^^^77YBGPPY7~^............................       ..... ...      .  ......:!~~~!77?J5GB#BGGBBPP5YYP####GYJJJ???77!~~!??~77~^~~~~~7!~~!7!~!77YJ5GGB#G55GGGPGG#5YYJ??777?YY??YYPYJ#&#&#GGGGBP!:..::^~~~~^^^^^~^~~^\n"
		+"	~!7~...  .. ..^!5BBP?!^~!J55GBYJJ5PB&&&&&&5?~~~~~^^~~^~~^^^~7!~7P##G?GGPP^^:~?!JBPB5J5Y?~:...:~~!J!YJ5YYJ??: ..... .... ..::::^^::::::........               .... .. :!~^!~!7?5PGBGPY!7J5PGPPB#####BJ??YJJ?J~7~^~?J~77~!!~~~J?!!?J7!7YY??J5#PYGB#GPGBGGG&#YJJ?7777?JJ7?J5P5Y###&#GBGPB#P??!~^^^^^^^~~~!!!!7?\n"
		+"	J!~J^..:!Y~^^^^JBP?~. ..~?5PPY!~!5G&&##&BGGP!^~~^^^^^^~^^^~Y?^^^JPBGJPGPP7::!JJ5G##JYPJ7~~!^:!7!7Y5YJYYJ5?:. ...           . .  ....:^^^^^~~~~!!!!!7^^~:....^.!:.  .:!~~!7?YPGBGY7?Y5555PGB#B#&&####PJYYJJ??7~~~~?Y7J7!7!~~??~!?!~!?PGJ?YYPBJ!?PBBGGPGGBB&#PYJ77777JJ?7JP5Y5&####GGPGGB#55PGBPY??777777!!7!!\n"
		+"	?7!JYJYG5~:::^JGY7:.   .~JGBY!^~7P#@G7?J7J#&G7!~~!7JY55J~!PY~~^^7Y5BPBGPPJ!7?JJYGBBP7!?7~~!!!JYJ5YPG55GPP^.  ..:....:.....       .  ..    ....:::^^~^!7!!!777?Y!^7?!!!?Y5PBBGG5JJYYJJY5GGB##B###BB##BJJJJJJJ??7!~~JYJ!!!~^!?~~!?7JPGBBP5JJ?PY!7Y5BBPGBB#G&&G5J??77?JJYJJP5JP&&###GP5GGGBBJPGB#&&##BBBGP5Y5Y~\n"
		+"	77?PP5PP~.  .7G5~.    .:75GY^^:^!5@#!^^:~P&#BGJYPP5PP55G!PB~^^^:~7JPGBGGG5777!7J7555GGYYPY!!J?PPPGP5GGBBPJ??^:::^~~^^^^^:....... .    .            .....::::~?J!?Y77J5BBGPY?????Y5PGB#BBBGP?!~J55PGBBGYJJJYJ?YY?!!!7?77???Y55YYYJ?7!!~!~~!!5P?!?B&&#&#PGPB&P!7J?777J55YYPJ~!Y####GPPP5Y5#J?J5PG#&&&&&&&B5Y~!\n"
		+"	77~?55J:.   ~?Y~:.    .~JB5~^::~!J@5^~^7JB#YB&GY??7!^^^!P&Y~^^:^^^~?G#GPGJ!~!~:!??Y5PPJJPJ!!?!JY5GBGPGGBPPP5?~^.^^^^~!7!!7~:.         .        .... ...:^^!JGGP5YYPB#PPG5YB##BGGBGPPY777!!!^~^~JJ5PGB#BPJ?YY??J7!77!?PG57JYY?~~^~^^~~~~~7?!J#Y7Y#&&##B55PP&B!~!??77755Y55?!~!###BGPYJ?JP#!!!77JYPPGGP5Y7~!?J\n"
		+"	~^~7J!..:7?!?7~..   ...75P~::^^!?5@?^^^7YBG7G###B?~~^!P&#J~~^::^~^~JYBGGG~7?JY?YYY?JJ??Y7:^!!!!YG#BB#BB#BPY7~::.:^~!777?J?^:.  ........   ... ......:::::JG##GPPG#&#G5PYYP##G5JYY??77???JY5!7!?PP55PGBBGGY7???J77YY??J7YJ?!~~~~~~~~~~~!!Y?!?#BB##&&&BYYY55G&5!!77777J555Y7~77B#BG5J?J??G#!!^~~!!~!7!^^::~?J5\n"
		+"	Y7~!!!~7JJ?777:..  ...:757^^^!YG&@&7J!~!PBP~P#&&5~^!YGBJ~^^^:^^^~~~?JJP#Y7?YPPBBY7J~^!JJ77?7!~~PGPYPB#B57^^^^~~~~!!JJYY?~:............        ...:^!JJ7!75B#GGBB##&GY5PPGGPY5P5YJ7!!7?JJ555P5Y5PGGGPPGGGB#G5J?YY?JYJJJ!!~~^^~~~~~~!!~~!!J!~7G#GB&&&&#JJJJ5YP#5!7?J7?J555P5Y5BBBGJ???J?Y#Y~!!!~~~^::..:~7JY5J\n"
		+"	&BY!?JJJ7~!7!^.     .:!??~^!?P#&@@5BB7?P&BJ~?#&#Y~~YJ~^^^~^^^^~~^:^!7?5BPGBBBBBG?~7~~7YPY?YY?JYY555GG5!^^~~~^~7J5YYPP7~:..... .  ...     ......:^~!!~!5GBB#BB&&&##BGBBB######BG55?!!!!JPPPGBGPP555GGGGGBB###BPJ7J7JYJ!!~~~^~~~~~~77!~~!~~~~~5#P?YGB#&P?7?JJYYBP????JJ55PP555BB5?JJ???YB5!777~^^^^~7JY5PP5Y7J\n"
		+"	YJ!??J????7!^.     .:7J?~^~7YB&&&5J57!5#&&7^!5PBP77~^?!~^~^^^^~~^^^~^!5J7GBBY5J7!!7~!?5GGBBPGGBBGPPP555Y7JY?~!Y5Y5YJ!:.......  ................:^~~~75&&#&&&&&&##&&&&&&&&&###BP5GGGGGGGGGGBGPGBGGBB##B#####&###P?YJ57~~~~!~^^~!7JJ?!~~~7JYJYPB#G?JG##&P77??J??P5YYJJ?YPP555P5J?????JP#G7!7!~^~!7YPGGBBY7!~JG\n"
		+"	777!!J55Y!~!!!.. .::75J~^~!JB&&@&&&G75PGGP7J?YBBP7^^JB#P5GPJJYPGY?7~^!Y!^!7??5Y5Y5J5YYJY5PB###BGPPGBGBBGP55PPPGGGG5~..............  .   .  .::^!7J55G&&#&&&&&#GPYJ??J??!!~::::.....:::::^~77?JJJY5YJJJJJYYYYJ??7!?J7!!!~!!!!!?YJ?7~~~!7Y55PGBB##BGB##&&BY???J??JYYJYYPGG55JJ???7??YG#5!!!~~!77???7777~~!?YPG\n"
		+"	?7777J?~^~!~!!:..:!?G57~!!7Y&&@&BYYJ?57~~^^^!GPPP7?!G#&&#B&####B#GP?7~^!~!!7PG5JPP5G#B5P55PBBPP5GGPGGBBGPPYYY555P?^.......:::::^~77^.:^!777?JJ5PGGPG#&&&#GY7~^:.....  ........ .....:^~~!777???7!^...     ..    :77777!~!!!!!7!!7??Y5G5PY5PB#&&&&#####&&&BYJ???J??J5YJYP5BGJ?5JJPB&&P7!!!77?JJJ?!~^~!77?YYY5\n"
		+"	7?!~~7!~!Y5P57.  !Y5Y7!~!!~5&&GY!?#BY!~~~^:^55YPPG##&&&BYP5J?5GBGPY55Y!!7^7Y5P?J#55GBBB5JJ5G57^^YPGPPGPGP5PP55J7?!~^^^^~^::..^?G#BGJ?Y5?YB#BGG##GPB&&&G?~...........   ...::::::^^~~~77777??!~:...  . ........:^~~^!!~!~!~~~7JYPGGGGGGG55GGP5YJJG#&&&&&##&&#GYJ????YJ??J55B55PB&&#PJ7777??777!~~7???!~!?JJYY\n"
		+"	7!~7JYG55Y?5J:   JPY77!7775&&B#&BBGY!^^^^^!?!?PB#&&&BY55~?7^^?BBBPY??JYJ7?J5YG55577J?77~~!775G5?YB#PGP5PPPYJ??7!!!~~!^~~^:^~?B&&#5JP#&&###&&#BPPGB##G!. .          ..::^!!!~!!!~!!!7~~!!?!:.          .. ..:^~~~~~~~~~!7?J?7YYJ55PGG5J7~~~~~~~!?G#&&#B##&&&&&##BP55YJYY5BGB#&&&B5777??J?JJYYY55YJ!^^::^7??J7\n"
		+"	G#77!?J~7J?J~.  !BY!!7???5&@@&#&#P57^^^~7?77YG#&&#GY??PY^~~^~YGBB5J??JY?75J~!J!!~~^7!JJ^!J7~JPP?7JY?7YYJ7~!7~^~:...   !YY5PG#&&&&#######BGYJ?5G###P~. ...       .:!!!7!~!!~~!!?777?J!~^....     .....  .:^~7!~~~^~~!!?JJ??JYPPPGG5?7!~!!7777?YPB#&GY777P#&&&&&&&&&#######B5PBP???J?7?JJJJ??77~::^:..::!?7!!!\n"
		+"	P&B5??JJ?YJ~.  7B5!!?JJYP#&&&#B5J7~^^~!??7YGG#&G?~^77!YY^~:~~!J5BPPBYJY5YP?75YJYJJ7YJ5J77^~~~~7PJJJ!!?Y57~7!JGY:.......^????YJ?7!~^^:.::.^?P#&&&G!...... .  .^^~~!JY7~7??YJ?J77!^:..        ....  .. .^~~7!~~~~~^^~!!7?JYY5PBBBBG5J??JJJYY5GG#&&B?!!7?5B&&&&##&#BBGGGGPB#BYJGGYJJJJ??J??7!^^:::..:^^~~!!!!?Y\n"
		+"	7PJ!7JY!!!.  ~5BY^!7J5GG#&&B57~!^~^:^^^!??J#&BY!~!~!~!~7~^^^^~!J5GG?Y5?YYGJJYYY55PPYY!^~^^^~^~~7Y7!?7?YYGPJP5~..    ....         .....^75B&&&&B!....    ..:^!!^!~~^~PP5Y?!:..                .......^7!!7!~~77777?JYYPGGB5555GBBGGGGPGGGBB#BGP5J!7?YPB&&&###BBGP55PGB###GP55PYYYJYYJ7!~~^:..::::^~!!!7?JYYJJ\n"
		+"	!~^::^7!^:^!JY?~~7?YPG#B##57^..:^:...:~77J##Y~^^^^^^~^:^Y!~^~~!JY5B!~7~?JY7~~::75?7!~7Y5!~!7~7YJJ?YG55PPGGGP7^:..     .  ...........75YB&####7. .......:77~^^7:^7?~~~:...        ....   ..........:!?!!!~~7JY55PGGGBBGY!^.....:!YPGGP5PB##BG5J5GG##&##&&#&&BG555PG#&##GYJYJ55PJY?!~^:.......^^!!!77!7?JJ??!~\n"
		+"	^::..:~7??!77::::!?G&#G55YJ7!~~!!77?!!J!5&#~:....:::^^..7Y!~~~!7J5PG~^^~!^^7J755J7!~!7?77YPPGB#GPB##BG#BGY7!::...     .............5&&GGB#&5.  ....:^7??7~?J?~^::^:.....         ... .. ......:^!???77!7Y55GBBBGPJ!^..     ....:^^^~!!7?YB#######BGY77JY5B#B#B#&&&#B5Y5GJJYPGY7!^^:.......:~^^::^^~~~!JJJY5Y\n"
		+"	^::.~J7!~.::!7~7?J7?7!!~!??JJYJJ?7?!?B?J#&&BBGGPG5YY5PP!~5P?~^!7Y55GY.....:^:~!~^~!!!J7JYG#BBB####&##&BP7^::....::...... ...... ..7&&&&&&B?:..:::^!JJ7^~:YJ^......             .............^!Y55JJJ7?PB##B5J!:.  ..  .......::^~~~!7??!~!YYJJGG57?!!~~77?JG&#B5Y7!~:.:7?7!~^:.................:..:^!??555YY\n"
		+"	::.:7?5YJ?YJ!!!~............::JG7^^~5BPB#&####BBBGB##&&B~:7PY?7JJ55PB!.. ... ........:~~?YPGG####BGPPPY?7!!!!~!!~^::::........^~!5#&&&&G?::^!77??JJ7: .::!!~^:.............  .........:^~~!7JYJ?JJJYP##GJ~...............::::~~!!!^^:~7!7??~!~75?7777!~!77YBBJ^....... ..........................::::^7!~:::\n"
		+"	::...::..::..... ...  ..::::.~7J!~~?5PGGPGGGBB###&&&&#G5J7~!5G5??JY5G5.   ..... ..........:^!JYYYJ?7?JJ??!!~!!^^~:..........~G&&&&@&&G^ .:!??JJ7!^..  .:!JJ7^:.............  ........~?JPJJY5PPPB#&#G57... .. .........::~~!!777!7~:::!^^:^~!??YJJ!?5?!!5GBP!:...................................:~7?J?!!7?!\n"
		+"	::........................:.!YJJYY5GGGBBBB##&&&##BGP555YYYYY?YBBPYJJ5?~:.:::.... ... ..   .....::::^~~~^^^^^:........  ...^~7#&&&&&&B^^~!7??!~^:...    :^^:. . .........  ........::7GBGGJJG&#&&&BJ^:.. ...        ..::^^~~!!!~~~~.........^!Y5J?P7PGJ?P#G?~:::................................:::^~~^::^^::\n"
		+"	::..........................:^^~~~^^:::::::::^^:.........:~~7JYG#G5JJYGGJ?!~~~^^::::..... ......  .::7J~...   .. ........~^^~75#&&&#YY5J7!~:::::::..  . .........................!PB##&&&&&##&&GY~^:... .........::^^^^:::^!!!!~:...........~7J?PG55?5B#G~:..........................................:::::::\n"
		+"	::................:........                 .......:........:::^7PBPYJ5BG?~~^::^^::^^:..........  .:.. .^!!!:...........^::!JJJJ5##5YJ~:.................................. ..:~75BB&&&&&&&&&##5^::........:::^^^^^^^^^^^!J?7?????7!!!::....~J5?5#BP75#&B?............................................:::::::\n"
		+"	:~^:::..::..:::::............:........:......:::...............::^?BBPYPBP~~^......:^::^^^^~!~~~~^^^^^^::~JYJ7~...   ....:^7J5PPBG?!^:::.. .................... ..::~~^::^!?5G#&&&&&&&&&BP5Y7:....:::^~~~^~^~^^^^^!7!~!7777??J?7!~^^:^:.^^!JJPGG##B##B&G~..........................................:::::::::\n"
		+"	::.....................:::........:::.............::..::.:..........JBGPG#B?. .........:::^:^~!!7!7J??77!7!!!~~7^.   .    ..:^~^:.... .....  .. .  ..::.:^~!7::?55!7Y5P5B&&&&#G5?!~^^^~:.  ..^~!~^~~!~~^::..::^::....:^:^~!~!!77!!~^^^^~7J5##&&&&&&&&#&Y!^:.........................................::::::::\n"
		+"	::.......................................:::::::.....................~BBGB##Y^........ ......::::::^~!~!~!!!7!^7?!^:.     .....................  .^^^^!75BG5YYG&&&##&&&&&BY!:......::.::.^^~~~!~^~~^::...^^:^~7J5P5?!~~!~~~^:^~^^^^^^~J5PB&&&&&&&&&&#GJ!^:..........................................::^^::^:\n"
		+"	::...................................:^^^::.......:::::..::...........^J!?PPPGJ^.           ......:^^:.....::^~~!7777~^::..... ............ .....!~^^77G&&###&&&&&@@@&&#5J?7!~~~~^^^::::::.::::...... .~?!~~~7PB###&G^^~^^^::^^~!!7JPB#&&&&&&&&#BGJ7~................................................:.:::::\n"
		+"	:..................... ....:::::::::::........................... .. .~7Y777~~7J5?~:...........   .. ... ......^~!!~??7!!!~~~~^:................:7?JJYP#&&&&&&#GG#&#BPJ7!!::...... ...:.............:.:7YJ7YG##BGPGPY7~~!!777?J5G#&&##G55YJ??77!^:..........................................:............:::\n"
		+"	^:......................:.:^:........  .........:^::::............. .7Y?JJ!7~~~!!JPGGP5YJ???7!7?7!77!~~^^~~^~~!!7!!!!7!!!!!~~~~~~^^^^:..... .....!PB###&&&&#BGY?7~:...     ..      ....................:!5B&@@&&P5JJ5Y5PBGGB&&&#BPJ?!~~::...........................................................::::::^^\n"
		+"	~::.............:...:::^^^::::..:^::::............:::::::.......... ~YJJJJJJ7!~!!!7JGBGPGPPPGPY?!~!!!7777?7!^^^^^^:^::::::~~~~!~!!~^::::::.... ...:~7?7~^:...... . ..........  ..     .       . . ..  .  .:~?5#&#&#&&&&&&&&&BP?!^::.......    ........ ....................................::::::...:...:^^^\n"
		+"	^::.......::^^::::::::::::.......^^^:.................:.......... ..:!?Y5PPP5Y??77!77!!~!7?G##BY?7~...........  ..    .  .......:~!~!!~~~!~~^^::^::::.....  .......... .....  ...   ...      ......           .7B#&&@@&&#P7^..     .. .............................:........................:.......:::::^^^\n"
		+"	~:....::::::........:::.:.::............................::...............::~!!?JYY555P555Y5PJ!^.:...        .  .. ......  ....  .:::^^::::.:^^^^^~~^::^:^..   .  ....  ...............    ...... ..  ..  . ..   .:!JY?~^..       .... ...... ..... . ...............:::..:::::::^::.:::.....::.:::...:::::^^\n"
		+"	~:........................  ...................::::.....................:......:^^::^^^^:..      ......:...........:::::..........:.. ....::::::::::^~^:^^:::..... ............................ .....  .......  .       . ......................................:......:^^:^^^^^^^^:::...............::^~~^~\n"
		+"	~:::.:..............     .  .    ..... .  . .....::..................:..:::.......     .........::::............... ...............::............:^~~~^^~~^~^^^^~:. ....    .  .....    .          ..  .............. ........::............................:........:...:^.::.::..:.......:..::.:.:^~^~~~^~\n"
		+"	!::................                       .  .  ...........::.......:.................. .......    ..........      ..................:..........:^^~~!~~7!^~~!~777^:....          ..                                     . ...... ..  ..      .  ..........:......:....:..:. .  ...:..:  :::::^^^::^~^^~!!~~\n"
		+"	J~~^^^^^^^^:^^::::::::...:..::...:..:..:..:.::..^~^:^^^~~^^^^::^::^:^::::^^^^^~~^^^~^^~^^:::::^^^:^^~::^:::::...:^^^^^^^^::::^~~^~^^^^^~~~~^^^~!???7777??Y???JJJJJ?7!777~~^::::::::::::::::....:::::::::::::::::::::::::::::::^^^^^^^:^::::^^^^^^~~~~~^^^^~~~^^^^^~:^7~^^^!^:~::~::~:^~::!7!777!!~!7777?J???\n"
		+" todos los jugadores con 3 o mas corazones pierden 1, ademas todos los jugadores pierden 8 puntos\n");
		for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){
					if(players[i].getnumberOfLifes()>=3){
						players[i].setnumberOfLifes(players[i].getnumberOfLifes()-1);
					}
						players[i].setscore(players[i].getscore()-8);
				}else{
					comprobant=true;
				}
			}
		break;
		
		case 4:
		 randomplayer1= (int)Math.random()*countplayers;
		 randomplayer2= (int)Math.random()*countplayers;
		 randomplayer3= (int)Math.random()*countplayers;
			message=(" una figura misteriosa se presenta entre sueños ofreciendo gran poder a cambio de un poco de vuestra vida\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&######&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##########################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##############################################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&####&&&&&&&&&&&&##########################################################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&#########&&&&&&#############################BBB###############################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&###################################BB###BBBG5PGB###&&&&&&&&&&#################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&#############################B######BBBGGPPGPPPBBB##&&&&&&@&&&&&&&&################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&#########################BBBBB##BBBGGP55555PGPGBBB###&&&&&&&&&&&&&&&&&&###############&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&########################BBBBBBBGPP5Y5YJY5PPPPGBBBB####&&&&&&&@&&&&&&&&@&&&##############&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&#######################BBBBBBP5YYYJYJJ?YPPGPGBBGBB#B###&&&&&&&&&&&&&&&&&&&@&&##############&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&######################BBBBBG5Y5JJYYYJ?YPGPGPGBGGBB#B###&&&&&&&&&&&&&&&&&&&@&@&################&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&##################BBBBBBBBBB55YYJJYJY5PGGGPPPGGBGBG######&&&&&&&&&&&&&&&&&&&&&@&###############&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&############BBBBBBBBBBBBBBBP55P5Y5Y5PGPP55PGPPGGBGGB#####&&&&&&&&&&&&&&&&&&&&&&@&#################&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&#############BBBBBBBBBBBBBBBPY55GGPPP5PP55Y55GP55BGGBB#BB####&&&&&&&&&&&&&&&&&&&&&&&##################&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&##############BBBBBBGBBBBBBBBBBYYPP5PPPPP55YYYPPGGPPGGGGB#B####&&&&&&&&&&&&&&&&&&&&&&&&##################&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&#############BBBBBBGGGBBBBBBBB5JY55Y5555555555PPPGPGBBGGBBBB###&&&&&&&&&&&&&&&&&&&&&&&&##B###############&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&##############BBBBBBBBBBBBBBBBBGJJY55555PPPPGPPP5PPGPGBGGGBBBB###&&&&&&&&&&&&&&&&&&&&&&&&#BBBB###############&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&#############BBBBBBBBBBBBBBBB#B5JY55555555PP5PPPPPPPPPPPPPPPPPGGGBB######&&&&&&&&&&&&&&&&&#BBB################&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&#############BBBGGPP555555YYYYY??YYY5PPPGBBBB###################&&###############&&&&&&&&&#BBBB#################&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&##########BGYJ?7!!!~~~!7?JYPPGBB##&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@&&&&&&&&&####&&#####&&#&&###B#################&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&########B##57~^~!7??JJYPPB#B#&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&######&&&&&&&&###############&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&##########B?^~!?Y55PPGBB##&&###&&&&&&&&&&&@@@@@@@&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&@@&&&&&&#####&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&###########?^7JJY5GGBBB######&&&&&&&&&@@@@@@@&##BBBBBBBBB##&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&@@@@@@&&&&&&&&##&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&#########B!?J!?YPBBB######&&&&&&&&&@@@@&&&#BBGP55PPGGGBB####&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&###########??!7?JGBB#####&&&&&&&&@@@&##BBBB#BBGP5PGGBBB##&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&###########GJ~!?YPB####&&&&&&&&&&@@&BGGGGGGBBBGGP5PGGGGB#&&&&&&&####&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&############G7~YPGBBB#&&&&&&&&&&@@@#PPPPPPGGGBBGGP5PGPPGB&&&&&&#BBB#&&&@@@@@@@@@@@@@@@&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&############G!?PGGB##&&&&&&&&@@@@&GY5PY??7!?J5PGGBBBBGG##&&&&&#BGG#&##&&&&&@@@&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&#############57JGB##&&&&&&&&&&@@@&PJ5Y7~~::^!J?YPB&B#B#&&&&&&&#BGB######&&&&&&&&&&####&&&&&&@@@@@@@@@@@@@@@@@@@@@@&##&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&############PJPBB#&&&&&&&&&&&@@#YJ5Y!77?YGGBGBBB&&&#&&&&&&&&&#######&&&&@&&&&&&&&########&@@@@@@@@@@@@@@@@@@@@@&##&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&############BP5GB#&&&&&&&&&&@@BYJ5??5GBB###&###&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@&&&######&&@@@@@@@@@@@@@@@@@@&&##&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&##############G5PB#&&&&&&&&&@&GJJY?PB#&&&##B##&@&@&@@&&&&&#&&&&@@@@@@@@&&@@&&&&&@@&&#####&&@@@@@@@@@@@@@@@@&###&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&############B##BGPGB#&&&&&&&@#5YJ7JB#@&##GGB#&&@&&&&@@&#GYG##&#&@@@@@@&&#&&@@@&&&@@&&B####&&@@@@@@@@@@@@&&#######&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&##############BBBBBGGGB#&&&&&&B5Y?:JB&@#5##&&&@&@&&&@&&&PPYG##&&&&&@@@@&&&&&@@@@&&&@@&#B###&&@@@@@@@@@@&&#######&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&#############BBBBBBBBGGBB##&&PYY^:YG&&GPG&@@@@@@@@@@@&#BP5&#&&&&&&&@@@@&@@@@@@&&&&&&&#BB#&&&@@@@@@@&&############&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&##############BB##B#B#BBBBBB#PYJ:.75B#&BP5G#&&&@&&###&&B7~#&##&&#&@@@&&&&&&&&&&&&&&&&#BB##&&@@@&&&###############&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&###########################PJ?: :JPPG&&BG#BBB#B###BPJ~!!GGGB&##B#&@@@@@@&&&&&&&&&&&#BB#&&&&&###################&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&#######################BYJ~.:?555G&BB#BGGP55PY5Y~!?!PGG#&##BBBB#&&&@&&&&#&&&&&#############################&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&####################PY?^!J7?YP@BPGGG?JPG&BP5JP!~PBB#&####&&&&&&&&#&&###&&&&&&&#########################&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&##############B?Y5Y7~J5P@PJY##5G&@#Y?~J?:~PGB##&######&@@@@&&&#&&&&&&&&&#######################&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&#############&Y75GGPP5P&5JYB&#&@&BP?7?!~!PB##&#########&@@@&&&&&&&&&&&&####################&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&##############B7!YGBPYP&5YP#@@#P#GY#&&BGPPB&&&&&&&@&&####&@&&&&&&&&&&&&##################&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&##############&P77YPPY5&5P#&@B!!G?7YB&5Y5B##&&&@@@&&#####B#&&&&&&&&&&&#################&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&###############?7?5PP5#BP&@#7^75!7?G#Y5P#&&@&&&&&&######B##&&&&&&&&&&################&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&############&Y7!?PPPG&BBB57?GYJJYP5?5PB&&#PP#&&#####&&####&&&&&&&&&###############&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&#############G7!~?5PPG&5GP!Y&GJ!^5JJY5BBBP5G#&&&####&&&&###&&&&&&&&#############&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&##############Y7!~?555BGGG5BB#P5GBBPPB###&&&&&&&&&&&&&&&&&#&&&&&&&&############&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&##############BJ77!7YP5BYG#B&#GPGPB&&#&@@@&@@@@@&&&&&&@&&&&&&&&&&&##########&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&###########&BYYJ?J55GB&BB&#Y!?7JG#P#&&#B&&&&&&#&&&&&&&&&&&&&&&&########&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##&BPP55P55G#&GB&Y5P55GB#&####&&&&&&&&&&&&&&&&&&&&&&#######&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#BGBBP55G##P&G5GBB#####&#&&&&&#&&&&&&&&&&&&&&&&####&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&BP#BGPPG&BB&GB#&&&@@&&&&&&&@@@&&&&&&&&&&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&B5G###GGG&G&BGBBB#&&&&&&@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&BB#BG###B##B#5YY5G&&B###&&@@@@&&&&&&&&&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&###&##BGBBBB&##5JY5G&&#&&&&@@@@@@&&&&&&@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&###&&##BBBGGBBB######&@&&&&@@@@@@@&&&@@@@@@@@@&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&#&&&&&####BBBBBB##&&##&&&&&&&&&&&&&&@@@@@@@@@@@&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@&&&&&&&&&&&&&###########&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&@&&&&&&&&&&&&#######&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&@&&&&&&&&&&&&&&##&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
			+"algunos de los suyos aceptaron el trato "+ players[randomplayer1].getnickname() + " , "+ players[randomplayer2].getnickname() +" y "+ players[randomplayer3].getnickname()+" decidieron hacer negocios con la figura misteriosa pierden 1 corazon  y ganan 10 puntos ");
			if(players[randomplayer1].getnumberOfLifes()>=1){
				players[randomplayer1].setnumberOfLifes(players[randomplayer1].getscore()+10);
				players[randomplayer1].setscore(players[randomplayer1].getnumberOfLifes()-1);
			}else{
				players[randomplayer1].setscore(players[randomplayer1].getscore()+10);
			} 
			if(players[randomplayer2].getnumberOfLifes()>=1){
				players[randomplayer2].setnumberOfLifes(players[randomplayer2].getscore()+10);
				players[randomplayer2].setscore(players[randomplayer2].getnumberOfLifes()-1);
			}else{
				players[randomplayer2].setscore(players[randomplayer2].getscore()+10);
			} 
			if(players[randomplayer3].getnumberOfLifes()>=1){
				players[randomplayer3].setnumberOfLifes(players[randomplayer3].getscore()+10);
				players[randomplayer3].setscore(players[randomplayer3].getnumberOfLifes()-1);
			}else{
				players[randomplayer3].setscore(players[randomplayer3].getscore()+10);
			} 
		
		break;
		
		case 5:
			message=(" os encontrais con un viejo medico el cual les ofrece un poco de ayuda para vuestros viajes\n"
			+"	.                                                                                                                                          .^~^.     .\n"
			+"	.                                                                                                                    :~~.         .::::^7??~.        .\n"
			+"	.                                                                                                               .^J557:       :~!!~!?JY!:            .\n"
			+"	.                                                 ..::^^~!!!!~^^:...                                        .~YGGY^.      :!JJJJY5Y7:                .\n"
			+"	.                                          .!YGB##&&@@@@@@@@@@@@@@@&&##BPYJ7~:.                         .!P##5!.     .:7JYY5PGP?^.                   .\n"
			+"	.                                         ~#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&#PJ~.               :?G&&G7:     .^75555PBGJ~.                       .\n"
			+"	.                                        !@@@@@@@@@@@&&&&&&@@@@@@@@@@&@@@@@@@@@@@@@@@&GY7:.    .^JB&&BJ^     .^?555PPGGY~.                           .\n"
			+"	.                                        Y@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&@&@@@@@@@&#5!7G@&Y^     .^7Y555PGGY~.                              ..\n"
			+"	.                                    ^7Y?^@@&@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@&#Y.    ^7YP55PGGY~.                              ......\n"
			+"	.                                :!J?!:   G@&&@&@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&@&&@@@~~?555PPGGY~.                        .::   .^^^.    .\n"
			+"	.                            :!J?~:       ~@&&@&@@@@@@@&@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@&@&@&&@B^P5GBG5!.                        .~?J7~~~~^:        .\n"
			+"	.                        :!J?~:            #@&@@&#G555B##@@&&&&&@@@@&&&&@@@@@@@@@@@@@@@@@@@&&&@#?BB5!.                        :75P5?777!:.           .\n"
			+"	.                    :!??!:                J@@&B&@@#?#@@@B&@@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@@@@P:~:                       .^JGBG5J??!^.               .\n"
			+"	.                .~??!:                    Y@@P&@@@@5@@@@@Y@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Y                       .~5B&#G5YJ7~.                   .\n"
			+"	.            .~77~:                       .&@#5@@@@&Y@@@@@Y&@@@5Y#@@@#P&@@@&@@@@@@@@@@@@@@@@Y                    .!5#&&BGPPJ~.                       .\n"
			+"	.         :~!~:                            #@@Y#@@@GJ&@@@GG@@@@&#@@@@#B&@@&5#@@@@@@@@@@@@@@&.                .7P&@&#GGPY!:                           .\n"
			+"	.      .::.    .5BGPJ7!^::..::^~!7?Y55PPPGG&@@&&@@@&&@@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@G             .!P&@&#BGG57:                               .\n"
			+"	.               .?&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!         .!5#@@&BBBP?^                                   .\n"
			+"	.                  :Y#@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@&^.    .!P&@@&#BBGJ~.                                      .\n"
			+"	.                     .~YB&@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&@@&&&&@@@@@&&&&&&&&&&&&&@@@@@@@@@@&#GYY5PGB#BGY~.                                          .\n"
			+"	.                          :~JP#&@@@@@@@&@&&&&&@@@@@&&&&&&#&&@@@&&&@@@@&&&&&&&&&&&&&&&&&&@@@@@@@@@@&#BG57~^:.......                                  .\n"
			+"	.                                .:::^PG~P&@@&&&&&&&@@B5?!!!5GB@@&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@&Y.                                  .\n"
			+"	.                                     GG.J&&@&@@@@@@@P?^     ?GG&@@&&&###&@&&&&&@&&&&&&&&@@@@@@@@@@@@@@@@@@@&G?^.                                    .\n"
			+"	.                                     5#~^#@@@@&&&&&&JJ       GP&#@&&#YB&&@&&&&&@@@@@@@@@&B#&@@@@@@&#BGY?~:.                                         .\n"
			+"	.                                     :&P!#@#BBBG5P&&J?       GP&&@&&PG#GB#&&&@@@@&&&&&&@~    ....                                                   .\n"
			+"	.                                  .^!YGPY?!~~!!?557#BJ.     ~BG&@@##PG&&&@@@@@@&&&&&&&@5                                                            .\n"
			+"	.                              .~J5??7777J5PGGPP5Y#G!&#5!^^~YBB&&&B!!?JY5&@&##&@@@@&&&@@~                                                            .\n"
			+"	.                           .~75P5GYJY5P55YJ??7777Y&P!B#&&&&&&&G5G!!!!!!!#GB##&&&&&&&&&&&7                                                           .\n"
			+"	.                          ^#GGBPGBG77!!!!!!!!!!!!!JBP77JY555YY5G?!!!!!!?&5&BB@@&&&@@@&@&~                                                           .\n"
			+"	.                      .7Y^BGYPBP5#P~!!!!!!!!!!!!!!!!J55YYY5PPPJ!!!7!!!!!BPG@BG#@&&&&&&@&:                                                           .\n"
			+"	.                  .!P&&#?BG55Y555#G~!!!!!!!!!!!!!777???JJJ?7!~!!!!!!!!!?YPP#@#&&&&&&@&?.                                                            .\n"
			+"	.              .~5#&B?: .BGY555YYY#P7!!!77?JY55PGGBBBBGGGGGGPPYJ?77!!!!?7YY5#&@&&&&@@@&?                                                             .\n"
			+"	.           ^JGBG?:  .~.BBY55555GG@B##B######&&P5B&@GPP#&&#BBB###BGPPYYPPYG###@&&&&&&&&@#:  ...                                                      .\n"
			+"	.       .!YG57.  .!P&@7BB5G5YYYG&&&&#BGPYJ7J#@G~Y#@B~!P&&G?!7G&@#Y55GB##BBB##&&&&&&&&&&&B.  .^^~~^^^^^^^^^^~^^^::::..                                .\n"
			+"	.    .~??~.   ^Y#&BY^.?&PG555B&&#BBGPP5555PB&&JG&&G75&&#?!75&@@5!!!!5B&@&&&&&&&&&&&&&&@#Y!.      .......::::::^^^^::::^^::.                          .\n"
			+"	.    .    :?GBG?:     #BGYP#@@&BG5J7~:..     ^YGBP5#&&J!JG&&#57~~!JG&@@&&&&&&&&&&@@@&&&&@@&P:                    .::^:...:^~~.                       .\n"
			+"	.     .~YP57:         &&B&@@#Y~:.                !Y#B5G&&&57!~!?5B&@@&&&&&&&&@@@@&&&&&&&&&&@&?                      :?......~?                       .\n"
			+"	. .^7J?~.             B@@@5:            ..:.:::..  ~Y&#5J7!7JYB#&@@&&&&&&@@@@@&@@@&&&&&&&&&&@@G:     .!~~!!7JJ??JJ!~^^:^^^~~^.                       .\n"
			+"	:!^:.                 G@P.        .:^^:^^^^:::.......~YB&&&&&@@&&&@@@@@&@@@@&@@@@@@@@@@@@@@@@@@@GJ!:.^!:~~:                                          .\n"
			+"	.                     .^        .~^:..!!:.....::::.   ^&@@@&&@@@@&&&@@@@&&@@@@@&@&&&&&&&&&&&&&&&&@&&&#G5?7!~^:....:.                                 .\n"
			+"	.                               ?7 ....:::^^^^~!!!???~&&&@@&&&&&&@@@@&&@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&BPJ?!~~~~77~^::^^^::..                     .\n"
			+"	.                               :7~^^:::^^^^........J@@&@@@@&&&&&&&&@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@&&#B###&&Y........:~?                   .\n"
			+"	.                                 .:::^^^^^~JB5.....^@&&&@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#BGGG55555Y?7~^^^^^^^^^^^                   .\n"
			+"	.                            .~!!7777!!!!!!!?G!......#@&&&&&@@@@@@@@@@&&&&@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&@J  :!::......:::.....                       .\n"
			+"	.                         .:~^:..........:~777!77!~~^~5#@&&&&&&@@@@@@@@@&@@@&&&&&&&&&@@@@@@@@@@@@@&&&&&&&&GJ!~~~~~!!77???7~^^^:.                     .\n"
			+"	.                      .??~:...:::::::..~7~^::::::^~!7JJ75#&@&##G5YJ??J&@@@@@@@@@@@@@@&&#G55Y55PB##&&&&&&&@@@&&&&&&&&&&&#B!....:^ .                  .\n"
			+"	.                     .G~.....7!.    .:7G:.............B~ JG!^.........55J?7JG&&&&&&B!:.............:::^^^^^^::::::::::...........:^                 .\n"
			+"	.                     .#~......!!!7!777!:~77J?^..^?~!!?7..P?....^~^.......... 5@&&&@P7~^::...........~.:. ......................::!~                 .\n"
			+"	.                      .77!?Y?7^........^~!~!?BJ.#!:~!^~!7J:.:G#YJ?J???!:.....~#&&@&@@GJ??7~^::.....^J7??55J!:^::...:^:::::::::::..                  .\n"
			+"	.                           :!77!777?7?JJ!^~~^!7.!Y?!:...... ?&.      .:7?.^....^!?J?7^::....:^^....:~5?:::~55..::::.                                .\n"
			+"	.                               .......  ~J!..................75:       .?P~.........^~!77!^................ G?                                      .\n"
			+"	.                                        GY............^:.....:BJ      :B~..........!.....:~P7..............^G^                                      .\n"
			+"	.                                         J5?!!!!7?7!~^~??!!7?J^       .YY~:..............:GP???!^:....:!!~77.                                       .\n"
			+"	.                                           .....         ...            .:^^~! ...........^!BG^~7JJJJJJ7:                                           .\n"
			+"	.                                                                             ~7^:.........:!G~      ..\n"                
			+"gracias a las inyecciones del medico todos obtienen una vida extra \n");
			for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){
						players[i].setnumberOfLifes(players[i].getnumberOfLifes()+1);
				}else{
					comprobant=true;
				}
			}
		break;
		
		case 6:
		 randomplayer1= (int)Math.random()*countplayers;
			message=(" en vuestros viajes os encontrais con una vieja leyenda materializada: la espada de arturo penworth\n"
			+"la leyenda cuenta que quien la levante sera imbudio con una de las gracias del propio rey arturo\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@&&&@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&####&&&&#####BBBBBBBBBGPP5YJJJ??77!!!!!7??7!77~^:::......................:::::::::.....\n"   
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&@@@@&&&&&&&&&&&&&&&&&&&&&&&&&##BBB#&&#####B#BBBBBBBBG55YJJ?777!~~!7???7??!~~^::......................::::::::::::.:^^::.\n" 
			+"	@@@@@@@@@@@@@@@@@@@@@&&&&&&&&@@@@@@&&&@@@@@@&&&&&&&&&&&&&##BBBB#########BBBBGGGGPPJ??77!!!!!!7?????7!!^:::....:............:::::::::::::::::^:::~~::^~\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&&&&&@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&###B#######BBBGGGP5555Y?!!~~!!77??JJ?7!!~^::.::..............::::::::::::::.:::::^~~~????5G\n"
			+"	@@@@@@@@@@@@@@@@&&&&&&&&&&&&&@@@@@@@@@@@@@@@&&&&&&&&&&&&&&#####B####BBBGPP5YYJJJJJ?!!!777?????7!~^::::::::......     .::::::.......::::^^~~!!7??555PGY\n"
			+"	@@@@@@@@@@@@@@&&&&&&&&&&&&&&@@@@@@@@@@@@@@&&&&&&&&&&&&&&&#BG?~JB###BBGP5YJJ?777???7????7?777!~^^::::::::::............::::::.......^~~!!!!77JJJJYY7?57\n"
			+"	@@@@@@@@@@@@@@@@@@@@@&&&&&&&@@@@@@@@@@@&&&&&&&&&&&&&&&&&&B&&?!YBBBBGGP5YJ?7!!!!7?JJJJJ?7!~~^:::::::::::...........:::^^:^::::::::^~!7???7777?7777~~^^:\n"
			+"	@@@@@@@@@@@@@@@@@@@@@&&&&&&@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&@&#JBBBBGGP55JJ7!!!!7?JYYJJJ7!~^^::::::::::::.......:::^:::^::::::::^~!77?????777!~^..:~~~:::\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&@@BYPGBGGP5YY?7!!7?7JJYYJ?7!^^:::::::::::..:....::::^^^^:::..:::^^~~7?JJJ??7??7!!~^::~77~^:::\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&#P?GGGP55YJ????JY?JJJ?7!^^::::::::::::.......::^^^^~^^:.:::^^~!!?JYYYYJ????7~^^^~^!7!~^^^^^\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@&&@@@@@&&&&&&&&&&&&&&@&&&&&&&&&&&&BB?PP55YYYYYYYYYY?77!~^^::::::::::::.....::::^~~~^^^^^::^^^~!7JYY5555YJ7!!!^^:^~~~~^^:^^^^^\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&@@@@@@@@&&&&&&@@@@@&&&&&&&&&&&&#B##PPP55YYJY55YYYJ!~^^^^::::::::::......::^^~^~~~::^^::^^^~!??Y5P555YJJ7!^^^^^^^~^^^:^^^~~~!\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&@@@@@@@@@@@@@@@@@@@@&&&########BBBG##5~J55?7Y5YYJJ7^::^~~^^::::::.....:::^^~~~^~~~:.^^^::^^~?555555YYY?!~^^^^~~~~~^^^~~~!!77?\n"
			+"	@@@@@@@@@@@@@@@@@@&&&&&&&&@@@@@@@@@@@@@@@@@&&&###BB###&&&&#PG&P^755P5YJ????7^:^~!~^^:::::....::^^~~~~~~:~~~::~~~~^~:~JPP555YJ?7~::^^~~~!~~~~!!77?JJJJJ\n"
			+"	@@@@@@@@@@@@@@@@@@&&&@@@&@@@@@@@@@@@@@@@@&&&###BGBB####&&&&&#G5!JGP5YJ??77?7^^^~!~^::::..:::^^~!!~~^~~~^~!~^^!7~^!!~:^?Y5YYJ7!~~^^^~~!7!!!7??JYY55P55J\n"
			+"	@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@@@@@&&&&&#BBBBBB####&&&&&&&BP!!55YJ???????^^:^7~^..::::^^~!!!!~~~^~~~^~~!!!7J7!7YY!^77JJ?!~~~~~~~!7???77JY5PGGGB#PGP\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&###B#BBB#&&&&&&&&&&#G?7?JJJ???????^^:~!~^:::^^~~!!!!!!!~~~~!!!!7JJJY55Y577?7?77!!~~^^^~~~7777???JYYPGGGG&&#B\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&#&######&&&&&&&&&&&&&&PY?7JJJJJ?????!~:~!~~~~~~!!!!~!!!!!!!!77??JY555P5J7Y7^7!~^^^:::^~7??????J5PGG5J?PBPGBBGB\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&##&&&&&&&&##&#BBB#&PYJ?JJJ????77?!^^~7!!!!!!7777!!7!!777!?JYJ5PG5Y5?!~!~~~^^:^^~!7?JJJJJYY55Y??!!!^!!JY5GGB\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&#####BBBBBBB&&GPY?JJ?777!77~:^!JJ?~~777!77!7777?JJ?J5PYP5J~~777!~~!~~~~!!7?JJJJJJYY5P5JY!^^7J5#&&&&&&\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@@&@@&&&&&&&&&&&&&&&&&&&&&#BBGBBGGPPPPPG@#BG!~~~^~~!7?7~~?YYJ!~!777~!!!!7???J55PP5J7^~JY?7!!!!~!!!7?JJJJJJJY5PPPYJJ!?YG#&&#&&&##\n"
			+"	@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&@&&&&&&###BGGYPY5?7JY55J&&&B?~~^^:^:^!?!!JYYJ!:~!~!~^~!7?YY5PP55YYYJ?5YJ77!!!!!7??JJJJJJJY5PGGGGY55PGGG##&&&&&&&\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&@@@@@&&&&##BBGG5PG#GBPPBBB&#&@&#PYY?~:^::::^!?YJ?~^~!7??JYY5P55555P5P555YJ7!!!!!!!7JYYYJJJ7!77?Y5GBPJJ?YPPBBBGB&&&&&\n"
			+"	@@@@@@@@@@@@@@@@@@&&&&&&&&&&&@@@@@@@@&&&&&#BBGGPG######&@@@&GB5Y?7?P55Y?7^^::...~??77?JJYY55P555PPPPP555Y?7!!!!!!!7?JJJJJJJJJ??7??!77J5PGBB##&#&##&&##\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&@@@@@@@@@@&&&&&##BGGGB#&&#&@@@@###5YY!775PGBG5Y?!~!~^^..:7JYYYY55PPYJPGGPPP55YJ7!!!!!!!7?JYYYJJJJY55Y??JJGBG5G#&#&&&&&&&&&&&&\n"
			+"	@@@@@@@@@@@@@@@@@@@&@&&&&@@@@@@@@&&&&&##BBGG#&&&@@@@&#GGBGPB&#GGPBBB#BP?J?!!~^^^~^^:!YPPGGGGGPPGPPP5YJ?7!!!!77?JJJJJJJJYPPGGBBG55B&&&#&&B&&&&&&&&&#BBB\n"
			+"	@@@&#@@@@@@@@@@@@@@@@&#@@@@@@@@&&&&&###BBGB&@@@@@@@&GPGB###&&&&&&##BBGGPY????7~~?7?~.:PBBGGGGPPPP55J?77!!777?JJYYYJJJYPGGP5JYBB#&##GY55BGB#B##&&##BGPB\n"
			+"	@@@@&&@@@@@@@@@@@@@@@@@@@@@@@@&&&&####BBB&&&@@@@@&&#BB&#B##&&##&&##B5BBBBPYJY???77777..?GG5GPPP5YY?77!7777?JYYYYYYY5PG5JJPJ77P&&#GBYPPPGB#####BBBBBBBB\n"
			+"	&&&&&&&@@@&&@@@@@@@@@@@@@@@&&&&&########&@@@@@@@&&&#BB&#B&&&&&&&&&&&G###BGYYYYY5???77!:.:?5PGP5YJ?7777?JJY55Y555555PY7JPB#BBB#&&&&&&#&B##BBG#GB##&&&##\n"
			+"	@&@@@&&&&@&B&@&@@@@@@@@@&&&&&&&&######&&@@@@@@@&&&&&##&##&&B&&&##&&#&&&&#BGGPY!YPJJY!~^:  :7J7!!77?77??JY5PPJJY55Y77J?JPBBB&#&&&&&#B##&&&&&#&###&&##&&\n"
			+"	&@@@@@@@@&@&@@@@@@@@@&&&&&&&&&###GG5P&&&@@@@&&&&@&&&&&##&B#&&@&&&&#B#&#&PPPPGJ77P5?J?~^7!. ..^~!!!!7?JJYJJYYYY?JYPGPBGP?5BB#&&&@@&&&&&&&&&&&&#####&&&&\n"
			+"	@@@@@@@@@@@@@@@@@@@&&&&&&&#####&#GGG&&##&@@@@@@&&&&&&#&&@&##&&&&@@&&#BBBG5PP5J5B&PY5Y?~:^::. .:^^~~~~!777!??YYJYYYPB#GB55B#&&&@@@@@@@@@&&&&&##&&&&&&&@\n"
			+"	@@@@@@@@@@@@@@@@@&&&&&&&#############&&#&&&@&&&@&&&@#####&@&B#&&@@B#GB#&##BPGGB#@&PP5GJ^::^^:..^~~~777J???7YGBBBG555GGB#GB&&&&&@@@@@@@@@@&&&##&&&&&&@@\n"
			+"	@@&&@@@@@@@@@@@@&&&&&&&&&&###&###BG#&@&&@@@@&&&&&&&&#BGBG#PBB@@&&&&&&GGG##&##GPB&&PG5?Y7~::::^^:^!?JJ??7?77?5PPPPG5GBB&&&&&&@@@@@@@@@@&@@&&&&&&&&@@&&@\n"
			+"	&BY5&@@@@@@@@@@&&&&&&&&&&###BBGBGYG&&&&&@&&@##@&&@&&#BGGB#P5&@@@#&#&&#GGBBPGGBPG#GP5J?JJ?~^::::^^~~^~~!^!7???YGPB#&&&&&&&&&&&@@@@@@@@@&&@&&&@@@@@@@&@@\n"
			+"	@@#B##&@@@@@@@&&&&&&&&&##P5J7~~~!7?#&@@@@@@&&&&#&#&&#&BB#&&&&&@@&&@@&&&&##GBGBGGGGGGGG5JYJ7?!^^^~^^:!!!?5BB#GGBPYB&&&&@@&&P#&&#@@@@@@&@@@&@@@&@@@@@@@@\n"
			+"	@@@@@@@@@@@&&&&#&&&&##BB#BP55Y???YP&@&@@@@@@@@@&B&###&&##&@&&@@&&&@@@&##&B##B#BP#BP55GPGGGPGGP5!~~~:^7YY?JGGB#BB&@@&##BY5PGGBBG&&&@@@&@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@&#&@@&B&&#B55YJJ?7JP?^^!&@&@@@@@@@@@@&&&&@&&##B&##&&&&##@&&&#&&BPBB##B##BBGG#B5BB#B#GJ!!!~^:JGP##&#&&&&&&BBGB#GB##B#B&@&&&&&@@@@@@@@@@@@@&@@\n"
			+"	@@@@@@@@@@@@@@@@@&&&&&#BBB###BBBPG&&&@@@@@@@@@&&@@@@@@&&&&GP#PG&&&&&&&&&#BGBB####B###G&&&&&&&&#PPYYJ~~7!YGGG5GYYP&&#&&@&#PG#&#G&&&&&#&&@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@@&&&&#GJ!7JY5BB#&&##@@@@@@@@@@@&&@@@@@@&B#BG#B?5B#G#&##&&BGBBBGBB&&&#####BB####&G5PPPGY?Y5?7?JPP#@&&&&@&&@&#@@@&&&&&&&&&&&@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@@@@&&&&&#BP?7??!JG&##B#&@@@@@&@@@@&&&@&##&#BGJ?G#&GGGGBBB#&BPP5YGB#B####&###&&&#&GPGYYJY5PG#GBGG#######&&&&&@@@@@@@&&&&@&@&@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@@@&&&&&&&&&&&&#BB#&&&&@&&&@@&&&&&&&@&#&&##&&&GP5JB#&&#&&####&BP##BBGPGGGGBB#&##&###PPPGP5J?5PPY5BG&&BGGBBB#B#&&&@@&&#B#GG&#&&@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@&@@@&&&##&&&&@@@@@@@@@@@@@@&&@@@@@&&&&&#B#GBGP#&B##B##&&&&&&#&&##B#BP5PPPPPPPPPGPG###BGGP5P55PGGGGBG#&#BGBJYPG#&&#BB#BP5GGB#&&@@@@@@@@@@@@@@@@\n"
			+"	@@@@@@@@@@&&&##&&B&&&@@@@@@@@@&&#&&&&@@@@@@&#&&&&&G#GBBBB&#BBGBG#BB&&&&&&B#GB###5PGPP55PPG#GBBGBB#BBB##BB&###&###555BG####BG#G#&&&&&&&@@@@@@@@@@@@@@@@\n"
			+" al intentar levantarla el jugador "+players[randomplayer1].getnickname() +" logra levantarle se le concede el don de arturo, el jugador aumenta en 2 sus corazones y  su puntaje en 3\n");
			players[randomplayer1].setnumberOfLifes(players[randomplayer1].getnumberOfLifes()+2);
			players[randomplayer1].setscore(players[randomplayer1].getscore()+3);
		break;
		
		case 7:
			message=("os encontrasteis a un viejo barquero que necesitaba vuestra ayuda para defender sus mercancias de posibles piratas, todo a cambio de una jugosa recompensa\n"
			+"	^::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^:^^^^^^^^^^^^^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^^^~7?????JY?7??????JJYJJJ??77?7!~~~~~~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!\n"
			+"	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^^^^!7???JJ??777777777JJ??7?7!7!!!~~~~~~^~~~~~^~~~~~~~~~~~~~~~~~~~~~~~~~~~!!\n"
			+"	^::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^^^~777?J??7777!!!!7775?777!!!7!!!~^~^^^^^^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
			+"	^::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^^^^~77?JYYYYYYJJ???77Y55Y55YYYYY?77~^^^^^^^^^^^^^~~~~~~~~~^^^^^^^~~~~~~~~~~~~\n"
			+"	^:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^~~^^^^^^^^^^^^^^^^^^^~^^^^~!77!?7?5555555555Y55GYYYYYYYYYJJJ?!^^^^^^^^^^^^^~~~~~^^^^^^^^^^^^~~~~~~~~~~\n"
			+"	^::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^:::^^^^^^^^^^^^^^^^^^~~~~~~^^^^^^^^^^^^^^^^^^~~~~!77!!777?Y5555555555555JJJJJJYJJ?7!!~^^^^^^^^^^^^^^^~~^^^^^^^^^^^^~~~~~~~~~~!\n"
			+"	^::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^:::^^^^^^^^^^^^^^^^^^^^^^^~^^^^^^^^^^^^^^^^^~~!7JJJJJJJJYYYYYYYYYYYY555J???77?J777!~~~^^^^^^^^^^^^^^^^^^^^^^^^^^^~~~~~~~~~~~~~\n"
			+"	^:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^^~~~~~~~~^^~^^^~^^^^^~~?JJYYYYYYYJYYYYYYYYYYJ?777!!!7Y?!~~~~~~^^^^^^^^^^^^^^^^^^~^^^^^^^^^^~~~~~~~~~~\n"
			+"	::::::::::::::.::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^~~~~~~~~~~^~~~^^^^^^^!JJYYYYYYYJ?JJJJJJJJYYYYYYYYYY5PJ7!~~~~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~~~~~~~~\n"
			+"	^::::::::::::::::::::::::...:::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^~~~~~~~~~~~~~~~^^^^^^^7?YYYYYYYJ????J?????JYYYY55YY555YYJJ?77!^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~~~^~~~~\n"
			+"	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^^^~~~~~~^^^^~~~7JYJYYY5J????7????7?JJYYYYYYYYYYYJJJ????~^^^^^^^^^^^^^^^^^~~^^^~^^^^^^~~~~~~~~~\n"
			+"	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^~~~~~^~~~~!?JJJJYYJ7777777?77?JJJJJJJJJYYJYJJJ??7!!^^::^^^^^^^^^^^^^^~~^^^~^^~~~^~~~~~~~~~\n"
			+"	:::::::::::::::::::::::::::::::::::::::::::.:::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^~~~^~~^^^^^^^^^~~~~~~~!7???JJJ?77777777777??JJJJJJ??JJ??J??777!!!~~^^::^^^^^^^^^^^^^^^^^^^^^^^~~~~~~~~~\n"
			+"	::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^:::::::^^^^^^^^^^~~~^^~^^^^^^^^^~~~~~~!7?????J?7!!777777!!???J????77??J??77!!!77?!^!77!!~^^^:::^^^^^^^^^^^^~~~~~~~~~~~~~\n"
			+"	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^::^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~~~7777?7777!!7777!!!!7J77777777777J!77!!7?J?7~^!??777?7!~^^^^^^^^^^^~~~~~~~~~~~~~~~~\n"
			+"	::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~^^^~~~~!7777777777!!777777!J5?7!!!!!!!7!JY!!77JJJ??!~^~???77????7!!~^^~^^^^^^~~~~~~~~~~~~~~\n"
			+"	::::::::::^^^^^^^^^^:::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~~^~~~^~!7???JYJJJJY5YYY5JJJ?J?!!!!!!!!?JJ5J!7JYYYJ?7!~^!?7777??????!~^^^^^^^^^^^~~~~~~~~~~~~\n"
			+"	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^~!!77777JJ?JJY555PGGPP5Y77777777!?YYPBPYY5YJJJ?7~~~!!!!!77??7?J7~^^~^^^^^~~~~~~~~~~~~~~~\n"
			+"	::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^::::::^^^~~~?J????JJJJYYYJJYYYYY5Y5P555PG5GGG555P5P5YYYYJJ?7!~~~^^^^^^^~~^~!7!^~~~~~~~~~~~~~~~~~~~~~~\n"
			+"	::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^^^^^^^^^^^^^^^:::::::^^^~~~7??JYYY555555PP5GG5PP5YYY55Y55PYPP55555YYJJJJ?7!~~~!^^^^^^^^^^^^^^~~~~~~~~^~~~~~~~~~~~~~~\n"
			+"	::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^^:::::!7?JJJYYYYYY5P555PYYYYY5YJJ5?J5555JYYJJJJJJ77!~~~!^^^^^^^^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~\n"
			+"	:::::::::::::::::::::::::::::::::^^~~~~~^^^^^::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::^!7?JJJYYYYY555Y5YYYYYYYJ?YYYYYY5JJYYJJYJJ?7!~~~~!~^^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
			+"	:::::::::::::::::::::::::::::::^~~~!!!!!!!!!!~~^^^^^^^^^^^::^~~^^^^^^^^^^^^~~~^:::::::^^^^^:::::::::::::::::::::::^77?JJJJJYYYYJY55YJJYYJ??YYYYY5J?YYJJJJJ?77!~~~~!~^^^^^^^^^^^^~~~~~~~~~~~~~~~^^^^^^^~~\n"
			+"	::::::::::::::::::::::::::::::~~~~!!!!77!!!!777!!!!!!!!!!!~~!!!!!77!!!!!!!!!!!!!~^^^^^~~~~~~^:::::::::::::::::^^:::~777?JJJYJJJJYYJJJJJJ?7?YYYYYY?JYJJYYJ?77!!~~~~!~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
			+"	:::::::^^^^:::::::::::::::^:^~~~~~!!77!7!!77777777!!!!!77!!!!!77777777777777777777!!~~~!!!!!~~^::::::::::::::::::::~!77???JJ???JJ?J?YYYYJJYYYY5YJ7JYYYJJ?77!!~~~~~!^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
			+"	^^^^^^^^^^::::::::::::::::::^!~~~!!!!!77777777777777777777777777777777777777777???77777777!!!!!!~~^:::::::::::::^^^~!!77?J?????YY??YY55Y5Y55YYJJ?7YYYJ?77!~~~~~~~~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
			+"	~^^^^^^:::::::::::::^^^^^^:^~!!!!7!!!777777777777??77777777777777??????????????????????????777!!!!!~~^::::^^^^^^^^^~!!7????777?Y???JJJJJJY55YYY??Y5P55YJJ777!!!!!!!:^^^^^^^^^^^^^^^^^^^^^^^^^^::^^^^^^^^\n"
			+"	~^^^^^^^^^^^^^^^^^^^^^^^^^^~!~~!!7777777777777777?77777777777???7777777777777777?777777?????7777777!7!~^:::^:::::::^!!??77!!!!?J!77??77J5PP555PGGBBBBBBBBGGGPPPJ!^^^^^:^:~~^^^^^^^^^^^^^^:::^^^^^^^^^^^^\n"
			+"	!!!!!~~~~^^^^^^^^^^^~~^^^^~!!~!!777??????77777?7????77777777??J?7777777777777777777777?77?77??7????????77!!~~~^^::~!7Y5YJY5J??YYJYYYPY5GGBGBBGPG#BB##########BGGJ^::^^::^!~^:::::^^^^^^^^^^^^^~~~~~~~~~~\n"
			+"	77777777777!!!!!!!!!!7!!!!777?7???J?JJ??????JJJ?JJ??J???????JYYYJ????JJJJJJ?JJJ?JJ??J??7J??JJJJJJJJJJJJJJJJJ??77!~?5PGBBBGGGGGGGBBBBBBBGGBBBG5PBBBBB#######BBBBBP!~~~~~!77!~~~~~~!!!!!!7777!7777777777??\n"
			+"	7777??777!77!!!!!!!!!!!!!!!!7777777777777777777777777777777777???777?777????????????JJJJJ?????????????JJJ???????JJY5GGGGPPPPPPGPGGGG5P55YP5G5P##################PYYYYYYYY55YYYYYYYYY5YYYYYYYJJYYYY5Y5YY5\n"
			+"	777777!!777777!!!!!!!777777777777777777777???????????JJJJ????????JJJJJJ??JJYYYJJJJJJJJYJJJJYYYYYJJJJJYYYYYYYYY5YJYY5PGPP55555Y5Y55YYJYJYY5YPG#################BBPPGPPPPPPPPPPPPGGGGGPGPPPGGPPPPPPPPGGGGG\n"
			+"	???JJJJJJJJJJJJJJJJJJJJJJJJYYYJYY555555YYYYYYYYYYY5YY5555555555555PP5555555555555Y55555PPP5555555555PPPGGPPPPGGY77JPGGGPPPPPP5P5PPPP5P5GPGPG#&&&&&&&&&####&#&##BBBGGBBGGGGGGBBBBBGGPGPPGGGGGGBGGGBBB#BBB\n"
			+"	P555PPPPPPPPPPPPPPPPPPPP555PP5555PPPPPPPPPPPPPPPPPPP55555PPPPPPPPPP5555555PPPPPPPPPP5555PPPPPGGGGGGGPGPGGGPPP5YJ77J5GGBBBBBGGBGGGGPP5Y5PGGB###########B#&&&##BBBBBBBBBGGBBGGGGGGPPPGGBB###BBBBBBBBBBBBBB\n"
			+"	PPPPPPPPPPPPGPPP5PGPPP55PPPPP55PPGGGGGGGGGGGGGGGGGGPPPGGPPP55555PPPPPPPPPPPPP5555555555PPGGPPPGPPPPP5YYYY5YYJJ?JYY5PPPPGGPPPPP5Y5YYJJYPPGGGGGGGGPPPP555PGBBGGBGGP5GGB########BBBBBB###&#&&&&############\n"
			+"	PPGGGGBBBBBBBGBGGGBBBGGGGGGPPPPPPPPPPPPPPGGPPPPPPPPPPPPP55555YYYYYYP5555P55555YYYJYYYY55Y5555555YYYYJJJJJY5555PP55P5P55P55P555YYYY55555555PGGGGGBBBBBGGGGGGGGGPPPPGGGBBBBB##B#####BBBGPGBBBBBBBBBB######\n"
			+"	BBBBBBBBBBBBBBBBBBBGBGGGGGPPPPPPGGGGGGGGBBBBBBBGGGGGPPPPPPPP55555PPP555YY5YYYYJJJJJ??Y5555555YYYYYYYYYYYYYYYYYY55Y5Y5555555YYYJY5Y555YYYY55555Y5PP555YPPGBBBBBBBBBBBBBBBBBBBBBBBBBGGGGBBBBBBBBB#########\n"
			+"	BBB#####BB##BBBBBBBGGGBGPGGGGBBBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGGPPP55P555PGP5YJYYYYYYY55P5555YYYY5555YYYYYY555PGGGPP55YYY55555555PP555555555Y55Y55PPGPGBBBBBBB#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB####BB#\n"
			+"	BBB########BBBBBBBBBBBBBGGGGGGGBBBBBBBBBBBBBBBBGGGBBBGGGGBBGGGGGGGGBBBBBBB##BBGGGGGGGGGGGGPPPPPPPPPGGGGBBBBBBBBBBBBBGGGGGGGGGGPPPPPPPPPPPPPPBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGBGBBBBGBBBGGBGGGBB#BB###B#####\n"
			+"	BBB#######BBBBBBBBBBGBBBBBBBBBBBB#####BBBBBBBBBBBGBBBBBBBBBBBBBBBBBBBBBBBBB#############BBBBBBBBBBBB#######BBBBBBBBGGGGGGGGPPPPPPPPGGGGGBBGGGGBBBBBBBGBBGGBGGGGGGGGPPPPPPGGGGGGGGGGGBBGGGB##&#####&&&&&&\n"
			+"	###BB##BBBBBBBBBBBBBBB####################BBBBBBB#BBB#####BBBBBBBBBBBBBBBB###################BB##BBBBBBBBBBBBBBBBBGGGGGGGGBGGGGGGBBBBGBBBBGGGGGGGGGPPPPPPGGGGGGGGGGGGGGGBBGGGGGGGGBBBGGB##&&&&####&&&&&&\n"
			+"	################BBBB####&&&&&&###############B###BBBB#####################&&#&&&##################BBBBBBBBBBBBGBBGGGGBBBBB############BBBBBBBBGGGGPP5PPPGBBBBBBBBGGGBBBBBBBGGGGGBGGGGGBBBB######&&&&&&&&\n"
			+"	##BB#################&&&#&&&&&&&&&###############BBB##############&##&&&&#&&&&&&###############BBBB###BBBBBBBBBBBBBBBBBBB#########################BBBBBBBBBBBBBBGBGBGBGGGGGGBGGGGPGBBB########&&&&&&&&&&\n"
			+"	###B##################&&#&&&&&&&&&&####################&##&#########&&&&&&&###########################BBBBBBBBBBBBBBBBBBBB#########&&&&&&&&&&&######BBBBBBBBBBGGGBGBGGGGGGGBB#######&&&&&&&&&&&&&&&&&&&&\n"
			+"	##################&&&&&#&&&&&&&&&&&&&&&&&&#####&&&&####&######################################BBBBBBBBBBBBBBBBBBBBB#######&&&&&&&&&&&&&&&&&&&&#############BBBBBBBBBBBB######&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"	#################&&&#&&#&&&&&&&&&&&&&&&&&&&&&##########&#################&&&##################BB###BBB#############&&&&&&&&&&&&&&&&&&&&&&&&&&######################B###&&&##&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
			+"el barquero te paga algo de dinero por el transporte y de paso te acerco a tu destino \n"
			+"todos los jugadores reciben 2 puntos");
			for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){					
						players[i].setscore(players[i].getscore()+2);
					
				}else{
					comprobant=true;
				}
			}
		break;
		
		case 8:
			message=("dentro de un bar os encontrasteis con un grupo de soldados quienes amablemente les ofrecen un trago por haberles salvado la vida en una batalla\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##BBBBBBBB#######B##BBB#####BBBBBB#####BBBBBBBBBBBBBB###BBBBBB###&BB&&&&&&#&&&&&&&&&&&&&&@@@@&@&&&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##########BBBBBBBBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##B#####B####BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#BB############&&&&&&&&&&&&&&@@&&@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&&&&&&&&&&&###################BBBBBBBBBBBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#BB#####BBBBBBBBBBBBB#############BBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBBBBBBBBBBBBBBBBBBBGGBBB#############&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##################B######BBBBBBBBBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBB#BBBBBBBBBBB###BGBBBB####B##B######BB###BBBB###B###############BBBBBBBGBBBGGGGGGPGGGGGGGGGGGGGGGGGBBBBBBBBBBBBBBBBBBBGGGBB#################&&&#B#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#######BBB#####################BBBBBBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBB#######BBBBBBBB#GY?P#BBB##BBBB######BBB##BBBBBB##B###B###BBBBB##BBBBBBBBBBBBBBBBYJJBBBBBBBBBBBBBB#BB######B##B###BBBBBBBBBGBB#################&GYJG&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#############B5JY#####################B#BBBBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#PJ?5#BBBBBBBBBB###BBBBB#BBBBBBBBBBB#BBBBBBB###B##BBBBBBBBBBBBBBBJJ?BBBBBBBBBBB############B###BBB#BBBBBBBBBBBBBBB###########&#&PJJP&##&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&###################BJJJ######################BBBBBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##B#B#BGGB#BBBBBBB########BBBBBBBBBBBBBBBB#BBBBBBB########BBBBBBBBBBBBPPPBBBBBBBBBBBBB#########BB######BB#############################GBB#################&&&&&&&&&&&&&&#############################GGG####################BBBBBBBBBBBBBBBBBBBBB\n"
			+"	BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#BBBBB###BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB##BBBBBBBBBBBBBBBB#####&&&####&##&##&&########&############&##&&&&&&&#################################BB##B#BBBB#BBBBB#BBBBBBBBBB##########B#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\n"
			+"	GGGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#BBBBBBBB#####B###############################&&&#####################&&&&&#&&&#&#&######&&&&###&############&&&&&#####################################################BBBBBBB##BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\n"
			+"	GGGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#BBB##############################&#################&##&&&&&&&&&&&&&&&&&&&&##&&&&####&##&##&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&&&&##################################################################BB#BBBBBBBBBBBGGGGGGBBBBBBBBBBBBBBBBBBBB\n"
			+"	GGGBBBBBBBBBBBBBBBBBBB#################B##############################GPBBG############&&##BG&&&&&&&&&&&&&&#B&#####B###########&#&&&#&&&&&&&&&#B#&&##&&&&&&&&&&&&#&&&&&&####################################B##################BBBBBBBBBBBBBBBBBBGBGGGBGGGGGGGBBBBBBBBBBBBBBBBBBBBB\n"
			+"	GGGBBBBBBBBBBBBBBBBBBB##############B#################################BPBGP#####BB########&5P&&&&&&&&&&&&&#GG######B######&B5####&&BPB&&##&&&&BYG&&##&&&&&&&&&&&&&&&&&&&&&&&###############################################B#BBGGGGGGGGBBBGGGGGGGGGBGGGGGGGBGBBBBBBBBBBBBBBBBBBBBBB\n"
			+"	GGBBBBBBBGGBBBBBBB#BB########B5P#######B#####B#########################B########P5BPG####&#PB&&&&&&&##&&&&#PPB#&##########&B5&###&&5?5&#5P#&&&P?5&&5Y#&&&&&&&&&########&#################################################BBBBBBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGBBBBBBBBBBBBBBBBBBBBBB\n"
			+"	PGBBBBBGPPGGGBBBBBBB##########BB######BB########B#####&######B########BB#########B#B#######G###&&&#&&#&&&&&###########B##&&##&#B###PYB&BY5#&&&GJB&&5Y#&&&#################################################################B#BBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBBBBBBBBBBBBBBBBB\n"
			+"	55PYYPB5YPGGBBBBBBB#BB########&&#####&#B#######BPB###PG######B####################&####BP#&###&&#BB&&#&&&#P55B&&##GPG#&&&#BPG#####&P5#&#5G#&&&PJ#&&G5&&&&&&#######&#############################################B##B######PYBBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGBBBBBBBBBBBBB\n"
			+"	5Y5JJG#BGBBB#####BBBGP##############BPYYP#######P###BJJB########B####B#B###########&&&#P?G&##&&&##&&&#&&&#BB##&&&#B####&&&###&&#&#B55&&#5B&&&#PY#&&GP&&&&&######&###################G####################################BBGBBPPGGGGGGGBBBBGGGGBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\n"
			+"	5PGGB####BGB#######BBG#######&#&####BPY5G#######B#&#####&#######PB##&#BGBBB#&######B&&&BGBGYPB####B#&&&&&&&B&&&&#G5P&&&&&##GP###&#BYG&&#YG####5Y##&GP####&##########################B##################BGB###B##############BBPPGGGBBBGGP5PB######BBBBBBBBBBBBBBBBBBBGGPPGBBBBBBBBB\n"
			+"	5G####BBBYJY########BBB#####GP#&&####&&&&&&##BGP5G#&&#&&&&&####&#&#PPPPGYJ?P&&##&&&#GGGBBBBGYYGBGP55B&B#BP5Y5PB&#GPB&&&&#P5JJYB&##BYB&&B?J5&##Y5&##GP##&###############################################5J?GBBB###BBBBBBBB###BBBBBBBGPY?77?5BBBBBBBBBBBBBGGGBBBBBBBBBBGPJ?YBBBBBBBBB\n"
			+"	B###BBBBGJYYBBBBBB####B####BGG###B##########BG5555G#G55##########&B5Y5P#5J?P##BBBB&GYYY5PYB&#YPB#&&#&&##&P?77?5&GGY?5P#&BPYJJ5B##BGJB&&BJYYB##JP&###G###############################################B#BJJ?GBB#######BBBBB##BBBGBBPY?!!!7?JY55555P5PPYJ77???J5GBBBBBBBBP?JYBBBBBBBBB\n"
			+"	####BBBBPGGGBBBB###BB################BYB######BGGGG#G5GGPG5GGYYJYBB5PGG##GBG#BGG5P#GJJJYJJB#&5PBB#BGBBBB###BBB#&GPJ?PPBGG5Y??5BB#P5J5B##BBGPB5??5B#G#############################################BBBBBBPGBBBGBBB###BBBBBBBBBBBBGJ7!!!!!~~~~~!7JJ???7~^!7!!^:^7YPPGGGGBBGGGBBBBBBBBB\n"
			+"	#####BGGGPP5YPPPGGGP5PPGGGGBGY5####BG5!5GB#####GGGB&GP#PGBPY5PP5YGBPPPP##B#G#BGPPP#P??JJ?JB&&5PBBBGPPGPPG#&&&&&&BY??JY#PPYJ7?5#GGBGP5P5YY5P5PYYJY555GP5G##########################################BBBBBB##BBBBBBBBBBBBBBBBBBBBBP?JJ7!~^^^^^^:^!!!!~^:^!~^~!^:^?77JY555PPGBBBBBBBBBB\n"
			+"	###BBBBG#G5PYJJ?777????JJYYGGYJB#&#BG5~YGB##BBBPP5G&G5#BGPGGBBGP5GB5YY5##B#B#GP5JP&P??JJ?JB&#YPBBBGPPG5PG######&BY?7?Y#PBG5JY5#BPGGGPYJYPBGPG5JJP5Y55JPGB#&&###########################BBBBBBBB#BBBBBBBBBBBGBBBBBBBBBBBBBBBBBBBBGBG5J7!!~^^^^^^^:::::::^~^~~:^?!:~!7???J5GBBBBBBBGB\n"
			+"	BB#BBBBGPG55YJ?7!~!!777?JJJP#BG######G7PB###GGGYJYG&#B##BGGBBGGPG##BGGBB##&&#BGPJP&GJJYYJY#&&GPBBBGGPPPPB##&&&&&GY?7J5#G#&&##&&#BPJ775G##5PB5?!!JP##PPBBBBBB###############B????777???777!7!?7J#BPGGPGBBBBBBGGBBGBBBBBBBBBBBBBBBB5JJ7!!7!!^^^:::..:::::^!:^!~!?::^^~!7?JYPBBBBBBBBB\n"
			+"	###BBBBBGPYJ?777777?JYJYJJJ5##########G########B###&##&#&&&&&####&&&&&&##&#&##BGG#&########&&#GBBBGGGGPGB###&&&&&##B##&&&##B#&#####GP##&#B##&BPP#####&&&BBGPPPGGPPBBBBBB###G!!~~!??J7J~~~!~7?~5#BGBBGGBGBBBBGBBBBBBBBBBB#BB#BBBB#5^?PP?~!7^^:::::::::..^~::^!?^::^^~!7??JYGBBBBBBBB\n"
			+"	###BB###G5J?777?JJJYP5JJYPPG##########&########&&&&###&&&&&&&&&&&&&&&&#&&&&&&&&&&&####&#B#####GPBGPPGGPGB###&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&#BBGGGG5JYYYYYPPBBB5!!???!JYJ7J??5JY5JYB#BBBBBBGGGBBBBBBBBBBBBGPY5B##BB#G!^!!7J?7!^^::::::::::.:^^:^!^::^~~~!77??J5BBBBBBGB\n"
			+"	####BBPY?77?77!77?JYY7!!JGB################################&############&&&&GGGB#GBB#BGBB#B###GPBGPPGG5PB###&&&#&&&&&&&&&&&&&&&&&&&##&&#B##BB#GB#B#&#&#####BGGGJ7??Y55G####Y!!?J!!?!???Y?!777777JB#BBBGGGBBBBBBBBGPY?!!!!JG#B###J^^^^^?J7!~^^:::::::::::^~~~:::^~~~!!!77??J5BGGGGGG\n"
			+"	B##BGPJ??77??7!!!7?JJ?7!?G##################&&#&#######&##############B&##&#~~YGY77?5?:J55GB#BGGBBBBBGGPGBB##GB&&&&&&&&###################BGPG55PGGBB#GG#&&G55555PPGBBB#BBGY!!!!!5P5PPPGPPYJPGGGPB##BBBBGGBBBBGY7~^^^~!!7JPBB##B7^:^^:!?!~~^^:::::::::::::::::^^~~!~~!!!!7?JGGGGGGG\n"
			+"	GBBGP5JJ???????77?JYJJJJJG####&&########BP##B##G###B########B###B##&&#G&&#&B~YG?!!!757^JJJPGGGGBBBBGPPBB5YPBP^Y#&&&&&&#&&&#####&&&###########BGGP5GBG&GPGBGPPP5B&#######G5YJ!!!!!B#5Y5B###5!G####BJB#BBBBBBGY7~:::^^^~~!7?YPBB#P~:^~~^~?!~^~^::^^^^^^^:::^^^^^^^^~~~^~~~7Y5PGGBGGGG\n"
			+"	GGG5YYJJJ???????JYYJJ5PGB######&##G##YYBB5B#YB#B#PBG####BPB#B#PBG#####G&&&&#PG?!!!!!5Y!P#BGB#BBB#BGGPPPG#&##G~5#&&&&&###########B######B#B####BBBPB#G&P55P5Y5P5#&######BB5JJ!!!!7B#B5?PBBB?7JBBG#B?J###BG5?!^^^^^::^^^~~!7?JGBGY7~^?Y77?7~^^~^^^~~^~~~^^^^^^::^^!7?JYYY5PGGGGGGGGGG\n"
			+"	GPPYJJ??JJJ?????J??J5PPB#######&PYJJGJJYJJYG?YJYYJP?G5J##########&###B5&&#PBG7!!~!~?5?~5BB#####&####BBB#BGBBG!P#&&&&&&&#BPB&##&PYG&####JB5####BPB5BGPB5PY5Y5Y5YPB#####GJ5YYY!!!!7B#BG55YPGJ?7YJY##?!G#GJ7!~~!~~~~7?7!77!!!!7J7!~!!^JYJ!77!~..:^^^~:~!!~~~~!7JY5PGGGGGGPPPPPPGPPGPPG\n"
			+"	JJJJ?????YYJJJ??77??JP#########&G5J7PJYGJ??P?P?7Y7Y!P?!###&&&&&#&&&&&BG&&BYG7!!!~~!?P~^YBBB##########BBG?!~??!J5PB#&&&&BJ?B&P5&J^?&#B#P~P?B#PB5?P?#PJYPPYP55P5P5G##BPPP5YJG5!!!!?###BB#BGBGPP5?JPP?!!J5J??7777?YPPYJJYJ?7~~!!~!~^~^JYY7?7^~^:.^^^~:~!!!?YPGGBGGGGGGPPPPPGPPGPGGGGGG\n"
			+"	BBG5Y?777?Y5555P55YY5B##########Y?Y?PJJJ7??P?5?7P75?577#&&#&&&&&##&&&&&&&BGP^!!!!!!PG?!5G####BGYYGGBBBB!  ..::^^^:^5#&&BPYB&PP#P5B&#B#PYB5#B5GYJ5J##GJYGBGP55GGPG##BBGPY??5Y!!!!?####BBBGGBB5Y?~. :~!!PBGP5Y?~!?JPGYJJ!~~^~~!^!!~~:~!5??!^^^~::^:~:~7YPGGGGGGGGGGGGGGGGPGGPPGGGPGPP\n"
			+"	#####BPY?7?JJY55#&#############&G5P5GPPP5P5G5GPPB5BYY??#&&&##BGPPPPPGBB#&##5^!!~~~!5?: !JY5PPPPP5?7??Y5~.~:....^7! ?P##&&&&###&&&&&&&##&#######&#&&PP#PPPPYPP55P55PPP5P55PBY!!!!?#####B#BGY7:..... .~!!5BBPYY~~7!!77!~^^^^~!!!7?7??55P55Y7!~~^~^^^^?GBBGGGGGBBGGGGGGGGGGGGGGGPPPPPP\n"
			+"	########BPYJYJJYB###########&##&#######&#########&#555G&&##BP5YJ??J5PJ!JPB#Y!!!~~~7PJ^.....::~?Y7.:~^^?^7~  ..  .J~~5JG#&&##GP##&&B5P#####GBP&&&##GGG&#GGP5Y5JJJ555YYJ5PB&&5!!!!J####BPY!:...:.......^!!JYJJY!^!!~::^^:^^~~!7??JYPGB#BBBB5?7!!7!7JYGBGGGBBGGGGGGGGPGGGGGGGGPGGGPGPP\n"
			+"	############G5JY5G#############&############&&##&&&&&&&&&##G5JJYYYY55~ ^7JGBBBGGGGB##BG5Y!77J55J5Y!!775^77......:?~!P#B#B#&##B#BB&#PG##G####B##B&#5B####GG55P5JJYYYY555####Y!!!!J##GJ^:. ...^:....... ^!~YJ?Y!^!!!!!~^:^^~!77??JYGGG#GGGBBGP5YJPBBBBBBBBBGGGGGGGGGGGGGPPPPPPPPPGGPP\n"
			+"	############&#BP5YG##############&&&#&&&&&&&&&&&&&&&&&#&&&BP55PPGBGG5!:~?YG5GGGBB#####BGP?J7GPJ.JPGBGB#!.!?!^^^!7^ JB&&&##&&&&&#B&&&&&&G##&&&&#G##&##&#&BY???YY!!77?5PB&###Y!77!!!~:...:.........:^....^:?PJJ!^777?!!!!~~!!77??J5GY5GGBGBBBB#BBBBBBBBGGGGGGGGGGGPGGPPGP!JPPPPP55PPP\n"
			+"	###############&#GP#########&###################&&#&&&#BGBBP55PGGGYJY?~JGB#?77!~!775B55G#&&&#PJ.?PP##&&PJ7!!!^!!!!7B&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@&###BG5???JPPG&#&&Y!~^:.....:::......:^^^:......755J55YJ7!!!777!!777?J5GYYPGGBBBBBBBBBBBBBGGGGPGGGGGGGGGGGPP5PPPPPPPPP55PP\n"
			+"	#################&########&##&&&&&##&####&####&&&&##&&&BGPBG555JPJ:7~!~7PGBBBBGGPPPPB5YP&&&&&#5:YP###&&&##BP?~PGP&&&&&&&&&&&&&&&&&&&&&&&&&&##&###&&G#&####&&&BPPGGY5Y5G&&GY^.....::::.....::^^::.. ......:5#55P5YYYYY?777777?JPB55PBBBBBBBBBBBBBBBBGGGGGGGGGGGGGGGGGGP5PPPPPPP5555P\n"
			+"	########################&&##&&&&##&&&###&#####&&&&BB#&&&&&BYJJ!~J:~J55J!PBBBBG7&##&&&GG#&&&#GPY~JPPPB&&&&#BB5?PG#&&&&&&&#B&##&&&&&&GP&&&#&&&&&#B###G#&B5GGGGG#&&#GBGP5YJ~:.....:::.....:^~~:........      :Y#PYPP5YJ?!!7??J?YPGP5GBBBBBBBBBBBBBBGGGGGGGGGGGGGGGPPPPPPP55PPPPP5555Y5\n"
			+"	###########&###########&&&&&&&&&&&&&&&#&&##&&&#BGGP55Y5PBBJ????Y7.?Y~~?Y5B#GBG?###&&&55B#GPP5Y?7?J5PPG####G#7^P#&&&&&&&&##&BB&&#5B&Y?&&#YB&&&&&#&?Y!G&&?5&#BP#&&#PBP?~:............:^~:^^:....:.....     . .?##PYJJ?!!7??Y5PGP5GBBBBGBBBBBBBBBGGGGGGGGGGGBGGGGGPPPPP5PP55555555P555\n"
			+"	#########&###########&##B#&&#&&&&&&&&&&&&&&#BP5Y?????7??YYYYY5GP^^P?:.YPY#&#&##&BPPGG5PP555YJJJJYJ7?G55PB#BB!^YB&&&&&&&&##&&##&#JB&PP&&#!G&&&&#7B!J7B@@YG@@@&BBBPY?!~^..::.......:^^^^:....:.:.......... ..  !B#G5JJ??JYPBBGGGBBBBGBBBBBBBBBGGBBBGGGGGGGBBBGPPPP55PPPP55555PP555555\n"
			+"	########&&&###&&&&&#&&#GJYG#&&&&&&&&&&&&#BPYJJJ????J????J5B#BBBY:?P~.:J55B##BGPG5YYY5Y5Y5P5JJ????7!!7JYYG##G~~Y5&&&&&&&&&&&&###G?G#J5&#G!G###BG^PYP5#&@P#@@@@&PJ7!77!!~:.::....::......:::.:.......:::... ... :5##BBBBB##BBBBBBBGGGBBBBBBBBGGBBBGGGGGGGBBGPP5PPPPPPPPP555PPPPPPPP55\n"
			+"	&#####&&#GGB&###&&&##&&&BYYG&&&&&#GPP555Y????????77???7??5#BGGG7:55!.:J55PPGGPY5PY7^^?JYJ?JJ7J7^^~.:~Y7??G&B!?P?&&&&&&&&&&&&&&5J^7BJ55YYJG?J?YY~Y!P?Y&@P#@@&PJ7777777!!~:.::........::.........:::...:...  ... .?##BBBBBBBBBBBGBGGBBBBBBGGGGBBGGGGGGGGBBPPP5PPPPPPPPPPPJY555PPP5555\n"
			+"	&#&&#&&&#5JY&##########&&&#&#&&&&#J7777?777???????7?J?JY5G#BG55^.P5~.:?57J!J!Y5?~!?. :7!J7~?77J^:7:::77?JB&#JJP?#&&&&&&&&&&&&&GY~~P?5P5JJG?P?5GJP?G7J&@P#@@&GJ777?7777!!!^..::.............::::::::....   .  .   ~G#BBBBBBGBGGBBBBBBBBBBBGGBBGGGGGGGGGBP5PGGPPPPPPP555Y77?5555PP555\n"
			+"	#&####&&BYY5####&########&##&&&#&&577777777?J????JJJJJY5###G5PJ.:B5~.:?P7J?Y7YP??JJ:.!YJPJYPJPJ~!7~~~J??5&&&PYP?#&@&&&&&&&&&&&Y??~5?YJYY?P?GJ5G?PJP?J&&G&&&&BB5?7?77777777!:.::.........::::::::.......   ... .   :5#BBBBBBGGGGBBBBBBBBBBGBBGGGGGGGGBBP5PPGGPPP555P555Y77?55555PPPP\n"
			+"	#&##&#&#####&&&&&&&&&###&&##&##&&&B?7??JJYY55PPPGBB#B#####BPPP5?P#Y~.^YPPGBGB#BGGY7!?PGGPGBGG5??!!7??Y5YB&&&BGGJ#&&&&&&&&&&&&&G5Y!55GGPGGBP#GB#5#557J#BB#B#BGBBP?77777!!7777~..:.......::^::::...........  ..       ?BBBBBBGGGGBBBBBBBBBGBGGGGGGGGGGBG5PGGPPPPPPP5555PP55555P55555P\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&#&&&&&&&&&&&&GPGB##&&&&&&&&&&&&#########5?JJPY!J5GPJPG&&&&BB####&#B5YJJ?7?JYJYY5B#&&&&##BJ#&&@&&&&&&&&&&&#Y~?#@@@&@@@@@@@@@GPJB#BB#BBBBG#&#Y77777!!!777!:.:........:.............              ~G#BBGGGGBGBBBBBBBGGGGGGGGGGGBBGPPGGPPPPPPPPPPP5PPPP55555P555P\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&&&&&&&&&&&&J:::GG:5#G~!5#&&&&&&&&&&&&#BGPY7.^?5PP5#&&&&&&##BY#&&&&&&&&&&&&&&&#GGB&@&&@@@@@@@@@@&P@&&#&BBBBB#&&BY77777!~!!77!^.::..................~~:.             .Y#BBBGGGGGBBBBBGGGGGGGGGGGGBGP5PGGPPPPPPPPPPP5555555555P5555\n"
			+"	&&&&&&&&#############&&&&&&&&&&&&&&&&&#&&&&&&&&&&&&&&&&&&##&&&#P??Y7:?5575#&&&&&&&&&&&&&&&#BGP!75GB##&&&&&&&###Y&&&&&&###BB&&&&&&@@&@@@@@@@@@@@@@@&#@&&&&##########GJ7777!~!!!!7~:::....!~~~:.......^!!~:..             7BBBGBBGGGBBBBGGGGPGGGGGGBBG55PGGGGPPPPPPPP55555P55PP5PPP55\n"
			+"	###&&&BP5555555P5555PPPPPPPPPPGGGGGGGGGGGGGGGGBBBBBBBBBB##BBBBPYJ5G?^YG5YG##&&&&&&&&&&&&&&##GY^7PBPB&&&&##&#YGBY&&&&&&###B#&&&&@&&B&@@@@@@@@@@@GP@&@&@@&@@@@@@@@@@@@&P?777!~!!!!7!^.....~7!!!~:.....^!^:...              ^P#GBBGGGBBBGGGGGGGGGGGGBGPP5PGGGGGGGGPPP5PP555555PPPPPPPP\n"
			+"	&#&&BP55Y555555555555555Y55Y5YYYYYYJYYJYJJJJJJJJJJJJJJJJYYYJYYJYJY#?:GBYYPGB###########&&&#&BY:!P#YB&&&&#&&BJG#5&&&&&&###&&&&&&@@&#&@@@@@&P#&@@PP@@@&#&&@@@@@@@&&&&&&@#Y777!!!!!!!7~:....^77~^~~:....:..       .          .JBBGGGBBBGGGGGGGGGGGGBGPPPPPGGGGGGGGGPP55555555555P5PPPP\n"
			+"	&&&&GYYJJYJJJJJJYYYJYJJJYYJJ????J???JJJJJJ?JJ??J????JJJ?JYJJ????7JG!:5P?J5BB############&##&#5:!G#5B&&###&&G7B#B&&&&&&&&@@@@&&@@@@@@@@@@@@Y5@@&&&@&&#5&@#P@&&#&&&&&&&&@&GJ777!!~!!!!!^.....^!!~!!!:....  .......          :!G#BBBBBGGGGGGGGGGGGGBPPPGGGGGGPPPPPPPPP5555555555PP5PPP\n"
			+"	&&&&######BBBBBBBBGGGGGPPG55555YY5YYJJJJJJJJJJ??J????????YYJJJ???JG~:P5???PB##&&#######&&##&#5:7G&PB&&###&&G!BGG#&@&@&PPGPPPPB&###&&&&@@@@&&&&&&&&##&&&BPP&&&&&#&##&&&&&@&G?77!!!!!!!!!:.....~!!~~!^...........       :~?PB####BBBGGGGGGGGGGGGGGGPGPGGGPPPPPPPPPPPPP55555555PPPPPPP\n"
			+"	&#&##&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&#&##########BBBBBBGB#######BBGBY?GPPPGB#&&&&&&&&&&&&&&&#BY:!P#GB&&&&##GY~5YPBB&&@B!YPPPPJPB777?JYPB@@@@@&&@@B#&#&&G!5B&&&#P&@@@&@&&&&&@#57!!!!!!!?Y!~^....^!~^^~~:........    .~?5B#&&######BGGGGGGGGGGGGGGBPPPPPGPPPPPPPPPPGPPGPPP55555555PPPP\n"
			+"	&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#########&&&&&&&&&&&&&&&&&#G7:7P#GB&&&&&#GJ!5P5#&&&@&5B&&&&#GPYYY55G#&&@@@@&&&@B5BBBBGYGBY#BGG###&@@&&&&&&&&BY7!!!~~JJ?7!~^:. .!~^^~~~:.... .:~?5B#&&##########BGBGGGGGGGGGGGGGPPPPPGPPPPPPPP55PPPPPPPP55555PPPPPP\n"
			+"	&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#GP#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#G7.7G#GG&&&#GPP!^G#JP&&&&&#&&&&#B5##&&&&&&&&@@@@&@&P#GPBB##B#7!BB#&#G5#@&&&#PG#&&&&G?!!!!!?Y7~~~~^^^~~!~~~~~~^:.^?GB###############BBBBGGGG5Y5GGGGGGPPPPGPPGGPPPPPPPPPP55PPPP5555PPPPPP\n"
			+"	&&&&&&&&&#G&&&&&&&&&&&&&&&&&&&&&&&&&&&&&BYJP&&&&&&&&&&&&&&&&&&&##&########&&&&&&&&&&#B&&&&&#B?:YB&B#&&#&5Y7~!G&##&@@@&@&&&&&&&&&@@@@&&&&&@&&@@&G&@P?YPG557?PGGG5?Y&&&&&GY?P###&&#57!!!!7YY?!~^^^^^!~~^^^^~7J5PPPGGGB####BBBBBBBBBBBBGGGJ??GGGGBGPPPPGGPPPPPPPPPPPPPPPPPPPPP555PGGPP\n"
			+"	&&&&&#&&&#B&&&&&&&&&&&&&&&&&&#&&#&&&&&&&GYYG&&&&&&&&&&&&&&&&&&&&#BGGB##BGPB#&&&&&&&G5P55BGP5Y!:JPGPPB&&#55Y?7B#&&&&@@@@&&&&&&&&&&&@&&@&##@@&@&#P&@&PJYYYYYYYYYYY5#&&&&&GYYPBBBBBB#BJ!!!!!J?777!~^~~^^^^~?5PGGGGGGGGBBB#BBBBBBPGBBBBBBBGJJJGGGGBGPPPPGGGPGGGPPPPP5PPPPPPPPPP555PPPPP\n"
			+"	&&&&&&&&&#&#&&&&##&#####&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#G5PBBBG55G#&&&&&&&BB#BG##BPY!^?YPGB#&&&&#G!7G##&&&&&@@@&&&&&&&&&&@&&&@&@@@@&&&P#&&&G5YYJYYJYY5G&@@@&&&&##&&&&######P?!!!!YY77777~~~^~JPGGGGGGGGGGGGB#BBBBBB#PBBBBBBBBBBBGGBGGBBGPPPGGGPGGGGGGPPPPPPP5PP55P5555PPPP\n"
			+"	&&&&&&&&&&&#&&&&#####&###&&#&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#BPGB##BBB##&&&&&&&&#&&&&B#B5^:YG#YG&&&&&&G~?##&&&@@&&&&&&&&&&&&&@&&&&@@@@@@&#PYB##&@BPP5555PGB&@&@@&&&&&&&&&&&&&&&&&#PPGPPPGP7!!!!!?PBGPPPGGGGGGPPPPGGGGGBBBBBBBBBBBBBBBGGBBGBBGGPPGBBGPGGGGBGGGGPPPPPGP5PP555PPPG\n"
			+"	&&&&&&&&&&&&&&&&&##&&&&&&&&&&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&#####BBBB###BGB###&&&&#&&######B5::YG#PP&&&&&#P!5####&&&@#G#&&&&&&&&&&&&&&&&&&B##PY5G5BBG&G5GPPGB&@@@@&&&&&&&&&&&&&&&&&&&&&&&&####P?!!!5BGGGPGGGGGBGPPGGGBGGGPPGGGBBBGGGBBBBBBBBBGBBGGPPGGBBGPPGGPPPPGGGPPPPPPPPP55PPGP\n"
			+"	&&&&####&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&########BBGGGBBGGGGBB##########BB&##P!^5GBB#&&&&#GJ^?5PPB&&&@#GB&&&&&&&&&&@@@@&&@&&&&&GP#####&B5BGB&@@@@@@&&&&&&&&&&&#&&&&&&&&####&&&&&GJ75#BGGGGGBGGBGGGGGGGGPPPGBBBGGGBBBGPPGGGBBBBBBBBPPPPPPGBGPPGPP5PPPP5PPPPP5PPP5PGGP\n"
			+"	&&&&####&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##&&&&&##GPJ?YYYJJ?JYPGBBBB########BGY~^5BBBB#&&&&B?.~YGPB&@&@BGB@@&&&&&@@@@@@@@@@@@@@&#G&&&&&&G5#PPB#B&&&@&##B##GG&#GG&&&&&&&&###B&&&&&&#PGB#BGGGGBBBBGGGGGGG55GBBBGGBBBBBGGGGBBBBBBBBBGGGPGGGGPPGPPPGGPPPP55PPPPP55PP5PPGP\n"
			+"	&&&#####&&&&&&&&&&&#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##&&#######BB#####B##&&&&&&&&#&&###BGJ^!YGBBPB&&&#G!.!YPP#&&&&BPB&&########&&&&&&&&&&&&BPB&&@&&G5#5PB##&&&&&&##&&#B&GP#&@&&&&&&&##&&&&#5G#&BB#BGGGBBBGGGGGBBG5GBBBBGGB#BBGGGGGB##BGGBBGGGGGBBGGGGGGP5PPGGGPPPPP55PPPPP55PPGG\n"
			+"	&&######&##&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&#J?????????7!7777!7!!!!!!!!!!!~~!J&&#G^7G####&&&&BP7.7PBG#&&&#G5G#BGGBBBG5PGB#B#&&&&&&#P5BBBB##5P#Y5&&&&&&&@@&&&&&&&5G&@@@&&&&&&#&&&&&#?Y#&#BBBBBBBBBBGGBBGG55BGBBGGBBBBBGGGGGGGGGGBBGGGGPGPPGGPGBGGP5PPGGGPPPPPPPPPPP55PGGG\n"
			+"	#&##&###&#BBBGGGGGGGGGGPPPGGGGGGGGBBBB##########&&&&&&&P?????777!!7!!!!~~~^:^^~~~!!~~!?PB##B5^7PBBBGGG#&#G!.!5BPJ#&&#GP#@&##&&&#GB#&&##&##&&&###&#####PG&Y5PGB&&&&&&&&&&&&#GB&&&&&&&&&&&&&&&&&PB&&&&###BBBBBBGGBGG57YGGBBPB#BBBGPGGGGPPPGBBGGPGGGGPPPGBPPGBGPPPGGGGPPPPPP555PPPPGGG\n"
			+"	&&&###&&&BGPPPP555P55YYYJJJJJJJJ???JJJJJJJJJJYYYYYYYY555???7777777!~~!!!~~^:~!!!77YPG####BG5?^75GBBBPPB#G57!J5GGG#&#GGB#P5BPB&&#BBB#&##&&&&&&#&#&&@@&&PGGJG&&&&&&&&&&&&@&&##&&#&&&&##&&&&&&&&&&&&##&G###BBBBBBBBGGJ!7GGBGGBGBBGPGGGBBGPPBBGGPGGGGGGPPGGGPGBBGPPPPGGPPPPPPPPPPPPPPGG\n"
			+"	BB#BJP#BBBG5YYYYYYYYYYYYYYYJJJJJJJJJJJJ??J????????????JYJ??77???J77?7~!!~^^!!!7777JP#&&#BG5J^ ^J5G####&&#P!!5GB5BBBBBBGBBGBPP#&#BBB#&#B&&&&&###&&&BB###B5YG###BB#&&&&&&&&&&###&&&&&&##&&####BBGG#5JB5GB#BGGGGPBGPP5!!5BBGPGGBGPGGGBBGGGPGBGBPGPGBBGPPPGGPPGGGGGPPGPGPPPPPPPPPPPPPGP\n"
			+"	YJJG?5YYJJY!?!77!?~J!7YYYYYYYJJJJJJJJJJ???JJ??????77??JYJYJJJJJJJ?PG5?J5Y777!!77777Y#&&#BG5J~.:?PB##&&&&#P?!?PBB###&&BB##&&&&&#B#BB&&B##PPB&#BB#&&PPYYBB5YGBBY??P&&&&&&&&&###########&&&##B#GJY?5Y?Y?Y?GJJY?5?Y??57J5PBBBGGGGGGGGGBBGGGPPBGBGGGPPBBGPPPGP5PGGGGGPPPGGGPJJ5PP55PPGPP\n"
			+"	5Y?P?5P5J?Y!J!??~7~J!!YYYYYYJJJJJJJJJJJJJJJJJJ????J???J5YY5555YYY5GBP5YPY?~!!7777775&&&#GP5J~.^?5PB###&#G5?^!5GBB#&&#GGB#&&&&#B#&###BB#BYJY#BGB#######PGP5###BB#######&#####################GYY?YY?PYY?5JY5?G?Y?JP7PBBBGBBBGGGGGGBBBGBBGPGGBGGGPPGGGGGGPPPPGGGPGGPPGGG5?7JPP5PPPGPP\n"
			+"	JY?G?Y?YJ?Y!J!?7~7~7!!YYYYYYYJJYYJYYJJJJJJJJJJ?JB#&&5?Y5555555YYYYP#BBBB57~7??J?7775&&&#BPY7^.^JPGB###&&B5^.!5GPB##&#GPG#&&&#B#&&##GB##BYY5##B########BBGYB#######################B#########P7Y?Y5?Y?Y?5JJ5?G?YJJY7PBBBBBBBBGGGGGGBBGGGBGGPPPGGPPGGBGGGGGPPPGGGGGG5PGG5?JYPPPPPGPPG\n"
			+"	GBG#GBBGG55JYJJJ?J?7!7Y5YYYYYYYYYYYYYYYYYJJJJJJJ#&&&B?Y5Y55555PP5YP##BGBBJ?JJJJJJ?5B&&&##GY!:.^?5G####&&BJ^!5GGG#&#BGPJYB##B#&&&#BGB###########B##BBBBBBGYG#####################B###########BPGGGBGBGGGBGGBGBGBPYJ?G#BBBBBBBBGGGGBBBGGGBBBGGPGGGPPGBBGGGGGPPPGGGGGGPPGGPPPPPPPGPPGG\n"
			+"	&&&&&&&&#PP55YYYJJ?77?YJ?JJJJJJJJJJJJJYJYYYYYYYYG#&#GJ555555555P5JP#BBYBG?^!???J5G#&&&&#GGJ~..~JPB####&#PY7JP###&#BBBG5PBBB#&&&#BG#BBBB####BB#######BBBBG5B########################B########################B##GPPG###BBBBBBBBGGGGBBGGGGBBBBGPGGGPGGBGGGGGGGPGGGGGGPGGGGGPGPGGGPPGB\n"
			+"	&#&&##&&#PP5YYYJJJJJJY5JJJJJJJJJJJ?????JJ??JJJJJJYYGJ?5555Y5555YJJPGPJ??7???7JG###BBBGP555Y!^:~?YPGGGB#GPY7JGB#&#GPB&#B#B#&&&&BB###BB###############B###BPB###############B##BB####G###################BBBBBBB###########BBBBBBBBGGBBBGGGGBBBBGGPGGGBGGGGGBBGGGGGGGGGGGGGGGGGGPPGBB\n"
			+"	&&#&#&&&#PP55YYYJYYJJY5JJJJJJJJJJJJJJJ??JJJJJJJJJJ?55?PP5555P5YJY55BGP5Y?7PB####B#BBGGGGGGP?77?J5PGBBBBGBPJJGBB#&&&&&#GB&&&&#B#####B###&################GG##############BBBBBBBB###PB#######################B#BB####BBBB#####B##BBBBBBBGGGBBBBBBGGGGGGGGGGGBBGPPGGGGGGGGGGGGGGGBBBB\n"
			+"	&&#####&B5P5YYYYYYJYJY5JJJJJJJJJJJJJJJJJJJYJJJJJJJY5B5GGGGGGGGGPBGBBB#####&&&##&&######B#BY!~^!YPB##BBB#BGP5P##&&&##B#&####BG##BBBBB#&&#################BB#############BBGBGGGBB##GP##B#############################BBBBBBB########BBBBBBBBBBBBBBBGGGBGGGGGBBBGGGGGGGBBGBGGPGBBBBGG\n"
			+"	#####&&&BP555YYYYJYYJYPYYYYJJJYYYJJJJJYJJJJ??J5PGGGGPGP55555555P55P5PPPP5PPPPPPPPPPPPPPPPP5?~~?5PPGBBB##BBG55####B######GB#######B#&&################BBBBBB###########BBBGGGGGB###5P#BBB##############################BBBBBBBBBBB#B##BBBBBBBBBBBBBBBBBBBGGGGGGGBBGGBBBGGGGPGBBBBGGG\n"
			+"	#######&BP5555YYYJYYJYP5YYYJJJYYYJJJJJYJJJJYPBBGGPPGGPPP5PPPPPP55PPPPPPPPPP55PP555YYJJJYYJJJYJJ??J5GGBG5Y?!~!7777?7777????JJJJJJG&&#################BBBBBBBBBB########BBBGGGGGB##BYBB#BB#############################BBB#BBBBB#BBBBBBBBB######BBBBBBBBBBBBBGGGGGBBBGGGGGGGBBBBBGGGG\n"
			+"	########BGP555YYJYYYJYP5YYYJJJYYYJJJJJYJJPBBGGGGGGGGGGBBGGGGGGPPPGGGGGGPPP55PPPP5PP55YJYYYYYY??JPB##BBP5J?!!777777!!777777!7!!!^7#################BBBBBBBBBBBB#####BBBBBBGGGGGBBB5P#GBBB#########################BG##BBBBBBBBBBB#BBBBBBBBBBB########BBBBBBBBBBBBBBGGGGGGBBBBBGGGGGG\n"
			+"	########GPPP55YYJYJYYYP5YYYYJJYYYJJJYJJYBBGPGGGGGGGGGGGBBBBGGGPPGGGGGGG5PP5GPPPPPP555P5YYYYJJYGB#B######BGJ?5PPPPPPPPGGGPPPPPP5Y5####################BBBBBBBBBBBBBBBBBBBGGGGPGBBPYBGBBGB#########################P?B###B#BBBBBBBBBBBBBBB#BB#B##BBBB##BBB#BBBBBBBBBBBBBBBBBBGGGGPGGG\n"
			+"	########GPPP5555JJJYYYP55YYJJJJYYJJYYY5#BGGGGGGGGGGBGBBBBBBGGGGGGBBPGPGGPPPPPP5PP5P55555PPPGBBBBB##&#####G?!J5PPPP55PPGGGGGGGGGPB&################BBBBGGGBBBBBBBBBBBBBBBGGGGGBBGYGBPGGGB##########################BB###B#BBBBBBBBB#BBBB###BB###BBBBBBBBBBBBBBBBBBBBBBBBBGGGGGGGGGPP\n"
			+"	########GPPP555YJJYYY5P5YYYJJJYYYJYYYPBBPYYPGGGGGGGGGBBBBBBBGBGGGGBGGPPPPPGPPP5PP5PGGGGGGBBBB##&&&######PYJ77YGBB###&&&######&&&&####B#############BBBBBB#BBBBBBBBBBBBBPYJPGB#GY5B5PGGB##################################BBBBBBBB#####B5YP#BB##BBBBBBBBBBBBBBGGGGGGGGGGGGGGGGGGGGPP\n"
			+"	######&#GPP5555YJYY5Y5PYYYYJYYYYYYYY5BGG5?7YGGGGGGGGGBBGGGGGGGBGGBBGBGGPPPPPPPPPGGPPGGBBBB#&&&&&########YJ!?75BGB##&&&&###&##########################BBBB#BBBBBBBBBBBGG5?7YBBGJ5B5Y5GGB###########################BB###BBBBBBBBBBBBBBBBYJJB###BBBBBBBBBBBBBBBBGGGGGGGGGGGGGGGGGGGGG\n"	
			+" aunque las primeras rondas las gastaron los soldados despues de un rato las rondas seguian y seguian viniendo \n"
			+"todos los jugadores pierden 1 punto");
			for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){					
						players[i].setscore(players[i].getscore()-1);
					
				}else{
					comprobant=true;
				}
			}
		break;
		
		case 9:
			message=("en un antiguo alberge los dueños amablemente les ofrecen posada, comida y medicinas a los heridos\n"	
			+"	GBB#GBB#&&@@&#####&B#B#BBG@##&&&&&&&&&##&@@&&&&&##BBGPP55GGPJYGPPPPYYJ77??7777??PPPPG5P5PPGBYJJJ#Y??777JJ~^~^^!~^^7P5J?Y5PJ?Y??Y5JJYG&BPG##GG#B&#BBPB&BGGG5G5P5????JJ5P5YY5YJJJJYYGGYYYJ?J5JJ?JYYJJYY5Y?Y5JJ55YYJYYY55YY#@&@@@@&@@@@&@&&BBB5JP5YY5555GBG55JJYY55PP55PPPPPGGBBGGBBBB\n"	
			+"	GBB#GGB&####&B&####BBB&BPB##B&@@&&B#B&&&@@@&&@#@@&&&##GGGGPG7?5J?PYYPGPGP5YJ?77?PPPPPGGBBPPGP5PPGPGPYJYY7!!~~7^~^:!YJJJYJJ??JJYYJJYYG##PP#BPB#B&BBBGB&#GGP5PPP5JJ??JJJYYJJJYYJJJJJPBYYYJJ?JYJ?JJYJ?J5JJYJJJ?JJJ5Y5555Y5Y#&##&&@&&@&&##B#GGP5P5PP5555GG#G5YYYJYY55Y5PPP5GPGGPPGPPGGB\n"	
			+"	GBB#GGG&#&&&&P&&&&#&BB&BB###BGBBGBGBGBGB####&&B&@#&&#&GBGG5G?YGP5PPYYP5BPJP577J?5P55BGPPGBGB#&BYY5GYJYJ7~!~!^7^!^:~?YYJJ?YJ?JJYJYYYYB##PPBBPB&#&&BBGG&BGPG55PP5J??JJJJYJJJJ?JYYJYJ5BY!:~^^!!!77??JJ??YYJJYYJJJYJ?Y5YYJYJB#&#&#&&&@&#BGGGGGPYP555J555PGBGP5YYYYYY55555PPGPP#&##BGGGG\n"	
			+"	GGB#PGG&BB#&&#&&&&GGGB&B#BGGPPPPPGGBBB#&#BG###B#&&&###GGBPYPJYB5JJG!?PJPY7JY7?YJYJYYPG5GGP55G&#PP#BYJY7!7!!~~~~^~^~JYY5Y7JYJ??JYY5PBGB#GGBBPGBGBBBGGG#GGPP5P5P5JJJ?JJJJJ?????JJYJJYGY!:^^:^^:^^::^::^~7Y???JYJJJJJJ?YYJ?GB&&@#&##@#BBBGGGPPYY555J5Y55PBB555YY5Y55Y55PPGG55B@@&&@@@@\n"	
			+"	GB##GGG&##&#&#&#&&B5##BBGGGBBBBGGPG##@@@&&&#B#&@&&&#&#GBGPYGYJBYJYG7JGJPJ!?Y?J^7Y5YY55P5YPP5G&BYG&GJYYYJJJYY?7YJ?J?JYJ???JJJ5BB#&&&@GP#&BB##GG#BBBBBGBBBGPGGP55J??7?????7J???JJY??JP57^~^!^^~^^^^^^^^:^J?JJJJ?J?JYJJJJJ?GG&#&BBB#&BGGGPP5P5J?JYYY5YY5PBG5Y55JJY5YYYP5PGPPP#@@@@@@@@\n"	
			+"	GB##GGG&#&&B&#BBB&#GP&#####&###GPP5PGB#@&&@@&B&&&B###&BBPP5PJJBYJJB?JGJ5J7?Y?7^??P55YPPPY55PG##PG&5YYYJJJYYJJJ??7YJJ?JYJY?JJ5B##&##&BYPBPPGBP5BPPGB5PBGGGB5G#PPGPPPPYYYY5Y555J??JJJG57^^~!!~!!!~~^^::::?JJJJJ7?JJ???JJJ?PPB##BBB#&BGPPPG555YJY55JYJY55BB5YYYYYYYYY555PG5PPB@@@@@@@@\n"	
			+"	GB##GGB&#&B&&#BPG&&&&&&&&&&###BPGG5YYPGBB&&&#B#&@B##GGG#P5PPY?BJ?JB?JGY5Y77P?YY7JG5P5Y5J5GB###&BB5??Y5JJJYYY?7?J?J??J?7JJYJJYP#BB#B#GY5GP5GP55PGPGG5PGPYPG5PPP5GGPGGPG#PPB#PGYYJ??JPP?^:^~^^^^~~~:^^:^:???J??JJJ?????JJ?YP##B##B##GGGP555P5YJJJYJYYY5PBBP5YYYYJY555P555PPPP@@@@@@&@\n"	
			+"	BB##BBB&&&&&&#&#&&BGB#&&&#&#BB##BPP55PGGGB#&##&&&&&#BGPPBGPGY?BJ??#?JGJ5J7!P7YP7Y555PBB###&#BB&G5PYYJYYJYJ?JJJJJ?J????JJ??JJY5B&B#B#GY5GPPPPPYPG5PBPPBP5PGJY5GY5PYPG55GPPBGYPY???!!5GY~.::::::::^^:::::7JJJ?JYJJJJJJJJJJYPBB#BBBBBGPPP5P5YPY?JYYJYYYY5GG55YYYYYJ555P55PPPYP&&&&@&##\n"	
			+"	BB##B##&&&@&&####&BPPP&#GGB####B#B5GP5PBGGPG####B#&&#G5GGGPPY7PJ??#?JPY5J77G!?Y75P55YB&&#BPP#&#PYJYJYPJ?JJYYY?YJ7??JJ????JJJJYB&BBB#BY5GP5PGPY5BJ5BGPBGYPGJY5PY55Y5G55BP5GB5PY???7?5BY!^~^^^^::::::::::!???J?7JJYJ?JJ??7?5GBB#BBBGPPPPPP5Y5Y?YYYYJJYYYPB5J??YYJ5YYY5555555G@&&&@#BB\n"	
			+"	BB#&#&&&&#&&&####&BPPP#BPPG&BGBBGGBGP55GPGB#&BB#&#BB#BPGGG55Y7PJ??#7?5Y5J??57JY?5PPP5B##GYBGBB&5???7??J?JJ?J?J?JJ?????J?J??JY5B&#B##BYYPG55GGPYGYPGG5BGY5BJY5PY55YJG55GP5GBYPY??77?5B5J??77~!!7!~~~^~~~7??J??J??7?77?????5BBG&BBGGPPP55PPY5JJYYYJYYYY5PBP?^~~~!75YY5555Y55P&##&@BPB\n"	
			+"	##&&&&&@&##&&#&#####B###BGB&BPBPBBBBG555YYPGGG#&@BBBBBG5PP5JJ75Y77#7?Y55Y??BJ?Y?YPPP5P&PGGBB5G&5Y7^~^~?777???7????77???JYJ???JB##BB&PYJPG55GPPYBJ5GPYGGYPBJJ5PY55JYBPPGGPPB5PY7!!!7YB5J77?^:::^^^^^:~777!?77?7???7777?7?JPB###PGGPP5555Y55YJJJ?JJY5YY5PBG?^~~~~!Y5555YYYY5P&##&&BGG\n"	
			+"	&&&&&&&@#BB#&#@&&&&&@@&&&&&&&&####BB#BP5P5YJJY5PGP5Y#BB5G5J??!55?7B??55YY?!B77J?Y5YJJY&G55GJPG&PB7:^:^?J?777??77775?77JJ?7??JYG##B##B5JPGY5G55PBY5GG5GG5PBJJ5PYY5YYB5PPP5PBP5YJ7!!?JGPY??J.^^^:^^^^::^7!!777?77??7!?PGGBB#B&&#5PBGGYGG5G5G5YYJ?JYYYYY55BBY!~~~~~YJYYY5YYYYYB&&&#GPP\n"	
			+"	#&&@&&&@&&&&&B&&&&&&&@@#&&&&#&&@&&##&##BBGGP5Y5YYJ55#PGG5J?JJ?Y555BYY5PY5?!B77J7JJYJ?J#BYYJY5P&YB?^^^^!??77??????5BPJ??7?7JJ?JB#BB#BB5?PGY5G55PBY5GBPGB5PBJYY555PYYB55PG5GG5PP7!7!!?GPJ?7?:^:^^:^^^::^!!!!7777?7!7?775#BPGB###5PBPPPBPGGGBPBPGPGGPGPBPG#BY!~~~^^Y5555YY5YJYG##&#GPP\n"	
			+"	BGGGG##&#&&@&#&&&&#BGP&B##&&###&&&&&&##BBB#GGP5555YYPGBPGG5PPPJJ5P#5PP5?5?~B7!J?JYY5JP#GY?77JP&YG?^^^^!?7?7?77??7YGGBBGY7!J?JYB#BB&BB5?PG55B555B55PBPPG55BJJJP5JP5YB5YPG5PB5Y57!!~~755J77J:::::::::::^?7!!!!!!!77!7?7YBBGPG#B#55P5P555PGYPP#PPPBBBB##B#BBYJYJ??JYYYYYYYY555B#&&&#BB\n"	
			+"	&#&#BBGBP5P##&#####B##B##&&&&&&&&&&&@&##&&BGGPGGBGP5J55Y5GPPGGP5YYBYYYP?Y?~G?~JJJ555JYY5Y?7?YP&P5BBG5?!~~7!!7!~77JPGPPJ?777??JG#BB#BBP?PG55G555BPPPGY5G55GJJYPPYP5YG5Y5GYPG5J57!7!~!55J7~?7^~~^^::::.^7!7!77!7!7!7?77YBBPGBBG#5YPYGPPP5GJ5PG5PGGBG#BGGBGG5J??JJJJJJJYY?5G&##&&&&#&#\n"	
			+"	@@@@@@@&&&&#&&########B#B####&&##&&#&&&@@&GPP5P555PPGGPPPPP5YPGBGP#G5PPJ5Y7PY!?5??JJJJ77?77?JY#5JJP#PJ!!7?!!7?JJJ?YY77!777!JJYG&B###BG?5G55B5Y5G5PGG55G5PP5JY5PYP5JG5Y5GY5G5J5!~!^~755?7!YPJ??7!!!~~~!!~!!7!~!777!777YBBGGBBB#Y5PYPP5P5GYPGBPPGGBGBBBBBGBPYJJ??JJJYJYYJYP&#B#&#&BB#\n"	
			+"	@@@&@@@@@@&@@&&&#####BB&###B####&#&&&@&&@&BGGPPJYJJJJJ?JYY?5BPPGG5B5?JGJ5PPBBY!YY?555YJY7???7!JBJ75G5~?7YGPJJ?77?77~!!!!!!!??YG##B##BP?YP55BP5PBY5GGPPGPPP5YY55JG5YG5YPGY5G5Y5!~!^^!5PJ!^?JPGP?!!!77??!~!~!!7!!~!7!7!YBGGBBBG#55P5PP5P5B5PPGP5PGBGBGBBBB#PJ?????J?JJJJYY5####&&&BP#\n"	
			+"	@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&@@@@@@@@&&&&#BGP5555JJG#B#BBB#P5PGJGBPBGP~?PY?7?P5GGGPYYYY5BGGJ?!?!7?77Y!77!~!777!~7!7777PB#B#B#PJ5GYYB5YYBY5PGY5BG5PP5YPPYG55GP55GJ5B5YG!~~^^!J5?~!!JG7?~!!!~^!!~!7!77777!!!!7!JBBBBBPJ7?YG5GP5P5BP5PGP5PGGPGGGBBGBPJJ?????JJJJYJYY#&#&&###P#\n"	
			+"	&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&@@@@@@&@@&@@&&##BGGGBBGGGGPBBPBBBPPB#G##BPGPP555YYY555555YYYYYJJY555YJJY?YJ?JJJYY?7?7??7?5B##BBB77????J???YJ?JY?J5YJY5?JY5YP5YP555G?JPPYP!7??7?5P5J7777~~~!~~^~~!!~!~!!!!!!!!!!!JGBBB5P5YJJP5P55P5GY5GGP5GGBPGGGBBB#GJJ???JJJJJJJYY5#&&#&###GB\n"	
			+"	&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&#####BBBBBGGBGBPGP55PPGPPB@@@@@&###B#BPG#BBBGGPPGPPP55P5555555YY55YY5JJYJ?7YP5PGPJ5YY5YYJYYJJYJJJ?JJJJ?7???!!??7?7?J?77?7?7PGP?!?5Y7~~~!^~??^~~~~~~~~~!!!!!7~!!!!JGGGGPGG#PYP5P55P5PY5GGPYGGGPGGGBBB#GYJJ?7J?JJYJYYYY#BB##&&&B#\n"	
			+"	#&&&&&&&&&&&&&&&&&&&&@&&&&&&&&&&@@@@&&&&@@@@@@&@@@&@@@@@@@@@&&&&&######BBBBPPB&@&&@&&@@&#B##BBB############&&&&&&#&&#B#BBBBBBBBGPGPPG#&#BB##BBBGGGGGGGGPGPPPGGP5YP5YY555Y5555P5YYJJ?JPY7??J5YJ~~^~~~!!~~!~~~~!J57!7J7JGBBB#BB#P5P5GPPG5PY5GBGYPPGGGGGGBGBBY7!?7??JYYYYYPYG5YJB&#&BB\n"	
			+"	######&&&####################&&&##&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@&&&&&&&@&#######@@&&&&G5Y55YYYYYYYYYJJJ??JYYYYYYYY55YY5PPPPPGGGGGGGBBBGB#####&&&&&&&&&&&&&&&&&&&&#&&&###&##&B5P5YY55P5555555JJ?????77777!!7?JJ?7?7?JG#GB#B##5YGYPPYGYG55GPB5PPBPGGPBBBBBJ7!77J??JJJYYJ5G&#B#&&&GB\n"	
			+"	#####&&&@&&&&#######BBBBBBBBBBBBBBBBB#####################&&&&&&##BBGGGGGGGGGBGGG#&&&BB&BY?JY55Y????JJ?JJJ???7?????77777777777!7?7!7777!!7?777!!777????JJYYYYYY5555PPPGPGBBBBB##&&&&#####BBBB#B##BGGPPPPGGGPPP5YJJJJJJ5PY555PGJ?J???777?77J?J?JJYY5Y5G5PGBP?7YJ7?JJJYYP55GBBBB#&&B#\n"	
			+"	&&@@@@@@@@@@@@&&&&&&&&##BBBBBBBBGGGGGGGGGGGGGGGGGGGGGGGGGGBBBBBGGGP5555YYJY555555G&#5B#&&5Y??JJ555PP5YJJJJJJJ?JJ?JJ???????J???7777!!!!!!!!!!!~~~!!!!!!~~!!!!!!!!!!7!!!77!!!!7777777?????JJJ?JJYYY55PPPGGGGGBBBB###BBBGGGGGGGPPP&#BBBBGPP5555Y55JYYYYJJJYJJPYY55JJYP5Y55YPPP5&GB#&BG\n"	
			+"	&@@@@@@@@@@@@@@@@@@@@@@@&&&&@@@&##BBBB##BBGGPPGGGGPPPPPPPPPPPPPP5Y5Y55YJYYYYYJJJYB&GJG##&##BBGGBBB#&&##BBPGB###BP5Y5PG##GPYYJ5PGGGPY5Y55P5J77?7JYJ?7!!77J??!!!~7??!!~~!!7!!~~~!!!~~~~~~~~~~^~~~^^~~~!77!~!~!777????J?JJYY55PPPPGGGBBB##&&&&&&&&&&&&&&#&&###GPPPPP5GYY5?Y5#&@&BB&&#G\n"	
			+"	&@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&###&&@&#BGPG#&#BG55PGGG55Y5PP5JJ??JYYYB@P5GG#BY5YYYYJJYJJY5GBY555B5J5YGB5P5#GPPPG#GBB#B##B#BBB###BB####&#BGB###&BGGB###BGPPGGBBPYYPGBG5Y55PPY???JY5Y?!!?J77?77!~~7?77!77?7!!!!!!~!!77!7777??????JYYY5PGPGBBBB##&&&&&&#BGP55PPPGB&&&@@@&&\n"	
			+"	&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&#&@@@&#B#@@&#BGG#&BGG#PGPG&G5PPGYJ5J7Y5JJJP!77~P?^?JGY~JJP5P^JPP~7P#??#7GJ7Y?G#Y!YBB7YYPG?JYBJ5JPBGBBB5P5#PPBP5PBPGG#BGBBBG#GG##&&#B##&#BP5PBBG5J5BB5YJY55Y?7J55J7?JYY??????????777?JJJJJJJJJYY555PPGGGGBBBB###&&@@@@@\n"	
			+"	#####&########&##&&###&###########BBB#######&&&&&&&&&&&&@@&&@@@@@@@&@@@@&@@#GGGBBB#PGPG&PPP5P?~J!?55PPPY~!!?P!^J?JJY?~JPY:JP!7^7G!7G~P~!7?PY!!^GP~7^J#7!5Y^7YYPPPP5!JY?!77?7!Y~Y!B??G7J?YJ!YBP5B##GY5&55PBY5#5GBP#G##&##BGB&##BB#&#GGB#&BPG#&BPBBBP55BBGPPPPPPPGG55JJGBBBB##&&&@@&@\n"	
			+"	#BGPGBBB#BBBB#@@@@@&&&&&#######BBGGGGGGGGGPPGGPY5BBBBBBBBBBBB#BBB#B##&&##&##&##BGBPBBPG#P5555Y?J7J5555P577Y?Y?!!7YJJ??55J~JJ~5?~JY7!J5~!??5!?Y~7Y~5~JP~!Y7:?7Y5Y55JJ^J~~!7!~!7!~Y5^J5!^7~?^??7!J7P5?5G^Y7B!Y#!5Y~?J#BBJJY??P7Y5JY5PYB#BG#B&#PBBGB&G#&B#&&&###&&@&##BPB&&&&#########\n"
			+"	&#BBBBB##GB#GB@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##BBB###############&&###&&#B555PGBBPGBG55#BP5P5PPP5P5P555PP5PP5555Y5YY5555Y5YYYYJY55YYY55555555PP5Y5Y5YYYJJYYJ??YJYJY?77JJ7JY!!J7!Y?5!YJJ??J775777?!5JJ5577Y5^Y5^P!J^JPB57!?!!7~75!!!?^5Y?7??G5~5PY5P?J#?YGBPPP5PPP&@&PY5P55PP55Y555PP\n"	
			+"	#BB##BGGB#BBG#&&####&&&&&&&&&##########&&####BBBBBBBBBGBGGPGBBBBB#&&&&#5??7!777YBG5YYY#G#J???77????7????JGBGPPPPPPP5PPPP5555P55PPPPPGPY7J555Y5PP5PGPP5PP55P5Y55P5555P5P5P555PPP5555555PP55P55Y555PGB5PPYP5Y55Y5Y5Y5555JYYYJY?YJYYYY?P5?YJJPJ?J5J57P7G!YPBB5J?BGG##GY??????J??7????J\n"	
			+"	BBBGPGGGPBBBGB&##&&&@@&@@@&&&@@@@@&@@&BGGGB#G#@&&#&&BBBBBBBBBBB#BB#&##5?7?!~~!?PP5Y?Y5#P#?7J7777!!!!!!7?7PBPP55PPPGGGGGGPPGGPGGGGGGG#P5:JP!!!~!7JJ@@@@@@&#&#&&@@@&&&&&&&&##&###777!!!77777?PP??!7?YP7?JPGPP555YYJ5Y5PGPPPPPGGPPPPP5P555PPPPGGPPPGPG5PPPPGBP55BGBBGY?77!7?????JJ???Y\n"	
			+"	#BBBGGGGGGGB5P##&@@@&&@&@&B#&&&&&@&&&&&&&#&BP###&###&&&B&&#&&&&#&&&BBG577!!~!75YP5Y?YJ#GB?!?7!!7!~77!!7?7G&#&B##&&###&#B#YJ?J?J?P&5?5YY^JY!7???Y77@@@@&&&####@@&&&&&&##&&#GBPGG~7!!!!!!!777PJ~7??~?5?~7~J77J?7?7777!J#GGGGGGGGGGGGGBBBBBBBBBBBGGBG#BBPJYYYY5YY5GBJY?~~~~J?777??!7?J\n"	
			+"	&&&&&&&&&#&#B#&&@&@@&@@@@&B#@&&&&@@&###@#@#JGBGB##G##BGB&#GBB#@G#@&GBBP?7~~~~J!PYYYJJJ#PBJ~77!~!~~!!!7~!!P&###BGGB###&BG&~::!Y57J&Y!JJY^JY!!77?Y!7&#&&&&&@&@&@&B&@@&&&&&@BP#P5G~!!!!!~~^~!!YY~!!7~JY?!!~!!?J7^7!7!7~!?77777777???JYJJJ??JJJJJJYYPBBBBY?YYYJJYYJYB?Y7~~~~!?7777J?7??\n"	
			+"	###BBBGBBBGGG&&@@@@&@@@@&BG#&&&&@&@#B##@#&BYGB5&###GBBPB##B#GB&G#@&GGG57:^~~^Y~PJ5YJ?7#5#J~7J?7!~^!!??7?~P&B#GYJ?J5B#&B#@?Y?^!Y7Y&?!???^?5~~7?JY!?&&&&&&&&&&&&#G#@&&&&&#&BP#P5G~!77!!!!~7?!JJ!!77^?YJ~!!!!7?7^77?7?~7BGG5??7?7J5BP555PPP5YYJJJ?YG5Y5PJ?J??JJY55JP!?7~^^~~JJ!77JYJJJ\n"	
			+"	BGGGGPGGPGGPG&&@&#@&&@&@&BGB&&#@@&&#B##&#&B5GG?@BB&PB&Y&#GBP#&&&#@&PPPY7^^^7?J?G?YJJ?7#JGY!77!!~!~~!!!!!~5#PB77^^7JP#BP5G~J!~?JYJ#J~?!!^?Y~~~~~!!?P5555GPPGPBGBP#@#&&&&B&P5#55P~!~^^~~^^~^^J5!7!!!5J!!!~7!~!!~!7!!~~!PGP5!!:^^5BG55PPGPPJPYYJJ?P&??5J?JYJJJYY5YJY~!!~^~~!7#BBBBBBB#\n"	
			+"	GBBBBGGBGBBPG@&&##&@#@#&&&B#&&&&&@&#B##@#&&GBGY55PPPPGJGPY55PGGBGBBPPPY7~^~77~!GJJJ?7?#YBY~?J??J7!!77!77~?YYY7.:.^?5GPJ!?5P5?JJJY#?~7^~^7Y!7?77?!!J?!7?JYJYYYGBP#@B&#&#G&P?GP5P~~????~~7!!7?P~7J?!5YY7!~!7!?7^7?7??~~Y55J7^::?B5555PPPPG55#BGPGB#??YJ?JJJJ?JJJ5Y577!~~!77?&@@&&@@@&\n"	
			+"	BBBGGBBBBGGGB&####&&&&&&&&&&&#&##@@#B&&&&&@#GGGGGBBBBBBGGGGBGGGGPPGPGPJ7~!!~~!7GJJJ?7?#5#P~7?77?!~!7!!77^YPPGP?7~!JGPGBP5B#P5BBBPBJ!7~7~75!!7!7J!!!~~~!77JG#5PG5B@B####G#P?GP55~~!???~~!~^775~~!7~5J7!7~~!~!7^!7~!!!!5GBPJY#B#JJJ5#@&&&BPYP77!!J#??JJ7J5557Y5PP?Y!~~^^^~!7#@&&@@@@&\n"	
			+"	BGB##BGBGBGGG&&@@@@@&&&&&&&&&#&&##@&B#&&&&@&GB#&BPPGPGPPGBGGGGG&&&&BB#GGBBBGGGG#BBGGPPGGGP5PPPPPP7~7~~!!^YPPGBBBGGB#GY!GJ5J!!!!?JYY~!7J~7Y?!7!?J!!7~^~~7??P#5PPYB@B###BGBP?GPY5!~7???^~77!?7P~~77!PY7!7!~7777~!7!7!!?GPPY?Y##5?J7J#&&##BP55.::~J#??55JJYY57YP55J5!~~^^^~~!B@#&@@&&&\n"	
			+"	B#BGGGPGPGGGB@&@@@@@@@@@@@@@@&#&&&&@##&&&&@&GB##BYJJ7Y!!7J7???Y&@#&&##GB##BBGBGGB##BGGBGGGB#BBBB@Y~?777!^?PPG###BBBBGP755BJ!~~~~?YY!7??^!YBY!~!!!7?!~!77YYY55PP5B@BBG#BGBGJB555~?!~~~~^~^^~?G?~!!5GJ!!!!~!!~!~~!!!!!?BGBBJ?!!!?J7!5Y5GBJ?5#BBG##&7755YJJ?7?JJ?JYY~^~^^~~!!B&&&@@@@@\n"	
			+"	PPBGBGBBGPGGB@&@@@@@&&@@@&&&@&&#@&&##&&&&#&&BG###PJ???~~^7^~77Y&@B&@&&GGBGBBBBBBG#B55G#BBBBBGB&B&P77!~!!!!55G5G5PYYY5P!J?Y7!~~!~?Y5!!?J^!5#Y7777?!J7~?7JY5PGGGBJG@BGGGPGBGJGP5G!Y577?!!77!?!!7!77!!?J7!~!J??J~!?JJJ!?BB#GGBJ?J?YYJPY5BB!~J@&#G##&7!5PYJJY5?5YYPY?77!!!!!77G&&&&@@@@\n"	
			+"	GBBBGGGGGGGGG&&@@@&@&@@@@@@##@@@@&&####&&&&&BG####PJ5!^~~!:^!!J&@B&&&#GBP5GGGPGGGBBGGGGGPPPPPPGB&Y?J????!~Y5Y?JJ??5Y??7?7Y7J7???JGP!!?J^!Y#Y7?!7!!Y5J?5J5PGPG#B?G@GGGGGGBG?G5Y5!Y5!??!!77!Y~!!~~!!~7J7~!~?JY?^~JYYJ!?B#&GGG?7JY??JY5?77!~5#PBG#&#?755JJYJ5?5Y55J?!7!~~~~!7B&#&@@@@@\n"	
			+"	BBBGGBBGGGGBG@&&&&&&&&&&&&&BB#&&&#BGGB#&#&&&B#B#B#B5B?^~!~^^!7Y&@B@&@#BBGB#B#BBBB#BBBBBBGBBGPPPB@Y777!~!~?GGG#PB!YYYY55PJGJYY#GGG#P~!JJ~!YBJ7?!77!PPJPBPPG5G#&B?G@BBBPGGGP?GGYG!557J?~~7?7J7P!~!!~^!7~~!~77?7~~7??7~?B##GPBPPGB55BGBGGGG5PB5PPGB&77Y5Y?J???JJJYY7^~~^:^~!!B&##@&&@&\n"	
			+"	BGGBGGGBBGGGG@BY5555Y5Y5YJJJJ????J7?!7?5GB&&#B##B##BBP!?!~^^!!5#@B@&@#BGBB#B#&GPPB&#B#&GGPGBBBBB&P!77!!7~J&#&BJ?Y5G?JJ?JYYJYP?P@&BG!!YJ!!YGJ7?!75YPBGBBPBGGBB#BJG@B#?5?^!JJGGJB!55~~~~~!!~!J&!~?7!~777~7~7!!7!~7777!?B#G55YYPGPGYPP5BJ!^~^7BGGBG&!!5PY?YJJ?555P5?^~~^:^~~~G&&&&&@@&\n"	
			+"	GBBGGBBBBGBBG@B7J?J?7?7?77777!7!!7!777775B&&&#BBBB##GP5P!!J?~!GB&B&&&#BGGPP5PBGGGGBGGGBGGPPP5PGG#B!!77!7!JBB#B5Y5GGPBPBP???7777777P!7Y?!~J5?!~!!GJP#GGBBBBB&B&B?G@B#!?B5!?JGG?G77J7?J!~?J7J!5!^JJ7!J??^?~??7J!~?77???G#P5#5YGBPBP5PY5!.7G?!###&##!?5P5JJJY?5PP55J~~~~~~~!7B&&&&&@@&\n"	
			+"	BGGBGGGBBBGBG&B7??J77?7?777?777!7!!?77??PG&@&#BBBB##BBB#Y7PG!?PG&B&&@#GGGGPPGPPGGGGBBGGGBBGGGGJP&B!7!!77?!~!!YGBBBGB#G&Y^::.......P~7??!~YJ!?77??~P&B#B#B##B&#B?P@B#~?#PJ?JGGJ5?^7?Y?!!J???~?!~??7!?YJ~7~7??J!~J?????G#GPG5!5GPPGP5YYY:?B?~###&@&7?YP5JYJJJYYY5PY!!!~~^!7?&##&&@@@@\n"	
			+"	GBBGGGGGBBBBG&B77?Y7!????7777777!7!?777JBG&&&BBBBBB##&B#BPG5!YPG@##&&&GG##BBBGBBBBBGBBBBB#B###GB&G~J7!!?7^:. YGPGPGG5YGJ:.:.......5~???~~YY!?7!7!!5&#B&B#BB&B&#?P@GB!GBP5JYGGJG7^7??7!!??77~?7~!!!!7777!!7!77!~77!77J5BP5PPPG5YJ5YY5Y5:!Y7!PG&@@@7J5YPJY?YY5YY55?:^~^^^~!7##B&&&&&&\n"	
			+"	#BBBBB#GGBBBB@B7!?J7!????777!7!7~!!77775#P&&&#BBBBB#&@&#GGYYYY5G&##&&&GGGBGGGGGGPB#B##BBGPB#BGB#&P~77!!?7^:. ?PGBBBBGBG7^::::.::::5!J7?~~5Y!??!7!7P&B###BB#B&##JP@#B?#GP5YG#BYG7~!^~~^^~!~!!77~??J?Y?Y7!7?77?7!JJ?J?P7~7?PGP5PGGPP5G5JJ??Y5YG#@&&!?5PPY5Y5Y55PP5J:~~~^^^~7#&#&&&@@@\n"	
			+"	BGGGGGGBGPGPG@B?77J?!?777!7!!7~7~~~~!!!P&P&&##GGGBB##@GBBBP5YY5P&##&&&B###BGPPPGBGGBB#GGPP5YPPPG&P!7??7??!~~~75YP55Y??JGGPGBP5555P#7?JY~~5J7!!!?775&###B####B#&YP@BGPPPPPPG##YP7~J77J7~J??J777!Y55?JJ5!!7?!!J?^?7!J?G?.~7PPP5YGPG#BG5YYYY5Y5GB#&&!J5PGYYY55Y5PYPY:~~^^~~!7B&&&#&&&&\n"	
			+"	GGGGGGGBGGPGG&#?77J?!?777~!!!7~7!!!!777G&P&@&BGGGBB#&&BBPYYJJY5P&&B&&@###&&BBBBB##&####&BGGGGGBB#Y77!!!!7!^::^.~YJ::..:#&B#@#####P&?55J!~5Y!??77775&####B##B##&YP@BGPGGGBB5BBYG?~J??7!~7?JY!77!JPY?JJ5!!7J7!J7~777?7J~ ^!5P#GPBGB##5Y?JJJ?YGP&###!?PPGY???JJJJJP5~~777!7JY&&#&&&&&&\n"	
			+"	#BB#BB##BBBBB&#J77JJ!7777!77!7~7!7!!?7JB&P&@&BB#B#&&&G&@BYYYYYPP&@B@&&GBBB##&&&####&&&&#G5PBGB##GBY7J???7!?!^~~!J5^!~^~GGPP55YYJGG&J5JJ!^YY~??!777P@####BB###&&55#BGGGGBBBYGGJPJ~?J?7~7YJJY7Y?!7?77?7!7!7!~~~~~!!~~!~: :^5PPG5JY5JGGP55??~7PP5Y?P?YPPPYY5GJ55PG55~77!~~~77&#&&&&&&&\n"	
			+"	BB#BBB#BBBB#B&#YJ7?J7??7J7??77!?!777??P&#P&&#B#BB#&&#G&#G5YYYY5P&@B&&&GGBGGG#BB###GBBGGBBPPP5PG&B55?J?7?7!5BP55?????????75P7.:: YB&?Y7J7^YJ^?J!?77Y#BBBGBBB#B#B555YYY5PP55YPGJG?!!7!~!5#GPGPPP7JYJ7??Y!7!?77?7^J?7J7!~^~^????777777777!!!!7!!777?JYPGGJY5BJ5PGP55!!!~^!777B&&&@@@@@\n"	
			+"	#B##GBBB#BBBB@#5J77?7?77777777!!!!~77JB&&5&@&##P5PGB#P&#GYJJJY5P&@G#@&GG##B##BBGB##BB#GPGGGGGBG&B55?J77?7!GJJYJ^~!!7?Y7~~#BJ.^^.?G&JJ?Y?~Y?~!~!!77Y&BBBBB######PPPP55JPGGBPGGYPJ~!J?JJ7JY??557!YYJ??J5~!~777J?^J7???~7?7!!!77J??????JJJJYY5PY555PPPPGGJYJJY5YYY5P!!!~^^!77B&#&&&@@@\n"	
			+"	BB##GBB##BGBB@&5?!777?77!7!777!!7!~J?Y##&P&&&&#P55G#GP&&BYJJJY5P&@B&&&GGB######B###BBGBBB#B##B#&&&????7?77577?YJP5PPPP??!B&J~?5J?P&YJJ5J~YJ^??7?J7?G555555YY5P#GGBGYP5YPGGPPGYPY~7J!?Y7?7~~Y7~!JJJ??JY!!!7????!???77!?YP??5YPY?Y?JJJYYYY555G5YYPGGP5PGYP55Y55PP5577?7!??JJ#&&&@@&&&\n"	
			+"	#BG###GB##BBB@&5?7????7J?77J?7777!7J?Y#&&G&&@##55GB&55G&GJYJYY5P&@B@&&BGGBGGGGGGPGBBGBBGGGGGGGGB&&??!!!!775JPY?!77?YJJ?J7##5GJJJ!5BJJY5J~JJ~?J7??7JGP555YYY5YPBGPGBYPPYGGP5PG5GY~7?!7777!!!!!!77!7777!77!!7777!777?J7JP5?75YP5?5YYYY5YY55Y5GP5YPGGP5PBY555JYGPG5P?????YY5J&&##&&&&&\n"	
			+"	BBBBGGBBBBBBB#&5??J??J7JJ!!?77!?!7?JJY#&@G#&@#&PY5GPJ5JGPYYYJY5P#@B@&&BGGBGGPPP5PGBBB###GGGGGGGBB&J7J??JJ7PP5Y7!~!!!!!7?!??777!777??YP5Y~JJ~?JYJ?7JBPP55YYY55PBPPBBYB5Y#GPPPGPBP!?77?7!??77?~7!7J???JY7?77YJ5J77JYJY!JP5??Y555?JJJYY555P5PPGP5YPGG5PPBJY55JJ55PGPJ?!?7?JYY&&&&&&@@@\n"	
			+"	##BBB#BBGG#BB&@P??J???7J?77?77!7!!JJ?YB&@G#@&B&GY5GPYYJ5GJJYYY5P#@B@&&BGGBBBBBBGGG###B##BGG##B#&B&Y7J?JJJ?7777??7????J?!J?JYY?J55YYYYPG5~?J~!7!!!7JBPP555YY55P#PPBG5G5YBGP5PGPBG!7J?J7~75YY?~77!??!JYY!??7YYY?77YYY?~JP577Y555Y&&####&&#&#5PPPJPGGPPGBGGGGGBGBBBBY7?7~!7YY@&&&@@@@@\n"	
			+"	BBBBBBBGGGBBG#@G7?J7??7J?7?J777?77JYY5GB#BB&&##PYPB&PYPBBJYYYY5P#@B@@&#GG####&&GPGBBGB##P5BGGPG##&Y7J?YYY?YYJYP?JGYJY5J7YYJ557JY5?J?YPB5!JY~7?7??7JBPP55555YPGBGPBG5G5YBGP5PBYGG!7??J?!7555?!!!!J?!?YJ7!J7YPYY5J55JJPP55??Y5YPY&####B##&&#5PGPJGGBPPGBBB##B&#&###Y?J777?5G@&&&@@@@@\n"	
			+"	GGPP5555YJJJJ?5B??YJJYY55Y5PPPGGGBBBBB##B&##&#GGY5GG#&&@#JYYYY5PB@B@@&#BBGGGGBBBGGGGGBGGBB#GGGGB#BP??77??JYYYJP??5JJJY?7?YY557?J5JJ?YG#G?JY!?????7JBPP5555YYPP#GPGG5P5YBGPPPB5PG7??7????YY?J5JJ555PP5555PP5YGP5G5YP55G5PJ?Y5YGY&#####&#B&#5PGGYGBBPPPG#@&@&&&&&##Y7?7?JJYB@@&@@&&&&\n"	
			+"	J???????7?JJ?7JBGB##B&&##&&&&&&&#######&&@&&@&BBYY5PBBB#BYYYYY5PB@##&@#BB#####BBBB#B###&&########&PJJJ??JJ5J5YP7?5JJYYJ??J5YY77Y5J?J5G#G7JY7J?J?77YBPP5555555PBGPGBP55JBGPPG#GGG55PPYYP55YBYJPYJGPJPBP#P5&GP#BGB#B####55Y?55JPY&&###B#B#&#5GGG5PGBGGPBB@&&&#&&###577?J555B####BBBGG\n"	
			+"	??????????????Y&&&&@#@@&##&#&&&&&&&#&&##&&&&&&#GJ5P5PPG#BYYYYY5PB@#B@@&GB####&&&&&&&BGB######BB##&5?YJ?JY?5Y5Y57?PJJY5J?JJ55Y7?5YJ?YYG#P7?Y???J??JYBPP5PP5555GBBGGGPPPJBP5P&&GPBPY5BG5G#PBBB5#GBBB######B#&###########G5Y?PG?P5#BBBBBGGGBPPY555YPPGGGBB##&&#&&###BPGGGBBBBBGGGGPPP5\n"	
			+"	??????J????777Y&&&&&&@&@@&&@&&&#####GGGGGB&@@&#GJY55PPP##55YY5PPB@##@@&BB#BBBBBBBBBBBBBBBBBBGBBB#&G?Y7?YJ?5YYY57?PYJY5?7JJ55Y7J5YY?Y5P#B5GG55PPPGP#GPP5PP5555GB#BBBGPPJBGPP&&GPGGB########&##BB#####B###GB##B###B##B#GYJY5JYYYG5555555P5PPPGGGGB####&#######BBBBBGPPPPP55YYYYYYYYYY\n"	
			+"	JJ?J?????J????YB&######&&#BBBBGB#B&&#&&&&&&&@#BB5YY5GBGB#5555PGGB@&#@@&#BBBBBBBBB######&#&&#&&&#B&B7YJYY??P55YP775YJYPJ7YJ5P5?Y55J?YPGBB###B5YBGYPBBGGPPP5555GB###BGBGPGGGGB#GBBG#############GBBBBBGB#GGBG#GBBGGBBBBPPPPGGGGGGGGBBGGGGGGGPPP55YYYJJJJ?????7??J??777????77??JJJJJJJ\n"	
			+"	J??????J????J?JB#BBBBB#&&&&&&&B#&&&#&&#B#@&#@&BBPGGGBGPB#PPP5PPPB@&B@@&BB#####&##&&&&&&@####&&#&#&BYYYYYPGP555PJ?55Y5GYJYYPP5?YP5Y?55G#BBB#&BB#&###&&&&&######&&##BBBGBBBBG5BGGBPB#BGBBPB#GBB5PBBB#B#BBGGBBBBGGBBGGGPP5555YJYJJ??7!!!!!!~~^~~~~~~~~~~~!!777!!!7777!7?77?7?????JJJJJ\n"	
			+"	YJJJJ?JJ??JJJ?JB@&&&&&&&&&&&#&#&#B##@&####&&&&B#&&&@@#PB&GPGPPPGB@&B@@#B#&#&&&&########B###BGGGGB&#GBGBBPPG55GPYJPG5YG5J55GPPY5GY5YPPB#GB&###G###&BBBBBGGBB#BBGGB#BPPG##GBBGGBB&BGBGG#BG#BGGBGPGP5555555YJJ?!!77~!~~~~^^^^^^^^^^^^~~~~~~^^^^^^^^~~!!!!!!77!!!!!!!!!77!!7!77777?????\n"	
			+"	YJYJYJJJJYYYJJYB@&@&&&&&B&&&####BGPPGG#&BB#&@&BB&@&@@&PG#BGPBGGBB@&#&@&B&BGBGBB###BGBBBBB#BBBBGBB##BPG#BGBB5JYB5?YBP5P5555555555Y55YPB#G#&###B##&&B5555555JJJJJJJJ?J????77777?JJYJJJJ?7!!!!~~~~~~~~~!!!77!!!~~!!~~~~~~~~~!!!~~^~^~~~~~^!!~!!!~~~~~!!77!!!!!!!!!!!~!!!!!77777777?777\n"	
			+"	JYJYYYJYYYYYYJY#@&&&###BBGGGB&&BB###&#&@&&@&@&G5&@&&@@PG#BGP55PPG@&&@@&B&##&&#####BB##BB###&&#BB&#B&&&##&&BPYJ5YYJJ???Y5J?JJJY55PPGGGPGB#B#BBGBB#&GY5YJJJ?J?????777777!!!~~~~~~~^^^::^^^^^^^^^^~~~~^^~^~~~!!!!!!!!!!~^^^~~~~77!!!!7!!!!7!!~!!~~~~~~^^~~~~!!~~!!!!~!!!~!!777777777!!\n"	
			+"	JYY55YYJYY555Y5B#B####BB#####&&##&####BB#&@&@&BB&@@@&@#&&&B5YY55P&#&@@&####&&##BB#B#BPB#GG##BBGGB#P#&&###&BJJ7?JYYY5PPPPGGBB#BBBBBBBGGP55Y555JJJJ?7777777777!!!!!!7!!!!!!!!!!!~~^~~~~^^^^~~~~^^^^^^::^^^^:^^^^^^:::^^^^~~~~~~~~~!!!!7!!!!!77777777777777??77!!7!!!!~~~!!!77!!!777!!\n"	
			+"	YY5555YY5555555B&#&&@@&&##&#&&&&&&&&&#B#&##&@&&&&@&@@@@@&&BPPPPGG&@&@&&#BBBBBGGGGGPGBBGGGGGGPGGGG#G##&BB##BGBBB#BBBGGGP55YJJJ?7777!~!!~~~~~~!~~~~~~~^~~~~~~~~!!!!!!!!~~^~^~^^^^^~~~~~~~~~~~~~~~~~~^^^^::::^^^^^::::^^^^:::^^^^^^^^^^~~!!!!!~~!~!!7!!!!!!!!!!!!!~~~~~!!~~~!7!!!77?77\n"	
			+"	5555PP55555YYY5B@&&&&#@&#&&&&@&&@@&#B&&&#G#&@&&&##&&#&###&#GBB&&&@@&&&&#BPPGGGBGGBBBBBBBB##&&&&&&&#BGGGG55555YJ??77!!!~~~^^^~~~~~~~~~~~~~~~~!~~~^~~^^^~^^^^^^^^^^^^^^^^^^^^^^^^^^^~^^^^^^^^^^^^^^:^^^:::^^^::^^^^^^^^^~^^^^^^^^^^^^^^~~^^~~~~~!~^~~~~~~~~~~~~~~~~~~~~~~!!!!!!!7777!\n"	
			+"	YY555555PYJYYY5B&@@&#&&&&#&@#BB#&#&#B@&&@&@@@&@&BBBBBGGGBBBB##&#B##BG#B###&&&&&&@@@@@&&&&##BBGGPP5YYJJJJ?????777!!!!!!!!!!7!77!!!!!!!!!!!!77!!~~~!~~~~~~^^^^^^^^^^^^^^^::^^^^^^^^^^^^^:^^::^:::::::^::::^:::::::::::::::^^:::^::^^^^^^::^~~^^~~~~~~~~~~~^~~~~^~~~^^~~~~^~^~~~^!!!!!\n"	
			+"	5YYYYYYYYYJJY55#&##&@@&#B#&&&&B@&&@&@@&@@&@@@@&&B5PGGBBBBBBGGPGPPGGGGBB#&&&&&#BBGGGP55Y55555YYJJYYJ??????????J??7!!!!!!~~~~~~~~~!!!~!!!!!!!7!!~!!~~~~~~^^^^^^~~~^~~~~~^~~^^^~~~~~~^^^^:^:::^^^:::::^::^:::::::::::::^^::^^::::::::::::^^^~~^::^^^^^^^^^^^^^^^~~^^~~~~~~~^~!!!!!!7!!\n"	
			+"gracias a los tratamientos y la comida aquellos jugadores mal heridos restauraron sus vidas a 3 y los que no sumaron una vida extra");
			for(int i=0; i<PLAYERS-1 && !comprobant;i++){
				if(players[i]!=null){
					if(players[i].getnumberOfLifes()<3){
						players[i].setnumberOfLifes(3);
					}else{
						players[i].setnumberOfLifes(players[i].getnumberOfLifes()+1);
					}
				}else{
					comprobant=true;
				}
			}
		break;
		}
		return message;
	}
	
}