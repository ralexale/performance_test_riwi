package repostiories;

import java.util.List;

public interface CrudRepository {
    Object save(Object object);

    void update(Object object);

    void delete(int id);

    Object find(String value);

    Object findByType(String value, String filterType);

    List<Object> findAll();
}
