import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import market.dataanalyser.ejb.Greeting;

@Path("/users")
public class UserResource {
	
	private Greeting bean;
	
	public UserResource(){
		try{
		InitialContext context = new InitialContext();
		bean = (Greeting) context.lookup("java:app/MarketDataAnalyserEJB/Greeting!market.dataanalyser.ejb.GreetingRemote");
		}catch(NamingException ex){
			
		}
	}

	@GET
	@Produces("text/plain")
	public String printMessage(){
		return "HI";
		
	}
	
    @PUT
    @POST
    @Consumes("text/plain")
    public void insertName(String name) {
    	System.out.println(name);
    	bean.compose_message(name);
    	bean.get_message();
    }
    
    /*
    public Response putMsg(@PathParam("param") String msg) {
        String output = "PUT: message says : " + msg;
        return Response.status(200).entity(output).build();
    }
    */

}

