package com.tutorialspoint;

import javax.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/users/{username}")
public class UserResource {
	
	private static SessionBeanLocal bean;
	
	public UserResource(){
		try{
		InitialContext context = new InitialContext();
		bean = (SessionBeanLocal) context.lookup("");
		}catch(NamingException ee){
			
		}
	}

	@GET
	@Produces("text/plain")
	public String printMessage(){
		return bean.getMessage();
		
	}
	

    @PUT
    @Consumes("text/plain")
    public Response putMsg(@PathParam("param") String msg) {
        String output = "PUT: message says : " + msg;
        return Response.status(200).entity(output).build();
    }

}
