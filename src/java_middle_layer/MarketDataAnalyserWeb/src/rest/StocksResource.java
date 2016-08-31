package rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import market.dataanalyser.ejb.MarketDataAnalyserBeanLocal;
import market.dataanalyser.jpa.CompareStocks;
import market.dataanalyser.jpa.MovAvgTrend;
import market.dataanalyser.jpa.Nasdaq;
import market.dataanalyser.jpa.VolumePriceTrend;

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
	@Path("variation/{ticker}/{fromDate}/{toDate}")
	public List<Nasdaq> fetchStockVariation(
	@PathParam("ticker") String tickerName,
	@PathParam("fromDate") String fromDate,
	@PathParam("toDate") String toDate){
		
		int startDate = Integer.parseInt(fromDate);
		int endDate = Integer.parseInt(toDate);
		System.out.println(startDate);
		System.out.println(endDate);
		return bean.fetchStockVariation(tickerName,startDate,endDate);
	}
	
	
	@GET
	@Produces("application/json")
	@Path("/{ticker1}/{ticker2}/{fromDate}/{toDate}")
	public CompareStocks compareTwoStocks(
	@PathParam("ticker1") String ticker1,
	@PathParam("ticker2") String ticker2,
	@PathParam("fromDate") String beginDate,
	@PathParam("toDate") String completeDate
	) {
	 int startDate = Integer.parseInt(beginDate);
	 int finalDate = Integer.parseInt(completeDate);

	 return bean.compareTwoStocks(ticker1,ticker2,startDate, finalDate);
	}
	
	@GET
	@Produces("application/json")
	@Path("/volumePriceTrend/{ticker}")
	public List<VolumePriceTrend> calculateVolumePriceTrend(@PathParam("ticker") String tickerName){
	return bean.calculateVolumePriceTrend(tickerName);
	}
	
	@GET
	@Produces("application/json")
	@Path("/movAvgTrend/{ticker}")
	public List<MovAvgTrend> calculateMovAvgTrend(@PathParam("ticker") String tickerName){
	    return bean.calculateMovAvgTrend(tickerName);
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

