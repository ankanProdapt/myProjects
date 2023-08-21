package chess;

public class Bishop extends Piece {
	
    public Bishop(String colour, int row, String column) {
        super("Bishop", colour, row, column);
    }
    

    @Override
    public boolean canMoveTo(int newRow, String newCol, Piece[][] chessBoard) {
        int currentRow = this.row;
        String currentCol = this.column;
        int currentColIndex = getCol(currentCol);
        int newColIndex = getCol(newCol);

        // Check if the new position is within the board bounds
        if (newRow <= 0 || newRow > 8) {
            return false;
        }
        
        
        // Check if the bishop is moving diagonally
        int rowDistance = Math.abs(newRow - currentRow);
        int colDistance = Math.abs(newColIndex - currentColIndex);
        
        if (rowDistance == colDistance) {
            int rowDirection = newRow > currentRow ? 1 : -1;
            int colDirection = newColIndex > currentColIndex ? 1 : -1;
            
            int r = currentRow + rowDirection;
            int c = currentColIndex + colDirection;
            
            if(chessBoard[r - 1][c] != null) {
            	return false;
            }

            while (r != newRow && c != newColIndex) {
            	r += rowDirection;
                c += colDirection;
                if (chessBoard[r - 1][c] != null) {
                    return false; // Path is blocked
                }
            }
            
            return true;
        }

 

        return false; // Invalid move
    }
    
}