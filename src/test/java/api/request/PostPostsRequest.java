package api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPostsRequest {

    @JsonProperty("title")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @JsonProperty("body")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String body;

    @JsonProperty("userId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;


    public static PostPostsRequest generatePostPostsRequest(Integer userId, String title, String body) {
        return new PostPostsRequestBuilder()
                .userId(userId)
                .title(title)
                .body(body)
                .build();

    }

    public static PostPostsRequest generateRequestWithoutId( String title, String body) {
        return new PostPostsRequestBuilder()
                .title(title)
                .body(body)
                .build();

    }
    public static PostPostsRequest generateRequestWithoutTitle(Integer userId, String body) {
        return new PostPostsRequestBuilder()
                .userId(userId)
                .body(body)
                .build();

    }
    public static PostPostsRequest generateRequestWithoutBody(Integer userId, String title) {
        return new PostPostsRequestBuilder()
                .userId(userId)
                .title(title)
                .build();

    }
}
