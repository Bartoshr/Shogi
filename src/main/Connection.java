/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bartosh
 */
public class Connection implements Runnable {
    
    public static boolean isServer;
    
       // TCP Components
    public static ServerSocket srvr;
    public static Socket socket;
    public static BufferedReader in = null;
    public static PrintWriter out = null;
    
    public String data;

    public Connection() {
    }
    
    public void sendString(String data){
        this.data = data;
    }
    
    public void connected(String s){
        try {
               // Send data
               if (data != null) {
                  out.print(data);
                  out.flush();
                  data = null;
               }

               // Receive data
               if (in.ready()) {
                  s = in.readLine();
                  if ((s != null) &&  (s.length() != 0)) {
                        JOptionPane.showMessageDialog(null, s);
                  }
               }
            }
            catch (IOException e) {
            }
    }
    
    public void close(){
        try {
            if(isServer) {
                socket.close();
                srvr.close();
            } else {
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void init(){
        try {
            if(isServer) {
                srvr = new ServerSocket(1234);
                socket = srvr.accept();
            } else {
                socket = new Socket("localhost", 1234);
            }
            
            in = new BufferedReader(new 
                  InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);  
        }catch (IOException ex) {
           Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    };

    
    @Override
    public void run() {
        
        String s = null;
        
        init();
        while(true){
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
            connected(s);
        }

    }
    
}
