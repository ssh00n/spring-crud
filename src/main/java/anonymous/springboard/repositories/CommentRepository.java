package anonymous.springboard.repositories;

import anonymous.springboard.models.Comment;
import anonymous.springboard.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

