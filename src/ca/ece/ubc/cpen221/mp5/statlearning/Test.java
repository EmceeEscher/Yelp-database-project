package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import ca.ece.ubc.cpen221.mp5.RestaurantDB;
import ca.ece.ubc.cpen221.mp5.User;

public class Test {
    public static void main(String[] args){
        RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviewstest.json", "data/users.json");
        Object queryObj = JSONValue.parse("{\"url\": \"http://www.yelp.com/user_details?userid=90wm_01FAIqhcgV_mPON9Q\", \"votes\": {\"funny\": 18, \"useful\": 33, \"cool\": 13}, \"review_count\": 35, \"type\": \"user\", \"user_id\": \"90wm_01FAIqhcgV_mPON9Q\", \"name\": \"Charlie E.\", \"average_stars\": 3.42857142857143}");
        JSONObject queryJSON = (JSONObject) queryObj;
        User u = new User(queryJSON);
        PriceFunction p = new PriceFunction(0,0,0);
        LatitudeFunction l1 = new LatitudeFunction(0,0,0);
        LongitudeFunction l2 = new LongitudeFunction(0,0,0);
        CategoryFunction c = new CategoryFunction(0,0,0);
        MeanRatingFunction mr = new MeanRatingFunction(0,0,0);
        List<MP5Function> list = new ArrayList<MP5Function>();
        list.add(p);
        list.add(l1);
        list.add(l2);
        list.add(c);
        list.add(mr);
        MP5Function result = Algorithms.getBestPredictor(u, db, list);
        System.out.println("R2 = " + result.getRSquared());
    }
    
}
