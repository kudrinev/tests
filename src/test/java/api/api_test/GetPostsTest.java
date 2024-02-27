package api.api_test;

import api.response.getPostCommentsResponse.GetPostCommentsResponseItem;
import api.response.getPostsResponse.GetPostsResponseItem;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetPostsTest extends BaseApiTest {

    @Test
    @DisplayName("GET /posts.Код 200.Проверка корректности ответа")
    public void getPosts() {
        setSpecification(requestSpecification(), response200Specification());
        List<GetPostsResponseItem> postList = given()
                .contentType(ContentType.JSON)
                .when()
                .get("posts")
                .then().log().all()
                .extract().body().jsonPath().getList(".", GetPostsResponseItem.class);
        for (int i = 0; i < postList.size(); i++) {
            Assertions.assertEquals(postList.get(i).getId(), i + 1);
        }
    }

    @Test
    @DisplayName("GET /posts/id.Код 200.Проверка тела ответа")
    public void getPostById() {
        int userId = 1;
        int postId = 1;
        String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";

        setSpecification(requestSpecification(), response200Specification());
        GetPostsResponseItem response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("posts/" + postId)
                .then().log().all()
                .extract().body().as(GetPostsResponseItem.class);

        Assertions.assertEquals(userId, response.getUserId());
        Assertions.assertEquals(postId, response.getId());
        Assertions.assertEquals(title, response.getTitle());
        Assertions.assertEquals(body, response.getBody());
    }


    @Test
    @DisplayName("GET /posts/id.Некорректный id поста в запросе")
    public void getPostByIncorrectId() {

        int postId = 111;

        setSpecification(requestSpecification(), response404Specification());
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("posts/" + postId)
                .then().log().all();

    }

    @Test
    @DisplayName("GET /posts/id/comments.Код 200.Проверка корректности ответа")
    public void getCommentByPostId() {
        int postId = 1;


        setSpecification(requestSpecification(), response200Specification());
        List<GetPostCommentsResponseItem> commentList = given()
                .contentType(ContentType.JSON)
                .when()
                .get("posts/" + postId + "/comments")
                .then().log().all()
                .extract().body().jsonPath().getList(".", GetPostCommentsResponseItem.class);
        for (int i = 0; i < commentList.size(); i++) {
            Assertions.assertEquals(commentList.get(i).getPostId(), (postId));
            Assertions.assertEquals(commentList.get(i).getId(), i + 1);
        }
    }
        @Test
        @DisplayName("GET /posts/id/comments.Некорректный id поста в запросе")
        public void getCommentByIncorrectPostId() {
            int postId = 111;

            setSpecification(requestSpecification(), response200Specification());
           given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("posts/" + postId + "/comments")
                    .then().log().all();
    }
}
