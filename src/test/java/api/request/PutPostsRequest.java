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
public class PutPostsRequest {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;

    @JsonProperty("userId")
    private Integer userId;


    public static PutPostsRequest generatePutPostsRequest(Integer postId,Integer userId, String title, String body) {
        return new PutPostsRequest.PutPostsRequestBuilder()
                .id(postId)
                .userId(userId)
                .title(title)
                .body(body)
                .build();

    }
}