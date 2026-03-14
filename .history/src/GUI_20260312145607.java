import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class GUI extends JPanel {
    private JToggleButton[][] buttons = new JToggleButton[7][7];

    public GUI() {
        setLayout(null); 
        setBackground(Color.WHITE);

        // --- RADIO BUTTON MENU ---
		JLabel label = new JLabel("Choose a Board Type");
		label.setBounds(10,25, 130, 30);
        JRadioButton englishRadio = new JRadioButton("English Board");
        englishRadio.setBounds(10, 50, 130, 30); // Placed on the far left
        englishRadio.setBackground(Color.WHITE);
        englishRadio.setSelected(true); // Default selection

        JRadioButton hexagonRadio = new JRadioButton("Hexagon Board");
        hexagonRadio.setBounds(10, 75, 130, 30); // Placed just below the English button
        hexagonRadio.setBackground(Color.WHITE);
        hexagonRadio.setEnabled(false); // Greys out the button since it isn't implemented yet
        hexagonRadio.setToolTipText("Coming soon!");

        // Group them so clicking one deselects the other
        ButtonGroup boardShapeGroup = new ButtonGroup();
        boardShapeGroup.add(englishRadio);
        boardShapeGroup.add(hexagonRadio);

		// Add label to the panel
		add(label);
        // Add the buttons to the panel
        add(englishRadio);
        add(hexagonRadio);

        //GAME STATE BUTTON
        JButton newGameButton = new JButton("New Game");
        newGameButton.setBounds(480, 370, 120, 30);
        add(newGameButton);
        // -------------------------

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                System.out.println("Clicked position: " + abstractButton.getName());
            }
        };

        // Build the English Cross Board
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                boolean isCorner = (row < 2 || row > 4) && (col < 2 || col > 4);
                boolean isCenter = (row == 3 && col == 3);

                if (!isCorner) {
                    JToggleButton button = new JToggleButton();
                    button.addActionListener(actionListener);
                    
                    button.setBounds(col * 55 + 150, row * 55 + 25, 50, 50);
                    
                    button.setName(col + "," + row);
                    button.setOpaque(true); 

                    if (isCenter) {
                        button.setSelected(false);
                        button.setBackground(Color.PINK); 
                        button.setText("O");
                    } else {
                        button.setSelected(true);
                        button.setBackground(Color.CYAN); 
                        button.setText("X");
                    }

                    buttons[col][row] = button; 
                    add(button); 
                }
            }
        }
        // https://courses.ics.hawaii.edu/ics111f16/javanotes7.0.1-web-site/source/chapter7/Checkers.java
  private static class CheckersMove {
        int fromRow, fromCol;  // Position of piece to be moved.
        int toRow, toCol;      // Square it is to move to.
        CheckersMove(int r1, int c1, int r2, int c2) {
                // Constructor.  Just set the values of the instance variables.
            fromRow = r1;
            fromCol = c1;
            toRow = r2;
            toCol = c2;
        }
        boolean isJump() {
                // Test whether this move is a jump.  It is assumed that
                // the move is legal.  In a jump, the piece moves two
                // rows.  (In a regular move, it only moves one row.)
            return (fromRow - toRow == 2 || fromRow - toRow == -2);
        }
    }
        
	}
    
}