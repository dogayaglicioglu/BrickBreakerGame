
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bricks extends JLabel {
	private int x;
	private int y;
	private int width;
	private int height;
	private int health;
		
	public Bricks(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
			
		//this.setIcon(new ImageIcon(getClass().getResource("images.png")));
		//this.setBounds(x, y, width, height);
	}
		
		
	public boolean reduceHealth() {
			
		return --health <= 0;
		
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	

}
