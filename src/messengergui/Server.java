/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messengergui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ishan
 */

public class Server extends Thread{
    ServerSocket server;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    ArrayList<Socket> sockets;
    int port;
    Server(int port) {
        try {
            this.port = port;
            sockets = new ArrayList<Socket>();
            server = new ServerSocket(port);
            System.out.println("Server created");
            System.out.println("Waiting for a client........");
            
            while(true) {
            socket = server.accept();
            sockets.add(socket);
            System.out.println("Client Accepted");
            new ServerListener(socket,sockets).start();
            }
        } catch (IOException ex) {
        }
            
    }
    
    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}
