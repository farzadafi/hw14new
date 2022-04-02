package service.impel;

import connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.impel.GenericRepositoryImpel;
import service.baseService.BaseService;

import java.util.List;

public class GenericServiceImpel<K,ID extends Integer> implements BaseService<K,ID> {

    private final GenericRepositoryImpel<K,Integer> genericRepositoryImpel = new GenericRepositoryImpel<>();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    @Override
    public K add(K k) {
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepositoryImpel.add(k);
                transaction.commit();
                return k;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public K findById(ID id) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return genericRepositoryImpel.findById(id);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public List<K> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return genericRepositoryImpel.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public void update(K k) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepositoryImpel.update(k);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void delete(K k) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepositoryImpel.delete(k);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }
}
