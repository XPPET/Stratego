
public class Miner extends Piece{

	public Miner(int initialRow, int initialCol, String team, String name) {
		super(initialRow, initialCol, team, "3");
	}

	public Piece attack(Piece opponent) {
    	if(opponent.getName().equals("*"))
    		return opponent;
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
	
}
