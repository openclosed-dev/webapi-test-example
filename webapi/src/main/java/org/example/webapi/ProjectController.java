package org.example.webapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("projects")
    public ResponseEntity<Document> findProject() {
        return ResponseEntity.ok(Document.data(service.find()));
    }

    @GetMapping("projects/{id}")
    public ResponseEntity<Document> getProject(
        @PathVariable("id") String id) {
        return service.get(Integer.valueOf(id))
            .map(p -> ResponseEntity.ok(Document.data(p)))
            .orElseGet(() -> {
                var error = Error.newError(
                    "101", HttpStatus.NOT_FOUND,
                    String.format("Project not found. id: %s", id));
                return ResponseEntity
                    .status(error.status())
                    .body(Document.errors(error));
            });
    }

    @PostMapping("projects")
    public ResponseEntity<Document> createProject(
        @RequestBody Document.One doc,
        UriComponentsBuilder uriBuilder
    ) {
        var data = doc.data();
        if (data instanceof Project p) {
            var created = service.create(p);
            var location = uriBuilder.path("/projects/{id}")
                .buildAndExpand(created.id())
                .toUri();
            return ResponseEntity
                .created(location)
                .body(Document.data(created));
        } else {
            var error = Error.newError(
                "102", HttpStatus.BAD_REQUEST,
                String.format("Invalid resource type: %s", data.type()));
            return ResponseEntity
                .status(error.status())
                .body(Document.errors(error));
        }
    }
}
