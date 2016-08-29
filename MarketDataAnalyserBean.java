package market.dataanalyser.ejb;


import java.math.BigDecimal;
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

import market.dataanalyser.jpa.Nasdaq;

/**
 * Session Bean implementation class MarketDataAnalyzerBean
 */
@Stateless
@Local(MarketDataAnalyserBeanLocal.class)
@Remote(MarketDataAnalyserBeanRemote.class)
@LocalBean
public class MarketDataAnalyserBean implements MarketDataAnalyserBeanRemote, MarketDataAnalyserBeanLocal {

  //TO BE INCLUDED IN REST APPLICATION  
//	List<String> NasdaqList;
//	String thisNasdaq;
//	Date fromDate;
//	Date toDate;
//	List<Nasdaq_Details> NasdaqData;  // GET CONTEXT FROM ENTITY BEAN
	
	
	@PersistenceContext(name="MarketAnalyserJPA")           //ADD PERSISTENCE CONTEXT
	EntityManager em;
	String simpleText;
	
	@Override
	public List<String> listAllStocks(){
		
		Query query=em.createQuery("SELECT s.ticker from Nasdaq as s");
		@SuppressWarnings("unchecked")
		List<String> NasdaqList=query.getResultList();
		return NasdaqList;
	}
//	public List<String> listAllStocksBySegment(String filterSegment){
//	  TypedQuery <Nasdaq> query=em.createQuery("SELECT s.ticker from Nasdaq as s where s.Segment=:filtersegment ",Nasdaq.class);
//	  query.setParameter("filtersegment",filterSegment);//complete this statement  
//	}
	
//	public List<String> listAllStocksByRegion(String filterRegion){
//	  TypedQuery <Nasdaq> query=em.createQuery("SELECT s.ticker from Nasdaq as s where s.Region=:filterregion ",Nasdaq.class);
//	  query.setParameter("filterregion",filterRegion);//complete this statement
//	}
	@Override
    public Nasdaq fetchStockDetails(String tickerName){
    	TypedQuery <Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and exchangeDate=12032005",Nasdaq.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",tickerName);
    	
		//CHECK EXCHANGE DATE
		Nasdaq NasdaqData=(Nasdaq) query.getResultList();
		return NasdaqData;
    }
	
    @Override
	public List<BigDecimal> fetchStockVariation(String ticker, int fromDate, int toDate,String frequency){
		
//    	if(fromDate > toDate){
//    		//THROW ERROR
//    	}
//    	
//    	switch(frequency){
//    	case "monthly":
//    		
//    		
//    		break;
////    	case "yearly":
////    		
////    		break;
//    	case "daily":
//    	default:
//    		Query query=em.createQuery("SELECT s.closingPrice from Nasdaq as s where s.ticker=:tickername and s.exchangeDate BETWEEN :fromdate AND :todate");//CHECK THE DATE FORMAT
//        	query.setParameter("tickername",ticker);
//        	query.setParameter("fromdate", fromDate);
//        	query.setParameter("todate", toDate);
//    		
//    	}
//    	
    	
		//QUERY TO DB
		//List<BigDecimal> StockVariationClosing=query.getResultList();
//		return StockVariationClosing;
		return null;
	}

	@Override
	public void compose_message(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String get_message() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
//	public compare(){
//		//REST API WILL HAVE TO IMPLEMENT WILL THIS
//	}
    
    
    
    
    
    

}
