package tr.edu.gazi.bm.bitirme2018.flink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tr.edu.gazi.bm.bitirme2018.flink.persist.dao.IBitCoinDao;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.BitCoinData;
import tr.edu.gazi.bm.bitirme2018.flink.service.interfaces.IBitCoinService;
import tr.edu.gazi.bm.bitirme2018.flink.util.GenericService;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericDao;

/**
 * Created by ramazancesur on 5/27/18.
 */
@Service
public class BitCoinService extends GenericService<BitCoinData,Long> implements IBitCoinService{
    private IBitCoinDao bitCoinDao;

    @Autowired
    public BitCoinService(@Qualifier("bitCoinDao") IGenericDao<BitCoinData, Long> genericDao) {
        super(genericDao);
        this.bitCoinDao= (IBitCoinDao) genericDao;
    }
}
