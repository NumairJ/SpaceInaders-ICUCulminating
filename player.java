/*
 * Numair
 * player
 * Create's player tank object
 */
package spaceInvaders;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class player extends ObjectReference{
	static int width, height;//width and height of tank
	int vx;//velocity of player
	boolean moveLeft = false, moveRight = false;//movement direction of tank
	/*
	* constructor
	* pre: int x - x coordinate , int y - y coordinate
	* post: Creates a tank object with a velocity set at 0 and with an image with width/height
	*/
	public player(int x, int y) {
				//Set's player s and y
				super(x, y);
				//sets velocity to 0
				vx = 0;
				//sets height and width of the tank
				width = 50;
				height = 35;
				//sets image to tank and scaled to the width and height
				Toolkit kit = Toolkit.getDefaultToolkit();
				image = kit.getImage("gameimg/spaceTank.png");
				image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);				
			}
	/* Purpose of Method: Determines the velocity based on boolean variables 
	 * Accepts:
	 * Returns: this tank's new x velocity coordinate
	 */
	public void Update() {
		//if moveleft is true and x is still in bounds, sets velocity to -2
		if (moveLeft == true && x > 0 ) {
			vx = -3;
		}
		//if moveright is true and x is still in bounds, sets velocity to 2
		else if (moveRight == true && x+width+11 <= 500 ) {
			vx = 3;
		}
		//defaults to 0 velocity to stop moving the tank
		else {
			vx = 0;
		}
	}
	/* Purpose of Method: Move method moves the x coordinate based on the velocity
	 * Accepts:
	 * Returns: this tank's new x coordinate
	 */
	public void Move() {
			x += vx;
	}
	/* Purpose of method: Creates a rectangle around the object making a rectangle, used for hitbox scans 
	 * Accepts:
	 * Returns: Rectangle around the tank
	 */
	public Rectangle bounds() {
		return new Rectangle(x, y, width, height);
	}
	//return x
	public int getX() {
		return x;
	}
	//return y
	public int getY() {
		return y;
	}
	//return moveLeft
	public boolean isMoveLeft() {
		return moveLeft;
	}
	//sets moveLeft
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	//returns moveRight
	public boolean isMoveRight() {
		return moveRight;
	}
	//sets moveRight
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
}




