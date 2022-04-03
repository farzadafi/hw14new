package repository;

import entity.Comment;
import repository.base.GenericRepository;

import java.util.List;

public interface CommentRepository extends GenericRepository<Comment,Integer> {
    List<Comment> showCommentByTwitId(Integer id);
    List<Comment> findByParentId(Integer id);
    List<Comment> findByUserId(Integer id);
    Comment findById(Integer id);
}
