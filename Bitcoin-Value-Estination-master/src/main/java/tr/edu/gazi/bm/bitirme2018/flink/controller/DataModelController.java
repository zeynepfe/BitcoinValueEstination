package tr.edu.gazi.bm.bitirme2018.flink.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;
import tr.edu.gazi.bm.bitirme2018.flink.util.BaseController;

/**
 * Created by ramazancesur on 6/1/18.
 */
@RestController
@RequestMapping("/dataModel")
public class DataModelController extends BaseController<DataModel>{

}
