import java.util.Arrays;
import java.awt.*;
import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.border.Border;


import javax.swing.*;


public class GUI extends JPanel 
{
	public int[][] boardlayout(){
	// Declaring a 2D array of integers
	int[][] arr;
	// Initializing row and column sizes of 7x7
	arr = new int[7][7];
	// Assigning values
	// // Fill each row with 2 
	for (int i = 0; i < 7; i++) { // for each row
		for (int j = 0; j < 7; j++) { // for each column 
			if ((i < 2 || i > 4)&& (j < 2 || j > 4)){
				arr[i][j] = 0; // Assign 0 to invalid spaces
				} else if ((i > 2 || i < 5) && (j > 2 || j < 5)){
					arr[i][j] = 1; // Assign 1 to valid spaces
				}
				arr[3][3] = 2;
                System.out.print(arr[i][j] + "\t");
			}//closing inner loop
            System.out.println("");
		}
		return arr;
	}
	

	public GUI()
	{
		setBackground( Color.WHITE );
		setDoubleBuffered( true );

        // Create a frame
        JFrame frame = new JFrame("Button Example");

		// Creating a panel to add and contain the buttons
		JPanel panel = new JPanel();

		// Set the PANEL's layout to null so it respects button.setBounds()
        panel.setLayout(null);
		
        // Set the frame size and layout
        frame.setSize(500, 500);
		ImageIcon icon = new ImageIcon("ball.png");

		
		for (int i = 0; i < 7; i++) { // for each row
		for (int j = 0; j < 7; j++) { // for each column 
			if ((i < 2 || i > 4)&& (j < 2 || j > 4)){
				
				// Add nothing, this are invalid spaces meant to be empty
				}  else if ((i == 3)&&(j == 3)){
					JButton button = new JButton(icon);
					button.setBounds(j * 55, i * 55, 50, 50);
					panel.add(button);

				}else if ((i > 2 || i < 5) && (j > 2 || j < 5)){
					JButton button = new JButton(icon);
					button.setBounds(j * 55, i * 55, 50, 50);					
					panel.add(button);

					
				}
			}//closing inner loop
            
		} 
        // Adding panel to frame
        frame.add(panel);
		 // Set the frame visibility to true
        frame.setVisible(true);
    }

  
    
@Override
public void paintComponent(Graphics g) {
         super.paintComponent( g );
		 
       //  Graphics2D g2 = (Graphics2D) g;


         // Reset your starting y value here 
        int[][] arr;
		// Initializing row and column sizes of 7x7
		arr = new int[7][7];
	// ENGLISH BOARD LAYOUT
	for (int i = 0; i < 7; i++) { // for each row
		for (int j = 0; j < 7; j++) { // for each column 
			if ((i < 2 || i > 4)&& (j < 2 || j > 4)){
				// g.setColor(Color.red);
				// g.fillOval(j*30, i*30, 10, 10);
				arr[i][j] = 0; // Assign 0 to invalid spaces
			
				}  else if ((i == 3)&&(j == 3)){
					g.setColor(Color.pink);
					g.fillOval(j*30, i*30, 10, 10);
					arr[i][j] = 2; // Assign 2 to free space in center

				}else if ((i > 2 || i < 5) && (j > 2 || j < 5)){
					g.setColor(Color.blue);
					g.fillOval(j*30, i*30, 10, 10);
					arr[i][j] = 1; // Assign 1 to valid spaces
					
				}
			}//closing inner loop
            
		} 

	
/* 
// HEXAGON BOARD LAYOUT
// https://dev.to/jitheshpoojari/mastering-c-programming-drawing-filled-patterns-with-loops-4def#filled-hexagon
int n = 5;

for (int i = 0; i <= n / 2; i++) {

    for (int j = n / 2 - i; j > 0; j--) {
        System.out.print(" ");
    }

    for (int j = 0; j < n + i; j++) {
        g.setColor(Color.pink);
        g.fillOval((n/2 - i + j) * 40, i * 40, 40, 40);
        System.out.print(" @");
    }

    System.out.println();
}

for (int i = n/2 - 1; i >= 0; i--) {

    for (int j = 0; j < n / 2 - i; j++) {
        System.out.print(" ");
    }

    for (int j = 0; j < n + i; j++) {
        g.setColor(Color.blue);
        g.fillOval((n/2 - i + j) * 40, (n - i - 1) * 40, 40, 40);
        System.out.print(" @");
    }

    System.out.println();
    */
}
}

	