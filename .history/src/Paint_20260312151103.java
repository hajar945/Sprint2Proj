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
import java.awt.Graphics;

public class Paint extends JPanel{

    public void painting() {
        private JToggleButton[][] buttons = new JToggleButton[7][7];

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
    }
}
