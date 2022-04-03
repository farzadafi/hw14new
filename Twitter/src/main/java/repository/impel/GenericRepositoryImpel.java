package repository.impel;

import connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.base.GenericRepository;

import java.util.List;

public class GenericRepositoryImpel<K,ID> implements GenericRepository<K,ID> {
    private Class<K> clazz;
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public GenericRepositoryImpel(Class<K> clazz) {
        this.clazz = clazz;
    }

    public GenericRepositoryImpel() {

    }


    @Override
    public void add(K k) {
        var session = sessionFactory.getCurrentSession();
        session.save(k);
    }

    @Override
    public void update(K k) {
        var session = sessionFactory.getCurrentSession();
        session.update(k);
    }

    @Override
    public void delete(K k) {
        var session = sessionFactory.getCurrentSession();
        session.delete(k);
    }

    @Override
    public K findById(ID id) {
        return sessionFactory.getCurrentSession().find(clazz,id);
    }

    @Override
    public List<K> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + clazz.getSimpleName(), clazz)
                                                              .getResultList();
    }
}
