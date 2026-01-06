import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Graveyard extends JPanel{

	
	private ArrayList<Piece> pieces = new ArrayList<>(); 
	
	
	
	
	public Graveyard(String colour) {
		if(colour.equals("Blue")) {
			Piece Bf = new Flag(0, 0, "Blue", null);
		    Piece Bb1 = new Bomb(0, 1, "Blue", null);
		    Piece Bb2 = new Bomb(0, 2, "Blue", null);
		    Piece Bb3 = new Bomb(0, 3, "Blue", null);
		    Piece Bb4 = new Bomb(0, 4, "Blue", null);
		    Piece Bb5 = new Bomb(0, 5, "Blue", null);
		    Piece Bb6 = new Bomb(0, 6, "Blue", null);
		    Piece Bspy = new Spy(0, 7, "Blue", null);
		    Scout B21 = new Scout(0, 8, "Blue", null);
		    Scout B22 = new Scout(0, 9, "Blue", null);
		    Scout B23 = new Scout(1, 0, "Blue", null);
		    Scout B24 = new Scout(1, 1, "Blue", null);
		    Scout B25 = new Scout(1, 2, "Blue", null);
		    Scout B26 = new Scout(1, 3, "Blue", null);
		    Scout B27 = new Scout(1, 4, "Blue", null);
		    Scout B28 = new Scout(1, 5, "Blue", null);
		    Piece B31 = new Miner(1, 6, "Blue", null);
		    Piece B32 = new Miner(1, 7, "Blue", null);
		    Piece B33 = new Miner(1, 8, "Blue", null);
		    Piece B34 = new Miner(1, 9, "Blue", null);
		    Piece B35 = new Miner(2, 0, "Blue", null);
		    Piece B41 = new Piece(2, 1, "Blue", "4");
		    Piece B42 = new Piece(2, 2, "Blue", "4");
		    Piece B43 = new Piece(2, 3, "Blue", "4");
		    Piece B44 = new Piece(2, 4, "Blue", "4");
		    Piece B51 = new Piece(2, 5, "Blue", "5");
		    Piece B52 = new Piece(2, 6, "Blue", "5");
		    Piece B53 = new Piece(2, 7, "Blue", "5");
		    Piece B54 = new Piece(2, 8, "Blue", "5");
		    Piece B61 = new Piece(2, 9, "Blue", "6");
		    Piece B62 = new Piece(3, 0, "Blue", "6");
		    Piece B63 = new Piece(3, 1, "Blue", "6");
		    Piece B64 = new Piece(3, 2, "Blue", "6");
		    Piece B71 = new Piece(3, 3, "Blue", "7");
		    Piece B72 = new Piece(3, 4, "Blue", "7");
		    Piece B73 = new Piece(3, 5, "Blue", "7");
		    Piece B81 = new Piece(3, 6, "Blue", "8");
		    Piece B82 = new Piece(3, 7, "Blue", "8");
		    Piece B9 = new Piece(3, 8, "Blue", "9");
		    Piece B10 = new Piece(3, 9, "Blue", "10");
		    



		    pieces.add(Bf);
		    pieces.add(Bb1);
		    pieces.add(Bb2);
		    pieces.add(Bb3);
		    pieces.add(Bb4);
		    pieces.add(Bb5);
		    pieces.add(Bb6);
		    pieces.add(B21);
		    pieces.add(B22);
		    pieces.add(B23);
		    pieces.add(B24);
		    pieces.add(B25);
		    pieces.add(B26);
		    pieces.add(B27);
		    pieces.add(B28);
		    pieces.add(B31);
		    pieces.add(B32);
		    pieces.add(B33);
		    pieces.add(B34);
		    pieces.add(B35);
		    pieces.add(B41);
		    pieces.add(B42);
		    pieces.add(B43);
		    pieces.add(B44);
		    pieces.add(B51);
		    pieces.add(B52);
		    pieces.add(B53);
		    pieces.add(B54);
		    pieces.add(B61);
		    pieces.add(B62);
		    pieces.add(B63);
		    pieces.add(B64);
		    pieces.add(B71);
		    pieces.add(B72);
		    pieces.add(B73);
		    pieces.add(B81);
		    pieces.add(B82);
		    pieces.add(B9);
		    pieces.add(B10);
		    pieces.add(Bspy);
		}
		else{    
		    Piece Rf = new Flag(0, 0, "Red", null);
		    Piece Rb1 = new Bomb(0, 1, "Red", null);
		    Piece Rb2 = new Bomb(0, 2, "Red", null);
		    Piece Rb3 = new Bomb(0, 3, "Red", null);
		    Piece Rb4 = new Bomb(0, 4, "Red", null);
		    Piece Rb5 = new Bomb(0, 5, "Red", null);
		    Piece Rb6 = new Bomb(0, 6, "Red", null);
		    Piece Rspy = new Spy(0,7, "Red", null);
		    Piece R21 = new Scout(0, 8, "Red", null);
		    Piece R22 = new Scout(0, 9, "Red", null);
		    Piece R23 = new Scout(1, 0, "Red", null);
		    Piece R24 = new Scout(1, 1, "Red", null);
		    Piece R25 = new Scout(1, 2, "Red", null);
		    Piece R26 = new Scout(1, 3, "Red", null);
		    Piece R27 = new Scout(1, 4, "Red", null);
		    Piece R28 = new Scout(1, 5, "Red", null);
		    Piece R31 = new Miner(1, 6, "Red", null);
		    Piece R32 = new Miner(1, 7, "Red", null);
		    Piece R33 = new Miner(1, 8, "Red", null);
		    Piece R34 = new Miner(1, 9, "Red", null);
		    Piece R35 = new Miner(2, 0, "Red", null);
		    Piece R41 = new Piece(2, 1, "Red", "4");
		    Piece R42 = new Piece(2, 2, "Red", "4");
		    Piece R43 = new Piece(2, 3, "Red", "4");
		    Piece R44 = new Piece(2, 4, "Red", "4");
		    Piece R51 = new Piece(2, 5, "Red", "5");
		    Piece R52 = new Piece(2, 6, "Red", "5");
		    Piece R53 = new Piece(2, 7, "Red", "5");
		    Piece R54 = new Piece(2, 8, "Red", "5");
		    Piece R61 = new Piece(2, 9, "Red", "6");
		    Piece R62 = new Piece(3, 0, "Red", "6");
		    Piece R63 = new Piece(3, 1, "Red", "6");
		    Piece R64 = new Piece(3, 2, "Red", "6");
		    Piece R71 = new Piece(3, 3, "Red", "7");
		    Piece R72 = new Piece(3, 4, "Red", "7");
		    Piece R73 = new Piece(3, 5, "Red", "7");
		    Piece R81 = new Piece(3, 6, "Red", "8");
		    Piece R82 = new Piece(3, 7, "Red", "8");
		    Piece R9 = new Piece(3, 8, "Red", "9");
		    Piece R10 = new Piece(3, 9, "Red", "10");
		    



		    pieces.add(Rf);
		    pieces.add(Rb1);
		    pieces.add(Rb2);
		    pieces.add(Rb3);
		    pieces.add(Rb4);
		    pieces.add(Rb5);
		    pieces.add(Rb6);
		    pieces.add(R21);
		    pieces.add(R22);
		    pieces.add(R23);
		    pieces.add(R24);
		    pieces.add(R25);
		    pieces.add(R26);
		    pieces.add(R27);
		    pieces.add(R28);
		    pieces.add(R31);
		    pieces.add(R32);
		    pieces.add(R33);
		    pieces.add(R34);
		    pieces.add(R35);
		    pieces.add(R41);
		    pieces.add(R42);
		    pieces.add(R43);
		    pieces.add(R44);
		    pieces.add(R51);
		    pieces.add(R52);
		    pieces.add(R53);
		    pieces.add(R54);
		    pieces.add(R61);
		    pieces.add(R62);
		    pieces.add(R63);
		    pieces.add(R64);
		    pieces.add(R71);
		    pieces.add(R72);
		    pieces.add(R73);
		    pieces.add(R81);
		    pieces.add(R82);
		    pieces.add(R9);
		    pieces.add(R10);
		    pieces.add(Rspy);
		}   
		
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            	if (!GameManager.isSetupPhase) return;

            	
                int sqSizeLocal = Math.min(getWidth() / 10, getHeight() / 4);
                int col = e.getX() / sqSizeLocal;
                int row = e.getY() / sqSizeLocal;

                for (Piece p : pieces) {
                    if (!p.isOnBoard() && p.getRow() == row && p.getCol() == col) {
                        GameManager.selectedPiece = p;
                        repaint();
                        break;
                    }
                }
            }
        });
	}
	
	private int sqSize;
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		sqSize = Math.min(getWidth() / 10, getHeight() / 4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int x = j * sqSize;
                int y = i * sqSize;

                g.setColor(Color.white);
                g.fillRect(x, y, sqSize, sqSize);

                g.setColor(Color.black);
                g.drawRect(x, y, sqSize, sqSize);
            }
        }

        for (Piece p : pieces) {
            if (p.isOnBoard()) continue;  

            int x = p.getCol() * sqSize + sqSize / 8;
            int y = p.getRow() * sqSize + sqSize / 8;
            int diameter = sqSize * 3 / 4;

            g.setColor(p.getTeam().equals("Red") ? Color.RED : Color.BLUE);
            g.fillOval(x, y, diameter, diameter);

            String rank = p.getName();
            Font font = new Font("Arial", Font.BOLD, diameter / 2);
            g.setFont(font);

            int stringWidth = g.getFontMetrics().stringWidth(rank);
            int stringHeight = g.getFontMetrics().getAscent();
            int textX = x + (diameter - stringWidth) / 2;
            int textY = y + (diameter + stringHeight) / 2 - 4;

            g.setColor(Color.WHITE);
            g.drawString(rank, textX, textY);

 
            if (GameManager.selectedPiece == p) {
                g.setColor(Color.GREEN);
                g.drawOval(x - 2, y - 2, diameter + 4, diameter + 4);
            }
        }
    }


	public ArrayList<Piece> getPieces() {
		return pieces;
	}


	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
		
	
	
}