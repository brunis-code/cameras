package io.mocky.cameras;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Класс unit тестов.
 * @autor safin.v
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@AutoConfigureMockMvc
class CamerasApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void find_camNotFound_404() throws Exception {

        mockMvc.perform(get("/cameras")).andExpect(status().isNotFound());

    }


    @Test
    public void find_camData() throws Exception {
        String expectedJson = "[{\"id\":1,\"urlType\":\"LIVE\",\"videoUrl\":\"rtsp://127.0.0.1/1\",\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\",\"ttl\":120},{\"id\":20,\"urlType\":\"ARCHIVE\",\"videoUrl\":\"rtsp://127.0.0.1/2\",\"value\":\"fa4b5b22-249b-11e9-ab14-d663bd873d93\",\"ttl\":60},{\"id\":3,\"urlType\":\"ARCHIVE\",\"videoUrl\":\"rtsp://127.0.0.1/3\",\"value\":\"fa4b5d52-249b-11e9-ab14-d663bd873d93\",\"ttl\":120},{\"id\":2,\"urlType\":\"LIVE\",\"videoUrl\":\"rtsp://127.0.0.1/20\",\"value\":\"fa4b5f64-249b-11e9-ab14-d663bd873d93\",\"ttl\":180}]";
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);

        JSONAssert.assertEquals(expectedJson, response.getBody(), false);

    }
}
