package api;

import api.response.GetPostsResponseItem;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.response.GetPostsResponse;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class GetPostsTest extends BaseApiTest {

    @Test
    @DisplayName("GET /posts.Код 200.Проверка корректности ответа")
    public void getPosts() {
        setSpecification(requestSpecification(), responseSpecification());
        List<GetPostsResponseItem> postList = given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "posts")
                .then().log().all()
                .extract().body().jsonPath().getList(".", GetPostsResponseItem.class);
        for (int i = 0; i < postList.size(); i++) {
            Assertions.assertEquals(postList.get(i).getId(), i + 1);
        }
    }

    @Test
    @DisplayName("GET /posts/1.Код 200.Проверка корректности ответа")
    public void getPostById() {
        int userId = 1;
        int id = 1;
        String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";

        setSpecification(requestSpecification(), responseSpecification());
        GetPostsResponseItem response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "posts/1")
                .then().log().all()
                .extract().body().as(GetPostsResponseItem.class);

        Assertions.assertEquals(userId, response.getUserId());
        Assertions.assertEquals(id, response.getId());
        Assertions.assertEquals(title, response.getTitle());
        Assertions.assertEquals(body, response.getBody());
    }
}
