import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import market.dataanalyser.ejb.MarketDataAnalyserBeanRemote;
import market.dataanalyser.jpa.Forex;
import market.dataanalyser.jpa.Liffe;
import market.dataanalyser.jpa.MovingAverageTrend;
import market.dataanalyser.jpa.Nasdaq;
import market.dataanalyser.jpa.VolumePriceTrend;


public class MainGreeting {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
try {
			
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
			MarketDataAnalyserBeanRemote bean = (MarketDataAnalyserBeanRemote) context.lookup(fullJndiName);

			//<String> stockList=bean.listAllStocks();
			
//			for(String elem: stockList){
//				System.out.println(elem);
//			}
			//System.out.println("no. of elements: "+stockList.size());
			Nasdaq data=bean.fetchNasdaqDetails("AACC");
			System.out.println(data.isUpArrow());
			
			//bean.fetchStockVariation("AACC", 20110103, 20110113);
			//bean.IsArrowUp("AACC");
			//bean.compareTwoStocks("AACC","AAME",20110103, 20110113);
			
			/*List<String> stockList1=bean.listAllStocksByFilter("All Sectors","All Regions","Nasdaq");
			
			for(String elem1: stockList1){
				System.out.println(elem1);
			}
			*/

//			System.out.println("no. of elements: "+stockList1.size());

//			List<Nasdaq> l= bean.fetchNasdaqVariation("AACC", 20110103, 20110113);
//			System.out.println(l.get(1).getClosingPrice());
			
			/*List<Liffe> l= bean.fetchLiffeVariation("CAN11", 20110103, 20110113);
			System.out.println(l.get(1).getClosingPrice());
			
			List<Forex> f =bean.fetchForexVariation("CAN11", 20110103, 20110113);
			System.out.println(f.get(1).getClosingPrice());
			*/
			
			
			
			/*<VolumePriceTrend> v= bean.calculateVolumePriceTrend("AACC");
			for(VolumePriceTrend bd : v){

				System.out.println(bd.getVpt());
			}
			
			
			List<MovAvgTrend> m= bean.calculateMovAvgTrend("AACC");
			*/
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public MainGreeting() {
		super();
		
	}

}