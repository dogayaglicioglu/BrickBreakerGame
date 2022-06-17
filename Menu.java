
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Menu{
	public static int gamenum = 0;
	public static int lives = 3;
	public static int score = 0;
	public static ArrayList<gamerDetail>gamers = new ArrayList<gamerDetail>();

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Game Menu");
		frame.setFocusable(true);
		JButton newgame = new JButton("New Game");
		newgame.setForeground(Color.red);
		JButton options = new JButton("Options");
		options.setForeground(Color.red);
		JButton scores = new JButton("Scores");
		scores.setForeground(Color.red);
		JButton help = new JButton("Help");
		help.setForeground(Color.red);
		JButton about = new JButton("About");
		about.setForeground(Color.red);
		JButton exit = new JButton("Exit");
		exit.setForeground(Color.red);
		//OPTIONS
		newgame.setBounds(150,50,150,50);
		options.setBounds(150,120,150,50);
		scores.setBounds(150,190,150,50);
		help.setBounds(150,260,150,50);
		about.setBounds(150,330,150,50);
		exit.setBounds(150,400,150,50);

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) {
					System.exit(1);
				}
					
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		frame.setBounds(0,0, 500, 500);
		frame.add(newgame);
		frame.add(options);
		frame.add(scores);
		frame.add(help);
		frame.add(about);
		frame.add(exit);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		newgame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lives = 3;
				score = 0;
				Game1 newgamm = new Game1();
			}
				
		}); 
		
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame helpframe = new JFrame();
				helpframe.setLocationRelativeTo(null);
				String about = "Player must smash a wall of brick by deflecting a bouncing\n"
						+ "ball with a paddle.\n"
						+ "The paddle maybe move horizontally and is controlled\n"
						+ "with the computer’s mouse or keyboard.\n"
						+ "The player gets 3 lives to start with; a life is lost if the\n"
						+ "ball hits the bottom of the screen.\n"
						+ "When all the bricks have been destroyed, the player advances\n"
						+ "to a new, harder level.\n"
						+ "If all lives are lost, the game is over.";
				JTextArea helpp = new JTextArea(about,0,0);
				helpp.setEditable(false);
				helpp.setSize(390,200);
				helpframe.add(helpp);
				helpframe.setSize(390, 200);
				frame.setResizable(false);
				helpframe.setLayout(null);
				helpframe.setVisible(true);
				
			}
		});
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame aboutframe = new JFrame();
				aboutframe.setLocationRelativeTo(null);
				String about = "DEVELOPER'S INFOS\n"
						+ "NAME/SURNAME: DOĞA YAĞLICIOĞLU\n"
						+ "SCHOOL NUMBER: 20180702065\n"
						+ "EMAIL: dogayaglicioglu@std.yeditepe.edu.tr\n";
						
				JTextArea aboutt = new JTextArea(about,0,0);
				aboutt.setEditable(false);
				aboutt.setSize(390,200);
				aboutframe.add(aboutt);
				aboutframe.setSize(390, 200);
				aboutframe.setResizable(false);
				aboutframe.setLayout(null);
				aboutframe.setVisible(true);
				
			}
			
		});
		options.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame gameopt = new JFrame("Select game level");
				gameopt.setFocusable(true);
				JButton level1 = new JButton("Level1");
				level1.setForeground(Color.BLUE);
				level1.setBounds(50,50,150,50);
				JButton level2 = new JButton("Level2");
				level2.setForeground(Color.BLUE);
				level2.setBounds(50,120,150,50);
				JButton level3 = new JButton("Level3");
				level3.setForeground(Color.BLUE);
				level3.setBounds(50,190,150,50);
				gameopt.setSize(300,300);
				gameopt.add(level1);
				gameopt.add(level2);
				gameopt.add(level3);
				gameopt.setLocationRelativeTo(null);
				gameopt.setResizable(false);
				gameopt.setLayout(null);
				gameopt.setVisible(true);
				gameopt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gameopt.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyPressed(KeyEvent e) {
						if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) {
							System.exit(1);
						}	
					}
					@Override
					public void keyReleased(KeyEvent e) {}
					
				});
				
				level1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						lives = 3;
						score = 0;
						gamenum++;
						gameopt.dispose();
						Game1 g = new Game1();
						
					}
					
				});
				level2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						lives = 3;
						score = 0;
						gamenum++;
						gameopt.dispose();
						Game2 g2 = new Game2();
					}
					
				});
				level3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						lives = 3;
						score = 0;
						gamenum++;
						gameopt.dispose();
						Game3 g3 = new Game3();
						
						
					}
					
				});
			}
			
		});
		scores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame scoreframe = new JFrame("LAST 10 SCORES");
				scoreframe.setVisible(true);
				scoreframe.setLocationRelativeTo(null);
				scoreframe.setSize(400,400);
				scoreframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				String lines = "";
				int maxten = 0;
				String ss = "";
				File file = new File("score.txt"); // skorları dosyaya yazma
				try {
					PrintWriter writer = new PrintWriter((new FileOutputStream(file, true)));
					
					for(gamerDetail iter: gamers) {
						writer.write(iter.toString());
					}
					
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Scanner f;
				try {
					f = new Scanner(file).useDelimiter(",\\s*");
					List<String> lisst = new ArrayList<String>();
					while(f.hasNext()) {
						ss = f.next();
						lisst.add(ss);
					}
					f.close();
					String[] l = lisst.toArray(new String[0]);
					for(String m: l) {
						lines = lines+m+"\n";
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JTextArea scorearea = new JTextArea();
				scorearea.setText(ss);
				scorearea.setEditable(false);
				scorearea.setVisible(true);
				scorearea.setSize(400,400);
				scoreframe.add(scorearea);				
			}
			
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
				
			}
			
		});
	}

}


