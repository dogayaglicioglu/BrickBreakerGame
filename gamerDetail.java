
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class gamerDetail {
	private String name;
	private String date;
	private int score;
	
	public gamerDetail(String name,int score) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		//System.out.println(dtf.format(now));  
		String date = dtf.format(now);
		//System.out.println(date);
	    this.date = date; 
		this.name = name;
		this.score = score;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return String.format("%s: %s %s: %s %s: %s\n","Name",name,"Score",score,"Date/Time",date);
		
	}
}
