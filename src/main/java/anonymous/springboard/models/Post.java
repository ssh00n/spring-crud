package anonymous.springboard.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts", indexes = {
        @Index(name = "idx_posts_deleted", columnList = "deleted"),
        @Index(name = "idx_posts_created_at", columnList = "createdAt")
})
@Getter
@Setter
public class Post extends BaseEntity {

    public Post(){

    }

    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    private String title;

    @Lob
    private String content;

    private boolean deleted = false;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
