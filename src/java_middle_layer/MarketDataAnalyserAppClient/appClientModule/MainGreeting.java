import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import market.dataanalyser.ejb.MarketDataAnalyserBeanRemote;
import market.dataanalyser.jpa.MovAvgTrend;
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
			//Nasdaq data=(Nasdaq)bean.fetchStockDetails("AACC");
			//System.out.println(data.toString());
			
			//bean.fetchStockVariation("AACC", 20110103, 20110113);
			//bean.IsArrowUp("AACC");
			//bean.compareTwoStocks("AACC","AAME",20110103, 20110113);
			
			/*List<String> stockList1=bean.listAllStocksByFilter("","EMEA","Nasdaq");
			
			for(String elem1: stockList1){
				System.out.println(elem1);
			}

			System.out.println("no. of elements: "+stockList1.size());
			*/
			
			
			
			List<VolumePriceTrend> v= bean.calculateVolumePriceTrend("AACC");
			for(VolumePriceTrend bd : v){

				System.out.println(bd.getVpt());
			}
			
			List<MovAvgTrend> m= bean.calculateMovAvgTrend("AACC");
			for(MovAvgTrend bd : m){

				System.out.println(bd.getMa());
			}
			
			
			
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