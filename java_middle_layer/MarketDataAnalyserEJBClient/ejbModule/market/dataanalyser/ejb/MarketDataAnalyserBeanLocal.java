package market.dataanalyser.ejb;

import javax.ejb.Local;

@Local
public interface MarketDataAnalyserBeanLocal {
	
	public void compose_message(String userName);	
	public String get_message();

}
