public class Move {
    private final Position start, destination;
    // Constructors
    public Move(Position start, Position destination){
        this.start = start;
        this.destination = destination;
    }
    // Methods
    public boolean isVertical(){
        return getX1() == getX2();
    }
    public boolean isNorth(){
        return getY2 = getY1
    }
}
