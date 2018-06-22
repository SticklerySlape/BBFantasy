package bbfantasy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeListener implements ActionListener {

	private Window window;
	private int type;
	
	public TypeListener(int type, Window window) {
		this.type = type;
		this.window = window;
	}
	public void actionPerformed(ActionEvent arg0) {
		window.setType(type);
	}

}
