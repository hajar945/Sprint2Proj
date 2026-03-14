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
private JToggleButton[][] buttons = new JToggleButton[7][7];

    public painting(){
            public void paintComponent(Grapics g) {
                /* Turn on antialiasing to get nicer ovals. */
                
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                setLayout(null); 
                setBackground(Color.WHITE);

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
                    if ( row % 2 == col % 2 )
                        g.setColor(Color.LIGHT_GRAY);
                        else
                            g.setColor(Color.GRAY);
                        g.fillRect(2 + col*20, 2 + row*20, 20, 20);

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
}
