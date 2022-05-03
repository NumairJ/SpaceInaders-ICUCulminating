/*
 * Numair
 * ObjectReference
 * Parent class for all objects
 */
package spaceInvaders;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class ObjectReference {
	protected Image image;//holds image for object
	protected int x, y;//holds x and y coordinates of object
	protected int width, height;//Holds width and height of object
	/*
	* constructor
	* pre: int x - x coordinate , int y - y coordinate
	* post: Creates an object with an x and y coordinate
	*/
	public ObjectReference(int x, int y) {
		//Stores X and Y coordinates
		this.x = x;
		this.y = y;
	}
	/* Purpose of method: draws image into the panel called at the appropriate x and y coordinate 
	 * Accepts: Graphics g, JPanel pnale
	 * Returns: image at appropriate location
	 */
	public void Draw(Graphics g, JPanel panel) {
		g.drawImage(image, x, y, panel);
	}
	/* Purpose of method: Creates a rectangle around the object making a rectangle, used for hitbox scans 
	 * Accepts:
	 * Returns: Rectangle around the objects
	 */
	public Rectangle bounds() {
		//creates new rectanlge around the objects, +5 is to make hitboxes larger
		return new Rectangle(x, y, width+5, height+5);
	}




}
