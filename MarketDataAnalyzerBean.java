package marketdataanalyzer.sessionbeans.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class MarketDataAnalyzerBean
 */
@Stateless
@Local(MarketDataAnalyzerBeanLocal.class)
@Remote(MarketDataAnalyzerBeanRemote.class)
@LocalBean
public class MarketDataAnalyzerBean implements MarketDataAnalyzerBeanRemote, MarketDataAnalyzerBeanLocal {

  //TO BE INCLUDED IN REST APPLICATION  
//	List<String> stockList;
//	String thisStock;
//	Date fromDate;
//	Date toDate;
//	List<Stock_Details> stockData;  // GET CONTEXT FROM ENTITY BEAN
	
	
//	@PersistenceContext(name="")           //ADD PERSISTENCE CONTEXT
	EntityManager em;
	String simpleText;
	
	public List<String> listAllStocks(){
		
		TypedQuery <Stock> query=em.createQuery("SELECT s.ticker from Stock as s");
		//QUERY TO DB
		List<String> stockList=query.getResultList();
		return stockList;
	}
    public Stock fetchStockDetails(String tickerName){
    	TypedQuery <Stock> query=em.createQuery("SELECT s from Stock as s where s.ticker=:tickername and exchangeDate=12032005",Stock.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",tickerName);
    	
		//QUERY TO DB
		List<Stock> stockData=query.getResultList();
		return stockData;
    }
	
	public List<double> fetchStockVariation(String ticker, Date fromDate, Date todate){
		Query query=em.createQuery("SELECT s.closingPrice from Stock as s where s.ticker=:tickername and s.exchangeDate BETWEEN :fromdate AND :todate");//CHECK THE DATE FORMAT
    	query.setParameter("tickername",tickerName);
    	query.setParameter("fromdate", fromDate);
    	query.setParameter("todate", toDate);
    	
    	
		//QUERY TO DB
		List<double> stockVariationClosing=query.getResultList();
		return stockVariationClosing;
	}
	
	
//	public compare(){
//		//REST API WILL HAVE TO IMPLEMENT WILL THIS
//	}
    
    
	public List<double> fetchStockVariationFrequency(String ticker, Date fromDate, Date todate,String frequency){
		
		
		Query query=em.createQuery("SELECT s.closingPrice from Stock as s where s.ticker=:tickername and s.exchangeDate BETWEEN :fromdate AND :todate");//CHECK THE DATE FORMAT
    	query.setParameter("tickername",tickerName);
    	query.setParameter("fromdate", fromDate);
    	query.setParameter("todate", toDate);
    	
    	
		//QUERY TO DB
		List<double> stockVariationClosing=query.getResultList();
		return stockVariationClosing;
	}
    
    
    
    

}
