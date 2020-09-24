package tr.edu.gazi.bm.bitirme2018.flink.jms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import tr.edu.gazi.bm.bitirme2018.flink.jms.bean.ServiceCrawlerBean;

@Component
public class AmQRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //Producer route
        System.out.println("işlem başladı...");

        // her 40 saniyede bir istek atıyor
        from("timer://simpleTimer?period=40000")
                .bean(ServiceCrawlerBean.class, "dataCrawlerBean")
                .to("log:level=INFO&showBody=true")
                .to("activemq:queue:dataQueue");


       /* from("timer://test?period=5000")
                .bean(SendDataBean.class,"process")
                .to("activemq://test-queue");;



        //Consumer queue
        from("activemq://test-queue")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        String message = exchange.getIn().getBody(String.class);
                        log.info("--------------------------------");
                        log.info("Receive message '{}' from queue.", message);
                    }
                });
*/
    }
}
