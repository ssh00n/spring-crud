package anonymous.springboard.repositories;

import anonymous.springboard.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Modifying
    @Query("UPDATE Post p SET p.deleted = true WHERE p.id = :id")
    void softDelete(@Param("id") Long id);

    @EntityGraph(attributePaths = {"comments"})
    Page<Post> findAllByDeletedFalse(Pageable pageable);
}
