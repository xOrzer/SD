package TCP_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

public class ChatServer {

	static final int PORT = 6666 ;
	
	static final String DISCONNECT_TAG = "DISCONNECT_TAG" ;
	static final String USER_LIST_TAG = "USER_LIST_TAG" ;
	static final String USER_LIST_SEPARATOR = "::" ;
	
	private HashMap<Socket, String> sockets = new HashMap<Socket, String>() ;
	
	public ChatServer() {
		try {
			
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while(true){
				System.out.println(new Date() + " -> Serveur en attente de connexion");
				Socket socket = serverSocket.accept();
				
				InputStream stream = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						stream));
				
				// Lecture du nom
				String name = reader.readLine() ;
				System.out.println(new Date() + " -> " + name + " connecté");
				
				sockets.put(socket, name) ;
				new Handler(socket).start() ;
				
				sendNamesList() ;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void sendNamesList(){
		sendToAll(USER_LIST_TAG) ;
		String msg = "" ;
		for (String name : sockets.values()){
			msg += name + USER_LIST_SEPARATOR ;
		}
		sendToAll(msg) ;
	}
	
	void sendToAll(String msg){
		PrintWriter writer ;
		try{
			for (Socket s: sockets.keySet()){
					writer = new PrintWriter(s.getOutputStream());
					writer.println(msg) ;
					writer.flush() ;
			}
		}catch (Exception e) {
			e.printStackTrace() ;
		}
	}

	public static void main(String[] args) {
		new ChatServer() ;
	}
	
	class Handler extends Thread{
		
		private final Socket socket ;
		BufferedReader reader ;
		private PrintWriter writer;
		
		public Handler(Socket socket) throws IOException {
			this.socket = socket ;
			InputStream stream = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(
					stream));
			writer = new PrintWriter(socket.getOutputStream());
		}
		
		public void run(){
			try{		
				String msg = "";
				while ((msg = reader.readLine()) != null  && !msg.equals(DISCONNECT_TAG)) {
					sendToAll(msg) ;
				}
			}catch (Exception e) {
				e.printStackTrace() ;
			}
			System.out.println(new Date() + " -> " + sockets.get(socket) + " deconnecte !");
			sockets.remove(socket) ;
			sendNamesList() ;
		}
	}
}
