package com.example.apigatewayservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class PriceClientImpl implements PriceClient{
    /**
     * @return
     */
    @Override
    public String getPrice() {
        var httpClient = HttpClient.newHttpClient();
        var httpGet = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:50006/price"))
                .build();

        try {
            log.info("Sending request to fetch price info");
            var httpResponse = httpClient.send(httpGet, HttpResponse.BodyHandlers.ofString());
            logResponse(httpResponse);
            return httpResponse.body();
        } catch (IOException e) {
            log.error("Failure occurred while getting price info", e);
        } catch (InterruptedException e) {
            log.error("Failure occurred while getting price info", e);
            Thread.currentThread().interrupt();
        }

        return null;
    }

    private void logResponse(HttpResponse<String> httpResponse) {
        if (isSuccessResponse(httpResponse.statusCode())) {
            log.info("Price info received successfully");
        } else {
            log.warn("Price info request failed");
        }
    }

    private boolean isSuccessResponse(int responseCode) {
        return responseCode >= 200 && responseCode <= 299;
    }
}
