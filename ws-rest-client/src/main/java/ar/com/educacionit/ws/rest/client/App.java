package ar.com.educacionit.ws.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.com.educacionit.ws.domain.Producto;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// client
        Client client = ClientBuilder.newClient();
        
        //target
        WebTarget webTarget = client.target("http://localhost:8080/ws-rest-producto-server/api/productos");
        
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.get();
        
        if(Status.OK.getStatusCode() == response.getStatus()) {
        	
        	GenericType<List<Producto>> productosEntityType = new  GenericType<List<Producto>>() {};
        	
        	List<Producto> productos = response.readEntity(productosEntityType);
        	
        	System.out.println(productos);
        }
        
    }
}
