package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.List;
import java.util.*;
import ca.ece.ubc.cpen221.mp5.*;

public class Algorithms {

    private static final double MIN_LONG = -122.27;
    private static final double MAX_LONG = -122.24;
    private static final double MIN_LAT = 37.86;
    private static final double MAX_LAT = 37.88;

    /**
     * Use k-means clustering to compute k clusters for the restaurants in the
     * database.
     * 
     * @param db:
     *            the database containing all the restaurant info
     * @param k:
     *            the number of clusters to compute; k must be greater than 0
     * @return a List of k Sets of Restaurants, where each Set contains the
     *         restaurants closest to a given cluster
     */
    public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {
        Map<Point, Set<Restaurant>> clusters;
        Point[] centers = new Point[k];
        for (int i = 0; i < k; i++) {
            double longDiff = MAX_LONG - MIN_LONG;
            double centerLong = Math.random() * longDiff + MIN_LONG;
            double latDiff = MAX_LAT - MIN_LAT;
            double centerLat = Math.random() * latDiff + MIN_LAT;
            Point center = new Point(centerLat, centerLong);
            centers[i] = center;
        }
        Collection<Restaurant> restaurants = db.getRestaurantMap().values();
        while (true) {
            clusters = createClusters(restaurants, centers);
            Point[] newCenters = calculateCenters(clusters);
            boolean centersChanged = false;
            for (int i = 0; i < newCenters.length; i++) {
                if (!centers[i].equals(newCenters[i])) {
                    centersChanged = true;
                }
            }
            if (!centersChanged) {
                List<Set<Restaurant>> clusterList = new ArrayList<Set<Restaurant>>();
                for (Point key : clusters.keySet()) {
                    clusterList.add(clusters.get(key));
                }
                return clusterList;
            }
            centers = newCenters;
        }
    }

    /**
     * Creates a cluster of Restaurants for each center, where each cluster
     * consists of all the Restaurants that are closer to that center than any
     * other center
     * 
     * @param restaurants:
     *            a collection of all Restaurants to be added to clusters
     * @param centers:
     *            an array of the current cluster centers, in Point form
     * @return clusters: a Map, where the keys are the current cluster centers,
     *         and the values are a Set of all Restaurants that are closer to
     *         the key center than any other center
     * 
     */
    private static Map<Point, Set<Restaurant>> createClusters(
            Collection<Restaurant> restaurants, Point[] centers) {
        int numClusters = centers.length;
        Map<Point, Set<Restaurant>> clusters = new HashMap<Point, Set<Restaurant>>();
        for (int i = 0; i < numClusters; i++) {
            clusters.put(centers[i], new HashSet<Restaurant>());
        }
        for (Restaurant r : restaurants) {
            Point location = new Point(r.getLatitude(), r.getLongitude());
            Point nearestCenter = centers[0];
            double shortestDistance = location.getDistance(nearestCenter);
            for (int i = 1; i < numClusters; i++) {
                if (location.getDistance(centers[i]) < shortestDistance) {
                    nearestCenter = centers[i];
                    shortestDistance = location.getDistance(centers[i]);
                }
            }
            clusters.get(nearestCenter).add(r);
        }
        return clusters;
    }

    /**
     * Calculates the new centers of each cluster in the given map
     * 
     * @param clusters:
     *            a Map with the current centers as keys, and their given
     *            clusters as values
     * @return centers: an array of Points that are the new centers of each
     *         cluster in the map. centers.length is equal to clusters.size()
     */
    private static Point[] calculateCenters(Map<Point, Set<Restaurant>> clusters) {
        Point[] centers = new Point[clusters.keySet().size()];
        int centerIndex = 0;
        for (Point key : clusters.keySet()) {
            Set<Restaurant> cluster = clusters.get(key);
            if(cluster.size() == 0){
                /*If a given center is not the closest center to any restaurant,
                 * calculate a new center to take its place. This may not fit
                 * the exact definition of k-means clustering, but it produces
                 * better clusters (i.e. no empty clusters) than alternatives.*/
                double longDiff = MAX_LONG - MIN_LONG;
                double centerLong = Math.random() * longDiff + MIN_LONG;
                double latDiff = MAX_LAT - MIN_LAT;
                double centerLat = Math.random() * latDiff + MIN_LAT;
                Point center = new Point(centerLat, centerLong);
                centers[centerIndex] = center;
            }else{
                double latSum = 0.0;
                double longSum = 0.0;
                for (Restaurant r : cluster) {
                    latSum += r.getLatitude();
                    longSum += r.getLongitude();
                }
                double latAverage = latSum / cluster.size();
                double longAverage = longSum / cluster.size();
                centers[centerIndex] = new Point(latAverage, longAverage);
            }
            centerIndex++;
        }

        return centers;
    }
    /**
     * Converts a given list of Restaurant clusters to a String in JSON format
     * giving information about the restaurants including which cluster they 
     * belong to
     * @param a list of clusters of restaurants
     * @return a String in JSON format of information about the given restaurants
     * including which cluster they are in
     */
    @SuppressWarnings("unchecked")
    public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
        JSONArray jsonRestaurantList = new JSONArray();
        for(int i = 0; i < clusters.size(); i++){
            for(Restaurant r : clusters.get(i)){
                Map restaurantMap = new LinkedHashMap();
                restaurantMap.put("x", r.getLongitude());
                restaurantMap.put("y", r.getLatitude());
                restaurantMap.put("name", r.getName());
                restaurantMap.put("cluster", i);
                restaurantMap.put("weight", 68.9);
                JSONObject restaurant = new JSONObject(restaurantMap);
                jsonRestaurantList.add(restaurant);
            }
        }
        
        return jsonRestaurantList.toJSONString();
    }

    /**
     * Returns a predictor function that will predict a rating for any restaurant
     * based on the given user's previous reviews and a certain criteria
     * @param u: the user whose reviews will be used to generate the predictor function
     * @param db: the database with all the Yelp data for restaurants, reviews, and users
     * @param featureFunction: The class of the featureFunction determines which
     * parameter will be used to generate the feature function. The actual content
     * of the featureFunction does not matter. featureFunction must be one of:
     * MeanRatingFunction, CategoryFunction, LongitudeFunction, LatitudeFunction,
     * PriceFunction. If not one of these, the method will return null
     * @return an MP5Function that will predict any restaurant's rating based on
     * a certain parameter and a user's past reviews. It will an instanceof the 
     * same class as featureFunction and will use the same parameter to predict ratings.
     */
    public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
        List<Review> userReviews = new ArrayList<Review>();
        String userID = u.getUserID();
        Map<String, Review> reviewMap = db.getReviewMap();
        for(Review r : reviewMap.values()){
            if(r.getUserID().equals(userID)){
                userReviews.add(r);
                /*System.out.println(r.getReviewID());
                Restaurant rest = new Restaurant(db.getRestaurant(r.getBusinessID()));
                System.out.println(rest.getPrice());
                System.out.println(r.getStars());*/          
            }
        }
        if(featureFunction instanceof PriceFunction){
            return getPricePredictor(userReviews, db);
        }else if(featureFunction instanceof MeanRatingFunction){
            return getMeanRatingPredictor(userReviews, db);
        }else if(featureFunction instanceof LongitudeFunction){
            return getLongitudePredictor(userReviews, db);
        }else if(featureFunction instanceof LatitudeFunction){
            return getLatitudePredictor(userReviews, db);
        }else if(featureFunction instanceof CategoryFunction){
            return getCategoryPredictor(userReviews, db);
        }
        return null;
    }
    
    /**
     * Generates a predictor function that uses price as its input, based on 
     * the given reviews
     * @param userReviews: the reviews used to generate the predictor function
     * @param db: the database containing all the Yelp data for restaurants,
     * reviews, and users
     * @return a function that predicts a rating for a restaurant given the price
     * of that restaurant
     */
    public static PriceFunction getPricePredictor(List<Review> userReviews, RestaurantDB db){
        List<Double> prices = new ArrayList<Double>();
        List<Double> ratings = new ArrayList<Double>();
        for(Review r : userReviews){
            JSONObject jsonRestaurant = db.getRestaurant(r.getBusinessID());
            Restaurant currRestaurant = new Restaurant(jsonRestaurant);
            prices.add((double) currRestaurant.getPrice());
            ratings.add((double) r.getStars());
        }
        double[] params = calculateParameters(prices, ratings);
        return new PriceFunction(params[0], params[1], params[2]);
    }
    
    /**
     * Generates a predictor function that uses the restaurant's mean rating as 
     * its input, based on the given reviews
     * @param userReviews: the reviews used to generate the predictor function
     * @param db: the database containing all the Yelp data for restaurants,
     * reviews, and users
     * @return a function that predicts a rating for a restaurant given the mean rating
     * of that restaurant
     */
    public static MeanRatingFunction getMeanRatingPredictor(List<Review> userReviews, RestaurantDB db){
        List<Double> meanRatings = new ArrayList<Double>();
        List<Double> ratings = new ArrayList<Double>();
        for(Review r : userReviews){
            JSONObject jsonRestaurant = db.getRestaurant(r.getBusinessID());
            Restaurant currRestaurant = new Restaurant(jsonRestaurant);
            meanRatings.add(currRestaurant.getRating());
            ratings.add((double) r.getStars());
        }
        double[] params = calculateParameters(meanRatings, ratings);
        return new MeanRatingFunction(params[0], params[1], params[2]);
    }
    
    /**
     * Generates a predictor function that uses the restaurant's longitude as 
     * its input, based on the given reviews
     * @param userReviews: the reviews used to generate the predictor function
     * @param db: the database containing all the Yelp data for restaurants,
     * reviews, and users
     * @return a function that predicts a rating for a restaurant given the longitude
     * of that restaurant
     */
    public static LongitudeFunction getLongitudePredictor(List<Review> userReviews, RestaurantDB db){
        List<Double> longitudes = new ArrayList<Double>();
        List<Double> ratings = new ArrayList<Double>();
        for(Review r : userReviews){
            JSONObject jsonRestaurant = db.getRestaurant(r.getBusinessID());
            Restaurant currRestaurant = new Restaurant(jsonRestaurant);
            longitudes.add(currRestaurant.getLongitude());
            ratings.add((double) r.getStars());
        }
        double[] params = calculateParameters(longitudes, ratings);
        return new LongitudeFunction(params[0], params[1], params[2]);
    }
    
    /**
     * Generates a predictor function that uses the restaurant's latitude as 
     * its input, based on the given reviews
     * @param userReviews: the reviews used to generate the predictor function
     * @param db: the database containing all the Yelp data for restaurants,
     * reviews, and users
     * @return a function that predicts a rating for a restaurant given the latitude
     * of that restaurant
     */
    public static LatitudeFunction getLatitudePredictor(List<Review> userReviews, RestaurantDB db){
        List<Double> latitudes = new ArrayList<Double>();
        List<Double> ratings = new ArrayList<Double>();
        for(Review r : userReviews){
            JSONObject jsonRestaurant = db.getRestaurant(r.getBusinessID());
            Restaurant currRestaurant = new Restaurant(jsonRestaurant);
            latitudes.add(currRestaurant.getLatitude());
            ratings.add((double) r.getStars());
        }
        double[] params = calculateParameters(latitudes, ratings);
        return new LatitudeFunction(params[0], params[1], params[2]);
    }
    
    /**
     * Generates a predictor function that uses the restaurant's categories as 
     * its input, based on the given reviews (this predictor is not very good)
     * @param userReviews: the reviews used to generate the predictor function
     * @param db: the database containing all the Yelp data for restaurants,
     * reviews, and users
     * @return a function that predicts a rating for a restaurant given the categories
     * of that restaurant
     */
    public static CategoryFunction getCategoryPredictor(List<Review> userReviews, RestaurantDB db){
        List<Double> categoryNums = new ArrayList<Double>();
        List<Double> ratings = new ArrayList<Double>();
        for(Review r : userReviews){
            JSONObject jsonRestaurant = db.getRestaurant(r.getBusinessID());
            Restaurant currRestaurant = new Restaurant(jsonRestaurant);
            double categoryNum = currRestaurant.getCategories().get(0).substring(0, 1).hashCode();
            categoryNums.add(categoryNum);
            ratings.add((double) r.getStars());
        }
        double[] params = calculateParameters(categoryNums, ratings);
        return new CategoryFunction(params[0], params[1], params[2]);
    }
    
    /**
     * Calculates the a, b, and RSquared values for a feature function
     * @param xVals the xValues of the dataset used to generate the function
     * @param yVals the yValues of the dataset used to generate the function
     * For all x in xVals, and y in yVals, x[i] must correspond to y[i]
     * @return an array containing a, b, and RSquared values, in that order
     */
    private static double[] calculateParameters(List<Double> xVals, List<Double> yVals){
        double[] parameters = new double[3];
        
        double xMean = 0;
        double yMean = 0;
        for(int i = 0; i < xVals.size(); i++){
            xMean += xVals.get(i);
            yMean += yVals.get(i);
        }
        xMean /= xVals.size();
        yMean /= yVals.size();

        double Sxx = 0;
        double Syy = 0;
        double Sxy = 0;
        for(int i = 0; i < xVals.size(); i++){
            Sxx += (xVals.get(i) - xMean)*(xVals.get(i) - xMean);
            Syy += (yVals.get(i) - yMean)*(yVals.get(i) - yMean);
            Sxy += (xVals.get(i) - xMean)*(yVals.get(i) - yMean);
        }
        
        double b = Sxy / Sxx;
        double a = yMean - b * xMean;
        double R2 = (Sxy * Sxy) / (Sxx * Syy);
        
        parameters[0] = a;
        parameters[1] = b;
        parameters[2] = R2;
        
        return parameters;      
    }
    /**
     * Returns the MP5Function that is the best predictor (has highest R^2)
     * for the given user
     * @param u: the user to find a feature function for
     * @param db: the database containing the Yelp data for the restaurants,
     * reviews, and users
     * @param featureFunctionList: the list of featureFunctions that you want to
     * find the best one of for the given user. The content of the feature functions
     * does not matter only their types. All feature Functions in featureFunctionList
     * must be one of: CategoryFunction, LatitudeFunction, LongitudeFunction,
     * MeanRatingFunction, PriceFunction
     * @return the best predictor function for the given user of the types of
     * feature functions available
     */
    public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
        Map<MP5Function, Double> r2Values = new HashMap<MP5Function, Double>();
        for(int i = 0; i < featureFunctionList.size(); i++){
            MP5Function func = getPredictor(u, db, featureFunctionList.get(i));
            double r2 = func.getRSquared();
            r2Values.put(func, r2);
        }
        MP5Function bestPredictor = getPredictor(u, db, featureFunctionList.get(0));
        double highestR2 = bestPredictor.getRSquared();
        for(MP5Function func : r2Values.keySet()){
            if(r2Values.get(func) > highestR2){
                bestPredictor = func;
                highestR2 = r2Values.get(func);
            }
        }
        return bestPredictor;
    }
}