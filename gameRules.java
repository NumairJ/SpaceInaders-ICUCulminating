/*
 * Numair
 * gameRules
 * Panel to holds rules and back button to go back to start panel
 */
package spaceInvaders;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class gameRules extends PanelReference implements ActionListener{
	  JButton backbtn = new JButton ("back");//Creates button to move back to start menu
	  JLabel titleText, rule1, rule12, rule13, rule2, rule3, rule4, rule5;//All labels needed for rules
		/*
		* constructor
		* pre: int w - width, int h - height
		* post: A panel is created for rules
		*/
	protected gameRules(int w, int h) {
	    //sets the panel size  
		super(w, h);
		  //Set lay out to box layout on the y axis
	      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	      
	      setBackground(Color.black); //sets the background to be black
	      //creates a border with insets making a rectangle where the text will be placed
	      setBorder(new EmptyBorder(190, 50, 100, 50)); 
	      //title label
		  titleText = new JLabel("RULES");
		  //Uses HTML to create all lables to make appropriate breaks within the text and text
		  rule1 = new JLabel("<html>1. Use the right and left arrow keys to move your tank. Moving right and left is essential part of the game as it is used to dodge and postion yourself to shoot at thealiens</html>");
		  rule2 = new JLabel("<html>2. Use the spacebar to shoot back at the aliens(Can only have 2 bullets on the screen at each given moment)</html>");
		  rule3 = new JLabel("<html>3. Getting hit by an alien's bullet will make you lose one hp</html>");
		  rule4 = new JLabel("<html>4. You lose when all lives are gone or when aliens reach your tank. Survive as longest as possible as aliens will respawn after being destroyed. Each wave destroyed restores one life.</html>");
		  //sets all text fonts to Times new Roman and to appropriate font size
		  titleText.setFont(new Font("Times New Roman", Font.PLAIN, 45));
		  rule1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		  rule2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		  rule3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		  rule4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		  //sets all text to be white against the black background
		  titleText.setForeground(Color.WHITE);
		  rule1.setForeground(Color.WHITE);
		  rule2.setForeground(Color.WHITE);
		  rule3.setForeground(Color.WHITE);
		  rule4.setForeground(Color.WHITE);
		  //aligns all components to be centered with the x axis
		  setAlignmentX(Component.CENTER_ALIGNMENT);
		  //sets background and foreground for the button
		  backbtn.setBackground(Color.BLACK);
		  backbtn.setForeground(Color.WHITE);
		  //adds the labels and button to panel
		  add(titleText);
		  add(rule1);
		  add(rule2);
		  add(rule3);
		  add(rule4);
		  add(backbtn);
	    }
	//returns backbtn
    public JButton getBack() {
		return backbtn;
	}
	//empty actionPerformed as the events are handled in GameMain
	public void actionPerformed(ActionEvent arg0) {		
	}
}
