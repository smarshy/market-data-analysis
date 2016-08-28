package rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import market.dataanalyser.ejb.GreetingLocal;

@Path("/users")
public class UserResource {
	
	private GreetingLocal bean;
	
	public UserResource(){
		try{
		InitialContext context = new InitialContext();
		bean = (GreetingLocal) context.lookup("java:app/MarketDataAnalyserEJB/Greeting!market.dataanalyser.ejb.GreetingLocal");
		}catch(NamingException ex){
			
		}
	}

	@GET
	@Produces("text/plain")
	public String printMessage(){
		String greetText = bean.get_message();
    	return greetText;	
	}
	
    @PUT
    @POST
    @Consumes("text/plain")
    public void insertName(String name) {
    	System.out.println(name);
    	bean.compose_message(name);
    	
    }

}

