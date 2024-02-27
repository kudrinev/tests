package api.response.getPostCommentsResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostCommentsResponseItem{
    private String name;
    private Integer postId;
    private Integer id;
    private String body;
    private String email;
}
