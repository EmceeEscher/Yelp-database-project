package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.*;

public class HelperRunnable implements Runnable{
    private Socket clientSocket = null;
    private RestaurantDB database;
    
    public HelperRunnable(Socket clientSocket, RestaurantDB database){
        this.clientSocket = clientSocket;
        this.database = database;
    }
    
    public void run(){
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    (clientSocket.getInputStream())));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            String query = input.readLine();
            String queryOutput = database.processQuery(query);
            output.write(queryOutput);
            output.close();
            input.close();
            System.out.println("Request processed: " + queryOutput);
        }catch(IOException e){
            System.out.println("Helper thread caught an error.");
            e.printStackTrace();
        }
    }

}
