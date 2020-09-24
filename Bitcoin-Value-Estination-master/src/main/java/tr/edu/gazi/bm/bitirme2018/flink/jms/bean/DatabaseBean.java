package tr.edu.gazi.bm.bitirme2018.flink.jms.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;
import tr.edu.gazi.bm.bitirme2018.flink.service.DataModelService;
import tr.edu.gazi.bm.bitirme2018.flink.service.interfaces.IDataModelService;
import tr.edu.gazi.bm.bitirme2018.flink.util.ApplicationContextHolder;

/**
 * Created by ramazancesur on 5/31/18.
 */
public class DatabaseBean implements Processor {
    private static Logger LOGGER = LoggerFactory.getLogger(DatabaseBean.class);
    private IDataModelService dataModelService = ApplicationContextHolder.getContext().getBean(DataModelService.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Object object = exchange.getIn().getBody();
        DataModel model = dataModelService.add((DataModel) object);
        exchange.getOut().setBody(model);
        LOGGER.info(model.toString());
    }
}