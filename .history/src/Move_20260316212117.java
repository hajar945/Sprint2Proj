public class Move {
    public int fromRow, fromCol;
    public int toRow, toCol;

    public Move(int r1, int c1, int r2, int c2) {
        fromRow = r1;
        fromCol = c1;
        toRow = r2;
        toCol = c2;
    }

    public boolean isJump() {
        return (Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2);
    }
}