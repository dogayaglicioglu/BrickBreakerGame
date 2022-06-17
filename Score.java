
import javax.swing.JLabel;

public class Score extends JLabel {
	private int score;
	
	public Score(int score) {
		setScore(score);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
		this.setText("Score : " + score);
		this.setBounds(1, 1, 200, 20);
	}

}