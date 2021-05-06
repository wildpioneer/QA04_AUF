package dao;

import models.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDao {

    int add(Project project) throws SQLException;

    Project getProject(int id) throws SQLException;

    int delete(int id) throws SQLException;

    int update(Project project) throws SQLException;

    List<Project> getProjects() throws SQLException;

    void drop() throws SQLException;

    void create() throws SQLException;
}
