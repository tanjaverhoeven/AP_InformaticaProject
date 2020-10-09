package edu.ap.projectteambisfits.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping(path = "/commentsfordefect/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getCommentsForDefect(@PathVariable String id) {
        return service.findAllByDefectid(id);
    }

    @DeleteMapping(path = "/comments/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable String id) {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/comment")
    public ResponseEntity<?> newComment(@RequestBody Comment comment) {
        service.saveComment(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}