/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messengergui;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ishan
 */
public class Client {
    Socket socket = null;
    private int port;
    private String host;
    private DataOutputStream out;
    
    Client(String host,int port) {
        this.host = host;
        this.port = port;
        
        while(socket == null) {
                try {
                    socket = new Socket(host,port);
                } catch (IOException ex) {
                    System.out.println("Still not connected to the Server.");
                }
            }
        System.out.println("Connected");
    }
    
}
