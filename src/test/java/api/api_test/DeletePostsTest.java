package api.api_test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePostsTest extends BaseApiTest {

    @Test
    @DisplayName("DELETE /posts/id.Код 200.Проверка тела ответа")
    public void getPostById() {

        int postId = 1;

        setSpecification(requestSpecification(), response200Specification());
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("posts/" + postId)
                .then().log().all();
    }


}
