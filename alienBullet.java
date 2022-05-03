/*
 * Numair Jaan
 * alienBullet
 * Creates alien bullet based on a random alien position
 */
package spaceInvaders;

import java.awt.Image;
import java.awt.Toolkit;

public class alienBullet extends ObjectReference{
	int vy, width, height;//Creates velocity, width, and height variables
	/*
	* constructor
	* pre: int x - x coordinate , int y - y coordinate
	* post: Creates a alien bullet object with a velocity set at -1 with an image with width/height
	*/
	public alienBullet(int x, int y) {
		//sets the x and y coordinates of the bullet
		super(x, y);
		//sets width and height of bullet
		width = 15;
		height = 20;
		//sets velocity of bullet
		vy = 1;
		//sets image to alien's bullet bullet and scaled to the width and height
		Toolkit kit = Toolkit.getDefaultToolkit();
		image = kit.getImage("gameimg/alienbullet2.png");
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	//move the y coordinate according to the velocity
	public void move() {
		y += vy;
	}
	//returns y
	public int getY() {
		return y;
	}
}