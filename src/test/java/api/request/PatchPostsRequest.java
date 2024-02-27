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
public class PatchPostsRequest{


    @JsonProperty("title")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;


    public static PatchPostsRequest generatePatchPostsRequest(String title) {
        return new PatchPostsRequest.PatchPostsRequestBuilder()
                .title(title)
                .build();
    }

    public static PatchPostsRequest generateRequestWithoutTitle() {
        return new PatchPostsRequest.PatchPostsRequestBuilder()
                .build();
    }

}