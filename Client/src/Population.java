/*
* Population.java
* Manages a population of candidate tours
*/
import java.io.BufferedReader;
import org.json.*;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.*;

public class Population {

    // Holds population of tours
    Tour[] tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }

    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }

    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    }

    public Tour getFittest() {
    	Tour tour = new Tour();
        Gson gson = new Gson();

        String toursJson = gson.toJson(tours);

        try {
        	String postUrl = "http://localhost:3000";
        	 HttpClient httpClient = new DefaultHttpClient();
        	HttpPost post = new HttpPost(postUrl);
            
            StringEntity postingString = new StringEntity(toursJson);
        	post.setEntity(postingString);
        	post.setHeader("Content-type", "application/json");
        	HttpResponse response = httpClient.execute(post);
            
            // Get the return message from the server           
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
   
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = bufReader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
         
            JSONObject jsonObj = new JSONObject(builder.toString());

            Tour newTour = new Tour();
            JSONArray arr = jsonObj.getJSONArray("tour");
            for (int i = 0; i < arr.length(); i++)
            {
            	String x = arr.getJSONObject(i).getString("x");
            	int xx = Integer.parseInt(x);
            	String y = arr.getJSONObject(i).getString("y");
            	int yy = Integer.parseInt(y);
            	newTour.setCity(i, new City(xx, yy));
            }
            
            
            String distance = jsonObj.getString("distance");
            String fitness = jsonObj.getString("fitness");
            int distance1 = Integer.parseInt(distance);
            double fitness1 = Double.parseDouble(fitness);
            newTour.setDistance(distance1);
            newTour.setFitness(fitness1);
            
            tour = newTour;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tour;
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }
}
