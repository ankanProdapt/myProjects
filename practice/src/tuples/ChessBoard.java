package tuples;

import java.util.Arrays;
import java.util.List;

public class ChessBoard {
	public static List<String> columns = Arrays.asList(new String[] {"a", "b", "c", "d", "e", "f", "g", "h"});
	public Piece[][] chessBoard = new Piece[8][8];
	
	
	public ChessBoard() {
		for(int i = 0; i < 8; i++) {
			Piece p1 = new Pawn("white", 2, columns.get(i));
			Piece p2 = new Pawn("black", 7, columns.get(i));
			
			addToBoard(p1, p2);
		}
		
	}
	
	public void addToBoard(Piece... pieces) {
		for(Piece p: pieces) {
			int row = p.row - 1;
			int col = columns.indexOf(p.column);
			chessBoard[row][col] = p;
		}
	}
	
	public String getPieceName(String command) {
		switch (command.charAt(0)) {
		case 'K':
			return "King";
		case 'Q':
			return "Queen";
		case 'R':
			return "Rook";
		case 'B':
			return "Bishop";
		case 'N':
			return "Knight";
		default:
			return "Pawn";
		}
	}
	
	public int getRow(String command) {
		int l = command.length();
		return Integer.valueOf(command.substring(l - 1));
	}
	
	public String getCol(String command) {
		int l = command.length();
		return command.substring(l - 2, l - 1);
	}
	
	public void executeMove(String colour, String command) throws Exception{
		String pieceName = getPieceName(command);
		
		if(command.contains("x")) {
			executeCapture(colour, command);
			return;
		}
		
		String[] strs = command.split("-");
		int sourceRow = getRow(strs[0]);
		int destinationRow = getRow(strs[1]);
		
		String sourceCol = getCol(strs[0]);
		String destinationCol = getCol(strs[1]);
		
		int sourceColInd = columns.indexOf(sourceCol);
		int destinationColInd = columns.indexOf(destinationCol);
		Piece p = chessBoard[sourceRow - 1][sourceColInd];
		
		if(p != null && p.name.equals(pieceName) && colour.equals(p.colour)) {
			if(p.canMoveTo(destinationRow, destinationCol, chessBoard)) {
				p.row = destinationRow;
				p.column = destinationCol;
				chessBoard[sourceRow - 1][sourceColInd] = null;
				chessBoard[destinationRow - 1][destinationColInd] = p;
				
				
			}
			else {
				System.out.println("Invalid Move");
				throw new Exception("Invalid Move " + command);
			}
		}
		else {
			System.out.println("Invalid Move");
			throw new Exception("Invalid Move" + command);
		}
		
	}
	
	public Piece getKing(String colour) {
		for(int i = 0; i < 8; i++) {
			for(Piece p: chessBoard[i]) {
				if(p != null && p.name.equals("King") && p.colour.equals(colour)) {
					return p;
				}
			}
		}
		return null;
	}
	
	
	public void executeCapture(String colour, String command) throws Exception{
		String pieceName = getPieceName(command);
		
		String[] strs = command.split("x");
		int sourceRow = getRow(strs[0]);
		int destinationRow = getRow(strs[1]);
		
		String sourceCol = getCol(strs[0]);
		String destinationCol = getCol(strs[1]);
		
		int sourceColInd = columns.indexOf(sourceCol);
		int destinationColInd = columns.indexOf(destinationCol);
		Piece p = chessBoard[sourceRow - 1][sourceColInd];
		
		
		if(p != null && p.name.equals(pieceName) && colour.equals(p.colour)) {
			if(p.canCapturePieceAt(destinationRow, destinationCol, chessBoard)) {
				p.row = destinationRow;
				p.column = destinationCol;
				chessBoard[sourceRow - 1][sourceColInd] = null;
				chessBoard[destinationRow - 1][destinationColInd] = p;
			}
			else {
				System.out.println("Invalid Move");
				throw new Exception("Invalid Move " + command);
			}
		}
		else {
			System.out.println("Invalid Move");
			throw new Exception("Invalid Move" + command);
		}
		
	}
	
	
	@Override
	public String toString() {
		String result = "";
		result += "    -----------------------------------------\n";
		for(int i = 7; i >= 0; i--) {
			result += "" + (i + 1) + "   |";
			for(int j = 0; j < 8; j++) {
				if(chessBoard[i][j] == null) {
					result += "    |";
				}
				else {
					result += chessBoard[i][j] + "|";
				}
			}
			result += "\n";
		}
		result += "    -----------------------------------------\n";
		result += "      ";
		for(String col: columns) {
			result += col + "    ";
		}
		result += "\n";
		
		return result;
	}
}
