package ar.com.educacionit.ws.rest.filters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import ar.com.educacionit.ws.domain.Role;
import ar.com.educacionit.ws.domain.User;
import ar.com.educacionit.ws.exceptions.ServiceException;
import ar.com.educacionit.ws.services.UserService;
import ar.com.educacionit.ws.services.impl.UserServiceImpl;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@Context //CDI
	private ResourceInfo resourceInfo; 
	
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    
    private static final String AUTHENTICATION_SCHEME = "Basic";

	private UserService userService = new UserServiceImpl();
	
	/**
     * A preflight request is an OPTIONS request
     * with an Origin header.
     */
    private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString("Origin") != null
                && request.getMethod().equalsIgnoreCase("OPTIONS");
    }
    
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
	  
		// If it's a preflight request, we abort the request with
        // a 200 status, and the CORS headers are added in the
        // response filter method below.
        if (isPreflightRequest(requestContext)) {
        	requestContext.abortWith(Response.ok().build());
            return;
        }
        
        //API DE REFLECTION
		Method method = resourceInfo.getResourceMethod();
		 
		//Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                         .entity("Access blocked for all users !!").build());
                return;
            }
              
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
              
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
              
            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You cannot access this resource").build());
                return;
            }
              
            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
              
            //Decode username and password
            String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));;
  
            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
              
            //Verifying Username and password
            System.out.println(username);
            System.out.println(password);
              
            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                  
                //Is user valid?
                if( ! isUserAllowed(username, password, rolesSet)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
                    return;
                }
            }/*else {
            	requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Metodo sin seguridad alguna!!").build());
            }*/
        }
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
		
        boolean isAllowed = false;
          
        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        try {

        	User user = userService.getUserByUsername(username);
			
        	if(user !=null && user.getPassword().equals(password)) {

        		for(String role : rolesSet) {
            		List<Role> roles = user.getRoles()
            				.stream()
            				.filter(x -> x.getRole().equals(role))
            				.collect(Collectors.toList());
            		isAllowed = !roles.isEmpty();
            		if(isAllowed) {
            			break;
            		}
        		};
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
        
        return isAllowed;
    }
}