package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.*;
import java.util.*;

public class RestaurantDBClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // Rep invariant: socket, in, out != null
    
    /**
     * Make a RestaurantDBClient and connect it to a server running on
     * hostname at the specified port.
     * @throws IOException if can't connect
     */
    public RestaurantDBClient(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    /**
     * Send a request to the server. Requires this is "open".
     * @param input query to be processed
     * @throws IOException if network or server failure
     */
    public void sendRequest(String input) throws IOException {
        out.println(input);
        out.flush(); // important! make sure x actually gets sent
    }
    
    /**
     * Get a reply from the next request that was submitted.
     * @return the answer to the requested query
     * @throws IOException if network or server failure
     */
    public String getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("connection terminated unexpectedly");
        }
        
        return reply;
    }

    /**
     * Closes the client's connection to the server.
     * This client is now "closed". Requires this is "open".
     * @throws IOException if close fails
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
    
    
    /**
     * Use a RestaurantDBServer to process this client's requests about
     * Yelp restaurant info.
     * @param args has one element which is the port number to connect to
     */
    public static void main(String[] args) {
        try {
            boolean stop = false;
            RestaurantDBClient client = new RestaurantDBClient("localhost", 
                    Integer.parseInt(args[0]));
            
            while(!stop){
                Scanner scanner = new Scanner(new InputStreamReader(System.in));
                String input = scanner.nextLine();
                if(!input.equals("Stop, please")){
                    System.out.println("input: " + input);
                    client.sendRequest(input);
                    String reply = client.getReply();
                    System.out.println(reply);
                }else{
                    stop = true;
                    scanner.close();
                    System.out.println("Client closed");
                    client.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
