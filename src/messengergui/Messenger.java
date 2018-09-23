/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messengergui;

import javax.swing.JFrame;

/**
 *
 * @author ishan
 */
public class Messenger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client = new Client("localhost", 5000);
        ClientSend send = new ClientSend(client);
        ClientRecieve recieve = new ClientRecieve(client);
        MessengerFrame frame = new MessengerFrame(send,recieve);
        frame.t.start();
        send.t.start();
        recieve.t.start();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
