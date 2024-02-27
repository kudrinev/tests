package api.api_test;

import api.request.PatchPostsRequest;
import api.response.PatchPostsResponse;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PatchPostsTest extends BaseApiTest {

    @Test
    @DisplayName("PATCH /posts/id.Код 200.Проверка корректности ответа")
    public void patchPosts() {
        setSpecification(requestSpecification(), response200Specification());
        Integer postId = 1;
        String title = RandomStringUtils.randomAlphabetic(10);

        PatchPostsRequest request = PatchPostsRequest.generatePatchPostsRequest(title);

        PatchPostsResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .patch("posts/" + postId)
                .then().log().all()
                .extract().as(PatchPostsResponse.class);

        Assertions.assertEquals(postId, response.getId());
        Assertions.assertEquals(title, response.getTitle());
    }
}
