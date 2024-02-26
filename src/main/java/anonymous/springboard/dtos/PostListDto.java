package anonymous.springboard.dtos;

import lombok.Getter;

@Getter
public class PostListDto {
    private Long id;
    private String title;

    public PostListDto(Long id, String title){
        this.id = id;
        this.title = title;
    }

}
