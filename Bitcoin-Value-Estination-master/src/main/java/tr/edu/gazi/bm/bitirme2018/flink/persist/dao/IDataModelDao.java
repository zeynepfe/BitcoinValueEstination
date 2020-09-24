package tr.edu.gazi.bm.bitirme2018.flink.persist.dao;

import org.springframework.stereotype.Repository;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericDao;

/**
 * Created by ramazancesur on 5/27/18.
 */
@Repository("dataModelDao")
public interface IDataModelDao extends IGenericDao<DataModel,Long> {

}
