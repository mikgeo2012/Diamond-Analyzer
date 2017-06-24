package camelinaction;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.util.jndi.JndiContext;

public class Endpoint {
	private String color;
	private String clarity;
	private String cut;
	private String inst;
	
	private AbstractUser user;
	
	public Endpoint(String cut, String clarity, String color, String inst) {
		this.cut = cut;
		this.clarity = clarity;
		this.color = color;
		this.inst = inst;
		if (inst.equals("GIA")) {
			user = new UserGIA();
		}
		else if (inst.equals("AGS")) {
			user = new UserAGS();
		}
		
		
	}

	
	public void r() {
		// create CamelContext
		JndiRegistry jndiRegistry = new JndiRegistry();
		try {
        	JndiContext jndiContext = new JndiContext();
        	
        	if (inst.equals("GIA")) {
        		jndiContext.bind("userBean", new UserGIA());
        	}
    		else if (inst.equals("AGS")) {
    			jndiContext.bind("userBean", new UserAGS());
    		}
        	
        	
			
			jndiRegistry.setContext(jndiContext);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("BEAN ERROR");
		}
		
        CamelContext context = new DefaultCamelContext(jndiRegistry);

        // connect to ActiveMQ JMS broker listening on localhost on port 61616
        ConnectionFactory connectionFactory = 
        	new ActiveMQConnectionFactory("tcp://localhost:61616");
        context.addComponent("jms",
            JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        subscribe(context);
        
        user.process();
		
	}
	
	public void subscribe(CamelContext context) {
		final String inst_final = this.inst;
		final String ct = cut;
		final String clr = color;
		final String clrty = clarity;
		
		try {
			context.addRoutes(new RouteBuilder() {
			    public void configure() {
			    	String topic = "jms:topic:MPCS_51050_FP_TOPIC_" + inst_final;
			    	
			    	
			    	
			    	onException(Exception.class).process(new Processor(){

						@Override
						public void process(Exchange exchange)
								throws Exception {
							 Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
							 log.error("Error has occurred: ", cause);
						}
		    			
		    		});
			    	
		    		
		    		
			        from(topic)
			        .filter().simple("${body} contains '" + ct + "' and ${body} contains '," + clr + ",' and "
			    		+ "${body} contains '" + clrty + "'")				    		
			    	.to("bean:userBean?method=processMessage");
			    }
			    	
			        
			    
			});
			
			context.start();
	        Thread.sleep(20000);

	        // stop the CamelContext
	        context.stop();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();;
		}
    }
	
	

}
