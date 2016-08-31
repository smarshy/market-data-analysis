package market.dataanalyser.ejb;


import java.math.BigDecimal;
import java.util.ArrayList;
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
import market.dataanalyser.jpa.VolumePriceTrend;
import market.dataanalyser.jpa.CompareStocks;

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
		
		Query query=em.createQuery("SELECT DISTINCT s.ticker from Nasdaq as s");
		@SuppressWarnings("unchecked")
		List<String> NasdaqList=query.getResultList();
		return NasdaqList;
	}
	public List<String> listAllStocksByFilter(String filterSegment,String filterRegion,String exchangeMarket){
		Query query = null;
		if(filterSegment=="" && filterRegion==""){
			query=em.createQuery("SELECT DISTINCT s.ticker from :exchangemarket as s");
		  	query.setParameter("exchangemarket",exchangeMarket);

		}
		else if(filterSegment==""){
			query=em.createQuery("SELECT DISTINCT s.ticker from :exchangemarket as s where s.region=:filterregion ");
		  	query.setParameter("filterregion",filterRegion);
		  	query.setParameter("exchangemarket",exchangeMarket);
		}
		else if(filterRegion==""){
			query=em.createQuery("SELECT DISTINCT s.ticker from :exchangemarket as s where s.sector=:filtersegment ");
		  	query.setParameter("filtersegment",filterSegment);
		  	query.setParameter("exchangemarket",exchangeMarket);
		}
		else{
			query=em.createQuery("SELECT DISTINCT s.ticker from :exchangemarket as s where s.sector=:filtersegment and s.region:=filterregion");
		  	query.setParameter("filtersegment",filterSegment);
		  	query.setParameter("exchangemarket",exchangeMarket);
		}
	  	//complete this statement 
	  	@SuppressWarnings("unchecked")
		List<String> NasdaqList=query.getResultList();
		return NasdaqList;
	}
	
	/*public List<String> listAllStocksByRegion(String filterRegion){
	  Query query=em.createQuery("SELECT s.ticker from Nasdaq as s where s.region=:filterregion ");
	  query.setParameter("filterregion",filterRegion);//complete this statement
	  @SuppressWarnings("unchecked")
	List<String> NasdaqList=query.getResultList();
		return NasdaqList;
	}*/
	@Override
    public Nasdaq fetchStockDetails(String tickerName){
    	TypedQuery <Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and exchangeDate=:exchangedate",Nasdaq.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",tickerName);
    	query.setParameter("exchangedate",20110103);
		//CHECK EXCHANGE DATE
		Nasdaq NasdaqData= query.getSingleResult();
		NasdaqData.setUpArrow(isArrowUp(tickerName));
		return NasdaqData;
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
//		for(Nasdaq stock: listOfNasdaq){
//			System.out.print(stock.getClosingPrice());
//			System.out.println(stock.isUpArrow());
//
//		}
		
		return listOfNasdaq;
	}

    public boolean isArrowUp(String ticker){
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
    
    public CompareStocks compareTwoStocks(String ticker1,String ticker2,int fromDate, int toDate){
		
    	CompareStocks compareStocks=new CompareStocks();
    	compareStocks.setStock1(fetchStockDetails(ticker1));
    	compareStocks.setStock2(fetchStockDetails(ticker2));
    	compareStocks.setListStock1(fetchStockVariation(ticker1, fromDate, toDate));
    	compareStocks.setListStock2(fetchStockVariation(ticker2, fromDate, toDate));
    	return compareStocks;
    	
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
	@Override
	public List<String> listAllStocksByRegion(String filterRegion) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
    public List<VolumePriceTrend> calculateVolumePriceTrend(String ticker){
    	
    	Query query=em.createQuery("SELECT DISTINCT s from Nasdaq as s where s.ticker=:tickername ");
    	query.setParameter("tickername",ticker);
    	@SuppressWarnings("unchecked")
		List<Nasdaq> list=query.getResultList();
    	List<Integer> volumeList=new ArrayList<Integer>();
    	List<BigDecimal> closingPriceList=new ArrayList<BigDecimal>();
    	List<Integer> dateList=new ArrayList<Integer>();
    	List<VolumePriceTrend> vptList=new ArrayList<VolumePriceTrend>();
    	for(Nasdaq n: list){
        	volumeList.add(n.getVolume());
        	closingPriceList.add(n.getClosingPrice());
        	dateList.add(n.getExchangeDate());
        	
    	}
    	VolumePriceTrend firstvpt=new VolumePriceTrend();
    	firstvpt.setVpt(volumeList.get(0));
    	firstvpt.setDate(dateList.get(0));
    	vptList.add(firstvpt);
    	
    	for(int i=1;i<list.size();i++){
    		VolumePriceTrend vptNew=new VolumePriceTrend();
    		BigDecimal d1=new BigDecimal(volumeList.get(i));
    		
    		
    		vptNew.setVpt(vptList.+(d1.multiply((closingPriceList.get(i).subtract(closingPriceList.get(i-1))).divide(closingPriceList.get(i-1))));
    		//vptList.add(i, );
    	}
    	
    	
    	
    	
    	
    	for()
    	
    	
    	
    	

    	
    	
    	
    	
		return null;
    	
    }

    
	

	
	
       

}
