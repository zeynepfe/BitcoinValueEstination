package tr.edu.gazi.bm.bitirme2018.flink.util.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tr.edu.gazi.bm.bitirme2018.flink.util.BaseEntity;

import java.util.List;

/**
 * Created by ramazancesur on 5/31/18.
 */
public interface IBaseController <T extends BaseEntity> {
    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getDataById(@PathVariable("id") Long id);

    ResponseEntity<Boolean> addData(@RequestBody T data);

    ResponseEntity<Boolean> updateData(@RequestBody T data);

    ResponseEntity<Boolean> deleteData(@RequestBody T data);
}
