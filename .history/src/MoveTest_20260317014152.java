import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest{
    
    @Test
    public void testValidHorizontalJump() {
        // Jumping from Column 1 to Column 3 on Row 3
        Move move = new Move(3, 1, 3, 3);
        assertTrue(move.isJump(), "A horizontal move of 2 spaces should be considered a jump.");
    }

    @Test
    public void testValidVerticalJump() {
        // Jumping from Row 5 to Row 3 on Column 3
        Move move = new Move(5, 3, 3, 3);
        assertTrue(move.isJump(), "A vertical move of 2 spaces should be considered a jump.");
    }

    @Test
    public void testInvalidDiagonalMove() {
        // Moving 1 space diagonally
        Move move = new Move(2, 2, 3, 3);
        assertFalse(move.isJump(), "Diagonal moves should fail the jump test in Peg Solitaire.");
    }

    @Test
    public void testPostMoveState(){
     // Test 2: Verify post-move state
        assertEquals(Game.EMPTY, game.pieceAt(fromRow, fromCol), "Source should be empty after move");
        assertEquals(Game.PEG, game.pieceAt(toRow, toCol), "Destination should contain a peg after move");
        assertEquals(Game.EMPTY, game.pieceAt(jumpRow, jumpCol), "Jumped peg should be removed after move");
    }
}