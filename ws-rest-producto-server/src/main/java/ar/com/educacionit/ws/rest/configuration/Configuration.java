package ar.com.educacionit.ws.rest.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")// directorio virtual
// Identifies the application path that serves as the base URI for all resource URIs provided by Path
public class Configuration extends Application {
	
}
