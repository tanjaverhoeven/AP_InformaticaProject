package edu.ap.projectteambisfits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ap.projectteambisfits.comment.Comment;
import edu.ap.projectteambisfits.comment.CommentRepository;
import edu.ap.projectteambisfits.comment.CommentServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommentServiceImplTest {

    @Mock
    CommentRepository repo;
    @InjectMocks
    CommentServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    public void shouldGetAllCommentsForDefect() throws Exception {
        when(repo.findAllByDefectid("defectid123")).thenReturn(
                Stream.of(new Comment("defectid123", "dit is een comment", "Bill Burr")).collect(Collectors.toList()));
        assertEquals(1, service.findAllByDefectid("defectid123").size());
    }

    @Test
    public void shouldCreateComment() throws Exception {
        Comment com = new Comment("defectid123", "dit is een comment", "Bill Burr");
        when(repo.save(com)).thenReturn(com);
        assertEquals(com, service.saveComment(com));
    }

}
