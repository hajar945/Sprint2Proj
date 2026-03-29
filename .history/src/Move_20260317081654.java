public class Move {
    // Where the peg is starting
    public int fromRow, fromCol;
    // Where the peg is trying to go
    public int toRow, toCol;

    // Constructor that rankes in fromRow, fromCol, toRow, and toCol
    public Move(int r1, int c1, int r2, int c2) {
        fromRow = r1;
        fromCol = c1;
        toRow = r2;
        toCol = c2;
    }

    /
    public boolean isJump() {
        return (Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2);
    }
}