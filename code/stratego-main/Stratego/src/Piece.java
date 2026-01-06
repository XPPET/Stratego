public class Piece {
    protected int row;
    protected int col;
    protected final int initialRow;
    protected final int initialCol;
    protected boolean isOnBoard;
    protected String team; // "Red" or "Blue"
    protected String name;

    public Piece(int initialRow, int initialCol, String team, String name) {
        this.initialRow = initialRow;
        this.initialCol = initialCol;
        this.row = initialRow;
        this.col = initialCol;
        this.team = team;
        this.isOnBoard = false;
        this.name = name;
    }

    public String getName() {
		return name;
	}

	public void placeOnBoard(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOnBoard = true;
    }

    public void sendToGraveyard(Graveyard graveyard) {
        this.row = initialRow;
        this.col = initialCol;
        this.isOnBoard = false;
        
        graveyard.getPieces().add(this);
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean isOnBoard() { return isOnBoard; }
    public String getTeam() { return team; }

 
    public boolean canMoveTo(int targetRow, int targetCol) {
    	if (!isOnBoard) return false;
    	
    	if (row == targetRow && col == targetCol) return false;

    	if (targetRow < 0 || targetRow >= 10 || targetCol < 0 || targetCol >= 10)
            return false;
    	
    	int rowDiff = Math.abs(targetRow - row);
        int colDiff = Math.abs(targetCol - col);
        if (!((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)))
            return false;

        
        return true;
    }
    
    public void moveTo(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    
    public Piece attack(Piece opponent) {
    	if(opponent.getName().equals("#")) {
    		GameManager.gameEnded = true;
    		return opponent;
    	}
    	if(opponent.getName().equals("*"))
    		return this;
    	if(Integer.parseInt(opponent.getName()) ==  Integer.parseInt(this.getName())) {
    		return null;
    	}
    	else if(Integer.parseInt(opponent.getName()) <  Integer.parseInt(this.getName())) {
    		return opponent;
    	}
    	else {
    		return this;
    	}
    }
    
    public boolean canMoveAnywhere(Board board) {
        if (!this.isOnBoard()) return false;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; 

        for (int[] dir : directions) {
            int newRow = this.row + dir[0];
            int newCol = this.col + dir[1];

            if (newRow < 0 || newRow >= 10 || newCol < 0 || newCol >= 10)
                continue;

            if (board.isLake(newRow, newCol))
                continue;

            Piece target = board.getPieceAt(newRow, newCol);
            if (target == null || !target.getTeam().equals(this.team)) {
                if (this.canMoveTo(newRow, newCol))
                    return true;
            }
        }

        return false;
    }
}

