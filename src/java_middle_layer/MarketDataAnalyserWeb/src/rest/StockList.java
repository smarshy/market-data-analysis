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

@Path("/stocks")
public class StockList {
	
	private MarketDataAnalyserBeanLocal bean;
	
	public StockList(){
		try{
		InitialContext context = new InitialContext();
		bean = (MarketDataAnalyserBeanLocal) context.lookup("java:app/MarketDataAnalyserEJB/MarketDataAnalyserBean!market.dataanalyser.ejb.MarketDataAnalyserBeanLocal");
		}catch(NamingException ex){
			
		}
	}

	@GET
	@Produces("application/json")
	public List<String> printMessage(){
		//String greetText = bean.get_message();
    	//return greetText;
		List<String> stockList = bean.listAllStocks();
		return stockList;
	}
	
    /*@PUT
    @POST
    @Consumes("text/plain")
    public void insertName(String name) {
    	System.out.println(name);
    	bean.compose_message(name);
    	
    }
    */

}

