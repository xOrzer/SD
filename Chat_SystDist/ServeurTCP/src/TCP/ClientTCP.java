package TCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
    Socket socket;
    public ClientTCP(){
    try{
      socket = new Socket("localhost", ServeurTCP.PORT);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("Saluuuut");
        writer.flush();

        writer.println("Greg is the Best");
        writer.flush();

        socket.close();


    }catch(UnknownHostException e){
        e.printStackTrace();
    }catch (IOException e){
        e.printStackTrace();
    }
}
    public static void main(String[] args){
        new ClientTCP();
    }
}
