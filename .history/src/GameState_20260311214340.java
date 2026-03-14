import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class GameState {
    private GameState   prev, future;
    private Move        lastMove;
    private Piece[][]   squares;

    // CONSTRUCTORS
    // Default 7x7 for the standard English Peg Solitaire cross board
    public GameState() {
        this(7, 7); 
    }

    public GameState(int boardWidth, int boardHeight) {
        squares = new Piece[boardWidth][boardHeight];
    }

    public GameState(int boardWidth, int boardHeight, String arrangement, Piece[] pieces) {
        this(boardWidth, boardHeight);
        HashMap<String, Piece> symbols = new HashMap<>();
        for (Piece piece : pieces) {
            symbols.put(piece.toString(), piece);
        }
        symbols.put(" ", null); // Space for empty holes

        int pointer = 0;
        for (int y = boardHeight - 1; y >= 0; y--) {
            for (int x = 0; x < boardWidth; x++) {
                if (pointer >= arrangement.length()) break;
                
                String symbol = arrangement.charAt(pointer++) + "";
                while (!symbols.containsKey(symbol) && pointer < arrangement.length()) {
                    symbol = arrangement.charAt(pointer++) + "";
                }
                setPiece(new Position(x, y), symbols.get(symbol));
            }
        }
    }

    // METHODS
    public boolean isMoveLegal(Move move) {
        // 1. Is the move within the bounds of the board?
        for (int coordinate : new int[]{move.getX1(), move.getY1(), move.getX2(), move.getY2()}) {
            if (coordinate < 0 || coordinate >= getBoardWidth())
                return false;
        }

        Piece startPiece = getPiece(move.getStart());
        Piece endPiece = getPiece(move.getEnd());
        
        // 2. Must start with a peg and end in an empty hole
        if (startPiece == null || endPiece != null) {
            return false;
        }

        // 3. Must be exactly 2 spaces away orthogonally (no diagonal jumps in standard peg solitaire)
        int dx = Math.abs(move.getX1() - move.getX2());
        int dy = Math.abs(move.getY1() - move.getY2());
        if (!((dx == 2 && dy == 0) || (dx == 0 && dy == 2))) {
            return false;
        }

        // 4. Must jump over a peg
        int midX = (move.getX1() + move.getX2()) / 2;
        int midY = (move.getY1() + move.getY2()) / 2;
        Piece jumpedPiece = getPiece(new Position(midX, midY));
        
        return jumpedPiece != null; // Legal if there is a peg to jump over
    }

    public void makeMove(Move move) {
        if (!isMoveLegal(move)) return;

        // Move the peg to the new hole
        setPiece(move.getEnd(), getPiece(move.getStart()));
        setPiece(move.getStart(), null); // Empty the start hole

        // Remove the jumped peg
        int midX = (move.getX1() + move.getX2()) / 2;
        int midY = (move.getY1() + move.getY2()) / 2;
        setPiece(new Position(midX, midY), null);

        setLastMove(move);
    }

    public abstract void postMoveUINotifications(UserInterface ui);

    public GameState copy() {
        GameState copy = null;
        try {
            copy = this.getClass().getDeclaredConstructor().newInstance();
            copy.prev = prev;
            copy.future = future;
            copy.lastMove = lastMove;
            for (int x = 0; x < squares.length; x++) {
                for (int y = 0; y < squares[x].length; y++) {
                    copy.squares[x][y] = squares[x][y];
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + this.getClass() + " must have a no-arg constructor for copy() to work.");
            System.exit(1);
        }
        return copy;
    }

    public boolean isTerminal() {
        // The game is over if there are no more legal successors/moves
        return getSuccessors().isEmpty();
    }

    public double evaluate() {
        // In Peg Solitaire, a lower score is better. A score of 1 means you won.
        int pegsRemaining = 0;
        for (Position position : getAllPossiblePositions()) {
            if (getPiece(position) != null) {
                pegsRemaining++;
            }
        }
        return pegsRemaining;
    }

    public ArrayList<GameState> getSuccessors() {
        ArrayList<GameState> successors = new ArrayList<>();
        // In Peg Solitaire, standard moves are just 2 spaces orthogonally.
        int[][] directions = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};

        for (Position pos : getAllPossiblePositions()) {
            if (getPiece(pos) != null) { // If there's a peg here
                for (int[] dir : directions) {
                    Move potentialMove = new Move(pos, new Position(pos.getX() + dir[0], pos.getY() + dir[1]));
                    if (isMoveLegal(potentialMove)) {
                        GameState nextState = this.copy();
                        nextState.makeMove(potentialMove);
                        successors.add(nextState);
                    }
                }
            }
        }
        return successors;
    }

    public ArrayList<Position> getAllPossiblePositions() {
        ArrayList<Position> positions = new ArrayList<>(getBoardWidth() * getBoardHeight());
        for (int x = 0; x < getBoardWidth(); x++) {
            for (int y = 0; y < getBoardHeight(); y++) {
                positions.add(new Position(x, y));
            }
        }
        return positions;
    }

    public boolean equals(GameState other) {
        return Arrays.deepEquals(squares, other.squares);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = getBoardHeight() - 1; y >= 0; y--) {
            sb.append(String.format("%2d|", y + 1));
            for (int x = 0; x < getBoardWidth(); x++) {
                if (squares[x][y] != null) {
                    sb.append(squares[x][y] + "|");
                } else {
                    sb.append(" |");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // GETTERS & SETTERS
    public GameState getPrev() { return prev; }
    public void setPrev(GameState prev) { this.prev = prev; }
    
    public GameState getFuture() { return future; }
    public void setFuture(GameState future) { this.future = future; }
    
    public Move getLastMove() { return lastMove; }
    public void setLastMove(Move lastMove) { this.lastMove = lastMove; }
    
    public Piece getPiece(Position position) { return squares[position.getX()][position.getY()]; }
    public void setPiece(Position position, Piece piece) { squares[position.getX()][position.getY()] = piece; }
    
    public int getBoardWidth() { return squares.length; }
    public int getBoardHeight() { return squares[0].length; }
}