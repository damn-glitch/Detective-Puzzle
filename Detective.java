import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Detective - Class to puzzle
 * @authors Group 3: Mirsaid Abbasov, Alisher Beisembekov, Omar Arafa, Ali Akbar Jafri
 * @version 01/03/2021
 * ISTE-121 Project
 */
public class Detective extends JFrame
{
   //Declaration of Attributes
   private JFrame jf;
   private JPanel jp;
   private JPanel jp1;
   private JPanel jp2;
   private Color color = new Color(3,176,242);
   private Color color2 = new Color(227,194,108);
   private JTextArea   jta1;
   private JTextArea   jta2;
   private JScrollPane jsp;
   private JTextArea   status;
   private JPanel      left;
   private JPanel      down;
   private JPanel      up;
   private JLabel      tries;
   private Icon        human;
   private Font        f;
   private Color       blue;
   private JTextArea   jta;
   private JPanel      jpCenter;
   private JPanel      jpp;
   private JPanel      jpNorth;
   private JPanel      jpSouth;
   private JLabel      jl;
   private JLabel      jlb;
   private JLabel      info;
   private JButton     jbStart;
   private JButton     jbKill;
   private Icon        infopng;
   private int[]       killer;
   private JMenuBar      jmb;
   private JMenu        tutorial;
   private JMenu        game;
   private JMenuItem    rules;
   private JMenuItem    reset;
   
   
   /**
    * Main method, which runs constructor and other methods
    * @param String [] args, which is an array of Strings
    * passed as parameters when you are running
    * your application through command line in the OS
    */
   public static void main(String [] args){
      new Detective();
   }
   
   //Default constructor
   public Detective()
   {
      //Setting settings
      setSize(1200, 700);
      setLocation(400, 250);
      setLayout(new GridLayout(0,3));
      setResizable(false);
      
      //Setting MatrixButton class so players can be generated
      MatrixButton[] player = new MatrixButton[100];
      
      int[] x = new int[23];
      
      //_____________Ali___________
      
      //Instantiating
      jf = new JFrame("Pane3");
      jf.setSize(400,700);
      jf.setLocation(500,50);
      JLabel jlSuggestions = new JLabel("SUGGESTIONS");
      JLabel jlPlayerNotes = new JLabel("PLAYER NOTES");
      JLabel scroll = new JLabel(new ImageIcon("scroll.png"));
      JLabel memo = new JLabel(new ImageIcon("memo6.png"));
      //Creating a panel
      jp = new JPanel();
      jp.setLayout(null);
      jp.setOpaque(true);
      jp.setBackground(new Color(3,176,242));
      jp.setLayout(null);
      
      //Creating a suggestion text-area
      jta1 = new JTextArea();
      jta1.setBackground(new Color(251,205,136));
      jta1.setForeground(Color.BLACK);
      jta1.setFont(new Font(Font.DIALOG,Font.PLAIN , 14));
      jta1.setEditable(false);
      jta1.setSize(170,180);
      jta1.setLocation(105,82);
      //Creating a notes text-area
      jta2 = new JTextArea();
      jta2.setBackground(new Color(133, 133, 133));
      jta2.setForeground(Color.BLACK);
      jta2.setFont(new Font(Font.DIALOG,Font.PLAIN , 14)); 
      jta2.setSize(100,100);
      jta2.setLineWrap(true);
      jta2.setWrapStyleWord(true);
      jsp = new JScrollPane(jta2);
      jsp.setBackground(new Color(255,255,255));
      jsp.setLocation(100,415);
      jsp.setSize(203,210);
      //Adding scroll for notes, so user can type as many info as he wants
      scroll.setSize(260,302);
      scroll.setLocation(62,15);
      //Setting size and location of Label
      memo.setSize(300,407);
      memo.setLocation(45,285);
      //Setting colors for suggestion
      jlSuggestions.setFont(new Font(Font.DIALOG, 140, 18)); 
      jlSuggestions.setBackground(new Color(0,0,0));
      jlSuggestions.setForeground(Color.BLACK);
      jlSuggestions.setSize(200,50);
      jlSuggestions.setLocation(105,44);
      
      //Setting colors for notes
      jlPlayerNotes.setFont(new Font(Font.DIALOG, 140, 18));
      jlPlayerNotes.setBackground(new Color(251,205,136));
      jlPlayerNotes.setForeground(Color.BLACK);
      jlPlayerNotes.setSize(195,55);
      jlPlayerNotes.setLocation(105,374);
      //Combining everything to panel
      jp.add(jlSuggestions);
      jp.add(jlPlayerNotes);
      jp.add(jta1);     
      jp.add(jsp);
      jp.add(scroll);
      jp.add(memo);

      
      infopng = new ImageIcon("Bg (1).png");
      
      
      //_____________Mirsaid___________
      
      //Instantiating
      f      = new Font("Arial Rounded MT Bold", 100,60);
      left   = new JPanel(new BorderLayout());
      down   = new JPanel(new BorderLayout());
      GridLayout layout = new GridLayout(10,10);
      layout.setHgap(0);
      layout.setVgap(0);
      up     = new JPanel(layout);
      blue   = new Color(0,112,192,255);
      human  = new ImageIcon("Screenshot_2021-02-19_140705-removebg-preview (8).png");
      status = new JTextArea("------------------------------\n Day: 0\n Alive: 100"); status.setFont(new Font("Arial Rounded MT Bold", 100,35)); setForeground(Color.BLACK); status.setEditable(false);
      tries = new JLabel(" TRIES: 3"); tries.setFont(f);
      //Setting colors
      getContentPane().setBackground(color);
      left.setBackground(color);
      down.setBackground(Color.GREEN);
      up.setBackground(color);
      status.setBackground(color);
      //Initialing killer
      killer = new int[2];
      //Randomizing killer's position
      killer[0] = randLocKiller(1,10);
      killer[1] = randLocKiller(1,10);
      //Section 2
      jbStart = new JButton("Start");
      jbKill = new JButton("Kill");
      jta = new JTextArea("Welcome to the Detective game, you can press the start button to start or go to tutorial to read rules first", 15, 25);
      //Setting each player
      int yuzfuz = 0;
      for(int i = 0; i < 10; i++)
      {
         for(int j = 0; j < 10; j++)
         {
            player[yuzfuz] = new MatrixButton(human, i, j);
            up.add(player[yuzfuz]);
            player[yuzfuz].setOpaque(false); player[yuzfuz].setContentAreaFilled(false); player[yuzfuz].setBorderPainted(false);
            yuzfuz += 1;
         }
      }
      //Combining down panel
      down.add(status, "North");
      down.add(tries, "South");
      //Combining left panel
      left.add(up, "North");
      left.add(down, "South");
      
      //____________Omar___________
      
      //Instantiating
      jpp = new JPanel(new BorderLayout());
      info = new JLabel(infopng);
      jpCenter = new JPanel(null);
      jpNorth = new JPanel(new FlowLayout());
      jpSouth = new JPanel(new FlowLayout());
          
      //____________Alisher___________
      //Establishing ButtonHandle class for action
      ButtonHandle bh = new ButtonHandle(jta1, killer, status, player, tries, jta, jbStart, jbKill, down, jta2, up);
      //Setting each player to enction and disabling them from the beginning
      for(int i = 0; i < 100; i++)
      {
         player[i].addActionListener( bh );
         player[i].setEnabled(false);
      }
      
      //____________Omar___________
      Color myColor = new Color(191, 191, 191);
      //Creating a label of Start and Kill button
      jl = new JLabel("");

      //Implemeting buttons' design
      jbStart.setIcon( new ImageIcon("Start4.png"));
      jbKill.setIcon( new ImageIcon("Kill (2).png"));
      jbKill.setVisible(false);
      jl.setIcon( new ImageIcon("dett.png"));
      jbStart.setPreferredSize(new Dimension(216,80));
      jbKill.setPreferredSize(new Dimension(216,80));
      jl.setFont(new Font("Calibri", Font.ITALIC, 40));
      jta.setFont(new Font("Calibri", Font.ITALIC, 20));
      jta.setLineWrap(true);
      jta.setWrapStyleWord(true);
      jta.setEditable(false);
      //Setting info and main text-area's bounds
      jta.setBounds(80,80,240,240);
      info.setBounds(-35,-30,500,500);
      //Setting colors of panels
      jta.setBackground(myColor);
      jpCenter.setBackground(color);
      jpNorth.setBackground(color);
      jpSouth.setBackground(color);
      //Adding text-area and buttons to panel
      jpCenter.add(jta);
      jpSouth.add(jbKill, "LEFT");
      jpSouth.add(jbStart);
      jpNorth.add(jl);
      jpCenter.add(info);
      
      jpp.add(jpSouth, "South");
      jpp.add(jpCenter, null);
      jpp.add(jpNorth, "North");
      
      add(left);
      add(jpp);
      add(jp);
      //Providing action to buttons
      jbStart.addActionListener( bh );
      jbKill.addActionListener( bh );
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);      
      //____________Alisher___________
      //setting icon
      setIconImage(new ImageIcon("icon.png").getImage());
      //Creating menubar, menu and menuitems
      jmb = new JMenuBar();
      jmb.setOpaque(true);
      //adding colors
      jmb.setBackground(new Color(3,176,242));
      tutorial = new JMenu("Tutorial");
      rules = new JMenuItem("Rules");
      tutorial.setMnemonic('T');
      rules.setMnemonic('R');
      tutorial.setOpaque(true);
      tutorial.setBackground(new Color(3,176,242));
      tutorial.add(rules);
      jmb.add(tutorial);
      setJMenuBar(jmb);
      //adding actions, so menuitem will work
      rules.addActionListener(bh);
      
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   /**
     * Method, which defines where killer is located
     * @param min : the starting value of the range (inclusive)
     * @param max : the ending value of the range (inclusive)
     * @return pseudoRandom is a pseudo-random integer in the range [min, max]
     */
	public static int randLocKiller(int min, int max) {
		if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
			throw new IllegalArgumentException("Invalid range");
		}
      int pseudoRandom =  new Random().nextInt(max - min + 1) + min;
		return pseudoRandom;
	}
}
