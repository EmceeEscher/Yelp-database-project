package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class CategoryFunction implements MP5Function{
    double a;
    double b;
    double R2;
    
    public CategoryFunction(double a, double b, double R2){
        this.a = a;
        this.b = b;
        this.R2 = R2;
    }
    
    /**
     * Compute a function that predicts a rating for the given restaurant
     * based on its mean rating.
     * 
     * @param yelpRestaurant: the restaurant to predict a rating for
     * @return the predicted rating for the restaurant based on its mean rating
     */
    public double f(Restaurant yelpRestaurant, RestaurantDB db){
        String categoryToUse = yelpRestaurant.getCategories().get(0);
        double x = categoryToUse.substring(0,1).hashCode();
        return a * x + b;
    }
    
    /**
     * 
     * @return this functions R^2 value, which is a numerical measure of its
     * predictive success
     */
    public double getRSquared(){
        return R2;
    }
    
    public double getA(){
        return a;
    }
    
    public double getB(){
        return b;
    }
}
