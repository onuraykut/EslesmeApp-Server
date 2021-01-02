package com.brothers.eslesmeapp.tools;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class CreateDynamicLinkFirebase {
    private final String FIREBASE_SERVER_KEY = "AAAAZvToyvc:APA91bE0xjK9ubzPdrzrqjzLW2uCg2BZZCqiq3c0_wOVfO8wbj3_PJ77UJSogiZYeLJ7qHNIefvA1uw6hdUS8Qks0hsHrZn4ZZA1ND-QEqSym9KnXn9r07Y-oeYFf6GdltozENJi0lBC";
    private final String FIREBASE_WEB_API_KEY = "AIzaSyBdgIXvIlTz0YwJESxaAYhaA_oAbndddVI";
    private final String FIREBASE_API_URL = "https://firebasedynamiclinks.googleapis.com/v1/shortLinks?key="+FIREBASE_WEB_API_KEY;


    public String createShortURL(String testId,String roomId) {


        JSONObject msg = new JSONObject();
        

        msg.put("domainUriPrefix", "https://eslesmeapp.page.link");
        msg.put("link", "https://ucretsizkitapindir.com/"+testId+"-"+roomId);
        JSONObject android = new JSONObject();
        android.put("androidPackageName", "com.brothersoftware.eslesmeapp");
        msg.put("androidInfo", android);
       

        System.out.println("\nCalling fcm Server >>>>>>>");
        String response = callToFcmServer(msg);
        System.out.println("Got response from fcm Server : " + response + "\n\n");
        
        JSONObject obj = new JSONObject(response);
        return obj.getString("shortLink");

       /* keys.forEach(key -> {
            System.out.println("\nCalling fcm Server >>>>>>>");
            String response = callToFcmServer(msg, key);
            System.out.println("Got response from fcm Server : " + response + "\n\n");
        }); */

    }

    private String callToFcmServer(JSONObject message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
     //   httpHeaders.set("Authorization", "key=" + FIREBASE_SERVER_KEY);
        httpHeaders.set("Content-Type", "application/json");

        JSONObject json = new JSONObject();

        json.put("dynamicLinkInfo", message);
        JSONObject suffix = new JSONObject();
        suffix.put("option", "SHORT");
        json.put("suffix", suffix);
        System.out.println("Sending :" + json.toString());

        HttpEntity<String> httpEntity = new HttpEntity<>(json.toString(), httpHeaders);
        return restTemplate.postForObject(FIREBASE_API_URL, httpEntity, String.class);
    }
}