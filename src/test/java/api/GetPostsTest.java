package api;

import api.response.GetPostsResponseItem;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.response.GetPostsResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;


public class GetPostsTest extends BaseApiTest {

    @Test
    public void getPosts() {
        setSpecification(requestSpecification(), responseSpecification());
        List<GetPostsResponseItem> postList = given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "posts")
                .then().log().all()
                .extract().body().jsonPath().getList(".", GetPostsResponseItem.class);
        for (int i = 0; i < postList.size(); i++) {
            Assertions.assertEquals(postList.get(i).getId(), i+1);
        }


    }


}
