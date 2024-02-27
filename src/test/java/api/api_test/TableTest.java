package api.api_test;

import api.response.getPostCommentsResponse.GetPostCommentsResponseItem;
import api.response.getPostsResponse.GetPostsResponseItem;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TableTest extends BaseApiTest {

    @Test
    @DisplayName("Вывод постов со связанными комментариями в таблицу")
    public void getPostToCommentRelation() {
        int postId;
        setSpecification(requestSpecification(), response200Specification());

        List<GetPostsResponseItem> postResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get("posts")
                .then()
                .extract().body().jsonPath().getList(".", GetPostsResponseItem.class);

        System.out.printf("%-80s %-100s", "Пост", "Комментарий");
        System.out.println();
        for (int i = 0; i < postResponse.size(); i++) {
            postId = i + 1;

            List<GetPostCommentsResponseItem> commentList = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("posts/" + postId + "/comments")
                    .then()
                    .extract().body().jsonPath().getList(".", GetPostCommentsResponseItem.class);
            System.out.printf("%-80s %-100s", postResponse.get(i).getTitle(), " ");
            System.out.println();

            for (GetPostCommentsResponseItem getPostCommentsResponseItem : commentList) {
                System.out.printf("%-80s %-100s", " ", getPostCommentsResponseItem.getBody().replace("\n", ""));
                System.out.println();
            }
        }

    }
}
