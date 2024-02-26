package anonymous.springboard.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment extends BaseEntity{

    public Comment(){

    }

    public Comment(String content){
        this.content = content;
    }


    @Lob // Large Object(in this case, Character Large Object>
    private String content;

    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
