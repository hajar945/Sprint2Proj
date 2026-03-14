import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.*;

public class Main extends JFrame
{
	
	
	public Main()
	{
		
		add( new GUI() );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setTitle( "Cells" );
		setSize( 750,550 );
		setVisible( true );
		setLocationRelativeTo( null );
		setResizable( false );
	}
	
	   // A main method so you can test the GUI standalone
    public static void main(String[] args) {
        JFrame frame = new JFrame("Peg Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 480);
        
        GUI gameBoard = new GUI();
        frame.add(gameBoard);
        
        frame.setLocationRelativeTo(null); // Centers the window on your screen
        frame.setVisible(true);
    }
}