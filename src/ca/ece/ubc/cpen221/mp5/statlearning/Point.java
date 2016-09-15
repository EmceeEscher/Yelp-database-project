package ca.ece.ubc.cpen221.mp5.statlearning;

public class Point {
    private double latitude;
    private double longitude;
    
    public Point(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * Returns the distance between this point and the given point
     * @param that: the point to find the distance from
     * @return the distance between this and that
     */
    public double getDistance(Point that){
        double latDifference = this.latitude - that.latitude;
        double longDifference = this.longitude - that.longitude;
        return Math.sqrt(latDifference * latDifference + longDifference * longDifference);
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Point)){return false;}
        Point that = (Point) obj;
        return (that.latitude == this.latitude) && (that.longitude == this.longitude);
    }
    
    public double getLatitude(){
        return latitude;
    }
    
    public double getLongitude(){
        return longitude;
    }
}
