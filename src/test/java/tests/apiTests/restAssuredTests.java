package tests.apiTests;

import com.google.gson.Gson;
import enums.ProjectType;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class restAssuredTests {
    @Test
    public void simpleRestAssuredTest1() {
        RestAssured.baseURI = "https://reqres.in";
        String endpoint = "";

        // Setup Request Object
        RequestSpecification request = given();

        // Setup Response Object
        Response response = request.request(Method.GET, endpoint);

        // Get Response Status Code
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test
    public void simpleRestAssuredTest1_2() {
        RestAssured.baseURI = "https://reqres.in";
        String endpoint = "/api/users/2";

        given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .body();
    }

    @Test
    public void simpleRestAssuredTest3_1() {
        RestAssured.baseURI = "https://reqres.in";
        String endpoint = "/api/users";
        String bodyJson = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        int projectID = given()
                .body(bodyJson)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().body()
                .extract().jsonPath().getInt("id");

        System.out.println(projectID);
    }

    @Test
    public void simpleRestAssuredTest2() {
        String expectedValue = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\"," +
                "\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},\"support\":{\"url\":" +
                "\"https://reqres.in/#support-heading\",\"text\":\"To keep ReqRes free, contributions towards server " +
                "costs are appreciated!\"}}";

        RestAssured.baseURI = "https://reqres.in";
        String endpoint = "/api/users/2";

        // Setup Request Object
        RequestSpecification request = given();

        // Setup Response Object
        Response response = request.request(Method.GET, endpoint);

        // Get Response Status Code
        int statusCode = response.getStatusCode();

        // Get Response Body
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(responseBody, expectedValue);
    }

    @Test
    public void simpleRestAssuredTest2_2() {
        RestAssured.baseURI = "https://reqres.in";
        String endpoint = "/api/users/2";

        given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"));
    }

    @Test
    public void serializationTest() {
        Gson gson = new Gson();

        Project project = Project.builder()
                .name("Name")
                .type(ProjectType.MULTIPLE)
                .isShowAnnouncement(true)
                .announcement("Announcement description")
                .build();

        String result = gson.toJson(project);

        System.out.println(result);
    }

    @Test
    public void deserializationTest() {
        Project expectedProject = Project.builder()
                .name("Name")
                .type(ProjectType.MULTIPLE)
                .isShowAnnouncement(true)
                .announcement("Announcement description")
                .build();

        Gson gson = new Gson();
        String jsonString = "{\"name\":\"Name\",\"announcement\":\"Announcement description\",\"isShowAnnouncement\":true,\"type\":\"MULTIPLE\"}";

        Project actualProject = gson.fromJson(jsonString, Project.class);

        System.out.println(actualProject.toString());
        Assert.assertTrue(expectedProject.equals(actualProject));
    }
}
