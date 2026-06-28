package org.example.webapi.test.project;

import org.example.webapi.test.Project;
import org.example.webapi.test.Document;
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
public class CreateProjectTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void createNewProject() {
        var project = new Project("Project X");
        var doc = client.post().uri("/projects")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(Document.data(project))
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Document.One.class)
            .returnResult()
            .getResponseBody();

        var data = doc.data();
        assertThat(data).isNotNull().isInstanceOf(Project.class);
        var created = (Project) data;
        assertThat(created.type()).isEqualTo(Project.TYPE);
        assertThat(created.id()).isEqualTo("1");
        assertThat(created.name()).isEqualTo(project.name());
    }
}
