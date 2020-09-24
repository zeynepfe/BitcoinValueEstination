package tr.edu.gazi.bm.bitirme2018.flink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tr.edu.gazi.bm.bitirme2018.flink.persist.dao.IDataModelDao;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;
import tr.edu.gazi.bm.bitirme2018.flink.service.interfaces.IDataModelService;
import tr.edu.gazi.bm.bitirme2018.flink.util.GenericService;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericDao;

/**
 * Created by ramazancesur on 5/27/18.
 */
@Service
public class DataModelService extends GenericService<DataModel,Long> implements IDataModelService {
    private IDataModelDao dataModelDao;

    @Autowired
    public DataModelService(@Qualifier("dataModelDao") IGenericDao<DataModel, Long> genericDao) {
        super(genericDao);
        this.dataModelDao= (IDataModelDao) genericDao;
    }


}
