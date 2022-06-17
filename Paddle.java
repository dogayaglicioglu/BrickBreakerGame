
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Paddle extends JLabel{
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private int maxSpeed;
	
	public Paddle(int x, int y, int w, int h, int s) {	
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.maxSpeed = s;
		
		this.speed = 0;
		this.setIcon(new ImageIcon(getClass().getResource("paddle.png")));
		this.setBounds(x, y, width, height);	
	}
	
	public void moveRight() {
		speed = maxSpeed;
	}
	
	public void moveLeft() {
		speed = -maxSpeed;
	}
	
	public void stop() {
		speed = 0;
	}
	
	public void update() {
		x += speed;
				
		if(x < 0) {
			x = 0;
		} else if(x > 500) {
			x = 500;
		}

		this.setLocation(x, y);
	}
}
