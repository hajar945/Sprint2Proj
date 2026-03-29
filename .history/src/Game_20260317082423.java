import java.util.ArrayList;
// This class contains the logic, no graphics. 
// It strictly handles the rules, the board array, 
// and calculating what jumps are legal
public class Game {

    // Labels for game states
    public static final int EMPTY = 0, PEG = 1, INVALID = 2;

    // 2D array for the game's board
    private int[][] board;
    // boardSize is 7 by default, but can be modified by player
    private int boardSize = 7;

    public Game() {
        setUpGame();
    }

    public void setBoardSize(int newSize) {
        this.boardSize = newSize;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    // Creates a cross-shaped board. Size based on whatever the boardSize is
    public void setUpGame() {
        board = new int[boardSize][boardSize];
        int cornerSize = boardSize / 3; // The INVALID spaces in the corners
        int center = boardSize / 2; // The center of the board

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                boolean isCorner = 
                // Is this square in the top rows OR the bottom rows?
                (row < cornerSize || row >= boardSize - cornerSize) &&
                // Is this square in the far-left columns OR the far-right columns?
                (col < cornerSize || col >= boardSize - cornerSize);
                // Check if the current sqaure is the center
                boolean isCenter = (row == center && col == center);

                // Assign the correct state to the current sqaure
                if (isCorner) {
                    board[row][col] = INVALID;
                } else if (isCenter) {
                    board[row][col] = EMPTY; // Center sq
                } else {
                    board[row][col] = PEG;
                }
            }
        }
    }

    public int pieceAt(int row, int col) {
        return board[row][col];
    }

    public void makeMove(Move move) {
        makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }

    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        board[toRow][toCol] = board[fromRow][fromCol];
        board[fromRow][fromCol] = EMPTY;

        int jumpRow = (fromRow + toRow) / 2;
        int jumpCol = (fromCol + toCol) / 2;
        board[jumpRow][jumpCol] = EMPTY;
    }

    public Move[] getLegalMoves() {
        ArrayList<Move> moves = new ArrayList<>();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board[row][col] == PEG) {
                    if (canJump(row, col, row - 1, col, row - 2, col)) moves.add(new Move(row, col, row - 2, col));
                    if (canJump(row, col, row + 1, col, row + 2, col)) moves.add(new Move(row, col, row + 2, col));
                    if (canJump(row, col, row, col - 1, row, col - 2)) moves.add(new Move(row, col, row, col - 2));
                    if (canJump(row, col, row, col + 1, row, col + 2)) moves.add(new Move(row, col, row, col + 2));
                }
            }
        }

        if (moves.isEmpty()) return null;

        Move[] moveArray = new Move[moves.size()];
        return moves.toArray(moveArray);
    }

    private boolean canJump(int r1, int c1, int r2, int c2, int r3, int c3) {
        if (r3 < 0 || r3 >= boardSize || c3 < 0 || c3 >= boardSize) return false;
        if (board[r3][c3] != EMPTY) return false;
        if (board[r2][c2] != PEG) return false;
        return true;
    }
}