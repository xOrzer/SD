package TCP_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientSocket {
	
	static final int PORT = 6666 ;
	
	private Socket socket;
	PrintWriter writer ;
	BufferedReader reader ;
	
	ChatClient app ;
	Thread listener ;

	public ChatClientSocket(ChatClient app) throws UnknownHostException, IOException {
		this.app = app ;
		
		socket = new Socket(app.hostName, PORT);
		writer = new PrintWriter(socket.getOutputStream());
		
		InputStream stream = socket.getInputStream();
		reader = new BufferedReader(new InputStreamReader(
				stream));
		
		app.setTitle(app.userName + " is connected") ;
		startListening() ;
		
		// envoi du nom au serveur
		writer.println(app.userName) ; writer.flush() ;

	}
	
	public void sendMsg(String sender, String msg){
		writer.println(sender) ; writer.flush();
		writer.println(msg) ; writer.flush();
	}
	
	private void listen(){
		try {
			String sender = "", msg = "" ;
			while( (sender = reader.readLine()) != null){
				msg = reader.readLine() ;
				//System.out.println("MSG recu : " + msg) ;
				if (sender.equals(ChatServer.USER_LIST_TAG)){
					String[] names = msg.split(ChatServer.USER_LIST_SEPARATOR) ;
					app.myGroup.setListData(names) ;
				}else{
					app.display(sender, msg) ;
				}
			}
		} catch (IOException e) {
			e.printStackTrace() ;
			System.exit(0) ;
		}
	}
	
	public void startListening(){
		Runnable runnable = new Runnable() {
			public void run() {
				listen() ;
			}
		};
		listener = new Thread(runnable) ;
		listener.start() ;
	}
	
	public void close() throws IOException{
		writer.println(ChatServer.DISCONNECT_TAG) ; writer.flush();
		listener.stop() ;
		socket.close();
	}

}
