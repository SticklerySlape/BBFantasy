package bbfantasy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PointListener implements ActionListener {

	private SheetReadWrite readWrite;
	private String range;
	private int category;
	private Window window;
	
	public PointListener(int category, Window window) {
		this.window = window;
		this.readWrite = window.getReadWrite();
		this.category = category;
	}
	public void actionPerformed(ActionEvent arg0) {
		range = "Week" + window.getWeek() + "!";
		switch(window.getPerson()) {
		case "Steve":
			range += "B" + category;
			break;
		case "Winston":
			range += "C" + category;
			break;
		case "JC":
			range += "D" + category;
			break;
		case "Brett":
			range += "E" + category;
			break;
		case "Tyler":
			range += "F" + category;
			break;
		case "Chris":
			range += "G" + category;
			break;
		case "Scottie":
			range += "H" + category;
			break;
		case "Faysal":
			range += "I" + category;
			break;
		case "Sam":
			range += "J" + category;
			break;
		case "Angie":
			range += "K" + category;
			break;
		case "Haleigh":
			range += "L" + category;
			break;
		case "Kaycee":
			range += "M" + category;
			break;
		case "Angela":
			range += "N" + category;
			break;
		case "Bayleigh":
			range += "O" + category;
			break;
		case "Kaitlyn":
			range += "P" + category;
			break;
		case "Rachel":
			range += "Q" + category;
			break;
		}
		try {
			readWrite.write(range);
			readWrite.append(window.getPerson() + " gets points for \"" + readWrite.read("Week1!A" + category) + "\" on week " + window.getWeek() + ".");
		} catch (IOException e) {
			System.out.println("Error in writing value");
			e.printStackTrace();
		}
		//write to log (e.g. "Player gets points for \"" + readwriter.read("Week1!A" + category + ":A" + category) + "\"")
	}
}
