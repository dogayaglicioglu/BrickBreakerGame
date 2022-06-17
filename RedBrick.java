
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RedBrick extends Bricks {
	private int health;
		
	public RedBrick(int x, int y, int w, int h) {
		super(x,y,w,h);
		this.health = 1;
			
		this.setIcon(new ImageIcon(getClass().getResource("redbrick.png")));
		this.setBounds(x, y, w, h);
	}
		
		
	public boolean reduceHealth() {
			
		return --health <= 0;
		
	}



}
