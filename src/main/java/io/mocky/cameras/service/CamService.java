package io.mocky.cameras.service;

import io.mocky.cameras.model.CamData;
import io.mocky.cameras.model.Camera;
import io.mocky.cameras.model.SourceData;
import io.mocky.cameras.model.TokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Класс сервиса для многопоточной обработки данных по камерам SourceData и TokenData.
 * @autor safin.v
 */
@Service
public class CamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CamService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AsyncService asyncService;



    public List<CamData> getCamData() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        //запрашиваем список доступных видеокамер
        ResponseEntity<List<Camera>> response = restTemplate.exchange(
                "http://www.mocky.io/v2/5c51b9dd3400003252129fb5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Camera>>() {
                });

        List<Camera> cameras = response.getBody();

        List<CompletableFuture<Object>> futureList = new ArrayList<>();

        // обрабатываем каждый запрос в отдельном потоке
        for (int i = 0; i < cameras.size(); i++) {
            LOGGER.info("getCameraData " + cameras.get(i));
            futureList.add(asyncService.getCameraData(SourceData.class, cameras.get(i).getSourceDataUrl()));
            futureList.add(asyncService.getCameraData(TokenData.class, cameras.get(i).getTokenDataUrl()));
        }

        //ждем завершения всех потоков
        sequence(futureList);
        LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));

            return  futuresToCamData(cameras, futureList);

    }

    private <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.toList())
        );
    }

    private <T> List<CamData> futuresToCamData(List<Camera> cameras, List<CompletableFuture<T>> futures) throws ExecutionException, InterruptedException {
        List<CamData> tList = new ArrayList<>();
        int countFuture = 0;
        for (int i = 0; i < cameras.size(); i++) {
            CamData camData = new CamData(cameras.get(i), (SourceData) futures.get(countFuture++).get(), (TokenData)futures.get(countFuture++).get());
            tList.add(camData);

        }

        return tList;
    }

}
