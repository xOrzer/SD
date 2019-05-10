package popop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends ChatClientFrame {

	static String appliName = "TCP Chat";

	Dimension prefSize = new Dimension(500, 300);
	JTextArea myDisplay;
	JTextField myText ;
	JList myGroup ;
	private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM) ;
	
	ChatClientSocket client ;
	
	public ChatClient() {
		this((String) null, (String) null);
	}

	public ChatClient(String aHost, String aUserName) {
		super(aHost, aUserName);
		
		buildUI();
		
		try {
			client = new ChatClientSocket(this) ;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public ChatClient(String aHost) {
		this(aHost, null) ;
	}
	
	public void display(String sender, String msg){
		myDisplay.append(dateFormat.format(new Date()) + " : "+ sender + " -> " + msg + "\n") ;
	}

	void sendText() {
		String message = myText.getText();
		client.sendMsg(userName, message) ;
		myText.setText("");
		myText.grabFocus();
	}

	void buildUI() {
		
		getContentPane().setLayout(new BorderLayout()) ;
		
		myDisplay = new JTextArea() ;
		myDisplay.setEditable(false) ;
		myDisplay.setLineWrap(true) ;
		JScrollPane scroll = new JScrollPane(myDisplay) ;
		add(scroll, BorderLayout.CENTER) ;
		
		myText = new JTextField() ;
		add(myText, BorderLayout.SOUTH) ;
		
		myGroup = new JList() ;
		myGroup.setPrototypeCellValue("Greg is ze Best !") ;
		scroll = new JScrollPane(myGroup) ;
		add(scroll, BorderLayout.EAST) ;
		
		addListeners();
		
		myText.grabFocus();
		
		setTitle("Not connected") ;
		setSize(prefSize) ;
		setVisible(true) ;
	}

	void addListeners() {

		myText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ev) {
				if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
					sendText();
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				closeWindow();
			}
		});

	}

	public void closeWindow() {
		try {
			client.close() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void main(String args[]) {
		new ChatClient("localhost");
	}

}
