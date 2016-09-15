package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ca.ece.ubc.cpen221.mp5.statlearning.Algorithms;
import ca.ece.ubc.cpen221.mp5.statlearning.Point;

public class Test {
    public static void main(String[] args){
        JSONParser parser = new JSONParser();
        /*try {
            Object obj = parser
                    .parse(new BufferedReader(new FileReader("data/restaurants.json")).readLine());

            JSONObject jsonObject = (JSONObject) obj; 
            Restaurant test = new Restaurant(jsonObject);
            System.out.println(test.getAddress());
            System.out.println(test.getBusinessID());
            System.out.println(test.getLatitude());
            System.out.println(test.getLongitude());
            System.out.println(test.getName());
            System.out.println(test.getRating());
            System.out.println(test.getPrice());
            List<String> neighborhoods = test.getNeighborhoods();
            for(int i = 0; i < neighborhoods.size(); i++){
                System.out.println(neighborhoods.get(i));
            }
            List<String> categories = test.getCategories();
            for(int i = 0; i < categories.size(); i++){
                System.out.println(categories.get(i));
            }
            Restaurant test2 = new Restaurant(jsonObject);
            System.out.println(test.equals(test2));
            System.out.println(test.hashCode());
            System.out.println(test2.hashCode());
        }catch(Exception e){
            e.printStackTrace();
        }*/
        
        /*try {
            Object obj = parser
                    .parse(new BufferedReader(new FileReader("data/users.json")).readLine());

            JSONObject jsonObject = (JSONObject) obj; 
            User test = new User(jsonObject);
            System.out.println(test.getName());
            System.out.println(test.getUserID());
            System.out.println(test.getAverageStars());
            Map<String, Integer> votes = test.getVotes();
            System.out.println(votes.get("funny"));
            System.out.println(votes.get("useful"));
            System.out.println(votes.get("cool"));
            User test2 = new User(jsonObject);
            System.out.println(test.equals(test2));
            System.out.println(test.hashCode());
            System.out.println(test2.hashCode());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/reviews.json"));
            Object obj = parser.parse(reader.readLine());
            Object obj2 = parser.parse(reader.readLine());

            JSONObject jsonObject = (JSONObject) obj; 
            Review test = new Review(jsonObject);
            System.out.println(test.getReviewID());
            System.out.println(test.getUserID());
            System.out.println(test.getStars());
            System.out.println(test.getReviewText());
            System.out.println(test.getBusinessID());
            Map<String, Integer> votes = test.getVotes();
            System.out.println(votes.get("funny"));
            System.out.println(votes.get("useful"));
            System.out.println(votes.get("cool"));
            Review test2 = new Review(jsonObject);
            System.out.println(test.equals(test2));
            System.out.println(test.hashCode());
            System.out.println(test2.hashCode());
            JSONObject jsonObject2 = (JSONObject) obj2;
            Review test3 = new Review(jsonObject2);
            System.out.println(test.equals(test3));
            System.out.println(test3.hashCode());
        }catch(Exception e){
            e.printStackTrace();
        }*/
        /*
        RestaurantDB database = new RestaurantDB("data/restaurants.json",
                "data/reviewstest.json", "data/users.json");
        System.out.println(database.randomReview("buttmunchies"));
        System.out.println(database.getRestaurant("QQIjsdcokFermi2ugoD6ow"));
        System.out.println(database.getRestaurant("whaaaaa"));*/
        /*try {
            BufferedReader reader = new BufferedReader(new FileReader("data/reviewstest.json"));
            Object obj = parser.parse(reader.readLine());
            JSONObject reviewInfo = (JSONObject) obj;
            System.out.println(database.addReview(reviewInfo));
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/reviews.json"));
            Object obj = parser.parse(reader.readLine());
            JSONObject reviewInfo = (JSONObject) obj;
            System.out.println(database.addReview(reviewInfo));
        }catch(Exception e){
            e.printStackTrace();
        }*/
        
        RestaurantDB database = new RestaurantDB("data/kmeanstest.json");
        //RestaurantDB database = new RestaurantDB("data/restaurants.json",
        //       "data/reviews.json", "data/users.json");
        List<Set<Restaurant>> clusters = Algorithms.kMeansClustering(5, database);
        System.out.println("finished calculating k-means");
        for(int i = 0; i < clusters.size(); i++){
            System.out.println("Cluster: " + i + " size: " + clusters.get(i).size());
            /*for(Restaurant r : clusters.get(i)){
                System.out.println("LAT: " + r.getLatitude() + " LONG: " + r.getLongitude());
            }*/
        }
        String jsonText = Algorithms.convertClustersToJSON(clusters);
        System.out.println(jsonText);
        try {
            PrintWriter out = new PrintWriter("C:/Users/Jacob/workspace/mp5-fall2015/visualize/voronoi.json");
            out.println(jsonText);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
