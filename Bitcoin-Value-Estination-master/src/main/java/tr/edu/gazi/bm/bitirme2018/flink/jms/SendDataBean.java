package tr.edu.gazi.bm.bitirme2018.flink.jms;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.edu.gazi.bm.bitirme2018.flink.helper.Helper;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.BitCoinData;

/**
 * Created by ramazancesur on 5/27/18.
 */
public class SendDataBean implements Processor {
    private Logger logger= LoggerFactory.getLogger(SendDataBean.class);
    private Helper helper= new Helper();

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("gelen data "+ exchange);
        BitCoinData bitCoinData= helper.createDummyData(BitCoinData.class);
        exchange.getOut().setBody(bitCoinData.toString());
        logger.info(bitCoinData.toString());
    }
}
