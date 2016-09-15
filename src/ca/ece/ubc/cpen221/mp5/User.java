package ca.ece.ubc.cpen221.mp5;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class User {
    /**
     * Rep invariant:
     *  The data in all the fields of the User class must match those
     *  given in this user's entry in the Yelp user dataset provided.
     * Abstraction function:
     *  Represents information about a particular user of the site Yelp, including
     *  data such as the user's name, ID number, and certain characteristics of
     *  their reviewing behavior
     */
    
    private String url;
    private Map<String, Integer> votes;
    private int reviewCount;
    private String type;
    private String userID;
    private String name;
    private double averageStars;
    private JSONObject jsonInfo;
    
    /**
     * Creates a User object that corresponds to one entry in the Yelp
     * dataset  
     * @param jsonData: the JSONObject that holds the data corresponding to one
     * entry in the Yelp user dataset
     */
    public User(JSONObject jsonData){
        jsonInfo = jsonData;
        url = (String) jsonData.get("url");
        votes = new HashMap<String, Integer>();
        JSONObject jsonVotes = (JSONObject) jsonData.get("votes");
        
        votes.put("funny", ((Long)jsonVotes.get("funny")).intValue());
        votes.put("useful", ((Long)jsonVotes.get("useful")).intValue());
        votes.put("cool", ((Long)jsonVotes.get("cool")).intValue());
        
        reviewCount = ((Long)jsonData.get("review_count")).intValue();
        type = (String) jsonData.get("type");
        userID = (String) jsonData.get("user_id");
        name = (String) jsonData.get("name");
        averageStars = (double) jsonData.get("average_stars");
    }
    
    /**
     * 
     * @return the User's name
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return the User's alphanumeric user ID, which should be unique for
     * every Yelp user
     */
    public String getUserID(){
        return userID;
    }
    
    /**
     * Returns an unmodifiable map of the user's vote tallies. The three keys
     * in the map are "funny", "useful", and "cool". The integer that each 
     * corresponds to is the number of times that this user has been voted 
     * "funny", "useful", or "cool" by other Yelp users
     * @return an unmodifiable map of the user's vote tallies
     */
    public Map<String, Integer> getVotes(){
        return Collections.unmodifiableMap(votes);
    }
    
    /**
     * 
     * @return the average number of stars that this Yelp user gives out to 
     * restaurants that they review
     */
    public double getAverageStars(){
        return averageStars;
    }
    
    /**
     * 
     * @return the JSON entity containing this user's Yelp info
     */
    public JSONObject getJSONInfo(){
        return jsonInfo;
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof User)){
            return false;
        }
        User that = (User) obj;
        return(this.userID.equals(that.userID));
    }
    
    @Override
    public int hashCode(){
        return userID.hashCode();
    }
}
