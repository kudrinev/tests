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
public class PatchPostsRequest{

    @JsonProperty("title")
    private String title;


    public static PatchPostsRequest generatePatchPostsRequest(String title) {
        return new PatchPostsRequest.PatchPostsRequestBuilder()
                .title(title)
                .build();
    }
}