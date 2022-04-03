package service.impel;

import connection.SessionFactorySingleton;
import entity.Comment;
import entity.Twit;
import entity.User;
import org.hibernate.SessionFactory;
import repository.impel.CommentRepositoryImpel;
import service.CommentService;
import utility.Utility;

import java.util.List;
import java.util.Scanner;

public class CommentServiceImpel extends GenericServiceImpel<Comment,Integer> implements CommentService {

    private final Scanner input = new Scanner(System.in);
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CommentRepositoryImpel commentRepositoryImpel = new CommentRepositoryImpel();
    private final Utility utility = new Utility();

    @Override
    public Comment add(Comment comment) {
        System.out.print("Enter your command:");
        String newComment = input.nextLine();
        comment.setComment(newComment);
        Comment comment1 = super.add(comment);
        System.out.println("Your comment added!");
        return comment1;
    }

    @Override
    public void showComment(Twit twit){
        List<Comment> commentList = findCommentByTwitId(twit);
        if(commentList.size() == 0)
            System.out.println("This Twit doesn't have any comment");
        else{
            for (Comment c:commentList
            ) {
                System.out.println("id:" + c.getId() +
                        "  Comment :" + c.getComment() +
                        "  User :" + c.getUser().getFullName());
                List<Comment> commentListReply = findWithParentId(c.getId());
                if(commentListReply.size() == 0)
                    System.out.println(" no reply!");
                else{
                    for (Comment co:commentListReply
                    ) {
                        System.out.print("Reply: ");
                        System.out.println("id: " + co.getId() +
                                "Reply: " + co.getComment() +
                                "User: " + co.getUser().getFullName());
                    }
                }
            }
        }
    }

    @Override
    public List<Comment> findCommentByTwitId(Twit twit) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return commentRepositoryImpel.showCommentByTwitId(twit.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public List<Comment> findWithParentId(Integer id){
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return commentRepositoryImpel.findByParentId(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public void replyComment(User user){
        System.out.print("Enter comment Id:");
        Integer command = utility.giveIntegerInput();
        Comment comment = findById(command);
        if(comment == null){
            System.out.println("You enter a wrong id");
            return;
        }
        System.out.print("Enter your Comment:");
        String newReply = input.nextLine();
        Comment newComment = Comment.builder().comment(newReply).user(user).parent(comment).build();
        super.add(newComment);
        System.out.println("Your reply added!");
    }
}
