package org.example.webapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("projects")
    public ResponseEntity<Response> findProject() {
        return ResponseEntity.ok(Response.data(service.find()));
    }

    @GetMapping("projects/{id}")
    public ResponseEntity<Response> getProject(
        @PathVariable("id") String id) {
        return service.get(Integer.valueOf(id))
            .map(p -> ResponseEntity.ok(Response.data(p)))
            .orElseGet(() -> {
                var error = Error.newError(
                    "101", HttpStatus.NOT_FOUND,
                    String.format("Project not found. id: %s", id));
                return ResponseEntity
                    .status(error.status())
                    .body(Response.error(error));
            });
    }
}
