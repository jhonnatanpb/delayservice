package co.com.devco.delayservice.soap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import co.com.devco.delayservice.soapdelay.DelayRequest;
import co.com.devco.delayservice.soapdelay.DelayResponse;

@Endpoint
public class SoapDelayEndpoint {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@PayloadRoot(namespace = "http://devco.com.co/delayservice/soapdelay", localPart = "DelayRequest")
	@ResponsePayload
	public DelayResponse delay(@RequestPayload DelayRequest request) {
		DelayResponse response = new DelayResponse();
		LOGGER.info("SERVICIO SOAP: Iniciando Espera");
		try {
			Thread.sleep(request.getDelay());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("SERVICIO SOAP: Fin Espera");
		response.setDelay("Ha terminado la espera");
		return response;
	}
}
