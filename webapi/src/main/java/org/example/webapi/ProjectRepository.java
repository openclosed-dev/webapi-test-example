package org.example.webapi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    private final JdbcTemplate template;

    public ProjectRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public List<Project> find() {
        return template.query(
            """
            SELECT id, name FROM project ORDER BY id
            """,
            ProjectRepository::map
        );
    }


    public Optional<Project> get(int id) {
        try (var s = template.queryForStream(
            """
            SELECT id, name FROM project WHERE id = ?
            """,
            ProjectRepository::map,
            id)) {
            return s.findFirst();
        }
    }

    public Project create(Project project) {
        var newProject = template.queryForObject(
            """
            INSERT INTO project(name)
            VALUES(?)
            RETURNING id, name
            """,
            ProjectRepository::map,
            project.name()
        );
        return newProject;
    }

    private static Project map(ResultSet rs, int rowNum) throws SQLException {
        return new Project(
            rs.getString("id"),
            rs.getString("name")
        );
    }
}
