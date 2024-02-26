package anonymous.springboard.services;

import anonymous.springboard.dtos.CommentDto;
import anonymous.springboard.dtos.PostDetailDto;
import anonymous.springboard.dtos.PostListDto;
import anonymous.springboard.dtos.PostUpdateDto;
import anonymous.springboard.models.Post;
import anonymous.springboard.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return this.postRepository.save(post);
    }

    public Page<PostListDto> getAllPosts(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostListDto> postDtos = posts.stream()
                .map(post -> new PostListDto(post.getId(), post.getTitle()))
                .collect(Collectors.toList());
        return new PageImpl<>(postDtos, pageable, posts.getTotalElements());
    }

    public PostDetailDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        // 삭제되지 않은 댓글만 필터링
        List<CommentDto> commentDtos = post.getComments().stream()
                .filter(comment -> !comment.isDeleted())
                .map(comment -> new CommentDto(comment.getId(), comment.getContent()))
                .collect(Collectors.toList());
        return new PostDetailDto(post.getId(), post.getTitle(), post.getContent(), commentDtos);
    }


    @Transactional
    public Post updatePost(Long id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));

        if (postUpdateDto.getTitle() != null) {
            post.setTitle(postUpdateDto.getTitle());
        }

        if (postUpdateDto.getContent() != null) {
            post.setContent(postUpdateDto.getContent());
        }

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        post.getComments().forEach(comment -> comment.setDeleted(true)); // 관련 댓글 soft delete 처리
        post.setDeleted(true); // 게시글 soft delete 처리
        postRepository.save(post); // 변경 사항 저장
    }
}
