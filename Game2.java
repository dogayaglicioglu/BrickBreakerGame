
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game2 extends JPanel implements ActionListener {
	public static boolean gameon = false;
	private int refreshPeriod = 15; // her 15 milisaniyede bir hızları kadar arttırıyor paddle ve topu
	
	private JFrame game2frame;
	private JLabel level;
	private JLabel lives;
	//private int livess = Menu.lives;
	private JLabel score;
	private Paddle paddle2;
	private Ball ball2;
	private Bricks [][] BrickBoard;
	private ImageIcon backg;
	private JLabel myback;
	private int BricknumX = 9;
	private int BricknumY = 3;
	private int allbrickcrashed =  BricknumX * BricknumY;
	
	private Timer timer2;
	
	
	public Game2()
	{	
		//backg = new ImageIcon(this.getClass().getResource("background.png"));
		//myback = new JLabel(backg);
		//myback.setSize(600,700);
		
		/*this.img = image;
		Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);*/
		//((AbstractButton) backGround).setIcon(new ImageIcon(getClass().getResource("background.png")));
		game2frame = new JFrame("Arkanoid Game Level2");
		game2frame.setFocusable(true);
		game2frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) {
					System.exit(1);
				}
					
			}
				
			

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		/*backGround = Toolkit.getDefaultToolkit().getImage("background.png");
		game2frame.setContentPane(new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backGround,0,0,null);
			}
			
		});
		
		game2frame.setContentPane(this);
		game2frame.add(this);
		this.setVisible(true);*/
		//game2frame.getContentPane().add(this);
		game2frame.setFocusTraversalKeysEnabled(false);
		game2frame.setLayout(null);
		game2frame.setFocusable(true);
		game2frame.setSize(600, 700);
		game2frame.setVisible(true);
		game2frame.setLocationRelativeTo(null);
		game2frame.setResizable(false);
		game2frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		level = new JLabel("Level 2");
		level.setBounds(201,1,200,20);
		//game2frame.add(lives);
		//gameon = true;
		ball2 = new Ball(225, 400, 20, 21, 5, 5);
		paddle2 = new Paddle(250, 620, 100, 30, 10);
		score = new JLabel("Score: " + Menu.score);
		score.setBounds(1,1,200,20);
		lives = new JLabel("Lives:" + Menu.lives);
		lives.setBounds(401,1,150,20);
		
		initBrick();
		game2frame.add(lives);
		game2frame.add(level);
		game2frame.add(score);
		game2frame.add(paddle2);
		game2frame.add(ball2);
		//game2frame.add(myback);
		
		game2frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					paddle2.moveRight();	
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle2.moveLeft();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {	
					paddle2.stop();	
				}
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle2.stop();
				}
				
			}	
		});
		
		timer2 = new Timer(0, this);
		timer2.setDelay(refreshPeriod);
		
		// START
		gameon = true;
		timer2.start();
	}
	
	public void initBrick() {
		BrickBoard = new Bricks[BricknumX][BricknumY];
			
		for(int i = 0; i < BricknumX; i++) {
			for(int j = 0; j < BricknumY; j++) {
				int xpos = 50 + i * 60;
				int ypos = 50 + j * 40;
				if(j == 1) {
					Bricks YellowBrick = new YellowBrick(xpos,ypos,40,20);
					game2frame.add(YellowBrick);
					BrickBoard[i][j] = YellowBrick;
					
				}
				else {
					
					Bricks RedBrick = new RedBrick(xpos, ypos, 40, 20);
					game2frame.add(RedBrick);
					BrickBoard[i][j] = RedBrick;
					
				}
				
				
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameon) {
			boolean success = ball2.update();
			if(!success) {
				if(Menu.lives > 0) {
					Menu.lives = Menu.lives - 1;
					lives.setText("Lives : " + Menu.lives);
					ball2.changeDirX();
					ball2.changeDirY();
				}
				if(Menu.lives == 0) {
					// BALL DROPPED
					timer2.stop();
					game2frame.dispose();
					String name = JOptionPane.showInputDialog("Enter name:");
					gamerDetail g = new gamerDetail(name,Menu.score);
					Menu.gamers.add(g);
					gameon = false;
				}
			}
			if(allbrickcrashed == 0) {
				game2frame.dispose();
				gameon = true;
				timer2.stop();
				
				//JOptionPane p = new JOptionPane();
				JFrame f = new JFrame("WIN !!");
				f.setLocationRelativeTo(null);
				f.setLayout(null);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setSize(300,300);
				JButton btn1 = new JButton("QUIT");
				btn1.setBounds(50,70,150,50);
				JButton btn2 = new JButton("Next Level");
				btn2.setBounds(50,140,150,50);
				f.add(btn1);
				f.add(btn2);
				btn2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						Game3 g3 = new Game3();
					}
					
				});
				btn1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						gameon = false;
						String name = JOptionPane.showInputDialog("Enter name:");
						gamerDetail g = new gamerDetail(name,Menu.score);
						Menu.gamers.add(g);
						//System.exit(1);
					}
					
					
				});
				
			}
			
			paddle2.update();
			
			// COLLISION DETECTION
			if(ball2.intersect(paddle2.getX(), paddle2.getY(), paddle2.getWidth(), paddle2.getHeight())){
				// PADDLE HIT
				ball2.changeDirY();
			}	
			
			/*if(allbrickcrashed == 0) {//kırmaya 27 kere girdiyse hepsi kırılmıştır
				game2frame.dispose();
				Game3 g3 = new Game3();
			}*/
			for(int i = 0; i < BricknumX; i++) {
				for(int j = 0; j < BricknumY; j++) {
					Bricks Brick = BrickBoard[i][j];
					if(Brick != null && ball2.intersect(Brick.getX(),Brick.getY(),Brick.getWidth(),Brick.getHeight())) {
							
						ball2.changeDirY();
							
						if(Brick.reduceHealth()) {
							//System.out.println(Brick.getHealth());
								//Brick CRASHED
							allbrickcrashed--;
							BrickBoard[i][j] = null;
							game2frame.remove(Brick);
							game2frame.revalidate();
							game2frame.repaint();
	
							// INCREASE SCORE BY 10
							Menu.score = Menu.score + 10;
							score.setText("Score : " + Menu.score);

							//score2.setScore(score2.getScore() + 10);
						}	
					}
				}
			}
			
			
		}
	}

}

