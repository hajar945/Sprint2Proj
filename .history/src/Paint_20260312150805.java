import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Paint extends JPanel{

    
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
}
