
public class Spy extends Piece{

	public Spy(int initialRow, int initialCol, String team, String name) {
		super(initialRow, initialCol, team, "1");
	}

	public Piece attack(Piece opponent) {
    	if(opponent.getName().equals("*"))
    		return this;
    	
    	if(opponent.getName().equals("10")) {
    		return opponent;
    	}
    	
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
