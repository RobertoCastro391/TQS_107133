
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;

import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class AppTest {

    String url = "https://jsonplaceholder.typicode.com/";

    @Test
    public void testStatusTodosCode() {
        given().get(url + "todos").then().statusCode(200);
    }

    @Test
    public void testTodos4() {
        given().get(url + "todos/4").then().body("title", Matchers.equalTo("et porro tempora"));
    }

    @Test
    public void testTodos198and199() {
        given().get(url + "todos").then().body("id", Matchers.hasItems(198, 199));
    }

    @Test
    public void test2SecsTime() {
        given().get(url + "todos").then().time(Matchers.lessThan(2000L));
    }
}