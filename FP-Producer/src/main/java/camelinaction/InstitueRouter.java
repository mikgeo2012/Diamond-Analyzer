package camelinaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class InstitueRouter extends AbstractObserver{

	public InstitueRouter(Broker b) {
		this.broker = b;
		this.broker.attach(this);
	}
	
	public void run() {
		// create CamelContext
        CamelContext context = new DefaultCamelContext();

        // connect to ActiveMQ JMS broker listening on localhost on port 61616
        ConnectionFactory connectionFactory = 
        	new ActiveMQConnectionFactory("tcp://localhost:61616");
        context.addComponent("jms",
            JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        
        // add our route to the CamelContext
        try {
			context.addRoutes(new RouteBuilder() {
			    public void configure() {
			        from("jms:MPCS_51050_FP_Diamond_Data")
			        .log("RECEIVED:  jms queue: ${body} from file: ${header.CamelFileNameOnly}")
	                .choice()
	                .when(body().regex(".*GIA.*"))
	                .to("jms:topic:MPCS_51050_FP_TOPIC_GIA")
	            	.when(body().regex(".*AGS.*"))
	            	.to("jms:topic:MPCS_51050_FP_TOPIC_AGS");
	            	
	                
	            }
	        });
			
			context.start();
	        Thread.sleep(10000);

	        // stop the CamelContext
	        context.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		// create CamelContext
        CamelContext context = new DefaultCamelContext();

        // connect to ActiveMQ JMS broker listening on localhost on port 61616
        ConnectionFactory connectionFactory = 
        	new ActiveMQConnectionFactory("tcp://localhost:61616");
        context.addComponent("jms",
            JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        
        // add our route to the CamelContext
        try {
			context.addRoutes(new RouteBuilder() {
			    public void configure() {
			        from("jms:MPCS_51050_FP_Diamond_Data")
			        .log("RECEIVED:  jms queue: ${body} from file: ${header.CamelFileNameOnly}")
	                .choice()
	                .when(body().regex(".*GIA.*"))
	                .to("jms:topic:MPCS_51050_FP_TOPIC_GIA")
	            	.when(body().regex(".*AGS.*"))
	            	.to("jms:topic:MPCS_51050_FP_TOPIC_AGS");
	            	
	                
	            }
	        });
			
			context.start();
	        Thread.sleep(10000);

	        // stop the CamelContext
	        context.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
