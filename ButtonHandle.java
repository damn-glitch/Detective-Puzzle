import java.awt. * ;
import javax.swing. * ;
import java.awt.event. * ;
import java.time. * ;
import java.util. * ;
/**
 * ButtonHandle - Class to hold all actions of buttons, and runs them
 * @authors Group 3: Mirsaid Abbasov, Alisher Beisembekov, Omar Arafa, Ali Akbar Jafri
 * @version 01/03/2021
 * ISTE-121 Project
 */
public class ButtonHandle extends JPanel implements ActionListener {
   //Declaration of Attributes
   private JTextArea sug;
	private JTextArea status;
	private JTextArea jta;
	private JTextArea jta2;
	private int[] killer;
	private int day;
	private int alive;
	private int tries;
	private Icon dead;
	private boolean kill;
	private int[][] disBut;
	private ArrayList deadList;
	private MatrixButton[] player;
	private JLabel bullets;
	private JButton jbTest;
	private JButton jbStart;
	private JButton jbKill;
	private int length;
	private JFrame jfEnd;
	private JPanel down;
	private JPanel up;
	private JScrollPane jsp;

	/**
	 * Constructor, which initilizes all the work to methods
	 * @param sug, suggestion text-area
	 * @param killer, location of killer
	 * @param status,
	 * @param player, player itself
	 * @param bullets, bullets to kill killer
	 * @param jta, text-area for central text-area, so it can guide user
	 * @param jbStart, Jbutton to start a game
	 * @param jbKill, Jbutton to kill a killer
	 * @param down, JPanel of tries
	 * @param jta2, text-area for notes frame
	 * @param up, a JPanel of players
	 */
	public ButtonHandle(JTextArea sug, int[] killer, JTextArea status, MatrixButton[] player, JLabel bullets, JTextArea jta, JButton jbStart, JButton jbKill, JPanel down, JTextArea jta2, JPanel up) {
		this.sug = sug;
		this.killer = killer;
		this.status = status;
		this.player = player;
		this.bullets = bullets;
		this.jbStart = jbStart;
		this.jbKill = jbKill;
		this.jta = jta;
		this.down = down;
		this.up = up;
		this.jta2 = jta2;
		//Start initialing of values
		day = 0;
		length = 0;
		alive = 100;
		tries = -1;
		kill = false;
		disBut = new int[100][2];
		deadList = new ArrayList < MatrixButton > ();
		dead = new ImageIcon("Dead.png");
		setVisible(true);

	}

	/**
	 * Method, which performs all action of the code from GUI
	 * @param ae for action performance
	 */
	public void actionPerformed(ActionEvent ae) {
		try {
			//Alisher
			if (ae.getActionCommand().equals("Rules")) {
				Tutorial();
			}
			//Alisher
			else if (ae.getActionCommand().equals("Start")) {
				Start();
			}
			///////////////////// Mirsaid //////////////////////
			else if (ae.getActionCommand().equals("")) {
				//Getting source of the clicker button and down casting it to MatrixButton
				MatrixButton mp = (MatrixButton) ae.getSource();
				//Seting default value for somedead
				boolean deadTest = false;

				//Checking if clicked player is dead
				for (int i = 0; i < deadList.size(); i++) {
					MatrixButton test = (MatrixButton) deadList.get(i);
					for (int j = 0; j < 100; j++) {
						if (mp.getRow() == test.getRow() && mp.getCol() == test.getCol()) {
							deadTest = true;
							break;
						}
					}
				}
				//The action which should be done if someone is dead
				if (deadTest) {
					//Text for displaying
					if (kill) {
						//Asking to kil someone else
						jta.setText("This person is dead, kill someone else.");
					}
					else {
						//Asing to ask suggetion from someone else
						jta.setText("This person is dead, ask someone who is not dead.");
					}
				}
				else {
					//Displaying text that user should read suggetion.
					jta.setText("                  ----->  \nRead the suggestion of the person. You now can kill someone by pressing the kill button or continue asking other people");

					//if Kill function is actived
					if (kill) {
						//Decrease people number
						alive--;
						//Seting status info
						status.setText(String.format("------------------------------\n Day: %d\n Alive: %d", day, alive));
						//Getting number of tries from status
						int tries = Integer.parseInt(bullets.getText().substring(8, 9)); // TRIES: 3

						//Alisher
						tries -= 1;
						bullets.setText(" TRIES: " + tries);
                  //Changing tries set color after each try
                  //Green->Yellow->Orange->Red
						if (tries == 2) {
							down.setBackground(Color.YELLOW);
						}
						else if (tries == 1) {
							down.setBackground(Color.ORANGE);
						}
						else if (tries == 0) {
							down.setBackground(Color.RED);
						}

						//Getting source of the clicker button and down casting it to MatrixButton
						mp = (MatrixButton) ae.getSource();
						//Seting dead Icon
						mp.setIcon(dead);
						//Adding player which is dead to dead list
						deadList.add(mp);
						//Deactivaing kill for next time 
						kill = false;

						//Checking if the person is choosen is the kill
						if (mp.getRow() == killer[0] && mp.getCol() == killer[1]) {
							//Calling Win method
							Win(jta, status, bullets, player, alive);
						}
						else {
							//Checking if the player is got the tries or not
							if (Integer.parseInt(bullets.getText().substring(8, 9)) == 0) {
								//Calling lose
								Lose(killer[0], killer[1], alive);
							}
							else {
								//Displaying that person which is killed is not killer
								jta.setText("The person which you killed is NOT a Killer. Get more information from suggetion to find killer or kill again if you want.");
							}
						}

						//Geting the length of the Disabled button array which is filled
						for (int i = 0; i < disBut.length; i++) {
							//Checking if it filled or not
							if (disBut[i][0] != 0) {
								length++;
							}
							else {
								break;
							}
						}

						//Fist two for loops is used to check if the button was turn off before
						for (int i = 0; i < length; i++) {
							for (int j = 0; j < 100; j++) {
								//Checking if the button was turn off before
								if (player[j].getRow() == disBut[i][0] && player[j].getCol() == disBut[i][1]) {
									//Fist two for loops is used to check if the person was killed before
									for (int d = 0; d < deadList.size(); d++) {
										//Getting the button which was dead
										MatrixButton m = (MatrixButton) deadList.get(d);
										//Checking if the button was killed before
										if (player[j].getRow() == m.getRow() && player[j].getCol() == m.getCol()) {
											player[j].setEnabled(true);
											break;
										}
										else {
											player[j].setEnabled(false);
										}
									}
								}
							}

						}
					}
					else {
						//Killing someone after 6 days
						int x = 0;
						int y = 0;
						//Checking if the day count is 6 or not
						if (day == 5) {
							alive--;
							//geting random X
							int radX = rand(1, 2);
							//Checking what random method difined
							if (radX == 1) {
								//Difining the X coordinate
								x = 2 + killer[0];
								//if the addition will go over the limit, opposite is done
								if (x >= 10) {
									x = -2 + killer[0];
								}
							}
							else {
								//geting random X
								x = killer[0] - 2;
								//if the addition will go over the limit, opposite is done
								if (x <= 1) {
									x = 2 + killer[0];
								}
							}

							//geting random Y
							int radY = rand(1, 2);
							//Checking what random method difined
							if (radY == 1) {
								//Difining the y coordinate
								y = 2 + killer[1];
								//if the addition will go over the limit, opposite is done
								if (y >= 10) {
									y = -2 + killer[1];
								}
							}
							else {
								y = killer[1] - 2;
								//if the addition will go over the limit, opposite is done
								if (y <= 1) {
									y = 2 + killer[1];
								}
							}

							//Finding the person who got the coordinate which is difined above                 
							for (int i = 0; i < 100; i++) {
								//Checking if the coordinates are the same
								if (player[i].getRow() == x && player[i].getCol() == y) {
									player[i].setEnabled(true);
									player[i].setIcon(dead);
									deadList.add(player[i]);
									jta.setText(String.format("Killer killed person in:\nx = %d\ny = %d", player[i].getRow(), player[i].getCol()));
									break;

								}
							}

						}
						//Getting the button
						mp = (MatrixButton) ae.getSource();
						mp.setEnabled(false);
						tries++;
						day++;
						//Seting data
						status.setText(String.format("------------------------------\n Day: %d\n Alive: %d", day, alive));
						//Checking if the number of days is 10
						if (day == 10) {
							//Calling lose method
							Lose(killer[0], killer[1], alive);
						}
						//Calling suggetion
						Sug(mp.getRow(), mp.getCol(), killer, tries);
						//Saving button to the disbled list
						disBut[tries][0] = mp.getRow();
						disBut[tries][1] = mp.getCol();
					}
				}
			}
			else if (ae.getActionCommand().equals("Kill")) {
				//This desables all buttons
				for (int i = 0; i < 100; i++) {
					player[i].setEnabled(true);
				}
				//Setting text
				jta.setText("Choose someone to KILL");

				kill = true;
				//Making empty suggetion
				sug.setText("");

			}
		}
		catch(NullPointerException e) {}

	}

	//Alisher

	/**
	 * Method, which opens a tutorial window
	 */
	public void Tutorial() {
      //Setting a new frame
		JFrame jfrm2 = new JFrame("Tutorial");
		jfrm2.setSize(1000, 600);
      //Writing tutorial
		JTextArea jta = new JTextArea("In this game, you should find the killer from the suggestions of the people. In the suggestions, they will be three attributes from which you could understand where the killer is located. The first is Distance, the second is Direction and the third one is Accuracy, which is kind of most important part. \nDistance - This shows you how far killer located from the person you choose. It could be from 0 to 10 or NO DATA. \nDirection - This shows you in which direction the Killer is located from the person you choose. It could be �Up�, �Down�, �Left� and �Right�.\nAccuracy - From the accuracy you should understand is the date mentioned above true or not. They will not differ in numbers like if the accuracy is low, it means distance will be -1 or +1 accurate. IT IS NOT WORKING LIKE THAT. Here is how it is working:\n1. 30% - 60% = that means the information which is given is completely random and most cases will be wrong.\n2. 60% - 90% = that means you will see the direction and it will be 100% correct, but the distance will be \"NO DATA\".\n3. 90% - 100% = that means you will have both distance and direction and they will be 100% correct.\nHow to win: Find where killer is located and kill him.\nHow you can lose:\n1. Each time you ask someone for a suggestion the day counter increases. If the day is 10, you will lose.\n2. You have a chance to kill 3 times, but if the tries are 0 and you did not kill the killer, you will lose.\n\nAdditional game mechanics:\n1. After 6 days killer will kill someone in the distance of 2 from him(Only 2, no 1).\n2. If you will ask someone for a suggestion, you will not be able to ask him again.\n3. During the game Text in the center of the app will help you to play by giving you tips.\n4. Fist 3 suggestion�s accuracy always will be in the range of 60% - 90%.\n\nThat is all, GOOD LUCK.", 5, 10);
		jfrm2.setVisible(true);
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
      //Making a locations of panels
		JPanel jpCenter = new JPanel(new FlowLayout());
		JPanel jpNorth = new JPanel(new FlowLayout());
		JPanel jpSouth = new JPanel(new FlowLayout());

		JLabel jl = new JLabel("");

		jl.setIcon(new ImageIcon("dett.png"));
		jl.setFont(new Font("Calibri", Font.ITALIC, 40));
		jta.setFont(new Font("Calibri", Font.ITALIC, 20));
		jta.setWrapStyleWord(true);
      //Stting background colors
		jpCenter.setBackground(new Color(0, 113, 192));
		jpNorth.setBackground(new Color(0, 113, 192));

		jpNorth.add(jl);
		jpCenter.add(jta);

		jfrm2.add(jpCenter, "Center");
		jfrm2.add(jpNorth, "North");
      //Adding scroll, so it user could scroll information for easier reading
		jsp = new JScrollPane(jta);
		jfrm2.add(jsp);
	}
   
	//Alisher
	/**
	 * Method, which Starts a game and resets it after completing it
	 */
	public void Start() {
      //Setting text to main text-area
		jta.setText("You now can choose someone to ask for a suggestion. You will see it on the right side of the app");
      //Changing start button to kill button
		jbStart.setVisible(false);
		jbKill.setVisible(true);
      //Enabling notes
		jta2.setEditable(true);
		jta2.setBackground(new Color(255, 255, 255));
      //Enabling kill Button
		jbKill.setEnabled(true);
      //Enabling players
		for (Component c: up.getComponents()) {
			if (c instanceof JButton) c.setEnabled(true);
		}
      //Setting values
		status.setText("------------------------------\n Day: 0\n Alive: 100");
		bullets.setText(" TRIES: 3");
		down.setBackground(Color.GREEN);
		jfEnd.setVisible(false);
		jbStart.setEnabled(true);
		jbKill.setEnabled(true);
      //setting values for a reset
		day = 0;
		alive = 100;
		tries = -1;
		length = 0;
		status.setText(String.format("------------------------------\n Day: %d\n Alive: %d", day, alive));
		kill = false;
		Arrays.fill(disBut, null);
		deadList.clear();
		killer[0] = rand(1, 10);
		killer[1] = rand(1, 10);
		for (int i = 0; i < 100; i++) {
			player[i].setEnabled(true);
		}
      //Adding icon(image) to a player
		for (int i = 0; i < 100; i++) {
			Icon pla = new ImageIcon("Screenshot_2021-02-19_140705-removebg-preview (8).png");
			player[i].setIcon(pla);
		}
	}
	///////////////////// Mirsaid //////////////////////
	/**
    * Sug, gives suggestion of a person depending on where he is located
    * @param x, the x cordinate of pressed player
    * @param y, the y cordinate of pressed player
    * @param killer array which contain killer location
    * @param guess will tell the number of the suggetion
    */
	public void Sug(int x, int y, int[] killer, int guess) {
      //Creating a positions based on killer positions
		int xdif = x - killer[0];

		if (xdif < 0) {
			xdif = xdif * -1;
		}

		int ydif = y - killer[1];

		if (ydif < 0) {
			ydif = ydif * -1;
		}

		int dis = 0;
		int d = 0;

		if (xdif > ydif) {
			dis = xdif;
			d = 0;
		}
		else if (xdif < ydif) {
			dis = ydif;
			d = 1;
		}
		else if (xdif == 0 && ydif == 0) {
			d = 2;
		}
      //Setting direction based on killer's location
		String dir = "";
		if (d == 0) {
			if (x > killer[0]) {
				dir = "Left";
			}
			else {
				dir = "Right";
			}
		}
		else if (d == 1) {
			if (y > killer[1]) {
				dir = "Up";
			}
			else {
				dir = "Down";
			}
		}
		else if (d == 2) //Killer
		{
			dis = rand(1, 10);
			dir = noInfo();
			sug.setText(String.format("Location of person: \nx = %d \ty = %d\nMain info:\nDistance: NO DATA\nDirection: %s\nAccuracy of info: %d%%", x, y, dir, rand(30, 50)));

		}

		if (d != 2) //Real person
		{
			if (guess > 1) {
				int luck = rand(1, 20);

				if (luck < 15) {
					int acc = rand(60, 100);
               //Showcasing an exact information
					if (acc < 90) {
						sug.setText(String.format("Location of person: \nx = %d \ty = %d\nMain info:\nDistance: NO DATA\nDirection: %s\nAccuracy: %d%%", x, y, dir, acc));
					}
               //Showcasing an not as exact information
					else {
						sug.setText(String.format("Location of person: \nx = %d \ty = %d\nMain info:\nDistance: %d\nDirection: %s\nAccuracy: %d%%", x, y, dis, dir, acc));
					}
				}
            //Showcasing an random information
				else {
					dis = rand(1, 10);
					dir = noInfo();
					sug.setText(String.format("Location of person: \nx = %d \ty = %d\nMain info:\nDistance: %d\nDirection: %s\nAccuracy: %d%%", x, y, dis, dir, rand(30, 50)));
				}
			}
         //Showcasing an not as exact information
			else {
				int acc = rand(60, 89);
				sug.setText(String.format("Location of person: \nx = %d \ty = %d\nMain info:\nDistance: NO DATA\nDirection: %s\nAccuracy: %d%%", x, y, dir, acc));
			}
		}
	}

	/**
     * Method, which generates a pseudo-random integer in the range [min, max]
     * Also it is used in a suggestion method
     * @param min : the starting value of the range (inclusive)
     * @param max : the ending value of the range (inclusive)
     * @return pseudoRandom is a pseudo-random integer in the range [min, max]
     */
	public int rand(int min, int max) {
		if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
			throw new IllegalArgumentException("Invalid range");
		}
      int pseudoRandom =  new Random().nextInt(max - min + 1) + min;
		return pseudoRandom;
	}

	/**
	 * Method, which sets and returns direction to user in a random case
	 * @return x, which is a information of a direction
	 */
	public String noInfo() {
		String x = "";
      //Setting direction in random
		switch (rand(1, 4)) {
   		case 1:
   			x = "Up";
   			break;
   		case 2:
   			x = "Right";
   			break;
   		case 3:
   			x = "Down";
   			break;
   		case 4:
   			x = "Left";
   			break;
		}
		return x;
	}
	//Omar modified by Alisher

	/**
	 * Method, which congrats user of winning this game
	 * @param jta, text-area for central text-area, so it can guide user
	 * @param status, an area of days and living
	 * @param tries, an area of tries
	 * @param player, player itself
	 * @param alive, amount of people user saved from killer
	 */
	public void Win(JTextArea jta, JTextArea status, JLabel tries, MatrixButton player[], int alive) {
      //Setting text to main text-area
		jta.setText("Congratulations, You found the killer!!!");
		status.setText("------------------------------\n Day: 0\n Alive: 100");
		tries.setText(" TRIES: 3");
		//Alisher
      //Setting to green again, since killer might killed someone and it would've remained previous color
		down.setBackground(Color.GREEN);
		for (int i = 0; i < 100; i++) {
			player[i].setEnabled(false);
		}
      //Creating a Jframe of victory
		jfEnd = new JFrame();
      //Enabling icon png
		jfEnd.setIconImage(new ImageIcon("icon.png").getImage());
		jfEnd.setSize(500, 600);
      //Setting location
		jfEnd.setLocation(1100, 135);
		jfEnd.setResizable(false);
		jfEnd.setVisible(true);
      //Adding panels
		JPanel jpEnd = new JPanel();
		jpEnd.setBackground(Color.GREEN);
		jpEnd.setForeground(Color.GREEN);
		jpEnd.setLayout(null);
		jpEnd.setOpaque(true);
      //Making it to Center
		jfEnd.add(jpEnd, BorderLayout.CENTER);
      //Adding amount of alive inside a loop picture, it looks beautiful
		JLabel jlLoop = new JLabel(new ImageIcon("win_loop.png"));
		jlLoop.setLocation(175, 20);
		jlLoop.setSize(100, 120);
      //Setting amount of people you saved in frame
		JLabel jlAlive = new JLabel("" + (alive));
		jlAlive.setFont(new Font(Font.SERIF, 1, 25));
		jlAlive.setLocation(215, 62);
		jlAlive.setSize(100, 25);
      //Setting text
		JLabel jlLive = new JLabel("LIVES SAVED");
		jlLive.setFont(new Font(Font.SERIF, 1, 25));
		jlLive.setLocation(160, 145);
		jlLive.setSize(300, 25);
      //Setting a sentence of your victory
		JLabel jlWon = new JLabel("YOU WON");
		jlWon.setFont(new Font(Font.SANS_SERIF, 1, 70));
		jlWon.setLocation(65, 200);
		jlWon.setSize(1000, 100);
      //Quit button
		JButton jbQuit = new JButton(new ImageIcon("quit.png"));
		jbQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae1) {
				System.exit(0);
			}
		});
		jbQuit.setSize(230, 80);
		jbQuit.setLocation(120, 320);
      //Restart button
		JButton jbRestart = new JButton(new ImageIcon("restart.png"));
		jbRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae2) {
				Start();
			}
		});
		jbRestart.setSize(230, 80);
		jbRestart.setLocation(120, 420);
      //Combining everything to JFrame
		jpEnd.add(jbRestart);
		jpEnd.add(jbQuit);
		jpEnd.add(jlAlive);
		jpEnd.add(jlLive);
		jpEnd.add(jlLoop);
		jpEnd.add(jlWon);
		jfEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	//Ali

	/**
	 * Method, which says user that he lost
	 * @param x, x-location of a killer
	 * @param y, y-location of a killer
	 * @param alive, amount of people who made it out alive
	 */
	public void Lose(int x, int y, int alive) {
		for (int i = 0; i < 100; i++) {
			player[i].setEnabled(false);
		}

		for (int i = 0; i < 100; i++) {
			if (player[i].getRow() == x && player[i].getCol() == y) {
				player[i].setIcon(new ImageIcon("killer.png"));
				player[i].setEnabled(true);
				player[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae1) {}
				});
				break;
			}
		}

		jbStart.setEnabled(false);
		jbKill.setEnabled(false);
      //Setting text to main text-area
		jta.setText("You now can see where killer was located");
		jfEnd = new JFrame();
      //Enabling icon png
		jfEnd.setIconImage(new ImageIcon("icon.png").getImage());
		jfEnd.setSize(500, 600);
      //Setting location
		jfEnd.setLocation(1100, 135);
		jfEnd.setResizable(false);
		jfEnd.setVisible(true);
      //Adding panels
		JPanel jpEnd = new JPanel();
		jpEnd.setBackground(Color.RED);
		jpEnd.setForeground(Color.RED);
		jpEnd.setLayout(null);
		jpEnd.setOpaque(true);
      //Making it to Center
		jfEnd.add(jpEnd, BorderLayout.CENTER);
      //Adding amount of dead inside a loop picture, it looks beautiful
		JLabel jlGrave = new JLabel(new ImageIcon("grave.png"));
		jlGrave.setLocation(175, 20);
		jlGrave.setSize(100, 120);
      //Setting amount of people you failed to save in frame
		JLabel jlDead1 = new JLabel("" + (100 - alive));
		jlDead1.setFont(new Font(Font.SERIF, 1, 25));
		jlDead1.setLocation(215, 82);
		jlDead1.setSize(100, 25);
      //Setting text
		JLabel jlDead2 = new JLabel("LIVES LOST");
		jlDead2.setFont(new Font(Font.SERIF, 1, 25));
		jlDead2.setLocation(160, 145);
		jlDead2.setSize(300, 25);
      //Setting a sentence of your failure
		JLabel jlDead3 = new JLabel("YOU FAILED");
		jlDead3.setFont(new Font(Font.SANS_SERIF, 1, 70));
		jlDead3.setLocation(40, 200);
		jlDead3.setSize(1000, 100);
      //Quit button
		JButton jbQuit = new JButton(new ImageIcon("quit.png"));
		jbQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae1) {
				System.exit(0);
			}
		});
		jbQuit.setSize(230, 80);
		jbQuit.setLocation(120, 320);
      //Restart button
		JButton jbRestart = new JButton(new ImageIcon("restart.png"));
		jbRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae2) {
				Start();
			}
		});
		jbRestart.setSize(230, 80);
		jbRestart.setLocation(120, 420);
      //Combining everything to JFrame
		jpEnd.add(jbRestart);
		jpEnd.add(jbQuit);
		jpEnd.add(jlDead1);
		jpEnd.add(jlDead2);
		jpEnd.add(jlGrave);
		jpEnd.add(jlDead3);
		jfEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}