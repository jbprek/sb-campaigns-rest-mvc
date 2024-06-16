package com.foo.campaign.endtoend;

import com.foo.campaign.web.dto.WorkerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WorkerEndToEndApiTest {

    @Autowired
    WebTestClient webClient;

    @Test
    void givenPreloadedData_whenGetSingleWorker_thenResponseFieldsMatch() {
        webClient.get()
                .uri("/workers/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(WorkerDto.class)
                .value(dto -> {
                    assertThat(dto.id()).isEqualTo(1L);
                    assertThat(dto.email()).contains("@");
                });

    }
}
