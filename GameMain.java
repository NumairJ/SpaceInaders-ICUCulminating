/*
 * Numair Jaan
 * GameMain
 * Main class that is a frame, holding all other panels to be shown and includes buttons. This is also resposible to switch panels.
 */
package spaceInvaders;

import java.awt.event.*;
import javax.swing.*;

public class GameMain extends JFrame implements ActionListener {
	int width = 500;//width for frame and panels
	int height = 700;//height for frame and panels
	static boolean gamestart = false;//determines if the game has started
	static boolean gamestop = false;//determine if the game has ended
	startScreen start = new startScreen(width, height);//Panel for the start menu
	gameRules rules = new gameRules(width, height);//Panel for rules
	game Game = new game(width, height);//Panel for main game
	gameOver end = new gameOver(width, height);//Panel for end panel
	/*
	* constructor
	* pre: 
	* post: A frame is created where all panels and respective components are place
	*/
	public GameMain() {
		//Creates JFrame with Space Invaders as the frame title
        super("Space Invaders");
        //Sets size of frame
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ensures the game doesn't start prematurely(Still keep the first initialize for the doublebuffered and to set request focus
        game.setRun(false);
        //Adds action listener to play button, rules button, play back button and the back button
        start.getPlay().addActionListener(this);
        start.getRules().addActionListener(this);
        rules.getBack().addActionListener(this);
        end.getPlayAgain().addActionListener(this);
        end.getBack().addActionListener(this);
        //Sets the start panel as the main panel
        setContentPane(start);
		setVisible(true);
		//Reduce flickering seen on the panels
		Game.setDoubleBuffered(true);
		//While loop Assists to switch to game panel and gives focus to that panel(Virthiya)
		while (true) {
			Game.requestFocusInWindow();
			//if user has lost/won the game and is on the game panel, changes to end panel
			if(getContentPane() == Game && game.isLose == true) {
				gamestop = false;
				//sets panel to ending screen
				setContentPane(end);
				revalidate();
				//resets isLose for when the player wants to return back to playing the game
				game.setLose(false);

			}
			//if game start is true by pressing the play button or play again, game panel will appear
			else if (gamestart == true) {
			gamestop = false;
			//creates a new game panel for the game to start on a clean slate, no changes in the variables
			Game = new game(width, height);
			//sets panel to the game
			setContentPane(Game);
			revalidate();
			Game.requestFocusInWindow();
			//Sets to false to ensure else if doesn't run again
			gamestart = false;
				}
			
			Game.requestFocusInWindow();

		}
    }
	/* Purpose of method: Perform appropriate action based on ActionEvent
	 * Accepts: ActionEvent event
	 * Returns: If a button is pressed, an appropriate action will occur
	 */
	public void actionPerformed( ActionEvent event ){
		//Pressing play button will set gamestart to true allowing the game panel to appear 
		if( event.getSource() == start.getPlay() || event.getSource() == end.getPlayAgain()){
			gamestart = true;

		 }
		//Rule button sets the panel to the rules
		 if( event.getSource() == start.getRules()){
			 setContentPane(rules);
			 revalidate();
		 }
		 //back button transfer from the rules panel to the start panel
		 if( event.getSource() == rules.getBack() || event.getSource() == end.getBack()){
			 setContentPane(start);
			 revalidate();
		 }
		}
	
    public static void main(String[] arguments) {
    	new GameMain();

    }
    //returns gamestop
	public static boolean isStopGame() {
		return gamestop;
	}

}
