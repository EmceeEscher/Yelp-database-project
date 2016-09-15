package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Restaurant {
    /**
     * Rep invariant:
     *  The data in all the fields of the Restaurant class must match those
     *  given in this restaurant's entry in the Yelp restaurant dataset provided.
     * Abstraction function:
     *  Represents Yelp information about a restaurant in Berkeley, including 
     *  data pertaining to the name, location, type, quality, and price of 
     *  the restaurant.
     */
    private boolean open;
    private String url;
    private double longitude;
    private double latitude;
    private List<String> neighborhoods;
    private String businessID;
    private String name;
    private List<String> categories;
    private String state;
    private String city;
    private String type;
    private double stars;
    private String address;
    private long reviewCount;
    private String photoURL;
    private List<String> schools;
    private long price;
    private JSONObject jsonInfo;
    
    /**
     * Creates a Restaurant object that corresponds to one entry in the Yelp
     * dataset  
     * @param jsonData: the JSONObject that holds the data corresponding to one
     * entry in the Yelp restaurant dataset
     */
    @SuppressWarnings("unchecked")
    public Restaurant(JSONObject jsonData){
        jsonInfo = jsonData;
        open = (boolean) jsonData.get("open");
        url = (String) jsonData.get("url");
        longitude = (double) jsonData.get("longitude");
        latitude = (double) jsonData.get("latitude");
        categories = new ArrayList<String>();
        JSONArray jsonCategories = (JSONArray) jsonData.get("categories");
        Iterator<String> categoryIterator = jsonCategories.iterator();
        while(categoryIterator.hasNext()){
            categories.add(categoryIterator.next());
        }
        neighborhoods = new ArrayList<String>();
        JSONArray jsonNeighborhoods = (JSONArray) jsonData.get("neighborhoods");
        Iterator<String> neighborhoodIterator = jsonNeighborhoods.iterator();
        while(neighborhoodIterator.hasNext()){
            neighborhoods.add(neighborhoodIterator.next());
        }
        businessID = (String) jsonData.get("business_id");
        type = (String) jsonData.get("type");
        stars = (double) jsonData.get("stars");
        name = (String) jsonData.get("name");
        address = (String) jsonData.get("full_address");
        reviewCount = (long) jsonData.get("review_count");
        photoURL = (String) jsonData.get("photo_url");
        price = (long) jsonData.get("price");
        state = (String) jsonData.get("state");
        type = (String) jsonData.get("type");
        city = (String) jsonData.get("city");
        schools = new ArrayList<String>();
        JSONArray jsonSchools = (JSONArray) jsonData.get("schools");
        Iterator<String> schoolIterator = jsonSchools.iterator();
        while(schoolIterator.hasNext()){
            schools.add(schoolIterator.next());
        }
        
    }
    
    /**
     * 
     * @return the restaurant's longitude
     */
    public double getLatitude(){
        return latitude;
    }
    
    /**
     * 
     * @return the restaurant's latitude
     */
    public double getLongitude(){
        return longitude;
    }
    
    /**
     * 
     * @return the restaurant's name
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return the restaurant's full street address
     */
    public String getAddress(){
        return address;
    }
    
    /**
     * Returns the restaurant's star rating. The rating is on a scale from 
     * 0 to 5, with half-points possible. The higher the rating, the better
     * the restaurant
     * @return the restaurant's star rating
     */
    public double getRating(){
        return stars;
    }
    
    /**
     * Returns the restaurant's price category, on a scale from 1 to 4, with
     * a higher number denoting a more expensive restaurant
     * @return the restaurant's price category
     */
    public int getPrice(){
        return (int) price;
    }
    
    /**
     * 
     * @return the restaurant's alphanumeric business ID
     */
    public String getBusinessID(){
        return businessID;
    }
    
    /**
     * 
     * @return an unmodifiable list that contains the neighborhoods that the
     * restaurant is located in
     */
    public List<String> getNeighborhoods(){
        return Collections.unmodifiableList(neighborhoods);
    }
    
    /**
     * @return an unmodifiable list that contains the Yelp categories that the 
     * restaurant is in
     */
    public List<String> getCategories(){
        return Collections.unmodifiableList(categories);
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
        if(!(obj instanceof Restaurant)){
            return false;
        }
        Restaurant that = (Restaurant) obj;
        return (that.businessID.equals(this.businessID));
    }

    @Override
    public int hashCode(){
        return businessID.hashCode();
    }

}
