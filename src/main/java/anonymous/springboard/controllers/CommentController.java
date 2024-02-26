package anonymous.springboard.controllers;


import anonymous.springboard.models.Comment;
import anonymous.springboard.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @PatchMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteComment(@PathVariable Long id) {
        commentService.softDeleteComment(id);
        return ResponseEntity.ok().build();
    }
}