package anonymous.springboard.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;

    public CommentDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}