package bbfantasy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonListener implements ActionListener {

	private String person;
	private Window window;
	
	public PersonListener(String person, Window window) {
		this.person = person;
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		window.setPerson(person);
	}

}
