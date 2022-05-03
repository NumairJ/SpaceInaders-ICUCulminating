/*
 * Numair Jaan
 * aliensTwo
 * Creates alien two object used in the game
 */
package spaceInvaders;

import java.awt.Image;
import java.awt.Toolkit;

public class aliensTwo extends ObjectReference {
	int vx, vy;//X and Y velocity
	static Boolean moveLeft;//Determines if it is moving left or not
	static Boolean moveDown = false;//Determines if it should move or not
	/*
	* constructor
	* pre: int x - x coordinate , int y - y coordinate
	* post: Creates a alien object with a velocity set at 1 for x and 5 for y and with an image with width/height
	*/
	public aliensTwo(int x, int y) {
		super(x, y);
		//sets up velocities and size of alien
		vx =1;
		vy =5;
		width= 25;
		height = 20;
		//default to moving right
		moveLeft = false;
		//sets image to player's bullet and scaled to the width and height
		Toolkit kit = Toolkit.getDefaultToolkit();
		image = kit.getImage("gameimg/balien2.png");
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	/* Purpose of method: Move alien2 to the left or right based on boolean - moveLeft
	 * Accepts: 
	 * Returns: this.alien2 new x coordinate
	 */
	public void move() {
		//moves to the right if moveLeft is false
		if (moveLeft == false)
			x += vx;
		//moves to the left is moveLeft is true
		else if (moveLeft == true)
			x -= vx;

	}
	/* Purpose of method: Move alien2 to the down based on boolean - moveDown
	 * Accepts: 
	 * Returns: this.alien2 new y coordinate
	 */
	public void moveDown() {
		//moves the alien down
		if (moveDown == true) {
			y += vy;
		}
	}
	/* Purpose of method: Determines if the alien should be moving left or right
	 * Accepts: 
	 * Returns: this.alien2 moveLeft either true if reaches right border and false if it reaches left border
	 */
	public void update() {
		//If alien reaches the left border, moveLeft is false
		if (x <= 0) {
			moveLeft = false;
			moveDown = true;
		}
		//If alien reaches right border, moveLeft is true
		else if (x >= 500 - width) {
			moveLeft = true;
			moveDown = true;
		}
	}
	//returns width
	public int getWidth() {
		return width;
	}
	//returns height
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	//returns moveDown
	public static boolean isMoveDown() {
		// TODO Auto-generated method stub
		return moveDown;
	}
	//sets moveDown
	public static void setMoveDown(boolean b) {
		moveDown = b;
	}
	//returns x
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	//returns y
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
}