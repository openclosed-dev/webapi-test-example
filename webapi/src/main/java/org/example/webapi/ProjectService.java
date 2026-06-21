package org.example.webapi;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo) {
        this.repo = repo;
    }

    public List<Project> find() {
        return repo.find();
    }

    public Optional<Project> get(int id) {
        return repo.get(id);
    }
}
