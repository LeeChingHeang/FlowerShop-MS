package com.jsonserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*
 * TODO: Working on Json Server to get data from json file
 *
 */
public class JsonServer {
    public static void main(String[] args) throws IOException {
        // Replace with your actual API endpoint
        String apiUrl = "http://localhost:3000/users";

        // Create a URL object
        URL url = new URL(apiUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode =connection.getResponseCode();

        // Read the response data
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Process the JSON response
            System.out.println(response.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }

        // Close the connection
        connection.disconnect();
    }
}

