
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Ball extends JLabel {
	private int x;
	private int y;
	private int width;
	private int height;
	private int speedX;
	private int speedY;
	
	public Ball(int x, int y, int w, int h, int sx, int sy) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		this.speedX = sx;
		this.speedY = sy;
		
		this.setIcon(new ImageIcon(getClass().getResource("ball.png")));
		this.setBounds(x, y, width, height);
	}
	
	public void changeDirX() {
		speedX = -speedX;
	}
	
	public void changeDirY() {
		speedY = -speedY;
	}
	
	public boolean update(){
		x += speedX;
		y += -speedY;
		
		if(x < 0 || x > 570) {
			changeDirX();
			return true;
		}
		
		if(y < 0) {
			changeDirY();
			return true;
		} 
		else if(y > 700) {
			return false;
		}
		
		this.setLocation(x, y);
		
		return true;
	}
	
	public boolean intersect(int x, int y, int w, int h) {
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(x, y, w, h);
		
		return rect1.intersects(rect2);
	}

}

