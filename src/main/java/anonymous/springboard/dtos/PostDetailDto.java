package anonymous.springboard.dtos;
import java.util.List;
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private List<CommentDto> comments;

    public PostDetailDto(Long id, String title, String content, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    // Getter와 Setter 생략
}