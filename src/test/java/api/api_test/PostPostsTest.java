package api.api_test;

import api.request.PostPostsRequest;
import api.response.PostPostsResponse;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PostPostsTest extends BaseApiTest {
    @Test
    @DisplayName("POST /posts.Код 200.Проверка корректности ответа")
    public void postPosts() {
        setSpecification(requestSpecification(), response201Specification());

        Integer userId = Integer.parseInt(RandomStringUtils.randomNumeric(1));
        String title = RandomStringUtils.randomAlphabetic(10);
        String body = RandomStringUtils.randomAlphabetic(20);

        PostPostsRequest request = PostPostsRequest.generatePostPostsRequest(userId,title,body);

       PostPostsResponse response =  given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .post("posts")
                .then().log().all()
               .extract().as(PostPostsResponse.class);

        Assertions.assertEquals(userId,response.getUserId());
        Assertions.assertEquals(title,response.getTitle());
        Assertions.assertEquals(body,response.getBody());
        Assertions.assertNotNull(response.getId());
        }

    @Test
    @DisplayName("POST /posts.Отправка запроса без userId")
    public void postWithoutId() {
        setSpecification(requestSpecification(), response201Specification());

        String title = RandomStringUtils.randomAlphabetic(10);
        String body = RandomStringUtils.randomAlphabetic(20);

        PostPostsRequest request = PostPostsRequest.generateRequestWithoutId(title,body);

        PostPostsResponse response =  given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .post("posts")
                .then().log().all()
                .extract().as(PostPostsResponse.class);

        Assertions.assertNull(response.getUserId());
        Assertions.assertEquals(title,response.getTitle());
        Assertions.assertEquals(body,response.getBody());
        Assertions.assertNotNull(response.getId());
    }
    @Test
    @DisplayName("POST /posts.Отправка запроса без title")
    public void postWithoutTitle() {
        setSpecification(requestSpecification(), response201Specification());

        Integer userId = Integer.parseInt(RandomStringUtils.randomNumeric(1));
        String body = RandomStringUtils.randomAlphabetic(20);

        PostPostsRequest request = PostPostsRequest.generateRequestWithoutTitle(userId,body);

        PostPostsResponse response =  given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .post("posts")
                .then().log().all()
                .extract().as(PostPostsResponse.class);

        Assertions.assertEquals(userId,response.getUserId());
        Assertions.assertNull(response.getTitle());
        Assertions.assertEquals(body,response.getBody());
        Assertions.assertNotNull(response.getId());
    }

    @Test
    @DisplayName("POST /posts.Отправка запроса без body")
    public void postWithoutBody() {
        setSpecification(requestSpecification(), response201Specification());

        Integer userId = Integer.parseInt(RandomStringUtils.randomNumeric(1));
        String title = RandomStringUtils.randomAlphabetic(10);

        PostPostsRequest request = PostPostsRequest.generateRequestWithoutBody(userId,title);

        PostPostsResponse response =  given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .post("posts")
                .then().log().all()
                .extract().as(PostPostsResponse.class);

        Assertions.assertEquals(userId,response.getUserId());
        Assertions.assertNull(response.getBody());
        Assertions.assertEquals(title,response.getTitle());
        Assertions.assertNotNull(response.getId());
    }
    }



