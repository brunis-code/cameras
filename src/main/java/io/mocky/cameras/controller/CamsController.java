package io.mocky.cameras.controller;

import io.mocky.cameras.model.CamData;
import io.mocky.cameras.model.Camera;
import io.mocky.cameras.service.CamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
/**
 * Класс контроллер для обработки запроса к Web сервису (http://127.0.0.1:8080).
 * @autor safin.v
 */
@RestController
public class CamsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CamsController.class);

    @Autowired
    CamService camService;



    @RequestMapping("/")
    public List<CamData> cameras() throws ExecutionException, InterruptedException {
        return camService.getCamData();
    }



}
