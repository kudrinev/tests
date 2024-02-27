package api.api_test;

import api.request.PutPostsRequest;
import api.response.PutPostsResponse;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PutPostsTest extends BaseApiTest {

    @Test
    @DisplayName("PUT /posts/id.Код 200.Проверка корректности ответа")
    public void putPosts() {
        setSpecification(requestSpecification(), response200Specification());

        Integer postId = 1;
        Integer userId = Integer.parseInt(RandomStringUtils.randomNumeric(1));
        String title = RandomStringUtils.randomAlphabetic(10);
        String body = RandomStringUtils.randomAlphabetic(20);

        PutPostsRequest request = PutPostsRequest.generatePutPostsRequest(postId,userId, title, body);

        PutPostsResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .put("posts/" + postId)
                .then().log().all()
                .extract().as(PutPostsResponse.class);

        Assertions.assertEquals(userId, response.getUserId());
        Assertions.assertEquals(title, response.getTitle());
        Assertions.assertEquals(body, response.getBody());
        Assertions.assertEquals(postId,response.getId());
    }
}
