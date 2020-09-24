package tr.edu.gazi.bm.bitirme2018.flink.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.gazi.bm.bitirme2018.flink.jms.AmQRouter;

/**
 * Created by ramazancesur on 5/27/18.
 */
@RestController
@RequestMapping("/testController")
public class AmQController {
    private static Logger LOGGER = LoggerFactory.getLogger(AmQController.class);

    @Autowired
    private AmQRouter router;

    @GetMapping("/createAmQSendData")
    public void createSendData() {
        try {
            router.configure();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
