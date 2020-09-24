package tr.edu.gazi.bm.bitirme2018.flink.util;

import org.springframework.beans.factory.annotation.Autowired;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericDao;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by ramazancesur on 5/27/18.
 */


public abstract class GenericService<T extends BaseEntity, K extends Serializable> implements IGenericService<T, K> {
    @Autowired
    IGenericDao<T, K> genericDao;

    public GenericService(IGenericDao<T, K> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void saveOrUpdate(T entity) {
        genericDao.save(entity);
    }

    @Override
    public List<T> getAll() {
        return genericDao.findAll();
    }

    @Override
    public T get(K id) {
        Optional<T> data= genericDao.findById(id);
        if (data.isPresent()){
            T myData = data.get();
            return myData.getEntityState() == EnumUtil.EntityState.ACTIVE ? myData : null;
        }
        return null;
    }

    @Override
    public T add(T entity) {
        try {
            genericDao.save(entity);
            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(T entity) {
        try {
            genericDao.saveAndFlush(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(T entity) {
        try {
            entity.setEntityState(EnumUtil.EntityState.PASSIVE);
            this.update(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(K id) {
        try {
            T entity = genericDao.getOne(id);
            entity.setEntityState(EnumUtil.EntityState.PASSIVE);
            this.update(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}