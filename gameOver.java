/*
 * Numair Jaan
 * GameOver
 * Panel after user has lost the game, shows game information within the same play session
 */
package spaceInvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class gameOver extends PanelReference{
	static int score;
	static int min;
	static String dsec;
	static int hiScore;
	static int sec;
	JButton playa = new JButton ("Play Again");//button for playing again
	JButton back = new JButton ("Back to Start");//button for going back to the beginning
	Image background;
	/*
	* constructor
	* pre: int w - width, int h - height
	* post: A panel is created for end screen where it the player can play again or see the menu again
	*/
	public gameOver(int w, int h) {
		//sets the panel size
		super(w,h);
		setLayout(null);
	    //Uses toolkit to grab the background image and resizes it to appropriate size  
	    Toolkit kit = Toolkit.getDefaultToolkit();
	    background = kit.getImage("gameimg/spaceBackground.jpg");
		background = background.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
		playa.setBounds(175,350,150,30);  
		back.setBounds(175,390,150,30);
		//sets foreground and background of plyabtn and backbtn
		playa.setBackground(Color.BLACK);
		back.setBackground(Color.BLACK);
		playa.setForeground(Color.WHITE);
		back.setForeground(Color.WHITE);
		//adds button to panel  
		add(playa);
		add(back);
		setVisible(true);
	}
	//empty actionPerformed as the events are handled in GameMain
	public void actionPerformed( ActionEvent event ){
	}
	/* Purpose of method: Draws game data based on game class and background 
	 * Accepts: Graphics g
	 * Returns: Game data and background
	 */
	public void paintComponent(Graphics g) {
	        Graphics2D comp2D = (Graphics2D) g;
	        //draws background
	        comp2D.drawImage(background, 0, 0, this);
	        //updates results for score, hiscore and time
	        update();
	        //creates new font size
	        Font f = new Font("", Font.PLAIN, 33);
	        //Displays game information in white
			g.setFont(f);
			g.setColor(Color.white);
	        g.drawString("Game Over" , 175, 206);
	        g.drawString("Score:" + score , 175, 243);
	        g.drawString("Time:" + min + ":" + dsec , 175, 280);
	        g.drawString("Hi-Score:" + hiScore, 175, 317);
	        //repaints information when new updated data is entered
	        repaint();

		}
	/* Purpose of method: Update game data based on the game class method 
	 * Accepts: 
	 * Returns: New game data into the appropriate variables
	 */
	public static void update() {
		//stores information from game class
		score = game.getScore();
		min = game.getMin();
		dsec = game.getDsec();

		//if new score is higher than previous hiscore, replaces hiscore with score
		if(score> hiScore) {
		hiScore = score;
		}
	}
	//returns back button
    public JButton getBack() {
		return back;
	}
    //returns playa button
    public JButton getPlayAgain() {
		return playa;
	}
}
