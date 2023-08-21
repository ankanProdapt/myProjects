package chess;

public class Piece {

    public String name;
    public String colour;
    public int row;
    public String column;

    public Piece(String name, String colour, int row, String column){
        this.name = name;
        this.colour = colour;
        this.row = row;
        this.column = column;
    }
    
    // This Method must be overridden in child class
    public boolean canMoveTo(int destinationRow, String destinationCol, Piece[][] chessBoard) {
    	throw new UnsupportedOperationException();
    }
    
    
    public boolean canCapturePieceAt(int destinationRow, String destinationCol, Piece[][] chessBoard) {
    	
    	int newColIndex = getCol(destinationCol);
    	Piece temp = chessBoard[destinationRow - 1][newColIndex];
    	
    	if(temp == null || temp.colour.equals(colour)) {
    		return false;
    	}
    	
    	chessBoard[destinationRow - 1][newColIndex] = null; 
    	if(canMoveTo(destinationRow, destinationCol, chessBoard)) {
    		return true;
    	}
    	else {
    		chessBoard[destinationRow - 1][newColIndex] = temp;
    		return false;
    	}
    }
    
    public int getCol() {
    	return ChessBoard.columns.indexOf(column);
    }
    
    public static int getCol(String col) {
    	return ChessBoard.columns.indexOf(col);
    }

    @Override
    public String toString() {
        return (this.name).substring(0,1)+"("+(this.colour).substring(0,1)+")";
    }
}