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
 * Session Bean implementation class MarketDataAnalyserBean
 */
@Stateless
@Local(MarketDataAnalyserBeanLocal.class)
@Remote(MarketDataAnalyserBeanRemote.class)
@LocalBean
public class MarketDataAnalyserBean implements MarketDataAnalyserBeanRemote, MarketDataAnalyserBeanLocal {
	
	@PersistenceContext(name="MarketAnalyserJPA") 
	private EntityManager em;
	private String simpleText;
	private String message;
	
	@Override
	public List<String> listAllStocks(){
		
		Query query=em.createQuery("SELECT s.ticker from Nasdaq as s");
		@SuppressWarnings("unchecked")
		List<String> NasdaqList=query.getResultList();
		System.out.println(NasdaqList);
		return NasdaqList;
	}

	@Override
    public Nasdaq fetchStockDetails(String tickerName){
    	TypedQuery <Nasdaq> query=em.createQuery("SELECT s from Nasdaq as s where s.ticker=:tickername and exchangeDate=12032005",Nasdaq.class);//CHECK THE DATE FORMAT
    	query.setParameter("tickername",tickerName);
    	
		//CHECK EXCHANGE DATE
		Nasdaq NasdaqData=(Nasdaq) query.getResultList();
		System.out.println(NasdaqData);
		return NasdaqData;
    }
	
    @Override
	public List<BigDecimal> fetchStockVariation(String ticker, int fromDate, int toDate,String frequency){
		
		return null;
	}

	@Override
	public void compose_message(String userName) {
		String sql = "SELECT t.ticker FROM Nasdaq as t WHERE t.indexkey=2";
        System.out.println(sql);
        Query query = em.createQuery(sql);
        
        System.out.println("hi");
        
        String dbString = (String) query.getSingleResult();
		message="Hello "+ userName + "\n"+ dbString;
		System.out.println(message);
		
	}

	@Override
	public String get_message() {
		return message;
	}  

}
