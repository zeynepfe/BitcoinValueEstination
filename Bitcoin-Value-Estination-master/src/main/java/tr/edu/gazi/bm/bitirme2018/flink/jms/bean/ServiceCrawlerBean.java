package tr.edu.gazi.bm.bitirme2018.flink.jms.bean;


import org.apache.camel.Exchange;
import tr.edu.gazi.bm.bitirme2018.flink.parser.HtmlParser;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;

import java.util.List;

/**
 * Created by ramazancesur on 5/31/18.
 */
public class ServiceCrawlerBean{

    public void dataCrawlerBean(Exchange exchange){
        HtmlParser htmlParser = new HtmlParser();
        List<DataModel> lstDataModel = htmlParser.siteParserData("https://tr.investing.com/crypto/bitcoin/btc-usd");
        exchange.getOut().setBody(lstDataModel);
    }

}
