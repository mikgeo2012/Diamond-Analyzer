/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

import java.util.ArrayList;
//import org.apache.camel.component.jms.JmsConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;


public class DataProducerEndpoint extends AbstractObserver {
	
	public DataProducerEndpoint(Broker b) {
		this.broker = b;
		this.broker.attach(this);
	}

    

	
	public void run() {
		
		String attr[] = {"Institute", "Price","Carat", "Color", "Clarity", "Cut"};
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
			        from("file:data/old/?noop=true")
			        .log("RECEIVED: ${body}")
			        .unmarshal().csv()
			        .split(body())
			        //.convertBodyTo(String.class)
			        .process(new Processor () {
			        	@Override
			        	public void process(Exchange exchange) throws Exception {
			        		String[] data = exchange.getIn().getBody(String.class).split(",");
			        		ArrayList<String> reformed_data = new ArrayList<>();
			        		
			        		UUID id = UUID.randomUUID();
			        		reformed_data.add(id.toString());
			        		
			        		for (int i = 0; i < data.length; i++) {
			        			
			        			if (i == 0) {
			        				reformed_data.add(data[i].substring(1).replaceAll("\\s",""));
			        			} else if (i == data.length - 1) {
			        				reformed_data.add(data[i].substring(0, data[i].length() - 1).replaceAll("\\s",""));
			        			} else {
			        				reformed_data.add(data[i].replaceAll("\\s",""));
			        			}
			        			
			      
			        		}
			        		
			        		
			        		
			        		StringBuilder msg = new StringBuilder();
			        		for (int i = 0; i < reformed_data.size(); i++) {
			        			msg.append(reformed_data.get(i));
			        			if (i < reformed_data.size() - 1) {
			        				msg.append(",");
			        			}
			        		}
			        				        		
			        		
			        		exchange.getIn().setBody(msg.toString());
			        		
			        		
			        	}
			        })
			        .to("jms:MPCS_51050_FP_Diamond_Data");
			        
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

        // start the route and let it do its work
        
		
	}




	@Override
	public void update() {
		String attr[] = {"Institute", "Price","Carat", "Color", "Clarity", "Cut"};
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
			        from("file:data/?noop=false&move=${file:parent}/old/${file:name}")
			        .log("RECEIVED: ${body}")
			        .unmarshal().csv()
			        .split(body())
			        //.convertBodyTo(String.class)
			        .process(new Processor () {
			        	@Override
			        	public void process(Exchange exchange) throws Exception {
			        		String[] data = exchange.getIn().getBody(String.class).split(",");
			        		ArrayList<String> reformed_data = new ArrayList<>();
			        		
			        		UUID id = UUID.randomUUID();
			        		reformed_data.add(id.toString());
			        		
			        		for (int i = 0; i < data.length; i++) {
			        			
			        			if (i == 0) {
			        				reformed_data.add(data[i].substring(1).replaceAll("\\s",""));
			        			} else if (i == data.length - 1) {
			        				reformed_data.add(data[i].substring(0, data[i].length() - 1).replaceAll("\\s",""));
			        			} else {
			        				reformed_data.add(data[i].replaceAll("\\s",""));
			        			}
			        			
			      
			        		}
			        		
			        		
			        		
			        		StringBuilder msg = new StringBuilder();
			        		for (int i = 0; i < reformed_data.size(); i++) {
			        			msg.append(reformed_data.get(i));
			        			if (i < reformed_data.size() - 1) {
			        				msg.append(",");
			        			}
			        		}
			        				        		
			        		
			        		exchange.getIn().setBody(msg.toString());
			        		
			        		
			        	}
			        })
			        .to("jms:MPCS_51050_FP_Diamond_Data");
			        
			    }
			});
			
			context.start();
	        Thread.sleep(8000);

	        // stop the CamelContext
	        context.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
		
	}
}
