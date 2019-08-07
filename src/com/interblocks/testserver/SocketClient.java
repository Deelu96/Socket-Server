package com.interblocks.testserver;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        System.out.println("Client Started. Start Chat on IP: " + InetAddress.getLocalHost() + "\n");
        while (true) {
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String text = reader.readLine();
            oos.writeObject(text);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            if (message.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println(message);
            }
            ois.close();
            oos.close();
            Thread.sleep(100);


        }
        System.out.println("Shutting down Client!!");
    }
}
