package tests.dbTests;

import dao.ProjectDaoImplementation;
import enums.ProjectType;
import lombok.SneakyThrows;
import models.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.DatabaseConnection;
import services.JdbcService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlTest {
    Logger logger = LogManager.getLogger();
    int prNum = 1;

    //@Test
    public void connectionTest() throws SQLException {
        JdbcService jdbcService = new JdbcService();
        ResultSet resultSet = jdbcService.executeQuery("select * from customers;");

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString(3);
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");

            logger.info("Result: id = " + id + ", firstname = " + firstname + ", lastname = " + lastname +
                    ", email = " + email + ", age = " + age);
        }

        jdbcService.closeConnection();
    }

    @SneakyThrows
    @BeforeTest
    public void setUp() {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();

        prDao.drop();
        prDao.create();
    }

    @SneakyThrows
    @Test
    public void test1() {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();
        Project project = Project.builder()
                .name("PR05")
                .announcement("PR05 ANON!!!")
                .isShowAnnouncement(true)
                .type(ProjectType.MULTIPLE)
                .build();

        System.out.println(prDao.add(project));
        System.out.println(prDao.add(project));
    }

    @SneakyThrows
    @Test
    public void test2() {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();
        Project project = Project.builder()
                .id(prNum)
                .name("PR05 UPD")
                .announcement("PR05 ANON UPD")
                .isShowAnnouncement(false)
                .type(ProjectType.SINGLE_FOR_ALL_CASES)
                .build();

        System.out.println(prDao.update(project));
    }

    @SneakyThrows
    @Test
    public void test3() {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();

        Project project = prDao.getProject(prNum);
        System.out.println(project.toString());
    }

    @SneakyThrows
    @Test
    public void test4() {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();

        for (Project pr : prDao.getProjects()) {
            System.out.println(pr.toString());
        }
    }

    @SneakyThrows
    @Test
    public void test5() {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();

        System.out.println(prDao.delete(prNum));
    }

    @AfterTest
    public void tearDown() {
        DatabaseConnection.closeConnection();
        System.out.println("Connection has been closed.");
    }
}
