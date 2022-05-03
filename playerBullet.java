/*
 * Numair
 * playerBullet
 * Creates object for player bullet when shot
 */
package spaceInvaders;

import java.awt.Image;
import java.awt.Toolkit;

public class playerBullet extends ObjectReference{
	int vy, width, height;//Creates velocity, width, and height variables
	/*
	* constructor
	* pre: int x - x coordinate , int y - y coordinate
	* post: Creates a bullet object with a velocity set at -1 and with an image with width/height
	*/
	public playerBullet(int x, int y) {
		//sets the x and y coordinates of the bullet
		super(x, y);
		//sets width and height of bullet
		width = 15;
		height = 20;
		//sets velocity of bullet
		vy = -1;
		//sets image to player's bullet and scaled to the width and height
		Toolkit kit = Toolkit.getDefaultToolkit();
		image = kit.getImage("gameimg/bullet2.png");
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	//moves the y coordinate based on velocity
	public void move() {
		y += vy;
	}
	//returns y
	public int getY() {
		return y;
	}
}
