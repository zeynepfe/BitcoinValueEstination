package tr.edu.gazi.bm.bitirme2018.flink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.BitCoinData;
import tr.edu.gazi.bm.bitirme2018.flink.service.interfaces.IBitCoinService;
import tr.edu.gazi.bm.bitirme2018.flink.util.BaseController;

/**
 * Created by ramazancesur on 5/31/18.
 */
@RestController
@RequestMapping("/bitcoin")
public class BitCoinController extends BaseController<BitCoinData>{

}
