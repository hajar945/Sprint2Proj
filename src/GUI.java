import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Arrays;

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
	}

@Override
public void paintComponent(Graphics g) {
         super.paintComponent( g );
		 
       //  Graphics2D g2 = (Graphics2D) g;


    int[][] arr = new int[7][7];

// ENGLISH BOARD LAYOUT
for (int i = 0; i < 7; i++) { // for each row
    for (int j = 0; j < 7; j++) { // for each column 
        
        // 1. The Invalid Corners
        if ((i < 2 || i > 4) && (j < 2 || j > 4)) {
            // We removed the Graphics (g.setColor and g.fillOval) here!
            // We just record it in the array secretly in the background.
            arr[i][j] = 0; 
            
        // 2. The Center Free Space
        } else if ((i == 3) && (j == 3)) {
            g.setColor(Color.pink);
            g.fillOval(j * 30, i * 30, 10, 10);
            arr[i][j] = 2; // 2 = empty hole
            
        // 3. The Valid Pegs
        } else {
            // Because we already filtered out the corners and the center,
            // everything else automatically falls in here.
            g.setColor(Color.blue);
            g.fillOval(j * 30, i * 30, 10, 10);
            arr[i][j] = 1; // 1 = peg
        }
        
    } // closing inner loop
}

// HEXAGON BOARD LAYOUT
// https://dev.to/jitheshpoojari/mastering-c-programming-drawing-filled-patterns-with-loops-4def#filled-hexagon
int n = 5; // Length of one side (and the top/bottom rows)
int size = 40; 
int halfSize = size / 2; 
int rowHeight = 35; // Slightly less than 'size' so they nest vertically

// Starting coordinates so it doesn't hug the absolute edge
int startX = 50; 
int startY = 50;

// Upper half (includes the middle row of 9)
for (int i = 0; i < n; i++) {
    int circlesInRow = n + i; 
    
    // Shifts left as the rows get wider
    int xOffset = (n - 1 - i) * halfSize; 
    
    for (int j = 0; j < circlesInRow; j++) {
        g.setColor(Color.pink);
        
        int x = startX + xOffset + (j * size);
        int y = startY + (i * rowHeight);
        
        g.fillOval(x, y, size, size);
    }
}

// Lower half
for (int i = n - 2; i >= 0; i--) {
    int circlesInRow = n + i; 
    
    // Shifts right as the rows get narrower
    int xOffset = (n - 1 - i) * halfSize; 
    
    // Calculates the actual Y row index. 
    // Total rows is (2 * n - 1). We subtract 'i' to mirror the top half.
    int rowNum = (2 * n - 2) - i; 
    
    for (int j = 0; j < circlesInRow; j++) {
        g.setColor(Color.blue);
        
        int x = startX + xOffset + (j * size);
        int y = startY + (rowNum * rowHeight);
        
        g.fillOval(x, y, size, size);
    }
}
}
}

	