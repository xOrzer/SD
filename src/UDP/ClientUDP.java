package UDP;

import java.io.IOException;
import java.net.*;

public class ClientUDP {
    public ClientUDP(){
        try{
            InetAddress address = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();

            byte[] buffer = "Bonjour".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, address, UDP.ServeurUDP.PORT);

            buffer = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length, address, UDP.ServeurUDP.PORT);

            socket.send(sendPacket);

            System.out.println("Client en attente");
            socket.receive(receivePacket);
            System.out.println(new String(receivePacket.getData()));

            socket.close();
        }catch (UnknownHostException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SocketException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new ClientUDP();
    }
}
