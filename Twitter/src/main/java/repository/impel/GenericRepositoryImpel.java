package repository.impel;

import repository.base.GenericRepository;

import java.util.List;

public class GenericRepositoryImpel<K,ID> implements GenericRepository<K,ID> {
    @Override
    public void add(K k) {

    }

    @Override
    public void update(K k) {

    }

    @Override
    public void delete(K k) {

    }

    @Override
    public K findById(ID id) {
        return null;
    }

    @Override
    public List<K> findAll() {
        return null;
    }
}
