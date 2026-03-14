import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GUI extends JPanel {
    // Storing the buttons in a 2D array so we can update them later
    private JToggleButton[][] buttons = new JToggleButton[7][7];

    public GUI() {
        setLayout(null); // Keeping your null layout for precise bounds
        setBackground(Color.WHITE);

        // Define ActionListener
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                // Printing the button's name to the console to verify clicks
                System.out.println("Clicked position: " + abstractButton.getName());
            }
        };

        // Build the English Cross Board
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                // The 4 corners of the board are 2x2 empty spaces
                boolean isCorner = (row < 2 || row > 4) && (col < 2 || col > 4);
                boolean isCenter = (row == 3 && col == 3);

                // If it's not a corner, it gets a button
                if (!isCorner) {
                    JToggleButton button = new JToggleButton();
                    button.addActionListener(actionListener);
                    
                    // Added a 25px offset so the board isn't squished against the window edge
                    button.setBounds(col * 55 + 25, row * 55 + 25, 50, 50);
                    
                    // Store the coordinates in the button's name so the ActionListener knows which one was clicked
                    button.setName(col + "," + row);
                    button.setOpaque(true); // Required for background colors to show on some operating systems

                    if (isCenter) {
                        button.setSelected(false);
                        button.setBackground(Color.PINK); // Center hole is empty
                        button.setText("O");
                    } else {
                        button.setSelected(true);
                        button.setBackground(Color.CYAN); // Every other valid space has a peg
                        button.setText("X");
                    }

                    buttons[col][row] = button; // Save to our array
                    add(button); // Add to the JPanel
                }
            }
        }
    }

 
}