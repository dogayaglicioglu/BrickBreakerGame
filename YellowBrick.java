
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class YellowBrick extends Bricks{
	private int health;
		
	public YellowBrick(int x, int y, int w, int h) {
		super(x,y,w,h);
		this.health = 2;
			
		this.setIcon(new ImageIcon(getClass().getResource("yellowbrick.png")));
		this.setBounds(x, y, w, h);
	}
		
		
	public boolean reduceHealth() {
		return --health <= 0;
		
	}


}
