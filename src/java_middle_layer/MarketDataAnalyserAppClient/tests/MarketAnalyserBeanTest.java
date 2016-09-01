import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import market.dataanalyser.ejb.MarketDataAnalyserBeanRemote;
import market.dataanalyser.jpa.Nasdaq;
import market.dataanalyser.jpa.StockMarkets;

public class MarketAnalyserBeanTest {

	MarketDataAnalyserBeanRemote bean;
	
	@Before
	public void setUp() throws Exception {
		// Create Properties for JNDI InitialContext.
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName()); 
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		prop.put("jboss.naming.client.ejb.context", true);
		

		// Create the JNDI InitialContext.
		Context context = new InitialContext(prop);
		
		// Formulate the full JNDI name for the Diary session bean.
		String appName     = "MarketDataAnalyser";
		String moduleName  = "MarketDataAnalyserEJB";
		String beanName    = "MarketDataAnalyserBean";
		String packageName = "market.dataanalyser.ejb";
		String className   = "MarketDataAnalyserBeanRemote";
		
		// Lookup the bean using the full JNDI name.
		String fullJndiName = String.format("%s/%s/%s!%s.%s", appName, moduleName, beanName, packageName, className);
		System.out.println(fullJndiName);
		bean = (MarketDataAnalyserBeanRemote) context.lookup(fullJndiName);
	}

	@Test
	public void listAllStocks() {
	StockMarkets sm = bean.listAllStocks();
	int nasdaqSize = sm.getNasdaqStockList().size();
	int forexSize = sm.getForexStockList().size();
	int liffeSize = sm.getLiffeStockList().size();
	assertEquals(nasdaqSize,2249);
	assertEquals(forexSize,441);
	assertEquals(liffeSize,441);
	}
	
	
	@Test
	public void test_fetchLiffeDetails(){
	    String ticker2 = "CAN11";
	    String check2 = bean.fetchLiffeDetails("CAN11").getTicker();
	    System.out.println(check2);
	    assertEquals(ticker2,check2);
	}

	@Test
	public void test_fetchForexDetails(){
	    String ticker3 = "CAN11";
	    String check3 = bean.fetchForexDetails("CAN11").getTicker();
	    assertEquals(ticker3,check3);
	}

	
	@Test
	public void test_fetchNasdaqDetails() {
	    String ticker1 = "AACC";
	    Nasdaq stockDetails = bean.fetchNasdaqDetails("AACC");
	    String tickerName = stockDetails.getTicker();
	    assertEquals(ticker1,tickerName);
	}
	
	@Test
	public void test_movingAverage(){
		int validStockAverage = bean.calculateMovingAverageTrend("CAH12").size();
		//int invalidStockAverage = bean.calculateMovingAverageTrend("CAH15").size();
		assertNotEquals(validStockAverage,258);
		//assertNotEquals(invalidStockAverage,0);
	}
	
	@Test
	public void test_priceVolume(){
		int validStockPriceVolume =  bean.calculateVolumePriceTrend("AACC").size();
		assertNotEquals(validStockPriceVolume,30);
	}
	
	/*@Test
	public void test_fetchNasdaqVariation(){
		int size = 75;
		int check = bean.calculateMovingAverageTrend("AACC").size();
		assertNotEquals(size,check);
	}
	
	@Test
	public void test_fetchLiffeVariation(){
		int size = 75;
		int check = bean.calculateMovingAverageTrend("AACC").size();
		assertNotEquals(size,check);
	}
	
	@Test
	public void test_fetchForexVariation(){
		int size = 75;
		int check = bean.calculateMovingAverageTrend("AACC").size();
		assertNotEquals(size,check);
	}
	*/
	
	/*@Test
	public void test_fetchNasdaqVariation() {
	    String ticker1 = "AACC";
	    String check1 = bean.fetchNasdaqDetails("AACC").getTicker();
	    System.out.println(check1);
	    assertEquals(ticker1,check1);
	}
	*/
	



}
