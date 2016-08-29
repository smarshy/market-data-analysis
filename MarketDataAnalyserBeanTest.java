package market.dataanalyser.ejb;


import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import market.dataanalyser.jpa.Nasdaq;


public class MarketDataAnalyserBeanTest {


	@Test
	public void testListAllStocks() {
		//fail("Not yet implemented");
		MarketDataAnalyserBean bean=new MarketDataAnalyserBean();
		List<String> all=bean.listAllStocks();///create an object  and then call this method
		Assert.assertEquals(1776473, all.size());//hardcode the cardinality of the list
	}
	/*@Test
	public void testParticularStock() {
		//fail("Not yet implemented");
		MarketDataAnalyserBean bean=new MarketDataAnalyserBean();
		List<String> all=bean.listAllStocks();///create an object  and then call this method
			    Assert.assertEquals(true, all.contains("AACC"));//hardcode the cardinality of the list
	}
	*/
//	@Test
//	public void testStockDetailsIsInstance() {
////		fail("Not yet implemented");
//		MarketDataAnalyserBean bean=new MarketDataAnalyserBean();
//		Nasdaq data=bean.fetchStockDetails("AACC");
//		Assert.assertEquals(true, data instanceof Nasdaq);
//	}
	
//	@Test
//	public void testStockDetailsIsDataTypeCorrect() {
////		fail("Not yet implemented");
//		MarketDataAnalyserBean bean=new MarketDataAnalyserBean();
//		Nasdaq data=bean.fetchStockDetails("AACC");
//		Assert.assertEquals(true ,data.getClosingPrice() instanceof BigDecimal);
//	}

//	@Test
//	public void testFetchStockVariation() {
////		fail("Not yet implemented");
//		MarketDataAnalyserBean bean=new MarketDataAnalyserBean();
//		Nasdaq data=bean.fetchStockVariation("AACC", , , daily);
//		Assert.assertEquals(1234,data.getClass());
//		
//	}

}
