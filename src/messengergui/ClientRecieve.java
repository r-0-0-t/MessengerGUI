/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messengergui;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ishan
 */
public class ClientRecieve implements Runnable {
    Thread t;
    private Socket socket = null;
    private DataInputStream in;
    ClientRecieve(Client client) {
        this.socket = client.socket;
        t = new Thread(this,"ClientRecieve");
    }
    
    public String toBeShown;
    volatile public boolean notShown = false;
    
    private void recieveText(String str) {
        notShown = true;
        toBeShown = str;
    } 
    
    boolean quit = false;

    @Override
    public void run() {
        try {
            try {
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                while(true) {
                    toBeShown = in.readUTF();
                recieveText(toBeShown);
                System.out.println(toBeShown + " Recieved");
                }
                
            } catch (Exception ex) {
                System.out.println(ex);
            } 
            
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientRecieve.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
}
