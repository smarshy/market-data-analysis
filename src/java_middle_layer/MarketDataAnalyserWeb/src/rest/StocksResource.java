package rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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
import market.dataanalyser.jpa.Forex;
import market.dataanalyser.jpa.Liffe;
import market.dataanalyser.jpa.MovingAverageTrend;
import market.dataanalyser.jpa.Nasdaq;
import market.dataanalyser.jpa.StockMarkets;
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
	public StockMarkets listAllStocks(){
		StockMarkets stockList = bean.listAllStocks();
		return stockList;
	}
	
	
	@GET
	@Produces("application/json")
	@Path("/NASDAQ/details/{ticker}")
	public Nasdaq fetchNasdaqDetails(@PathParam("ticker") String tickerName) {
	    return bean.fetchNasdaqDetails(tickerName);
	}

	@GET
	@Produces("application/json")
	@Path("/LIFFE/details/{ticker}")
	public Liffe fetchLiffeDetails(@PathParam("ticker") String tickerName) {
	    return bean.fetchLiffeDetails(tickerName);
	}

	@GET
	@Produces("application/json")
	@Path("/FOREX/details/{ticker}")
	public Forex fetchForexDetails(@PathParam("ticker") String tickerName){
	    return bean.fetchForexDetails(tickerName);
	}


	@GET
	@Produces("application/json")
	@Path("/NASDAQ/variation/{ticker}")
	public List<Nasdaq> fetchNasdaqVariation(@PathParam("ticker") String ticker) {

	    return bean.fetchNasdaqVariation(ticker);
	}
	
	@GET
	@Produces("application/json")
	@Path("/LIFFE/variation/{ticker}")
	public List<Liffe> fetchLiffeVariation(@PathParam("ticker") String ticker) {

	    return bean.fetchLiffeVariation(ticker);
	}
	
	@GET
	@Produces("application/json")
	@Path("/FOREX/variation/{ticker}")
	public List<Forex> fetchForexVariation(@PathParam("ticker") String ticker) {

	    return bean.fetchForexVariation(ticker);
	}

	@GET
	@Produces("application/json")
	@Path("NASDAQ/variation/{ticker}/{fromDate}/{toDate}")
	public List<Nasdaq> fetchNasdaqVariation(@PathParam("ticker") String ticker, @PathParam("fromDate") String fromDate,
	        @PathParam("toDate") String toDate) {
	    int beginDate = Integer.parseInt(fromDate);
	    int completeDate = Integer.parseInt(toDate);

	    return bean.fetchNasdaqVariation(ticker, beginDate, completeDate);
	}

	@GET
	@Produces("application/json")
	@Path("LIFFE/variation/{ticker}/{fromDate}/{toDate}")
	public List<Liffe> fetchLiffeVariation(@PathParam("ticker") String ticker, @PathParam("fromDate") String fromDate,
	        @PathParam("toDate") String toDate) {
	    int beginDate = Integer.parseInt(fromDate);
	    int completeDate = Integer.parseInt(toDate);

	    return bean.fetchLiffeVariation(ticker, beginDate, completeDate);
	}

	@GET
	@Produces("application/json")
	@Path("FOREX/variation/{ticker}/{fromDate}/{toDate}")
	public List<Forex> fetchForexVariation(@PathParam("ticker") String ticker, @PathParam("fromDate") String fromDate,
	        @PathParam("toDate") String toDate) {
	    int beginDate = Integer.parseInt(fromDate);
	    int completeDate = Integer.parseInt(toDate);

	    return bean.fetchForexVariation(ticker, beginDate, completeDate);
	}


	
	@GET
	@Produces("application/json")
	@Path("/compareStocks/{ticker1}/{ticker2}/{fromDate}/{toDate}")
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
	@Path("/movingAverage/{ticker}")
	public List<MovingAverageTrend> calculateMovAvgTrend(@PathParam("ticker") String tickerName){
	    return bean.calculateMovingAverageTrend(tickerName);
	}
	
	@GET
	@Produces("application/json")
	@Path("/{regionFilter}/{segmentFilter}/{exchangeMarketFilter}")
	public List<String> listAllStocksByFilter(@PathParam("regionfilter") @DefaultValue("All Regions") String regionFilter,
	@PathParam("segmentFilter") @DefaultValue("All Sectors") String segmentFilter,
	@PathParam("exchangeMarketFilter") @DefaultValue("Nasdaq") String exchangeMarketFilter) {
	    if (bean == null) {
	        return null;
	    }
	    
	    return bean.listAllStocksByFilter(segmentFilter, regionFilter, exchangeMarketFilter);
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

