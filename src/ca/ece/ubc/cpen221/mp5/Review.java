package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONObject;
import java.util.*;

// TODO: Use this class to represent a Yelp review.

public class Review {
    /**
     * Rep invariant:
     *  The data in all the fields of the Review class must match those
     *  given in this review's entry in the Yelp review dataset provided.
     * Abstraction function:
     *  Represents information about a particular review on Yelp, including
     *  the review's contents, as well as information about the user who wrote
     *  it and the restaurant that is the subject of the review
     */
    
    private String type;
    private String businessID;
    private String businessName;
    private Map<String, Integer> votes;
    private String reviewID;
    private String text;
    private int stars;
    private String userID;
    private String date;
    private JSONObject jsonInfo;
    
    /**
     * Creates a Review object that corresponds to one entry in the Yelp
     * dataset  
     * @param jsonData: the JSONObject that holds the data corresponding to one
     * entry in the Yelp review dataset
     */
    public Review(JSONObject jsonData){
        jsonInfo = jsonData;
        type = (String) jsonData.get("type");
        businessID = (String) jsonData.get("business_id");
        JSONObject jsonVotes = (JSONObject) jsonData.get("votes");
        votes = new HashMap<String, Integer>();
        
        votes.put("funny", ((Long)jsonVotes.get("funny")).intValue());
        votes.put("useful", ((Long)jsonVotes.get("useful")).intValue());
        votes.put("cool", ((Long)jsonVotes.get("cool")).intValue());
        
        reviewID = (String) jsonData.get("review_id");
        text = (String) jsonData.get("text");
        stars = ((Long)jsonData.get("stars")).intValue();
        userID = (String) jsonData.get("user_id");
        date = (String) jsonData.get("date");
    }
    
    /**
     * 
     * @return the alphanumeric ID of the business that is the 
     * subject of the review
     */
    public String getBusinessID(){
        return businessID;
    }
    
    /**
     * 
     * @return the alphanumeric ID of this specific review
     */
    public String getReviewID(){
        return reviewID;
    }
    
    /**
     * 
     * @return the alphanumeric ID of the user who wrote the review
     */
    public String getUserID(){
        return userID;
    }
    
    /**
     * 
     * @return the full text of the body of the review
     */
    public String getReviewText(){
        return text;
    }
    
    /**
     * Returns an unmodifiable map of the review's vote tallies. The three keys
     * in the map are "funny", "useful", and "cool". The integer that each 
     * corresponds to is the number of times that this review has been voted 
     * "funny", "useful", or "cool" by other Yelp users
     * @return an unmodifiable map of the review's vote tallies
     */
    public Map<String, Integer> getVotes(){
        return Collections.unmodifiableMap(votes);
    }
    
    /**
     * 
     * @return returns the number of stars awarded to the restaurant in this review
     */
    public int getStars(){
        return stars;
    }
    
    /**
     * 
     * @return the JSON entity containing this review's Yelp info
     */
    public JSONObject getJSONInfo(){
        return jsonInfo;
    }
    
    /**
     * Sets the name of the restaurant that this review is about
     * @param businessName: the name of the restaurant that the review is about
     */
    public void setBusinessName(String businessName){
        this.businessName = businessName;
    }
    
    /**
     * 
     * @return the name of the restaurant that is the subject of the review
     */
    public String getBusinessName(){
        return businessName;
    }
    
    @Override 
    public boolean equals(Object obj){
        if(!(obj instanceof Review))
            return false;
        Review that = (Review) obj;
        return that.reviewID == this.reviewID;
    }
    
    @Override
    public int hashCode(){
        return reviewID.hashCode();
    }
}
