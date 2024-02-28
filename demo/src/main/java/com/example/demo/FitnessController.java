package com.example.demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
//import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FitnessController {
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";


    @GetMapping("/check")
    public String getURLStatus(@RequestParam String url) throws URISyntaxException {
        String returnMessage = "";
        try {
            @SuppressWarnings("deprecation")
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if (responseCodeCategory != 2 || responseCodeCategory != 3) {
                returnMessage = SITE_IS_DOWN;

            } else {
                returnMessage = SITE_IS_UP;

            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;

        }
        return returnMessage;
    }

}
