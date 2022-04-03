package repository.impel;

import connection.SessionFactorySingleton;
import entity.Comment;
import org.hibernate.SessionFactory;
import repository.CommentRepository;

import java.util.List;

public class CommentRepositoryImpel extends GenericRepositoryImpel<Comment,Integer> implements CommentRepository {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public List<Comment> showCommentByTwitId(Integer id){
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM Comment as c WHERE c.twit.id = :twit_id", Comment.class);
        query.setParameter("twit_id",id);
        return query.list();
    }

    @Override
    public List<Comment> findByParentId(Integer id){
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM Comment as c WHERE c.parent.id = :parent_id", Comment.class);
        query.setParameter("parent_id",id);
        return query.list();
    }

}
