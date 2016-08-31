package market.dataanalyser.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import market.dataanalyser.jpa.CompareStocks;
import market.dataanalyser.jpa.MovAvgTrend;
import market.dataanalyser.jpa.Nasdaq;
import market.dataanalyser.jpa.VolumePriceTrend;

@Local
public interface MarketDataAnalyserBeanLocal {
	
	public void compose_message(String userName);	
	public String get_message();

	public List<String> listAllStocks();
	public Nasdaq fetchStockDetails(String tickerName);
	public List<Nasdaq> fetchStockVariation(String ticker, int fromDate, int toDate);
	public boolean IsArrowUp(String ticker);
	//	public List<String> listAllStocksBySegment(String filterSegment);
//	public List<String> listAllStocksByRegion(String filterRegion);
	public CompareStocks compareTwoStocks(String ticker1, String ticker2, int fromDate, int toDate);
	public List<VolumePriceTrend> calculateVolumePriceTrend(String ticker);
	public List<MovAvgTrend> calculateMovAvgTrend(String ticker);
	public List<String> listAllStocksByFilter(String filterSegment, String filterRegion, String exchangeMarket);
}
