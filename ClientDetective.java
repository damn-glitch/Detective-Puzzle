import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

/**
 * ClientDetective - Class to puzzle
 * @authors Alisher Beisembekov
 * @version 01/03/2021
 * Project
 */

public class ClientDetective extends JFrame implements ActionListener {
   //Declaration of Attributes
   private JFrame jf;
   private JPanel jp;
   private JPanel jp1;
   private JPanel jp2;
   private Color color = new Color(3, 176, 242);
   private Color color2 = new Color(227, 194, 108);
   private JTextArea jta1;
   private JTextArea jta2; //9
   private JScrollPane jsp;
   private JTextArea status; //3
   private JPanel left;
   private JPanel down; //8
   private transient JPanel up; //10
   private JLabel tries; //5
   private Icon human;
   private Font f;
   private Color blue;
   private JTextArea jta; //1
   private JPanel jpCenter;
   private JPanel jpp;
   private JPanel jpNorth;
   private JPanel jpSouth;
   private JLabel jl;
   private JLabel jlb;
   private JLabel info;
   private transient JButton jbStart; //6
   private transient JButton jbKill; //7
   private Icon infopng;
   private int[] killer; //2
   private JMenuBar jmb;
   private JMenu tutorial;
   private JMenu game;
   private JMenuItem rules;
   private JMenuItem reset;
   private ObjectOutputStream dos;
   private ObjectInputStream ois;
   private MatrixButton[] player; //4
   private JFrame jfEnd;

   private JMenu jmNetwork;
   private JMenuItem jmChat;
   private JMenuItem jmLeaderBoard;
   
   private Socket s = null;
   private BufferedReader br;
   private PrintWriter pw;
   private JTextArea jtaChat;
   private JScrollPane jspChat;
   private JButton jbSend;
   private JTextField jtfChat;
   private JMenuBar jmbChat;
   private JMenu jmFile;
   private JMenuItem jmiExit;

   /**
    * Main method, which runs constructor and other methods
    * @param String [] args, which is an array of Strings
    * passed as parameters when you are running
    * your application through command line in the OS
    */
   public static void main(String[] args) {
      new ClientDetective();
   }

   //Default constructor
   public ClientDetective() {
      //Setting settings
      setSize(1200, 700);
      setLocation(400, 250);
      setLayout(new GridLayout(0, 3));
      //setResizable(false);

      //Setting MatrixButton class so players can be generated
      MatrixButton[] player = new MatrixButton[100];

      int[] x = new int[23];

      //_____________Ali___________

      //Instantiating
      jf = new JFrame("Pane3");
      jf.setSize(400, 700);
      jf.setLocation(500, 50);
      JLabel jlSuggestions = new JLabel("SUGGESTIONS");
      JLabel jlPlayerNotes = new JLabel("PLAYER NOTES");
      JLabel scroll = new JLabel(new ImageIcon("scroll.png"));
      JLabel memo = new JLabel(new ImageIcon("memo6.png"));
      //Creating a panel
      jp = new JPanel();
      jp.setLayout(null);
      jp.setOpaque(true);
      jp.setBackground(new Color(3, 176, 242));
      jp.setLayout(null);

      //Creating a suggestion text-area
      jta1 = new JTextArea();
      jta1.setBackground(new Color(251, 205, 136));
      jta1.setForeground(Color.BLACK);
      jta1.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
      jta1.setEditable(false);
      jta1.setSize(170, 180);
      jta1.setLocation(105, 82);
      //Creating a notes text-area
      jta2 = new JTextArea();
      jta2.setBackground(new Color(133, 133, 133));
      jta2.setForeground(Color.BLACK);
      jta2.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
      jta2.setSize(100, 100);
      jta2.setLineWrap(true);
      jta2.setWrapStyleWord(true);
      jsp = new JScrollPane(jta2);
      jsp.setBackground(new Color(255, 255, 255));
      jsp.setLocation(100, 415);
      jsp.setSize(203, 210);
      //Adding scroll for notes, so user can type as many info as he wants
      scroll.setSize(260, 302);
      scroll.setLocation(62, 15);
      //Setting size and location of Label
      memo.setSize(300, 407);
      memo.setLocation(45, 285);
      //Setting colors for suggestion
      jlSuggestions.setFont(new Font(Font.DIALOG, 140, 18));
      jlSuggestions.setBackground(new Color(0, 0, 0));
      jlSuggestions.setForeground(Color.BLACK);
      jlSuggestions.setSize(200, 50);
      jlSuggestions.setLocation(105, 44);

      //Setting colors for notes
      jlPlayerNotes.setFont(new Font(Font.DIALOG, 140, 18));
      jlPlayerNotes.setBackground(new Color(251, 205, 136));
      jlPlayerNotes.setForeground(Color.BLACK);
      jlPlayerNotes.setSize(195, 55);
      jlPlayerNotes.setLocation(105, 374);
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
      f = new Font("Arial Rounded MT Bold", 100, 60);
      left = new JPanel(new BorderLayout());
      down = new JPanel(new BorderLayout());
      GridLayout layout = new GridLayout(10, 10);
      layout.setHgap(0);
      layout.setVgap(0);
      up = new JPanel(layout);
      blue = new Color(0, 112, 192, 255);
      human = new ImageIcon("Screenshot_2021-02-19_140705-removebg-preview (8).png");
      status = new JTextArea("------------------------------\n Day: 0\n Alive: 100");
      status.setFont(new Font("Arial Rounded MT Bold", 100, 35));
      setForeground(Color.BLACK);
      status.setEditable(false);
      tries = new JLabel(" TRIES: 3");
      tries.setFont(f);
      //Setting colors
      getContentPane().setBackground(color);
      left.setBackground(color);
      down.setBackground(Color.GREEN);
      up.setBackground(color);
      status.setBackground(color);
      //       //Initialing killer
      // //       killer = new int[2];
      // //       //Randomizing killer's position
      // //       killer[0] = randLocKiller(1,10);
      // //       killer[1] = randLocKiller(1,10);
      //Section 2
      jbStart = new JButton("Start");
      jbKill = new JButton("Kill");
      jta = new JTextArea("Welcome to the ClientDetective game, you can press the start button to start or go to tutorial to read rules first", 15, 25);
      //Setting each player
      int yuzfuz = 0;
      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            player[yuzfuz] = new MatrixButton(human, i, j);
            up.add(player[yuzfuz]);
            player[yuzfuz].setOpaque(false);
            player[yuzfuz].setContentAreaFilled(false);
            player[yuzfuz].setBorderPainted(false);
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

      //Setting each player to enction and disabling them from the beginning
      for (int i = 0; i < 100; i++) {
         player[i].addActionListener(this);
         player[i].setEnabled(false);
      }

      //____________Omar___________
      Color myColor = new Color(191, 191, 191);
      //Creating a label of Start and Kill button
      jl = new JLabel("");

      //Implemeting buttons' design
      jbStart.setIcon(new ImageIcon("Start4.png"));
      jbKill.setIcon(new ImageIcon("Kill (2).png"));
      jbKill.setVisible(false);
      jl.setIcon(new ImageIcon("dett.png"));
      jbStart.setPreferredSize(new Dimension(216, 80));
      jbKill.setPreferredSize(new Dimension(216, 80));
      jl.setFont(new Font("Calibri", Font.ITALIC, 40));
      jta.setFont(new Font("Calibri", Font.ITALIC, 20));
      jta.setLineWrap(true);
      jta.setWrapStyleWord(true);
      jta.setEditable(false);
      //Setting info and main text-area's bounds
      jta.setBounds(80, 80, 240, 240);
      info.setBounds(-35, -30, 500, 500);
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
      jbStart.addActionListener(this);
      jbKill.addActionListener(this);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      //____________Alisher___________
      //setting icon
      setIconImage(new ImageIcon("icon.png").getImage());
      //Creating menubar, menu and menuitems
      jmb = new JMenuBar();
      jmb.setOpaque(true);
      //adding colors
      jmb.setBackground(new Color(3, 176, 242));
      tutorial = new JMenu("Tutorial");
      rules = new JMenuItem("Rules");
      tutorial.setMnemonic('T');
      rules.setMnemonic('R');
      tutorial.setOpaque(true);
      tutorial.setBackground(new Color(3, 176, 242));
      tutorial.add(rules);
      jmb.add(tutorial);
      setJMenuBar(jmb);
      //adding actions, so menu item will work
      rules.addActionListener(this);

      jmNetwork = new JMenu("Network");
      jmChat = new JMenuItem("Chat");
      jmNetwork.setMnemonic('N');
      jmChat.setMnemonic('C');
      jmNetwork.setOpaque(true);
      jmNetwork.setBackground(new Color(3, 176, 242));
      jmNetwork.add(jmChat);
      jmb.add(jmNetwork);
      jmChat.addActionListener(this);
      
      jmLeaderBoard = new JMenuItem("LeaderBoard");
      jmLeaderBoard.setMnemonic('L');
      jmNetwork.add(jmLeaderBoard);
      jmLeaderBoard.addActionListener(this);
      

      try {
         //Creating thread and making connetion
         s = new Socket("localhost", 1234);

         ois = new ObjectInputStream(s.getInputStream());
         dos = new ObjectOutputStream(s.getOutputStream());

      } catch (IOException e) {
         System.out.print("IO Exception happened");
      }
      //       catch(UnknownHostException uhe)
      //       {
      //          System.out.print("IO Exception happened");
      //       }

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }

   public void actionPerformed(ActionEvent e) {
      try {
         dos.writeObject(jta1); //1
         dos.flush();
         dos.writeObject(killer); //2
         dos.flush();
         dos.writeObject(status); //3
         dos.flush();
         dos.writeObject(player); //4
         dos.flush();
         dos.writeObject(tries); //5
         dos.flush();
         dos.writeObject(jta); //X
         dos.flush();
         System.out.print("BEFORE - ");
         // dos.writeObject(jbStart);//6 causing exception
         // dos.flush();
         System.out.println("AFTER");
         //dos.writeObject(jbKill);//7 causing exception
         //dos.flush();
         dos.writeObject(down); //8
         dos.flush();
         dos.writeObject(jta2); //9
         dos.flush();
         //dos.writeObject(up);//10  causing exception
         //dos.flush();
         dos.writeObject(e); //11
         dos.flush();

         JTextArea jta1R = (JTextArea) ois.readObject();
         jta1 = jta1R;
         int[] killerR = (int[]) ois.readObject();
         killer = killerR;
         JTextArea statusR = (JTextArea) ois.readObject();
         status = statusR;
         MatrixButton[] playerR = (MatrixButton[]) ois.readObject();
         player = playerR;
         JLabel triesR = (JLabel) ois.readObject();
         tries = triesR;
         JTextArea jtaR = (JTextArea) ois.readObject();
         jta = jtaR; //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
         System.out.println(jta.getText());
         JButton jbStartR = (JButton) ois.readObject();
         jbStart = jbStartR;
         JButton jbKillR = (JButton) ois.readObject();
         jbKill = jbKillR;
         JPanel downR = (JPanel) ois.readObject();
         down = downR;
         JTextArea jta2R = (JTextArea) ois.readObject();
         jta2 = jta2R;
         JPanel upR = (JPanel) ois.readObject();
         up = upR;

         int _alive = Integer.parseInt((String) ois.readObject());

         String commander = (String) ois.readObject();

         if (commander.equals("T")) {
            Tutorial();

         } else if (commander.equals("Ch")) {
            //Creating GUI
            JFrame jfChat = new JFrame("Chat");
            jfChat.setSize(400, 500);
            jfChat.setLocation(500, 200);
            jfChat.setIconImage(new ImageIcon("Chat.png").getImage());
            //Adding listener as anonymous inner class, so the information of closed connection will be showed
            jfChat.addWindowListener(
               new WindowAdapter() {
                  public void windowClosing(WindowEvent we) {
                     System.out.println("Connection Closed");

                     try {
                        s.close();
                     } catch (Exception e) {}

                     jfChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  }
               }
            );
            //Adding listener as anonymous inner class, so the information of not connected to server will be showed
            jfChat.addKeyListener(
               new KeyAdapter() {
                  public void keyReleased(KeyEvent ke) {

                     System.out.println("AAAAA");

                     if (ke.getKeyCode() == 10) {
                        System.out.println("XXXXXXX");

                        try {
                           pw.println(jtfChat.getText());
                           pw.flush();
                           jtfChat.setText("");
                        } catch (Exception e1) {
                           jtaChat.append("You are not connected to the server! \nEither the server is off or an error occored while Connecting.\n");
                        }
                     }
                  }
               }
            );
            //Continuation of GUI
            JPanel jpChat = new JPanel();
            jpChat.setSize(360, 400);
            jpChat.setBackground(new Color(77, 162, 255));

            jtfChat = new JTextField(22);
            jtfChat.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
            jtfChat.setFont(new Font("Arial", Font.PLAIN, 17));
            jtfChat.setBackground(new Color(210, 232, 251));

            jbSend = new JButton(new ImageIcon("send4.png"));
            jbSend.setBackground(new Color(238, 238, 238));
            jbSend.setOpaque(false);
            jbSend.setContentAreaFilled(false);
            jbSend.setBorderPainted(false);
            jbSend.setSize(40, 40);
            //Adding listener as anonymous inner class, so the information of not connected to server will be showed
            jbSend.addActionListener(
               new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {
                     try {
                        pw.println(jtfChat.getText());
                        pw.flush();
                        jtfChat.setText("");
                     } catch (Exception e) {
                        jtaChat.append("You are not connected to the server! \nEither the server is off or an error occored while Connecting.\n");
                     }
                  }
               }
            );

            //Adding menu
            jmbChat = new JMenuBar();
            jmFile = new JMenu("File");
            jmiExit = new JMenuItem("Exit");
            jmFile.add(jmiExit);
            jmbChat.add(jmFile);
            //Adding listener as anonymous inner class, for exitting client
            jmiExit.addActionListener(
               new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {
                     jfChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  }
               });

            //Continuation of GUI
            jtaChat = new JTextArea(10, 15);
            jtaChat.setEditable(false);
            jtaChat.setLineWrap(true);
            jtaChat.setWrapStyleWord(true);
            jtfChat.setFont(new Font("Arial", Font.PLAIN, 17));
            jtaChat.setBackground(new Color(210, 232, 251));
            jspChat = new JScrollPane(jtaChat);

            jpChat.add(jtfChat);
            jpChat.add(jbSend);

            jfChat.add(jspChat, BorderLayout.CENTER);
            jfChat.add(jpChat, BorderLayout.SOUTH);
            jfChat.setJMenuBar(jmbChat);
            jfChat.setVisible(true);
            jfChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            boolean serverConnected = false;
            //Connecting to the socket
            try
            {
               s = new Socket("localhost",1234);
               br = new BufferedReader(new InputStreamReader(s.getInputStream()));
         	   pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
               pw.println("connectme");
               pw.flush();
               jtfChat.setEditable(true);
               jbSend.setEnabled(true);
               serverConnected = true;
               //Adding from the socket to the JTextArea
               while(true)
               {
                  jtaChat.append(br.readLine()+"\n");
               }
            } 
            catch(UnknownHostException uhe)
            { 
               JOptionPane.showMessageDialog(null, "Unable to connect to host.");
               jtfChat.setEditable(false);
               jbSend.setEnabled(false);
               serverConnected = true;
            } 
            catch(IOException ioe)
            { 
               JOptionPane.showMessageDialog(null, "Unable to connect to host.");
               jtfChat.setEditable(false);
               jbSend.setEnabled(false);
               serverConnected = true;
            } 
            //Connection to the server
            while(serverConnected){
               try
               {
                  while(true){
                     s = new Socket("localhost",1234); //Socket initiziling with port
                     br = new BufferedReader(new InputStreamReader(s.getInputStream()));
               	   pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                     pw.println("connectme");
                     pw.flush();
                     
                     if(s != null){
                        JOptionPane.showMessageDialog(null, "Connection to server regained");
                        break;
                     }
                  
                  }
                  jtfChat.setEditable(true);
                  jbSend.setEnabled(true);
                  serverConnected = true;
                  //Adding from the socket to the JTextArea
                  while(true)
                  {
                     jtaChat.append(br.readLine()+"\n");
                  }
               } 
               catch(Exception E){}
            }
         } else if(commander.equals("Le")){
            //LeaderBoard(_alive);
         } else if (commander.equals("L")) {
            Lose(killer[0], killer[1], _alive); //ALIVE
         } else if (commander.equals("W")) {
            Win(_alive);
         } else if (commander.equals("E")) {
            jfEnd.setVisible(false);
         } else if (commander.equals("S")){
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
            tries.setText(" TRIES: 3");
            down.setBackground(Color.GREEN);
            // // 		jfEnd.setVisible(false);////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            jbStart.setEnabled(true);
            jbKill.setEnabled(true);

         }

         // status.setVisible();
         //jta1.setVisible(false);
         //  jta1.requestFocusInWindow();
         status.repaint();
         // status.requestFocusInWindow();
         tries.revalidate();
         //  tries.requestFocusInWindow();
         jta.repaint();
         //  jta.requestFocusInWindow();
         jbStart.repaint();
         //  jbStart.requestFocusInWindow();
         // jbKillR.setVisible(true); causing exception
         // jbKill.requestFocusInWindow();
         down.revalidate();
         //  down.requestFocusInWindow();
         jta2.repaint();
         //  jta2.requestFocusInWindow();
         //  upR.setVisible(true); //causing exception
         //up.requestFocusInWindow(); 
      } catch (ClassNotFoundException cnfe) {
         System.out.println("Class Not Found Exception Occured!");
      } catch (IOException ioe) {
         System.out.println("IO Exception Occured!");
         System.out.print(ioe.getMessage());
      }

   }
   //Alisher

	/**
	 * Method, which opens a tutorial window
	 */
   public void Tutorial(){
      //Setting a new frame
            JFrame jfrm2 = new JFrame("Tutorial");
            jfrm2.setSize(1000, 600);
            //Writing tutorial
            JTextArea jta = new JTextArea("In this game, you should find the killer from the suggestions of the people. In the suggestions, they will be three attributes from which you could understand where the killer is located. The first is Distance, the second is Direction and the third one is Accuracy, which is kind of most important part. \nDistance - This shows you how far killer located from the person you choose. This will indicate the row or column in which the killer can locate, it will not show you the exact place of the killer. It could be from 0 to 10 or NO DATA. \nDirection - This shows you in which direction the Killer is located from the person you choose. It could be \"Up\", \"Down\", \"Left\" and \"Right\".\nAccuracy - From the accuracy you should understand is the date mentioned above true or not. They will not differ in numbers like if the accuracy is low, it means distance will be -1 or +1 accurate. IT IS NOT WORKING LIKE THAT. Here is how it is working:\n1. 30% - 60% = that means the information which is given is completely random and most cases will be wrong.\n2. 60% - 90% = that means you will see the direction and it will be 100% correct, but the distance will be \"NO DATA\".\n3. 90% - 100% = that means you will have both distance and direction and they will be 100% correct.\nHow to win: Find where killer is located and kill him.\nHow you can lose:\n1. Each time you ask someone for a suggestion the day counter increases. If the day is 10, you will lose.\n2. You have a chance to kill 3 times, but if the tries are 0 and you did not kill the killer, you will lose.\n\nAdditional game mechanics:\n1. After 6 days killer will kill someone in the distance of 2 from him diagonally(Only 2, no 1).\n2. If you will ask someone for a suggestion, you will not be able to ask him again.\n3. During the game Text in the center of the app will help you to play by giving you tips.\n4. Fist 3 suggestion's accuracy always will be in the range of 60% - 90%.\n\nThat is all, GOOD LUCK.", 5, 10);
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
   
   public void LeaderBoard(){
      
   }
   
   //Omar modified by Alisher

   /**
    * Method, which congrats user of winning this game
    * @param alive, amount of people user saved from killer
    */
   public void Win(int alive) {
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
      //jfEnd = new JFrame();
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
      jbRestart.setText("Start");
      jbRestart.addActionListener(this);
      // 			public void actionPerformed(ActionEvent ae2) {
      // 				//Start();//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      // 			}
      // 		});
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

   //Ali Akbar

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
      //jfEnd = new JFrame();
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
      jbRestart.setText("Start");
      jbRestart.addActionListener(this);
      // 			//public void actionPerformed(ActionEvent ae2) {
      // 				//Start();//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      //             
      //             
      // 			}
      // 		});
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

   // //    /**
   // //      * Method, which defines where killer is located
   // //      * @param min : the starting value of the range (inclusive)
   // //      * @param max : the ending value of the range (inclusive)
   // //      * @return pseudoRandom is a pseudo-random integer in the range [min, max]
   // //      */
   // //    public static int randLocKiller(int min, int max) {
   // //       if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
   // //          throw new IllegalArgumentException("Invalid range");
   // //       }
   // //       int pseudoRandom =  new Random().nextInt(max - min + 1) + min;
   // //       return pseudoRandom;
   // //    }

   class MatrixButton extends JButton {
      //Attributes
      private final int row;
      private final int col;
      /**
       * Constructor, which activates matrix button
       * @param t is an icon(image) of player
       * @param col is a column position of player
       * @param row is a row position of player
       */
      public MatrixButton(Icon t, int col, int row) {
         super(t);
         this.row = row;
         this.col = col;
      }
      /**
       * Method, which returns a row position of player
       * @return (row + 1), a row position of player
       */
      public int getRow() {
         return row + 1;
      }
      /**
       * Method, which returns a column position of player
       * @return (col + 1), a column position of player
       */
      public int getCol() {
         return col + 1;
      }
   }
}
