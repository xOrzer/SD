package TCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP{
    Socket socket;

    public ClientTCP(){
        try{
            socket = new Socket("localhost", ServeurTCP.PORT);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("Saluuut");
            writer.flush();

            writer.println("Greg is the best");
            writer.flush();

            socket.close();
        }
        catch (UnknownHostException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new ClientTCP();
    }
}
