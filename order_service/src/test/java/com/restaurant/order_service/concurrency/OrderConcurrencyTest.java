package com.restaurant.order_service.concurrency;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class OrderConcurrencyTest {

    public static void main(String[] args)  {

        try (HttpClient client = HttpClient.newHttpClient()) {

        String orderA = """
                {
                    "customerId": 11,
                    "restaurantId": 2,
                    "items": [
                        {
                            "menuItemId": 6,
                            "quantity": 1
                        }
                    ]
                }
                """;

        String orderB = """
                {
                    "customerId": 12,
                    "restaurantId": 2,
                    "items": [
                        {
                            "menuItemId": 6,
                            "quantity": 1
                        }
                    ]
                }
                """;

        CountDownLatch startSignal = new CountDownLatch(1);

        HttpRequest requestA = createRequest(orderA);
        HttpRequest requestB = createRequest(orderB);

        CompletableFuture<HttpResponse<String>> futureA =
                CompletableFuture.supplyAsync(() -> sendRequest(client, requestA, startSignal));

        CompletableFuture<HttpResponse<String>> futureB =
                CompletableFuture.supplyAsync(() -> sendRequest(client, requestB, startSignal));

        // İki isteği aynı anda başlat.
        startSignal.countDown();

        HttpResponse<String> responseA = futureA.join();
        HttpResponse<String> responseB = futureB.join();

        System.out.println("Customer A: " + responseA.statusCode());
        System.out.println(responseA.body());

        System.out.println("Customer B: " + responseB.statusCode());
        System.out.println(responseB.body());




    }
    }

    private static HttpRequest createRequest(String json) {
        return HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/orders"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    private static HttpResponse<String> sendRequest(
            HttpClient client,
            HttpRequest request,
            CountDownLatch startSignal
    ) {
        try {
            startSignal.await();

            return client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
