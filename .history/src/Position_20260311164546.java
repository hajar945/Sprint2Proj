// https://www.youtube.com/watch?v=gdCj_4HFBkY
public class Position {
    private int x, y;
    //Constructors
    public Position(char file, int rank){
        x = fileToInt(file);
        y = rankToInt(rank);
    }
    public Position(int arrayX, int arrayY){
        x = arrayX;
        y = arrayY;
    }

    // Helpers
    /* Translate the file of the algebraic move to its array equivalent.
     */
    private static int fileToInt(char file){
        if(!Character.isAlphabetic(file)){
            return -1;
        }
        // if rank is an ascii capital letter
        if (file>= 97)
            return file - 97; // A = 0, B = 1, etc.
        else
            return file - 65; // a = 0, b = 1, etc.
    }

    
}
