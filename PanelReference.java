/*
 * Numair 
 * PanelReference
 * Parent class to all panels, allows finding width and height much easier
 */
package spaceInvaders;

import javax.swing.*;

public class PanelReference extends JPanel {
	int width, height;//holds width and height
	/*
	* constructor
	* pre: int w - width, int h - height
	* post: A panel is with height and width
	*/
	protected PanelReference(int w, int h) {
		//sets the width and height of the panel
		this.width = w;
		this.height = h;
	}
}
