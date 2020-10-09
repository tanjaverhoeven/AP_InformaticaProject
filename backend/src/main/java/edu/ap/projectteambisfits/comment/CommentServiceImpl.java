package edu.ap.projectteambisfits.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repo;

    @Override
    public List<Comment> findAllByDefectid(String defectid) {
        return repo.findAllByDefectid(defectid);
    }

    @Override
    public void deleteComment(String id) {
        repo.deleteById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return repo.save(comment);
    }

}