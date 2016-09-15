package ca.ece.ubc.cpen221.mp5;

import java.util.Set;
import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import ca.ece.ubc.cpen221.mp5.Query.Query;
import ca.ece.ubc.cpen221.mp5.Query.QueryFactory;



public class RestaurantDB {
    /*
     * Rep invariant: three map fields contain information for restaurants, users,
     * and reviews, respectively. For each map, the ID of any given object is the key
     * in the map for that object. 
     * Each map must not be null.
     * Each review in reviewMap must be about a restaurant in restaurantMap
     * Abstraction function:
     * -Each object in the restaurantMap represents a Yelp profile of a restaurant
     * near UC Berkely
     * -Each object in the reviewMap represents a Yelp review of one of the 
     * restaurants in the restaurantMap
     * -Each object in the userMap represents the profile of a Yelp user
     * Thread Safety argument:
     * All methods available through the multithreaded server either return new objects
     * or immutable objects only
     * 
     */
    
    private Map<String, Restaurant> restaurantMap;
    private Map<String, User> userMap;
    private Map<String, Review> reviewMap;
    
	/**
	 * Create a database from the Yelp dataset given the names of three files:
	 * <ul>
	 * <li>One that contains data about the restaurants;</li>
	 * <li>One that contains reviews of the restaurants;</li>
	 * <li>One that contains information about the users that submitted reviews.
	 * </li>
	 * </ul>
	 * The files contain data in JSON format.
	 * 
	 * @param restaurantJSONfilename
	 *            the filename for the restaurant data
	 * @param reviewsJSONfilename
	 *            the filename for the reviews
	 * @param usersJSONfilename
	 *            the filename for the users
	 */
	public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, 
	        String usersJSONfilename) {
		JSONParser parser = new JSONParser();
		restaurantMap = new HashMap<String, Restaurant>();
		userMap = new HashMap<String, User>();
		reviewMap = new HashMap<String, Review>();
		try{
		    BufferedReader restaurantReader = 
		            new BufferedReader(new FileReader(restaurantJSONfilename));
		    Object nextLine = restaurantReader.readLine();
		    while(nextLine != null){
		        nextLine = parser.parse((String)nextLine);
		        JSONObject currData = (JSONObject) nextLine;
		        Restaurant currRestaurant = new Restaurant(currData);
		        restaurantMap.put(currRestaurant.getBusinessID(), currRestaurant);
		        nextLine = restaurantReader.readLine();
		    }
		    restaurantReader.close();
		    BufferedReader userReader =
		            new BufferedReader(new FileReader(usersJSONfilename));
		    nextLine = userReader.readLine();
		    
		    while(nextLine != null){
		        nextLine = parser.parse((String)nextLine);
		        JSONObject currData = (JSONObject) nextLine;
		        User currUser = new User(currData);
		        userMap.put(currUser.getUserID(), currUser);
		        nextLine = userReader.readLine();
		    }
		    userReader.close();
		    BufferedReader reviewReader =
		            new BufferedReader(new FileReader(reviewsJSONfilename));
		    nextLine = reviewReader.readLine();
		    
		    while(nextLine != null){
		        nextLine = parser.parse((String)nextLine);
		        JSONObject currData = (JSONObject) nextLine;
		        Review currReview = new Review(currData);
		        currReview.setBusinessName(
		                restaurantMap.get(currReview.getBusinessID()).getName());
		        reviewMap.put(currReview.getReviewID(), currReview);
		        nextLine = reviewReader.readLine();
		    }
		    reviewReader.close();
		
		}catch(Exception e){
		    e.printStackTrace();
		}
	}
	
	public RestaurantDB(String restaurantJSONfilename){
	    JSONParser parser = new JSONParser();
        restaurantMap = new HashMap<String, Restaurant>();
        userMap = new HashMap<String, User>();
        reviewMap = new HashMap<String, Review>();
        try{
            BufferedReader restaurantReader = 
                    new BufferedReader(new FileReader(restaurantJSONfilename));
            Object nextLine = restaurantReader.readLine();
            while(nextLine != null){
                nextLine = parser.parse((String)nextLine);
                JSONObject currData = (JSONObject) nextLine;
                Restaurant currRestaurant = new Restaurant(currData);
                restaurantMap.put(currRestaurant.getBusinessID(), currRestaurant);
                nextLine = restaurantReader.readLine();
            }
            System.out.println("about to close reader");
            restaurantReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public String processQuery(String query){
	    if(query.length() > 12 && query.substring(0, 12).equals("randomReview")){
	        JSONObject result = randomReview(query.substring(14, query.length()-2));
	        return result.toString();
	    }else if(query.length() > 13 && query.substring(0, 13).equals("getRestaurant")){
	        JSONObject result = getRestaurant(query.substring(15, query.length()-2));
	        return result.toString();
	    }else if(query.length() > 13 && query.substring(0, 13).equals("addRestaurant")){
	        Object queryObj = JSONValue.parse(query.substring(15, query.length()-2));
	        JSONObject queryJSON = (JSONObject) queryObj;
	        JSONObject result = addRestaurant(queryJSON);
	        return result.toString();
	    }else if(query.length() > 7 && query.substring(0, 7).equals("addUser")){
	        Object queryObj = JSONValue.parse(query.substring(9, query.length()-2));
            JSONObject queryJSON = (JSONObject) queryObj;
            JSONObject result = addUser(queryJSON);
            return result.toString();
	    }else if(query.length() > 9 && query.substring(0, 9).equals("addReview")){
	        Object queryObj = JSONValue.parse(query.substring(11, query.length()-2));
            JSONObject queryJSON = (JSONObject) queryObj;
            JSONObject result = addReview(queryJSON);
            return result.toString();
	    }else{
	        try{
	            Set<Restaurant> results = query(query);
	            if (results.size() == 0){
	                JSONObject failMessage = new JSONObject();
	                failMessage.put("Message", "No restaurants met the given criteria");
	                return failMessage.toString();
	            }
	            String validRestaurants = "";
	            for(Restaurant r : results){
	                validRestaurants += r.getName() + "\n";
	            }
	            return validRestaurants;
	        }catch(Exception e){
	            e.printStackTrace();
	            JSONObject failMessage = new JSONObject();
                failMessage.put("Message", "There was an error with the formatting of your request");
                return failMessage.toString();
	        }
	    }
	}

	public Set<Restaurant> query(String queryString) {
		return QueryFactory.parse(queryString);
	}
	
	/**
	 * Returns a random review for the given restaurant or an error message if
	 * there are no reviews for that restaurant in the database
	 * @param restaurantName: the name of the restaurant to find a review for
	 * @return: a random review for the given restaurant in JSON format, or an
	 * error message in JSON format if there are no reviews for the given
	 * restaurant in the database
	 */
	@SuppressWarnings("unchecked")
    private JSONObject randomReview(String restaurantName){
	    List<Review> restaurantReviews = new ArrayList<Review>();
	    for(String key : reviewMap.keySet()){
	        if(reviewMap.get(key).getBusinessName().equals(restaurantName))
	            restaurantReviews.add(reviewMap.get(key));
	    }
	    if(restaurantReviews.size() > 0){
	        int randIndex = (int)(Math.random()*restaurantReviews.size());
	        return restaurantReviews.get(randIndex).getJSONInfo();
	    }
	    JSONObject failMessage = new JSONObject();
	    failMessage.put("Message", "No reviews for given restaurant in database");
	    return failMessage;
	}
	
	/**
	 * Returns the information for the restaurant corresponding to the given 
	 * business ID or an error message if there is no restaurant with that
	 * business ID in the database
	 * @param businessID: the alphanumeric business ID of the restaurant to be 
	 * searched for
	 * @return the JSON information of the restaurant corresponding to the given
	 * business ID, or a JSON-formatted error message if there is no restaurant
	 * in the database with the given business ID
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getRestaurant(String businessID){
	    if(restaurantMap.containsKey(businessID))
	        return restaurantMap.get(businessID).getJSONInfo();
	    JSONObject failMessage = new JSONObject();
	    failMessage.put("Message", "There is no restaurant in the database with"
	            + " that business ID");
	    return failMessage;
	}
	
	/**
	 * Adds a restaurant to the database with the given data or does nothing
	 * if that restaurant is already in the database
	 * @param restaurantInfo is the JSON-formatted data of the restaurant to be
	 * added to the database
	 * @return a JSON-formatted message saying whether or not the restaurant
	 * was successfully added to the database
	 */
	@SuppressWarnings("unchecked")
    private JSONObject addRestaurant(JSONObject restaurantInfo){
	    try{
	        Restaurant newRestaurant = new Restaurant(restaurantInfo);
	        for(String key : restaurantMap.keySet()){
	            Restaurant curr = restaurantMap.get(key);
	            if(curr.getAddress().equals(newRestaurant.getAddress()) &&
	                    curr.getName().equals(newRestaurant.getName())){
	                JSONObject failMessage = new JSONObject();
	                failMessage.put("Message", "There is already a restaurant with"
	                        + " that name and address in the database.");
	                return failMessage;
	            }
	        }
	        restaurantMap.put(newRestaurant.getBusinessID(), newRestaurant);
	        JSONObject successMessage = new JSONObject();
	        successMessage.put("Message", "Restaurant added to database.");
	        return successMessage;
	    }catch(NullPointerException e){
	        JSONObject failMessage = new JSONObject();
	        failMessage.put("Message", "Did not add restaurant to database. " +
	                "Restaurant info was not in proper JSON format.");
	        return failMessage;
	    }
	}
	
	/**
     * Adds a user with the given data to the database or does nothing
     * if that user is already in the database
     * @param userInfo is the JSON-formatted data of the user to be
     * added to the database
     * @return a JSON-formatted message saying whether or not the user
     * was successfully added to the database
     */
	@SuppressWarnings("unchecked")
    private JSONObject addUser(JSONObject userInfo){
	    try{
	        User newUser = new User(userInfo);
	        for(String key : userMap.keySet()){
	            User curr = userMap.get(key);
	            if(curr.getUserID().equals(newUser.getUserID())){
	                JSONObject failMessage = new JSONObject();
	                failMessage.put("Message", "That user is already in the database.");
	                return failMessage;
	            }
	        }
	        userMap.put(newUser.getUserID(), newUser);
	        JSONObject successMessage = new JSONObject();
	        successMessage.put("Message", "User added to database.");
	        return successMessage;
	    }catch(NullPointerException e){
	        JSONObject failMessage = new JSONObject();
            failMessage.put("Message", "Did not add user to database. " +
                    "User info was not in proper JSON format.");
            return failMessage;
	    }
	}
	
	/**
     * Adds a review with the given data to the database or does nothing
     * if that review is already in the database
     * @param reviewInfo is the JSON-formatted data of the review to be
     * added to the database
     * @return a JSON-formatted message saying whether or not the review
     * was successfully added to the database
     */
	@SuppressWarnings("unchecked")
    private JSONObject addReview(JSONObject reviewInfo){
	    try{
	        Review newReview = new Review(reviewInfo);
	        for(String key : reviewMap.keySet()){
	            Review curr = reviewMap.get(key);
	            if(curr.getReviewID().equals(newReview.getReviewID())){
	                JSONObject failMessage = new JSONObject();
	                failMessage.put("Message", "That review is already in the database.");
	                return failMessage;
	            }
	        }
	        reviewMap.put(newReview.getReviewID(), newReview);
	        JSONObject successMessage = new JSONObject();
	        successMessage.put("Message", "Review added to database.");
	        return successMessage;
	    }catch(NullPointerException e){
	        JSONObject failMessage = new JSONObject();
            failMessage.put("Message", "Did not add review to database. " +
                    "Review info was not in proper JSON format.");
            return failMessage;
	    }
	}
	
	/**
	 * Returns a map containing all the restaurants in the database, used for
	 * k-means clustering. Is not available through server queries
	 * 
	 * @return an unmodifiable map of the restaurants in the database, where
	 * the keys are the restaurants' business IDs
	 */
	public Map<String, Restaurant> getRestaurantMap(){
	    return Collections.unmodifiableMap(restaurantMap);
	}
	
	/**
     * Returns a map containing all the reviews in the database, used for
     * leastSquares fitting. Is not available through server queries
     * 
     * @return an unmodifiable map of the reviews in the database, where
     * the keys are the reviews' review IDs
     */
    public Map<String, Review> getReviewMap(){
        return Collections.unmodifiableMap(reviewMap);
    }
}


