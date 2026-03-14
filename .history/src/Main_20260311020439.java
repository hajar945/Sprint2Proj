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
	
	public static void main(String [] args)
	{
		new Main();
		GUI gui = new GUI();
		gui.boardlayout();
		gui.repaint();
		System.out.println(new File("C:\\Users\\hajar\\Downloads\\COMP-SCI 449 - Found of SE\\CS449_Sprint2_Hajar_Wilkes\\Sprint2Proj\\src\\image.ico").exists());
		ImageIO.read(File("C:\\\\Users\\\\hajar\\\\Downloads\\\\COMP-SCI 449 - Found of SE\\\\CS449_Sprint2_Hajar_Wilkes\\\\Sprint2Proj\\\\src\\\\image.ico"));
   
	}
}