package service.impel;

import connection.SessionFactorySingleton;
import entity.User;
import org.hibernate.SessionFactory;
import repository.impel.FollowerRepositoryImpel;

public class FollowerServiceImpel {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final FollowerRepositoryImpel followerRepositoryImpel = new FollowerRepositoryImpel();

    public Integer unfollow(User user1, User user2){
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return followerRepositoryImpel.unFollow(user1.getId(),user2.getId());
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
