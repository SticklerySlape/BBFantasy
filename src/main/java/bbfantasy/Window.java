package bbfantasy;
import javax.swing.JPanel;

import com.google.api.client.util.store.FileDataStoreFactory;

import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String person = "Steve";
	private int week = 1;
	private int type = 1;
	private SheetReadWrite readWrite;
	private JPanel panel;
	private JPanel panelTop;
	private JPanel panelCenter = new JPanel(new MigLayout("", "170[][]40", "40[][][][][][]20"));
	private JPanel panelBottom = new JPanel(new MigLayout());
	private JButton w1 = new JButton("Week 1"); private JButton w2 = new JButton("Week 2");
	private JButton w3 = new JButton("Week 3"); private JButton w4 = new JButton("Week 4");
	private JButton w5 = new JButton("Week 5"); private JButton w6 = new JButton("Week 6");
	private JButton w7 = new JButton("Week 7"); private JButton w8 = new JButton("Week 8");
	private JButton w9 = new JButton("Week 9"); private JButton w10 = new JButton("Week 10");
	private JButton w11 = new JButton("Week 11"); private JButton w12 = new JButton("Week 12");
	private JButton[] weekButtons = new JButton[] {w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12};
	private JButton Steve = new JButton("Steve"); private JButton Winston = new JButton("Winston");
	private JButton Lantry = new JButton("JC"); private JButton Brett = new JButton("Brett");
	private JButton Tyler = new JButton("Tyler"); private JButton Chris = new JButton("Chris");
	private JButton Scottie = new JButton("Scottie"); private JButton Faysal = new JButton("Faysal");
	private JButton Sam = new JButton("Sam"); private JButton Angie = new JButton("Angie");
	private JButton Haleigh = new JButton("Haleigh"); private JButton Kaycee = new JButton("Kaycee");
	private JButton Angela = new JButton("Angela"); private JButton Bayleigh = new JButton("Bayleigh");
	private JButton Kaitlyn = new JButton("Kaitlyn"); private JButton Rachel = new JButton("Rachel");
	private JButton[] nameButtons = new JButton[] {Steve, Winston, Lantry, Brett, Tyler, Chris, Scottie, Faysal, Sam, Angie, Haleigh, Kaycee, Angela, Bayleigh, Kaitlyn, Rachel};
	private JLabel currentWeek = new JLabel("Week " + week);
	private JLabel currentPerson = new JLabel(person);
	private JButton c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25,
	c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37, c38, c39;
	private JButton[] catButtons = new JButton[] {c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25,
	c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37, c38, c39};
	private JButton type1 = new JButton("Manipulation");
	private JButton type2 = new JButton("Social");
	private JButton type3 = new JButton("Comps");
	private JButton type4 = new JButton("Eviction");
	
	

	public Window() throws IOException, GeneralSecurityException {
		final java.util.logging.Logger buggyLogger = java.util.logging.Logger.getLogger(FileDataStoreFactory.class.getName());
        buggyLogger.setLevel(java.util.logging.Level.SEVERE);
		initUI();
	}

	private void initCatButtons() throws IOException{
		Dimension catSize = new Dimension(350,30);
		int i = 0;
		for(List<Object> row : readWrite.readColumn("Week1!A2:A39")){
			catButtons[i] = new JButton((String)row.get(0));
			catButtons[i].addActionListener(new PointListener((i + 2), this));
			catButtons[i].setMinimumSize(catSize);
			catButtons[i].setMaximumSize(catSize);
			i++;
		}
	}
	private void initUI() throws IOException, GeneralSecurityException {
		readWrite = new SheetReadWrite();
		Dimension windowSize = new Dimension(1050,500);
		this.setSize(windowSize);
		initPanelTop();
		initCatButtons();
		initPanelCenter();
		initPanelBottom();
		panel = new JPanel(new BorderLayout());
		panel.add(panelTop, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelBottom, BorderLayout.SOUTH);
		add(panel);
		setTitle("BB Fantasy");
		this.setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initPanelTop() {
		Font labelFont = new Font("Arial", Font.BOLD, 14);
		Dimension labelSize = new Dimension(70,20);
		currentWeek.setFont(labelFont); currentPerson.setFont(labelFont);
		currentWeek.setMinimumSize(labelSize); currentPerson.setMinimumSize(labelSize);
		panelTop = new JPanel(new MigLayout("", 
				"[][][][][][][][][][][][][]",
				"[][][][]"));
		int i;
		for(i = 0; i < weekButtons.length; i++) {
			weekButtons[i].addActionListener(new WeekListener((i+1), this));
			panelTop.add(weekButtons[i], "cell " + i + " 0, growx");
		}
		panelTop.add(currentWeek, "cell 8 1");
		for(i = 0; i < nameButtons.length; i++) {
			nameButtons[i].addActionListener(new PersonListener(nameButtons[i].getText(), this));
			if(i < 8) panelTop.add(nameButtons[i], "cell " + i + " 1, growx");
			else panelTop.add(nameButtons[i], "cell " + (i - 8) + " 2, growx");
		}
		panelTop.add(currentPerson, "cell 8 2");
	}
	
	private void initPanelCenter() {
		int i = 0; int j = 0; int k = 0; int l = 0;
		switch(type) {
		case 1: 
			i = 2; j = 13;
			break;
		case 2:
			i = 13; j = 24;
			break;
		case 3:
			i = 24; j = 31;
			break;
		case 4:
			i = 31; j = 40;
			break;
		}
		for(; i < j; i++) {
			if(k == 2) { //k == column
				k = 0;
				l++; //l == row (when column is 2 it means go to next row and reset column to 0
			}
			panelCenter.add(catButtons[i - 2], "cell " + k + " " + l + ", growx");
			catButtons[i -2].setVisible(true);
			k++;
		}
	}
	
	private void initPanelBottom() {
		type1.addActionListener(new TypeListener(1, this));
		type2.addActionListener(new TypeListener(2, this));
		type3.addActionListener(new TypeListener(3, this));
		type4.addActionListener(new TypeListener(4, this));
		panelBottom.add(type1, "gapleft 335"); panelBottom.add(type2); panelBottom.add(type3); panelBottom.add(type4);
	}
	public static void main(String... args) throws IOException, GeneralSecurityException {
		new Window();
	}
	
	public int getWeek() { return week; }
	public String getPerson() { return person; } 
	public void setWeek(int week) { 
		this.week = week; 
		currentWeek.setText("Week " + week);
	}
	public void setPerson(String person) {
		this.person = person; 
		currentPerson.setText(person);
	}
	public void setType(int type) {
		this.type = type;
		for(int i = 0; i < catButtons.length; i++) catButtons[i].setVisible(false);
		panelCenter.removeAll();
		initPanelCenter();
	}
	public SheetReadWrite getReadWrite() { return readWrite; }
}
