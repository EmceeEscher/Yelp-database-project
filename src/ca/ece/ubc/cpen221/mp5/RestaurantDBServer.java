package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.*;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {
    private static RestaurantDB database;
    private static boolean isStopped = false;
    private Thread runningThread = null;
    private static ServerSocket hostSocket = null;

	/**
	 * Creates a server in the given port, with a database created from the
	 * JSON information of restaurants, reviews, and users
	 * 
	 * @param port: the port that the server will be running from
	 * @param restaurantsJSONfilename: the name of the file with the restaurant
	 * information in JSON format
	 * @param reveiwsJSONfilename: the name of the file with the review
	 * information in JSON format
	 * @param usersJSONfilename: the name of the file with the user information
	 * in JSON format
	 */
	public RestaurantDBServer(int port, String restaurantsJSONfilename, 
	        String reviewsJSONfilename, String usersJSONfilename) throws IOException{
	    database = new RestaurantDB(restaurantsJSONfilename, reviewsJSONfilename,
	            usersJSONfilename);
        hostSocket = new ServerSocket(port);
	}
	
	/**
	 * Creates and runs a server that can accept query requests for information
	 * about restaurants on Yelp
	 * @param args: variables entered in the command line, in order: port number
	 * the server should run in, then the filenames of the files with the restaurant,
	 * review, and user info, respectively, in JSON format
	 * @throws IOException if the port the server is supposed to connect to is
	 * already occupied
	 */
	public static void main(String[] args) throws IOException{
	    try{
	        RestaurantDBServer server = new RestaurantDBServer(Integer.parseInt(args[0]), 
	                args[1], args[2], args[3]);
	        server.serve();
	    }catch(IOException e){
	        e.printStackTrace();
	    }finally{
	        hostSocket.close();
	    }
	            	    
	}
	
	/**
	 * Creates a server that runs continuously, and can accept and respond to
	 * queries from a number of clients simultaneously
	 * @throws IOException if the port that the server is supposed to occupy
	 * is already in use
	 */
	public void serve() throws IOException {
        while (true) {
            // block until a client connects
            final Socket clientSocket = hostSocket.accept();
            // create a new thread to handle that client
            Thread handler = new Thread(new Runnable() {
                public void run() {
                    try {
                        try {
                            handle(clientSocket);
                        } finally {
                            clientSocket.close();
                        }
                    } catch (IOException ioe) {
                        // this exception wouldn't terminate serve(),
                        // since we're now on a different thread, but
                        // we still need to handle it
                        ioe.printStackTrace();
                    }
                }
            });
            // start the thread
            handler.start();
        }
    }
	
	/**
	 * Reads and handles a client's request, after the client has connected to the
	 * server
	 * @param clientSocket: the socket holding the connection to the client
	 * @throws IOException TODO write something here
	 */
	private void handle(Socket clientSocket) throws IOException{
	    BufferedReader input = new BufferedReader(new InputStreamReader(
                (clientSocket.getInputStream())));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(
                clientSocket.getOutputStream()), true);
	    
	    try{
	        for (String line = input.readLine(); line != null; line = input
                    .readLine()) {
	            String query = line;
	            String queryOutput = database.processQuery(query);
	            output.println(queryOutput);
	        
	        }
        }finally{
            output.close();
            input.close();
        }
	}

}
