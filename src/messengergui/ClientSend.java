/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messengergui;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ishan
 */
class ClientSend implements Runnable{
    Thread t;
    private Socket socket = null;
    private DataOutputStream out;
    
    ClientSend(Client client) {
        this.socket = client.socket;
        System.out.println(socket.getInetAddress());
        t = new Thread(this,"ClientThread");
    }
    
    private String toBeSend = "";
    volatile boolean notSent = false;
    
    public void sendText(String str) {
        notSent = true;
        toBeSend = str;
    } 

    @Override
    public void run() {
        try {
            try {
                out = new DataOutputStream(socket.getOutputStream());
                while(true) {
                    if(notSent) {
                        out.writeUTF(toBeSend);
                        out.flush();
                        notSent = false;
                    }
                }
                
            } catch (IOException ex) {
                System.out.println(ex);
            } 
            
            out.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null,ex);
        }
        
    }
}
