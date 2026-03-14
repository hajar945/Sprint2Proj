import javax.swing.JButton;

public class GameState {
    private JButton newGameButton;  // Button for starting a new game.
    
    public void newGame(){
        JButton newGameButton = new JButton();
        newGameButton.setBounds(210, 60, 120, 30);
        add(newGameButton);
    }

}

