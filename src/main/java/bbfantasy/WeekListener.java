package bbfantasy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeekListener implements ActionListener {

	private int week;
	private Window window;
	
	public WeekListener(int week, Window window) {
		this.week = week;
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		window.setWeek(week);
	}

}
