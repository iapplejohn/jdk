package com.jemmy.https;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsTest2 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://monetization.api.unity.com/stats/v1/operate/organizations/1375592881146?fields=adrequest_count&apikey=fe8976c7f0798dc6c078cdb316c7a856da251519ba7b02e04342c5a876d1f25d&scale=day&start=2020-03-15T00:00:00Z&end=2020-03-15T00:00:00Z");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            System.out.println(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
