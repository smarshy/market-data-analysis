package rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import market.dataanalyser.ejb.MarketDataAnalyserBeanLocal;
import market.dataanalyser.jpa.Nasdaq;

@Path("/stocks")
public class StocksResource {
	
	private MarketDataAnalyserBeanLocal bean;
	
	public StocksResource(){
		try{
		InitialContext context = new InitialContext();
		bean = (MarketDataAnalyserBeanLocal) context.lookup("java:app/MarketDataAnalyserEJB/MarketDataAnalyserBean!market.dataanalyser.ejb.MarketDataAnalyserBeanLocal");
		}catch(NamingException ex){
			
		}
	}

	@GET
	@Produces("application/json")
	public List<String> listAllStocks(){
		//String greetText = bean.get_message();
    	//return greetText;
		List<String> stockList = bean.listAllStocks();
		return stockList;
	}
	
	@GET
	@Produces("application/json")
	@Path("/query")
	public Nasdaq fetchStockDetails(@QueryParam("ticker") String tickerName) {
		return bean.fetchStockDetails(tickerName);
	}
	
	@GET
	@Produces("application/json")
	@Path("/query")
	public List<Nasdaq> fetchStockVariation(
	@QueryParam("ticker") String tickerName,
	@QueryParam("fromDate") String fromDate,
	@QueryParam("toDate") String toDate){
		
		int startDate = Integer.parseInt(fromDate);
		int endDate = Integer.parseInt(toDate);
		return bean.fetchStockVariation(tickerName,startDate,endDate);
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

