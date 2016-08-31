package market.dataanalyser.ejb;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    	TypedQuery <Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and exchangeDate=:exchangedate",Nasdaq.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",tickerName);
    	query.setParameter("exchangedate",20110103);
		//CHECK EXCHANGE DATE
		Nasdaq nasdaqData= query.getSingleResult();
		return nasdaqData;
    }
	
	
	@Override
	public List<Nasdaq> fetchStockVariation(String ticker, int fromDate, int toDate){
		
    	
//    	if(fromDate > toDate){
//    		//THROW ERROR
//    	}
    	
		TypedQuery<Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and s.exchangeDate BETWEEN :fromdate AND :todate",Nasdaq.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",ticker);
    	query.setParameter("fromdate", fromDate);
    	query.setParameter("todate", toDate);
        	
		List<Nasdaq> listOfNasdaq=query.getResultList();
		for(Nasdaq stock: listOfNasdaq){
			System.out.println(stock.getClosingPrice());
		}
		
		return listOfNasdaq;
	}

    public boolean IsArrowUp(String ticker){
    	System.out.println("Inside isArrow");
    	Query query=em.createQuery("SELECT s.closingPrice from Nasdaq as s where s.ticker=:tickername order by s.exchangeDate DESC");
    	query.setParameter("tickername",ticker);
    	query.setMaxResults(2);
    	System.out.println("query executed");
    	@SuppressWarnings("unchecked")
		List<BigDecimal> list=query.getResultList();
    	System.out.println("result retrieved");
    	 int result=list.get(0).compareTo(list.get(1));
    	 if(result == 1){
    		 return false;
    	 }
    	 else
    		 return true;
    }
    
//    public CompareStocks compareTwoStocks(String ticker1,String ticker2,int fromDate, int toDate){
//    	
//    }
	@Override
	public void compose_message(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String get_message() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void compose_message(String userName) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public String get_message() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
//	public compare(){
//		//REST API WILL HAVE TO IMPLEMENT WILL THIS
//	}
    
    
    
    
    
    

}
