
public class Flag extends Piece{

	public Flag(int initialRow, int initialCol, String team, String name) {
        super(initialRow, initialCol, team, "#"); 
    }

	
	@Override
    public boolean canMoveTo(int targetRow, int targetCol) {
		return false;
	}
	
}
