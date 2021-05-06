package dao;

import enums.ProjectType;
import models.Project;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImplementation implements ProjectDao {

    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int add(Project project) throws SQLException {
        String query = "INSERT INTO project (name, announcement, show_announcement, type) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, project.getName());
        ps.setString(2, project.getAnnouncement());
        ps.setBoolean(3, project.isShowAnnouncement());
        ps.setInt(4, project.getType().getValue());

        return ps.executeUpdate();
    }

    @Override
    public Project getProject(int id) throws SQLException {
        String query = "select * from project where id = ?;";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        boolean check = false;
        Project project = Project.builder().build();

        while (rs.next()) {
            check = true;

            project.setId(rs.getInt("id"));
            project.setName(rs.getString("name"));
            project.setAnnouncement(rs.getString("announcement"));
            project.setShowAnnouncement(rs.getBoolean("show_announcement"));
            project.setType(ProjectType.getEnumByValue(rs.getInt("type")));
        }

        return check ? project : null;
    }

    @Override
    public int delete(int id) throws SQLException {
        String query = "" +
                "DELETE FROM project " +
                "WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        int result = ps.executeUpdate();

        if (result > 0) {
            return result;
        } else {
            throw new SQLException();
        }
    }

    @Override
    public int update(Project project) throws SQLException {
        String query = "" +
                "UPDATE project " +
                "SET name = ?, announcement = ?, show_announcement = ?, type = ?" +
                "WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, project.getName());
        ps.setString(2, project.getAnnouncement());
        ps.setBoolean(3, project.isShowAnnouncement());
        ps.setInt(4, project.getType().getValue());
        ps.setInt(5, project.getId());

        return ps.executeUpdate();
    }

    @Override
    public List<Project> getProjects() throws SQLException {
        String query = "select * from project";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<Project> projectList = new ArrayList<>();

        while (rs.next()) {
            Project project = Project.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .announcement(rs.getString("announcement"))
                    .isShowAnnouncement(rs.getBoolean("show_announcement"))
                    .type(ProjectType.getEnumByValue(rs.getInt("type")))
                    .build();
            projectList.add(project);
        }

        return projectList;
    }

    @Override
    public void drop() throws SQLException {
        String query = "drop table if exists project cascade;";

        PreparedStatement ps = con.prepareStatement(query);
        ps.execute();
    }

    @Override
    public void create() throws SQLException {
        String query =
                "create table project\n" +
                "(\n" +
                "    id                serial not null\n" +
                "        constraint project_pk\n" +
                "            primary key,\n" +
                "    name              varchar,\n" +
                "    announcement      varchar,\n" +
                "    show_announcement boolean,\n" +
                "    type              integer\n" +
                ");\n" +
                "\n" +
                "alter table project\n" +
                "    owner to postgres;\n" +
                "\n" +
                "create unique index project_id_uindex\n" +
                "    on project (id);\n";

        PreparedStatement ps = con.prepareStatement(query);
        ps.execute();
    }
}
