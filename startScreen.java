/*
 * Numair
 * startScreen
 * First panel user sees, hold play and rule button
 */
package spaceInvaders;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;

import javax.swing.*;

public class startScreen extends PanelReference implements ActionListener {
	  Image background, title;//images for background and title
	  JButton playbtn = new JButton ("Play");//button for play
	  JButton instrucbtn = new JButton ("Instructions");//button for instructions
		/*
		* constructor
		* pre: int w - width, int h - height
		* post: A panel is created for the start screen that holds the menu
		*/
	protected startScreen(int w, int h) {
	      //set the panel to correct size
		  super(w, h);
	      setLayout(null);
	      //grab both images and scales them for background and title
	      Toolkit kit = Toolkit.getDefaultToolkit();
	      background = kit.getImage("gameimg/spaceBackground.jpg");
	      title = kit.getImage("gameimg/spaceInvadersTitle.png");
		  background = background.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
		  title = title.getScaledInstance(500, 200, Image.SCALE_SMOOTH);
		  //sets bounds to the buttons
		  playbtn.setBounds(175,350,150,30);  
		  instrucbtn.setBounds(175,390,150,30);
		  //sets the buttons foreground and background
		  playbtn.setBackground(Color.BLACK);
		  instrucbtn.setBackground(Color.BLACK);
		  playbtn.setForeground(Color.WHITE);
		  instrucbtn.setForeground(Color.WHITE);
		  //adds buttons to panel
		  add(playbtn);
		  add(instrucbtn);
		  setVisible(true);
	    }
	//empty actionPerformed as the events are handled in GameMain
	public void actionPerformed( ActionEvent event ){
		}
	/* Purpose of method: paints the background and title image 
	 * Accepts: Graphics g
	 * Returns: image of background and title image
	 */
    public void paintComponent(Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        //Draws background and title
        comp2D.drawImage(background, 0, 0, this);
        comp2D.drawImage(title, 0, 0, this);

    }
    //returns instrucbtn
    public JButton getRules() {
		return instrucbtn;
	}
    //returns playbtn
    public JButton getPlay() {
		return playbtn;
	}
}
