package tr.edu.gazi.bm.bitirme2018.flink.util.interfaces;

import tr.edu.gazi.bm.bitirme2018.flink.util.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ramazancesur on 5/27/18.
 */
public interface   IGenericService<T extends BaseEntity, K extends Serializable> {

    public void saveOrUpdate(T entity);

    public List<T> getAll();

    public T get(K id) throws Exception;

    public T add(T entity);

    public boolean update(T entity);

    public boolean remove(T entity);

    public boolean remove(K id);
}