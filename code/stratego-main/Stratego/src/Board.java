import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JPanel;

public class Board extends JPanel{
	

	private int xCoord = 0;
	private int yCoord = 0;
	
	private int sqSize;
	
	 private Graveyard bgraveyard;
	 private Graveyard rgraveyard;


	char[] alphabet = "ABCDEFGHIJ".toCharArray();          //Char list με τα γράμματα που χρειάζονται
	
	
	private ArrayList<Piece> pieces = new ArrayList<>(); 
	
	private boolean isWaiting = false;
	
	
	private final Set<Point> lakeCells = Set.of(
	        new Point(4, 2), new Point(4, 3),
	        new Point(5, 2), new Point(5, 3),
	        new Point(4, 6), new Point(4, 7),
	        new Point(5, 6), new Point(5, 7)
	    ); 
	
	private BoardListener listener;

	public void setBoardListener(BoardListener listener) {
	    this.listener = listener;
	}
	
	
	public boolean isLake(int row, int col) {
        return lakeCells.contains(new Point(row, col));
    }
	
	public boolean isValidPlacement(int row, String team) {
	    if (team.equals("Red")) {
	        return row >= 6 && row <= 9;
	    } else {
	        return row >= 0 && row <= 3;
	    }
	}
	
	public boolean isOccupied(int row, int col) {
	    for (Piece p : pieces) {
	        if ((p.isOnBoard() && p.getRow() == row && p.getCol() == col) ) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean allPiecesPlaced(String team) {
	    ArrayList<Piece> playerPieces = team.equals("Red") ? rgraveyard.getPieces() : bgraveyard.getPieces();
	    return playerPieces.isEmpty(); 
	}
	
	public boolean hasAnyLegalMove(String team) {
	    for (Piece p : pieces) {
	        if (!p.isOnBoard() || !p.getTeam().equals(team)) continue;
	        int r = p.getRow();
	        int c = p.getCol();
	        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	        for (int[] d : dirs) {
	            int nr = r + d[0], nc = c + d[1];
	            if (nr < 0 || nr > 9 || nc < 0 || nc > 9) continue;
	            if (isLake(nr, nc)) continue;
	            if (!p.canMoveTo(nr, nc)) continue;
	            Piece other = getPieceAt(nr, nc);
	            if (other != null && other.getTeam().equals(team)) continue;
	            return true;
	        }
	    }
	    return false;
	}

	public boolean areFlagsProtectedAndNoMiners() {
	    boolean redFlagProtected = isFlagProtected("Red");
	    boolean blueFlagProtected = isFlagProtected("Blue");

	    boolean redHasMiner = teamHasMovableMiner("Red");
	    boolean blueHasMiner = teamHasMovableMiner("Blue");

	    return redFlagProtected && blueFlagProtected && (!redHasMiner && !blueHasMiner);
	}
	
	public boolean isFlagProtected(String team) {
	    for (Piece p : pieces) {
	        if (p instanceof Flag && p.getTeam().equals(team)) {
	            int row = p.getRow();
	            int col = p.getCol();
	            for (int[] dir : new int[][]{{-1,0},{1,0},{0,-1},{0,1}}) {
	                Piece adj = getPieceAt(row + dir[0], col + dir[1]);
	                if (!(adj instanceof Bomb)) return false;
	            }
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean teamHasMovableMiner(String team) {
	    for (Piece p : pieces) {
	        if (p.getTeam().equals(team) && p.getName().equals("3") && p.isOnBoard()) {
	            if (p.canMoveAnywhere(this)) return true;
	        }
	    }
	    return false;
	}
	
	public Board( Graveyard bGraveyard, Graveyard rGraveyard) {

		bgraveyard = bGraveyard;
		rgraveyard = rGraveyard; 
       

		addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int sqSizeLocal = Math.min(getWidth() / 10, getHeight() / 10);
		        int col = e.getX() / sqSizeLocal;
		        int row = e.getY() / sqSizeLocal;

		        boolean fight = false;
		     
		        if (GameManager.selectedPiece != null) {
		        	if (isWaiting) return;
		        	
		        	if (!GameManager.selectedPiece.getTeam().equals(GameManager.currentPlayer.getTeam())) return;
		            if (isLake(row, col)) return;
		            if (isOccupied(row, col)) {
		         		Piece clickedPiece = getPieceAt(row, col);
		                if (clickedPiece != null && clickedPiece.getTeam().equals(GameManager.currentPlayer.getTeam())) {
		                    GameManager.selectedPiece = clickedPiece;
		                    repaint();
		                    return;
		                }
		                else {
		                	fight = true;
		                }
	            	}
		            	         	
		            if(GameManager.isSetupPhase) {
		            	if (!isValidPlacement(row, GameManager.selectedPiece.getTeam())) return;            	

			            GameManager.selectedPiece.placeOnBoard(row, col);
			            bgraveyard.getPieces().remove(GameManager.selectedPiece);
			            rgraveyard.getPieces().remove(GameManager.selectedPiece);
			            getPieces().add(GameManager.selectedPiece);

		            }
		            else {                
		            	if (!GameManager.selectedPiece.canMoveTo(row, col)) return;
		            	
		            	if(fight) {
		            		Piece clickedPiece = getPieceAt(row, col);
		            		Piece defeated = GameManager.selectedPiece.attack(clickedPiece);
		            		
		            		if(defeated == null) {
		            			if(GameManager.selectedPiece.getTeam().equals("Blue")) {
			            			GameManager.selectedPiece.sendToGraveyard(bGraveyard);
			            			clickedPiece.sendToGraveyard(rGraveyard);
		            			}
			            		else {
			            			GameManager.selectedPiece.sendToGraveyard(rGraveyard);
			            			clickedPiece.sendToGraveyard(bGraveyard);
			            		}
		            		}
		            		else {
		            			if(defeated.equals(clickedPiece))
	            					GameManager.selectedPiece.moveTo(row, col);
		            			if(defeated.getTeam().equals("Blue"))
		            				defeated.sendToGraveyard(bGraveyard);
		            			else {
		            				defeated.sendToGraveyard(rGraveyard);
		            			}
		            		}
		            	}
		            	else {
		            		GameManager.selectedPiece.moveTo(row, col);
		            	}
		            	isWaiting = true;
		            	
		            	new javax.swing.Timer(5000, evt -> {
			                isWaiting = false;

			                if (listener != null) {
			                    listener.onPreparePageRequested(); 
			                }
			                
			                ((javax.swing.Timer) evt.getSource()).stop(); 
			            }).start();
		            }
		            
		            rgraveyard.repaint();
		            bgraveyard.repaint();
		            repaint();
		            GameManager.selectedPiece = null;
		            
		            
		        } else {
		            for (Piece p : pieces) {
		                if (p.isOnBoard() && p.getRow() == row && p.getCol() == col) {
		                	if (!p.getTeam().equals(GameManager.currentPlayer.getTeam())) return;

		                    GameManager.selectedPiece = p;
		                    repaint();
		                    bgraveyard.repaint();
		                    rgraveyard.repaint();
		                    break;
		                }
		            }
		        }
		    }
		});
    }
	
	public Piece getPieceAt(int row, int col) {
	    for (Piece p : pieces) {
	        if (p.isOnBoard() && p.getRow() == row && p.getCol() == col) {
	            return p;
	        }
	    }
	    return null; 
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}


	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		
		int sqSize1 = this.getWidth()/10;
		int sqSize2 = this.getHeight()/10;
		
		sqSize = sqSize1;
		if(sqSize2 < sqSize1)
			sqSize = sqSize2;
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				int x = j * sqSize;
				int y = i * sqSize;
				if( ( (i == 4 || i ==5 ) && (j == 2 || j == 3 ) ) || (i == 4 || i ==5 ) && ( j == 6 || j == 7) ) {          //Λίμνες
					g.setColor(new Color(0x85B6CE));                                                                         
					g.fillRect(x, y, sqSize, sqSize);                                                                       
				}
				else {                                                                                                      //Όλα τα κελιά εκτός των λιμνών
					g.setColor(new Color(0xB69F66));
					g.fillRect(x, y, sqSize, sqSize);
					
					g.setColor(Color.black);                   //Γραμμές
					g.drawRect(x, y, sqSize, sqSize);
				
					
					
	                
					if(x == 0) {                                   
						g.setColor(Color.BLACK);
				        g.setFont(new Font("Arial", Font.PLAIN, sqSize / 3));
				        g.drawString(Integer.toString(10 - i), x , y + 100);                       //Αρίθμηση των γραμμών στην πρώτη στήλη
					}
				}
				
			}
		}
		
                
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, sqSize / 3));
		for(int i=0; i<10; i++) {                          //Αρίθμηση των στηλών στην 1η γραμμή
			g.drawString(Character.toString(alphabet[i]), (i*sqSize)+(sqSize-25), (sqSize*10)+1);        
		}
		
		
		for (Piece p : pieces) {
		    if (!p.isOnBoard()) continue;

		    int pieceX = p.getCol() * sqSize + sqSize / 8;
		    int pieceY = p.getRow() * sqSize + sqSize / 8;
		    int diameter = sqSize * 3 / 4;

		    g.setColor(p.getTeam().equals("Red") ? Color.RED : Color.BLUE);
		    g.fillOval(pieceX, pieceY, diameter, diameter);

		    String rank = p.getName();
		    Font font = new Font("Arial", Font.BOLD, diameter / 2);
		    g.setFont(font);

		    int stringWidth = g.getFontMetrics().stringWidth(rank);
		    int stringHeight = g.getFontMetrics().getAscent();
		    int textX = pieceX + (diameter - stringWidth) / 2;
		    int textY = pieceY + (diameter + stringHeight) / 2 - 4;

		    g.setColor(Color.WHITE);
		    g.drawString(rank, textX, textY);
		    
		    if (GameManager.selectedPiece == p) {
                g.setColor(Color.GREEN);
                g.drawOval(pieceX - 2, pieceY - 2, diameter + 4, diameter + 4);
            }
		}
		
		
		
		
	}
	
	public void setXY(int x, int y) {
		xCoord = x - sqSize/2;
		yCoord = y - sqSize/2;
	}
	

}