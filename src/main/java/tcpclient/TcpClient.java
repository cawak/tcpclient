package tcpclient;

import java.io.*;
import java.net.Socket;

public class TcpClient {
    public static void main(String [] args) {

        try (Socket clientSocket = new Socket("localhost", 12345);
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader inFromUser = new BufferedReader(inputStreamReader);
             DataInputStream inFromServer = new DataInputStream(inputStream);
             DataOutputStream outToServer = new DataOutputStream(outputStream)
        ){
            System.out.println("Hello");
            while (true) {
                System.out.print("Command : ");
                try {
                    String temp = inFromUser.readLine();
                    if (temp.equals("quit")){
                        System.out.println("Bye");
                        break;
                    }
                    outToServer.writeUTF(temp);
                    outToServer.flush();

                    System.out.print(inFromServer.readUTF());
                } catch (Exception e) {
                    System.out.println("Got an exception after command: " + e);
                    throw new Exception(e);
                }
            }
        } catch (Exception ex) {
            System.out.println("Got an exception when opening client socket. " + ex);
        }
    }
}
