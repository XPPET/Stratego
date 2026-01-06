import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame {
	
	
	Graveyard rGraveyard,bGraveyard;
	
	private ArrayList<Player> players = new ArrayList<>();    
	
	private Player bluePlayer;
	private Player redPlayer;

	private JLabel playerLabel; 
	
	private JLabel timerLabel;
	private JPanel timerPanel;
	
	private JButton readyButton;
	
	private boolean firstLoginComplete;                                   // Μια μεταβλητή boolean που θα γίνεται true αν ο 1ος παίκτης συνδεθεί επιτυχώς
 
	private boolean gameHasStarted;
	
	private int drawOffer = 0;
	
	private int turn;                                              //mod2 = 0->κόκκινος, mod2 = 1->μπλε
	
	
	public GUI() {
		openMenuPage();                                                //Ο constructor του GUI -> ουσιαστικά το main menu
	}
	
	
	public void openMenuPage() {		  
		
		bluePlayer = null;
		redPlayer = null;
		
		firstLoginComplete = false;    //Κάθε φορά που γυρνάμε στο main menu-> loginComplete =false -> ξανά πρέπει να συνδεθεί ο 1ος παίκτης 
		
		gameHasStarted = false;
		
		GameManager.isSetupPhase = true;
		
		turn = 0;
		
		
	    JButton gameButton = new JButton("Two-Player Game");      
	    JButton rulesButton = new JButton("Rules");
	    JButton statsButton = new JButton("Statistics");        
		
		
		JLabel titleLabel = new JLabel("                             STRATEGO");            //Μπακάλικος τρόπος αλλά δουλεύει 
		titleLabel.setFont(new Font("Serif", Font.PLAIN, 100));
		titleLabel.setForeground(new Color(0x111B41));
		
		
		JPanel panel = new JPanel();                                                     //Το panel όλων των buttons
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		JPanel panelFinal = new JPanel();                                                   //Το τελικό panel
		panelFinal.setLayout(new BorderLayout());
		
		gameButton.setMaximumSize(new Dimension(200, 100));
		rulesButton.setMaximumSize(new Dimension(200, 100));
		statsButton.setMaximumSize(new Dimension(200, 100));
		
		
		panelFinal.setBackground(new Color(0x800000));
		panel.setBackground(new Color(0x800000));
		
		
		panelFinal.add(titleLabel, BorderLayout.NORTH);
		panel.add(Box.createRigidArea(new Dimension(220, 150)));
		panel.add(gameButton);
		panel.add(Box.createRigidArea(new Dimension(220, 150)));
		panel.add(rulesButton);
		panel.add(Box.createRigidArea(new Dimension(220, 150)));
		panel.add(statsButton);
		panelFinal.add(panel, BorderLayout.CENTER);
		
		
		this.setContentPane(panelFinal);
		
		setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setTitle("Stratego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		gameButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openLoginPage();
			}});
	}
	
	
	
	
	public void openLoginPage() {
		
		JPanel panel = new JPanel();                                                    //Το panel όλων των buttons και του textField
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		JPanel signInPanel = new JPanel();                                              //Το panel ώστε το loginButton να είναι στην ίδια ευθεία με το textField
		signInPanel.setLayout(new FlowLayout());
		
		JPanel panelFinal = new JPanel();                                                   //Το τελικό panel
		panelFinal.setLayout(new BorderLayout());
		
		
		JLabel label = new JLabel("");
		if(!firstLoginComplete) {
			panelFinal.setBackground(new Color(0x800000));
			panel.setBackground(new Color(0x800000));
			signInPanel.setBackground(new Color(0x800000));
			
			
			label.setText("                           RED TEAM");
			label.setForeground(new Color(0x111B41));
		}
		else {
			panelFinal.setBackground(new Color(0x111B41));
			panel.setBackground(new Color(0x111B41));
			signInPanel.setBackground(new Color(0x111B41));
			
			
			label.setText("                           BLUE TEAM");
			label.setForeground(new Color(0x800000));
		}
		label.setFont(new Font("Serif", Font.PLAIN, 100));
		
		
		JTextField text = new JTextField("Enter username");
		
		
		JButton loginButton = new JButton("Sign In");
		JButton signUpButton = new JButton("Create New Account");
		JButton backToMenuButton = new JButton("Back to Main Menu"); 
		
		
		signInPanel.setMaximumSize(new Dimension(700, 100));
		loginButton.setPreferredSize(new Dimension(200, 100));
		text.setPreferredSize(new Dimension(300, 50));
		signUpButton.setMaximumSize(new Dimension(200, 100));
		backToMenuButton.setMaximumSize(new Dimension(200, 100));
		
		
		panelFinal.add(label, BorderLayout.NORTH);
		panel.add(Box.createRigidArea(new Dimension(250, 150)));
		signInPanel.add(loginButton);
		signInPanel.add(text);
		panel.add(signInPanel);
		panel.add(Box.createRigidArea(new Dimension(250, 150)));
		panel.add(signUpButton);
		panel.add(Box.createRigidArea(new Dimension(250, 150)));
		panel.add(backToMenuButton);
		panelFinal.add(panel, BorderLayout.CENTER);
		
		
		this.setContentPane(panelFinal);
		
		
		setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setTitle("Stratego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		backToMenuButton.addActionListener(new ActionListener(){                      // Για το back to menu button {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstLoginComplete) 
					JOptionPane.showMessageDialog(null, "Players have been reset/Both players need to login.");
				openMenuPage();		
			}});                                                                     //                  }
		
		loginButton.addActionListener(new ActionListener(){                // Για το sign in button   {
			@Override
            public void actionPerformed(ActionEvent e) {
			
				
				Player player = null;
				for(Player p: players) {
					if(p.getUsername().equals(text.getText()))
						player = p;
				}

				if(text.getText().isEmpty() || text.getText().equals("Enter username"))
					JOptionPane.showMessageDialog(null, "You must enter a username", "Error", JOptionPane.ERROR_MESSAGE);
				else if(player == null)
					JOptionPane.showMessageDialog(null, "Player " + text.getText() + " does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				else if(player.equals(redPlayer)) {
					JOptionPane.showMessageDialog(null, "Player " + text.getText() + " has already joined the game", "Error", JOptionPane.ERROR_MESSAGE);
					player = null;
				}
				else
					openPasswordPage(player);
			}			
		}); 
		
		text.addMouseListener(new MouseAdapter() {                                 // Για να αδειάζει το textField όταν κλικάρουμε σε αυτό
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(text.getText().equals("Enter username"))
		        text.setText("");
		    }
		});                                                               //                                   }
		
		
		signUpButton.addActionListener(new ActionListener() {            // Για το create account button {

			@Override
			public void actionPerformed(ActionEvent e) {
				openCreatePage();
			}                      
		});                                                                 //                       }
		
	}
	
	
	
	
	
	public void openPasswordPage(Player player) {
		
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new FlowLayout());
		
		JPanel returnPanel = new JPanel();
		returnPanel.setLayout(new FlowLayout());
		
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new BorderLayout());
		
		JButton loginButton = new JButton("Sign In");
		JTextField text = new JTextField(player.getUsername() + " enter your Password");
		
		JButton returnButton = new JButton("Return");
		
		returnButton.setPreferredSize(new Dimension(200, 100) );
		loginButton.setPreferredSize(new Dimension(200, 100));
		text.setPreferredSize(new Dimension(300, 50));
		
	
		loginPanel.add(Box.createRigidArea(new Dimension(0, 400)));
		loginPanel.add(loginButton);
		loginPanel.add(text);
		finalPanel.add(loginPanel, BorderLayout.NORTH);
		returnPanel.add(Box.createRigidArea(new Dimension(0, 400)));
		returnPanel.add(returnButton);
		finalPanel.add(returnPanel, BorderLayout.CENTER);
		
		
		this.setContentPane(finalPanel);
		
		
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);   
		this.setTitle("Stratego");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "You must enter a password", "Error", JOptionPane.ERROR_MESSAGE);
				else if(!text.getText().equals(player.getPassword()))
					JOptionPane.showMessageDialog(null, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					if(firstLoginComplete ) {
						player.setTeam("Blue");
						bluePlayer = player;
						openPreparePage(null);
					}
					else {
						player.setTeam("Red");
						redPlayer = player;
						firstLoginComplete = true;
						openLoginPage();
					}
					JOptionPane.showMessageDialog(null, "Player " + player.getUsername() + " has joined the game");
				}	
			}
		});
		
		
		text.addMouseListener(new MouseAdapter() {                                 // Για να αδειάζει το textField όταν κλικάρουμε σε αυτό
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(text.getText().equals(player.getUsername() + " enter your Password"))
		        text.setText("");
		    }
		});
		
		returnButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openLoginPage();
			}
		});
	
	}
	
	
	
	
	public void openCreatePage() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JTextField usernameTextField = new JTextField("Username");
		JTextField pswdTextField = new JTextField("Password");
		JTextField confirmPswdTextField = new JTextField("Confirm Password");
		
		JButton createButton = new JButton("Create Account");
		JButton menuButton = new JButton("Back to Main Menu");
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new BorderLayout());
		
		
		
		usernameTextField.setMaximumSize(new Dimension(1000, 100));
		pswdTextField.setMaximumSize(new Dimension(1000, 100));
		confirmPswdTextField.setMaximumSize(new Dimension(1000, 100));	
		
		createButton.setPreferredSize(new Dimension(200, 100));
		menuButton.setPreferredSize(new Dimension(200, 100));
		
		
		
		buttonPanel.add(Box.createRigidArea(new Dimension(750, 150)));
		buttonPanel.add(menuButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(70, 150)));
		buttonPanel.add(createButton);
		
		
		panel.add(Box.createRigidArea(new Dimension(200, 150)));
		panel.add(usernameTextField);
		panel.add(Box.createRigidArea(new Dimension(200, 150)));
		panel.add(pswdTextField);
		panel.add(Box.createRigidArea(new Dimension(200, 150)));
		panel.add(confirmPswdTextField);
		panel.add(Box.createRigidArea(new Dimension(200, 150)));
		
		
		finalPanel.add(panel, BorderLayout.NORTH);
		finalPanel.add(buttonPanel, BorderLayout.CENTER);
		
		
        this.setContentPane(finalPanel);
		
		
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);   
		this.setTitle("Stratego");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		usernameTextField.addMouseListener(new MouseAdapter() {                                 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(usernameTextField.getText().equals("Username"))
		    		usernameTextField.setText("");
		    }
		});
		
		pswdTextField.addMouseListener(new MouseAdapter() {                                 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(pswdTextField.getText().equals("Password"))
		    		pswdTextField.setText("");
		    }
		});
		
		confirmPswdTextField.addMouseListener(new MouseAdapter() {                                 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(confirmPswdTextField.getText().equals("Confirm Password"))
		    		confirmPswdTextField.setText("");
		    }
		});
		
		
		menuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstLoginComplete) 
					JOptionPane.showMessageDialog(null, "Players have been reset/Both players need to login.");
				openMenuPage();	
			}
			 
		});
		
		createButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Player player = null;
				for(Player p: players) {
					if(p.getUsername().equals(usernameTextField.getText()))
						player = p;
				}
				
				if(usernameTextField.getText().isEmpty() || usernameTextField.getText().equals("Username")){
					JOptionPane.showMessageDialog(null, "You must enter a username", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(pswdTextField.getText().isEmpty() || pswdTextField.getText().equals("Password")) {
					JOptionPane.showMessageDialog(null, "You must enter a password", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(confirmPswdTextField.getText().isEmpty() || confirmPswdTextField.getText().equals("Confirm Password")) {
					JOptionPane.showMessageDialog(null, "You must enter a password", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!pswdTextField.getText().equals(confirmPswdTextField.getText())){
					JOptionPane.showMessageDialog(null, "Your passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
				}
			    else if(player != null){
					JOptionPane.showMessageDialog(null, "A player with this username already exists", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Player p = new Player(usernameTextField.getText(), pswdTextField.getText());
					players.add(p);
					if(firstLoginComplete ) {
						p.setTeam("Blue");
						bluePlayer = p;
						openPreparePage(null);
					}
					else {
						firstLoginComplete = true;
						p.setTeam("Red");
						redPlayer = p;
						openLoginPage();
					}
					
					JOptionPane.showMessageDialog(null, "Player " + usernameTextField.getText() + " has been successfully created");
					
				}
			}
		});
		
	}
	
	
	public void openPreparePage(JPanel gamePanel) {
		
		JButton readyButton = new JButton("READY");
		
		JLabel textLabel ;
		if(turn % 2 == 0) {
		    textLabel = new JLabel(redPlayer.getUsername() + " prepare for your turn", SwingConstants.CENTER);
		    textLabel.setForeground(new Color(0x800000));
		}else {
			textLabel = new JLabel(bluePlayer.getUsername() + " prepare for your turn", SwingConstants.CENTER);
			textLabel.setForeground(new Color(0x111B41));
		}
		textLabel.setFont(new Font("Serif", Font.BOLD, 80));
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel finalPanel =new JPanel();
		finalPanel.setLayout(new BorderLayout());
		
		
		readyButton.setMaximumSize(new Dimension(200, 100));
		
		
		finalPanel.add(textLabel, BorderLayout.NORTH);
		panel.add(Box.createRigidArea(new Dimension(120, 300)));
		panel.add(readyButton);
		panel.add(Box.createRigidArea(new Dimension(120, 300)));
		finalPanel.add(panel, BorderLayout.CENTER);
		
		
		
        this.setContentPane(finalPanel);
		
		
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);   
		this.setTitle("Stratego");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		readyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!gameHasStarted) {
					openGamePage();
					gameHasStarted = true;
				}
				else {
					nextTurnInGame(gamePanel);
				}
				GameManager.selectedPiece = null;
			}
			 
		});
	}
	
	
	
	public void openGamePage() {
		
		GameManager.currentPlayer = redPlayer;
	    
		GameManager.gameEnded = false;
		
		
	    JButton resignButton = new JButton("Abort");
	    JButton drawButton = new JButton("Offer Draw"); 
		
		readyButton = new JButton("READY");
		
		
		
		
		bGraveyard = new Graveyard("Blue");
		rGraveyard = new Graveyard("Red");
		GameManager.board = new Board(bGraveyard, rGraveyard);

		JPanel panel = new JPanel();             //Το layout του συνολικού panel που περιέχει το board και το graveyardPanel
		panel.setLayout(new BorderLayout());     //Border layout: 5 areas (NORTH/EAST/WEST/SOUTH/CENTER)
		
		
		JPanel buttonPanel = new JPanel();      //Το layout του panel που περιέχει τα 2 buttons και το timer
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));        //Flow layout: όλα στη σειρά το ένα δίπλα στο άλλο(
		
		
		JPanel graveyardPanel = new JPanel();               //Το layout του panel που περιέχει τα 2 "νεκροταφεία" και το buttonPanel
		graveyardPanel.setLayout(new BoxLayout(graveyardPanel, BoxLayout.Y_AXIS));      //Box layout: flow layout απλά μπορεί και κάθετα
		
		timerPanel = new JPanel();
		timerPanel.setLayout(new BorderLayout());

		
		bGraveyard.setPreferredSize(new Dimension(900, 900));
		rGraveyard.setPreferredSize(new Dimension(900, 900));

		
		drawButton.setPreferredSize(new Dimension(100, 100));
		resignButton.setPreferredSize(new Dimension(100, 100));
		
		readyButton.setPreferredSize(new Dimension(300, 100));
		
			
		timerLabel = new JLabel();                                        //Το timer
		timerLabel.setText("00:00");                                     
		timerLabel.setFont(new Font("Verdana",Font.PLAIN,100));           
		timerLabel.setForeground(Color.white);
		timerLabel.setBackground(new Color(0x111B41));
		timerLabel.setOpaque(true);                                     //Για να φαίνεται το background
		
		playerLabel = new JLabel(GameManager.currentPlayer.getUsername(), SwingConstants.CENTER );
		playerLabel.setFont(new Font("Verdana",Font.PLAIN,20));
		playerLabel.setForeground(new Color(0x800000));
		playerLabel.setOpaque(true);
		
		
		GameManager.board.setBoardListener(() -> {
			updateForNextTurn(drawButton, resignButton);
			if(!GameManager.gameEnded)
				openPreparePage(panel);
		});
		
		
		timerPanel.add(readyButton);
		timerPanel.add(playerLabel, BorderLayout.SOUTH);
		
		
		buttonPanel.add(drawButton);
		drawButton.setVisible(false);
		buttonPanel.add(Box.createRigidArea(new Dimension(180, 0)));     //Κενά ανάμεσα στα στοιχεία
		buttonPanel.add(timerPanel);
		buttonPanel.add(Box.createRigidArea(new Dimension(180, 0)));
		buttonPanel.add(resignButton);	
		
		
		
	    graveyardPanel.add(bGraveyard);                                  //Όλα στη σειρά από πάνω προς τα κάτω με τη σειρά που γίναν add 
	    graveyardPanel.add(buttonPanel);
	    buttonPanel.add(Box.createRigidArea(new Dimension(0, 270)));     //Κενό ανάμεσα στο buttonPanel και το κάτω νεκροταφείο
	    graveyardPanel.add(rGraveyard);
	    
		 
		
        
        panel.add(GameManager.board, BorderLayout.CENTER);                          //Χρησιμοποιούμε 2 areas CENTER->board και EAST->graveyardPanel
        panel.add(graveyardPanel, BorderLayout.EAST);
        
		
		
		this.setContentPane(panel);
		
		
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);   
		this.setTitle("Stratego");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		readyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//if (board.allPiecesPlaced(GameManager.currentPlayer.getTeam())) {	
				    updateForNextTurn(drawButton, resignButton);
				    if(!GameManager.gameEnded)
				    	 openPreparePage(panel);
				//}
				//else
					//JOptionPane.showMessageDialog(null, "You need to place all your pieces first.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		
		resignButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(GameManager.isSetupPhase)
					openMenuPage();
				else {
					int result = JOptionPane.showConfirmDialog(null,"If you proceed you will lose the game","Warning",JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						GameManager.gameEnded = true;
						openGameEndedPage();
					}
				}
			}
			
		});
		
		
		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(drawOffer > 0) {
					int result = JOptionPane.showConfirmDialog(null,"If you proceed the game will end as a draw","Warning",JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						GameManager.gameEnded = true;
						openGameEndedPage();
					}
				}
				else {
					int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to offer a draw?","Warning",JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.OK_OPTION) 
						drawOffer = 1;
				}
			}
			
		});
		
		

	}
	
	
	
	public void nextTurnInGame(JPanel panel){
		
		    if(drawOffer > 0)
			     JOptionPane.showMessageDialog(null,"Your opponent has offered a draw.","Draw", JOptionPane.QUESTION_MESSAGE);

		    this.setContentPane(panel);
		    this.revalidate();
		    this.repaint();

		    this.setVisible(true);
		    this.setExtendedState(JFrame.MAXIMIZED_BOTH);   
		    this.setTitle("Stratego");
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
	
	 public void updateForNextTurn(JButton drawButton, JButton resignButton) {
		 
		 
		 if(!GameManager.isSetupPhase) {
			 Player opponent = GameManager.currentPlayer.getTeam().equals("Red") ? bluePlayer : redPlayer;
			 if (!GameManager.board.hasAnyLegalMove(opponent.getTeam()) || !GameManager.board.areFlagsProtectedAndNoMiners()) {
				 GameManager.gameEnded = true;
			 }
			 
		 }
		 
		 
		 turn++;
		 
		 if(drawOffer == 1) {
			 drawOffer ++;
		 }
		 else if(drawOffer == 2)
			 drawOffer = 0;
		 
		 
		 if(GameManager.gameEnded == true)
			 openGameEndedPage();
		 else {
			 if(turn==2)
				 GameManager.isSetupPhase = false; 
			 
			 if(!GameManager.isSetupPhase) {
				    timerPanel.remove(readyButton);
					timerPanel.add(timerLabel, BorderLayout.CENTER);
					
					resignButton.setText("Resign");
					drawButton.setVisible(true);
			 }
			 
			 if(turn % 2 == 0) {
		            GameManager.currentPlayer = redPlayer;
		        } else {
		            GameManager.currentPlayer = bluePlayer;
		        }
		        
		        playerLabel.setText(GameManager.currentPlayer.getUsername());

		        if(GameManager.currentPlayer.getTeam().equals("Blue")) {
		            playerLabel.setForeground(new Color(0x111B41));
		        } else {
		            playerLabel.setForeground(new Color(0x800000));
		        }
		        


		        repaint();
		 }
		 
   

	}
	 
	 public void openGameEndedPage() {
		 
		 JPanel panel = new JPanel();
		 
		 this.setContentPane(panel);
			
			
		 this.setVisible(true);
		 this.setExtendedState(JFrame.MAXIMIZED_BOTH);   
		 this.setTitle("Stratego");
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
	 }

}
