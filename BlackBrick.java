
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlackBrick extends Bricks {
	private int health;
		
	public BlackBrick(int x, int y, int w, int h) {
		super(x,y,w,h);
		this.health = 3;
			
		this.setIcon(new ImageIcon(getClass().getResource("blackbrick.png")));
		this.setBounds(x, y, w, h);
	}
		
		
	public boolean reduceHealth() {
			
		return --health <= 0;
		
	}



}