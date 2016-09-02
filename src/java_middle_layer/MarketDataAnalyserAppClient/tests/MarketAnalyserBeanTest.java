import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
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
		int validStockAverageSize = bean.calculateMovingAverageTrend("AACC").size();
		assertEquals(validStockAverageSize,30);
	}
	
	@Test
	public void test_priceVolume(){
		int validStockPriceVolumeSize =  bean.calculateVolumePriceTrend("AACC").size();
		assertEquals(validStockPriceVolumeSize,30);
	}
	
	@Test
	public void test_fetchNasdaqVariation(){
		int variationSize = bean.fetchNasdaqVariation("AACC").size();
		assertEquals(variationSize,30);
	}
	
	@Test
	public void test_fetchLiffeVariation(){
		int variationSize = bean.fetchLiffeVariation("CAN11").size();
		assertEquals(variationSize,139);
	}
	
	@Test
	public void test_fetchForexVariation(){
		int variationSize = bean.fetchForexVariation("CAH12").size();
		assertEquals(variationSize,258);
	}
	
	@Test
	public void test_fetchNasdaqVariationWithDate(){
		int variationSize = bean.fetchNasdaqVariation("AACC", 20110103 , 20110208 ).size();
		assertEquals(variationSize,27);
	}
	
	@Test
	public void test_fetchLiffeVariationWithDate(){
		int variationSize = bean.fetchLiffeVariation("CAN11", 20110106 , 20110708).size();
		assertEquals(variationSize,132);
	}
	
	@Test
	public void test_fetchForexVariationWithDate(){
		int variationSize = bean.fetchForexVariation("CAH12", 20110110 , 20111230).size();
		assertEquals(variationSize,253);
	}
	
	@Test
	public void test_isArrowUp(){
		assertNotNull(bean.isArrowUp("CAH12", "Forex"));
	}
	
	@Test
	public void test_listStocksByFilter(){
		//filter only based on exchange
		List<String> filteredNasdaqList = bean.listAllStocksByFilter("All Sectors","All Regions","Nasdaq");
		int nasdaqListLength = filteredNasdaqList.size();
		List<String> filteredForexList = bean.listAllStocksByFilter("All Sectors","All Regions","Forex");
		int forexListLength = filteredForexList.size();
		List<String> filteredLiffeList = bean.listAllStocksByFilter("All Sectors","All Regions","Liffe");
		int liffeListLength = filteredLiffeList.size();
		
		//filter only based on sector and region
		List<String> filteredNasdaqOilList = bean.listAllStocksByFilter("Oil and Gas","All Regions","Nasdaq");
		int nasdaqOilListLength = filteredNasdaqOilList.size();
		List<String> filteredForexApacList = bean.listAllStocksByFilter("All Sectors","APAC","Forex");
		int forexApacListLength = filteredForexApacList.size();
		List<String> filteredTechApacLiffeList = bean.listAllStocksByFilter("Technology","APAC","Liffe");
		int liffeApacTechListLength = filteredTechApacLiffeList.size();
		
			
		assertEquals(nasdaqListLength,2249);
		assertEquals(forexListLength,441);
		assertEquals(liffeListLength,441);
		assertEquals(nasdaqOilListLength,2232);
		assertEquals(forexApacListLength,437);
		assertEquals(liffeApacTechListLength,398);
		
	}
	

}
