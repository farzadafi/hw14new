package service;

import entity.Comment;
import entity.Twit;
import entity.User;
import service.base.BaseService;

import java.util.List;

public interface CommentService extends BaseService<Comment,Integer> {
    void showComment(Twit twit);
    List<Comment> findCommentByTwitId(Twit twit);
    List<Comment> findWithParentId(Integer id);
    void replyComment(User user);
    Comment findById(Integer id);
}
