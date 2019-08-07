package com.interblocks.testserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        System.out.println("Server Started. Listening to incoming chat on IP: " + InetAddress.getLocalHost() + " & Port: " + port + ". \n");
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Input: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            if (message.equalsIgnoreCase("username")) {
                oos.writeObject("UserName: Deelaka");
            } else if (message.equalsIgnoreCase("password")) {
                oos.writeObject("UserName: ABCD");
            } else if (message.equalsIgnoreCase("exit")) {
                oos.writeObject(message);
                break;
            } else {
                oos.writeObject("Input Not Recognized");
            }
            ois.close();
            oos.close();
            socket.close();
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

}
