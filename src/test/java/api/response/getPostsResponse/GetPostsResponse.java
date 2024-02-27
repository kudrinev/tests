package api.response.getPostsResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostsResponse{

    @JsonProperty("GetPostsResponse")
    private List<GetPostsResponseItem> getPostsResponse;
}