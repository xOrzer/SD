package TCP_Boucle;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class ClientTCP_boucle {
    Socket socket;

    public ClientTCP_boucle() {
        try {
            System.out.println(new Date() + " -> Client démarré");

            socket = new Socket("localhost", TCP_boucle_Helper.PORT);
            System.out.println(new Date() + " -> Client connecté");

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("saluuuut");
            writer.flush();

            writer.println(TCP_boucle_Helper.END_OF_MESSAGE);
            writer.flush();

            InputStream stream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(new Date() + " -> Recu du serveur" + " : " + line);
            }

            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientTCP_boucle();
    }

}
