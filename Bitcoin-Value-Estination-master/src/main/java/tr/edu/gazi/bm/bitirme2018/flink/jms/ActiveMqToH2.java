package tr.edu.gazi.bm.bitirme2018.flink.jms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import tr.edu.gazi.bm.bitirme2018.flink.jms.bean.DatabaseBean;

/**
 * Created by ramazancesur on 5/31/18.
 */
@Component
public class ActiveMqToH2 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq://dataQueue")
                .bean(DatabaseBean.class, "process")
                .to("log:level=INFO&showBody=true")
                .log("işlem başarlı...");
    }
}
