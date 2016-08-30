package market.dataanalyser.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import market.dataanalyser.jpa.Nasdaq;

@Remote
public interface MarketDataAnalyserBeanRemote {
	
	public void compose_message(String userName);
	public String get_message();
	
	public List<String> listAllStocks();
	public Nasdaq fetchStockDetails(String tickerName);
	public List<Nasdaq> fetchStockVariation(String ticker, int fromDate, int toDate);
	 public boolean IsArrowUp(String ticker);
	//	public List<String> listAllStocksBySegment(String filterSegment);
//	public List<String> listAllStocksByRegion(String filterRegion);

}
