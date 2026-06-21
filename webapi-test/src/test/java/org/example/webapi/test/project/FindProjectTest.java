package org.example.webapi.test.project;

import org.example.webapi.test.Project;
import org.example.webapi.test.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Sql("reset.sql")
@SqlMergeMode(MergeMode.MERGE)
public class FindProjectTest {

    @Autowired
    private WebTestClient client;

    @Test
    @Sql("add-project.sql")
    public void findOneProject() {
        var response = client.get().uri("/projects")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Response.Many.class)
            .returnResult()
            .getResponseBody();

        var data = response.data();
        assertThat(data).isNotNull().hasSize(1);

        var first = (Project) data.get(0);
        assertThat(first.id()).isEqualTo("101");
        assertThat(first.name()).isEqualTo("Project foo");
    }

    @Test
    @Sql("add-projects.sql")
    public void findManyProjects() {
        var response = client.get().uri("/projects")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Response.Many.class)
            .returnResult()
            .getResponseBody();

        var data = response.data();
        assertThat(data).isNotNull().hasSize(3);

        var first = (Project) data.get(0);
        assertThat(first.id()).isEqualTo("101");
        assertThat(first.name()).isEqualTo("Project foo");

        var second = (Project) data.get(1);
        assertThat(second.id()).isEqualTo("102");
        assertThat(second.name()).isEqualTo("Project bar");

        var third = (Project) data.get(2);
        assertThat(third.id()).isEqualTo("103");
        assertThat(third.name()).isEqualTo("Project baz");
    }

    @Test
    public void findNoProjects() {
        var response = client.get().uri("/projects")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Response.Many.class)
            .returnResult()
            .getResponseBody();

        var data = response.data();
        assertThat(data).isNotNull().isEmpty();
    }
}
