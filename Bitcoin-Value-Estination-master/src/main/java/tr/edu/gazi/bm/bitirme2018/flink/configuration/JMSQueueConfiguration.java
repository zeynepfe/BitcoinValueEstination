package tr.edu.gazi.bm.bitirme2018.flink.configuration;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.apache.camel.CamelContext;
import org.apache.camel.ThreadPoolRejectedPolicy;
import org.apache.camel.spi.ThreadPoolProfile;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

import java.io.File;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;


@Configuration
public class JMSQueueConfiguration {

    public static final String MY_DEFAULT_PROFILE = "myDefaultProfile";
    public static final String ACTIVEMQ = "activemq";


    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService broker() throws Exception {
        final BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.addConnector("vm://localhost");
        PersistenceAdapter persistenceAdapter = new KahaDBPersistenceAdapter();
        File dir = new File(System.getProperty("user.home") + File.separator + "kaha");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        persistenceAdapter.setDirectory(dir);
        broker.setPersistenceAdapter(persistenceAdapter);
        broker.setPersistent(true);
        return broker;
    }

    @Bean
    CamelContextConfiguration contextConfiguration(@Value("${spring.activemq.user}") String user,
                                                   @Value("${spring.activemq.password}") String password,
                                                   @Value("${spring.activemq.broker-url}") String url) {
        return new CamelContextConfiguration() {

            @Override
            public void beforeApplicationStart(CamelContext context) {
                // your custom configuration goes here
                ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
                threadPoolProfile.setId(MY_DEFAULT_PROFILE);
                threadPoolProfile.setPoolSize(10);
                threadPoolProfile.setMaxPoolSize(15);
                threadPoolProfile.setMaxQueueSize(250);
                threadPoolProfile.setKeepAliveTime(25L);
                threadPoolProfile.setRejectedPolicy(ThreadPoolRejectedPolicy.Abort);
                context.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
                context.addComponent(ACTIVEMQ, jmsComponentAutoAcknowledge(connectionFactory));
            }

        };
    }

}
