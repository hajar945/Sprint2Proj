import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// This class contains zero game logic. 
// It just listens for your clicks, 
// asks PegSolitaireGame if the move 
// is allowed, and then draws the resulting board on the screen.
public class UI extends JPanel implements ActionListener, MouseListener {

    private PegSolitaireGame game;
    private BoardPanel boardPanel;
    private JButton newGameButton;
    private JButton sizeButton;
    private JLabel message;

    private boolean gameInProgress;
    private int selectedRow, selectedCol;
    private PegSolitaireMove[] legalMoves;

    public PegSolitaireUI() {
        game = new PegSolitaireGame();
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(0, 150, 0));

        boardPanel = new BoardPanel();
        boardPanel.addMouseListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0, 150, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        newGameButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        sizeButton = new JButton("Change Size");
        sizeButton.addActionListener(this);
        sizeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(sizeButton);

        message = new JLabel("", JLabel.CENTER);
        message.setFont(new Font("Serif", Font.BOLD, 14));
        message.setForeground(Color.GREEN);
        message.setPreferredSize(new Dimension(0, 40));

        add(buttonPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        add(message, BorderLayout.SOUTH);

        doNewGame();
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == newGameButton) doNewGame();
        else if (src == sizeButton) doChangeSize();
    }

    private void doChangeSize() {
        String input = JOptionPane.showInputDialog(this, "Enter board size (odd numbers only, e.g., 7, 9):");
        if (input != null) {
            try {
                int newSize = Integer.parseInt(input);
                if (newSize < 3 || newSize % 2 == 0) {
                    JOptionPane.showMessageDialog(this, "Invalid! Size must be an odd number greater than 2.");
                } else {
                    game.setBoardSize(newSize);
                    gameInProgress = false;
                    doNewGame();
                    
                    Window window = SwingUtilities.getWindowAncestor(this);
                    if (window != null) {
                        window.pack();
                        window.setLocationRelativeTo(null);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }
    }

    private void doNewGame() {
        game.setUpGame();
        legalMoves = game.getLegalMoves();
        selectedRow = -1;
        message.setText("Make your move.");
        gameInProgress = true;
        repaint();
    }

    private void gameOver(String str) {
        message.setText(str);
        gameInProgress = false;
    }

    private void doClickSquare(int row, int col) {
        for (int i = 0; i < legalMoves.length; i++) {
            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                selectedRow = row;
                selectedCol = col;
                message.setText("Make your move.");
                repaint();
                return;
            }
        }

        if (selectedRow < 0) {
            message.setText("Click the piece you want to move.");
            return;
        }

        for (int i = 0; i < legalMoves.length; i++) {
            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol &&
                legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                doMakeMove(legalMoves[i]);
                return;
            }
        }

        message.setText("Click the square you want to move to.");
    }

    private void doMakeMove(PegSolitaireMove move) {
        game.makeMove(move);
        legalMoves = game.getLegalMoves();

        if (legalMoves == null) {
            int pegsRemaining = 0;
            int size = game.getBoardSize();
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (game.pieceAt(r, c) == PegSolitaireGame.PEG) pegsRemaining++;
                }
            }

            if (pegsRemaining == 1) {
                gameOver("You Win! Only 1 peg left!");
            } else {
                gameOver("Game Over! Pegs left: " + pegsRemaining);
            }
        } else {
            message.setText("Make your move.");
        }

        selectedRow = -1;
        repaint();
    }

    // --- INNER CLASS FOR PAINTING THE BOARD ---
    private class BoardPanel extends JPanel {
        public BoardPanel() {
            setBackground(new Color(0, 150, 0));
        }

        @Override
        public Dimension getPreferredSize() {
            if (game == null) return new Dimension(300, 300);
            int pixelSize = (game.getBoardSize() * 40) + 20;
            return new Dimension(pixelSize, pixelSize);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int size = game.getBoardSize();
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    int piece = game.pieceAt(row, col);
                    if (piece == PegSolitaireGame.INVALID) g.setColor(Color.DARK_GRAY);
                    else g.setColor(Color.LIGHT_GRAY);

                    g.fillRect(2 + col * 40, 2 + row * 40, 40, 40);

                    if (piece == PegSolitaireGame.PEG) {
                        g.setColor(Color.RED);
                        g.fillOval(6 + col * 40, 6 + row * 40, 32, 32);
                    } else if (piece == PegSolitaireGame.EMPTY) {
                        g.setColor(Color.BLACK);
                        g.fillOval(16 + col * 40, 16 + row * 40, 12, 12);
                    }
                }
            }

            if (gameInProgress && legalMoves != null) {
                g.setColor(Color.cyan);
                for (int i = 0; i < legalMoves.length; i++) {
                    g.drawRect(2 + legalMoves[i].fromCol * 40, 2 + legalMoves[i].fromRow * 40, 39, 39);
                    g.drawRect(3 + legalMoves[i].fromCol * 40, 3 + legalMoves[i].fromRow * 40, 37, 37);
                }
                if (selectedRow >= 0) {
                    g.setColor(Color.white);
                    g.drawRect(2 + selectedCol * 40, 2 + selectedRow * 40, 39, 39);
                    g.drawRect(3 + selectedCol * 40, 3 + selectedRow * 40, 37, 37);
                    g.setColor(Color.green);
                    for (int i = 0; i < legalMoves.length; i++) {
                        if (legalMoves[i].fromCol == selectedCol && legalMoves[i].fromRow == selectedRow) {
                            g.drawRect(2 + legalMoves[i].toCol * 40, 2 + legalMoves[i].toRow * 40, 39, 39);
                            g.drawRect(3 + legalMoves[i].toCol * 40, 3 + legalMoves[i].toRow * 40, 37, 37);
                        }
                    }
                }
            }
        }
    }

    public void mousePressed(MouseEvent evt) {
        if (!gameInProgress) {
            message.setText("Click \"New Game\" to start a new game.");
        } else {
            int col = (evt.getX() - 2) / 40;
            int row = (evt.getY() - 2) / 40;
            if (col >= 0 && col < game.getBoardSize() && row >= 0 && row < game.getBoardSize()) {
                doClickSquare(row, col);
            }
        }
    }
    public void mouseReleased(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
}