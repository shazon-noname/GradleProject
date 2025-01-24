package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class App {
    public boolean getGreeting(String[] args) {
        if (args.length != 1) {
            System.out.println("Помилка передачі параметрів\nПотрібен один параметр");
            return false;
        }
        String url = args[0];
        System.out.println("Url from args  : " + url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
    
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseBody = response.body().string();
                System.out.println("Response : " + responseBody);
                return true;
            } else {
                System.out.println("Request failed with HTTP error code " + response.code());
                return false;
            }
        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean result = new App().getGreeting(args);
        System.out.println("Success: " + result);
    }
}
