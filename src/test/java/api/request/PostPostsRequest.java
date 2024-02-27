package api.request;

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
    private String title;

    @JsonProperty("body")
    private String body;

    @JsonProperty("userId")
    private Integer userId;


    public static PostPostsRequest generatePostPostsRequest(Integer userId, String title, String body) {
        return new PostPostsRequestBuilder()
                .userId(userId)
                .title(title)
                .body(body)
                .build();

    }

}
