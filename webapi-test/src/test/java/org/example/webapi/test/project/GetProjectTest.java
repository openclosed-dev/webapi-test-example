package org.example.webapi.test.project;

import org.example.webapi.test.Project;
import org.example.webapi.test.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Sql("reset.sql")
@SqlMergeMode(MergeMode.MERGE)
public class GetProjectTest {

    @Autowired
    private WebTestClient client;

    @Test
    @Sql("add-project.sql")
    public void getProject() {
        var response = client.get().uri("/projects/101")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Response.One.class)
            .returnResult()
            .getResponseBody();

        Project project = (Project) response.data();
        assertThat(project).isNotNull();
        assertThat(project.id()).isEqualTo("101");
        assertThat(project.name()).isEqualTo("Project foo");
    }

    @Test
    public void getNonexistentProject() {
        var response = client.get().uri("/projects/999")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Response.Errors.class)
            .returnResult()
            .getResponseBody();

        var errors = response.errors();
        assertThat(errors).hasSize(1);

        var error = errors.getFirst();
        assertThat(error.code()).isEqualTo("101");
        assertThat(error.status()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
