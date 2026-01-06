
public class Scout extends Piece{
	
	public Scout(int initialRow, int initialCol, String team, String name) {
        super(initialRow, initialCol, team, "2"); 
    }

	
	@Override
    public boolean canMoveTo(int targetRow, int targetCol) {
		if (!this.isOnBoard()) return false;
    
		if (row != targetRow && col != targetCol) return false;
		
        if (row == targetRow && col == targetCol) return false;
        
        Board board = GameManager.board;
        
        if (row == targetRow) {
        	int step = (targetCol > col) ? 1 : -1;
        	for (int c = col + step; c != targetCol; c += step) {
                if (board.isLake(row, c) || board.isOccupied(row, c)) return false;
            }
        
        }
        else if (col == targetCol) { 
            int step = (targetRow > row) ? 1 : -1;
            for (int r = row + step; r != targetRow; r += step) {
                if (board.isLake(r, col) || board.isOccupied(r, col)) return false;
            }
        }
        
        
        return true;
	}
}
