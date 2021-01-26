package io.mocky.cameras.service;

import io.mocky.cameras.model.CamData;
import io.mocky.cameras.model.Camera;
import io.mocky.cameras.model.SourceData;
import io.mocky.cameras.model.TokenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public <T> CompletableFuture<Object> getCameraData(Class<T> t, String url) {

        return CompletableFuture.completedFuture(restTemplate.getForObject(url, t));

    }


}
