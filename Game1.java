
import java.awt.Color;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game1 extends JPanel implements ActionListener {
	public static boolean gameon = false;
	private int refreshPeriod = 15; // her 15 milisaniyede bir hızları kadar arttırıyor paddle ve topu
	private JFrame gameframe;
	private boolean gameresult;
	private JLabel level;
	private JLabel lives;
	private JLabel score;
	private Paddle paddle;
	private Ball ball;
	private Bricks [][] RedBrickBoard;
	
	private int RedBricknumX = 9;
	private int RedBricknumY = 3;
	private int allbrickcrashed =  RedBricknumX * RedBricknumY;
	
	private Timer timer;
	
	public Game1()
	{
		gameframe = new JFrame("Arkanoid Game Level1");
		gameframe.setFocusable(true);
		//gameframe.getContentPane(setBackground(Color.orange));
		gameframe.addKeyListener(new KeyListener() {

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
		gameframe.setFocusTraversalKeysEnabled(false);
		gameframe.setLayout(null);
		gameframe.setFocusable(true);
		gameframe.setBounds(0, 0, 600, 700);
		gameframe.setVisible(true);
		gameframe.setLocationRelativeTo(null);
		gameframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		level = new JLabel("Level 1");
		level.setBounds(201,1,200,20);
		ball = new Ball(225, 400, 20, 21, 3, 3);
		paddle = new Paddle(250, 620, 100, 30, 10);
		score = new JLabel("Score:" + Menu.score);
		score.setBounds(1,1,200,20);
		lives = new JLabel("Lives:" + Menu.lives);
		lives.setBounds(401,1,150,20);
		initRedBrick();
		gameframe.add(lives);
		gameframe.add(score);
		gameframe.add(paddle);
		gameframe.add(ball);
		gameframe.add(level);
		gameframe.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					paddle.moveRight();	
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle.moveLeft();
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {	
					paddle.stop();	
				}
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle.stop();
				}
				
			}	
		});
		
		timer = new Timer(0, this);
		timer.setDelay(refreshPeriod);
		
		// START
		gameon = true;
		timer.start();
	}
	
	public void initRedBrick() {
		RedBrickBoard = new RedBrick[RedBricknumX][RedBricknumY];
			
		for(int i = 0; i < RedBricknumX; i++) {
			for(int j = 0; j < RedBricknumY; j++) {
				int xpos = 50 + i * 60;
				int ypos = 50 + j * 40;
				RedBrick RedBrick = new RedBrick(xpos, ypos, 40, 20);
				
				gameframe.add(RedBrick);
				RedBrickBoard[i][j] = RedBrick;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameon) {
			
			boolean success = ball.update();
			if(!success) {
				if(Menu.lives > 0) {
					Menu.lives = Menu.lives - 1;
					lives.setText("Lives : " + Menu.lives);
					ball.changeDirX();
					ball.changeDirY();
					//ball.setBounds(225, 400, 20, 21);
				}
				if(Menu.lives == 0) {
				// BALL DROPPED
				gameframe.dispose();
				String name = JOptionPane.showInputDialog("Enter name:");
				gamerDetail g = new gamerDetail(name,Menu.score);
				Menu.gamers.add(g);
				timer.stop();
				gameon = false;
				gameresult = false;
				}
				
			}
			if(allbrickcrashed == 0) {
				gameframe.dispose();
				gameon = true;
				timer.stop();
				
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
						Game2 g2 = new Game2();
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
			
			paddle.update();
			
			// COLLISION DETECTION
			if(ball.intersect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight())){
				// PADDLE HIT
				ball.changeDirY();
			}	
			
			for(int i = 0; i < RedBricknumX; i++) {
				for(int j = 0; j < RedBricknumY; j++) {
					Bricks RedBrick = RedBrickBoard[i][j];
					if(RedBrick != null && ball.intersect(RedBrick.getX(), RedBrick.getY(), RedBrick.getWidth(), RedBrick.getHeight())) {
						
						ball.changeDirY();
						
						if(RedBrick.reduceHealth()) {
							// RedBrick CRASHED
							allbrickcrashed--;
							RedBrickBoard[i][j] = null;
							gameframe.remove(RedBrick);
							gameframe.revalidate();
							gameframe.repaint();

							// INCREASE SCORE BY 10
							//Menu.score++;
							Menu.score = Menu.score + 10;
							score.setText("Score : " + Menu.score);

						}	
					}
				}
			}
		}
		
	}

	public boolean isGameresult() {
		return gameresult;
	}

	public void setGameresult(boolean gameresult) {
		this.gameresult = gameresult;
	}
	
	

}

