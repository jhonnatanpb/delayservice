package co.com.devco.delayservice.soap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
	    messageDispatcherServlet.setApplicationContext(context);
	    messageDispatcherServlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	@Bean(name = "delay")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema delaySchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("DelayPort");
		definition.setTargetNamespace("http://devco.com.co/delayservice/soapdelay");
		definition.setLocationUri("/ws");
		definition.setSchema(delaySchema);
		return definition;
	}

	@Bean
	public XsdSchema delaySchema() {
		return new SimpleXsdSchema(new ClassPathResource("delay.xsd"));
	}
}
