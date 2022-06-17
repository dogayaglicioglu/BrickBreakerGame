
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

public class Game3 extends JPanel implements ActionListener {
	public static boolean gameon = false;
	private int refreshPeriod = 15; // her 15 milisaniyede bir hızları kadar arttırıyor paddle ve topu
	//private static boolean isfinish = false;
	private JFrame game3frame;
	private JLabel level;
	private JLabel lives;
	//private int livess = Menu.lives;
	private JLabel score;
	private Paddle paddle;
	private Ball ball;
	private Bricks [][] BrickBoard;
	private YellowBrick YellowBrick;
	
	
	private int BricknumX = 9;
	private int BricknumY = 3;
	private int allbrickcrashed = BricknumX * BricknumY;

	
	private Timer timer3;
	
	
	public Game3()
	{		
		game3frame = new JFrame("Arkanoid Game Level3");
		game3frame.setFocusable(true);
		game3frame.addKeyListener(new KeyListener() {

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
		game3frame.setFocusTraversalKeysEnabled(false);
		game3frame.setLayout(null);
		game3frame.setFocusable(true);
		game3frame.setBounds(0, 0, 600, 700);
		game3frame.setVisible(true);
		game3frame.setLocationRelativeTo(null);
		game3frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		level = new JLabel("Level 3");
		level.setBounds(201,1,200,20);
		ball = new Ball(225, 400, 20, 21, 7, 7);
		paddle = new Paddle(250, 620, 100, 30, 10);
		score = new JLabel("Score: "+ Menu.score);
		score.setBounds(1, 1, 200, 20);
		//score = new Score(0);
		lives = new JLabel("Lives:" + Menu.lives);
		lives.setBounds(401,1,150,20);
		
		initBrick();
		game3frame.add(lives);
		game3frame.add(level);
		game3frame.add(score);
		game3frame.add(paddle);
		game3frame.add(ball);
		
		
		game3frame.addKeyListener(new KeyListener() {
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
		
		timer3 = new Timer(0, this);
		timer3.setDelay(refreshPeriod);
		
		// START
		gameon = true;
		timer3.start();
	}
	
	public void initBrick() {
		BrickBoard = new Bricks[BricknumX][BricknumY];
			
		for(int i = 0; i < BricknumX; i++) {
			for(int j = 0; j < BricknumY; j++) {
				int xpos = 50 + i * 60;
				int ypos = 50 + j * 40;
				if(j == 0) {
					Bricks BlackBrick = new BlackBrick(xpos,ypos,40,20);
					game3frame.add(BlackBrick);
					BrickBoard[i][j] = BlackBrick;
				}
				else if(j == 1) {
					Bricks YellowBrick = new YellowBrick(xpos,ypos,40,20);
					game3frame.add(YellowBrick);
					BrickBoard[i][j] = YellowBrick;
					
				}
				else {
				Bricks RedBrick = new RedBrick(xpos, ypos, 40, 20);
				game3frame.add(RedBrick);
				BrickBoard[i][j] = RedBrick;
					
				
				}
				
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
				}
				if(Menu.lives == 0) {
				// BALL DROPPED
				timer3.stop();
				game3frame.dispose();
				String name = JOptionPane.showInputDialog("Enter name:");
				/*Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				formatter.format(date);
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 1);
				calendar.set(Calendar.SECOND, 0);
				Date time = calendar.getTime();*/


				
				gamerDetail g = new gamerDetail(name,Menu.score);
				Menu.gamers.add(g);
				//timer3.stop();
				gameon = false;
				}
			}
			if(allbrickcrashed == 0) {
				game3frame.dispose();
				gameon = true;
				timer3.stop();
				
				JFrame f = new JFrame("WIN !!");
				f.setLocationRelativeTo(null);
				f.setLayout(null);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setSize(300,300);
				JButton btn1 = new JButton("QUIT");
				btn1.setBounds(50,140,150,50);
				f.add(btn1);
				btn1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						gameon = false;
						String name = JOptionPane.showInputDialog("Enter name:");
						gamerDetail g = new gamerDetail(name,Menu.score);
					}
					
					
				});
				
				
			}
			
			paddle.update();
			
			// COLLISION DETECTION
			if(ball.intersect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight())){
				// PADDLE HIT
				ball.changeDirY();
			}	
			
			for(int i = 0; i < BricknumX; i++) {
				for(int j = 0; j < BricknumY; j++) {
					Bricks Brick = BrickBoard[i][j];
					if(Brick != null && ball.intersect(Brick.getX(),Brick.getY(),Brick.getWidth(),Brick.getHeight())) {
						
						ball.changeDirY();
						
						if(Brick.reduceHealth()) {
							//System.out.println(Brick.getHealth());
							// RedBrick CRASHED
							allbrickcrashed--;
							BrickBoard[i][j] = null;
							game3frame.remove(Brick);
							game3frame.revalidate();
							game3frame.repaint();

							// INCREASE SCORE BY 10
							Menu.score = Menu.score + 10;
							score.setText("Score : " + Menu.score);

							//score.setScore(score.getScore() + 10);
						}	
					}
				}
			}
		}
	}

}

