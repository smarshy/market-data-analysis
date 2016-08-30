package rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import market.dataanalyser.ejb.MarketDataAnalyserBeanLocal;
import market.dataanalyser.jpa.Nasdaq;

@Path("/stock/details")
public class StockDetails {
	
private MarketDataAnalyserBeanLocal bean;
	
	public StockDetails(){
		try{
		InitialContext context = new InitialContext();
		bean = (MarketDataAnalyserBeanLocal) context.lookup("java:app/MarketDataAnalyserEJB/MarketDataAnalyserBean!market.dataanalyser.ejb.MarketDataAnalyserBeanLocal");
		}catch(NamingException ex){
			
		}
	}
	
	@GET
	@Produces("application/json")
	public Nasdaq getStockDetails(String name){
		//String greetText = bean.get_message();
    	//return greetText;
		Nasdaq stockDetails = bean.fetchStockDetails(name);
		return stockDetails;
	}
	
    /*@PUT
    @POST
    @Consumes("text/plain")
    public void getStockName(String name) {
    	System.out.println(name);
    	
    }
    */

}
