package tr.edu.gazi.bm.bitirme2018.flink.util.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.gazi.bm.bitirme2018.flink.util.BaseEntity;

import java.io.Serializable;

/**
 * Created by ramazancesur on 5/27/18.
 */
@NoRepositoryBean
@Transactional
public interface IGenericDao<T extends BaseEntity, K extends Serializable>
                 extends JpaRepository<T,K> {

}
