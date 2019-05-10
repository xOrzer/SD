package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCP {
    static final int PORT = 6666;
    public ServeurTCP(){
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur pret");

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté");

            InputStream stream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line ="";
            int i =0;
            while((line = reader.readLine())!=null){
                System.out.println("MSG "+ ++i + " : " + line);
            }
            socket.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
            new ServeurTCP();
    }
}
