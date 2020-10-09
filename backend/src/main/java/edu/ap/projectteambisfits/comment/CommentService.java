package edu.ap.projectteambisfits.comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByDefectid(String defectid);

    void deleteComment(String id);

    Comment saveComment(Comment comment);
}