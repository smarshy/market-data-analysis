package rest;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//
//import market.dataanalyser.ejb.MarketDataAnalyserBeanLocal;
//
//@Path("/users")
//public class UserResource {
//	
//	private MarketDataAnalyserBeanLocal bean;
//	
//	public UserResource(){
//		try{
//		InitialContext context = new InitialContext();
//		bean = (MarketDataAnalyserBeanLocal) context.lookup("java:app/MarketDataAnalyserEJB/MarketDataAnalyserBean!market.dataanalyser.ejb.MarketDataAnalyserBeanLocal");
//		}catch(NamingException ex){
//			
//		}
//	}
//
//	@GET
//	@Produces("text/plain")
//	public String printMessage(){
//		String greetText = bean.get_message();
//    	return greetText;	
//	}
//	
//    @PUT
//    @POST
//    @Consumes("text/plain")
//    public void insertName(String name) {
//    	System.out.println(name);
//    	bean.compose_message(name);
//    	
//    }
//
//}

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import market.dataanalyser.ejb.MarketDataAnalyserBean;
import market.dataanalyser.ejb.MarketDataAnalyserBeanLocal;
import market.dataanalyser.jpa.Nasdaq;



@Path("/stocks")
public class StocksResource {
// private static SessionBeanLocal bean;
	MarketDataAnalyserBeanLocal bean;
	

public StocksResource() {
    
     try{
    	 String appName     = "java:app";
			String moduleName  = "MarketDataAnalyserEJB";
			String beanName    = "MarketDataAnalyserBean";
			String packageName = "market.dataanalyser.ejb";
			String className   = "MarketDataAnalyserBeanLocal";
			
			// Lookup the bean using the full JNDI name.
			String fullJndiName = String.format("%s/%s/%s!%s.%s", appName, moduleName, beanName, packageName, className);
     InitialContext context = new InitialContext();
     bean = (MarketDataAnalyserBeanLocal) context.lookup("java:app/MarketDataAnalyserEJB/MarketDataAnalyserBean!market.dataanalyser.ejb.MarketDataAnalyserBeanLocal");
     }catch(NamingException ee){
    	 
     }
}

@GET
@Produces("application/json")
public List<String> listAllStocks() {
    return bean.listAllStocks();
}

 @GET
@Produces("application/json")
 @Path("/query")
public Nasdaq fetchStockDetails(@QueryParam("ticker") String tickerName) {
return bean.fetchStockDetails(tickerName);
 }
 /*
private Date getDateFromString(String dateString) {
    Date date = null;
    try {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        date = df.parse(dateString);
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return date;
}

@GET
@Produces("application/json")
public List<String> listAllStocksByRegion(@QueryParam("regionFilter") @DefaultValue("") String regionFilter) {
    if (sbean == null) {
        return null;
    }

    if (regionFilter.length() == 0) {
        return sbean.listAllStocks();
    } else {
        return sbean.listAllStocksByRegion(regionFilter);
    }
}

@GET
@Produces("application/json")
public List<String> listAllStocksBySegment(@QueryParam("segmentFilter") @DefaultValue("") String segmentFilter) {
    if (sbean == null) {
        return null;
    }

    if (segmentFilter.length() == 0) {
        return sbean.listAllStocks();
    } else {
        return sbean.listAllStocksBySegment(segmentFilter);
    }
}

@GET
@Produces("application/json")
public List<Stock> compareStocks(@QueryParam("ticker1") String ticker1, @QueryParam("ticker2") String ticker2) {
    return sbean.compareStocks(ticker1, ticker2);
}

@GET
@Produces("application/json")
public List<Double> fetchStockVariation(String ticker, Date fromDate, Date todate, String frequency) {

    return null;
}*/
 }
