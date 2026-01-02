/**
 Culminating Project
 This program is a cafe game called Bora's Cafe, where you buy ingredients every day ranging from coffee, cream sugar and ice.
 You get a daily report on your earnings + how many ingredients you have left over. The number of customers are determined by the weather.
 To win you must earn over $50 so that Bora can upgrade her cafe to sell pastries and baked goods.
 Written by: Shorna Kundu
 Last modified: May 18, 2024
 */
import com.google.gson.Gson;
import java.net.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        // creating an instance of coffee using the API link
        Coffee[] icedCoffee = API("https://api.sampleapis.com/coffee/iced");

        // creating a new game + starting the action listener for the buttons
        GUI gui = new GUI(icedCoffee);
        gui.setUpButtonListeners();
    }

    /*
    This is the tutorial I used to ue the API: https://www.youtube.com/watch?v=zZoboXqsCNw&t=88s
    this is how I got the GSON library installed: https://www.youtube.com/watch?v=Qc9EfiepfWs
    the following links helped me figure out how to use the GSON library (parse JSON)
    https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
    https://stackoverflow.com/questions/5314813/json-gson-fromjson-java-objects
     */

    /**
     * method to grab API information
     * @param APILink --> link to the public API
     * @return --> returns the values from the API
     */
    public static Coffee[] API(String APILink) {
        // try catch to test for errors when opening API
        try {
            // creating an instance of the URL class
            URL url = new URI(APILink).toURL();

            // connecting to the web service
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // get request --> trying to get data
            con.setRequestMethod("GET");
            con.connect();

            // getting the requse code
            int responseCode = con.getResponseCode();

            // status code 200 means the request has succeeded
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);

            } else {
                /* string builder instance --> "class in the Java API that provides a mutable sequence of characters."
                   https://www.simplilearn.com/tutorials/java-tutorial/stringbuilder-in-java
                 */
                StringBuilder informationString = new StringBuilder();
                Scanner scan = new Scanner(url.openStream()); //scanner for URL info

                // adding it to the StringBuilder
                while (scan.hasNext()) {
                    informationString.append(scan.nextLine());
                }
                scan.close();
                // System.out.println(informationString);

                // GSON Library
                Gson gson = new Gson();

                // De-serialize to an object --> Take info
                Coffee[] coffeeList = gson.fromJson(informationString.toString(), Coffee[].class);

//                for (Coffee coffee : coffeeList) {
//                    System.out.println(coffee.toString());
//                }
                
                return coffeeList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
