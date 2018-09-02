package co.com.devco.delayservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.devco.delayservice.model.Delay;

@RestController
public class DelayController {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@PostMapping(value = "/restdelay")
	public String delay(@RequestBody Delay delay) {
		LOGGER.info("SERVICIO REST: Iniciando Espera");
		try {
			Thread.sleep(delay.getDelay());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("SERVICIO REST: Fin Espera");
		return "Ha terminado la espera";
	}
	
}
