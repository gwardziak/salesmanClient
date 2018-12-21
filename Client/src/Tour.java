
/*
* Tour.java
* Stores a candidate tour
*/

import java.util.ArrayList;
import java.util.Collections;

public class Tour {

    // Holds our tour of cities
    private ArrayList tour = new ArrayList<City>();
    // Cache
    private double fitness = 0;
    private int distance = 0;

    // Constructs a blank tour
    public Tour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Tour(ArrayList tour) {
        this.tour = tour;
    }
    
    public void setFitness(double fitness) {
    	this.fitness = fitness;
    }
    
    public void setDistance(int distance) {
    	this.distance = distance;
    }
    
    public double getFitness() {
    	return fitness;
    }
    
    public int getDistance() {
    	return distance;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {
        return (City) tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i) + "|";
        }
        return geneString;
    }
}