package service.baseService;

import java.util.List;

public interface BaseService<K,ID extends Integer> {
    K add (K k);
    K findById(ID id);
    List<K> findAll();
    void update(K t);
    void delete(K k);
}
