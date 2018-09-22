/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messengergui;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ishan
 */
public class ServerListener extends Thread{
    
    protected Socket socket;
    protected DataInputStream in;
    protected DataOutputStream out;
    ArrayList<Socket> sockets;
    public ServerListener(Socket socket,ArrayList<Socket> sockets) {
        this.socket = socket;
        this.sockets = sockets;
    }

    
    @Override
    public void run() {
        try {
            in = new DataInputStream(
            new BufferedInputStream(socket.getInputStream())
            );
            String line = "";
            while( !line.equals("Over")) {
                try{
                line = in.readUTF();
                for(int i=0;i<sockets.size();++i){
                    out = new DataOutputStream( sockets.get(i).getOutputStream() );
                    out.writeUTF(line);
                }
                }
                catch(IOException i) 
                { 
                    System.out.println("Closing connection.");
                    in.close();
                    socket.close();
                    sockets.remove(socket);
                    break;
                }
            }    
            
        } catch (IOException ex) {
                System.out.println(ex);
        }
    }
    
}
