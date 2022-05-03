/*
 * Numair Jaan
 * game
 * Class that plays the Space Invaders game.
 */
package spaceInvaders;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;


public class game extends PanelReference implements Runnable, KeyListener{
	  Image background;//Holds image of background
	  player tank;//Player's tank object
	  ArrayList<playerBullet> playerb = new ArrayList<playerBullet>();//Holds array list of user's bullet(Used to keep player from spamming the spacebar)
	  ArrayList<alienBullet> alienb = new ArrayList<alienBullet>();//Holds array list for alien bullets(Array list is used to later remove them with ease and create new ones)
	  ArrayList<aliensOne> alien1 = new ArrayList<aliensOne>();//Holds the 20 red aliens object 
	  ArrayList<aliensTwo> alien2 = new ArrayList<aliensTwo>();//Holds the 10 yello aliens object
	  static Boolean isLose = false;//Determines if the game has ended by either aliens reaching objective, killing all aliens or user has lost
	  static Boolean run;//Determines when the runner should stop and start
	  static int score;//Holds user current score 
	  static int lives;//Holds user lives
	  int maxb;//Holds max bullets user can use
	  int alienx;//Starting x coord for aliens
	  int alieny;//Starting y coord for aliens
	  Thread runner;//Runner thread that is the core of the game
	  int sec=0;//Number of seconds that pass by, start at 0
	  static int min;//Number of minutes that pass by
	  static String dsec;//Converts seconds to string to be formatted
	  String scor;//Converts the score into string to be formatted
	  Timer timer;//Timer used to time the whole game
	  DecimalFormat dFormat = new DecimalFormat("00");//Format used for seconds, shows two digits
	  DecimalFormat sFormat = new DecimalFormat("0000");//Format used for score, shows four digits
		/*
		* constructor
		* pre: int w - width, int h - height
		* post: A panel is created for the game, where all objects are placed and begins a thread
		*/
	protected game(int w, int h) {
		//sets the size of the panel
		super(w, h);
		setFocusable(true);
		setDoubleBuffered(true);
		//no layout
		setLayout(null);
		//adds key listener
	    addKeyListener(this);
	      //Initialize variables for the main game
	      run = true;
	      dsec = "00";
	      min = 0;
	      score = 0;
		  lives = 5;
		  maxb = 2;
		  alienx = 75;
		  alieny= 60;
	    //Uses toolkit to grab the background image and resizes it to appropriate size  
	    Toolkit kit = Toolkit.getDefaultToolkit();
	    background = kit.getImage("gameimg/spaceBackground.jpg");
		background = background.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
				
		//Creates new player object near the middle panel
		tank =  new player(250,550);
		//generates aliens
		alienBack();
		
		setVisible(true);
		//Based on virthiya
		Runnable generate = new Runnable() {
			/* Purpose of method: To create maximum of 6 bullets from aliens on the screen
			 * Accepts:
			 * Returns: New bullet at a random alien location
			 */
			public void run() {
				//try/catch in case the alien1 or alien2 arraylist is empty
				try {
					//Ensures there are only 6 bullets on the screen
					if (alienb.size() < 6) {
						//creates a random number based number of alien1 left, and create bullet based on their location
						int d = (int)(Math.random()*(1+alien1.size()-1));
						alienb.add(new alienBullet(alien1.get(d).getX()+(25/2), alien1.get(d).getY()+20));
						//creates a random number based number of alien1 left, and create bullet based on their location
						d = (int)(Math.random()*(1+alien1.size()-1));
						alienb.add(new alienBullet(alien1.get(d).getX()+(25/2), alien1.get(d).getY()+20));
						//creates a random number based number of alien2 left, and create bullet based on their location
						d = (int)(Math.random()*(1+alien2.size()-1));
						alienb.add(new alienBullet(alien2.get(d).getX()+(25/2), alien2.get(d).getY()+20));
					}	
				} catch (Exception e) {
				}
			}
		};
		//Creates a schedule for generate method to execute first 4 seconds and then after that, every 1 second if the statements are true
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(generate, 4, 1, TimeUnit.SECONDS);

		//begins runner
		runner = new Thread(this);
		runner.start();
		//begins timer
		gameTimer();
		timer.start();
			
		}
	/* Purpose of method: To count the number of seconds and minutes that pass by
	 * Accepts:
	 * Returns: New bullet at a random alien location
	 */		
	public void gameTimer() {
		//creates new timer that counts up every 1000ms and creates an actionListener every 1000ms
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 //sec is increased by one and formats into string
				 sec++;
				 dsec = dFormat.format(sec);
				 //every 60 sec, min++ and sec is reset to zero
				 if(sec==60) {
					 sec=0;
					 min++;
				 }
				 
			}
		});
		
	}
	/* Purpose of method: To create alien at appropriate locations
	 * Accepts:
	 * Returns: Aliens location
	 */	
	public void alienBack() {
		//For loop used to create an array of Aliens 1(20)
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				alien1.add(new aliensOne(alienx, alieny));
				alienx += alien1.get(i).getWidth() + 5;
			}
			alienx = 75;
			alieny += alien1.get(i).getHeight() + 5;
		}
		//For loop used to create a row of 10 Aliens 2
		for (int j = 0; j < 10; j++) {
			alien2.add(new aliensTwo(alienx, alieny));
			alienx += alien2.get(j).getWidth() + 5;
		}
		//resets alien original position
		  alienx = 75;
		  alieny= 60;
	}
	/* Purpose of method: To draw all objects, background and data 
	 * Accepts: Graphics g
	 * Returns: All drawn images of objects, data and background
	 */
	public void paintComponent(Graphics g) {
		        //Continues to draw player, aliens, background and bullets until the game has ended
				if(GameMain.isStopGame() == false) {
					//Draws background to cover past images
			        Graphics2D comp2D = (Graphics2D) g;
			        comp2D.drawImage(background, 0, 0, this);

					tank.Draw(g, this);
					//draws bullets for player
					for (int i = 0; i < playerb.size(); i++) {
						playerb.get(i).Draw(g, this);
					}
					//draw alien bullets
					for (int i = 0; i < alienb.size(); i++) {
						alienb.get(i).Draw(g, this);
					}
					//draw alien1 and alien 2
					for (int i = 0; i < alien1.size(); i++) {
						alien1.get(i).Draw(g, this);
					}
					for (int i = 0; i < alien2.size(); i++) {
						alien2.get(i).Draw(g, this);
					}
					//formats score into 4 digits(0000)
					scor = sFormat.format(score);
					//sets new font for g
					Font f = new Font("", Font.PLAIN, 33);
					g.setFont(f);
					g.setColor(Color.white);
					//outputs the game information to player
					g.drawString("Score:" + scor + "  Lives:" + lives + "  Time: " + min+ ":" + dsec , 10, 620);   
				}
				//If loss fills the panel with the background
				else {
			        Graphics2D comp2D = (Graphics2D) g;
			        comp2D.drawImage(background, 0, 0, this);
				}

	}
	/* Purpose of method: Perform appropriate movement based on KeyEvent
	 * Accepts: KeyEvent e
	 * Returns: New tank x or bullet based KeyPressed
	 */
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		//if right arrow enables tank to move to the right
		if (keyCode == KeyEvent.VK_RIGHT) {
			tank.setMoveRight(true);
		}
		//if right arrow enables tank to move to the left
		if (keyCode == KeyEvent.VK_LEFT) {
			tank.setMoveLeft(true);
		}
		//if spacebar is pressed creates new bullet within the arraylist at middle of the tank location. This only happens if there are no bullet objects present on the screen
		if (keyCode == KeyEvent.VK_SPACE && playerb.size() < maxb) {
			playerb.add(new playerBullet(tank.getX()+(player.width/2)-8,tank.getY()));

		}
	}

	
	public void keyReleased(KeyEvent e) {	
	}

	public void keyTyped(KeyEvent e) {		
	}
	/* Purpose of method: Run thread runner until boolean run is false
	 * Accepts: 
	 * Returns: If a button is pressed, an appropriate action will occur
	 */
	public void run() {
		//continues to run untill run is false(When user either wins or lose)
		while (run) {
			//allows the tank to set velocity
			tank.Update();
			//Moves tank according to velocity
			tank.Move();
			//Sets both movement to be false, making velocity 0, stopping the tank
			tank.setMoveRight(false);
			tank.setMoveLeft(false);
			
			//Removes bullets that have left the panel, removing bullet allows another bullet to be creates at that very spot within the array
			for (int i = 0; i < playerb.size(); i++) {
				//determines if the bullet has left the upper panel
				if (playerb.get(i).getY() < 0) {
					playerb.remove(i);
				}
				
				else {
					
					for (int j = 0; j < alien2.size(); j++) {
						//determines if the bullet has connected to an alien 2 granting 100 points
						if (playerb.get(i).bounds().intersects(alien2.get(j).bounds())) {
							playerb.remove(i);
							alien2.remove(j);
							score += 100;
							break;
							}
						}
					}
				}
			//Removes alien and bullet for the first row, alien 1
			for (int i = 0; i < playerb.size(); i++) {
					for (int j = 0; j < alien1.size(); j++) {
						//determines if the bullet has connected to an alien 1 granting 150 points
						if (playerb.get(i).bounds().intersects(alien1.get(j).bounds())) {
							playerb.remove(i);
							alien1.remove(j);
							score += 150;
							break;
							}
						}
					}
			//determines if the player has killed all aliens creates the aliens back and remove any existing bullets
			if(alien1.size() == 0 && alien2.size() == 0) {
				//removes player's bullets
				for(int i = 0; i < playerb.size(); i++) {
					playerb.remove(i);
				}
				//removes alien's bullets
				for(int i = 0; i < alienb.size(); i++) {
					alienb.remove(i);
				}
				alienBack();
				//adds one extra live for player after each wave of aliens killed
				lives++;
			}
			
			//Removes aliens bullets that have left he panel, removing bullet allows another bullet to be creates at that very spot within the array
			for (int i = 0; i < alienb.size(); i++) {
				//determines if the bullet has left the bottom panel
				if (alienb.get(i).getY() > height) {
					alienb.remove(i);
					
				}
				//determines if the bullet has connected to the player taking away one life
				else if (alienb.get(i).bounds().intersects(tank.bounds())) {
							alienb.remove(i);
								lives--;
								//determines if the user has died, stops timer and runner
								if(lives == 0) {
									timer.stop();
									isLose=true;
									run = false;
								}
							}
						}
			
			//Allows bullet to travel vertically
			for (int i = 0; i < playerb.size(); i++) {
				playerb.get(i).move();
			}
			for (int i = 0; i < alienb.size(); i++) {
				alienb.get(i).move();
			}
			
			//Aliens update determining if they should move left, right or down			
			for (int i = 0; i < alien1.size(); i++) {
				alien1.get(i).update();
			}
			for (int i = 0; i < alien2.size(); i++) {
				alien2.get(i).update();
			}
			//If one alien determines it should move all aliens move down, ensures no contact between aliens
			if (aliensOne.isMoveDown() == true) {
				for (int j = 0; j < alien1.size(); j++) {
					alien1.get(j).moveDown();
				}
				//sets alien 2 to move down true
				aliensTwo.setMoveDown(true);
				for (int j = 0; j < alien2.size(); j++) {
					alien2.get(j).moveDown();
				}
			}
			//sets alien move down false to not cause another move down
			aliensOne.setMoveDown(false);
			aliensTwo.setMoveDown(false);
			
			//move alien1 to its velocity
			for (int i = 0; i < alien1.size(); i++) {
				alien1.get(i).move();
				//If alien1 has reached y goal, runner stops and timer stops
				if(alien1.get(i).getY()>=570) {
					isLose=true;
					timer.stop();
					run = false;
				}
			}
			//move alien2 to its velocity
			for (int i = 0; i < alien2.size(); i++) {
				alien2.get(i).move();
				//If alien2 has reached y goal, runner stops and timer stops
				if(alien2.get(i).getY()>=570) {
					isLose=true;
					timer.stop();
					run = false;
				}
			}
			
			//repaints all images and grabs focus
			repaint();            
            //Sleeps the thread to keep the game moving to fast
			try {
				Thread.sleep(5);
			} 
			catch (InterruptedException e) {
			}
		}
		//stops timer(Used in the beginning of the project when new game classes are created and are stopped)
		timer.stop();
	}
	//sets isLose
	public static void setLose(boolean b) {
		isLose = b;
	}
	//returns score
	public static int getScore() {
		return score;
	}
	//returns min
	public static int getMin() {
		return min;
	}
	//returns seconds in string
	public static String getDsec() {
		return dsec;
	}
	//sets run
	public static void setRun(Boolean b) {
		run = b;
	}
	

}
