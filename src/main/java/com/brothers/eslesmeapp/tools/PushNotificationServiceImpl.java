package com.brothers.eslesmeapp.tools;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class PushNotificationServiceImpl {
    private final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
    private final String FIREBASE_SERVER_KEY = "AAAAUUps8vw:APA91bFIfywanUfQTWtApzHfaGy23ZgRhz4E9hWqRWXs0gHZgRDfboDpHrbAsDw_KELHgduj4MDJOS_8ipj5m8rDduwB0mjIEBHCApEaEwPzUo9o2GMgqTFbzDQyAhse9SwRhkrMHXpB";


    public void sendPushNotification(String key, String messageTitle, String message) {


        JSONObject msg = new JSONObject();

        msg.put("title", messageTitle);
        msg.put("body", message);
        msg.put("view", "CozdugumTestler");
        msg.put("click_action", "FLUTTER_NOTIFICATION_CLICK");

    //    System.out.println("\nCalling fcm Server >>>>>>>");
        String response = callToFcmServer(msg, key);
      //  System.out.println("Got response from fcm Server : " + response + "\n\n");

       /* keys.forEach(key -> {
            System.out.println("\nCalling fcm Server >>>>>>>");
            String response = callToFcmServer(msg, key);
            System.out.println("Got response from fcm Server : " + response + "\n\n");
        }); */

    }

    private String callToFcmServer(JSONObject message, String receiverFcmKey) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + FIREBASE_SERVER_KEY);
        httpHeaders.set("Content-Type", "application/json");

        JSONObject json = new JSONObject();

        json.put("data", message);
        json.put("notification", message);
        json.put("to", receiverFcmKey);

     //   System.out.println("Sending :" + json.toString());

        HttpEntity<String> httpEntity = new HttpEntity<>(json.toString(), httpHeaders);
        return restTemplate.postForObject(FIREBASE_API_URL, httpEntity, String.class);
    }
}