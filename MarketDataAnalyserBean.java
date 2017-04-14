package market.dataanalyser.ejb;


import java.math.BigDecimal;
import java.math.RoundingMode;
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
import market.dataanalyser.jpa.StockMarkets;
import market.dataanalyser.jpa.VolumePriceTrend;
import market.dataanalyser.jpa.CompareStocks;
import market.dataanalyser.jpa.Forex;
import market.dataanalyser.jpa.Liffe;
import market.dataanalyser.jpa.MovingAverageTrend;

/**
 * Session Bean implementation class MarketDataAnalyzerBean
 */
@Stateless
@Local(MarketDataAnalyserBeanLocal.class)
@Remote(MarketDataAnalyserBeanRemote.class)
@LocalBean
public class MarketDataAnalyserBean implements MarketDataAnalyserBeanRemote, MarketDataAnalyserBeanLocal {

 	
	@PersistenceContext(name="MarketAnalyserJPA")          
	EntityManager em;
	String simpleText;
	/*
	/Method to return a list of all stock tickers across all Stock exchanges.
	*/
	public StockMarkets listAllStocks(){
		Query query=em.createQuery("SELECT DISTINCT s.ticker from Nasdaq as s");
		@SuppressWarnings("unchecked")
		List<String> nasdaqStockList=query.getResultList();
		
		Query query2=em.createQuery("SELECT DISTINCT s.ticker from Liffe as s");
		@SuppressWarnings("unchecked")
		List<String> liffeStockList=query2.getResultList();
		
		Query query3=em.createQuery("SELECT DISTINCT s.ticker from Forex as s");
		@SuppressWarnings("unchecked")
		List<String> forexStockList=query3.getResultList();
		
		 //create an instance of the class StockMarkets
		StockMarkets stocksList=new StockMarkets();
		//setting the fields of the instance stocksList
		stocksList.setNasdaqStockList(nasdaqStockList);
		stocksList.setLiffeStockList(liffeStockList);
		stocksList.setForexStockList(forexStockList);
//		stocksList.setDefaultStock(fetchStockDetails("AAPL"));
		
		return stocksList;
		
		
	}
	
	
	
	
	
//	@Override
//	public List<String> listAllStocks(){
//		
//		Query query=em.createQuery("SELECT DISTINCT s.ticker from Nasdaq as s");
//		@SuppressWarnings("unchecked")
//		List<String> NasdaqList=query.getResultList();
//		return NasdaqList;
//	}
	
	public List<String> listAllStocksByFilter(String filterSegment,String filterRegion,String exchangeMarket){
		Query query = null;
		if(filterSegment.equals("allSectors") && filterRegion.equals("allRegions")){
			query=em.createQuery("SELECT DISTINCT s.ticker from " + exchangeMarket + " as s");
//		  	query.setParameter("exchangemarket",exchangeMarket);

		}
		else if(filterSegment.equals("allSectors")){
			query=em.createQuery("SELECT DISTINCT s.ticker from " + exchangeMarket + " as s where s.region=:filterregion");
		  	query.setParameter("filterregion",filterRegion);
//		  	query.setParameter("exchangemarket",exchangeMarket);
		}
		else if(filterRegion.equals("allRegions")){
			query=em.createQuery("SELECT DISTINCT s.ticker from " + exchangeMarket + " as s where s.sector=:filtersegment");
		  	query.setParameter("filtersegment",filterSegment);
//		  	query.setParameter("exchangemarket",exchangeMarket);
		}
		else{
			query=em.createQuery("SELECT DISTINCT s.ticker from " + exchangeMarket + " as s where s.sector=:filtersegment and s.region=:filterregion");
		  	query.setParameter("filtersegment",filterSegment);
		  	query.setParameter("filterregion",filterRegion);
//		  	query.setParameter("exchangemarket",exchangeMarket);
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
    public Nasdaq fetchNasdaqDetails(String tickerName){
    	//TypedQuery <Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and exchangeDate=:exchangedate",Nasdaq.class);//CHECK THE DATE FORMAT
    	TypedQuery<Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername Order by s.exchangeDate DESC ",Nasdaq.class);
    	query.setParameter("tickername",tickerName);
    	query.setMaxResults(1);
    	//query.setParameter("exchangedate",20110103);
		//CHECK EXCHANGE DATE
		Nasdaq NasdaqData= query.getSingleResult();
		NasdaqData.setUpArrow(isArrowUp(tickerName));
		return NasdaqData;
    }
	
	@Override
    public Liffe fetchLiffeDetails(String tickerName){
    	//TypedQuery <Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and exchangeDate=:exchangedate",Nasdaq.class);//CHECK THE DATE FORMAT
    	TypedQuery<Liffe> query=em.createQuery("SELECT s from Liffe as s where s.ticker=:tickername Order by s.exchangeDate DESC ",Liffe.class);
    	query.setParameter("tickername",tickerName);
    	query.setMaxResults(1);
    	//query.setParameter("exchangedate",20110103);
		//CHECK EXCHANGE DATE
		Liffe liffeData= query.getSingleResult();
		liffeData.setUpArrow(isArrowUp(tickerName));
		return liffeData;
    }
	
	@Override
    public Forex fetchForexDetails(String tickerName){
    	//TypedQuery <Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and exchangeDate=:exchangedate",Nasdaq.class);//CHECK THE DATE FORMAT
    	TypedQuery<Forex> query=em.createQuery("SELECT s from Forex as s where s.ticker=:tickername Order by s.exchangeDate DESC ",Forex.class);
    	query.setParameter("tickername",tickerName);
    	query.setMaxResults(1);
    	//query.setParameter("exchangedate",20110103);
		//CHECK EXCHANGE DATE
		Forex forexData= query.getSingleResult();
		forexData.setUpArrow(isArrowUp(tickerName));
		return forexData;
    }
	
	public List<Nasdaq> fetchNasdaqVariation(String ticker){
		TypedQuery<Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername ",Nasdaq.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",ticker);
        	
		List<Nasdaq> listOfNasdaq=query.getResultList();
//		for(Nasdaq stock: listOfNasdaq){
//			System.out.print(stock.getClosingPrice());
//			System.out.println(stock.isUpArrow());
//
//		}
		
		return listOfNasdaq;
	}
	
	public List<Liffe> fetchLiffeVariation(String ticker){
		TypedQuery<Liffe> query=em.createQuery("SELECT s from Liffe as s where s.ticker=:tickername ",Liffe.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",ticker);
        	
		List<Liffe> listOfLiffe=query.getResultList();
//		for(Nasdaq stock: listOfNasdaq){
//			System.out.print(stock.getClosingPrice());
//			System.out.println(stock.isUpArrow());
//
//		}
		
		return listOfLiffe;
	}
	
	public List<Forex> fetchForexVariation(String ticker){
		TypedQuery<Forex> query=em.createQuery("SELECT s from Forex as s where s.ticker=:tickername ",Forex.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",ticker);
        	
		List<Forex> listOfForex=query.getResultList();
		
		return listOfForex;
	}
	
	
	@Override
	//////////////stock needs to be changed to nasdaq
	public List<Nasdaq> fetchStockVariation(String ticker, int fromDate, int toDate){
		
    	
//    	if(fromDate > toDate){
//    		//THROW ERROR
//    	}
		
		
		TypedQuery<Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and s.exchangeDate BETWEEN :fromdate AND :todate",Nasdaq.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",ticker);
    	query.setParameter("fromdate", fromDate);
    	query.setParameter("todate", toDate);
        	
		List<Nasdaq> listOfNasdaq=query.getResultList();
		
		return listOfNasdaq;
	}
	
	@Override
	public List<Liffe> fetchLiffeVariation(String ticker, int fromDate, int toDate){
		
    	
//    	if(fromDate > toDate){
//    		//THROW ERROR
//    	}
		
		
		TypedQuery<Liffe> query=em.createQuery("SELECT s from Liffe as s where s.ticker=:tickername and s.exchangeDate BETWEEN :fromdate AND :todate",Liffe.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",ticker);
    	query.setParameter("fromdate", fromDate);
    	query.setParameter("todate", toDate);
        	
		List<Liffe> listOfLiffe=query.getResultList();
		
		return listOfLiffe;
	}

	@Override
	public List<Forex> fetchForexVariation(String ticker, int fromDate, int toDate){
		
    	
//    	if(fromDate > toDate){
//    		//THROW ERROR
//    	}
		
		
		TypedQuery<Forex> query=em.createQuery("SELECT s from Forex as s where s.ticker=:tickername and s.exchangeDate BETWEEN :fromdate AND :todate",Forex.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",ticker);
    	query.setParameter("fromdate", fromDate);
    	query.setParameter("todate", toDate);
        	
		List<Forex> listOfForex=query.getResultList();
		
		return listOfForex;
	}
	
    public BigDecimal isArrowUp(String ticker){
    	System.out.println("Inside isArrow");
    	Query query=em.createQuery("SELECT s.closingPrice from Nasdaq as s where s.ticker=:tickername order by s.exchangeDate DESC");
    	query.setParameter("tickername",ticker);
    	query.setMaxResults(2);
    	System.out.println("query executed");
    	@SuppressWarnings("unchecked")
		List<BigDecimal> list=query.getResultList();
    	System.out.println("result retrieved");
    	BigDecimal result=((list.get(0).subtract(list.get(1))).divide(list.get(1),RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
    	return result;
    }
   /* 
    public CompareStocks compareTwoStocks(String ticker1,String ticker2,int fromDate, int toDate,String market1,String market2){
		
    	CompareStocks compareStocks=new CompareStocks();
    	if(market1=="Liffe"&& market2=="Liffe"){
    		compareStocks.setStock1(fetchLiffeDetails(ticker1));
        	compareStocks.setStock2(fetchLiffeDetails(ticker2));
        	compareStocks.setListStock1(fetchLiffeVariation(ticker1, fromDate, toDate));
        	compareStocks.setListStock2(fetchLiffeVariation(ticker2, fromDate, toDate));
    	}
    	else if((market1=="Liffe"&& market2=="Forex") || (market1=="Forex"&& market2=="Liffe")){
    		compareStocks.setStock1(fetchLiffeDetails(ticker1));
        	compareStocks.setStock3(fetchForexDetails(ticker2));
        	compareStocks.setListStock1(fetchLiffeVariation(ticker1, fromDate, toDate));
        	compareStocks.setListStock3(fetchForexVariation(ticker2, fromDate, toDate));
    	}
    	else if(market1=="Forex"&& market2=="Forex"){
    		compareStocks.setStock3(fetchForexDetails(ticker1));
        	compareStocks.setStock4(fetchForexDetails(ticker2));
        	compareStocks.setListStock3(fetchForexVariation(ticker1, fromDate, toDate));
        	compareStocks.setListStock4(fetchForexVariation(ticker2, fromDate, toDate));
    	}
    	return compareStocks;
    	
    }*/
    
    
    public CompareStocks compareTwoStocks(String ticker1,String ticker2,int fromDate, int toDate){
    	
    	
    	CompareStocks compareStocks=new CompareStocks();
    	compareStocks.setStock1(fetchNasdaqDetails(ticker1));
    	compareStocks.setStock2(fetchNasdaqDetails(ticker2));
    	compareStocks.setStockList1(fetchStockVariation(ticker1, fromDate, toDate));
    	compareStocks.setStockList2(fetchStockVariation(ticker2, fromDate, toDate));
    	
    	
    	
    	return null;
    	
    }
    
    ////should be deleted
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
    	firstvpt.setVpt(new BigDecimal(volumeList.get(0)));
    	firstvpt.setDate(dateList.get(0));
    	vptList.add(firstvpt);
    	
    	for(int i=1;i<list.size();i++){
    		VolumePriceTrend vptNew=new VolumePriceTrend();
    		BigDecimal d1=new BigDecimal(volumeList.get(i));
    		BigDecimal d2=vptList.get(i-1).getVpt();
    		
    		vptNew.setVpt(d2.add(d1.multiply(closingPriceList.get(i).subtract(closingPriceList.get(i-1)).divide((closingPriceList.get(i-1)),2,RoundingMode.HALF_UP))));
    		vptNew.setDate(dateList.get(i));
    		vptList.add(i, vptNew);
    	}
    	
		return vptList;
    	
    }

    
	//
public List<MovingAverageTrend> calculateMovingAverageTrend(String ticker){
    	
    	Query query=em.createQuery("SELECT DISTINCT s from Nasdaq as s where s.ticker=:tickername ");
    	query.setParameter("tickername",ticker);
    	@SuppressWarnings("unchecked")
		List<Nasdaq> list=query.getResultList();
    	List<BigDecimal> closingPriceList=new ArrayList<BigDecimal>();
    	List<Integer> dateList=new ArrayList<Integer>();
    	List<MovingAverageTrend> maList=new ArrayList<MovingAverageTrend>();
    	for(Nasdaq n: list){
        	closingPriceList.add(n.getClosingPrice());
        	dateList.add(n.getExchangeDate());
        	
    	}
    	MovingAverageTrend firstMA=new MovingAverageTrend();
    	firstMA.setMa(closingPriceList.get(0));
    	firstMA.setDate(dateList.get(0));
    	MovingAverageTrend secondMA=new MovingAverageTrend();
    	secondMA.setMa(closingPriceList.get(1));
    	secondMA.setDate(dateList.get(1));
    	
    	maList.add(firstMA);
    	maList.add(secondMA);
    	
    	for(int i=2;i<list.size();i++){
    		MovingAverageTrend maNew=new MovingAverageTrend();
    		
    		BigDecimal d1=maList.get(i-2).getMa();
    		BigDecimal d2=maList.get(i-1).getMa();
    		BigDecimal d3=closingPriceList.get(i);
    		maNew.setMa((d1.add(d2).add(d3)).divide(new BigDecimal(3),2,RoundingMode.HALF_UP));
    		//vptNew.setVpt(d2.add(d1.multiply(closingPriceList.get(i).subtract(closingPriceList.get(i-1)).divide((closingPriceList.get(i-1)),2,RoundingMode.HALF_UP))));
    		maNew.setDate(dateList.get(i));
    		maList.add(i, maNew);
    	}
    	
		return maList;
    	
    }


}
