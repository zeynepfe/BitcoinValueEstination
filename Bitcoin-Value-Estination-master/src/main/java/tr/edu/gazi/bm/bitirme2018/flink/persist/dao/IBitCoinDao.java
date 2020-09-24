package tr.edu.gazi.bm.bitirme2018.flink.persist.dao;

import org.springframework.stereotype.Repository;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.BitCoinData;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericDao;

/**
 * Created by ramazancesur on 5/27/18.
 */
@Repository("bitCoinDao")
public interface IBitCoinDao extends IGenericDao<BitCoinData, Long> {


}