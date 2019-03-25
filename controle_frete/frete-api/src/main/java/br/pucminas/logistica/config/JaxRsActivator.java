package br.pucminas.logistica.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class JaxRsActivator extends Application {

	public JaxRsActivator() {
		init();
	}

	private void init() {
		
	}
}
